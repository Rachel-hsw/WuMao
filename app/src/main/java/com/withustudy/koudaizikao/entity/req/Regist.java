package com.withustudy.koudaizikao.entity.req;

public class Regist
{
  private String accountType;
  private String authCode;
  private String clientId;
  private String clientType;
  private String imei;
  private String net;
  private String nickname;
  private String passWord;
  private String phoneNumber;
  private String versionName;

  public String getAccountType()
  {
    return this.accountType;
  }

  public String getAuthCode()
  {
    return this.authCode;
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

  public String getNickname()
  {
    return this.nickname;
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

  public void setAuthCode(String paramString)
  {
    this.authCode = paramString;
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

  public void setNickname(String paramString)
  {
    this.nickname = paramString;
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
    return "ReqRegistObject [passWord=" + this.passWord + ", phoneNumber=" + this.phoneNumber + ", authCode=" + this.authCode + ", accountType=" + this.accountType + ", nickname=" + this.nickname + ", clientId=" + this.clientId + "]";
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.entity.req.Regist
 * JD-Core Version:    0.6.0
 */