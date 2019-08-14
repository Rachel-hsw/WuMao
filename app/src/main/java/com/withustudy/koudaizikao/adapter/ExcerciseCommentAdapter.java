package com.withustudy.koudaizikao.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.http.FileDownLoad;
import com.withustudy.koudaizikao.entity.req.Comment;
import com.withustudy.koudaizikao.entity.req.UserInfo;
import com.withustudy.koudaizikao.tools.DateTools;
import com.withustudy.koudaizikao.tools.ToolsUtils;
import java.util.List;

public class ExcerciseCommentAdapter extends BaseAdapter
{
  public static final int TYPE1 = 0;
  public static final int TYPE2 = 1;
  private List<Comment> list;
  private CallBackListener mBackListener;
  private Context mContext;
  private Handler mHandler;

  public ExcerciseCommentAdapter(Context paramContext, List<Comment> paramList, Handler paramHandler)
  {
    this.mContext = paramContext;
    this.list = paramList;
    this.mBackListener = new CallBackListener();
    this.mHandler = paramHandler;
  }

  public int getCount()
  {
    if (this.list == null)
      return 0;
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
    ViewHolder1 localViewHolder1;
    Comment localComment;
    String str4;
    if (paramView == null)
    {
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
      localComment = (Comment)this.list.get(paramInt);
      UserInfo localUserInfo = localComment.getUserInfo();
      String str1 = localUserInfo.getNickname();
      localViewHolder1.textName.setText(str1);
      String str2 = localUserInfo.getHeadPic();
      if ((str2 != null) && (!str2.equals("")))
        FileDownLoad.getInstance(this.mContext).downLoadImage(localUserInfo.getHeadPic(), localViewHolder1.imageAvatar, 100);
      long l = localComment.getCommentTime();
      String str3 = DateTools.getPretime(this.mContext, l);
      localViewHolder1.textTime.setText(str3);
      localViewHolder1.textFloor.setText(paramInt + 1 + "楼");
      localViewHolder1.textContent.setText(Html.fromHtml(ToolsUtils.formatText(localComment.getContent(), "#343434")));
      str4 = localComment.getReplyFloorContent();
      if ((str4 != null) && (!str4.equals("")))
        break label391;
      localViewHolder1.textReply.setVisibility(8);
    }
    while (true)
    {
      Button localButton = localViewHolder1.buttonReply;
      1 local1 = new OnClickListener(paramInt)
      {
        public void onClick(View paramView)
        {
          Comment localComment = (Comment)ExcerciseCommentAdapter.this.list.get(this.val$position);
          Infor localInfor = new Infor(ExcerciseCommentAdapter.this);
          localInfor.comment = localComment;
          localInfor.postion = this.val$position;
          Message localMessage = ExcerciseCommentAdapter.this.mHandler.obtainMessage(7, localInfor);
          ExcerciseCommentAdapter.this.mHandler.sendMessage(localMessage);
        }
      };
      localButton.setOnClickListener(local1);
      localViewHolder1.textArea.setText(localComment.getLocation());
      return paramView;
      localViewHolder1 = (ViewHolder1)paramView.getTag();
      break;
      label391: localViewHolder1.textReply.setVisibility(0);
      String[] arrayOfString = str4.split(":");
      if ((arrayOfString == null) || (arrayOfString.length != 2))
        continue;
      String str5 = arrayOfString[0];
      String str6 = arrayOfString[1];
      Html.fromHtml(ToolsUtils.formatText(str5, "#D6D6D6"));
      localViewHolder1.textReply.setText(Html.fromHtml(ToolsUtils.formatText(new StringBuilder(String.valueOf(str5)).append(":").toString(), "#D7D7D7") + ToolsUtils.formatText(str6, "#747474")));
    }
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
      case 2131100409:
      }
    }
  }

  public class Infor
  {
    public Comment comment;
    public int itemHight;
    public int postion;

    public Infor()
    {
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
 * Qualified Name:     com.withustudy.koudaizikao.adapter.ExcerciseCommentAdapter
 * JD-Core Version:    0.6.0
 */