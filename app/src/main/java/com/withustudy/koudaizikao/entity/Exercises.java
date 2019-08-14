package com.withustudy.koudaizikao.entity;

import java.io.Serializable;
import java.util.List;

public class Exercises
  implements Serializable
{
  private String category;
  private Comments comments;
  private List<String> correctAnswer;
  private ErrorRate errorRate;
  private String exerciseId;
  private String exerciseNo;
  private Explain explain;
  private KpointDetail kpointDetail;
  private List<String> labels;
  private MyErrorRate myErrorRate;
  private List<Options> options;
  private double score;
  private Stem stem;
  private int totalNum;

  public String getCategory()
  {
    return this.category;
  }

  public Comments getComments()
  {
    return this.comments;
  }

  public List<String> getCorrectAnswer()
  {
    return this.correctAnswer;
  }

  public ErrorRate getErrorRate()
  {
    return this.errorRate;
  }

  public String getExerciseId()
  {
    return this.exerciseId;
  }

  public String getExerciseNo()
  {
    return this.exerciseNo;
  }

  public Explain getExplain()
  {
    return this.explain;
  }

  public KpointDetail getKpointDetail()
  {
    return this.kpointDetail;
  }

  public List<String> getLabels()
  {
    return this.labels;
  }

  public MyErrorRate getMyErrorRate()
  {
    return this.myErrorRate;
  }

  public List<Options> getOptions()
  {
    return this.options;
  }

  public double getScore()
  {
    return this.score;
  }

  public Stem getStem()
  {
    return this.stem;
  }

  public int getTotalNum()
  {
    return this.totalNum;
  }

  public void setCategory(String paramString)
  {
    this.category = paramString;
  }

  public void setComments(Comments paramComments)
  {
    this.comments = paramComments;
  }

  public void setCorrectAnswer(List<String> paramList)
  {
    this.correctAnswer = paramList;
  }

  public void setErrorRate(ErrorRate paramErrorRate)
  {
    this.errorRate = paramErrorRate;
  }

  public void setExerciseId(String paramString)
  {
    this.exerciseId = paramString;
  }

  public void setExerciseNo(String paramString)
  {
    this.exerciseNo = paramString;
  }

  public void setExplain(Explain paramExplain)
  {
    this.explain = paramExplain;
  }

  public void setKpointDetail(KpointDetail paramKpointDetail)
  {
    this.kpointDetail = paramKpointDetail;
  }

  public void setLabels(List<String> paramList)
  {
    this.labels = paramList;
  }

  public void setMyErrorRate(MyErrorRate paramMyErrorRate)
  {
    this.myErrorRate = paramMyErrorRate;
  }

  public void setOptions(List<Options> paramList)
  {
    this.options = paramList;
  }

  public void setScore(double paramDouble)
  {
    this.score = paramDouble;
  }

  public void setStem(Stem paramStem)
  {
    this.stem = paramStem;
  }

  public void setTotalNum(int paramInt)
  {
    this.totalNum = paramInt;
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.entity.Exercises
 * JD-Core Version:    0.6.0
 */