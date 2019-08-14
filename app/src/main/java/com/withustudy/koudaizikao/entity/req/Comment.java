package com.withustudy.koudaizikao.entity.req;

import java.io.Serializable;

public class Comment
  implements Serializable
{
  protected String clientType;
  private long commentTime;
  private String content;
  protected String imei;
  private String location;
  protected String net;
  private String replyFloorContent;
  private UserInfo userInfo;
  protected String versionName;

  public String getClientType()
  {
    return this.clientType;
  }

  public long getCommentTime()
  {
    return this.commentTime;
  }

  public String getContent()
  {
    return this.content;
  }

  public String getImei()
  {
    return this.imei;
  }

  public String getLocation()
  {
    return this.location;
  }

  public String getNet()
  {
    return this.net;
  }

  public String getReplyFloorContent()
  {
    return this.replyFloorContent;
  }

  public UserInfo getUserInfo()
  {
    return this.userInfo;
  }

  public String getVersionName()
  {
    return this.versionName;
  }

  public void setClientType(String paramString)
  {
    this.clientType = paramString;
  }

  public void setCommentTime(long paramLong)
  {
    this.commentTime = paramLong;
  }

  public void setContent(String paramString)
  {
    this.content = paramString;
  }

  public void setImei(String paramString)
  {
    this.imei = paramString;
  }

  public void setLocation(String paramString)
  {
    this.location = paramString;
  }

  public void setNet(String paramString)
  {
    this.net = paramString;
  }

  public void setReplyFloorContent(String paramString)
  {
    this.replyFloorContent = paramString;
  }

  public void setUserInfo(UserInfo paramUserInfo)
  {
    this.userInfo = paramUserInfo;
  }

  public void setVersionName(String paramString)
  {
    this.versionName = paramString;
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.entity.req.Comment
 * JD-Core Version:    0.6.0
 */