package com.withustudy.koudaizikao.entity;

import java.io.Serializable;
import java.util.List;

public class Province
  implements Serializable
{
  private List<Major> major;
  private String provId;
  private String provName;

  public List<Major> getMajor()
  {
    return this.major;
  }

  public String getProvId()
  {
    return this.provId;
  }

  public String getProvName()
  {
    return this.provName;
  }

  public void setMajor(List<Major> paramList)
  {
    this.major = paramList;
  }

  public void setProvId(String paramString)
  {
    this.provId = paramString;
  }

  public void setProvName(String paramString)
  {
    this.provName = paramString;
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.entity.Province
 * JD-Core Version:    0.6.0
 */