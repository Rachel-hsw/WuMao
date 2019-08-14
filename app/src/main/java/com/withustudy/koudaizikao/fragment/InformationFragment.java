package com.withustudy.koudaizikao.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import com.android.http.FileDownLoad;
import com.google.gson.Gson;
import com.umeng.analytics.MobclickAgent;
import com.withustudy.koudaizikao.action.AbsBaseUrlConstructor;
import com.withustudy.koudaizikao.action.UrlFactory;
import com.withustudy.koudaizikao.activity.InformationDetailActivity;
import com.withustudy.koudaizikao.activity.InformationTypeActivity;
import com.withustudy.koudaizikao.adapter.InformationMainListAdapter;
import com.withustudy.koudaizikao.adapter.InformationViewPagerAdapter;
import com.withustudy.koudaizikao.base.AbsBaseFragment;
import com.withustudy.koudaizikao.config.KouDaiSP;
import com.withustudy.koudaizikao.custom.AdaptiveListView;
import com.withustudy.koudaizikao.custom.AotuViewPager;
import com.withustudy.koudaizikao.custom.LoadingView;
import com.withustudy.koudaizikao.entity.News;
import com.withustudy.koudaizikao.entity.content.NewsListContent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InformationFragment extends AbsBaseFragment
{
  public static final int ACTION_GET = 10;
  private List<News> commenList;
  private News daily;
  private List<News> dailyList;
  private List<News> examinationList;
  private ImageView[] imageAlternation;
  private ImageView imageTop;
  private boolean isInitViewPager = false;
  private LinearLayout[] layoutType;
  private List<ImageView> list;
  private InformationMainListAdapter mAdapter;
  private CallBackListener mBackListener;
  private AdaptiveListView mListView;
  private LoadingView mLoadingView;
  private InformationViewPagerAdapter mPagerAdapter;
  private ScrollView mScrollView;
  private AotuViewPager mViewPager;
  private List<News> officialList;
  private final double scaleOfViewPager = 0.52D;
  private List<News> signList;
  private TextView textDailyContent;
  private TextView textPraise;
  private TextView textShare;
  private List<News> topList;
  private List<News> totalList;

  private void imageViewOrViewPager()
  {
    if (this.topList.size() == 0)
    {
      this.imageTop.setVisibility(0);
      this.mViewPager.setVisibility(8);
      return;
    }
    if (this.topList.size() == 1)
    {
      this.imageTop.setVisibility(0);
      this.mViewPager.setVisibility(8);
      this.mFileDownLoad.downLoadImage(((News)this.topList.get(0)).getAttachUrl(), this.imageTop);
      return;
    }
    this.imageTop.setVisibility(8);
    this.mViewPager.setVisibility(0);
    initViewPager(this.topList);
  }

  private void initDaily()
  {
    this.daily = ((News)this.dailyList.get(0));
    this.textDailyContent.setText(this.daily.getBriefText());
    this.textPraise.setText(this.daily.getThumbNum());
    this.textShare.setText(this.daily.getTransmitNum());
  }

  private void initList()
  {
    this.dailyList.clear();
    this.examinationList.clear();
    this.signList.clear();
    this.officialList.clear();
    this.commenList.clear();
    this.topList.clear();
    int i = 0;
    if (i >= this.totalList.size());
    for (int j = 0; ; j++)
    {
      if ((j >= this.totalList.size()) || (this.topList.size() >= 3))
      {
        return;
        if (((News)this.totalList.get(i)).getArticleType().equals("DAILY_CHEESE"))
          this.dailyList.add((News)this.totalList.get(i));
        while (true)
        {
          i++;
          break;
          if ((((News)this.totalList.get(i)).getArticleType().equals("EXAMINATION_ROAD")) && (this.examinationList.size() < 3))
          {
            this.examinationList.add((News)this.totalList.get(i));
            continue;
          }
          if ((((News)this.totalList.get(i)).getArticleType().equals("SIGN_UP_INFO")) && (this.signList.size() < 3))
          {
            this.signList.add((News)this.totalList.get(i));
            continue;
          }
          if ((((News)this.totalList.get(i)).getArticleType().equals("OFFICIAL_NEWS")) && (this.officialList.size() < 3))
          {
            this.officialList.add((News)this.totalList.get(i));
            continue;
          }
          if ((!((News)this.totalList.get(i)).getArticleType().equals("COMMEN_QUESTION")) || (this.commenList.size() >= 3))
            continue;
          this.commenList.add((News)this.totalList.get(i));
        }
      }
      if (!((News)this.totalList.get(j)).getHandpickedTag().equals("1"))
        continue;
      this.topList.add((News)this.totalList.get(j));
    }
  }

  private void initViewPager(List<News> paramList)
  {
    initViewPagerList(paramList);
    this.mPagerAdapter = new InformationViewPagerAdapter(this.mContext, this.list, paramList);
    FrameLayout.LayoutParams localLayoutParams = (FrameLayout.LayoutParams)this.mViewPager.getLayoutParams();
    localLayoutParams.width = -1;
    localLayoutParams.height = (int)(0.52D * this.mSP.getWidth());
    this.mViewPager.setLayoutParams(localLayoutParams);
    this.mViewPager.setAdapter(this.mPagerAdapter);
    this.mViewPager.setCurrentItem(100 * this.mPagerAdapter.getListCount());
    this.mViewPager.startScroll();
  }

  private void initViewPagerList(List<News> paramList)
  {
    if (this.list == null)
    {
      this.list = new ArrayList();
      this.imageAlternation = new ImageView[paramList.size()];
    }
    for (int i = 0; ; i++)
    {
      if (i >= paramList.size())
      {
        return;
        this.list.clear();
        break;
      }
      this.imageAlternation[i] = new ImageView(this.mContext);
      this.imageAlternation[i].setBackgroundColor(Color.parseColor("#00000000"));
      this.imageAlternation[i].setImageResource(2130837808);
      this.imageAlternation[i].setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
      this.imageAlternation[i].setScaleType(ScaleType.FIT_XY);
      this.imageAlternation[i].setTag(Integer.valueOf(i));
      this.imageAlternation[i].setOnClickListener(this.mBackListener);
      this.list.add(this.imageAlternation[i]);
    }
  }

  public void bindData()
  {
    FrameLayout.LayoutParams localLayoutParams = (FrameLayout.LayoutParams)this.mViewPager.getLayoutParams();
    localLayoutParams.width = -1;
    localLayoutParams.height = (int)(0.52D * this.mSP.getWidth());
    this.imageTop.setLayoutParams(localLayoutParams);
  }

  public void initData()
  {
    this.mBackListener = new CallBackListener();
    this.totalList = new ArrayList();
    this.dailyList = new ArrayList();
    this.examinationList = new ArrayList();
    this.signList = new ArrayList();
    this.officialList = new ArrayList();
    this.commenList = new ArrayList();
    this.topList = new ArrayList();
  }

  public void initListener()
  {
    this.mViewPager.setOnPageChangeListener(this.mBackListener);
    this.imageTop.setOnClickListener(this.mBackListener);
    this.mListView.setOnItemClickListener(this.mBackListener);
    this.textDailyContent.setOnClickListener(this.mBackListener);
    for (int i = 0; ; i++)
    {
      if (i >= this.layoutType.length)
        return;
      this.layoutType[i].setOnClickListener(this.mBackListener);
    }
  }

  public void initView(View paramView)
  {
    this.mLoadingView = ((LoadingView)paramView.findViewById(2131100133));
    this.mScrollView = ((ScrollView)paramView.findViewById(2131100134));
    this.mViewPager = ((AotuViewPager)paramView.findViewById(2131100136));
    this.imageTop = ((ImageView)paramView.findViewById(2131100135));
    this.layoutType = new LinearLayout[5];
    this.layoutType[0] = ((LinearLayout)paramView.findViewById(2131100137));
    this.layoutType[1] = ((LinearLayout)paramView.findViewById(2131100138));
    this.layoutType[2] = ((LinearLayout)paramView.findViewById(2131100139));
    this.layoutType[3] = ((LinearLayout)paramView.findViewById(2131100140));
    this.layoutType[4] = ((LinearLayout)paramView.findViewById(2131100141));
    this.textPraise = ((TextView)paramView.findViewById(2131100143));
    this.textShare = ((TextView)paramView.findViewById(2131100145));
    this.textDailyContent = ((TextView)paramView.findViewById(2131100146));
    this.mListView = ((AdaptiveListView)paramView.findViewById(2131100147));
  }

  public void onError(String paramString1, String paramString2, int paramInt)
  {
    super.onError(paramString1, paramString2, paramInt);
  }

  public View onInflaterRootView(LayoutInflater paramLayoutInflater, Bundle paramBundle)
  {
    return paramLayoutInflater.inflate(2130903117, null);
  }

  public void onResume()
  {
    super.onResume();
    String[] arrayOfString = new String[3];
    for (int i = 0; ; i++)
    {
      if (i >= arrayOfString.length)
      {
        if (!this.mSP.getUserId().equals(""))
          arrayOfString[0] = this.mSP.getUserId();
        if (!this.mSP.getProId().equals(""))
          arrayOfString[1] = this.mSP.getProId();
        if (!this.mSP.getMajorId().equals(""))
          arrayOfString[2] = this.mSP.getMajorId();
        UrlFactory.getInstance().newsMain().constructUrl(this, arrayOfString, 10, this.mContext);
        return;
      }
      arrayOfString[i] = "";
    }
  }

  public void onSuccess(String paramString1, Map<String, String> paramMap, String paramString2, int paramInt)
  {
    super.onSuccess(paramString1, paramMap, paramString2, paramInt);
    switch (paramInt)
    {
    default:
    case 10:
    }
    while (true)
    {
      return;
      if (paramString1 == null)
        continue;
      try
      {
        NewsListContent localNewsListContent = (NewsListContent)UrlFactory.getInstanceGson().fromJson(paramString1, NewsListContent.class);
        if ((localNewsListContent == null) || (localNewsListContent.getBasicNewsList() == null))
          continue;
        this.mLoadingView.setVisibility(8);
        this.mScrollView.setVisibility(0);
        this.totalList.clear();
        this.totalList.addAll(localNewsListContent.getBasicNewsList());
        initList();
        this.mAdapter = new InformationMainListAdapter(this.examinationList, this.signList, this.officialList, this.commenList, this.mContext);
        this.mListView.setFocusable(false);
        this.mListView.setSelector(2131230724);
        this.mListView.setAdapter(this.mAdapter);
        initDaily();
        if (this.isInitViewPager)
          continue;
        imageViewOrViewPager();
        this.isInitViewPager = true;
        return;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
    }
  }

  class CallBackListener
    implements ViewPager.OnPageChangeListener, OnClickListener, OnItemClickListener
  {
    CallBackListener()
    {
    }

    public void onClick(View paramView)
    {
      if ((paramView instanceof ImageView))
      {
        if (InformationFragment.this.topList.size() != 0)
        {
          MobclickAgent.onEvent(InformationFragment.this.mContext, "news_main_top");
          Bundle localBundle7 = new Bundle();
          localBundle7.putSerializable("content", (Serializable)InformationFragment.this.topList.get(((Integer)paramView.getTag()).intValue()));
          InformationFragment.this.startNewActivity(InformationDetailActivity.class, false, localBundle7);
          return;
        }
        Toast.makeText(InformationFragment.this.mContext, "还没有推荐新闻哦", 0).show();
        return;
      }
      switch (paramView.getId())
      {
      case 2131100142:
      case 2131100143:
      case 2131100144:
      case 2131100145:
      default:
        return;
      case 2131100137:
        MobclickAgent.onEvent(InformationFragment.this.mContext, "news_main_daily");
        Bundle localBundle6 = new Bundle();
        localBundle6.putInt("information_type", 0);
        InformationFragment.this.startNewActivity(InformationTypeActivity.class, false, localBundle6);
        return;
      case 2131100146:
        MobclickAgent.onEvent(InformationFragment.this.mContext, "news_main_content_daily");
        Bundle localBundle5 = new Bundle();
        localBundle5.putSerializable("content", InformationFragment.this.daily);
        InformationFragment.this.startNewActivity(InformationDetailActivity.class, false, localBundle5);
        return;
      case 2131100138:
        MobclickAgent.onEvent(InformationFragment.this.mContext, "news_main_road");
        Bundle localBundle4 = new Bundle();
        localBundle4.putInt("information_type", 1);
        InformationFragment.this.startNewActivity(InformationTypeActivity.class, false, localBundle4);
        return;
      case 2131100139:
        MobclickAgent.onEvent(InformationFragment.this.mContext, "news_main_sign");
        Bundle localBundle3 = new Bundle();
        localBundle3.putInt("information_type", 6);
        InformationFragment.this.startNewActivity(InformationTypeActivity.class, false, localBundle3);
        return;
      case 2131100140:
        MobclickAgent.onEvent(InformationFragment.this.mContext, "news_main_office");
        Bundle localBundle2 = new Bundle();
        localBundle2.putInt("information_type", 11);
        InformationFragment.this.startNewActivity(InformationTypeActivity.class, false, localBundle2);
        return;
      case 2131100141:
      }
      MobclickAgent.onEvent(InformationFragment.this.mContext, "news_main_question");
      Bundle localBundle1 = new Bundle();
      localBundle1.putInt("information_type", 16);
      InformationFragment.this.startNewActivity(InformationTypeActivity.class, false, localBundle1);
    }

    public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
    {
      News localNews = (News)InformationFragment.this.mAdapter.getItem(paramInt);
      if ((!localNews.getArticleId().equals("-2")) && (!localNews.getArticleId().equals("-1")))
        if (localNews.getArticleType().equals("EXAMINATION_ROAD"))
        {
          MobclickAgent.onEvent(InformationFragment.this.mContext, "news_main_content_road");
          Bundle localBundle5 = new Bundle();
          localBundle5.putSerializable("content", localNews);
          InformationFragment.this.startNewActivity(InformationDetailActivity.class, false, localBundle5);
        }
      do
      {
        return;
        if (localNews.getArticleType().equals("SIGN_UP_INFO"))
        {
          MobclickAgent.onEvent(InformationFragment.this.mContext, "news_main_content_sign");
          break;
        }
        if (localNews.getArticleType().equals("OFFICIAL_NEWS"))
        {
          MobclickAgent.onEvent(InformationFragment.this.mContext, "news_main_content_office");
          break;
        }
        if (!localNews.getArticleType().equals("COMMEN_QUESTION"))
          break;
        MobclickAgent.onEvent(InformationFragment.this.mContext, "news_main_content_question");
        break;
        if (localNews.getInstruction().equals("EXAMINATION_ROAD"))
        {
          MobclickAgent.onEvent(InformationFragment.this.mContext, "news_main_list_road");
          Bundle localBundle4 = new Bundle();
          localBundle4.putInt("information_type", 1);
          InformationFragment.this.startNewActivity(InformationTypeActivity.class, false, localBundle4);
          return;
        }
        if (localNews.getInstruction().equals("SIGN_UP_INFO"))
        {
          MobclickAgent.onEvent(InformationFragment.this.mContext, "news_main_list_sign");
          Bundle localBundle3 = new Bundle();
          localBundle3.putInt("information_type", 6);
          InformationFragment.this.startNewActivity(InformationTypeActivity.class, false, localBundle3);
          return;
        }
        if (!localNews.getInstruction().equals("OFFICIAL_NEWS"))
          continue;
        MobclickAgent.onEvent(InformationFragment.this.mContext, "news_main_list_office");
        Bundle localBundle2 = new Bundle();
        localBundle2.putInt("information_type", 11);
        InformationFragment.this.startNewActivity(InformationTypeActivity.class, false, localBundle2);
        return;
      }
      while (!localNews.getInstruction().equals("COMMEN_QUESTION"));
      MobclickAgent.onEvent(InformationFragment.this.mContext, "news_main_list_question");
      Bundle localBundle1 = new Bundle();
      localBundle1.putInt("information_type", 16);
      InformationFragment.this.startNewActivity(InformationTypeActivity.class, false, localBundle1);
    }

    public void onPageScrollStateChanged(int paramInt)
    {
    }

    public void onPageScrolled(int paramInt1, float paramFloat, int paramInt2)
    {
    }

    public void onPageSelected(int paramInt)
    {
      int i = paramInt % InformationFragment.this.mPagerAdapter.getListCount();
      if (i < 0)
        (i + InformationFragment.this.mPagerAdapter.getListCount());
    }
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.fragment.InformationFragment
 * JD-Core Version:    0.6.0
 */