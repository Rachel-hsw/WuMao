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
import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import com.android.http.FileUpload;
import com.google.gson.Gson;
import com.withustudy.koudaizikao.action.AbsBaseUrlConstructor;
import com.withustudy.koudaizikao.action.UrlFactory;
import com.withustudy.koudaizikao.base.AbsBaseActivity;
import com.withustudy.koudaizikao.config.KouDaiSP;
import com.withustudy.koudaizikao.custom.AddImageView;
import com.withustudy.koudaizikao.entity.SimpleResult;
import com.withustudy.koudaizikao.tools.FileTools;
import com.withustudy.koudaizikao.tools.ImageTools;
import com.withustudy.koudaizikao.tools.LogUtils;
import com.withustudy.koudaizikao.tools.ProTools;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AddPostActivity extends AbsBaseActivity
{
  public static final int ADD_FINISH = 1;
  public static final int GET_IMAGE = 3;
  public static final int MAX_IMAGE_COUNT = 9;
  public static final int REMOVE_VIEW = 2;
  public static final int SHOW_KEYBOARD = 4;
  public static final int UPLOAD_FINISH = 5;
  private Button buttonLocation;
  private Button buttonPicture;
  private Button buttonPublish;
  private int currCount = 0;
  private EditText editContent;
  private EditText editTitle;
  private File file;
  private List<File> fileList;
  private String[] fileName;
  private List<Integer> hightList;
  private int id;
  private List<AddImageView> imageList;
  private boolean isInit = false;
  private LinearLayout layoutChild;
  private LinearLayout layoutLocation;
  private LinearLayout layoutMain;
  private RelativeLayout layoutPicture;
  private CallBackListener mBackListener;
  private AddPostHandler mHandler;
  private InputMethodManager manager;
  private String[] param;
  private TextView textBack;
  private TextView textImageCount;
  private TextView textLocation;

  private void deleteAllFile()
  {
    for (int i = 0; ; i++)
    {
      if (i >= this.fileList.size())
        return;
      this.mFileTools.deleteFile(((File)this.fileList.get(i)).getName());
    }
  }

  private void initImage(int paramInt)
  {
    setImageCount();
    int i = 0;
    if (i >= 9)
      return;
    AddImageView localAddImageView = new AddImageView(this.mContext);
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(paramInt, paramInt);
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
    if ((this.hightList != null) && (this.hightList.size() == 2) && (!this.isInit) && (((Integer)this.hightList.get(0)).intValue() != 0) && (((Integer)this.hightList.get(1)).intValue() != 0))
    {
      int i = ((Integer)this.hightList.get(0)).intValue() - ((Integer)this.hightList.get(1)).intValue();
      if (i <= 0)
        i = 0 - i;
      LayoutParams localLayoutParams = new LayoutParams(-1, i);
      this.layoutPicture.setLayoutParams(localLayoutParams);
      this.layoutPicture.setVisibility(0);
      getWindow().setSoftInputMode(48);
      initImage(i / 2);
      this.isInit = true;
    }
  }

  private void setImageCount()
  {
    if ((this.currCount < 0) || (this.currCount > 9))
      return;
    this.textImageCount.setText(getResources().getString(2131165365) + this.currCount + getResources().getString(2131165366) + (9 - this.currCount) + getResources().getString(2131165367));
  }

  protected void bindData()
  {
    this.editTitle.requestFocus();
    this.manager.toggleSoftInput(0, 2);
    this.mHandler.sendMessageDelayed(this.mHandler.obtainMessage(4), 100L);
    this.textLocation.setText("北京市清河");
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
    this.mHandler = new AddPostHandler();
    this.manager = ((InputMethodManager)getSystemService("input_method"));
    this.id = getIntent().getExtras().getInt("id");
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
    this.textBack.setOnClickListener(this.mBackListener);
    this.layoutMain.getViewTreeObserver().addOnGlobalLayoutListener(this.mBackListener);
    this.buttonPicture.setOnClickListener(this.mBackListener);
    this.buttonLocation.setOnClickListener(this.mBackListener);
    this.buttonPublish.setOnClickListener(this.mBackListener);
  }

  protected void initView()
  {
    this.layoutMain = ((LinearLayout)findViewById(2131099688));
    this.textBack = ((TextView)findViewById(2131099689));
    this.buttonPublish = ((Button)findViewById(2131099690));
    this.editTitle = ((EditText)findViewById(2131099691));
    this.editContent = ((EditText)findViewById(2131099692));
    this.layoutLocation = ((LinearLayout)findViewById(2131099693));
    this.textLocation = ((TextView)findViewById(2131099694));
    this.buttonLocation = ((Button)findViewById(2131099695));
    this.buttonPicture = ((Button)findViewById(2131099696));
    this.layoutPicture = ((RelativeLayout)findViewById(2131099697));
    this.layoutChild = ((LinearLayout)findViewById(2131099699));
    this.textImageCount = ((TextView)findViewById(2131099700));
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
    Toast.makeText(this.mContext, "发送失败，请稍后再试", 0).show();
  }

  public void onSuccess(String paramString1, Map<String, String> paramMap, String paramString2, int paramInt)
  {
    super.onSuccess(paramString1, paramMap, paramString2, paramInt);
    this.mProTools.dismissDislog();
    if (paramString1 != null)
      try
      {
        SimpleResult localSimpleResult = (SimpleResult)UrlFactory.getInstanceGson().fromJson(paramString1, SimpleResult.class);
        if ((localSimpleResult != null) && (localSimpleResult.getResult().equals("true")))
        {
          this.mHandler.sendEmptyMessage(1);
          return;
        }
        Toast.makeText(this.mContext, "服务器未能返回数据，请稍后再试", 0).show();
        return;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        Toast.makeText(this.mContext, "服务器未响应，请稍后再试", 0).show();
      }
  }

  protected void setContentView()
  {
    setContentView(2130903041);
  }

  public class AddPostHandler extends Handler
  {
    public AddPostHandler()
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
        AddPostActivity.this.deleteAllFile();
        AddPostActivity.this.setResult(23);
        AddPostActivity.this.finish(2130968578, 2130968582);
        return;
      case 2:
        int j = ((Integer)paramMessage.obj).intValue();
        int k = j;
        int m = -1 + AddPostActivity.this.fileList.size();
        if (k >= AddPostActivity.this.imageList.size())
        {
          AddPostActivity.this.currCount = j;
          AddPostActivity.this.setImageCount();
          return;
        }
        if (m >= j)
        {
          AddPostActivity.this.mFileTools.deleteFile(((File)AddPostActivity.this.fileList.get(m)).getName());
          AddPostActivity.this.fileList.remove(m);
          m--;
        }
        if (k == j)
          ((AddImageView)AddPostActivity.this.imageList.get(k)).removeView(false);
        while (true)
        {
          k++;
          break;
          ((AddImageView)AddPostActivity.this.imageList.get(k)).removeView(true);
        }
      case 3:
        AddPostActivity.this.file = AddPostActivity.this.mFileTools.createFile(System.currentTimeMillis() + ".JPEG");
        LogUtils.myLog(AddPostActivity.this.file.getAbsolutePath());
        Intent localIntent = new Intent("android.intent.action.GET_CONTENT");
        localIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        AddPostActivity.this.startActivityForResult(localIntent, ((Integer)paramMessage.obj).intValue());
        return;
      case 4:
        AddPostActivity.this.manager.showSoftInput(AddPostActivity.this.editTitle, 0);
        return;
      case 5:
      }
      AddPostActivity.this.fileName = ((String[])paramMessage.obj);
      int i = 0;
      if (i >= AddPostActivity.this.fileName.length)
      {
        UrlFactory.getInstance().publishPost().constructUrl(AddPostActivity.this, AddPostActivity.this.param, 1, AddPostActivity.this.mContext);
        return;
      }
      if (i != -1 + AddPostActivity.this.fileName.length)
        AddPostActivity.this.param[4] = (AddPostActivity.this.param[4] + AddPostActivity.access$11(AddPostActivity.this)[i] + ",");
      while (true)
      {
        i++;
        break;
        AddPostActivity.this.param[4] = (AddPostActivity.this.param[4] + AddPostActivity.access$11(AddPostActivity.this)[i]);
      }
    }
  }

  class CallBackListener
    implements OnClickListener, OnGlobalLayoutListener
  {
    CallBackListener()
    {
    }

    public void onClick(View paramView)
    {
      switch (paramView.getId())
      {
      case 2131099691:
      case 2131099692:
      case 2131099693:
      case 2131099694:
      default:
        return;
      case 2131099689:
        AddPostActivity.this.finish(2130968578, 2130968582);
        return;
      case 2131099696:
        if (!AddPostActivity.this.isInit)
          AddPostActivity.this.initPictureLayout();
        AddPostActivity.this.manager.toggleSoftInput(0, 2);
        return;
      case 2131099695:
        AddPostActivity.this.layoutLocation.setVisibility(8);
        return;
      case 2131099690:
      }
      if (AddPostActivity.this.editTitle.getText().toString().equals(""))
      {
        Toast.makeText(AddPostActivity.this.mContext, "帖子标题不能为空", 0).show();
        return;
      }
      if (AddPostActivity.this.editContent.getText().toString().equals(""))
      {
        Toast.makeText(AddPostActivity.this.mContext, "帖子内容不能为空", 0).show();
        return;
      }
      AddPostActivity.this.param = new String[5];
      AddPostActivity.this.param[0] = AddPostActivity.access$19(AddPostActivity.this).getUserId();
      AddPostActivity.this.param[1] = URLEncoder.encode(AddPostActivity.access$9(AddPostActivity.this).getText().toString());
      AddPostActivity.this.param[2] = URLEncoder.encode(AddPostActivity.access$17(AddPostActivity.this).getText().toString());
      AddPostActivity.this.param[3] = String.valueOf(AddPostActivity.access$20(AddPostActivity.this));
      AddPostActivity.this.param[4] = "";
      if (AddPostActivity.this.fileList.size() != 0)
        new FileUpload(AddPostActivity.this.fileList, new String[] { "bbs" }, AddPostActivity.this.mHandler).start();
      while (true)
      {
        AddPostActivity.this.mProTools.startDialog("努力发表中");
        return;
        UrlFactory.getInstance().publishPost().constructUrl(AddPostActivity.this, AddPostActivity.this.param, 1, AddPostActivity.this.mContext);
      }
    }

    public void onGlobalLayout()
    {
      Rect localRect = new Rect();
      AddPostActivity.this.layoutMain.getWindowVisibleDisplayFrame(localRect);
      int i = AddPostActivity.this.mSP.getHeight() - (localRect.bottom - localRect.top);
      if (AddPostActivity.this.hightList == null)
      {
        AddPostActivity.this.hightList = new ArrayList();
        AddPostActivity.this.hightList.add(Integer.valueOf(i));
      }
      do
        return;
      while (AddPostActivity.this.hightList.contains(Integer.valueOf(i)));
      AddPostActivity.this.hightList.add(Integer.valueOf(i));
    }
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.activity.AddPostActivity
 * JD-Core Version:    0.6.0
 */