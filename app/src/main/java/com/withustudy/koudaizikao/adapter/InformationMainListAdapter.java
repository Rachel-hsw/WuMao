package com.withustudy.koudaizikao.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.withustudy.koudaizikao.entity.News;
import com.withustudy.koudaizikao.tools.DateTools;
import java.util.ArrayList;
import java.util.List;

public class InformationMainListAdapter extends BaseAdapter
{
  private final int TYPE1 = 0;
  private final int TYPE2 = 1;
  private final int TYPE3 = 2;
  private List<News> list;
  private Context mContext;

  public InformationMainListAdapter(List<News> paramList1, List<News> paramList2, List<News> paramList3, List<News> paramList4, Context paramContext)
  {
    this.mContext = paramContext;
    setList(paramList1, paramList2, paramList3, paramList4);
  }

  private void setList(List<News> paramList1, List<News> paramList2, List<News> paramList3, List<News> paramList4)
  {
    this.list = new ArrayList();
    News localNews1 = new News();
    localNews1.setArticleId(String.valueOf(-2));
    this.list.add(localNews1);
    News localNews2 = new News();
    localNews2.setArticleId(String.valueOf(-1));
    localNews2.setInstruction("EXAMINATION_ROAD");
    this.list.add(localNews2);
    int i = 0;
    int j;
    label164: int k;
    if (i >= paramList1.size())
    {
      News localNews3 = new News();
      localNews3.setArticleId(String.valueOf(-2));
      this.list.add(localNews3);
      News localNews4 = new News();
      localNews4.setArticleId(String.valueOf(-1));
      localNews4.setInstruction("SIGN_UP_INFO");
      this.list.add(localNews4);
      j = 0;
      if (j < paramList2.size())
        break label368;
      News localNews5 = new News();
      localNews5.setArticleId(String.valueOf(-2));
      this.list.add(localNews5);
      News localNews6 = new News();
      localNews6.setArticleId(String.valueOf(-1));
      localNews6.setInstruction("OFFICIAL_NEWS");
      this.list.add(localNews6);
      k = 0;
      label246: if (k < paramList3.size())
        break label395;
      News localNews7 = new News();
      localNews7.setArticleId(String.valueOf(-2));
      this.list.add(localNews7);
      News localNews8 = new News();
      localNews8.setArticleId(String.valueOf(-1));
      localNews8.setInstruction("COMMEN_QUESTION");
      this.list.add(localNews8);
    }
    for (int m = 0; ; m++)
    {
      if (m >= paramList4.size())
      {
        return;
        this.list.add((News)paramList1.get(i));
        i++;
        break;
        label368: this.list.add((News)paramList2.get(j));
        j++;
        break label164;
        label395: this.list.add((News)paramList3.get(k));
        k++;
        break label246;
      }
      this.list.add((News)paramList4.get(m));
    }
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
    if (((News)this.list.get(paramInt)).getArticleId().equals("-2"))
      return 0;
    if (((News)this.list.get(paramInt)).getArticleId().equals("-1"))
      return 1;
    return 2;
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    int i = getItemViewType(paramInt);
    ViewHolder2 localViewHolder2;
    ViewHolder3 localViewHolder3;
    if (paramView == null)
    {
      localViewHolder2 = null;
      localViewHolder3 = null;
      switch (i)
      {
      default:
        switch (i)
        {
        default:
        case 1:
        case 2:
        }
      case 0:
      case 1:
      case 2:
      }
    }
    do
    {
      return paramView;
      ViewHolder1 localViewHolder1 = new ViewHolder1();
      paramView = LayoutInflater.from(this.mContext).inflate(2130903148, null);
      localViewHolder1.mImageView = ((ImageView)paramView.findViewById(2131100410));
      paramView.setTag(localViewHolder1);
      localViewHolder2 = null;
      localViewHolder3 = null;
      break;
      localViewHolder2 = new ViewHolder2();
      paramView = LayoutInflater.from(this.mContext).inflate(2130903149, null);
      localViewHolder2.mImageView = ((ImageView)paramView.findViewById(2131100411));
      localViewHolder2.mTextView = ((TextView)paramView.findViewById(2131100412));
      paramView.setTag(localViewHolder2);
      localViewHolder3 = null;
      break;
      localViewHolder3 = new ViewHolder3();
      paramView = LayoutInflater.from(this.mContext).inflate(2130903150, null);
      localViewHolder3.imageDivider = ((ImageView)paramView.findViewById(2131100413));
      localViewHolder3.mTextView1 = ((TextView)paramView.findViewById(2131100414));
      localViewHolder3.mTextView2 = ((TextView)paramView.findViewById(2131100416));
      localViewHolder3.mTextView3 = ((TextView)paramView.findViewById(2131100415));
      paramView.setTag(localViewHolder3);
      localViewHolder2 = null;
      break;
      switch (i)
      {
      default:
        localViewHolder2 = null;
        localViewHolder3 = null;
        break;
      case 0:
        ((ViewHolder1)paramView.getTag());
        localViewHolder2 = null;
        localViewHolder3 = null;
        break;
      case 1:
        localViewHolder2 = (ViewHolder2)paramView.getTag();
        localViewHolder3 = null;
        break;
      case 2:
        localViewHolder3 = (ViewHolder3)paramView.getTag();
        localViewHolder2 = null;
        break;
        if (((News)this.list.get(paramInt)).getInstruction().equals("EXAMINATION_ROAD"))
        {
          localViewHolder2.mImageView.setBackgroundResource(2130837671);
          localViewHolder2.mTextView.setTextColor(Color.parseColor("#fcb616"));
          localViewHolder2.mTextView.setText(2131165265);
          return paramView;
        }
        if (((News)this.list.get(paramInt)).getInstruction().equals("SIGN_UP_INFO"))
        {
          localViewHolder2.mImageView.setBackgroundResource(2130837668);
          localViewHolder2.mTextView.setTextColor(Color.parseColor("#fb8e55"));
          localViewHolder2.mTextView.setText(2131165266);
          return paramView;
        }
        if (!((News)this.list.get(paramInt)).getInstruction().equals("OFFICIAL_NEWS"))
          continue;
        localViewHolder2.mImageView.setBackgroundResource(2130837669);
        localViewHolder2.mTextView.setTextColor(Color.parseColor("#40b0ef"));
        localViewHolder2.mTextView.setText(2131165267);
        return paramView;
      }
    }
    while (!((News)this.list.get(paramInt)).getInstruction().equals("COMMEN_QUESTION"));
    localViewHolder2.mImageView.setBackgroundResource(2130837670);
    localViewHolder2.mTextView.setTextColor(Color.parseColor("#f7586c"));
    localViewHolder2.mTextView.setText(2131165268);
    return paramView;
    News localNews = (News)this.list.get(paramInt);
    if (((News)this.list.get(paramInt - 1)).getArticleId().equals("-1"))
    {
      localViewHolder3.imageDivider.setVisibility(8);
      localViewHolder3.mTextView1.setText(localNews.getInstruction());
      if ((!localNews.getArticleType().equals("SIGN_UP_INFO")) && (!localNews.getArticleType().equals("COMMEN_QUESTION")))
        break label740;
      localViewHolder3.mTextView2.setVisibility(8);
    }
    while (true)
    {
      localViewHolder3.mTextView3.setText(DateTools.getPretime(this.mContext, Long.valueOf(localNews.getCreatedTime()).longValue()));
      return paramView;
      localViewHolder3.imageDivider.setVisibility(0);
      break;
      label740: localViewHolder3.mTextView2.setVisibility(0);
      localViewHolder3.mTextView2.setText(localNews.getBriefText());
    }
  }

  public int getViewTypeCount()
  {
    return 3;
  }

  class ViewHolder1
  {
    public ImageView mImageView;

    ViewHolder1()
    {
    }
  }

  class ViewHolder2
  {
    public ImageView mImageView;
    public TextView mTextView;

    ViewHolder2()
    {
    }
  }

  class ViewHolder3
  {
    public ImageView imageDivider;
    public TextView mTextView1;
    public TextView mTextView2;
    public TextView mTextView3;

    ViewHolder3()
    {
    }
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.adapter.InformationMainListAdapter
 * JD-Core Version:    0.6.0
 */