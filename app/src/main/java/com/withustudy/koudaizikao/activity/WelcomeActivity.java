package com.withustudy.koudaizikao.activity;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;
import com.google.gson.Gson;
import com.igexin.sdk.PushManager;
import com.umeng.analytics.AnalyticsConfig;
import com.umeng.analytics.MobclickAgent;
import com.withustudy.koudaizikao.MyApplication;
import com.withustudy.koudaizikao.R;
import com.withustudy.koudaizikao.action.AbsSimpleImplUrlFactory;
import com.withustudy.koudaizikao.action.UrlFactory;
import com.withustudy.koudaizikao.base.AbsBaseActivity;
import com.withustudy.koudaizikao.config.KouDaiSP;
import com.withustudy.koudaizikao.entity.LoginBean;
import com.withustudy.koudaizikao.entity.req.Login;
import com.withustudy.koudaizikao.entity.req.LoginThird;
import com.withustudy.koudaizikao.tools.LogUtils;
import com.withustudy.koudaizikao.tools.MD5Tools;
import com.withustudy.koudaizikao.tools.ScreenTools;
import com.withustudy.koudaizikao.tools.ToolsUtils;
import java.util.Map;

public class WelcomeActivity extends AbsBaseActivity
{
  public static final int ACTION_LOGIN = 11;
  public static final int ACTION_THIRD_LOGIN = 12;
  public static final int CHECK = 1;
  private static double SCALE = 0.0D;
  private static double SCALE1 = 0.0D;
  private static double SCREEN_SCALE = 0.0D;
  private static double SCREEN_SCALE1 = 0.0D;
  private static final int TIME = 1000;
  public static final int TO_MAIN = 2;
  public static final int TO_SKIP = 3;
  private ImageView image;
  private ImageView image1;
  private WelcomeHandler mHandler;
  private LocationClient mLocationClient;

  static
  {
    SCALE = 0.144D;
    SCREEN_SCALE1 = 0.158D;
    SCALE1 = 0.263D;
  }

  private boolean checkIsFirstInstall()
  {
    return this.mSP.getFirst();
  }

  private void initBaiDu()
  {
    LogUtils.myLog("initBaiDu   开始定位");
    this.mLocationClient = ((MyApplication)getApplication()).mLocationClient;
    LocationClientOption localLocationClientOption = new LocationClientOption();
    localLocationClientOption.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
    localLocationClientOption.setCoorType("bd09ll");
    localLocationClientOption.setScanSpan(2000);
    localLocationClientOption.setIsNeedAddress(true);
    localLocationClientOption.setOpenGps(true);
    localLocationClientOption.setLocationNotify(true);
    localLocationClientOption.setIgnoreKillProcess(true);
    localLocationClientOption.setEnableSimulateGps(false);
    localLocationClientOption.setIsNeedLocationDescribe(true);
    localLocationClientOption.setIsNeedLocationPoiList(true);
    this.mLocationClient.setLocOption(localLocationClientOption);
    this.mLocationClient.start();
  }

  private void initGeTui()
  {
    PushManager.getInstance().initialize(getApplicationContext());
  }

  protected void bindData()
  {
    int i = (int)(this.mSP.getWidth() * SCREEN_SCALE);
    LinearLayout.LayoutParams localLayoutParams1 = new LinearLayout.LayoutParams(i, (int)(i * SCALE));
    this.image.setLayoutParams(localLayoutParams1);
    this.image.setBackgroundResource(2130837927);
    int j = (int)(this.mSP.getWidth() * SCREEN_SCALE1);
    LinearLayout.LayoutParams localLayoutParams2 = new LinearLayout.LayoutParams(j, (int)(j * SCALE1));
    this.image1.setLayoutParams(localLayoutParams2);
    this.image1.setBackgroundResource(2130837928);
    this.mHandler.sendMessageDelayed(this.mHandler.obtainMessage(1), 1000L);
  }

  public void getVersion()
  {
    try
    {
      PackageInfo localPackageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
      this.mSP.setVersionCode(String.valueOf(localPackageInfo.versionCode));
      this.mSP.setVersionName(localPackageInfo.versionName);
      return;
    }
    catch (NameNotFoundException localNameNotFoundException)
    {
      localNameNotFoundException.printStackTrace();
      this.mSP.setVersionCode("");
      this.mSP.setVersionName("");
    }
  }

  protected void initData()
  {
    MobclickAgent.openActivityDurationTrack(false);
    AnalyticsConfig.enableEncrypt(true);
    MobclickAgent.updateOnlineConfig(this.mContext);
    this.mHandler = new WelcomeHandler();
    initGeTui();
    initBaiDu();
    ScreenTools.savePra(this.mContext);
    getVersion();
  }

  protected void initListener()
  {
  }

  protected void initView()
  {
    this.image = ((ImageView)findViewById(2131100017));
    this.image1 = ((ImageView)findViewById(2131100018));
  }

  public void onError(String paramString1, String paramString2, int paramInt)
  {
    super.onError(paramString1, paramString2, paramInt);
    switch (paramInt)
    {
    default:
      return;
    case 11:
    }
    this.mHandler.sendEmptyMessage(3);
  }

  public void onSuccess(String paramString1, Map<String, String> paramMap, String paramString2, int paramInt)
  {
    super.onSuccess(paramString1, paramMap, paramString2, paramInt);
    switch (paramInt)
    {
    default:
    case 11:
    case 12:
    }
    do
      return;
    while (paramString1 == null);
    try
    {
      LoginBean localLoginBean = (LoginBean)UrlFactory.getInstanceGson().fromJson(paramString1, LoginBean.class);
      if ((localLoginBean != null) && (localLoginBean.getState().equals("STATE_OK")))
      {
        this.mHandler.sendEmptyMessage(2);
        return;
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      this.mHandler.sendEmptyMessage(3);
      return;
    }
    this.mHandler.sendEmptyMessage(3);
  }

  protected void setContentView()
  {
    setContentView(com.withustudy.koudaizikao.R.layout.activity_welcome);
  }

  class WelcomeHandler extends Handler
  {
    WelcomeHandler()
    {
    }

    public void handleMessage(Message paramMessage)
    {
      super.handleMessage(paramMessage);
      switch (paramMessage.what)
      {
      default:
      case 1:
        do
        {
          return;
          if (WelcomeActivity.this.checkIsFirstInstall())
          {
            WelcomeActivity.this.startNewActivity(GuideActivity.class, true, null);
            return;
          }
          if (WelcomeActivity.this.mSP.getUserId().equals(""))
          {
            WelcomeActivity.this.startNewActivity(SkipActivity.class, true, null);
            return;
          }
          if ((WelcomeActivity.this.mSP.getPhone().equals("")) || (WelcomeActivity.this.mSP.getPassword().equals("")))
            continue;
          Login localLogin = new Login();
          localLogin.setClientType(ToolsUtils.getSDK());
          localLogin.setImei(ToolsUtils.getImei(WelcomeActivity.this.mContext));
          localLogin.setNet(ToolsUtils.getStringNetworkType(WelcomeActivity.this.mContext));
          localLogin.setVersionName(WelcomeActivity.this.mSP.getVersionName());
          localLogin.setClientId(WelcomeActivity.this.mSP.getClientId());
          localLogin.setPhoneNumber(WelcomeActivity.this.mSP.getPhone());
          localLogin.setPassWord(MD5Tools.MD5(WelcomeActivity.this.mSP.getPassword()));
          localLogin.setAccountType("INNER_TYPE");
          UrlFactory.getInstance().login().constructUrl(WelcomeActivity.this, localLogin, 11);
          return;
        }
        while ((WelcomeActivity.this.mSP.getThirdId().equals("")) || (WelcomeActivity.this.mSP.getThirdType().equals("")));
        LoginThird localLoginThird = new LoginThird();
        localLoginThird.setVersionName(WelcomeActivity.this.mSP.getVersionName());
        localLoginThird.setClientType(ToolsUtils.getSDK());
        localLoginThird.setImei(ToolsUtils.getImei(WelcomeActivity.this.mContext));
        localLoginThird.setNet(ToolsUtils.getStringNetworkType(WelcomeActivity.this.mContext));
        localLoginThird.setAccountType(WelcomeActivity.this.mSP.getThirdType());
        localLoginThird.setThirdPartyToken(WelcomeActivity.this.mSP.getThirdId());
        localLoginThird.setNickname(WelcomeActivity.this.mSP.getNickName());
        localLoginThird.setProfileUrl(WelcomeActivity.this.mSP.getAvatar());
        localLoginThird.setClientId(WelcomeActivity.this.mSP.getClientId());
        UrlFactory.getInstance().login().constructUrl(WelcomeActivity.this, localLoginThird, 12);
        return;
      case 2:
        WelcomeActivity.this.startNewActivity(MainActivity.class, true, null);
        return;
      case 3:
      }
      WelcomeActivity.this.startNewActivity(SkipActivity.class, true, null);
    }
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.activity.WelcomeActivity
 * JD-Core Version:    0.6.0
 */