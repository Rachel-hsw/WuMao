package com.withustudy.koudaizikao.entity;

import java.io.Serializable;

public class News
  implements Serializable
{
  private String articleId;
  private String articleType;
  private String attachUrl;
  private String briefText;
  private String commentNum;
  private String createdTime;
  private String favoriteTag;
  private String handpickedTag;
  private String instruction;
  private String panelUrl;
  private String thumbNum;
  private String thumbTag;
  private String transmitNum;

  public String getArticleId()
  {
    return this.articleId;
  }

  public String getArticleType()
  {
    return this.articleType;
  }

  public String getAttachUrl()
  {
    return this.attachUrl;
  }

  public String getBriefText()
  {
    return this.briefText;
  }

  public String getCommentNum()
  {
    return this.commentNum;
  }

  public String getCreatedTime()
  {
    return this.createdTime;
  }

  public String getFavoriteTag()
  {
    return this.favoriteTag;
  }

  public String getHandpickedTag()
  {
    return this.handpickedTag;
  }

  public String getInstruction()
  {
    return this.instruction;
  }

  public String getPanelUrl()
  {
    return this.panelUrl;
  }

  public String getThumbNum()
  {
    return this.thumbNum;
  }

  public String getThumbTag()
  {
    return this.thumbTag;
  }

  public String getTransmitNum()
  {
    return this.transmitNum;
  }

  public void setArticleId(String paramString)
  {
    this.articleId = paramString;
  }

  public void setArticleType(String paramString)
  {
    this.articleType = paramString;
  }

  public void setAttachUrl(String paramString)
  {
    this.attachUrl = paramString;
  }

  public void setBriefText(String paramString)
  {
    this.briefText = paramString;
  }

  public void setCommentNum(String paramString)
  {
    this.commentNum = paramString;
  }

  public void setCreatedTime(String paramString)
  {
    this.createdTime = paramString;
  }

  public void setFavoriteTag(String paramString)
  {
    this.favoriteTag = paramString;
  }

  public void setHandpickedTag(String paramString)
  {
    this.handpickedTag = paramString;
  }

  public void setInstruction(String paramString)
  {
    this.instruction = paramString;
  }

  public void setPanelUrl(String paramString)
  {
    this.panelUrl = paramString;
  }

  public void setThumbNum(String paramString)
  {
    this.thumbNum = paramString;
  }

  public void setThumbTag(String paramString)
  {
    this.thumbTag = paramString;
  }

  public void setTransmitNum(String paramString)
  {
    this.transmitNum = paramString;
  }

  public String toString()
  {
    return "News [instruction=" + this.instruction + ", briefText=" + this.briefText + ", articleId=" + this.articleId + ", articleType=" + this.articleType + ", createdTime=" + this.createdTime + ", panelUrl=" + this.panelUrl + ", attachUrl=" + this.attachUrl + ", favoriteTag=" + this.favoriteTag + ", thumbTag=" + this.thumbTag + ", transmitNum=" + this.transmitNum + ", thumbNum=" + this.thumbNum + ", commentNum=" + this.commentNum + ", handpickedTag=" + this.handpickedTag + "]";
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.entity.News
 * JD-Core Version:    0.6.0
 */