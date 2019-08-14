package com.withustudy.koudaizikao.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.android.http.FileDownLoad;
import com.withustudy.koudaizikao.activity.ShowPictureActivity;
import com.withustudy.koudaizikao.base.AbsBaseActivity;
import com.withustudy.koudaizikao.config.KouDaiSP;
import com.withustudy.koudaizikao.entity.Post;
import com.withustudy.koudaizikao.entity.PostReply;
import com.withustudy.koudaizikao.tools.DateTools;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PostDetailAdapter extends BaseAdapter
{
  private static final int TYPE1 = 0;
  private static final int TYPE2 = 1;
  private List<PostReply> list;
  private CallBackListener mBackListener;
  private Context mContext;
  private Handler mHandler;
  private Post mPost;

  public PostDetailAdapter(Context paramContext, Post paramPost, List<PostReply> paramList, Handler paramHandler)
  {
    this.mContext = paramContext;
    this.mPost = paramPost;
    this.list = paramList;
    this.mHandler = paramHandler;
    this.mBackListener = new CallBackListener();
  }

  private void downLoad(ImageView paramImageView, int paramInt1, int paramInt2)
  {
    paramImageView.setImageResource(2131230720);
    FileDownLoad.getInstance(this.mContext).downLoadImage(((String)this.mPost.getPost_files().get(paramInt1)).split(";")[0], paramImageView);
    paramImageView.setVisibility(0);
    paramImageView.setOnClickListener(new OnClickListener(paramInt1)
    {
      public void onClick(View paramView)
      {
        Bundle localBundle = new Bundle();
        localBundle.putInt("currentitem_key", this.val$index);
        ArrayList localArrayList = new ArrayList();
        for (int i = 0; ; i++)
        {
          if (i >= PostDetailAdapter.this.mPost.getPost_files().size())
          {
            localBundle.putSerializable("image_name", (Serializable)localArrayList);
            ((AbsBaseActivity)PostDetailAdapter.this.mContext).startNewActivity(ShowPictureActivity.class, false, localBundle);
            return;
          }
          localArrayList.add(((String)PostDetailAdapter.this.mPost.getPost_files().get(i)).split(";")[0]);
        }
      }
    });
  }

  private void downLoadReply(ImageView paramImageView, int paramInt1, int paramInt2)
  {
    paramImageView.setImageResource(2131230720);
    FileDownLoad.getInstance(this.mContext).downLoadImage(((String)((PostReply)this.list.get(paramInt2)).getPost_files().get(paramInt1)).split(";")[0], paramImageView);
    paramImageView.setVisibility(0);
    paramImageView.setOnClickListener(new OnClickListener(paramInt1, paramInt2)
    {
      public void onClick(View paramView)
      {
        Bundle localBundle = new Bundle();
        localBundle.putInt("currentitem_key", this.val$index);
        ArrayList localArrayList = new ArrayList();
        for (int i = 0; ; i++)
        {
          if (i >= ((PostReply)PostDetailAdapter.this.list.get(this.val$position)).getPost_files().size())
          {
            localBundle.putSerializable("image_name", (Serializable)localArrayList);
            ((AbsBaseActivity)PostDetailAdapter.this.mContext).startNewActivity(ShowPictureActivity.class, false, localBundle);
            return;
          }
          localArrayList.add(((String)((PostReply)PostDetailAdapter.this.list.get(this.val$position)).getPost_files().get(i)).split(";")[0]);
        }
      }
    });
  }

  private void setReplyTable(TableLayout paramTableLayout, LinearLayout[] paramArrayOfLinearLayout, int paramInt1, int paramInt2)
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
      localLayoutParams.setMargins((int)(paramInt2 / 2 * KouDaiSP.getInstance(this.mContext).getDensity() / 160.0F), 0, (int)(paramInt2 / 2 * KouDaiSP.getInstance(this.mContext).getDensity() / 160.0F), 0);
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
    return 1 + this.list.size();
  }

  public Object getItem(int paramInt)
  {
    if (paramInt != 0)
      return this.list.get(paramInt);
    return this.mPost;
  }

  public long getItemId(int paramInt)
  {
    return paramInt;
  }

  public int getItemViewType(int paramInt)
  {
    if (paramInt == 0)
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
        paramView.setOnClickListener(this.mBackListener);
        switch (i)
        {
        default:
        case 0:
        case 1:
        }
      case 0:
      case 1:
      }
    }
    do
    {
      return paramView;
      localViewHolder1 = new ViewHolder1();
      paramView = LayoutInflater.from(this.mContext).inflate(2130903138, null);
      localViewHolder1.imageAvatar = ((ImageView)paramView.findViewById(2131100315));
      localViewHolder1.textName = ((TextView)paramView.findViewById(2131100316));
      localViewHolder1.textTime = ((TextView)paramView.findViewById(2131100317));
      localViewHolder1.textTitle = ((TextView)paramView.findViewById(2131100319));
      localViewHolder1.textFloor = ((TextView)paramView.findViewById(2131100318));
      localViewHolder1.textContent = ((TextView)paramView.findViewById(2131100320));
      localViewHolder1.mTableLayout = ((TableLayout)paramView.findViewById(2131100321));
      localViewHolder1.mLayout = new LinearLayout[3];
      localViewHolder1.mLayout[0] = ((LinearLayout)paramView.findViewById(2131100322));
      localViewHolder1.mLayout[1] = ((LinearLayout)paramView.findViewById(2131100326));
      localViewHolder1.mLayout[2] = ((LinearLayout)paramView.findViewById(2131100330));
      localViewHolder1.image = new ImageView[9];
      localViewHolder1.image[0] = ((ImageView)paramView.findViewById(2131100323));
      localViewHolder1.image[1] = ((ImageView)paramView.findViewById(2131100324));
      localViewHolder1.image[2] = ((ImageView)paramView.findViewById(2131100325));
      localViewHolder1.image[3] = ((ImageView)paramView.findViewById(2131100327));
      localViewHolder1.image[4] = ((ImageView)paramView.findViewById(2131100328));
      localViewHolder1.image[5] = ((ImageView)paramView.findViewById(2131100329));
      localViewHolder1.image[6] = ((ImageView)paramView.findViewById(2131100331));
      localViewHolder1.image[7] = ((ImageView)paramView.findViewById(2131100332));
      localViewHolder1.image[8] = ((ImageView)paramView.findViewById(2131100333));
      localViewHolder1.textArea = ((TextView)paramView.findViewById(2131100334));
      localViewHolder1.textDelete = ((TextView)paramView.findViewById(2131100335));
      localViewHolder1.textPraise = ((TextView)paramView.findViewById(2131100336));
      localViewHolder1.textDiscuss = ((TextView)paramView.findViewById(2131100337));
      paramView.setTag(localViewHolder1);
      localViewHolder2 = null;
      break;
      localViewHolder2 = new ViewHolder2();
      paramView = LayoutInflater.from(this.mContext).inflate(2130903139, null);
      localViewHolder2.imageAvatar = ((ImageView)paramView.findViewById(2131100338));
      localViewHolder2.textHost = ((TextView)paramView.findViewById(2131100339));
      localViewHolder2.textName = ((TextView)paramView.findViewById(2131100340));
      localViewHolder2.textTime = ((TextView)paramView.findViewById(2131100341));
      localViewHolder2.textFloor = ((TextView)paramView.findViewById(2131100342));
      localViewHolder2.textContent = ((TextView)paramView.findViewById(2131100343));
      localViewHolder2.mTableLayout = ((TableLayout)paramView.findViewById(2131100344));
      localViewHolder2.mLayout = new LinearLayout[3];
      localViewHolder2.mLayout[0] = ((LinearLayout)paramView.findViewById(2131100345));
      localViewHolder2.mLayout[1] = ((LinearLayout)paramView.findViewById(2131100349));
      localViewHolder2.mLayout[2] = ((LinearLayout)paramView.findViewById(2131100353));
      localViewHolder2.image = new ImageView[9];
      localViewHolder2.image[0] = ((ImageView)paramView.findViewById(2131100346));
      localViewHolder2.image[1] = ((ImageView)paramView.findViewById(2131100347));
      localViewHolder2.image[2] = ((ImageView)paramView.findViewById(2131100348));
      localViewHolder2.image[3] = ((ImageView)paramView.findViewById(2131100350));
      localViewHolder2.image[4] = ((ImageView)paramView.findViewById(2131100351));
      localViewHolder2.image[5] = ((ImageView)paramView.findViewById(2131100352));
      localViewHolder2.image[6] = ((ImageView)paramView.findViewById(2131100354));
      localViewHolder2.image[7] = ((ImageView)paramView.findViewById(2131100355));
      localViewHolder2.image[8] = ((ImageView)paramView.findViewById(2131100356));
      localViewHolder2.textReply = ((TextView)paramView.findViewById(2131100357));
      localViewHolder2.textArea = ((TextView)paramView.findViewById(2131100358));
      localViewHolder2.buttonReply = ((Button)paramView.findViewById(2131100359));
      paramView.setTag(localViewHolder2);
      localViewHolder1 = null;
      break;
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
        break;
      }
    }
    while (this.mPost == null);
    int n;
    if ((this.mPost.getUser_headimg() != null) && (!this.mPost.getUser_headimg().equals("")))
    {
      FileDownLoad.getInstance(this.mContext).downLoadImage(this.mPost.getUser_headimg(), localViewHolder1.imageAvatar, 1000);
      localViewHolder1.textName.setText(this.mPost.getUser_name());
      localViewHolder1.textTime.setText(DateTools.getPretime(this.mContext, this.mPost.getTopic_time()));
      localViewHolder1.textFloor.setText(this.mContext.getResources().getString(2131165362));
      localViewHolder1.textTitle.setText(this.mPost.getTopic_title());
      localViewHolder1.textContent.setText(this.mPost.getTopic_sub_desc());
      if (this.mPost.getPost_img_count() == 0)
        break label1349;
      setTable(localViewHolder1.mTableLayout, localViewHolder1.mLayout, this.mPost.getPost_img_count(), 24);
      n = 0;
      if (n < 9)
        break label1274;
      localViewHolder1.textArea.setText(this.mPost.getPost_area());
      localViewHolder1.textPraise.setText(String.valueOf(this.mPost.getTopic_praise()));
      localViewHolder1.textDiscuss.setText(String.valueOf(this.mPost.getTopic_replies()));
      if (KouDaiSP.getInstance(this.mContext).getUserId().equals(""))
        break label1420;
      if (!KouDaiSP.getInstance(this.mContext).getUserId().equals(this.mPost.getUserid()))
        break label1407;
      localViewHolder1.textDelete.setVisibility(0);
    }
    while (true)
    {
      localViewHolder1.textDelete.setOnClickListener(this.mBackListener);
      return paramView;
      localViewHolder1.imageAvatar.setImageResource(2130837672);
      break;
      label1274: if (n < this.mPost.getPost_img_count())
        downLoad(localViewHolder1.image[n], n, paramInt);
      while (true)
      {
        n++;
        break;
        if (this.mPost.getPost_img_count() == 2)
        {
          localViewHolder1.image[n].setVisibility(8);
          continue;
        }
        localViewHolder1.image[n].setVisibility(4);
      }
      label1349: localViewHolder1.mTableLayout.setVisibility(8);
      for (int m = 0; m < 9; m++)
      {
        if (m < 3)
          localViewHolder1.mLayout[m].setVisibility(8);
        localViewHolder1.image[m].setVisibility(8);
      }
      label1407: localViewHolder1.textDelete.setVisibility(8);
      continue;
      label1420: localViewHolder1.textDelete.setVisibility(8);
    }
    label1557: int k;
    if ((((PostReply)this.list.get(paramInt - 1)).getUser_headimg() != null) && (!((PostReply)this.list.get(paramInt - 1)).getUser_headimg().equals("")))
    {
      FileDownLoad.getInstance(this.mContext).downLoadImage(((PostReply)this.list.get(paramInt - 1)).getUser_headimg(), localViewHolder2.imageAvatar, 1000);
      if (!((PostReply)this.list.get(paramInt - 1)).getUserid().equals(this.mPost.getUserid()))
        break label1903;
      localViewHolder2.textHost.setVisibility(0);
      localViewHolder2.textName.setText(((PostReply)this.list.get(paramInt - 1)).getUser_name());
      localViewHolder2.textTime.setText(DateTools.getPretime(this.mContext, ((PostReply)this.list.get(paramInt - 1)).getPost_time()));
      localViewHolder2.textFloor.setText(paramInt + "楼");
      localViewHolder2.textContent.setText(((PostReply)this.list.get(paramInt - 1)).getPost_text());
      if (((PostReply)this.list.get(paramInt - 1)).getPost_img_count() == 0)
        break label2015;
      setReplyTable(localViewHolder2.mTableLayout, localViewHolder2.mLayout, ((PostReply)this.list.get(paramInt - 1)).getPost_img_count(), 80);
      k = 0;
      if (k < 9)
        break label1916;
      label1735: if (((PostReply)this.list.get(paramInt - 1)).getReply_type() != 1)
        break label2073;
      localViewHolder2.textReply.setVisibility(0);
      localViewHolder2.textReply.setText("回复" + ((PostReply)this.list.get(paramInt - 1)).getReply_username() + "：" + ((PostReply)this.list.get(paramInt - 1)).getReply_content());
    }
    while (true)
    {
      localViewHolder2.buttonReply.setTag(Integer.valueOf(paramInt - 1));
      localViewHolder2.buttonReply.setOnClickListener(this.mBackListener);
      localViewHolder2.textArea.setText(((PostReply)this.list.get(paramInt - 1)).getPost_area());
      return paramView;
      localViewHolder2.imageAvatar.setImageResource(2130837672);
      break;
      label1903: localViewHolder2.textHost.setVisibility(8);
      break label1557;
      label1916: if (k < ((PostReply)this.list.get(paramInt - 1)).getPost_img_count())
        downLoadReply(localViewHolder2.image[k], k, paramInt - 1);
      while (true)
      {
        k++;
        break;
        if (((PostReply)this.list.get(paramInt - 1)).getPost_img_count() == 2)
        {
          localViewHolder2.image[k].setVisibility(8);
          continue;
        }
        localViewHolder2.image[k].setVisibility(4);
      }
      label2015: localViewHolder2.mTableLayout.setVisibility(8);
      for (int j = 0; j < 9; j++)
      {
        if (j < 3)
          localViewHolder2.mLayout[j].setVisibility(8);
        localViewHolder2.image[j].setVisibility(8);
      }
      break label1735;
      label2073: localViewHolder2.textReply.setVisibility(8);
      localViewHolder2.textReply.setText("");
    }
  }

  public int getViewTypeCount()
  {
    return 2;
  }

  public Post getmPost()
  {
    return this.mPost;
  }

  public void setmPost(Post paramPost)
  {
    this.mPost = paramPost;
  }

  class CallBackListener
    implements OnClickListener
  {
    CallBackListener()
    {
    }

    public void onClick(View paramView)
    {
      if (paramView.getId() == 2131100335)
      {
        if (!KouDaiSP.getInstance(PostDetailAdapter.this.mContext).getUserId().equals(""))
        {
          PostDetailAdapter.this.mHandler.sendEmptyMessage(8);
          return;
        }
        Toast.makeText(PostDetailAdapter.this.mContext, "请先登录", 0).show();
        return;
      }
      if (paramView.getId() == 2131100359)
      {
        if (!KouDaiSP.getInstance(PostDetailAdapter.this.mContext).getUserId().equals(""))
        {
          Message localMessage = PostDetailAdapter.this.mHandler.obtainMessage(4, (Integer)paramView.getTag());
          PostDetailAdapter.this.mHandler.sendMessage(localMessage);
          return;
        }
        Toast.makeText(PostDetailAdapter.this.mContext, "请先登录", 0).show();
        return;
      }
      PostDetailAdapter.this.mHandler.sendEmptyMessage(3);
    }
  }

  class ViewHolder1
  {
    public ImageView[] image;
    public ImageView imageAvatar;
    public LinearLayout[] mLayout;
    public TableLayout mTableLayout;
    public TextView textArea;
    public TextView textContent;
    public TextView textDelete;
    public TextView textDiscuss;
    public TextView textFloor;
    public TextView textName;
    public TextView textPraise;
    public TextView textTime;
    public TextView textTitle;

    ViewHolder1()
    {
    }
  }

  class ViewHolder2
  {
    public Button buttonReply;
    public ImageView[] image;
    public ImageView imageAvatar;
    public LinearLayout[] mLayout;
    public TableLayout mTableLayout;
    public TextView textArea;
    public TextView textContent;
    public TextView textFloor;
    public TextView textHost;
    public TextView textName;
    public TextView textReply;
    public TextView textTime;

    ViewHolder2()
    {
    }
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.adapter.PostDetailAdapter
 * JD-Core Version:    0.6.0
 */