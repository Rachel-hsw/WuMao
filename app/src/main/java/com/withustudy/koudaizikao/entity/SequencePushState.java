package com.withustudy.koudaizikao.entity;

import java.io.Serializable;

public class SequencePushState
  implements Serializable
{
  private ResponseStatus responseStatus;
  private SectionStat sectionStat;

  public ResponseStatus getResponseStatus()
  {
    return this.responseStatus;
  }

  public SectionStat getSectionStat()
  {
    return this.sectionStat;
  }

  public void setResponseStatus(ResponseStatus paramResponseStatus)
  {
    this.responseStatus = paramResponseStatus;
  }

  public void setSectionStat(SectionStat paramSectionStat)
  {
    this.sectionStat = paramSectionStat;
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.entity.SequencePushState
 * JD-Core Version:    0.6.0
 */