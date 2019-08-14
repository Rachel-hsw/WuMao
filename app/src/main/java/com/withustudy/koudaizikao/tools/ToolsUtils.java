package com.withustudy.koudaizikao.tools;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;

public class ToolsUtils
{
  private static Animation AnimRotateLoading;
  public static final int NETTYPE_CMNET = 3;
  public static final int NETTYPE_CMWAP = 2;
  public static final int NETTYPE_WIFI = 1;

  public static byte[] Bitmap2Bytes(Bitmap paramBitmap)
  {
    if (paramBitmap == null)
      return null;
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    paramBitmap.compress(CompressFormat.PNG, 100, localByteArrayOutputStream);
    return localByteArrayOutputStream.toByteArray();
  }

  public static boolean availWholePhoneNum(String paramString)
  {
    if ((paramString == null) || ("".equals(paramString.trim())))
      return false;
    String str = paramString.toLowerCase().trim();
    for (int i = 0; ; i++)
    {
      if (i >= str.length())
        return true;
      if ("0123456789,+".indexOf(str.charAt(i)) == -1)
        break;
    }
  }

  public static boolean checkLineName(String paramString)
  {
    return paramString.replace("（", "(").replace("）", ")").matches("^.+\\(.+\\-.+\\)$");
  }

  public static int dip2px(Context paramContext, float paramFloat)
  {
    return (int)(0.5F + paramFloat * paramContext.getResources().getDisplayMetrics().density);
  }

  public static Bitmap drawableToBitmap(Drawable paramDrawable)
  {
    if (paramDrawable == null)
      return null;
    int i = paramDrawable.getIntrinsicWidth();
    int j = paramDrawable.getIntrinsicHeight();
    if (paramDrawable.getOpacity() != -1);
    for (Config localConfig = Config.ARGB_8888; ; localConfig = Config.RGB_565)
    {
      Bitmap localBitmap = Bitmap.createBitmap(i, j, localConfig);
      Canvas localCanvas = new Canvas(localBitmap);
      paramDrawable.setBounds(0, 0, paramDrawable.getIntrinsicWidth(), paramDrawable.getIntrinsicHeight());
      paramDrawable.draw(localCanvas);
      return localBitmap;
    }
  }

  public static String encode(String paramString)
  {
    if (paramString == null)
      return "";
    try
    {
      String str = URLEncoder.encode(paramString, "UTF-8").replace("+", "%20").replace("*", "%2A").replace("%7E", "~").replace("#", "%23");
      return str;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
    }
    throw new RuntimeException(localUnsupportedEncodingException.getMessage(), localUnsupportedEncodingException);
  }

  public static String formatKM(int paramInt)
  {
    if (paramInt < 1000)
      return paramInt + "m";
    int i = (int)Math.round(10.0D * paramInt / 1000.0D);
    if (i > 9999)
      return i / 10 + "km";
    if (i % 10 == 0)
      return i / 10 + "km";
    return i / 10.0D + "km";
  }

  public static String formatText(String paramString1, String paramString2)
  {
    return "<font        color=\"" + paramString2 + "\">" + paramString1 + "</font>";
  }

  public static String formatText(String paramString1, String paramString2, String paramString3)
  {
    return "<font      size=\"" + paramString3 + "\"   color=\"" + paramString2 + "\">" + paramString1 + "</font>";
  }

  public static String formatText(String paramString1, String paramString2, boolean paramBoolean)
  {
    if (paramBoolean)
      return "<font  color=\"" + paramString2 + "\">" + "<b>" + paramString1 + "</b>" + "</font>";
    return "<font color=\"" + paramString2 + "\">" + paramString1 + "</font>";
  }

  public static String formatText2Big(String paramString1, String paramString2)
  {
    return "<font   color=\"" + paramString2 + "\">" + "<big>" + paramString1 + "</big>" + "</font>";
  }

  public static String formatTextBold(String paramString)
  {
    return "<b>" + paramString + "</b>";
  }

  public static String formatTextLink(String paramString1, String paramString2)
  {
    return "<a font color=\"" + paramString2 + "\">" + paramString1 + "</a>";
  }

  public static String getImei(Context paramContext)
  {
    String str = "";
    if (paramContext != null)
    {
      Object localObject = paramContext.getSystemService("phone");
      if (localObject != null)
      {
        TelephonyManager localTelephonyManager = (TelephonyManager)localObject;
        if (localTelephonyManager != null)
          str = localTelephonyManager.getDeviceId();
      }
    }
    return str;
  }

  public static float getImgRotate(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    double d = 180.0D * Math.atan(Math.abs(paramInt1 - paramInt3) / Math.abs(paramInt2 - paramInt4)) / 3.141592653589793D;
    if (paramInt1 <= paramInt3)
      if (paramInt2 > paramInt4)
        d = 180.0D - d;
    while (true)
    {
      return (float)Math.round(d);
      if (paramInt2 >= paramInt4)
      {
        d += 180.0D;
        continue;
      }
      d = 360.0D - d;
    }
  }

  public static int getNetworkType(Context paramContext)
  {
    try
    {
      NetworkInfo localNetworkInfo = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
      if (localNetworkInfo == null)
        return 0;
      int j = localNetworkInfo.getType();
      if (j == 0)
      {
        String str = localNetworkInfo.getExtraInfo();
        boolean bool1 = StringUtils.isEmpty(str);
        i = 0;
        if (!bool1)
        {
          boolean bool2 = str.toLowerCase().equals("cmnet");
          if (!bool2)
            break label73;
          i = 3;
        }
      }
      while (true)
      {
        return i;
        label73: i = 2;
        continue;
        i = 0;
        if (j != 1)
          continue;
        i = 1;
      }
    }
    catch (Exception localException)
    {
      while (true)
        int i = 0;
    }
  }

  public static Bitmap getPicFromBytes(byte[] paramArrayOfByte, Options paramOptions)
  {
    if (paramArrayOfByte != null)
    {
      if (paramOptions != null)
        return BitmapFactory.decodeByteArray(paramArrayOfByte, 0, paramArrayOfByte.length, paramOptions);
      return BitmapFactory.decodeByteArray(paramArrayOfByte, 0, paramArrayOfByte.length);
    }
    return null;
  }

  public static String getSDK()
  {
    switch (Build.VERSION.SDK_INT)
    {
    default:
      return "android_";
    case 1:
      return "android_" + "1.0";
    case 2:
      return "android_" + "1.1";
    case 3:
      return "android_" + "1.5";
    case 4:
      return "android_" + "1.6";
    case 5:
      return "android_" + "2.0";
    case 6:
      return "android_" + "2.0.1";
    case 7:
      return "android_" + "2.1";
    case 8:
      return "android_" + "2.2";
    case 9:
      return "android_" + "2.3";
    case 10:
      return "android_" + "2.3.3";
    case 11:
      return "android_" + "3.0";
    case 12:
      return "android_" + "3.1";
    case 13:
      return "android_" + "3.2";
    case 14:
      return "android_" + "4.0";
    case 15:
      return "android_" + "4.0.2";
    case 16:
      return "android_" + "4.1";
    case 17:
      return "android_" + "4.2";
    case 18:
      return "android_" + "4.3";
    case 19:
      return "android_" + "4.4";
    case 20:
      return "android_" + "4.4";
    case 21:
      return "android_" + "5.0";
    case 22:
      return "android_" + "5.1";
    case 23:
    }
    return "android_" + "6.0";
  }

  public static String getStringNetworkType(Context paramContext)
  {
    if (getNetworkType(paramContext) == 1)
      return "WIFI";
    if (getNetworkType(paramContext) == 2)
      return "WAP";
    if (getNetworkType(paramContext) == 3)
      return "NET";
    return null;
  }

  public static boolean isEmail(String paramString)
  {
    return Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$").matcher(paramString).matches();
  }

  public static boolean isMobileNO(String paramString)
  {
    if (TextUtils.isEmpty(paramString))
      return false;
    return paramString.matches("[1][3578]\\d{9}");
  }

  public static boolean isNetworkConnected(Context paramContext)
  {
    NetworkInfo localNetworkInfo = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
    return (localNetworkInfo != null) && (localNetworkInfo.isConnectedOrConnecting());
  }

  public static boolean isNull(String paramString)
  {
    return (paramString == null) || ("".equals(paramString.trim())) || ("null".equals(paramString));
  }

  public static boolean isQQNum(String paramString)
  {
    return paramString.matches("([1-9][0-9]{4})|([0-9]{6,10})");
  }

  public static boolean matchPhone(String paramString)
  {
    return Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$").matcher(paramString).find();
  }

  public static int px2dip(Context paramContext, float paramFloat)
  {
    return (int)(0.5F + paramFloat / paramContext.getResources().getDisplayMetrics().density);
  }

  public static void setList(int paramInt, PullToRefreshListView paramPullToRefreshListView, Context paramContext)
  {
    switch (paramInt)
    {
    default:
    case -1:
    case 0:
    case 1:
    case 2:
    }
    while (true)
    {
      paramPullToRefreshListView.getLoadingLayoutProxy(false, true).setPullLabel(paramContext.getResources().getString(2131165194));
      paramPullToRefreshListView.getLoadingLayoutProxy(false, true).setReleaseLabel(paramContext.getResources().getString(2131165195));
      paramPullToRefreshListView.getLoadingLayoutProxy(false, true).setRefreshingLabel(paramContext.getResources().getString(2131165196));
      paramPullToRefreshListView.getLoadingLayoutProxy(true, false).setPullLabel(paramContext.getResources().getString(2131165191));
      paramPullToRefreshListView.getLoadingLayoutProxy(true, false).setReleaseLabel(paramContext.getResources().getString(2131165192));
      paramPullToRefreshListView.getLoadingLayoutProxy(true, false).setRefreshingLabel(paramContext.getResources().getString(2131165193));
      return;
      paramPullToRefreshListView.setMode(PullToRefreshBase.Mode.DISABLED);
      continue;
      paramPullToRefreshListView.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
      continue;
      paramPullToRefreshListView.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
      continue;
      paramPullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
    }
  }

  public static void setLoading(View paramView)
  {
    if (AnimRotateLoading == null)
    {
      AnimRotateLoading = new RotateAnimation(0.0F, 359.0F, 1, 0.5F, 1, 0.5F);
      AnimRotateLoading.setInterpolator(new LinearInterpolator());
      AnimRotateLoading.setRepeatCount(99);
      AnimRotateLoading.setDuration(1000L);
      AnimRotateLoading.setAnimationListener(null);
    }
    AnimRotateLoading.setAnimationListener(new AnimationListener(paramView)
    {
      public void onAnimationEnd(Animation paramAnimation)
      {
        ToolsUtils.this.startAnimation(ToolsUtils.AnimRotateLoading);
      }

      public void onAnimationRepeat(Animation paramAnimation)
      {
      }

      public void onAnimationStart(Animation paramAnimation)
      {
      }
    });
    paramView.startAnimation(AnimRotateLoading);
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.tools.ToolsUtils
 * JD-Core Version:    0.6.0
 */