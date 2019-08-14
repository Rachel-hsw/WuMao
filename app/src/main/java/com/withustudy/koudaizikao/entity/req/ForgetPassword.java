package com.withustudy.koudaizikao.entity.req;

public class ForgetPassword
{
  private String authCode;
  protected String clientType;
  protected String imei;
  protected String net;
  private String newPassWord;
  private String phoneNumber;
  protected String versionName;

  public String getAuthCode()
  {
    return this.authCode;
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

  public String getNewPassWord()
  {
    return this.newPassWord;
  }

  public String getPhoneNumber()
  {
    return this.phoneNumber;
  }

  public String getVersionName()
  {
    return this.versionName;
  }

  public void setAuthCode(String paramString)
  {
    this.authCode = paramString;
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
    return "ForgetPassword [phoneNumber=" + this.phoneNumber + ", newPassWord=" + this.newPassWord + ", authCode=" + this.authCode + "]";
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.entity.req.ForgetPassword
 * JD-Core Version:    0.6.0
 */