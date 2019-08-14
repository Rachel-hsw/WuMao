package com.withustudy.koudaizikao.entity.content;

import com.withustudy.koudaizikao.entity.Forum;
import java.util.List;

public class ForumsContent
{
  private List<Forum> forums;
  private String msg;
  private String result;

  public List<Forum> getForums()
  {
    return this.forums;
  }

  public String getMsg()
  {
    return this.msg;
  }

  public String getResult()
  {
    return this.result;
  }

  public void setForums(List<Forum> paramList)
  {
    this.forums = paramList;
  }

  public void setMsg(String paramString)
  {
    this.msg = paramString;
  }

  public void setResult(String paramString)
  {
    this.result = paramString;
  }

  public String toString()
  {
    return "ForumsContent [result=" + this.result + ", forums=" + this.forums + "]";
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.entity.content.ForumsContent
 * JD-Core Version:    0.6.0
 */