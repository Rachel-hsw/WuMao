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
import com.withustudy.koudaizikao.action.AbsBaseUrlConstructor;
import com.withustudy.koudaizikao.action.AbsSimpleImplUrlFactory;
import com.withustudy.koudaizikao.action.UrlFactory;
import com.withustudy.koudaizikao.base.AbsBaseActivity;
import com.withustudy.koudaizikao.config.KouDaiSP;
import com.withustudy.koudaizikao.custom.MyCountTimer;
import com.withustudy.koudaizikao.entity.AuthCodeBean;
import com.withustudy.koudaizikao.entity.content.ResultState;
import com.withustudy.koudaizikao.entity.req.ForgetPassword;
import com.withustudy.koudaizikao.tools.MD5Tools;
import com.withustudy.koudaizikao.tools.ProTools;
import com.withustudy.koudaizikao.tools.ToolsUtils;
import java.util.Map;

public class ReSetPasswordActivity extends AbsBaseActivity
{
  public static final int ACTION_GET_IDENTIFYCODE = 10;
  public static final int ACTION_SUBMIT = 11;
  public static final int SUBMIT = 1;
  public static final int SUBMIT_ERROR = 4;
  public static final int SUBMIT_FAIL1 = 2;
  public static final int SUBMIT_FAIL2 = 3;
  private Button buttonBack;
  private Button buttonGetIdentifyCode;
  private Button buttonSubmit;
  private EditText editIdentifyCode;
  private EditText editNewPassword;
  private EditText editPhone;
  private CallBackListener mBackListener;
  private ReSetPasswordHandler mHandler;

  private boolean checkInput()
  {
    if (this.editPhone.getText().toString().equals(""))
    {
      Toast.makeText(this.mContext, "请输入手机号", 1).show();
      return false;
    }
    if (this.editIdentifyCode.getText().toString().equals(""))
    {
      Toast.makeText(this.mContext, "请输入验证码", 1).show();
      return false;
    }
    if (this.editNewPassword.getText().toString().equals(""))
    {
      Toast.makeText(this.mContext, "请输入新密码", 1).show();
      return false;
    }
    return true;
  }

  protected void bindData()
  {
  }

  protected void initData()
  {
    this.mBackListener = new CallBackListener();
    this.mHandler = new ReSetPasswordHandler();
  }

  protected void initListener()
  {
    this.buttonBack.setOnClickListener(this.mBackListener);
    this.buttonGetIdentifyCode.setOnClickListener(this.mBackListener);
    this.buttonSubmit.setOnClickListener(this.mBackListener);
  }

  protected void initView()
  {
    this.buttonBack = ((Button)findViewById(2131099910));
    this.editPhone = ((EditText)findViewById(2131099911));
    this.buttonGetIdentifyCode = ((Button)findViewById(2131099912));
    this.editIdentifyCode = ((EditText)findViewById(2131099913));
    this.editNewPassword = ((EditText)findViewById(2131099914));
    this.buttonSubmit = ((Button)findViewById(2131099915));
  }

  public void onError(String paramString1, String paramString2, int paramInt)
  {
    super.onError(paramString1, paramString2, paramInt);
    this.mProTools.dismissDislog();
  }

  public void onSuccess(String paramString1, Map<String, String> paramMap, String paramString2, int paramInt)
  {
    super.onSuccess(paramString1, paramMap, paramString2, paramInt);
    this.mProTools.dismissDislog();
    switch (paramInt)
    {
    default:
    case 10:
    case 11:
    }
    ResultState localResultState;
    do
    {
      do
      {
        while (true)
        {
          return;
          AuthCodeBean localAuthCodeBean;
          try
          {
            localAuthCodeBean = (AuthCodeBean)UrlFactory.getInstanceGson().fromJson(paramString1, AuthCodeBean.class);
            if (localAuthCodeBean == null)
              break;
            if (localAuthCodeBean.getAuthCodeState().equals("STATE_OK"))
            {
              new MyCountTimer(this.buttonGetIdentifyCode, -851960, -6908266).start();
              return;
            }
          }
          catch (Exception localException2)
          {
            Toast.makeText(getApplicationContext(), "服务器未响应，请稍后再试", 0).show();
            return;
          }
          if (localAuthCodeBean.getAuthCodeState().equals("STATE_AUTHCODE_UNEXPIRED"))
          {
            Toast.makeText(getApplicationContext(), "验证码未过期", 0).show();
            return;
          }
          if (!localAuthCodeBean.getAuthCodeState().equals("STATE_USER_ALREADY_EXIST"))
            continue;
          Toast.makeText(getApplicationContext(), "该手机已经注册！", 0).show();
          return;
        }
        Toast.makeText(getApplicationContext(), "服务器未能返回数据，请稍后再试", 0).show();
        return;
      }
      while (paramString1 == null);
      try
      {
        localResultState = (ResultState)UrlFactory.getInstanceGson().fromJson(paramString1, ResultState.class);
        if ((localResultState == null) || (!localResultState.getState().equals("STATE_OK")))
          continue;
        this.mHandler.sendEmptyMessage(1);
        return;
      }
      catch (Exception localException1)
      {
        localException1.printStackTrace();
        this.mHandler.sendEmptyMessage(3);
        return;
      }
    }
    while ((localResultState != null) && (localResultState.getState().equals("STATE_AUTHCODE_ERROR")));
    this.mHandler.sendEmptyMessage(2);
  }

  protected void setContentView()
  {
    setContentView(2130903069);
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
      case 2131099911:
      case 2131099913:
      case 2131099914:
      default:
      case 2131099910:
      case 2131099912:
      case 2131099915:
      }
      do
      {
        return;
        ReSetPasswordActivity.this.finish();
        return;
        if (ReSetPasswordActivity.this.editPhone.getText().toString().equals(""))
        {
          Toast.makeText(ReSetPasswordActivity.this.mContext, "请输入手机号", 0).show();
          return;
        }
        if (!ToolsUtils.isMobileNO(ReSetPasswordActivity.this.editPhone.getText().toString().trim()))
        {
          Toast.makeText(ReSetPasswordActivity.this.mContext, "请输入一个正确的手机号", 0).show();
          return;
        }
        switch (ToolsUtils.getNetworkType(ReSetPasswordActivity.this.getApplicationContext()))
        {
        default:
          Toast.makeText(ReSetPasswordActivity.this.getApplicationContext(), "无法连接服务器，请检查网络设置", 0).show();
          return;
        case 1:
        case 2:
        case 3:
        }
        ReSetPasswordActivity.this.mProTools.startDialog(true);
        AbsBaseUrlConstructor localAbsBaseUrlConstructor = UrlFactory.getInstance().getAuthCode();
        ReSetPasswordActivity localReSetPasswordActivity = ReSetPasswordActivity.this;
        String[] arrayOfString = new String[2];
        arrayOfString[0] = ReSetPasswordActivity.access$1(ReSetPasswordActivity.this).getText().toString();
        arrayOfString[1] = "FORGET_USER";
        localAbsBaseUrlConstructor.constructUrl(localReSetPasswordActivity, arrayOfString, 10, ReSetPasswordActivity.this.mContext);
        return;
      }
      while (!ReSetPasswordActivity.this.checkInput());
      ForgetPassword localForgetPassword = new ForgetPassword();
      localForgetPassword.setVersionName(ReSetPasswordActivity.this.mSP.getVersionName());
      localForgetPassword.setClientType(ToolsUtils.getSDK());
      localForgetPassword.setImei(ToolsUtils.getImei(ReSetPasswordActivity.this.mContext));
      localForgetPassword.setNet(ToolsUtils.getStringNetworkType(ReSetPasswordActivity.this.mContext));
      localForgetPassword.setPhoneNumber(ReSetPasswordActivity.this.editPhone.getText().toString());
      localForgetPassword.setAuthCode(ReSetPasswordActivity.this.editIdentifyCode.getText().toString());
      localForgetPassword.setNewPassWord(MD5Tools.MD5(ReSetPasswordActivity.this.editNewPassword.getText().toString()));
      UrlFactory.getInstance().updatePassword().constructUrl(ReSetPasswordActivity.this, localForgetPassword, 11);
      ReSetPasswordActivity.this.mProTools.startDialog();
    }
  }

  class ReSetPasswordHandler extends Handler
  {
    ReSetPasswordHandler()
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
        ReSetPasswordActivity.this.finish();
        return;
      case 2:
        Toast.makeText(ReSetPasswordActivity.this.mContext, "服务器未能返回数据，请稍后再试", 0).show();
        return;
      case 3:
        Toast.makeText(ReSetPasswordActivity.this.mContext, "服务器未响应，请稍后再试", 0).show();
        return;
      case 4:
      }
      Toast.makeText(ReSetPasswordActivity.this.mContext, "验证码错误", 0).show();
    }
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.activity.ReSetPasswordActivity
 * JD-Core Version:    0.6.0
 */