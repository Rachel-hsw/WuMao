package com.withustudy.koudaizikao.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.http.FileDownLoad;
import com.withustudy.koudaizikao.config.KouDaiSP;
import com.withustudy.koudaizikao.entity.Forum;
import java.util.List;

public class AllSectionAdapter extends BaseAdapter
{
  private List<Forum> forumList;
  private Context mContext;

  public AllSectionAdapter(Context paramContext, List<Forum> paramList)
  {
    this.mContext = paramContext;
    this.forumList = paramList;
  }

  public int getCount()
  {
    return this.forumList.size();
  }

  public Object getItem(int paramInt)
  {
    return this.forumList.get(paramInt);
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
      paramView = LayoutInflater.from(this.mContext).inflate(2130903135, null);
      localViewHolder = new ViewHolder();
      localViewHolder.imageDivier = ((ImageView)paramView.findViewById(2131100300));
      localViewHolder.imageAvatar = ((ImageView)paramView.findViewById(2131100301));
      localViewHolder.textName = ((TextView)paramView.findViewById(2131100302));
      localViewHolder.textContent = ((TextView)paramView.findViewById(2131100304));
      localViewHolder.textFollow = ((TextView)paramView.findViewById(2131100303));
      localViewHolder.textTime = ((TextView)paramView.findViewById(2131100305));
      paramView.setTag(localViewHolder);
      if (paramInt != 0)
        break label335;
      localViewHolder.imageDivier.setVisibility(8);
      label132: if ((((Forum)this.forumList.get(paramInt)).getForum_img() == null) || (((Forum)this.forumList.get(paramInt)).getForum_img().equals("")))
        break label347;
      FileDownLoad.getInstance(this.mContext).downLoadImage(((Forum)this.forumList.get(paramInt)).getForum_img(), localViewHolder.imageAvatar);
    }
    while (true)
    {
      localViewHolder.textName.setText(((Forum)this.forumList.get(paramInt)).getForum_name());
      localViewHolder.textContent.setText(((Forum)this.forumList.get(paramInt)).getForum_desc());
      if (KouDaiSP.getInstance(this.mContext).getUserId().equals(""))
        break label382;
      localViewHolder.textFollow.setVisibility(0);
      if (((Forum)this.forumList.get(paramInt)).getForum_isfollow() != 1)
        break label360;
      localViewHolder.textFollow.setText(this.mContext.getResources().getString(2131165347));
      return paramView;
      localViewHolder = (ViewHolder)paramView.getTag();
      break;
      label335: localViewHolder.imageDivier.setVisibility(0);
      break label132;
      label347: localViewHolder.imageAvatar.setImageResource(2130837673);
    }
    label360: localViewHolder.textFollow.setText(this.mContext.getResources().getString(2131165348));
    return paramView;
    label382: localViewHolder.textFollow.setVisibility(8);
    return paramView;
  }

  class ViewHolder
  {
    public ImageView imageAvatar;
    public ImageView imageDivier;
    public TextView textContent;
    public TextView textFollow;
    public TextView textName;
    public TextView textTime;

    ViewHolder()
    {
    }
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.adapter.AllSectionAdapter
 * JD-Core Version:    0.6.0
 */