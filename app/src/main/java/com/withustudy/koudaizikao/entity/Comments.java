package com.withustudy.koudaizikao.entity;

import com.withustudy.koudaizikao.entity.req.Comment;
import java.io.Serializable;
import java.util.List;

public class Comments
  implements Serializable
{
  private List<Comment> comment;
  private int totalNum;

  public List<Comment> getComment()
  {
    return this.comment;
  }

  public int getTotalNum()
  {
    return this.totalNum;
  }

  public void setComment(List<Comment> paramList)
  {
    this.comment = paramList;
  }

  public void setTotalNum(int paramInt)
  {
    this.totalNum = paramInt;
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.entity.Comments
 * JD-Core Version:    0.6.0
 */