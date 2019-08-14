package com.withustudy.koudaizikao.entity.req.push;

public class PushState
{
  protected String clientType;
  protected String imei;
  protected String net;
  private String reason;
  private String status;
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

  public String getReason()
  {
    return this.reason;
  }

  public String getStatus()
  {
    return this.status;
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

  public void setReason(String paramString)
  {
    this.reason = paramString;
  }

  public void setStatus(String paramString)
  {
    this.status = paramString;
  }

  public void setVersionName(String paramString)
  {
    this.versionName = paramString;
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.entity.req.push.PushState
 * JD-Core Version:    0.6.0
 */