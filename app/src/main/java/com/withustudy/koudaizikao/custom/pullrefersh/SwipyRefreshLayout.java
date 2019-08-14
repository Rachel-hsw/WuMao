package com.withustudy.koudaizikao.custom.pullrefersh;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Transformation;
import android.widget.AbsListView;
import com.withustudy.koudaizikao.R.styleable;

public class SwipyRefreshLayout extends ViewGroup
{
  private static final int ALPHA_ANIMATION_DURATION = 300;
  private static final int ANIMATE_TO_START_DURATION = 200;
  private static final int ANIMATE_TO_TRIGGER_DURATION = 200;
  private static final int CIRCLE_BG_LIGHT = -328966;
  private static final int CIRCLE_DIAMETER = 40;
  private static final int CIRCLE_DIAMETER_LARGE = 56;
  private static final float DECELERATE_INTERPOLATION_FACTOR = 2.0F;
  public static final int DEFAULT = 1;
  private static final int DEFAULT_CIRCLE_TARGET = 64;
  private static final float DRAG_RATE = 0.5F;
  private static final int INVALID_POINTER = -1;
  public static final int LARGE = 0;
  private static final int[] LAYOUT_ATTRS;
  private static final String LOG_TAG = SwipyRefreshLayout.class.getSimpleName();
  private static final int MAX_ALPHA = 255;
  private static final float MAX_PROGRESS_ANGLE = 0.8F;
  private static final float MAX_SWIPE_DISTANCE_FACTOR = 0.6F;
  private static final int REFRESH_TRIGGER_DISTANCE = 120;
  private static final int SCALE_DOWN_DURATION = 150;
  private static final int STARTING_PROGRESS_ALPHA = 76;
  private int mActivePointerId = -1;
  private Animation mAlphaMaxAnimation;
  private Animation mAlphaStartAnimation;
  private final Animation mAnimateToCorrectPosition = new Animation()
  {
    public void applyTransformation(float paramFloat, Transformation paramTransformation)
    {
      int i;
      if (!SwipyRefreshLayout.this.mUsingCustomStart)
        switch ($SWITCH_TABLE$com$withustudy$koudaizikao$custom$pullrefersh$SwipyRefreshLayoutDirection()[SwipyRefreshLayout.this.mDirection.ordinal()])
        {
        default:
          i = (int)(SwipyRefreshLayout.this.mSpinnerFinalOffset - Math.abs(SwipyRefreshLayout.this.mOriginalOffsetTop));
        case 2:
        }
      while (true)
      {
        int j = SwipyRefreshLayout.this.mFrom + (int)(paramFloat * (i - SwipyRefreshLayout.this.mFrom)) - SwipyRefreshLayout.this.mCircleView.getTop();
        SwipyRefreshLayout.this.setTargetOffsetTopAndBottom(j, false);
        return;
        i = SwipyRefreshLayout.this.getMeasuredHeight() - (int)SwipyRefreshLayout.this.mSpinnerFinalOffset;
        continue;
        i = (int)SwipyRefreshLayout.this.mSpinnerFinalOffset;
      }
    }
  };
  private final Animation mAnimateToStartPosition = new Animation()
  {
    public void applyTransformation(float paramFloat, Transformation paramTransformation)
    {
      SwipyRefreshLayout.this.moveToStart(paramFloat);
    }
  };
  private boolean mBothDirection;
  private int mCircleHeight;
  private CircleImageView mCircleView;
  private int mCircleViewIndex = -1;
  private int mCircleWidth;
  private int mCurrentTargetOffsetTop;
  private final DecelerateInterpolator mDecelerateInterpolator;
  private SwipyRefreshLayoutDirection mDirection;
  protected int mFrom;
  private float mInitialDownY;
  private float mInitialMotionY;
  private boolean mIsBeingDragged;
  private OnRefreshListener mListener;
  private int mMediumAnimationDuration;
  private boolean mNotify;
  private boolean mOriginalOffsetCalculated = false;
  protected int mOriginalOffsetTop;
  private MaterialProgressDrawable mProgress;
  private AnimationListener mRefreshListener = new AnimationListener()
  {
    public void onAnimationEnd(Animation paramAnimation)
    {
      if (SwipyRefreshLayout.this.mRefreshing)
      {
        SwipyRefreshLayout.this.mProgress.setAlpha(255);
        SwipyRefreshLayout.this.mProgress.start();
        if ((SwipyRefreshLayout.this.mNotify) && (SwipyRefreshLayout.this.mListener != null))
          SwipyRefreshLayout.this.mListener.onRefresh(SwipyRefreshLayout.this.mDirection);
      }
      while (true)
      {
        SwipyRefreshLayout.this.mCurrentTargetOffsetTop = SwipyRefreshLayout.this.mCircleView.getTop();
        return;
        SwipyRefreshLayout.this.mProgress.stop();
        SwipyRefreshLayout.this.mCircleView.setVisibility(8);
        SwipyRefreshLayout.this.setColorViewAlpha(255);
        if (SwipyRefreshLayout.this.mScale)
        {
          SwipyRefreshLayout.this.setAnimationProgress(0.0F);
          continue;
        }
        SwipyRefreshLayout.this.setTargetOffsetTopAndBottom(SwipyRefreshLayout.this.mOriginalOffsetTop - SwipyRefreshLayout.this.mCurrentTargetOffsetTop, true);
      }
    }

    public void onAnimationRepeat(Animation paramAnimation)
    {
    }

    public void onAnimationStart(Animation paramAnimation)
    {
    }
  };
  private boolean mRefreshing = false;
  private boolean mReturningToStart;
  private boolean mScale;
  private Animation mScaleAnimation;
  private Animation mScaleDownAnimation;
  private Animation mScaleDownToStartAnimation;
  private float mSpinnerFinalOffset;
  private float mStartingScale;
  private View mTarget;
  private float mTotalDragDistance = -1.0F;
  private int mTouchSlop;
  private boolean mUsingCustomStart;

  static
  {
    LAYOUT_ATTRS = new int[] { 16842766 };
  }

  public SwipyRefreshLayout(Context paramContext)
  {
    this(paramContext, null);
  }

  public SwipyRefreshLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.mTouchSlop = ViewConfiguration.get(paramContext).getScaledTouchSlop();
    this.mMediumAnimationDuration = getResources().getInteger(17694721);
    setWillNotDraw(false);
    this.mDecelerateInterpolator = new DecelerateInterpolator(2.0F);
    TypedArray localTypedArray1 = paramContext.obtainStyledAttributes(paramAttributeSet, LAYOUT_ATTRS);
    setEnabled(localTypedArray1.getBoolean(0, true));
    localTypedArray1.recycle();
    TypedArray localTypedArray2 = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.SwipyRefreshLayout);
    SwipyRefreshLayoutDirection localSwipyRefreshLayoutDirection = SwipyRefreshLayoutDirection.getFromInt(localTypedArray2.getInt(0, 0));
    if (localSwipyRefreshLayoutDirection != SwipyRefreshLayoutDirection.BOTH)
      this.mDirection = localSwipyRefreshLayoutDirection;
    for (this.mBothDirection = false; ; this.mBothDirection = true)
    {
      localTypedArray2.recycle();
      DisplayMetrics localDisplayMetrics = getResources().getDisplayMetrics();
      this.mCircleWidth = (int)(40.0F * localDisplayMetrics.density);
      this.mCircleHeight = (int)(40.0F * localDisplayMetrics.density);
      createProgressView();
      ViewCompat.setChildrenDrawingOrderEnabled(this, true);
      this.mSpinnerFinalOffset = (64.0F * localDisplayMetrics.density);
      return;
      this.mDirection = SwipyRefreshLayoutDirection.TOP;
    }
  }

  private void animateOffsetToCorrectPosition(int paramInt, AnimationListener paramAnimationListener)
  {
    this.mFrom = paramInt;
    this.mAnimateToCorrectPosition.reset();
    this.mAnimateToCorrectPosition.setDuration(200L);
    this.mAnimateToCorrectPosition.setInterpolator(this.mDecelerateInterpolator);
    if (paramAnimationListener != null)
      this.mCircleView.setAnimationListener(paramAnimationListener);
    this.mCircleView.clearAnimation();
    this.mCircleView.startAnimation(this.mAnimateToCorrectPosition);
  }

  private void animateOffsetToStartPosition(int paramInt, AnimationListener paramAnimationListener)
  {
    if (this.mScale)
    {
      startScaleDownReturnToStartAnimation(paramInt, paramAnimationListener);
      return;
    }
    this.mFrom = paramInt;
    this.mAnimateToStartPosition.reset();
    this.mAnimateToStartPosition.setDuration(200L);
    this.mAnimateToStartPosition.setInterpolator(this.mDecelerateInterpolator);
    if (paramAnimationListener != null)
      this.mCircleView.setAnimationListener(paramAnimationListener);
    this.mCircleView.clearAnimation();
    this.mCircleView.startAnimation(this.mAnimateToStartPosition);
  }

  private void createProgressView()
  {
    this.mCircleView = new CircleImageView(getContext(), -328966, 20.0F);
    this.mProgress = new MaterialProgressDrawable(getContext(), this);
    this.mProgress.setBackgroundColor(-328966);
    this.mCircleView.setImageDrawable(this.mProgress);
    this.mCircleView.setVisibility(8);
    addView(this.mCircleView);
  }

  private void ensureTarget()
  {
    if (this.mTarget == null);
    for (int i = 0; ; i++)
    {
      if (i >= getChildCount());
      while (true)
      {
        if ((this.mTotalDragDistance == -1.0F) && (getParent() != null) && (((View)getParent()).getHeight() > 0))
        {
          DisplayMetrics localDisplayMetrics = getResources().getDisplayMetrics();
          this.mTotalDragDistance = (int)Math.min(0.6F * ((View)getParent()).getHeight(), 120.0F * localDisplayMetrics.density);
        }
        return;
        View localView = getChildAt(i);
        if (localView.equals(this.mCircleView))
          break;
        this.mTarget = localView;
      }
    }
  }

  private float getMotionEventY(MotionEvent paramMotionEvent, int paramInt)
  {
    int i = MotionEventCompat.findPointerIndex(paramMotionEvent, paramInt);
    if (i < 0)
      return -1.0F;
    return MotionEventCompat.getY(paramMotionEvent, i);
  }

  private boolean isAlphaUsedForScale()
  {
    return Build.VERSION.SDK_INT < 11;
  }

  private boolean isAnimationRunning(Animation paramAnimation)
  {
    return (paramAnimation != null) && (paramAnimation.hasStarted()) && (!paramAnimation.hasEnded());
  }

  private void moveToStart(float paramFloat)
  {
    setTargetOffsetTopAndBottom(this.mFrom + (int)(paramFloat * (this.mOriginalOffsetTop - this.mFrom)) - this.mCircleView.getTop(), false);
  }

  private void onSecondaryPointerUp(MotionEvent paramMotionEvent)
  {
    int i = MotionEventCompat.getActionIndex(paramMotionEvent);
    if (MotionEventCompat.getPointerId(paramMotionEvent, i) == this.mActivePointerId)
      if (i != 0)
        break label33;
    label33: for (int j = 1; ; j = 0)
    {
      this.mActivePointerId = MotionEventCompat.getPointerId(paramMotionEvent, j);
      return;
    }
  }

  private void setAnimationProgress(float paramFloat)
  {
    if (isAlphaUsedForScale())
    {
      setColorViewAlpha((int)(255.0F * paramFloat));
      return;
    }
    ViewCompat.setScaleX(this.mCircleView, paramFloat);
    ViewCompat.setScaleY(this.mCircleView, paramFloat);
  }

  private void setColorViewAlpha(int paramInt)
  {
    this.mCircleView.getBackground().setAlpha(paramInt);
    this.mProgress.setAlpha(paramInt);
  }

  private void setRawDirection(SwipyRefreshLayoutDirection paramSwipyRefreshLayoutDirection)
  {
    if (this.mDirection == paramSwipyRefreshLayoutDirection)
      return;
    this.mDirection = paramSwipyRefreshLayoutDirection;
    switch ($SWITCH_TABLE$com$withustudy$koudaizikao$custom$pullrefersh$SwipyRefreshLayoutDirection()[this.mDirection.ordinal()])
    {
    default:
      int j = -this.mCircleView.getMeasuredHeight();
      this.mOriginalOffsetTop = j;
      this.mCurrentTargetOffsetTop = j;
      return;
    case 2:
    }
    int i = getMeasuredHeight();
    this.mOriginalOffsetTop = i;
    this.mCurrentTargetOffsetTop = i;
  }

  private void setRefreshing(boolean paramBoolean1, boolean paramBoolean2)
  {
    if (this.mRefreshing != paramBoolean1)
    {
      this.mNotify = paramBoolean2;
      ensureTarget();
      this.mRefreshing = paramBoolean1;
      if (this.mRefreshing)
        animateOffsetToCorrectPosition(this.mCurrentTargetOffsetTop, this.mRefreshListener);
    }
    else
    {
      return;
    }
    startScaleDownAnimation(this.mRefreshListener);
  }

  private void setTargetOffsetTopAndBottom(int paramInt, boolean paramBoolean)
  {
    this.mCircleView.bringToFront();
    this.mCircleView.offsetTopAndBottom(paramInt);
    this.mCurrentTargetOffsetTop = this.mCircleView.getTop();
    if ((paramBoolean) && (Build.VERSION.SDK_INT < 11))
      invalidate();
  }

  private Animation startAlphaAnimation(int paramInt1, int paramInt2)
  {
    if ((this.mScale) && (isAlphaUsedForScale()))
      return null;
    6 local6 = new Animation(paramInt1, paramInt2)
    {
      public void applyTransformation(float paramFloat, Transformation paramTransformation)
      {
        SwipyRefreshLayout.this.mProgress.setAlpha((int)(this.val$startingAlpha + paramFloat * (this.val$endingAlpha - this.val$startingAlpha)));
      }
    };
    local6.setDuration(300L);
    this.mCircleView.setAnimationListener(null);
    this.mCircleView.clearAnimation();
    this.mCircleView.startAnimation(local6);
    return local6;
  }

  @SuppressLint({"NewApi"})
  private void startProgressAlphaMaxAnimation()
  {
    this.mAlphaMaxAnimation = startAlphaAnimation(this.mProgress.getAlpha(), 255);
  }

  @SuppressLint({"NewApi"})
  private void startProgressAlphaStartAnimation()
  {
    this.mAlphaStartAnimation = startAlphaAnimation(this.mProgress.getAlpha(), 76);
  }

  private void startScaleDownAnimation(AnimationListener paramAnimationListener)
  {
    this.mScaleDownAnimation = new Animation()
    {
      public void applyTransformation(float paramFloat, Transformation paramTransformation)
      {
        SwipyRefreshLayout.this.setAnimationProgress(1.0F - paramFloat);
      }
    };
    this.mScaleDownAnimation.setDuration(150L);
    this.mCircleView.setAnimationListener(paramAnimationListener);
    this.mCircleView.clearAnimation();
    this.mCircleView.startAnimation(this.mScaleDownAnimation);
  }

  @SuppressLint({"NewApi"})
  private void startScaleDownReturnToStartAnimation(int paramInt, AnimationListener paramAnimationListener)
  {
    this.mFrom = paramInt;
    if (isAlphaUsedForScale());
    for (this.mStartingScale = this.mProgress.getAlpha(); ; this.mStartingScale = ViewCompat.getScaleX(this.mCircleView))
    {
      this.mScaleDownToStartAnimation = new Animation()
      {
        public void applyTransformation(float paramFloat, Transformation paramTransformation)
        {
          float f = SwipyRefreshLayout.this.mStartingScale + paramFloat * -SwipyRefreshLayout.this.mStartingScale;
          SwipyRefreshLayout.this.setAnimationProgress(f);
          SwipyRefreshLayout.this.moveToStart(paramFloat);
        }
      };
      this.mScaleDownToStartAnimation.setDuration(150L);
      if (paramAnimationListener != null)
        this.mCircleView.setAnimationListener(paramAnimationListener);
      this.mCircleView.clearAnimation();
      this.mCircleView.startAnimation(this.mScaleDownToStartAnimation);
      return;
    }
  }

  private void startScaleUpAnimation(AnimationListener paramAnimationListener)
  {
    this.mCircleView.setVisibility(0);
    if (Build.VERSION.SDK_INT >= 11)
      this.mProgress.setAlpha(255);
    this.mScaleAnimation = new Animation()
    {
      public void applyTransformation(float paramFloat, Transformation paramTransformation)
      {
        SwipyRefreshLayout.this.setAnimationProgress(paramFloat);
      }
    };
    this.mScaleAnimation.setDuration(this.mMediumAnimationDuration);
    if (paramAnimationListener != null)
      this.mCircleView.setAnimationListener(paramAnimationListener);
    this.mCircleView.clearAnimation();
    this.mCircleView.startAnimation(this.mScaleAnimation);
  }

  public boolean canChildScrollDown()
  {
    if (Build.VERSION.SDK_INT < 14)
    {
      AbsListView localAbsListView;
      if ((this.mTarget instanceof AbsListView))
        localAbsListView = (AbsListView)this.mTarget;
      try
      {
        int i;
        int j;
        if ((localAbsListView.getCount() > 0) && (1 + localAbsListView.getLastVisiblePosition() == localAbsListView.getCount()))
        {
          i = localAbsListView.getChildAt(localAbsListView.getLastVisiblePosition() - localAbsListView.getFirstVisiblePosition()).getBottom();
          j = localAbsListView.getPaddingBottom();
        }
        return i == j;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        return true;
      }
    }
    return ViewCompat.canScrollVertically(this.mTarget, 1);
  }

  public boolean canChildScrollUp()
  {
    if (Build.VERSION.SDK_INT < 14)
    {
      if ((this.mTarget instanceof AbsListView))
      {
        AbsListView localAbsListView = (AbsListView)this.mTarget;
        if ((localAbsListView.getChildCount() <= 0) || ((localAbsListView.getFirstVisiblePosition() <= 0) && (localAbsListView.getChildAt(0).getTop() >= localAbsListView.getPaddingTop())));
      }
      do
      {
        return true;
        return false;
      }
      while (this.mTarget.getScrollY() > 0);
      return false;
    }
    return ViewCompat.canScrollVertically(this.mTarget, -1);
  }

  protected int getChildDrawingOrder(int paramInt1, int paramInt2)
  {
    if (this.mCircleViewIndex < 0);
    do
    {
      return paramInt2;
      if (paramInt2 == paramInt1 - 1)
        return this.mCircleViewIndex;
    }
    while (paramInt2 < this.mCircleViewIndex);
    return paramInt2 + 1;
  }

  public SwipyRefreshLayoutDirection getDirection()
  {
    if (this.mBothDirection)
      return SwipyRefreshLayoutDirection.BOTH;
    return this.mDirection;
  }

  public boolean isRefreshing()
  {
    return this.mRefreshing;
  }

  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
  {
    ensureTarget();
    int i = MotionEventCompat.getActionMasked(paramMotionEvent);
    if ((this.mReturningToStart) && (i == 0))
      this.mReturningToStart = false;
    switch ($SWITCH_TABLE$com$withustudy$koudaizikao$custom$pullrefersh$SwipyRefreshLayoutDirection()[this.mDirection.ordinal()])
    {
    default:
      if ((isEnabled()) && (!this.mReturningToStart) && ((this.mBothDirection) || (!canChildScrollUp())) && (!this.mRefreshing))
        break;
    case 2:
      do
        return false;
      while ((!isEnabled()) || (this.mReturningToStart) || ((!this.mBothDirection) && (canChildScrollDown())) || (this.mRefreshing));
    }
    switch (i)
    {
    case 4:
    case 5:
    default:
    case 0:
    case 2:
    case 6:
    case 1:
    case 3:
    }
    while (true)
    {
      return this.mIsBeingDragged;
      setTargetOffsetTopAndBottom(this.mOriginalOffsetTop - this.mCircleView.getTop(), true);
      this.mActivePointerId = MotionEventCompat.getPointerId(paramMotionEvent, 0);
      this.mIsBeingDragged = false;
      float f3 = getMotionEventY(paramMotionEvent, this.mActivePointerId);
      if (f3 == -1.0F)
        break;
      this.mInitialDownY = f3;
      if (this.mActivePointerId == -1)
        break;
      float f1 = getMotionEventY(paramMotionEvent, this.mActivePointerId);
      if (f1 == -1.0F)
        break;
      if (this.mBothDirection)
      {
        if (f1 > this.mInitialDownY)
          setRawDirection(SwipyRefreshLayoutDirection.TOP);
        while (((this.mDirection == SwipyRefreshLayoutDirection.BOTTOM) && (canChildScrollDown())) || ((this.mDirection == SwipyRefreshLayoutDirection.TOP) && (canChildScrollUp())))
        {
          this.mInitialDownY = f1;
          return false;
          if (f1 >= this.mInitialDownY)
            continue;
          setRawDirection(SwipyRefreshLayoutDirection.BOTTOM);
        }
      }
      float f2;
      switch ($SWITCH_TABLE$com$withustudy$koudaizikao$custom$pullrefersh$SwipyRefreshLayoutDirection()[this.mDirection.ordinal()])
      {
      default:
        f2 = f1 - this.mInitialDownY;
        if ((f2 <= this.mTouchSlop) || (this.mIsBeingDragged))
          continue;
        switch ($SWITCH_TABLE$com$withustudy$koudaizikao$custom$pullrefersh$SwipyRefreshLayoutDirection()[this.mDirection.ordinal()])
        {
        default:
        case 2:
        }
      case 2:
        label380: for (this.mInitialMotionY = (this.mInitialDownY + this.mTouchSlop); ; this.mInitialMotionY = (this.mInitialDownY - this.mTouchSlop))
        {
          this.mIsBeingDragged = true;
          this.mProgress.setAlpha(76);
          break;
          f2 = this.mInitialDownY - f1;
          break label380;
        }
        onSecondaryPointerUp(paramMotionEvent);
        continue;
        this.mIsBeingDragged = false;
        this.mActivePointerId = -1;
      }
    }
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int i = getMeasuredWidth();
    int j = getMeasuredHeight();
    if (getChildCount() == 0);
    do
    {
      return;
      if (this.mTarget != null)
        continue;
      ensureTarget();
    }
    while (this.mTarget == null);
    View localView = this.mTarget;
    int k = getPaddingLeft();
    int m = getPaddingTop();
    int n = i - getPaddingLeft() - getPaddingRight();
    int i1 = j - getPaddingTop() - getPaddingBottom();
    localView.layout(k, m, k + n, m + i1);
    int i2 = this.mCircleView.getMeasuredWidth();
    int i3 = this.mCircleView.getMeasuredHeight();
    this.mCircleView.layout(i / 2 - i2 / 2, this.mCurrentTargetOffsetTop, i / 2 + i2 / 2, i3 + this.mCurrentTargetOffsetTop);
  }

  public void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
    if (this.mTarget == null)
      ensureTarget();
    if (this.mTarget == null)
      return;
    this.mTarget.measure(MeasureSpec.makeMeasureSpec(getMeasuredWidth() - getPaddingLeft() - getPaddingRight(), 1073741824), MeasureSpec.makeMeasureSpec(getMeasuredHeight() - getPaddingTop() - getPaddingBottom(), 1073741824));
    this.mCircleView.measure(MeasureSpec.makeMeasureSpec(this.mCircleWidth, 1073741824), MeasureSpec.makeMeasureSpec(this.mCircleHeight, 1073741824));
    if ((!this.mUsingCustomStart) && (!this.mOriginalOffsetCalculated))
      this.mOriginalOffsetCalculated = true;
    switch ($SWITCH_TABLE$com$withustudy$koudaizikao$custom$pullrefersh$SwipyRefreshLayoutDirection()[this.mDirection.ordinal()])
    {
    default:
      int k = -this.mCircleView.getMeasuredHeight();
      this.mOriginalOffsetTop = k;
      this.mCurrentTargetOffsetTop = k;
      label170: this.mCircleViewIndex = -1;
    case 2:
    }
    for (int i = 0; i < getChildCount(); i++)
    {
      if (getChildAt(i) != this.mCircleView)
        continue;
      this.mCircleViewIndex = i;
      return;
      int j = getMeasuredHeight();
      this.mOriginalOffsetTop = j;
      this.mCurrentTargetOffsetTop = j;
      break label170;
    }
  }

  @SuppressLint({"NewApi"})
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    int i = MotionEventCompat.getActionMasked(paramMotionEvent);
    if ((this.mReturningToStart) && (i == 0))
      this.mReturningToStart = false;
    switch ($SWITCH_TABLE$com$withustudy$koudaizikao$custom$pullrefersh$SwipyRefreshLayoutDirection()[this.mDirection.ordinal()])
    {
    default:
      if ((isEnabled()) && (!this.mReturningToStart) && (!canChildScrollUp()) && (!this.mRefreshing))
        break;
      return false;
    case 2:
      if ((isEnabled()) && (!this.mReturningToStart) && (!canChildScrollDown()) && (!this.mRefreshing))
        break;
      return false;
    }
    switch (i)
    {
    case 4:
    default:
    case 0:
    case 2:
    case 5:
    case 6:
      while (true)
      {
        return true;
        this.mActivePointerId = MotionEventCompat.getPointerId(paramMotionEvent, 0);
        this.mIsBeingDragged = false;
        continue;
        int j = MotionEventCompat.findPointerIndex(paramMotionEvent, this.mActivePointerId);
        if (j < 0)
          return false;
        float f3 = MotionEventCompat.getY(paramMotionEvent, j);
        float f4;
        switch ($SWITCH_TABLE$com$withustudy$koudaizikao$custom$pullrefersh$SwipyRefreshLayoutDirection()[this.mDirection.ordinal()])
        {
        default:
          f4 = 0.5F * (f3 - this.mInitialMotionY);
        case 2:
        }
        float f5;
        while (this.mIsBeingDragged)
        {
          this.mProgress.showArrow(true);
          f5 = f4 / this.mTotalDragDistance;
          if (f5 >= 0.0F)
            break label288;
          return false;
          f4 = 0.5F * (this.mInitialMotionY - f3);
        }
        label288: float f6 = Math.min(1.0F, Math.abs(f5));
        float f7 = 5.0F * (float)Math.max(f6 - 0.4D, 0.0D) / 3.0F;
        float f8 = Math.abs(f4) - this.mTotalDragDistance;
        float f9;
        label352: float f11;
        float f12;
        int k;
        if (this.mUsingCustomStart)
        {
          f9 = this.mSpinnerFinalOffset - this.mOriginalOffsetTop;
          float f10 = Math.max(0.0F, Math.min(f8, 2.0F * f9) / f9);
          f11 = 2.0F * (float)(f10 / 4.0F - Math.pow(f10 / 4.0F, 2.0D));
          f12 = 2.0F * (f9 * f11);
          if (this.mDirection != SwipyRefreshLayoutDirection.TOP)
            break label615;
          k = this.mOriginalOffsetTop + (int)(f12 + f9 * f6);
          label431: if (this.mCircleView.getVisibility() != 0)
            this.mCircleView.setVisibility(0);
          if (!this.mScale)
          {
            ViewCompat.setScaleX(this.mCircleView, 1.0F);
            ViewCompat.setScaleY(this.mCircleView, 1.0F);
          }
          if (f4 >= this.mTotalDragDistance)
            break label634;
          if (this.mScale)
            setAnimationProgress(f4 / this.mTotalDragDistance);
          if ((this.mProgress.getAlpha() > 76) && (!isAnimationRunning(this.mAlphaStartAnimation)))
            startProgressAlphaStartAnimation();
          float f14 = f7 * 0.8F;
          this.mProgress.setStartEndTrim(0.0F, Math.min(0.8F, f14));
          this.mProgress.setArrowScale(Math.min(1.0F, f7));
        }
        while (true)
        {
          float f13 = 0.5F * (-0.25F + 0.4F * f7 + 2.0F * f11);
          this.mProgress.setProgressRotation(f13);
          setTargetOffsetTopAndBottom(k - this.mCurrentTargetOffsetTop, true);
          break;
          f9 = this.mSpinnerFinalOffset;
          break label352;
          label615: k = this.mOriginalOffsetTop - (int)(f12 + f9 * f6);
          break label431;
          label634: if ((this.mProgress.getAlpha() >= 255) || (isAnimationRunning(this.mAlphaMaxAnimation)))
            continue;
          startProgressAlphaMaxAnimation();
        }
        this.mActivePointerId = MotionEventCompat.getPointerId(paramMotionEvent, MotionEventCompat.getActionIndex(paramMotionEvent));
        continue;
        onSecondaryPointerUp(paramMotionEvent);
      }
    case 1:
    case 3:
    }
    if (this.mActivePointerId == -1)
      return false;
    float f1 = MotionEventCompat.getY(paramMotionEvent, MotionEventCompat.findPointerIndex(paramMotionEvent, this.mActivePointerId));
    float f2;
    switch ($SWITCH_TABLE$com$withustudy$koudaizikao$custom$pullrefersh$SwipyRefreshLayoutDirection()[this.mDirection.ordinal()])
    {
    default:
      f2 = 0.5F * (f1 - this.mInitialMotionY);
      this.mIsBeingDragged = false;
      if (f2 <= this.mTotalDragDistance)
        break;
      setRefreshing(true, true);
    case 2:
    }
    while (true)
    {
      this.mActivePointerId = -1;
      return false;
      f2 = 0.5F * (this.mInitialMotionY - f1);
      break;
      this.mRefreshing = false;
      this.mProgress.setStartEndTrim(0.0F, 0.0F);
      boolean bool = this.mScale;
      7 local7 = null;
      if (!bool)
        local7 = new AnimationListener()
        {
          public void onAnimationEnd(Animation paramAnimation)
          {
            if (!SwipyRefreshLayout.this.mScale)
              SwipyRefreshLayout.this.startScaleDownAnimation(null);
          }

          public void onAnimationRepeat(Animation paramAnimation)
          {
          }

          public void onAnimationStart(Animation paramAnimation)
          {
          }
        };
      animateOffsetToStartPosition(this.mCurrentTargetOffsetTop, local7);
      this.mProgress.showArrow(false);
    }
  }

  public void requestDisallowInterceptTouchEvent(boolean paramBoolean)
  {
  }

  @Deprecated
  public void setColorScheme(int[] paramArrayOfInt)
  {
    setColorSchemeResources(paramArrayOfInt);
  }

  public void setColorSchemeColors(int[] paramArrayOfInt)
  {
    ensureTarget();
    this.mProgress.setColorSchemeColors(paramArrayOfInt);
  }

  public void setColorSchemeResources(int[] paramArrayOfInt)
  {
    Resources localResources = getResources();
    int[] arrayOfInt = new int[paramArrayOfInt.length];
    for (int i = 0; ; i++)
    {
      if (i >= paramArrayOfInt.length)
      {
        setColorSchemeColors(arrayOfInt);
        return;
      }
      arrayOfInt[i] = localResources.getColor(paramArrayOfInt[i]);
    }
  }

  public void setDirection(SwipyRefreshLayoutDirection paramSwipyRefreshLayoutDirection)
  {
    if (paramSwipyRefreshLayoutDirection == SwipyRefreshLayoutDirection.BOTH)
      this.mBothDirection = true;
    while (true)
      switch ($SWITCH_TABLE$com$withustudy$koudaizikao$custom$pullrefersh$SwipyRefreshLayoutDirection()[this.mDirection.ordinal()])
      {
      default:
        int j = -this.mCircleView.getMeasuredHeight();
        this.mOriginalOffsetTop = j;
        this.mCurrentTargetOffsetTop = j;
        return;
        this.mBothDirection = false;
        this.mDirection = paramSwipyRefreshLayoutDirection;
      case 2:
      }
    int i = getMeasuredHeight();
    this.mOriginalOffsetTop = i;
    this.mCurrentTargetOffsetTop = i;
  }

  public void setDistanceToTriggerSync(int paramInt)
  {
    this.mTotalDragDistance = paramInt;
  }

  public void setOnRefreshListener(OnRefreshListener paramOnRefreshListener)
  {
    this.mListener = paramOnRefreshListener;
  }

  public void setProgressBackgroundColor(int paramInt)
  {
    this.mCircleView.setBackgroundColor(paramInt);
    this.mProgress.setBackgroundColor(getResources().getColor(paramInt));
  }

  public void setRefreshing(boolean paramBoolean)
  {
    if ((paramBoolean) && (this.mRefreshing != paramBoolean))
    {
      this.mRefreshing = paramBoolean;
      int i;
      if (!this.mUsingCustomStart)
        switch ($SWITCH_TABLE$com$withustudy$koudaizikao$custom$pullrefersh$SwipyRefreshLayoutDirection()[this.mDirection.ordinal()])
        {
        default:
          i = (int)(this.mSpinnerFinalOffset - Math.abs(this.mOriginalOffsetTop));
        case 2:
        }
      while (true)
      {
        setTargetOffsetTopAndBottom(i - this.mCurrentTargetOffsetTop, true);
        this.mNotify = false;
        startScaleUpAnimation(this.mRefreshListener);
        return;
        i = getMeasuredHeight() - (int)this.mSpinnerFinalOffset;
        continue;
        i = (int)this.mSpinnerFinalOffset;
      }
    }
    setRefreshing(paramBoolean, false);
  }

  public void setSize(int paramInt)
  {
    if ((paramInt != 0) && (paramInt != 1))
      return;
    DisplayMetrics localDisplayMetrics = getResources().getDisplayMetrics();
    int j;
    if (paramInt == 0)
    {
      j = (int)(56.0F * localDisplayMetrics.density);
      this.mCircleWidth = j;
    }
    int i;
    for (this.mCircleHeight = j; ; this.mCircleHeight = i)
    {
      this.mCircleView.setImageDrawable(null);
      this.mProgress.updateSizes(paramInt);
      this.mCircleView.setImageDrawable(this.mProgress);
      return;
      i = (int)(40.0F * localDisplayMetrics.density);
      this.mCircleWidth = i;
    }
  }

  public static abstract interface OnRefreshListener
  {
    public abstract void onRefresh(SwipyRefreshLayoutDirection paramSwipyRefreshLayoutDirection);
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.custom.pullrefersh.SwipyRefreshLayout
 * JD-Core Version:    0.6.0
 */