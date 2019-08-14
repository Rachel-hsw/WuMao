package com.withustudy.koudaizikao.entity.req;

public class ReqBrushRank
{
  protected String clientType;
  protected String imei;
  protected String net;
  private String timeSpan;
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

  public String getNet()
  {
    return this.net;
  }

  public String getTimeSpan()
  {
    return this.timeSpan;
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

  public void setNet(String paramString)
  {
    this.net = paramString;
  }

  public void setTimeSpan(String paramString)
  {
    this.timeSpan = paramString;
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
 * Qualified Name:     com.withustudy.koudaizikao.entity.req.ReqBrushRank
 * JD-Core Version:    0.6.0
 */