package com.withustudy.koudaizikao.custom;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.withustudy.koudaizikao.activity.PostDetailActivity.PostDetailHandler;

public class AddImageView extends RelativeLayout
{
  private ImageView image;
  private ImageView imageDelte;
  private int index;
  private CallBackListener mBackListener;
  private Context mContext;
  private Handler mHandler;

  public AddImageView(Context paramContext)
  {
    super(paramContext);
    this.mContext = paramContext;
    initView();
  }

  public AddImageView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.mContext = paramContext;
    initView();
  }

  public AddImageView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    this.mContext = paramContext;
    initView();
  }

  @TargetApi(21)
  public AddImageView(Context paramContext, AttributeSet paramAttributeSet, int paramInt1, int paramInt2)
  {
    super(paramContext, paramAttributeSet, paramInt1, paramInt2);
    this.mContext = paramContext;
    initView();
  }

  private void initView()
  {
    this.mBackListener = new CallBackListener();
    View localView = LayoutInflater.from(this.mContext).inflate(2130903096, null);
    this.image = ((ImageView)localView.findViewById(2131100058));
    this.imageDelte = ((ImageView)localView.findViewById(2131100059));
    this.image.setOnClickListener(this.mBackListener);
    this.imageDelte.setOnClickListener(this.mBackListener);
    addView(localView);
  }

  public int getIndex()
  {
    return this.index;
  }

  public void removeView(boolean paramBoolean)
  {
    this.image.setImageBitmap(null);
    this.image.setBackgroundResource(2130837587);
    if (paramBoolean)
      setVisibility(8);
    this.imageDelte.setVisibility(8);
  }

  public void setDeleteVisibility(int paramInt)
  {
    this.imageDelte.setVisibility(paramInt);
  }

  public void setImage(Bitmap paramBitmap)
  {
    this.image.setImageBitmap(paramBitmap);
  }

  public void setIndex(int paramInt)
  {
    this.index = paramInt;
  }

  public void setmHandler(Handler paramHandler)
  {
    this.mHandler = paramHandler;
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
      case 2131100058:
        if ((AddImageView.this.mHandler instanceof PostDetailActivity.PostDetailHandler));
        for (Message localMessage2 = AddImageView.this.mHandler.obtainMessage(6, Integer.valueOf(AddImageView.this.index)); ; localMessage2 = AddImageView.this.mHandler.obtainMessage(3, Integer.valueOf(AddImageView.this.index)))
        {
          AddImageView.this.mHandler.sendMessage(localMessage2);
          return;
        }
      case 2131100059:
      }
      if ((AddImageView.this.mHandler instanceof PostDetailActivity.PostDetailHandler));
      for (Message localMessage1 = AddImageView.this.mHandler.obtainMessage(5, Integer.valueOf(AddImageView.this.index)); ; localMessage1 = AddImageView.this.mHandler.obtainMessage(2, Integer.valueOf(AddImageView.this.index)))
      {
        AddImageView.this.mHandler.sendMessage(localMessage1);
        return;
      }
    }
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.custom.AddImageView
 * JD-Core Version:    0.6.0
 */