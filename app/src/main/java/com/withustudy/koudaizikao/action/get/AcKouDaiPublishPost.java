package com.withustudy.koudaizikao.action.get;

import android.content.Context;
import com.android.http.RequestManager;
import com.android.http.RequestManager.RequestListener;
import com.withustudy.koudaizikao.action.AbsBaseUrlConstructor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AcKouDaiPublishPost extends AbsBaseUrlConstructor
{
  public void constructUrl(RequestManager.RequestListener paramRequestListener, String[] paramArrayOfString, int paramInt, Context paramContext)
  {
    super.constructUrl(paramRequestListener, paramArrayOfString, paramInt, paramContext);
    HashMap localHashMap = new HashMap();
    localHashMap.put("userid", paramArrayOfString[0]);
    if (!paramArrayOfString[1].equals(""))
      localHashMap.put("title", paramArrayOfString[1]);
    localHashMap.put("content", paramArrayOfString[2]);
    localHashMap.put("forum_id", paramArrayOfString[3]);
    if (!paramArrayOfString[4].equals(""))
      localHashMap.put("filenames", paramArrayOfString[4]);
    localHashMap.put("originate", "1");
    localHashMap.put("versionName", (String)this.baseList.get(0));
    if (!((String)this.baseList.get(1)).equals(""))
      localHashMap.put("net", (String)this.baseList.get(1));
    localHashMap.put("clientType", (String)this.baseList.get(2));
    localHashMap.put("imei", (String)this.baseList.get(3));
    RequestManager.getInstance().get("http://bbs.kdzikao.com/forumTopicApi/add/bbs.page", localHashMap, paramRequestListener, paramInt);
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.action.get.AcKouDaiPublishPost
 * JD-Core Version:    0.6.0
 */