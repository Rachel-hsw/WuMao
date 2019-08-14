package com.withustudy.koudaizikao.activity;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore.Images.Media;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import com.android.http.FileUpload;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.umeng.analytics.MobclickAgent;
import com.withustudy.koudaizikao.action.AbsBaseUrlConstructor;
import com.withustudy.koudaizikao.action.UrlFactory;
import com.withustudy.koudaizikao.adapter.PostDetailAdapter;
import com.withustudy.koudaizikao.base.AbsBaseActivity;
import com.withustudy.koudaizikao.config.KouDaiSP;
import com.withustudy.koudaizikao.custom.AddImageView;
import com.withustudy.koudaizikao.custom.LoadingView;
import com.withustudy.koudaizikao.custom.SharePopWindow;
import com.withustudy.koudaizikao.entity.Post;
import com.withustudy.koudaizikao.entity.PostReply;
import com.withustudy.koudaizikao.tools.FileTools;
import com.withustudy.koudaizikao.tools.ImageTools;
import com.withustudy.koudaizikao.tools.LogUtils;
import com.withustudy.koudaizikao.tools.ProTools;
import com.withustudy.koudaizikao.tools.ScreenTools;
import com.withustudy.koudaizikao.tools.ToolsUtils;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class PostDetailActivity extends AbsBaseActivity
{
  public static final int ACTION_COLLECT = 15;
  public static final int ACTION_DELETE_POST = 11;
  public static final int ACTION_GET_POST = 10;
  public static final int ACTION_LOAD = 13;
  public static final int ACTION_PRAISE = 14;
  public static final int ACTION_PUBLISH = 16;
  public static final int ACTION_REFRESH = 12;
  public static final int DELETE_POST = 8;
  public static final int EDIT_HINT = 4;
  public static final int GET_IMAGE = 6;
  public static final int HIDE_KEYBOARD = 3;
  public static final int LOAD_FINISH = 2;
  public static final int MAX_IMAGE_COUNT = 9;
  public static final int PUBLISH_FINISH = 9;
  public static final int REFRESH_FINISH = 1;
  public static final int REMOVE_VIEW = 5;
  public static final int UPLOAD_FINISH = 7;
  private Button buttonBack;
  private Button buttonCollect;
  private Button buttonLocation;
  private Button buttonPicture;
  private Button buttonPublish;
  private Button buttonShare;
  private int currCount;
  private EditText edit;
  private File file;
  private List<File> fileList;
  private String[] fileName;
  private int hight = 0;
  private List<Integer> hightList;
  private int id;
  private List<AddImageView> imageList;
  private boolean isCollect = false;
  private boolean isInit = false;
  private boolean isLoading = false;
  private boolean isLocation = true;
  private boolean isPraise = false;
  private boolean isPraiseState = true;
  private boolean isPush = false;
  private boolean isReplyPost = true;
  private LinearLayout layoutChild;
  private LinearLayout layoutEdit;
  private LinearLayout layoutLocation;
  private RelativeLayout layoutPicture;
  private List<PostReply> list;
  private PostDetailAdapter mAdapter;
  private CallBackListener mBackListener;
  private PostDetailHandler mHandler;
  private SwipeRefreshLayout mLayout;
  private PullToRefreshListView mListView;
  private LoadingView mLoadingView;
  private Post mPost;
  private InputMethodManager manager;
  private final int pageCount = 10;
  private int pageNum;
  private LinearLayout parent;
  private int replyId;
  private String[] replyPara;
  private TextView textImageCount;
  private TextView textTitle;

  private void deleteAllFile()
  {
    for (int i = 0; ; i++)
    {
      if (i >= this.fileList.size())
        return;
      this.mFileTools.deleteFile(((File)this.fileList.get(i)).getName());
    }
  }

  private void getPost()
  {
    String[] arrayOfString = new String[2];
    arrayOfString[0] = this.mSP.getUserId();
    arrayOfString[1] = String.valueOf(this.id);
    UrlFactory.getInstance().getSinglePost().constructUrl(this, arrayOfString, 10, this.mContext);
  }

  private void getPostReply()
  {
    String[] arrayOfString = new String[4];
    arrayOfString[0] = this.mSP.getUserId();
    arrayOfString[1] = String.valueOf(this.id);
    arrayOfString[2] = String.valueOf(this.pageNum);
    arrayOfString[3] = String.valueOf(10);
    if (this.pageNum == 1)
    {
      UrlFactory.getInstance().getPostReply().constructUrl(this, arrayOfString, 12, this.mContext);
      return;
    }
    UrlFactory.getInstance().getPostReply().constructUrl(this, arrayOfString, 13, this.mContext);
  }

  private void initImage()
  {
    if (this.imageList != null)
      this.imageList.clear();
    if (this.layoutChild != null)
      this.layoutChild.removeAllViews();
    setImageCount();
    int i = 0;
    if (i >= 9)
      return;
    AddImageView localAddImageView = new AddImageView(this.mContext);
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(this.hight, this.hight);
    if (i != 8)
      localLayoutParams.setMargins(0, 0, 69 * (this.mSP.getWidth() / 720), 0);
    localAddImageView.setLayoutParams(localLayoutParams);
    localAddImageView.setIndex(i);
    localAddImageView.setmHandler(this.mHandler);
    if (i == 0)
      localAddImageView.setVisibility(0);
    while (true)
    {
      this.imageList.add(localAddImageView);
      this.layoutChild.addView(localAddImageView);
      i++;
      break;
      localAddImageView.setVisibility(8);
    }
  }

  private void initPictureLayout()
  {
    if ((this.hightList.size() == 2) && (!this.isInit) && (((Integer)this.hightList.get(0)).intValue() != 0) && (((Integer)this.hightList.get(1)).intValue() != 0))
    {
      this.hight = (((Integer)this.hightList.get(0)).intValue() - ((Integer)this.hightList.get(1)).intValue());
      if (this.hight <= 0)
        this.hight = (0 - this.hight);
      LayoutParams localLayoutParams = new LayoutParams(-1, this.hight);
      this.layoutPicture.setLayoutParams(localLayoutParams);
      this.hight /= 2;
      initImage();
      this.isInit = true;
    }
  }

  private void setImageCount()
  {
    if ((this.currCount < 0) || (this.currCount > 9))
      return;
    this.textImageCount.setText(getResources().getString(2131165365) + this.currCount + getResources().getString(2131165366) + (9 - this.currCount) + getResources().getString(2131165367));
  }

  private void showKeyboard()
  {
    this.isPraiseState = false;
    this.buttonPicture.setBackgroundResource(2130837591);
    if (this.layoutPicture.getVisibility() == 0)
      this.layoutPicture.setVisibility(8);
    this.manager.toggleSoftInput(0, 2);
  }

  protected void bindData()
  {
    this.mAdapter = new PostDetailAdapter(this.mContext, this.mPost, this.list, this.mHandler);
    ToolsUtils.setList(1, this.mListView, this.mContext);
    this.mListView.setAdapter(this.mAdapter);
    this.edit.setHint(getResources().getString(2131165363) + getResources().getString(2131165362));
    if (this.mSP.getUserId().equals(""))
    {
      this.layoutEdit.setVisibility(8);
      this.layoutLocation.setVisibility(8);
    }
    this.mLoadingView.setVisibility(0);
    this.mListView.setVisibility(8);
    getPost();
  }

  public void finish()
  {
    super.finish();
    deleteAllFile();
  }

  public void finish(int paramInt1, int paramInt2)
  {
    super.finish(paramInt1, paramInt2);
    deleteAllFile();
  }

  protected void initData()
  {
    this.mBackListener = new CallBackListener();
    this.mHandler = new PostDetailHandler();
    this.id = getIntent().getExtras().getInt("id");
    this.isPush = getIntent().getExtras().getBoolean("push");
    if (this.isPush)
      ScreenTools.savePra(this.mContext);
    this.manager = ((InputMethodManager)getSystemService("input_method"));
    this.list = new ArrayList();
    if (this.imageList == null)
    {
      this.imageList = new ArrayList();
      this.fileList = new ArrayList();
      return;
    }
    this.imageList.clear();
    deleteAllFile();
    this.fileList.clear();
  }

  protected void initListener()
  {
    this.buttonBack.setOnClickListener(this.mBackListener);
    this.buttonCollect.setOnClickListener(this.mBackListener);
    this.buttonShare.setOnClickListener(this.mBackListener);
    this.mLayout.setOnRefreshListener(this.mBackListener);
    this.mListView.setOnRefreshListener(this.mBackListener);
    this.mListView.setOnScrollListener(this.mBackListener);
    this.buttonLocation.setOnClickListener(this.mBackListener);
    this.buttonPicture.setOnClickListener(this.mBackListener);
    this.edit.setOnTouchListener(this.mBackListener);
    this.buttonPublish.setOnClickListener(this.mBackListener);
    this.parent.getViewTreeObserver().addOnGlobalLayoutListener(this.mBackListener);
  }

  protected void initView()
  {
    this.parent = ((LinearLayout)findViewById(2131099870));
    this.buttonBack = ((Button)findViewById(2131099871));
    this.textTitle = ((TextView)findViewById(2131099872));
    this.buttonCollect = ((Button)findViewById(2131099873));
    this.buttonShare = ((Button)findViewById(2131099874));
    this.mLayout = ((SwipeRefreshLayout)findViewById(2131099875));
    this.mLoadingView = ((LoadingView)findViewById(2131099877));
    this.mListView = ((PullToRefreshListView)findViewById(2131099876));
    this.layoutLocation = ((LinearLayout)findViewById(2131099878));
    this.buttonLocation = ((Button)findViewById(2131099880));
    this.layoutEdit = ((LinearLayout)findViewById(2131099881));
    this.buttonPicture = ((Button)findViewById(2131099882));
    this.edit = ((EditText)findViewById(2131099883));
    this.buttonPublish = ((Button)findViewById(2131099884));
    this.layoutPicture = ((RelativeLayout)findViewById(2131099885));
    this.layoutChild = ((LinearLayout)findViewById(2131099887));
    this.textImageCount = ((TextView)findViewById(2131099888));
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if (paramInt2 == -1)
      if (paramIntent == null);
    while (true)
    {
      try
      {
        Uri localUri = paramIntent.getData();
        if (localUri == null)
          continue;
        ContentResolver localContentResolver = getContentResolver();
        Options localOptions = ImageTools.getOptions(localContentResolver.openInputStream(localUri));
        ((AddImageView)this.imageList.get(paramInt1)).setImage(BitmapFactory.decodeStream(localContentResolver.openInputStream(localUri), null, localOptions));
        ((AddImageView)this.imageList.get(paramInt1)).setDeleteVisibility(0);
        if (paramInt1 == 8)
          continue;
        ((AddImageView)this.imageList.get(paramInt1 + 1)).setVisibility(0);
        this.currCount = (1 + this.currCount);
        setImageCount();
        if (this.mFileTools.saveBitmapToSDCard(this.file, MediaStore.Images.Media.getBitmap(localContentResolver, localUri)) == null)
          continue;
        this.fileList.add(this.file);
        super.onActivityResult(paramInt1, paramInt2, paramIntent);
        return;
      }
      catch (IOException localIOException)
      {
        localIOException.printStackTrace();
        continue;
      }
      catch (OutOfMemoryError localOutOfMemoryError)
      {
        Toast.makeText(this.mContext, "图片过大，小袋内存不够用啦!", 0).show();
        localOutOfMemoryError.printStackTrace();
        continue;
      }
      if (this.file == null)
        continue;
      this.mFileTools.deleteFile(this.file.getName());
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
    case 12:
      this.mLayout.setRefreshing(false);
      return;
    case 13:
    }
    this.isLoading = false;
    this.mListView.onRefreshComplete();
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

  // ERROR //
  public void onSuccess(String paramString1, java.util.Map<String, String> paramMap, String paramString2, int paramInt)
  {
    // Byte code:
    //   0: aload_0
    //   1: aload_1
    //   2: aload_2
    //   3: aload_3
    //   4: iload 4
    //   6: invokespecial 698	com/withustudy/koudaizikao/base/AbsBaseActivity:onSuccess	(Ljava/lang/String;Ljava/util/Map;Ljava/lang/String;I)V
    //   9: aload_0
    //   10: getfield 229	com/withustudy/koudaizikao/activity/PostDetailActivity:mProTools	Lcom/withustudy/koudaizikao/tools/ProTools;
    //   13: invokevirtual 674	com/withustudy/koudaizikao/tools/ProTools:dismissDislog	()V
    //   16: aload_0
    //   17: getfield 475	com/withustudy/koudaizikao/activity/PostDetailActivity:mLoadingView	Lcom/withustudy/koudaizikao/custom/LoadingView;
    //   20: bipush 8
    //   22: invokevirtual 478	com/withustudy/koudaizikao/custom/LoadingView:setVisibility	(I)V
    //   25: aload_0
    //   26: getfield 446	com/withustudy/koudaizikao/activity/PostDetailActivity:mListView	Lcom/handmark/pulltorefresh/library/PullToRefreshListView;
    //   29: iconst_0
    //   30: invokevirtual 479	com/handmark/pulltorefresh/library/PullToRefreshListView:setVisibility	(I)V
    //   33: iload 4
    //   35: tableswitch	default:+41 -> 76, 10:+42->77, 11:+595->630, 12:+701->736, 13:+799->834, 14:+298->333, 15:+501->536, 16:+893->928
    //   77: aload_1
    //   78: ifnull -2 -> 76
    //   81: invokestatic 702	com/withustudy/koudaizikao/action/UrlFactory:getInstanceGson	()Lcom/google/gson/Gson;
    //   84: aload_1
    //   85: ldc_w 704
    //   88: invokevirtual 710	com/google/gson/Gson:fromJson	(Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object;
    //   91: checkcast 704	com/withustudy/koudaizikao/entity/content/PostSingleContent
    //   94: astore 28
    //   96: aload 28
    //   98: ifnull +220 -> 318
    //   101: aload 28
    //   103: invokevirtual 713	com/withustudy/koudaizikao/entity/content/PostSingleContent:getResult	()Ljava/lang/String;
    //   106: ldc_w 715
    //   109: invokevirtual 470	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   112: ifeq +206 -> 318
    //   115: aload_0
    //   116: aload 28
    //   118: invokevirtual 719	com/withustudy/koudaizikao/entity/content/PostSingleContent:getTopic	()Lcom/withustudy/koudaizikao/entity/Post;
    //   121: putfield 205	com/withustudy/koudaizikao/activity/PostDetailActivity:mPost	Lcom/withustudy/koudaizikao/entity/Post;
    //   124: aload_0
    //   125: iconst_1
    //   126: putfield 234	com/withustudy/koudaizikao/activity/PostDetailActivity:pageNum	I
    //   129: aload_0
    //   130: invokespecial 243	com/withustudy/koudaizikao/activity/PostDetailActivity:getPostReply	()V
    //   133: aload_0
    //   134: getfield 205	com/withustudy/koudaizikao/activity/PostDetailActivity:mPost	Lcom/withustudy/koudaizikao/entity/Post;
    //   137: invokevirtual 724	com/withustudy/koudaizikao/entity/Post:getTopic_title	()Ljava/lang/String;
    //   140: ifnull +33 -> 173
    //   143: aload_0
    //   144: getfield 205	com/withustudy/koudaizikao/activity/PostDetailActivity:mPost	Lcom/withustudy/koudaizikao/entity/Post;
    //   147: invokevirtual 724	com/withustudy/koudaizikao/entity/Post:getTopic_title	()Ljava/lang/String;
    //   150: ldc_w 467
    //   153: invokevirtual 470	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   156: ifne +17 -> 173
    //   159: aload_0
    //   160: getfield 585	com/withustudy/koudaizikao/activity/PostDetailActivity:textTitle	Landroid/widget/TextView;
    //   163: aload_0
    //   164: getfield 205	com/withustudy/koudaizikao/activity/PostDetailActivity:mPost	Lcom/withustudy/koudaizikao/entity/Post;
    //   167: invokevirtual 724	com/withustudy/koudaizikao/entity/Post:getTopic_title	()Ljava/lang/String;
    //   170: invokevirtual 423	android/widget/TextView:setText	(Ljava/lang/CharSequence;)V
    //   173: aload_0
    //   174: getfield 190	com/withustudy/koudaizikao/activity/PostDetailActivity:mSP	Lcom/withustudy/koudaizikao/config/KouDaiSP;
    //   177: invokevirtual 300	com/withustudy/koudaizikao/config/KouDaiSP:getUserId	()Ljava/lang/String;
    //   180: ldc_w 467
    //   183: invokevirtual 470	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   186: ifne +55 -> 241
    //   189: aload_0
    //   190: getfield 205	com/withustudy/koudaizikao/activity/PostDetailActivity:mPost	Lcom/withustudy/koudaizikao/entity/Post;
    //   193: invokevirtual 727	com/withustudy/koudaizikao/entity/Post:getTopoic_isfollow	()I
    //   196: iconst_1
    //   197: if_icmpne +85 -> 282
    //   200: aload_0
    //   201: iconst_1
    //   202: putfield 110	com/withustudy/koudaizikao/activity/PostDetailActivity:isCollect	Z
    //   205: aload_0
    //   206: getfield 201	com/withustudy/koudaizikao/activity/PostDetailActivity:buttonCollect	Landroid/widget/Button;
    //   209: ldc_w 728
    //   212: invokevirtual 429	android/widget/Button:setBackgroundResource	(I)V
    //   215: aload_0
    //   216: getfield 205	com/withustudy/koudaizikao/activity/PostDetailActivity:mPost	Lcom/withustudy/koudaizikao/entity/Post;
    //   219: invokevirtual 731	com/withustudy/koudaizikao/entity/Post:getTopoic_ispraise	()I
    //   222: iconst_1
    //   223: if_icmpne +77 -> 300
    //   226: aload_0
    //   227: iconst_1
    //   228: putfield 112	com/withustudy/koudaizikao/activity/PostDetailActivity:isPraise	Z
    //   231: aload_0
    //   232: getfield 263	com/withustudy/koudaizikao/activity/PostDetailActivity:buttonPicture	Landroid/widget/Button;
    //   235: ldc_w 732
    //   238: invokevirtual 429	android/widget/Button:setBackgroundResource	(I)V
    //   241: aload_0
    //   242: getfield 134	com/withustudy/koudaizikao/activity/PostDetailActivity:mAdapter	Lcom/withustudy/koudaizikao/adapter/PostDetailAdapter;
    //   245: aload_0
    //   246: getfield 205	com/withustudy/koudaizikao/activity/PostDetailActivity:mPost	Lcom/withustudy/koudaizikao/entity/Post;
    //   249: invokevirtual 736	com/withustudy/koudaizikao/adapter/PostDetailAdapter:setmPost	(Lcom/withustudy/koudaizikao/entity/Post;)V
    //   252: aload_0
    //   253: getfield 134	com/withustudy/koudaizikao/activity/PostDetailActivity:mAdapter	Lcom/withustudy/koudaizikao/adapter/PostDetailAdapter;
    //   256: invokevirtual 739	com/withustudy/koudaizikao/adapter/PostDetailAdapter:notifyDataSetChanged	()V
    //   259: return
    //   260: astore 27
    //   262: aload 27
    //   264: invokevirtual 740	java/lang/Exception:printStackTrace	()V
    //   267: aload_0
    //   268: getfield 184	com/withustudy/koudaizikao/activity/PostDetailActivity:mContext	Landroid/content/Context;
    //   271: ldc_w 742
    //   274: iconst_0
    //   275: invokestatic 661	android/widget/Toast:makeText	(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
    //   278: invokevirtual 664	android/widget/Toast:show	()V
    //   281: return
    //   282: aload_0
    //   283: iconst_0
    //   284: putfield 110	com/withustudy/koudaizikao/activity/PostDetailActivity:isCollect	Z
    //   287: aload_0
    //   288: getfield 201	com/withustudy/koudaizikao/activity/PostDetailActivity:buttonCollect	Landroid/widget/Button;
    //   291: ldc_w 743
    //   294: invokevirtual 429	android/widget/Button:setBackgroundResource	(I)V
    //   297: goto -82 -> 215
    //   300: aload_0
    //   301: iconst_0
    //   302: putfield 112	com/withustudy/koudaizikao/activity/PostDetailActivity:isPraise	Z
    //   305: aload_0
    //   306: getfield 263	com/withustudy/koudaizikao/activity/PostDetailActivity:buttonPicture	Landroid/widget/Button;
    //   309: ldc_w 744
    //   312: invokevirtual 429	android/widget/Button:setBackgroundResource	(I)V
    //   315: goto -74 -> 241
    //   318: aload_0
    //   319: getfield 184	com/withustudy/koudaizikao/activity/PostDetailActivity:mContext	Landroid/content/Context;
    //   322: ldc_w 746
    //   325: iconst_0
    //   326: invokestatic 661	android/widget/Toast:makeText	(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
    //   329: invokevirtual 664	android/widget/Toast:show	()V
    //   332: return
    //   333: aload_1
    //   334: ifnull -258 -> 76
    //   337: invokestatic 702	com/withustudy/koudaizikao/action/UrlFactory:getInstanceGson	()Lcom/google/gson/Gson;
    //   340: aload_1
    //   341: ldc_w 748
    //   344: invokevirtual 710	com/google/gson/Gson:fromJson	(Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object;
    //   347: checkcast 748	com/withustudy/koudaizikao/entity/SimpleResult
    //   350: astore 23
    //   352: aload 23
    //   354: ifnull +167 -> 521
    //   357: aload 23
    //   359: invokevirtual 749	com/withustudy/koudaizikao/entity/SimpleResult:getResult	()Ljava/lang/String;
    //   362: ldc_w 715
    //   365: invokevirtual 470	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   368: ifeq +153 -> 521
    //   371: aload_0
    //   372: getfield 112	com/withustudy/koudaizikao/activity/PostDetailActivity:isPraise	Z
    //   375: ifeq +87 -> 462
    //   378: aload_0
    //   379: iconst_0
    //   380: putfield 112	com/withustudy/koudaizikao/activity/PostDetailActivity:isPraise	Z
    //   383: aload_0
    //   384: getfield 205	com/withustudy/koudaizikao/activity/PostDetailActivity:mPost	Lcom/withustudy/koudaizikao/entity/Post;
    //   387: iconst_m1
    //   388: aload_0
    //   389: getfield 205	com/withustudy/koudaizikao/activity/PostDetailActivity:mPost	Lcom/withustudy/koudaizikao/entity/Post;
    //   392: invokevirtual 752	com/withustudy/koudaizikao/entity/Post:getTopic_praise	()I
    //   395: invokestatic 755	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   398: invokevirtual 378	java/lang/Integer:intValue	()I
    //   401: iadd
    //   402: invokevirtual 758	com/withustudy/koudaizikao/entity/Post:setTopic_praise	(I)V
    //   405: aload_0
    //   406: getfield 134	com/withustudy/koudaizikao/activity/PostDetailActivity:mAdapter	Lcom/withustudy/koudaizikao/adapter/PostDetailAdapter;
    //   409: invokevirtual 739	com/withustudy/koudaizikao/adapter/PostDetailAdapter:notifyDataSetChanged	()V
    //   412: new 504	android/content/Intent
    //   415: dup
    //   416: invokespecial 759	android/content/Intent:<init>	()V
    //   419: astore 24
    //   421: new 511	android/os/Bundle
    //   424: dup
    //   425: invokespecial 760	android/os/Bundle:<init>	()V
    //   428: astore 25
    //   430: aload 25
    //   432: ldc_w 762
    //   435: aload_0
    //   436: getfield 205	com/withustudy/koudaizikao/activity/PostDetailActivity:mPost	Lcom/withustudy/koudaizikao/entity/Post;
    //   439: invokevirtual 766	android/os/Bundle:putSerializable	(Ljava/lang/String;Ljava/io/Serializable;)V
    //   442: aload 24
    //   444: ldc_w 768
    //   447: aload 25
    //   449: invokevirtual 772	android/content/Intent:putExtra	(Ljava/lang/String;Landroid/os/Bundle;)Landroid/content/Intent;
    //   452: pop
    //   453: aload_0
    //   454: bipush 24
    //   456: aload 24
    //   458: invokevirtual 776	com/withustudy/koudaizikao/activity/PostDetailActivity:setResult	(ILandroid/content/Intent;)V
    //   461: return
    //   462: aload_0
    //   463: iconst_1
    //   464: putfield 112	com/withustudy/koudaizikao/activity/PostDetailActivity:isPraise	Z
    //   467: aload_0
    //   468: getfield 205	com/withustudy/koudaizikao/activity/PostDetailActivity:mPost	Lcom/withustudy/koudaizikao/entity/Post;
    //   471: iconst_1
    //   472: aload_0
    //   473: getfield 205	com/withustudy/koudaizikao/activity/PostDetailActivity:mPost	Lcom/withustudy/koudaizikao/entity/Post;
    //   476: invokevirtual 752	com/withustudy/koudaizikao/entity/Post:getTopic_praise	()I
    //   479: invokestatic 755	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   482: invokevirtual 378	java/lang/Integer:intValue	()I
    //   485: iadd
    //   486: invokevirtual 758	com/withustudy/koudaizikao/entity/Post:setTopic_praise	(I)V
    //   489: aload_0
    //   490: getfield 134	com/withustudy/koudaizikao/activity/PostDetailActivity:mAdapter	Lcom/withustudy/koudaizikao/adapter/PostDetailAdapter;
    //   493: invokevirtual 739	com/withustudy/koudaizikao/adapter/PostDetailAdapter:notifyDataSetChanged	()V
    //   496: goto -84 -> 412
    //   499: astore 22
    //   501: aload 22
    //   503: invokevirtual 740	java/lang/Exception:printStackTrace	()V
    //   506: aload_0
    //   507: getfield 184	com/withustudy/koudaizikao/activity/PostDetailActivity:mContext	Landroid/content/Context;
    //   510: ldc_w 742
    //   513: iconst_0
    //   514: invokestatic 661	android/widget/Toast:makeText	(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
    //   517: invokevirtual 664	android/widget/Toast:show	()V
    //   520: return
    //   521: aload_0
    //   522: getfield 184	com/withustudy/koudaizikao/activity/PostDetailActivity:mContext	Landroid/content/Context;
    //   525: ldc_w 746
    //   528: iconst_0
    //   529: invokestatic 661	android/widget/Toast:makeText	(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
    //   532: invokevirtual 664	android/widget/Toast:show	()V
    //   535: return
    //   536: aload_1
    //   537: ifnull -461 -> 76
    //   540: invokestatic 702	com/withustudy/koudaizikao/action/UrlFactory:getInstanceGson	()Lcom/google/gson/Gson;
    //   543: aload_1
    //   544: ldc_w 748
    //   547: invokevirtual 710	com/google/gson/Gson:fromJson	(Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object;
    //   550: checkcast 748	com/withustudy/koudaizikao/entity/SimpleResult
    //   553: astore 21
    //   555: aload 21
    //   557: ifnull +58 -> 615
    //   560: aload 21
    //   562: invokevirtual 749	com/withustudy/koudaizikao/entity/SimpleResult:getResult	()Ljava/lang/String;
    //   565: ldc_w 715
    //   568: invokevirtual 470	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   571: ifeq +44 -> 615
    //   574: aload_0
    //   575: getfield 110	com/withustudy/koudaizikao/activity/PostDetailActivity:isCollect	Z
    //   578: ifeq +31 -> 609
    //   581: aload_0
    //   582: iconst_0
    //   583: putfield 110	com/withustudy/koudaizikao/activity/PostDetailActivity:isCollect	Z
    //   586: return
    //   587: astore 20
    //   589: aload 20
    //   591: invokevirtual 740	java/lang/Exception:printStackTrace	()V
    //   594: aload_0
    //   595: getfield 184	com/withustudy/koudaizikao/activity/PostDetailActivity:mContext	Landroid/content/Context;
    //   598: ldc_w 742
    //   601: iconst_0
    //   602: invokestatic 661	android/widget/Toast:makeText	(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
    //   605: invokevirtual 664	android/widget/Toast:show	()V
    //   608: return
    //   609: aload_0
    //   610: iconst_1
    //   611: putfield 110	com/withustudy/koudaizikao/activity/PostDetailActivity:isCollect	Z
    //   614: return
    //   615: aload_0
    //   616: getfield 184	com/withustudy/koudaizikao/activity/PostDetailActivity:mContext	Landroid/content/Context;
    //   619: ldc_w 746
    //   622: iconst_0
    //   623: invokestatic 661	android/widget/Toast:makeText	(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
    //   626: invokevirtual 664	android/widget/Toast:show	()V
    //   629: return
    //   630: aload_1
    //   631: ifnull -555 -> 76
    //   634: invokestatic 702	com/withustudy/koudaizikao/action/UrlFactory:getInstanceGson	()Lcom/google/gson/Gson;
    //   637: aload_1
    //   638: ldc_w 748
    //   641: invokevirtual 710	com/google/gson/Gson:fromJson	(Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object;
    //   644: checkcast 748	com/withustudy/koudaizikao/entity/SimpleResult
    //   647: astore 19
    //   649: aload 19
    //   651: ifnull +70 -> 721
    //   654: aload 19
    //   656: invokevirtual 749	com/withustudy/koudaizikao/entity/SimpleResult:getResult	()Ljava/lang/String;
    //   659: ldc_w 715
    //   662: invokevirtual 470	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   665: ifeq +56 -> 721
    //   668: aload_0
    //   669: getfield 184	com/withustudy/koudaizikao/activity/PostDetailActivity:mContext	Landroid/content/Context;
    //   672: ldc_w 778
    //   675: iconst_0
    //   676: invokestatic 661	android/widget/Toast:makeText	(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
    //   679: invokevirtual 664	android/widget/Toast:show	()V
    //   682: aload_0
    //   683: bipush 21
    //   685: invokevirtual 780	com/withustudy/koudaizikao/activity/PostDetailActivity:setResult	(I)V
    //   688: aload_0
    //   689: ldc_w 781
    //   692: ldc_w 782
    //   695: invokevirtual 783	com/withustudy/koudaizikao/activity/PostDetailActivity:finish	(II)V
    //   698: return
    //   699: astore 18
    //   701: aload 18
    //   703: invokevirtual 740	java/lang/Exception:printStackTrace	()V
    //   706: aload_0
    //   707: getfield 184	com/withustudy/koudaizikao/activity/PostDetailActivity:mContext	Landroid/content/Context;
    //   710: ldc_w 742
    //   713: iconst_0
    //   714: invokestatic 661	android/widget/Toast:makeText	(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
    //   717: invokevirtual 664	android/widget/Toast:show	()V
    //   720: return
    //   721: aload_0
    //   722: getfield 184	com/withustudy/koudaizikao/activity/PostDetailActivity:mContext	Landroid/content/Context;
    //   725: ldc_w 746
    //   728: iconst_0
    //   729: invokestatic 661	android/widget/Toast:makeText	(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
    //   732: invokevirtual 664	android/widget/Toast:show	()V
    //   735: return
    //   736: aload_0
    //   737: getfield 446	com/withustudy/koudaizikao/activity/PostDetailActivity:mListView	Lcom/handmark/pulltorefresh/library/PullToRefreshListView;
    //   740: iconst_0
    //   741: invokevirtual 479	com/handmark/pulltorefresh/library/PullToRefreshListView:setVisibility	(I)V
    //   744: aload_0
    //   745: getfield 247	com/withustudy/koudaizikao/activity/PostDetailActivity:mLayout	Landroid/support/v4/widget/SwipeRefreshLayout;
    //   748: iconst_0
    //   749: invokevirtual 678	android/support/v4/widget/SwipeRefreshLayout:setRefreshing	(Z)V
    //   752: aload_1
    //   753: ifnull -677 -> 76
    //   756: invokestatic 702	com/withustudy/koudaizikao/action/UrlFactory:getInstanceGson	()Lcom/google/gson/Gson;
    //   759: aload_1
    //   760: ldc_w 785
    //   763: invokevirtual 710	com/google/gson/Gson:fromJson	(Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object;
    //   766: checkcast 785	com/withustudy/koudaizikao/entity/content/ReplyContent
    //   769: astore 16
    //   771: aload 16
    //   773: ifnull -697 -> 76
    //   776: aload 16
    //   778: invokevirtual 786	com/withustudy/koudaizikao/entity/content/ReplyContent:getResult	()Ljava/lang/String;
    //   781: ldc_w 715
    //   784: invokevirtual 470	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   787: ifeq -711 -> 76
    //   790: aload_0
    //   791: getfield 223	com/withustudy/koudaizikao/activity/PostDetailActivity:mHandler	Lcom/withustudy/koudaizikao/activity/PostDetailActivity$PostDetailHandler;
    //   794: aload_0
    //   795: getfield 223	com/withustudy/koudaizikao/activity/PostDetailActivity:mHandler	Lcom/withustudy/koudaizikao/activity/PostDetailActivity$PostDetailHandler;
    //   798: iconst_1
    //   799: aload 16
    //   801: invokevirtual 790	com/withustudy/koudaizikao/entity/content/ReplyContent:getPosts	()Ljava/util/List;
    //   804: invokevirtual 794	com/withustudy/koudaizikao/activity/PostDetailActivity$PostDetailHandler:obtainMessage	(ILjava/lang/Object;)Landroid/os/Message;
    //   807: invokevirtual 798	com/withustudy/koudaizikao/activity/PostDetailActivity$PostDetailHandler:sendMessage	(Landroid/os/Message;)Z
    //   810: pop
    //   811: return
    //   812: astore 15
    //   814: aload 15
    //   816: invokevirtual 740	java/lang/Exception:printStackTrace	()V
    //   819: aload_0
    //   820: getfield 184	com/withustudy/koudaizikao/activity/PostDetailActivity:mContext	Landroid/content/Context;
    //   823: ldc_w 742
    //   826: iconst_0
    //   827: invokestatic 661	android/widget/Toast:makeText	(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
    //   830: invokevirtual 664	android/widget/Toast:show	()V
    //   833: return
    //   834: aload_0
    //   835: iconst_0
    //   836: putfield 114	com/withustudy/koudaizikao/activity/PostDetailActivity:isLoading	Z
    //   839: aload_0
    //   840: getfield 446	com/withustudy/koudaizikao/activity/PostDetailActivity:mListView	Lcom/handmark/pulltorefresh/library/PullToRefreshListView;
    //   843: invokevirtual 681	com/handmark/pulltorefresh/library/PullToRefreshListView:onRefreshComplete	()V
    //   846: aload_1
    //   847: ifnull -771 -> 76
    //   850: invokestatic 702	com/withustudy/koudaizikao/action/UrlFactory:getInstanceGson	()Lcom/google/gson/Gson;
    //   853: aload_1
    //   854: ldc_w 785
    //   857: invokevirtual 710	com/google/gson/Gson:fromJson	(Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object;
    //   860: checkcast 785	com/withustudy/koudaizikao/entity/content/ReplyContent
    //   863: astore 13
    //   865: aload 13
    //   867: ifnull -791 -> 76
    //   870: aload 13
    //   872: invokevirtual 786	com/withustudy/koudaizikao/entity/content/ReplyContent:getResult	()Ljava/lang/String;
    //   875: ldc_w 715
    //   878: invokevirtual 470	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   881: ifeq -805 -> 76
    //   884: aload_0
    //   885: getfield 223	com/withustudy/koudaizikao/activity/PostDetailActivity:mHandler	Lcom/withustudy/koudaizikao/activity/PostDetailActivity$PostDetailHandler;
    //   888: aload_0
    //   889: getfield 223	com/withustudy/koudaizikao/activity/PostDetailActivity:mHandler	Lcom/withustudy/koudaizikao/activity/PostDetailActivity$PostDetailHandler;
    //   892: iconst_2
    //   893: aload 13
    //   895: invokevirtual 790	com/withustudy/koudaizikao/entity/content/ReplyContent:getPosts	()Ljava/util/List;
    //   898: invokevirtual 794	com/withustudy/koudaizikao/activity/PostDetailActivity$PostDetailHandler:obtainMessage	(ILjava/lang/Object;)Landroid/os/Message;
    //   901: invokevirtual 798	com/withustudy/koudaizikao/activity/PostDetailActivity$PostDetailHandler:sendMessage	(Landroid/os/Message;)Z
    //   904: pop
    //   905: return
    //   906: astore 12
    //   908: aload 12
    //   910: invokevirtual 740	java/lang/Exception:printStackTrace	()V
    //   913: aload_0
    //   914: getfield 184	com/withustudy/koudaizikao/activity/PostDetailActivity:mContext	Landroid/content/Context;
    //   917: ldc_w 742
    //   920: iconst_0
    //   921: invokestatic 661	android/widget/Toast:makeText	(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
    //   924: invokevirtual 664	android/widget/Toast:show	()V
    //   927: return
    //   928: aload_1
    //   929: ifnull -853 -> 76
    //   932: invokestatic 702	com/withustudy/koudaizikao/action/UrlFactory:getInstanceGson	()Lcom/google/gson/Gson;
    //   935: aload_1
    //   936: ldc_w 800
    //   939: invokevirtual 710	com/google/gson/Gson:fromJson	(Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object;
    //   942: checkcast 800	com/withustudy/koudaizikao/entity/content/PublishPostReplyResult
    //   945: astore 6
    //   947: aload 6
    //   949: ifnull +172 -> 1121
    //   952: aload 6
    //   954: invokevirtual 801	com/withustudy/koudaizikao/entity/content/PublishPostReplyResult:getResult	()Ljava/lang/String;
    //   957: ldc_w 715
    //   960: invokevirtual 470	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   963: ifeq +158 -> 1121
    //   966: aload_0
    //   967: getfield 184	com/withustudy/koudaizikao/activity/PostDetailActivity:mContext	Landroid/content/Context;
    //   970: ldc_w 803
    //   973: iconst_0
    //   974: invokestatic 661	android/widget/Toast:makeText	(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
    //   977: invokevirtual 664	android/widget/Toast:show	()V
    //   980: aload_0
    //   981: getfield 205	com/withustudy/koudaizikao/activity/PostDetailActivity:mPost	Lcom/withustudy/koudaizikao/entity/Post;
    //   984: iconst_1
    //   985: aload_0
    //   986: getfield 205	com/withustudy/koudaizikao/activity/PostDetailActivity:mPost	Lcom/withustudy/koudaizikao/entity/Post;
    //   989: invokevirtual 806	com/withustudy/koudaizikao/entity/Post:getTopic_replies	()I
    //   992: invokestatic 755	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   995: invokevirtual 378	java/lang/Integer:intValue	()I
    //   998: iadd
    //   999: invokevirtual 809	com/withustudy/koudaizikao/entity/Post:setTopic_replies	(I)V
    //   1002: aload_0
    //   1003: getfield 134	com/withustudy/koudaizikao/activity/PostDetailActivity:mAdapter	Lcom/withustudy/koudaizikao/adapter/PostDetailAdapter;
    //   1006: invokevirtual 739	com/withustudy/koudaizikao/adapter/PostDetailAdapter:notifyDataSetChanged	()V
    //   1009: aload_0
    //   1010: invokespecial 486	com/withustudy/koudaizikao/activity/PostDetailActivity:deleteAllFile	()V
    //   1013: aload_0
    //   1014: getfield 142	com/withustudy/koudaizikao/activity/PostDetailActivity:fileList	Ljava/util/List;
    //   1017: invokeinterface 326 1 0
    //   1022: aload_0
    //   1023: iconst_0
    //   1024: putfield 155	com/withustudy/koudaizikao/activity/PostDetailActivity:currCount	I
    //   1027: aload_0
    //   1028: invokespecial 386	com/withustudy/koudaizikao/activity/PostDetailActivity:initImage	()V
    //   1031: aload_0
    //   1032: getfield 260	com/withustudy/koudaizikao/activity/PostDetailActivity:layoutPicture	Landroid/widget/RelativeLayout;
    //   1035: bipush 8
    //   1037: invokevirtual 433	android/widget/RelativeLayout:setVisibility	(I)V
    //   1040: aload_0
    //   1041: getfield 223	com/withustudy/koudaizikao/activity/PostDetailActivity:mHandler	Lcom/withustudy/koudaizikao/activity/PostDetailActivity$PostDetailHandler;
    //   1044: iconst_3
    //   1045: invokevirtual 813	com/withustudy/koudaizikao/activity/PostDetailActivity$PostDetailHandler:sendEmptyMessage	(I)Z
    //   1048: pop
    //   1049: aload_0
    //   1050: getfield 223	com/withustudy/koudaizikao/activity/PostDetailActivity:mHandler	Lcom/withustudy/koudaizikao/activity/PostDetailActivity$PostDetailHandler;
    //   1053: aload_0
    //   1054: getfield 223	com/withustudy/koudaizikao/activity/PostDetailActivity:mHandler	Lcom/withustudy/koudaizikao/activity/PostDetailActivity$PostDetailHandler;
    //   1057: bipush 9
    //   1059: aload 6
    //   1061: invokevirtual 816	com/withustudy/koudaizikao/entity/content/PublishPostReplyResult:getPost	()Lcom/withustudy/koudaizikao/entity/PostReply;
    //   1064: invokevirtual 794	com/withustudy/koudaizikao/activity/PostDetailActivity$PostDetailHandler:obtainMessage	(ILjava/lang/Object;)Landroid/os/Message;
    //   1067: invokevirtual 798	com/withustudy/koudaizikao/activity/PostDetailActivity$PostDetailHandler:sendMessage	(Landroid/os/Message;)Z
    //   1070: pop
    //   1071: new 504	android/content/Intent
    //   1074: dup
    //   1075: invokespecial 759	android/content/Intent:<init>	()V
    //   1078: astore 9
    //   1080: new 511	android/os/Bundle
    //   1083: dup
    //   1084: invokespecial 760	android/os/Bundle:<init>	()V
    //   1087: astore 10
    //   1089: aload 10
    //   1091: ldc_w 762
    //   1094: aload_0
    //   1095: getfield 205	com/withustudy/koudaizikao/activity/PostDetailActivity:mPost	Lcom/withustudy/koudaizikao/entity/Post;
    //   1098: invokevirtual 766	android/os/Bundle:putSerializable	(Ljava/lang/String;Ljava/io/Serializable;)V
    //   1101: aload 9
    //   1103: ldc_w 768
    //   1106: aload 10
    //   1108: invokevirtual 772	android/content/Intent:putExtra	(Ljava/lang/String;Landroid/os/Bundle;)Landroid/content/Intent;
    //   1111: pop
    //   1112: aload_0
    //   1113: bipush 24
    //   1115: aload 9
    //   1117: invokevirtual 776	com/withustudy/koudaizikao/activity/PostDetailActivity:setResult	(ILandroid/content/Intent;)V
    //   1120: return
    //   1121: aload_0
    //   1122: getfield 184	com/withustudy/koudaizikao/activity/PostDetailActivity:mContext	Landroid/content/Context;
    //   1125: ldc_w 746
    //   1128: iconst_0
    //   1129: invokestatic 661	android/widget/Toast:makeText	(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
    //   1132: invokevirtual 664	android/widget/Toast:show	()V
    //   1135: return
    //   1136: astore 5
    //   1138: aload 5
    //   1140: invokevirtual 740	java/lang/Exception:printStackTrace	()V
    //   1143: aload_0
    //   1144: getfield 184	com/withustudy/koudaizikao/activity/PostDetailActivity:mContext	Landroid/content/Context;
    //   1147: ldc_w 742
    //   1150: iconst_0
    //   1151: invokestatic 661	android/widget/Toast:makeText	(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
    //   1154: invokevirtual 664	android/widget/Toast:show	()V
    //   1157: return
    //   1158: astore 5
    //   1160: goto -22 -> 1138
    //   1163: astore 5
    //   1165: goto -27 -> 1138
    //   1168: astore 22
    //   1170: goto -669 -> 501
    //   1173: astore 22
    //   1175: goto -674 -> 501
    //
    // Exception table:
    //   from	to	target	type
    //   81	96	260	java/lang/Exception
    //   101	173	260	java/lang/Exception
    //   173	215	260	java/lang/Exception
    //   215	241	260	java/lang/Exception
    //   241	259	260	java/lang/Exception
    //   282	297	260	java/lang/Exception
    //   300	315	260	java/lang/Exception
    //   318	332	260	java/lang/Exception
    //   337	352	499	java/lang/Exception
    //   357	412	499	java/lang/Exception
    //   412	421	499	java/lang/Exception
    //   462	496	499	java/lang/Exception
    //   521	535	499	java/lang/Exception
    //   540	555	587	java/lang/Exception
    //   560	586	587	java/lang/Exception
    //   609	614	587	java/lang/Exception
    //   615	629	587	java/lang/Exception
    //   634	649	699	java/lang/Exception
    //   654	698	699	java/lang/Exception
    //   721	735	699	java/lang/Exception
    //   756	771	812	java/lang/Exception
    //   776	811	812	java/lang/Exception
    //   850	865	906	java/lang/Exception
    //   870	905	906	java/lang/Exception
    //   932	947	1136	java/lang/Exception
    //   952	1080	1136	java/lang/Exception
    //   1121	1135	1136	java/lang/Exception
    //   1080	1089	1158	java/lang/Exception
    //   1089	1120	1163	java/lang/Exception
    //   421	430	1168	java/lang/Exception
    //   430	461	1173	java/lang/Exception
  }

  protected void setContentView()
  {
    setContentView(2130903066);
  }

  class CallBackListener
    implements OnGlobalLayoutListener, OnScrollListener, OnClickListener, OnRefreshListener, PullToRefreshBase.OnRefreshListener<ListView>, OnTouchListener
  {
    CallBackListener()
    {
    }

    public void onClick(View paramView)
    {
      switch (paramView.getId())
      {
      case 2131099872:
      case 2131099875:
      case 2131099876:
      case 2131099877:
      case 2131099878:
      case 2131099879:
      case 2131099881:
      case 2131099883:
      default:
      case 2131099871:
      case 2131099873:
      case 2131099874:
      case 2131099880:
      case 2131099882:
        do
        {
          do
          {
            return;
            PostDetailActivity.this.finish();
          }
          while (!PostDetailActivity.this.isPush);
          PostDetailActivity.this.startNewActivity(WelcomeActivity.class, false, null);
          return;
          if (PostDetailActivity.this.mSP.getUserId().equals(""))
          {
            Toast.makeText(PostDetailActivity.this.mContext, "请先登录", 0).show();
            return;
          }
          String[] arrayOfString2 = new String[3];
          arrayOfString2[0] = PostDetailActivity.access$22(PostDetailActivity.this).getUserId();
          arrayOfString2[1] = String.valueOf(PostDetailActivity.access$23(PostDetailActivity.this));
          if (PostDetailActivity.this.isCollect)
          {
            arrayOfString2[2] = "unfollow";
            PostDetailActivity.this.buttonCollect.setBackgroundResource(2130837597);
            UrlFactory.getInstance().collectPost().constructUrl(PostDetailActivity.this, arrayOfString2, 15, PostDetailActivity.this.mContext);
            return;
          }
          MobclickAgent.onEvent(PostDetailActivity.this.mContext, "bbs_collect");
          Toast.makeText(PostDetailActivity.this.mContext, "收藏成功", 0).show();
          arrayOfString2[2] = "follow";
          PostDetailActivity.this.buttonCollect.setBackgroundResource(2130837586);
          UrlFactory.getInstance().collectPost().constructUrl(PostDetailActivity.this, arrayOfString2, 15, PostDetailActivity.this.mContext);
          return;
          MobclickAgent.onEvent(PostDetailActivity.this.mContext, "bbs_share");
          new SharePopWindow(PostDetailActivity.this, PostDetailActivity.this.edit, "http://share.kdzikao.com/bbs/share.page?topic_id=" + PostDetailActivity.this.id, PostDetailActivity.this.mPost.getTopic_title(), PostDetailActivity.this.mPost.getTopic_sub_desc()).showPop();
          return;
          PostDetailActivity.this.layoutLocation.setVisibility(8);
          PostDetailActivity.this.isLocation = false;
          return;
          if (PostDetailActivity.this.isPraiseState)
          {
            String[] arrayOfString1 = new String[3];
            arrayOfString1[0] = PostDetailActivity.access$22(PostDetailActivity.this).getUserId();
            arrayOfString1[1] = String.valueOf(PostDetailActivity.access$23(PostDetailActivity.this));
            if (PostDetailActivity.this.isPraise)
            {
              arrayOfString1[2] = "cancel";
              PostDetailActivity.this.buttonPicture.setBackgroundResource(2130837624);
              UrlFactory.getInstance().praisePost().constructUrl(PostDetailActivity.this, arrayOfString1, 14, PostDetailActivity.this.mContext);
              return;
            }
            arrayOfString1[2] = "add";
            PostDetailActivity.this.buttonPicture.setBackgroundResource(2130837592);
            UrlFactory.getInstance().praisePost().constructUrl(PostDetailActivity.this, arrayOfString1, 14, PostDetailActivity.this.mContext);
            return;
          }
          PostDetailActivity.this.initPictureLayout();
          if (PostDetailActivity.this.layoutPicture.getVisibility() != 0)
            continue;
          PostDetailActivity.this.layoutPicture.setVisibility(8);
          PostDetailActivity.this.manager.toggleSoftInput(0, 2);
          return;
        }
        while (PostDetailActivity.this.layoutPicture.getVisibility() != 8);
        PostDetailActivity.this.manager.hideSoftInputFromWindow(PostDetailActivity.this.getCurrentFocus().getWindowToken(), 2);
        PostDetailActivity.this.layoutPicture.setVisibility(0);
        return;
      case 2131099884:
      }
      if (PostDetailActivity.this.edit.getText().toString().equals(""))
      {
        Toast.makeText(PostDetailActivity.this.mContext, "回复内容不能为空", 0).show();
        return;
      }
      PostDetailActivity.this.replyPara = new String[5];
      if (PostDetailActivity.this.isReplyPost)
      {
        MobclickAgent.onEvent(PostDetailActivity.this.mContext, "bbs_publish");
        PostDetailActivity.this.replyPara[0] = String.valueOf(PostDetailActivity.access$23(PostDetailActivity.this));
        PostDetailActivity.this.replyPara[1] = "0";
        PostDetailActivity.this.replyPara[2] = PostDetailActivity.access$22(PostDetailActivity.this).getUserId();
        PostDetailActivity.this.replyPara[3] = URLEncoder.encode(PostDetailActivity.access$7(PostDetailActivity.this).getText().toString());
        PostDetailActivity.this.replyPara[4] = "";
        if (PostDetailActivity.this.fileList.size() == 0)
          break label895;
        new FileUpload(PostDetailActivity.this.fileList, new String[] { "bbs" }, PostDetailActivity.this.mHandler).start();
      }
      while (true)
      {
        PostDetailActivity.this.mProTools.startDialog("努力发表中");
        return;
        MobclickAgent.onEvent(PostDetailActivity.this.mContext, "bbs_reply");
        PostDetailActivity.this.replyPara[0] = String.valueOf(PostDetailActivity.access$34(PostDetailActivity.this));
        PostDetailActivity.this.replyPara[1] = "1";
        break;
        label895: UrlFactory.getInstance().AddPostReply().constructUrl(PostDetailActivity.this, PostDetailActivity.this.replyPara, 16, PostDetailActivity.this.mContext);
      }
    }

    public void onGlobalLayout()
    {
      Rect localRect = new Rect();
      PostDetailActivity.this.parent.getWindowVisibleDisplayFrame(localRect);
      int i = PostDetailActivity.this.mSP.getHeight() - (localRect.bottom - localRect.top);
      if (PostDetailActivity.this.hightList == null)
      {
        PostDetailActivity.this.hightList = new ArrayList();
        PostDetailActivity.this.hightList.add(Integer.valueOf(i));
      }
      do
        return;
      while (PostDetailActivity.this.hightList.contains(Integer.valueOf(i)));
      PostDetailActivity.this.hightList.add(Integer.valueOf(i));
    }

    public void onRefresh()
    {
      PostDetailActivity.this.mLayout.setRefreshing(true);
      PostDetailActivity.this.pageNum = 1;
      PostDetailActivity.this.getPostReply();
    }

    public void onRefresh(PullToRefreshBase<ListView> paramPullToRefreshBase)
    {
      if (!PostDetailActivity.this.isLoading)
      {
        PostDetailActivity.this.isLoading = true;
        PostDetailActivity localPostDetailActivity = PostDetailActivity.this;
        localPostDetailActivity.pageNum = (1 + localPostDetailActivity.pageNum);
        PostDetailActivity.this.getPostReply();
      }
    }

    public void onScroll(AbsListView paramAbsListView, int paramInt1, int paramInt2, int paramInt3)
    {
      if (paramInt1 == 0)
      {
        PostDetailActivity.this.mLayout.setEnabled(true);
        return;
      }
      PostDetailActivity.this.mLayout.setEnabled(false);
    }

    public void onScrollStateChanged(AbsListView paramAbsListView, int paramInt)
    {
      PostDetailActivity.this.mHandler.sendEmptyMessage(3);
    }

    public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
    {
      switch (paramView.getId())
      {
      default:
      case 2131099883:
      }
      do
      {
        return false;
        PostDetailActivity.this.isPraiseState = false;
        PostDetailActivity.this.buttonPicture.setBackgroundResource(2130837591);
      }
      while (PostDetailActivity.this.layoutPicture.getVisibility() != 0);
      PostDetailActivity.this.layoutPicture.setVisibility(8);
      return false;
    }
  }

  public class PostDetailHandler extends Handler
  {
    public PostDetailHandler()
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
      case 4:
      case 5:
      case 6:
      case 7:
      case 8:
      case 9:
      }
      do
      {
        return;
        PostDetailActivity.this.list.clear();
        PostDetailActivity.this.list.addAll((List)paramMessage.obj);
        PostDetailActivity.this.mAdapter.notifyDataSetChanged();
        return;
        PostDetailActivity.this.list.addAll((List)paramMessage.obj);
        PostDetailActivity.this.mAdapter.notifyDataSetChanged();
        return;
        PostDetailActivity.this.manager.hideSoftInputFromWindow(PostDetailActivity.this.getCurrentFocus().getWindowToken(), 2);
        PostDetailActivity.this.isPraiseState = true;
        PostDetailActivity.this.layoutLocation.setVisibility(8);
        PostDetailActivity.this.layoutPicture.setVisibility(8);
        PostDetailActivity.this.buttonPicture.setBackgroundResource(2130837624);
        PostDetailActivity.this.edit.setText("");
        PostDetailActivity.this.edit.setHint(PostDetailActivity.this.getResources().getString(2131165363) + PostDetailActivity.this.getResources().getString(2131165362));
        PostDetailActivity.this.isReplyPost = true;
        return;
        PostDetailActivity.this.edit.setText("");
        PostDetailActivity.this.edit.setHint(PostDetailActivity.this.getResources().getString(2131165363) + (1 + ((Integer)paramMessage.obj).intValue()) + "楼");
        PostDetailActivity.this.isReplyPost = false;
        PostDetailActivity.this.replyId = ((PostReply)PostDetailActivity.this.list.get(((Integer)paramMessage.obj).intValue())).getPost_id();
        PostDetailActivity.this.showKeyboard();
        return;
        int j = ((Integer)paramMessage.obj).intValue();
        int k = j;
        int m = -1 + PostDetailActivity.this.fileList.size();
        if (k >= PostDetailActivity.this.imageList.size())
        {
          PostDetailActivity.this.currCount = j;
          PostDetailActivity.this.setImageCount();
          return;
        }
        if (m >= j)
        {
          PostDetailActivity.this.mFileTools.deleteFile(((File)PostDetailActivity.this.fileList.get(m)).getName());
          PostDetailActivity.this.fileList.remove(m);
          m--;
        }
        if (k == j)
          ((AddImageView)PostDetailActivity.this.imageList.get(k)).removeView(false);
        while (true)
        {
          k++;
          break;
          ((AddImageView)PostDetailActivity.this.imageList.get(k)).removeView(true);
        }
        PostDetailActivity.this.file = PostDetailActivity.this.mFileTools.createFile(System.currentTimeMillis() + ".JPEG");
        LogUtils.myLog(PostDetailActivity.this.file.getAbsolutePath());
        Intent localIntent = new Intent("android.intent.action.GET_CONTENT");
        localIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        PostDetailActivity.this.startActivityForResult(localIntent, ((Integer)paramMessage.obj).intValue());
        return;
        PostDetailActivity.this.fileName = ((String[])paramMessage.obj);
        int i = 0;
        if (i >= PostDetailActivity.this.fileName.length)
        {
          UrlFactory.getInstance().AddPostReply().constructUrl(PostDetailActivity.this, PostDetailActivity.this.replyPara, 16, PostDetailActivity.this.mContext);
          return;
        }
        if (i != -1 + PostDetailActivity.this.fileName.length)
          PostDetailActivity.this.replyPara[4] = (PostDetailActivity.this.replyPara[4] + PostDetailActivity.access$19(PostDetailActivity.this)[i] + ",");
        while (true)
        {
          i++;
          break;
          PostDetailActivity.this.replyPara[4] = (PostDetailActivity.this.replyPara[4] + PostDetailActivity.access$19(PostDetailActivity.this)[i]);
        }
        String[] arrayOfString = new String[2];
        arrayOfString[0] = PostDetailActivity.access$22(PostDetailActivity.this).getUserId();
        arrayOfString[1] = String.valueOf(PostDetailActivity.access$23(PostDetailActivity.this));
        UrlFactory.getInstance().deletePost().constructUrl(PostDetailActivity.this, arrayOfString, 11, PostDetailActivity.this.mContext);
        return;
      }
      while ((PostDetailActivity.this.list.size() % 10 == 0) && (PostDetailActivity.this.list.size() != 0));
      PostDetailActivity.this.list.add((PostReply)paramMessage.obj);
      PostDetailActivity.this.mAdapter.notifyDataSetChanged();
    }
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.activity.PostDetailActivity
 * JD-Core Version:    0.6.0
 */