package com.withustudy.koudaizikao.custom;

import android.os.CountDownTimer;
import android.widget.TextView;

public class Counter extends CountDownTimer
{
  private static final int MINUTES = 3600;
  private static final int SECONDS = 60;
  private long first = 0L;
  private long mtmp = 0L;
  private long mtmp2 = 0L;
  private long third = 0L;
  private TextView tv;
  private long twice = 0L;

  public Counter(long paramLong1, long paramLong2)
  {
    super(paramLong1, paramLong2);
  }

  public Counter(TextView paramTextView, int paramInt1, int paramInt2)
  {
    this(paramInt1, paramInt2);
    this.tv = paramTextView;
  }

  public void onFinish()
  {
  }

  public void onTick(long paramLong)
  {
    this.first = (paramLong / 1000L);
    if (this.first < 60L)
    {
      TextView localTextView6 = this.tv;
      StringBuilder localStringBuilder10 = new StringBuilder("00:00:");
      if (this.first < 10L);
      for (Object localObject10 = "0" + this.first; ; localObject10 = Long.valueOf(this.first))
      {
        localTextView6.setText(localObject10);
        return;
      }
    }
    if (this.first < 3600L)
    {
      this.twice = (this.first % 60L);
      this.mtmp = (this.first / 60L);
      if (this.twice == 0L)
      {
        TextView localTextView5 = this.tv;
        StringBuilder localStringBuilder9 = new StringBuilder("00:");
        if (this.mtmp < 10L);
        for (Object localObject9 = "0" + this.mtmp; ; localObject9 = Long.valueOf(this.mtmp))
        {
          localTextView5.setText(localObject9 + ":00");
          return;
        }
      }
      TextView localTextView4 = this.tv;
      StringBuilder localStringBuilder7 = new StringBuilder("00:");
      Object localObject7;
      StringBuilder localStringBuilder8;
      if (this.mtmp < 10L)
      {
        localObject7 = "0" + this.mtmp;
        localStringBuilder8 = localStringBuilder7.append(localObject7).append(":");
        if (this.twice >= 10L)
          break label346;
      }
      label346: for (Object localObject8 = "0" + this.twice; ; localObject8 = Long.valueOf(this.twice))
      {
        localTextView4.setText(localObject8);
        return;
        localObject7 = Long.valueOf(this.mtmp);
        break;
      }
    }
    this.twice = (this.first % 3600L);
    this.mtmp = (this.first / 3600L);
    if (this.twice == 0L)
    {
      this.tv.setText("0" + this.first / 3600L + ":00:00");
      return;
    }
    if (this.twice < 60L)
    {
      TextView localTextView3 = this.tv;
      StringBuilder localStringBuilder5 = new StringBuilder();
      Object localObject5;
      StringBuilder localStringBuilder6;
      if (this.mtmp < 10L)
      {
        localObject5 = "0" + this.mtmp;
        localStringBuilder6 = localStringBuilder5.append(localObject5).append(":00:");
        if (this.twice >= 10L)
          break label559;
      }
      label559: for (Object localObject6 = "0" + this.twice; ; localObject6 = Long.valueOf(this.twice))
      {
        localTextView3.setText(localObject6);
        return;
        localObject5 = Long.valueOf(this.mtmp);
        break;
      }
    }
    this.third = (this.twice % 60L);
    this.mtmp2 = (this.twice / 60L);
    if (this.third == 0L)
    {
      TextView localTextView2 = this.tv;
      StringBuilder localStringBuilder3 = new StringBuilder();
      Object localObject3;
      StringBuilder localStringBuilder4;
      if (this.mtmp < 10L)
      {
        localObject3 = "0" + this.mtmp;
        localStringBuilder4 = localStringBuilder3.append(localObject3).append(":");
        if (this.mtmp2 >= 10L)
          break label730;
      }
      label730: for (Object localObject4 = "0" + this.mtmp2; ; localObject4 = Long.valueOf(this.mtmp2))
      {
        localTextView2.setText(localObject4 + ":00");
        return;
        localObject3 = Long.valueOf(this.mtmp);
        break;
      }
    }
    TextView localTextView1 = this.tv;
    StringBuilder localStringBuilder1 = new StringBuilder();
    Object localObject1;
    StringBuilder localStringBuilder2;
    if (this.mtmp < 10L)
    {
      localObject1 = "0" + this.mtmp;
      localStringBuilder2 = localStringBuilder1.append(localObject1).append(":");
      if (this.mtmp2 >= 10L)
        break label873;
    }
    label873: for (Object localObject2 = "0" + this.mtmp2; ; localObject2 = Long.valueOf(this.mtmp2))
    {
      localTextView1.setText(localObject2 + ":" + this.third);
      return;
      localObject1 = Long.valueOf(this.mtmp);
      break;
    }
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.custom.Counter
 * JD-Core Version:    0.6.0
 */