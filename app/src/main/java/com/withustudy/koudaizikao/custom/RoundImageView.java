package com.withustudy.koudaizikao.custom;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.widget.ImageView;
import com.withustudy.koudaizikao.R.styleable;

public class RoundImageView extends ImageView
{
  private static final int BODER_RADIUS_DEFAULT = 10;
  private static final String STATE_BORDER_RADIUS = "state_border_radius";
  private static final String STATE_INSTANCE = "state_instance";
  private static final String STATE_TYPE = "state_type";
  public static final int TYPE_CIRCLE = 0;
  public static final int TYPE_ROUND = 1;
  private Paint mBitmapPaint = new Paint();
  private BitmapShader mBitmapShader;
  private int mBorderRadius;
  private Matrix mMatrix = new Matrix();
  private int mRadius;
  private RectF mRoundRect;
  private int mWidth;
  private int type;

  public RoundImageView(Context paramContext)
  {
    this(paramContext, null);
  }

  public RoundImageView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.mBitmapPaint.setAntiAlias(true);
    TypedArray localTypedArray = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.RoundImageView);
    this.mBorderRadius = localTypedArray.getDimensionPixelSize(0, (int)TypedValue.applyDimension(1, 10.0F, getResources().getDisplayMetrics()));
    this.type = localTypedArray.getInt(1, 0);
    localTypedArray.recycle();
  }

  private Bitmap drawableToBitamp(Drawable paramDrawable)
  {
    if ((paramDrawable instanceof BitmapDrawable))
      return ((BitmapDrawable)paramDrawable).getBitmap();
    int i = paramDrawable.getIntrinsicWidth();
    int j = paramDrawable.getIntrinsicHeight();
    Bitmap localBitmap = Bitmap.createBitmap(i, j, Config.ARGB_8888);
    Canvas localCanvas = new Canvas(localBitmap);
    paramDrawable.setBounds(0, 0, i, j);
    paramDrawable.draw(localCanvas);
    return localBitmap;
  }

  private void setUpShader()
  {
    Drawable localDrawable = getDrawable();
    if (localDrawable == null)
      return;
    Bitmap localBitmap = drawableToBitamp(localDrawable);
    this.mBitmapShader = new BitmapShader(localBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
    float f = 1.0F;
    if (this.type == 0)
    {
      int i = Math.min(localBitmap.getWidth(), localBitmap.getHeight());
      f = 1.0F * this.mWidth / i;
    }
    while (true)
    {
      this.mMatrix.setScale(f, f);
      this.mBitmapShader.setLocalMatrix(this.mMatrix);
      this.mBitmapPaint.setShader(this.mBitmapShader);
      return;
      if (this.type != 1)
        continue;
      Log.e("TAG", "b'w = " + localBitmap.getWidth() + " , " + "b'h = " + localBitmap.getHeight());
      if ((localBitmap.getWidth() == getWidth()) && (localBitmap.getHeight() == getHeight()))
        continue;
      f = Math.max(1.0F * getWidth() / localBitmap.getWidth(), 1.0F * getHeight() / localBitmap.getHeight());
    }
  }

  public int dp2px(int paramInt)
  {
    return (int)TypedValue.applyDimension(1, paramInt, getResources().getDisplayMetrics());
  }

  protected void onDraw(Canvas paramCanvas)
  {
    Log.e("TAG", "onDraw");
    if (getDrawable() == null)
      return;
    setUpShader();
    if (this.type == 1)
    {
      paramCanvas.drawRoundRect(this.mRoundRect, this.mBorderRadius, this.mBorderRadius, this.mBitmapPaint);
      return;
    }
    paramCanvas.drawCircle(this.mRadius, this.mRadius, this.mRadius, this.mBitmapPaint);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
    if (this.type == 0)
    {
      this.mWidth = Math.min(getMeasuredWidth(), getMeasuredHeight());
      this.mRadius = (this.mWidth / 2);
      setMeasuredDimension(this.mWidth, this.mWidth);
    }
  }

  protected void onRestoreInstanceState(Parcelable paramParcelable)
  {
    if ((paramParcelable instanceof Bundle))
    {
      Bundle localBundle = (Bundle)paramParcelable;
      super.onRestoreInstanceState(((Bundle)paramParcelable).getParcelable("state_instance"));
      this.type = localBundle.getInt("state_type");
      this.mBorderRadius = localBundle.getInt("state_border_radius");
      return;
    }
    super.onRestoreInstanceState(paramParcelable);
  }

  protected Parcelable onSaveInstanceState()
  {
    Bundle localBundle = new Bundle();
    localBundle.putParcelable("state_instance", super.onSaveInstanceState());
    localBundle.putInt("state_type", this.type);
    localBundle.putInt("state_border_radius", this.mBorderRadius);
    return localBundle;
  }

  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    if (this.type == 1)
      this.mRoundRect = new RectF(0.0F, 0.0F, paramInt1, paramInt2);
  }

  public void setBorderRadius(int paramInt)
  {
    int i = dp2px(paramInt);
    if (this.mBorderRadius != i)
    {
      this.mBorderRadius = i;
      invalidate();
    }
  }

  public void setType(int paramInt)
  {
    if (this.type != paramInt)
    {
      this.type = paramInt;
      if ((this.type != 1) && (this.type != 0))
        this.type = 0;
      requestLayout();
    }
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.custom.RoundImageView
 * JD-Core Version:    0.6.0
 */