package com.withustudy.koudaizikao.entity;

import java.io.Serializable;

public class Kpoint
  implements Serializable
{
  private String id;
  private String name;
  private String sn;

  public String getId()
  {
    return this.id;
  }

  public String getName()
  {
    return this.name;
  }

  public String getSn()
  {
    return this.sn;
  }

  public void setId(String paramString)
  {
    this.id = paramString;
  }

  public void setName(String paramString)
  {
    this.name = paramString;
  }

  public void setSn(String paramString)
  {
    this.sn = paramString;
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.entity.Kpoint
 * JD-Core Version:    0.6.0
 */