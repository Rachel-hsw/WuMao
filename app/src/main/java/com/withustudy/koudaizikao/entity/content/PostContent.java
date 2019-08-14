package com.withustudy.koudaizikao.entity.content;

import com.withustudy.koudaizikao.entity.Forum;
import com.withustudy.koudaizikao.entity.Post;
import java.util.List;

public class PostContent
{
  private Forum forum;
  private String msg;
  private String result;
  private List<Post> topTopics;
  private List<Post> topics;

  public Forum getForum()
  {
    return this.forum;
  }

  public String getMsg()
  {
    return this.msg;
  }

  public String getResult()
  {
    return this.result;
  }

  public List<Post> getTopTopics()
  {
    return this.topTopics;
  }

  public List<Post> getTopics()
  {
    return this.topics;
  }

  public void setForum(Forum paramForum)
  {
    this.forum = paramForum;
  }

  public void setMsg(String paramString)
  {
    this.msg = paramString;
  }

  public void setResult(String paramString)
  {
    this.result = paramString;
  }

  public void setTopTopics(List<Post> paramList)
  {
    this.topTopics = paramList;
  }

  public void setTopics(List<Post> paramList)
  {
    this.topics = paramList;
  }

  public String toString()
  {
    return "PostContent [result=" + this.result + ", msg=" + this.msg + ", topics=" + this.topics + ", topTopics=" + this.topTopics + ", forum=" + this.forum + "]";
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.entity.content.PostContent
 * JD-Core Version:    0.6.0
 */