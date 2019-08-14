package com.withustudy.koudaizikao.entity.req;

public class MajorUpLoad
{
  protected String clientType;
  protected String imei;
  private String majorId;
  private String majorName;
  protected String net;
  private String provId;
  private String provName;
  protected String versionName;

  public String getClientType()
  {
    return this.clientType;
  }

  public String getImei()
  {
    return this.imei;
  }

  public String getMajorId()
  {
    return this.majorId;
  }

  public String getMajorName()
  {
    return this.majorName;
  }

  public String getNet()
  {
    return this.net;
  }

  public String getProvId()
  {
    return this.provId;
  }

  public String getProvName()
  {
    return this.provName;
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

  public void setMajorId(String paramString)
  {
    this.majorId = paramString;
  }

  public void setMajorName(String paramString)
  {
    this.majorName = paramString;
  }

  public void setNet(String paramString)
  {
    this.net = paramString;
  }

  public void setProvId(String paramString)
  {
    this.provId = paramString;
  }

  public void setProvName(String paramString)
  {
    this.provName = paramString;
  }

  public void setVersionName(String paramString)
  {
    this.versionName = paramString;
  }

  public String toString()
  {
    return "MajorUpLoad [majorId=" + this.majorId + ", majorName=" + this.majorName + ", provId=" + this.provId + ", provName=" + this.provName + "]";
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.entity.req.MajorUpLoad
 * JD-Core Version:    0.6.0
 */