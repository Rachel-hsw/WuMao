package com.withustudy.koudaizikao.entity;

import java.io.Serializable;
import java.util.List;

public class Explain
  implements Serializable
{
  private List<String> figure;
  private String text;
  private String voiceUrl;

  public List<String> getFigure()
  {
    return this.figure;
  }

  public String getText()
  {
    return this.text;
  }

  public String getVoiceUrl()
  {
    return this.voiceUrl;
  }

  public void setFigure(List<String> paramList)
  {
    this.figure = paramList;
  }

  public void setText(String paramString)
  {
    this.text = paramString;
  }

  public void setVoiceUrl(String paramString)
  {
    this.voiceUrl = paramString;
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.entity.Explain
 * JD-Core Version:    0.6.0
 */