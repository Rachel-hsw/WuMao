package com.withustudy.koudaizikao.custom.circleprogressbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.ViewGroup;
import com.withustudy.koudaizikao.R.styleable;

public class ProgressButton extends ViewGroup
{
  private Context mContext;

  public ProgressButton(Context paramContext)
  {
    super(paramContext);
    this.mContext = paramContext;
    init();
  }

  public ProgressButton(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.mContext = paramContext;
    parseAttributes(paramAttributeSet);
    init();
  }

  private void init()
  {
  }

  private void parseAttributes(AttributeSet paramAttributeSet)
  {
    this.mContext.obtainStyledAttributes(paramAttributeSet, R.styleable.CircleProgressBar).recycle();
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.custom.circleprogressbar.ProgressButton
 * JD-Core Version:    0.6.0
 */