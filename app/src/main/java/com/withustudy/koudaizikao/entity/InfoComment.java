package com.withustudy.koudaizikao.entity;

public class InfoComment
{
  private String commentId;
  private String commentTime;
  private String content;
  private String floorId;
  private String nickname;
  private String parentId;
  private String profileUrl;
  private String replyContent;
  private String replyName;

  public String getCommentId()
  {
    return this.commentId;
  }

  public String getCommentTime()
  {
    return this.commentTime;
  }

  public String getContent()
  {
    return this.content;
  }

  public String getFloorId()
  {
    return this.floorId;
  }

  public String getNickname()
  {
    return this.nickname;
  }

  public String getParentId()
  {
    return this.parentId;
  }

  public String getProfileUrl()
  {
    return this.profileUrl;
  }

  public String getReplyContent()
  {
    return this.replyContent;
  }

  public String getReplyName()
  {
    return this.replyName;
  }

  public void setCommentId(String paramString)
  {
    this.commentId = paramString;
  }

  public void setCommentTime(String paramString)
  {
    this.commentTime = paramString;
  }

  public void setContent(String paramString)
  {
    this.content = paramString;
  }

  public void setFloorId(String paramString)
  {
    this.floorId = paramString;
  }

  public void setNickname(String paramString)
  {
    this.nickname = paramString;
  }

  public void setParentId(String paramString)
  {
    this.parentId = paramString;
  }

  public void setProfileUrl(String paramString)
  {
    this.profileUrl = paramString;
  }

  public void setReplyContent(String paramString)
  {
    this.replyContent = paramString;
  }

  public void setReplyName(String paramString)
  {
    this.replyName = paramString;
  }

  public String toString()
  {
    return "InfoComment [commentId=" + this.commentId + ", nickname=" + this.nickname + ", profileUrl=" + this.profileUrl + ", content=" + this.content + ", commentTime=" + this.commentTime + ", floorId=" + this.floorId + ", parentId=" + this.parentId + ", replyName=" + this.replyName + ", replyContent=" + this.replyContent + "]";
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.entity.InfoComment
 * JD-Core Version:    0.6.0
 */