package com.withustudy.koudaizikao.entity.req;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserInfo
  implements Serializable
{
  protected String clientType;
  private String createTime;
  private String headPic;
  protected String imei;
  protected String net;
  private String nickname;
  private String orders;
  private String province;
  private List<Subjects> subjects = new ArrayList();
  private String uid;
  protected String versionName;
  private long wallet;

  public String getClientType()
  {
    return this.clientType;
  }

  public String getCreateTime()
  {
    return this.createTime;
  }

  public String getHeadPic()
  {
    return this.headPic;
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

  public String getOrders()
  {
    return this.orders;
  }

  public String getProvince()
  {
    return this.province;
  }

  public List<Subjects> getSubjects()
  {
    return this.subjects;
  }

  public String getUid()
  {
    return this.uid;
  }

  public String getVersionName()
  {
    return this.versionName;
  }

  public long getWallet()
  {
    return this.wallet;
  }

  public void setClientType(String paramString)
  {
    this.clientType = paramString;
  }

  public void setCreateTime(String paramString)
  {
    this.createTime = paramString;
  }

  public void setHeadPic(String paramString)
  {
    this.headPic = paramString;
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

  public void setOrders(String paramString)
  {
    this.orders = paramString;
  }

  public void setProvince(String paramString)
  {
    this.province = paramString;
  }

  public void setSubjects(List<Subjects> paramList)
  {
    this.subjects = paramList;
  }

  public void setUid(String paramString)
  {
    this.uid = paramString;
  }

  public void setVersionName(String paramString)
  {
    this.versionName = paramString;
  }

  public void setWallet(long paramLong)
  {
    this.wallet = paramLong;
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.entity.req.UserInfo
 * JD-Core Version:    0.6.0
 */