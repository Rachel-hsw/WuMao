package com.withustudy.koudaizikao.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.android.http.FileDownLoad;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.DisplayImageOptions.Builder;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.umeng.analytics.MobclickAgent;
import com.withustudy.koudaizikao.base.AbsBaseActivity;
import com.withustudy.koudaizikao.config.KouDaiSP;
import com.withustudy.koudaizikao.entity.req.ExerciseIdList;
import com.withustudy.koudaizikao.tools.LogUtils;
import com.withustudy.koudaizikao.tools.MyLog;
import java.util.HashMap;
import java.util.Map;

public class SimulationInitActivity extends AbsBaseActivity
  implements OnClickListener
{
  public static HashMap<Integer, String> levelToString = new HashMap()
  {
    private static final long serialVersionUID = 1L;
  };
  public static final int simulation_init = 3;
  private LinearLayout bg_ll;
  private Bundle bundle;
  private TextView examinee_tv;
  private ExerciseIdList exerciseIdList;
  private int level = 1;
  private LinearLayout simu_init_back_ll;
  private Button simulation_high_level_btn;
  private Button simulation_low_level_btn;
  private Button simulation_medium_level_btn;
  private RelativeLayout start_simulation_btn;
  private String subjectId;
  private String subjectName;
  private TextView subject_name_tv;
  private ImageView userImag;

  private void loadImage(ImageView paramImageView, String paramString)
  {
    DisplayImageOptions localDisplayImageOptions = new DisplayImageOptions.Builder().showImageOnLoading(2130837666).showImageForEmptyUri(2130837672).showImageOnFail(2130837672).cacheInMemory(true).cacheOnDisc(true).displayer(new RoundedBitmapDisplayer(110)).build();
    ImageLoader.getInstance().displayImage(paramString, paramImageView, localDisplayImageOptions);
  }

  private void resetBg()
  {
    this.simulation_low_level_btn.setTextColor(getResources().getColor(2131230737));
    this.simulation_medium_level_btn.setTextColor(getResources().getColor(2131230737));
    this.simulation_high_level_btn.setTextColor(getResources().getColor(2131230737));
    this.simulation_low_level_btn.setBackgroundResource(2131230724);
    this.simulation_medium_level_btn.setBackgroundResource(2131230724);
    this.simulation_high_level_btn.setBackgroundResource(2131230724);
  }

  private void select(int paramInt)
  {
    this.level = paramInt;
    resetBg();
    switch (paramInt)
    {
    default:
      return;
    case 0:
      this.simulation_low_level_btn.setTextColor(getResources().getColor(2131230725));
      this.simulation_low_level_btn.setBackgroundResource(2130837549);
      return;
    case 1:
      this.simulation_medium_level_btn.setTextColor(getResources().getColor(2131230725));
      this.simulation_medium_level_btn.setBackgroundResource(2130837549);
      return;
    case 2:
    }
    this.simulation_high_level_btn.setTextColor(getResources().getColor(2131230725));
    this.simulation_high_level_btn.setBackgroundResource(2130837549);
  }

  protected void bindData()
  {
  }

  protected void initData()
  {
    this.examinee_tv.setText(this.mSP.getNickName());
    this.subject_name_tv.setText(this.subjectName);
    String str = this.mSP.getAvatar();
    if ((str == null) || (str.equals("")))
    {
      loadImage(this.userImag, "drawable://2130837672");
      return;
    }
    this.mFileDownLoad.downLoadImage(str, this.userImag, 1000);
  }

  protected void initListener()
  {
    this.start_simulation_btn.setOnClickListener(this);
    this.simulation_low_level_btn.setOnClickListener(this);
    this.simulation_medium_level_btn.setOnClickListener(this);
    this.simulation_high_level_btn.setOnClickListener(this);
    this.simu_init_back_ll.setOnClickListener(this);
  }

  protected void initView()
  {
    try
    {
      this.bundle = getIntent().getExtras();
      this.subjectId = this.bundle.getString("subjectId");
      this.subjectName = this.bundle.getString("subjectName");
      label37: this.start_simulation_btn = ((RelativeLayout)findViewById(2131099986));
      this.userImag = ((ImageView)findViewById(2131099952));
      this.simulation_low_level_btn = ((Button)findViewById(2131099983));
      this.simulation_medium_level_btn = ((Button)findViewById(2131099984));
      this.simulation_high_level_btn = ((Button)findViewById(2131099985));
      this.subject_name_tv = ((TextView)findViewById(2131099981));
      this.examinee_tv = ((TextView)findViewById(2131099980));
      this.simu_init_back_ll = ((LinearLayout)findViewById(2131099979));
      this.bg_ll = ((LinearLayout)findViewById(2131099982));
      return;
    }
    catch (Exception localException)
    {
      break label37;
    }
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    case 2131099980:
    case 2131099981:
    case 2131099982:
    default:
      return;
    case 2131099986:
      MobclickAgent.onEvent(this.mContext, "test_start");
      MyLog.log("模拟考用户的信息", "\n");
      MyLog.log("username", this.examinee_tv.getText().toString().trim());
      MyLog.log("level", this.level);
      Toast.makeText(getApplicationContext(), "开始考试", 0).show();
      MyLog.log("subjectName", this.examinee_tv.getText().toString().trim());
      Bundle localBundle = new Bundle();
      localBundle.putInt("FromPage", 3);
      localBundle.putString("level", (String)levelToString.get(Integer.valueOf(this.level)));
      localBundle.putString("subjectId", this.subjectId);
      localBundle.putString("subjectName", this.subjectName);
      startNewActivity(QuestionDetailActivity.class, 2130968581, 2130968579, false, localBundle);
      finish(0, 0);
      return;
    case 2131099983:
      MobclickAgent.onEvent(this.mContext, "test_low_difficult");
      select(0);
      return;
    case 2131099984:
      MobclickAgent.onEvent(this.mContext, "test_middle_difficult");
      select(1);
      return;
    case 2131099985:
      MobclickAgent.onEvent(this.mContext, "test_hight_difficult");
      select(2);
      return;
    case 2131099979:
    }
    finish();
  }

  public void onSuccess(String paramString1, Map<String, String> paramMap, String paramString2, int paramInt)
  {
    super.onSuccess(paramString1, paramMap, paramString2, paramInt);
    if (paramString1 != null)
    {
      this.exerciseIdList = ((ExerciseIdList)new Gson().fromJson(paramString1, ExerciseIdList.class));
      if (this.exerciseIdList != null)
      {
        Bundle localBundle = new Bundle();
        localBundle.putInt("FromPage", 3);
        localBundle.putString("level", (String)levelToString.get(Integer.valueOf(this.level)));
        localBundle.putString("subjectId", this.subjectId);
        startNewActivity(QuestionDetailActivity.class, 2130968581, 2130968579, false, localBundle);
        return;
      }
      LogUtils.myLog("模拟考id集合解析异常");
      return;
    }
    LogUtils.myLog("模拟考id集为null");
  }

  protected void setContentView()
  {
    setContentView(2130903080);
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.activity.SimulationInitActivity
 * JD-Core Version:    0.6.0
 */