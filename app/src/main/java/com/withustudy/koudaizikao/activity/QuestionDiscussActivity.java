package com.withustudy.koudaizikao.activity;

import android.content.res.Resources;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.withustudy.koudaizikao.adapter.QuestionDiscussAdapter;
import com.withustudy.koudaizikao.base.AbsBaseActivity;
import com.withustudy.koudaizikao.tools.LogUtils;
import com.withustudy.koudaizikao.tools.ToolsUtils;
import java.util.ArrayList;
import java.util.List;

public class QuestionDiscussActivity extends AbsBaseActivity
{
  public static final int LOAD_FINISH = 2;
  public static final int REFRESH_FINISH = 1;
  private Button buttonBack;
  private boolean isLoad = false;
  private boolean isRefresh = false;
  private List<Integer> list;
  private QuestionDiscussAdapter mAdapter;
  private CallBackListener mBackListener;
  private QuestionDiscussHandler mHandler;
  private PullToRefreshListView mListView;
  private TextView textCount;

  private void initListView()
  {
    this.mListView.setAdapter(this.mAdapter);
    ToolsUtils.setList(2, this.mListView, this.mContext);
    this.mListView.setOnRefreshListener(this.mBackListener);
  }

  protected void bindData()
  {
    this.textCount.setText(getResources().getString(2131165302) + "1203" + getResources().getString(2131165303));
    initListView();
  }

  protected void initData()
  {
    this.mBackListener = new CallBackListener();
    this.mHandler = new QuestionDiscussHandler();
    this.list = new ArrayList();
    this.list.add(Integer.valueOf(0));
    this.list.add(Integer.valueOf(0));
    this.list.add(Integer.valueOf(0));
    this.list.add(Integer.valueOf(1));
    this.list.add(Integer.valueOf(0));
    this.mAdapter = new QuestionDiscussAdapter(this.mContext, this.list);
  }

  protected void initListener()
  {
    this.buttonBack.setOnClickListener(this.mBackListener);
    LogUtils.myLog("QuestionDiscussActivity list=" + this.list.hashCode());
  }

  protected void initView()
  {
    this.buttonBack = ((Button)findViewById(2131099905));
    this.textCount = ((TextView)findViewById(2131099906));
    this.mListView = ((PullToRefreshListView)findViewById(2131099907));
  }

  protected void setContentView()
  {
    setContentView(2130903068);
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
      case 2131099905:
      }
      QuestionDiscussActivity.this.finish(2130968578, 2130968582);
    }

    public void onPullDownToRefresh(PullToRefreshBase<ListView> paramPullToRefreshBase)
    {
      if (!QuestionDiscussActivity.this.isRefresh)
      {
        QuestionDiscussActivity.this.isRefresh = true;
        QuestionDiscussActivity.this.mHandler.sendEmptyMessage(1);
      }
    }

    public void onPullUpToRefresh(PullToRefreshBase<ListView> paramPullToRefreshBase)
    {
      if (!QuestionDiscussActivity.this.isLoad)
      {
        QuestionDiscussActivity.this.isLoad = true;
        QuestionDiscussActivity.this.mHandler.sendEmptyMessage(2);
      }
    }
  }

  class QuestionDiscussHandler extends Handler
  {
    QuestionDiscussHandler()
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
        QuestionDiscussActivity.this.list.clear();
        QuestionDiscussActivity.this.list.add(Integer.valueOf(0));
        QuestionDiscussActivity.this.list.add(Integer.valueOf(0));
        QuestionDiscussActivity.this.list.add(Integer.valueOf(0));
        QuestionDiscussActivity.this.list.add(Integer.valueOf(1));
        QuestionDiscussActivity.this.list.add(Integer.valueOf(0));
        QuestionDiscussActivity.this.mAdapter.notifyDataSetChanged();
        QuestionDiscussActivity.this.mListView.onRefreshComplete();
        QuestionDiscussActivity.this.isRefresh = false;
        return;
      case 2:
      }
      QuestionDiscussActivity.this.list.add(Integer.valueOf(0));
      QuestionDiscussActivity.this.list.add(Integer.valueOf(1));
      QuestionDiscussActivity.this.list.add(Integer.valueOf(0));
      QuestionDiscussActivity.this.mAdapter.notifyDataSetChanged();
      QuestionDiscussActivity.this.mListView.onRefreshComplete();
      QuestionDiscussActivity.this.isLoad = false;
    }
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.activity.QuestionDiscussActivity
 * JD-Core Version:    0.6.0
 */