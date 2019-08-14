package com.withustudy.koudaizikao.action.post;

import com.android.http.RequestManager;
import com.android.http.RequestManager.RequestListener;
import com.google.gson.Gson;
import com.withustudy.koudaizikao.action.AbsSimpleImplUrlFactory;
import com.withustudy.koudaizikao.action.UrlFactory;

public class AcKouDaiAddExerciseComment extends AbsSimpleImplUrlFactory
{
  public void constructUrl(RequestManager.RequestListener paramRequestListener, Object paramObject, int paramInt)
  {
    RequestManager.getInstance().postJson("http://m.kdzikao.com/exercise/add_exercise_comment", UrlFactory.getInstanceGson().toJson(paramObject), paramRequestListener, paramInt);
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.action.post.AcKouDaiAddExerciseComment
 * JD-Core Version:    0.6.0
 */