package com.withustudy.koudaizikao.entity;

import java.io.Serializable;
import java.util.List;

public class BrushExcerciseBean
  implements Serializable
{
  private List<Exercises> exercises;
  private boolean isEnd;

  public List<Exercises> getExercises()
  {
    return this.exercises;
  }

  public boolean isEnd()
  {
    return this.isEnd;
  }

  public void setEnd(boolean paramBoolean)
  {
    this.isEnd = paramBoolean;
  }

  public void setExercises(List<Exercises> paramList)
  {
    this.exercises = paramList;
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.entity.BrushExcerciseBean
 * JD-Core Version:    0.6.0
 */