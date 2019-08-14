package com.withustudy.koudaizikao.entity;

import java.io.Serializable;

public class SectionStat
  implements Serializable
{
  private double beatRate;
  private int completeNum;
  private int correctNum;
  private int errorNum;

  public double getBeatRate()
  {
    return this.beatRate;
  }

  public int getCompleteNum()
  {
    return this.completeNum;
  }

  public int getCorrectNum()
  {
    return this.correctNum;
  }

  public int getErrorNum()
  {
    return this.errorNum;
  }

  public void setBeatRate(double paramDouble)
  {
    this.beatRate = paramDouble;
  }

  public void setCompleteNum(int paramInt)
  {
    this.completeNum = paramInt;
  }

  public void setCorrectNum(int paramInt)
  {
    this.correctNum = paramInt;
  }

  public void setErrorNum(int paramInt)
  {
    this.errorNum = paramInt;
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.entity.SectionStat
 * JD-Core Version:    0.6.0
 */