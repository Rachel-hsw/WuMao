package com.withustudy.koudaizikao.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.withustudy.koudaizikao.fragment.BrushRankingFragment;
import java.util.List;

public class BrushViewPagerAdapter extends FragmentPagerAdapter
{
  private List<BrushRankingFragment> list;

  public BrushViewPagerAdapter(FragmentManager paramFragmentManager, List<BrushRankingFragment> paramList)
  {
    super(paramFragmentManager);
    this.list = paramList;
  }

  public int getCount()
  {
    return this.list.size();
  }

  public Fragment getItem(int paramInt)
  {
    return (Fragment)this.list.get(paramInt);
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.adapter.BrushViewPagerAdapter
 * JD-Core Version:    0.6.0
 */