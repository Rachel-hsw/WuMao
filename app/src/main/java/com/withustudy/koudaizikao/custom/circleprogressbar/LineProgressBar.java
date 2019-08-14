package com.withustudy.koudaizikao.custom.circleprogressbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.graphics.Paint.Style;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import com.withustudy.koudaizikao.R.styleable;

public class LineProgressBar extends View
{
  private static final String KEY_INSTANCE_STATE = "instance_state";
  private static final String KEY_STATE_CURRENT_PROGRESS = "state_current_progress";
  private static final String KEY_STATE_NEED_ANIM = "state_need_anim";
  private static final String KEY_STATE_NEED_SHOW_TEXT = "state_need_show_text";
  private static final String KEY_STATE_ORIENTATION = "state_orientation";
  private static final float MAX_PROGRESS = 100.0F;
  private Paint mBackgroundPaint;
  private Context mContext;
  private int mCurrentProgress = 0;
  private int mLpbBackgroundColor = -16777216;
  private float mLpbImageHeight = -1.0F;
  private float mLpbImageWidth = -1.0F;
  private boolean mLpbNeedAnim = false;
  private boolean mLpbNeedShowText = true;
  private Orientation mLpbOrientation = Orientation.horizontal;
  private int mLpbProgressColor = -65536;
  private Bitmap mLpbProgressImage;
  private int mLpbProgressTextColor = -1;
  private Bitmap mLpbSecondaryProgressImage;
  private int mMaxWidth;
  private int mMinWidth;
  private Paint mProgressPaint;
  private Paint mTextPaint;

  public LineProgressBar(Context paramContext)
  {
    super(paramContext);
    this.mContext = paramContext;
    init();
  }

  public LineProgressBar(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.mContext = paramContext;
    parseAttributes(paramAttributeSet);
    init();
  }

  private void drawBackground(Canvas paramCanvas)
  {
    paramCanvas.drawRect(0.0F, 0.0F, getWidth(), getHeight(), this.mBackgroundPaint);
  }

  private void drawLineProgress(Canvas paramCanvas)
  {
    float f1 = this.mCurrentProgress / 100.0F;
    int i;
    float f2;
    float f3;
    label47: float f4;
    if (this.mLpbOrientation == Orientation.vertical)
    {
      i = getHeight();
      f2 = i;
      if (this.mLpbOrientation != Orientation.horizontal)
        break label89;
      f3 = f1 * getWidth();
      if (this.mLpbOrientation != Orientation.vertical)
        break label99;
      f4 = getHeight() * (1.0F - f1);
    }
    while (true)
    {
      paramCanvas.drawRect(0.0F, f2, f3, f4, this.mProgressPaint);
      return;
      i = 0;
      break;
      label89: f3 = getWidth();
      break label47;
      label99: f4 = getHeight();
    }
  }

  private void drawProgressText(Canvas paramCanvas)
  {
    if ((this.mLpbProgressImage != null) || (!this.mLpbNeedShowText))
      return;
    String str = String.valueOf(this.mCurrentProgress);
    int i = (int)(this.mMinWidth / 2.5D);
    this.mTextPaint.setTextSize(i);
    FontMetrics localFontMetrics = this.mTextPaint.getFontMetrics();
    float f1 = (float)Math.ceil(localFontMetrics.descent - localFontMetrics.top);
    float f2 = this.mTextPaint.measureText(str);
    paramCanvas.drawText(str, (this.mMaxWidth - f2) / 2.0F, (this.mMinWidth - f1) / 2.0F + i, this.mTextPaint);
  }

  private void init()
  {
    initBackgroundPaint();
    initProgressPaint();
    initTextPaint();
  }

  private void initBackgroundPaint()
  {
    this.mBackgroundPaint = initImagePaint(this.mLpbSecondaryProgressImage, this.mLpbBackgroundColor);
  }

  private Paint initImagePaint(Bitmap paramBitmap, int paramInt)
  {
    Paint localPaint = new Paint();
    localPaint.setAntiAlias(true);
    localPaint.setDither(true);
    localPaint.setStyle(Style.FILL);
    if (paramBitmap != null)
    {
      localPaint.setShader(new BitmapShader(paramBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
      return localPaint;
    }
    localPaint.setColor(paramInt);
    return localPaint;
  }

  private void initProgressPaint()
  {
    this.mProgressPaint = initImagePaint(this.mLpbProgressImage, this.mLpbProgressColor);
  }

  private void initTextPaint()
  {
    this.mTextPaint = new Paint();
    this.mTextPaint.setAntiAlias(true);
    this.mTextPaint.setColor(this.mLpbProgressTextColor);
  }

  private void parseAttributes(AttributeSet paramAttributeSet)
  {
    TypedArray localTypedArray = this.mContext.obtainStyledAttributes(paramAttributeSet, R.styleable.LineProgressBar);
    this.mLpbBackgroundColor = localTypedArray.getColor(0, this.mLpbBackgroundColor);
    this.mLpbProgressColor = localTypedArray.getColor(3, this.mLpbProgressColor);
    this.mLpbProgressTextColor = localTypedArray.getColor(4, this.mLpbProgressTextColor);
    this.mLpbNeedShowText = localTypedArray.getBoolean(5, this.mLpbNeedShowText);
    this.mLpbNeedAnim = localTypedArray.getBoolean(6, this.mLpbNeedAnim);
    this.mLpbImageWidth = localTypedArray.getDimension(7, this.mLpbImageWidth);
    this.mLpbImageHeight = localTypedArray.getDimension(8, this.mLpbImageHeight);
    int i = localTypedArray.getInteger(9, Orientation.horizontal.getValue());
    BitmapDrawable localBitmapDrawable1 = (BitmapDrawable)localTypedArray.getDrawable(1);
    BitmapDrawable localBitmapDrawable2 = (BitmapDrawable)localTypedArray.getDrawable(2);
    localTypedArray.recycle();
    Orientation localOrientation;
    if (i == Orientation.horizontal.getValue())
    {
      localOrientation = Orientation.horizontal;
      this.mLpbOrientation = localOrientation;
      if (localBitmapDrawable1 == null)
        break label216;
    }
    label216: for (Bitmap localBitmap1 = localBitmapDrawable1.getBitmap(); ; localBitmap1 = null)
    {
      this.mLpbProgressImage = localBitmap1;
      Bitmap localBitmap2 = null;
      if (localBitmapDrawable2 != null)
        localBitmap2 = localBitmapDrawable2.getBitmap();
      this.mLpbSecondaryProgressImage = localBitmap2;
      resizeImageIfNeed();
      return;
      localOrientation = Orientation.vertical;
      break;
    }
  }

  private Bitmap resizeImage(Bitmap paramBitmap)
  {
    return Bitmap.createScaledBitmap(paramBitmap, (int)this.mLpbImageWidth, (int)this.mLpbImageHeight, false);
  }

  private void resizeImageIfNeed()
  {
    if ((this.mLpbProgressImage == null) || (this.mLpbImageWidth == -1.0F) || (this.mLpbImageHeight == -1.0F))
      return;
    this.mLpbProgressImage = resizeImage(this.mLpbProgressImage);
    if (this.mLpbSecondaryProgressImage == null);
    for (Bitmap localBitmap = null; ; localBitmap = resizeImage(this.mLpbSecondaryProgressImage))
    {
      this.mLpbSecondaryProgressImage = localBitmap;
      return;
    }
  }

  public void invalidateUi()
  {
    if (Looper.getMainLooper() == Looper.myLooper())
    {
      invalidate();
      return;
    }
    postInvalidate();
  }

  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    drawBackground(paramCanvas);
    drawLineProgress(paramCanvas);
    drawProgressText(paramCanvas);
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    if (!paramBoolean)
      return;
    this.mMaxWidth = getWidth();
    int i = getHeight();
    if (this.mLpbProgressImage != null)
    {
      this.mMaxWidth = Math.max(this.mMaxWidth, this.mLpbProgressImage.getWidth());
      i = Math.max(i, this.mLpbProgressImage.getHeight());
    }
    this.mMinWidth = Math.min(this.mMaxWidth, i);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
    if (this.mLpbProgressImage != null)
      setMeasuredDimension(this.mLpbProgressImage.getWidth(), this.mLpbProgressImage.getHeight());
  }

  protected void onRestoreInstanceState(Parcelable paramParcelable)
  {
    if ((paramParcelable instanceof Bundle))
    {
      Bundle localBundle = (Bundle)paramParcelable;
      this.mCurrentProgress = localBundle.getInt("state_current_progress");
      this.mLpbNeedAnim = localBundle.getBoolean("state_need_anim");
      this.mLpbNeedShowText = localBundle.getBoolean("state_need_show_text");
      if (localBundle.getInt("state_orientation", 0) == Orientation.vertical.getValue());
      for (Orientation localOrientation = Orientation.vertical; ; localOrientation = Orientation.horizontal)
      {
        this.mLpbOrientation = localOrientation;
        super.onRestoreInstanceState(localBundle.getParcelable("instance_state"));
        return;
      }
    }
    super.onRestoreInstanceState(paramParcelable);
  }

  protected Parcelable onSaveInstanceState()
  {
    Bundle localBundle = new Bundle();
    localBundle.putParcelable("instance_state", super.onSaveInstanceState());
    localBundle.putInt("state_current_progress", this.mCurrentProgress);
    localBundle.putBoolean("state_need_show_text", this.mLpbNeedShowText);
    localBundle.putBoolean("state_need_anim", this.mLpbNeedAnim);
    localBundle.putInt("state_orientation", this.mLpbOrientation.getValue());
    return localBundle;
  }

  public void setProgress(int paramInt)
  {
    if (paramInt > 100.0F)
      paramInt = 100;
    this.mCurrentProgress = paramInt;
    if (!this.mLpbNeedAnim)
      invalidateUi();
  }

  private static enum Orientation
  {
    private int value;

    static
    {
      Orientation[] arrayOfOrientation = new Orientation[2];
      arrayOfOrientation[0] = horizontal;
      arrayOfOrientation[1] = vertical;
      ENUM$VALUES = arrayOfOrientation;
    }

    private Orientation(int arg3)
    {
      int j;
      this.value = j;
    }

    public int getValue()
    {
      return this.value;
    }
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.custom.circleprogressbar.LineProgressBar
 * JD-Core Version:    0.6.0
 */