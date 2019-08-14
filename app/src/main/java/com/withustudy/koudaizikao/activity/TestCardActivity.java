package com.withustudy.koudaizikao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;
import com.withustudy.koudaizikao.base.AbsBaseActivity;
import java.util.HashMap;

public class TestCardActivity extends AbsBaseActivity
{
  private GridView simu_card_gv;
  public HashMap<Integer, Boolean> stateDone = new HashMap();

  protected void bindData()
  {
    if (this.stateDone != null)
      this.stateDone.size();
  }

  protected void initData()
  {
    this.stateDone = ((HashMap)getIntent().getExtras().get("stateDone"));
  }

  protected void initListener()
  {
  }

  protected void initView()
  {
    this.simu_card_gv = ((GridView)findViewById(2131100007));
  }

  protected void setContentView()
  {
    setContentView(2130903085);
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.activity.TestCardActivity
 * JD-Core Version:    0.6.0
 */