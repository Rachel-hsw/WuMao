package com.withustudy.koudaizikao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.Html;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.withustudy.koudaizikao.action.AbsSimpleImplUrlFactory;
import com.withustudy.koudaizikao.action.UrlFactory;
import com.withustudy.koudaizikao.adapter.ExcerciseCommentAdapter;
import com.withustudy.koudaizikao.adapter.ExcerciseCommentAdapter.Infor;
import com.withustudy.koudaizikao.base.AbsBaseActivity;
import com.withustudy.koudaizikao.config.KouDaiSP;
import com.withustudy.koudaizikao.custom.SharePopWindow;
import com.withustudy.koudaizikao.custom.pullrefersh.SwipyRefreshLayout;
import com.withustudy.koudaizikao.custom.pullrefersh.SwipyRefreshLayout.OnRefreshListener;
import com.withustudy.koudaizikao.custom.pullrefersh.SwipyRefreshLayoutDirection;
import com.withustudy.koudaizikao.entity.PublishCommentBean;
import com.withustudy.koudaizikao.entity.RspQuestionComment;
import com.withustudy.koudaizikao.entity.req.Comment;
import com.withustudy.koudaizikao.entity.req.ReqComment;
import com.withustudy.koudaizikao.entity.req.ReqQuestionMoreComment;
import com.withustudy.koudaizikao.entity.req.UserInfo;
import com.withustudy.koudaizikao.tools.LogUtils;
import com.withustudy.koudaizikao.tools.ProTools;
import com.withustudy.koudaizikao.tools.ToolsUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class QuestionMoreComentActivity extends AbsBaseActivity
  implements OnRefreshListener, OnClickListener, PullToRefreshBase.OnRefreshListener2<ListView>, OnScrollListener
{
  private static final int action_more_load = 1;
  private static final int action_more_refresh = 0;
  private static final int action_pubish_comment = 2;
  private static final int action_pubish_fail = 4;
  private static final int action_pubish_ok = 3;
  private Button btn_publish;
  private List<Comment> cacheData = new ArrayList();
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
  private EditText et_publish;
  private String exerciseId;
  private Handler handler = new Handler()
  {
    public void handleMessage(Message paramMessage)
    {
      switch (paramMessage.what)
      {
      case 2:
      case 5:
      case 6:
      default:
      case 0:
        List localList2;
        do
        {
          return;
          QuestionMoreComentActivity.this.mSwipyRefreshLayout.setRefreshing(false);
          localList2 = QuestionMoreComentActivity.this.rsp.getComment();
        }
        while ((localList2 == null) || (localList2.size() <= 0));
        QuestionMoreComentActivity.this.cacheData.clear();
        QuestionMoreComentActivity.this.cacheData.addAll(localList2);
        LogUtils.myLog("刷新数据 ExcerciseCommentAdapter size=" + QuestionMoreComentActivity.this.cacheData.size());
        if (QuestionMoreComentActivity.this.mAdapter == null)
        {
          QuestionMoreComentActivity.this.mAdapter = new ExcerciseCommentAdapter(QuestionMoreComentActivity.this.getApplicationContext(), QuestionMoreComentActivity.this.cacheData, this);
          QuestionMoreComentActivity.this.mListView.setAdapter(QuestionMoreComentActivity.this.mAdapter);
          return;
        }
        QuestionMoreComentActivity.this.mAdapter.notifyDataSetChanged();
        return;
      case 1:
        QuestionMoreComentActivity.this.mListView.onRefreshComplete();
        List localList1 = QuestionMoreComentActivity.this.rsp.getComment();
        if ((localList1 != null) && (localList1.size() > 0))
        {
          QuestionMoreComentActivity.this.cacheData.addAll(localList1);
          LogUtils.myLog("加载更多 ExcerciseCommentAdapter size=" + QuestionMoreComentActivity.this.cacheData.size());
          if (QuestionMoreComentActivity.this.mAdapter == null)
          {
            QuestionMoreComentActivity.this.mAdapter = new ExcerciseCommentAdapter(QuestionMoreComentActivity.this.getApplicationContext(), QuestionMoreComentActivity.this.cacheData, this);
            QuestionMoreComentActivity.this.mListView.setAdapter(QuestionMoreComentActivity.this.mAdapter);
          }
        }
        while (true)
        {
          QuestionMoreComentActivity.this.isLoading = false;
          return;
          QuestionMoreComentActivity.this.mAdapter.notifyDataSetChanged();
          continue;
          Toast.makeText(QuestionMoreComentActivity.this.getApplicationContext(), "没有更多评论了!", 0).show();
        }
      case 3:
        QuestionMoreComentActivity.this.et_publish.setText("");
        QuestionMoreComentActivity.this.manager.hideSoftInputFromWindow(QuestionMoreComentActivity.this.getCurrentFocus().getWindowToken(), 2);
        Toast.makeText(QuestionMoreComentActivity.this.getApplicationContext(), "评论成功!", 0).show();
        QuestionMoreComentActivity.this.refreshData();
        return;
      case 4:
        Toast.makeText(QuestionMoreComentActivity.this.getApplicationContext(), "评论失败!", 0).show();
        return;
      case 7:
      }
      QuestionMoreComentActivity.this.manager.toggleSoftInput(0, 2);
      QuestionMoreComentActivity.this.infor = ((Infor)paramMessage.obj);
    }
  };
  private Infor infor;
  private boolean isLoading = false;
  private boolean isShare = false;
  private LinearLayout layout_question_detail_edit;
  private LinearLayout ll_share;
  private LinearLayout ll_tucao_back;
  private ExcerciseCommentAdapter mAdapter;
  private PullToRefreshListView mListView;
  private SwipyRefreshLayout mSwipyRefreshLayout;
  private InputMethodManager manager;
  private int pageNum = 0;
  private PublishCommentBean publishCommentBean;
  private RspQuestionComment rsp;
  private String subjectId;

  private void dismissPop1()
  {
    if (this.chapterSectionListPop != null)
    {
      this.chapterSectionListPop.dismiss();
      this.chapterSectionListPop = null;
    }
    backgroundAlpha(1.0F);
  }

  private void loadData()
  {
    this.mProTools.startDialog(true);
    ReqQuestionMoreComment localReqQuestionMoreComment = new ReqQuestionMoreComment();
    localReqQuestionMoreComment.setVersionName(this.mSP.getVersionName());
    localReqQuestionMoreComment.setClientType(ToolsUtils.getSDK());
    localReqQuestionMoreComment.setImei(ToolsUtils.getImei(this.mContext));
    localReqQuestionMoreComment.setNet(ToolsUtils.getStringNetworkType(this.mContext));
    localReqQuestionMoreComment.setPageNo(this.pageNum);
    localReqQuestionMoreComment.setExerciseId(this.exerciseId);
    UrlFactory.getInstance().getExerciseCommentMore().constructUrl(this, localReqQuestionMoreComment, 1);
  }

  private void publishComment()
  {
    String str = this.et_publish.getText().toString().trim();
    if (str.equals(""))
    {
      Toast.makeText(getApplicationContext(), "内容不能为空", 0).show();
      return;
    }
    ReqComment localReqComment = new ReqComment();
    localReqComment.setVersionName(this.mSP.getVersionName());
    localReqComment.setClientType(ToolsUtils.getSDK());
    localReqComment.setImei(ToolsUtils.getImei(this.mContext));
    localReqComment.setNet(ToolsUtils.getStringNetworkType(this.mContext));
    localReqComment.setExerciseId(this.exerciseId);
    Comment localComment1 = new Comment();
    if (this.infor != null)
    {
      int i = this.infor.postion;
      Comment localComment2 = this.infor.comment;
      Html.fromHtml("今日刷题" + ToolsUtils.formatText("", "#D6D6D6"));
      localComment1.setReplyFloorContent("回复" + (i + 1) + "楼" + localComment2.getLocation() + "学员: " + localComment2.getContent());
      this.infor = null;
    }
    localComment1.setCommentTime(System.currentTimeMillis());
    localComment1.setContent(str);
    localComment1.setLocation(this.mSP.getCityName());
    UserInfo localUserInfo = new UserInfo();
    localUserInfo.setUid(this.mSP.getUserId());
    localComment1.setUserInfo(localUserInfo);
    localReqComment.setComment(localComment1);
    this.mProTools.startDialog(true);
    UrlFactory.getInstance().postAddExerciseComment().constructUrl(this, localReqComment, 2);
  }

  private void refreshData()
  {
    this.mProTools.startDialog(true);
    ReqQuestionMoreComment localReqQuestionMoreComment = new ReqQuestionMoreComment();
    localReqQuestionMoreComment.setVersionName(this.mSP.getVersionName());
    localReqQuestionMoreComment.setClientType(ToolsUtils.getSDK());
    localReqQuestionMoreComment.setImei(ToolsUtils.getImei(this.mContext));
    localReqQuestionMoreComment.setNet(ToolsUtils.getStringNetworkType(this.mContext));
    this.pageNum = 0;
    localReqQuestionMoreComment.setPageNo(this.pageNum);
    localReqQuestionMoreComment.setExerciseId(this.exerciseId);
    UrlFactory.getInstance().getExerciseCommentMore().constructUrl(this, localReqQuestionMoreComment, 0);
  }

  private void showPop1()
  {
    if (this.chapterSectionListPop != null)
    {
      this.chapterSectionListPop.dismiss();
      this.chapterSectionListPop = null;
    }
    View localView = getLayoutInflater().inflate(2130903092, null);
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
    this.chapterSectionListPop = new PopupWindow(localView, this.mSP.getWidth(), ToolsUtils.dip2px(this, 650.0F));
    this.chapterSectionListPop.setAnimationStyle(2131361794);
    this.chapterSectionListPop.setFocusable(true);
    localView.setOnKeyListener(new OnKeyListener()
    {
      public boolean onKey(View paramView, int paramInt, KeyEvent paramKeyEvent)
      {
        if (paramInt == 4)
        {
          QuestionMoreComentActivity.this.chapterSectionListPop.dismiss();
          QuestionMoreComentActivity.this.chapterSectionListPop = null;
          QuestionMoreComentActivity.this.backgroundAlpha(1.0F);
          return true;
        }
        return false;
      }
    });
    this.chapterSectionListPop.showAtLocation(this.mSwipyRefreshLayout, 80, 0, 0);
    backgroundAlpha(0.6F);
  }

  public void backgroundAlpha(float paramFloat)
  {
    WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
    localLayoutParams.alpha = paramFloat;
    getWindow().setAttributes(localLayoutParams);
  }

  protected void bindData()
  {
    refreshData();
  }

  protected void initData()
  {
    Bundle localBundle = getIntent().getExtras();
    try
    {
      this.exerciseId = localBundle.getString("exerciseId");
      this.subjectId = localBundle.getString("subjectId");
      label30: this.manager = ((InputMethodManager)getSystemService("input_method"));
      return;
    }
    catch (Exception localException)
    {
      break label30;
    }
  }

  protected void initListener()
  {
    this.mSwipyRefreshLayout.setRefreshing(true);
    this.mSwipyRefreshLayout.setOnRefreshListener(this);
    this.ll_share.setOnClickListener(this);
    this.ll_tucao_back.setOnClickListener(this);
    this.btn_publish.setOnClickListener(this);
    this.mListView.setOnScrollListener(this);
    this.mListView.setOnRefreshListener(this);
  }

  protected void initView()
  {
    this.layout_question_detail_edit = ((LinearLayout)findViewById(2131100107));
    this.mListView = ((PullToRefreshListView)findViewById(2131100106));
    this.mSwipyRefreshLayout = ((SwipyRefreshLayout)findViewById(2131100105));
    this.ll_share = ((LinearLayout)findViewById(2131100104));
    this.ll_tucao_back = ((LinearLayout)findViewById(2131100103));
    this.et_publish = ((EditText)findViewById(2131100108));
    this.btn_publish = ((Button)findViewById(2131100109));
    ToolsUtils.setList(1, this.mListView, this.mContext);
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
      return;
    case 2131100109:
      publishComment();
      return;
    case 2131100103:
      finish();
      return;
    case 2131100104:
      new SharePopWindow(this, this.layout_question_detail_edit).showPop();
      return;
    case 2131100039:
      Toast.makeText(getApplicationContext(), "隐藏", 0).show();
      dismissPop1();
      return;
    case 2131099965:
      Toast.makeText(getApplicationContext(), "微博", 0).show();
      return;
    case 2131099966:
      Toast.makeText(getApplicationContext(), "朋友圈", 0).show();
      return;
    case 2131099967:
    }
    Toast.makeText(getApplicationContext(), "微信", 0).show();
  }

  public void onPullDownToRefresh(PullToRefreshBase<ListView> paramPullToRefreshBase)
  {
  }

  public void onPullUpToRefresh(PullToRefreshBase<ListView> paramPullToRefreshBase)
  {
    if (!this.isLoading)
    {
      this.pageNum = (1 + this.pageNum);
      this.isLoading = true;
      loadData();
    }
  }

  public void onRefresh(SwipyRefreshLayoutDirection paramSwipyRefreshLayoutDirection)
  {
    if (paramSwipyRefreshLayoutDirection.equals(SwipyRefreshLayoutDirection.getFromInt(0)))
    {
      refreshData();
      return;
    }
    if (paramSwipyRefreshLayoutDirection.equals(SwipyRefreshLayoutDirection.getFromInt(1)))
    {
      LogUtils.myLog("加载");
      return;
    }
    this.mSwipyRefreshLayout.setRefreshing(false);
  }

  public void onScroll(AbsListView paramAbsListView, int paramInt1, int paramInt2, int paramInt3)
  {
    if (paramInt1 == 0)
    {
      this.mSwipyRefreshLayout.setEnabled(true);
      return;
    }
    this.mSwipyRefreshLayout.setEnabled(false);
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
        case 2:
          this.publishCommentBean = ((PublishCommentBean)localGson.fromJson(paramString1, PublishCommentBean.class));
          if (this.publishCommentBean == null)
            break label119;
          if ("OK".equals(this.publishCommentBean.getStatus()))
          {
            this.handler.sendEmptyMessage(3);
            return;
          }
        case 0:
        case 1:
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        return;
      }
      this.handler.sendEmptyMessage(4);
      return;
      label119: LogUtils.myLog("发布评论解析异常");
      return;
      this.rsp = ((RspQuestionComment)localGson.fromJson(paramString1, RspQuestionComment.class));
      if (this.rsp != null)
      {
        this.handler.sendEmptyMessage(0);
        return;
      }
      LogUtils.myLog("刷新数据 解析评论为null");
      return;
      this.rsp = ((RspQuestionComment)localGson.fromJson(paramString1, RspQuestionComment.class));
      if (this.rsp != null)
      {
        this.handler.sendEmptyMessage(1);
        return;
      }
      LogUtils.myLog("加载更多 解析评论为null");
      return;
    }
    LogUtils.myLog("response::---------" + paramString1);
  }

  protected void setContentView()
  {
    setContentView(2130903105);
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.activity.QuestionMoreComentActivity
 * JD-Core Version:    0.6.0
 */