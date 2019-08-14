package com.withustudy.koudaizikao.config;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.withustudy.koudaizikao.tools.LogUtils;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class KouDaiSP
{
  private static final String APP_NAME = "koudai";
  private static final String AVATAR = "avatar";
  private static final String CITY_NAME = "city_name";
  private static final String CLIENTID = "CLIENTID";
  private static final String DENSITY = "DENSITY";
  private static final String FIRST_INSTALL = "isFirstInstall";
  private static final String HEIGHT = "HEIGHT";
  private static final String LATITUDE = "latitude";
  private static final String LONGITUDE = "longitude";
  private static final String MAJOR_ID = "major_id";
  private static final String MAJOR_NAME = "major_name";
  private static final String NICK_NAME = "nick_name";
  private static final String PASSWORD = "password";
  private static final String PHONE = "phone";
  private static final String PRO_ID = "pro_id";
  private static final String PRO_NAME = "pro_name";
  private static final String THIRD_ID = "THIRD_ID";
  private static final String THIRD_TYPE = "THIRD_TYPE";
  private static final String UPDATE_SUBJECT = "update_subject";
  private static final String USER_ID = "uId";
  private static final String VERSION_CODE = "version_code";
  private static final String VERSION_NAME = "version_name";
  private static final String WIDTH = "WIDTH";
  private static KouDaiSP instance;
  private Context mContext;

  private KouDaiSP(Context paramContext)
  {
    this.mContext = paramContext;
  }

  public static KouDaiSP getInstance(Context paramContext)
  {
    if (instance == null)
      monitorenter;
    try
    {
      if (instance == null)
        instance = new KouDaiSP(paramContext);
      return instance;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public void cacheAnser(int paramInt, HashMap<Integer, String> paramHashMap)
  {
    Set localSet = paramHashMap.keySet();
    String str1 = "";
    Iterator localIterator = localSet.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
      {
        if (!str1.equals(""))
          this.mContext.getSharedPreferences("koudai", 0).edit().putString(paramInt, str1).commit();
        return;
      }
      Integer localInteger = (Integer)localIterator.next();
      String str2 = (String)paramHashMap.get(localInteger);
      if ((str2 == null) || (str2.equals("")))
        continue;
      str1 = localInteger + "$$" + str2 + "%%";
      LogUtils.myLog("缓存模拟考答案=" + str1);
    }
  }

  public void clearUserInfo()
  {
    setUserId("");
    setPhone("");
    setPassword("");
    setProId("");
    setProName("");
    setMajorId("");
    setMajorName("");
    setAvatar("");
    setNickName("");
    setThirdId("");
    setThirdType("");
  }

  public String getAvatar()
  {
    return this.mContext.getSharedPreferences("koudai", 0).getString("avatar", "");
  }

  public String getCityName()
  {
    return this.mContext.getSharedPreferences("koudai", 0).getString("city_name", "");
  }

  public String getClientId()
  {
    return this.mContext.getSharedPreferences("koudai", 0).getString("CLIENTID", "");
  }

  public float getDensity()
  {
    return this.mContext.getSharedPreferences("koudai", 0).getFloat("DENSITY", 0.0F);
  }

  public boolean getFirst()
  {
    return this.mContext.getSharedPreferences("koudai", 0).getBoolean("isFirstInstall", true);
  }

  public int getHeight()
  {
    return this.mContext.getSharedPreferences("koudai", 0).getInt("HEIGHT", 0);
  }

  public String getLatitude()
  {
    return this.mContext.getSharedPreferences("koudai", 0).getString("latitude", "");
  }

  public String getLongitude()
  {
    return this.mContext.getSharedPreferences("koudai", 0).getString("longitude", "");
  }

  public String getMajorId()
  {
    return this.mContext.getSharedPreferences("koudai", 0).getString("major_id", "");
  }

  public String getMajorName()
  {
    return this.mContext.getSharedPreferences("koudai", 0).getString("major_name", "");
  }

  public String getNickName()
  {
    return this.mContext.getSharedPreferences("koudai", 0).getString("nick_name", "");
  }

  public String getPassword()
  {
    return this.mContext.getSharedPreferences("koudai", 0).getString("password", "");
  }

  public String getPhone()
  {
    return this.mContext.getSharedPreferences("koudai", 0).getString("phone", "");
  }

  public String getProId()
  {
    return this.mContext.getSharedPreferences("koudai", 0).getString("pro_id", "");
  }

  public String getProName()
  {
    return this.mContext.getSharedPreferences("koudai", 0).getString("pro_name", "");
  }

  public String getThirdId()
  {
    return this.mContext.getSharedPreferences("koudai", 0).getString("THIRD_ID", "");
  }

  public String getThirdType()
  {
    return this.mContext.getSharedPreferences("koudai", 0).getString("THIRD_TYPE", "");
  }

  public boolean getUpdateSubject()
  {
    return this.mContext.getSharedPreferences("koudai", 0).getBoolean("update_subject", false);
  }

  public String getUserId()
  {
    return this.mContext.getSharedPreferences("koudai", 0).getString("uId", "");
  }

  public String getVersionCode()
  {
    return this.mContext.getSharedPreferences("koudai", 0).getString("version_code", "");
  }

  public String getVersionName()
  {
    return this.mContext.getSharedPreferences("koudai", 0).getString("version_name", "");
  }

  public int getWidth()
  {
    return this.mContext.getSharedPreferences("koudai", 0).getInt("WIDTH", 0);
  }

  public void setAvatar(String paramString)
  {
    this.mContext.getSharedPreferences("koudai", 0).edit().putString("avatar", paramString).commit();
  }

  public void setCityName(String paramString)
  {
    this.mContext.getSharedPreferences("koudai", 0).edit().putString("city_name", paramString).commit();
  }

  public void setClientId(String paramString)
  {
    this.mContext.getSharedPreferences("koudai", 0).edit().putString("CLIENTID", paramString).commit();
  }

  public void setDensity(float paramFloat)
  {
    this.mContext.getSharedPreferences("koudai", 0).edit().putFloat("DENSITY", paramFloat).commit();
  }

  public void setFirst(boolean paramBoolean)
  {
    this.mContext.getSharedPreferences("koudai", 0).edit().putBoolean("isFirstInstall", paramBoolean).commit();
  }

  public void setHeight(int paramInt)
  {
    this.mContext.getSharedPreferences("koudai", 0).edit().putInt("HEIGHT", paramInt).commit();
  }

  public void setLatitude(String paramString)
  {
    this.mContext.getSharedPreferences("koudai", 0).edit().putString("latitude", paramString).commit();
  }

  public void setLongitude(String paramString)
  {
    this.mContext.getSharedPreferences("koudai", 0).edit().putString("longitude", paramString).commit();
  }

  public void setMajorId(String paramString)
  {
    this.mContext.getSharedPreferences("koudai", 0).edit().putString("major_id", paramString).commit();
  }

  public void setMajorName(String paramString)
  {
    this.mContext.getSharedPreferences("koudai", 0).edit().putString("major_name", paramString).commit();
  }

  public void setNickName(String paramString)
  {
    this.mContext.getSharedPreferences("koudai", 0).edit().putString("nick_name", paramString).commit();
  }

  public void setPassword(String paramString)
  {
    this.mContext.getSharedPreferences("koudai", 0).edit().putString("password", paramString).commit();
  }

  public void setPhone(String paramString)
  {
    this.mContext.getSharedPreferences("koudai", 0).edit().putString("phone", paramString).commit();
  }

  public void setProId(String paramString)
  {
    this.mContext.getSharedPreferences("koudai", 0).edit().putString("pro_id", paramString).commit();
  }

  public void setProName(String paramString)
  {
    this.mContext.getSharedPreferences("koudai", 0).edit().putString("pro_name", paramString).commit();
  }

  public void setThirdId(String paramString)
  {
    this.mContext.getSharedPreferences("koudai", 0).edit().putString("THIRD_ID", paramString).commit();
  }

  public void setThirdType(String paramString)
  {
    this.mContext.getSharedPreferences("koudai", 0).edit().putString("THIRD_TYPE", paramString).commit();
  }

  public void setUpdateSubject(boolean paramBoolean)
  {
    this.mContext.getSharedPreferences("koudai", 0).edit().putBoolean("update_subject", paramBoolean).commit();
  }

  public void setUserId(String paramString)
  {
    this.mContext.getSharedPreferences("koudai", 0).edit().putString("uId", paramString).commit();
  }

  public void setVersionCode(String paramString)
  {
    this.mContext.getSharedPreferences("koudai", 0).edit().putString("version_code", paramString).commit();
  }

  public void setVersionName(String paramString)
  {
    this.mContext.getSharedPreferences("koudai", 0).edit().putString("version_name", paramString).commit();
  }

  public void setWidth(int paramInt)
  {
    this.mContext.getSharedPreferences("koudai", 0).edit().putInt("WIDTH", paramInt).commit();
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.config.KouDaiSP
 * JD-Core Version:    0.6.0
 */