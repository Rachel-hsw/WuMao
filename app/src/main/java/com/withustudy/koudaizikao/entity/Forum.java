package com.withustudy.koudaizikao.entity;

import java.io.Serializable;

public class Forum
  implements Serializable
{
  private String forum_desc;
  private int forum_id;
  private String forum_img;
  private int forum_isfollow;
  private long forum_last_post_date;
  private String forum_name;
  private int forum_reply;
  private int forum_topics;
  private int forum_user;

  public String getForum_desc()
  {
    return this.forum_desc;
  }

  public int getForum_id()
  {
    return this.forum_id;
  }

  public String getForum_img()
  {
    return this.forum_img;
  }

  public int getForum_isfollow()
  {
    return this.forum_isfollow;
  }

  public long getForum_last_post_date()
  {
    return this.forum_last_post_date;
  }

  public String getForum_name()
  {
    return this.forum_name;
  }

  public int getForum_reply()
  {
    return this.forum_reply;
  }

  public int getForum_topics()
  {
    return this.forum_topics;
  }

  public int getForum_user()
  {
    return this.forum_user;
  }

  public void setForum_desc(String paramString)
  {
    this.forum_desc = paramString;
  }

  public void setForum_id(int paramInt)
  {
    this.forum_id = paramInt;
  }

  public void setForum_img(String paramString)
  {
    this.forum_img = paramString;
  }

  public void setForum_isfollow(int paramInt)
  {
    this.forum_isfollow = paramInt;
  }

  public void setForum_last_post_date(long paramLong)
  {
    this.forum_last_post_date = paramLong;
  }

  public void setForum_name(String paramString)
  {
    this.forum_name = paramString;
  }

  public void setForum_reply(int paramInt)
  {
    this.forum_reply = paramInt;
  }

  public void setForum_topics(int paramInt)
  {
    this.forum_topics = paramInt;
  }

  public void setForum_user(int paramInt)
  {
    this.forum_user = paramInt;
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.entity.Forum
 * JD-Core Version:    0.6.0
 */