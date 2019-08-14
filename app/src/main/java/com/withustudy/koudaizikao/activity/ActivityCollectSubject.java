package com.withustudy.koudaizikao.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.test.FlowLayout;
import com.google.gson.Gson;
import com.himamis.retex.renderer.android.LaTeXView;
import com.withustudy.koudaizikao.action.AbsSimpleImplUrlFactory;
import com.withustudy.koudaizikao.action.UrlFactory;
import com.withustudy.koudaizikao.base.AbsBaseActivity;
import com.withustudy.koudaizikao.config.KouDaiSP;
import com.withustudy.koudaizikao.entity.OpreationState;
import com.withustudy.koudaizikao.entity.Subject;
import com.withustudy.koudaizikao.entity.SubjectFavoriteExercise;
import com.withustudy.koudaizikao.entity.req.FavoriteExercise;
import com.withustudy.koudaizikao.entity.req.ReqDeleFavor;
import com.withustudy.koudaizikao.entity.req.UserSubject;
import com.withustudy.koudaizikao.tools.DateTools;
import com.withustudy.koudaizikao.tools.LogUtils;
import com.withustudy.koudaizikao.tools.ProTools;
import com.withustudy.koudaizikao.tools.ToolsUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ActivityCollectSubject extends AbsBaseActivity
  implements OnClickListener
{
  private static final int action_delete_id = 6;
  private static final int action_delete_id_fail = 7;
  public static final int activity_collect = 15;
  private static final int delete_item = 2;
  private static final int notify_delete = 3;
  private static final int refresh_data;
  private List<FavoriteExercise> cacheList = new ArrayList();
  private TextView collect2_tv_name;
  private CollectReceive collectReceive;
  private Button collect_btn_delete;
  private ImageButton collect_edit_switch;
  private LinearLayout collect_ll_back;
  private LinearLayout collect_ll_delete;
  private List<String> deList = new ArrayList();
  private List<String> deleteIds = new ArrayList();
  private Handler handler = new Handler()
  {
    public void handleMessage(Message paramMessage)
    {
      switch (paramMessage.what)
      {
      case 1:
      case 4:
      case 5:
      case 6:
      default:
        return;
      case 3:
        if (ActivityCollectSubject.this.cacheList != null)
          ActivityCollectSubject.this.cacheList.clear();
        Iterator localIterator2 = ActivityCollectSubject.this.sfe.getFavoriteExercise().iterator();
        while (true)
        {
          if (!localIterator2.hasNext())
          {
            if (ActivityCollectSubject.this.myCollect1Adapter != null)
              break;
            ActivityCollectSubject.this.myCollect1Adapter = new MyCollect2Adapter(ActivityCollectSubject.this);
            ActivityCollectSubject.this.mListView.setAdapter(ActivityCollectSubject.this.myCollect1Adapter);
            return;
          }
          FavoriteExercise localFavoriteExercise = (FavoriteExercise)localIterator2.next();
          String str = localFavoriteExercise.getExerciseId();
          if (ActivityCollectSubject.this.deList.contains(str))
            continue;
          ActivityCollectSubject.this.cacheList.add(localFavoriteExercise);
        }
        ActivityCollectSubject.this.myCollect1Adapter.notifyDataSetChanged();
        return;
      case 0:
        if (ActivityCollectSubject.this.cacheList != null)
          ActivityCollectSubject.this.cacheList.clear();
        ActivityCollectSubject.this.cacheList.addAll(ActivityCollectSubject.this.sfe.getFavoriteExercise());
        if (ActivityCollectSubject.this.myCollect1Adapter == null)
        {
          ActivityCollectSubject.this.myCollect1Adapter = new MyCollect2Adapter(ActivityCollectSubject.this);
          ActivityCollectSubject.this.mListView.setAdapter(ActivityCollectSubject.this.myCollect1Adapter);
          return;
        }
        ActivityCollectSubject.this.myCollect1Adapter.notifyDataSetChanged();
        return;
      case 2:
        Toast.makeText(ActivityCollectSubject.this.getApplicationContext(), "删除成功！", 0).show();
        Iterator localIterator1 = ActivityCollectSubject.this.itemEditState.keySet().iterator();
        while (true)
        {
          if (!localIterator1.hasNext())
          {
            ActivityCollectSubject.this.cacheList.clear();
            ActivityCollectSubject.this.cacheList.addAll(ActivityCollectSubject.this.temp);
            ActivityCollectSubject.this.cacheList.size();
            ActivityCollectSubject.this.myCollect1Adapter.notifyDataSetChanged();
            return;
          }
          Integer localInteger = (Integer)localIterator1.next();
          ActivityCollectSubject.this.itemEditState.put(localInteger, Boolean.valueOf(false));
        }
      case 7:
      }
      Toast.makeText(ActivityCollectSubject.this.getApplicationContext(), "删除失败！", 0).show();
    }
  };
  private boolean isEdit = false;
  private HashMap<Integer, Boolean> itemEditState = new HashMap();
  private ListView mListView;
  private MyCollect2Adapter myCollect1Adapter;
  private String rtexcerciseId;
  private SubjectFavoriteExercise sfe;
  private Subject subject;
  private int t;
  private List<FavoriteExercise> temp;
  private String type;

  private void deleFavorExcerByIds()
  {
    this.mProTools.startDialog(true);
    Subject localSubject = this.sfe.getSubject();
    ReqDeleFavor localReqDeleFavor = new ReqDeleFavor();
    localReqDeleFavor.setVersionName(this.mSP.getVersionName());
    localReqDeleFavor.setClientType(ToolsUtils.getSDK());
    localReqDeleFavor.setImei(ToolsUtils.getImei(this.mContext));
    localReqDeleFavor.setNet(ToolsUtils.getStringNetworkType(this.mContext));
    UserSubject localUserSubject = new UserSubject();
    localUserSubject.setUid(this.mSP.getUserId());
    localUserSubject.setSubjectId(localSubject.getId());
    localReqDeleFavor.setUserSubject(localUserSubject);
    localReqDeleFavor.setExerciseId(this.deleteIds);
    UrlFactory.getInstance().deleteFavorExcercise().constructUrl(this, localReqDeleFavor, 6);
  }

  private void deleteCheck()
  {
    int i;
    if (this.isEdit)
    {
      this.temp = new ArrayList();
      this.deleteIds.clear();
      if ((this.cacheList != null) && (this.cacheList.size() > 0))
      {
        i = 0;
        if (i < this.cacheList.size())
          break label66;
        deleFavorExcerByIds();
      }
    }
    return;
    label66: Boolean localBoolean = (Boolean)this.itemEditState.get(Integer.valueOf(i));
    FavoriteExercise localFavoriteExercise = (FavoriteExercise)this.cacheList.get(i);
    if ((localBoolean == null) || (!localBoolean.booleanValue()))
      this.temp.add(localFavoriteExercise);
    while (true)
    {
      i++;
      break;
      String str = localFavoriteExercise.getExerciseId();
      this.deleteIds.add(str);
    }
  }

  private void goBack()
  {
    if (this.isEdit)
    {
      boolean bool1 = this.isEdit;
      boolean bool2 = false;
      if (bool1);
      while (true)
      {
        this.isEdit = bool2;
        goEditUI();
        return;
        bool2 = true;
      }
    }
    finish(0, 0);
  }

  private void goEditUI()
  {
    if (this.isEdit)
    {
      this.collect_edit_switch.setVisibility(8);
      this.myCollect1Adapter.notifyDataSetChanged();
      this.collect_ll_delete.setVisibility(0);
      return;
    }
    this.collect_edit_switch.setVisibility(0);
    this.myCollect1Adapter.notifyDataSetChanged();
    this.collect_ll_delete.setVisibility(8);
  }

  private void initCollectReceive()
  {
    this.collectReceive = new CollectReceive();
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addAction("com.koudai.collect");
    registerReceiver(this.collectReceive, localIntentFilter);
  }

  private List<View> scaningString(String paramString, boolean paramBoolean)
  {
    if ((paramString != null) && (!paramString.equals("")) && (paramString.length() > 0))
    {
      ArrayList localArrayList = new ArrayList();
      this.t = 0;
      String str1 = "";
      int i = 0;
      if (i >= paramString.length())
      {
        if (!str1.equals(""))
        {
          TextView localTextView3 = new TextView(this);
          localTextView3.setText(str1);
          localTextView3.setTextColor(getResources().getColor(2131230741));
          localArrayList.add(localTextView3);
        }
        return localArrayList;
      }
      String str2 = paramString.substring(i, i + 1);
      if (str2.equals("$"))
      {
        TextView localTextView1 = new TextView(this);
        localTextView1.setTextColor(getResources().getColor(2131230741));
        localTextView1.setText(str1);
        localArrayList.add(localTextView1);
        str1 = "";
        i += 2;
      }
      while (true)
      {
        i++;
        break;
        if (str2.equals("#"))
        {
          LaTeXView localLaTeXView = new LaTeXView(this);
          localLaTeXView.setLatexText(str1);
          localArrayList.add(localLaTeXView);
          str1 = "";
          i += 2;
          continue;
        }
        if (str2.equals("_"))
        {
          if ((i + 1 < paramString.length()) && (i + 2 < paramString.length()) && (i + 3 < paramString.length()) && (i + 4 < paramString.length()))
          {
            String str3 = paramString.substring(i + 1, i + 2);
            String str4 = paramString.substring(i + 2, i + 3);
            String str5 = paramString.substring(i + 3, i + 4);
            if ((str3.equals("_")) && (str4.equals("_")) && (str5.equals("_")))
            {
              TextView localTextView2 = new TextView(this);
              localTextView2.setText(str1);
              localTextView2.setTextColor(getResources().getColor(2131230741));
              localArrayList.add(localTextView2);
              str1 = "";
              ImageView localImageView = new ImageView(this);
              this.t = (1 + this.t);
              switch (this.t)
              {
              default:
              case 1:
              case 2:
              case 3:
              case 4:
              case 5:
              }
              while (true)
              {
                localArrayList.add(localImageView);
                i += 4;
                break;
                localImageView.setBackgroundResource(2130837813);
                localImageView.setTag("tk1");
                continue;
                localImageView.setBackgroundResource(2130837823);
                localImageView.setTag("tk2");
                continue;
                localImageView.setBackgroundResource(2130837825);
                localImageView.setTag("tk3");
                continue;
                localImageView.setBackgroundResource(2130837827);
                localImageView.setTag("tk4");
                continue;
                localImageView.setBackgroundResource(2130837829);
                localImageView.setTag("tk5");
              }
            }
            str1 = str1 + str2 + str3 + str4 + str5;
            i += 3;
            continue;
          }
          str1 = str1 + str2;
          continue;
        }
        str1 = str1 + str2;
      }
    }
    return null;
  }

  protected void bindData()
  {
  }

  protected void initData()
  {
    Bundle localBundle = getIntent().getExtras();
    try
    {
      this.sfe = ((SubjectFavoriteExercise)localBundle.getSerializable("SubjectFavoriteExercise"));
      this.subject = this.sfe.getSubject();
      label33: if (this.sfe != null)
        this.handler.sendEmptyMessage(0);
      initCollectReceive();
      return;
    }
    catch (Exception localException)
    {
      break label33;
    }
  }

  protected void initListener()
  {
    this.collect_ll_back.setOnClickListener(this);
    this.collect_ll_delete.setOnClickListener(this);
    this.collect_edit_switch.setOnClickListener(this);
    this.collect_btn_delete.setOnClickListener(this);
    this.mListView.setOnItemClickListener(new OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
      {
        if (ActivityCollectSubject.this.isEdit)
        {
          Boolean localBoolean = (Boolean)ActivityCollectSubject.this.itemEditState.get(Integer.valueOf(paramInt));
          View localView1 = paramView.findViewById(2131100052);
          View localView2 = paramView.findViewById(2131100048);
          View localView3 = paramView.findViewById(2131100049);
          if ((localBoolean == null) || (!localBoolean.booleanValue()))
          {
            localView1.setVisibility(8);
            localView2.setVisibility(0);
            localView3.setVisibility(0);
            localView3.setBackgroundResource(2130837638);
            ActivityCollectSubject.this.itemEditState.put(Integer.valueOf(paramInt), Boolean.valueOf(true));
            return;
          }
          localView1.setVisibility(8);
          localView2.setVisibility(0);
          localView3.setVisibility(0);
          localView3.setBackgroundResource(2130837924);
          ActivityCollectSubject.this.itemEditState.put(Integer.valueOf(paramInt), Boolean.valueOf(false));
          return;
        }
        ((FavoriteExercise)ActivityCollectSubject.this.cacheList.get(paramInt));
        Bundle localBundle = new Bundle();
        Subject localSubject = ActivityCollectSubject.this.sfe.getSubject();
        String str1 = localSubject.getId();
        String str2 = localSubject.getName();
        localBundle.putString("subjectId", str1);
        localBundle.putString("subjectName", str2);
        localBundle.putInt("FromPage", 15);
        localBundle.putSerializable("favoriteExercise", (FavoriteExercise)ActivityCollectSubject.this.cacheList.get(paramInt));
        ActivityCollectSubject.this.startNewActivity(QuestionDetailActivity.class, 2130968581, 2130968579, false, localBundle);
      }
    });
  }

  protected void initView()
  {
    this.collect_edit_switch = ((ImageButton)findViewById(2131099755));
    this.collect2_tv_name = ((TextView)findViewById(2131099754));
    this.mListView = ((ListView)findViewById(2131099756));
    this.collect_ll_delete = ((LinearLayout)findViewById(2131099757));
    this.collect_btn_delete = ((Button)findViewById(2131099758));
    this.collect_ll_back = ((LinearLayout)findViewById(2131099753));
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    case 2131099754:
    case 2131099756:
    case 2131099757:
    default:
      return;
    case 2131099755:
      if (this.isEdit);
      for (boolean bool = false; ; bool = true)
      {
        this.isEdit = bool;
        goEditUI();
        return;
      }
    case 2131099758:
      deleteCheck();
      return;
    case 2131099753:
    }
    goBack();
  }

  protected void onDestroy()
  {
    super.onDestroy();
    unregisterReceiver(this.collectReceive);
  }

  protected void onStart()
  {
    super.onStart();
    this.handler.sendEmptyMessage(3);
  }

  public void onSuccess(String paramString1, Map<String, String> paramMap, String paramString2, int paramInt)
  {
    super.onSuccess(paramString1, paramMap, paramString2, paramInt);
    this.mProTools.dismissDislog();
    if (paramString1 != null)
      try
      {
        OpreationState localOpreationState = (OpreationState)UrlFactory.getInstanceGson().fromJson(paramString1, OpreationState.class);
        if (localOpreationState != null)
        {
          if (localOpreationState.getStatus().equals("OK"))
          {
            this.handler.sendEmptyMessage(2);
            return;
          }
          this.handler.sendEmptyMessage(7);
          return;
        }
      }
      catch (Exception localException)
      {
      }
  }

  protected void setContentView()
  {
    setContentView(2130903051);
  }

  public class CollectReceive extends BroadcastReceiver
  {
    public CollectReceive()
    {
    }

    public void onReceive(Context paramContext, Intent paramIntent)
    {
      Bundle localBundle = paramIntent.getExtras();
      ActivityCollectSubject.this.rtexcerciseId = ((String)localBundle.getSerializable("excerciseId"));
      ActivityCollectSubject.this.type = localBundle.getString("type");
      if (!"0".equals(ActivityCollectSubject.this.type))
        ActivityCollectSubject.this.deList.add(ActivityCollectSubject.this.rtexcerciseId);
      LogUtils.myLog("CollectReceive excerciseId  " + ActivityCollectSubject.this.rtexcerciseId + "   type  " + ActivityCollectSubject.this.type);
    }
  }

  class MyCollect2Adapter extends BaseAdapter
  {
    public MyCollect2Adapter()
    {
    }

    public int getCount()
    {
      return ActivityCollectSubject.this.cacheList.size();
    }

    public Object getItem(int paramInt)
    {
      return null;
    }

    public long getItemId(int paramInt)
    {
      return 0L;
    }

    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      ViewHolder localViewHolder;
      long l;
      Boolean localBoolean;
      label245: Iterator localIterator;
      if (paramView == null)
      {
        paramView = View.inflate(ActivityCollectSubject.this.getApplicationContext(), 2130903094, null);
        localViewHolder = new ViewHolder();
        localViewHolder.collect_fl_stem = ((FlowLayout)paramView.findViewById(2131100055));
        localViewHolder.collect_tv_name = ((TextView)paramView.findViewById(2131100051));
        localViewHolder.collect_tv_time = ((TextView)paramView.findViewById(2131100048));
        localViewHolder.edit_iv = ((ImageView)paramView.findViewById(2131100049));
        localViewHolder.subject_tv_edit_time = ((TextView)paramView.findViewById(2131100052));
        localViewHolder.tv_sn = ((TextView)paramView.findViewById(2131100050));
        localViewHolder.excer_typeLabel = ((TextView)paramView.findViewById(2131100053));
        paramView.setTag(localViewHolder);
        FavoriteExercise localFavoriteExercise = (FavoriteExercise)ActivityCollectSubject.this.cacheList.get(paramInt);
        String str1 = ActivityCollectSubject.this.subject.getName();
        localViewHolder.collect_tv_name.setText(str1);
        if (localFavoriteExercise != null)
        {
          l = localFavoriteExercise.getTime();
          localBoolean = (Boolean)ActivityCollectSubject.this.itemEditState.get(Integer.valueOf(paramInt));
          if (!ActivityCollectSubject.this.isEdit)
            break label449;
          localViewHolder.edit_iv.setVisibility(0);
          if ((localBoolean != null) && (localBoolean.booleanValue()))
            break label436;
          localViewHolder.edit_iv.setBackgroundResource(2130837924);
          String str4 = DateTools.dateFormatYMD(l);
          localViewHolder.subject_tv_edit_time.setText(str4);
          localViewHolder.collect_tv_time.setText(str4);
          localViewHolder.collect_tv_time.setVisibility(0);
          localViewHolder.subject_tv_edit_time.setVisibility(8);
          localViewHolder.excer_typeLabel.setText(localFavoriteExercise.getCategory());
          if ((localViewHolder.collect_fl_stem != null) && (localViewHolder.collect_fl_stem.getChildCount() > 0))
          {
            localViewHolder.collect_fl_stem.removeAllViews();
            localViewHolder.collect_fl_stem.setOption(true);
          }
          String str3 = localFavoriteExercise.getStemText();
          LogUtils.myLog("scanString===-----------------" + str3);
          List localList = ActivityCollectSubject.this.scaningString(str3, false);
          if ((localList == null) || (localList.size() <= 0))
            break label569;
          localIterator = localList.iterator();
        }
      }
      while (true)
      {
        if (!localIterator.hasNext())
        {
          localViewHolder.collect_fl_stem.setVisibility(0);
          return paramView;
          localViewHolder = (ViewHolder)paramView.getTag();
          break;
          label436: localViewHolder.edit_iv.setBackgroundResource(2130837638);
          break label245;
          label449: if ((localBoolean == null) || (!localBoolean.booleanValue()))
            localViewHolder.edit_iv.setBackgroundResource(2130837924);
          while (true)
          {
            localViewHolder.edit_iv.setVisibility(8);
            String str2 = DateTools.dateFormatYMD(l);
            localViewHolder.collect_tv_time.setText(str2);
            localViewHolder.subject_tv_edit_time.setText(str2);
            localViewHolder.collect_tv_time.setVisibility(0);
            localViewHolder.subject_tv_edit_time.setVisibility(8);
            break;
            localViewHolder.edit_iv.setBackgroundResource(2130837638);
          }
        }
        View localView = (View)localIterator.next();
        localViewHolder.collect_fl_stem.addView(localView);
      }
      label569: localViewHolder.collect_fl_stem.setVisibility(8);
      return paramView;
    }

    class ViewHolder
    {
      private FlowLayout collect_fl_stem;
      private TextView collect_tv_name;
      private TextView collect_tv_time;
      private ImageView edit_iv;
      private TextView excer_typeLabel;
      private TextView subject_tv_edit_time;
      private TextView tv_sn;

      ViewHolder()
      {
      }
    }
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.activity.ActivityCollectSubject
 * JD-Core Version:    0.6.0
 */