package com.withustudy.koudaizikao.commen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.List;

public abstract class CommonAdapter<T> extends BaseAdapter
{
  protected Context mContext;
  protected List<T> mDatas;
  protected LayoutInflater mInflater;
  protected final int mItemLayoutId;

  public CommonAdapter(Context paramContext, List<T> paramList, int paramInt)
  {
    this.mContext = paramContext;
    this.mInflater = LayoutInflater.from(this.mContext);
    this.mDatas = paramList;
    this.mItemLayoutId = paramInt;
  }

  private ViewHolder getViewHolder(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    return ViewHolder.get(this.mContext, paramView, paramViewGroup, this.mItemLayoutId, paramInt);
  }

  public abstract void convert(ViewHolder paramViewHolder, T paramT);

  public int getCount()
  {
    return this.mDatas.size();
  }

  public T getItem(int paramInt)
  {
    return this.mDatas.get(paramInt);
  }

  public long getItemId(int paramInt)
  {
    return paramInt;
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    ViewHolder localViewHolder = getViewHolder(paramInt, paramView, paramViewGroup);
    convert(localViewHolder, getItem(paramInt));
    return localViewHolder.getConvertView();
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.commen.CommonAdapter
 * JD-Core Version:    0.6.0
 */