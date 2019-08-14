package com.withustudy.koudaizikao.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.Gson;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.bean.SocializeConfig;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.controller.listener.SocializeListeners.UMAuthListener;
import com.umeng.socialize.controller.listener.SocializeListeners.UMDataListener;
import com.umeng.socialize.exception.SocializeException;
import com.umeng.socialize.sso.UMSsoHandler;
import com.withustudy.koudaizikao.action.AbsSimpleImplUrlFactory;
import com.withustudy.koudaizikao.action.UrlFactory;
import com.withustudy.koudaizikao.base.AbsBaseActivity;
import com.withustudy.koudaizikao.config.KouDaiSP;
import com.withustudy.koudaizikao.entity.LoginBean;
import com.withustudy.koudaizikao.entity.req.LoginThird;
import com.withustudy.koudaizikao.fragment.LoginFragment;
import com.withustudy.koudaizikao.fragment.RegisterFragment;
import com.withustudy.koudaizikao.tools.LogUtils;
import com.withustudy.koudaizikao.tools.ProTools;
import com.withustudy.koudaizikao.tools.ToolsUtils;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class LoginActivity extends AbsBaseActivity
{
  public static final int ACTION_REQ_THIRD = 8;
  public static final int ERROR = 6;
  public static final int THIRD_LOGIN = 10;
  private ImageButton QQ;
  private ImageButton WeiBo;
  private ImageButton WeiXin;
  private String avatarUrl = "";
  private Button buttonBack;
  private CallBackListener mBackListener;
  private LoginHandler mHandler;
  private LoginFragment mLoginFragment;
  private RegisterFragment mRegisterFragment;
  private ScrollView mScrollView;
  private InputMethodManager manager;
  private String nickname = "";
  private TextView textTitle;
  private String thridUid = "";
  private String type = "";

  private void getUserInfo(SHARE_MEDIA paramSHARE_MEDIA)
  {
    this.mController.getPlatformInfo(this, paramSHARE_MEDIA, new SocializeListeners.UMDataListener()
    {
      public void onComplete(int paramInt, Map<String, Object> paramMap)
      {
        StringBuilder localStringBuilder;
        Iterator localIterator;
        if (paramMap != null)
        {
          LogUtils.myLog("----------------------------第三方信息----------------------------------");
          localStringBuilder = new StringBuilder();
          localIterator = paramMap.keySet().iterator();
          if (localIterator.hasNext())
            break label288;
          LogUtils.myToast(LoginActivity.this, localStringBuilder.toString());
          LogUtils.myLog("----------------------------第三方信息----------------------------------");
          if (!LoginActivity.this.type.equals("WEIBO_TYPE"))
            break label373;
          LoginActivity.this.nickname = ((String)paramMap.get("screen_name"));
          LoginActivity.this.avatarUrl = ((String)paramMap.get("profile_image_url"));
          LogUtils.myLog(LoginActivity.this.nickname + ":" + LoginActivity.this.avatarUrl);
        }
        while (true)
        {
          LoginThird localLoginThird = new LoginThird();
          localLoginThird.setVersionName(LoginActivity.this.mSP.getVersionName());
          localLoginThird.setClientType(ToolsUtils.getSDK());
          localLoginThird.setImei(ToolsUtils.getImei(LoginActivity.this.mContext));
          localLoginThird.setNet(ToolsUtils.getStringNetworkType(LoginActivity.this.mContext));
          localLoginThird.setAccountType(LoginActivity.this.type);
          localLoginThird.setThirdPartyToken(LoginActivity.this.thridUid);
          localLoginThird.setNickname(LoginActivity.this.nickname);
          localLoginThird.setProfileUrl(LoginActivity.this.avatarUrl);
          localLoginThird.setClientId(LoginActivity.this.mSP.getClientId());
          UrlFactory.getInstance().login().constructUrl(LoginActivity.this, localLoginThird, 8);
          return;
          label288: String str = (String)localIterator.next();
          LogUtils.myLog(str + "::--" + paramMap.get(str));
          localStringBuilder.append(str + "::--" + paramMap.get(str));
          break;
          label373: if (LoginActivity.this.type.equals("WEIXIN_TYPE"))
          {
            LoginActivity.this.nickname = ((String)paramMap.get("nickname"));
            LoginActivity.this.avatarUrl = ((String)paramMap.get("headimgurl"));
            LogUtils.myLog(LoginActivity.this.nickname + ":" + LoginActivity.this.avatarUrl);
            continue;
          }
          if (!LoginActivity.this.type.equals("QQ_TYPE"))
            continue;
          LoginActivity.this.nickname = ((String)paramMap.get("screen_name"));
          LoginActivity.this.avatarUrl = ((String)paramMap.get("profile_image_url"));
          LogUtils.myLog(LoginActivity.this.nickname + ":" + LoginActivity.this.avatarUrl);
        }
      }

      public void onStart()
      {
      }
    });
  }

  private void login(SHARE_MEDIA paramSHARE_MEDIA)
  {
    this.mController.doOauthVerify(this, paramSHARE_MEDIA, new SocializeListeners.UMAuthListener()
    {
      public void onCancel(SHARE_MEDIA paramSHARE_MEDIA)
      {
      }

      public void onComplete(Bundle paramBundle, SHARE_MEDIA paramSHARE_MEDIA)
      {
        LoginActivity.this.thridUid = paramBundle.getString("uid");
        LogUtils.myLog("thridUid", LoginActivity.this.thridUid);
        if (!TextUtils.isEmpty(LoginActivity.this.thridUid))
        {
          LoginActivity.this.getUserInfo(paramSHARE_MEDIA);
          return;
        }
        LogUtils.myLog("授权失败");
      }

      public void onError(SocializeException paramSocializeException, SHARE_MEDIA paramSHARE_MEDIA)
      {
      }

      public void onStart(SHARE_MEDIA paramSHARE_MEDIA)
      {
        LogUtils.myLog("开始获取thridUid");
      }
    });
  }

  protected void bindData()
  {
    String str = getIntent().getStringExtra("flag");
    if (str.equals("0"))
      setFragmentContent(0);
    if (str.equals("1"))
      setFragmentContent(1);
  }

  protected void initData()
  {
    this.mHandler = new LoginHandler();
    this.mBackListener = new CallBackListener();
    this.mLoginFragment = new LoginFragment(this);
    this.mRegisterFragment = new RegisterFragment(this);
    this.manager = ((InputMethodManager)this.mContext.getSystemService("input_method"));
  }

  protected void initListener()
  {
    this.WeiBo.setOnClickListener(this.mBackListener);
    this.WeiXin.setOnClickListener(this.mBackListener);
    this.QQ.setOnClickListener(this.mBackListener);
    this.buttonBack.setOnClickListener(this.mBackListener);
    this.mScrollView.setOnTouchListener(this.mBackListener);
  }

  protected void initView()
  {
    this.textTitle = ((TextView)findViewById(2131099709));
    this.WeiXin = ((ImageButton)findViewById(2131099828));
    this.QQ = ((ImageButton)findViewById(2131099827));
    this.WeiBo = ((ImageButton)findViewById(2131099826));
    this.buttonBack = ((Button)findViewById(2131099823));
    this.mScrollView = ((ScrollView)findViewById(2131099824));
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    UMSsoHandler localUMSsoHandler = SocializeConfig.getSocializeConfig().getSsoHandler(paramInt1);
    if (localUMSsoHandler != null)
      localUMSsoHandler.authorizeCallBack(paramInt1, paramInt2, paramIntent);
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
    if (paramString1 != null);
    switch (paramInt)
    {
    default:
      return;
    case 8:
    }
    LoginBean localLoginBean = (LoginBean)UrlFactory.getInstanceGson().fromJson(paramString1, LoginBean.class);
    if (localLoginBean != null)
    {
      this.mHandler.sendMessage(this.mHandler.obtainMessage(10, localLoginBean));
      return;
    }
    this.mHandler.sendEmptyMessage(6);
  }

  protected void setContentView()
  {
    setContentView(2130903059);
  }

  public void setFragmentContent(int paramInt)
  {
    FragmentTransaction localFragmentTransaction = this.mFragmentManager.beginTransaction();
    if (paramInt == 0)
    {
      this.textTitle.setText("登录");
      localFragmentTransaction.replace(2131099825, this.mLoginFragment).commit();
      return;
    }
    this.textTitle.setText("新用户注册");
    localFragmentTransaction.replace(2131099825, this.mRegisterFragment).commit();
  }

  class CallBackListener
    implements OnClickListener, OnTouchListener
  {
    CallBackListener()
    {
    }

    public void onClick(View paramView)
    {
      switch (paramView.getId())
      {
      case 2131099824:
      case 2131099825:
      default:
        return;
      case 2131099823:
        LoginActivity.this.finish();
        return;
      case 2131099826:
        LoginActivity.this.mProTools.startDialog();
        LoginActivity.this.type = "WEIBO_TYPE";
        LoginActivity.this.login(SHARE_MEDIA.SINA);
        return;
      case 2131099828:
        LoginActivity.this.mProTools.startDialog();
        LoginActivity.this.type = "WEIXIN_TYPE";
        LoginActivity.this.login(SHARE_MEDIA.WEIXIN);
        return;
      case 2131099827:
      }
      LoginActivity.this.mProTools.startDialog();
      LoginActivity.this.type = "QQ_TYPE";
      LoginActivity.this.login(SHARE_MEDIA.QQ);
    }

    public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
    {
      LoginActivity.this.manager.hideSoftInputFromWindow(LoginActivity.this.getCurrentFocus().getWindowToken(), 2);
      return false;
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
      case 6:
      case 10:
      }
      do
      {
        LoginBean localLoginBean;
        do
        {
          return;
          Toast.makeText(LoginActivity.this.mContext, "服务器未响应，请稍后再试", 0).show();
          return;
          localLoginBean = (LoginBean)paramMessage.obj;
        }
        while (!"STATE_OK".equals(localLoginBean.getState()));
        LoginActivity.this.mSP.setUserId(localLoginBean.getUid());
        LoginActivity.this.mSP.setThirdId(LoginActivity.this.thridUid);
        LoginActivity.this.mSP.setThirdType(LoginActivity.this.type);
        LoginActivity.this.mSP.setNickName(LoginActivity.this.nickname);
        LoginActivity.this.mSP.setAvatar(LoginActivity.this.avatarUrl);
        LoginActivity.this.startNewActivity(MainActivity.class, true, null);
      }
      while (SkipActivity.mHandler == null);
      SkipActivity.mHandler.sendEmptyMessage(1);
    }
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.activity.LoginActivity
 * JD-Core Version:    0.6.0
 */