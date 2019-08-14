package com.withustudy.koudaizikao.tools;

import android.content.Context;
import android.widget.Toast;
import java.io.PrintStream;

public class LogUtils
{
  private static boolean isDebug = false;

  public static boolean isDebug()
  {
    return isDebug;
  }

  public static void myLog(Object paramObject)
  {
    if (isDebug)
      System.out.println(paramObject);
  }

  public static void myLog(String paramString, Object paramObject)
  {
    if (isDebug)
      System.out.println(paramString + "------" + paramObject);
  }

  public static void myToast(Context paramContext, Object paramObject)
  {
    if (isDebug)
      Toast.makeText(paramContext, paramObject.toString(), 0).show();
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.tools.LogUtils
 * JD-Core Version:    0.6.0
 */