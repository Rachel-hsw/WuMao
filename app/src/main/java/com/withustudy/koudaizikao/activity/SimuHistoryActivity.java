package com.withustudy.koudaizikao.activity;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.withustudy.koudaizikao.base.AbsBaseActivity;
import com.withustudy.koudaizikao.custom.pullrefersh.SwipyRefreshLayout;
import com.withustudy.koudaizikao.custom.pullrefersh.SwipyRefreshLayout.OnRefreshListener;
import com.withustudy.koudaizikao.custom.pullrefersh.SwipyRefreshLayoutDirection;
import com.withustudy.koudaizikao.tools.ToolsUtils;
import java.util.ArrayList;
import java.util.List;

public class SimuHistoryActivity extends AbsBaseActivity
  implements PullToRefreshBase.OnRefreshListener2<ListView>, OnRefreshListener, OnClickListener, OnScrollListener
{
  private static final int action_pro_load = 1;
  private static final int action_pro_refresh;
  private Handler handler = new Handler()
  {
    public void handleMessage(Message paramMessage)
    {
      switch (paramMessage.what)
      {
      default:
        return;
      case 1:
        SimuHistoryActivity.this.lv_history.onRefreshComplete();
        if (SimuHistoryActivity.this.maAdapter == null)
        {
          SimuHistoryActivity.this.list = new ArrayList();
          SimuHistoryActivity.this.list.add("");
          SimuHistoryActivity.this.list.add("");
          SimuHistoryActivity.this.list.add("");
          SimuHistoryActivity.this.maAdapter = new MyBaseAdapter(SimuHistoryActivity.this, SimuHistoryActivity.this.list);
          SimuHistoryActivity.this.lv_history.setAdapter(SimuHistoryActivity.this.maAdapter);
        }
        while (true)
        {
          SimuHistoryActivity.this.isLoading = false;
          return;
          SimuHistoryActivity.this.list.add("");
          SimuHistoryActivity.this.maAdapter.setList(SimuHistoryActivity.this.list);
          SimuHistoryActivity.this.maAdapter.notifyDataSetChanged();
        }
      case 0:
      }
      SimuHistoryActivity.this.swipyrefreshlayout_hitory.setRefreshing(false);
      if (SimuHistoryActivity.this.maAdapter == null)
      {
        SimuHistoryActivity.this.list = new ArrayList();
        SimuHistoryActivity.this.list.add("");
        SimuHistoryActivity.this.list.add("");
        SimuHistoryActivity.this.list.add("");
        SimuHistoryActivity.this.maAdapter = new MyBaseAdapter(SimuHistoryActivity.this, SimuHistoryActivity.this.list);
        SimuHistoryActivity.this.lv_history.setAdapter(SimuHistoryActivity.this.maAdapter);
        return;
      }
      SimuHistoryActivity.this.list.clear();
      SimuHistoryActivity.this.list.add("");
      SimuHistoryActivity.this.maAdapter.setList(SimuHistoryActivity.this.list);
      SimuHistoryActivity.this.maAdapter.notifyDataSetChanged();
    }
  };
  private boolean isLoading = false;
  private boolean isRefreshing = false;
  private List<String> list;
  private LinearLayout ll_back;
  private PullToRefreshListView lv_history;
  private MyBaseAdapter maAdapter;
  private SwipyRefreshLayout swipyrefreshlayout_hitory;

  protected void bindData()
  {
    this.swipyrefreshlayout_hitory.setRefreshing(true);
    this.lv_history.setOnScrollListener(this);
    this.handler.sendEmptyMessage(0);
  }

  protected void initData()
  {
    ToolsUtils.setList(1, this.lv_history, this);
  }

  protected void initListener()
  {
    this.lv_history.setOnRefreshListener(this);
    this.swipyrefreshlayout_hitory.setOnRefreshListener(this);
  }

  protected void initView()
  {
    this.swipyrefreshlayout_hitory = ((SwipyRefreshLayout)findViewById(2131099949));
    this.lv_history = ((PullToRefreshListView)findViewById(2131099950));
  }

  public void onClick(View paramView)
  {
    paramView.getId();
  }

  public void onPullDownToRefresh(PullToRefreshBase<ListView> paramPullToRefreshBase)
  {
  }

  public void onPullUpToRefresh(PullToRefreshBase<ListView> paramPullToRefreshBase)
  {
    if (!this.isLoading)
    {
      this.handler.sendEmptyMessage(1);
      this.isLoading = true;
    }
  }

  public void onRefresh(SwipyRefreshLayoutDirection paramSwipyRefreshLayoutDirection)
  {
    if (paramSwipyRefreshLayoutDirection.equals(SwipyRefreshLayoutDirection.getFromInt(0)))
    {
      this.swipyrefreshlayout_hitory.setRefreshing(true);
      this.handler.sendEmptyMessage(0);
    }
  }

  public void onScroll(AbsListView paramAbsListView, int paramInt1, int paramInt2, int paramInt3)
  {
    if (paramInt1 == 0)
    {
      this.swipyrefreshlayout_hitory.setEnabled(true);
      return;
    }
    this.swipyrefreshlayout_hitory.setEnabled(false);
  }

  public void onScrollStateChanged(AbsListView paramAbsListView, int paramInt)
  {
  }

  protected void setContentView()
  {
    setContentView(2130903075);
  }

  private class MyBaseAdapter extends BaseAdapter
  {
    private List list;

    public MyBaseAdapter(List arg2)
    {
      Object localObject;
      this.list = localObject;
    }

    public int getCount()
    {
      if (this.list == null)
        return 0;
      return this.list.size();
    }

    public Object getItem(int paramInt)
    {
      return null;
    }

    public long getItemId(int paramInt)
    {
      return 0L;
    }

    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      ViewHolder localViewHolder;
      if (paramView == null)
      {
        paramView = View.inflate(SimuHistoryActivity.this.getApplicationContext(), 2130903129, null);
        localViewHolder = new ViewHolder(SimuHistoryActivity.this);
        ViewHolder.access$0(localViewHolder, (TextView)paramView.findViewById(2131100279));
        ViewHolder.access$1(localViewHolder, (TextView)paramView.findViewById(2131100280));
        ViewHolder.access$2(localViewHolder, (TextView)paramView.findViewById(2131100285));
        ViewHolder.access$3(localViewHolder, (TextView)paramView.findViewById(2131100287));
        ViewHolder.access$4(localViewHolder, (TextView)paramView.findViewById(2131099765));
        ViewHolder.access$5(localViewHolder, (TextView)paramView.findViewById(2131100283));
        ViewHolder.access$6(localViewHolder, (TextView)paramView.findViewById(2131100289));
        paramView.setTag(localViewHolder);
      }
      while (true)
      {
        ViewHolder.access$7(localViewHolder).setText("2015.8.1");
        ViewHolder.access$8(localViewHolder).setText("100");
        ViewHolder.access$9(localViewHolder).setText("99/100");
        ViewHolder.access$10(localViewHolder).setText("99/100");
        ViewHolder.access$11(localViewHolder).setText("120分钟");
        ViewHolder.access$12(localViewHolder).setText("24/25");
        ViewHolder.access$13(localViewHolder).setText("99/100");
        return paramView;
        localViewHolder = (ViewHolder)paramView.getTag();
      }
    }

    public void setList(List paramList)
    {
      this.list = paramList;
    }
  }

  class ViewHolder
  {
    private TextView tv_articleate;
    private TextView tv_date;
    private TextView tv_fen;
    private TextView tv_mulicate;
    private TextView tv_single;
    private TextView tv_time;
    private TextView tv_yuedu;

    ViewHolder()
    {
    }
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.activity.SimuHistoryActivity
 * JD-Core Version:    0.6.0
 */