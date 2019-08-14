package com.withustudy.koudaizikao.entity;

import java.util.List;

public class ChapterSectionListBean
{
  private List<ChapterSummary> chapterSummary;
  private boolean hasLastFlag;

  public List<ChapterSummary> getChapterSummary()
  {
    return this.chapterSummary;
  }

  public boolean isHasLastFlag()
  {
    return this.hasLastFlag;
  }

  public void setChapterSummary(List<ChapterSummary> paramList)
  {
    this.chapterSummary = paramList;
  }

  public void setHasLastFlag(boolean paramBoolean)
  {
    this.hasLastFlag = paramBoolean;
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.entity.ChapterSectionListBean
 * JD-Core Version:    0.6.0
 */