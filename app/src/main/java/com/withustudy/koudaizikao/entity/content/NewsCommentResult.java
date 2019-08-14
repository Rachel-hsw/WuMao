package com.withustudy.koudaizikao.entity.content;

public class NewsCommentResult
{
  private String commitId;
  private String newsStatus;

  public String getCommitId()
  {
    return this.commitId;
  }

  public String getNewsStatus()
  {
    return this.newsStatus;
  }

  public void setCommitId(String paramString)
  {
    this.commitId = paramString;
  }

  public void setNewsStatus(String paramString)
  {
    this.newsStatus = paramString;
  }

  public String toString()
  {
    return "NewsCommentResult [commitId=" + this.commitId + ", newsStatus=" + this.newsStatus + "]";
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.entity.content.NewsCommentResult
 * JD-Core Version:    0.6.0
 */