package com.withustudy.koudaizikao.entity;

public class SubjectState
{
  private double beatRate;
  private String currentRank;
  private String doneCount;
  private String lackCount;

  public double getBeatRate()
  {
    return this.beatRate;
  }

  public String getCurrentRank()
  {
    return this.currentRank;
  }

  public String getDoneCount()
  {
    return this.doneCount;
  }

  public String getLackCount()
  {
    return this.lackCount;
  }

  public void setBeatRate(double paramDouble)
  {
    this.beatRate = paramDouble;
  }

  public void setCurrentRank(String paramString)
  {
    this.currentRank = paramString;
  }

  public void setDoneCount(String paramString)
  {
    this.doneCount = paramString;
  }

  public void setLackCount(String paramString)
  {
    this.lackCount = paramString;
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.entity.SubjectState
 * JD-Core Version:    0.6.0
 */