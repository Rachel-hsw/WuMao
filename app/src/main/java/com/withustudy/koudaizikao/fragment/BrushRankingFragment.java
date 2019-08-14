package com.withustudy.koudaizikao.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.android.http.FileDownLoad;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.DisplayImageOptions.Builder;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.withustudy.koudaizikao.action.AbsSimpleImplUrlFactory;
import com.withustudy.koudaizikao.action.UrlFactory;
import com.withustudy.koudaizikao.activity.BrushRankingActivity;
import com.withustudy.koudaizikao.adapter.BrushRankingAdapter;
import com.withustudy.koudaizikao.base.AbsBaseFragment;
import com.withustudy.koudaizikao.config.KouDaiSP;
import com.withustudy.koudaizikao.custom.SharePopWindow;
import com.withustudy.koudaizikao.custom.pullrefersh.SwipyRefreshLayout;
import com.withustudy.koudaizikao.custom.pullrefersh.SwipyRefreshLayout.OnRefreshListener;
import com.withustudy.koudaizikao.custom.pullrefersh.SwipyRefreshLayoutDirection;
import com.withustudy.koudaizikao.entity.UserBrushSummary;
import com.withustudy.koudaizikao.entity.UserBrushSummaryW;
import com.withustudy.koudaizikao.entity.UserMockSummary;
import com.withustudy.koudaizikao.entity.UserMockSummaryW;
import com.withustudy.koudaizikao.entity.req.ReqBrushRank;
import com.withustudy.koudaizikao.entity.req.UserSubject;
import com.withustudy.koudaizikao.tools.LogUtils;
import com.withustudy.koudaizikao.tools.ProTools;
import com.withustudy.koudaizikao.tools.ToolsUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BrushRankingFragment extends AbsBaseFragment
  implements PullToRefreshBase.OnRefreshListener2<ListView>, OnScrollListener, OnRefreshListener, OnClickListener
{
  private static final int LOAD_FINISH = 1;
  private static final int action_get_mock_rank = 2;
  private static final int action_get_user_rank = 1;
  private static final int action_pro_load = 11;
  private static final int action_pro_refresh = 10;
  private BrushRankingActivity activity;
  private Button buttonShare;
  private List<UserBrushSummary> cacheData = new ArrayList();
  private List<UserMockSummary> cacheData2;
  private PopupWindow chapterSectionListPop;
  private LinearLayout chapter_brush_ll;
  private ImageView chapter_icon;
  private RelativeLayout chapter_list_pop_diss_ll;
  private Button chapter_share_cancel;
  private LinearLayout chapter_share_ll;
  private TextView chapter_tv1;
  private TextView chapter_tv2;
  private ImageButton chapter_weibo_share;
  private ImageButton chapter_weixin;
  private ImageButton chapter_weixin_pengyou;
  private Handler handler = new Handler()
  {
    public void handleMessage(Message paramMessage)
    {
      switch (paramMessage.what)
      {
      default:
      case 11:
      case 10:
      }
      while (true)
      {
        return;
        BrushRankingFragment.this.mListView.onRefreshComplete();
        switch (BrushRankingFragment.this.type)
        {
        case 1:
        default:
        case 0:
        }
        while (true)
        {
          BrushRankingFragment.this.isLoading = false;
          return;
          List localList3 = BrushRankingFragment.this.userSummaryW.getUserBrushSummary();
          if (BrushRankingFragment.this.userSummaryW.getMyRankPos() == -1)
            BrushRankingFragment.this.textFindMe.setText("还米有刷题");
          while (true)
          {
            if ((localList3 == null) || (localList3.size() <= 0))
              break label217;
            BrushRankingFragment.this.cacheData.addAll(localList3);
            if (BrushRankingFragment.this.mAdapter != null)
              break label219;
            BrushRankingFragment.this.mAdapter = new BrushRankingAdapter(0, BrushRankingFragment.this.getActivity(), BrushRankingFragment.this.cacheData);
            BrushRankingFragment.this.mListView.setAdapter(BrushRankingFragment.this.mAdapter);
            break;
            BrushRankingFragment.this.textFindMe.setText("找找我在哪儿?");
          }
          label217: continue;
          label219: BrushRankingFragment.this.mAdapter.notifyDataSetChanged();
        }
        BrushRankingFragment.this.swipyrefreshlayout_rank.setRefreshing(false);
        switch (BrushRankingFragment.this.type)
        {
        default:
          return;
        case 0:
          List localList2 = BrushRankingFragment.this.userSummaryW.getUserBrushSummary();
          if (BrushRankingFragment.this.userSummaryW.getMyRankPos() == -1)
            BrushRankingFragment.this.textFindMe.setText("还米有刷题");
          while ((localList2 != null) && (localList2.size() > 0))
          {
            BrushRankingFragment.this.cacheData.clear();
            BrushRankingFragment.this.cacheData.addAll(localList2);
            if (BrushRankingFragment.this.mAdapter != null)
              break label425;
            BrushRankingFragment.this.mAdapter = new BrushRankingAdapter(0, BrushRankingFragment.this.activity, BrushRankingFragment.this.cacheData);
            BrushRankingFragment.this.mListView.setAdapter(BrushRankingFragment.this.mAdapter);
            return;
            BrushRankingFragment.this.textFindMe.setText("找找我在哪儿?");
          }
          label425: BrushRankingFragment.this.mAdapter.notifyDataSetChanged();
          return;
        case 1:
        }
        if (BrushRankingFragment.this.cacheData2 == null)
          BrushRankingFragment.this.cacheData2 = new ArrayList();
        List localList1 = BrushRankingFragment.this.userMockSummaryW.getUserMockSummary();
        if (BrushRankingFragment.this.userMockSummaryW.getMyRankPos() == -1)
          BrushRankingFragment.this.textFindMe.setText("还米有模考");
        while ((localList1 != null) && (localList1.size() > 0))
        {
          BrushRankingFragment.this.cacheData2.clear();
          BrushRankingFragment.this.cacheData2.addAll(localList1);
          if (BrushRankingFragment.this.mAdapter != null)
            break label608;
          BrushRankingFragment.this.mAdapter = new BrushRankingAdapter(1, BrushRankingFragment.this.activity, BrushRankingFragment.this.cacheData2);
          BrushRankingFragment.this.mListView.setAdapter(BrushRankingFragment.this.mAdapter);
          return;
          BrushRankingFragment.this.textFindMe.setText("找找我在哪儿?");
        }
      }
      label608: BrushRankingFragment.this.mAdapter.notifyDataSetChanged();
    }
  };
  private ImageView imageMyAvatar;
  private boolean isInit = true;
  private boolean isLoading = false;
  private boolean isPrepared;
  private boolean isShare = false;
  protected boolean isVisible;
  private LinearLayout ll_bottom;
  private BrushRankingAdapter mAdapter;
  private boolean mHasLoadedOnce;
  private PullToRefreshListView mListView;
  private String subjectId;
  private SwipyRefreshLayout swipyrefreshlayout_rank;
  private TextView textFindMe;
  private TextView textMyName;
  private int type;
  private UserMockSummaryW userMockSummaryW;
  private UserBrushSummaryW userSummaryW;

  private void dismissPop()
  {
    if (this.chapterSectionListPop != null)
    {
      this.chapterSectionListPop.dismiss();
      this.chapterSectionListPop = null;
    }
    backgroundAlpha(1.0F);
  }

  private void lazyLoad()
  {
    if ((!this.isPrepared) || (!this.isVisible) || (this.mHasLoadedOnce))
      return;
    this.activity = ((BrushRankingActivity)getActivity());
    this.mProTools.startDialog(true);
    this.type = this.activity.type;
    switch (this.type)
    {
    default:
      return;
    case 0:
      reqBrushRank();
      return;
    case 1:
    }
    reqMockRank();
  }

  private void loadImage(ImageView paramImageView, String paramString)
  {
    DisplayImageOptions localDisplayImageOptions = new DisplayImageOptions.Builder().showImageOnLoading(2130837666).showImageForEmptyUri(2130837672).showImageOnFail(2130837672).cacheInMemory(true).cacheOnDisc(true).displayer(new RoundedBitmapDisplayer(110)).build();
    ImageLoader.getInstance().displayImage(paramString, paramImageView, localDisplayImageOptions);
  }

  private void onInvisible()
  {
  }

  private void onVisible()
  {
    lazyLoad();
  }

  private void reqBrushRank()
  {
    ReqBrushRank localReqBrushRank = new ReqBrushRank();
    localReqBrushRank.setClientType(ToolsUtils.getSDK());
    localReqBrushRank.setImei(ToolsUtils.getImei(this.mContext));
    localReqBrushRank.setNet(ToolsUtils.getStringNetworkType(this.mContext));
    localReqBrushRank.setVersionName(this.mSP.getVersionName());
    localReqBrushRank.setTimeSpan((String)BrushRankingActivity.indexToTag.get(Integer.valueOf(this.activity.currentIndex)));
    UserSubject localUserSubject = new UserSubject();
    localUserSubject.setSubjectId(this.activity.subjectId);
    localUserSubject.setUid(this.mSP.getUserId());
    localReqBrushRank.setUserSubject(localUserSubject);
    UrlFactory.getInstance().getUserBrushRank().constructUrl(this, localReqBrushRank, 1);
  }

  private void reqMockRank()
  {
    ReqBrushRank localReqBrushRank = new ReqBrushRank();
    localReqBrushRank.setClientType(ToolsUtils.getSDK());
    localReqBrushRank.setImei(ToolsUtils.getImei(this.mContext));
    localReqBrushRank.setNet(ToolsUtils.getStringNetworkType(this.mContext));
    localReqBrushRank.setVersionName(this.mSP.getVersionName());
    localReqBrushRank.setTimeSpan((String)BrushRankingActivity.indexToTag.get(Integer.valueOf(this.activity.currentIndex)));
    UserSubject localUserSubject = new UserSubject();
    localUserSubject.setSubjectId(this.activity.subjectId);
    localUserSubject.setUid(this.mSP.getUserId());
    localReqBrushRank.setUserSubject(localUserSubject);
    UrlFactory.getInstance().getMockRank().constructUrl(this, localReqBrushRank, 2);
  }

  private void setList()
  {
  }

  private void showPop()
  {
    if (this.chapterSectionListPop != null)
    {
      this.chapterSectionListPop.dismiss();
      this.chapterSectionListPop = null;
    }
    View localView = getActivity().getLayoutInflater().inflate(2130903092, null);
    this.chapter_list_pop_diss_ll = ((RelativeLayout)localView.findViewById(2131100039));
    this.chapter_share_ll = ((LinearLayout)localView.findViewById(2131100044));
    this.chapter_brush_ll = ((LinearLayout)localView.findViewById(2131100043));
    this.chapter_icon = ((ImageView)localView.findViewById(2131100040));
    this.chapter_tv1 = ((TextView)localView.findViewById(2131100041));
    this.chapter_tv2 = ((TextView)localView.findViewById(2131100042));
    this.chapter_share_ll.setVisibility(0);
    this.chapter_brush_ll.setVisibility(8);
    this.chapter_weibo_share = ((ImageButton)localView.findViewById(2131099965));
    this.chapter_weixin_pengyou = ((ImageButton)localView.findViewById(2131099966));
    this.chapter_weixin = ((ImageButton)localView.findViewById(2131099967));
    this.chapter_share_cancel = ((Button)localView.findViewById(2131100045));
    this.chapter_weixin.setOnClickListener(this);
    this.chapter_weixin_pengyou.setOnClickListener(this);
    this.chapter_weibo_share.setOnClickListener(this);
    this.chapter_share_cancel.setOnClickListener(this);
    this.chapter_list_pop_diss_ll.setOnClickListener(this);
    this.chapter_tv1.setVisibility(8);
    this.chapter_tv2.setText("号召更多的小伙伴来学习吧~");
    this.chapter_tv2.setVisibility(0);
    this.chapter_icon.setBackgroundResource(2130837702);
    localView.setFocusable(true);
    localView.setFocusableInTouchMode(true);
    this.chapterSectionListPop = new PopupWindow(localView, KouDaiSP.getInstance(this.mContext).getWidth(), ToolsUtils.dip2px(getActivity(), 650.0F));
    this.chapterSectionListPop.setAnimationStyle(2131361794);
    this.chapterSectionListPop.setFocusable(true);
    localView.setOnKeyListener(new OnKeyListener()
    {
      public boolean onKey(View paramView, int paramInt, KeyEvent paramKeyEvent)
      {
        if (paramInt == 4)
        {
          BrushRankingFragment.this.chapterSectionListPop.dismiss();
          BrushRankingFragment.this.chapterSectionListPop = null;
          BrushRankingFragment.this.backgroundAlpha(1.0F);
          return true;
        }
        return false;
      }
    });
    this.chapterSectionListPop.showAtLocation(this.ll_bottom, 80, 0, 0);
    backgroundAlpha(0.6F);
  }

  public void backgroundAlpha(float paramFloat)
  {
    WindowManager.LayoutParams localLayoutParams = getActivity().getWindow().getAttributes();
    localLayoutParams.alpha = paramFloat;
    getActivity().getWindow().setAttributes(localLayoutParams);
  }

  public void bindData()
  {
    this.swipyrefreshlayout_rank.setRefreshing(true);
    this.swipyrefreshlayout_rank.setOnRefreshListener(this);
    this.mListView.setOnScrollListener(this);
    this.mListView.setOnRefreshListener(this);
    this.textFindMe.setOnClickListener(this);
    this.buttonShare.setOnClickListener(this);
    String str1 = this.mSP.getAvatar();
    String str2 = this.mSP.getNickName();
    if ((str1 == null) || (str1.equals("")))
      loadImage(this.imageMyAvatar, "drawable://2130837672");
    while (true)
    {
      this.textMyName.setText(str2);
      return;
      this.mFileDownLoad.downLoadImage(str1, this.imageMyAvatar, 1000);
    }
  }

  public void initData()
  {
    this.activity = ((BrushRankingActivity)getActivity());
    this.subjectId = this.activity.subjectId;
    ToolsUtils.setList(-1, this.mListView, getActivity());
    this.isPrepared = true;
    lazyLoad();
  }

  public void initListener()
  {
    this.textFindMe.setOnClickListener(this);
    this.buttonShare.setOnClickListener(this);
  }

  public void initView(View paramView)
  {
    this.ll_bottom = ((LinearLayout)paramView.findViewById(2131099768));
    this.swipyrefreshlayout_rank = ((SwipyRefreshLayout)paramView.findViewById(2131100118));
    this.mListView = ((PullToRefreshListView)paramView.findViewById(2131100119));
    this.imageMyAvatar = ((ImageView)paramView.findViewById(2131100120));
    this.textMyName = ((TextView)paramView.findViewById(2131100121));
    this.textFindMe = ((TextView)paramView.findViewById(2131100122));
    this.buttonShare = ((Button)paramView.findViewById(2131100123));
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    case 2131099965:
    case 2131099966:
    case 2131099967:
    default:
    case 2131100122:
      do
      {
        do
        {
          return;
          switch (this.type)
          {
          default:
            return;
          case 0:
          case 1:
          }
        }
        while (this.userSummaryW == null);
        int j = this.userSummaryW.getMyRankPos();
        this.mAdapter.setMyItem(j);
        this.mAdapter.notifyDataSetChanged();
        this.mListView.requestFocus();
        ((ListView)this.mListView.getRefreshableView()).smoothScrollToPosition(j + 1);
        return;
      }
      while (this.userSummaryW == null);
      int i = this.userSummaryW.getMyRankPos();
      if (i == -1)
      {
        this.textFindMe.setText("还米有模考");
        return;
      }
      this.mAdapter.setMyItem(i);
      this.mAdapter.notifyDataSetChanged();
      this.mListView.requestFocus();
      ((ListView)this.mListView.getRefreshableView()).smoothScrollToPosition(i + 1);
      return;
    case 2131100123:
      new SharePopWindow((BrushRankingActivity)getActivity(), this.ll_bottom).showPop();
      return;
    case 2131100039:
    }
    dismissPop();
  }

  public void onHiddenChanged(boolean paramBoolean)
  {
    super.onHiddenChanged(paramBoolean);
  }

  public View onInflaterRootView(LayoutInflater paramLayoutInflater, Bundle paramBundle)
  {
    return paramLayoutInflater.inflate(2130903111, null);
  }

  public void onPullDownToRefresh(PullToRefreshBase<ListView> paramPullToRefreshBase)
  {
  }

  public void onPullUpToRefresh(PullToRefreshBase<ListView> paramPullToRefreshBase)
  {
    if (!this.isLoading)
    {
      this.handler.sendEmptyMessage(11);
      this.isLoading = true;
    }
  }

  public void onRefresh(SwipyRefreshLayoutDirection paramSwipyRefreshLayoutDirection)
  {
    if (paramSwipyRefreshLayoutDirection.equals(SwipyRefreshLayoutDirection.getFromInt(0)))
    {
      this.swipyrefreshlayout_rank.setRefreshing(true);
      this.handler.sendEmptyMessage(10);
    }
  }

  public void onResume()
  {
    super.onResume();
  }

  public void onScroll(AbsListView paramAbsListView, int paramInt1, int paramInt2, int paramInt3)
  {
    if (paramInt1 == 0)
    {
      this.swipyrefreshlayout_rank.setEnabled(true);
      return;
    }
    this.swipyrefreshlayout_rank.setEnabled(false);
  }

  public void onScrollStateChanged(AbsListView paramAbsListView, int paramInt)
  {
  }

  public void onSuccess(String paramString1, Map<String, String> paramMap, String paramString2, int paramInt)
  {
    super.onSuccess(paramString1, paramMap, paramString2, paramInt);
    this.mProTools.dismissDislog();
    if (paramString1 != null)
    {
      Gson localGson;
      try
      {
        localGson = UrlFactory.getInstanceGson();
        switch (paramInt)
        {
        case 1:
          this.userSummaryW = ((UserBrushSummaryW)localGson.fromJson(paramString1, UserBrushSummaryW.class));
          if (this.userSummaryW != null)
          {
            this.handler.sendEmptyMessage(10);
            return;
          }
        case 2:
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        return;
      }
      LogUtils.myLog("刷题榜解析宜昌");
      return;
      this.userMockSummaryW = ((UserMockSummaryW)localGson.fromJson(paramString1, UserMockSummaryW.class));
      if (this.userMockSummaryW != null)
      {
        this.handler.sendEmptyMessage(10);
        return;
      }
      LogUtils.myLog("刷题榜解析宜昌");
      return;
    }
    LogUtils.myLog(paramString1);
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
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.fragment.BrushRankingFragment
 * JD-Core Version:    0.6.0
 */