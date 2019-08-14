package com.withustudy.koudaizikao.custom;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class QuestionStatisticsView extends LinearLayout
{
  private ImageView[] imageMaster;
  private Context mContext;
  private TextView textMyWrong;
  private TextView textTitleLeft;
  private TextView textTitleMiddle;
  private TextView textTitleRight;
  private TextView textTotalWrong;

  public QuestionStatisticsView(Context paramContext)
  {
    super(paramContext);
    this.mContext = paramContext;
    initView();
  }

  public QuestionStatisticsView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.mContext = paramContext;
    initView();
  }

  public QuestionStatisticsView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    this.mContext = paramContext;
    initView();
  }

  @TargetApi(21)
  public QuestionStatisticsView(Context paramContext, AttributeSet paramAttributeSet, int paramInt1, int paramInt2)
  {
    super(paramContext, paramAttributeSet, paramInt1, paramInt2);
    this.mContext = paramContext;
    initView();
  }

  private void initView()
  {
    View localView = LayoutInflater.from(this.mContext).inflate(2130903100, null);
    this.textTitleLeft = ((TextView)localView.findViewById(2131100077));
    this.textTitleMiddle = ((TextView)localView.findViewById(2131100079));
    this.textTitleRight = ((TextView)localView.findViewById(2131100081));
    this.textTotalWrong = ((TextView)localView.findViewById(2131100078));
    this.textMyWrong = ((TextView)localView.findViewById(2131100080));
    this.imageMaster = new ImageView[5];
    this.imageMaster[0] = ((ImageView)localView.findViewById(2131100082));
    this.imageMaster[1] = ((ImageView)localView.findViewById(2131100083));
    this.imageMaster[2] = ((ImageView)localView.findViewById(2131100084));
    this.imageMaster[3] = ((ImageView)localView.findViewById(2131100085));
    this.imageMaster[4] = ((ImageView)localView.findViewById(2131100086));
    addView(localView);
    localView.setLayoutParams(new LayoutParams(-1, -2));
  }

  public void setImageMaster(int paramInt)
  {
    if ((paramInt > 5) || (paramInt < 1))
      return;
    int i = 0;
    label13: if (i < 5)
    {
      if (i >= paramInt)
        break label40;
      this.imageMaster[i].setBackgroundResource(2130837809);
    }
    while (true)
    {
      i++;
      break label13;
      break;
      label40: this.imageMaster[i].setBackgroundResource(2130837809);
    }
  }

  public void setTextContentLeft(String paramString)
  {
    this.textTotalWrong.setText(paramString);
  }

  public void setTextContentMiddle(String paramString)
  {
    this.textMyWrong.setText(paramString);
  }

  public void setTextTitleLeft(String paramString)
  {
    this.textTitleLeft.setText(paramString);
  }

  public void setTextTitleMiddle(String paramString)
  {
    this.textTitleMiddle.setText(paramString);
  }

  public void setTextTitleRight(String paramString)
  {
    this.textTitleRight.setText(paramString);
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.custom.QuestionStatisticsView
 * JD-Core Version:    0.6.0
 */