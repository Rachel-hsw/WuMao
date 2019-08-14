package com.withustudy.koudaizikao.activity;

import android.app.Activity;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.webkit.WebView;
import com.withustudy.koudaizikao.web.VideoEnabledWebChromeClient;
import com.withustudy.koudaizikao.web.VideoEnabledWebChromeClient.ToggledFullscreenCallback;
import com.withustudy.koudaizikao.web.VideoEnabledWebView;

public class ExampleActivity extends Activity
{
  private VideoEnabledWebChromeClient webChromeClient;
  private VideoEnabledWebView webView;

  public void onBackPressed()
  {
    if (!this.webChromeClient.onBackPressed())
    {
      if (this.webView.canGoBack())
        this.webView.goBack();
    }
    else
      return;
    super.onBackPressed();
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903053);
    this.webView = ((VideoEnabledWebView)findViewById(2131099772));
    this.webChromeClient = new VideoEnabledWebChromeClient(findViewById(2131099771), (ViewGroup)findViewById(2131099773), getLayoutInflater().inflate(2130903200, null), this.webView)
    {
      public void onProgressChanged(WebView paramWebView, int paramInt)
      {
      }
    };
    this.webChromeClient.setOnToggledFullscreen(new ToggledFullscreenCallback()
    {
      public void toggledFullscreen(boolean paramBoolean)
      {
        if (paramBoolean)
        {
          WindowManager.LayoutParams localLayoutParams2 = ExampleActivity.this.getWindow().getAttributes();
          localLayoutParams2.flags = (0x400 | localLayoutParams2.flags);
          localLayoutParams2.flags = (0x80 | localLayoutParams2.flags);
          ExampleActivity.this.getWindow().setAttributes(localLayoutParams2);
          if (Build.VERSION.SDK_INT >= 14)
            ExampleActivity.this.getWindow().getDecorView().setSystemUiVisibility(1);
        }
        do
        {
          return;
          WindowManager.LayoutParams localLayoutParams1 = ExampleActivity.this.getWindow().getAttributes();
          localLayoutParams1.flags = (0xFFFFFBFF & localLayoutParams1.flags);
          localLayoutParams1.flags = (0xFFFFFF7F & localLayoutParams1.flags);
          ExampleActivity.this.getWindow().setAttributes(localLayoutParams1);
        }
        while (Build.VERSION.SDK_INT < 14);
        ExampleActivity.this.getWindow().getDecorView().setSystemUiVisibility(0);
      }
    });
    this.webView.setWebChromeClient(this.webChromeClient);
    this.webView.loadUrl("http://123.57.148.33:8088/list.html");
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.activity.ExampleActivity
 * JD-Core Version:    0.6.0
 */