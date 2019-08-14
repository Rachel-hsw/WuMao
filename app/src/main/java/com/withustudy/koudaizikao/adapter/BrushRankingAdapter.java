package com.withustudy.koudaizikao.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.DisplayImageOptions.Builder;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.withustudy.koudaizikao.R;
import com.withustudy.koudaizikao.entity.UserBrushSummary;
import com.withustudy.koudaizikao.entity.UserMockSummary;
import com.withustudy.koudaizikao.entity.req.UserInfo;
import java.text.DecimalFormat;
import java.util.List;

public class BrushRankingAdapter extends BaseAdapter
{
  private List cacheData;
  private int index = -1;
  private Context mContext;
  private int type;

  public BrushRankingAdapter(int paramInt, Context paramContext, List paramList)
  {
    this.mContext = paramContext;
    this.cacheData = paramList;
    this.type = paramInt;
  }

  private void loadImage(ImageView paramImageView, String paramString)
  {
    DisplayImageOptions localDisplayImageOptions = new DisplayImageOptions.Builder().showImageOnLoading(2130837666).showImageForEmptyUri(2130837672).showImageOnFail(2130837672).cacheInMemory(true).cacheOnDisc(true).displayer(new RoundedBitmapDisplayer(1000)).build();
    ImageLoader.getInstance().displayImage(paramString, paramImageView, localDisplayImageOptions);
  }

  public int getCount()
  {
    return this.cacheData.size();
  }

  public Object getItem(int paramInt)
  {
    return this.cacheData.get(paramInt);
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
      paramView = LayoutInflater.from(this.mContext).inflate(2130903143, null);
      localViewHolder = new ViewHolder();
      localViewHolder.layout = ((LinearLayout)paramView.findViewById(2131100384));
      localViewHolder.imageAvatar = ((ImageView)paramView.findViewById(2131100386));
      localViewHolder.textName = ((TextView)paramView.findViewById(2131100387));
      localViewHolder.textRank = ((TextView)paramView.findViewById(2131100385));
      localViewHolder.textCountTitle = ((TextView)paramView.findViewById(2131100388));
      localViewHolder.textCount = ((TextView)paramView.findViewById(2131100389));
      localViewHolder.textAccuracyTitle = ((TextView)paramView.findViewById(2131100390));
      localViewHolder.textAccuracy = ((TextView)paramView.findViewById(2131100391));
      paramView.setTag(localViewHolder);
      switch (this.type)
      {
      default:
        if (paramInt != 0)
          break;
        localViewHolder.textRank.setBackgroundResource(2130837774);
        localViewHolder.textRank.setText(null);
      case 0:
      case 1:
      }
    }
    while (true)
    {
      if (this.index == -1)
        break label996;
      if (this.index != paramInt)
        break label776;
      localViewHolder.layout.setBackgroundColor(this.mContext.getResources().getColor(R.color.activity_color));
      localViewHolder.textRank.setTextColor(Color.parseColor("#ffffff"));
      localViewHolder.textName.setTextColor(Color.parseColor("#ffffff"));
      localViewHolder.textCountTitle.setTextColor(Color.parseColor("#ffffff"));
      localViewHolder.textCount.setTextColor(Color.parseColor("#ffffff"));
      localViewHolder.textAccuracyTitle.setTextColor(Color.parseColor("#ffffff"));
      localViewHolder.textAccuracy.setTextColor(Color.parseColor("#ffffff"));
      return paramView;
      localViewHolder = (ViewHolder)paramView.getTag();
      break;
      UserBrushSummary localUserBrushSummary = (UserBrushSummary)this.cacheData.get(paramInt);
      localViewHolder.textCountTitle.setVisibility(0);
      localViewHolder.textAccuracyTitle.setVisibility(0);
      UserInfo localUserInfo2 = localUserBrushSummary.getUserInfo();
      String str3 = localUserInfo2.getHeadPic();
      String str4 = localUserInfo2.getNickname();
      if ((str3 == null) || (str3.equals("")))
        loadImage(localViewHolder.imageAvatar, "drawable://2130837672");
      while (true)
      {
        localViewHolder.textName.setText(str4);
        localViewHolder.textCount.setText(localUserBrushSummary.getBrushNum() + "道");
        String str5 = new DecimalFormat("#.##").format(100.0D * localUserBrushSummary.getCorrectRate());
        localViewHolder.textAccuracy.setText(str5 + "%");
        break;
        loadImage(localViewHolder.imageAvatar, str3);
      }
      UserMockSummary localUserMockSummary = (UserMockSummary)this.cacheData.get(paramInt);
      UserInfo localUserInfo1 = localUserMockSummary.getUserInfo();
      String str1 = localUserInfo1.getHeadPic();
      String str2 = localUserInfo1.getNickname();
      localViewHolder.textCountTitle.setVisibility(4);
      localViewHolder.textAccuracyTitle.setVisibility(4);
      double d = localUserMockSummary.getScore();
      localViewHolder.textCount.setText(d + "分");
      localViewHolder.textName.setText(str2);
      if ((str1 == null) || (str1.equals("")))
        loadImage(localViewHolder.imageAvatar, "drawable://2130837672");
      while (true)
      {
        long l = localUserMockSummary.getCostTime();
        localViewHolder.textAccuracy.setText(l + "分钟");
        break;
        loadImage(localViewHolder.imageAvatar, str1);
      }
      if (paramInt == 1)
      {
        localViewHolder.textRank.setBackgroundResource(2130837775);
        localViewHolder.textRank.setText(null);
        continue;
      }
      if (paramInt == 2)
      {
        localViewHolder.textRank.setBackgroundResource(2130837776);
        localViewHolder.textRank.setText(null);
        continue;
      }
      localViewHolder.textRank.setBackgroundResource(2131230724);
      localViewHolder.textRank.setText(String.valueOf(paramInt + 1));
    }
    label776: localViewHolder.layout.setBackgroundColor(Color.parseColor("#ffffff"));
    if (paramInt < 3)
    {
      localViewHolder.textRank.setTextColor(this.mContext.getResources().getColor(R.color.activity_color));
      localViewHolder.textName.setTextColor(this.mContext.getResources().getColor(R.color.activity_color));
      localViewHolder.textCountTitle.setTextColor(Color.parseColor("#999999"));
      localViewHolder.textCount.setTextColor(this.mContext.getResources().getColor(R.color.activity_color));
      localViewHolder.textAccuracyTitle.setTextColor(Color.parseColor("#999999"));
      localViewHolder.textAccuracy.setTextColor(this.mContext.getResources().getColor(R.color.activity_color));
      return paramView;
    }
    localViewHolder.textRank.setTextColor(this.mContext.getResources().getColor(R.color.activity_color));
    localViewHolder.textName.setTextColor(Color.parseColor("#999999"));
    localViewHolder.textCountTitle.setTextColor(Color.parseColor("#999999"));
    localViewHolder.textCount.setTextColor(Color.parseColor("#7b7b7b"));
    localViewHolder.textAccuracyTitle.setTextColor(Color.parseColor("#999999"));
    localViewHolder.textAccuracy.setTextColor(Color.parseColor("#7b7b7b"));
    return paramView;
    label996: localViewHolder.layout.setBackgroundColor(Color.parseColor("#ffffff"));
    if (paramInt < 3)
    {
      localViewHolder.textRank.setTextColor(this.mContext.getResources().getColor(R.color.activity_color));
      localViewHolder.textName.setTextColor(this.mContext.getResources().getColor(R.color.activity_color));
      localViewHolder.textCountTitle.setTextColor(Color.parseColor("#999999"));
      localViewHolder.textCount.setTextColor(this.mContext.getResources().getColor(R.color.activity_color));
      localViewHolder.textAccuracyTitle.setTextColor(Color.parseColor("#999999"));
      localViewHolder.textAccuracy.setTextColor(this.mContext.getResources().getColor(R.color.activity_color));
      return paramView;
    }
    localViewHolder.textRank.setTextColor(this.mContext.getResources().getColor(R.color.activity_color));
    localViewHolder.textName.setTextColor(Color.parseColor("#999999"));
    localViewHolder.textCountTitle.setTextColor(Color.parseColor("#999999"));
    localViewHolder.textCount.setTextColor(Color.parseColor("#7b7b7b"));
    localViewHolder.textAccuracyTitle.setTextColor(Color.parseColor("#999999"));
    localViewHolder.textAccuracy.setTextColor(Color.parseColor("#7b7b7b"));
    return paramView;
  }

  public void setList(List paramList)
  {
    this.cacheData = paramList;
  }

  public void setMyItem(int paramInt)
  {
    if ((paramInt < 0) || (paramInt > -1 + this.cacheData.size()))
      return;
    this.index = paramInt;
  }

  class ViewHolder
  {
    public ImageView imageAvatar;
    public LinearLayout layout;
    public TextView textAccuracy;
    public TextView textAccuracyTitle;
    public TextView textCount;
    public TextView textCountTitle;
    public TextView textName;
    public TextView textRank;

    ViewHolder()
    {
    }
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.adapter.BrushRankingAdapter
 * JD-Core Version:    0.6.0
 */