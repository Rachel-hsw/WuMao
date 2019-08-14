package com.withustudy.koudaizikao.custom;

import android.os.CountDownTimer;
import android.widget.TextView;

public class MyCountTimer extends CountDownTimer
{
  public static final int TIME_COUNT = 240000;
  private TextView btn;
  private int endStrRid;
  private int normalColor;
  private int timingColor;

  public MyCountTimer(long paramLong1, long paramLong2, TextView paramTextView, int paramInt)
  {
    super(paramLong1, paramLong2);
    this.btn = paramTextView;
    this.endStrRid = paramInt;
  }

  public MyCountTimer(TextView paramTextView)
  {
    super(240000L, 1000L);
    this.btn = paramTextView;
    this.endStrRid = 2131165361;
  }

  public MyCountTimer(TextView paramTextView, int paramInt)
  {
    super(240000L, 1000L);
    this.btn = paramTextView;
    this.endStrRid = paramInt;
  }

  public MyCountTimer(TextView paramTextView, int paramInt1, int paramInt2)
  {
    this(paramTextView);
    this.normalColor = paramInt1;
    this.timingColor = paramInt2;
  }

  public void onFinish()
  {
    if (this.normalColor > 0)
      this.btn.setTextColor(this.normalColor);
    this.btn.setText(this.endStrRid);
    this.btn.setEnabled(true);
  }

  public void onTick(long paramLong)
  {
    if (this.timingColor > 0)
      this.btn.setTextColor(this.timingColor);
    this.btn.setEnabled(false);
    this.btn.setText(paramLong / 1000L + "秒");
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.custom.MyCountTimer
 * JD-Core Version:    0.6.0
 */