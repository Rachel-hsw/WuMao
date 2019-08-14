package com.withustudy.koudaizikao.entity.content;

import com.withustudy.koudaizikao.entity.Post;

public class PostSingleContent
{
  private String msg;
  private String result;
  private Post topic;

  public String getMsg()
  {
    return this.msg;
  }

  public String getResult()
  {
    return this.result;
  }

  public Post getTopic()
  {
    return this.topic;
  }

  public void setMsg(String paramString)
  {
    this.msg = paramString;
  }

  public void setResult(String paramString)
  {
    this.result = paramString;
  }

  public void setTopic(Post paramPost)
  {
    this.topic = paramPost;
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.entity.content.PostSingleContent
 * JD-Core Version:    0.6.0
 */