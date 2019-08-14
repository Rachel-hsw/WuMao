package com.withustudy.koudaizikao.entity.content;

import com.withustudy.koudaizikao.entity.Province;
import java.io.Serializable;
import java.util.List;

public class ProvMajorsContent
  implements Serializable
{
  private List<Province> provMajors;

  public List<Province> getProvMajors()
  {
    return this.provMajors;
  }

  public void setProvMajors(List<Province> paramList)
  {
    this.provMajors = paramList;
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.entity.content.ProvMajorsContent
 * JD-Core Version:    0.6.0
 */