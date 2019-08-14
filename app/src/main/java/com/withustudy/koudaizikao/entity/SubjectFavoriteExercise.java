package com.withustudy.koudaizikao.entity;

import com.withustudy.koudaizikao.entity.req.FavoriteExercise;
import java.io.Serializable;
import java.util.List;

public class SubjectFavoriteExercise
  implements Serializable
{
  private List<FavoriteExercise> favoriteExercise;
  private Subject subject;

  public List<FavoriteExercise> getFavoriteExercise()
  {
    return this.favoriteExercise;
  }

  public Subject getSubject()
  {
    return this.subject;
  }

  public void setFavoriteExercise(List<FavoriteExercise> paramList)
  {
    this.favoriteExercise = paramList;
  }

  public void setSubject(Subject paramSubject)
  {
    this.subject = paramSubject;
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.entity.SubjectFavoriteExercise
 * JD-Core Version:    0.6.0
 */