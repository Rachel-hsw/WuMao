package com.withustudy.koudaizikao.entity;

import java.util.List;

public class SubjectErrorExercise
{
  private List<ErrorExercise> errorExercise;
  private Subject subject;

  public List<ErrorExercise> getErrorExercise()
  {
    return this.errorExercise;
  }

  public Subject getSubject()
  {
    return this.subject;
  }

  public void setErrorExercise(List<ErrorExercise> paramList)
  {
    this.errorExercise = paramList;
  }

  public void setSubject(Subject paramSubject)
  {
    this.subject = paramSubject;
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.entity.SubjectErrorExercise
 * JD-Core Version:    0.6.0
 */