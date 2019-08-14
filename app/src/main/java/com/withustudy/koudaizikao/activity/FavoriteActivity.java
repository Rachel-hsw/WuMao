package com.withustudy.koudaizikao.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.withustudy.koudaizikao.R;
import com.withustudy.koudaizikao.base.AbsBaseActivity;
import com.withustudy.koudaizikao.fragment.FavoriteExerciseFragment;
import com.withustudy.koudaizikao.fragment.FavoriteNewsFragment;
import com.withustudy.koudaizikao.fragment.FavoritePostFragment;

public class FavoriteActivity extends AbsBaseActivity
{
  private Button buttonBack;
  private Button[] buttonTitle;
  private ImageView[] imageTitle;
  private FavoriteViewPagerAdapter mAdapter;
  private CallBackListener mCallBackListener;
  private FavoriteExerciseFragment mFavoriteExerciseFragment;
  private FavoriteNewsFragment mFavoriteNewsFragment;
  private FavoritePostFragment mFavoritePostFragment;
  private ViewPager mViewPager;

  private void hideFragment(FragmentTransaction paramFragmentTransaction)
  {
    if (this.mFavoriteExerciseFragment != null)
      paramFragmentTransaction.hide(this.mFavoriteExerciseFragment);
    if (this.mFavoriteNewsFragment != null)
      paramFragmentTransaction.hide(this.mFavoriteNewsFragment);
    if (this.mFavoritePostFragment != null)
      paramFragmentTransaction.hide(this.mFavoritePostFragment);
  }

  private void setState(int paramInt)
  {
    if ((paramInt < 0) || (paramInt > 2))
      return;
    int i = 0;
    label12: if (i < 3)
    {
      if (i != paramInt)
        break label53;
      this.buttonTitle[i].setTextColor(Color.parseColor("#00B8E6"));
      this.imageTitle[i].setBackgroundResource(R.color.activity_color);
    }
    while (true)
    {
      i++;
      break label12;
      break;
      label53: this.buttonTitle[i].setTextColor(Color.parseColor("#999999"));
      this.imageTitle[i].setBackgroundColor(Color.parseColor("#ffffff"));
    }
  }

  protected void bindData()
  {
    this.mViewPager.setAdapter(this.mAdapter);
    setState(0);
  }

  protected void initData()
  {
    this.mCallBackListener = new CallBackListener();
    this.mFavoriteExerciseFragment = new FavoriteExerciseFragment();
    this.mFavoriteNewsFragment = new FavoriteNewsFragment();
    this.mFavoritePostFragment = new FavoritePostFragment();
    this.mAdapter = new FavoriteViewPagerAdapter(getSupportFragmentManager(), this.mFavoriteExerciseFragment, this.mFavoriteNewsFragment, this.mFavoritePostFragment);
  }

  protected void initListener()
  {
    for (int i = 0; ; i++)
    {
      if (i >= 3)
      {
        this.buttonBack.setOnClickListener(this.mCallBackListener);
        this.mViewPager.setOnPageChangeListener(this.mCallBackListener);
        return;
      }
      this.buttonTitle[i].setOnClickListener(this.mCallBackListener);
    }
  }

  protected void initView()
  {
    this.buttonBack = ((Button)findViewById(2131099774));
    this.buttonTitle = new Button[3];
    this.buttonTitle[0] = ((Button)findViewById(2131099775));
    this.buttonTitle[1] = ((Button)findViewById(2131099776));
    this.buttonTitle[2] = ((Button)findViewById(2131099777));
    this.imageTitle = new ImageView[3];
    this.imageTitle[0] = ((ImageView)findViewById(2131099778));
    this.imageTitle[1] = ((ImageView)findViewById(2131099779));
    this.imageTitle[2] = ((ImageView)findViewById(2131099780));
    this.mViewPager = ((ViewPager)findViewById(2131099781));
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramInt1 == 20)
    {
      String str = paramIntent.getStringExtra("result");
      this.mFavoriteNewsFragment.updateState(str, paramInt2);
    }
  }

  protected void setContentView()
  {
    setContentView(2130903054);
  }

  class CallBackListener
    implements OnClickListener, OnPageChangeListener
  {
    CallBackListener()
    {
    }

    public void onClick(View paramView)
    {
      switch (paramView.getId())
      {
      default:
        return;
      case 2131099774:
        FavoriteActivity.this.finish();
        return;
      case 2131099775:
        FavoriteActivity.this.setState(0);
        FavoriteActivity.this.mViewPager.setCurrentItem(0);
        return;
      case 2131099776:
        FavoriteActivity.this.setState(1);
        FavoriteActivity.this.mViewPager.setCurrentItem(1);
        return;
      case 2131099777:
      }
      FavoriteActivity.this.setState(2);
      FavoriteActivity.this.mViewPager.setCurrentItem(2);
    }

    public void onPageScrollStateChanged(int paramInt)
    {
    }

    public void onPageScrolled(int paramInt1, float paramFloat, int paramInt2)
    {
    }

    public void onPageSelected(int paramInt)
    {
      FavoriteActivity.this.setState(paramInt);
    }
  }

  public class FavoriteViewPagerAdapter extends FragmentPagerAdapter
  {
    private FavoriteExerciseFragment mFavoriteExerciseFragment;
    private FavoriteNewsFragment mFavoriteNewsFragment;
    private FavoritePostFragment mFavoritePostFragment;

    public FavoriteViewPagerAdapter(FragmentManager paramFavoriteExerciseFragment, FavoriteExerciseFragment paramFavoriteNewsFragment, FavoriteNewsFragment paramFavoritePostFragment, FavoritePostFragment arg5)
    {
      super();
      this.mFavoriteExerciseFragment = paramFavoriteNewsFragment;
      this.mFavoriteNewsFragment = paramFavoritePostFragment;
      Object localObject;
      this.mFavoritePostFragment = localObject;
    }

    public int getCount()
    {
      return 3;
    }

    public Fragment getItem(int paramInt)
    {
      if (paramInt == 0)
        return this.mFavoriteExerciseFragment;
      if (paramInt == 1)
        return this.mFavoriteNewsFragment;
      if (paramInt == 2)
        return this.mFavoritePostFragment;
      return null;
    }
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.activity.FavoriteActivity
 * JD-Core Version:    0.6.0
 */