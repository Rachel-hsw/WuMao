package com.withustudy.koudaizikao.action.get;

import android.content.Context;
import com.android.http.RequestManager;
import com.android.http.RequestManager.RequestListener;
import com.withustudy.koudaizikao.action.AbsBaseUrlConstructor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AcKouDaiErrorExerciseBasket extends AbsBaseUrlConstructor
{
  public void constructUrl(RequestManager.RequestListener paramRequestListener, String[] paramArrayOfString, int paramInt, Context paramContext)
  {
    super.constructUrl(paramRequestListener, paramArrayOfString, paramInt, paramContext);
    HashMap localHashMap = new HashMap();
    localHashMap.put("uId", paramArrayOfString[0]);
    localHashMap.put("sortType", paramArrayOfString[1]);
    localHashMap.put("versionName", (String)this.baseList.get(0));
    if (!((String)this.baseList.get(1)).equals(""))
      localHashMap.put("net", (String)this.baseList.get(1));
    localHashMap.put("clientType", (String)this.baseList.get(2));
    localHashMap.put("imei", (String)this.baseList.get(3));
    RequestManager.getInstance().get("http://m.kdzikao.com/exercise/error_exercise_basket", localHashMap, paramRequestListener, paramInt);
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.action.get.AcKouDaiErrorExerciseBasket
 * JD-Core Version:    0.6.0
 */