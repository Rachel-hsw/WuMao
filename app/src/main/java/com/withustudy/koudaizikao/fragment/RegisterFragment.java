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
import com.withustudy.koudaizikao.action.AbsBaseUrlConstructor;
import com.withustudy.koudaizikao.action.UrlFactory;
import com.withustudy.koudaizikao.action.post.AcKouDaiRegistImpl;
import com.withustudy.koudaizikao.activity.LoginActivity;
import com.withustudy.koudaizikao.activity.MainActivity;
import com.withustudy.koudaizikao.activity.SkipActivity;
import com.withustudy.koudaizikao.activity.SkipActivity.SkipHandler;
import com.withustudy.koudaizikao.base.AbsBaseFragment;
import com.withustudy.koudaizikao.config.KouDaiSP;
import com.withustudy.koudaizikao.custom.MyCountTimer;
import com.withustudy.koudaizikao.entity.AuthCodeBean;
import com.withustudy.koudaizikao.entity.RegistBean;
import com.withustudy.koudaizikao.entity.req.Regist;
import com.withustudy.koudaizikao.tools.MD5Tools;
import com.withustudy.koudaizikao.tools.ProTools;
import com.withustudy.koudaizikao.tools.ToolsUtils;
import java.util.Map;

public class RegisterFragment extends AbsBaseFragment
{
  public static final int ACTION_GET_AUTH_CODE = 10;
  public static final int ACTION_REGISTER = 11;
  public static final int ERROR = 6;
  public static final int HIDE = 60;
  public static final int JSON_ERROR = 13;
  public static final int REGIST = 1;
  public static final int REGIST_ERROR = 3;
  public static final int REGIST_FAIL = 4;
  public static final int SERVICE_EXCEPTION_ERROR = 14;
  public static final int VERFYCODE_HAVE_REGIST = 16;
  public static final int VERFYCODE_NONE_DATE = 15;
  private Button buttonCode;
  private Button buttonRegister;
  private EditText editCode;
  private EditText editNickname;
  private EditText editPassword;
  private EditText editPhoneNumber;
  private LoginActivity mActivity;
  private CallBackListener mBackListener;
  private RegisterHandler mHandler;
  private InputMethodManager manager;
  private TextView textToLogin;

  public RegisterFragment(LoginActivity paramLoginActivity)
  {
    this.mActivity = paramLoginActivity;
  }

  private void regist()
  {
    String str1 = this.editPhoneNumber.getText().toString().trim();
    if (ToolsUtils.isMobileNO(str1))
    {
      String str2 = this.editCode.getText().toString().trim();
      if ((str2 != null) && (!str2.equals("")))
      {
        if (!this.editNickname.getText().toString().equals(""))
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
          Regist localRegist = new Regist();
          localRegist.setVersionName(this.mSP.getVersionName());
          localRegist.setClientType(ToolsUtils.getSDK());
          localRegist.setImei(ToolsUtils.getImei(this.mContext));
          localRegist.setNet(ToolsUtils.getStringNetworkType(this.mContext));
          localRegist.setAuthCode(str2);
          localRegist.setPhoneNumber(str1);
          localRegist.setAccountType("INNER_TYPE");
          localRegist.setPassWord(MD5Tools.MD5(this.editPassword.getText().toString().trim()));
          localRegist.setNickname(this.editNickname.getText().toString().trim());
          localRegist.setClientId(this.mSP.getClientId());
          this.mProTools.startDialog();
          UrlFactory.getInstance().postRegist().constructUrl(this, localRegist, 11);
          return;
        }
        Toast.makeText(this.mContext, "请输入一个昵称", 0).show();
        return;
      }
      Toast.makeText(this.mContext, "请输入验证码", 0).show();
      return;
    }
    Toast.makeText(this.mContext, "手机号非法输入", 0).show();
  }

  private void sendCode()
  {
    String str = this.editPhoneNumber.getText().toString().trim();
    if (ToolsUtils.isMobileNO(str))
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
      this.mProTools.startDialog();
      UrlFactory.getInstance().getAuthCode().constructUrl(this, new String[] { str, "NEW_USER" }, 10, this.mContext);
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
    this.mHandler = new RegisterHandler();
    this.manager = ((InputMethodManager)this.mContext.getSystemService("input_method"));
  }

  public void initListener()
  {
    this.buttonCode.setOnClickListener(this.mBackListener);
    this.textToLogin.setOnClickListener(this.mBackListener);
    this.buttonRegister.setOnClickListener(this.mBackListener);
  }

  public void initView(View paramView)
  {
    this.buttonRegister = ((Button)paramView.findViewById(2131100472));
    this.editPhoneNumber = ((EditText)paramView.findViewById(2131100466));
    this.buttonCode = ((Button)paramView.findViewById(2131100467));
    this.editCode = ((EditText)paramView.findViewById(2131100468));
    this.editPassword = ((EditText)paramView.findViewById(2131100469));
    this.editNickname = ((EditText)paramView.findViewById(2131100470));
    this.textToLogin = ((TextView)paramView.findViewById(2131100471));
  }

  public void onError(String paramString1, String paramString2, int paramInt)
  {
    super.onError(paramString1, paramString2, paramInt);
    this.mProTools.dismissDislog();
    this.mHandler.sendEmptyMessage(6);
  }

  public View onInflaterRootView(LayoutInflater paramLayoutInflater, Bundle paramBundle)
  {
    return paramLayoutInflater.inflate(2130903168, null);
  }

  public void onSuccess(String paramString1, Map<String, String> paramMap, String paramString2, int paramInt)
  {
    super.onSuccess(paramString1, paramMap, paramString2, paramInt);
    this.mProTools.dismissDislog();
    switch (paramInt)
    {
    default:
    case 10:
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
            new MyCountTimer(this.buttonCode, -851960, -6908266).start();
            return;
          }
        }
        catch (Exception localException2)
        {
          this.mHandler.sendEmptyMessage(14);
          return;
        }
        if (localAuthCodeBean.getAuthCodeState().equals("STATE_AUTHCODE_UNEXPIRED"))
        {
          this.mHandler.sendEmptyMessage(15);
          return;
        }
        if (!localAuthCodeBean.getAuthCodeState().equals("STATE_USER_ALREADY_EXIST"))
          continue;
        this.mHandler.sendEmptyMessage(16);
        return;
      }
      this.mHandler.sendEmptyMessage(13);
      return;
    case 11:
    }
    RegistBean localRegistBean;
    try
    {
      localRegistBean = (RegistBean)UrlFactory.getInstanceGson().fromJson(paramString1, RegistBean.class);
      if (localRegistBean == null)
        break label271;
      if (localRegistBean.getState().equals("STATE_OK"))
      {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(1, localRegistBean));
        return;
      }
    }
    catch (Exception localException1)
    {
      this.mHandler.sendEmptyMessage(14);
      return;
    }
    if (localRegistBean.getState().equals("STATE_AUTHCODE_ERROR"))
    {
      this.mHandler.sendEmptyMessage(3);
      return;
    }
    this.mHandler.sendEmptyMessage(4);
    return;
    label271: this.mHandler.sendEmptyMessage(13);
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
      case 2131100468:
      case 2131100469:
      case 2131100470:
      default:
        return;
      case 2131100472:
        RegisterFragment.this.mHandler.sendEmptyMessageDelayed(60, 200L);
        RegisterFragment.this.regist();
        return;
      case 2131100467:
        RegisterFragment.this.sendCode();
        return;
      case 2131100471:
      }
      RegisterFragment.this.mActivity.setFragmentContent(0);
    }
  }

  class RegisterHandler extends Handler
  {
    RegisterHandler()
    {
    }

    public void handleMessage(Message paramMessage)
    {
      super.handleMessage(paramMessage);
      switch (paramMessage.what)
      {
      default:
      case 1:
      case 3:
      case 4:
      case 15:
      case 16:
      case 60:
      }
      while (true)
      {
        return;
        RegistBean localRegistBean = (RegistBean)paramMessage.obj;
        RegisterFragment.this.mSP.setUserId(localRegistBean.getUid());
        RegisterFragment.this.mSP.setPhone(RegisterFragment.this.editPhoneNumber.getText().toString().trim());
        RegisterFragment.this.mSP.setPassword(RegisterFragment.this.editPassword.getText().toString().trim());
        RegisterFragment.this.startNewActivity(MainActivity.class, true, null);
        Toast.makeText(RegisterFragment.this.mContext, "注册成功", 0).show();
        if (SkipActivity.mHandler == null)
          continue;
        SkipActivity.mHandler.sendEmptyMessage(1);
        return;
        Toast.makeText(RegisterFragment.this.mContext, "验证码错误", 0).show();
        return;
        Toast.makeText(RegisterFragment.this.mContext, "注册失败", 0).show();
        return;
        Toast.makeText(RegisterFragment.this.mContext, "验证码未过期", 0).show();
        return;
        Toast.makeText(RegisterFragment.this.mContext, "该手机已经注册！", 0).show();
        return;
        try
        {
          if (RegisterFragment.this.manager == null)
            continue;
          RegisterFragment.this.manager.hideSoftInputFromWindow(((Activity)RegisterFragment.this.mContext).getCurrentFocus().getWindowToken(), 2);
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
 * Qualified Name:     com.withustudy.koudaizikao.fragment.RegisterFragment
 * JD-Core Version:    0.6.0
 */