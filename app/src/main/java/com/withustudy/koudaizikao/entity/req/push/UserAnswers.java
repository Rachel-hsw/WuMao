package com.withustudy.koudaizikao.entity.req.push;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserAnswers
  implements Serializable
{
  private long brushTime;
  private String exerciseId;
  private boolean isCorrect;
  private String kpointId;
  private List<String> userAnswer = new ArrayList();
  private double userScore;

  public long getBrushTime()
  {
    return this.brushTime;
  }

  public String getExerciseId()
  {
    return this.exerciseId;
  }

  public String getKpointId()
  {
    return this.kpointId;
  }

  public List<String> getUserAnswer()
  {
    return this.userAnswer;
  }

  public double getUserScore()
  {
    return this.userScore;
  }

  public boolean isCorrect()
  {
    return this.isCorrect;
  }

  public void setBrushTime(long paramLong)
  {
    this.brushTime = paramLong;
  }

  public void setCorrect(boolean paramBoolean)
  {
    this.isCorrect = paramBoolean;
  }

  public void setExerciseId(String paramString)
  {
    this.exerciseId = paramString;
  }

  public void setKpointId(String paramString)
  {
    this.kpointId = paramString;
  }

  public void setUserAnswer(List<String> paramList)
  {
    this.userAnswer = paramList;
  }

  public void setUserScore(double paramDouble)
  {
    this.userScore = paramDouble;
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.entity.req.push.UserAnswers
 * JD-Core Version:    0.6.0
 */