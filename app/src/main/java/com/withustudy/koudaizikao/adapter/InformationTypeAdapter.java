package com.withustudy.koudaizikao.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.http.FileDownLoad;
import com.withustudy.koudaizikao.entity.News;
import java.util.List;

public class InformationTypeAdapter extends BaseAdapter
{
  private List<News> list;
  private Context mContext;

  public InformationTypeAdapter(Context paramContext, List<News> paramList)
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
    ViewHolder localViewHolder;
    if (paramView == null)
    {
      localViewHolder = new ViewHolder();
      paramView = LayoutInflater.from(this.mContext).inflate(2130903151, null);
      localViewHolder.imageAvatar = ((ImageView)paramView.findViewById(2131100417));
      localViewHolder.textTitle = ((TextView)paramView.findViewById(2131100418));
      localViewHolder.textContent = ((TextView)paramView.findViewById(2131100419));
      localViewHolder.textCount = ((TextView)paramView.findViewById(2131100420));
      paramView.setTag(localViewHolder);
      if ((((News)this.list.get(paramInt)).getPanelUrl() == null) || (((News)this.list.get(paramInt)).getPanelUrl().equals("")))
        break label301;
      FileDownLoad.getInstance(this.mContext).downLoadImage(((News)this.list.get(paramInt)).getPanelUrl(), localViewHolder.imageAvatar);
      label164: localViewHolder.textTitle.setText(((News)this.list.get(paramInt)).getInstruction());
      if ((!((News)this.list.get(paramInt)).getArticleType().equals("SIGN_UP_INFO")) && (!((News)this.list.get(paramInt)).getArticleType().equals("COMMEN_QUESTION")))
        break label314;
      localViewHolder.textContent.setVisibility(4);
    }
    while (true)
    {
      localViewHolder.textCount.setText(((News)this.list.get(paramInt)).getCommentNum() + "回复");
      return paramView;
      localViewHolder = (ViewHolder)paramView.getTag();
      break;
      label301: localViewHolder.imageAvatar.setImageResource(2130837674);
      break label164;
      label314: localViewHolder.textContent.setVisibility(0);
      localViewHolder.textContent.setText(((News)this.list.get(paramInt)).getBriefText());
    }
  }

  class ViewHolder
  {
    public ImageView imageAvatar;
    public TextView textContent;
    public TextView textCount;
    public TextView textTitle;

    ViewHolder()
    {
    }
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.adapter.InformationTypeAdapter
 * JD-Core Version:    0.6.0
 */