package com.withustudy.koudaizikao.entity;

import java.io.Serializable;

public class MockPushState
  implements Serializable
{
  private MoackStat moackStat;
  private ResponseStatus responseStatus;

  public MoackStat getMoackStat()
  {
    return this.moackStat;
  }

  public ResponseStatus getResponseStatus()
  {
    return this.responseStatus;
  }

  public void setMoackStat(MoackStat paramMoackStat)
  {
    this.moackStat = paramMoackStat;
  }

  public void setResponseStatus(ResponseStatus paramResponseStatus)
  {
    this.responseStatus = paramResponseStatus;
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.entity.MockPushState
 * JD-Core Version:    0.6.0
 */