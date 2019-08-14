package com.withustudy.koudaizikao.activity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.withustudy.koudaizikao.base.AbsBaseActivity;
import com.withustudy.koudaizikao.config.KouDaiSP;
import com.withustudy.koudaizikao.entity.Version;
import java.util.List;

public class UpdateNewActivity extends AbsBaseActivity
{
  private Button buttonCancel;
  private Button buttonSure;
  private LinearLayout layoutContent;
  private LinearLayout layoutMain;
  private List<String> list;
  private CallBackListener mBackListener;
  private UpdateHandler mHandler;
  private LinearLayout.LayoutParams mParamsContent;
  private FrameLayout.LayoutParams mParamsMain;
  private Version mVersion;

  private void setContent()
  {
    this.layoutContent.removeAllViews();
    for (int i = 0; ; i++)
    {
      if (i >= this.list.size())
        return;
      TextView localTextView = new TextView(this.mContext);
      localTextView.setLayoutParams(this.mParamsContent);
      localTextView.setText((CharSequence)this.list.get(i));
      localTextView.setTextColor(Color.parseColor("#666666"));
      localTextView.setTextSize(2, 15.0F);
      this.layoutContent.addView(localTextView);
    }
  }

  protected void bindData()
  {
    this.layoutMain.setLayoutParams(this.mParamsMain);
    setContent();
  }

  protected void initData()
  {
    this.mHandler = new UpdateHandler();
    this.mBackListener = new CallBackListener();
    this.mParamsMain = new FrameLayout.LayoutParams((int)(0.89D * this.mSP.getWidth()), (int)(0.49D * this.mSP.getHeight()));
    this.mParamsContent = new LinearLayout.LayoutParams(-1, -2);
    this.mParamsContent.setMargins((int)(0.083D * this.mSP.getWidth()), 10, 0, 0);
    this.mVersion = ((Version)getIntent().getExtras().getSerializable("update"));
    this.list = this.mVersion.getUpdateInfo();
  }

  protected void initListener()
  {
    this.buttonSure.setOnClickListener(this.mBackListener);
    this.buttonCancel.setOnClickListener(this.mBackListener);
  }

  protected void initView()
  {
    this.layoutMain = ((LinearLayout)findViewById(2131100010));
    this.layoutContent = ((LinearLayout)findViewById(2131100011));
    this.buttonSure = ((Button)findViewById(2131100013));
    this.buttonCancel = ((Button)findViewById(2131100012));
  }

  protected void setContentView()
  {
    setContentView(2130903086);
  }

  class CallBackListener
    implements OnClickListener
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
      case 2131100013:
        new Intent();
        Intent localIntent = new Intent();
        localIntent.setAction("android.intent.action.VIEW");
        localIntent.setData(Uri.parse("http://kdzikao.com/download/android/kdzikao.apk"));
        UpdateNewActivity.this.startActivity(localIntent);
        UpdateNewActivity.this.finish(0, 0);
        return;
      case 2131100012:
      }
      UpdateNewActivity.this.finish(0, 0);
    }
  }

  class UpdateHandler extends Handler
  {
    UpdateHandler()
    {
    }

    public void handleMessage(Message paramMessage)
    {
      super.handleMessage(paramMessage);
    }
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.activity.UpdateNewActivity
 * JD-Core Version:    0.6.0
 */