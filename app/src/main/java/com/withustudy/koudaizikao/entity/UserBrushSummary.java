package com.withustudy.koudaizikao.entity;

import com.withustudy.koudaizikao.entity.req.UserInfo;

public class UserBrushSummary
{
  private int brushNum;
  private double correctRate;
  private UserInfo userInfo;

  public int getBrushNum()
  {
    return this.brushNum;
  }

  public double getCorrectRate()
  {
    return this.correctRate;
  }

  public UserInfo getUserInfo()
  {
    return this.userInfo;
  }

  public void setBrushNum(int paramInt)
  {
    this.brushNum = paramInt;
  }

  public void setCorrectRate(double paramDouble)
  {
    this.correctRate = paramDouble;
  }

  public void setUserInfo(UserInfo paramUserInfo)
  {
    this.userInfo = paramUserInfo;
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.entity.UserBrushSummary
 * JD-Core Version:    0.6.0
 */