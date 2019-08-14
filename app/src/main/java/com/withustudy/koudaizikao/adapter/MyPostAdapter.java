package com.withustudy.koudaizikao.adapter;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TableLayout;
import android.widget.TextView;
import com.android.http.FileDownLoad;
import com.withustudy.koudaizikao.activity.MyPostActivity.MyPostHandler;
import com.withustudy.koudaizikao.activity.ShowPictureActivity;
import com.withustudy.koudaizikao.base.AbsBaseActivity;
import com.withustudy.koudaizikao.config.KouDaiSP;
import com.withustudy.koudaizikao.entity.Post;
import com.withustudy.koudaizikao.fragment.FavoritePostFragment.FavoritePostHandler;
import com.withustudy.koudaizikao.tools.DateTools;
import java.io.Serializable;
import java.util.List;

public class MyPostAdapter extends BaseAdapter
{
  private List<Post> list;
  private Context mContext;
  private Handler mHandler;

  public MyPostAdapter(Context paramContext, List<Post> paramList, Handler paramHandler)
  {
    this.mContext = paramContext;
    this.list = paramList;
    this.mHandler = paramHandler;
  }

  private void downLoad(ImageView paramImageView, int paramInt1, int paramInt2)
  {
    paramImageView.setImageResource(2131230720);
    FileDownLoad.getInstance(this.mContext).downLoadImage((String)((Post)this.list.get(paramInt2)).getPost_files().get(paramInt1), paramImageView);
    paramImageView.setVisibility(0);
    paramImageView.setOnClickListener(new OnClickListener(paramInt1, paramInt2)
    {
      public void onClick(View paramView)
      {
        Bundle localBundle = new Bundle();
        localBundle.putInt("currentitem_key", this.val$index);
        localBundle.putSerializable("image_name", (Serializable)((Post)MyPostAdapter.this.list.get(this.val$position)).getPost_files());
        ((AbsBaseActivity)MyPostAdapter.this.mContext).startNewActivity(ShowPictureActivity.class, false, localBundle);
      }
    });
  }

  private void setTable(TableLayout paramTableLayout, LinearLayout[] paramArrayOfLinearLayout, int paramInt1, int paramInt2)
  {
    paramTableLayout.setVisibility(0);
    int i = KouDaiSP.getInstance(this.mContext).getWidth() - (int)(paramInt2 * KouDaiSP.getInstance(this.mContext).getDensity() / 160.0F);
    LayoutParams localLayoutParams;
    if (paramInt1 <= 3)
    {
      paramArrayOfLinearLayout[0].setVisibility(0);
      paramArrayOfLinearLayout[1].setVisibility(8);
      paramArrayOfLinearLayout[2].setVisibility(8);
      localLayoutParams = new LayoutParams(i, 30 + i / 3);
    }
    while (true)
    {
      paramTableLayout.setLayoutParams(localLayoutParams);
      return;
      if ((paramInt1 > 3) && (paramInt1 <= 6))
      {
        paramArrayOfLinearLayout[0].setVisibility(0);
        paramArrayOfLinearLayout[1].setVisibility(0);
        paramArrayOfLinearLayout[2].setVisibility(8);
        localLayoutParams = new LayoutParams(i, 30 + 2 * (i / 3));
        continue;
      }
      paramArrayOfLinearLayout[0].setVisibility(0);
      paramArrayOfLinearLayout[1].setVisibility(0);
      paramArrayOfLinearLayout[2].setVisibility(0);
      localLayoutParams = new LayoutParams(i, i + 35);
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

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    ViewHolder2 localViewHolder2;
    label562: int j;
    if (paramView == null)
    {
      paramView = LayoutInflater.from(this.mContext).inflate(2130903141, null);
      localViewHolder2 = new ViewHolder2();
      localViewHolder2.imageAvatar = ((ImageView)paramView.findViewById(2131100361));
      localViewHolder2.textName = ((TextView)paramView.findViewById(2131100362));
      localViewHolder2.textTime = ((TextView)paramView.findViewById(2131100363));
      localViewHolder2.textElite = ((TextView)paramView.findViewById(2131100364));
      localViewHolder2.textTitle = ((TextView)paramView.findViewById(2131100365));
      localViewHolder2.textContent = ((TextView)paramView.findViewById(2131100366));
      localViewHolder2.mTableLayout = ((TableLayout)paramView.findViewById(2131100367));
      localViewHolder2.mLayout = new LinearLayout[3];
      localViewHolder2.mLayout[0] = ((LinearLayout)paramView.findViewById(2131100368));
      localViewHolder2.mLayout[1] = ((LinearLayout)paramView.findViewById(2131100372));
      localViewHolder2.mLayout[2] = ((LinearLayout)paramView.findViewById(2131100376));
      localViewHolder2.image = new ImageView[9];
      localViewHolder2.image[0] = ((ImageView)paramView.findViewById(2131100369));
      localViewHolder2.image[1] = ((ImageView)paramView.findViewById(2131100370));
      localViewHolder2.image[2] = ((ImageView)paramView.findViewById(2131100371));
      localViewHolder2.image[3] = ((ImageView)paramView.findViewById(2131100373));
      localViewHolder2.image[4] = ((ImageView)paramView.findViewById(2131100374));
      localViewHolder2.image[5] = ((ImageView)paramView.findViewById(2131100375));
      localViewHolder2.image[6] = ((ImageView)paramView.findViewById(2131100377));
      localViewHolder2.image[7] = ((ImageView)paramView.findViewById(2131100378));
      localViewHolder2.image[8] = ((ImageView)paramView.findViewById(2131100379));
      localViewHolder2.textArea = ((TextView)paramView.findViewById(2131100380));
      localViewHolder2.textPraise = ((TextView)paramView.findViewById(2131100381));
      localViewHolder2.textDiscuss = ((TextView)paramView.findViewById(2131100382));
      paramView.setTag(localViewHolder2);
      paramView.setOnClickListener(new OnClickListener(paramInt)
      {
        public void onClick(View paramView)
        {
          Bundle localBundle = new Bundle();
          localBundle.putInt("id", ((Post)MyPostAdapter.this.list.get(this.val$position)).getTopic_id());
          Message localMessage;
          if ((MyPostAdapter.this.mHandler instanceof MyPostActivity.MyPostHandler))
            localMessage = MyPostAdapter.this.mHandler.obtainMessage(3, localBundle);
          while (true)
          {
            MyPostAdapter.this.mHandler.sendMessage(localMessage);
            return;
            boolean bool = MyPostAdapter.this.mHandler instanceof FavoritePostFragment.FavoritePostHandler;
            localMessage = null;
            if (!bool)
              continue;
            localMessage = MyPostAdapter.this.mHandler.obtainMessage(1, localBundle);
          }
        }
      });
      if ((((Post)this.list.get(paramInt)).getUser_headimg() != null) && (!((Post)this.list.get(paramInt)).getUser_headimg().equals("")))
        FileDownLoad.getInstance(this.mContext).downLoadImage(((Post)this.list.get(paramInt)).getUser_headimg(), localViewHolder2.imageAvatar, 1000);
      localViewHolder2.textName.setText(((Post)this.list.get(paramInt)).getUser_name());
      localViewHolder2.textTime.setText(DateTools.getPretime(this.mContext, ((Post)this.list.get(paramInt)).getTopic_time()));
      if (((Post)this.list.get(paramInt)).getTopic_level() != 1)
        break label763;
      localViewHolder2.textElite.setVisibility(0);
      localViewHolder2.textTitle.setText(((Post)this.list.get(paramInt)).getTopic_title());
      localViewHolder2.textContent.setText(((Post)this.list.get(paramInt)).getTopic_sub_desc());
      if (((Post)this.list.get(paramInt)).getPost_img_count() == 0)
        break label869;
      setTable(localViewHolder2.mTableLayout, localViewHolder2.mLayout, ((Post)this.list.get(paramInt)).getPost_img_count(), 48);
      j = 0;
      if (j < 9)
        break label776;
    }
    while (true)
    {
      localViewHolder2.textArea.setText(((Post)this.list.get(paramInt)).getPost_area());
      localViewHolder2.textPraise.setText(String.valueOf(((Post)this.list.get(paramInt)).getTopic_praise()));
      localViewHolder2.textDiscuss.setText(String.valueOf(((Post)this.list.get(paramInt)).getTopic_replies()));
      return paramView;
      localViewHolder2 = (ViewHolder2)paramView.getTag();
      break;
      label763: localViewHolder2.textElite.setVisibility(8);
      break label562;
      label776: if (j < ((Post)this.list.get(paramInt)).getPost_img_count())
        downLoad(localViewHolder2.image[j], j, paramInt);
      while (true)
      {
        j++;
        break;
        if (((Post)this.list.get(paramInt)).getPost_img_count() == 2)
        {
          localViewHolder2.image[j].setVisibility(8);
          continue;
        }
        localViewHolder2.image[j].setVisibility(4);
      }
      label869: localViewHolder2.mTableLayout.setVisibility(8);
      for (int i = 0; i < 9; i++)
      {
        if (i < 3)
          localViewHolder2.mLayout[i].setVisibility(8);
        localViewHolder2.image[i].setVisibility(8);
      }
    }
  }

  class ViewHolder2
  {
    public ImageView[] image;
    public ImageView imageAvatar;
    public LinearLayout[] mLayout;
    public TableLayout mTableLayout;
    public TextView textArea;
    public TextView textContent;
    public TextView textDiscuss;
    public TextView textElite;
    public TextView textName;
    public TextView textPraise;
    public TextView textTime;
    public TextView textTitle;

    ViewHolder2()
    {
    }
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.adapter.MyPostAdapter
 * JD-Core Version:    0.6.0
 */