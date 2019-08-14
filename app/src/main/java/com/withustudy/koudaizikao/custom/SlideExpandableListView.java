package com.withustudy.koudaizikao.custom;

import android.content.Context;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;

class SlideExpandableListView extends ListView
{
  private SlideExpandableListAdapter adapter;

  public SlideExpandableListView(Context paramContext)
  {
    super(paramContext);
  }

  public SlideExpandableListView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public SlideExpandableListView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }

  public boolean collapse()
  {
    if (this.adapter != null)
      return this.adapter.collapseLastOpen();
    return false;
  }

  public void enableExpandOnItemClick()
  {
    setOnItemClickListener(new OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
      {
        ((SlideExpandableListAdapter)SlideExpandableListView.this.getAdapter()).getExpandToggleButton(paramView).performClick();
      }
    });
  }

  public void onRestoreInstanceState(Parcelable paramParcelable)
  {
    if (!(paramParcelable instanceof AbstractSlideExpandableListAdapter.SavedState))
    {
      super.onRestoreInstanceState(paramParcelable);
      return;
    }
    AbstractSlideExpandableListAdapter.SavedState localSavedState = (AbstractSlideExpandableListAdapter.SavedState)paramParcelable;
    super.onRestoreInstanceState(localSavedState.getSuperState());
    this.adapter.onRestoreInstanceState(localSavedState);
  }

  public Parcelable onSaveInstanceState()
  {
    return this.adapter.onSaveInstanceState(super.onSaveInstanceState());
  }

  public void setAdapter(ListAdapter paramListAdapter)
  {
    this.adapter = new SlideExpandableListAdapter(paramListAdapter);
    super.setAdapter(this.adapter);
  }

  public void setAdapter(ListAdapter paramListAdapter, int paramInt1, int paramInt2)
  {
    this.adapter = new SlideExpandableListAdapter(paramListAdapter, paramInt1, paramInt2);
    super.setAdapter(this.adapter);
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.custom.SlideExpandableListView
 * JD-Core Version:    0.6.0
 */