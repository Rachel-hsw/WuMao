package com.withustudy.koudaizikao.entity.req.push;

import com.withustudy.koudaizikao.entity.req.UserSubject;
import java.util.List;

public class ReqPushAnser
{
  private String brushType;
  protected String clientType;
  protected String imei;
  private long mockCostTime;
  protected String net;
  private List<UserAnswers> userAnswers;
  private UserSubject userSubject;
  protected String versionName;

  public String getBrushType()
  {
    return this.brushType;
  }

  public String getClientType()
  {
    return this.clientType;
  }

  public String getImei()
  {
    return this.imei;
  }

  public long getMockCostTime()
  {
    return this.mockCostTime;
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

  public void setBrushType(String paramString)
  {
    this.brushType = paramString;
  }

  public void setClientType(String paramString)
  {
    this.clientType = paramString;
  }

  public void setImei(String paramString)
  {
    this.imei = paramString;
  }

  public void setMockCostTime(long paramLong)
  {
    this.mockCostTime = paramLong;
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
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.entity.req.push.ReqPushAnser
 * JD-Core Version:    0.6.0
 */