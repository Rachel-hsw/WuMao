package com.withustudy.koudaizikao.custom.pullrefersh;

public enum SwipyRefreshLayoutDirection
{
  private int mValue;

  static
  {
    BOTTOM = new SwipyRefreshLayoutDirection("BOTTOM", 1, 1);
    BOTH = new SwipyRefreshLayoutDirection("BOTH", 2, 2);
    SwipyRefreshLayoutDirection[] arrayOfSwipyRefreshLayoutDirection = new SwipyRefreshLayoutDirection[3];
    arrayOfSwipyRefreshLayoutDirection[0] = TOP;
    arrayOfSwipyRefreshLayoutDirection[1] = BOTTOM;
    arrayOfSwipyRefreshLayoutDirection[2] = BOTH;
    ENUM$VALUES = arrayOfSwipyRefreshLayoutDirection;
  }

  private SwipyRefreshLayoutDirection(int arg3)
  {
    int j;
    this.mValue = j;
  }

  public static SwipyRefreshLayoutDirection getFromInt(int paramInt)
  {
    SwipyRefreshLayoutDirection[] arrayOfSwipyRefreshLayoutDirection = values();
    int i = arrayOfSwipyRefreshLayoutDirection.length;
    for (int j = 0; ; j++)
    {
      SwipyRefreshLayoutDirection localSwipyRefreshLayoutDirection;
      if (j >= i)
        localSwipyRefreshLayoutDirection = BOTH;
      do
      {
        return localSwipyRefreshLayoutDirection;
        localSwipyRefreshLayoutDirection = arrayOfSwipyRefreshLayoutDirection[j];
      }
      while (localSwipyRefreshLayoutDirection.mValue == paramInt);
    }
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.custom.pullrefersh.SwipyRefreshLayoutDirection
 * JD-Core Version:    0.6.0
 */