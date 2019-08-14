package com.withustudy.koudaizikao.activity;

import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.withustudy.koudaizikao.base.AbsBaseActivity;

public class VideoWebViewActivity extends AbsBaseActivity
{
  private WebView mWebView;

  protected void bindData()
  {
    WebSettings localWebSettings = this.mWebView.getSettings();
    localWebSettings.setJavaScriptEnabled(true);
    localWebSettings.setAllowFileAccess(true);
    localWebSettings.setBuiltInZoomControls(true);
    this.mWebView.loadUrl("http://m.kdzikao.com/video_server/list.html");
    this.mWebView.setWebViewClient(new webViewClient(null));
  }

  protected void initData()
  {
  }

  protected void initListener()
  {
  }

  protected void initView()
  {
    this.mWebView = ((WebView)findViewById(2131100016));
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if ((this.mWebView.canGoBack()) && (paramInt == 4))
    {
      this.mWebView.goBack();
      return true;
    }
    return super.onKeyDown(paramInt, paramKeyEvent);
  }

  protected void setContentView()
  {
    setContentView(2130903088);
  }

  private class webViewClient extends WebViewClient
  {
    private webViewClient()
    {
    }

    public boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString)
    {
      paramWebView.loadUrl(paramString);
      return true;
    }
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.activity.VideoWebViewActivity
 * JD-Core Version:    0.6.0
 */