package com.withustudy.koudaizikao.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import com.android.http.FileDownLoad;
import com.android.http.RequestManager.RequestListener;
import com.umeng.analytics.MobclickAgent;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.bean.SocializeConfig;
import com.umeng.socialize.bean.SocializeEntity;
import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.controller.listener.SocializeListeners.SnsPostListener;
import com.umeng.socialize.media.QQShareContent;
import com.umeng.socialize.media.SinaShareContent;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.sso.QZoneSsoHandler;
import com.umeng.socialize.sso.SinaSsoHandler;
import com.umeng.socialize.sso.UMQQSsoHandler;
import com.umeng.socialize.sso.UMSsoHandler;
import com.umeng.socialize.weixin.controller.UMWXHandler;
import com.umeng.socialize.weixin.media.CircleShareContent;
import com.umeng.socialize.weixin.media.WeiXinShareContent;
import com.withustudy.koudaizikao.R;
import com.withustudy.koudaizikao.config.KouDaiSP;
import com.withustudy.koudaizikao.tools.FileTools;
import com.withustudy.koudaizikao.tools.LogUtils;
import com.withustudy.koudaizikao.tools.ProTools;
import java.util.Map;

public abstract class AbsBaseActivity extends FragmentActivity
  implements RequestManager.RequestListener
{
  protected Context mContext;
  protected UMSocialService mController;
  protected FileDownLoad mFileDownLoad;
  protected FileTools mFileTools;
  protected FragmentManager mFragmentManager;
  protected ProTools mProTools;
  protected KouDaiSP mSP;
  protected UMSocialService mShareController;

  private void addQZoneQQPlatform()
  {
    UMQQSsoHandler localUMQQSsoHandler = new UMQQSsoHandler(this, "1104849186", "Td1yI6yjZR8vICkf");
    localUMQQSsoHandler.setTargetUrl("http://www.umeng.com");
    localUMQQSsoHandler.addToSocialSDK();
    new QZoneSsoHandler(this, "1104849186", "Td1yI6yjZR8vICkf").addToSocialSDK();
  }

  private void addWeiXinPlatform()
  {
    UMWXHandler localUMWXHandler = new UMWXHandler(this, "wx6f30ad5bcc7fbe40", "c808277cdf3c3ff291d16df71195ec6c");
    localUMWXHandler.setRefreshTokenAvailable(false);
    localUMWXHandler.addToSocialSDK();
  }

  private void initAllData()
  {
    com.umeng.socialize.utils.Log.LOG = true;
    initUMService();
    addQZoneQQPlatform();
    addWeiXinPlatform();
    initUMShare();
  }

  private void initUMService()
  {
    this.mController = UMServiceFactory.getUMSocialService("com.umeng.login");
    this.mController.getConfig().setSsoHandler(new SinaSsoHandler());
  }

  private void initUMShare()
  {
    this.mShareController = UMServiceFactory.getUMSocialService("com.umeng.share");
    SinaSsoHandler localSinaSsoHandler = new SinaSsoHandler();
    this.mShareController.getConfig().setSsoHandler(localSinaSsoHandler);
    UMWXHandler localUMWXHandler1 = new UMWXHandler(this, "wx6f30ad5bcc7fbe40", "c808277cdf3c3ff291d16df71195ec6c");
    UMWXHandler localUMWXHandler2 = new UMWXHandler(this, "wx6f30ad5bcc7fbe40", "c808277cdf3c3ff291d16df71195ec6c");
    localUMWXHandler1.addToSocialSDK();
    localUMWXHandler2.setToCircle(true);
    localUMWXHandler2.addToSocialSDK();
    UMQQSsoHandler localUMQQSsoHandler = new UMQQSsoHandler(this, "1104849186", "Td1yI6yjZR8vICkf");
    localUMQQSsoHandler.setTargetUrl("http://www.umeng.com");
    localUMQQSsoHandler.addToSocialSDK();
    new QZoneSsoHandler(this, "1104849186", "Td1yI6yjZR8vICkf").addToSocialSDK();
  }

  protected abstract void bindData();

  public void finish()
  {
    super.finish();
    overridePendingTransition(2130968578, 2130968582);
  }

  public void finish(int paramInt1, int paramInt2)
  {
    super.finish();
    overridePendingTransition(paramInt1, paramInt2);
  }

  protected abstract void initData();

  protected abstract void initListener();

  protected abstract void initView();

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    UMSsoHandler localUMSsoHandler = SocializeConfig.getSocializeConfig().getSsoHandler(paramInt1);
    if (localUMSsoHandler != null)
      localUMSsoHandler.authorizeCallBack(paramInt1, paramInt2, paramIntent);
  }

  protected final void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.mContext = this;
    this.mFragmentManager = getSupportFragmentManager();
    this.mProTools = ProTools.getInstance(this.mContext, R.style.DialogStyle);
    this.mFileTools = FileTools.getInstance(this.mContext);
    this.mFileDownLoad = FileDownLoad.getInstance(this.mContext);
    this.mSP = KouDaiSP.getInstance(this.mContext);
    initAllData();
    setContentView();
    initView();
    initData();
    bindData();
    initListener();
  }

  public void onError(String paramString1, String paramString2, int paramInt)
  {
    LogUtils.myLog("onError!");
    LogUtils.myLog("actionId----->" + paramInt);
    LogUtils.myLog(paramString1);
  }

  protected void onPause()
  {
    super.onPause();
    if (!LogUtils.isDebug())
    {
      MobclickAgent.onPageEnd("SplashScreen");
      MobclickAgent.onPause(this);
    }
  }

  public void onRequest()
  {
  }

  protected void onResume()
  {
    super.onResume();
    if (!LogUtils.isDebug())
    {
      MobclickAgent.onPageStart("SplashScreen");
      MobclickAgent.onResume(this);
    }
  }

  public void onSuccess(String paramString1, Map<String, String> paramMap, String paramString2, int paramInt)
  {
    LogUtils.myLog("OnSucess!");
    LogUtils.myLog("actionId----->" + paramInt);
    LogUtils.myLog("res----->" + paramString1);
  }

  protected abstract void setContentView();

  public void share(SHARE_MEDIA paramSHARE_MEDIA, String paramString)
  {
    switch ($SWITCH_TABLE$com$umeng$socialize$bean$SHARE_MEDIA()[paramSHARE_MEDIA.ordinal()])
    {
    case 6:
    case 8:
    default:
      return;
    case 10:
      CircleShareContent localCircleShareContent = new CircleShareContent();
      localCircleShareContent.setTitle("快来口袋自考学本科吧，20大专业全科讲解题目，刷刷题就过关哦~");
      localCircleShareContent.setShareContent("快来口袋自考学本科吧，20大专业全科讲解题目，刷刷题就过关哦~");
      localCircleShareContent.setTargetUrl(paramString);
      localCircleShareContent.setShareMedia(new UMImage(this, 2130837810));
      this.mShareController.setShareMedia(localCircleShareContent);
      this.mShareController.postShare(this, SHARE_MEDIA.WEIXIN_CIRCLE, new SocializeListeners.SnsPostListener()
      {
        public void onComplete(SHARE_MEDIA paramSHARE_MEDIA, int paramInt, SocializeEntity paramSocializeEntity)
        {
          if ((paramInt != 200) && (paramInt == -101));
        }

        public void onStart()
        {
        }
      });
      return;
    case 9:
      WeiXinShareContent localWeiXinShareContent = new WeiXinShareContent();
      localWeiXinShareContent.setTitle("口袋自考");
      localWeiXinShareContent.setShareContent("快来口袋自考学本科吧，20大专业全科讲解题目，刷刷题就过关哦~");
      localWeiXinShareContent.setTargetUrl(paramString);
      localWeiXinShareContent.setShareMedia(new UMImage(this, 2130837810));
      this.mShareController.setShareMedia(localWeiXinShareContent);
      this.mShareController.postShare(this, SHARE_MEDIA.WEIXIN, new SocializeListeners.SnsPostListener()
      {
        public void onComplete(SHARE_MEDIA paramSHARE_MEDIA, int paramInt, SocializeEntity paramSocializeEntity)
        {
          if ((paramInt != 200) && (paramInt == -101));
        }

        public void onStart()
        {
        }
      });
      return;
    case 5:
      SinaShareContent localSinaShareContent = new SinaShareContent();
      localSinaShareContent.setTitle("口袋自考");
      localSinaShareContent.setShareContent("快来口袋自考学本科吧，20大专业全科讲解题目，刷刷题就过关哦~" + paramString);
      localSinaShareContent.setTargetUrl(paramString);
      localSinaShareContent.setShareImage(new UMImage(this, 2130837810));
      this.mShareController.setShareMedia(localSinaShareContent);
      this.mShareController.postShare(this, SHARE_MEDIA.SINA, new SocializeListeners.SnsPostListener()
      {
        public void onComplete(SHARE_MEDIA paramSHARE_MEDIA, int paramInt, SocializeEntity paramSocializeEntity)
        {
          if ((paramInt != 200) && (paramInt == -101));
        }

        public void onStart()
        {
        }
      });
      return;
    case 7:
    }
    QQShareContent localQQShareContent = new QQShareContent();
    localQQShareContent.setTitle("口袋自考");
    localQQShareContent.setShareContent("快来口袋自考学本科吧，20大专业全科讲解题目，刷刷题就过关哦~");
    localQQShareContent.setTargetUrl(paramString);
    localQQShareContent.setShareImage(new UMImage(this, 2130837810));
    this.mShareController.setShareMedia(localQQShareContent);
    this.mShareController.postShare(this, SHARE_MEDIA.QQ, new SocializeListeners.SnsPostListener()
    {
      public void onComplete(SHARE_MEDIA paramSHARE_MEDIA, int paramInt, SocializeEntity paramSocializeEntity)
      {
        if ((paramInt != 200) && (paramInt == -101));
      }

      public void onStart()
      {
      }
    });
  }

  public void share(SHARE_MEDIA paramSHARE_MEDIA, String paramString1, String paramString2, String paramString3)
  {
    String str1 = "口袋自考";
    String str2 = "快来口袋自考学本科吧，20大专业全科讲解题目，刷刷题就过关哦~";
    if ((paramString2 != null) && (!paramString2.equals("")) && (!paramString2.equals("null")))
      str1 = paramString2;
    if ((paramString3 != null) && (!paramString3.equals("")) && (!paramString3.equals("null")))
      str2 = paramString3;
    switch ($SWITCH_TABLE$com$umeng$socialize$bean$SHARE_MEDIA()[paramSHARE_MEDIA.ordinal()])
    {
    case 6:
    case 8:
    default:
      return;
    case 10:
      CircleShareContent localCircleShareContent = new CircleShareContent();
      localCircleShareContent.setTitle(str1);
      localCircleShareContent.setShareContent(str2);
      localCircleShareContent.setTargetUrl(paramString1);
      localCircleShareContent.setShareMedia(new UMImage(this, 2130837810));
      this.mShareController.setShareMedia(localCircleShareContent);
      this.mShareController.postShare(this, SHARE_MEDIA.WEIXIN_CIRCLE, new SocializeListeners.SnsPostListener()
      {
        public void onComplete(SHARE_MEDIA paramSHARE_MEDIA, int paramInt, SocializeEntity paramSocializeEntity)
        {
          if ((paramInt != 200) && (paramInt == -101));
        }

        public void onStart()
        {
        }
      });
      return;
    case 9:
      WeiXinShareContent localWeiXinShareContent = new WeiXinShareContent();
      localWeiXinShareContent.setTitle(str1);
      localWeiXinShareContent.setShareContent(str2);
      localWeiXinShareContent.setTargetUrl(paramString1);
      localWeiXinShareContent.setShareMedia(new UMImage(this, 2130837810));
      this.mShareController.setShareMedia(localWeiXinShareContent);
      this.mShareController.postShare(this, SHARE_MEDIA.WEIXIN, new SocializeListeners.SnsPostListener()
      {
        public void onComplete(SHARE_MEDIA paramSHARE_MEDIA, int paramInt, SocializeEntity paramSocializeEntity)
        {
          if ((paramInt != 200) && (paramInt == -101));
        }

        public void onStart()
        {
        }
      });
      return;
    case 5:
      SinaShareContent localSinaShareContent = new SinaShareContent();
      localSinaShareContent.setTitle(str1);
      localSinaShareContent.setShareContent(str2);
      localSinaShareContent.setTargetUrl(paramString1);
      localSinaShareContent.setShareImage(new UMImage(this, 2130837810));
      this.mShareController.setShareMedia(localSinaShareContent);
      this.mShareController.postShare(this, SHARE_MEDIA.SINA, new SocializeListeners.SnsPostListener()
      {
        public void onComplete(SHARE_MEDIA paramSHARE_MEDIA, int paramInt, SocializeEntity paramSocializeEntity)
        {
          if ((paramInt != 200) && (paramInt == -101));
        }

        public void onStart()
        {
        }
      });
      return;
    case 7:
    }
    QQShareContent localQQShareContent = new QQShareContent();
    localQQShareContent.setTitle(str1);
    localQQShareContent.setShareContent(str2);
    localQQShareContent.setTargetUrl(paramString1);
    localQQShareContent.setShareImage(new UMImage(this, 2130837810));
    this.mShareController.setShareMedia(localQQShareContent);
    this.mShareController.postShare(this, SHARE_MEDIA.QQ, new SocializeListeners.SnsPostListener()
    {
      public void onComplete(SHARE_MEDIA paramSHARE_MEDIA, int paramInt, SocializeEntity paramSocializeEntity)
      {
        if ((paramInt != 200) && (paramInt == -101));
      }

      public void onStart()
      {
      }
    });
  }

  public final void startNewActivity(Class<? extends Activity> paramClass, int paramInt1, int paramInt2, boolean paramBoolean, Bundle paramBundle)
  {
    Intent localIntent = new Intent(this, paramClass);
    if (paramBundle != null)
      localIntent.putExtras(paramBundle);
    startActivity(localIntent);
    if (getParent() == null)
      overridePendingTransition(paramInt1, paramInt2);
    while (true)
    {
      if (paramBoolean)
        super.finish();
      return;
      getParent().overridePendingTransition(paramInt1, paramInt2);
    }
  }

  public final void startNewActivity(Class<? extends Activity> paramClass, boolean paramBoolean, Bundle paramBundle)
  {
    Intent localIntent = new Intent(this, paramClass);
    if (paramBundle != null)
      localIntent.putExtras(paramBundle);
    startActivity(localIntent);
    if (getParent() == null)
      overridePendingTransition(2130968581, 2130968579);
    while (true)
    {
      if (paramBoolean)
        super.finish();
      return;
      getParent().overridePendingTransition(2130968581, 2130968579);
    }
  }

  public final void startNewActivityForResult(Class<? extends Activity> paramClass, int paramInt1, int paramInt2, int paramInt3, Bundle paramBundle)
  {
    Intent localIntent = new Intent(this, paramClass);
    if (paramBundle != null)
      localIntent.putExtras(paramBundle);
    startActivityForResult(localIntent, paramInt3);
    if (getParent() == null)
    {
      overridePendingTransition(paramInt1, paramInt2);
      return;
    }
    getParent().overridePendingTransition(paramInt1, paramInt2);
  }

  public final void startNewActivityForResult(Class<? extends Activity> paramClass, int paramInt, Bundle paramBundle)
  {
    Intent localIntent = new Intent(this, paramClass);
    if (paramBundle != null)
      localIntent.putExtras(paramBundle);
    startActivityForResult(localIntent, paramInt);
    if (getParent() == null)
    {
      overridePendingTransition(2130968581, 2130968579);
      return;
    }
    getParent().overridePendingTransition(2130968581, 2130968579);
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.base.AbsBaseActivity
 * JD-Core Version:    0.6.0
 */