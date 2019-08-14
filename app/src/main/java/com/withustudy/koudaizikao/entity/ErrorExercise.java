package com.withustudy.koudaizikao.entity;

import java.io.Serializable;

public class ErrorExercise
  implements Serializable
{
  private Chapter chapter;
  private String exerciseId;
  private long time;

  public Chapter getChapter()
  {
    return this.chapter;
  }

  public String getExerciseId()
  {
    return this.exerciseId;
  }

  public long getTime()
  {
    return this.time;
  }

  public void setChapter(Chapter paramChapter)
  {
    this.chapter = paramChapter;
  }

  public void setExerciseId(String paramString)
  {
    this.exerciseId = paramString;
  }

  public void setTime(long paramLong)
  {
    this.time = paramLong;
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.entity.ErrorExercise
 * JD-Core Version:    0.6.0
 */