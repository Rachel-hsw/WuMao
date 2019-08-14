package com.withustudy.koudaizikao.custom;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class LoadingView extends LinearLayout
{
  private AnimationDrawable mAnimationDrawable;
  private Context mContext;
  private ImageView mImageView;
  private TextView mTextView;

  public LoadingView(Context paramContext)
  {
    super(paramContext);
    this.mContext = paramContext;
    initView();
  }

  public LoadingView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.mContext = paramContext;
    initView();
  }

  public LoadingView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    this.mContext = paramContext;
    initView();
  }

  @SuppressLint({"NewApi"})
  public LoadingView(Context paramContext, AttributeSet paramAttributeSet, int paramInt1, int paramInt2)
  {
    super(paramContext, paramAttributeSet, paramInt1, paramInt2);
    this.mContext = paramContext;
    initView();
  }

  private void initView()
  {
    View localView = LayoutInflater.from(this.mContext).inflate(2130903097, null);
    this.mImageView = ((ImageView)localView.findViewById(2131100060));
    this.mTextView = ((TextView)localView.findViewById(2131100061));
    this.mImageView.setImageResource(2130837509);
    this.mAnimationDrawable = ((AnimationDrawable)this.mImageView.getDrawable());
    this.mImageView.post(new Runnable()
    {
      public void run()
      {
        if (LoadingView.this.mAnimationDrawable.isRunning())
          LoadingView.this.mAnimationDrawable.stop();
        LoadingView.this.mAnimationDrawable.start();
      }
    });
    addView(localView);
    setGravity(17);
  }

  public void setText(String paramString)
  {
    this.mTextView.setText(paramString);
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.custom.LoadingView
 * JD-Core Version:    0.6.0
 */