package com.withustudy.koudaizikao.custom;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SubjectTabView extends LinearLayout
{
  private ImageView image;
  private Context mContext;
  private TextView text;

  public SubjectTabView(Context paramContext)
  {
    super(paramContext);
    this.mContext = paramContext;
    initView();
  }

  public SubjectTabView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.mContext = paramContext;
    initView();
  }

  public SubjectTabView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    this.mContext = paramContext;
    initView();
  }

  @TargetApi(21)
  public SubjectTabView(Context paramContext, AttributeSet paramAttributeSet, int paramInt1, int paramInt2)
  {
    super(paramContext, paramAttributeSet, paramInt1, paramInt2);
    this.mContext = paramContext;
    initView();
  }

  private void initView()
  {
    View localView = inflate(this.mContext, 2130903095, null);
    this.text = ((TextView)localView.findViewById(2131100056));
    this.image = ((ImageView)localView.findViewById(2131100057));
    addView(localView);
  }

  public void setSubject(String paramString)
  {
    this.text.setText(paramString);
  }

  public void setSubjectState(int paramInt)
  {
    if (paramInt == 1)
    {
      this.image.setVisibility(0);
      this.text.getPaint().setFakeBoldText(true);
      this.text.setTextColor(Color.parseColor("#ffffff"));
      this.text.setTextSize(2, 16.0F);
      return;
    }
    this.image.setVisibility(8);
    this.text.getPaint().setFakeBoldText(false);
    this.text.setTextColor(Color.parseColor("#A9A9A9"));
    this.text.setTextSize(2, 15.0F);
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.custom.SubjectTabView
 * JD-Core Version:    0.6.0
 */