package com.withustudy.koudaizikao.entity.req;

public class UserBaseInfo
{
  protected String clientType;
  private String gender;
  protected String imei;
  protected String net;
  private String newPassWord;
  private String nickname;
  private String oldPassWord;
  private String profileUrl;
  protected String versionName;

  public String getClientType()
  {
    return this.clientType;
  }

  public String getGender()
  {
    return this.gender;
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

  public String getNickname()
  {
    return this.nickname;
  }

  public String getOldPassWord()
  {
    return this.oldPassWord;
  }

  public String getProfileUrl()
  {
    return this.profileUrl;
  }

  public String getVersionName()
  {
    return this.versionName;
  }

  public void setClientType(String paramString)
  {
    this.clientType = paramString;
  }

  public void setGender(String paramString)
  {
    this.gender = paramString;
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

  public void setNickname(String paramString)
  {
    this.nickname = paramString;
  }

  public void setOldPassWord(String paramString)
  {
    this.oldPassWord = paramString;
  }

  public void setProfileUrl(String paramString)
  {
    this.profileUrl = paramString;
  }

  public void setVersionName(String paramString)
  {
    this.versionName = paramString;
  }

  public String toString()
  {
    return "UserBaseInfo [oldPassWord=" + this.oldPassWord + ", newPassWord=" + this.newPassWord + ", nickname=" + this.nickname + ", profileUrl=" + this.profileUrl + ", gender=" + this.gender + "]";
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.entity.req.UserBaseInfo
 * JD-Core Version:    0.6.0
 */