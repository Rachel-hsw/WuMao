package com.withustudy.koudaizikao.custom;

import android.app.Activity;
import android.content.Context;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.withustudy.koudaizikao.base.AbsBaseActivity;
import com.withustudy.koudaizikao.config.KouDaiSP;
import com.withustudy.koudaizikao.tools.ToolsUtils;

public class SharePopWindow
{
  private Button buttonCancel;
  private String con = "";
  private ImageView imageFriend;
  private ImageView imageQQ;
  private ImageView imageWeiBo;
  private ImageView imageWeiXin;
  private LinearLayout layout;
  private CallBackListener mBackListener;
  private Activity mContext;
  private PopupWindow mPop;
  private View parents;
  private TextView text;
  private String ti = "";
  private String url = "";

  public SharePopWindow(Activity paramActivity, View paramView, String paramString1, String paramString2, String paramString3)
  {
    this.mContext = paramActivity;
    this.parents = paramView;
    this.url = paramString1;
    this.ti = paramString2;
    this.con = paramString3;
    this.mBackListener = new CallBackListener();
  }

  public SharePopWindow(Context paramContext, View paramView)
  {
    this.mContext = ((Activity)paramContext);
    this.parents = paramView;
    this.mBackListener = new CallBackListener();
  }

  private void backgroundAlpha(float paramFloat)
  {
    WindowManager.LayoutParams localLayoutParams = this.mContext.getWindow().getAttributes();
    localLayoutParams.alpha = paramFloat;
    this.mContext.getWindow().setAttributes(localLayoutParams);
  }

  private View initView()
  {
    View localView = LinearLayout.inflate(this.mContext, 2130903098, null);
    this.text = ((TextView)localView.findViewById(2131100062));
    this.layout = ((LinearLayout)localView.findViewById(2131100063));
    this.imageWeiXin = ((ImageView)localView.findViewById(2131100064));
    this.imageFriend = ((ImageView)localView.findViewById(2131100065));
    this.imageQQ = ((ImageView)localView.findViewById(2131100066));
    this.imageWeiBo = ((ImageView)localView.findViewById(2131100067));
    this.buttonCancel = ((Button)localView.findViewById(2131100068));
    this.text.setOnClickListener(this.mBackListener);
    this.layout.setOnClickListener(this.mBackListener);
    this.imageWeiXin.setOnClickListener(this.mBackListener);
    this.imageFriend.setOnClickListener(this.mBackListener);
    this.imageQQ.setOnClickListener(this.mBackListener);
    this.imageWeiBo.setOnClickListener(this.mBackListener);
    this.buttonCancel.setOnClickListener(this.mBackListener);
    localView.setFocusable(true);
    localView.setFocusableInTouchMode(true);
    localView.setOnKeyListener(this.mBackListener);
    return localView;
  }

  public void dismiss()
  {
    if (this.mPop != null)
      this.mPop.dismiss();
    this.mPop = null;
    backgroundAlpha(1.0F);
  }

  public String getUrl()
  {
    return this.url;
  }

  public void showPop()
  {
    if (this.mPop != null)
    {
      this.mPop.dismiss();
      this.mPop = null;
    }
    this.mPop = new PopupWindow(initView(), KouDaiSP.getInstance(this.mContext).getWidth(), ToolsUtils.dip2px(this.mContext, 450.0F));
    this.mPop.setAnimationStyle(2131361794);
    this.mPop.setFocusable(true);
    this.mPop.showAtLocation(this.parents, 80, 0, 0);
    backgroundAlpha(0.3F);
  }

  class CallBackListener
    implements OnClickListener, OnKeyListener
  {
    CallBackListener()
    {
    }

    public void onClick(View paramView)
    {
      AbsBaseActivity localAbsBaseActivity = (AbsBaseActivity)SharePopWindow.this.mContext;
      switch (paramView.getId())
      {
      default:
        return;
      case 2131100064:
        if (SharePopWindow.this.url.equals(""))
          localAbsBaseActivity.share(SHARE_MEDIA.WEIXIN, "http://share.kdzikao.com/app/share.page");
        while (true)
        {
          SharePopWindow.this.dismiss();
          return;
          localAbsBaseActivity.share(SHARE_MEDIA.WEIXIN, SharePopWindow.this.url, SharePopWindow.this.ti, SharePopWindow.this.con);
        }
      case 2131100065:
        if (SharePopWindow.this.url.equals(""))
          localAbsBaseActivity.share(SHARE_MEDIA.WEIXIN_CIRCLE, "http://share.kdzikao.com/app/share.page");
        while (true)
        {
          SharePopWindow.this.dismiss();
          return;
          localAbsBaseActivity.share(SHARE_MEDIA.WEIXIN_CIRCLE, SharePopWindow.this.url, SharePopWindow.this.ti, SharePopWindow.this.con);
        }
      case 2131100066:
        if (SharePopWindow.this.url.equals(""))
          localAbsBaseActivity.share(SHARE_MEDIA.QQ, "http://share.kdzikao.com/app/share.page");
        while (true)
        {
          SharePopWindow.this.dismiss();
          return;
          localAbsBaseActivity.share(SHARE_MEDIA.QQ, SharePopWindow.this.url, SharePopWindow.this.ti, SharePopWindow.this.con);
        }
      case 2131100067:
        if (SharePopWindow.this.url.equals(""))
          localAbsBaseActivity.share(SHARE_MEDIA.SINA, "http://share.kdzikao.com/app/share.page");
        while (true)
        {
          SharePopWindow.this.dismiss();
          return;
          localAbsBaseActivity.share(SHARE_MEDIA.SINA, SharePopWindow.this.url, SharePopWindow.this.ti, SharePopWindow.this.con);
        }
      case 2131100062:
      case 2131100063:
      case 2131100068:
      }
      SharePopWindow.this.dismiss();
    }

    public boolean onKey(View paramView, int paramInt, KeyEvent paramKeyEvent)
    {
      if (paramInt == 4)
      {
        SharePopWindow.this.dismiss();
        return true;
      }
      return false;
    }
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.custom.SharePopWindow
 * JD-Core Version:    0.6.0
 */