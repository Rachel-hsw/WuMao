package com.withustudy.koudaizikao.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

public class SimulationHistoryAdapter extends BaseAdapter
{
  private List<Integer> list;
  private Context mContext;

  public SimulationHistoryAdapter(List<Integer> paramList, Context paramContext)
  {
    this.list = paramList;
    this.mContext = paramContext;
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
    ViewHolder localViewHolder;
    if (paramView == null)
    {
      localViewHolder = new ViewHolder();
      paramView = LayoutInflater.from(this.mContext).inflate(2130903153, null);
      localViewHolder.imageResult = ((ImageView)paramView.findViewById(2131100422));
      localViewHolder.textScore = ((TextView)paramView.findViewById(2131100423));
      localViewHolder.textSpend = ((TextView)paramView.findViewById(2131100424));
      localViewHolder.textTime = ((TextView)paramView.findViewById(2131100425));
      localViewHolder.textType1 = ((TextView)paramView.findViewById(2131100426));
      localViewHolder.textType2 = ((TextView)paramView.findViewById(2131100427));
      localViewHolder.textType3 = ((TextView)paramView.findViewById(2131100428));
      localViewHolder.textLastTime = ((TextView)paramView.findViewById(2131100429));
      paramView.setTag(localViewHolder);
      if (((Integer)this.list.get(paramInt)).intValue() < 60)
        break label300;
      localViewHolder.imageResult.setBackgroundResource(2130837809);
      localViewHolder.textScore.setTextColor(Color.parseColor("#00FF00"));
    }
    while (true)
    {
      localViewHolder.textScore.setText(this.list.get(paramInt) + "分");
      localViewHolder.textSpend.setText("耗时12分钟");
      localViewHolder.textTime.setText("2015年8月1日");
      localViewHolder.textType1.setText("单选：24/25       单选：24/25");
      localViewHolder.textType2.setText("单选：24/25       单选：24/25");
      localViewHolder.textType3.setText("单选：24/25       单选：24/25");
      localViewHolder.textLastTime.setText("上次复习时间：2015年8月1日");
      return paramView;
      localViewHolder = (ViewHolder)paramView.getTag();
      break;
      label300: localViewHolder.imageResult.setBackgroundResource(2130837809);
      localViewHolder.textScore.setTextColor(Color.parseColor("#FF0000"));
    }
  }

  class ViewHolder
  {
    public ImageView imageResult;
    public TextView textLastTime;
    public TextView textScore;
    public TextView textSpend;
    public TextView textTime;
    public TextView textType1;
    public TextView textType2;
    public TextView textType3;

    ViewHolder()
    {
    }
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.adapter.SimulationHistoryAdapter
 * JD-Core Version:    0.6.0
 */