package com.withustudy.koudaizikao.entity;

import java.io.Serializable;

public class SmartStat
  implements Serializable
{
  private double beatRate;
  private double graspLevel;

  public double getBeatRate()
  {
    return this.beatRate;
  }

  public double getGraspLevel()
  {
    return this.graspLevel;
  }

  public void setBeatRate(double paramDouble)
  {
    this.beatRate = paramDouble;
  }

  public void setGraspLevel(double paramDouble)
  {
    this.graspLevel = paramDouble;
  }

  public void setGraspLevel(int paramInt)
  {
    this.graspLevel = paramInt;
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.entity.SmartStat
 * JD-Core Version:    0.6.0
 */