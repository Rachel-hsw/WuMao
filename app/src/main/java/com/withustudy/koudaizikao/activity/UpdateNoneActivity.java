package com.withustudy.koudaizikao.activity;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;
import com.withustudy.koudaizikao.base.AbsBaseActivity;
import com.withustudy.koudaizikao.config.KouDaiSP;

public class UpdateNoneActivity extends AbsBaseActivity
{
  private Button buttonCancel;
  private LinearLayout layoutMain;
  private CallBackListener mBackListener;

  protected void bindData()
  {
  }

  protected void initData()
  {
    this.mBackListener = new CallBackListener();
  }

  protected void initListener()
  {
    this.buttonCancel.setOnClickListener(this.mBackListener);
  }

  protected void initView()
  {
    this.layoutMain = ((LinearLayout)findViewById(2131100014));
    FrameLayout.LayoutParams localLayoutParams = new FrameLayout.LayoutParams((int)(0.89D * this.mSP.getWidth()), (int)(0.49D * this.mSP.getHeight()));
    this.layoutMain.setLayoutParams(localLayoutParams);
    this.buttonCancel = ((Button)findViewById(2131100015));
  }

  protected void setContentView()
  {
    setContentView(2130903087);
  }

  class CallBackListener
    implements OnClickListener
  {
    CallBackListener()
    {
    }

    public void onClick(View paramView)
    {
      UpdateNoneActivity.this.finish(0, 0);
    }
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.activity.UpdateNoneActivity
 * JD-Core Version:    0.6.0
 */