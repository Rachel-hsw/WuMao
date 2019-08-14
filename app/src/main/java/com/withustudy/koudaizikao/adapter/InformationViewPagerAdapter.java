package com.withustudy.koudaizikao.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import com.android.http.FileDownLoad;
import com.withustudy.koudaizikao.entity.News;
import java.util.ArrayList;
import java.util.List;

public class InformationViewPagerAdapter extends PagerAdapter
{
  private List<News> imageList;
  private List<ImageView> listViews;
  private Context mContext;

  public InformationViewPagerAdapter(Context paramContext, List<ImageView> paramList, List<News> paramList1)
  {
    this.mContext = paramContext;
    this.listViews = paramList;
    this.imageList = paramList1;
  }

  public void destroyItem(View paramView, int paramInt, Object paramObject)
  {
  }

  public int getCount()
  {
    return 2147483647;
  }

  public int getListCount()
  {
    return this.listViews.size();
  }

  public Object instantiateItem(ViewGroup paramViewGroup, int paramInt)
  {
    int i = paramInt % this.listViews.size();
    if (i < 0)
      i += this.listViews.size();
    ImageView localImageView = (ImageView)this.listViews.get(i);
    ViewParent localViewParent = localImageView.getParent();
    if (localViewParent != null)
      ((ViewGroup)localViewParent).removeView(localImageView);
    FileDownLoad.getInstance(this.mContext).downLoadImage(((News)this.imageList.get(i)).getAttachUrl(), localImageView);
    paramViewGroup.addView(localImageView);
    return localImageView;
  }

  public boolean isViewFromObject(View paramView, Object paramObject)
  {
    return paramView == paramObject;
  }

  public void setListViews(ArrayList<ImageView> paramArrayList)
  {
    this.listViews = paramArrayList;
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.adapter.InformationViewPagerAdapter
 * JD-Core Version:    0.6.0
 */