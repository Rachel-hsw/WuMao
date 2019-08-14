package com.withustudy.koudaizikao.custom;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

public class AotuViewPager extends ViewPager
{
  protected static final int MSG_KEEP_SILENT = 2;
  protected static final int MSG_UPDATE_IMAGE = 1;
  private long apartTime;
  private int currentItem = 0;
  private ViewPagerAutoScrollHandler mAutoScrollHandler;
  private Context mContext;

  public AotuViewPager(Context paramContext)
  {
    super(paramContext);
    this.mContext = paramContext;
    initPra();
  }

  public AotuViewPager(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.mContext = paramContext;
    initPra();
  }

  private void initPra()
  {
    this.mAutoScrollHandler = new ViewPagerAutoScrollHandler();
    this.apartTime = 3000L;
    this.currentItem = 0;
  }

  public void setApartTime(long paramLong)
  {
    this.apartTime = paramLong;
  }

  public void startScroll()
  {
    this.mAutoScrollHandler.sendEmptyMessageDelayed(1, this.apartTime);
  }

  public void stopScroll()
  {
    this.mAutoScrollHandler.sendEmptyMessage(2);
  }

  class ViewPagerAutoScrollHandler extends Handler
  {
    ViewPagerAutoScrollHandler()
    {
    }

    public void handleMessage(Message paramMessage)
    {
      super.handleMessage(paramMessage);
      if (AotuViewPager.this.mContext == null)
        return;
      if (hasMessages(1))
        removeMessages(1);
      switch (paramMessage.what)
      {
      case 2:
      default:
        return;
      case 1:
      }
      AotuViewPager.this.currentItem = AotuViewPager.this.getCurrentItem();
      AotuViewPager localAotuViewPager = AotuViewPager.this;
      localAotuViewPager.currentItem = (1 + localAotuViewPager.currentItem);
      AotuViewPager.this.setCurrentItem(AotuViewPager.this.currentItem);
      sendEmptyMessageDelayed(1, AotuViewPager.this.apartTime);
    }
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.custom.AotuViewPager
 * JD-Core Version:    0.6.0
 */