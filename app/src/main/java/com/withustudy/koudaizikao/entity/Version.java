package com.withustudy.koudaizikao.entity;

import java.io.Serializable;
import java.util.List;

public class Version
  implements Serializable
{
  private String downloadUrl;
  private String remark;
  private String status;
  private List<String> updateInfo;
  private String versionCode;
  private String versionName;

  public String getDownloadUrl()
  {
    return this.downloadUrl;
  }

  public String getRemark()
  {
    return this.remark;
  }

  public String getStatus()
  {
    return this.status;
  }

  public List<String> getUpdateInfo()
  {
    return this.updateInfo;
  }

  public String getVersionCode()
  {
    return this.versionCode;
  }

  public String getVersionName()
  {
    return this.versionName;
  }

  public void setDownloadUrl(String paramString)
  {
    this.downloadUrl = paramString;
  }

  public void setRemark(String paramString)
  {
    this.remark = paramString;
  }

  public void setStatus(String paramString)
  {
    this.status = paramString;
  }

  public void setUpdateInfo(List<String> paramList)
  {
    this.updateInfo = paramList;
  }

  public void setVersionCode(String paramString)
  {
    this.versionCode = paramString;
  }

  public void setVersionName(String paramString)
  {
    this.versionName = paramString;
  }

  public String toString()
  {
    return "Version [status=" + this.status + ", remark=" + this.remark + ", versionCode=" + this.versionCode + ", versionName=" + this.versionName + ", downloadUrl=" + this.downloadUrl + ", updateInfo=" + this.updateInfo + "]";
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.entity.Version
 * JD-Core Version:    0.6.0
 */