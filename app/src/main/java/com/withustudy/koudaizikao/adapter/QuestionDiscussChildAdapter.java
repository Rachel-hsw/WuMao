package com.withustudy.koudaizikao.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.withustudy.koudaizikao.custom.QuestionDiscussView;
import java.util.List;

public class QuestionDiscussChildAdapter extends BaseAdapter
{
  private final int TYPE1 = 0;
  private final int TYPE2 = 1;
  private List<String> list;
  private Context mContext;

  public QuestionDiscussChildAdapter(Context paramContext, List<String> paramList)
  {
    this.mContext = paramContext;
    this.list = paramList;
  }

  public int getCount()
  {
    return 1 + this.list.size();
  }

  public Object getItem(int paramInt)
  {
    return null;
  }

  public long getItemId(int paramInt)
  {
    return paramInt;
  }

  public int getItemViewType(int paramInt)
  {
    if (paramInt != this.list.size())
      return 0;
    return 1;
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    int i = getItemViewType(paramInt);
    ViewHolder localViewHolder;
    ViewHolder2 localViewHolder2;
    if (paramView == null)
    {
      localViewHolder = null;
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
        localViewHolder = new ViewHolder();
        paramView = new QuestionDiscussView(this.mContext);
        localViewHolder.mQuestionDiscussView = ((QuestionDiscussView)paramView);
        paramView.setTag(localViewHolder);
        localViewHolder2 = null;
        continue;
        localViewHolder2 = new ViewHolder2();
        paramView = LayoutInflater.from(this.mContext).inflate(2130903152, null);
        localViewHolder2.text = ((TextView)paramView.findViewById(2131100421));
        paramView.setTag(localViewHolder2);
        localViewHolder = null;
        continue;
        switch (i)
        {
        default:
          localViewHolder = null;
          localViewHolder2 = null;
          break;
        case 0:
          localViewHolder = (ViewHolder)paramView.getTag();
          localViewHolder2 = null;
          break;
        case 1:
          localViewHolder2 = (ViewHolder2)paramView.getTag();
          localViewHolder = null;
        }
      case 0:
      case 1:
      }
    localViewHolder.mQuestionDiscussView.setImageAvatar(2130837673);
    localViewHolder.mQuestionDiscussView.setTextUsername("驻马店学员");
    localViewHolder.mQuestionDiscussView.setFloorVisibility(8);
    localViewHolder.mQuestionDiscussView.setDiscussButtonVisibility(8);
    localViewHolder.mQuestionDiscussView.setTextContent("楼上有病！尼玛，这题很简单吗？我怎么没觉得呢，劳资连题目都美看懂！");
    localViewHolder.mQuestionDiscussView.setTextTime("2015年8月1日 11:20");
    return paramView;
    localViewHolder2.text.setText(this.mContext.getResources().getString(2131165304));
    return paramView;
  }

  public int getViewTypeCount()
  {
    return 2;
  }

  class ViewHolder
  {
    public QuestionDiscussView mQuestionDiscussView;

    ViewHolder()
    {
    }
  }

  class ViewHolder2
  {
    public TextView text;

    ViewHolder2()
    {
    }
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.adapter.QuestionDiscussChildAdapter
 * JD-Core Version:    0.6.0
 */