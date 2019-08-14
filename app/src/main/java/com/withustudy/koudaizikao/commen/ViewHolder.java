package com.withustudy.koudaizikao.commen;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewHolder
{
  private View mConvertView;
  private int mPosition;
  private final SparseArray<View> mViews;

  private ViewHolder(Context paramContext, ViewGroup paramViewGroup, int paramInt1, int paramInt2)
  {
    this.mPosition = paramInt2;
    this.mViews = new SparseArray();
    this.mConvertView = LayoutInflater.from(paramContext).inflate(paramInt1, paramViewGroup, false);
    this.mConvertView.setTag(this);
  }

  public static ViewHolder get(Context paramContext, View paramView, ViewGroup paramViewGroup, int paramInt1, int paramInt2)
  {
    if (paramView == null)
      return new ViewHolder(paramContext, paramViewGroup, paramInt1, paramInt2);
    return (ViewHolder)paramView.getTag();
  }

  public View getConvertView()
  {
    return this.mConvertView;
  }

  public int getPosition()
  {
    return this.mPosition;
  }

  public <T extends View> T getView(int paramInt)
  {
    View localView = (View)this.mViews.get(paramInt);
    if (localView == null)
    {
      localView = this.mConvertView.findViewById(paramInt);
      this.mViews.put(paramInt, localView);
    }
    return localView;
  }

  public ViewHolder setImageBitmap(int paramInt, Bitmap paramBitmap)
  {
    ((ImageView)getView(paramInt)).setImageBitmap(paramBitmap);
    return this;
  }

  public ViewHolder setImageResource(int paramInt1, int paramInt2)
  {
    ((ImageView)getView(paramInt1)).setImageResource(paramInt2);
    return this;
  }

  public ViewHolder setText(int paramInt, String paramString)
  {
    ((TextView)getView(paramInt)).setText(paramString);
    return this;
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.commen.ViewHolder
 * JD-Core Version:    0.6.0
 */