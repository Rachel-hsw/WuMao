package com.withustudy.koudaizikao.entity;

import java.io.Serializable;

public class SmartPushState
  implements Serializable
{
  private ResponseStatus responseStatus;
  private SmartStat smartStat;

  public ResponseStatus getResponseStatus()
  {
    return this.responseStatus;
  }

  public SmartStat getSmartStat()
  {
    return this.smartStat;
  }

  public void setResponseStatus(ResponseStatus paramResponseStatus)
  {
    this.responseStatus = paramResponseStatus;
  }

  public void setSmartStat(SmartStat paramSmartStat)
  {
    this.smartStat = paramSmartStat;
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.entity.SmartPushState
 * JD-Core Version:    0.6.0
 */