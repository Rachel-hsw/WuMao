package com.withustudy.koudaizikao.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import com.android.http.FileDownLoad;
import com.withustudy.koudaizikao.config.KouDaiSP;
import java.util.List;

public class BbsGridMainAdapter extends BaseAdapter
{
  private List<String> imageUrl;
  private Context mContext;

  public BbsGridMainAdapter(Context paramContext, List<String> paramList)
  {
    this.mContext = paramContext;
    this.imageUrl = paramList;
  }

  public int getCount()
  {
    return this.imageUrl.size();
  }

  public Object getItem(int paramInt)
  {
    return this.imageUrl.get(paramInt);
  }

  public long getItemId(int paramInt)
  {
    return paramInt;
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    ImageView localImageView;
    if (paramView == null)
    {
      paramView = new LinearLayout(this.mContext);
      localImageView = new ImageView(this.mContext);
      localImageView.setLayoutParams(new LayoutParams(-2, (KouDaiSP.getInstance(this.mContext).getWidth() - (int)(30.0F * KouDaiSP.getInstance(this.mContext).getDensity() / 160.0F)) / 3));
      localImageView.setScaleType(ScaleType.CENTER_CROP);
      ((LinearLayout)paramView).addView(localImageView);
      paramView.setTag(localImageView);
    }
    while (true)
    {
      localImageView.setBackgroundResource(2130837673);
      FileDownLoad.getInstance(this.mContext).downLoadImage((String)this.imageUrl.get(paramInt), localImageView);
      return paramView;
      localImageView = (ImageView)paramView.getTag();
    }
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.adapter.BbsGridMainAdapter
 * JD-Core Version:    0.6.0
 */