package com.withustudy.koudaizikao.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.TextView;

import com.withustudy.koudaizikao.R;
import com.withustudy.koudaizikao.base.AbsBaseActivity;
import com.withustudy.koudaizikao.config.KouDaiSP;
import com.withustudy.koudaizikao.fragment.InformationTypeFragment;
import java.util.ArrayList;
import java.util.List;

public class InformationTypeActivity extends AbsBaseActivity
{
  public static final int ADD_REPLY = 3;
  public static final int GET_DATA = 1;
  public static final int REQUEST_GET_DETAIL = 20;
  public static final int RESULT_ALL = 24;
  public static final int RESULT_COLLECT = 22;
  public static final int RESULT_NOTHING = 21;
  public static final int RESULT_PRAISE = 23;
  public static final int RESULT_REPLY = 25;
  public static final int SCROLL = 2;
  public static InformationTypeHandler mHandler;
  private Button buttonBack;
  private int currType;
  private List<InformationTypeFragment> list;
  private InformationTypePagerAdapter mAdapter;
  private CallBackListener mBackListener;
  private HorizontalScrollView mScrollView;
  private ViewPager mViewPager;
  private TextView[] textType;

  private void setType()
  {
    if (this.currType == 0)
      this.mScrollView.scrollTo(0, 0);
    int i;
    while (true)
    {
      i = 0;
      if (i < 5)
        break;
      return;
      if (this.currType != 4)
        continue;
      this.mScrollView.scrollTo(this.mSP.getWidth(), 0);
    }
    if (i == this.currType)
      this.textType[i].setTextColor(getResources().getColor(R.color.activity_color));
    while (true)
    {
      i++;
      break;
      this.textType[i].setTextColor(Color.parseColor("#666666"));
    }
  }

  protected void bindData()
  {
    this.mViewPager.setAdapter(this.mAdapter);
    setType();
    this.mViewPager.setCurrentItem(this.currType);
    mHandler.sendEmptyMessageDelayed(1, 500L);
  }

  protected void initData()
  {
    this.mBackListener = new CallBackListener();
    mHandler = new InformationTypeHandler();
    this.list = new ArrayList();
    int i = 0;
    if (i >= 5)
      switch (getIntent().getExtras().getInt("information_type"))
      {
      default:
      case 0:
      case 1:
      case 6:
      case 11:
      case 16:
      }
    while (true)
    {
      this.mAdapter = new InformationTypePagerAdapter(getSupportFragmentManager(), this.list);
      return;
      InformationTypeFragment localInformationTypeFragment = new InformationTypeFragment(i);
      this.list.add(localInformationTypeFragment);
      i++;
      break;
      this.currType = 0;
      continue;
      this.currType = 1;
      continue;
      this.currType = 2;
      continue;
      this.currType = 3;
      continue;
      this.currType = 4;
      mHandler.sendEmptyMessageDelayed(2, 500L);
    }
  }

  protected void initListener()
  {
    this.buttonBack.setOnClickListener(this.mBackListener);
    for (int i = 0; ; i++)
    {
      if (i >= 5)
      {
        this.mViewPager.setOnPageChangeListener(this.mBackListener);
        return;
      }
      this.textType[i].setOnClickListener(this.mBackListener);
    }
  }

  protected void initView()
  {
    this.buttonBack = ((Button)findViewById(2131099803));
    this.textType = new TextView[5];
    this.textType[0] = ((TextView)findViewById(2131099805));
    this.textType[1] = ((TextView)findViewById(2131099806));
    this.textType[2] = ((TextView)findViewById(2131099807));
    this.textType[3] = ((TextView)findViewById(2131099808));
    this.textType[4] = ((TextView)findViewById(2131099809));
    this.mScrollView = ((HorizontalScrollView)findViewById(2131099804));
    this.mViewPager = ((ViewPager)findViewById(2131099810));
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    String str;
    if (paramInt1 == 20)
      str = paramIntent.getStringExtra("result");
    for (int i = 0; ; i++)
    {
      if (i >= this.list.size())
        return;
      ((InformationTypeFragment)this.list.get(i)).updateState(str, paramInt2);
    }
  }

  protected void setContentView()
  {
    setContentView(2130903056);
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
      case 2131099804:
      default:
        return;
      case 2131099803:
        InformationTypeActivity.this.finish();
        return;
      case 2131099805:
        InformationTypeActivity.this.currType = 0;
        InformationTypeActivity.this.setType();
        InformationTypeActivity.this.mViewPager.setCurrentItem(InformationTypeActivity.this.currType);
        return;
      case 2131099806:
        InformationTypeActivity.this.currType = 1;
        InformationTypeActivity.this.setType();
        InformationTypeActivity.this.mViewPager.setCurrentItem(InformationTypeActivity.this.currType);
        return;
      case 2131099807:
        InformationTypeActivity.this.currType = 2;
        InformationTypeActivity.this.setType();
        InformationTypeActivity.this.mViewPager.setCurrentItem(InformationTypeActivity.this.currType);
        return;
      case 2131099808:
        InformationTypeActivity.this.currType = 3;
        InformationTypeActivity.this.setType();
        InformationTypeActivity.this.mViewPager.setCurrentItem(InformationTypeActivity.this.currType);
        return;
      case 2131099809:
      }
      InformationTypeActivity.this.currType = 4;
      InformationTypeActivity.this.setType();
      InformationTypeActivity.this.mViewPager.setCurrentItem(InformationTypeActivity.this.currType);
    }

    public void onPageScrollStateChanged(int paramInt)
    {
    }

    public void onPageScrolled(int paramInt1, float paramFloat, int paramInt2)
    {
    }

    public void onPageSelected(int paramInt)
    {
      ((InformationTypeFragment)InformationTypeActivity.this.list.get(paramInt)).getData();
      InformationTypeActivity.this.currType = paramInt;
      InformationTypeActivity.this.setType();
    }
  }

  public class InformationTypeHandler extends Handler
  {
    public InformationTypeHandler()
    {
    }

    public void handleMessage(Message paramMessage)
    {
      super.handleMessage(paramMessage);
      switch (paramMessage.what)
      {
      default:
      case 1:
      case 2:
      case 3:
      }
      while (true)
      {
        return;
        ((InformationTypeFragment)InformationTypeActivity.this.list.get(InformationTypeActivity.this.currType)).getData();
        return;
        InformationTypeActivity.this.mScrollView.scrollTo(InformationTypeActivity.this.mSP.getWidth(), 0);
        return;
        for (int i = 0; i < InformationTypeActivity.this.list.size(); i++)
          ((InformationTypeFragment)InformationTypeActivity.this.list.get(i)).addReply((String)paramMessage.obj);
      }
    }
  }

  class InformationTypePagerAdapter extends FragmentPagerAdapter
  {
    private List<InformationTypeFragment> list;

    public InformationTypePagerAdapter(List<InformationTypeFragment> arg2)
    {
      super();
      Object localObject;
      this.list = localObject;
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
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.activity.InformationTypeActivity
 * JD-Core Version:    0.6.0
 */