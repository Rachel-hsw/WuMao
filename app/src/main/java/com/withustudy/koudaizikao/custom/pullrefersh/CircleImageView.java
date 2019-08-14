package com.withustudy.koudaizikao.custom.pullrefersh;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Build.VERSION;
import android.support.v4.view.ViewCompat;
import android.util.DisplayMetrics;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;

class CircleImageView extends ImageView
{
  private static final int FILL_SHADOW_COLOR = 1023410176;
  private static final int KEY_SHADOW_COLOR = 503316480;
  private static final int SHADOW_ELEVATION = 4;
  private static final float SHADOW_RADIUS = 3.5F;
  private static final float X_OFFSET = 0.0F;
  private static final float Y_OFFSET = 1.75F;
  private Animation.AnimationListener mListener;
  private int mShadowRadius;

  public CircleImageView(Context paramContext, int paramInt, float paramFloat)
  {
    super(paramContext);
    float f = getContext().getResources().getDisplayMetrics().density;
    int i = (int)(2.0F * (paramFloat * f));
    int j = (int)(1.75F * f);
    int k = (int)(0.0F * f);
    this.mShadowRadius = (int)(3.5F * f);
    ShapeDrawable localShapeDrawable;
    if (elevationSupported())
    {
      localShapeDrawable = new ShapeDrawable(new OvalShape());
      ViewCompat.setElevation(this, 4.0F * f);
    }
    while (true)
    {
      localShapeDrawable.getPaint().setColor(paramInt);
      setBackgroundDrawable(localShapeDrawable);
      return;
      localShapeDrawable = new ShapeDrawable(new OvalShadow(this.mShadowRadius, i));
      ViewCompat.setLayerType(this, 1, localShapeDrawable.getPaint());
      localShapeDrawable.getPaint().setShadowLayer(this.mShadowRadius, k, j, 503316480);
      int m = this.mShadowRadius;
      setPadding(m, m, m, m);
    }
  }

  private boolean elevationSupported()
  {
    return Build.VERSION.SDK_INT >= 21;
  }

  public void onAnimationEnd()
  {
    super.onAnimationEnd();
    if (this.mListener != null)
      this.mListener.onAnimationEnd(getAnimation());
  }

  public void onAnimationStart()
  {
    super.onAnimationStart();
    if (this.mListener != null)
      this.mListener.onAnimationStart(getAnimation());
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
    if (!elevationSupported())
      setMeasuredDimension(getMeasuredWidth() + 2 * this.mShadowRadius, getMeasuredHeight() + 2 * this.mShadowRadius);
  }

  public void setAnimationListener(Animation.AnimationListener paramAnimationListener)
  {
    this.mListener = paramAnimationListener;
  }

  public void setBackgroundColor(int paramInt)
  {
    if ((getBackground() instanceof ShapeDrawable))
    {
      Resources localResources = getResources();
      ((ShapeDrawable)getBackground()).getPaint().setColor(localResources.getColor(paramInt));
    }
  }

  private class OvalShadow extends OvalShape
  {
    private int mCircleDiameter;
    private RadialGradient mRadialGradient;
    private Paint mShadowPaint = new Paint();
    private int mShadowRadius;

    public OvalShadow(int paramInt1, int arg3)
    {
      this.mShadowRadius = paramInt1;
      int i;
      this.mCircleDiameter = i;
      float f1 = this.mCircleDiameter / 2;
      float f2 = this.mCircleDiameter / 2;
      float f3 = this.mShadowRadius;
      int[] arrayOfInt = new int[2];
      arrayOfInt[0] = 1023410176;
      this.mRadialGradient = new RadialGradient(f1, f2, f3, arrayOfInt, null, Shader.TileMode.CLAMP);
      this.mShadowPaint.setShader(this.mRadialGradient);
    }

    public void draw(Canvas paramCanvas, Paint paramPaint)
    {
      int i = CircleImageView.this.getWidth();
      int j = CircleImageView.this.getHeight();
      paramCanvas.drawCircle(i / 2, j / 2, this.mCircleDiameter / 2 + this.mShadowRadius, this.mShadowPaint);
      paramCanvas.drawCircle(i / 2, j / 2, this.mCircleDiameter / 2, paramPaint);
    }
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.custom.pullrefersh.CircleImageView
 * JD-Core Version:    0.6.0
 */