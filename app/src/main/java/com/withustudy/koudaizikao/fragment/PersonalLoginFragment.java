package com.withustudy.koudaizikao.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.http.FileDownLoad;
import com.umeng.analytics.MobclickAgent;
import com.withustudy.koudaizikao.activity.ActivityErrorListActivity;
import com.withustudy.koudaizikao.activity.FavoriteActivity;
import com.withustudy.koudaizikao.activity.PersonalSetActivity;
import com.withustudy.koudaizikao.activity.SimuHistoryListActivity;
import com.withustudy.koudaizikao.base.AbsBaseFragment;

public class PersonalLoginFragment extends AbsBaseFragment
{
  private Button buttonEdit;
  private ImageView imageAvatar;
  private LinearLayout layoutCollect;
  private LinearLayout layoutHistory;
  private LinearLayout layoutWrong;
  private CallBackListener mBackListener;
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
    this.buttonEdit.setOnClickListener(this.mBackListener);
    this.layoutWrong.setOnClickListener(this.mBackListener);
    this.layoutCollect.setOnClickListener(this.mBackListener);
    this.layoutHistory.setOnClickListener(this.mBackListener);
  }

  public void initView(View paramView)
  {
    this.imageAvatar = ((ImageView)paramView.findViewById(2131100166));
    this.textNickName = ((TextView)paramView.findViewById(2131100167));
    this.buttonEdit = ((Button)paramView.findViewById(2131100168));
    this.layoutCollect = ((LinearLayout)paramView.findViewById(2131100169));
    this.layoutWrong = ((LinearLayout)paramView.findViewById(2131100170));
    this.layoutHistory = ((LinearLayout)paramView.findViewById(2131100171));
  }

  public View onInflaterRootView(LayoutInflater paramLayoutInflater, Bundle paramBundle)
  {
    return paramLayoutInflater.inflate(2130903120, null);
  }

  public void setAvatar(String paramString)
  {
    this.mFileDownLoad.downLoadImage(paramString, this.imageAvatar, 1000);
  }

  public void setNickName(String paramString)
  {
    this.textNickName.setText(paramString);
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
      case 2131100168:
        MobclickAgent.onEvent(PersonalLoginFragment.this.mContext, "personal_set_person");
        PersonalLoginFragment.this.startNewActivity(PersonalSetActivity.class, false, null);
        return;
      case 2131100169:
        MobclickAgent.onEvent(PersonalLoginFragment.this.mContext, "personal_follow");
        PersonalLoginFragment.this.startNewActivity(FavoriteActivity.class, false, null);
        return;
      case 2131100170:
        MobclickAgent.onEvent(PersonalLoginFragment.this.mContext, "personal_error");
        PersonalLoginFragment.this.startNewActivity(ActivityErrorListActivity.class, false, null);
        return;
      case 2131100171:
      }
      MobclickAgent.onEvent(PersonalLoginFragment.this.mContext, "personal_history");
      PersonalLoginFragment.this.startNewActivity(SimuHistoryListActivity.class, false, null);
    }
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.fragment.PersonalLoginFragment
 * JD-Core Version:    0.6.0
 */