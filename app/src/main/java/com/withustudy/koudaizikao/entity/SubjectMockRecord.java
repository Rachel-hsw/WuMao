package com.withustudy.koudaizikao.entity;

import java.io.Serializable;
import java.util.List;

public class SubjectMockRecord
  implements Serializable
{
  private List<MockRecord> mockRecord;
  private Subject subject;

  public List<MockRecord> getMockRecord()
  {
    return this.mockRecord;
  }

  public Subject getSubject()
  {
    return this.subject;
  }

  public void setMockRecord(List<MockRecord> paramList)
  {
    this.mockRecord = paramList;
  }

  public void setSubject(Subject paramSubject)
  {
    this.subject = paramSubject;
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.entity.SubjectMockRecord
 * JD-Core Version:    0.6.0
 */