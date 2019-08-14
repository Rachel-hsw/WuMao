package com.withustudy.koudaizikao.custom;

import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.SparseIntArray;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListAdapter;
import android.widget.ListView;
import java.util.BitSet;

public abstract class AbstractSlideExpandableListAdapter extends WrapperListAdapterImpl
{
  private int animationDuration = 330;
  private OnItemExpandCollapseListener expandCollapseListener;
  private View lastOpen = null;
  private int lastOpenPosition = -1;
  private BitSet openItems = new BitSet();
  private ViewGroup parent;
  private final SparseIntArray viewHeights = new SparseIntArray(10);

  public AbstractSlideExpandableListAdapter(ListAdapter paramListAdapter)
  {
    super(paramListAdapter);
  }

  private void animateView(View paramView, int paramInt)
  {
    ExpandCollapseAnimation localExpandCollapseAnimation = new ExpandCollapseAnimation(paramView, paramInt);
    localExpandCollapseAnimation.setDuration(getAnimationDuration());
    localExpandCollapseAnimation.setAnimationListener(new AnimationListener(paramInt, paramView)
    {
      public void onAnimationEnd(Animation paramAnimation)
      {
        ListView localListView;
        int i;
        Rect localRect1;
        Rect localRect2;
        if ((this.val$type == 0) && ((AbstractSlideExpandableListAdapter.this.parent instanceof ListView)))
        {
          localListView = (ListView)AbstractSlideExpandableListAdapter.this.parent;
          i = this.val$target.getBottom();
          localRect1 = new Rect();
          boolean bool = this.val$target.getGlobalVisibleRect(localRect1);
          localRect2 = new Rect();
          localListView.getGlobalVisibleRect(localRect2);
          if (bool)
            break label93;
          localListView.smoothScrollBy(i, AbstractSlideExpandableListAdapter.this.getAnimationDuration());
        }
        label93: 
        do
          return;
        while (localRect2.bottom != localRect1.bottom);
        localListView.smoothScrollBy(i, AbstractSlideExpandableListAdapter.this.getAnimationDuration());
      }

      public void onAnimationRepeat(Animation paramAnimation)
      {
      }

      public void onAnimationStart(Animation paramAnimation)
      {
      }
    });
    paramView.startAnimation(localExpandCollapseAnimation);
  }

  private void enableFor(View paramView1, View paramView2, int paramInt)
  {
    if ((paramView2 == this.lastOpen) && (paramInt != this.lastOpenPosition))
      this.lastOpen = null;
    if (paramInt == this.lastOpenPosition)
      this.lastOpen = paramView2;
    if (this.viewHeights.get(paramInt, -1) == -1)
    {
      this.viewHeights.put(paramInt, paramView2.getMeasuredHeight());
      updateExpandable(paramView2, paramInt);
    }
    while (true)
    {
      paramView1.setOnClickListener(new OnClickListener(paramView2, paramInt)
      {
        public void onClick(View paramView)
        {
          Animation localAnimation = this.val$target.getAnimation();
          if ((localAnimation != null) && (localAnimation.hasStarted()) && (!localAnimation.hasEnded()))
          {
            localAnimation.setAnimationListener(new AnimationListener(paramView)
            {
              public void onAnimationEnd(Animation paramAnimation)
              {
                this.val$view.performClick();
              }

              public void onAnimationRepeat(Animation paramAnimation)
              {
              }

              public void onAnimationStart(Animation paramAnimation)
              {
              }
            });
            return;
          }
          this.val$target.setAnimation(null);
          int i;
          if (this.val$target.getVisibility() == 0)
          {
            i = 1;
            if (i != 0)
              break label229;
            AbstractSlideExpandableListAdapter.this.openItems.set(this.val$position, true);
            label79: if (i != 0)
              break label247;
            if ((AbstractSlideExpandableListAdapter.this.lastOpenPosition != -1) && (AbstractSlideExpandableListAdapter.this.lastOpenPosition != this.val$position))
            {
              if (AbstractSlideExpandableListAdapter.this.lastOpen != null)
              {
                AbstractSlideExpandableListAdapter.this.animateView(AbstractSlideExpandableListAdapter.this.lastOpen, 1);
                AbstractSlideExpandableListAdapter.this.notifiyExpandCollapseListener(1, AbstractSlideExpandableListAdapter.this.lastOpen, AbstractSlideExpandableListAdapter.this.lastOpenPosition);
              }
              AbstractSlideExpandableListAdapter.this.openItems.set(AbstractSlideExpandableListAdapter.this.lastOpenPosition, false);
            }
            AbstractSlideExpandableListAdapter.this.lastOpen = this.val$target;
            AbstractSlideExpandableListAdapter.this.lastOpenPosition = this.val$position;
          }
          while (true)
          {
            AbstractSlideExpandableListAdapter.this.animateView(this.val$target, i);
            AbstractSlideExpandableListAdapter.this.notifiyExpandCollapseListener(i, this.val$target, this.val$position);
            return;
            i = 0;
            break;
            label229: AbstractSlideExpandableListAdapter.this.openItems.set(this.val$position, false);
            break label79;
            label247: if (AbstractSlideExpandableListAdapter.this.lastOpenPosition != this.val$position)
              continue;
            AbstractSlideExpandableListAdapter.this.lastOpenPosition = -1;
          }
        }
      });
      return;
      updateExpandable(paramView2, paramInt);
    }
  }

  private void notifiyExpandCollapseListener(int paramInt1, View paramView, int paramInt2)
  {
    if (this.expandCollapseListener != null)
    {
      if (paramInt1 != 0)
        break label23;
      this.expandCollapseListener.onExpand(paramView, paramInt2);
    }
    label23: 
    do
      return;
    while (paramInt1 != 1);
    this.expandCollapseListener.onCollapse(paramView, paramInt2);
  }

  private static BitSet readBitSet(Parcel paramParcel)
  {
    BitSet localBitSet = new BitSet();
    if (paramParcel == null);
    while (true)
    {
      return localBitSet;
      int i = paramParcel.readInt();
      for (int j = 0; j < i; j++)
        localBitSet.set(paramParcel.readInt());
    }
  }

  private void updateExpandable(View paramView, int paramInt)
  {
    LinearLayout.LayoutParams localLayoutParams = (LinearLayout.LayoutParams)paramView.getLayoutParams();
    if (this.openItems.get(paramInt))
    {
      paramView.setVisibility(0);
      localLayoutParams.bottomMargin = 0;
      return;
    }
    paramView.setVisibility(8);
    localLayoutParams.bottomMargin = (0 - this.viewHeights.get(paramInt));
  }

  private static void writeBitSet(Parcel paramParcel, BitSet paramBitSet)
  {
    int i = -1;
    if ((paramParcel == null) || (paramBitSet == null))
      return;
    paramParcel.writeInt(paramBitSet.cardinality());
    while (true)
    {
      i = paramBitSet.nextSetBit(i + 1);
      if (i == -1)
        break;
      paramParcel.writeInt(i);
    }
  }

  public boolean collapseLastOpen()
  {
    if (isAnyItemExpanded())
    {
      if (this.lastOpen != null)
        animateView(this.lastOpen, 1);
      this.openItems.set(this.lastOpenPosition, false);
      this.lastOpenPosition = -1;
      return true;
    }
    return false;
  }

  public void enableFor(View paramView, int paramInt)
  {
    View localView1 = getExpandToggleButton(paramView);
    View localView2 = getExpandableView(paramView);
    localView2.measure(paramView.getWidth(), paramView.getHeight());
    enableFor(localView1, localView2, paramInt);
    localView2.requestLayout();
  }

  public int getAnimationDuration()
  {
    return this.animationDuration;
  }

  public abstract View getExpandToggleButton(View paramView);

  public abstract View getExpandableView(View paramView);

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    this.parent = paramViewGroup;
    View localView = this.wrapped.getView(paramInt, paramView, paramViewGroup);
    enableFor(localView, paramInt);
    return localView;
  }

  public boolean isAnyItemExpanded()
  {
    return this.lastOpenPosition != -1;
  }

  public void onRestoreInstanceState(SavedState paramSavedState)
  {
    if (paramSavedState != null)
    {
      this.lastOpenPosition = paramSavedState.lastOpenPosition;
      this.openItems = paramSavedState.openItems;
    }
  }

  public Parcelable onSaveInstanceState(Parcelable paramParcelable)
  {
    SavedState localSavedState = new SavedState(paramParcelable);
    localSavedState.lastOpenPosition = this.lastOpenPosition;
    localSavedState.openItems = this.openItems;
    return localSavedState;
  }

  public void removeItemExpandCollapseListener()
  {
    this.expandCollapseListener = null;
  }

  public void setAnimationDuration(int paramInt)
  {
    if (paramInt < 0)
      throw new IllegalArgumentException("Duration is less than zero");
    this.animationDuration = paramInt;
  }

  public void setItemExpandCollapseListener(OnItemExpandCollapseListener paramOnItemExpandCollapseListener)
  {
    this.expandCollapseListener = paramOnItemExpandCollapseListener;
  }

  public static abstract interface OnItemExpandCollapseListener
  {
    public abstract void onCollapse(View paramView, int paramInt);

    public abstract void onExpand(View paramView, int paramInt);
  }

  static class SavedState extends BaseSavedState
  {
    public static final Creator<SavedState> CREATOR = new Creator()
    {
      public SavedState createFromParcel(Parcel paramParcel)
      {
        return new SavedState(paramParcel, null);
      }

      public SavedState[] newArray(int paramInt)
      {
        return new SavedState[paramInt];
      }
    };
    public int lastOpenPosition = -1;
    public BitSet openItems = null;

    private SavedState(Parcel paramParcel)
    {
      super();
      this.lastOpenPosition = paramParcel.readInt();
      this.openItems = AbstractSlideExpandableListAdapter.access$0(paramParcel);
    }

    SavedState(Parcelable paramParcelable)
    {
      super();
    }

    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      super.writeToParcel(paramParcel, paramInt);
      paramParcel.writeInt(this.lastOpenPosition);
      AbstractSlideExpandableListAdapter.access$1(paramParcel, this.openItems);
    }
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.custom.AbstractSlideExpandableListAdapter
 * JD-Core Version:    0.6.0
 */