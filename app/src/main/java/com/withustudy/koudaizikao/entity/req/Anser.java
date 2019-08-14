package com.withustudy.koudaizikao.entity.req;

import java.util.List;

public class Anser
{
  protected String clientType;
  protected String imei;
  private KPoint kPoint;
  private String lastKpointFlag;
  protected String net;
  private List<UserAnswers> userAnswers;
  private UserSubject userSubject;
  protected String versionName;

  public String getClientType()
  {
    return this.clientType;
  }

  public String getImei()
  {
    return this.imei;
  }

  public String getLastKpointFlag()
  {
    return this.lastKpointFlag;
  }

  public String getNet()
  {
    return this.net;
  }

  public List<UserAnswers> getUserAnswers()
  {
    return this.userAnswers;
  }

  public UserSubject getUserSubject()
  {
    return this.userSubject;
  }

  public String getVersionName()
  {
    return this.versionName;
  }

  public KPoint getkPoint()
  {
    return this.kPoint;
  }

  public void setClientType(String paramString)
  {
    this.clientType = paramString;
  }

  public void setImei(String paramString)
  {
    this.imei = paramString;
  }

  public void setLastKpointFlag(String paramString)
  {
    this.lastKpointFlag = paramString;
  }

  public void setNet(String paramString)
  {
    this.net = paramString;
  }

  public void setUserAnswers(List<UserAnswers> paramList)
  {
    this.userAnswers = paramList;
  }

  public void setUserSubject(UserSubject paramUserSubject)
  {
    this.userSubject = paramUserSubject;
  }

  public void setVersionName(String paramString)
  {
    this.versionName = paramString;
  }

  public void setkPoint(KPoint paramKPoint)
  {
    this.kPoint = paramKPoint;
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.entity.req.Anser
 * JD-Core Version:    0.6.0
 */