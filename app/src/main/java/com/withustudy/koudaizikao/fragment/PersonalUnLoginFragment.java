package com.withustudy.koudaizikao.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.umeng.analytics.MobclickAgent;
import com.withustudy.koudaizikao.activity.LoginActivity;
import com.withustudy.koudaizikao.base.AbsBaseFragment;

public class PersonalUnLoginFragment extends AbsBaseFragment
{
  private ImageView imageAvatar;
  private CallBackListener mBackListener;
  private TextView textAdv;
  private TextView textNickName;

  public void bindData()
  {
  }

  public void initData()
  {
    this.mBackListener = new CallBackListener();
  }

  public void initListener()
  {
    this.imageAvatar.setOnClickListener(this.mBackListener);
    this.textAdv.setOnClickListener(this.mBackListener);
    this.textNickName.setOnClickListener(this.mBackListener);
  }

  public void initView(View paramView)
  {
    this.imageAvatar = ((ImageView)paramView.findViewById(2131100172));
    this.textNickName = ((TextView)paramView.findViewById(2131100173));
    this.textAdv = ((TextView)paramView.findViewById(2131100174));
  }

  public View onInflaterRootView(LayoutInflater paramLayoutInflater, Bundle paramBundle)
  {
    return paramLayoutInflater.inflate(2130903121, null);
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
      default:
        return;
      case 2131100172:
      case 2131100173:
      case 2131100174:
      }
      MobclickAgent.onEvent(PersonalUnLoginFragment.this.mContext, "login");
      Intent localIntent = new Intent(PersonalUnLoginFragment.this.mContext.getApplicationContext(), LoginActivity.class);
      localIntent.putExtra("flag", "0");
      PersonalUnLoginFragment.this.startActivity(localIntent);
    }
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.fragment.PersonalUnLoginFragment
 * JD-Core Version:    0.6.0
 */