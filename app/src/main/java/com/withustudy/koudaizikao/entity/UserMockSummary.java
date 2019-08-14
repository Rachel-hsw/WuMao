package com.withustudy.koudaizikao.entity;

import com.withustudy.koudaizikao.entity.req.UserInfo;
import java.io.Serializable;

public class UserMockSummary
  implements Serializable
{
  private long costTime;
  private double score;
  private UserInfo userInfo;

  public long getCostTime()
  {
    return this.costTime;
  }

  public double getScore()
  {
    return this.score;
  }

  public UserInfo getUserInfo()
  {
    return this.userInfo;
  }

  public void setCostTime(long paramLong)
  {
    this.costTime = paramLong;
  }

  public void setScore(double paramDouble)
  {
    this.score = paramDouble;
  }

  public void setUserInfo(UserInfo paramUserInfo)
  {
    this.userInfo = paramUserInfo;
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.entity.UserMockSummary
 * JD-Core Version:    0.6.0
 */