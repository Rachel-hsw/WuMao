package com.withustudy.koudaizikao.custom;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class OptionView extends LinearLayout
{
  private Button button;
  private List<String> clicked;
  private Context context;
  private Handler handler;
  private boolean isClickAble = true;
  private boolean[] isClicked;
  private boolean isInit = false;
  private boolean isMultiple = false;
  private boolean isSim;
  private int len;
  private CallBackListener mCallBackListener;
  private int messageWhat;
  private int[] optionRRes = { 2130837504, 2130837516, 2130837603, 2130837615, 2130837628, 2130837644 };
  private int[] optionWRes = { 2130837507, 2130837519, 2130837606, 2130837618, 2130837631, 2130837647 };
  private List<String> options;
  private int[] optionsClickedRes = { 2130837504, 2130837516, 2130837603, 2130837615, 2130837628, 2130837644 };
  private int[] optionsRes = { 2130837506, 2130837518, 2130837605, 2130837617, 2130837630, 2130837646 };
  private List<Integer> rightCount;
  private int rightLen;
  private int state = 0;
  private TextView[] textView;

  public OptionView(Context paramContext)
  {
    super(paramContext);
    this.context = paramContext;
    initPra();
  }

  public OptionView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.context = paramContext;
    initPra();
  }

  public OptionView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    this.context = paramContext;
    initPra();
  }

  @TargetApi(21)
  public OptionView(Context paramContext, AttributeSet paramAttributeSet, int paramInt1, int paramInt2)
  {
    super(paramContext, paramAttributeSet, paramInt1, paramInt2);
    this.context = paramContext;
    initPra();
  }

  private void addList(int paramInt)
  {
    switch (paramInt)
    {
    default:
      return;
    case 0:
      this.clicked.add("A");
      return;
    case 1:
      this.clicked.add("B");
      return;
    case 2:
      this.clicked.add("C");
      return;
    case 3:
      this.clicked.add("D");
      return;
    case 4:
      this.clicked.add("E");
      return;
    case 5:
    }
    this.clicked.add("F");
  }

  private void initPra()
  {
    setOrientation(1);
    this.mCallBackListener = new CallBackListener();
  }

  private void setMultipleResult()
  {
    this.isClickAble = false;
    int i = 0;
    if (i >= this.len)
    {
      if (this.state != 2)
        this.state = 1;
      return;
    }
    int j = 0;
    int k = 0;
    label33: if (k >= this.rightLen)
    {
      label41: if (j == this.rightLen)
        break label96;
      setTextViewByResult(this.textView[i], i, true);
    }
    while (true)
    {
      i++;
      break;
      if (i == ((Integer)this.rightCount.get(k)).intValue())
        break label41;
      k++;
      j++;
      break label33;
      label96: if (this.isClicked[i] == 0)
        continue;
      setTextViewByResult(this.textView[i], i, false);
      this.state = 2;
    }
  }

  private void setSingleResult(int paramInt)
  {
    this.isClickAble = false;
    int i = 0;
    if (i >= this.len)
      return;
    int j = 0;
    int k = 0;
    label21: if (k >= this.rightLen)
    {
      label30: if (j == this.rightLen)
        break label96;
      setTextViewByResult(this.textView[i], i, true);
      if (paramInt == i)
        this.state = 1;
    }
    while (true)
    {
      i++;
      break;
      if (i == ((Integer)this.rightCount.get(k)).intValue())
        break label30;
      k++;
      j++;
      break label21;
      label96: if (paramInt != i)
        continue;
      setTextViewByResult(this.textView[i], i, false);
      this.state = 2;
    }
  }

  private void setTextViewByClick(TextView paramTextView, int paramInt, boolean paramBoolean)
  {
    Drawable localDrawable;
    if (paramBoolean)
    {
      localDrawable = getResources().getDrawable(this.optionsRes[paramInt]);
      paramTextView.setTextColor(Color.parseColor("#666666"));
    }
    while (true)
    {
      localDrawable.setBounds(0, 0, localDrawable.getMinimumWidth(), localDrawable.getMinimumHeight());
      paramTextView.setCompoundDrawables(localDrawable, null, null, null);
      return;
      localDrawable = getResources().getDrawable(this.optionsClickedRes[paramInt]);
      paramTextView.setTextColor(Color.parseColor("#34b601"));
    }
  }

  private void setTextViewByResult(TextView paramTextView, int paramInt, boolean paramBoolean)
  {
    Drawable localDrawable1;
    Drawable localDrawable2;
    if (paramBoolean)
    {
      localDrawable1 = getResources().getDrawable(this.optionRRes[paramInt]);
      localDrawable2 = getResources().getDrawable(2130837734);
      paramTextView.setTextColor(Color.parseColor("#34b601"));
    }
    while (true)
    {
      localDrawable1.setBounds(0, 0, localDrawable1.getMinimumWidth(), localDrawable1.getMinimumHeight());
      localDrawable2.setBounds(0, 0, localDrawable2.getMinimumWidth(), localDrawable2.getMinimumHeight());
      paramTextView.setCompoundDrawables(localDrawable1, null, localDrawable2, null);
      return;
      localDrawable1 = getResources().getDrawable(this.optionWRes[paramInt]);
      localDrawable2 = getResources().getDrawable(2130837736);
      paramTextView.setTextColor(Color.parseColor("#FF0033"));
    }
  }

  public List<String> getClicked()
  {
    this.clicked.clear();
    for (int i = 0; ; i++)
    {
      if (i >= this.len)
        return this.clicked;
      if (this.isClicked[i] == 0)
        continue;
      addList(i);
    }
  }

  public void initOptions(List<String> paramList, List<Integer> paramList1, Handler paramHandler, int paramInt, boolean paramBoolean)
  {
    this.options = paramList;
    this.rightCount = paramList1;
    this.handler = paramHandler;
    this.messageWhat = paramInt;
    this.isSim = paramBoolean;
    this.len = paramList.size();
    this.rightLen = paramList1.size();
    LayoutParams localLayoutParams1;
    int i;
    if (this.rightLen > 1)
    {
      this.isMultiple = true;
      this.clicked = new ArrayList();
      this.textView = new TextView[this.len];
      localLayoutParams1 = new LayoutParams(-2, -2);
      localLayoutParams1.setMargins(0, 50, 0, 0);
      i = 0;
      label108: if (i < this.len)
        break label283;
      this.isClicked = new boolean[this.len];
    }
    for (int j = 0; ; j++)
    {
      if (j >= this.len)
      {
        if ((this.isMultiple) && (!paramBoolean))
        {
          this.button = new Button(this.context);
          this.button.setText(this.context.getResources().getString(2131165286));
          this.button.setBackgroundResource(2130837735);
          this.button.setTextColor(Color.parseColor("#ffffff"));
          this.button.setTextSize(2, 18.0F);
          LayoutParams localLayoutParams2 = new LayoutParams(-1, -1);
          localLayoutParams2.setMargins(0, 50, 0, 0);
          this.button.setLayoutParams(localLayoutParams2);
          this.button.setOnClickListener(this.mCallBackListener);
          addView(this.button);
        }
        this.isInit = true;
        return;
        this.isMultiple = false;
        break;
        label283: this.textView[i] = new TextView(this.context);
        this.textView[i].setTag(Integer.valueOf(i));
        this.textView[i].setText((CharSequence)paramList.get(i));
        this.textView[i].setTextColor(Color.parseColor("#666666"));
        this.textView[i].setTextSize(2, 18.0F);
        this.textView[i].getPaint().setFakeBoldText(true);
        this.textView[i].setLayoutParams(localLayoutParams1);
        this.textView[i].setGravity(16);
        Drawable localDrawable = getResources().getDrawable(this.optionsRes[i]);
        localDrawable.setBounds(0, 0, localDrawable.getMinimumWidth(), localDrawable.getMinimumHeight());
        this.textView[i].setCompoundDrawables(localDrawable, null, null, null);
        this.textView[i].setCompoundDrawablePadding(40);
        this.textView[i].setOnClickListener(this.mCallBackListener);
        addView(this.textView[i]);
        i++;
        break label108;
      }
      this.isClicked[j] = false;
    }
  }

  public boolean isInit()
  {
    return this.isInit;
  }

  public void showRightAnswer()
  {
    this.isClickAble = false;
    int i = 0;
    if (i >= this.len)
    {
      if (this.button != null)
        this.button.setVisibility(8);
      return;
    }
    for (int j = 0; ; j++)
    {
      if (j >= this.rightLen)
      {
        i++;
        break;
      }
      if (i != ((Integer)this.rightCount.get(j)).intValue())
        continue;
      setTextViewByResult(this.textView[i], i, true);
    }
  }

  class CallBackListener
    implements OnClickListener
  {
    CallBackListener()
    {
    }

    public void onClick(View paramView)
    {
      int m;
      int n;
      if ((paramView instanceof Button))
      {
        m = 0;
        n = 0;
        if (n >= OptionView.this.len)
        {
          if (m != 0)
            break label82;
          Toast.makeText(OptionView.this.context, OptionView.this.context.getResources().getString(2131165287), 0).show();
        }
      }
      label82: 
      do
      {
        return;
        if (OptionView.this.isClicked[n] != 0)
          m = 1;
        n++;
        break;
        OptionView.this.setMultipleResult();
        if (OptionView.this.button != null)
          OptionView.this.button.setVisibility(8);
        Message localMessage2 = OptionView.this.handler.obtainMessage(OptionView.this.messageWhat, Integer.valueOf(OptionView.this.state));
        OptionView.this.handler.sendMessage(localMessage2);
        return;
      }
      while (!OptionView.this.isClickAble);
      if (!OptionView.this.isMultiple)
      {
        String str = ((TextView)paramView).getText().toString();
        if (!OptionView.this.isSim)
          for (int k = 0; ; k++)
          {
            if (k >= OptionView.this.len)
            {
              Message localMessage1 = OptionView.this.handler.obtainMessage(OptionView.this.messageWhat, Integer.valueOf(OptionView.this.state));
              OptionView.this.handler.sendMessage(localMessage1);
              return;
            }
            if (!str.equals(OptionView.this.options.get(k)))
              continue;
            OptionView.this.isClicked[k] = 1;
            OptionView.this.setSingleResult(k);
          }
        int j = 0;
        label305: if (j < OptionView.this.len)
        {
          if (!str.equals(OptionView.this.options.get(j)))
            break label375;
          OptionView.this.isClicked[j] = 1;
          OptionView.this.setTextViewByClick(OptionView.this.textView[j], j, false);
        }
        while (true)
        {
          j++;
          break label305;
          break;
          label375: OptionView.this.isClicked[j] = 0;
          OptionView.this.setTextViewByClick(OptionView.this.textView[j], j, true);
        }
      }
      int i = ((Integer)paramView.getTag()).intValue();
      if (OptionView.this.isClicked[i] != 0)
      {
        OptionView.this.setTextViewByClick(OptionView.this.textView[i], i, true);
        OptionView.this.isClicked[i] = 0;
        return;
      }
      OptionView.this.setTextViewByClick(OptionView.this.textView[i], i, false);
      OptionView.this.isClicked[i] = 1;
    }
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.custom.OptionView
 * JD-Core Version:    0.6.0
 */