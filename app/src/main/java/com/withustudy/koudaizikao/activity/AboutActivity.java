package com.withustudy.koudaizikao.activity;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.umeng.analytics.MobclickAgent;
import com.withustudy.koudaizikao.R;
import com.withustudy.koudaizikao.base.AbsBaseActivity;
import com.withustudy.koudaizikao.config.KouDaiSP;

public class AboutActivity extends AbsBaseActivity
{
  private Button buttonBack;
  private CallBackListener mBackListener;
  private LinearLayout mEr;
  private LinearLayout mWeiBo;
  private LinearLayout mWeiXin;
  private TextView textLaw;
  private TextView textVision;

  protected void bindData()
  {
    this.textVision.setText("口袋自考 (" + this.mSP.getVersionName() + ")");
  }

  protected void initData()
  {
    this.mBackListener = new CallBackListener();
  }

  protected void initListener()
  {
    this.buttonBack.setOnClickListener(this.mBackListener);
    this.mEr.setOnClickListener(this.mBackListener);
    this.textLaw.setOnClickListener(this.mBackListener);
    this.mWeiBo.setOnClickListener(this.mBackListener);
    this.mWeiXin.setOnClickListener(this.mBackListener);
  }

  protected void initView()
  {
    this.buttonBack = ((Button)findViewById(R.id.button_about_back));
    this.textVision = ((TextView)findViewById(R.id.text_about_vision));
    this.mEr = ((LinearLayout)findViewById(R.id.personalitemview_about_er));
    this.mWeiBo = ((LinearLayout)findViewById(R.id.personalitemview_about_weibo));
    this.mWeiXin = ((LinearLayout)findViewById(R.id.personalitemview_about_weixin));
    this.textLaw = ((TextView)findViewById(R.id.text_about_down1));
  }

  protected void setContentView()
  {
    setContentView(R.layout.activity_about);
  }

  class CallBackListener
    implements OnClickListener
  {
    CallBackListener()
    {
    }

    public void onClick(View paramView)
    {
      switch (paramView.getId())
      {
      case 2131099681:
      case 2131099684:
      case 2131099685:
      case 2131099686:
      case R.id.text_about_down1:
      default:
        return;
      case 2131099680:
        AboutActivity.this.finish();
        return;
      case 2131099682:
        MobclickAgent.onEvent(AboutActivity.this.mContext, "personal_er");
        AboutActivity.this.startNewActivity(CodeActivity.class, false, null);
        return;
      case R.id.personalitemview_about_weibo:
      }
      MobclickAgent.onEvent(AboutActivity.this.mContext, "personal_weibo");
      Intent localIntent = new Intent();
      localIntent.setAction("android.intent.action.VIEW");
      localIntent.setData(Uri.parse("http://weibo.cn/kdzikao"));
      AboutActivity.this.startActivity(localIntent);
    }
  }
}
