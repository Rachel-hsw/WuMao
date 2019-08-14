package com.withustudy.koudaizikao.web;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.webkit.WebChromeClient;
import android.webkit.WebChromeClient.CustomViewCallback;
import android.webkit.WebSettings;
import android.widget.FrameLayout;
import android.widget.VideoView;

public class VideoEnabledWebChromeClient extends WebChromeClient
  implements OnPreparedListener, OnCompletionListener, OnErrorListener
{
  private View activityNonVideoView;
  private ViewGroup activityVideoView;
  private boolean isVideoFullscreen;
  private View loadingView;
  private ToggledFullscreenCallback toggledFullscreenCallback;
  private CustomViewCallback videoViewCallback;
  private FrameLayout videoViewContainer;
  private VideoEnabledWebView webView;

  public VideoEnabledWebChromeClient()
  {
  }

  public VideoEnabledWebChromeClient(View paramView, ViewGroup paramViewGroup)
  {
    this.activityNonVideoView = paramView;
    this.activityVideoView = paramViewGroup;
    this.loadingView = null;
    this.webView = null;
    this.isVideoFullscreen = false;
  }

  public VideoEnabledWebChromeClient(View paramView1, ViewGroup paramViewGroup, View paramView2)
  {
    this.activityNonVideoView = paramView1;
    this.activityVideoView = paramViewGroup;
    this.loadingView = paramView2;
    this.webView = null;
    this.isVideoFullscreen = false;
  }

  public VideoEnabledWebChromeClient(View paramView1, ViewGroup paramViewGroup, View paramView2, VideoEnabledWebView paramVideoEnabledWebView)
  {
    this.activityNonVideoView = paramView1;
    this.activityVideoView = paramViewGroup;
    this.loadingView = paramView2;
    this.webView = paramVideoEnabledWebView;
    this.isVideoFullscreen = false;
  }

  public View getVideoLoadingProgressView()
  {
    if (this.loadingView != null)
    {
      this.loadingView.setVisibility(0);
      return this.loadingView;
    }
    return super.getVideoLoadingProgressView();
  }

  public boolean isVideoFullscreen()
  {
    return this.isVideoFullscreen;
  }

  public boolean onBackPressed()
  {
    if (this.isVideoFullscreen)
    {
      onHideCustomView();
      return true;
    }
    return false;
  }

  public void onCompletion(MediaPlayer paramMediaPlayer)
  {
    onHideCustomView();
  }

  public boolean onError(MediaPlayer paramMediaPlayer, int paramInt1, int paramInt2)
  {
    return false;
  }

  public void onHideCustomView()
  {
    if (this.isVideoFullscreen)
    {
      this.activityVideoView.setVisibility(4);
      this.activityVideoView.removeView(this.videoViewContainer);
      this.activityNonVideoView.setVisibility(0);
      if ((this.videoViewCallback != null) && (!this.videoViewCallback.getClass().getName().contains(".chromium.")))
        this.videoViewCallback.onCustomViewHidden();
      this.isVideoFullscreen = false;
      this.videoViewContainer = null;
      this.videoViewCallback = null;
      if (this.toggledFullscreenCallback != null)
        this.toggledFullscreenCallback.toggledFullscreen(false);
    }
  }

  public void onPrepared(MediaPlayer paramMediaPlayer)
  {
    if (this.loadingView != null)
      this.loadingView.setVisibility(8);
  }

  public void onShowCustomView(View paramView, int paramInt, CustomViewCallback paramCustomViewCallback)
  {
    onShowCustomView(paramView, paramCustomViewCallback);
  }

  public void onShowCustomView(View paramView, CustomViewCallback paramCustomViewCallback)
  {
    View localView;
    if ((paramView instanceof FrameLayout))
    {
      FrameLayout localFrameLayout = (FrameLayout)paramView;
      localView = localFrameLayout.getFocusedChild();
      this.isVideoFullscreen = true;
      this.videoViewContainer = localFrameLayout;
      this.videoViewCallback = paramCustomViewCallback;
      this.activityNonVideoView.setVisibility(4);
      this.activityVideoView.addView(this.videoViewContainer, new LayoutParams(-1, -1));
      this.activityVideoView.setVisibility(0);
      if (!(localView instanceof VideoView))
        break label120;
      VideoView localVideoView = (VideoView)localView;
      localVideoView.setOnPreparedListener(this);
      localVideoView.setOnCompletionListener(this);
      localVideoView.setOnErrorListener(this);
    }
    while (true)
    {
      if (this.toggledFullscreenCallback != null)
        this.toggledFullscreenCallback.toggledFullscreen(true);
      return;
      label120: if ((this.webView == null) || (!this.webView.getSettings().getJavaScriptEnabled()) || (!(localView instanceof SurfaceView)))
        continue;
      String str = new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf("javascript:")).append("var _ytrp_html5_video_last;").toString())).append("var _ytrp_html5_video = document.getElementsByTagName('video')[0];").toString())).append("if (_ytrp_html5_video != undefined && _ytrp_html5_video != _ytrp_html5_video_last) {").toString())).append("_ytrp_html5_video_last = _ytrp_html5_video;").toString())).append("function _ytrp_html5_video_ended() {").toString())).append("_VideoEnabledWebView.notifyVideoEnd();").toString())).append("}").toString())).append("_ytrp_html5_video.addEventListener('ended', _ytrp_html5_video_ended);").toString() + "}";
      this.webView.loadUrl(str);
    }
  }

  public void setOnToggledFullscreen(ToggledFullscreenCallback paramToggledFullscreenCallback)
  {
    this.toggledFullscreenCallback = paramToggledFullscreenCallback;
  }

  public static abstract interface ToggledFullscreenCallback
  {
    public abstract void toggledFullscreen(boolean paramBoolean);
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.web.VideoEnabledWebChromeClient
 * JD-Core Version:    0.6.0
 */