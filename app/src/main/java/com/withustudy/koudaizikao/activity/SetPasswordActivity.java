package com.withustudy.koudaizikao.activity;

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
import com.withustudy.koudaizikao.entity.req.Login;
import com.withustudy.koudaizikao.entity.req.UpdatePassword;
import com.withustudy.koudaizikao.tools.MD5Tools;
import com.withustudy.koudaizikao.tools.ProTools;
import com.withustudy.koudaizikao.tools.ToolsUtils;
import java.util.Map;

public class SetPasswordActivity extends AbsBaseActivity
{
  public static final int ACTION_CHECK_PASSWORD = 10;
  public static final int ACTION_SET_PASSWORD = 11;
  public static final int CHECK_FAIL1 = 2;
  public static final int CHECK_FAIL2 = 3;
  public static final int CHECK_SUCCESS = 1;
  public static final int DISMISS_DIALOG = 6;
  public static final int UPDATE = 4;
  public static final int UPDATE_FAIL = 5;
  private Button buttonBack;
  private Button buttonSave;
  private EditText editNew;
  private EditText editOld;
  private EditText editSure;
  private CallBackListener mBackListener;
  private SetPasswordHandler mHandler;

  private boolean checkInput()
  {
    if (this.editOld.getText().toString().equals(""))
    {
      Toast.makeText(this.mContext, "请输入原密码", 0).show();
      return false;
    }
    if (this.editNew.getText().toString().equals(""))
    {
      Toast.makeText(this.mContext, "请输入新密码", 0).show();
      return false;
    }
    if (this.editSure.getText().toString().equals(""))
    {
      Toast.makeText(this.mContext, "请再次输入新密码", 0).show();
      return false;
    }
    if (!this.editSure.getText().toString().equals(this.editNew.getText().toString()))
    {
      Toast.makeText(this.mContext, "密码不一致，请重新输入", 0).show();
      return false;
    }
    if (this.editOld.getText().toString().equals(this.editNew.getText().toString()))
    {
      Toast.makeText(this.mContext, "新密码不能和旧密码相同，请重新输入", 0).show();
      return false;
    }
    return true;
  }

  protected void bindData()
  {
  }

  protected void initData()
  {
    this.mHandler = new SetPasswordHandler();
    this.mBackListener = new CallBackListener();
  }

  protected void initListener()
  {
    this.buttonBack.setOnClickListener(this.mBackListener);
    this.buttonSave.setOnClickListener(this.mBackListener);
  }

  protected void initView()
  {
    this.buttonBack = ((Button)findViewById(2131099943));
    this.buttonSave = ((Button)findViewById(2131099947));
    this.editOld = ((EditText)findViewById(2131099944));
    this.editNew = ((EditText)findViewById(2131099945));
    this.editSure = ((EditText)findViewById(2131099946));
  }

  public void onError(String paramString1, String paramString2, int paramInt)
  {
    super.onError(paramString1, paramString2, paramInt);
    this.mProTools.dismissDislog();
    Toast.makeText(this.mContext, "服务器未响应，请稍后再试", 0).show();
  }

  public void onSuccess(String paramString1, Map<String, String> paramMap, String paramString2, int paramInt)
  {
    super.onSuccess(paramString1, paramMap, paramString2, paramInt);
    switch (paramInt)
    {
    default:
    case 10:
    case 11:
    }
    label120: 
    do
    {
      do
        return;
      while (paramString1 == null);
      try
      {
        ResultState localResultState2 = (ResultState)UrlFactory.getInstanceGson().fromJson(paramString1, ResultState.class);
        if (localResultState2 == null)
          break label120;
        if (localResultState2.getState().equals("STATE_OK"))
        {
          this.mHandler.sendEmptyMessage(1);
          return;
        }
      }
      catch (Exception localException2)
      {
        localException2.printStackTrace();
        this.mHandler.sendEmptyMessage(6);
        Toast.makeText(this.mContext, "服务器未响应，请稍后再试", 0).show();
        return;
      }
      this.mHandler.sendEmptyMessage(2);
      return;
      this.mHandler.sendEmptyMessage(3);
      return;
      this.mHandler.sendEmptyMessage(6);
    }
    while (paramString1 == null);
    try
    {
      ResultState localResultState1 = (ResultState)UrlFactory.getInstanceGson().fromJson(paramString1, ResultState.class);
      if ((localResultState1 != null) && (localResultState1.getState().equals("STATE_OK")))
      {
        this.mHandler.sendEmptyMessage(4);
        return;
      }
    }
    catch (Exception localException1)
    {
      localException1.printStackTrace();
      this.mHandler.sendEmptyMessage(5);
      return;
    }
    this.mHandler.sendEmptyMessage(5);
  }

  protected void setContentView()
  {
    setContentView(2130903073);
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
      case 2131099943:
      case 2131099947:
      }
      do
      {
        return;
        SetPasswordActivity.this.finish();
        return;
      }
      while (!SetPasswordActivity.this.checkInput());
      SetPasswordActivity.this.mProTools.startDialog(true);
      Login localLogin = new Login();
      localLogin.setVersionName(SetPasswordActivity.this.mSP.getVersionName());
      localLogin.setClientType(ToolsUtils.getSDK());
      localLogin.setImei(ToolsUtils.getImei(SetPasswordActivity.this.mContext));
      localLogin.setNet(ToolsUtils.getStringNetworkType(SetPasswordActivity.this.mContext));
      localLogin.setPhoneNumber(SetPasswordActivity.this.mSP.getPhone());
      localLogin.setPassWord(MD5Tools.MD5(SetPasswordActivity.this.editOld.getText().toString()));
      localLogin.setAccountType("INNER_TYPE");
      localLogin.setClientId(SetPasswordActivity.this.mSP.getClientId());
      UrlFactory.getInstance().login().constructUrl(SetPasswordActivity.this, localLogin, 10);
    }
  }

  class SetPasswordHandler extends Handler
  {
    SetPasswordHandler()
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
        UpdatePassword localUpdatePassword = new UpdatePassword();
        localUpdatePassword.setVersionName(SetPasswordActivity.this.mSP.getVersionName());
        localUpdatePassword.setClientType(ToolsUtils.getSDK());
        localUpdatePassword.setImei(ToolsUtils.getImei(SetPasswordActivity.this.mContext));
        localUpdatePassword.setNet(ToolsUtils.getStringNetworkType(SetPasswordActivity.this.mContext));
        localUpdatePassword.setUid(SetPasswordActivity.this.mSP.getUserId());
        localUpdatePassword.setOldPassWord(MD5Tools.MD5(SetPasswordActivity.this.editOld.getText().toString()));
        localUpdatePassword.setNewPassWord(MD5Tools.MD5(SetPasswordActivity.this.editNew.getText().toString()));
        UrlFactory.getInstance().updatePassword().constructUrl(SetPasswordActivity.this, localUpdatePassword, 11);
        return;
      case 2:
        SetPasswordActivity.this.mProTools.dismissDislog();
        Toast.makeText(SetPasswordActivity.this.mContext, "原密码不正确", 0).show();
        return;
      case 3:
        SetPasswordActivity.this.mProTools.dismissDislog();
        Toast.makeText(SetPasswordActivity.this.mContext, "服务器未能返回数据，请稍后再试", 0).show();
        return;
      case 4:
        SetPasswordActivity.this.finish();
        return;
      case 5:
        Toast.makeText(SetPasswordActivity.this.mContext, "服务器未能返回数据，请稍后再试", 0).show();
        return;
      case 6:
      }
      SetPasswordActivity.this.mProTools.dismissDislog();
    }
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.activity.SetPasswordActivity
 * JD-Core Version:    0.6.0
 */