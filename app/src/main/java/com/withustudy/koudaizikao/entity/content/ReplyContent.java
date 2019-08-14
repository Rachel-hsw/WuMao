package com.withustudy.koudaizikao.entity.content;

import com.withustudy.koudaizikao.entity.PostReply;
import java.util.List;

public class ReplyContent
{
  private String msg;
  private List<PostReply> posts;
  private String result;

  public String getMsg()
  {
    return this.msg;
  }

  public List<PostReply> getPosts()
  {
    return this.posts;
  }

  public String getResult()
  {
    return this.result;
  }

  public void setMsg(String paramString)
  {
    this.msg = paramString;
  }

  public void setPosts(List<PostReply> paramList)
  {
    this.posts = paramList;
  }

  public void setResult(String paramString)
  {
    this.result = paramString;
  }

  public String toString()
  {
    return "ReplyContent [result=" + this.result + ", msg=" + this.msg + ", posts=" + this.posts + "]";
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.entity.content.ReplyContent
 * JD-Core Version:    0.6.0
 */