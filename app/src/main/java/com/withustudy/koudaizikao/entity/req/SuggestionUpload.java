package com.withustudy.koudaizikao.entity.req;

public class SuggestionUpload
{
  protected String clientType;
  private String content;
  protected String imei;
  protected String net;
  private String uid;
  protected String versionName;

  public String getClientType()
  {
    return this.clientType;
  }

  public String getContent()
  {
    return this.content;
  }

  public String getImei()
  {
    return this.imei;
  }

  public String getNet()
  {
    return this.net;
  }

  public String getUid()
  {
    return this.uid;
  }

  public String getVersionName()
  {
    return this.versionName;
  }

  public void setClientType(String paramString)
  {
    this.clientType = paramString;
  }

  public void setContent(String paramString)
  {
    this.content = paramString;
  }

  public void setImei(String paramString)
  {
    this.imei = paramString;
  }

  public void setNet(String paramString)
  {
    this.net = paramString;
  }

  public void setUid(String paramString)
  {
    this.uid = paramString;
  }

  public void setVersionName(String paramString)
  {
    this.versionName = paramString;
  }

  public String toString()
  {
    return "SuggestionUpload [uid=" + this.uid + ", versionName=" + this.versionName + ", content=" + this.content + "]";
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.entity.req.SuggestionUpload
 * JD-Core Version:    0.6.0
 */