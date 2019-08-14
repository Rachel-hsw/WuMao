package com.withustudy.koudaizikao.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.withustudy.koudaizikao.db.DbService;
import com.withustudy.koudaizikao.entity.req.push.UserAnswers;
import com.withustudy.koudaizikao.tools.LogUtils;
import java.util.List;
import koudai.db.UserAnsDao;

public class PushBroadCastReceiver extends BroadcastReceiver
{
  private UserAnsDao dao;
  private DbService dbService;
  private String excerciseBrushId;
  private Context mContext;
  private String uid;

  private List<String> getCorrctAns(UserAnswers paramUserAnswers, String paramString)
  {
    String[] arrayOfString = paramString.split("^_^");
    int i;
    if ((arrayOfString != null) && (arrayOfString.length > 0))
      i = arrayOfString.length;
    for (int j = 0; ; j++)
    {
      if (j >= i)
        return null;
      arrayOfString[j];
    }
  }

  private List<String> getUserAnsStr(UserAnswers paramUserAnswers, String paramString)
  {
    String[] arrayOfString = paramString.split("^_^");
    int i;
    if ((arrayOfString != null) && (arrayOfString.length > 0))
      i = arrayOfString.length;
    for (int j = 0; ; j++)
    {
      if (j >= i)
        return null;
      String str = arrayOfString[j];
      paramUserAnswers.getUserAnswer().add(str);
    }
  }

  public void onReceive(Context paramContext, Intent paramIntent)
  {
    this.excerciseBrushId = ((String)paramIntent.getExtras().get("excerciseBrushId"));
    LogUtils.myLog("推送题目到服务器  excerciseBrushId=" + this.excerciseBrushId);
    Intent localIntent = new Intent(paramContext, PushExcerciseService.class);
    Bundle localBundle = new Bundle();
    localBundle.putString("excerciseBrushId", this.excerciseBrushId);
    localIntent.putExtras(localBundle);
    this.mContext.startService(localIntent);
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.service.PushBroadCastReceiver
 * JD-Core Version:    0.6.0
 */