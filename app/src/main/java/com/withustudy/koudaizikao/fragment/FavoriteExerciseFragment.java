package com.withustudy.koudaizikao.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.withustudy.koudaizikao.action.AbsBaseUrlConstructor;
import com.withustudy.koudaizikao.action.AbsSimpleImplUrlFactory;
import com.withustudy.koudaizikao.action.UrlFactory;
import com.withustudy.koudaizikao.activity.ActivityCollectSubject;
import com.withustudy.koudaizikao.activity.InformationDetailActivity;
import com.withustudy.koudaizikao.activity.QuestionDetailActivity;
import com.withustudy.koudaizikao.adapter.FavoriteAdapter;
import com.withustudy.koudaizikao.base.AbsBaseFragment;
import com.withustudy.koudaizikao.config.KouDaiSP;
import com.withustudy.koudaizikao.custom.pullrefersh.SwipyRefreshLayout;
import com.withustudy.koudaizikao.custom.pullrefersh.SwipyRefreshLayout.OnRefreshListener;
import com.withustudy.koudaizikao.custom.pullrefersh.SwipyRefreshLayoutDirection;
import com.withustudy.koudaizikao.entity.ExerciseSummary;
import com.withustudy.koudaizikao.entity.ExerciseSummaryCollect;
import com.withustudy.koudaizikao.entity.Subject;
import com.withustudy.koudaizikao.entity.SubjectFavoriteExercise;
import com.withustudy.koudaizikao.entity.SubjectFavoriteExerciseW;
import com.withustudy.koudaizikao.entity.req.FavoriteExercise;
import com.withustudy.koudaizikao.entity.req.GetCollectExcercises;
import com.withustudy.koudaizikao.tools.DateTools;
import com.withustudy.koudaizikao.tools.LogUtils;
import com.withustudy.koudaizikao.tools.ToolsUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FavoriteExerciseFragment extends AbsBaseFragment
  implements OnRefreshListener
{
  public static final String FORUM = "forum";
  public static final String INFORMATION = "information";
  public static final int LOAD_FINISH = 2;
  public static final String PRO = "pro";
  public static final int REFRESH_FINISH = 1;
  private static final int action_forum_load = 5;
  private static final int action_forum_refresh = 4;
  private static final int action_info_load = 3;
  private static final int action_info_refresh = 2;
  private static final int action_pro_load = 1;
  private static final int action_pro_refresh;
  private List<SubjectFavoriteExercise> cache = new ArrayList();
  private List cacheAllData = new ArrayList();
  private List<ExerciseSummary> excercilseList = new ArrayList();
  private ExerciseSummaryCollect exerciseSummaryCollect;
  private boolean isLoading = false;
  private boolean isRefreshing = false;
  private List<String> list;
  private LinearLayout ll_no_data;
  private FavoriteAdapter mAdapter;
  private CallBackListener mCallBackListener;
  private FavoriteHandler mHandler;
  private ListView mListView;
  private SwipyRefreshLayout mSwipyRefreshLayout;
  private MyCollect1Adapter myCollect1Adapter;
  private List newsList = new ArrayList();
  private List noteList = new ArrayList();
  private SubjectFavoriteExerciseW sExerciseW;
  private String type = "pro";

  private void setList(int paramInt)
  {
    this.mListView.setOnItemClickListener(this.mCallBackListener);
    String str = this.mSP.getUserId();
    switch (paramInt)
    {
    default:
      return;
    case 0:
    }
    this.type = "pro";
    UrlFactory.getInstance().getCollectExcercise().constructUrl(this, new String[] { str, "12", "EXERCISE" }, 0, this.mContext);
  }

  public void bindData()
  {
    GetCollectExcercises localGetCollectExcercises = new GetCollectExcercises();
    localGetCollectExcercises.setClientType(ToolsUtils.getSDK());
    localGetCollectExcercises.setImei(ToolsUtils.getImei(this.mContext));
    localGetCollectExcercises.setNet(ToolsUtils.getStringNetworkType(this.mContext));
    localGetCollectExcercises.setVersionName(this.mSP.getVersionName());
    localGetCollectExcercises.setUid(this.mSP.getUserId());
    UrlFactory.getInstance().getExercisefavorites().constructUrl(this, localGetCollectExcercises, 0);
  }

  public void initData()
  {
    this.mHandler = new FavoriteHandler();
    this.mCallBackListener = new CallBackListener();
  }

  public void initListener()
  {
  }

  public void initView(View paramView)
  {
    this.mListView = ((ListView)paramView.findViewById(2131100125));
    this.ll_no_data = ((LinearLayout)paramView.findViewById(2131100126));
  }

  public View onInflaterRootView(LayoutInflater paramLayoutInflater, Bundle paramBundle)
  {
    return paramLayoutInflater.inflate(2130903114, null);
  }

  public void onRefresh(SwipyRefreshLayoutDirection paramSwipyRefreshLayoutDirection)
  {
  }

  public void onResume()
  {
    super.onResume();
    GetCollectExcercises localGetCollectExcercises = new GetCollectExcercises();
    localGetCollectExcercises.setClientType(ToolsUtils.getSDK());
    localGetCollectExcercises.setImei(ToolsUtils.getImei(this.mContext));
    localGetCollectExcercises.setNet(ToolsUtils.getStringNetworkType(this.mContext));
    localGetCollectExcercises.setVersionName(this.mSP.getVersionName());
    localGetCollectExcercises.setUid(this.mSP.getUserId());
    UrlFactory.getInstance().getExercisefavorites().constructUrl(this, localGetCollectExcercises, 0);
  }

  public void onStart()
  {
    super.onStart();
    GetCollectExcercises localGetCollectExcercises = new GetCollectExcercises();
    localGetCollectExcercises.setClientType(ToolsUtils.getSDK());
    localGetCollectExcercises.setImei(ToolsUtils.getImei(this.mContext));
    localGetCollectExcercises.setNet(ToolsUtils.getStringNetworkType(this.mContext));
    localGetCollectExcercises.setVersionName(this.mSP.getVersionName());
    localGetCollectExcercises.setUid(this.mSP.getUserId());
    UrlFactory.getInstance().getExercisefavorites().constructUrl(this, localGetCollectExcercises, 0);
  }

  public void onSuccess(String paramString1, Map<String, String> paramMap, String paramString2, int paramInt)
  {
    super.onSuccess(paramString1, paramMap, paramString2, paramInt);
    Gson localGson;
    if (paramString1 != null)
      localGson = UrlFactory.getInstanceGson();
    switch (paramInt)
    {
    case 2:
    case 3:
    case 4:
    case 5:
    default:
      return;
    case 0:
    case 1:
    }
    try
    {
      this.sExerciseW = ((SubjectFavoriteExerciseW)localGson.fromJson(paramString1, SubjectFavoriteExerciseW.class));
      if (this.sExerciseW != null)
      {
        this.mHandler.sendEmptyMessage(paramInt);
        return;
      }
    }
    catch (Exception localException)
    {
      LogUtils.myLog("收藏主界面数据实体解析异常:" + localException.getMessage());
      return;
    }
    LogUtils.myLog("收藏主界面数据解析实体bean为null");
  }

  public void setUserVisibleHint(boolean paramBoolean)
  {
    super.setUserVisibleHint(paramBoolean);
  }

  class CallBackListener
    implements PullToRefreshBase.OnRefreshListener2<ListView>, OnItemClickListener
  {
    CallBackListener()
    {
    }

    public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
    {
      if (FavoriteExerciseFragment.this.type.equals("pro"))
      {
        FavoriteExerciseFragment.this.startNewActivity(QuestionDetailActivity.class, 2130968581, 2130968579, false, null);
        return;
      }
      FavoriteExerciseFragment.this.startNewActivity(InformationDetailActivity.class, 2130968581, 2130968579, false, null);
    }

    public void onPullDownToRefresh(PullToRefreshBase<ListView> paramPullToRefreshBase)
    {
      if (!FavoriteExerciseFragment.this.isRefreshing)
      {
        String str = FavoriteExerciseFragment.this.mSP.getUserId();
        UrlFactory.getInstance().getCollectExcercise().constructUrl(FavoriteExerciseFragment.this, new String[] { str, "12", "EXERCISE" }, 0, FavoriteExerciseFragment.this.mContext);
        FavoriteExerciseFragment.this.isRefreshing = true;
      }
    }

    public void onPullUpToRefresh(PullToRefreshBase<ListView> paramPullToRefreshBase)
    {
      if (!FavoriteExerciseFragment.this.isLoading)
      {
        String str = FavoriteExerciseFragment.this.mSP.getUserId();
        UrlFactory.getInstance().getCollectExcercise().constructUrl(FavoriteExerciseFragment.this, new String[] { str, "12", "EXERCISE" }, 1, FavoriteExerciseFragment.this.mContext);
        FavoriteExerciseFragment.this.isLoading = true;
      }
    }
  }

  class FavoriteHandler extends Handler
  {
    FavoriteHandler()
    {
    }

    public void handleMessage(Message paramMessage)
    {
      super.handleMessage(paramMessage);
      switch (paramMessage.what)
      {
      default:
      case 1:
      case 0:
      }
      label190: 
      do
      {
        return;
        List localList2 = FavoriteExerciseFragment.this.exerciseSummaryCollect.getExerciseSummary();
        FavoriteExerciseFragment.this.mSwipyRefreshLayout.setRefreshing(false);
        if ((localList2 != null) && (localList2.size() > 0))
        {
          FavoriteExerciseFragment.this.excercilseList.addAll(localList2);
          FavoriteExerciseFragment.this.cacheAllData.clear();
          FavoriteExerciseFragment.this.cacheAllData.addAll(FavoriteExerciseFragment.this.excercilseList);
          if (FavoriteExerciseFragment.this.mAdapter != null)
            break label190;
          FavoriteExerciseFragment.this.mAdapter = new FavoriteAdapter(FavoriteExerciseFragment.this.mContext, FavoriteExerciseFragment.this.cacheAllData, false, FavoriteExerciseFragment.this.type);
          FavoriteExerciseFragment.this.mListView.setAdapter(FavoriteExerciseFragment.this.mAdapter);
        }
        while (true)
        {
          FavoriteExerciseFragment.this.isLoading = false;
          return;
          FavoriteExerciseFragment.this.mAdapter.setType("pro");
          FavoriteExerciseFragment.this.mAdapter.notifyDataSetChanged();
        }
      }
      while (FavoriteExerciseFragment.this.sExerciseW == null);
      List localList1 = FavoriteExerciseFragment.this.sExerciseW.getSubjectFavoriteExercise();
      if ((localList1 != null) && (localList1.size() > 0))
      {
        FavoriteExerciseFragment.this.mListView.setVisibility(0);
        FavoriteExerciseFragment.this.ll_no_data.setVisibility(8);
        FavoriteExerciseFragment.this.cache.clear();
        FavoriteExerciseFragment.this.cache.addAll(localList1);
        if (FavoriteExerciseFragment.this.myCollect1Adapter == null)
        {
          FavoriteExerciseFragment.this.myCollect1Adapter = new MyCollect1Adapter(FavoriteExerciseFragment.this, FavoriteExerciseFragment.this.cache);
          FavoriteExerciseFragment.this.mListView.setAdapter(FavoriteExerciseFragment.this.myCollect1Adapter);
          return;
        }
        FavoriteExerciseFragment.this.myCollect1Adapter.notifyDataSetChanged();
        return;
      }
      FavoriteExerciseFragment.this.cache.clear();
      if (FavoriteExerciseFragment.this.myCollect1Adapter == null)
      {
        FavoriteExerciseFragment.this.myCollect1Adapter = new MyCollect1Adapter(FavoriteExerciseFragment.this, FavoriteExerciseFragment.this.cache);
        FavoriteExerciseFragment.this.mListView.setAdapter(FavoriteExerciseFragment.this.myCollect1Adapter);
      }
      while (true)
      {
        FavoriteExerciseFragment.this.mListView.setVisibility(8);
        FavoriteExerciseFragment.this.ll_no_data.setVisibility(0);
        return;
        FavoriteExerciseFragment.this.myCollect1Adapter.notifyDataSetChanged();
      }
    }
  }

  class MyCollect1Adapter extends BaseAdapter
  {
    private List list;

    public MyCollect1Adapter(List arg2)
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
        paramView = View.inflate(FavoriteExerciseFragment.this.getActivity(), 2130903093, null);
        localViewHolder = new ViewHolder();
        localViewHolder.collect_tv_name = ((TextView)paramView.findViewById(2131100046));
        localViewHolder.collect_tv_time = ((TextView)paramView.findViewById(2131100047));
        paramView.setTag(localViewHolder);
      }
      while (true)
      {
        SubjectFavoriteExercise localSubjectFavoriteExercise = (SubjectFavoriteExercise)this.list.get(paramInt);
        Subject localSubject = localSubjectFavoriteExercise.getSubject();
        localViewHolder.collect_tv_name.setText(localSubject.getName());
        List localList = localSubjectFavoriteExercise.getFavoriteExercise();
        String str = "0";
        if ((localList != null) && (localList.size() > 0))
        {
          FavoriteExercise localFavoriteExercise = (FavoriteExercise)localList.get(0);
          if (localFavoriteExercise != null)
            str = DateTools.dateFormatYMD(localFavoriteExercise.getTime());
        }
        localViewHolder.collect_tv_time.setText(str);
        paramView.setOnClickListener(new OnClickListener(localSubjectFavoriteExercise)
        {
          public void onClick(View paramView)
          {
            Bundle localBundle = new Bundle();
            localBundle.putSerializable("SubjectFavoriteExercise", this.val$sfe);
            FavoriteExerciseFragment.this.startNewActivity(ActivityCollectSubject.class, 2130968581, 2130968579, false, localBundle);
          }
        });
        return paramView;
        localViewHolder = (ViewHolder)paramView.getTag();
      }
    }

    public void setList(List paramList)
    {
      this.list = paramList;
    }

    class ViewHolder
    {
      private TextView collect_tv_name;
      private TextView collect_tv_time;

      ViewHolder()
      {
      }
    }
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.fragment.FavoriteExerciseFragment
 * JD-Core Version:    0.6.0
 */