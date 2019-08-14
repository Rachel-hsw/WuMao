package com.withustudy.koudaizikao.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.withustudy.koudaizikao.action.AbsBaseUrlConstructor;
import com.withustudy.koudaizikao.action.UrlFactory;
import com.withustudy.koudaizikao.adapter.MyPostAdapter;
import com.withustudy.koudaizikao.base.AbsBaseActivity;
import com.withustudy.koudaizikao.config.KouDaiSP;
import com.withustudy.koudaizikao.entity.Post;
import com.withustudy.koudaizikao.entity.content.PostContent;
import com.withustudy.koudaizikao.tools.ProTools;
import com.withustudy.koudaizikao.tools.ToolsUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MyPostActivity extends AbsBaseActivity
{
  public static final int ACTION_LOAD = 11;
  public static final int ACTION_REFRESH = 10;
  public static final int LOAD_FINISH = 2;
  public static final int MY_PUBLISH = 100;
  public static final int MY_REPLY = 101;
  public static final int REFRESH_FINISH = 1;
  public static final int START_NEW_ACTIVITY = 3;
  private Button buttonBack;
  private boolean isLoading = false;
  private boolean isRefresh = false;
  private List<Post> list;
  private MyPostAdapter mAdapter;
  private CallBackListener mBackListener;
  private MyPostHandler mHandler;
  private LinearLayout mLayout;
  private PullToRefreshListView mListView;
  private final int pageCount = 5;
  private int pageNum;
  private String[] parameter;
  private int state;
  private TextView textTitle;

  protected void bindData()
  {
    if (this.state == 100)
      this.textTitle.setText(getResources().getString(2131165234));
    while (true)
    {
      ToolsUtils.setList(2, this.mListView, this.mContext);
      this.isRefresh = true;
      this.pageNum = 1;
      this.parameter[2] = String.valueOf(this.pageNum);
      this.parameter[3] = String.valueOf(5);
      UrlFactory.getInstance().getMyPost().constructUrl(this, this.parameter, 10, this.mContext);
      this.mProTools.startDialog();
      return;
      this.textTitle.setText(getResources().getString(2131165235));
    }
  }

  protected void initData()
  {
    this.mHandler = new MyPostHandler();
    this.mBackListener = new CallBackListener();
    this.state = getIntent().getExtras().getInt("state");
    this.parameter = new String[4];
    if (this.state == 100)
      this.parameter[0] = "list";
    while (true)
    {
      this.parameter[1] = this.mSP.getUserId();
      this.list = new ArrayList();
      return;
      this.parameter[0] = "replyList";
    }
  }

  protected void initListener()
  {
    this.buttonBack.setOnClickListener(this.mBackListener);
    this.mListView.setOnRefreshListener(this.mBackListener);
  }

  protected void initView()
  {
    this.buttonBack = ((Button)findViewById(2131099842));
    this.textTitle = ((TextView)findViewById(2131099843));
    this.mLayout = ((LinearLayout)findViewById(2131099845));
    this.mListView = ((PullToRefreshListView)findViewById(2131099844));
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramInt2 == 21)
    {
      this.mProTools.startDialog(true);
      this.isRefresh = true;
      this.pageNum = 1;
      this.parameter[2] = String.valueOf(this.pageNum);
      this.parameter[3] = String.valueOf(5);
      UrlFactory.getInstance().getMyPost().constructUrl(this, this.parameter, 10, this.mContext);
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
      this.isRefresh = false;
      this.mListView.onRefreshComplete();
      return;
    case 11:
    }
    this.isLoading = false;
    this.mListView.onRefreshComplete();
  }

  public void onSuccess(String paramString1, Map<String, String> paramMap, String paramString2, int paramInt)
  {
    super.onSuccess(paramString1, paramMap, paramString2, paramInt);
    this.mProTools.dismissDislog();
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
        this.isRefresh = false;
        this.mListView.onRefreshComplete();
      }
      while (paramString1 == null);
      try
      {
        PostContent localPostContent2 = (PostContent)UrlFactory.getInstanceGson().fromJson(paramString1, PostContent.class);
        if ((localPostContent2 != null) && (localPostContent2.getResult().equals("true")))
        {
          this.mHandler.sendMessage(this.mHandler.obtainMessage(1, localPostContent2.getTopics()));
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
      this.isLoading = false;
      this.mListView.onRefreshComplete();
    }
    while (paramString1 == null);
    try
    {
      PostContent localPostContent1 = (PostContent)UrlFactory.getInstanceGson().fromJson(paramString1, PostContent.class);
      if ((localPostContent1 != null) && (localPostContent1.getResult().equals("true")))
      {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(2, localPostContent1.getTopics()));
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
    setContentView(2130903062);
  }

  class CallBackListener
    implements OnClickListener, PullToRefreshBase.OnRefreshListener2<ListView>
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
      case 2131099842:
      }
      MyPostActivity.this.finish(2130968578, 2130968582);
    }

    public void onPullDownToRefresh(PullToRefreshBase<ListView> paramPullToRefreshBase)
    {
      if (!MyPostActivity.this.isRefresh)
      {
        MyPostActivity.this.isRefresh = true;
        MyPostActivity.this.pageNum = 1;
        MyPostActivity.this.parameter[2] = String.valueOf(MyPostActivity.access$11(MyPostActivity.this));
        MyPostActivity.this.parameter[3] = String.valueOf(5);
        UrlFactory.getInstance().getMyPost().constructUrl(MyPostActivity.this, MyPostActivity.this.parameter, 10, MyPostActivity.this.mContext);
      }
    }

    public void onPullUpToRefresh(PullToRefreshBase<ListView> paramPullToRefreshBase)
    {
      if (!MyPostActivity.this.isLoading)
      {
        MyPostActivity.this.isLoading = true;
        MyPostActivity localMyPostActivity = MyPostActivity.this;
        localMyPostActivity.pageNum = (1 + localMyPostActivity.pageNum);
        MyPostActivity.this.parameter[2] = String.valueOf(MyPostActivity.access$11(MyPostActivity.this));
        MyPostActivity.this.parameter[3] = String.valueOf(5);
        UrlFactory.getInstance().getMyPost().constructUrl(MyPostActivity.this, MyPostActivity.this.parameter, 11, MyPostActivity.this.mContext);
      }
    }
  }

  public class MyPostHandler extends Handler
  {
    public MyPostHandler()
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
        MyPostActivity.this.list.clear();
        MyPostActivity.this.list.addAll((List)paramMessage.obj);
        if (MyPostActivity.this.list.size() == 0)
          MyPostActivity.this.mLayout.setVisibility(0);
        while (true)
        {
          MyPostActivity.this.mAdapter = new MyPostAdapter(MyPostActivity.this.mContext, MyPostActivity.this.list, MyPostActivity.this.mHandler);
          MyPostActivity.this.mListView.setAdapter(MyPostActivity.this.mAdapter);
          return;
          MyPostActivity.this.mLayout.setVisibility(8);
        }
      case 2:
        MyPostActivity.this.list.addAll((List)paramMessage.obj);
        MyPostActivity.this.mAdapter.notifyDataSetChanged();
        return;
      case 3:
      }
      MyPostActivity.this.startNewActivityForResult(PostDetailActivity.class, 2130968581, 2130968579, 20, (Bundle)paramMessage.obj);
    }
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.activity.MyPostActivity
 * JD-Core Version:    0.6.0
 */