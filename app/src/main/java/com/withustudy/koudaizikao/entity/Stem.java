package com.withustudy.koudaizikao.entity;

import java.io.Serializable;
import java.util.List;

public class Stem
  implements Serializable
{
  private List<String> figure;
  private String text;

  public List<String> getFigure()
  {
    return this.figure;
  }

  public String getText()
  {
    return this.text;
  }

  public void setFigure(List<String> paramList)
  {
    this.figure = paramList;
  }

  public void setText(String paramString)
  {
    this.text = paramString;
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.entity.Stem
 * JD-Core Version:    0.6.0
 */