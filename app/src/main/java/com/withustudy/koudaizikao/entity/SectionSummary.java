package com.withustudy.koudaizikao.entity;

public class SectionSummary
{
  private String doneExerciseNum;
  private String graspLevel;
  private Section section;
  private String totalExerciseNum;

  public String getDoneExerciseNum()
  {
    return this.doneExerciseNum;
  }

  public String getGraspLevel()
  {
    return this.graspLevel;
  }

  public Section getSection()
  {
    return this.section;
  }

  public String getTotalExerciseNum()
  {
    return this.totalExerciseNum;
  }

  public void setDoneExerciseNum(String paramString)
  {
    this.doneExerciseNum = paramString;
  }

  public void setGraspLevel(String paramString)
  {
    this.graspLevel = paramString;
  }

  public void setSection(Section paramSection)
  {
    this.section = paramSection;
  }

  public void setTotalExerciseNum(String paramString)
  {
    this.totalExerciseNum = paramString;
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.entity.SectionSummary
 * JD-Core Version:    0.6.0
 */