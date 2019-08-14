package com.withustudy.koudaizikao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.gson.Gson;
import com.withustudy.koudaizikao.action.AbsSimpleImplUrlFactory;
import com.withustudy.koudaizikao.action.UrlFactory;
import com.withustudy.koudaizikao.base.AbsBaseActivity;
import com.withustudy.koudaizikao.config.KouDaiSP;
import com.withustudy.koudaizikao.entity.content.ResultState;
import com.withustudy.koudaizikao.entity.req.ChangePersonalInfo;
import com.withustudy.koudaizikao.entity.req.UserBaseInfo;
import com.withustudy.koudaizikao.fragment.PersonalFragment;
import com.withustudy.koudaizikao.fragment.PersonalFragment.PersonalHandler;
import com.withustudy.koudaizikao.tools.ProTools;
import com.withustudy.koudaizikao.tools.ToolsUtils;
import java.util.Map;

public class SetNickNameActivity extends AbsBaseActivity
{
  public static final int ACTION_SET_NICKNAME = 1;
  public static final int DISMISS = 5;
  public static final int SET = 1;
  public static final int SET_FAIL1 = 2;
  public static final int SET_FAIL2 = 3;
  public static final int SET_FAIL3 = 4;
  private Button buttonBack;
  private Button buttonSave;
  private EditText edit;
  private CallBackListener mBackListener;
  private SetNickNameHandler mHandler;

  protected void bindData()
  {
    Bundle localBundle = getIntent().getExtras();
    if ((localBundle != null) && (localBundle.getString("nickname") != null) && (!localBundle.getString("nickname").equals("")))
      this.edit.setText(localBundle.getString("nickname"));
  }

  public void finish()
  {
    setResult(1);
    super.finish();
  }

  public void finish(int paramInt1, int paramInt2)
  {
    setResult(1);
    super.finish(paramInt1, paramInt2);
  }

  protected void initData()
  {
    this.mBackListener = new CallBackListener();
    this.mHandler = new SetNickNameHandler();
  }

  protected void initListener()
  {
    this.buttonBack.setOnClickListener(this.mBackListener);
    this.buttonSave.setOnClickListener(this.mBackListener);
  }

  protected void initView()
  {
    this.buttonBack = ((Button)findViewById(2131099940));
    this.buttonSave = ((Button)findViewById(2131099941));
    this.edit = ((EditText)findViewById(2131099942));
  }

  public void onError(String paramString1, String paramString2, int paramInt)
  {
    super.onError(paramString1, paramString2, paramInt);
    this.mHandler.sendEmptyMessage(5);
    this.mHandler.sendEmptyMessage(4);
  }

  public void onSuccess(String paramString1, Map<String, String> paramMap, String paramString2, int paramInt)
  {
    super.onSuccess(paramString1, paramMap, paramString2, paramInt);
    this.mHandler.sendEmptyMessage(5);
    switch (paramInt)
    {
    default:
    case 1:
    }
    do
      return;
    while (paramString1 == null);
    try
    {
      ResultState localResultState = (ResultState)UrlFactory.getInstanceGson().fromJson(paramString1, ResultState.class);
      if ((localResultState != null) && (localResultState.getState().equals("STATE_OK")))
      {
        this.mHandler.sendEmptyMessage(1);
        return;
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      this.mHandler.sendEmptyMessage(3);
      return;
    }
    this.mHandler.sendEmptyMessage(2);
  }

  protected void setContentView()
  {
    setContentView(2130903072);
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
      case 2131099940:
        SetNickNameActivity.this.finish();
        return;
      case 2131099941:
      }
      if (SetNickNameActivity.this.edit.getText().toString().equals(""))
      {
        Toast.makeText(SetNickNameActivity.this.mContext, "昵称不能为空", 0).show();
        return;
      }
      SetNickNameActivity.this.mProTools.startDialog();
      ChangePersonalInfo localChangePersonalInfo = new ChangePersonalInfo();
      localChangePersonalInfo.setVersionName(SetNickNameActivity.this.mSP.getVersionName());
      localChangePersonalInfo.setClientType(ToolsUtils.getSDK());
      localChangePersonalInfo.setImei(ToolsUtils.getImei(SetNickNameActivity.this.mContext));
      localChangePersonalInfo.setNet(ToolsUtils.getStringNetworkType(SetNickNameActivity.this.mContext));
      localChangePersonalInfo.setUid(SetNickNameActivity.this.mSP.getUserId());
      UserBaseInfo localUserBaseInfo = new UserBaseInfo();
      localUserBaseInfo.setNickname(SetNickNameActivity.this.edit.getText().toString());
      localChangePersonalInfo.setUserBasicInfo(localUserBaseInfo);
      UrlFactory.getInstance().changePersonalInfo().constructUrl(SetNickNameActivity.this, localChangePersonalInfo, 1);
    }
  }

  class SetNickNameHandler extends Handler
  {
    SetNickNameHandler()
    {
    }

    public void handleMessage(Message paramMessage)
    {
      super.handleMessage(paramMessage);
      switch (paramMessage.what)
      {
      case 5:
      default:
        return;
      case 1:
        PersonalFragment.mHandler.sendEmptyMessage(4);
        SetNickNameActivity.this.finish();
        return;
      case 2:
        Toast.makeText(SetNickNameActivity.this.mContext, "服务器未能返回数据，请稍后再试", 0).show();
        return;
      case 3:
        Toast.makeText(SetNickNameActivity.this.mContext, "服务器未响应，请稍后再试", 0).show();
        return;
      case 4:
      }
      Toast.makeText(SetNickNameActivity.this.mContext, "服务器未响应，请稍后再试", 0).show();
    }
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.activity.SetNickNameActivity
 * JD-Core Version:    0.6.0
 */