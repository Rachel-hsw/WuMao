package com.withustudy.koudaizikao.entity.req;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserAnswers
  implements Serializable
{
  protected String clientType;
  private List<String> correctAnswer = new ArrayList();
  private String exerciseId;
  protected String imei;
  protected String net;
  private String score;
  private List<String> userAnswer = new ArrayList();
  protected String versionName;

  public String getClientType()
  {
    return this.clientType;
  }

  public List<String> getCorrectAnswer()
  {
    return this.correctAnswer;
  }

  public String getExerciseId()
  {
    return this.exerciseId;
  }

  public String getImei()
  {
    return this.imei;
  }

  public String getNet()
  {
    return this.net;
  }

  public String getScore()
  {
    return this.score;
  }

  public List<String> getUserAnswer()
  {
    return this.userAnswer;
  }

  public String getVersionName()
  {
    return this.versionName;
  }

  public void setClientType(String paramString)
  {
    this.clientType = paramString;
  }

  public void setCorrectAnswer(List<String> paramList)
  {
    this.correctAnswer = paramList;
  }

  public void setExerciseId(String paramString)
  {
    this.exerciseId = paramString;
  }

  public void setImei(String paramString)
  {
    this.imei = paramString;
  }

  public void setNet(String paramString)
  {
    this.net = paramString;
  }

  public void setScore(String paramString)
  {
    this.score = paramString;
  }

  public void setUserAnswer(List<String> paramList)
  {
    this.userAnswer = paramList;
  }

  public void setVersionName(String paramString)
  {
    this.versionName = paramString;
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.entity.req.UserAnswers
 * JD-Core Version:    0.6.0
 */