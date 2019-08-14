package com.withustudy.koudaizikao.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.Gson;
import com.withustudy.koudaizikao.action.AbsSimpleImplUrlFactory;
import com.withustudy.koudaizikao.action.UrlFactory;
import com.withustudy.koudaizikao.activity.LoginActivity;
import com.withustudy.koudaizikao.activity.MainActivity;
import com.withustudy.koudaizikao.activity.ReSetPasswordActivity;
import com.withustudy.koudaizikao.activity.SkipActivity;
import com.withustudy.koudaizikao.activity.SkipActivity.SkipHandler;
import com.withustudy.koudaizikao.base.AbsBaseFragment;
import com.withustudy.koudaizikao.config.KouDaiSP;
import com.withustudy.koudaizikao.entity.LoginBean;
import com.withustudy.koudaizikao.entity.req.Login;
import com.withustudy.koudaizikao.tools.MD5Tools;
import com.withustudy.koudaizikao.tools.ProTools;
import com.withustudy.koudaizikao.tools.ToolsUtils;
import java.util.Map;

public class LoginFragment extends AbsBaseFragment
{
  public static final int ACTION_LOGIN = 0;
  public static final int ERROR = 6;
  public static final int HIDE = 60;
  public static final int JSON_ERROR = 13;
  public static final int LOGIN = 2;
  public static final int LOGIN_FAIL = 5;
  public static final int SERVICE_EXCEPTION_ERROR = 14;
  private Button buttonLogin;
  private EditText editPassword;
  private EditText editPhoneNumber;
  private LoginActivity mActivity;
  private CallBackListener mBackListener;
  private LoginHandler mHandler;
  private InputMethodManager manager;
  private TextView textForget;
  private TextView textGoToRegister;

  public LoginFragment(LoginActivity paramLoginActivity)
  {
    this.mActivity = paramLoginActivity;
  }

  private void login()
  {
    String str1 = this.editPhoneNumber.getText().toString().trim();
    String str2 = this.editPassword.getText().toString().trim();
    if (ToolsUtils.isMobileNO(str1))
    {
      if ((str2 != null) && (!str2.equals("")))
      {
        switch (ToolsUtils.getNetworkType(this.mContext))
        {
        default:
          Toast.makeText(this.mContext, "当前没有网络", 0).show();
          return;
        case 1:
        case 2:
        case 3:
        }
        Login localLogin = new Login();
        localLogin.setVersionName(this.mSP.getVersionName());
        localLogin.setClientType(ToolsUtils.getSDK());
        localLogin.setImei(ToolsUtils.getImei(this.mContext));
        localLogin.setNet(ToolsUtils.getStringNetworkType(this.mContext));
        localLogin.setPhoneNumber(str1);
        localLogin.setPassWord(MD5Tools.MD5(str2));
        localLogin.setAccountType("INNER_TYPE");
        localLogin.setClientId(this.mSP.getClientId());
        this.mProTools.startDialog();
        UrlFactory.getInstance().login().constructUrl(this, localLogin, 0);
        return;
      }
      Toast.makeText(this.mContext, "请输入密码", 0).show();
      return;
    }
    Toast.makeText(this.mContext, "手机号非法输入", 0).show();
  }

  public void bindData()
  {
  }

  public void initData()
  {
    this.mBackListener = new CallBackListener();
    this.mHandler = new LoginHandler();
    this.manager = ((InputMethodManager)this.mContext.getSystemService("input_method"));
  }

  public void initListener()
  {
    this.buttonLogin.setOnClickListener(this.mBackListener);
    this.textForget.setOnClickListener(this.mBackListener);
    this.textGoToRegister.setOnClickListener(this.mBackListener);
  }

  public void initView(View paramView)
  {
    this.buttonLogin = ((Button)paramView.findViewById(2131100455));
    this.editPhoneNumber = ((EditText)paramView.findViewById(2131100451));
    this.editPassword = ((EditText)paramView.findViewById(2131100452));
    this.textForget = ((TextView)paramView.findViewById(2131100453));
    this.textGoToRegister = ((TextView)paramView.findViewById(2131100454));
  }

  public void onError(String paramString1, String paramString2, int paramInt)
  {
    super.onError(paramString1, paramString2, paramInt);
    this.mProTools.dismissDislog();
    this.mHandler.sendEmptyMessage(6);
  }

  public View onInflaterRootView(LayoutInflater paramLayoutInflater, Bundle paramBundle)
  {
    return paramLayoutInflater.inflate(2130903160, null);
  }

  public void onSuccess(String paramString1, Map<String, String> paramMap, String paramString2, int paramInt)
  {
    super.onSuccess(paramString1, paramMap, paramString2, paramInt);
    this.mProTools.dismissDislog();
    switch (paramInt)
    {
    default:
      return;
    case 0:
    }
    LoginBean localLoginBean;
    try
    {
      localLoginBean = (LoginBean)UrlFactory.getInstanceGson().fromJson(paramString1, LoginBean.class);
      if (localLoginBean == null)
        break label159;
      if (localLoginBean.getState().equals("STATE_OK"))
      {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(2, localLoginBean));
        return;
      }
    }
    catch (Exception localException)
    {
      this.mHandler.sendEmptyMessage(14);
      return;
    }
    if (localLoginBean.getState().equals("STATE_USER_NOT_EXIST"))
    {
      this.mHandler.sendMessage(this.mHandler.obtainMessage(5, Integer.valueOf(1)));
      return;
    }
    this.mHandler.sendMessage(this.mHandler.obtainMessage(5, Integer.valueOf(2)));
    return;
    label159: this.mHandler.sendEmptyMessage(13);
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
      case 2131100455:
        LoginFragment.this.mHandler.sendEmptyMessageDelayed(60, 200L);
        LoginFragment.this.login();
        return;
      case 2131100453:
        LoginFragment.this.startNewActivity(ReSetPasswordActivity.class, false, null);
        return;
      case 2131100454:
      }
      LoginFragment.this.mActivity.setFragmentContent(1);
    }
  }

  class LoginHandler extends Handler
  {
    LoginHandler()
    {
    }

    public void handleMessage(Message paramMessage)
    {
      super.handleMessage(paramMessage);
      switch (paramMessage.what)
      {
      default:
      case 2:
      case 5:
      case 13:
      case 14:
      case 60:
      }
      while (true)
      {
        return;
        LoginBean localLoginBean = (LoginBean)paramMessage.obj;
        LoginFragment.this.mSP.setUserId(localLoginBean.getUid());
        LoginFragment.this.mSP.setPhone(LoginFragment.this.editPhoneNumber.getText().toString().trim());
        LoginFragment.this.mSP.setPassword(LoginFragment.this.editPassword.getText().toString().trim());
        LoginFragment.this.startNewActivity(MainActivity.class, true, null);
        if (SkipActivity.mHandler == null)
          continue;
        SkipActivity.mHandler.sendEmptyMessage(1);
        return;
        Toast.makeText(LoginFragment.this.mContext, "用户名或密码错", 0).show();
        return;
        Toast.makeText(LoginFragment.this.mContext, "服务器未能返回数据，请稍后再试", 0).show();
        return;
        Toast.makeText(LoginFragment.this.mContext, "服务器未响应，请稍后再试", 0).show();
        return;
        try
        {
          if (LoginFragment.this.manager == null)
            continue;
          LoginFragment.this.manager.hideSoftInputFromWindow(((Activity)LoginFragment.this.mContext).getCurrentFocus().getWindowToken(), 2);
          return;
        }
        catch (Exception localException)
        {
        }
      }
    }
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.fragment.LoginFragment
 * JD-Core Version:    0.6.0
 */