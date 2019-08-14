package com.withustudy.koudaizikao.entity;

import java.util.List;

public class PostReply
{
  private int forum_id;
  private String post_area;
  private List<String> post_files;
  private int post_floor;
  private int post_id;
  private int post_img_count;
  private String post_text;
  private long post_time;
  private String reply_content;
  private String reply_floor;
  private int reply_id;
  private int reply_type;
  private String reply_userid;
  private String reply_username;
  private String user_headimg;
  private String user_name;
  private String userid;

  public int getForum_id()
  {
    return this.forum_id;
  }

  public String getPost_area()
  {
    return this.post_area;
  }

  public List<String> getPost_files()
  {
    return this.post_files;
  }

  public int getPost_floor()
  {
    return this.post_floor;
  }

  public int getPost_id()
  {
    return this.post_id;
  }

  public int getPost_img_count()
  {
    return this.post_img_count;
  }

  public String getPost_text()
  {
    return this.post_text;
  }

  public long getPost_time()
  {
    return this.post_time;
  }

  public String getReply_content()
  {
    return this.reply_content;
  }

  public String getReply_floor()
  {
    return this.reply_floor;
  }

  public int getReply_id()
  {
    return this.reply_id;
  }

  public int getReply_type()
  {
    return this.reply_type;
  }

  public String getReply_userid()
  {
    return this.reply_userid;
  }

  public String getReply_username()
  {
    return this.reply_username;
  }

  public String getUser_headimg()
  {
    return this.user_headimg;
  }

  public String getUser_name()
  {
    return this.user_name;
  }

  public String getUserid()
  {
    return this.userid;
  }

  public void setForum_id(int paramInt)
  {
    this.forum_id = paramInt;
  }

  public void setPost_area(String paramString)
  {
    this.post_area = paramString;
  }

  public void setPost_files(List<String> paramList)
  {
    this.post_files = paramList;
  }

  public void setPost_floor(int paramInt)
  {
    this.post_floor = paramInt;
  }

  public void setPost_id(int paramInt)
  {
    this.post_id = paramInt;
  }

  public void setPost_img_count(int paramInt)
  {
    this.post_img_count = paramInt;
  }

  public void setPost_text(String paramString)
  {
    this.post_text = paramString;
  }

  public void setPost_time(long paramLong)
  {
    this.post_time = paramLong;
  }

  public void setReply_content(String paramString)
  {
    this.reply_content = paramString;
  }

  public void setReply_floor(String paramString)
  {
    this.reply_floor = paramString;
  }

  public void setReply_id(int paramInt)
  {
    this.reply_id = paramInt;
  }

  public void setReply_type(int paramInt)
  {
    this.reply_type = paramInt;
  }

  public void setReply_userid(String paramString)
  {
    this.reply_userid = paramString;
  }

  public void setReply_username(String paramString)
  {
    this.reply_username = paramString;
  }

  public void setUser_headimg(String paramString)
  {
    this.user_headimg = paramString;
  }

  public void setUser_name(String paramString)
  {
    this.user_name = paramString;
  }

  public void setUserid(String paramString)
  {
    this.userid = paramString;
  }

  public String toString()
  {
    return "PostReply [post_id=" + this.post_id + ", forum_id=" + this.forum_id + ", post_text=" + this.post_text + ", post_time=" + this.post_time + ", post_floor=" + this.post_floor + ", post_img_count=" + this.post_img_count + ", post_files=" + this.post_files + ", userid=" + this.userid + ", user_name=" + this.user_name + ", user_headimg=" + this.user_headimg + ", post_area=" + this.post_area + ", reply_type=" + this.reply_type + ", reply_id=" + this.reply_id + ", reply_floor=" + this.reply_floor + ", reply_userid=" + this.reply_userid + ", reply_username=" + this.reply_username + ", reply_content=" + this.reply_content + "]";
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.entity.PostReply
 * JD-Core Version:    0.6.0
 */