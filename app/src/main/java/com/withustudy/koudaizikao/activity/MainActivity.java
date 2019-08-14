package com.withustudy.koudaizikao.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.Gson;
import com.umeng.analytics.MobclickAgent;
import com.withustudy.koudaizikao.R;
import com.withustudy.koudaizikao.action.AbsBaseUrlConstructor;
import com.withustudy.koudaizikao.action.AbsSimpleImplUrlFactory;
import com.withustudy.koudaizikao.action.UrlFactory;
import com.withustudy.koudaizikao.base.AbsBaseActivity;
import com.withustudy.koudaizikao.config.Constants.BrushType;
import com.withustudy.koudaizikao.config.KouDaiSP;
import com.withustudy.koudaizikao.db.DbService;
import com.withustudy.koudaizikao.entity.MockPushState;
import com.withustudy.koudaizikao.entity.PersonalInfo;
import com.withustudy.koudaizikao.entity.ResponseStatus;
import com.withustudy.koudaizikao.entity.SequencePushState;
import com.withustudy.koudaizikao.entity.SmartPushState;
import com.withustudy.koudaizikao.entity.Version;
import com.withustudy.koudaizikao.entity.content.MajorContent;
import com.withustudy.koudaizikao.entity.req.MajorUpLoad;
import com.withustudy.koudaizikao.entity.req.UserSubject;
import com.withustudy.koudaizikao.entity.req.push.ReqPushAnser;
import com.withustudy.koudaizikao.entity.req.push.UserAnswers;
import com.withustudy.koudaizikao.fragment.BBSFragment;
import com.withustudy.koudaizikao.fragment.BrushMainFragment;
import com.withustudy.koudaizikao.fragment.InformationFragment;
import com.withustudy.koudaizikao.fragment.PersonalFragment;
import com.withustudy.koudaizikao.tools.LogUtils;
import com.withustudy.koudaizikao.tools.ToolsUtils;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.query.QueryBuilder;
import de.greenrobot.dao.query.WhereCondition;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import koudai.db.BrushExcerciseBatch;
import koudai.db.BrushExcerciseBatchDao;
import koudai.db.UserAns;
import koudai.db.UserAnsDao;
import koudai.db.UserAnsDao.Properties;

public class MainActivity extends AbsBaseActivity
  implements OnClickListener
{
  public static final int ACTION_GET_USER_INFO = 10;
  public static final int ACTION_UPDATE = 11;
  public static final int GET_USER_INFO = 99;
  private static final int push_anser = 100;
  private LinearLayout bbs_ll_tab;
  private BrushExcerciseBatchDao brushExcerciseBatchsDao;
  private LinearLayout brush_ll_tab;
  private UserAnsDao dao;
  private DbService dbService;
  private String excerciseBrushId;
  private long exitTime = 0L;
  private Handler handler = new Handler()
  {
    public void handleMessage(Message paramMessage)
    {
      switch (paramMessage.what)
      {
      default:
      case 0:
      case 99:
      }
      MajorContent localMajorContent;
      do
      {
        do
          return;
        while (MainActivity.this.reListener == null);
        MainActivity.this.reListener.refreshRank();
        return;
        localMajorContent = (MajorContent)paramMessage.obj;
      }
      while (localMajorContent.getMajor() == null);
      MainActivity.this.mSP.setProId(localMajorContent.getMajor().getProvId());
      MainActivity.this.mSP.setProName(localMajorContent.getMajor().getProvName());
      MainActivity.this.mSP.setMajorId(localMajorContent.getMajor().getMajorId());
      MainActivity.this.mSP.setMajorName(localMajorContent.getMajor().getMajorName());
    }
  };
  private ImageView[] imageTab;
  private Fragment mBbsFragment;
  private BrushMainFragment mBrushFragment;
  private Fragment mMyselfFragment;
  private Fragment mNewsFragment;
  private MockPushState mockPushState;
  private long mockTime;
  private LinearLayout myself_ll_tab;
  private LinearLayout news_ll_tab;
  private PushRec pushRec;
  private String pushSujectId;
  private RefreshRankListener reListener;
  private SequencePushState sequencePushState;
  private SmartPushState smartPushState;
  private TextView[] textTab;
  private int tryCount = 0;
  private String type = "";
  List<UserAnswers> userList = new ArrayList();

  private void cacheBrushExcerciseId()
  {
    BrushExcerciseBatch localBrushExcerciseBatch = new BrushExcerciseBatch();
    localBrushExcerciseBatch.setBrushExcerciseId(this.excerciseBrushId);
    localBrushExcerciseBatch.setBrushType(this.type);
    localBrushExcerciseBatch.setMockCostTime(Long.valueOf(this.mockTime));
    this.brushExcerciseBatchsDao.insert(localBrushExcerciseBatch);
  }

  private void checkBrushID()
  {
    Iterator localIterator = this.brushExcerciseBatchsDao.queryBuilder().list().iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return;
      BrushExcerciseBatch localBrushExcerciseBatch = (BrushExcerciseBatch)localIterator.next();
      pushOneBatch(localBrushExcerciseBatch.getBrushExcerciseId(), localBrushExcerciseBatch.getBrushType(), localBrushExcerciseBatch.getMockCostTime().longValue());
    }
  }

  private void deleteCache()
  {
    new Thread(new Runnable()
    {
      public void run()
      {
        List localList = MainActivity.this.dao.queryBuilder().where(UserAnsDao.Properties.ExcerciseBrushId.eq(MainActivity.this.excerciseBrushId), new WhereCondition[0]).list();
        MainActivity.this.dao.deleteInTx(localList);
      }
    }).start();
  }

  private List<String> getUserAnsStr(UserAnswers paramUserAnswers, String paramString)
  {
    String[] arrayOfString = paramString.split("%%%%");
    List localList = paramUserAnswers.getUserAnswer();
    int i;
    if ((arrayOfString != null) && (arrayOfString.length > 0))
      i = arrayOfString.length;
    for (int j = 0; ; j++)
    {
      if (j >= i)
        return localList;
      String str = arrayOfString[j];
      LogUtils.myLog("string=" + str);
      localList.add(str);
    }
  }

  private void hideFragment(FragmentTransaction paramFragmentTransaction)
  {
    if (this.mBrushFragment != null)
      paramFragmentTransaction.hide(this.mBrushFragment);
    if (this.mNewsFragment != null)
      paramFragmentTransaction.hide(this.mNewsFragment);
    if (this.mBbsFragment != null)
      paramFragmentTransaction.hide(this.mBbsFragment);
    if (this.mMyselfFragment != null)
      paramFragmentTransaction.hide(this.mMyselfFragment);
  }

  private void initPushAns()
  {
    this.pushRec = new PushRec();
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addAction("com.koudai.pushAnsers");
    registerReceiver(this.pushRec, localIntentFilter);
    this.dbService = DbService.getInstance(this.mContext);
    this.dao = this.dbService.getUserAnsDao();
    this.brushExcerciseBatchsDao = this.dbService.getBrushExcerciseBatchDao();
  }

  private void pushOneBatch(String paramString1, String paramString2, long paramLong)
  {
    new Thread(new Runnable(paramString1, paramString2, paramLong)
    {
      public void run()
      {
        MainActivity.this.userList.clear();
        QueryBuilder localQueryBuilder = MainActivity.this.dao.queryBuilder();
        LogUtils.myLog("查找的批次id----------------------------------------------------" + this.val$brushId);
        Iterator localIterator = localQueryBuilder.where(UserAnsDao.Properties.ExcerciseBrushId.eq(this.val$brushId), new WhereCondition[0]).list().iterator();
        while (true)
        {
          if (!localIterator.hasNext())
          {
            LogUtils.myLog("题目数=" + MainActivity.this.userList.size());
            LogUtils.myLog("推送题目数=" + MainActivity.this.userList.size());
            ReqPushAnser localReqPushAnser = new ReqPushAnser();
            localReqPushAnser.setUserAnswers(MainActivity.this.userList);
            localReqPushAnser.setVersionName(MainActivity.this.mSP.getVersionName());
            localReqPushAnser.setClientType(ToolsUtils.getSDK());
            localReqPushAnser.setImei(ToolsUtils.getImei(MainActivity.this.mContext));
            localReqPushAnser.setNet(ToolsUtils.getStringNetworkType(MainActivity.this.mContext));
            UserSubject localUserSubject = new UserSubject();
            localUserSubject.setSubjectId(MainActivity.this.pushSujectId);
            localUserSubject.setUid(MainActivity.this.mSP.getUserId());
            localReqPushAnser.setUserSubject(localUserSubject);
            localReqPushAnser.setBrushType(this.val$type);
            localReqPushAnser.setMockCostTime(this.val$mockTime);
            UrlFactory.getInstance().postUserAnserList().constructUrl(MainActivity.this, localReqPushAnser, 100);
            return;
          }
          UserAns localUserAns = (UserAns)localIterator.next();
          Long localLong = localUserAns.getBrushTime();
          String str1 = localUserAns.getExerciseId();
          String str2 = localUserAns.getKpointId();
          String str3 = localUserAns.getUserAnswer();
          Double localDouble = localUserAns.getUserScore();
          Boolean localBoolean = localUserAns.getIsCorrect();
          UserAnswers localUserAnswers = new UserAnswers();
          localUserAnswers.setBrushTime(localLong.longValue());
          localUserAnswers.setCorrect(localBoolean.booleanValue());
          localUserAnswers.setUserScore(localDouble.doubleValue());
          localUserAnswers.setUserAnswer(MainActivity.this.getUserAnsStr(localUserAnswers, str3));
          localUserAnswers.setKpointId(str2);
          localUserAnswers.setExerciseId(str1);
          MainActivity.this.userList.add(localUserAnswers);
        }
      }
    }).start();
  }

  private void setImage(int paramInt)
  {
    int i = 0;
    if (i >= 4)
      return;
    if (i == paramInt)
    {
      switch (i)
      {
      default:
      case 0:
      case 1:
      case 2:
      case 3:
      }
      while (true)
      {
        this.textTab[i].setTextColor(getResources().getColor(R.color.activity_color));
        i++;
        break;
        this.imageTab[0].setBackgroundResource(2130837799);
        continue;
        this.imageTab[1].setBackgroundResource(2130837801);
        continue;
        this.imageTab[2].setBackgroundResource(2130837803);
        continue;
        this.imageTab[3].setBackgroundResource(2130837805);
      }
    }
    switch (i)
    {
    default:
    case 0:
    case 1:
    case 2:
    case 3:
    }
    while (true)
    {
      this.textTab[i].setTextColor(Color.parseColor("#666666"));
      break;
      this.imageTab[0].setBackgroundResource(2130837800);
      continue;
      this.imageTab[1].setBackgroundResource(2130837802);
      continue;
      this.imageTab[2].setBackgroundResource(2130837804);
      continue;
      this.imageTab[3].setBackgroundResource(2130837806);
    }
  }

  private void setSelect(int paramInt)
  {
    FragmentTransaction localFragmentTransaction = this.mFragmentManager.beginTransaction();
    hideFragment(localFragmentTransaction);
    switch (paramInt)
    {
    default:
      localFragmentTransaction.commit();
      return;
    case 0:
      MobclickAgent.onEvent(this.mContext, "tab_main_1");
      if (this.mBrushFragment == null)
      {
        this.mBrushFragment = new BrushMainFragment();
        localFragmentTransaction.add(2131099829, this.mBrushFragment);
      }
      while (true)
      {
        setImage(paramInt);
        break;
        localFragmentTransaction.show(this.mBrushFragment);
      }
    case 1:
      MobclickAgent.onEvent(this.mContext, "tab_main_2");
      if (this.mNewsFragment == null)
      {
        this.mNewsFragment = new InformationFragment();
        localFragmentTransaction.add(2131099829, this.mNewsFragment);
      }
      while (true)
      {
        setImage(paramInt);
        break;
        localFragmentTransaction.show(this.mNewsFragment);
      }
    case 2:
      MobclickAgent.onEvent(this.mContext, "tab_main_3");
      if (this.mBbsFragment == null)
      {
        this.mBbsFragment = new BBSFragment();
        localFragmentTransaction.add(2131099829, this.mBbsFragment);
      }
      while (true)
      {
        setImage(paramInt);
        break;
        localFragmentTransaction.show(this.mBbsFragment);
      }
    case 3:
    }
    MobclickAgent.onEvent(this.mContext, "tab_main_4");
    if (this.mMyselfFragment == null)
    {
      this.mMyselfFragment = new PersonalFragment();
      localFragmentTransaction.add(2131099829, this.mMyselfFragment);
    }
    while (true)
    {
      setImage(paramInt);
      break;
      localFragmentTransaction.show(this.mMyselfFragment);
    }
  }

  protected void bindData()
  {
    if (!this.mSP.getUserId().equals(""))
    {
      AbsBaseUrlConstructor localAbsBaseUrlConstructor1 = UrlFactory.getInstance().personal();
      String[] arrayOfString1 = new String[1];
      arrayOfString1[0] = this.mSP.getUserId();
      localAbsBaseUrlConstructor1.constructUrl(this, arrayOfString1, 10, this.mContext);
      AbsBaseUrlConstructor localAbsBaseUrlConstructor2 = UrlFactory.getInstance().Update();
      String[] arrayOfString2 = new String[2];
      arrayOfString2[0] = this.mSP.getUserId();
      arrayOfString2[1] = this.mSP.getVersionName();
      localAbsBaseUrlConstructor2.constructUrl(this, arrayOfString2, 11, this.mContext);
    }
  }

  protected void initData()
  {
    if (!this.mSP.getUserId().equals(""))
      setSelect(0);
    while (true)
    {
      initPushAns();
      return;
      setSelect(1);
    }
  }

  protected void initListener()
  {
    this.brush_ll_tab.setOnClickListener(this);
    this.news_ll_tab.setOnClickListener(this);
    this.bbs_ll_tab.setOnClickListener(this);
    this.myself_ll_tab.setOnClickListener(this);
  }

  protected void initView()
  {
    this.brush_ll_tab = ((LinearLayout)findViewById(2131099830));
    this.news_ll_tab = ((LinearLayout)findViewById(2131099833));
    this.bbs_ll_tab = ((LinearLayout)findViewById(2131099836));
    this.myself_ll_tab = ((LinearLayout)findViewById(2131099839));
    this.imageTab = new ImageView[4];
    this.imageTab[0] = ((ImageView)findViewById(2131099831));
    this.imageTab[1] = ((ImageView)findViewById(2131099834));
    this.imageTab[2] = ((ImageView)findViewById(2131099837));
    this.imageTab[3] = ((ImageView)findViewById(2131099840));
    this.textTab = new TextView[4];
    this.textTab[0] = ((TextView)findViewById(2131099832));
    this.textTab[1] = ((TextView)findViewById(2131099835));
    this.textTab[2] = ((TextView)findViewById(2131099838));
    this.textTab[3] = ((TextView)findViewById(2131099841));
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
      return;
    case 2131099830:
      if (this.mSP.getUserId().equals(""))
      {
        Toast.makeText(this.mContext, "请先登录", 0).show();
        Intent localIntent = new Intent(this.mContext.getApplicationContext(), LoginActivity.class);
        localIntent.putExtra("flag", "0");
        startActivity(localIntent);
        return;
      }
      setSelect(0);
      return;
    case 2131099833:
      setSelect(1);
      return;
    case 2131099836:
      setSelect(2);
      return;
    case 2131099839:
    }
    setSelect(3);
  }

  protected void onDestroy()
  {
    super.onDestroy();
    unregisterReceiver(this.pushRec);
  }

  public void onError(String paramString1, String paramString2, int paramInt)
  {
    super.onError(paramString1, paramString2, paramInt);
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramInt == 4)
    {
      if (System.currentTimeMillis() - this.exitTime > 2000L)
      {
        Toast.makeText(getApplicationContext(), "再按一次退出程序", 0).show();
        this.exitTime = System.currentTimeMillis();
      }
      while (true)
      {
        return true;
        finish();
        MobclickAgent.onKillProcess(this.mContext);
        System.exit(0);
      }
    }
    return super.onKeyDown(paramInt, paramKeyEvent);
  }

  protected void onStart()
  {
    super.onStart();
  }

  public void onSuccess(String paramString1, Map<String, String> paramMap, String paramString2, int paramInt)
  {
    super.onSuccess(paramString1, paramMap, paramString2, paramInt);
    switch (paramInt)
    {
    default:
    case 10:
    case 11:
    case 100:
    }
    while (true)
    {
      return;
      if (paramString1 == null)
        continue;
      try
      {
        PersonalInfo localPersonalInfo = (PersonalInfo)UrlFactory.getInstanceGson().fromJson(paramString1, PersonalInfo.class);
        if (localPersonalInfo != null)
        {
          this.mSP.setNickName(localPersonalInfo.getNickname());
          this.mSP.setAvatar(localPersonalInfo.getProfileUrl());
          return;
        }
        Toast.makeText(this.mContext, "获取用户信息失败", 0).show();
        return;
      }
      catch (Exception localException7)
      {
        localException7.printStackTrace();
        Toast.makeText(this.mContext, "服务器未响应，请稍后再试", 0).show();
        return;
      }
      if (paramString1 == null)
        continue;
      try
      {
        Version localVersion = (Version)UrlFactory.getInstanceGson().fromJson(paramString1, Version.class);
        if (localVersion != null)
        {
          if (!localVersion.getStatus().equals("STATUS_OK"))
            continue;
          Bundle localBundle2 = new Bundle();
          try
          {
            localBundle2.putSerializable("update", localVersion);
            startNewActivity(UpdateNewActivity.class, 0, 0, false, localBundle2);
            return;
          }
          catch (Exception localException5)
          {
          }
          label203: localException5.printStackTrace();
          Toast.makeText(this.mContext, "服务器未响应，请稍后再试", 0).show();
          return;
        }
        Toast.makeText(this.mContext, "服务器未能返回数据，请稍后再试", 0).show();
        return;
        if (paramString1 == null)
          continue;
        Gson localGson = UrlFactory.getInstanceGson();
        if (Constants.BrushType.SMART_BRUSH.equals(this.type))
          try
          {
            this.smartPushState = ((SmartPushState)localGson.fromJson(paramString1, SmartPushState.class));
            if (this.smartPushState == null)
              continue;
            ResponseStatus localResponseStatus4 = this.smartPushState.getResponseStatus();
            if ((localResponseStatus4 != null) && ("OK".equals(localResponseStatus4.getStatus())))
            {
              this.handler.sendEmptyMessage(0);
              deleteCache();
              Intent localIntent = new Intent();
              localIntent.setAction("com.koudai.update_smart_ui");
              Bundle localBundle1 = new Bundle();
              localBundle1.putSerializable("smartPushState", this.smartPushState);
              localIntent.putExtras(localBundle1);
              sendBroadcast(localIntent);
              return;
            }
            cacheBrushExcerciseId();
            pushOneBatch(this.excerciseBrushId, this.type, this.mockTime);
            this.tryCount = (1 + this.tryCount);
            if (this.tryCount != 1)
              continue;
            cacheBrushExcerciseId();
            return;
          }
          catch (Exception localException4)
          {
            localException4.printStackTrace();
            return;
          }
        if (Constants.BrushType.MOCK_EXAM.equals(this.type))
          try
          {
            this.mockPushState = ((MockPushState)localGson.fromJson(paramString1, MockPushState.class));
            if (this.mockPushState == null)
              continue;
            ResponseStatus localResponseStatus3 = this.mockPushState.getResponseStatus();
            if ((localResponseStatus3 != null) && ("OK".equals(localResponseStatus3.getStatus())))
            {
              this.handler.sendEmptyMessage(0);
              deleteCache();
              return;
            }
            cacheBrushExcerciseId();
            pushOneBatch(this.excerciseBrushId, this.type, this.mockTime);
            this.tryCount = (1 + this.tryCount);
            if (this.tryCount != 1)
              continue;
            cacheBrushExcerciseId();
            return;
          }
          catch (Exception localException3)
          {
            localException3.printStackTrace();
            return;
          }
        if (Constants.BrushType.SEQUENCE_BRUSH.equals(this.type))
          try
          {
            this.sequencePushState = ((SequencePushState)localGson.fromJson(paramString1, SequencePushState.class));
            if (this.sequencePushState == null)
              continue;
            ResponseStatus localResponseStatus2 = this.sequencePushState.getResponseStatus();
            if ((localResponseStatus2 != null) && ("OK".equals(localResponseStatus2.getStatus())))
            {
              this.handler.sendEmptyMessage(0);
              deleteCache();
              return;
            }
            cacheBrushExcerciseId();
            pushOneBatch(this.excerciseBrushId, this.type, this.mockTime);
            this.tryCount = (1 + this.tryCount);
            if (this.tryCount != 1)
              continue;
            cacheBrushExcerciseId();
            return;
          }
          catch (Exception localException2)
          {
            localException2.printStackTrace();
            return;
          }
        if (!Constants.BrushType.REAL_EXAM.equals(this.type))
          continue;
        try
        {
          this.mockPushState = ((MockPushState)localGson.fromJson(paramString1, MockPushState.class));
          if (this.mockPushState == null)
            continue;
          ResponseStatus localResponseStatus1 = this.mockPushState.getResponseStatus();
          if ((localResponseStatus1 != null) && ("OK".equals(localResponseStatus1.getStatus())))
          {
            this.handler.sendEmptyMessage(0);
            deleteCache();
            return;
          }
          cacheBrushExcerciseId();
          pushOneBatch(this.excerciseBrushId, this.type, this.mockTime);
          this.tryCount = (1 + this.tryCount);
          if (this.tryCount != 1)
            continue;
          cacheBrushExcerciseId();
          return;
        }
        catch (Exception localException1)
        {
          localException1.printStackTrace();
          return;
        }
      }
      catch (Exception localException6)
      {
        break label203;
      }
    }
  }

  protected void setContentView()
  {
    setContentView(2130903060);
  }

  public void setReListener(RefreshRankListener paramRefreshRankListener)
  {
    this.reListener = paramRefreshRankListener;
  }

  public class PushRec extends BroadcastReceiver
  {
    public PushRec()
    {
    }

    public void onReceive(Context paramContext, Intent paramIntent)
    {
      Bundle localBundle = paramIntent.getExtras();
      MainActivity.this.excerciseBrushId = ((String)localBundle.getSerializable("excerciseBrushId"));
      LogUtils.myLog("主界面收到广播  推送题目到服务器  excerciseBrushId=" + MainActivity.this.excerciseBrushId);
      MainActivity.this.type = localBundle.getString("type");
      MainActivity.this.pushSujectId = localBundle.getString("subjectId");
      MainActivity.this.mockTime = localBundle.getLong("mockTime");
      MainActivity.this.pushOneBatch(MainActivity.this.excerciseBrushId, MainActivity.this.type, MainActivity.this.mockTime);
    }
  }

  public static abstract interface RefreshRankListener
  {
    public abstract void refreshRank();
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.activity.MainActivity
 * JD-Core Version:    0.6.0
 */