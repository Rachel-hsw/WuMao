package com.withustudy.koudaizikao.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;
import com.google.gson.Gson;
import com.withustudy.koudaizikao.action.AbsBaseUrlConstructor;
import com.withustudy.koudaizikao.action.UrlFactory;
import com.withustudy.koudaizikao.activity.PostDetailActivity;
import com.withustudy.koudaizikao.adapter.MyPostAdapter;
import com.withustudy.koudaizikao.base.AbsBaseFragment;
import com.withustudy.koudaizikao.config.KouDaiSP;
import com.withustudy.koudaizikao.custom.pullrefersh.SwipyRefreshLayout;
import com.withustudy.koudaizikao.custom.pullrefersh.SwipyRefreshLayout.OnRefreshListener;
import com.withustudy.koudaizikao.custom.pullrefersh.SwipyRefreshLayoutDirection;
import com.withustudy.koudaizikao.entity.Post;
import com.withustudy.koudaizikao.entity.content.PostContent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FavoritePostFragment extends AbsBaseFragment
{
  public static final int ACTION_GET = 10;
  public static final int START_NEW_ACTIVITY = 1;
  private List<Post> list;
  private MyPostAdapter mAdapter;
  private CallBackListener mBackListener;
  private FavoritePostHandler mHandler;
  private LinearLayout mLayout;
  private ListView mListView;
  private SwipyRefreshLayout mSwipyRefreshLayout;
  private final int pagecount = 500;
  private final int pagenum = 1;

  public void bindData()
  {
    this.mListView.setAdapter(this.mAdapter);
  }

  public void initData()
  {
    this.mBackListener = new CallBackListener();
    this.mHandler = new FavoritePostHandler();
    this.list = new ArrayList();
    this.mAdapter = new MyPostAdapter(this.mContext, this.list, this.mHandler);
  }

  public void initListener()
  {
    this.mSwipyRefreshLayout.setOnRefreshListener(this.mBackListener);
    this.mListView.setOnScrollListener(this.mBackListener);
  }

  public void initView(View paramView)
  {
    this.mSwipyRefreshLayout = ((SwipyRefreshLayout)paramView.findViewById(2131100131));
    this.mListView = ((ListView)paramView.findViewById(2131100132));
    this.mLayout = ((LinearLayout)paramView.findViewById(2131100130));
  }

  public void onError(String paramString1, String paramString2, int paramInt)
  {
    super.onError(paramString1, paramString2, paramInt);
    this.mSwipyRefreshLayout.setRefreshing(false);
  }

  public View onInflaterRootView(LayoutInflater paramLayoutInflater, Bundle paramBundle)
  {
    return paramLayoutInflater.inflate(2130903116, null);
  }

  public void onResume()
  {
    super.onResume();
    AbsBaseUrlConstructor localAbsBaseUrlConstructor = UrlFactory.getInstance().getCollectPost();
    String[] arrayOfString = new String[3];
    arrayOfString[0] = this.mSP.getUserId();
    arrayOfString[1] = String.valueOf(1);
    arrayOfString[2] = String.valueOf(500);
    localAbsBaseUrlConstructor.constructUrl(this, arrayOfString, 10, this.mContext);
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
    try
    {
      PostContent localPostContent = (PostContent)UrlFactory.getInstanceGson().fromJson(paramString1, PostContent.class);
      if ((localPostContent == null) || (!localPostContent.getResult().equals("true")))
        break label191;
      if (localPostContent.getTopics().size() != 0)
      {
        this.mListView.setVisibility(0);
        this.mLayout.setVisibility(8);
        this.list.clear();
        this.list.addAll(localPostContent.getTopics());
        this.mAdapter.notifyDataSetChanged();
        return;
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      this.mListView.setVisibility(8);
      this.mLayout.setVisibility(0);
      Toast.makeText(this.mContext, "服务器未响应，请稍后再试", 0).show();
      return;
    }
    this.mListView.setVisibility(8);
    this.mLayout.setVisibility(0);
    return;
    label191: this.mListView.setVisibility(8);
    this.mLayout.setVisibility(0);
    Toast.makeText(this.mContext, "服务器未能返回数据，请稍后再试", 0).show();
  }

  class CallBackListener
    implements OnRefreshListener, OnScrollListener
  {
    CallBackListener()
    {
    }

    public void onRefresh(SwipyRefreshLayoutDirection paramSwipyRefreshLayoutDirection)
    {
      FavoritePostFragment.this.mSwipyRefreshLayout.setRefreshing(true);
      AbsBaseUrlConstructor localAbsBaseUrlConstructor = UrlFactory.getInstance().getCollectPost();
      FavoritePostFragment localFavoritePostFragment = FavoritePostFragment.this;
      String[] arrayOfString = new String[3];
      arrayOfString[0] = FavoritePostFragment.access$2(FavoritePostFragment.this).getUserId();
      arrayOfString[1] = String.valueOf(1);
      arrayOfString[2] = String.valueOf(500);
      localAbsBaseUrlConstructor.constructUrl(localFavoritePostFragment, arrayOfString, 10, FavoritePostFragment.this.mContext);
    }

    public void onScroll(AbsListView paramAbsListView, int paramInt1, int paramInt2, int paramInt3)
    {
      if (paramInt1 == 0)
      {
        FavoritePostFragment.this.mSwipyRefreshLayout.setEnabled(true);
        return;
      }
      FavoritePostFragment.this.mSwipyRefreshLayout.setEnabled(false);
    }

    public void onScrollStateChanged(AbsListView paramAbsListView, int paramInt)
    {
    }
  }

  public class FavoritePostHandler extends Handler
  {
    public FavoritePostHandler()
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
      }
      FavoritePostFragment.this.startNewActivity(PostDetailActivity.class, false, (Bundle)paramMessage.obj);
    }
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.fragment.FavoritePostFragment
 * JD-Core Version:    0.6.0
 */