package com.withustudy.koudaizikao.entity;

import com.withustudy.koudaizikao.entity.req.ExamDate;
import com.withustudy.koudaizikao.entity.req.UserSubject;
import java.io.Serializable;

public class ReqOldExam
  implements Serializable
{
  private ExamDate examDate;
  private UserSubject userSubject;

  public ExamDate getExamDate()
  {
    return this.examDate;
  }

  public UserSubject getUserSubject()
  {
    return this.userSubject;
  }

  public void setExamDate(ExamDate paramExamDate)
  {
    this.examDate = paramExamDate;
  }

  public void setUserSubject(UserSubject paramUserSubject)
  {
    this.userSubject = paramUserSubject;
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.entity.ReqOldExam
 * JD-Core Version:    0.6.0
 */