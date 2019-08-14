package com.withustudy.koudaizikao.entity;

import java.util.List;

public class ChapterKpointSummary
{
  private Chapter chapter;
  private String learnedKpointNum;
  private List<SectionKpointSummary> sectionKpointSummary;
  private String totalKpointNum;

  public Chapter getChapter()
  {
    return this.chapter;
  }

  public String getLearnedKpointNum()
  {
    return this.learnedKpointNum;
  }

  public List<SectionKpointSummary> getSectionKpointSummary()
  {
    return this.sectionKpointSummary;
  }

  public String getTotalKpointNum()
  {
    return this.totalKpointNum;
  }

  public void setChapter(Chapter paramChapter)
  {
    this.chapter = paramChapter;
  }

  public void setLearnedKpointNum(String paramString)
  {
    this.learnedKpointNum = paramString;
  }

  public void setSectionKpointSummary(List<SectionKpointSummary> paramList)
  {
    this.sectionKpointSummary = paramList;
  }

  public void setTotalKpointNum(String paramString)
  {
    this.totalKpointNum = paramString;
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.entity.ChapterKpointSummary
 * JD-Core Version:    0.6.0
 */