package com.withustudy.koudaizikao.entity;

import java.io.Serializable;

public class ChooseProToChooseSub
  implements Serializable
{
  private String majorId;
  private String majorName;
  private String proId;
  private String provName;

  public String getMajorId()
  {
    return this.majorId;
  }

  public String getMajorName()
  {
    return this.majorName;
  }

  public String getProId()
  {
    return this.proId;
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

  public void setProId(String paramString)
  {
    this.proId = paramString;
  }

  public void setProvName(String paramString)
  {
    this.provName = paramString;
  }

  public String toString()
  {
    return "ChooseProToChooseSub [proId=" + this.proId + ", majorId=" + this.majorId + ", majorName=" + this.majorName + ", provName=" + this.provName + "]";
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.entity.ChooseProToChooseSub
 * JD-Core Version:    0.6.0
 */