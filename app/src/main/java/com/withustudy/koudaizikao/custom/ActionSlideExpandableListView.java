package com.withustudy.koudaizikao.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ListAdapter;

public class ActionSlideExpandableListView extends SlideExpandableListView
{
  private int[] buttonIds = null;
  private OnActionClickListener listener;

  public ActionSlideExpandableListView(Context paramContext)
  {
    super(paramContext);
  }

  public ActionSlideExpandableListView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public ActionSlideExpandableListView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }

  public void setAdapter(ListAdapter paramListAdapter)
  {
    super.setAdapter(new WrapperListAdapterImpl(paramListAdapter)
    {
      public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
      {
        View localView1 = this.wrapped.getView(paramInt, paramView, paramViewGroup);
        int[] arrayOfInt;
        int i;
        if ((ActionSlideExpandableListView.this.buttonIds != null) && (localView1 != null))
        {
          arrayOfInt = ActionSlideExpandableListView.this.buttonIds;
          i = arrayOfInt.length;
        }
        for (int j = 0; ; j++)
        {
          if (j >= i)
            return localView1;
          int k = arrayOfInt[j];
          View localView2 = localView1.findViewById(k);
          if (localView2 == null)
            continue;
          localView2.findViewById(k).setOnClickListener(new OnClickListener(localView1, paramInt)
          {
            public void onClick(View paramView)
            {
              if (ActionSlideExpandableListView.this.listener != null)
                ActionSlideExpandableListView.this.listener.onClick(this.val$listView, paramView, this.val$position);
            }
          });
        }
      }
    });
  }

  public void setItemActionListener(OnActionClickListener paramOnActionClickListener, int[] paramArrayOfInt)
  {
    this.listener = paramOnActionClickListener;
    this.buttonIds = paramArrayOfInt;
  }

  public static abstract interface OnActionClickListener
  {
    public abstract void onClick(View paramView1, View paramView2, int paramInt);
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.custom.ActionSlideExpandableListView
 * JD-Core Version:    0.6.0
 */