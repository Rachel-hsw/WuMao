package com.withustudy.koudaizikao.activity;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import com.withustudy.koudaizikao.base.AbsBaseActivity;

public class CodeActivity extends AbsBaseActivity
{
  private Button buttonBack;
  private Button buttonShare;
  private ImageView imageEr;
  private CallBackListener mBackListener;

  protected void bindData()
  {
  }

  protected void initData()
  {
    this.mBackListener = new CallBackListener();
  }

  protected void initListener()
  {
    this.buttonBack.setOnClickListener(this.mBackListener);
    this.buttonShare.setOnClickListener(this.mBackListener);
  }

  protected void initView()
  {
    this.buttonBack = ((Button)findViewById(2131099750));
    this.buttonShare = ((Button)findViewById(2131099751));
    this.imageEr = ((ImageView)findViewById(2131099752));
  }

  protected void setContentView()
  {
    setContentView(2130903050);
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
      case 2131099751:
      default:
        return;
      case 2131099750:
      }
      CodeActivity.this.finish();
    }
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.activity.CodeActivity
 * JD-Core Version:    0.6.0
 */