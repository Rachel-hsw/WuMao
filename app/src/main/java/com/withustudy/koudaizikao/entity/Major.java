package com.withustudy.koudaizikao.entity;

import java.io.Serializable;

public class Major
  implements Serializable
{
  private String majorId;
  private String majorName;
  private String provName;

  public String getMajorId()
  {
    return this.majorId;
  }

  public String getMajorName()
  {
    return this.majorName;
  }

  public String getProvName()
  {
    return this.provName;
  }

  public void setMajorId(String paramString)
  {
    this.majorId = paramString;
  }

  public void setMajorName(String paramString)
  {
    this.majorName = paramString;
  }

  public void setProvName(String paramString)
  {
    this.provName = paramString;
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.entity.Major
 * JD-Core Version:    0.6.0
 */