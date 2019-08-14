package com.withustudy.koudaizikao.entity.req;

import com.withustudy.koudaizikao.entity.Kpoint;
import java.io.Serializable;

public class FavoriteExercise
  implements Serializable
{
  private String category;
  protected String clientType;
  private String exerciseId;
  protected String imei;
  private Kpoint kpoint;
  protected String net;
  private String stemText;
  private long time;
  protected String versionName;

  public String getCategory()
  {
    return this.category;
  }

  public String getClientType()
  {
    return this.clientType;
  }

  public String getExerciseId()
  {
    return this.exerciseId;
  }

  public String getImei()
  {
    return this.imei;
  }

  public Kpoint getKpoint()
  {
    return this.kpoint;
  }

  public String getNet()
  {
    return this.net;
  }

  public String getStemText()
  {
    return this.stemText;
  }

  public long getTime()
  {
    return this.time;
  }

  public String getVersionName()
  {
    return this.versionName;
  }

  public void setCategory(String paramString)
  {
    this.category = paramString;
  }

  public void setClientType(String paramString)
  {
    this.clientType = paramString;
  }

  public void setExerciseId(String paramString)
  {
    this.exerciseId = paramString;
  }

  public void setImei(String paramString)
  {
    this.imei = paramString;
  }

  public void setKpoint(Kpoint paramKpoint)
  {
    this.kpoint = paramKpoint;
  }

  public void setNet(String paramString)
  {
    this.net = paramString;
  }

  public void setStemText(String paramString)
  {
    this.stemText = paramString;
  }

  public void setTime(long paramLong)
  {
    this.time = paramLong;
  }

  public void setVersionName(String paramString)
  {
    this.versionName = paramString;
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.entity.req.FavoriteExercise
 * JD-Core Version:    0.6.0
 */