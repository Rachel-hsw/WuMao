package com.withustudy.koudaizikao.entity.content;

import com.withustudy.koudaizikao.entity.News;
import java.util.List;

public class NewsListContent
{
  private List<News> basicNewsList;

  public List<News> getBasicNewsList()
  {
    return this.basicNewsList;
  }

  public void setBasicNewsList(List<News> paramList)
  {
    this.basicNewsList = paramList;
  }

  public String toString()
  {
    return "NewsListContent [basicNewsList=" + this.basicNewsList + "]";
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.entity.content.NewsListContent
 * JD-Core Version:    0.6.0
 */