package com.withustudy.koudaizikao.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.android.http.FileDownLoad;
import com.withustudy.koudaizikao.config.KouDaiSP;
import com.withustudy.koudaizikao.entity.InfoComment;
import com.withustudy.koudaizikao.tools.DateTools;
import java.util.List;

public class InformationDetailAdapter extends BaseAdapter
{
  public static final int TYPE1 = 0;
  public static final int TYPE2 = 1;
  private List<InfoComment> list;
  private CallBackListener mBackListener;
  private Context mContext;
  private Handler mHandler;

  public InformationDetailAdapter(Context paramContext, List<InfoComment> paramList, Handler paramHandler)
  {
    this.mContext = paramContext;
    this.list = paramList;
    this.mBackListener = new CallBackListener();
    this.mHandler = paramHandler;
  }

  public int getCount()
  {
    if (this.list.size() == 0)
      return 0;
    return 1 + this.list.size();
  }

  public Object getItem(int paramInt)
  {
    if (paramInt != this.list.size())
      return this.list.get(paramInt);
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
    ViewHolder1 localViewHolder1;
    if (paramView == null)
    {
      localViewHolder1 = null;
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
      case 1:
      default:
        return paramView;
        localViewHolder1 = new ViewHolder1();
        paramView = LayoutInflater.from(this.mContext).inflate(2130903146, null);
        localViewHolder1.imageAvatar = ((ImageView)paramView.findViewById(2131100401));
        localViewHolder1.textName = ((TextView)paramView.findViewById(2131100403));
        localViewHolder1.textTime = ((TextView)paramView.findViewById(2131100404));
        localViewHolder1.textFloor = ((TextView)paramView.findViewById(2131100405));
        localViewHolder1.textContent = ((TextView)paramView.findViewById(2131100406));
        localViewHolder1.textReply = ((TextView)paramView.findViewById(2131100407));
        localViewHolder1.textArea = ((TextView)paramView.findViewById(2131100408));
        localViewHolder1.buttonReply = ((Button)paramView.findViewById(2131100409));
        paramView.setTag(localViewHolder1);
        continue;
        paramView = LayoutInflater.from(this.mContext).inflate(2130903147, null);
        paramView.setTag(paramView);
        localViewHolder1 = null;
        continue;
        localViewHolder1 = null;
        switch (i)
        {
        case 1:
        default:
          localViewHolder1 = null;
          break;
        case 0:
          localViewHolder1 = (ViewHolder1)paramView.getTag();
        }
      case 0:
      }
    if ((((InfoComment)this.list.get(paramInt)).getProfileUrl() != null) && (!((InfoComment)this.list.get(paramInt)).getProfileUrl().equals("")))
    {
      FileDownLoad.getInstance(this.mContext).downLoadImage(((InfoComment)this.list.get(paramInt)).getProfileUrl(), localViewHolder1.imageAvatar, 1000);
      localViewHolder1.textName.setText(((InfoComment)this.list.get(paramInt)).getNickname());
      localViewHolder1.textTime.setText(DateTools.getPretime(this.mContext, Long.valueOf(((InfoComment)this.list.get(paramInt)).getCommentTime()).longValue()));
      localViewHolder1.textFloor.setText(((InfoComment)this.list.get(paramInt)).getFloorId() + "楼");
      localViewHolder1.textContent.setText(((InfoComment)this.list.get(paramInt)).getContent());
      if ((((InfoComment)this.list.get(paramInt)).getParentId() == null) || (((InfoComment)this.list.get(paramInt)).getParentId().equals("0")))
        break label650;
      localViewHolder1.textReply.setVisibility(0);
      localViewHolder1.textReply.setText("回复 " + ((InfoComment)this.list.get(paramInt)).getReplyName() + ":  " + ((InfoComment)this.list.get(paramInt)).getReplyContent());
    }
    while (true)
    {
      localViewHolder1.buttonReply.setTag(Integer.valueOf(paramInt));
      localViewHolder1.buttonReply.setOnClickListener(this.mBackListener);
      localViewHolder1.textArea.setText("");
      return paramView;
      localViewHolder1.imageAvatar.setImageResource(2130837672);
      break;
      label650: localViewHolder1.textReply.setVisibility(8);
    }
  }

  public int getViewTypeCount()
  {
    return 2;
  }

  class CallBackListener
    implements OnClickListener
  {
    CallBackListener()
    {
    }

    public void onClick(View paramView)
    {
      switch (paramView.getId())
      {
      default:
        return;
      case 2131100409:
      }
      if (KouDaiSP.getInstance(InformationDetailAdapter.this.mContext).getUserId().equals(""))
      {
        Toast.makeText(InformationDetailAdapter.this.mContext, "请先登录", 0).show();
        return;
      }
      Message localMessage = InformationDetailAdapter.this.mHandler.obtainMessage(1, InformationDetailAdapter.this.list.get(((Integer)paramView.getTag()).intValue()));
      InformationDetailAdapter.this.mHandler.sendMessage(localMessage);
    }
  }

  class ViewHolder1
  {
    public Button buttonReply;
    public ImageView imageAvatar;
    public TextView textArea;
    public TextView textContent;
    public TextView textFloor;
    public TextView textName;
    public TextView textReply;
    public TextView textTime;

    ViewHolder1()
    {
    }
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.adapter.InformationDetailAdapter
 * JD-Core Version:    0.6.0
 */