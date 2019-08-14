package com.withustudy.koudaizikao.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.withustudy.koudaizikao.R;
import com.withustudy.koudaizikao.entity.Subject;
import java.util.List;

public class ChooseSubjectAdapter extends BaseAdapter
{
  private int[] chlicked;
  private List<Subject> list;
  private Context mContext;

  public ChooseSubjectAdapter(List<Subject> paramList, Context paramContext)
  {
    this.list = paramList;
    this.mContext = paramContext;
    this.chlicked = new int[paramList.size()];
    for (int i = 0; ; i++)
    {
      if (i >= paramList.size())
        return;
      this.chlicked[i] = 0;
    }
  }

  public int[] getChlicked()
  {
    return this.chlicked;
  }

  public int getCount()
  {
    return this.list.size();
  }

  public Object getItem(int paramInt)
  {
    return this.list.get(paramInt);
  }

  public long getItemId(int paramInt)
  {
    return paramInt;
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    TextView localTextView;
    if (paramView == null)
    {
      paramView = LayoutInflater.from(this.mContext).inflate(2130903134, null);
      localTextView = (TextView)paramView.findViewById(2131100299);
      paramView.setTag(localTextView);
      if (this.chlicked[paramInt] != 0)
        break label96;
      localTextView.setBackgroundResource(2130837610);
      localTextView.setTextColor(Color.parseColor("#999999"));
    }
    while (true)
    {
      localTextView.setText(((Subject)this.list.get(paramInt)).getName());
      return paramView;
      localTextView = (TextView)paramView.getTag();
      break;
      label96: localTextView.setBackgroundResource(2130837609);
      localTextView.setTextColor(this.mContext.getResources().getColor(R.color.activity_color));
    }
  }

  public void setChlicked(int paramInt)
  {
    if (this.chlicked[paramInt] == 0)
    {
      this.chlicked[paramInt] = 1;
      return;
    }
    this.chlicked[paramInt] = 0;
  }

  public void setChlicked(int[] paramArrayOfInt)
  {
    this.chlicked = paramArrayOfInt;
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.adapter.ChooseSubjectAdapter
 * JD-Core Version:    0.6.0
 */