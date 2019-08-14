package com.withustudy.koudaizikao.custom.circleprogressbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.FontMetrics;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Shader.TileMode;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import com.withustudy.koudaizikao.R.styleable;

public class CircleProgressBar extends View
{
  private static final String KEY_INSTANCE_STATE = "instance_state";
  private static final String KEY_STATE_ANGLE_STEP = "state_angle_step";
  private static final String KEY_STATE_CURRENT_PROGRESS = "state_current_progress";
  private static final String KEY_STATE_NEED_ANIM = "state_need_anim";
  private static final String KEY_STATE_NEED_SHOW_TEXT = "state_need_show_text";
  private static final float MAX_PROGRESS = 100.0F;
  private static Handler mHandler = new Handler(Looper.getMainLooper());
  private int mAngleStep = 0;
  private AnimRunnable mAnimRunnable;
  private RectF mBackgroundRectF;
  private int[] mColorScheme;
  private Context mContext;
  private int mCpbBackgroundColor = Color.parseColor("#7df5f5f5");
  private Paint mCpbBackgroundPaint;
  private int mCpbForegroundColor = Color.parseColor("#0dfb7d");
  private Paint mCpbForegroundPaint;
  private int mCpbMaxAngle = 360;
  private boolean mCpbNeedAnim = true;
  private boolean mCpbNeedShowText = true;
  private int mCpbProgressTextColor;
  private int mCpbStartAngle = -90;
  private int mCpbStrokeWidth = 10;
  private Paint mCpbTextPaint;
  private int mCpbWholeBackgroundColor = Color.parseColor("#ffeeeaff");
  private Paint mCpbWholeBackgroundPaint;
  private int mCurrentProgress = 0;
  private RectF mForegroundRectF;
  private LoadingCallBack mLoadingCallBack;
  private int mMinWidth;
  private RectF mWholeRectF;
  private float t = 0.0F;

  public CircleProgressBar(Context paramContext)
  {
    super(paramContext);
    this.mContext = paramContext;
    init();
  }

  public CircleProgressBar(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.mContext = paramContext;
    parseAttributes(paramAttributeSet);
    init();
  }

  private void drawCpbBackground(Canvas paramCanvas)
  {
    paramCanvas.drawArc(this.mBackgroundRectF, this.mCpbStartAngle, this.mCpbMaxAngle, false, this.mCpbBackgroundPaint);
  }

  private void drawCpbForeground(Canvas paramCanvas)
  {
    int i = this.mAngleStep + this.mCpbStartAngle;
    int j = (int)(this.mCurrentProgress / 100.0F * this.mCpbMaxAngle);
    paramCanvas.drawArc(this.mForegroundRectF, i, j, false, this.mCpbForegroundPaint);
  }

  private void drawCpbWholeBackground(Canvas paramCanvas)
  {
    paramCanvas.drawCircle(this.mForegroundRectF.centerX(), this.mForegroundRectF.centerY(), this.mForegroundRectF.height() / 2.0F, this.mCpbWholeBackgroundPaint);
  }

  private void drawProgressText(Canvas paramCanvas)
  {
    if (!this.mCpbNeedShowText)
      return;
    String str = String.valueOf(this.mCurrentProgress);
    int i = (int)((this.mMinWidth - 2 * this.mCpbStrokeWidth) / 2.5D);
    this.mCpbTextPaint.setTextSize(130.0F);
    FontMetrics localFontMetrics = this.mCpbTextPaint.getFontMetrics();
    float f1 = (float)Math.ceil(localFontMetrics.descent - localFontMetrics.top);
    float f2 = this.mCpbTextPaint.measureText(str);
    float f3 = (this.mMinWidth - f2) / 2.0F;
    float f4 = (this.mMinWidth - f1) / 2.0F + i;
    paramCanvas.drawText(str + "%", f3 - 10.0F, f4 - 100.0F, this.mCpbTextPaint);
    this.mCpbTextPaint.setTextSize(50.0F);
    if (this.t == 0.0F)
      this.t = f3;
    paramCanvas.drawText("正确率", this.t - 18.0F, f4 - 30.0F, this.mCpbTextPaint);
  }

  private void init()
  {
    initWholeBackgroundPaint();
    initProgressBackgroundPaint();
    initProgressForegroundPaint();
    initTextPaint();
    initRectF();
    initAnim();
  }

  private void initAnim()
  {
    if (this.mCpbNeedAnim)
      this.mAnimRunnable = new AnimRunnable(null);
  }

  private void initProgressBackgroundPaint()
  {
    this.mCpbBackgroundPaint = new Paint();
    this.mCpbBackgroundPaint.setAntiAlias(true);
    this.mCpbBackgroundPaint.setStyle(Style.STROKE);
    this.mCpbBackgroundPaint.setStrokeWidth(this.mCpbStrokeWidth);
    this.mCpbBackgroundPaint.setColor(this.mCpbBackgroundColor);
  }

  private void initProgressForegroundPaint()
  {
    this.mCpbForegroundPaint = new Paint();
    this.mCpbForegroundPaint.setAntiAlias(true);
    this.mCpbForegroundPaint.setDither(true);
    this.mCpbForegroundPaint.setStyle(Style.STROKE);
    this.mCpbForegroundPaint.setStrokeCap(Cap.ROUND);
    this.mCpbForegroundPaint.setStrokeWidth(this.mCpbStrokeWidth);
    this.mCpbForegroundPaint.setColor(this.mCpbForegroundColor);
  }

  private void initRectF()
  {
    this.mBackgroundRectF = new RectF();
    this.mForegroundRectF = new RectF();
    this.mWholeRectF = new RectF();
  }

  private void initShader()
  {
    int[] arrayOfInt = this.mColorScheme;
    LinearGradient localLinearGradient = null;
    if (arrayOfInt != null)
    {
      int i = this.mColorScheme.length;
      localLinearGradient = null;
      if (i != 0)
      {
        float f = this.mMinWidth - this.mCpbStrokeWidth / 2;
        localLinearGradient = new LinearGradient(0.0F, 0.0F, f, f, this.mColorScheme, null, TileMode.CLAMP);
      }
    }
    if (localLinearGradient != null)
      setShader(localLinearGradient);
  }

  private void initTextPaint()
  {
    this.mCpbTextPaint = new Paint();
    this.mCpbTextPaint.setAntiAlias(true);
    this.mCpbTextPaint.setColor(this.mCpbProgressTextColor);
  }

  private void initWholeBackgroundPaint()
  {
    this.mCpbWholeBackgroundPaint = new Paint();
    this.mCpbWholeBackgroundPaint.setAntiAlias(true);
    this.mCpbWholeBackgroundPaint.setColor(this.mCpbWholeBackgroundColor);
  }

  private void parseAttributes(AttributeSet paramAttributeSet)
  {
    int i = 1;
    TypedArray localTypedArray = this.mContext.obtainStyledAttributes(paramAttributeSet, R.styleable.CircleProgressBar);
    this.mCpbWholeBackgroundColor = localTypedArray.getColor(0, this.mCpbWholeBackgroundColor);
    this.mCpbForegroundColor = localTypedArray.getColor(i, this.mCpbForegroundColor);
    this.mCpbBackgroundColor = localTypedArray.getColor(2, this.mCpbBackgroundColor);
    this.mCpbProgressTextColor = localTypedArray.getColor(3, this.mCpbProgressTextColor);
    this.mCpbStrokeWidth = localTypedArray.getInt(4, this.mCpbStrokeWidth);
    this.mCpbStartAngle = localTypedArray.getInt(5, this.mCpbStartAngle);
    this.mCpbMaxAngle = localTypedArray.getInt(8, this.mCpbMaxAngle);
    this.mCpbNeedShowText = localTypedArray.getBoolean(7, this.mCpbNeedShowText);
    this.mCpbNeedAnim = localTypedArray.getBoolean(6, this.mCpbNeedAnim);
    if ((this.mCpbMaxAngle % 360 == 0) && (this.mCpbNeedAnim))
    {
      this.mCpbNeedAnim = i;
      if (this.mCpbProgressTextColor != 0)
        break label186;
    }
    label186: for (int j = this.mCpbForegroundColor; ; j = this.mCpbProgressTextColor)
    {
      this.mCpbProgressTextColor = j;
      localTypedArray.recycle();
      return;
      i = 0;
      break;
    }
  }

  private void setShader(Shader paramShader)
  {
    this.mCpbForegroundPaint.setShader(paramShader);
    if ((this.mCpbProgressTextColor != this.mCpbForegroundColor) || (!this.mCpbNeedShowText))
      return;
    this.mCpbTextPaint.setShader(paramShader);
  }

  private void startAnimIfNeed()
  {
    if (this.mAnimRunnable != null)
      mHandler.removeCallbacks(this.mAnimRunnable);
    if (this.mCpbNeedAnim)
      mHandler.post(this.mAnimRunnable);
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

  protected void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    if (this.mAnimRunnable != null)
      mHandler.removeCallbacks(this.mAnimRunnable);
  }

  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    drawCpbWholeBackground(paramCanvas);
    drawCpbBackground(paramCanvas);
    drawCpbForeground(paramCanvas);
    drawProgressText(paramCanvas);
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    if (paramBoolean)
    {
      this.mMinWidth = Math.min(getWidth(), getHeight());
      int i = this.mCpbStrokeWidth / 2;
      int j = this.mCpbStrokeWidth / 2;
      int k = this.mMinWidth - this.mCpbStrokeWidth / 2;
      int m = this.mMinWidth - this.mCpbStrokeWidth / 2;
      this.mBackgroundRectF.set(i, j, k, m);
      this.mForegroundRectF.set(i, j, k, m);
      this.mWholeRectF.set(this.mCpbStrokeWidth / 2, this.mCpbStrokeWidth / 2, this.mMinWidth - this.mCpbStrokeWidth / 2, this.mMinWidth - this.mCpbStrokeWidth / 2);
      initShader();
    }
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
    startAnimIfNeed();
  }

  protected void onRestoreInstanceState(Parcelable paramParcelable)
  {
    if ((paramParcelable instanceof Bundle))
    {
      Bundle localBundle = (Bundle)paramParcelable;
      this.mCurrentProgress = localBundle.getInt("state_current_progress");
      this.mAngleStep = localBundle.getInt("state_angle_step");
      this.mCpbNeedAnim = localBundle.getBoolean("state_need_anim");
      this.mCpbNeedShowText = localBundle.getBoolean("state_need_show_text");
      super.onRestoreInstanceState(localBundle.getParcelable("instance_state"));
      return;
    }
    super.onRestoreInstanceState(paramParcelable);
  }

  protected Parcelable onSaveInstanceState()
  {
    Bundle localBundle = new Bundle();
    localBundle.putParcelable("instance_state", super.onSaveInstanceState());
    localBundle.putInt("state_current_progress", this.mCurrentProgress);
    localBundle.putInt("state_angle_step", this.mAngleStep);
    localBundle.putBoolean("state_need_show_text", this.mCpbNeedShowText);
    localBundle.putBoolean("state_need_anim", this.mCpbNeedAnim);
    return localBundle;
  }

  public void setColorScheme(int[] paramArrayOfInt)
  {
    this.mColorScheme = paramArrayOfInt;
  }

  public void setLoadingCallBack(LoadingCallBack paramLoadingCallBack)
  {
    this.mLoadingCallBack = paramLoadingCallBack;
  }

  public void setProgress(int paramInt)
  {
    if (paramInt > 100.0F)
      paramInt = 100;
    this.mCurrentProgress = paramInt;
    if (!this.mCpbNeedAnim)
      invalidateUi();
  }

  private class AnimRunnable
    implements Runnable
  {
    private AnimRunnable()
    {
    }

    private void invalidateView()
    {
      CircleProgressBar localCircleProgressBar = CircleProgressBar.this;
      localCircleProgressBar.mAngleStep = (2 + localCircleProgressBar.mAngleStep);
      CircleProgressBar.this.invalidate();
    }

    public void run()
    {
      if (CircleProgressBar.this.mCurrentProgress >= 100.0F)
      {
        CircleProgressBar.this.mCurrentProgress = 100;
        invalidateView();
        CircleProgressBar.mHandler.removeCallbacks(this);
        if (CircleProgressBar.this.mLoadingCallBack != null)
          CircleProgressBar.this.mLoadingCallBack.loadingComplete(CircleProgressBar.this);
        return;
      }
      invalidateView();
      CircleProgressBar.mHandler.postDelayed(this, 12L);
    }
  }

  public static abstract interface LoadingCallBack
  {
    public abstract void loadingComplete(View paramView);
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.custom.circleprogressbar.CircleProgressBar
 * JD-Core Version:    0.6.0
 */