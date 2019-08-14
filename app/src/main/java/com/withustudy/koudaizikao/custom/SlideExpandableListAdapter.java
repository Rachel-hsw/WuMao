package com.withustudy.koudaizikao.custom;

import android.view.View;
import android.widget.ListAdapter;

public class SlideExpandableListAdapter extends AbstractSlideExpandableListAdapter
{
  private int expandable_view_id;
  private int toggle_button_id;

  public SlideExpandableListAdapter(ListAdapter paramListAdapter)
  {
    this(paramListAdapter, 2131099651, 2131099652);
  }

  public SlideExpandableListAdapter(ListAdapter paramListAdapter, int paramInt1, int paramInt2)
  {
    super(paramListAdapter);
    this.toggle_button_id = paramInt1;
    this.expandable_view_id = paramInt2;
  }

  public View getExpandToggleButton(View paramView)
  {
    return paramView.findViewById(this.toggle_button_id);
  }

  public View getExpandableView(View paramView)
  {
    return paramView.findViewById(this.expandable_view_id);
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.custom.SlideExpandableListAdapter
 * JD-Core Version:    0.6.0
 */