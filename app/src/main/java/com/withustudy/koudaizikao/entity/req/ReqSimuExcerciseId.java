package com.withustudy.koudaizikao.entity.req;

public class ReqSimuExcerciseId
{
  protected String clientType;
  protected String imei;
  private String level;
  protected String net;
  private UserSubject userSubject;
  protected String versionName;

  public String getClientType()
  {
    return this.clientType;
  }

  public String getImei()
  {
    return this.imei;
  }

  public String getLevel()
  {
    return this.level;
  }

  public String getNet()
  {
    return this.net;
  }

  public UserSubject getUserSubject()
  {
    return this.userSubject;
  }

  public String getVersionName()
  {
    return this.versionName;
  }

  public void setClientType(String paramString)
  {
    this.clientType = paramString;
  }

  public void setImei(String paramString)
  {
    this.imei = paramString;
  }

  public void setLevel(String paramString)
  {
    this.level = paramString;
  }

  public void setNet(String paramString)
  {
    this.net = paramString;
  }

  public void setUserSubject(UserSubject paramUserSubject)
  {
    this.userSubject = paramUserSubject;
  }

  public void setVersionName(String paramString)
  {
    this.versionName = paramString;
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.entity.req.ReqSimuExcerciseId
 * JD-Core Version:    0.6.0
 */