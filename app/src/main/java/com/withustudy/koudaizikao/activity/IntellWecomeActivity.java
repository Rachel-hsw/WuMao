package com.withustudy.koudaizikao.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.umeng.analytics.MobclickAgent;
import com.withustudy.koudaizikao.base.AbsBaseActivity;
import com.withustudy.koudaizikao.config.KouDaiSP;
import com.withustudy.koudaizikao.custom.SharePopWindow;
import com.withustudy.koudaizikao.entity.SmartPushState;
import com.withustudy.koudaizikao.entity.SmartStat;
import com.withustudy.koudaizikao.tools.ProTools;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class IntellWecomeActivity extends AbsBaseActivity
  implements OnClickListener
{
  private HashMap<Integer, String> currRight = new HashMap();
  private int donecount;
  private int errorcount;
  private Button go_next;
  private double graspLevel;
  private Handler handler = new Handler()
  {
    public void handleMessage(Message paramMessage)
    {
      switch (paramMessage.what)
      {
      default:
      case 0:
      }
      do
        return;
      while (IntellWecomeActivity.this.smartPushState == null);
      IntellWecomeActivity.this.smartPushState.getSmartStat().getBeatRate();
      double d = IntellWecomeActivity.this.smartPushState.getSmartStat().getGraspLevel();
      IntellWecomeActivity.this.ll_loading.setVisibility(8);
      IntellWecomeActivity.this.ll_intell_w.setVisibility(0);
      IntellWecomeActivity.this.mProTools.dismissDislog();
      IntellWecomeActivity.this.tv_beat_other.setText("恭喜你完成了本次刷题");
      if ((d >= 0.0D) && (d < 30.0D))
      {
        IntellWecomeActivity.this.tv_level.setText("多刷题才可以过关哦");
        IntellWecomeActivity.this.iv_level.setBackgroundResource(2130837575);
      }
      while (true)
      {
        int i = (int)Math.ceil(d);
        IntellWecomeActivity.this.tv_rate.setText(i + "%");
        return;
        if ((d >= 30.0D) && (d < 45.0D))
        {
          IntellWecomeActivity.this.tv_level.setText("进步很快咯，加油！");
          IntellWecomeActivity.this.iv_level.setBackgroundResource(2130837576);
          continue;
        }
        if ((d >= 45.0D) && (d < 60.0D))
        {
          IntellWecomeActivity.this.tv_level.setText("通过考试，就在前方~");
          IntellWecomeActivity.this.iv_level.setBackgroundResource(2130837577);
          continue;
        }
        if ((d >= 60.0D) && (d < 75.0D))
        {
          IntellWecomeActivity.this.tv_level.setText("越刷越轻松~");
          IntellWecomeActivity.this.iv_level.setBackgroundResource(2130837578);
          continue;
        }
        if ((d >= 75.0D) && (d < 85.0D))
        {
          IntellWecomeActivity.this.tv_level.setText("真棒! 分分钟秒杀考试了");
          IntellWecomeActivity.this.iv_level.setBackgroundResource(2130837579);
          continue;
        }
        if (d < 85.0D)
          continue;
        IntellWecomeActivity.this.tv_level.setText("抱大腿~小袋被你折服了！");
        IntellWecomeActivity.this.iv_level.setBackgroundResource(2130837580);
      }
    }
  };
  private ImageButton intell_wecome_back;
  private ImageView iv_level;
  private View ll_intell_w;
  private LinearLayout ll_loading;
  private double rate;
  private int rightcount;
  private Button share;
  private SmartPushState smartPushState;
  private SmartStat smartStat;
  private String subjectId;
  private String subjectName;
  private TextView tv_beat_other;
  private TextView tv_done;
  private TextView tv_error;
  private TextView tv_level;
  private TextView tv_name;
  private TextView tv_rate;
  private TextView tv_right;
  private UpdateReceive updateReceive;
  private ImageView userImag;

  private void initReceiveNotify()
  {
    this.updateReceive = new UpdateReceive();
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addAction("com.koudai.update_smart_ui");
    registerReceiver(this.updateReceive, localIntentFilter);
  }

  protected void bindData()
  {
  }

  protected void initData()
  {
    Bundle localBundle = getIntent().getExtras();
    try
    {
      this.subjectId = localBundle.getString("subjectId");
      this.subjectName = localBundle.getString("subjectName");
      this.currRight = ((HashMap)localBundle.getSerializable("currRight"));
      label41: Iterator localIterator;
      if (this.currRight != null)
        localIterator = this.currRight.keySet().iterator();
      while (true)
      {
        if (!localIterator.hasNext())
        {
          String str1 = this.mSP.getAvatar();
          if (str1 != null)
            str1.equals("");
          this.tv_beat_other.setText("恭喜你完成了本次刷题");
          this.tv_done.setText(this.donecount);
          this.tv_error.setText(this.errorcount);
          this.tv_right.setText(this.rightcount);
          this.tv_name.setText(this.subjectName);
          return;
        }
        Integer localInteger = (Integer)localIterator.next();
        String str2 = (String)this.currRight.get(localInteger);
        if ("true".equals(str2))
        {
          this.rightcount = (1 + this.rightcount);
          this.donecount = (1 + this.donecount);
          continue;
        }
        if ("false".equals(str2))
        {
          this.errorcount = (1 + this.errorcount);
          this.donecount = (1 + this.donecount);
          continue;
        }
        "##".equals(str2);
      }
    }
    catch (Exception localException)
    {
      break label41;
    }
  }

  protected void initListener()
  {
    this.intell_wecome_back.setOnClickListener(this);
    this.go_next.setOnClickListener(this);
    this.share.setOnClickListener(this);
  }

  protected void initView()
  {
    this.mProTools.startDialog(true);
    initReceiveNotify();
    this.ll_intell_w = ((LinearLayout)findViewById(2131100290));
    this.ll_loading = ((LinearLayout)findViewById(2131100295));
    this.share = ((Button)findViewById(2131099713));
    this.intell_wecome_back = ((ImageButton)findViewById(2131100291));
    this.go_next = ((Button)findViewById(2131100294));
    this.tv_name = ((TextView)findViewById(2131099709));
    this.tv_level = ((TextView)findViewById(2131099957));
    this.tv_beat_other = ((TextView)findViewById(2131100292));
    this.tv_rate = ((TextView)findViewById(2131100293));
    this.tv_right = ((TextView)findViewById(2131099711));
    this.tv_error = ((TextView)findViewById(2131099712));
    this.tv_done = ((TextView)findViewById(2131099710));
    this.userImag = ((ImageView)findViewById(2131099952));
    this.iv_level = ((ImageView)findViewById(2131099956));
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
      return;
    case 2131099713:
      MobclickAgent.onEvent(this.mContext, "brush_i_con_share_now");
      new SharePopWindow(this, this.ll_intell_w).showPop();
      return;
    case 2131100291:
      MobclickAgent.onEvent(this.mContext, "brush_i_close_con");
      finish();
      return;
    case 2131100294:
    }
    MobclickAgent.onEvent(this.mContext, "brush_i_con_continue");
    Bundle localBundle = new Bundle();
    localBundle.putString("subjectId", this.subjectId);
    localBundle.putInt("FromPage", 888);
    Intent localIntent = new Intent(getApplicationContext(), QuestionDetailActivity.class);
    localIntent.putExtras(localBundle);
    startActivity(localIntent);
    finish();
  }

  protected void onDestroy()
  {
    super.onDestroy();
    unregisterReceiver(this.updateReceive);
  }

  protected void setContentView()
  {
    setContentView(2130903130);
  }

  public class UpdateReceive extends BroadcastReceiver
  {
    public UpdateReceive()
    {
    }

    public void onReceive(Context paramContext, Intent paramIntent)
    {
      Bundle localBundle = paramIntent.getExtras();
      IntellWecomeActivity.this.smartPushState = ((SmartPushState)localBundle.getSerializable("smartPushState"));
      IntellWecomeActivity.this.handler.sendEmptyMessage(0);
    }
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.activity.IntellWecomeActivity
 * JD-Core Version:    0.6.0
 */