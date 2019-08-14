package com.withustudy.koudaizikao.tools;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import org.apache.commons.lang3.StringUtils;

public class NetTools
{
  public static final int NETTYPE_CMNET = 3;
  public static final int NETTYPE_CMWAP = 2;
  public static final int NETTYPE_WIFI = 1;

  public static int getNetworkType(Context paramContext)
  {
    NetworkInfo localNetworkInfo = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
    if (localNetworkInfo == null)
      return 0;
    int i = localNetworkInfo.getType();
    int j;
    if (i == 0)
    {
      String str = localNetworkInfo.getExtraInfo();
      boolean bool = StringUtils.isEmpty(str);
      j = 0;
      if (!bool)
      {
        if (!str.toLowerCase().equals("cmnet"))
          break label65;
        j = 3;
      }
    }
    while (true)
    {
      return j;
      label65: j = 2;
      continue;
      j = 0;
      if (i != 1)
        continue;
      j = 1;
    }
  }

  public static boolean isNetworkConnected(Context paramContext)
  {
    NetworkInfo localNetworkInfo = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
    return (localNetworkInfo != null) && (localNetworkInfo.isConnectedOrConnecting());
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.tools.NetTools
 * JD-Core Version:    0.6.0
 */