package com.withustudy.koudaizikao.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import com.google.gson.Gson;
import com.withustudy.koudaizikao.action.AbsSimpleImplUrlFactory;
import com.withustudy.koudaizikao.action.UrlFactory;
import com.withustudy.koudaizikao.base.AbsBaseActivity;
import com.withustudy.koudaizikao.config.KouDaiSP;
import com.withustudy.koudaizikao.custom.ViewPagerIndicator;
import com.withustudy.koudaizikao.custom.ViewPagerIndicator.PageChangeListener;
import com.withustudy.koudaizikao.entity.Subject;
import com.withustudy.koudaizikao.entity.SubjectW;
import com.withustudy.koudaizikao.entity.req.ReqMockList;
import com.withustudy.koudaizikao.fragment.SubjectSimuHistoryFm;
import com.withustudy.koudaizikao.tools.LogUtils;
import com.withustudy.koudaizikao.tools.ProTools;
import com.withustudy.koudaizikao.tools.ToolsUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class SimuHistoryListActivity extends AbsBaseActivity
  implements OnClickListener
{
  private static final int action_get_mock_subject_list;
  public int currentIndex;
  private Handler handler = new Handler()
  {
    public void handleMessage(Message paramMessage)
    {
      switch (paramMessage.what)
      {
      default:
      case 0:
      }
      do
      {
        return;
        SimuHistoryListActivity.this.subjectList = SimuHistoryListActivity.this.subjectW.getSubject();
      }
      while ((SimuHistoryListActivity.this.subjectList == null) || (SimuHistoryListActivity.this.subjectList.size() <= 0));
      if (SimuHistoryListActivity.this.mDatas == null)
        SimuHistoryListActivity.this.mDatas = new ArrayList();
      SimuHistoryListActivity.this.mDatas.clear();
      SimuHistoryListActivity.this.mTabContents.clear();
      Iterator localIterator = SimuHistoryListActivity.this.subjectList.iterator();
      while (true)
      {
        if (!localIterator.hasNext())
        {
          SimuHistoryListActivity.this.mAdapter = new FragmentPagerAdapter(SimuHistoryListActivity.this.getSupportFragmentManager())
          {
            public int getCount()
            {
              return SimuHistoryListActivity.this.mTabContents.size();
            }

            public Fragment getItem(int paramInt)
            {
              return (Fragment)SimuHistoryListActivity.this.mTabContents.get(paramInt);
            }
          };
          SimuHistoryListActivity.this.id_vp.setAdapter(SimuHistoryListActivity.this.mAdapter);
          SimuHistoryListActivity.this.id_indicator.setTabItemTitles(SimuHistoryListActivity.this.mDatas);
          SimuHistoryListActivity.this.id_indicator.setViewPager(SimuHistoryListActivity.this.id_vp, 0);
          SimuHistoryListActivity.this.id_vp.setCurrentItem(0);
          return;
        }
        Subject localSubject = (Subject)localIterator.next();
        SimuHistoryListActivity.this.mDatas.add(localSubject.getName());
        SubjectSimuHistoryFm localSubjectSimuHistoryFm = new SubjectSimuHistoryFm();
        Bundle localBundle = new Bundle();
        localBundle.putSerializable("subject", localSubject);
        localSubjectSimuHistoryFm.setArguments(localBundle);
        SimuHistoryListActivity.this.mTabContents.add(localSubjectSimuHistoryFm);
      }
    }
  };
  private ViewPagerIndicator id_indicator;
  private ViewPager id_vp;
  private LinearLayout ll_back;
  private FragmentPagerAdapter mAdapter;
  private List<String> mDatas;
  private List<SubjectSimuHistoryFm> mTabContents = new ArrayList();
  private List<Subject> subjectList = new ArrayList();
  private SubjectW subjectW;

  protected void bindData()
  {
  }

  protected void initData()
  {
    this.mProTools.startDialog(true);
    ReqMockList localReqMockList = new ReqMockList();
    localReqMockList.setVersionName(this.mSP.getVersionName());
    localReqMockList.setClientType(ToolsUtils.getSDK());
    localReqMockList.setImei(ToolsUtils.getImei(this.mContext));
    localReqMockList.setNet(ToolsUtils.getStringNetworkType(this.mContext));
    localReqMockList.setUid(this.mSP.getUserId());
    UrlFactory.getInstance().getMockHistory().constructUrl(this, localReqMockList, 0);
  }

  protected void initListener()
  {
    this.id_indicator.setOnPageChangeListener(new PageChangeListener()
    {
      public void onPageScrollStateChanged(int paramInt)
      {
      }

      public void onPageScrolled(int paramInt1, float paramFloat, int paramInt2)
      {
      }

      public void onPageSelected(int paramInt)
      {
        SimuHistoryListActivity.this.currentIndex = paramInt;
      }
    });
    this.ll_back.setOnClickListener(this);
  }

  protected void initView()
  {
    this.id_indicator = ((ViewPagerIndicator)findViewById(2131100093));
    this.id_vp = ((ViewPager)findViewById(2131100094));
    this.ll_back = ((LinearLayout)findViewById(2131100479));
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
      return;
    case 2131100479:
    }
    finish(0, 0);
  }

  public void onSuccess(String paramString1, Map<String, String> paramMap, String paramString2, int paramInt)
  {
    super.onSuccess(paramString1, paramMap, paramString2, paramInt);
    this.mProTools.dismissDislog();
    Gson localGson;
    if (paramString1 != null)
      localGson = UrlFactory.getInstanceGson();
    switch (paramInt)
    {
    default:
      return;
    case 0:
    }
    try
    {
      this.subjectW = ((SubjectW)localGson.fromJson(paramString1, SubjectW.class));
      if (this.subjectW != null)
      {
        this.handler.sendEmptyMessage(0);
        return;
      }
      LogUtils.myLog("解析宜昌");
      return;
    }
    catch (Exception localException)
    {
    }
  }

  protected void setContentView()
  {
    setContentView(2130903171);
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.activity.SimuHistoryListActivity
 * JD-Core Version:    0.6.0
 */