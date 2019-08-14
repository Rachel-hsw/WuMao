package com.withustudy.koudaizikao.entity.req;

public class Login
{
  private String accountType;
  private String clientId;
  private String clientType;
  private String imei;
  private String net;
  private String passWord;
  private String phoneNumber;
  private String versionName;

  public String getAccountType()
  {
    return this.accountType;
  }

  public String getClientId()
  {
    return this.clientId;
  }

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

  public String getPassWord()
  {
    return this.passWord;
  }

  public String getPhoneNumber()
  {
    return this.phoneNumber;
  }

  public String getVersionName()
  {
    return this.versionName;
  }

  public void setAccountType(String paramString)
  {
    this.accountType = paramString;
  }

  public void setClientId(String paramString)
  {
    this.clientId = paramString;
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

  public void setPassWord(String paramString)
  {
    this.passWord = paramString;
  }

  public void setPhoneNumber(String paramString)
  {
    this.phoneNumber = paramString;
  }

  public void setVersionName(String paramString)
  {
    this.versionName = paramString;
  }

  public String toString()
  {
    return "Login [phoneNumber=" + this.phoneNumber + ", passWord=" + this.passWord + ", accountType=" + this.accountType + ", clientId=" + this.clientId + "]";
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.entity.req.Login
 * JD-Core Version:    0.6.0
 */