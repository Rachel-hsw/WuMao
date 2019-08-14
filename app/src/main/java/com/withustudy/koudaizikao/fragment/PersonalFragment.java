package com.withustudy.koudaizikao.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.Gson;
import com.umeng.analytics.MobclickAgent;
import com.withustudy.koudaizikao.action.AbsBaseUrlConstructor;
import com.withustudy.koudaizikao.action.UrlFactory;
import com.withustudy.koudaizikao.activity.AboutActivity;
import com.withustudy.koudaizikao.activity.ChooseProfessionActivity;
import com.withustudy.koudaizikao.activity.ChooseSubjectActivity;
import com.withustudy.koudaizikao.activity.LoginActivity;
import com.withustudy.koudaizikao.activity.MyPostActivity;
import com.withustudy.koudaizikao.activity.SuggestionActivity;
import com.withustudy.koudaizikao.activity.UpdateNewActivity;
import com.withustudy.koudaizikao.activity.UpdateNoneActivity;
import com.withustudy.koudaizikao.base.AbsBaseFragment;
import com.withustudy.koudaizikao.config.Constants.Subject;
import com.withustudy.koudaizikao.config.KouDaiSP;
import com.withustudy.koudaizikao.custom.SharePopWindow;
import com.withustudy.koudaizikao.entity.ChooseProToChooseSub;
import com.withustudy.koudaizikao.entity.PersonalInfo;
import com.withustudy.koudaizikao.entity.Subject;
import com.withustudy.koudaizikao.entity.Version;
import com.withustudy.koudaizikao.entity.content.MajorContent;
import com.withustudy.koudaizikao.entity.req.MajorUpLoad;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PersonalFragment extends AbsBaseFragment
{
  public static final int ACTION_GET_MAJOR_AND_SUBJECT = 11;
  public static final int ACTION_GET_USER = 10;
  public static final int ACTION_UPDATE = 12;
  public static final int GET_MAJOR_AND_SUBJECT = 1;
  public static final int GET_MAJOR_AND_SUBJECT_FAIL = 2;
  public static final int INIT_AVATAR_AND_NICKNAME = 5;
  public static final int START_GET_MAJOR_AND_SUBJECT = 3;
  public static final int START_GET_PERSONAL = 4;
  public static PersonalHandler mHandler;
  private Button buttonExit;
  private boolean isLogin;
  private LinearLayout layoutSubject;
  public List<Subject> list;
  private LinearLayout mAbout;
  private CallBackListener mBackListener;
  private PersonalInfo mPersonalInfo = null;
  private PersonalLoginFragment mPersonalLoginFragment;
  private PersonalUnLoginFragment mPersonalUnLoginFragment;
  private LinearLayout mPerssional;
  private LinearLayout mPost;
  private LinearLayout mPraise;
  private LinearLayout mReply;
  private ScrollView mScrollView;
  private LinearLayout mShare;
  private LinearLayout mSubject;
  private LinearLayout mSuggestion;
  private LinearLayout mUpdate;
  private TextView textProfessional;
  private TextView textTitle;
  private TextView textUpdate;

  private void initMajorAndSubject()
  {
    this.textProfessional.setText(getResources().getString(2131165230));
    this.list.clear();
    Subject localSubject = new Subject();
    localSubject.setName(getResources().getString(2131165230));
    this.list.add(localSubject);
  }

  private void initTop()
  {
    if (this.isLogin)
    {
      this.textTitle.setVisibility(0);
      this.mFragmentManager.beginTransaction().replace(2131100152, this.mPersonalLoginFragment).commit();
      this.buttonExit.setVisibility(0);
      return;
    }
    this.textTitle.setVisibility(8);
    this.mFragmentManager.beginTransaction().replace(2131100152, this.mPersonalUnLoginFragment).commit();
    this.buttonExit.setVisibility(8);
  }

  private boolean isLogin()
  {
    if (!this.isLogin)
    {
      Toast.makeText(this.mContext, getResources().getString(2131165232), 0).show();
      return false;
    }
    return this.isLogin;
  }

  private void setSubject()
  {
    this.layoutSubject.removeAllViews();
    int i = 0;
    if (i >= this.list.size())
    {
      if (Constants.Subject.sId != null)
        break label134;
      Constants.Subject.sId = new ArrayList();
    }
    label38: for (int j = 0; ; j++)
    {
      if (j >= this.list.size())
      {
        return;
        TextView localTextView = new TextView(this.mContext);
        LayoutParams localLayoutParams = new LayoutParams(-1, -2);
        localTextView.setText(((Subject)this.list.get(i)).getName());
        localTextView.setTextColor(Color.parseColor("#999999"));
        localTextView.setTextSize(2, 15.0F);
        localTextView.setLayoutParams(localLayoutParams);
        this.layoutSubject.addView(localTextView);
        i++;
        break;
        Constants.Subject.sId.clear();
        break label38;
      }
      Constants.Subject.sId.add(((Subject)this.list.get(j)).getId());
    }
  }

  public void bindData()
  {
    this.textUpdate.setText(this.mSP.getVersionName());
    initMajorAndSubject();
    if (this.isLogin)
    {
      AbsBaseUrlConstructor localAbsBaseUrlConstructor = UrlFactory.getInstance().getMajorSubject();
      String[] arrayOfString = new String[1];
      arrayOfString[0] = this.mSP.getUserId();
      localAbsBaseUrlConstructor.constructUrl(this, arrayOfString, 11, this.mContext);
      mHandler.sendEmptyMessageDelayed(5, 500L);
    }
  }

  public void initData()
  {
    if (!this.mSP.getUserId().equals(""));
    for (this.isLogin = true; ; this.isLogin = false)
    {
      this.mPersonalLoginFragment = new PersonalLoginFragment();
      this.mPersonalUnLoginFragment = new PersonalUnLoginFragment();
      this.mBackListener = new CallBackListener();
      mHandler = new PersonalHandler();
      this.list = new ArrayList();
      return;
    }
  }

  public void initListener()
  {
    this.mPerssional.setOnClickListener(this.mBackListener);
    this.textProfessional.setOnClickListener(this.mBackListener);
    this.mSubject.setOnClickListener(this.mBackListener);
    this.layoutSubject.setOnClickListener(this.mBackListener);
    this.mPost.setOnClickListener(this.mBackListener);
    this.mReply.setOnClickListener(this.mBackListener);
    this.mSuggestion.setOnClickListener(this.mBackListener);
    this.mShare.setOnClickListener(this.mBackListener);
    this.mUpdate.setOnClickListener(this.mBackListener);
    this.mPraise.setOnClickListener(this.mBackListener);
    this.mAbout.setOnClickListener(this.mBackListener);
    this.buttonExit.setOnClickListener(this.mBackListener);
  }

  public void initView(View paramView)
  {
    this.mScrollView = ((ScrollView)paramView.findViewById(2131100151));
    this.textTitle = ((TextView)paramView.findViewById(2131100150));
    this.textProfessional = ((TextView)paramView.findViewById(2131100154));
    this.layoutSubject = ((LinearLayout)paramView.findViewById(2131100156));
    this.mPerssional = ((LinearLayout)paramView.findViewById(2131100153));
    this.mSubject = ((LinearLayout)paramView.findViewById(2131100155));
    this.mPost = ((LinearLayout)paramView.findViewById(2131100157));
    this.mReply = ((LinearLayout)paramView.findViewById(2131100158));
    this.mSuggestion = ((LinearLayout)paramView.findViewById(2131100159));
    this.mShare = ((LinearLayout)paramView.findViewById(2131100160));
    this.mUpdate = ((LinearLayout)paramView.findViewById(2131100161));
    this.textUpdate = ((TextView)paramView.findViewById(2131100162));
    this.mPraise = ((LinearLayout)paramView.findViewById(2131100163));
    this.mAbout = ((LinearLayout)paramView.findViewById(2131100164));
    this.buttonExit = ((Button)paramView.findViewById(2131100165));
  }

  public void onError(String paramString1, String paramString2, int paramInt)
  {
    super.onError(paramString1, paramString2, paramInt);
    Toast.makeText(this.mContext, "服务器未响应，请稍后再试", 0).show();
  }

  public View onInflaterRootView(LayoutInflater paramLayoutInflater, Bundle paramBundle)
  {
    return paramLayoutInflater.inflate(2130903119, null);
  }

  public void onResume()
  {
    super.onResume();
    initTop();
    setSubject();
  }

  public void onSuccess(String paramString1, Map<String, String> paramMap, String paramString2, int paramInt)
  {
    super.onSuccess(paramString1, paramMap, paramString2, paramInt);
    switch (paramInt)
    {
    default:
    case 10:
    case 11:
    case 12:
    }
    do
    {
      do
        while (true)
        {
          return;
          if (paramString1 == null)
            continue;
          try
          {
            this.mPersonalInfo = ((PersonalInfo)UrlFactory.getInstanceGson().fromJson(paramString1, PersonalInfo.class));
            if (this.mPersonalInfo != null)
            {
              this.mPersonalLoginFragment.setNickName(this.mPersonalInfo.getNickname());
              this.mSP.setNickName(this.mPersonalInfo.getNickname());
              if (this.mPersonalInfo.getProfileUrl().equals(""))
                continue;
              this.mPersonalLoginFragment.setAvatar(this.mPersonalInfo.getProfileUrl());
              this.mSP.setAvatar(this.mPersonalInfo.getProfileUrl());
              return;
            }
            Toast.makeText(this.mContext, "服务器未能返回数据，请稍后再试", 0).show();
            return;
          }
          catch (Exception localException4)
          {
            localException4.printStackTrace();
            Toast.makeText(this.mContext, "服务器未响应，请稍后再试", 0).show();
            return;
          }
        }
      while (paramString1 == null);
      try
      {
        MajorContent localMajorContent = (MajorContent)UrlFactory.getInstanceGson().fromJson(paramString1, MajorContent.class);
        if ((localMajorContent != null) && (localMajorContent.getMajor() != null) && (localMajorContent.getSubject() != null))
        {
          mHandler.sendMessage(mHandler.obtainMessage(1, localMajorContent));
          return;
        }
        mHandler.sendEmptyMessage(2);
        return;
      }
      catch (Exception localException3)
      {
        localException3.printStackTrace();
        Toast.makeText(this.mContext, "服务器未响应，请稍后再试", 0).show();
        return;
      }
    }
    while (paramString1 == null);
    try
    {
      Version localVersion = (Version)UrlFactory.getInstanceGson().fromJson(paramString1, Version.class);
      if (localVersion != null)
      {
        if (localVersion.getStatus().equals("STATUS_OK"))
        {
          Bundle localBundle = new Bundle();
          try
          {
            localBundle.putSerializable("update", localVersion);
            startNewActivity(UpdateNewActivity.class, 0, 0, false, localBundle);
            return;
          }
          catch (Exception localException1)
          {
          }
          label335: localException1.printStackTrace();
          Toast.makeText(this.mContext, "服务器未响应，请稍后再试", 0).show();
          return;
        }
        startNewActivity(UpdateNoneActivity.class, 0, 0, false, null);
        return;
      }
      Toast.makeText(this.mContext, "服务器未能返回数据，请稍后再试", 0).show();
      return;
    }
    catch (Exception localException2)
    {
      break label335;
    }
  }

  class CallBackListener
    implements OnClickListener
  {
    CallBackListener()
    {
    }

    public void onClick(View paramView)
    {
      switch (paramView.getId())
      {
      case 2131100162:
      default:
        return;
      case 2131100153:
      case 2131100154:
        MobclickAgent.onEvent(PersonalFragment.this.mContext, "personal_choose_major");
        if (PersonalFragment.this.isLogin())
        {
          PersonalFragment.this.startNewActivity(ChooseProfessionActivity.class, 2130968581, 2130968579, false, null);
          return;
        }
        Intent localIntent5 = new Intent(PersonalFragment.this.mContext.getApplicationContext(), LoginActivity.class);
        localIntent5.putExtra("flag", "0");
        PersonalFragment.this.startActivity(localIntent5);
        return;
      case 2131100155:
      case 2131100156:
        MobclickAgent.onEvent(PersonalFragment.this.mContext, "personal_choose_subject");
        if (PersonalFragment.this.isLogin())
        {
          if ((PersonalFragment.this.mSP.getProId().equals("")) || (PersonalFragment.this.mSP.getProName().equals("")) || (PersonalFragment.this.mSP.getMajorId().equals("")) || (PersonalFragment.this.mSP.getMajorName().equals("")))
          {
            Toast.makeText(PersonalFragment.this.mContext, PersonalFragment.this.getResources().getString(2131165231), 0).show();
            return;
          }
          ChooseProToChooseSub localChooseProToChooseSub = new ChooseProToChooseSub();
          localChooseProToChooseSub.setProId(PersonalFragment.this.mSP.getProId());
          localChooseProToChooseSub.setProvName(PersonalFragment.this.mSP.getProName());
          localChooseProToChooseSub.setMajorId(PersonalFragment.this.mSP.getMajorId());
          localChooseProToChooseSub.setMajorName(PersonalFragment.this.mSP.getMajorName());
          Bundle localBundle3 = new Bundle();
          localBundle3.putSerializable("Major", localChooseProToChooseSub);
          PersonalFragment.this.startNewActivity(ChooseSubjectActivity.class, false, localBundle3);
          return;
        }
        Intent localIntent4 = new Intent(PersonalFragment.this.mContext.getApplicationContext(), LoginActivity.class);
        localIntent4.putExtra("flag", "0");
        PersonalFragment.this.startActivity(localIntent4);
        return;
      case 2131100157:
        MobclickAgent.onEvent(PersonalFragment.this.mContext, "personal_my_post");
        if (PersonalFragment.this.isLogin())
        {
          Bundle localBundle2 = new Bundle();
          localBundle2.putInt("state", 100);
          PersonalFragment.this.startNewActivity(MyPostActivity.class, false, localBundle2);
          return;
        }
        Intent localIntent3 = new Intent(PersonalFragment.this.mContext.getApplicationContext(), LoginActivity.class);
        localIntent3.putExtra("flag", "0");
        PersonalFragment.this.startActivity(localIntent3);
        return;
      case 2131100158:
        MobclickAgent.onEvent(PersonalFragment.this.mContext, "personal_my_reply");
        if (PersonalFragment.this.isLogin())
        {
          Bundle localBundle1 = new Bundle();
          localBundle1.putInt("state", 101);
          PersonalFragment.this.startNewActivity(MyPostActivity.class, false, localBundle1);
          return;
        }
        Intent localIntent2 = new Intent(PersonalFragment.this.mContext.getApplicationContext(), LoginActivity.class);
        localIntent2.putExtra("flag", "0");
        PersonalFragment.this.startActivity(localIntent2);
        return;
      case 2131100159:
        PersonalFragment.this.startNewActivity(SuggestionActivity.class, false, null);
        return;
      case 2131100160:
        MobclickAgent.onEvent(PersonalFragment.this.mContext, "personal_share");
        new SharePopWindow(PersonalFragment.this.getActivity(), PersonalFragment.this.buttonExit).showPop();
        return;
      case 2131100161:
        MobclickAgent.onEvent(PersonalFragment.this.mContext, "personal_update");
        AbsBaseUrlConstructor localAbsBaseUrlConstructor = UrlFactory.getInstance().Update();
        PersonalFragment localPersonalFragment = PersonalFragment.this;
        String[] arrayOfString = new String[2];
        arrayOfString[0] = PersonalFragment.access$3(PersonalFragment.this).getUserId();
        arrayOfString[1] = PersonalFragment.access$3(PersonalFragment.this).getVersionName();
        localAbsBaseUrlConstructor.constructUrl(localPersonalFragment, arrayOfString, 12, PersonalFragment.this.mContext);
        return;
      case 2131100163:
        MobclickAgent.onEvent(PersonalFragment.this.mContext, "personal_android_market");
        Intent localIntent1 = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + PersonalFragment.this.mContext.getPackageName()));
        localIntent1.addFlags(268435456);
        PersonalFragment.this.startActivity(localIntent1);
        return;
      case 2131100164:
        MobclickAgent.onEvent(PersonalFragment.this.mContext, "personal_about");
        PersonalFragment.this.startNewActivity(AboutActivity.class, false, null);
        return;
      case 2131100165:
      }
      MobclickAgent.onEvent(PersonalFragment.this.mContext, "personal_exit");
      PersonalFragment.this.mSP.clearUserInfo();
      PersonalFragment.this.initMajorAndSubject();
      PersonalFragment.this.setSubject();
      PersonalFragment.this.isLogin = false;
      PersonalFragment.this.textTitle.setVisibility(8);
      PersonalFragment.this.mFragmentManager.beginTransaction().replace(2131100152, new PersonalUnLoginFragment()).commit();
      PersonalFragment.this.buttonExit.setVisibility(8);
      PersonalFragment.this.mScrollView.scrollTo(0, 0);
    }
  }

  public class PersonalHandler extends Handler
  {
    public PersonalHandler()
    {
    }

    public void handleMessage(Message paramMessage)
    {
      super.handleMessage(paramMessage);
      switch (paramMessage.what)
      {
      case 2:
      default:
      case 1:
        do
          return;
        while (!PersonalFragment.this.isLogin);
        MajorContent localMajorContent = (MajorContent)paramMessage.obj;
        PersonalFragment.this.textProfessional.setText(localMajorContent.getMajor().getProvName() + "," + localMajorContent.getMajor().getMajorName());
        PersonalFragment.this.list.clear();
        PersonalFragment.this.list.addAll(localMajorContent.getSubject());
        PersonalFragment.this.setSubject();
        PersonalFragment.this.mSP.setProId(localMajorContent.getMajor().getProvId());
        PersonalFragment.this.mSP.setProName(localMajorContent.getMajor().getProvName());
        PersonalFragment.this.mSP.setMajorId(localMajorContent.getMajor().getMajorId());
        PersonalFragment.this.mSP.setMajorName(localMajorContent.getMajor().getMajorName());
        return;
      case 4:
        AbsBaseUrlConstructor localAbsBaseUrlConstructor3 = UrlFactory.getInstance().personal();
        PersonalFragment localPersonalFragment3 = PersonalFragment.this;
        String[] arrayOfString3 = new String[1];
        arrayOfString3[0] = PersonalFragment.access$3(PersonalFragment.this).getUserId();
        localAbsBaseUrlConstructor3.constructUrl(localPersonalFragment3, arrayOfString3, 10, PersonalFragment.this.mContext);
        return;
      case 3:
        AbsBaseUrlConstructor localAbsBaseUrlConstructor2 = UrlFactory.getInstance().getMajorSubject();
        PersonalFragment localPersonalFragment2 = PersonalFragment.this;
        String[] arrayOfString2 = new String[1];
        arrayOfString2[0] = PersonalFragment.access$3(PersonalFragment.this).getUserId();
        localAbsBaseUrlConstructor2.constructUrl(localPersonalFragment2, arrayOfString2, 11, PersonalFragment.this.mContext);
        return;
      case 5:
      }
      if ((!PersonalFragment.this.mSP.getAvatar().equals("")) && (!PersonalFragment.this.mSP.getNickName().equals("")))
      {
        PersonalFragment.this.mPersonalLoginFragment.setNickName(PersonalFragment.this.mSP.getNickName());
        PersonalFragment.this.mPersonalLoginFragment.setAvatar(PersonalFragment.this.mSP.getAvatar());
        return;
      }
      AbsBaseUrlConstructor localAbsBaseUrlConstructor1 = UrlFactory.getInstance().personal();
      PersonalFragment localPersonalFragment1 = PersonalFragment.this;
      String[] arrayOfString1 = new String[1];
      arrayOfString1[0] = PersonalFragment.access$3(PersonalFragment.this).getUserId();
      localAbsBaseUrlConstructor1.constructUrl(localPersonalFragment1, arrayOfString1, 10, PersonalFragment.this.mContext);
    }
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.fragment.PersonalFragment
 * JD-Core Version:    0.6.0
 */