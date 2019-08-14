package com.withustudy.koudaizikao.custom;

import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

public abstract class FragmentpaperViewAdapater extends PagerAdapter
{
  private static final boolean DEBUG = true;
  private static final String TAG = "FragmentPagerAdapter";
  private BeforeShowListenner beforeShowListenner;
  private final FragmentManager mFragmentManager;

  public FragmentpaperViewAdapater(FragmentManager paramFragmentManager)
  {
    this.mFragmentManager = paramFragmentManager;
  }

  private static String makeFragmentName(int paramInt1, int paramInt2)
  {
    return "android:switcher:" + paramInt1 + ":" + paramInt2;
  }

  public void destroyItem(ViewGroup paramViewGroup, int paramInt, Object paramObject)
  {
    paramViewGroup.removeView((View)paramObject);
  }

  public abstract Fragment getItem(int paramInt);

  public Object instantiateItem(ViewGroup paramViewGroup, int paramInt)
  {
    Fragment localFragment = getItem(paramInt);
    if (!localFragment.isAdded())
    {
      FragmentTransaction localFragmentTransaction = this.mFragmentManager.beginTransaction();
      localFragmentTransaction.add(localFragment, localFragment.getClass().getName());
      localFragmentTransaction.commitAllowingStateLoss();
      this.mFragmentManager.executePendingTransactions();
    }
    if (localFragment.getView().getParent() == null)
    {
      if (this.beforeShowListenner != null)
        this.beforeShowListenner.beforeShowListenner(paramInt);
      paramViewGroup.addView(localFragment.getView());
    }
    return localFragment.getView();
  }

  public boolean isViewFromObject(View paramView, Object paramObject)
  {
    return paramView == paramObject;
  }

  public void restoreState(Parcelable paramParcelable, ClassLoader paramClassLoader)
  {
  }

  public Parcelable saveState()
  {
    return null;
  }

  public void setBeforeShowListenner(BeforeShowListenner paramBeforeShowListenner)
  {
    this.beforeShowListenner = paramBeforeShowListenner;
  }

  public void startUpdate(ViewGroup paramViewGroup)
  {
  }

  public static abstract interface BeforeShowListenner
  {
    public abstract void beforeShowListenner(int paramInt);
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.custom.FragmentpaperViewAdapater
 * JD-Core Version:    0.6.0
 */