package com.withustudy.koudaizikao.activity;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore.Images.Media;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.android.http.FileDownLoad;
import com.android.http.FileUpload;
import com.google.gson.Gson;
import com.withustudy.koudaizikao.action.AbsBaseUrlConstructor;
import com.withustudy.koudaizikao.action.AbsSimpleImplUrlFactory;
import com.withustudy.koudaizikao.action.UrlFactory;
import com.withustudy.koudaizikao.base.AbsBaseActivity;
import com.withustudy.koudaizikao.config.KouDaiSP;
import com.withustudy.koudaizikao.custom.LoadingView;
import com.withustudy.koudaizikao.entity.PersonalInfo;
import com.withustudy.koudaizikao.entity.content.ResultState;
import com.withustudy.koudaizikao.entity.req.ChangePersonalInfo;
import com.withustudy.koudaizikao.entity.req.UserBaseInfo;
import com.withustudy.koudaizikao.fragment.PersonalFragment;
import com.withustudy.koudaizikao.fragment.PersonalFragment.PersonalHandler;
import com.withustudy.koudaizikao.tools.FileTools;
import com.withustudy.koudaizikao.tools.LogUtils;
import com.withustudy.koudaizikao.tools.ProTools;
import com.withustudy.koudaizikao.tools.ToolsUtils;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PersonalSetActivity extends AbsBaseActivity
{
  public static final int ACTION_GET_PERSONAL = 10;
  public static final int ACTION_SET_AVATAR = 11;
  public static final int ACTION_SET_SEX = 12;
  public static final int DISMISS_DIALOG = 6;
  public static final int REQUEST_GET_BITMAP = 20;
  public static final int REQUEST_SET_NICKNAME = 21;
  public static final int SET_AVATAR_FAIL = 4;
  public static final int SET_AVATAR_FINISH = 2;
  public static final int SET_SEX_FAIL = 5;
  public static final int SET_SEX_FINISH = 3;
  public static final int UPLOAD_FINISH = 1;
  private Button buttonBack;
  private File file;
  private ImageView imageAvatar;
  private CallBackListener mBackListener;
  private PersonalSetHandler mHandler;
  private LinearLayout mLayoutContent;
  private LoadingView mLoadingView;
  private LinearLayout mNickName;
  private LinearLayout mPassword;
  private PersonalInfo mPersonalInfo;
  private LinearLayout mPhone;
  private LinearLayout mQQ;
  private LinearLayout mWeiBo;
  private LinearLayout mWeiXin;
  private TextView textNickname;
  private TextView textPhone;
  private TextView textQQ;
  private TextView[] textSex;
  private TextView textWeiBo;
  private TextView textWeiXin;

  private void getPersonal()
  {
    AbsBaseUrlConstructor localAbsBaseUrlConstructor = UrlFactory.getInstance().personal();
    String[] arrayOfString = new String[2];
    arrayOfString[0] = this.mSP.getUserId();
    arrayOfString[1] = "get";
    localAbsBaseUrlConstructor.constructUrl(this, arrayOfString, 10, this.mContext);
  }

  private String hidePhoneNumber(String paramString)
  {
    if (paramString.length() != 11)
      return null;
    try
    {
      Long.valueOf(paramString);
      String str1 = paramString.substring(0, 3);
      String str2 = paramString.substring(7, 11);
      String str3 = str1 + "****" + str2;
      return str3;
    }
    catch (NumberFormatException localNumberFormatException)
    {
    }
    return null;
  }

  private void setSex(int paramInt)
  {
    int i = 0;
    if (i >= this.textSex.length)
      return;
    if (i == paramInt)
    {
      this.textSex[i].setBackgroundColor(Color.parseColor("#00B8E6"));
      this.textSex[i].setTextColor(Color.parseColor("#ffffff"));
    }
    while (true)
    {
      i++;
      break;
      this.textSex[i].setBackgroundColor(Color.parseColor("#ffffff"));
      this.textSex[i].setTextColor(Color.parseColor("#999999"));
    }
  }

  protected void bindData()
  {
    this.mLayoutContent.setVisibility(8);
    this.mLoadingView.setVisibility(0);
    setSex(2);
    getPersonal();
  }

  public void finish()
  {
    super.finish();
    if (this.file != null)
      this.mFileTools.deleteFile(this.file.getName());
  }

  public void finish(int paramInt1, int paramInt2)
  {
    super.finish(paramInt1, paramInt2);
    if (this.file != null)
      this.mFileTools.deleteFile(this.file.getName());
  }

  protected void initData()
  {
    this.mHandler = new PersonalSetHandler();
    this.mBackListener = new CallBackListener();
  }

  protected void initListener()
  {
    this.buttonBack.setOnClickListener(this.mBackListener);
    for (int i = 0; ; i++)
    {
      if (i >= this.textSex.length)
      {
        this.mNickName.setOnClickListener(this.mBackListener);
        this.mPhone.setOnClickListener(this.mBackListener);
        this.mPassword.setOnClickListener(this.mBackListener);
        this.imageAvatar.setOnClickListener(this.mBackListener);
        return;
      }
      this.textSex[i].setOnClickListener(this.mBackListener);
    }
  }

  protected void initView()
  {
    this.buttonBack = ((Button)findViewById(2131099850));
    this.mLoadingView = ((LoadingView)findViewById(2131099869));
    this.mLayoutContent = ((LinearLayout)findViewById(2131099851));
    this.imageAvatar = ((ImageView)findViewById(2131099853));
    this.textNickname = ((TextView)findViewById(2131099854));
    this.mNickName = ((LinearLayout)findViewById(2131099855));
    this.mPhone = ((LinearLayout)findViewById(2131099856));
    this.textPhone = ((TextView)findViewById(2131099857));
    this.textSex = new TextView[3];
    this.textSex[0] = ((TextView)findViewById(2131099859));
    this.textSex[1] = ((TextView)findViewById(2131099860));
    this.textSex[2] = ((TextView)findViewById(2131099861));
    this.mPassword = ((LinearLayout)findViewById(2131099862));
    this.mWeiXin = ((LinearLayout)findViewById(2131099863));
    this.textWeiXin = ((TextView)findViewById(2131099864));
    this.mWeiBo = ((LinearLayout)findViewById(2131099865));
    this.textWeiBo = ((TextView)findViewById(2131099866));
    this.mQQ = ((LinearLayout)findViewById(2131099867));
    this.textQQ = ((TextView)findViewById(2131099868));
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    switch (paramInt1)
    {
    default:
    case 20:
    case 21:
    }
    while (true)
    {
      super.onActivityResult(paramInt1, paramInt2, paramIntent);
      return;
      if (paramInt2 == -1)
      {
        if (paramIntent == null)
          continue;
        try
        {
          Uri localUri = paramIntent.getData();
          if (localUri == null)
            continue;
          ContentResolver localContentResolver = getContentResolver();
          Options localOptions = new Options();
          localOptions.inSampleSize = 4;
          localObject = BitmapFactory.decodeStream(localContentResolver.openInputStream(localUri), null, localOptions);
          i = ((Bitmap)localObject).getWidth();
          j = ((Bitmap)localObject).getHeight();
          if (i > j)
          {
            localObject = Bitmap.createBitmap((Bitmap)localObject, (i - j) / 2, 0, j, j);
            if (this.mFileTools.saveBitmapToSDCard(this.file, (Bitmap)localObject) == null)
              continue;
            ((Bitmap)localObject).recycle();
            ArrayList localArrayList = new ArrayList();
            localArrayList.add(this.file);
            this.mProTools.startDialog("正在上传头像");
            new FileUpload(localArrayList, new String[] { "account" }, this.mHandler).start();
          }
        }
        catch (IOException localIOException)
        {
          while (true)
          {
            int i;
            int j;
            localIOException.printStackTrace();
            break;
            if (j <= i)
              continue;
            int k = j - i;
            Bitmap localBitmap = Bitmap.createBitmap((Bitmap)localObject, 0, k / 2, i, i);
            Object localObject = localBitmap;
          }
        }
        catch (OutOfMemoryError localOutOfMemoryError)
        {
          Toast.makeText(this.mContext, "图片过大，小袋内存不够用啦!", 0).show();
          localOutOfMemoryError.printStackTrace();
        }
        continue;
      }
      if (this.file == null)
        continue;
      this.mFileTools.deleteFile(this.file.getName());
      continue;
      getPersonal();
    }
  }

  public void onError(String paramString1, String paramString2, int paramInt)
  {
    super.onError(paramString1, paramString2, paramInt);
    this.mHandler.sendEmptyMessage(4);
    switch (paramInt)
    {
    default:
      return;
    case 11:
    }
    this.mHandler.sendEmptyMessage(6);
  }

  protected void onResume()
  {
    super.onResume();
  }

  public void onSuccess(String paramString1, Map<String, String> paramMap, String paramString2, int paramInt)
  {
    super.onSuccess(paramString1, paramMap, paramString2, paramInt);
    this.mLoadingView.setVisibility(8);
    this.mLayoutContent.setVisibility(0);
    switch (paramInt)
    {
    default:
    case 10:
    case 11:
    case 12:
    }
    label227: 
    do
    {
      do
      {
        while (true)
        {
          return;
          if (paramString1 == null)
            continue;
          while (true)
          {
            try
            {
              this.mPersonalInfo = ((PersonalInfo)UrlFactory.getInstanceGson().fromJson(paramString1, PersonalInfo.class));
              if (this.mPersonalInfo == null)
                break label271;
              if (!this.mPersonalInfo.getNickname().equals(""))
              {
                this.textNickname.setText(this.mPersonalInfo.getNickname());
                this.textPhone.setText(hidePhoneNumber(this.mPersonalInfo.getPhoneNumber()));
                if (this.mPersonalInfo.getProfileUrl().equals(""))
                  continue;
                this.mFileDownLoad.downLoadImage(this.mPersonalInfo.getProfileUrl(), this.imageAvatar, 1000);
                if (!this.mPersonalInfo.getGender().equals("MALE"))
                  break label227;
                setSex(0);
                return;
              }
            }
            catch (Exception localException3)
            {
              localException3.printStackTrace();
              Toast.makeText(this.mContext, "服务器未响应，请稍后再试", 0).show();
              return;
            }
            this.textNickname.setText("小袋");
            continue;
            if (this.mPersonalInfo.getGender().equals("FEMALE"))
            {
              setSex(1);
              return;
            }
            if (!this.mPersonalInfo.getGender().equals("DEFAULT"))
              break;
            setSex(2);
            return;
          }
        }
        Toast.makeText(this.mContext, "服务器未能返回数据，请稍后再试", 0).show();
        return;
        this.mHandler.sendEmptyMessage(6);
      }
      while (paramString1 == null);
      try
      {
        ResultState localResultState2 = (ResultState)UrlFactory.getInstanceGson().fromJson(paramString1, ResultState.class);
        if ((localResultState2 != null) && (localResultState2.getState().equals("STATE_OK")))
        {
          this.mHandler.sendMessage(this.mHandler.obtainMessage(2));
          return;
        }
      }
      catch (Exception localException2)
      {
        localException2.printStackTrace();
        Toast.makeText(this.mContext, "服务器未响应，请稍后再试", 0).show();
        return;
      }
      this.mHandler.sendEmptyMessage(4);
      return;
    }
    while (paramString1 == null);
    try
    {
      label271: ResultState localResultState1 = (ResultState)UrlFactory.getInstanceGson().fromJson(paramString1, ResultState.class);
      if ((localResultState1 != null) && (localResultState1.getState().equals("STATE_OK")))
      {
        this.mHandler.sendEmptyMessage(3);
        return;
      }
    }
    catch (Exception localException1)
    {
      localException1.printStackTrace();
      Toast.makeText(this.mContext, "服务器未响应，请稍后再试", 0).show();
      return;
    }
    this.mHandler.sendEmptyMessage(5);
  }

  protected void setContentView()
  {
    setContentView(2130903065);
  }

  class CallBackListener
    implements OnClickListener
  {
    CallBackListener()
    {
    }

    public void onClick(View paramView)
    {
      ChangePersonalInfo localChangePersonalInfo = new ChangePersonalInfo();
      localChangePersonalInfo.setVersionName(PersonalSetActivity.this.mSP.getVersionName());
      localChangePersonalInfo.setClientType(ToolsUtils.getSDK());
      localChangePersonalInfo.setImei(ToolsUtils.getImei(PersonalSetActivity.this.mContext));
      localChangePersonalInfo.setNet(ToolsUtils.getStringNetworkType(PersonalSetActivity.this.mContext));
      localChangePersonalInfo.setUid(PersonalSetActivity.this.mSP.getUserId());
      switch (paramView.getId())
      {
      case 2131099851:
      case 2131099852:
      case 2131099854:
      case 2131099857:
      case 2131099858:
      default:
      case 2131099850:
      case 2131099855:
      case 2131099856:
        do
        {
          return;
          PersonalSetActivity.this.finish();
          return;
          Bundle localBundle = new Bundle();
          localBundle.putString("nickname", PersonalSetActivity.this.mPersonalInfo.getNickname());
          PersonalSetActivity.this.startNewActivityForResult(SetNickNameActivity.class, 21, localBundle);
          return;
        }
        while (!PersonalSetActivity.this.mPersonalInfo.getPhoneNumber().equals(""));
        PersonalSetActivity.this.startNewActivityForResult(BindPhoneActivity.class, 21, null);
        return;
      case 2131099859:
        PersonalSetActivity.this.setSex(0);
        UserBaseInfo localUserBaseInfo3 = new UserBaseInfo();
        localUserBaseInfo3.setGender("MALE");
        localChangePersonalInfo.setUserBasicInfo(localUserBaseInfo3);
        UrlFactory.getInstance().changePersonalInfo().constructUrl(PersonalSetActivity.this, localChangePersonalInfo, 12);
        return;
      case 2131099860:
        PersonalSetActivity.this.setSex(1);
        UserBaseInfo localUserBaseInfo2 = new UserBaseInfo();
        localUserBaseInfo2.setGender("FEMALE");
        localChangePersonalInfo.setUserBasicInfo(localUserBaseInfo2);
        UrlFactory.getInstance().changePersonalInfo().constructUrl(PersonalSetActivity.this, localChangePersonalInfo, 12);
        return;
      case 2131099861:
        PersonalSetActivity.this.setSex(2);
        UserBaseInfo localUserBaseInfo1 = new UserBaseInfo();
        localUserBaseInfo1.setGender("DEFAULT");
        localChangePersonalInfo.setUserBasicInfo(localUserBaseInfo1);
        UrlFactory.getInstance().changePersonalInfo().constructUrl(PersonalSetActivity.this, localChangePersonalInfo, 12);
        return;
      case 2131099862:
        if (PersonalSetActivity.this.mPersonalInfo.getPhoneNumber().equals(""))
        {
          Toast.makeText(PersonalSetActivity.this.mContext, "请先绑定手机号", 0).show();
          return;
        }
        PersonalSetActivity.this.startNewActivity(SetPasswordActivity.class, false, null);
        return;
      case 2131099853:
      }
      PersonalSetActivity.this.file = PersonalSetActivity.this.mFileTools.createFile(System.currentTimeMillis() + ".JPEG");
      LogUtils.myLog(PersonalSetActivity.this.file.getAbsolutePath());
      Intent localIntent = new Intent("android.intent.action.GET_CONTENT");
      localIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
      PersonalSetActivity.this.startActivityForResult(localIntent, 20);
    }
  }

  public class PersonalSetHandler extends Handler
  {
    public PersonalSetHandler()
    {
    }

    public void handleMessage(Message paramMessage)
    {
      super.handleMessage(paramMessage);
      switch (paramMessage.what)
      {
      default:
        return;
      case 1:
        ChangePersonalInfo localChangePersonalInfo = new ChangePersonalInfo();
        localChangePersonalInfo.setVersionName(PersonalSetActivity.this.mSP.getVersionName());
        localChangePersonalInfo.setClientType(ToolsUtils.getSDK());
        localChangePersonalInfo.setImei(ToolsUtils.getImei(PersonalSetActivity.this.mContext));
        localChangePersonalInfo.setNet(ToolsUtils.getStringNetworkType(PersonalSetActivity.this.mContext));
        localChangePersonalInfo.setUid(PersonalSetActivity.this.mSP.getUserId());
        UserBaseInfo localUserBaseInfo = new UserBaseInfo();
        localUserBaseInfo.setProfileUrl(((String[])paramMessage.obj)[0]);
        localChangePersonalInfo.setUserBasicInfo(localUserBaseInfo);
        UrlFactory.getInstance().changePersonalInfo().constructUrl(PersonalSetActivity.this, localChangePersonalInfo, 11);
        return;
      case 2:
        Toast.makeText(PersonalSetActivity.this.mContext, "设置头像成功", 0).show();
        PersonalSetActivity.this.getPersonal();
        PersonalFragment.mHandler.sendEmptyMessage(4);
        return;
      case 3:
        Toast.makeText(PersonalSetActivity.this.mContext, "设置性别成功", 0).show();
        return;
      case 4:
      case 5:
        Toast.makeText(PersonalSetActivity.this.mContext, "服务器未能返回数据，请稍后再试", 0).show();
        return;
      case 6:
      }
      PersonalSetActivity.this.mProTools.dismissDislog();
    }
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.activity.PersonalSetActivity
 * JD-Core Version:    0.6.0
 */