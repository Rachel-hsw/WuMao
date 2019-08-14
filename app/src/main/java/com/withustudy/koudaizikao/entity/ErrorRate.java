package com.withustudy.koudaizikao.entity;

import java.io.Serializable;

public class ErrorRate
  implements Serializable
{
  private int errorNum;
  private double errorRate;
  private int totalNum;

  public int getErrorNum()
  {
    return this.errorNum;
  }

  public double getErrorRate()
  {
    return this.errorRate;
  }

  public int getTotalNum()
  {
    return this.totalNum;
  }

  public void setErrorNum(int paramInt)
  {
    this.errorNum = paramInt;
  }

  public void setErrorRate(double paramDouble)
  {
    this.errorRate = paramDouble;
  }

  public void setTotalNum(int paramInt)
  {
    this.totalNum = paramInt;
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.entity.ErrorRate
 * JD-Core Version:    0.6.0
 */