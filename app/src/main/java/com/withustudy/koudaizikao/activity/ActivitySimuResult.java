package com.withustudy.koudaizikao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.android.http.FileDownLoad;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.DisplayImageOptions.Builder;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.umeng.analytics.MobclickAgent;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.withustudy.koudaizikao.base.AbsBaseActivity;
import com.withustudy.koudaizikao.config.KouDaiSP;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class ActivitySimuResult extends AbsBaseActivity
  implements OnClickListener
{
  public static final int scan_test_paper = 17;
  private int FromPage;
  private ImageButton chapter_weibo_share;
  private ImageButton chapter_weixin;
  private ImageButton chapter_weixin_pengyou;
  private int count;
  public HashMap<Integer, String> currRight;
  private int error;
  private ArrayList<Integer> errorList = new ArrayList();
  private ImageButton error_explain_ib;
  private Bundle extras;
  private TextView fenshu;
  public HashMap<Integer, Boolean> isHaveDone;
  private ImageView iv_level;
  private String level;
  private LinearLayout ll_simu_result_mock_rank;
  private TextView nandu;
  private ImageButton rank_ib;
  private ArrayList<Integer> rightList = new ArrayList();
  private ImageButton scan_test;
  private RelativeLayout simu_res_back;
  private TextView simu_res_name;
  private double statisticsSocre;
  private TextView subj_name;
  private String subjectId;
  private String subjectName;
  private double time;
  private TextView timeTv;
  private TextView tv_level;
  private ImageView userImag;
  private int userLevel = 0;

  private void devideLevel()
  {
    if ((this.statisticsSocre > 0.0D) && (this.statisticsSocre < 60.0D))
      this.userLevel = 0;
    while (true)
      switch (this.userLevel)
      {
      default:
        return;
        if ((this.statisticsSocre >= 60.0D) && (this.statisticsSocre <= 70.0D))
        {
          this.userLevel = 1;
          continue;
        }
        if (this.statisticsSocre < 70.0D)
          continue;
        this.userLevel = 2;
      case 0:
      case 1:
      case 2:
      }
    this.iv_level.setBackgroundResource(2130837934);
    this.tv_level.setText("不太理想！ 要努力啦~~");
    return;
    this.iv_level.setBackgroundResource(2130837935);
    this.tv_level.setText("及格万岁！ 再接再厉~~");
    return;
    this.tv_level.setText("棒棒哒！继续加油~~");
    this.iv_level.setBackgroundResource(2130837933);
  }

  private void goneMockButton()
  {
    switch (this.FromPage)
    {
    default:
      return;
    case 16:
    }
    this.ll_simu_result_mock_rank.setVisibility(8);
  }

  private void loadImage(ImageView paramImageView, String paramString)
  {
    DisplayImageOptions localDisplayImageOptions = new DisplayImageOptions.Builder().showImageOnLoading(2130837666).showImageForEmptyUri(2130837672).showImageOnFail(2130837672).cacheInMemory(true).cacheOnDisc(true).displayer(new RoundedBitmapDisplayer(110)).build();
    ImageLoader.getInstance().displayImage(paramString, paramImageView, localDisplayImageOptions);
  }

  protected void bindData()
  {
  }

  protected void initData()
  {
    try
    {
      this.extras = getIntent().getExtras();
      this.statisticsSocre = this.extras.getDouble("statisticsSocre");
      this.currRight = ((HashMap)this.extras.getSerializable("currRight"));
      this.level = this.extras.getString("level");
      this.count = this.extras.getInt("count");
      this.FromPage = this.extras.getInt("FromPage");
      this.subjectId = this.extras.getString("subjectId");
      this.time = this.extras.getDouble("time");
      this.subjectName = this.extras.getString("subjectName");
      label118: goneMockButton();
      devideLevel();
      String str1 = this.mSP.getAvatar();
      if ((str1 != null) && (!str1.equals("")))
        this.mFileDownLoad.downLoadImage(str1, this.userImag, 1000);
      String str2 = this.mSP.getNickName();
      this.simu_res_name.setText(str2);
      Iterator localIterator;
      if ("LOW".equals(this.level))
      {
        this.nandu.setText("低等");
        int i = (int)(this.time / 3600.0D);
        int j = (int)((this.time - i * 3600) / 60.0D);
        int k = (int)(this.time - i * 3600 - j * 60);
        this.timeTv.setText(i + "时" + j + "分" + k + "秒");
        this.subj_name.setText(this.subjectName);
        localIterator = this.currRight.keySet().iterator();
      }
      while (true)
      {
        if (!localIterator.hasNext())
        {
          (100.0D * (1.0D * this.rightList.size() / this.count));
          this.fenshu.setText(this.statisticsSocre);
          return;
          if ("MIDDLE".equals(this.level))
          {
            this.nandu.setText("中等");
            break;
          }
          if (!"HIGH".equals(this.level))
            break;
          this.nandu.setText("高级");
          break;
        }
        Integer localInteger = (Integer)localIterator.next();
        String str3 = (String)this.currRight.get(localInteger);
        if ((str3 == null) || (str3.equals("")))
        {
          this.errorList.add(localInteger);
          continue;
        }
        if ("true".equals(str3))
        {
          this.rightList.add(localInteger);
          continue;
        }
        this.errorList.add(localInteger);
      }
    }
    catch (Exception localException)
    {
      break label118;
    }
  }

  protected void initListener()
  {
    this.simu_res_back.setOnClickListener(this);
    this.scan_test.setOnClickListener(this);
    this.rank_ib.setOnClickListener(this);
    this.error_explain_ib.setOnClickListener(this);
    this.chapter_weibo_share.setOnClickListener(this);
    this.chapter_weixin_pengyou.setOnClickListener(this);
    this.chapter_weixin.setOnClickListener(this);
  }

  protected void initView()
  {
    this.simu_res_back = ((RelativeLayout)findViewById(2131099955));
    this.ll_simu_result_mock_rank = ((LinearLayout)findViewById(2131099962));
    this.fenshu = ((TextView)findViewById(2131099958));
    this.tv_level = ((TextView)findViewById(2131099957));
    this.simu_res_name = ((TextView)findViewById(2131099953));
    this.timeTv = ((TextView)findViewById(2131099959));
    this.nandu = ((TextView)findViewById(2131099960));
    this.subj_name = ((TextView)findViewById(2131099954));
    this.scan_test = ((ImageButton)findViewById(2131099961));
    this.rank_ib = ((ImageButton)findViewById(2131099963));
    this.error_explain_ib = ((ImageButton)findViewById(2131099964));
    this.userImag = ((ImageView)findViewById(2131099952));
    this.iv_level = ((ImageView)findViewById(2131099956));
    this.chapter_weibo_share = ((ImageButton)findViewById(2131099965));
    this.chapter_weixin_pengyou = ((ImageButton)findViewById(2131099966));
    this.chapter_weixin = ((ImageButton)findViewById(2131099967));
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    case 2131099956:
    case 2131099957:
    case 2131099958:
    case 2131099959:
    case 2131099960:
    case 2131099962:
    default:
      return;
    case 2131099966:
      MobclickAgent.onEvent(this.mContext, "test_con_share");
      share(SHARE_MEDIA.WEIXIN_CIRCLE, "http://share.kdzikao.com/app/share.page");
      return;
    case 2131099967:
      MobclickAgent.onEvent(this.mContext, "test_con_share");
      share(SHARE_MEDIA.WEIXIN, "http://share.kdzikao.com/app/share.page");
      return;
    case 2131099965:
      MobclickAgent.onEvent(this.mContext, "test_con_share");
      share(SHARE_MEDIA.SINA, "http://share.kdzikao.com/app/share.page");
      return;
    case 2131099955:
      MobclickAgent.onEvent(this.mContext, "test_close_con");
      setResult(102);
      finish(0, 0);
      return;
    case 2131099961:
      MobclickAgent.onEvent(this.mContext, "test_con_show_paper");
      setResult(100);
      finish(0, 0);
      return;
    case 2131099963:
      MobclickAgent.onEvent(this.mContext, "test_con_rank");
      Bundle localBundle = new Bundle();
      localBundle.putInt("type", 1);
      localBundle.putString("subjectId", this.subjectId);
      localBundle.putString("subjectName", this.subjectName);
      startNewActivity(BrushRankingActivity.class, 2130968581, 2130968579, false, localBundle);
      return;
    case 2131099964:
    }
    MobclickAgent.onEvent(this.mContext, "test_con_explain_error");
    setResult(101);
    finish(0, 0);
  }

  protected void setContentView()
  {
    setContentView(2130903076);
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.activity.ActivitySimuResult
 * JD-Core Version:    0.6.0
 */