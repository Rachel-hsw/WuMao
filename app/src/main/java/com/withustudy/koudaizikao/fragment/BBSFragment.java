package com.withustudy.koudaizikao.fragment;

import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.umeng.analytics.MobclickAgent;
import com.withustudy.koudaizikao.action.AbsBaseUrlConstructor;
import com.withustudy.koudaizikao.action.UrlFactory;
import com.withustudy.koudaizikao.activity.AllSectionActivity;
import com.withustudy.koudaizikao.activity.BbsHotActivity;
import com.withustudy.koudaizikao.activity.SectionDetailActivity;
import com.withustudy.koudaizikao.adapter.BbsMainAdapter;
import com.withustudy.koudaizikao.base.AbsBaseFragment;
import com.withustudy.koudaizikao.config.KouDaiSP;
import com.withustudy.koudaizikao.custom.LoadingView;
import com.withustudy.koudaizikao.entity.Forum;
import com.withustudy.koudaizikao.entity.content.ForumsContent;
import com.withustudy.koudaizikao.tools.ToolsUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BBSFragment extends AbsBaseFragment
{
  public static final String HOT_TYPE = "type";
  public static final int REFRESH = 1;
  public static BBSMainHandler mHandler;
  private Button buttonAll;
  private boolean isRefresh = false;
  private LinearLayout layoutPost;
  private LinearLayout layoutSection;
  private List<Forum> list;
  private BbsMainAdapter mAdapter;
  private CallBackListener mBackListener;
  private PullToRefreshListView mListView;
  private LoadingView mLoadingView;
  private String[] parameter;

  public void bindData()
  {
    this.mListView.setVisibility(8);
    this.mLoadingView.setVisibility(0);
    this.mListView.setAdapter(this.mAdapter);
    ToolsUtils.setList(0, this.mListView, this.mContext);
    mHandler.sendEmptyMessage(1);
  }

  public void initData()
  {
    this.parameter = new String[3];
    this.parameter[0] = this.mSP.getUserId();
    this.parameter[1] = this.mSP.getProId();
    this.parameter[2] = this.mSP.getMajorId();
    this.mBackListener = new CallBackListener();
    mHandler = new BBSMainHandler();
    this.list = new ArrayList();
    this.mAdapter = new BbsMainAdapter(this.mContext, 1);
    this.mAdapter.setForumList(this.list);
  }

  public void initListener()
  {
    this.buttonAll.setOnClickListener(this.mBackListener);
    this.layoutPost.setOnClickListener(this.mBackListener);
    this.layoutSection.setOnClickListener(this.mBackListener);
    this.mListView.setOnItemClickListener(this.mBackListener);
    this.mListView.setOnRefreshListener(this.mBackListener);
  }

  public void initView(View paramView)
  {
    this.buttonAll = ((Button)paramView.findViewById(2131100111));
    this.layoutPost = ((LinearLayout)paramView.findViewById(2131100112));
    this.layoutSection = ((LinearLayout)paramView.findViewById(2131100113));
    this.mLoadingView = ((LoadingView)paramView.findViewById(2131100115));
    this.mListView = ((PullToRefreshListView)paramView.findViewById(2131100114));
  }

  public void onError(String paramString1, String paramString2, int paramInt)
  {
    super.onError(paramString1, paramString2, paramInt);
    this.isRefresh = false;
    this.mListView.onRefreshComplete();
    Toast.makeText(this.mContext, getResources().getString(2131165381), 0).show();
  }

  public View onInflaterRootView(LayoutInflater paramLayoutInflater, Bundle paramBundle)
  {
    return paramLayoutInflater.inflate(2130903108, null);
  }

  public void onResume()
  {
    super.onResume();
    if (!this.parameter[0].equals(this.mSP.getUserId()))
    {
      this.parameter[0] = this.mSP.getUserId();
      this.parameter[1] = this.mSP.getProId();
      this.parameter[2] = this.mSP.getMajorId();
      mHandler.sendEmptyMessage(1);
    }
  }

  public void onSuccess(String paramString1, Map<String, String> paramMap, String paramString2, int paramInt)
  {
    super.onSuccess(paramString1, paramMap, paramString2, paramInt);
    this.mLoadingView.setVisibility(8);
    this.mListView.setVisibility(0);
    this.isRefresh = false;
    this.mListView.onRefreshComplete();
    Gson localGson;
    if (paramString1 != null)
      localGson = new Gson();
    try
    {
      ForumsContent localForumsContent = (ForumsContent)localGson.fromJson(paramString1, ForumsContent.class);
      if (localForumsContent != null)
      {
        this.list.clear();
        this.list.addAll(localForumsContent.getForums());
        this.mAdapter.notifyDataSetChanged();
      }
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  public class BBSMainHandler extends Handler
  {
    public BBSMainHandler()
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
      BBSFragment.this.isRefresh = true;
      UrlFactory.getInstance().getBBSFollow().constructUrl(BBSFragment.this, BBSFragment.this.parameter, 1, BBSFragment.this.mContext);
    }
  }

  class CallBackListener
    implements OnClickListener, OnItemClickListener, PullToRefreshBase.OnRefreshListener<ListView>
  {
    CallBackListener()
    {
    }

    public void onClick(View paramView)
    {
      switch (paramView.getId())
      {
      default:
        return;
      case 2131100111:
        MobclickAgent.onEvent(BBSFragment.this.mContext, "bbs_all");
        BBSFragment.this.startNewActivity(AllSectionActivity.class, false, null);
        return;
      case 2131100112:
        MobclickAgent.onEvent(BBSFragment.this.mContext, "bbs_hot_post");
        Bundle localBundle2 = new Bundle();
        localBundle2.putInt("type", 0);
        BBSFragment.this.startNewActivity(BbsHotActivity.class, false, localBundle2);
        return;
      case 2131100113:
      }
      MobclickAgent.onEvent(BBSFragment.this.mContext, "bbs_hot_section");
      Bundle localBundle1 = new Bundle();
      localBundle1.putInt("type", 1);
      BBSFragment.this.startNewActivity(BbsHotActivity.class, false, localBundle1);
    }

    public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
    {
      MobclickAgent.onEvent(BBSFragment.this.mContext, "bbs_into_followed_section");
      Bundle localBundle = new Bundle();
      localBundle.putSerializable("receive_code", (Serializable)BBSFragment.this.list.get(paramInt - 1));
      BBSFragment.this.startNewActivity(SectionDetailActivity.class, 2130968581, 2130968579, false, localBundle);
    }

    public void onRefresh(PullToRefreshBase<ListView> paramPullToRefreshBase)
    {
      if (!BBSFragment.this.isRefresh)
        BBSFragment.mHandler.sendEmptyMessage(1);
    }
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.fragment.BBSFragment
 * JD-Core Version:    0.6.0
 */