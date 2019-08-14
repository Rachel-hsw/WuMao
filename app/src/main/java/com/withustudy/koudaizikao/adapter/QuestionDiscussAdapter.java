package com.withustudy.koudaizikao.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.withustudy.koudaizikao.custom.QuestionDiscussView;
import java.util.ArrayList;
import java.util.List;

public class QuestionDiscussAdapter extends BaseAdapter
{
  private List<Integer> list;
  private List<String> listChild;
  private Context mContext;

  public QuestionDiscussAdapter(Context paramContext, List<Integer> paramList)
  {
    this.mContext = paramContext;
    this.list = paramList;
    this.listChild = new ArrayList();
    this.listChild.add("");
    this.listChild.add("");
    this.listChild.add("");
  }

  public int getCount()
  {
    return this.list.size();
  }

  public Object getItem(int paramInt)
  {
    return null;
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
      paramView = new QuestionDiscussView(this.mContext);
      localViewHolder.mQuestionDiscussView = ((QuestionDiscussView)paramView);
      paramView.setTag(localViewHolder);
    }
    while (true)
    {
      localViewHolder.mQuestionDiscussView.setImageAvatar(2130837673);
      localViewHolder.mQuestionDiscussView.setTextUsername("焦作学员");
      localViewHolder.mQuestionDiscussView.setTextFloor("1楼");
      localViewHolder.mQuestionDiscussView.setTextContent("这道题弱爆了，地球人都会回答！这道题弱爆了，地球人都会回答！");
      localViewHolder.mQuestionDiscussView.setTextTime("2015年8月1日 10:10");
      if (((Integer)this.list.get(paramInt)).intValue() != 1)
        break;
      localViewHolder.mQuestionDiscussView.setImageDividerVisibility(0);
      localViewHolder.mQuestionDiscussView.setListVisibility(0);
      QuestionDiscussChildAdapter localQuestionDiscussChildAdapter = new QuestionDiscussChildAdapter(this.mContext, this.listChild);
      localViewHolder.mQuestionDiscussView.setList(localQuestionDiscussChildAdapter, this.listChild);
      return paramView;
      localViewHolder = (ViewHolder)paramView.getTag();
    }
    localViewHolder.mQuestionDiscussView.setImageDividerVisibility(8);
    localViewHolder.mQuestionDiscussView.setListVisibility(8);
    return paramView;
  }

  class ViewHolder
  {
    public QuestionDiscussView mQuestionDiscussView;

    ViewHolder()
    {
    }
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.adapter.QuestionDiscussAdapter
 * JD-Core Version:    0.6.0
 */