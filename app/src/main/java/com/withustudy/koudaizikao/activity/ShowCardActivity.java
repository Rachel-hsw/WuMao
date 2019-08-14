package com.withustudy.koudaizikao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.umeng.analytics.MobclickAgent;
import com.withustudy.koudaizikao.base.AbsBaseActivity;
import com.withustudy.koudaizikao.custom.SharePopWindow;
import com.withustudy.koudaizikao.custom.circleprogressbar.CircleProgressBar;
import com.withustudy.koudaizikao.entity.SectionStat;
import com.withustudy.koudaizikao.tools.LogUtils;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class ShowCardActivity extends AbsBaseActivity
  implements OnClickListener
{
  static int current;
  private ImageButton anser_back;
  private CircleProgressBar bar3;
  private HashMap<Integer, String> currRight = new HashMap();
  private int donecount = 0;
  private int errorcount = 0;
  private LinearLayout ll_defalt;
  private LinearLayout ll_parent;
  private int max = 0;
  private int rightcount = 0;
  private SectionStat sectionStat;
  private Button share;
  private String subjectName;
  private TextView tv_beat;
  private TextView tv_done;
  private TextView tv_error;
  private TextView tv_name;
  private TextView tv_right;

  private void delay()
  {
    try
    {
      Thread.sleep(40L);
      return;
    }
    catch (InterruptedException localInterruptedException)
    {
      localInterruptedException.printStackTrace();
    }
  }

  protected void bindData()
  {
    new Thread()
    {
      public void run()
      {
        super.run();
        while (true)
        {
          if (ShowCardActivity.current > ShowCardActivity.this.max)
            return;
          ShowCardActivity.current = 1 + ShowCardActivity.current;
          ShowCardActivity.this.bar3.setProgress(ShowCardActivity.current);
          ShowCardActivity.this.delay();
        }
      }
    }
    .start();
  }

  protected void initData()
  {
    Bundle localBundle = getIntent().getExtras();
    try
    {
      this.currRight = ((HashMap)localBundle.getSerializable("currRight"));
      this.subjectName = localBundle.getString("subjectName");
      label31: Iterator localIterator;
      if (this.currRight != null)
        localIterator = this.currRight.keySet().iterator();
      while (true)
      {
        if (!localIterator.hasNext())
        {
          LogUtils.myLog("rightcount=" + this.rightcount);
          LogUtils.myLog("errorcount=" + this.errorcount);
          LogUtils.myLog("donecount=" + this.donecount);
          this.tv_done.setText(this.donecount);
          this.tv_right.setText(this.rightcount);
          this.tv_error.setText(this.errorcount);
          this.tv_name.setText(this.subjectName);
          this.tv_beat.setText("恭喜你完成了本节的刷题");
          this.max = (int)(100.0D * (1.0D * this.rightcount / this.donecount));
          if (this.max != 0)
            break;
          this.ll_defalt.setVisibility(0);
          this.bar3.setVisibility(8);
          return;
        }
        Integer localInteger = (Integer)localIterator.next();
        String str = (String)this.currRight.get(localInteger);
        if ("true".equals(str))
        {
          this.rightcount = (1 + this.rightcount);
          this.donecount = (1 + this.donecount);
          continue;
        }
        if ("false".equals(str))
        {
          this.errorcount = (1 + this.errorcount);
          this.donecount = (1 + this.donecount);
          continue;
        }
        "##".equals(str);
      }
      this.max = (-1 + this.max);
      this.ll_defalt.setVisibility(8);
      this.bar3.setVisibility(0);
      return;
    }
    catch (Exception localException)
    {
      break label31;
    }
  }

  protected void initListener()
  {
    this.anser_back.setOnClickListener(this);
    this.share.setOnClickListener(this);
  }

  protected void initView()
  {
    this.anser_back = ((ImageButton)findViewById(2131099705));
    this.ll_parent = ((LinearLayout)findViewById(2131099704));
    this.bar3 = ((CircleProgressBar)findViewById(2131099707));
    this.tv_right = ((TextView)findViewById(2131099711));
    this.tv_error = ((TextView)findViewById(2131099712));
    this.tv_done = ((TextView)findViewById(2131099710));
    this.tv_name = ((TextView)findViewById(2131099709));
    this.tv_beat = ((TextView)findViewById(2131099708));
    this.share = ((Button)findViewById(2131099713));
    this.ll_defalt = ((LinearLayout)findViewById(2131099706));
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
      return;
    case 2131099705:
      MobclickAgent.onEvent(this.mContext, "brush_c_close_con");
      finish();
      current = 0;
      return;
    case 2131099713:
    }
    MobclickAgent.onEvent(this.mContext, "brush_c_share_con");
    new SharePopWindow(this, this.ll_parent).showPop();
  }

  protected void setContentView()
  {
    setContentView(2130903043);
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.activity.ShowCardActivity
 * JD-Core Version:    0.6.0
 */