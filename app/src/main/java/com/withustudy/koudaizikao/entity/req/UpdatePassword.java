package com.withustudy.koudaizikao.entity.req;

public class UpdatePassword
{
  protected String clientType;
  protected String imei;
  protected String net;
  private String newPassWord;
  private String oldPassWord;
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

  public String getNet()
  {
    return this.net;
  }

  public String getNewPassWord()
  {
    return this.newPassWord;
  }

  public String getOldPassWord()
  {
    return this.oldPassWord;
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

  public void setNet(String paramString)
  {
    this.net = paramString;
  }

  public void setNewPassWord(String paramString)
  {
    this.newPassWord = paramString;
  }

  public void setOldPassWord(String paramString)
  {
    this.oldPassWord = paramString;
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
    return "UpdatePassword [uid=" + this.uid + ", newPassWord=" + this.newPassWord + ", oldPassWord=" + this.oldPassWord + "]";
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.entity.req.UpdatePassword
 * JD-Core Version:    0.6.0
 */