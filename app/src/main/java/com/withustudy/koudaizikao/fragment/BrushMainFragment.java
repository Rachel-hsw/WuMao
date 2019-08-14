package com.withustudy.koudaizikao.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;
import com.google.gson.Gson;
import com.umeng.analytics.MobclickAgent;
import com.withustudy.koudaizikao.action.AbsBaseUrlConstructor;
import com.withustudy.koudaizikao.action.UrlFactory;
import com.withustudy.koudaizikao.activity.ChooseProfessionActivity;
import com.withustudy.koudaizikao.activity.ChooseSubjectActivity;
import com.withustudy.koudaizikao.activity.MainActivity;
import com.withustudy.koudaizikao.activity.MainActivity.RefreshRankListener;
import com.withustudy.koudaizikao.base.AbsBaseFragment;
import com.withustudy.koudaizikao.config.KouDaiSP;
import com.withustudy.koudaizikao.custom.FragmentpaperViewAdapater;
import com.withustudy.koudaizikao.custom.FragmentpaperViewAdapater.BeforeShowListenner;
import com.withustudy.koudaizikao.custom.ViewPagerIndicator;
import com.withustudy.koudaizikao.custom.ViewPagerIndicator.PageChangeListener;
import com.withustudy.koudaizikao.entity.ChooseProToChooseSub;
import com.withustudy.koudaizikao.entity.Subject;
import com.withustudy.koudaizikao.entity.SubjectW;
import com.withustudy.koudaizikao.entity.content.MajorContent;
import com.withustudy.koudaizikao.entity.req.MajorUpLoad;
import com.withustudy.koudaizikao.tools.LogUtils;
import com.withustudy.koudaizikao.tools.MyLog;
import com.withustudy.koudaizikao.tools.ProTools;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class BrushMainFragment extends AbsBaseFragment
  implements BeforeShowListenner, MainActivity.RefreshRankListener
{
  private static final int get_subject_list_by_net = 101;
  private static final int get_subject_list_by_property = 100;
  private MainActivity activity;
  public int currentIndex;
  private boolean first = false;
  private Handler handler = new Handler()
  {
    public void handleMessage(Message paramMessage)
    {
      switch (paramMessage.what)
      {
      case 1:
      default:
      case 100:
      }
      List localList;
      do
      {
        return;
        localList = BrushMainFragment.this.subject;
      }
      while ((localList == null) || (localList.size() <= 0));
      BrushMainFragment.this.subjectList.clear();
      BrushMainFragment.this.subjectList.addAll(localList);
      if ((BrushMainFragment.this.subjectList != null) && (BrushMainFragment.this.subjectList.size() > 0))
      {
        if (BrushMainFragment.this.mDatas == null)
          BrushMainFragment.this.mDatas = new ArrayList();
        BrushMainFragment.this.mDatas.clear();
        BrushMainFragment.this.mTabContents.clear();
        Iterator localIterator = BrushMainFragment.this.subjectList.iterator();
        while (true)
        {
          if (!localIterator.hasNext())
          {
            BrushMainFragment.this.mViewPager.setOffscreenPageLimit(1);
            BrushMainFragment.this.mAdapter = new FragmentpaperViewAdapater(BrushMainFragment.this.getChildFragmentManager())
            {
              public int getCount()
              {
                return BrushMainFragment.this.mTabContents.size();
              }

              public Fragment getItem(int paramInt)
              {
                return (Fragment)BrushMainFragment.this.mTabContents.get(paramInt);
              }
            };
            BrushMainFragment.this.mIndicator.setTabItemTitles(BrushMainFragment.this.mDatas);
            BrushMainFragment.this.mAdapter.setBeforeShowListenner(BrushMainFragment.this);
            BrushMainFragment.this.mViewPager.setAdapter(BrushMainFragment.this.mAdapter);
            BrushMainFragment.this.mIndicator.setViewPager(BrushMainFragment.this.mViewPager, 0);
            BrushMainFragment.this.mViewPager.setCurrentItem(0);
            return;
          }
          Subject localSubject = (Subject)localIterator.next();
          BrushMainFragment.this.mDatas.add(localSubject.getName());
          SubjectFragment localSubjectFragment = new SubjectFragment();
          Bundle localBundle = new Bundle();
          localBundle.putSerializable("subject", localSubject);
          localSubjectFragment.setArguments(localBundle);
          BrushMainFragment.this.mTabContents.add(localSubjectFragment);
        }
      }
      LogUtils.myLog("科目列表为null");
    }
  };
  private FragmentpaperViewAdapater mAdapter;
  public List<String> mDatas;
  private ViewPagerIndicator mIndicator;
  private List<SubjectFragment> mTabContents = new ArrayList();
  private ViewPager mViewPager;
  private MajorContent majorW;
  private List<Subject> subject;
  private List<Subject> subjectList = new ArrayList();
  private SubjectW subjectW;

  public void beforeShowListenner(int paramInt)
  {
    if ((paramInt < 0) || (paramInt > this.mTabContents.size()))
      return;
    ((SubjectFragment)this.mTabContents.get(paramInt)).refresh((Subject)this.subjectList.get(paramInt), paramInt, (String)this.mDatas.get(paramInt));
  }

  public void bindData()
  {
  }

  public void initData()
  {
    this.mProTools.startDialog(true);
    String str = this.mSP.getUserId();
    UrlFactory.getInstance().getMajorSubject().constructUrl(this, new String[] { str }, 101, this.mContext);
  }

  public void initListener()
  {
    this.mIndicator.setOnPageChangeListener(new PageChangeListener()
    {
      public void onPageScrollStateChanged(int paramInt)
      {
      }

      public void onPageScrolled(int paramInt1, float paramFloat, int paramInt2)
      {
      }

      public void onPageSelected(int paramInt)
      {
        MobclickAgent.onEvent(BrushMainFragment.this.mContext, "brush_change_subject");
        BrushMainFragment.this.currentIndex = paramInt;
        MyLog.log("onPageSelected==", Integer.valueOf(((SubjectFragment)BrushMainFragment.this.mTabContents.get(paramInt)).hashCode()));
      }
    });
  }

  public void initView(View paramView)
  {
    this.mViewPager = ((ViewPager)paramView.findViewById(2131100094));
    this.mIndicator = ((ViewPagerIndicator)paramView.findViewById(2131100093));
  }

  public void onAttach(Activity paramActivity)
  {
    super.onAttach(paramActivity);
    this.activity = ((MainActivity)paramActivity);
    ((MainActivity)paramActivity).setReListener(this);
  }

  public void onDestroy()
  {
    super.onDestroy();
    this.mSP.setUpdateSubject(false);
  }

  public void onHiddenChanged(boolean paramBoolean)
  {
    super.onHiddenChanged(paramBoolean);
    if (!paramBoolean)
    {
      this.mProTools.startDialog(true);
      String str = this.mSP.getUserId();
      UrlFactory.getInstance().getMajorSubject().constructUrl(this, new String[] { str }, 101, this.mContext);
    }
  }

  public View onInflaterRootView(LayoutInflater paramLayoutInflater, Bundle paramBundle)
  {
    return View.inflate(getActivity(), 2130903112, null);
  }

  public void onResume()
  {
    super.onResume();
  }

  public void onStart()
  {
    super.onStart();
    if ((this.mSP.getUpdateSubject()) && (!this.first))
    {
      this.first = true;
      this.mProTools.startDialog(true);
      String str = this.mSP.getUserId();
      UrlFactory.getInstance().getMajorSubject().constructUrl(this, new String[] { str }, 101, this.mContext);
      this.mSP.setUpdateSubject(false);
    }
  }

  public void onSuccess(String paramString1, Map<String, String> paramMap, String paramString2, int paramInt)
  {
    super.onSuccess(paramString1, paramMap, paramString2, paramInt);
    this.mProTools.dismissDislog();
    if (paramString1 != null);
    try
    {
      Gson localGson = UrlFactory.getInstanceGson();
      switch (paramInt)
      {
      case 100:
        this.subjectW = ((SubjectW)localGson.fromJson(paramString1, SubjectW.class));
        if (this.subjectW != null)
        {
          LogUtils.myLog(this.subjectW);
          this.handler.sendEmptyMessage(100);
          return;
        }
        LogUtils.myLog("科目id解析异常");
        return;
      case 101:
        this.majorW = ((MajorContent)localGson.fromJson(paramString1, MajorContent.class));
        if (this.majorW != null)
        {
          MajorUpLoad localMajorUpLoad = this.majorW.getMajor();
          if ((localMajorUpLoad == null) || (localMajorUpLoad.getProvId().equals("")) || (localMajorUpLoad.getProvName().equals("")) || (localMajorUpLoad.getMajorId().equals("")) || (localMajorUpLoad.getMajorName().equals("")))
          {
            Toast.makeText(getActivity(), "请先选择专业信息", 0).show();
            startNewActivity(ChooseProfessionActivity.class, false, null);
            return;
          }
          this.mSP.setMajorName(localMajorUpLoad.getMajorName());
          this.mSP.setProName(localMajorUpLoad.getProvName());
          this.mSP.setMajorId(localMajorUpLoad.getMajorId());
          this.mSP.setProId(localMajorUpLoad.getProvId());
          this.subject = this.majorW.getSubject();
          if ((this.subject != null) && (this.subject.size() > 0))
          {
            this.handler.sendEmptyMessage(100);
            return;
          }
          Toast.makeText(getActivity(), "请填报科目", 0).show();
          ChooseProToChooseSub localChooseProToChooseSub = new ChooseProToChooseSub();
          localChooseProToChooseSub.setProId(this.mSP.getProId());
          localChooseProToChooseSub.setProvName(this.mSP.getProName());
          localChooseProToChooseSub.setMajorId(this.mSP.getMajorId());
          localChooseProToChooseSub.setMajorName(this.mSP.getMajorName());
          Bundle localBundle = new Bundle();
          localBundle.putSerializable("Major", localChooseProToChooseSub);
          startNewActivity(ChooseSubjectActivity.class, false, localBundle);
          return;
        }
        LogUtils.myLog("解析用户的专业信息异常");
      }
      return;
    }
    catch (Exception localException)
    {
    }
  }

  public void refreshData()
  {
    int i = this.mTabContents.size();
    if ((this.currentIndex >= 0) && (this.currentIndex <= i - 1))
      ((SubjectFragment)this.mTabContents.get(this.currentIndex)).refresh((Subject)this.subjectList.get(this.currentIndex), this.currentIndex, (String)this.mDatas.get(this.currentIndex));
  }

  public void refreshRank()
  {
    refreshData();
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.fragment.BrushMainFragment
 * JD-Core Version:    0.6.0
 */