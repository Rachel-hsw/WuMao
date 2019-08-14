package com.withustudy.koudaizikao.entity;

import java.io.Serializable;

public class Options
  implements Serializable
{
  private String content;
  private String id;

  public String getContent()
  {
    return this.content;
  }

  public String getId()
  {
    return this.id;
  }

  public void setContent(String paramString)
  {
    this.content = paramString;
  }

  public void setId(String paramString)
  {
    this.id = paramString;
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.entity.Options
 * JD-Core Version:    0.6.0
 */