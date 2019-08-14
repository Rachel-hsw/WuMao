package com.withustudy.koudaizikao.entity;

import java.util.List;

public class ErrorSummaryByChapter
{
  private List<ChapterErrorSummary> chapterErrorSummary;
  private String errorExerciseNum;
  private Subject subject;

  public List<ChapterErrorSummary> getChapterErrorSummary()
  {
    return this.chapterErrorSummary;
  }

  public String getErrorExerciseNum()
  {
    return this.errorExerciseNum;
  }

  public Subject getSubject()
  {
    return this.subject;
  }

  public void setChapterErrorSummary(List<ChapterErrorSummary> paramList)
  {
    this.chapterErrorSummary = paramList;
  }

  public void setErrorExerciseNum(String paramString)
  {
    this.errorExerciseNum = paramString;
  }

  public void setSubject(Subject paramSubject)
  {
    this.subject = paramSubject;
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.entity.ErrorSummaryByChapter
 * JD-Core Version:    0.6.0
 */