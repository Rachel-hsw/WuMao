package com.withustudy.koudaizikao.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.withustudy.koudaizikao.action.AbsSimpleImplUrlFactory;
import com.withustudy.koudaizikao.action.UrlFactory;
import com.withustudy.koudaizikao.activity.SimuHistoryListActivity;
import com.withustudy.koudaizikao.base.AbsBaseFragment;
import com.withustudy.koudaizikao.config.KouDaiSP;
import com.withustudy.koudaizikao.custom.pullrefersh.SwipyRefreshLayout;
import com.withustudy.koudaizikao.custom.pullrefersh.SwipyRefreshLayout.OnRefreshListener;
import com.withustudy.koudaizikao.custom.pullrefersh.SwipyRefreshLayoutDirection;
import com.withustudy.koudaizikao.entity.CategoryScore;
import com.withustudy.koudaizikao.entity.MockRecord;
import com.withustudy.koudaizikao.entity.Subject;
import com.withustudy.koudaizikao.entity.SubjectMockRecord;
import com.withustudy.koudaizikao.entity.SubjectMockRecordW;
import com.withustudy.koudaizikao.entity.req.ReqMockList;
import com.withustudy.koudaizikao.tools.LogUtils;
import com.withustudy.koudaizikao.tools.ProTools;
import com.withustudy.koudaizikao.tools.ToolsUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SubjectSimuHistoryFm extends AbsBaseFragment
  implements PullToRefreshBase.OnRefreshListener2<ListView>, OnScrollListener, OnRefreshListener
{
  private static final int action_pro_load = 1;
  private static final int action_pro_refresh;
  private SimuHistoryListActivity activity;
  private List<MockRecord> cacheData = new ArrayList();
  private int currentIndex;
  private Handler handler = new Handler()
  {
    public void handleMessage(Message paramMessage)
    {
      switch (paramMessage.what)
      {
      default:
      case 1:
      case 0:
      }
      label154: List localList1;
      do
      {
        SubjectMockRecord localSubjectMockRecord1;
        do
        {
          return;
          SubjectSimuHistoryFm.this.lv_history.onRefreshComplete();
          SubjectMockRecord localSubjectMockRecord2 = SubjectSimuHistoryFm.this.sRecordW.getSubjectMockRecord();
          if (localSubjectMockRecord2 != null)
          {
            List localList2 = localSubjectMockRecord2.getMockRecord();
            if ((localList2 != null) && (localList2.size() > 0))
            {
              SubjectSimuHistoryFm.this.cacheData.addAll(localList2);
              if (SubjectSimuHistoryFm.this.maAdapter != null)
                break label154;
              SubjectSimuHistoryFm.this.maAdapter = new MyBaseAdapter(SubjectSimuHistoryFm.this, SubjectSimuHistoryFm.this.cacheData);
              SubjectSimuHistoryFm.this.lv_history.setAdapter(SubjectSimuHistoryFm.this.maAdapter);
            }
          }
          while (true)
          {
            SubjectSimuHistoryFm.this.isLoading = false;
            return;
            SubjectSimuHistoryFm.this.maAdapter.setList(SubjectSimuHistoryFm.this.cacheData);
            SubjectSimuHistoryFm.this.maAdapter.notifyDataSetChanged();
          }
          SubjectSimuHistoryFm.this.swipyrefreshlayout_hitory.setRefreshing(false);
          localSubjectMockRecord1 = SubjectSimuHistoryFm.this.sRecordW.getSubjectMockRecord();
        }
        while (localSubjectMockRecord1 == null);
        localList1 = localSubjectMockRecord1.getMockRecord();
      }
      while ((localList1 == null) || (localList1.size() <= 0));
      SubjectSimuHistoryFm.this.cacheData.clear();
      SubjectSimuHistoryFm.this.cacheData.addAll(localList1);
      if (SubjectSimuHistoryFm.this.maAdapter == null)
      {
        SubjectSimuHistoryFm.this.maAdapter = new MyBaseAdapter(SubjectSimuHistoryFm.this, SubjectSimuHistoryFm.this.cacheData);
        SubjectSimuHistoryFm.this.lv_history.setAdapter(SubjectSimuHistoryFm.this.maAdapter);
        return;
      }
      SubjectSimuHistoryFm.this.maAdapter.setList(SubjectSimuHistoryFm.this.cacheData);
      SubjectSimuHistoryFm.this.maAdapter.notifyDataSetChanged();
    }
  };
  private boolean isInit = true;
  private boolean isLoading = false;
  private boolean isPrepared;
  private boolean isRefreshing = false;
  protected boolean isVisible;
  private List<String> list;
  private LinearLayout ll_back;
  private PullToRefreshListView lv_history;
  private boolean mHasLoadedOnce;
  private MyBaseAdapter maAdapter;
  private List mlist;
  private SubjectMockRecordW sRecordW;
  private Subject subject;
  private SwipyRefreshLayout swipyrefreshlayout_hitory;
  private View view;

  private void lazyLoad()
  {
    if ((!this.isPrepared) || (!this.isVisible) || (this.mHasLoadedOnce))
      return;
    this.activity = ((SimuHistoryListActivity)getActivity());
    this.mProTools.startDialog(true);
    this.currentIndex = this.activity.currentIndex;
    ReqMockList localReqMockList = new ReqMockList();
    localReqMockList.setClientType(ToolsUtils.getSDK());
    localReqMockList.setImei(ToolsUtils.getImei(this.mContext));
    localReqMockList.setNet(ToolsUtils.getStringNetworkType(this.mContext));
    localReqMockList.setVersionName(this.mSP.getVersionName());
    localReqMockList.setUid(this.mSP.getUserId());
    localReqMockList.setSubjectId(this.subject.getId());
    UrlFactory.getInstance().getMockHistory().constructUrl(this, localReqMockList, 0);
  }

  private void onInvisible()
  {
  }

  private void onVisible()
  {
    lazyLoad();
  }

  public void bindData()
  {
    this.swipyrefreshlayout_hitory.setRefreshing(true);
    this.swipyrefreshlayout_hitory.setOnRefreshListener(this);
    this.lv_history.setOnScrollListener(this);
    this.lv_history.setOnRefreshListener(this);
  }

  public void initData()
  {
    ToolsUtils.setList(-1, this.lv_history, getActivity());
    this.isPrepared = true;
    lazyLoad();
  }

  public void initListener()
  {
  }

  public void initView(View paramView)
  {
    this.swipyrefreshlayout_hitory = ((SwipyRefreshLayout)paramView.findViewById(2131099949));
    this.lv_history = ((PullToRefreshListView)paramView.findViewById(2131099950));
  }

  public View onInflaterRootView(LayoutInflater paramLayoutInflater, Bundle paramBundle)
  {
    try
    {
      this.subject = ((Subject)getArguments().getSerializable("subject"));
      label16: this.view = View.inflate(getActivity(), 2130903075, null);
      return this.view;
    }
    catch (Exception localException)
    {
      break label16;
    }
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

  public void onSuccess(String paramString1, Map<String, String> paramMap, String paramString2, int paramInt)
  {
    super.onSuccess(paramString1, paramMap, paramString2, paramInt);
    this.mProTools.dismissDislog();
    Gson localGson;
    if (paramString1 != null)
      localGson = UrlFactory.getInstanceGson();
    switch (paramInt)
    {
    default:
      return;
    case 0:
    }
    try
    {
      this.sRecordW = ((SubjectMockRecordW)localGson.fromJson(paramString1, SubjectMockRecordW.class));
      if (this.sRecordW != null)
      {
        this.handler.sendEmptyMessage(0);
        return;
      }
      LogUtils.myLog("解析宜昌");
      return;
    }
    catch (Exception localException)
    {
    }
  }

  public void refresh(List<String> paramList, int paramInt)
  {
    this.mlist = paramList;
    this.currentIndex = paramInt;
  }

  public void setUserVisibleHint(boolean paramBoolean)
  {
    super.setUserVisibleHint(paramBoolean);
    if (getUserVisibleHint())
    {
      this.isVisible = true;
      onVisible();
      return;
    }
    this.isVisible = false;
    onInvisible();
  }

  private class MyBaseAdapter extends BaseAdapter
  {
    private List<MockRecord> list;

    public MyBaseAdapter()
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
        paramView = View.inflate(SubjectSimuHistoryFm.this.getActivity(), 2130903129, null);
        SubjectSimuHistoryFm localSubjectSimuHistoryFm = SubjectSimuHistoryFm.this;
        localViewHolder = new ViewHolder(localSubjectSimuHistoryFm);
        LinearLayout localLinearLayout2 = (LinearLayout)paramView.findViewById(2131100281);
        ViewHolder.access$0(localViewHolder, localLinearLayout2);
        TextView localTextView5 = (TextView)paramView.findViewById(2131100279);
        ViewHolder.access$1(localViewHolder, localTextView5);
        TextView localTextView6 = (TextView)paramView.findViewById(2131100280);
        ViewHolder.access$2(localViewHolder, localTextView6);
        TextView localTextView7 = (TextView)paramView.findViewById(2131099765);
        ViewHolder.access$3(localViewHolder, localTextView7);
        paramView.setTag(localViewHolder);
      }
      while (true)
      {
        MockRecord localMockRecord = (MockRecord)this.list.get(paramInt);
        String str1 = localMockRecord.getDate();
        Object localObject = str1;
        try
        {
          String str4 = str1.substring(0, 4);
          String str5 = str1.substring(4, 6);
          String str6 = str1.substring(6, str1.length());
          localObject = "";
          String str7 = str4 + "." + str5 + "." + str6;
          localObject = str7;
          label217: ViewHolder.access$4(localViewHolder).setText((CharSequence)localObject);
          long l = localMockRecord.getCostTime();
          ViewHolder.access$5(localViewHolder).setText(l + "分钟");
          double d1 = localMockRecord.getFinalScore();
          ViewHolder.access$6(localViewHolder).setText(d1);
          List localList = localMockRecord.getCategoryScore();
          if ((ViewHolder.access$7(localViewHolder) != null) && (ViewHolder.access$7(localViewHolder).getChildCount() > 0))
            ViewHolder.access$7(localViewHolder).removeAllViews();
          int i;
          int k;
          if ((localList != null) && (localList.size() > 0))
          {
            i = localList.size();
            int j = (int)Math.ceil(1.0D * i / 2.0D);
            k = 0;
            if (k < j);
          }
          else
          {
            return paramView;
            localViewHolder = (ViewHolder)paramView.getTag();
            continue;
          }
          LinearLayout localLinearLayout1 = (LinearLayout)View.inflate(SubjectSimuHistoryFm.this.getActivity(), 2130903161, null);
          TextView localTextView1 = (TextView)localLinearLayout1.findViewById(2131100456);
          TextView localTextView2 = (TextView)localLinearLayout1.findViewById(2131100457);
          TextView localTextView3 = (TextView)localLinearLayout1.findViewById(2131100458);
          TextView localTextView4 = (TextView)localLinearLayout1.findViewById(2131100459);
          int m = k * 2;
          int n = 1 + k * 2;
          if ((m >= 0) && (m < i))
          {
            CategoryScore localCategoryScore2 = (CategoryScore)localList.get(m);
            String str3 = localCategoryScore2.getCategory();
            double d4 = localCategoryScore2.getUserScore();
            double d5 = localCategoryScore2.getTotalScore();
            localTextView1.setText(str3);
            localTextView2.setText(d4 + "/" + d5);
          }
          if ((n >= 0) && (n < i))
          {
            CategoryScore localCategoryScore1 = (CategoryScore)localList.get(n);
            String str2 = localCategoryScore1.getCategory();
            double d2 = localCategoryScore1.getUserScore();
            double d3 = localCategoryScore1.getTotalScore();
            localTextView3.setText(str2);
            localTextView4.setText(d2 + "/" + d3);
          }
          while (true)
          {
            ViewHolder.access$7(localViewHolder).addView(localLinearLayout1);
            k++;
            break;
            localTextView3.setVisibility(8);
            localTextView4.setVisibility(8);
          }
        }
        catch (Exception localException)
        {
          break label217;
        }
      }
    }

    public void setList(List<MockRecord> paramList)
    {
      this.list = paramList;
    }
  }

  class ViewHolder
  {
    private LinearLayout style_contanier_ll;
    private TextView tv_date;
    private TextView tv_fen;
    private TextView tv_time;

    ViewHolder()
    {
    }
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.fragment.SubjectSimuHistoryFm
 * JD-Core Version:    0.6.0
 */