package com.withustudy.koudaizikao.action;

import android.content.Context;
import com.android.http.RequestManager.RequestListener;
import com.withustudy.koudaizikao.config.KouDaiSP;
import com.withustudy.koudaizikao.tools.ToolsUtils;
import java.util.ArrayList;
import java.util.List;

public abstract class AbsBaseUrlConstructor
{
  protected static final String DOMAIN_BBS_NAME = "http://bbs.kdzikao.com";
  protected static final String DOMAIN_NAME = "http://m.kdzikao.com";
  public static final String SHARE_APP = "http://share.kdzikao.com/app/share.page";
  public static final String SHARE_NEWS = "http://share.kdzikao.com/news/share.page?article_id=";
  public static final String SHARE_POST = "http://share.kdzikao.com/bbs/share.page?topic_id=";
  protected List<String> baseList;

  public void constructUrl(RequestManager.RequestListener paramRequestListener, String[] paramArrayOfString, int paramInt, Context paramContext)
  {
    this.baseList = new ArrayList();
    this.baseList.add(KouDaiSP.getInstance(paramContext).getVersionName());
    if (ToolsUtils.getNetworkType(paramContext) == 1)
      this.baseList.add("WIFI");
    while (true)
    {
      this.baseList.add(ToolsUtils.getSDK());
      this.baseList.add(ToolsUtils.getImei(paramContext));
      return;
      if (ToolsUtils.getNetworkType(paramContext) == 2)
      {
        this.baseList.add("WAP");
        continue;
      }
      if (ToolsUtils.getNetworkType(paramContext) == 3)
      {
        this.baseList.add("NET");
        continue;
      }
      this.baseList.add("");
    }
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.action.AbsBaseUrlConstructor
 * JD-Core Version:    0.6.0
 */