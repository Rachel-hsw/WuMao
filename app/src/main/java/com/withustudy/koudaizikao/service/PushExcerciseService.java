package com.withustudy.koudaizikao.service;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import com.android.http.RequestManager.RequestListener;
import com.google.gson.Gson;
import com.withustudy.koudaizikao.action.AbsSimpleImplUrlFactory;
import com.withustudy.koudaizikao.action.UrlFactory;
import com.withustudy.koudaizikao.entity.req.UserSubject;
import com.withustudy.koudaizikao.entity.req.push.PushState;
import com.withustudy.koudaizikao.entity.req.push.ReqPushAnser;
import com.withustudy.koudaizikao.entity.req.push.UserAnswers;
import com.withustudy.koudaizikao.tools.LogUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PushExcerciseService extends Service
  implements RequestManager.RequestListener
{
  private String excerciseBrushId;
  private int lastPosition = 0;
  private PushState pushState;
  private int pushStep;
  public HashMap<Integer, UserAnswers> resBrush = new HashMap();

  public IBinder onBind(Intent paramIntent)
  {
    return null;
  }

  public void onError(String paramString1, String paramString2, int paramInt)
  {
  }

  public void onRequest()
  {
  }

  public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2)
  {
    if (paramIntent != null)
    {
      LogUtils.myLog("onStartCommand");
      LogUtils.myLog("onStartCommand");
      LogUtils.myLog("onStartCommand");
      LogUtils.myLog("onStartCommand");
      this.excerciseBrushId = paramIntent.getExtras().getString("excerciseBrushId");
      LogUtils.myLog("启动服务excerciseBrushId=" + this.excerciseBrushId);
    }
    return super.onStartCommand(paramIntent, paramInt1, paramInt2);
  }

  public void onSuccess(String paramString1, Map<String, String> paramMap, String paramString2, int paramInt)
  {
    Gson localGson;
    if (paramString1 != null)
    {
      LogUtils.myLog("推送回复:: " + paramString1);
      localGson = UrlFactory.getInstanceGson();
    }
    switch (paramInt)
    {
    default:
      return;
    case 0:
    }
    try
    {
      this.pushState = ((PushState)localGson.fromJson(paramString1, PushState.class));
      if (this.pushState != null)
      {
        "OK".equals(this.pushState.getStatus());
        return;
      }
    }
    catch (Exception localException)
    {
      LogUtils.myLog("推送答案解析异常:: " + localException.getMessage());
      return;
    }
    LogUtils.myLog("推送异常:: ");
  }

  public void pushServer(UserSubject paramUserSubject)
  {
    new Thread(new Runnable(paramUserSubject)
    {
      public void run()
      {
        ArrayList localArrayList = new ArrayList();
        Iterator localIterator = PushExcerciseService.this.resBrush.keySet().iterator();
        while (true)
        {
          if (!localIterator.hasNext())
          {
            ReqPushAnser localReqPushAnser = new ReqPushAnser();
            localReqPushAnser.setUserAnswers(localArrayList);
            localReqPushAnser.setUserSubject(this.val$us);
            UrlFactory.getInstance().postUserAnserList().constructUrl(PushExcerciseService.this, localReqPushAnser, 0);
            return;
          }
          Integer localInteger = (Integer)localIterator.next();
          localArrayList.add((UserAnswers)PushExcerciseService.this.resBrush.get(localInteger));
        }
      }
    }).start();
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.service.PushExcerciseService
 * JD-Core Version:    0.6.0
 */