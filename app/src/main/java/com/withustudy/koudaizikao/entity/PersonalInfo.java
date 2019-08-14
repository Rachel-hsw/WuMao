package com.withustudy.koudaizikao.entity;

import java.io.Serializable;

public class PersonalInfo
  implements Serializable
{
  private String accountType;
  private String gender;
  private String nickname;
  private String phoneNumber;
  private String profileUrl;

  public String getAccountType()
  {
    return this.accountType;
  }

  public String getGender()
  {
    return this.gender;
  }

  public String getNickname()
  {
    return this.nickname;
  }

  public String getPhoneNumber()
  {
    return this.phoneNumber;
  }

  public String getProfileUrl()
  {
    return this.profileUrl;
  }

  public void setAccountType(String paramString)
  {
    this.accountType = paramString;
  }

  public void setGender(String paramString)
  {
    this.gender = paramString;
  }

  public void setNickname(String paramString)
  {
    this.nickname = paramString;
  }

  public void setPhoneNumber(String paramString)
  {
    this.phoneNumber = paramString;
  }

  public void setProfileUrl(String paramString)
  {
    this.profileUrl = paramString;
  }

  public String toString()
  {
    return "PersonalInfo [nickname=" + this.nickname + ", phoneNumber=" + this.phoneNumber + ", profileUrl=" + this.profileUrl + "]";
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.entity.PersonalInfo
 * JD-Core Version:    0.6.0
 */