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
import com.withustudy.koudaizikao.entity.Post;
import com.withustudy.koudaizikao.tools.DateTools;
import java.util.List;

public class BbsMainAdapter extends BaseAdapter
{
  private static final int TYPE1 = 0;
  private static final int TYPE2 = 1;
  private List<Forum> forumList;
  private Context mContext;
  private List<Post> postList;
  private int type;

  public BbsMainAdapter(Context paramContext, int paramInt)
  {
    this.mContext = paramContext;
    this.type = paramInt;
  }

  public int getCount()
  {
    if (this.type == 0)
      return this.postList.size();
    return this.forumList.size();
  }

  public List<Forum> getForumList()
  {
    return this.forumList;
  }

  public Object getItem(int paramInt)
  {
    if (this.type == 0)
      return this.postList.get(paramInt);
    return this.forumList.get(paramInt);
  }

  public long getItemId(int paramInt)
  {
    return paramInt;
  }

  public int getItemViewType(int paramInt)
  {
    if (this.type == 0)
      return 0;
    return 1;
  }

  public List<Post> getPostList()
  {
    return this.postList;
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
        localViewHolder1 = new ViewHolder1();
        paramView = LayoutInflater.from(this.mContext).inflate(2130903136, null);
        localViewHolder1.textNum = ((TextView)paramView.findViewById(2131100306));
        localViewHolder1.textName = ((TextView)paramView.findViewById(2131100307));
        localViewHolder1.textFrom = ((TextView)paramView.findViewById(2131100308));
        localViewHolder1.textBrowse = ((TextView)paramView.findViewById(2131100309));
        paramView.setTag(localViewHolder1);
        localViewHolder2 = null;
        continue;
        localViewHolder2 = new ViewHolder2();
        paramView = LayoutInflater.from(this.mContext).inflate(2130903137, null);
        localViewHolder2.imageAvatar = ((ImageView)paramView.findViewById(2131100310));
        localViewHolder2.textName = ((TextView)paramView.findViewById(2131100311));
        localViewHolder2.textContent = ((TextView)paramView.findViewById(2131100313));
        localViewHolder2.textFollow = ((TextView)paramView.findViewById(2131100312));
        localViewHolder2.textTime = ((TextView)paramView.findViewById(2131100314));
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
    localViewHolder1.textNum.setText(String.valueOf(paramInt + 1));
    localViewHolder1.textName.setText(((Post)this.postList.get(paramInt)).getTopic_title());
    localViewHolder1.textFrom.setText("来自：" + ((Post)this.postList.get(paramInt)).getForum_name());
    localViewHolder1.textBrowse.setText("浏览：" + ((Post)this.postList.get(paramInt)).getTopic_views());
    return paramView;
    if ((((Forum)this.forumList.get(paramInt)).getForum_img() != null) && (!((Forum)this.forumList.get(paramInt)).getForum_img().equals("")))
    {
      FileDownLoad.getInstance(this.mContext).downLoadImage(((Forum)this.forumList.get(paramInt)).getForum_img(), localViewHolder2.imageAvatar);
      localViewHolder2.textName.setText(((Forum)this.forumList.get(paramInt)).getForum_name());
      localViewHolder2.textContent.setText(((Forum)this.forumList.get(paramInt)).getForum_desc());
      if (KouDaiSP.getInstance(this.mContext).getUserId().equals(""))
        break label703;
      localViewHolder2.textFollow.setVisibility(0);
      if (((Forum)this.forumList.get(paramInt)).getForum_isfollow() != 1)
        break label680;
      localViewHolder2.textFollow.setText(this.mContext.getResources().getString(2131165347));
    }
    while (true)
    {
      localViewHolder2.textTime.setText(DateTools.getPretime(this.mContext, ((Forum)this.forumList.get(paramInt)).getForum_last_post_date()));
      return paramView;
      localViewHolder2.imageAvatar.setImageResource(2130837673);
      break;
      label680: localViewHolder2.textFollow.setText(this.mContext.getResources().getString(2131165348));
      continue;
      label703: localViewHolder2.textFollow.setVisibility(8);
    }
  }

  public int getViewTypeCount()
  {
    return 2;
  }

  public void setForumList(List<Forum> paramList)
  {
    this.forumList = paramList;
  }

  public void setPostList(List<Post> paramList)
  {
    this.postList = paramList;
  }

  class ViewHolder1
  {
    public TextView textBrowse;
    public TextView textFrom;
    public TextView textName;
    public TextView textNum;

    ViewHolder1()
    {
    }
  }

  class ViewHolder2
  {
    public ImageView imageAvatar;
    public TextView textContent;
    public TextView textFollow;
    public TextView textName;
    public TextView textTime;

    ViewHolder2()
    {
    }
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.adapter.BbsMainAdapter
 * JD-Core Version:    0.6.0
 */