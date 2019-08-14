package com.withustudy.koudaizikao.tools;

import android.content.Context;
import android.content.res.Resources;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTools
{
  public static final long DAY = 86400000L;
  public static final long HOUR = 3600000L;
  public static final long MIN = 60000L;
  public static final long MONTH = 2592000000L;
  public static final long SEC = 1000L;
  public static final long WEEK = 604800000L;
  public static final long YEAR = 31104000000L;

  public static String DateConvertString(String paramString)
  {
    String str = "";
    try
    {
      Date localDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(paramString);
      long l1 = (System.currentTimeMillis() - localDate.getTime()) / 60000L;
      str = l1 + "分钟前";
      if (l1 < 2L)
        str = "刚刚";
      long l2 = l1 / 60L / 24L;
      if ((l1 > 60L) && (l2 < 1L))
        str = l1 / 60L + "小时前";
      if (l2 > 0L)
        str = l2 + "天前";
      if (l2 == 1L)
        str = "昨天";
      return str;
    }
    catch (Exception localException)
    {
    }
    return str;
  }

  public static String DateConvertString2(String paramString)
  {
    Object localObject = "";
    try
    {
      Date localDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(paramString);
      long l1 = (System.currentTimeMillis() - localDate.getTime()) / 60000L;
      localObject = l1 + "分钟前";
      if (l1 < 2L)
        localObject = "刚刚";
      long l2 = l1 / 60L / 24L;
      if ((l1 > 60L) && (l2 < 1L))
      {
        String str = l1 / 60L + "小时前";
        localObject = str;
      }
      if (l2 > 0L)
        localObject = paramString;
      if (l2 == 1L)
        localObject = paramString;
      return localObject;
    }
    catch (Exception localException)
    {
    }
    return (String)localObject;
  }

  public static String dateFormatHMS()
  {
    return new SimpleDateFormat("HH:mm:ss").format(new Date());
  }

  public static String dateFormatYMD(long paramLong)
  {
    return new SimpleDateFormat("yyyy-MM-dd").format(new Date(paramLong));
  }

  public static String dateFormatYMD2(long paramLong)
  {
    return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(paramLong));
  }

  public static String getCurrentDateTime()
  {
    return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
  }

  public static String getMyMistakeTime(Context paramContext, long paramLong)
  {
    long l = System.currentTimeMillis() - paramLong;
    if (l <= 86400000L)
      return paramContext.getResources().getString(2131165321);
    if ((l > 86400000L) && (l <= 172800000L))
      return paramContext.getResources().getString(2131165322);
    if ((l > 172800000L) && (l <= 604800000L))
      return longToString(l, true);
    if ((l > 604800000L) && (l <= 1209600000L))
      return paramContext.getResources().getString(2131165323);
    if ((l > 1209600000L) && (l <= 2592000000L))
      return longToString(l, true);
    return longToString(l, false);
  }

  public static String getPretime(Context paramContext, long paramLong)
  {
    new Date(paramLong);
    long l1 = System.currentTimeMillis() - paramLong;
    if (l1 < 60000L)
    {
      long l2 = l1 / 1000L;
      if (l2 == 0L)
        return "刚刚";
      return (int)l2 + paramContext.getResources().getString(2131165350);
    }
    if ((l1 >= 60000L) && (l1 < 3600000L))
      return (int)(l1 / 60000L) + paramContext.getResources().getString(2131165351);
    if ((l1 >= 3600000L) && (l1 < 86400000L))
      return (int)(l1 / 3600000L) + paramContext.getResources().getString(2131165352);
    if ((l1 >= 86400000L) && (l1 < 2592000000L))
      return (int)(l1 / 86400000L) + paramContext.getResources().getString(2131165353);
    if ((l1 >= 2592000000L) && (l1 < 31104000000L))
      return (int)(l1 / 2592000000L) + paramContext.getResources().getString(2131165354);
    return (int)(l1 / 31104000000L) + paramContext.getResources().getString(2131165355);
  }

  public static long getTimeMillis(String paramString)
  {
    try
    {
      long l = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(paramString).getTime();
      return l;
    }
    catch (ParseException localParseException)
    {
      localParseException.printStackTrace();
    }
    return 0L;
  }

  public static String longToString(long paramLong, boolean paramBoolean)
  {
    Date localDate = new Date(paramLong);
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTime(localDate);
    if (paramBoolean);
    for (SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("M月d日"); ; localSimpleDateFormat = new SimpleDateFormat("M月"))
      return localSimpleDateFormat.format(localCalendar.getTime());
  }

  public static long subDate(String paramString1, String paramString2)
  {
    try
    {
      SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
      Date localDate = localSimpleDateFormat.parse(paramString1);
      long l = (localSimpleDateFormat.parse(paramString2).getTime() - localDate.getTime()) / 86400000L;
      return l;
    }
    catch (ParseException localParseException)
    {
      localParseException.printStackTrace();
    }
    return 0L;
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.tools.DateTools
 * JD-Core Version:    0.6.0
 */