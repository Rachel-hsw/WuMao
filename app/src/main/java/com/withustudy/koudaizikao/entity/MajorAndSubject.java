package com.withustudy.koudaizikao.entity;

import com.withustudy.koudaizikao.entity.req.MajorUpLoad;
import java.util.List;

public class MajorAndSubject
{
  private MajorUpLoad major;
  private List<Subject> subject;

  public MajorUpLoad getMajor()
  {
    return this.major;
  }

  public List<Subject> getSubject()
  {
    return this.subject;
  }

  public void setMajor(MajorUpLoad paramMajorUpLoad)
  {
    this.major = paramMajorUpLoad;
  }

  public void setSubject(List<Subject> paramList)
  {
    this.subject = paramList;
  }

  public String toString()
  {
    return "MajorAndSubject [major=" + this.major + ", subject=" + this.subject + "]";
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.entity.MajorAndSubject
 * JD-Core Version:    0.6.0
 */