package com.withustudy.koudaizikao.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.withustudy.koudaizikao.R;
import com.withustudy.koudaizikao.config.KouDaiSP;
import com.withustudy.koudaizikao.entity.Province;
import java.util.List;

public class ChooseProvinceAdapter extends BaseAdapter
{
  private int choosen;
  private List<Province> list;
  private Context mContext;

  public ChooseProvinceAdapter(Context paramContext, List<Province> paramList, int paramInt)
  {
    this.mContext = paramContext;
    this.list = paramList;
    this.choosen = paramInt;
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
      paramView = LayoutInflater.from(this.mContext).inflate(2130903133, null);
      localTextView = (TextView)paramView.findViewById(2131100298);
      paramView.setTag(localTextView);
    }
    while (true)
    {
      localTextView.setLayoutParams(new AbsListView.LayoutParams((int)(KouDaiSP.getInstance(this.mContext).getWidth() - 159.0F * KouDaiSP.getInstance(this.mContext).getDensity() / 160.0F) / 4, (int)(KouDaiSP.getInstance(this.mContext).getWidth() - 159.0F * KouDaiSP.getInstance(this.mContext).getDensity() / 160.0F) / 4));
      String str = ((Province)this.list.get(paramInt)).getProvName();
      localTextView.setText(str.substring(0, -1 + str.length()));
      if (paramInt != this.choosen)
        break;
      localTextView.setBackgroundResource(2130837612);
      localTextView.setTextColor(this.mContext.getResources().getColor(R.color.activity_color));
      return paramView;
      localTextView = (TextView)paramView.getTag();
    }
    localTextView.setBackgroundResource(2130837613);
    localTextView.setTextColor(Color.parseColor("#999999"));
    return paramView;
  }

  public void setChoosen(int paramInt)
  {
    this.choosen = paramInt;
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.adapter.ChooseProvinceAdapter
 * JD-Core Version:    0.6.0
 */