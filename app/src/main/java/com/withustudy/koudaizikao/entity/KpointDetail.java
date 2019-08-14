package com.withustudy.koudaizikao.entity;

import java.io.Serializable;

public class KpointDetail
  implements Serializable
{
  private double errorRate;
  private String explainText;
  private double graspLevel;
  private String graspRequire;
  private Kpoint kpoint;
  private double myErrorRate;

  public double getErrorRate()
  {
    return this.errorRate;
  }

  public String getExplainText()
  {
    return this.explainText;
  }

  public double getGraspLevel()
  {
    return this.graspLevel;
  }

  public String getGraspRequire()
  {
    return this.graspRequire;
  }

  public Kpoint getKpoint()
  {
    return this.kpoint;
  }

  public double getMyErrorRate()
  {
    return this.myErrorRate;
  }

  public void setErrorRate(double paramDouble)
  {
    this.errorRate = paramDouble;
  }

  public void setExplainText(String paramString)
  {
    this.explainText = paramString;
  }

  public void setGraspLevel(double paramDouble)
  {
    this.graspLevel = paramDouble;
  }

  public void setGraspRequire(String paramString)
  {
    this.graspRequire = paramString;
  }

  public void setKpoint(Kpoint paramKpoint)
  {
    this.kpoint = paramKpoint;
  }

  public void setMyErrorRate(double paramDouble)
  {
    this.myErrorRate = paramDouble;
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.entity.KpointDetail
 * JD-Core Version:    0.6.0
 */