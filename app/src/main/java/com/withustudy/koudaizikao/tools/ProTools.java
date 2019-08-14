package com.withustudy.koudaizikao.tools;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ProTools
{
  private static ProTools instance;
  private LoadingDialog mDialog;

  private ProTools(Context paramContext, int paramInt)
  {
    this.mDialog = new LoadingDialog(paramContext, paramInt);
  }

  public static ProTools getInstance(Context paramContext, int paramInt)
  {
    instance = new ProTools(paramContext, paramInt);
    return instance;
  }

  public void dismissDislog()
  {
    if ((this.mDialog != null) && (this.mDialog.isShowing()))
      this.mDialog.dismiss();
  }

  public void startDialog()
  {
    this.mDialog.setCancelable(true);
    this.mDialog.show();
  }

  public void startDialog(int paramInt)
  {
    this.mDialog.setCancelable(true);
    this.mDialog.show();
    this.mDialog.setTextColor(paramInt);
  }

  public void startDialog(String paramString)
  {
    this.mDialog.setCancelable(true);
    this.mDialog.show();
    this.mDialog.setText(paramString);
  }

  public void startDialog(boolean paramBoolean)
  {
    this.mDialog.setCancelable(paramBoolean);
    this.mDialog.show();
  }

  public void startDialog(boolean paramBoolean, String paramString, int paramInt)
  {
    this.mDialog.setCancelable(paramBoolean);
    this.mDialog.show();
    this.mDialog.setText(paramString);
    this.mDialog.setTextColor(paramInt);
  }

  private class LoadingDialog extends ProgressDialog
  {
    private ImageView image;
    private AnimationDrawable mAnimationDrawable;
    private TextView text;

    public LoadingDialog(Context arg2)
    {
      super();
    }

    public LoadingDialog(Context paramInt, int arg3)
    {
      super(i);
    }

    public void dismiss()
    {
      super.dismiss();
      this.image.post(new Runnable()
      {
        public void run()
        {
          if (LoadingDialog.this.mAnimationDrawable.isRunning())
            LoadingDialog.this.mAnimationDrawable.stop();
        }
      });
    }

    protected void onCreate(Bundle paramBundle)
    {
      super.onCreate(paramBundle);
      View localView = LayoutInflater.from(getContext()).inflate(2130903101, null);
      this.image = ((ImageView)localView.findViewById(2131100087));
      this.image.setImageResource(2130837509);
      this.mAnimationDrawable = ((AnimationDrawable)this.image.getDrawable());
      this.text = ((TextView)localView.findViewById(2131100088));
      setContentView(localView);
    }

    public void setText(String paramString)
    {
      this.text.setText(paramString);
    }

    public void setTextColor(int paramInt)
    {
      this.text.setTextColor(paramInt);
    }

    public void show()
    {
      super.show();
      this.image.post(new Runnable()
      {
        public void run()
        {
          if (LoadingDialog.this.mAnimationDrawable.isRunning())
            LoadingDialog.this.mAnimationDrawable.stop();
          LoadingDialog.this.mAnimationDrawable.start();
        }
      });
    }
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.tools.ProTools
 * JD-Core Version:    0.6.0
 */