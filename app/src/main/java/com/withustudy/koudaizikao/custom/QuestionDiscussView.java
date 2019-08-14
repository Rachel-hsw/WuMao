package com.withustudy.koudaizikao.custom;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import com.withustudy.koudaizikao.adapter.QuestionDiscussChildAdapter;
import java.util.List;

public class QuestionDiscussView extends LinearLayout
{
  private Button buttonDiscuss;
  private ImageView imageAvatar;
  private ImageView imageDivider;
  private List<String> listChild;
  private AdaptiveListView listView;
  private QuestionDiscussChildAdapter mAdapter;
  private CallBackListener mBackListener;
  private Context mContext;
  private TextView textContent;
  private TextView textFloor;
  private TextView textTime;
  private TextView textUserName;

  public QuestionDiscussView(Context paramContext)
  {
    super(paramContext);
    this.mContext = paramContext;
    initView();
  }

  public QuestionDiscussView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.mContext = paramContext;
    initView();
  }

  public QuestionDiscussView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    this.mContext = paramContext;
    initView();
  }

  @TargetApi(21)
  public QuestionDiscussView(Context paramContext, AttributeSet paramAttributeSet, int paramInt1, int paramInt2)
  {
    super(paramContext, paramAttributeSet, paramInt1, paramInt2);
    this.mContext = paramContext;
    initView();
  }

  private void initView()
  {
    this.mBackListener = new CallBackListener();
    View localView = LayoutInflater.from(this.mContext).inflate(2130903099, null);
    this.imageAvatar = ((ImageView)localView.findViewById(2131100069));
    this.textUserName = ((TextView)localView.findViewById(2131100070));
    this.textFloor = ((TextView)localView.findViewById(2131100071));
    this.textContent = ((TextView)localView.findViewById(2131100072));
    this.textTime = ((TextView)localView.findViewById(2131100073));
    this.listView = ((AdaptiveListView)localView.findViewById(2131100076));
    this.buttonDiscuss = ((Button)localView.findViewById(2131100074));
    this.imageDivider = ((ImageView)localView.findViewById(2131100075));
    this.buttonDiscuss.setOnClickListener(this.mBackListener);
    this.listView.setOnItemClickListener(this.mBackListener);
    this.listView.setSelector(2131230724);
    addView(localView);
    LayoutParams localLayoutParams = new LayoutParams(-1, -2);
    localLayoutParams.setMargins(20, 0, 20, 0);
    localView.setLayoutParams(localLayoutParams);
  }

  public void setDiscussButtonVisibility(int paramInt)
  {
    this.buttonDiscuss.setVisibility(paramInt);
  }

  public void setFloorVisibility(int paramInt)
  {
    this.textFloor.setVisibility(paramInt);
  }

  public void setImageAvatar(int paramInt)
  {
    this.imageAvatar.setBackgroundResource(paramInt);
  }

  public void setImageAvatar(Bitmap paramBitmap)
  {
    this.imageAvatar.setImageBitmap(paramBitmap);
  }

  public void setImageDividerVisibility(int paramInt)
  {
    this.imageDivider.setVisibility(paramInt);
  }

  public void setList(QuestionDiscussChildAdapter paramQuestionDiscussChildAdapter, List<String> paramList)
  {
    this.mAdapter = paramQuestionDiscussChildAdapter;
    this.listChild = paramList;
    this.listView.setAdapter(paramQuestionDiscussChildAdapter);
  }

  public void setListVisibility(int paramInt)
  {
    this.listView.setVisibility(paramInt);
  }

  public void setTextContent(String paramString)
  {
    this.textContent.setText(paramString);
  }

  public void setTextFloor(String paramString)
  {
    this.textFloor.setText(paramString);
  }

  public void setTextTime(String paramString)
  {
    this.textTime.setText(paramString);
  }

  public void setTextUsername(String paramString)
  {
    this.textUserName.setText(paramString);
  }

  class CallBackListener
    implements OnClickListener, OnItemClickListener
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
      case 2131100074:
      }
      Toast.makeText(QuestionDiscussView.this.mContext, "评论", 0).show();
    }

    public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
    {
      if (paramInt == -1 + QuestionDiscussView.this.mAdapter.getCount())
      {
        QuestionDiscussView.this.listChild.add("");
        QuestionDiscussView.this.listChild.add("");
        QuestionDiscussView.this.listChild.add("");
        QuestionDiscussView.this.mAdapter.notifyDataSetChanged();
      }
    }
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.custom.QuestionDiscussView
 * JD-Core Version:    0.6.0
 */