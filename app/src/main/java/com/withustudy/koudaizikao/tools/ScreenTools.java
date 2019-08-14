package com.withustudy.koudaizikao.tools;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.withustudy.koudaizikao.config.KouDaiSP;

public class ScreenTools
{
  public static int getScreenDisplayMetrics(Context paramContext)
  {
    new DisplayMetrics();
    return paramContext.getResources().getDisplayMetrics().densityDpi;
  }

  public static int getScreenHeight(Context paramContext)
  {
    return ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay().getHeight();
  }

  public static int getScreenWidth(Context paramContext)
  {
    return ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay().getWidth();
  }

  public static void savePra(Context paramContext)
  {
    KouDaiSP.getInstance(paramContext).setWidth(getScreenWidth(paramContext));
    KouDaiSP.getInstance(paramContext).setHeight(getScreenHeight(paramContext));
    KouDaiSP.getInstance(paramContext).setDensity(getScreenDisplayMetrics(paramContext));
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.tools.ScreenTools
 * JD-Core Version:    0.6.0
 */