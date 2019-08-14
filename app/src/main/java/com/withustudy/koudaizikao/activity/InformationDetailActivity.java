package com.withustudy.koudaizikao.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import com.android.http.FileDownLoad;
import com.google.gson.Gson;
import com.umeng.analytics.MobclickAgent;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.withustudy.koudaizikao.action.AbsBaseUrlConstructor;
import com.withustudy.koudaizikao.action.AbsSimpleImplUrlFactory;
import com.withustudy.koudaizikao.action.UrlFactory;
import com.withustudy.koudaizikao.adapter.InformationDetailAdapter;
import com.withustudy.koudaizikao.base.AbsBaseActivity;
import com.withustudy.koudaizikao.config.KouDaiSP;
import com.withustudy.koudaizikao.custom.AdaptiveListView;
import com.withustudy.koudaizikao.custom.LoadingView;
import com.withustudy.koudaizikao.entity.InfoComment;
import com.withustudy.koudaizikao.entity.News;
import com.withustudy.koudaizikao.entity.content.NewsCommentContent;
import com.withustudy.koudaizikao.entity.content.NewsCommentResult;
import com.withustudy.koudaizikao.entity.content.NewsDetailContent;
import com.withustudy.koudaizikao.entity.content.NewsPushContent;
import com.withustudy.koudaizikao.entity.content.ResultStatus;
import com.withustudy.koudaizikao.entity.req.NewsComment;
import com.withustudy.koudaizikao.tools.DateTools;
import com.withustudy.koudaizikao.tools.ProTools;
import com.withustudy.koudaizikao.tools.ScreenTools;
import com.withustudy.koudaizikao.tools.ToolsUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InformationDetailActivity extends AbsBaseActivity
{
  public static final int ACITON_REPLY = 11;
  public static final int ACTION_COLLECT = 13;
  public static final int ACTION_GET_CONTENT = 10;
  public static final int ACTION_GET_REPLY = 12;
  public static final int ACTION_PRAISE = 14;
  public static final int ACTION_PUSH = 15;
  public static final int ADD_REPLY = 2;
  public static final int ADD_REPLY_FAIL = 3;
  public static final int GET_FLOOR = 1;
  private Button buttonBack;
  private Button buttonCollect;
  private Button buttonDiscuss;
  private Button buttonPraise;
  private Button buttonShare;
  private String content;
  private EditText editDiscuss;
  private int id;
  private ImageView imageShare1;
  private ImageView imageShare2;
  private ImageView imageShare3;
  private ImageView imageShare4;
  private boolean isCollect = false;
  private boolean isPraise = false;
  private boolean isPush = false;
  private boolean isUpdateCollect = false;
  private boolean isUpdatePraise = false;
  private LinearLayout layoutContent;
  private FrameLayout layoutDiscuss;
  private LinearLayout layoutEdit;
  private FrameLayout layoutShare;
  private List<InfoComment> list;
  private InformationDetailAdapter mAdapter;
  private CallBackListener mBackListener;
  private InformationDetailHandler mHandler;
  private AdaptiveListView mListView;
  private LoadingView mLoadingView;
  private News mNews;
  private ScrollView mScrollView;
  private InputMethodManager manager;
  private int parentId = 0;
  private TextView textDiscuss;
  private TextView textTime;
  private TextView textTitle;
  private TextView textTypeTitle;

  private void initKeyboard()
  {
    this.manager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 2);
    this.editDiscuss.setHint(getResources().getString(2131165276));
    this.editDiscuss.setText("");
    this.parentId = 0;
  }

  private void initPra()
  {
    if (this.mNews.getArticleType().equals("DAILY_CHEESE"))
    {
      this.textTypeTitle.setText("每日芝士");
      this.textDiscuss.setText(this.mNews.getCommentNum() + getResources().getString(2131165271));
      this.textTitle.setText(this.mNews.getInstruction());
      if (!this.mNews.getFavoriteTag().equals("1"))
        break label319;
      this.isCollect = true;
      this.buttonCollect.setBackgroundResource(2130837586);
      label106: if (this.mNews.getArticleType().equals("DAILY_CHEESE"))
      {
        this.buttonPraise.setVisibility(0);
        if (!this.mNews.getThumbTag().equals("1"))
          break label337;
        this.isPraise = true;
        this.buttonPraise.setBackgroundResource(2130837592);
      }
    }
    while (true)
    {
      this.mListView.setAdapter(this.mAdapter);
      this.mListView.setSelector(2131230724);
      this.mListView.setOnItemClickListener(this.mBackListener);
      this.mScrollView.setOnTouchListener(this.mBackListener);
      return;
      if (this.mNews.getArticleType().equals("EXAMINATION_ROAD"))
      {
        this.textTypeTitle.setText("自考路上");
        break;
      }
      if (this.mNews.getArticleType().equals("SIGN_UP_INFO"))
      {
        this.textTypeTitle.setText("报考讯息");
        break;
      }
      if (this.mNews.getArticleType().equals("OFFICIAL_NEWS"))
      {
        this.textTypeTitle.setText("新闻看点");
        break;
      }
      if (!this.mNews.getArticleType().equals("COMMEN_QUESTION"))
        break;
      this.textTypeTitle.setText("常见问题");
      break;
      label319: this.isCollect = false;
      this.buttonCollect.setBackgroundResource(2130837597);
      break label106;
      label337: this.isPraise = false;
      this.buttonPraise.setBackgroundResource(2130837624);
    }
  }

  private void setContent()
  {
    LayoutParams localLayoutParams = new LayoutParams(-1, -2);
    List localList = splitContent(this.content);
    int i = 0;
    if (i >= localList.size())
      return;
    if ((!((String)localList.get(i)).contains("<$")) || (!((String)localList.get(i)).contains("$>")))
    {
      TextView localTextView = new TextView(this.mContext);
      localTextView.setText((CharSequence)localList.get(i));
      localTextView.setLayoutParams(localLayoutParams);
      localTextView.setTextColor(Color.parseColor("#333333"));
      localTextView.setTextSize(2, 16.0F);
      localTextView.setLineSpacing(0.0F, 1.4F);
      this.layoutContent.addView(localTextView);
    }
    while (true)
    {
      i++;
      break;
      ImageView localImageView = new ImageView(this.mContext);
      localImageView.setLayoutParams(new LayoutParams(-2, -2));
      localImageView.setImageResource(2130837808);
      localImageView.setScaleType(ScaleType.FIT_XY);
      localImageView.setTag(Integer.valueOf(i));
      localImageView.setOnClickListener(new OnClickListener(localList)
      {
        public void onClick(View paramView)
        {
          Bundle localBundle = new Bundle();
          localBundle.putInt("currentitem_key", 0);
          ArrayList localArrayList = new ArrayList();
          localArrayList.add(((String)this.val$list.get(((Integer)paramView.getTag()).intValue())).substring(2, -2 + ((String)this.val$list.get(((Integer)paramView.getTag()).intValue())).length()));
          localBundle.putSerializable("image_name", (Serializable)localArrayList);
          InformationDetailActivity.this.startNewActivity(ShowPictureActivity.class, false, localBundle);
        }
      });
      this.mFileDownLoad.downLoadImformationDetail(((String)localList.get(i)).substring(2, -2 + ((String)localList.get(i)).length()), localImageView, this.mContext);
      this.layoutContent.addView(localImageView);
    }
  }

  private List<String> splitContent(String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    if ((!paramString.contains("<$")) || (!paramString.contains("$>")))
    {
      localArrayList.add(paramString);
      return localArrayList;
    }
    int i = 0;
    int j = 0;
    for (int k = 0; ; k++)
    {
      if (k >= paramString.length())
      {
        if ((paramString.endsWith("$>")) || (!paramString.contains("$>")))
          break;
        localArrayList.add(paramString.substring(2 + paramString.lastIndexOf("$>"), paramString.length()));
        return localArrayList;
      }
      if ((paramString.charAt(k) == '<') && (paramString.charAt(k + 1) == '$'))
      {
        i = k;
        if ((paramString.substring(j, i) != null) && (!paramString.substring(j, i).equals("")))
          localArrayList.add(paramString.substring(j, i));
      }
      if ((paramString.charAt(k) != '$') || (paramString.charAt(k + 1) != '>'))
        continue;
      int m = k + 1;
      localArrayList.add(paramString.substring(i, m + 1));
      j = m + 1;
    }
  }

  protected void bindData()
  {
    if (!this.isPush)
    {
      initPra();
      AbsBaseUrlConstructor localAbsBaseUrlConstructor2 = UrlFactory.getInstance().getInfoDetail();
      String[] arrayOfString2 = new String[1];
      arrayOfString2[0] = this.mNews.getArticleId();
      localAbsBaseUrlConstructor2.constructUrl(this, arrayOfString2, 10, this.mContext);
      return;
    }
    AbsBaseUrlConstructor localAbsBaseUrlConstructor1 = UrlFactory.getInstance().newsPush();
    String[] arrayOfString1 = new String[1];
    arrayOfString1[0] = String.valueOf(this.id);
    localAbsBaseUrlConstructor1.constructUrl(this, arrayOfString1, 15, this.mContext);
  }

  public void finish()
  {
    Intent localIntent = new Intent();
    localIntent.putExtra("result", this.mNews.getArticleId());
    if ((this.isUpdateCollect) && (this.isUpdatePraise))
      setResult(24, localIntent);
    while (true)
    {
      super.finish();
      return;
      if ((!this.isUpdateCollect) && (this.isUpdatePraise))
      {
        setResult(23, localIntent);
        continue;
      }
      if ((this.isUpdateCollect) && (!this.isUpdatePraise))
      {
        setResult(22, localIntent);
        continue;
      }
      if ((this.isUpdateCollect) || (this.isUpdatePraise))
        continue;
      setResult(21, localIntent);
    }
  }

  protected void initData()
  {
    this.manager = ((InputMethodManager)getSystemService("input_method"));
    this.mBackListener = new CallBackListener();
    this.mHandler = new InformationDetailHandler();
    this.list = new ArrayList();
    this.mAdapter = new InformationDetailAdapter(this.mContext, this.list, this.mHandler);
    this.mNews = ((News)getIntent().getExtras().getSerializable("content"));
    this.id = getIntent().getExtras().getInt("id");
    this.isPush = getIntent().getExtras().getBoolean("push");
    if (this.isPush)
      ScreenTools.savePra(this.mContext);
  }

  protected void initListener()
  {
    this.buttonBack.setOnClickListener(this.mBackListener);
    this.buttonCollect.setOnClickListener(this.mBackListener);
    this.buttonShare.setOnClickListener(this.mBackListener);
    this.imageShare1.setOnClickListener(this.mBackListener);
    this.imageShare2.setOnClickListener(this.mBackListener);
    this.imageShare3.setOnClickListener(this.mBackListener);
    this.imageShare4.setOnClickListener(this.mBackListener);
    this.buttonPraise.setOnClickListener(this.mBackListener);
    this.buttonDiscuss.setOnClickListener(this.mBackListener);
  }

  protected void initView()
  {
    this.buttonBack = ((Button)findViewById(2131099782));
    this.textTypeTitle = ((TextView)findViewById(2131099783));
    this.buttonCollect = ((Button)findViewById(2131099785));
    this.buttonShare = ((Button)findViewById(2131099786));
    this.textDiscuss = ((TextView)findViewById(2131099784));
    this.textTitle = ((TextView)findViewById(2131099789));
    this.textTime = ((TextView)findViewById(2131099790));
    this.layoutContent = ((LinearLayout)findViewById(2131099791));
    this.layoutShare = ((FrameLayout)findViewById(2131099792));
    this.mLoadingView = ((LoadingView)findViewById(2131099787));
    this.mScrollView = ((ScrollView)findViewById(2131099788));
    this.imageShare1 = ((ImageView)findViewById(2131099793));
    this.imageShare2 = ((ImageView)findViewById(2131099794));
    this.imageShare3 = ((ImageView)findViewById(2131099795));
    this.imageShare4 = ((ImageView)findViewById(2131099796));
    this.layoutDiscuss = ((FrameLayout)findViewById(2131099797));
    this.mListView = ((AdaptiveListView)findViewById(2131099798));
    this.layoutEdit = ((LinearLayout)findViewById(2131099799));
    this.editDiscuss = ((EditText)findViewById(2131099801));
    this.buttonPraise = ((Button)findViewById(2131099800));
    this.buttonDiscuss = ((Button)findViewById(2131099802));
  }

  public void onError(String paramString1, String paramString2, int paramInt)
  {
    super.onError(paramString1, paramString2, paramInt);
    switch (paramInt)
    {
    default:
      return;
    case 11:
      this.mProTools.dismissDislog();
      return;
    case 12:
    }
    this.mProTools.dismissDislog();
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramInt == 4)
    {
      finish();
      if (this.isPush)
        startNewActivity(WelcomeActivity.class, false, null);
      return true;
    }
    return super.onKeyDown(paramInt, paramKeyEvent);
  }

  protected void onResume()
  {
    super.onResume();
    if (this.mSP.getUserId().equals(""))
    {
      this.layoutEdit.setVisibility(8);
      return;
    }
    this.layoutEdit.setVisibility(0);
  }

  public void onSuccess(String paramString1, Map<String, String> paramMap, String paramString2, int paramInt)
  {
    super.onSuccess(paramString1, paramMap, paramString2, paramInt);
    this.mProTools.dismissDislog();
    switch (paramInt)
    {
    default:
    case 15:
    case 10:
    case 11:
    case 12:
    case 13:
    case 14:
    }
    while (true)
    {
      return;
      if (paramString1 == null)
        continue;
      try
      {
        NewsPushContent localNewsPushContent = (NewsPushContent)UrlFactory.getInstanceGson().fromJson(paramString1, NewsPushContent.class);
        if (localNewsPushContent != null)
        {
          this.mNews = localNewsPushContent.getBasicNews();
          initPra();
          AbsBaseUrlConstructor localAbsBaseUrlConstructor = UrlFactory.getInstance().getInfoDetail();
          String[] arrayOfString = new String[1];
          arrayOfString[0] = this.mNews.getArticleId();
          localAbsBaseUrlConstructor.constructUrl(this, arrayOfString, 10, this.mContext);
          return;
        }
      }
      catch (Exception localException6)
      {
        localException6.printStackTrace();
        Toast.makeText(this.mContext, "服务器未响应，请稍后再试", 0).show();
        return;
      }
      Toast.makeText(this.mContext, "服务器未能返回数据，请稍后再试", 0).show();
      return;
      if (paramString1 == null)
        continue;
      try
      {
        NewsDetailContent localNewsDetailContent = (NewsDetailContent)UrlFactory.getInstanceGson().fromJson(paramString1, NewsDetailContent.class);
        if (localNewsDetailContent != null)
        {
          this.mLoadingView.setVisibility(8);
          this.mScrollView.setVisibility(0);
          this.content = localNewsDetailContent.getContent();
          this.textTime.setText(localNewsDetailContent.getAuthor() + "  " + DateTools.getPretime(this.mContext, Long.valueOf(this.mNews.getCreatedTime()).longValue()));
          setContent();
          this.layoutShare.setVisibility(0);
          if ((localNewsDetailContent.getCommentList() == null) || (localNewsDetailContent.getCommentList().size() == 0))
            continue;
          this.layoutDiscuss.setVisibility(0);
          this.list.clear();
          this.list.addAll(localNewsDetailContent.getCommentList());
          this.mAdapter.notifyDataSetChanged();
          return;
        }
      }
      catch (Exception localException5)
      {
        localException5.printStackTrace();
        Toast.makeText(this.mContext, "服务器未响应，请稍后再试", 0).show();
        return;
      }
      Toast.makeText(this.mContext, "服务器未能返回数据，请稍后再试", 0).show();
      return;
      if (paramString1 == null)
        continue;
      try
      {
        NewsCommentResult localNewsCommentResult = (NewsCommentResult)UrlFactory.getInstanceGson().fromJson(paramString1, NewsCommentResult.class);
        if ((localNewsCommentResult != null) && (localNewsCommentResult.getNewsStatus().equals("NEWS_STATUS_OK")))
        {
          this.mHandler.sendEmptyMessage(2);
          return;
        }
      }
      catch (Exception localException4)
      {
        localException4.printStackTrace();
        this.mHandler.sendEmptyMessage(3);
        return;
      }
      this.mHandler.sendEmptyMessage(3);
      return;
      if (paramString1 == null)
        continue;
      try
      {
        NewsCommentContent localNewsCommentContent = (NewsCommentContent)UrlFactory.getInstanceGson().fromJson(paramString1, NewsCommentContent.class);
        if ((localNewsCommentContent != null) && (localNewsCommentContent.getComments() != null))
        {
          this.layoutDiscuss.setVisibility(0);
          this.list.addAll(localNewsCommentContent.getComments());
          this.mAdapter.notifyDataSetChanged();
          return;
        }
      }
      catch (Exception localException3)
      {
        localException3.printStackTrace();
        Toast.makeText(this.mContext, "服务器未响应，请稍后再试", 0).show();
        return;
      }
      Toast.makeText(this.mContext, "没有更多评论了", 0).show();
      return;
      if (paramString1 == null)
        continue;
      try
      {
        ResultStatus localResultStatus2 = (ResultStatus)UrlFactory.getInstanceGson().fromJson(paramString1, ResultStatus.class);
        if ((localResultStatus2 == null) || (!localResultStatus2.getStatus().equals("NEWS_STATUS_OK")))
          break label681;
        if (!this.isCollect)
          break label654;
        if (this.mNews.getFavoriteTag().equals("1"))
        {
          this.isUpdateCollect = false;
          return;
        }
      }
      catch (Exception localException2)
      {
        localException2.printStackTrace();
        Toast.makeText(this.mContext, "服务器未响应，请稍后再试", 0).show();
        return;
      }
      this.isUpdateCollect = true;
      return;
      label654: if (this.mNews.getFavoriteTag().equals("1"))
      {
        this.isUpdateCollect = true;
        return;
      }
      this.isUpdateCollect = false;
      return;
      label681: Toast.makeText(this.mContext, "服务器未能返回数据，请稍后再试", 0).show();
      return;
      if (paramString1 == null)
        continue;
      try
      {
        ResultStatus localResultStatus1 = (ResultStatus)UrlFactory.getInstanceGson().fromJson(paramString1, ResultStatus.class);
        if ((localResultStatus1 == null) || (!localResultStatus1.getStatus().equals("NEWS_STATUS_OK")))
          continue;
        if (!this.isPraise)
          break label790;
        if (!this.mNews.getThumbTag().equals("1"))
          break;
        this.isUpdatePraise = false;
        return;
      }
      catch (Exception localException1)
      {
        localException1.printStackTrace();
        Toast.makeText(this.mContext, "服务器未响应，请稍后再试", 0).show();
        return;
      }
    }
    this.isUpdatePraise = true;
    return;
    label790: if (this.mNews.getThumbTag().equals("1"))
    {
      this.isUpdatePraise = true;
      return;
    }
    this.isUpdatePraise = false;
  }

  protected void setContentView()
  {
    setContentView(2130903055);
  }

  class CallBackListener
    implements OnClickListener, OnItemClickListener, OnTouchListener
  {
    CallBackListener()
    {
    }

    public void onClick(View paramView)
    {
      switch (paramView.getId())
      {
      case 2131099783:
      case 2131099784:
      case 2131099787:
      case 2131099788:
      case 2131099789:
      case 2131099790:
      case 2131099791:
      case 2131099792:
      case 2131099797:
      case 2131099798:
      case 2131099799:
      case 2131099801:
      default:
      case 2131099782:
        do
        {
          return;
          InformationDetailActivity.this.finish();
        }
        while (!InformationDetailActivity.this.isPush);
        InformationDetailActivity.this.startNewActivity(WelcomeActivity.class, false, null);
        return;
      case 2131099785:
        if (InformationDetailActivity.this.mSP.getUserId().equals(""))
        {
          Toast.makeText(InformationDetailActivity.this.mContext, "请先登录", 0).show();
          return;
        }
        if (!InformationDetailActivity.this.isCollect)
        {
          MobclickAgent.onEvent(InformationDetailActivity.this.mContext, "news_collect");
          Toast.makeText(InformationDetailActivity.this.mContext, "收藏成功", 0).show();
          InformationDetailActivity.this.buttonCollect.setBackgroundResource(2130837586);
          InformationDetailActivity.this.isCollect = true;
          AbsBaseUrlConstructor localAbsBaseUrlConstructor4 = UrlFactory.getInstance().newsCollect();
          InformationDetailActivity localInformationDetailActivity4 = InformationDetailActivity.this;
          String[] arrayOfString4 = new String[3];
          arrayOfString4[0] = InformationDetailActivity.access$9(InformationDetailActivity.this).getUserId();
          arrayOfString4[1] = InformationDetailActivity.access$3(InformationDetailActivity.this).getArticleId();
          arrayOfString4[2] = "ADD";
          localAbsBaseUrlConstructor4.constructUrl(localInformationDetailActivity4, arrayOfString4, 13, InformationDetailActivity.this.mContext);
          return;
        }
        InformationDetailActivity.this.buttonCollect.setBackgroundResource(2130837597);
        InformationDetailActivity.this.isCollect = false;
        AbsBaseUrlConstructor localAbsBaseUrlConstructor3 = UrlFactory.getInstance().newsCollect();
        InformationDetailActivity localInformationDetailActivity3 = InformationDetailActivity.this;
        String[] arrayOfString3 = new String[3];
        arrayOfString3[0] = InformationDetailActivity.access$9(InformationDetailActivity.this).getUserId();
        arrayOfString3[1] = InformationDetailActivity.access$3(InformationDetailActivity.this).getArticleId();
        arrayOfString3[2] = "DELETE";
        localAbsBaseUrlConstructor3.constructUrl(localInformationDetailActivity3, arrayOfString3, 13, InformationDetailActivity.this.mContext);
        return;
      case 2131099786:
        InformationDetailActivity.this.mScrollView.scrollTo(0, InformationDetailActivity.this.layoutContent.getHeight());
        return;
      case 2131099800:
        if (InformationDetailActivity.this.mSP.getUserId().equals(""))
        {
          Toast.makeText(InformationDetailActivity.this.mContext, "请先登录", 0).show();
          return;
        }
        if (!InformationDetailActivity.this.isPraise)
        {
          InformationDetailActivity.this.buttonPraise.setBackgroundResource(2130837592);
          InformationDetailActivity.this.isPraise = true;
          AbsBaseUrlConstructor localAbsBaseUrlConstructor2 = UrlFactory.getInstance().newsPraise();
          InformationDetailActivity localInformationDetailActivity2 = InformationDetailActivity.this;
          String[] arrayOfString2 = new String[3];
          arrayOfString2[0] = InformationDetailActivity.access$9(InformationDetailActivity.this).getUserId();
          arrayOfString2[1] = InformationDetailActivity.access$3(InformationDetailActivity.this).getArticleId();
          arrayOfString2[2] = "ADD";
          localAbsBaseUrlConstructor2.constructUrl(localInformationDetailActivity2, arrayOfString2, 14, InformationDetailActivity.this.mContext);
          return;
        }
        InformationDetailActivity.this.buttonPraise.setBackgroundResource(2130837624);
        InformationDetailActivity.this.isPraise = false;
        AbsBaseUrlConstructor localAbsBaseUrlConstructor1 = UrlFactory.getInstance().newsPraise();
        InformationDetailActivity localInformationDetailActivity1 = InformationDetailActivity.this;
        String[] arrayOfString1 = new String[3];
        arrayOfString1[0] = InformationDetailActivity.access$9(InformationDetailActivity.this).getUserId();
        arrayOfString1[1] = InformationDetailActivity.access$3(InformationDetailActivity.this).getArticleId();
        arrayOfString1[2] = "CANCEL";
        localAbsBaseUrlConstructor1.constructUrl(localInformationDetailActivity1, arrayOfString1, 14, InformationDetailActivity.this.mContext);
        return;
      case 2131099793:
        MobclickAgent.onEvent(InformationDetailActivity.this.mContext, "news_share");
        InformationDetailActivity.this.share(SHARE_MEDIA.WEIXIN, "http://share.kdzikao.com/news/share.page?article_id=" + InformationDetailActivity.this.mNews.getArticleId(), InformationDetailActivity.this.mNews.getInstruction(), InformationDetailActivity.this.mNews.getBriefText());
        return;
      case 2131099794:
        MobclickAgent.onEvent(InformationDetailActivity.this.mContext, "news_share");
        InformationDetailActivity.this.share(SHARE_MEDIA.WEIXIN_CIRCLE, "http://share.kdzikao.com/news/share.page?article_id=" + InformationDetailActivity.this.mNews.getArticleId(), InformationDetailActivity.this.mNews.getInstruction(), InformationDetailActivity.this.mNews.getBriefText());
        return;
      case 2131099795:
        MobclickAgent.onEvent(InformationDetailActivity.this.mContext, "news_share");
        InformationDetailActivity.this.share(SHARE_MEDIA.QQ, "http://share.kdzikao.com/news/share.page?article_id=" + InformationDetailActivity.this.mNews.getArticleId(), InformationDetailActivity.this.mNews.getInstruction(), InformationDetailActivity.this.mNews.getBriefText());
        return;
      case 2131099796:
        MobclickAgent.onEvent(InformationDetailActivity.this.mContext, "news_share");
        InformationDetailActivity.this.share(SHARE_MEDIA.SINA, "http://share.kdzikao.com/news/share.page?article_id=" + InformationDetailActivity.this.mNews.getArticleId(), InformationDetailActivity.this.mNews.getInstruction(), InformationDetailActivity.this.mNews.getBriefText());
        return;
      case 2131099802:
      }
      if (InformationDetailActivity.this.editDiscuss.getText().toString().equals(""))
      {
        Toast.makeText(InformationDetailActivity.this.mContext, "评论内容不能为空", 0).show();
        return;
      }
      NewsComment localNewsComment = new NewsComment();
      localNewsComment.setVersionName(InformationDetailActivity.this.mSP.getVersionName());
      localNewsComment.setClientType(ToolsUtils.getSDK());
      localNewsComment.setImei(ToolsUtils.getImei(InformationDetailActivity.this.mContext));
      localNewsComment.setNet(ToolsUtils.getStringNetworkType(InformationDetailActivity.this.mContext));
      localNewsComment.setArticleId(InformationDetailActivity.this.mNews.getArticleId());
      localNewsComment.setUid(InformationDetailActivity.this.mSP.getUserId());
      localNewsComment.setContent(InformationDetailActivity.this.editDiscuss.getText().toString());
      localNewsComment.setParentId(String.valueOf(InformationDetailActivity.this.parentId));
      if (InformationDetailActivity.this.parentId == 0)
        MobclickAgent.onEvent(InformationDetailActivity.this.mContext, "news_publish");
      while (true)
      {
        InformationDetailActivity.this.mProTools.startDialog("努力发表中");
        UrlFactory.getInstance().newsComment().constructUrl(InformationDetailActivity.this, localNewsComment, 11);
        return;
        MobclickAgent.onEvent(InformationDetailActivity.this.mContext, "news_reply");
      }
    }

    public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
    {
      if (paramInt == InformationDetailActivity.this.list.size())
      {
        InformationDetailActivity.this.mProTools.startDialog();
        AbsBaseUrlConstructor localAbsBaseUrlConstructor = UrlFactory.getInstance().getNewsComment();
        InformationDetailActivity localInformationDetailActivity = InformationDetailActivity.this;
        String[] arrayOfString = new String[2];
        arrayOfString[0] = InformationDetailActivity.access$3(InformationDetailActivity.this).getArticleId();
        arrayOfString[1] = String.valueOf(InformationDetailActivity.access$7(InformationDetailActivity.this).size());
        localAbsBaseUrlConstructor.constructUrl(localInformationDetailActivity, arrayOfString, 12, InformationDetailActivity.this.mContext);
      }
    }

    public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
    {
      InformationDetailActivity.this.initKeyboard();
      return false;
    }
  }

  class InformationDetailHandler extends Handler
  {
    InformationDetailHandler()
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
        InformationDetailActivity.this.editDiscuss.requestFocus();
        InformationDetailActivity.this.editDiscuss.setHint(InformationDetailActivity.this.getResources().getString(2131165363) + ((InfoComment)paramMessage.obj).getFloorId() + InformationDetailActivity.this.getResources().getString(2131165270));
        InformationDetailActivity.this.manager.toggleSoftInput(0, 2);
        InformationDetailActivity.this.parentId = Integer.valueOf(((InfoComment)paramMessage.obj).getCommentId()).intValue();
        return;
      case 2:
        if (InformationTypeActivity.mHandler != null)
          InformationTypeActivity.mHandler.sendMessage(InformationTypeActivity.mHandler.obtainMessage(3, InformationDetailActivity.this.mNews.getArticleId()));
        Toast.makeText(InformationDetailActivity.this.mContext, "评论成功", 0).show();
        InformationDetailActivity.this.initKeyboard();
        InformationDetailActivity.this.textDiscuss.setText(1 + Integer.valueOf(InformationDetailActivity.this.mNews.getCommentNum()).intValue() + InformationDetailActivity.this.getResources().getString(2131165271));
        AbsBaseUrlConstructor localAbsBaseUrlConstructor = UrlFactory.getInstance().getNewsComment();
        InformationDetailActivity localInformationDetailActivity = InformationDetailActivity.this;
        String[] arrayOfString = new String[2];
        arrayOfString[0] = InformationDetailActivity.access$3(InformationDetailActivity.this).getArticleId();
        arrayOfString[1] = String.valueOf(InformationDetailActivity.access$7(InformationDetailActivity.this).size());
        localAbsBaseUrlConstructor.constructUrl(localInformationDetailActivity, arrayOfString, 12, InformationDetailActivity.this.mContext);
        return;
      case 3:
      }
      Toast.makeText(InformationDetailActivity.this.mContext, "服务器未响应，请稍后再试", 0).show();
      InformationDetailActivity.this.initKeyboard();
    }
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.activity.InformationDetailActivity
 * JD-Core Version:    0.6.0
 */