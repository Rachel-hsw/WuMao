package com.withustudy.koudaizikao.entity.req;

public class FindSubject
{
  protected String clientType;
  protected String imei;
  private MajorUpLoad major;
  protected String net;
  private String uid;
  protected String versionName;

  public String getClientType()
  {
    return this.clientType;
  }

  public String getImei()
  {
    return this.imei;
  }

  public MajorUpLoad getMajor()
  {
    return this.major;
  }

  public String getNet()
  {
    return this.net;
  }

  public String getUid()
  {
    return this.uid;
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

  public void setMajor(MajorUpLoad paramMajorUpLoad)
  {
    this.major = paramMajorUpLoad;
  }

  public void setNet(String paramString)
  {
    this.net = paramString;
  }

  public void setUid(String paramString)
  {
    this.uid = paramString;
  }

  public void setVersionName(String paramString)
  {
    this.versionName = paramString;
  }

  public String toString()
  {
    return "UploadProv [uid=" + this.uid + ", major=" + this.major + "]";
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.entity.req.FindSubject
 * JD-Core Version:    0.6.0
 */