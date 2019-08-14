package com.withustudy.koudaizikao.entity;

import java.io.Serializable;
import java.util.List;

public class MockRecord
  implements Serializable
{
  private List<CategoryScore> categoryScore;
  private long costTime;
  private String date;
  private double finalScore;

  public List<CategoryScore> getCategoryScore()
  {
    return this.categoryScore;
  }

  public long getCostTime()
  {
    return this.costTime;
  }

  public String getDate()
  {
    return this.date;
  }

  public double getFinalScore()
  {
    return this.finalScore;
  }

  public void setCategoryScore(List<CategoryScore> paramList)
  {
    this.categoryScore = paramList;
  }

  public void setCostTime(long paramLong)
  {
    this.costTime = paramLong;
  }

  public void setDate(String paramString)
  {
    this.date = paramString;
  }

  public void setFinalScore(double paramDouble)
  {
    this.finalScore = paramDouble;
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.entity.MockRecord
 * JD-Core Version:    0.6.0
 */