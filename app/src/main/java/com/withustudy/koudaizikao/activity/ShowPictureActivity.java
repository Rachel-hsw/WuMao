package com.withustudy.koudaizikao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView.ScaleType;
import com.android.http.FileDownLoad;
import com.withustudy.koudaizikao.base.AbsBaseActivity;
import java.util.ArrayList;
import java.util.List;
import uk.co.senab.photoview.PhotoView;

public class ShowPictureActivity extends AbsBaseActivity
{
  public static final String CURRENTITEM_KEY = "currentitem_key";
  public static final String IMAGE_NAME = "image_name";
  private MyPageAdapter adapter;
  private int currentItem = 0;
  private List<View> list = null;
  private CallBackListener mBackListener;
  private ViewPager pager;
  private List<String> urlList = null;

  private void initListViews(int paramInt)
  {
    PhotoView localPhotoView = new PhotoView(this);
    localPhotoView.setBackgroundColor(-16777216);
    localPhotoView.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
    localPhotoView.setScaleType(ImageView.ScaleType.FIT_CENTER);
    this.mFileDownLoad.downLoadImage((String)this.urlList.get(paramInt), localPhotoView);
    localPhotoView.setOnClickListener(this.mBackListener);
    this.list.add(localPhotoView);
  }

  protected void bindData()
  {
    for (int i = 0; ; i++)
    {
      if (i >= this.urlList.size())
      {
        this.adapter = new MyPageAdapter(this.list);
        this.pager.setAdapter(this.adapter);
        this.pager.setCurrentItem(this.currentItem);
        return;
      }
      initListViews(i);
    }
  }

  protected void initData()
  {
    this.mBackListener = new CallBackListener();
    this.currentItem = getIntent().getExtras().getInt("currentitem_key");
    this.urlList = ((List)getIntent().getExtras().getSerializable("image_name"));
    if (this.list == null)
    {
      this.list = new ArrayList();
      return;
    }
    this.list.clear();
  }

  protected void initListener()
  {
  }

  protected void initView()
  {
    this.pager = ((ViewPager)findViewById(2131099948));
  }

  protected void setContentView()
  {
    setContentView(2130903074);
  }

  class CallBackListener
    implements OnClickListener
  {
    CallBackListener()
    {
    }

    public void onClick(View paramView)
    {
      ShowPictureActivity.this.finish();
    }
  }

  class MyPageAdapter extends PagerAdapter
  {
    private List<View> listViews;
    private int size;

    public MyPageAdapter()
    {
      Object localObject;
      this.listViews = localObject;
      if (localObject == null);
      for (int i = 0; ; i = localObject.size())
      {
        this.size = i;
        return;
      }
    }

    public void destroyItem(View paramView, int paramInt, Object paramObject)
    {
      ((ViewPager)paramView).removeView((View)this.listViews.get(paramInt % this.size));
    }

    public void finishUpdate(View paramView)
    {
    }

    public int getCount()
    {
      return this.size;
    }

    public int getItemPosition(Object paramObject)
    {
      return -2;
    }

    public Object instantiateItem(View paramView, int paramInt)
    {
      try
      {
        ((ViewPager)paramView).addView((View)this.listViews.get(paramInt % this.size), 0);
        label26: return this.listViews.get(paramInt % this.size);
      }
      catch (Exception localException)
      {
        break label26;
      }
    }

    public boolean isViewFromObject(View paramView, Object paramObject)
    {
      return paramView == paramObject;
    }
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.activity.ShowPictureActivity
 * JD-Core Version:    0.6.0
 */