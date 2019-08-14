package com.withustudy.koudaizikao.web;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import java.util.Map;

public class VideoEnabledWebView extends WebView
{
  private boolean addedJavascriptInterface = false;
  private VideoEnabledWebChromeClient videoEnabledWebChromeClient;

  public VideoEnabledWebView(Context paramContext)
  {
    super(paramContext);
  }

  public VideoEnabledWebView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public VideoEnabledWebView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }

  private void addJavascriptInterface()
  {
    if (!this.addedJavascriptInterface)
    {
      addJavascriptInterface(new JavascriptInterface(), "_VideoEnabledWebView");
      this.addedJavascriptInterface = true;
    }
  }

  public boolean isVideoFullscreen()
  {
    return (this.videoEnabledWebChromeClient != null) && (this.videoEnabledWebChromeClient.isVideoFullscreen());
  }

  public void loadData(String paramString1, String paramString2, String paramString3)
  {
    addJavascriptInterface();
    super.loadData(paramString1, paramString2, paramString3);
  }

  public void loadDataWithBaseURL(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
  {
    addJavascriptInterface();
    super.loadDataWithBaseURL(paramString1, paramString2, paramString3, paramString4, paramString5);
  }

  public void loadUrl(String paramString)
  {
    addJavascriptInterface();
    super.loadUrl(paramString);
  }

  public void loadUrl(String paramString, Map<String, String> paramMap)
  {
    addJavascriptInterface();
    super.loadUrl(paramString, paramMap);
  }

  @SuppressLint({"SetJavaScriptEnabled"})
  public void setWebChromeClient(WebChromeClient paramWebChromeClient)
  {
    getSettings().setJavaScriptEnabled(true);
    if ((paramWebChromeClient instanceof VideoEnabledWebChromeClient))
      this.videoEnabledWebChromeClient = ((VideoEnabledWebChromeClient)paramWebChromeClient);
    super.setWebChromeClient(paramWebChromeClient);
  }

  public class JavascriptInterface
  {
    public JavascriptInterface()
    {
    }

    @JavascriptInterface
    public void notifyVideoEnd()
    {
      Log.d("___", "GOT IT");
      new Handler(Looper.getMainLooper()).post(new Runnable()
      {
        public void run()
        {
          if (VideoEnabledWebView.this.videoEnabledWebChromeClient != null)
            VideoEnabledWebView.this.videoEnabledWebChromeClient.onHideCustomView();
        }
      });
    }
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.web.VideoEnabledWebView
 * JD-Core Version:    0.6.0
 */