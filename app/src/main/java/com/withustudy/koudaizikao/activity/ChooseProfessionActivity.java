package com.withustudy.koudaizikao.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.Gson;
import com.umeng.analytics.MobclickAgent;
import com.withustudy.koudaizikao.action.AbsBaseUrlConstructor;
import com.withustudy.koudaizikao.action.UrlFactory;
import com.withustudy.koudaizikao.adapter.ChooseProfessionAdapter;
import com.withustudy.koudaizikao.adapter.ChooseProvinceAdapter;
import com.withustudy.koudaizikao.base.AbsBaseActivity;
import com.withustudy.koudaizikao.config.KouDaiSP;
import com.withustudy.koudaizikao.custom.AdaptiveGridView;
import com.withustudy.koudaizikao.entity.ChooseProToChooseSub;
import com.withustudy.koudaizikao.entity.Major;
import com.withustudy.koudaizikao.entity.Province;
import com.withustudy.koudaizikao.entity.content.ProvMajorsContent;
import com.withustudy.koudaizikao.tools.DbTools;
import com.withustudy.koudaizikao.tools.ProTools;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ChooseProfessionActivity extends AbsBaseActivity
{
  public static final int ACTION_GET_PROV = 10;
  public static final String FINISH_CHOOSE_PROFESSION_ACTIVITY = "finish_choose_profession_activity";
  private Button buttonBack;
  private Button buttonSave;
  private AdaptiveGridView gridProfession;
  private AdaptiveGridView gridProvince;
  private List<Province> listProvince;
  private CallBackListener mBackListener;
  private ChooseProfessionReceiver mBroadcastReceiver;
  private ChooseProfessionAdapter mProfessionAdapter;
  private ChooseProvinceAdapter mProvinceAdapter;
  private Major major;
  private String proId;
  private TextView textSuggestion;

  private void setMajorList(int paramInt)
  {
    this.mProfessionAdapter = new ChooseProfessionAdapter(this.mContext, ((Province)this.listProvince.get(paramInt)).getMajor());
    int i;
    int j;
    if (!this.mSP.getMajorId().equals(""))
    {
      i = 0;
      j = 0;
      if (j >= ((Province)this.listProvince.get(paramInt)).getMajor().size())
        if (i == 0)
          this.mProfessionAdapter.setChoosen(0);
    }
    while (true)
    {
      this.gridProfession.setAdapter(this.mProfessionAdapter);
      this.gridProfession.setSelector(2131230724);
      this.major = new Major();
      if ((this.mSP.getMajorName().equals("")) || (!this.mSP.getProId().equals(this.proId)))
        break label261;
      this.major.setMajorId(this.mSP.getMajorId());
      this.major.setMajorName(this.mSP.getMajorName());
      this.major.setProvName(this.mSP.getProName());
      return;
      if (((Major)((Province)this.listProvince.get(paramInt)).getMajor().get(j)).getMajorId().equals(this.mSP.getMajorId()))
      {
        this.mProfessionAdapter.setChoosen(j);
        i = 1;
      }
      j++;
      break;
      this.mProfessionAdapter.setChoosen(0);
    }
    label261: this.major = ((Major)this.mProfessionAdapter.getItem(0));
  }

  protected void bindData()
  {
    this.mProTools.startDialog();
    AbsBaseUrlConstructor localAbsBaseUrlConstructor = UrlFactory.getInstance().getProv();
    String[] arrayOfString = new String[1];
    arrayOfString[0] = this.mSP.getUserId();
    localAbsBaseUrlConstructor.constructUrl(this, arrayOfString, 10, this.mContext);
  }

  protected void initData()
  {
    this.mBackListener = new CallBackListener();
    this.listProvince = new ArrayList();
    this.mBroadcastReceiver = new ChooseProfessionReceiver();
    IntentFilter localIntentFilter = new IntentFilter("finish_choose_profession_activity");
    registerReceiver(this.mBroadcastReceiver, localIntentFilter);
  }

  protected void initListener()
  {
    this.buttonBack.setOnClickListener(this.mBackListener);
    this.gridProvince.setOnItemClickListener(this.mBackListener);
    this.gridProfession.setOnItemClickListener(this.mBackListener);
    this.buttonSave.setOnClickListener(this.mBackListener);
    this.textSuggestion.setOnClickListener(this.mBackListener);
  }

  protected void initView()
  {
    this.buttonBack = ((Button)findViewById(2131099742));
    this.buttonSave = ((Button)findViewById(2131099745));
    this.textSuggestion = ((TextView)findViewById(2131099746));
    this.gridProvince = ((AdaptiveGridView)findViewById(2131099743));
    this.gridProfession = ((AdaptiveGridView)findViewById(2131099744));
  }

  protected void onDestroy()
  {
    super.onDestroy();
    unregisterReceiver(this.mBroadcastReceiver);
  }

  public void onError(String paramString1, String paramString2, int paramInt)
  {
    super.onError(paramString1, paramString2, paramInt);
    this.mProTools.dismissDislog();
    Toast.makeText(this.mContext, "服务器未响应，请稍后再试", 0).show();
  }

  public void onSuccess(String paramString1, Map<String, String> paramMap, String paramString2, int paramInt)
  {
    super.onSuccess(paramString1, paramMap, paramString2, paramInt);
    this.mProTools.dismissDislog();
    switch (paramInt)
    {
    default:
    case 10:
    }
    do
      return;
    while (paramString1 == null);
    while (true)
    {
      ProvMajorsContent localProvMajorsContent;
      int i;
      int j;
      try
      {
        localProvMajorsContent = (ProvMajorsContent)UrlFactory.getInstanceGson().fromJson(paramString1, ProvMajorsContent.class);
        if (localProvMajorsContent == null)
          break label362;
        this.listProvince.clear();
        this.listProvince.addAll(localProvMajorsContent.getProvMajors());
        if (this.mSP.getProId().equals(""))
          break label314;
        i = 0;
        j = 0;
        if (j >= localProvMajorsContent.getProvMajors().size())
        {
          if (i != 0)
            continue;
          this.mProvinceAdapter = new ChooseProvinceAdapter(this.mContext, this.listProvince, 0);
          this.proId = ((Province)this.listProvince.get(0)).getProvId();
          setMajorList(0);
          this.gridProvince.setAdapter(this.mProvinceAdapter);
          this.gridProvince.setSelector(2131230724);
          DbTools.deleteProvinceList1(getApplicationContext());
          DbTools.saveProvinceList1(getApplicationContext(), localProvMajorsContent);
          return;
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        Toast.makeText(this.mContext, "服务器未响应，请稍后再试", 0).show();
        return;
      }
      if (((Province)localProvMajorsContent.getProvMajors().get(j)).getProvId().equals(this.mSP.getProId()))
      {
        this.mProvinceAdapter = new ChooseProvinceAdapter(this.mContext, this.listProvince, j);
        this.proId = ((Province)this.listProvince.get(j)).getProvId();
        setMajorList(j);
        i = 1;
        break label377;
        label314: this.mProvinceAdapter = new ChooseProvinceAdapter(this.mContext, this.listProvince, 0);
        this.proId = ((Province)this.listProvince.get(0)).getProvId();
        setMajorList(0);
        continue;
        label362: Toast.makeText(this.mContext, "服务器未能返回数据，请稍后再试", 0).show();
        return;
      }
      label377: j++;
    }
  }

  protected void setContentView()
  {
    setContentView(2130903048);
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
      case 2131099743:
      case 2131099744:
      default:
        return;
      case 2131099742:
        ChooseProfessionActivity.this.finish();
        return;
      case 2131099745:
        if ((ChooseProfessionActivity.this.proId == null) || (ChooseProfessionActivity.this.proId.equals("")) || (ChooseProfessionActivity.this.major == null))
        {
          Toast.makeText(ChooseProfessionActivity.this.mContext, "请先选择省份和专业", 0).show();
          return;
        }
        MobclickAgent.onEvent(ChooseProfessionActivity.this.mContext, "personal_choose_subject");
        ChooseProToChooseSub localChooseProToChooseSub = new ChooseProToChooseSub();
        localChooseProToChooseSub.setProId(ChooseProfessionActivity.this.proId);
        localChooseProToChooseSub.setProvName(ChooseProfessionActivity.this.major.getProvName());
        localChooseProToChooseSub.setMajorId(ChooseProfessionActivity.this.major.getMajorId());
        localChooseProToChooseSub.setMajorName(ChooseProfessionActivity.this.major.getMajorName());
        Bundle localBundle = new Bundle();
        localBundle.putSerializable("Major", localChooseProToChooseSub);
        ChooseProfessionActivity.this.startNewActivity(ChooseSubjectActivity.class, false, localBundle);
        return;
      case 2131099746:
      }
      ChooseProfessionActivity.this.startNewActivity(SuggestionActivity.class, false, null);
    }

    public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
    {
      switch (paramAdapterView.getId())
      {
      default:
        return;
      case 2131099743:
        ChooseProfessionActivity.this.mProvinceAdapter.setChoosen(paramInt);
        ChooseProfessionActivity.this.mProvinceAdapter.notifyDataSetChanged();
        ChooseProfessionActivity.this.proId = ((Province)ChooseProfessionActivity.this.listProvince.get(paramInt)).getProvId();
        ChooseProfessionActivity.this.setMajorList(paramInt);
        return;
      case 2131099744:
      }
      ChooseProfessionActivity.this.mProfessionAdapter.setChoosen(paramInt);
      ChooseProfessionActivity.this.mProfessionAdapter.notifyDataSetChanged();
      ChooseProfessionActivity.this.major = ((Major)ChooseProfessionActivity.this.mProfessionAdapter.getItem(paramInt));
    }
  }

  class ChooseProfessionReceiver extends BroadcastReceiver
  {
    ChooseProfessionReceiver()
    {
    }

    public void onReceive(Context paramContext, Intent paramIntent)
    {
      if (ChooseProfessionActivity.this != null)
        ChooseProfessionActivity.this.finish();
    }
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.activity.ChooseProfessionActivity
 * JD-Core Version:    0.6.0
 */