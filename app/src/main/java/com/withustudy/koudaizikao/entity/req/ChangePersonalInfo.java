package com.withustudy.koudaizikao.entity.req;

public class ChangePersonalInfo
{
  protected String clientType;
  protected String imei;
  protected String net;
  private String uid;
  private UserBaseInfo userBasicInfo;
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

  public String getUid()
  {
    return this.uid;
  }

  public UserBaseInfo getUserBasicInfo()
  {
    return this.userBasicInfo;
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

  public void setUid(String paramString)
  {
    this.uid = paramString;
  }

  public void setUserBasicInfo(UserBaseInfo paramUserBaseInfo)
  {
    this.userBasicInfo = paramUserBaseInfo;
  }

  public void setVersionName(String paramString)
  {
    this.versionName = paramString;
  }

  public String toString()
  {
    return "ChangePersonalInfo [uid=" + this.uid + ", userBasicInfo=" + this.userBasicInfo + "]";
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.entity.req.ChangePersonalInfo
 * JD-Core Version:    0.6.0
 */