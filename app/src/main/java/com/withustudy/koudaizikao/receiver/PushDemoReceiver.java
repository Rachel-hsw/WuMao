package com.withustudy.koudaizikao.receiver;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat.Builder;
import android.support.v4.app.TaskStackBuilder;
import com.android.http.RequestManager.RequestListener;
import com.igexin.sdk.PushManager;
import com.withustudy.koudaizikao.action.AbsBaseUrlConstructor;
import com.withustudy.koudaizikao.action.UrlFactory;
import com.withustudy.koudaizikao.activity.InformationDetailActivity;
import com.withustudy.koudaizikao.activity.NotificationActivity;
import com.withustudy.koudaizikao.activity.PostDetailActivity;
import com.withustudy.koudaizikao.config.KouDaiSP;
import com.withustudy.koudaizikao.tools.DateTools;
import com.withustudy.koudaizikao.tools.LogUtils;
import java.util.Map;

public class PushDemoReceiver extends BroadcastReceiver
{
  public static StringBuilder payloadData = new StringBuilder();

  public void onReceive(Context paramContext, Intent paramIntent)
  {
    Bundle localBundle = paramIntent.getExtras();
    LogUtils.myLog("---------------------------------------------------------------------------------------------------------");
    LogUtils.myLog(DateTools.dateFormatYMD2(System.currentTimeMillis()) + "GetuiSdkDemo   onReceive() action=" + localBundle.getInt("action"));
    LogUtils.myLog(DateTools.dateFormatYMD2(System.currentTimeMillis()) + "GetuiSdkDemo   onReceive() action=" + localBundle.getInt("action"));
    LogUtils.myLog(DateTools.dateFormatYMD2(System.currentTimeMillis()) + "GetuiSdkDemo   onReceive() action=" + localBundle.getInt("action"));
    LogUtils.myLog("---------------------------------------------------------------------------------------------------------");
    switch (localBundle.getInt("action"))
    {
    case 10003:
    case 10004:
    case 10005:
    case 10006:
    default:
    case 10001:
      String str6;
      String str7;
      String str8;
      String str9;
      label733: 
      do
      {
        return;
        byte[] arrayOfByte = localBundle.getByteArray("payload");
        String str2 = localBundle.getString("taskid");
        String str3 = localBundle.getString("messageid");
        LogUtils.myLog("---------------------------------------------透传数据------------------------------------------------------------");
        LogUtils.myLog(DateTools.dateFormatYMD2(System.currentTimeMillis()) + "透传数据taskid=" + str2);
        LogUtils.myLog(DateTools.dateFormatYMD2(System.currentTimeMillis()) + "透传数据messageid=" + str3);
        LogUtils.myLog(DateTools.dateFormatYMD2(System.currentTimeMillis()) + "透传数据taskid=" + str2);
        LogUtils.myLog(DateTools.dateFormatYMD2(System.currentTimeMillis()) + "透传数据messageid=" + str3);
        LogUtils.myLog(DateTools.dateFormatYMD2(System.currentTimeMillis()) + "透传数据taskid=" + str2);
        LogUtils.myLog("---------------------------------------------透传数据------------------------------------------------------------");
        boolean bool = PushManager.getInstance().sendFeedbackMessage(paramContext, str2, str3, 90001);
        StringBuilder localStringBuilder = new StringBuilder("第三方回执接口调用");
        if (bool);
        for (String str4 = "成功"; ; str4 = "失败")
        {
          LogUtils.myLog(str4);
          if (arrayOfByte == null)
            break;
          String str5 = new String(arrayOfByte);
          LogUtils.myLog("---------------------------------------------透传数据------------------------------------------------------------");
          LogUtils.myLog(DateTools.dateFormatYMD2(System.currentTimeMillis()) + "透传数据data=" + str5);
          LogUtils.myLog(DateTools.dateFormatYMD2(System.currentTimeMillis()) + "透传数据data=" + str5);
          LogUtils.myLog(DateTools.dateFormatYMD2(System.currentTimeMillis()) + "透传数据data=" + str5);
          LogUtils.myLog(DateTools.dateFormatYMD2(System.currentTimeMillis()) + "透传数据data=" + str5);
          LogUtils.myLog(DateTools.dateFormatYMD2(System.currentTimeMillis()) + "透传数据data=" + str5);
          LogUtils.myLog("---------------------------------------------透传数据------------------------------------------------------------");
          LogUtils.myLog("透传数据data=" + str5);
          LogUtils.myLog("GetuiSdkDemo", "receiver payload : " + str5);
          payloadData.append(str5);
          payloadData.append("\n");
          String[] arrayOfString2 = str5.split(";");
          if ((arrayOfString2 == null) || (arrayOfString2.length < 4))
            break;
          str6 = arrayOfString2[0];
          str7 = arrayOfString2[1];
          str8 = arrayOfString2[2];
          str9 = arrayOfString2[3];
          if (!str6.equals("社区"))
            break label733;
          sendNotification(paramContext, str7, str8, str9, "社区");
          return;
        }
      }
      while (!str6.equals("资讯"));
      sendNotification(paramContext, str7, str8, str9, "资讯");
      return;
    case 10002:
    }
    String str1 = localBundle.getString("clientid");
    KouDaiSP.getInstance(paramContext).setClientId(str1);
    AbsBaseUrlConstructor localAbsBaseUrlConstructor = UrlFactory.getInstance().launch();
    1 local1 = new RequestManager.RequestListener()
    {
      public void onError(String paramString1, String paramString2, int paramInt)
      {
      }

      public void onRequest()
      {
      }

      public void onSuccess(String paramString1, Map<String, String> paramMap, String paramString2, int paramInt)
      {
        LogUtils.myLog("res", paramString1);
      }
    };
    String[] arrayOfString1 = new String[2];
    arrayOfString1[0] = str1;
    arrayOfString1[1] = KouDaiSP.getInstance(paramContext).getUserId();
    localAbsBaseUrlConstructor.constructUrl(local1, arrayOfString1, 0, paramContext);
  }

  public void sendNotification(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4)
  {
    NotificationCompat.Builder localBuilder = new NotificationCompat.Builder(paramContext);
    localBuilder.setDefaults(-1);
    localBuilder.setSmallIcon(2130837666);
    localBuilder.setContentTitle(paramString1);
    localBuilder.setContentText(paramString2);
    Intent localIntent = new Intent();
    if (paramString4.equals("资讯"))
      localIntent.setClass(paramContext, InformationDetailActivity.class);
    while (true)
    {
      Bundle localBundle = new Bundle();
      localBundle.putInt("id", Integer.valueOf(paramString3).intValue());
      localBundle.putBoolean("push", true);
      localIntent.putExtras(localBundle);
      TaskStackBuilder localTaskStackBuilder = TaskStackBuilder.create(paramContext);
      localTaskStackBuilder.addParentStack(NotificationActivity.class);
      localTaskStackBuilder.addNextIntent(localIntent);
      localBuilder.setContentIntent(localTaskStackBuilder.getPendingIntent(0, 134217728));
      ((NotificationManager)paramContext.getSystemService("notification")).notify(1, localBuilder.build());
      return;
      localIntent.setClass(paramContext, PostDetailActivity.class);
    }
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.receiver.PushDemoReceiver
 * JD-Core Version:    0.6.0
 */