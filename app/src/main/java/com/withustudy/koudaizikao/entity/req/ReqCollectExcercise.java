package com.withustudy.koudaizikao.entity.req;

public class ReqCollectExcercise
{
  private String category;
  protected String clientType;
  private FavoriteExercise favoriteExercise;
  protected String imei;
  protected String net;
  private String stemText;
  private UserSubject userSubject;
  protected String versionName;

  public String getCategory()
  {
    return this.category;
  }

  public String getClientType()
  {
    return this.clientType;
  }

  public FavoriteExercise getFavoriteExercise()
  {
    return this.favoriteExercise;
  }

  public String getImei()
  {
    return this.imei;
  }

  public String getNet()
  {
    return this.net;
  }

  public String getStemText()
  {
    return this.stemText;
  }

  public UserSubject getUserSubject()
  {
    return this.userSubject;
  }

  public String getVersionName()
  {
    return this.versionName;
  }

  public void setCategory(String paramString)
  {
    this.category = paramString;
  }

  public void setClientType(String paramString)
  {
    this.clientType = paramString;
  }

  public void setFavoriteExercise(FavoriteExercise paramFavoriteExercise)
  {
    this.favoriteExercise = paramFavoriteExercise;
  }

  public void setImei(String paramString)
  {
    this.imei = paramString;
  }

  public void setNet(String paramString)
  {
    this.net = paramString;
  }

  public void setStemText(String paramString)
  {
    this.stemText = paramString;
  }

  public void setUserSubject(UserSubject paramUserSubject)
  {
    this.userSubject = paramUserSubject;
  }

  public void setVersionName(String paramString)
  {
    this.versionName = paramString;
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.entity.req.ReqCollectExcercise
 * JD-Core Version:    0.6.0
 */