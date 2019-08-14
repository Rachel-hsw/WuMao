package com.withustudy.koudaizikao.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.android.http.FileDownLoad;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.umeng.analytics.MobclickAgent;
import com.withustudy.koudaizikao.action.AbsBaseUrlConstructor;
import com.withustudy.koudaizikao.action.UrlFactory;
import com.withustudy.koudaizikao.adapter.SectionDetailAdapter;
import com.withustudy.koudaizikao.base.AbsBaseActivity;
import com.withustudy.koudaizikao.config.KouDaiSP;
import com.withustudy.koudaizikao.custom.LoadingView;
import com.withustudy.koudaizikao.entity.Forum;
import com.withustudy.koudaizikao.entity.Post;
import com.withustudy.koudaizikao.entity.SimpleResult;
import com.withustudy.koudaizikao.entity.content.PostContent;
import com.withustudy.koudaizikao.fragment.BBSFragment;
import com.withustudy.koudaizikao.fragment.BBSFragment.BBSMainHandler;
import com.withustudy.koudaizikao.tools.LogUtils;
import com.withustudy.koudaizikao.tools.ProTools;
import com.withustudy.koudaizikao.tools.ToolsUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SectionDetailActivity extends AbsBaseActivity
{
  public static final int ACTION_FOLLOW = 12;
  public static final int ACTION_LOAD = 11;
  public static final int ACTION_REFRESH = 10;
  public static final String ALL = "all";
  public static final String ELITE = "elite";
  public static final int FOLLOW = 3;
  public static final int LOAD_FINISH = 2;
  public static final String RECEIVE_CODE = "receive_code";
  public static final int REFRESH_FINISH = 1;
  public static final int REQUEST_ADD_POST = 22;
  public static final int REQUEST_DELETE_POST = 20;
  public static final int RESULT_ADD_POST = 23;
  public static final int RESULT_CHANGE = 24;
  public static final int RESULT_DELETE_POST = 21;
  public static final int START_NEW_ACTIVITY = 4;
  private Button buttonAll;
  private Button buttonBack;
  private Button buttonElite;
  private Button buttonPublish;
  private ImageView imageAll;
  private ImageView imageAvatar;
  private ImageView imageElite;
  private boolean isLoading = false;
  private LinearLayout layoutAll;
  private LinearLayout layoutElite;
  private List<Post> list;
  private SectionDetailAdapter mAdapter;
  private CallBackListener mBackListener;
  private Forum mForum;
  private SectionDetailHandler mHandler;
  private SwipeRefreshLayout mLayout;
  private PullToRefreshListView mListView;
  private LoadingView mLoadingView;
  private final int pageCount = 8;
  private int pageNum = 1;
  private String[] param;
  private String showType;
  private List<Post> temp;
  private TextView textDiscuss;
  private TextView textFollow;
  private TextView textName;
  private TextView textPost;
  private TextView textSwitch;
  private int topCount;

  private void setButton(int paramInt)
  {
    if (paramInt == 0)
    {
      this.buttonAll.setTextColor(Color.parseColor("#ffffff"));
      this.imageAll.setVisibility(0);
      this.buttonElite.setTextColor(Color.parseColor("#DCDCDC"));
      this.imageElite.setVisibility(4);
    }
    for (this.showType = "all"; ; this.showType = "elite")
    {
      this.pageNum = 1;
      setPara();
      UrlFactory.getInstance().getPost().constructUrl(this, this.param, 10, this.mContext);
      return;
      this.buttonElite.setTextColor(Color.parseColor("#ffffff"));
      this.imageElite.setVisibility(0);
      this.buttonAll.setTextColor(Color.parseColor("#DCDCDC"));
      this.imageAll.setVisibility(4);
    }
  }

  private void setPara()
  {
    this.param = new String[6];
    this.param[0] = this.mSP.getUserId();
    this.param[1] = String.valueOf(this.mForum.getForum_id());
    this.param[2] = String.valueOf(this.pageNum);
    this.param[3] = String.valueOf(8);
    this.param[4] = "3";
    if (this.showType.equals("all"))
    {
      this.param[5] = "0";
      return;
    }
    this.param[5] = "1";
  }

  protected void bindData()
  {
    if ((this.mForum.getForum_img() != null) && (!this.mForum.getForum_img().equals("")))
      this.mFileDownLoad.downLoadImage(this.mForum.getForum_img(), this.imageAvatar);
    ToolsUtils.setList(1, this.mListView, this.mContext);
    this.mLoadingView.setVisibility(0);
    setButton(0);
  }

  protected void initData()
  {
    this.mForum = ((Forum)getIntent().getExtras().getSerializable("receive_code"));
    this.mBackListener = new CallBackListener();
    this.mHandler = new SectionDetailHandler();
    this.temp = new ArrayList();
    this.list = new ArrayList();
  }

  protected void initListener()
  {
    this.buttonBack.setOnClickListener(this.mBackListener);
    this.layoutAll.setOnClickListener(this.mBackListener);
    this.layoutElite.setOnClickListener(this.mBackListener);
    this.mLayout.setOnRefreshListener(this.mBackListener);
    this.mListView.setOnScrollListener(this.mBackListener);
    this.mListView.setOnRefreshListener(this.mBackListener);
    this.buttonPublish.setOnClickListener(this.mBackListener);
    this.textSwitch.setOnClickListener(this.mBackListener);
  }

  protected void initView()
  {
    this.mLayout = ((SwipeRefreshLayout)findViewById(2131099937));
    this.buttonBack = ((Button)findViewById(2131099923));
    this.layoutAll = ((LinearLayout)findViewById(2131099924));
    this.layoutElite = ((LinearLayout)findViewById(2131099927));
    this.buttonAll = ((Button)findViewById(2131099925));
    this.buttonElite = ((Button)findViewById(2131099928));
    this.imageAll = ((ImageView)findViewById(2131099926));
    this.imageElite = ((ImageView)findViewById(2131099929));
    this.buttonPublish = ((Button)findViewById(2131099930));
    this.imageAvatar = ((ImageView)findViewById(2131099931));
    this.textName = ((TextView)findViewById(2131099933));
    this.textFollow = ((TextView)findViewById(2131099934));
    this.textPost = ((TextView)findViewById(2131099935));
    this.textDiscuss = ((TextView)findViewById(2131099936));
    this.textSwitch = ((TextView)findViewById(2131099932));
    this.mLoadingView = ((LoadingView)findViewById(2131099939));
    this.mListView = ((PullToRefreshListView)findViewById(2131099938));
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if ((paramInt2 == 21) || (paramInt2 == 23))
    {
      this.mProTools.startDialog(true);
      this.pageNum = 1;
      setPara();
      UrlFactory.getInstance().getPost().constructUrl(this, this.param, 10, this.mContext);
      if (paramInt2 == 21)
        this.mForum.setForum_topics(-1 + this.mForum.getForum_topics());
    }
    do
    {
      return;
      this.mForum.setForum_topics(1 + this.mForum.getForum_topics());
      return;
    }
    while (paramInt2 != 24);
    Post localPost = (Post)paramIntent.getBundleExtra("result").getSerializable("change");
    for (int i = 0; ; i++)
    {
      if (i >= this.list.size())
      {
        this.mAdapter.notifyDataSetChanged();
        return;
      }
      if (((Post)this.list.get(i)).getTopic_id() != localPost.getTopic_id())
        continue;
      this.list.remove(i);
      this.list.add(i, localPost);
    }
  }

  public void onError(String paramString1, String paramString2, int paramInt)
  {
    super.onError(paramString1, paramString2, paramInt);
    this.mProTools.dismissDislog();
    switch (paramInt)
    {
    default:
      return;
    case 10:
      this.mLayout.setRefreshing(false);
      return;
    case 11:
    }
    this.isLoading = false;
    this.mListView.onRefreshComplete();
  }

  protected void onResume()
  {
    super.onResume();
    this.textName.setText(this.mForum.getForum_name());
    this.textFollow.setText(String.valueOf(this.mForum.getForum_user()));
    this.textPost.setText(String.valueOf(this.mForum.getForum_topics()));
    this.textDiscuss.setText(String.valueOf(this.mForum.getForum_reply()));
    if (!this.mSP.getUserId().equals(""))
    {
      this.textSwitch.setVisibility(0);
      if (this.mForum.getForum_isfollow() == 1)
      {
        this.textSwitch.setBackgroundResource(2130837521);
        this.textSwitch.setText(getResources().getString(2131165349));
        return;
      }
      this.textSwitch.setBackgroundResource(2130837527);
      this.textSwitch.setText(getResources().getString(2131165346));
      return;
    }
    this.textSwitch.setVisibility(8);
  }

  public void onSuccess(String paramString1, Map<String, String> paramMap, String paramString2, int paramInt)
  {
    super.onSuccess(paramString1, paramMap, paramString2, paramInt);
    this.mProTools.dismissDislog();
    this.mLoadingView.setVisibility(8);
    switch (paramInt)
    {
    default:
    case 10:
    case 11:
    case 12:
    }
    do
    {
      do
      {
        do
        {
          return;
          this.mLayout.setRefreshing(false);
        }
        while (paramString1 == null);
        try
        {
          PostContent localPostContent2 = (PostContent)UrlFactory.getInstanceGson().fromJson(paramString1, PostContent.class);
          if ((localPostContent2 != null) && (localPostContent2.getResult().equals("true")))
          {
            this.mForum = localPostContent2.getForum();
            this.temp.clear();
            if (localPostContent2.getTopTopics() != null)
            {
              this.topCount = localPostContent2.getTopTopics().size();
              this.temp.addAll(localPostContent2.getTopTopics());
            }
            if (localPostContent2.getTopics() != null)
              this.temp.addAll(localPostContent2.getTopics());
            this.mHandler.sendEmptyMessage(1);
            return;
          }
        }
        catch (Exception localException3)
        {
          localException3.printStackTrace();
          Toast.makeText(this.mContext, "服务器未响应，请稍后再试", 0).show();
          return;
        }
        Toast.makeText(this.mContext, "这个模块还没有任何帖子", 0).show();
        return;
        this.isLoading = false;
        this.mListView.onRefreshComplete();
      }
      while (paramString1 == null);
      try
      {
        PostContent localPostContent1 = (PostContent)UrlFactory.getInstanceGson().fromJson(paramString1, PostContent.class);
        if ((localPostContent1 != null) && (localPostContent1.getResult().equals("true")))
        {
          this.mForum = localPostContent1.getForum();
          if (localPostContent1.getTopics() != null)
            this.temp.addAll(localPostContent1.getTopics());
          this.mHandler.sendEmptyMessage(2);
          return;
        }
      }
      catch (Exception localException2)
      {
        localException2.printStackTrace();
        Toast.makeText(this.mContext, "服务器未响应，请稍后再试", 0).show();
        return;
      }
      Toast.makeText(this.mContext, "这个模块还没有任何帖子", 0).show();
      return;
    }
    while (paramString1 == null);
    try
    {
      SimpleResult localSimpleResult = (SimpleResult)UrlFactory.getInstanceGson().fromJson(paramString1, SimpleResult.class);
      if (localSimpleResult != null)
      {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(3, localSimpleResult.getResult()));
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
    setContentView(2130903071);
  }

  class CallBackListener
    implements OnScrollListener, OnClickListener, OnRefreshListener, PullToRefreshBase.OnRefreshListener<ListView>
  {
    CallBackListener()
    {
    }

    public void onClick(View paramView)
    {
      switch (paramView.getId())
      {
      case 2131099925:
      case 2131099926:
      case 2131099928:
      case 2131099929:
      case 2131099931:
      default:
        return;
      case 2131099932:
        if (SectionDetailActivity.this.mSP.getUserId().equals(""))
        {
          Toast.makeText(SectionDetailActivity.this.mContext, "请先登录", 0).show();
          return;
        }
        String[] arrayOfString = new String[3];
        arrayOfString[0] = SectionDetailActivity.access$14(SectionDetailActivity.this).getUserId();
        arrayOfString[1] = String.valueOf(SectionDetailActivity.access$8(SectionDetailActivity.this).getForum_id());
        if (SectionDetailActivity.this.mForum.getForum_isfollow() == 0)
        {
          MobclickAgent.onEvent(SectionDetailActivity.this.mContext, "bbs_follow");
          LogUtils.myLog("关注");
          arrayOfString[2] = "follow";
          UrlFactory.getInstance().followSection().constructUrl(SectionDetailActivity.this, arrayOfString, 12, SectionDetailActivity.this.mContext);
        }
        while (true)
        {
          SectionDetailActivity.this.mProTools.startDialog(true);
          return;
          LogUtils.myLog("取消关注");
          arrayOfString[2] = "unfollow";
          UrlFactory.getInstance().followSection().constructUrl(SectionDetailActivity.this, arrayOfString, 12, SectionDetailActivity.this.mContext);
        }
      case 2131099923:
        SectionDetailActivity.this.finish();
        return;
      case 2131099924:
        SectionDetailActivity.this.setButton(0);
        return;
      case 2131099927:
        SectionDetailActivity.this.setButton(1);
        return;
      case 2131099930:
      }
      if (SectionDetailActivity.this.mSP.getUserId().equals(""))
      {
        Toast.makeText(SectionDetailActivity.this.mContext, "请先登录", 0).show();
        return;
      }
      Bundle localBundle = new Bundle();
      localBundle.putInt("id", SectionDetailActivity.this.mForum.getForum_id());
      SectionDetailActivity.this.startNewActivityForResult(AddPostActivity.class, 22, localBundle);
    }

    public void onRefresh()
    {
      SectionDetailActivity.this.mLayout.setRefreshing(true);
      SectionDetailActivity.this.pageNum = 1;
      SectionDetailActivity.this.setPara();
      UrlFactory.getInstance().getPost().constructUrl(SectionDetailActivity.this, SectionDetailActivity.this.param, 10, SectionDetailActivity.this.mContext);
    }

    public void onRefresh(PullToRefreshBase<ListView> paramPullToRefreshBase)
    {
      if (!SectionDetailActivity.this.isLoading)
      {
        SectionDetailActivity.this.isLoading = true;
        SectionDetailActivity localSectionDetailActivity = SectionDetailActivity.this;
        localSectionDetailActivity.pageNum = (1 + localSectionDetailActivity.pageNum);
        SectionDetailActivity.this.setPara();
        UrlFactory.getInstance().getPost().constructUrl(SectionDetailActivity.this, SectionDetailActivity.this.param, 11, SectionDetailActivity.this.mContext);
      }
    }

    public void onScroll(AbsListView paramAbsListView, int paramInt1, int paramInt2, int paramInt3)
    {
      if (paramInt1 == 0)
      {
        SectionDetailActivity.this.mLayout.setEnabled(true);
        return;
      }
      SectionDetailActivity.this.mLayout.setEnabled(false);
    }

    public void onScrollStateChanged(AbsListView paramAbsListView, int paramInt)
    {
    }
  }

  class SectionDetailHandler extends Handler
  {
    SectionDetailHandler()
    {
    }

    public void handleMessage(Message paramMessage)
    {
      super.handleMessage(paramMessage);
      switch (paramMessage.what)
      {
      default:
      case 1:
      case 2:
      case 3:
        do
        {
          return;
          SectionDetailActivity.this.mAdapter = new SectionDetailAdapter(SectionDetailActivity.this.mContext, SectionDetailActivity.this.topCount, SectionDetailActivity.this.list, SectionDetailActivity.this.mHandler);
          SectionDetailActivity.this.mListView.setAdapter(SectionDetailActivity.this.mAdapter);
          SectionDetailActivity.this.list.clear();
          SectionDetailActivity.this.list.addAll(SectionDetailActivity.this.temp);
          SectionDetailActivity.this.mAdapter.notifyDataSetChanged();
          return;
          SectionDetailActivity.this.list.clear();
          SectionDetailActivity.this.list.addAll(SectionDetailActivity.this.temp);
          SectionDetailActivity.this.mAdapter.notifyDataSetChanged();
          return;
        }
        while (!((String)paramMessage.obj).equals("true"));
        if (SectionDetailActivity.this.mForum.getForum_isfollow() == 1)
        {
          SectionDetailActivity.this.textSwitch.setBackgroundResource(2130837527);
          SectionDetailActivity.this.textSwitch.setText(SectionDetailActivity.this.getResources().getString(2131165346));
          SectionDetailActivity.this.mForum.setForum_isfollow(0);
        }
        while (true)
        {
          if (BBSFragment.mHandler != null)
            BBSFragment.mHandler.sendEmptyMessage(1);
          Toast.makeText(SectionDetailActivity.this.mContext, "操作成功", 0).show();
          return;
          SectionDetailActivity.this.textSwitch.setBackgroundResource(2130837521);
          SectionDetailActivity.this.textSwitch.setText(SectionDetailActivity.this.getResources().getString(2131165349));
          SectionDetailActivity.this.mForum.setForum_isfollow(1);
        }
      case 4:
      }
      MobclickAgent.onEvent(SectionDetailActivity.this.mContext, "bbs_into_post");
      SectionDetailActivity.this.startNewActivityForResult(PostDetailActivity.class, 20, (Bundle)paramMessage.obj);
    }
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.activity.SectionDetailActivity
 * JD-Core Version:    0.6.0
 */