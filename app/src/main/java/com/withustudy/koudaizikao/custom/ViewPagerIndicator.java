package com.withustudy.koudaizikao.custom;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.withustudy.koudaizikao.R.styleable;
import java.util.Iterator;
import java.util.List;

public class ViewPagerIndicator extends LinearLayout
{
  private static final int COLOR_TEXT_HIGHLIGHTCOLOR = 2599386;
  private static final int COLOR_TEXT_NORMAL = 2013265919;
  private static final int COUNT_DEFAULT_TAB = 2;
  private static final float RADIO_TRIANGEL = 0.1666667F;
  private final int DIMENSION_TRIANGEL_WIDTH = (int)(0.1666667F * (getScreenWidth() / 3));
  private int mInitTranslationX;
  private Paint mPaint;
  private Path mPath;
  private List<String> mTabTitles;
  private int mTabVisibleCount = 2;
  private float mTranslationX;
  private int mTriangleHeight;
  private int mTriangleWidth;
  public ViewPager mViewPager;
  private PageChangeListener onPageChangeListener;

  public ViewPagerIndicator(Context paramContext)
  {
    this(paramContext, null);
  }

  public ViewPagerIndicator(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    TypedArray localTypedArray = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.ViewPagerIndicator);
    this.mTabVisibleCount = localTypedArray.getInt(0, 2);
    if (this.mTabVisibleCount < 0)
      this.mTabVisibleCount = 2;
    localTypedArray.recycle();
    this.mPaint = new Paint();
    this.mPaint.setAntiAlias(true);
    this.mPaint.setColor(Color.parseColor("#ffffffff"));
    this.mPaint.setStyle(Style.FILL);
    this.mPaint.setPathEffect(new CornerPathEffect(3.0F));
  }

  private TextView generateTextView(String paramString)
  {
    TextView localTextView = new TextView(getContext());
    LayoutParams localLayoutParams = new LayoutParams(-1, -1);
    localLayoutParams.width = (getScreenWidth() / this.mTabVisibleCount);
    localTextView.setGravity(17);
    localTextView.setTextColor(getResources().getColor(2131230765));
    localTextView.setText(paramString);
    localTextView.setTextSize(2, 16.0F);
    localTextView.setLayoutParams(localLayoutParams);
    return localTextView;
  }

  private void initTriangle()
  {
    this.mPath = new Path();
    this.mTriangleHeight = (int)(this.mTriangleWidth / 2 / Math.sqrt(2.0D));
    this.mPath.moveTo(0.0F, 0.0F);
    this.mPath.lineTo(this.mTriangleWidth, 0.0F);
    this.mPath.lineTo(this.mTriangleWidth / 2, -this.mTriangleHeight);
    this.mPath.close();
  }

  private void resetTextViewColor()
  {
    for (int i = 0; ; i++)
    {
      if (i >= getChildCount())
        return;
      View localView = getChildAt(i);
      if (!(localView instanceof TextView))
        continue;
      ((TextView)localView).setTextColor(getResources().getColor(2131230765));
    }
  }

  protected void dispatchDraw(Canvas paramCanvas)
  {
    paramCanvas.save();
    paramCanvas.translate(this.mInitTranslationX + this.mTranslationX, 1 + getHeight());
    paramCanvas.drawPath(this.mPath, this.mPaint);
    paramCanvas.restore();
    super.dispatchDraw(paramCanvas);
  }

  public int getScreenWidth()
  {
    WindowManager localWindowManager = (WindowManager)getContext().getSystemService("window");
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    localWindowManager.getDefaultDisplay().getMetrics(localDisplayMetrics);
    return localDisplayMetrics.widthPixels;
  }

  protected void highLightTextView(int paramInt)
  {
    View localView = getChildAt(paramInt);
    if ((localView instanceof TextView))
      ((TextView)localView).setTextColor(getResources().getColor(2131230764));
  }

  protected void onFinishInflate()
  {
    Log.e("TAG", "onFinishInflate");
    super.onFinishInflate();
    int i = getChildCount();
    if (i == 0)
      return;
    for (int j = 0; ; j++)
    {
      if (j >= i)
      {
        setItemClickEvent();
        return;
      }
      View localView = getChildAt(j);
      LayoutParams localLayoutParams = (LayoutParams)localView.getLayoutParams();
      localLayoutParams.weight = 0.0F;
      localLayoutParams.width = (getScreenWidth() / this.mTabVisibleCount);
      localView.setLayoutParams(localLayoutParams);
    }
  }

  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    this.mTriangleWidth = (int)(0.1666667F * (paramInt1 / this.mTabVisibleCount));
    this.mTriangleWidth = Math.min(this.DIMENSION_TRIANGEL_WIDTH, this.mTriangleWidth);
    initTriangle();
    this.mInitTranslationX = (getWidth() / this.mTabVisibleCount / 2 - this.mTriangleWidth / 2);
  }

  public void scroll(int paramInt, float paramFloat)
  {
    this.mTranslationX = (getWidth() / this.mTabVisibleCount * (paramFloat + paramInt));
    int i = getScreenWidth() / this.mTabVisibleCount;
    if ((paramFloat > 0.0F) && (paramInt >= -2 + this.mTabVisibleCount) && (getChildCount() > this.mTabVisibleCount))
    {
      if (this.mTabVisibleCount == 1)
        break label92;
      scrollTo(i * (paramInt - (-2 + this.mTabVisibleCount)) + (int)(paramFloat * i), 0);
    }
    while (true)
    {
      invalidate();
      return;
      label92: scrollTo(paramInt * i + (int)(paramFloat * i), 0);
    }
  }

  public void setItemClickEvent()
  {
    int i = getChildCount();
    for (int j = 0; ; j++)
    {
      if (j >= i)
        return;
      int k = j;
      getChildAt(j).setOnClickListener(new OnClickListener(k)
      {
        public void onClick(View paramView)
        {
          ViewPagerIndicator.this.mViewPager.setCurrentItem(this.val$j);
        }
      });
    }
  }

  public void setOnPageChangeListener(PageChangeListener paramPageChangeListener)
  {
    this.onPageChangeListener = paramPageChangeListener;
  }

  public void setTabItemTitles(List<String> paramList)
  {
    Iterator localIterator;
    if ((paramList != null) && (paramList.size() > 0))
    {
      removeAllViews();
      this.mTabTitles = paramList;
      localIterator = this.mTabTitles.iterator();
    }
    while (true)
    {
      if (!localIterator.hasNext())
      {
        setItemClickEvent();
        return;
      }
      addView(generateTextView((String)localIterator.next()));
    }
  }

  public void setViewPager(ViewPager paramViewPager, int paramInt)
  {
    this.mViewPager = paramViewPager;
    paramViewPager.setOnPageChangeListener(new OnPageChangeListener()
    {
      public void onPageScrollStateChanged(int paramInt)
      {
        if (ViewPagerIndicator.this.onPageChangeListener != null)
          ViewPagerIndicator.this.onPageChangeListener.onPageScrollStateChanged(paramInt);
      }

      public void onPageScrolled(int paramInt1, float paramFloat, int paramInt2)
      {
        ViewPagerIndicator.this.scroll(paramInt1, paramFloat);
        if (ViewPagerIndicator.this.onPageChangeListener != null)
          ViewPagerIndicator.this.onPageChangeListener.onPageScrolled(paramInt1, paramFloat, paramInt2);
      }

      public void onPageSelected(int paramInt)
      {
        ViewPagerIndicator.this.resetTextViewColor();
        ViewPagerIndicator.this.highLightTextView(paramInt);
        if (ViewPagerIndicator.this.onPageChangeListener != null)
          ViewPagerIndicator.this.onPageChangeListener.onPageSelected(paramInt);
      }
    });
    paramViewPager.setCurrentItem(paramInt);
    highLightTextView(paramInt);
  }

  public void setVisibleTabCount(int paramInt)
  {
    this.mTabVisibleCount = paramInt;
  }

  public static abstract interface PageChangeListener
  {
    public abstract void onPageScrollStateChanged(int paramInt);

    public abstract void onPageScrolled(int paramInt1, float paramFloat, int paramInt2);

    public abstract void onPageSelected(int paramInt);
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.custom.ViewPagerIndicator
 * JD-Core Version:    0.6.0
 */