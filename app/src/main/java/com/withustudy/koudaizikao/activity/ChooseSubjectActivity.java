package com.withustudy.koudaizikao.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.Toast;
import com.google.gson.Gson;
import com.withustudy.koudaizikao.action.AbsSimpleImplUrlFactory;
import com.withustudy.koudaizikao.action.UrlFactory;
import com.withustudy.koudaizikao.adapter.ChooseSubjectAdapter;
import com.withustudy.koudaizikao.base.AbsBaseActivity;
import com.withustudy.koudaizikao.config.Constants.Subject;
import com.withustudy.koudaizikao.config.KouDaiSP;
import com.withustudy.koudaizikao.custom.AdaptiveGridView;
import com.withustudy.koudaizikao.entity.ChooseProToChooseSub;
import com.withustudy.koudaizikao.entity.MajorAndSubject;
import com.withustudy.koudaizikao.entity.Subject;
import com.withustudy.koudaizikao.entity.SubjectW;
import com.withustudy.koudaizikao.entity.content.ResultStatus;
import com.withustudy.koudaizikao.entity.req.FindSubject;
import com.withustudy.koudaizikao.entity.req.MajorUpLoad;
import com.withustudy.koudaizikao.entity.req.UploadMajorAndSubject;
import com.withustudy.koudaizikao.fragment.PersonalFragment;
import com.withustudy.koudaizikao.fragment.PersonalFragment.PersonalHandler;
import com.withustudy.koudaizikao.tools.ProTools;
import com.withustudy.koudaizikao.tools.ToolsUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ChooseSubjectActivity extends AbsBaseActivity
{
  public static final int ACTION_GET_SUBJECT = 10;
  public static final int ACTION_SAVE_SUBJECT = 11;
  public static final int GET_SUBJECT = 1;
  public static final int GET_SUBJECT_FAIL = 2;
  public static final int ON_ERROR = 5;
  public static final int UPDATE_SUBJECT = 3;
  public static final int UPDATE_SUBJECT_FAIL = 4;
  private Button buttonBack;
  private Button buttonSave;
  private AdaptiveGridView gridSubject;
  private List<Subject> list;
  private ChooseSubjectAdapter mAdapter;
  private CallBackListener mBackListener;
  private ChooseProToChooseSub mChooseProToChooseSub;
  private ChooseSubjectHandler mHandler;

  private boolean checkClicked()
  {
    int i = 1;
    int[] arrayOfInt = this.mAdapter.getChlicked();
    int j = 0;
    for (int k = 0; ; k++)
    {
      if (k >= arrayOfInt.length)
      {
        if (j == 0)
          i = 0;
        return i;
      }
      if (arrayOfInt[k] != i)
        continue;
      j++;
    }
  }

  protected void bindData()
  {
    this.mProTools.startDialog();
    FindSubject localFindSubject = new FindSubject();
    localFindSubject.setVersionName(this.mSP.getVersionName());
    localFindSubject.setClientType(ToolsUtils.getSDK());
    localFindSubject.setImei(ToolsUtils.getImei(this.mContext));
    localFindSubject.setNet(ToolsUtils.getStringNetworkType(this.mContext));
    localFindSubject.setUid(this.mSP.getUserId());
    MajorUpLoad localMajorUpLoad = new MajorUpLoad();
    localMajorUpLoad.setMajorId(this.mChooseProToChooseSub.getMajorId());
    localMajorUpLoad.setMajorName(this.mChooseProToChooseSub.getMajorName());
    localMajorUpLoad.setProvName(this.mChooseProToChooseSub.getProvName());
    localFindSubject.setMajor(localMajorUpLoad);
    UrlFactory.getInstance().getSelectSubject().constructUrl(this, localFindSubject, 10);
  }

  protected void initData()
  {
    this.mChooseProToChooseSub = ((ChooseProToChooseSub)getIntent().getExtras().getSerializable("Major"));
    this.mHandler = new ChooseSubjectHandler();
    this.mBackListener = new CallBackListener();
    this.list = new ArrayList();
  }

  protected void initListener()
  {
    this.buttonBack.setOnClickListener(this.mBackListener);
    this.buttonSave.setOnClickListener(this.mBackListener);
    this.gridSubject.setOnItemClickListener(this.mBackListener);
  }

  protected void initView()
  {
    this.buttonBack = ((Button)findViewById(2131099747));
    this.buttonSave = ((Button)findViewById(2131099749));
    this.gridSubject = ((AdaptiveGridView)findViewById(2131099748));
  }

  public void onError(String paramString1, String paramString2, int paramInt)
  {
    super.onError(paramString1, paramString2, paramInt);
    this.mHandler.sendEmptyMessage(5);
  }

  public void onSuccess(String paramString1, Map<String, String> paramMap, String paramString2, int paramInt)
  {
    super.onSuccess(paramString1, paramMap, paramString2, paramInt);
    this.mProTools.dismissDislog();
    switch (paramInt)
    {
    default:
    case 10:
    case 11:
    }
    do
    {
      do
        return;
      while (paramString1 == null);
      try
      {
        SubjectW localSubjectW = (SubjectW)UrlFactory.getInstanceGson().fromJson(paramString1, SubjectW.class);
        if ((localSubjectW != null) && (localSubjectW.getSubject() != null))
        {
          this.mHandler.sendMessage(this.mHandler.obtainMessage(1, localSubjectW.getSubject()));
          return;
        }
      }
      catch (Exception localException2)
      {
        localException2.printStackTrace();
        Toast.makeText(this.mContext, "服务器未响应，请稍后再试", 0).show();
        return;
      }
      this.mHandler.sendEmptyMessage(2);
      return;
    }
    while (paramString1 == null);
    try
    {
      ResultStatus localResultStatus = (ResultStatus)UrlFactory.getInstanceGson().fromJson(paramString1, ResultStatus.class);
      if ((localResultStatus != null) && (localResultStatus.getStatus().equals("OK")))
      {
        this.mHandler.sendEmptyMessage(3);
        return;
      }
    }
    catch (Exception localException1)
    {
      localException1.printStackTrace();
      Toast.makeText(this.mContext, "服务器未响应，请稍后再试", 0).show();
      return;
    }
    this.mHandler.sendEmptyMessage(4);
  }

  protected void setContentView()
  {
    setContentView(2130903049);
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
      case 2131099748:
      default:
      case 2131099747:
      case 2131099749:
      }
      do
      {
        return;
        ChooseSubjectActivity.this.finish();
        return;
      }
      while (ChooseSubjectActivity.this.mAdapter == null);
      if (!ChooseSubjectActivity.this.checkClicked())
      {
        Toast.makeText(ChooseSubjectActivity.this.mContext, "请至少选择一个专业", 0).show();
        return;
      }
      ArrayList localArrayList = new ArrayList();
      int[] arrayOfInt = ChooseSubjectActivity.this.mAdapter.getChlicked();
      for (int i = 0; ; i++)
      {
        if (i >= arrayOfInt.length)
        {
          UploadMajorAndSubject localUploadMajorAndSubject = new UploadMajorAndSubject();
          localUploadMajorAndSubject.setVersionName(ChooseSubjectActivity.this.mSP.getVersionName());
          localUploadMajorAndSubject.setClientType(ToolsUtils.getSDK());
          localUploadMajorAndSubject.setImei(ToolsUtils.getImei(ChooseSubjectActivity.this.mContext));
          localUploadMajorAndSubject.setNet(ToolsUtils.getStringNetworkType(ChooseSubjectActivity.this.mContext));
          localUploadMajorAndSubject.setUid(ChooseSubjectActivity.this.mSP.getUserId());
          MajorUpLoad localMajorUpLoad = new MajorUpLoad();
          localMajorUpLoad.setMajorId(ChooseSubjectActivity.this.mChooseProToChooseSub.getMajorId());
          localMajorUpLoad.setMajorName(ChooseSubjectActivity.this.mChooseProToChooseSub.getMajorName());
          localMajorUpLoad.setProvId(ChooseSubjectActivity.this.mChooseProToChooseSub.getProId());
          localMajorUpLoad.setProvName(ChooseSubjectActivity.this.mChooseProToChooseSub.getProvName());
          MajorAndSubject localMajorAndSubject = new MajorAndSubject();
          localMajorAndSubject.setMajor(localMajorUpLoad);
          localMajorAndSubject.setSubject(localArrayList);
          localUploadMajorAndSubject.setMajorSubject(localMajorAndSubject);
          ChooseSubjectActivity.this.mProTools.startDialog();
          UrlFactory.getInstance().saveMajorAndSubject().constructUrl(ChooseSubjectActivity.this, localUploadMajorAndSubject, 11);
          return;
        }
        if (arrayOfInt[i] != 1)
          continue;
        localArrayList.add((Subject)ChooseSubjectActivity.this.list.get(i));
      }
    }

    public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
    {
      ChooseSubjectActivity.this.mAdapter.setChlicked(paramInt);
      ChooseSubjectActivity.this.mAdapter.notifyDataSetChanged();
    }
  }

  class ChooseSubjectHandler extends Handler
  {
    ChooseSubjectHandler()
    {
    }

    public void handleMessage(Message paramMessage)
    {
      super.handleMessage(paramMessage);
      switch (paramMessage.what)
      {
      default:
        return;
      case 1:
        ChooseSubjectActivity.this.list.clear();
        ChooseSubjectActivity.this.list.addAll((List)paramMessage.obj);
        ChooseSubjectActivity.this.mAdapter = new ChooseSubjectAdapter(ChooseSubjectActivity.this.list, ChooseSubjectActivity.this.mContext);
        List localList;
        int[] arrayOfInt;
        if (Constants.Subject.sId != null)
        {
          localList = Constants.Subject.sId;
          arrayOfInt = new int[ChooseSubjectActivity.this.list.size()];
        }
        for (int i = 0; ; i++)
        {
          if (i >= ChooseSubjectActivity.this.list.size())
          {
            ChooseSubjectActivity.this.mAdapter.setChlicked(arrayOfInt);
            ChooseSubjectActivity.this.gridSubject.setAdapter(ChooseSubjectActivity.this.mAdapter);
            ChooseSubjectActivity.this.gridSubject.setSelector(2131230724);
            return;
          }
          arrayOfInt[i] = 0;
          if (!localList.contains(((Subject)ChooseSubjectActivity.this.list.get(i)).getId()))
            continue;
          arrayOfInt[i] = 1;
        }
      case 2:
        Toast.makeText(ChooseSubjectActivity.this.mContext, "服务器未能返回数据，请稍后再试", 0).show();
        return;
      case 3:
        ChooseSubjectActivity.this.mSP.setProId(ChooseSubjectActivity.this.mChooseProToChooseSub.getProId());
        ChooseSubjectActivity.this.mSP.setProName(ChooseSubjectActivity.this.mChooseProToChooseSub.getProvName());
        ChooseSubjectActivity.this.mSP.setMajorId(ChooseSubjectActivity.this.mChooseProToChooseSub.getMajorId());
        ChooseSubjectActivity.this.mSP.setMajorName(ChooseSubjectActivity.this.mChooseProToChooseSub.getMajorName());
        ChooseSubjectActivity.this.mSP.setUpdateSubject(true);
        if (PersonalFragment.mHandler != null)
          PersonalFragment.mHandler.sendEmptyMessage(3);
        Intent localIntent = new Intent("finish_choose_profession_activity");
        ChooseSubjectActivity.this.mContext.sendBroadcast(localIntent);
        ChooseSubjectActivity.this.finish();
        return;
      case 4:
        Toast.makeText(ChooseSubjectActivity.this.mContext, "服务器未能返回数据，请稍后再试", 0).show();
        return;
      case 5:
      }
      ChooseSubjectActivity.this.mProTools.dismissDislog();
      Toast.makeText(ChooseSubjectActivity.this.mContext, "服务器未响应，请稍后再试", 0).show();
    }
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.activity.ChooseSubjectActivity
 * JD-Core Version:    0.6.0
 */