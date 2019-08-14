package com.withustudy.koudaizikao.entity.req;

public class ReqComment
{
  protected String clientType;
  private Comment comment;
  private String exerciseId;
  protected String imei;
  protected String net;
  protected String versionName;

  public String getClientType()
  {
    return this.clientType;
  }

  public Comment getComment()
  {
    return this.comment;
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

  public String getVersionName()
  {
    return this.versionName;
  }

  public void setClientType(String paramString)
  {
    this.clientType = paramString;
  }

  public void setComment(Comment paramComment)
  {
    this.comment = paramComment;
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

  public void setVersionName(String paramString)
  {
    this.versionName = paramString;
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.entity.req.ReqComment
 * JD-Core Version:    0.6.0
 */