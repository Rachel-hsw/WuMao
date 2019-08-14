package com.withustudy.koudaizikao.entity.req;

import java.io.Serializable;

public class Subjects
  implements Serializable
{
  protected String clientType;
  private String id;
  protected String imei;
  private String name;
  protected String net;
  protected String versionName;

  public String getClientType()
  {
    return this.clientType;
  }

  public String getId()
  {
    return this.id;
  }

  public String getImei()
  {
    return this.imei;
  }

  public String getName()
  {
    return this.name;
  }

  public String getNet()
  {
    return this.net;
  }

  public String getVersionName()
  {
    return this.versionName;
  }

  public void setClientType(String paramString)
  {
    this.clientType = paramString;
  }

  public void setId(String paramString)
  {
    this.id = paramString;
  }

  public void setImei(String paramString)
  {
    this.imei = paramString;
  }

  public void setName(String paramString)
  {
    this.name = paramString;
  }

  public void setNet(String paramString)
  {
    this.net = paramString;
  }

  public void setVersionName(String paramString)
  {
    this.versionName = paramString;
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.entity.req.Subjects
 * JD-Core Version:    0.6.0
 */