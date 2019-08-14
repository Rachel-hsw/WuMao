package com.withustudy.koudaizikao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.gson.Gson;
import com.umeng.analytics.MobclickAgent;
import com.withustudy.koudaizikao.action.AbsSimpleImplUrlFactory;
import com.withustudy.koudaizikao.action.UrlFactory;
import com.withustudy.koudaizikao.base.AbsBaseActivity;
import com.withustudy.koudaizikao.config.KouDaiSP;
import com.withustudy.koudaizikao.custom.SharePopWindow;
import com.withustudy.koudaizikao.entity.KpointDetails;
import com.withustudy.koudaizikao.entity.KpointDetailsW;
import com.withustudy.koudaizikao.entity.Section;
import com.withustudy.koudaizikao.entity.req.ReqKowledgeExplain;
import com.withustudy.koudaizikao.entity.req.UserSubject;
import com.withustudy.koudaizikao.fragment.FragmentKPoint;
import com.withustudy.koudaizikao.tools.LogUtils;
import com.withustudy.koudaizikao.tools.ProTools;
import com.withustudy.koudaizikao.tools.ToolsUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class KnowledgePointDetailActivity extends AbsBaseActivity
  implements OnClickListener
{
  private ImageButton bt_share;
  private List<KpointDetails> cacheAllData = new ArrayList();
  private int currentIndex;
  private Handler handler = new Handler()
  {
    public void handleMessage(Message paramMessage)
    {
      switch (paramMessage.what)
      {
      default:
      case 0:
      }
      List localList;
      do
      {
        return;
        localList = KnowledgePointDetailActivity.this.kpointDetailsW.getKpointDetails();
      }
      while (localList == null);
      KnowledgePointDetailActivity.this.cacheAllData.addAll(localList);
      int i = localList.size();
      for (int j = 0; ; j++)
      {
        if (j >= i)
        {
          if (KnowledgePointDetailActivity.this.mAdapter != null)
            break;
          KnowledgePointDetailActivity.this.mAdapter = new FragmentPagerAdapter(KnowledgePointDetailActivity.this.getSupportFragmentManager())
          {
            public int getCount()
            {
              return KnowledgePointDetailActivity.this.mContents.size();
            }

            public Fragment getItem(int paramInt)
            {
              return (Fragment)KnowledgePointDetailActivity.this.mContents.get(paramInt);
            }
          };
          KnowledgePointDetailActivity.this.vp.setAdapter(KnowledgePointDetailActivity.this.mAdapter);
          ((FragmentKPoint)KnowledgePointDetailActivity.this.mContents.get(0)).refreshData((KpointDetails)KnowledgePointDetailActivity.this.cacheAllData.get(0), KnowledgePointDetailActivity.this.cacheAllData);
          return;
        }
        FragmentKPoint localFragmentKPoint = new FragmentKPoint();
        KnowledgePointDetailActivity.this.mContents.add(localFragmentKPoint);
      }
      KnowledgePointDetailActivity.this.mAdapter.notifyDataSetChanged();
    }
  };
  private LinearLayout knowlege_point_back_ll;
  private KpointDetailsW kpointDetailsW;
  private List<KpointDetails> latestData = new ArrayList();
  private FragmentPagerAdapter mAdapter;
  private List<FragmentKPoint> mContents = new ArrayList();
  private Bundle mbundle;
  private String sectionId;
  private String sectionName;
  private String sectionSn;
  public String subjectId;
  private TextView text_knowlege_point_name;
  private ViewPager vp;

  protected void bindData()
  {
  }

  public void callBack(int paramInt)
  {
    this.vp.setCurrentItem(paramInt);
  }

  protected void initData()
  {
    this.mbundle = getIntent().getExtras();
    try
    {
      this.subjectId = this.mbundle.getString("subjectId");
      this.sectionId = this.mbundle.getString("sectionId");
      this.sectionName = this.mbundle.getString("sectionName");
      this.sectionSn = this.mbundle.getString("sectionSn");
      label63: this.text_knowlege_point_name.setText(this.sectionName);
      this.mProTools.startDialog(true);
      ReqKowledgeExplain localReqKowledgeExplain = new ReqKowledgeExplain();
      localReqKowledgeExplain.setVersionName(this.mSP.getVersionName());
      localReqKowledgeExplain.setClientType(ToolsUtils.getSDK());
      localReqKowledgeExplain.setImei(ToolsUtils.getImei(this.mContext));
      localReqKowledgeExplain.setNet(ToolsUtils.getStringNetworkType(this.mContext));
      UserSubject localUserSubject = new UserSubject();
      localUserSubject.setSubjectId(this.subjectId);
      localUserSubject.setUid(this.mSP.getUserId());
      localReqKowledgeExplain.setUserSubject(localUserSubject);
      Section localSection = new Section();
      localSection.setId(this.sectionId);
      localSection.setName(this.sectionName);
      localSection.setSn(this.sectionSn);
      localReqKowledgeExplain.setSection(localSection);
      UrlFactory.getInstance().getKowledgeExplain().constructUrl(this, localReqKowledgeExplain, 0);
      return;
    }
    catch (Exception localException)
    {
      break label63;
    }
  }

  protected void initListener()
  {
    this.bt_share.setOnClickListener(this);
    this.knowlege_point_back_ll.setOnClickListener(this);
    this.vp.setOnPageChangeListener(new OnPageChangeListener()
    {
      public void onPageScrollStateChanged(int paramInt)
      {
      }

      public void onPageScrolled(int paramInt1, float paramFloat, int paramInt2)
      {
      }

      public void onPageSelected(int paramInt)
      {
        KnowledgePointDetailActivity.this.refreshData(paramInt);
      }
    });
  }

  protected void initView()
  {
    this.vp = ((ViewPager)findViewById(2131099822));
    this.knowlege_point_back_ll = ((LinearLayout)findViewById(2131099817));
    this.bt_share = ((ImageButton)findViewById(2131099821));
    this.text_knowlege_point_name = ((TextView)findViewById(2131099818));
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
      return;
    case 2131099817:
      finish();
      return;
    case 2131099821:
    }
    MobclickAgent.onEvent(this.mContext, "brush_k_share");
    new SharePopWindow(this, this.vp).showPop();
  }

  public void onSuccess(String paramString1, Map<String, String> paramMap, String paramString2, int paramInt)
  {
    super.onSuccess(paramString1, paramMap, paramString2, paramInt);
    this.mProTools.dismissDislog();
    if (paramString1 != null);
    switch (paramInt)
    {
    default:
      return;
    case 0:
    }
    try
    {
      this.kpointDetailsW = ((KpointDetailsW)UrlFactory.getInstanceGson().fromJson(paramString1, KpointDetailsW.class));
      if (this.kpointDetailsW != null)
      {
        this.handler.sendEmptyMessage(0);
        return;
      }
    }
    catch (Exception localException)
    {
      LogUtils.myLog("知识点详情解析异常" + localException.getMessage());
      return;
    }
    LogUtils.myLog("知识点详情解析为null");
  }

  protected void refreshData(int paramInt)
  {
    this.currentIndex = paramInt;
    ((FragmentKPoint)this.mContents.get(paramInt)).refreshData((KpointDetails)this.cacheAllData.get(paramInt), this.cacheAllData);
  }

  protected void setContentView()
  {
    setContentView(2130903058);
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.activity.KnowledgePointDetailActivity
 * JD-Core Version:    0.6.0
 */