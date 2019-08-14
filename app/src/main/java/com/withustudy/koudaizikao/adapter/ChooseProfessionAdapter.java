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
import com.withustudy.koudaizikao.entity.Major;
import java.util.List;

public class ChooseProfessionAdapter extends BaseAdapter
{
  private int choosen;
  private List<Major> list;
  private Context mContext;

  public ChooseProfessionAdapter(Context paramContext, List<Major> paramList)
  {
    this.mContext = paramContext;
    this.list = paramList;
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
      paramView = LayoutInflater.from(this.mContext).inflate(2130903132, null);
      localTextView = (TextView)paramView.findViewById(2131100297);
      paramView.setTag(localTextView);
      if (paramInt != this.choosen)
        break label102;
      localTextView.setBackgroundResource(2130837609);
      localTextView.setTextColor(this.mContext.getResources().getColor(R.color.activity_color));
    }
    while (true)
    {
      localTextView.setText(((Major)this.list.get(paramInt)).getMajorName());
      return paramView;
      localTextView = (TextView)paramView.getTag();
      break;
      label102: localTextView.setBackgroundResource(2130837610);
      localTextView.setTextColor(Color.parseColor("#999999"));
    }
  }

  public void setChoosen(int paramInt)
  {
    this.choosen = paramInt;
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.adapter.ChooseProfessionAdapter
 * JD-Core Version:    0.6.0
 */