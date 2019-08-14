package com.withustudy.koudaizikao.action;

import android.content.Context;
import com.android.http.RequestManager.RequestListener;

public abstract class AbsSimpleImplUrlFactory extends AbsBaseUrlConstructor
{
  public abstract void constructUrl(RequestManager.RequestListener paramRequestListener, Object paramObject, int paramInt);

  public void constructUrl(RequestManager.RequestListener paramRequestListener, String[] paramArrayOfString, int paramInt, Context paramContext)
  {
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.action.AbsSimpleImplUrlFactory
 * JD-Core Version:    0.6.0
 */