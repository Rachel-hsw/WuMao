package com.withustudy.koudaizikao.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;
import com.google.gson.Gson;
import com.withustudy.koudaizikao.action.AbsBaseUrlConstructor;
import com.withustudy.koudaizikao.action.UrlFactory;
import com.withustudy.koudaizikao.activity.InformationDetailActivity;
import com.withustudy.koudaizikao.adapter.InformationTypeAdapter;
import com.withustudy.koudaizikao.base.AbsBaseFragment;
import com.withustudy.koudaizikao.config.KouDaiSP;
import com.withustudy.koudaizikao.custom.pullrefersh.SwipyRefreshLayout;
import com.withustudy.koudaizikao.custom.pullrefersh.SwipyRefreshLayout.OnRefreshListener;
import com.withustudy.koudaizikao.custom.pullrefersh.SwipyRefreshLayoutDirection;
import com.withustudy.koudaizikao.entity.News;
import com.withustudy.koudaizikao.entity.content.NewsListContent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FavoriteNewsFragment extends AbsBaseFragment
{
  public static final int ACTION_GET = 10;
  private LinearLayout layoutNothing;
  private List<News> list;
  private InformationTypeAdapter mAdapter;
  private CallBackListener mBackListener;
  private ListView mListView;
  private SwipyRefreshLayout mSwipyRefreshLayout;

  private void getData()
  {
    AbsBaseUrlConstructor localAbsBaseUrlConstructor = UrlFactory.getInstance().getFavoriteNews();
    String[] arrayOfString = new String[1];
    arrayOfString[0] = this.mSP.getUserId();
    localAbsBaseUrlConstructor.constructUrl(this, arrayOfString, 10, this.mContext);
  }

  public void bindData()
  {
    this.mListView.setAdapter(this.mAdapter);
    getData();
  }

  public void initData()
  {
    this.mBackListener = new CallBackListener();
    this.list = new ArrayList();
    this.mAdapter = new InformationTypeAdapter(this.mContext, this.list);
  }

  public void initListener()
  {
    this.mListView.setOnItemClickListener(this.mBackListener);
    this.mListView.setOnScrollListener(this.mBackListener);
    this.mSwipyRefreshLayout.setOnRefreshListener(this.mBackListener);
  }

  public void initView(View paramView)
  {
    this.mSwipyRefreshLayout = ((SwipyRefreshLayout)paramView.findViewById(2131100128));
    this.mListView = ((ListView)paramView.findViewById(2131100129));
    this.layoutNothing = ((LinearLayout)paramView.findViewById(2131100127));
  }

  public void onError(String paramString1, String paramString2, int paramInt)
  {
    super.onError(paramString1, paramString2, paramInt);
    this.mSwipyRefreshLayout.setRefreshing(false);
  }

  public View onInflaterRootView(LayoutInflater paramLayoutInflater, Bundle paramBundle)
  {
    return paramLayoutInflater.inflate(2130903115, null);
  }

  public void onSuccess(String paramString1, Map<String, String> paramMap, String paramString2, int paramInt)
  {
    super.onSuccess(paramString1, paramMap, paramString2, paramInt);
    this.mSwipyRefreshLayout.setRefreshing(false);
    switch (paramInt)
    {
    default:
    case 10:
    }
    do
      return;
    while (paramString1 == null);
    NewsListContent localNewsListContent;
    try
    {
      localNewsListContent = (NewsListContent)UrlFactory.getInstanceGson().fromJson(paramString1, NewsListContent.class);
      if ((localNewsListContent == null) || (localNewsListContent.getBasicNewsList() == null))
        break label169;
      if (localNewsListContent.getBasicNewsList().size() == 0)
      {
        this.layoutNothing.setVisibility(0);
        this.mListView.setVisibility(8);
        return;
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      Toast.makeText(this.mContext, "服务器未响应，请稍后再试", 0).show();
      return;
    }
    this.layoutNothing.setVisibility(8);
    this.mListView.setVisibility(0);
    this.list.clear();
    this.list.addAll(localNewsListContent.getBasicNewsList());
    this.mAdapter.notifyDataSetChanged();
    return;
    label169: this.layoutNothing.setVisibility(0);
    this.mListView.setVisibility(8);
  }

  public void updateState(String paramString, int paramInt)
  {
    int i = 0;
    if (i >= this.list.size())
      return;
    if (((News)this.list.get(i)).getArticleId().equals(paramString))
      switch (paramInt)
      {
      case 21:
      default:
      case 22:
      case 24:
      case 23:
      }
    while (true)
    {
      i++;
      break;
      getData();
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
    implements OnItemClickListener, OnRefreshListener, OnScrollListener
  {
    CallBackListener()
    {
    }

    public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
    {
      Bundle localBundle = new Bundle();
      localBundle.putSerializable("content", (Serializable)FavoriteNewsFragment.this.list.get(paramInt));
      FavoriteNewsFragment.this.startNewActivityForResult(InformationDetailActivity.class, 20, localBundle);
    }

    public void onRefresh(SwipyRefreshLayoutDirection paramSwipyRefreshLayoutDirection)
    {
      FavoriteNewsFragment.this.mSwipyRefreshLayout.setRefreshing(true);
      FavoriteNewsFragment.this.getData();
    }

    public void onScroll(AbsListView paramAbsListView, int paramInt1, int paramInt2, int paramInt3)
    {
      if (paramInt1 == 0)
      {
        FavoriteNewsFragment.this.mSwipyRefreshLayout.setEnabled(true);
        return;
      }
      FavoriteNewsFragment.this.mSwipyRefreshLayout.setEnabled(false);
    }

    public void onScrollStateChanged(AbsListView paramAbsListView, int paramInt)
    {
    }
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.fragment.FavoriteNewsFragment
 * JD-Core Version:    0.6.0
 */