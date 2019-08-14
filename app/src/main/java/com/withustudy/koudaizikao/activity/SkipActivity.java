package com.withustudy.koudaizikao.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.umeng.analytics.MobclickAgent;
import com.withustudy.koudaizikao.base.AbsBaseActivity;
import com.withustudy.koudaizikao.config.KouDaiSP;

@SuppressLint({"ShowToast"})
public class SkipActivity extends AbsBaseActivity
  implements OnClickListener
{
  public static final int FINISH = 1;
  private static double SCALE;
  private static double SCREEN_SCALE = 0.347D;
  public static SkipHandler mHandler;
  private Button btn_login;
  private Button btn_regist;
  private ImageView image_text;
  private TextView skip_ll;

  static
  {
    SCALE = 0.144D;
  }

  protected void bindData()
  {
    int i = (int)(this.mSP.getWidth() * SCREEN_SCALE);
    LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(i, (int)(i * SCALE));
    this.image_text.setLayoutParams(localLayoutParams);
    this.image_text.setBackgroundResource(2130837927);
  }

  protected void initData()
  {
    mHandler = new SkipHandler();
  }

  protected void initListener()
  {
    this.btn_login.setOnClickListener(this);
    this.skip_ll.setOnClickListener(this);
    this.btn_regist.setOnClickListener(this);
  }

  protected void initView()
  {
    this.skip_ll = ((TextView)findViewById(2131099987));
    this.btn_login = ((Button)findViewById(2131099990));
    this.btn_regist = ((Button)findViewById(2131099991));
    this.image_text = ((ImageView)findViewById(2131099989));
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    case 2131099988:
    case 2131099989:
    default:
      return;
    case 2131099987:
      MobclickAgent.onEvent(this.mContext, "no_login_no_register");
      this.mSP.clearUserInfo();
      startNewActivity(MainActivity.class, true, null);
      return;
    case 2131099990:
      MobclickAgent.onEvent(this.mContext, "login");
      Bundle localBundle2 = new Bundle();
      localBundle2.putString("flag", "0");
      startNewActivity(LoginActivity.class, false, localBundle2);
      return;
    case 2131099991:
    }
    MobclickAgent.onEvent(this.mContext, "register");
    Bundle localBundle1 = new Bundle();
    localBundle1.putString("flag", "1");
    startNewActivity(LoginActivity.class, false, localBundle1);
  }

  protected void setContentView()
  {
    setContentView(2130903081);
  }

  public class SkipHandler extends Handler
  {
    public SkipHandler()
    {
    }

    public void handleMessage(Message paramMessage)
    {
      super.handleMessage(paramMessage);
      switch (paramMessage.what)
      {
      default:
        return;
      case 1:
      }
      SkipActivity.this.finish();
    }
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.activity.SkipActivity
 * JD-Core Version:    0.6.0
 */