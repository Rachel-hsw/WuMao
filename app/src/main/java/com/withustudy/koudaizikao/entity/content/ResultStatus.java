package com.withustudy.koudaizikao.entity.content;

public class ResultStatus
{
  private String reason;
  private String status;

  public String getReason()
  {
    return this.reason;
  }

  public String getStatus()
  {
    return this.status;
  }

  public void setReason(String paramString)
  {
    this.reason = paramString;
  }

  public void setStatus(String paramString)
  {
    this.status = paramString;
  }

  public String toString()
  {
    return "ResultStatus [status=" + this.status + ", reason=" + this.reason + "]";
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.entity.content.ResultStatus
 * JD-Core Version:    0.6.0
 */