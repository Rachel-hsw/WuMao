package com.withustudy.koudaizikao.entity.content;

import com.withustudy.koudaizikao.entity.InfoComment;
import java.util.List;

public class NewsDetailContent
{
  private String author;
  private List<InfoComment> commentList;
  private String content;

  public String getAuthor()
  {
    return this.author;
  }

  public List<InfoComment> getCommentList()
  {
    return this.commentList;
  }

  public String getContent()
  {
    return this.content;
  }

  public void setAuthor(String paramString)
  {
    this.author = paramString;
  }

  public void setCommentList(List<InfoComment> paramList)
  {
    this.commentList = paramList;
  }

  public void setContent(String paramString)
  {
    this.content = paramString;
  }

  public String toString()
  {
    return "NewsDetail [author=" + this.author + ", content=" + this.content + ", commentList=" + this.commentList + "]";
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.entity.content.NewsDetailContent
 * JD-Core Version:    0.6.0
 */