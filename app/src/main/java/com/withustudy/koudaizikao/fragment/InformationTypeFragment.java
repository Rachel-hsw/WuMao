package com.withustudy.koudaizikao.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.withustudy.koudaizikao.action.AbsBaseUrlConstructor;
import com.withustudy.koudaizikao.action.UrlFactory;
import com.withustudy.koudaizikao.activity.InformationDetailActivity;
import com.withustudy.koudaizikao.adapter.InformationTypeAdapter;
import com.withustudy.koudaizikao.base.AbsBaseFragment;
import com.withustudy.koudaizikao.config.KouDaiSP;
import com.withustudy.koudaizikao.custom.LoadingView;
import com.withustudy.koudaizikao.entity.News;
import com.withustudy.koudaizikao.entity.content.NewsListContent;
import com.withustudy.koudaizikao.tools.ToolsUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InformationTypeFragment extends AbsBaseFragment
{
  public static final int ACTION_LOAD = 11;
  public static final int ACTION_REFRESH = 10;
  public static final int CHANGE_COLLECT = 1;
  public static final int CHANGE_PRAISE = 2;
  private int currType;
  private boolean isLoading = false;
  private boolean isRefresh = false;
  private List<News> list;
  private InformationTypeAdapter mAdapter;
  private CallBackListener mBackListener;
  private PullToRefreshListView mListView;
  private LoadingView mLoadingView;
  private String[] para;

  public InformationTypeFragment()
  {
    this.currType = 0;
  }

  public InformationTypeFragment(int paramInt)
  {
    this.currType = paramInt;
  }

  private void setType(int paramInt)
  {
    this.list.clear();
    if (this.mAdapter != null)
      this.mAdapter.notifyDataSetChanged();
    switch (paramInt)
    {
    default:
      return;
    case 0:
      this.para[0] = "DAILY_CHEESE";
      return;
    case 1:
      this.para[0] = "EXAMINATION_ROAD";
      return;
    case 2:
      this.para[0] = "SIGN_UP_INFO";
      return;
    case 3:
      this.para[0] = "OFFICIAL_NEWS";
      return;
    case 4:
    }
    this.para[0] = "COMMEN_QUESTION";
  }

  public void addReply(String paramString)
  {
    if ((this.list != null) && (this.mAdapter != null));
    for (int i = 0; ; i++)
    {
      if (i >= this.list.size())
      {
        this.mAdapter.notifyDataSetChanged();
        return;
      }
      if (!((News)this.list.get(i)).getArticleId().equals(paramString))
        continue;
      ((News)this.list.get(i)).setCommentNum(String.valueOf(1 + Integer.valueOf(((News)this.list.get(i)).getCommentNum()).intValue()));
    }
  }

  public void bindData()
  {
    this.mListView.setVisibility(8);
    this.mLoadingView.setVisibility(0);
    ToolsUtils.setList(2, this.mListView, this.mContext);
  }

  public void getData()
  {
    if ((this.list != null) && (this.list.size() == 0))
      UrlFactory.getInstance().getInfoList().constructUrl(this, this.para, 10, this.mContext);
  }

  public void initData()
  {
    this.mBackListener = new CallBackListener();
    this.para = new String[5];
    for (int i = 0; ; i++)
    {
      if (i >= 5)
      {
        this.list = new ArrayList();
        setType(this.currType);
        this.para[1] = String.valueOf(this.list.size());
        this.para[2] = this.mSP.getProId();
        this.para[3] = this.mSP.getMajorId();
        this.para[4] = this.mSP.getUserId();
        return;
      }
      this.para[i] = "";
    }
  }

  public void initListener()
  {
    this.mListView.setOnRefreshListener(this.mBackListener);
    this.mListView.setOnItemClickListener(this.mBackListener);
  }

  public void initView(View paramView)
  {
    this.mListView = ((PullToRefreshListView)paramView.findViewById(2131100149));
    this.mLoadingView = ((LoadingView)paramView.findViewById(2131100148));
  }

  public void onError(String paramString1, String paramString2, int paramInt)
  {
    super.onError(paramString1, paramString2, paramInt);
    Toast.makeText(this.mContext, "服务器未响应，请稍后再试", 0).show();
    switch (paramInt)
    {
    default:
      return;
    case 10:
      this.mListView.onRefreshComplete();
      this.isRefresh = false;
      return;
    case 11:
    }
    this.mListView.onRefreshComplete();
    this.isLoading = false;
  }

  public View onInflaterRootView(LayoutInflater paramLayoutInflater, Bundle paramBundle)
  {
    return paramLayoutInflater.inflate(2130903118, null);
  }

  public void onResume()
  {
    super.onResume();
    this.para[2] = this.mSP.getProId();
    this.para[3] = this.mSP.getMajorId();
    this.para[4] = this.mSP.getUserId();
  }

  public void onSuccess(String paramString1, Map<String, String> paramMap, String paramString2, int paramInt)
  {
    super.onSuccess(paramString1, paramMap, paramString2, paramInt);
    this.mLoadingView.setVisibility(8);
    this.mListView.setVisibility(0);
    switch (paramInt)
    {
    default:
    case 10:
    case 11:
    }
    do
    {
      do
      {
        return;
        this.mListView.onRefreshComplete();
        this.isRefresh = false;
      }
      while (paramString1 == null);
      try
      {
        NewsListContent localNewsListContent2 = (NewsListContent)UrlFactory.getInstanceGson().fromJson(paramString1, NewsListContent.class);
        if ((localNewsListContent2 != null) && (localNewsListContent2.getBasicNewsList() != null))
        {
          this.list.clear();
          this.list.addAll(localNewsListContent2.getBasicNewsList());
          this.mAdapter = new InformationTypeAdapter(this.mContext, this.list);
          this.mListView.setAdapter(this.mAdapter);
          return;
        }
      }
      catch (Exception localException2)
      {
        localException2.printStackTrace();
        this.mLoadingView.setVisibility(8);
        Toast.makeText(this.mContext, "服务器未响应，请稍后再试", 0).show();
        return;
      }
      this.mLoadingView.setVisibility(8);
      return;
      this.mListView.onRefreshComplete();
      this.isLoading = false;
    }
    while (paramString1 == null);
    try
    {
      NewsListContent localNewsListContent1 = (NewsListContent)UrlFactory.getInstanceGson().fromJson(paramString1, NewsListContent.class);
      if ((localNewsListContent1 != null) && (localNewsListContent1.getBasicNewsList() != null))
      {
        this.list.addAll(localNewsListContent1.getBasicNewsList());
        this.mAdapter.notifyDataSetChanged();
        return;
      }
    }
    catch (Exception localException1)
    {
      localException1.printStackTrace();
      Toast.makeText(this.mContext, "服务器未响应，请稍后再试", 0).show();
      return;
    }
    Toast.makeText(this.mContext, "已经没有更多资讯了哦", 0).show();
  }

  public void updateState(String paramString, int paramInt)
  {
    int i;
    if (this.list != null)
    {
      i = 0;
      if (i < this.list.size());
    }
    else
    {
      return;
    }
    if (((News)this.list.get(i)).getArticleId().equals(paramString))
      switch (paramInt)
      {
      case 21:
      default:
      case 24:
      case 22:
      case 23:
      }
    while (true)
    {
      i++;
      break;
      if (((News)this.list.get(i)).getFavoriteTag().equals("0"))
        ((News)this.list.get(i)).setFavoriteTag("1");
      while (true)
      {
        if (!((News)this.list.get(i)).getThumbTag().equals("0"))
          break label195;
        ((News)this.list.get(i)).setThumbTag("1");
        break;
        ((News)this.list.get(i)).setFavoriteTag("0");
      }
      label195: ((News)this.list.get(i)).setThumbTag("0");
      continue;
      if (((News)this.list.get(i)).getFavoriteTag().equals("0"))
      {
        ((News)this.list.get(i)).setFavoriteTag("1");
        continue;
      }
      ((News)this.list.get(i)).setFavoriteTag("0");
      continue;
      if (((News)this.list.get(i)).getThumbTag().equals("0"))
      {
        ((News)this.list.get(i)).setThumbTag("1");
        continue;
      }
      ((News)this.list.get(i)).setThumbTag("0");
    }
  }

  class CallBackListener
    implements PullToRefreshBase.OnRefreshListener2<ListView>, OnItemClickListener
  {
    CallBackListener()
    {
    }

    public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
    {
      Bundle localBundle = new Bundle();
      localBundle.putSerializable("content", (Serializable)InformationTypeFragment.this.list.get(paramInt - 1));
      InformationTypeFragment.this.startNewActivityForResult(InformationDetailActivity.class, 20, localBundle);
    }

    public void onPullDownToRefresh(PullToRefreshBase<ListView> paramPullToRefreshBase)
    {
      if (!InformationTypeFragment.this.isRefresh)
      {
        InformationTypeFragment.this.isRefresh = true;
        InformationTypeFragment.this.para[1] = "0";
        UrlFactory.getInstance().getInfoList().constructUrl(InformationTypeFragment.this, InformationTypeFragment.this.para, 10, InformationTypeFragment.this.mContext);
      }
    }

    public void onPullUpToRefresh(PullToRefreshBase<ListView> paramPullToRefreshBase)
    {
      if (!InformationTypeFragment.this.isLoading)
      {
        InformationTypeFragment.this.isLoading = true;
        InformationTypeFragment.this.para[1] = String.valueOf(InformationTypeFragment.access$6(InformationTypeFragment.this).size());
        UrlFactory.getInstance().getInfoList().constructUrl(InformationTypeFragment.this, InformationTypeFragment.this.para, 11, InformationTypeFragment.this.mContext);
      }
    }
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.fragment.InformationTypeFragment
 * JD-Core Version:    0.6.0
 */