package com.withustudy.koudaizikao.entity;

public class ExerciseSummary
{
  private String category;
  private String exerciseDesc;
  private String exerciseId;
  private Kpoint kpoint;
  private String time;

  public String getCategory()
  {
    return this.category;
  }

  public String getExerciseDesc()
  {
    return this.exerciseDesc;
  }

  public String getExerciseId()
  {
    return this.exerciseId;
  }

  public Kpoint getKpoint()
  {
    return this.kpoint;
  }

  public String getTime()
  {
    return this.time;
  }

  public void setCategory(String paramString)
  {
    this.category = paramString;
  }

  public void setExerciseDesc(String paramString)
  {
    this.exerciseDesc = paramString;
  }

  public void setExerciseId(String paramString)
  {
    this.exerciseId = paramString;
  }

  public void setKpoint(Kpoint paramKpoint)
  {
    this.kpoint = paramKpoint;
  }

  public void setTime(String paramString)
  {
    this.time = paramString;
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.entity.ExerciseSummary
 * JD-Core Version:    0.6.0
 */