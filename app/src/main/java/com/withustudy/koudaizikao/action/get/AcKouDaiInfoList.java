package com.withustudy.koudaizikao.action.get;

import android.content.Context;
import com.android.http.RequestManager;
import com.android.http.RequestManager.RequestListener;
import com.withustudy.koudaizikao.action.AbsBaseUrlConstructor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AcKouDaiInfoList extends AbsBaseUrlConstructor
{
  public void constructUrl(RequestManager.RequestListener paramRequestListener, String[] paramArrayOfString, int paramInt, Context paramContext)
  {
    super.constructUrl(paramRequestListener, paramArrayOfString, paramInt, paramContext);
    HashMap localHashMap = new HashMap();
    localHashMap.put("newsType", paramArrayOfString[0]);
    localHashMap.put("lastIndex", paramArrayOfString[1]);
    if (!paramArrayOfString[2].equals(""))
      localHashMap.put("provinceId", paramArrayOfString[2]);
    if (!paramArrayOfString[3].equals(""))
      localHashMap.put("majorId", paramArrayOfString[3]);
    if (!paramArrayOfString[4].equals(""))
      localHashMap.put("uid", paramArrayOfString[4]);
    localHashMap.put("versionName", (String)this.baseList.get(0));
    if (!((String)this.baseList.get(1)).equals(""))
      localHashMap.put("net", (String)this.baseList.get(1));
    localHashMap.put("clientType", (String)this.baseList.get(2));
    localHashMap.put("imei", (String)this.baseList.get(3));
    RequestManager.getInstance().get("http://m.kdzikao.com/news/get_basic_news", localHashMap, paramRequestListener, paramInt);
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.action.get.AcKouDaiInfoList
 * JD-Core Version:    0.6.0
 */