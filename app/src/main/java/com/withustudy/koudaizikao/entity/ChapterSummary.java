package com.withustudy.koudaizikao.entity;

import java.util.List;

public class ChapterSummary
{
  private Chapter chapter;
  private int doneExerciseNum;
  private String graspLevel;
  private List<SectionSummary> sectionSummary;
  private int totalExerciseNum;

  public Chapter getChapter()
  {
    return this.chapter;
  }

  public int getDoneExerciseNum()
  {
    return this.doneExerciseNum;
  }

  public String getGraspLevel()
  {
    return this.graspLevel;
  }

  public List<SectionSummary> getSectionSummary()
  {
    return this.sectionSummary;
  }

  public int getTotalExerciseNum()
  {
    return this.totalExerciseNum;
  }

  public void setChapter(Chapter paramChapter)
  {
    this.chapter = paramChapter;
  }

  public void setDoneExerciseNum(int paramInt)
  {
    this.doneExerciseNum = paramInt;
  }

  public void setGraspLevel(String paramString)
  {
    this.graspLevel = paramString;
  }

  public void setSectionSummary(List<SectionSummary> paramList)
  {
    this.sectionSummary = paramList;
  }

  public void setTotalExerciseNum(int paramInt)
  {
    this.totalExerciseNum = paramInt;
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.entity.ChapterSummary
 * JD-Core Version:    0.6.0
 */