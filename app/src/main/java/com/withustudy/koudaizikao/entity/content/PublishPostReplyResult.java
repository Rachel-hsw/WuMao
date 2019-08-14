package com.withustudy.koudaizikao.entity.content;

import com.withustudy.koudaizikao.entity.PostReply;

public class PublishPostReplyResult
{
  private PostReply post;
  private String result;

  public PostReply getPost()
  {
    return this.post;
  }

  public String getResult()
  {
    return this.result;
  }

  public void setPost(PostReply paramPostReply)
  {
    this.post = paramPostReply;
  }

  public void setResult(String paramString)
  {
    this.result = paramString;
  }

  public String toString()
  {
    return "PublishPostReplyResult [post=" + this.post + ", result=" + this.result + "]";
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.entity.content.PublishPostReplyResult
 * JD-Core Version:    0.6.0
 */