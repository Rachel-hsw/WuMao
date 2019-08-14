package com.withustudy.koudaizikao.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.withustudy.koudaizikao.config.KouDaiSP;
import com.withustudy.koudaizikao.entity.ExerciseSummary;
import com.withustudy.koudaizikao.entity.Kpoint;
import com.withustudy.koudaizikao.tools.DateTools;
import java.util.List;

public class FavoriteAdapter extends BaseAdapter
{
  private static final int TYPE1 = 0;
  private static final int TYPE2 = 1;
  private boolean isDelete;
  private List list;
  private Context mContext;
  private String type;

  public FavoriteAdapter(Context paramContext, List paramList, boolean paramBoolean, String paramString)
  {
    this.mContext = paramContext;
    this.list = paramList;
    this.isDelete = paramBoolean;
    this.type = paramString;
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

  public int getItemViewType(int paramInt)
  {
    if (this.type.equals("pro"))
      return 0;
    return 1;
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    int i = getItemViewType(paramInt);
    ViewHolder1 localViewHolder1;
    ViewHolder2 localViewHolder2;
    if (paramView == null)
    {
      localViewHolder1 = null;
      localViewHolder2 = null;
      switch (i)
      {
      default:
      case 0:
      case 1:
      }
    }
    while (true)
      switch (i)
      {
      default:
        return paramView;
        paramView = LayoutInflater.from(this.mContext).inflate(2130903144, null);
        localViewHolder1 = new ViewHolder1();
        localViewHolder1.textTitle = ((TextView)paramView.findViewById(2131100392));
        localViewHolder1.textTime = ((TextView)paramView.findViewById(2131100393));
        localViewHolder1.textContent = ((TextView)paramView.findViewById(2131100394));
        localViewHolder1.textType = ((TextView)paramView.findViewById(2131100395));
        paramView.setTag(localViewHolder1);
        localViewHolder2 = null;
        continue;
        paramView = LayoutInflater.from(this.mContext).inflate(2130903145, null);
        localViewHolder2 = new ViewHolder2();
        localViewHolder2.textUser = ((TextView)paramView.findViewById(2131100396));
        localViewHolder2.imageAvatar = ((ImageView)paramView.findViewById(2131100397));
        localViewHolder2.textTitle = ((TextView)paramView.findViewById(2131100398));
        localViewHolder2.textContent = ((TextView)paramView.findViewById(2131100399));
        localViewHolder2.textTime = ((TextView)paramView.findViewById(2131100400));
        paramView.setTag(localViewHolder2);
        localViewHolder1 = null;
        continue;
        switch (i)
        {
        default:
          localViewHolder1 = null;
          localViewHolder2 = null;
          break;
        case 0:
          localViewHolder1 = (ViewHolder1)paramView.getTag();
          localViewHolder2 = null;
          break;
        case 1:
          localViewHolder2 = (ViewHolder2)paramView.getTag();
          localViewHolder1 = null;
        }
      case 0:
      case 1:
      }
    ExerciseSummary localExerciseSummary = (ExerciseSummary)this.list.get(paramInt);
    String str1 = localExerciseSummary.getKpoint().getName();
    localViewHolder1.textTitle.setText(str1);
    Object localObject = "";
    try
    {
      String str3 = DateTools.dateFormatYMD(Long.valueOf(localExerciseSummary.getTime()).longValue());
      localObject = str3;
      label386: localViewHolder1.textTime.setText((CharSequence)localObject);
      String str2 = localExerciseSummary.getExerciseDesc();
      localViewHolder1.textContent.setText(str2);
      LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams((int)(0.75D * KouDaiSP.getInstance(this.mContext).getWidth()), -2);
      localViewHolder1.textContent.setLayoutParams(localLayoutParams);
      localViewHolder1.textType.setText(this.mContext.getResources().getString(2131165282));
      return paramView;
      if (this.type.equals("information"))
      {
        localViewHolder2.textUser.setText("小编");
        localViewHolder2.imageAvatar.setBackgroundResource(2130837673);
        localViewHolder2.textTitle.setText("自考报考海淀区考生注意事项");
        localViewHolder2.textContent.setText("今年的考试情况是这个样子的今年的考试情况是这个样子的今年的考试情况是这个样子的今年的考试情况是这个样子的");
        localViewHolder2.textTime.setText("2015年8月1日");
        return paramView;
      }
      localViewHolder2.textUser.setText("刘胡兰");
      localViewHolder2.imageAvatar.setBackgroundResource(2130837673);
      localViewHolder2.textTitle.setText("在这里开个帖子，记录自己未来几年自考生活");
      localViewHolder2.textContent.setText("楼主今年高中毕业，高考只考了400多，只过我们省的大专线，哦呵呵呵呵呵呵");
      localViewHolder2.textTime.setText("2015年8月1日");
      return paramView;
    }
    catch (Exception localException)
    {
      break label386;
    }
  }

  public int getViewTypeCount()
  {
    return 2;
  }

  public void setDelete(boolean paramBoolean)
  {
    this.isDelete = paramBoolean;
  }

  public void setType(String paramString)
  {
    this.type = paramString;
  }

  class ViewHolder1
  {
    TextView textContent;
    TextView textTime;
    TextView textTitle;
    TextView textType;

    ViewHolder1()
    {
    }
  }

  class ViewHolder2
  {
    ImageView imageAvatar;
    TextView textContent;
    TextView textTime;
    TextView textTitle;
    TextView textUser;

    ViewHolder2()
    {
    }
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.adapter.FavoriteAdapter
 * JD-Core Version:    0.6.0
 */