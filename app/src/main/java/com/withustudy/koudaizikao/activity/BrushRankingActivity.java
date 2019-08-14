package com.withustudy.koudaizikao.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.umeng.analytics.MobclickAgent;
import com.withustudy.koudaizikao.R;
import com.withustudy.koudaizikao.adapter.BrushViewPagerAdapter;
import com.withustudy.koudaizikao.base.AbsBaseActivity;
import com.withustudy.koudaizikao.fragment.BrushRankingFragment;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BrushRankingActivity extends AbsBaseActivity
{
  private static final int action_get_user_rank = 1;
  public static HashMap<Integer, String> indexToTag = new HashMap()
  {
    private static final long serialVersionUID = 1L;
  };
  private Button buttonBack;
  private Button[] buttonTab;
  public int currentIndex = 0;
  private ImageView[] imageTab;
  private List<BrushRankingFragment> list = new ArrayList();
  private BrushViewPagerAdapter mAdapter;
  private CallBackListener mBackListener;
  private ViewPager mViewPager;
  public String subjectId;
  public String subjectName;
  private TextView text_brush_ranking_title;
  public int type;

  private void setButton(int paramInt)
  {
    if ((paramInt > 3) || (paramInt < 0))
      return;
    int i = 0;
    if (i >= 4);
    switch (paramInt)
    {
    default:
      return;
    case 0:
      if (this.type == 0)
      {
        MobclickAgent.onEvent(this.mContext, "brush_rank_today");
        return;
        if (i == paramInt)
        {
          this.buttonTab[i].setTextColor(getResources().getColor(R.color.activity_color));
          this.imageTab[i].setVisibility(View.VISIBLE);
        }
        while (true)
        {
          i++;
          break;
          this.buttonTab[i].setTextColor(Color.parseColor("#666666"));
          this.imageTab[i].setVisibility(4);
        }
      }
      MobclickAgent.onEvent(this.mContext, "test_rank_today");
      return;
    case 1:
      if (this.type == 0)
      {
        MobclickAgent.onEvent(this.mContext, "brush_rank_week");
        return;
      }
      MobclickAgent.onEvent(this.mContext, "test_rank_week");
      return;
    case 2:
      if (this.type == 0)
      {
        MobclickAgent.onEvent(this.mContext, "brush_rank_month");
        return;
      }
      MobclickAgent.onEvent(this.mContext, "test_rank_month");
      return;
    case 3:
    }
    if (this.type == 0)
    {
      MobclickAgent.onEvent(this.mContext, "brush_rank_all\t");
      return;
    }
    MobclickAgent.onEvent(this.mContext, "test_rank_all");
  }

  protected void bindData()
  {
    for (int i = 0; ; i++)
    {
      if (i >= 4)
      {
        this.mAdapter = new BrushViewPagerAdapter(this.mFragmentManager, this.list);
        this.mViewPager.setAdapter(this.mAdapter);
        setButton(0);
        this.mViewPager.setCurrentItem(0);
        return;
      }
      BrushRankingFragment localBrushRankingFragment = new BrushRankingFragment();
      this.list.add(localBrushRankingFragment);
    }
  }

  protected void initData()
  {
    Bundle localBundle = getIntent().getExtras();
    try
    {
      this.type = localBundle.getInt("type");
      this.subjectId = localBundle.getString("subjectId");
      this.subjectName = localBundle.getString("subjectName");
      label38: this.mBackListener = new CallBackListener();
      switch (this.type)
      {
      default:
        return;
      case 0:
        this.text_brush_ranking_title.setText("刷题排行榜");
        return;
      case 1:
      }
      this.text_brush_ranking_title.setText("模考排行榜");
      return;
    }
    catch (Exception localException)
    {
      break label38;
    }
  }

  protected void initListener()
  {
    for (int i = 0; ; i++)
    {
      if (i >= 4)
      {
        this.mViewPager.setOnPageChangeListener(this.mBackListener);
        this.buttonBack.setOnClickListener(this.mBackListener);
        return;
      }
      this.buttonTab[i].setOnClickListener(this.mBackListener);
    }
  }

  protected void initView()
  {
    this.text_brush_ranking_title = ((TextView)findViewById(2131099724));
    this.buttonBack = ((Button)findViewById(2131099725));
    this.buttonTab = new Button[4];
    this.imageTab = new ImageView[4];
    this.buttonTab[0] = ((Button)findViewById(2131099726));
    this.buttonTab[1] = ((Button)findViewById(2131099727));
    this.buttonTab[2] = ((Button)findViewById(2131099728));
    this.buttonTab[3] = ((Button)findViewById(2131099729));
    this.imageTab[0] = ((ImageView)findViewById(2131099730));
    this.imageTab[1] = ((ImageView)findViewById(2131099731));
    this.imageTab[2] = ((ImageView)findViewById(2131099732));
    this.imageTab[3] = ((ImageView)findViewById(2131099733));
    this.mViewPager = ((ViewPager)findViewById(2131099734));
  }

  protected void setContentView()
  {
    setContentView(2130903046);
  }

  public class CallBackListener
    implements OnClickListener, OnPageChangeListener
  {
    public CallBackListener()
    {
    }

    public void onClick(View paramView)
    {
      switch (paramView.getId())
      {
      default:
        return;
      case 2131099725:
        BrushRankingActivity.this.finish(2130968578, 2130968582);
        return;
      case 2131099726:
        BrushRankingActivity.this.setButton(0);
        BrushRankingActivity.this.currentIndex = 0;
        BrushRankingActivity.this.mViewPager.setCurrentItem(0, true);
        return;
      case 2131099727:
        BrushRankingActivity.this.setButton(1);
        BrushRankingActivity.this.currentIndex = 1;
        BrushRankingActivity.this.mViewPager.setCurrentItem(1, true);
        return;
      case 2131099728:
        BrushRankingActivity.this.setButton(2);
        BrushRankingActivity.this.currentIndex = 2;
        BrushRankingActivity.this.mViewPager.setCurrentItem(2, true);
        return;
      case 2131099729:
      }
      BrushRankingActivity.this.setButton(3);
      BrushRankingActivity.this.currentIndex = 3;
      BrushRankingActivity.this.mViewPager.setCurrentItem(3, true);
    }

    public void onPageScrollStateChanged(int paramInt)
    {
    }

    public void onPageScrolled(int paramInt1, float paramFloat, int paramInt2)
    {
    }

    public void onPageSelected(int paramInt)
    {
      BrushRankingActivity.this.mViewPager.setCurrentItem(paramInt);
      BrushRankingActivity.this.setButton(paramInt);
      BrushRankingActivity.this.currentIndex = paramInt;
    }
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.activity.BrushRankingActivity
 * JD-Core Version:    0.6.0
 */