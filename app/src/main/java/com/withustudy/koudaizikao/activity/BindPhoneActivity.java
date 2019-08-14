package com.withustudy.koudaizikao.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
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
import com.withustudy.koudaizikao.entity.RegistBean;
import com.withustudy.koudaizikao.entity.req.BindPhone;
import com.withustudy.koudaizikao.tools.MD5Tools;
import com.withustudy.koudaizikao.tools.ProTools;
import com.withustudy.koudaizikao.tools.ToolsUtils;
import java.util.Map;

public class BindPhoneActivity extends AbsBaseActivity
{
  public static final int ACTION_BIND = 11;
  public static final int ACTION_GET_AUTH_CODE = 10;
  public static final int BIND = 1;
  public static final int BIND_ERROR = 2;
  public static final int BIND_FAIL = 3;
  public static final int ERROR = 6;
  public static final int HIDE = 60;
  public static final int JSON_ERROR = 13;
  public static final int SERVICE_EXCEPTION_ERROR = 14;
  public static final int VERFYCODE_HAVE_REGIST = 16;
  public static final int VERFYCODE_NONE_DATE = 15;
  private Button buttonBack;
  private Button buttonBind;
  private Button buttonCode;
  private EditText editCode;
  private EditText editPassWord;
  private EditText editPhoneNumber;
  private CallBackListener mBackListener;
  private BindHandler mHandler;
  private InputMethodManager manager;

  private void bind()
  {
    String str1 = this.editPhoneNumber.getText().toString().trim();
    if (ToolsUtils.isMobileNO(str1))
    {
      String str2 = this.editCode.getText().toString().trim();
      if ((str2 != null) && (!str2.equals("")))
      {
        String str3 = this.editPassWord.getText().toString().trim();
        if (!str3.equals(""))
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
          BindPhone localBindPhone = new BindPhone();
          localBindPhone.setVersionName(this.mSP.getVersionName());
          localBindPhone.setClientType(ToolsUtils.getSDK());
          localBindPhone.setImei(ToolsUtils.getImei(this.mContext));
          localBindPhone.setNet(ToolsUtils.getStringNetworkType(this.mContext));
          localBindPhone.setAuthCode(str2);
          localBindPhone.setPhoneNumber(str1);
          localBindPhone.setAccountType("INNER_TYPE");
          localBindPhone.setPassWord(MD5Tools.MD5(str3));
          localBindPhone.setUid(this.mSP.getUserId());
          this.mProTools.startDialog();
          UrlFactory.getInstance().bindPhone().constructUrl(this, localBindPhone, 11);
          return;
        }
        Toast.makeText(this.mContext, "请输入密码", 0).show();
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
      UrlFactory.getInstance().getAuthCode().constructUrl(this, new String[] { str, "BIND_USER" }, 10, this.mContext);
      return;
    }
    Toast.makeText(this.mContext, "手机号非法输入", 0).show();
  }

  protected void bindData()
  {
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
    this.mHandler = new BindHandler();
    this.manager = ((InputMethodManager)this.mContext.getSystemService("input_method"));
  }

  protected void initListener()
  {
    this.buttonBack.setOnClickListener(this.mBackListener);
    this.buttonBind.setOnClickListener(this.mBackListener);
    this.buttonCode.setOnClickListener(this.mBackListener);
  }

  protected void initView()
  {
    this.buttonBack = ((Button)findViewById(2131099718));
    this.buttonBind = ((Button)findViewById(2131099723));
    this.editPhoneNumber = ((EditText)findViewById(2131099719));
    this.buttonCode = ((Button)findViewById(2131099720));
    this.editCode = ((EditText)findViewById(2131099721));
    this.editPassWord = ((EditText)findViewById(2131099722));
  }

  public void onError(String paramString1, String paramString2, int paramInt)
  {
    super.onError(paramString1, paramString2, paramInt);
    this.mProTools.dismissDislog();
    this.mHandler.sendEmptyMessage(6);
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
        break label262;
      if (localRegistBean.getState().equals("STATE_OK"))
      {
        this.mHandler.sendEmptyMessage(1);
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
      this.mHandler.sendEmptyMessage(2);
      return;
    }
    this.mHandler.sendEmptyMessage(3);
    return;
    label262: this.mHandler.sendEmptyMessage(13);
  }

  protected void setContentView()
  {
    setContentView(2130903045);
  }

  class BindHandler extends Handler
  {
    BindHandler()
    {
    }

    public void handleMessage(Message paramMessage)
    {
      super.handleMessage(paramMessage);
      switch (paramMessage.what)
      {
      default:
      case 1:
      case 2:
      case 3:
      case 15:
      case 16:
      case 60:
      }
      while (true)
      {
        return;
        BindPhoneActivity.this.mSP.setPhone(BindPhoneActivity.this.editPhoneNumber.getText().toString().trim());
        BindPhoneActivity.this.mSP.setPassword(BindPhoneActivity.this.editPassWord.getText().toString().trim());
        Toast.makeText(BindPhoneActivity.this.mContext, "绑定成功", 0).show();
        BindPhoneActivity.this.finish();
        return;
        Toast.makeText(BindPhoneActivity.this.mContext, "验证码错误", 0).show();
        return;
        Toast.makeText(BindPhoneActivity.this.mContext, "绑定失败", 0).show();
        return;
        Toast.makeText(BindPhoneActivity.this.mContext, "验证码未过期", 0).show();
        return;
        Toast.makeText(BindPhoneActivity.this.mContext, "该手机已经注册！", 0).show();
        return;
        try
        {
          if (BindPhoneActivity.this.manager == null)
            continue;
          BindPhoneActivity.this.manager.hideSoftInputFromWindow(((Activity)BindPhoneActivity.this.mContext).getCurrentFocus().getWindowToken(), 2);
          return;
        }
        catch (Exception localException)
        {
        }
      }
    }
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
      case 2131099719:
      case 2131099721:
      case 2131099722:
      default:
        return;
      case 2131099718:
        BindPhoneActivity.this.finish();
        return;
      case 2131099723:
        BindPhoneActivity.this.mHandler.sendEmptyMessageDelayed(60, 200L);
        BindPhoneActivity.this.bind();
        return;
      case 2131099720:
      }
      BindPhoneActivity.this.sendCode();
    }
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.activity.BindPhoneActivity
 * JD-Core Version:    0.6.0
 */