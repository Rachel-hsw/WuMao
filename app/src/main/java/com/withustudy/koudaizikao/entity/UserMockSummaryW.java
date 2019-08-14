package com.withustudy.koudaizikao.entity;

import java.io.Serializable;
import java.util.List;

public class UserMockSummaryW
  implements Serializable
{
  private int myRankPos;
  private List<UserMockSummary> userMockSummary;

  public int getMyRankPos()
  {
    return this.myRankPos;
  }

  public List<UserMockSummary> getUserMockSummary()
  {
    return this.userMockSummary;
  }

  public void setMyRankPos(int paramInt)
  {
    this.myRankPos = paramInt;
  }

  public void setUserMockSummary(List<UserMockSummary> paramList)
  {
    this.userMockSummary = paramList;
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.entity.UserMockSummaryW
 * JD-Core Version:    0.6.0
 */