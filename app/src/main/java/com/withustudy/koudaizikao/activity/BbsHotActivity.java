package com.withustudy.koudaizikao.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.Gson;
import com.umeng.analytics.MobclickAgent;
import com.withustudy.koudaizikao.action.AbsBaseUrlConstructor;
import com.withustudy.koudaizikao.action.UrlFactory;
import com.withustudy.koudaizikao.adapter.BbsMainAdapter;
import com.withustudy.koudaizikao.base.AbsBaseActivity;
import com.withustudy.koudaizikao.config.KouDaiSP;
import com.withustudy.koudaizikao.custom.LoadingView;
import com.withustudy.koudaizikao.entity.Forum;
import com.withustudy.koudaizikao.entity.Post;
import com.withustudy.koudaizikao.entity.content.ForumsContent;
import com.withustudy.koudaizikao.entity.content.PostContent;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class BbsHotActivity extends AbsBaseActivity
{
  public static final int ACTION_POST = 2;
  public static final int ACTION_SECTION = 1;
  public static final int INVALID = -1;
  public static final int LOADING_DATA_FINISH = 10;
  public static final int POST = 0;
  public static final int SECTION = 1;
  private Button buttonBack;
  private List<Forum> forumList;
  private BbsMainAdapter mAdapter;
  private CallBackListener mBackListener;
  private BbsHotHandler mHandler;
  private ListView mListView;
  private LoadingView mLoadingView;
  private String[] parameter;
  private List<Post> postList;
  private TextView textTitle;
  private int type;

  protected void bindData()
  {
    if (this.type == 0)
    {
      this.textTitle.setText(getResources().getString(2131165343));
      return;
    }
    this.textTitle.setText(getResources().getString(2131165345));
  }

  protected void initData()
  {
    this.type = getIntent().getIntExtra("type", -1);
    this.mBackListener = new CallBackListener();
    this.mHandler = new BbsHotHandler();
    this.parameter = new String[1];
    this.parameter[0] = this.mSP.getUserId();
  }

  protected void initListener()
  {
    this.buttonBack.setOnClickListener(this.mBackListener);
    this.mListView.setOnItemClickListener(this.mBackListener);
  }

  protected void initView()
  {
    this.textTitle = ((TextView)findViewById(2131099715));
    this.buttonBack = ((Button)findViewById(2131099714));
    this.mListView = ((ListView)findViewById(2131099717));
    this.mLoadingView = ((LoadingView)findViewById(2131099716));
  }

  public void onError(String paramString1, String paramString2, int paramInt)
  {
    super.onError(paramString1, paramString2, paramInt);
    Toast.makeText(this.mContext, getResources().getString(2131165381), 0).show();
  }

  protected void onResume()
  {
    super.onResume();
    this.mListView.setVisibility(8);
    this.mLoadingView.setVisibility(0);
    if (this.type == 1)
    {
      UrlFactory.getInstance().getHotSection().constructUrl(this, this.parameter, 1, this.mContext);
      return;
    }
    UrlFactory.getInstance().getHotPost().constructUrl(this, this.parameter, 2, this.mContext);
  }

  public void onSuccess(String paramString1, Map<String, String> paramMap, String paramString2, int paramInt)
  {
    super.onSuccess(paramString1, paramMap, paramString2, paramInt);
    this.mLoadingView.setVisibility(8);
    this.mListView.setVisibility(0);
    switch (paramInt)
    {
    default:
    case 1:
    case 2:
    }
    do
    {
      do
        return;
      while (paramString1 == null);
      try
      {
        ForumsContent localForumsContent = (ForumsContent)UrlFactory.getInstanceGson().fromJson(paramString1, ForumsContent.class);
        if (localForumsContent != null)
        {
          this.forumList = localForumsContent.getForums();
          this.mHandler.sendEmptyMessage(10);
          return;
        }
      }
      catch (Exception localException2)
      {
        localException2.printStackTrace();
        Toast.makeText(this.mContext, "服务器未响应，请稍后再试", 0).show();
        return;
      }
      Toast.makeText(this.mContext, "服务器未能返回数据，请稍后再试", 0).show();
      return;
    }
    while (paramString1 == null);
    try
    {
      PostContent localPostContent = (PostContent)UrlFactory.getInstanceGson().fromJson(paramString1, PostContent.class);
      if (localPostContent != null)
      {
        this.postList = localPostContent.getTopics();
        this.mHandler.sendEmptyMessage(10);
        return;
      }
    }
    catch (Exception localException1)
    {
      localException1.printStackTrace();
      Toast.makeText(this.mContext, "服务器未响应，请稍后再试", 0).show();
      return;
    }
    Toast.makeText(this.mContext, "服务器未能返回数据，请稍后再试", 0).show();
  }

  protected void setContentView()
  {
    setContentView(2130903044);
  }

  class BbsHotHandler extends Handler
  {
    BbsHotHandler()
    {
    }

    public void handleMessage(Message paramMessage)
    {
      super.handleMessage(paramMessage);
      switch (paramMessage.what)
      {
      default:
        return;
      case 10:
      }
      BbsHotActivity.this.mAdapter = new BbsMainAdapter(BbsHotActivity.this.mContext, BbsHotActivity.this.type);
      if (BbsHotActivity.this.type == 1)
        BbsHotActivity.this.mAdapter.setForumList(BbsHotActivity.this.forumList);
      while (true)
      {
        BbsHotActivity.this.mListView.setAdapter(BbsHotActivity.this.mAdapter);
        return;
        BbsHotActivity.this.mAdapter.setPostList(BbsHotActivity.this.postList);
      }
    }
  }

  class CallBackListener
    implements OnClickListener, OnItemClickListener
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
      case 2131099714:
      }
      BbsHotActivity.this.finish(2130968578, 2130968582);
    }

    public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
    {
      if (BbsHotActivity.this.type == 1)
      {
        Bundle localBundle1 = new Bundle();
        localBundle1.putSerializable("receive_code", (Serializable)BbsHotActivity.this.forumList.get(paramInt));
        BbsHotActivity.this.startNewActivity(SectionDetailActivity.class, 2130968581, 2130968579, false, localBundle1);
        return;
      }
      MobclickAgent.onEvent(BbsHotActivity.this.mContext, "bbs_into_hot_post");
      Bundle localBundle2 = new Bundle();
      localBundle2.putInt("id", ((Post)BbsHotActivity.this.postList.get(paramInt)).getTopic_id());
      BbsHotActivity.this.startNewActivity(PostDetailActivity.class, 2130968581, 2130968579, false, localBundle2);
    }
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.activity.BbsHotActivity
 * JD-Core Version:    0.6.0
 */