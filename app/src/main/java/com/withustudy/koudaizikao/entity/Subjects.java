package com.withustudy.koudaizikao.entity;

import java.io.Serializable;

public class Subjects
  implements Serializable
{
  private String id;
  private String name;

  public String getId()
  {
    return this.id;
  }

  public String getName()
  {
    return this.name;
  }

  public void setId(String paramString)
  {
    this.id = paramString;
  }

  public void setName(String paramString)
  {
    this.name = paramString;
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.entity.Subjects
 * JD-Core Version:    0.6.0
 */