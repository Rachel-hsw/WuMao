package com.withustudy.koudaizikao.activity;

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
import com.withustudy.koudaizikao.adapter.SimulationHistoryAdapter;
import com.withustudy.koudaizikao.base.AbsBaseActivity;
import com.withustudy.koudaizikao.tools.ToolsUtils;
import java.util.ArrayList;
import java.util.List;

public class SimulationHistoryActivity extends AbsBaseActivity
{
  public static final int LOAD_FINISH = 2;
  public static final int REFRESH_FINISH = 1;
  private Button buttonBack;
  private boolean isLoad = false;
  private boolean isRefresh = false;
  private List<Integer> list;
  private SimulationHistoryAdapter mAdapter;
  private CallBackListener mBackListener;
  private SimulationHistoryHandler mHandler;
  private PullToRefreshListView mListView;
  private TextView textTitle;

  private void setListView()
  {
    this.mListView.setAdapter(this.mAdapter);
    ToolsUtils.setList(2, this.mListView, this.mContext);
    this.mListView.setOnRefreshListener(this.mBackListener);
  }

  protected void bindData()
  {
    this.textTitle.setText("国际商务理论");
    setListView();
  }

  protected void initData()
  {
    this.mHandler = new SimulationHistoryHandler();
    this.mBackListener = new CallBackListener();
    this.list = new ArrayList();
    for (int i = 0; ; i++)
    {
      if (i >= 5)
      {
        this.mAdapter = new SimulationHistoryAdapter(this.list, this.mContext);
        return;
      }
      this.list.add(Integer.valueOf((int)(100.0D * Math.random())));
    }
  }

  protected void initListener()
  {
    this.buttonBack.setOnClickListener(this.mBackListener);
  }

  protected void initView()
  {
    this.buttonBack = ((Button)findViewById(2131099976));
    this.textTitle = ((TextView)findViewById(2131099977));
    this.mListView = ((PullToRefreshListView)findViewById(2131099978));
  }

  protected void setContentView()
  {
    setContentView(2130903079);
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
      case 2131099976:
      }
      SimulationHistoryActivity.this.finish(2130968578, 2130968582);
    }

    public void onPullDownToRefresh(PullToRefreshBase<ListView> paramPullToRefreshBase)
    {
      if (!SimulationHistoryActivity.this.isRefresh)
      {
        SimulationHistoryActivity.this.mHandler.sendEmptyMessage(1);
        SimulationHistoryActivity.this.isRefresh = true;
      }
    }

    public void onPullUpToRefresh(PullToRefreshBase<ListView> paramPullToRefreshBase)
    {
      if (!SimulationHistoryActivity.this.isLoad)
      {
        SimulationHistoryActivity.this.mHandler.sendEmptyMessage(2);
        SimulationHistoryActivity.this.isLoad = true;
      }
    }
  }

  class SimulationHistoryHandler extends Handler
  {
    SimulationHistoryHandler()
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
        SimulationHistoryActivity.this.mListView.onRefreshComplete();
        SimulationHistoryActivity.this.list.clear();
        for (int j = 0; ; j++)
        {
          if (j >= 5)
          {
            SimulationHistoryActivity.this.isRefresh = false;
            return;
          }
          SimulationHistoryActivity.this.list.add(Integer.valueOf((int)(100.0D * Math.random())));
        }
      case 2:
      }
      SimulationHistoryActivity.this.mListView.onRefreshComplete();
      for (int i = 0; ; i++)
      {
        if (i >= 3)
        {
          SimulationHistoryActivity.this.isLoad = false;
          return;
        }
        SimulationHistoryActivity.this.list.add(Integer.valueOf((int)(100.0D * Math.random())));
      }
    }
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.activity.SimulationHistoryActivity
 * JD-Core Version:    0.6.0
 */