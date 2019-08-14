package com.withustudy.koudaizikao.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.google.gson.Gson;
import com.withustudy.koudaizikao.action.AbsSimpleImplUrlFactory;
import com.withustudy.koudaizikao.action.UrlFactory;
import com.withustudy.koudaizikao.activity.ActivityErrorListActivity;
import com.withustudy.koudaizikao.activity.QuestionDetailActivity;
import com.withustudy.koudaizikao.base.AbsBaseFragment;
import com.withustudy.koudaizikao.config.KouDaiSP;
import com.withustudy.koudaizikao.entity.Chapter;
import com.withustudy.koudaizikao.entity.ErrorExercise;
import com.withustudy.koudaizikao.entity.SubjectErrorExercise;
import com.withustudy.koudaizikao.entity.SubjectErrorExerciseW;
import com.withustudy.koudaizikao.entity.Subjects;
import com.withustudy.koudaizikao.entity.req.UserSubject;
import com.withustudy.koudaizikao.tools.LogUtils;
import com.withustudy.koudaizikao.tools.ProTools;
import com.withustudy.koudaizikao.tools.ToolsUtils;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TimeZone;

public class ErrorListFragement extends AbsBaseFragment
{
  public static final long MILLIS_IN_DAY = 86400000L;
  public static final int SECONDS_IN_DAY = 86400;
  private static final int action_id_load_fm_data = 0;
  public static final int errorList_fragement = 10;
  private static final int sort_by_chapter = 3;
  private static final int sort_by_time = 2;
  private ActivityErrorListActivity activity;
  private List<ErrorExercise> currentData = new ArrayList();
  private int currentIndex;
  private List<String> deleteIds;
  private MyErrorAdapter errorAdapter;
  private Handler handler = new Handler()
  {
    List<ErrorExercise> list;
    SubjectErrorExercise subjectErrorExercise;

    public void handleMessage(Message paramMessage)
    {
      switch (paramMessage.what)
      {
      case 1:
      default:
        return;
      case 3:
        this.list = ((List)ErrorListFragement.this.activity.orignalData.get(Integer.valueOf(ErrorListFragement.this.currentIndex)));
        if (this.list == null)
        {
          this.list = new ArrayList();
          ErrorListFragement.this.activity.orignalData.put(Integer.valueOf(ErrorListFragement.this.currentIndex), this.list);
        }
        this.list.clear();
        this.subjectErrorExercise = ErrorListFragement.this.subjectErrorExerciseW.getSubjectErrorExercise();
        if (this.subjectErrorExercise != null)
        {
          List localList2 = this.subjectErrorExercise.getErrorExercise();
          if ((localList2 != null) && (localList2.size() > 0))
            this.list.addAll(localList2);
        }
        ErrorListFragement.this.currentData = this.list;
        ErrorListFragement.this.sortByChapter();
        return;
      case 2:
      }
      this.list = ((List)ErrorListFragement.this.activity.orignalData.get(Integer.valueOf(ErrorListFragement.this.currentIndex)));
      if (this.list == null)
      {
        this.list = new ArrayList();
        ErrorListFragement.this.activity.orignalData.put(Integer.valueOf(ErrorListFragement.this.currentIndex), this.list);
      }
      this.list.clear();
      this.subjectErrorExercise = ErrorListFragement.this.subjectErrorExerciseW.getSubjectErrorExercise();
      if (this.subjectErrorExercise != null)
      {
        List localList1 = this.subjectErrorExercise.getErrorExercise();
        if ((localList1 != null) && (localList1.size() > 0))
          this.list.addAll(localList1);
      }
      ErrorListFragement.this.currentData = this.list;
      ErrorListFragement.this.sortDataByTime();
    }
  };
  private boolean isShowTimeStyle;
  private List<ErrorExercise> latest;
  private ListView lv;
  private List<Entry<String, List<ErrorExercise>>> mHashMapEntryList;
  private List<Entry<String, List<ErrorExercise>>> mTimeHashMapEntryList;
  private List<String> mlist;
  private View rootView;
  private Subjects subject;
  private SubjectErrorExerciseW subjectErrorExerciseW;

  public static String getDate(long paramLong)
  {
    Timestamp localTimestamp = new Timestamp(paramLong);
    String[] arrayOfString = localTimestamp.split(" ")[0].split("-");
    return arrayOfString[0] + arrayOfString[1] + arrayOfString[2];
  }

  private boolean haveDelete(String paramString)
  {
    if ((this.deleteIds != null) && (this.deleteIds.size() > 0));
    for (int i = 0; ; i++)
    {
      if (i >= this.deleteIds.size())
        return false;
      if (paramString.equals((String)this.deleteIds.get(i)))
        return true;
    }
  }

  public static boolean isSameDayOfMillis(long paramLong1, long paramLong2)
  {
    long l = paramLong1 - paramLong2;
    return (l < 86400000L) && (l > -86400000L) && (toDay(paramLong1) == toDay(paramLong2));
  }

  private void sortByChapter()
  {
    this.latest = new ArrayList();
    Iterator localIterator2;
    HashMap localHashMap;
    Iterator localIterator1;
    if ((this.currentData != null) && (this.currentData.size() > 0))
    {
      localIterator2 = this.currentData.iterator();
      if (localIterator2.hasNext());
    }
    else
    {
      if ((this.latest == null) || (this.latest.size() <= 0))
        break label488;
      localHashMap = new HashMap();
      new ArrayList();
      if (localHashMap != null)
        localHashMap.clear();
      if (this.mHashMapEntryList != null)
        this.mHashMapEntryList.clear();
      localIterator1 = this.latest.iterator();
    }
    while (true)
    {
      if (!localIterator1.hasNext())
      {
        LogUtils.myLog("错题栏 ::" + localHashMap);
        this.mHashMapEntryList = new ArrayList(localHashMap.entrySet());
        Collections.sort(this.mHashMapEntryList, new Comparator()
        {
          public int compare(Entry<String, List<ErrorExercise>> paramEntry1, Entry<String, List<ErrorExercise>> paramEntry2)
          {
            return Integer.parseInt(((String)paramEntry1.getKey()).split("_")[1]) - Integer.parseInt(((String)paramEntry2.getKey()).split("_")[1]);
          }
        });
        if ((this.mHashMapEntryList != null) && (this.mHashMapEntryList.size() > 0))
        {
          Object localObject2 = (List)this.activity.cahceGroupListHM.get(Integer.valueOf(this.currentIndex));
          if (localObject2 == null)
          {
            localObject2 = new ArrayList();
            this.activity.cahceGroupListHM.put(Integer.valueOf(this.currentIndex), localObject2);
          }
          ((List)localObject2).clear();
          ((List)localObject2).addAll(this.mHashMapEntryList);
          if (this.errorAdapter != null)
            break label480;
          this.errorAdapter = new MyErrorAdapter();
          this.lv.setAdapter(this.errorAdapter);
        }
        return;
        ErrorExercise localErrorExercise2 = (ErrorExercise)localIterator2.next();
        boolean bool = haveDelete(localErrorExercise2.getExerciseId());
        if (bool)
        {
          LogUtils.myLog("sortByChapter haveDelete  " + bool);
          break;
        }
        this.latest.add(localErrorExercise2);
        break;
      }
      ErrorExercise localErrorExercise1 = (ErrorExercise)localIterator1.next();
      if (localHashMap.containsKey(localErrorExercise1.getChapter().getId()))
      {
        ((List)localHashMap.get(localErrorExercise1.getChapter().getId())).add(localErrorExercise1);
        continue;
      }
      ArrayList localArrayList = new ArrayList();
      localArrayList.add(localErrorExercise1);
      localHashMap.put(localErrorExercise1.getChapter().getId(), localArrayList);
    }
    label480: this.errorAdapter.notifyDataSetChanged();
    return;
    label488: Object localObject1 = (List)this.activity.cahceGroupListHM.get(Integer.valueOf(this.currentIndex));
    if (localObject1 == null)
    {
      localObject1 = new ArrayList();
      this.activity.cahceGroupListHM.put(Integer.valueOf(this.currentIndex), localObject1);
    }
    ((List)localObject1).clear();
    if (this.mHashMapEntryList != null)
    {
      this.mHashMapEntryList.clear();
      ((List)localObject1).addAll(this.mHashMapEntryList);
    }
    if (this.errorAdapter == null)
    {
      this.errorAdapter = new MyErrorAdapter();
      this.lv.setAdapter(this.errorAdapter);
      return;
    }
    this.errorAdapter.notifyDataSetChanged();
  }

  private void sortDataByTime()
  {
    this.latest = new ArrayList();
    Iterator localIterator2;
    HashMap localHashMap;
    Iterator localIterator1;
    if ((this.currentData != null) && (this.currentData.size() > 0))
    {
      localIterator2 = this.currentData.iterator();
      if (localIterator2.hasNext());
    }
    else
    {
      if ((this.latest == null) || (this.latest.size() <= 0))
        break label489;
      localHashMap = new HashMap();
      new ArrayList();
      if (localHashMap != null)
        localHashMap.clear();
      if (this.mTimeHashMapEntryList != null)
        this.mTimeHashMapEntryList.clear();
      localIterator1 = this.latest.iterator();
    }
    while (true)
    {
      if (!localIterator1.hasNext())
      {
        LogUtils.myLog("错题蓝=" + localHashMap);
        this.mTimeHashMapEntryList = new ArrayList(localHashMap.entrySet());
        Collections.sort(this.mTimeHashMapEntryList, new Comparator()
        {
          public int compare(Entry<String, List<ErrorExercise>> paramEntry1, Entry<String, List<ErrorExercise>> paramEntry2)
          {
            String str = (String)paramEntry1.getKey();
            return ((String)paramEntry2.getKey()).compareTo(str);
          }
        });
        if ((this.mTimeHashMapEntryList != null) && (this.mTimeHashMapEntryList.size() > 0))
        {
          Object localObject2 = (List)this.activity.cahceGroupListHM.get(Integer.valueOf(this.currentIndex));
          if (localObject2 == null)
          {
            localObject2 = new ArrayList();
            this.activity.cahceGroupListHM.put(Integer.valueOf(this.currentIndex), localObject2);
          }
          ((List)localObject2).clear();
          ((List)localObject2).addAll(this.mTimeHashMapEntryList);
          if (this.errorAdapter != null)
            break label481;
          this.errorAdapter = new MyErrorAdapter();
          this.lv.setAdapter(this.errorAdapter);
        }
        return;
        ErrorExercise localErrorExercise2 = (ErrorExercise)localIterator2.next();
        boolean bool = haveDelete(localErrorExercise2.getExerciseId());
        if (bool)
        {
          LogUtils.myLog("sortDataByTime haveDelete " + bool);
          break;
        }
        this.latest.add(localErrorExercise2);
        break;
      }
      ErrorExercise localErrorExercise1 = (ErrorExercise)localIterator1.next();
      if (localHashMap.containsKey(getDate(localErrorExercise1.getTime())))
      {
        ((List)localHashMap.get(getDate(localErrorExercise1.getTime()))).add(localErrorExercise1);
        continue;
      }
      ArrayList localArrayList = new ArrayList();
      localArrayList.add(localErrorExercise1);
      localHashMap.put(getDate(localErrorExercise1.getTime()), localArrayList);
    }
    label481: this.errorAdapter.notifyDataSetChanged();
    return;
    label489: Object localObject1 = (List)this.activity.cahceGroupListHM.get(Integer.valueOf(this.currentIndex));
    if (localObject1 == null)
    {
      localObject1 = new ArrayList();
      this.activity.cahceGroupListHM.put(Integer.valueOf(this.currentIndex), localObject1);
    }
    ((List)localObject1).clear();
    if (this.mTimeHashMapEntryList != null)
    {
      this.mTimeHashMapEntryList.clear();
      ((List)localObject1).addAll(this.mTimeHashMapEntryList);
    }
    if (this.errorAdapter == null)
    {
      this.errorAdapter = new MyErrorAdapter();
      this.lv.setAdapter(this.errorAdapter);
      return;
    }
    this.errorAdapter.notifyDataSetChanged();
  }

  private static long toDay(long paramLong)
  {
    return (paramLong + TimeZone.getDefault().getOffset(paramLong)) / 86400000L;
  }

  public void bindData()
  {
  }

  public void initData()
  {
    this.activity = ((ActivityErrorListActivity)getActivity());
  }

  public void initListener()
  {
    this.lv.setOnItemClickListener(new OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
      {
        ArrayList localArrayList = (ArrayList)((Entry)((List)ErrorListFragement.this.activity.cahceGroupListHM.get(Integer.valueOf(ErrorListFragement.this.currentIndex))).get(paramInt)).getValue();
        Bundle localBundle = new Bundle();
        localBundle.putSerializable("errorExercises", localArrayList);
        String str = ErrorListFragement.this.subject.getId();
        localBundle.putSerializable("subjectName", (String)((ActivityErrorListActivity)ErrorListFragement.this.getActivity()).mDatas.get(ErrorListFragement.this.currentIndex));
        localBundle.putSerializable("subjectId", str);
        localBundle.putInt("FromPage", 10);
        ErrorListFragement.this.startNewActivity(QuestionDetailActivity.class, 2130968581, 2130968579, false, localBundle);
      }
    });
  }

  public void initView(View paramView)
  {
    this.lv = ((ListView)paramView.findViewById(2131100124));
  }

  public void onHiddenChanged(boolean paramBoolean)
  {
    super.onHiddenChanged(paramBoolean);
  }

  public View onInflaterRootView(LayoutInflater paramLayoutInflater, Bundle paramBundle)
  {
    try
    {
      this.subject = ((Subjects)getArguments().getSerializable("subject"));
      label17: this.rootView = View.inflate(getActivity(), 2130903113, null);
      return this.rootView;
    }
    catch (Exception localException)
    {
      break label17;
    }
  }

  public void onSuccess(String paramString1, Map<String, String> paramMap, String paramString2, int paramInt)
  {
    super.onSuccess(paramString1, paramMap, paramString2, paramInt);
    this.mProTools.dismissDislog();
    if (paramString1 != null)
    {
      Gson localGson = UrlFactory.getInstanceGson();
      try
      {
        this.subjectErrorExerciseW = ((SubjectErrorExerciseW)localGson.fromJson(paramString1, SubjectErrorExerciseW.class));
        if (this.subjectErrorExerciseW != null)
        {
          this.handler.sendEmptyMessage(3);
          return;
        }
        LogUtils.myLog("解析错题栏位null");
        return;
      }
      catch (Exception localException)
      {
        LogUtils.myLog("解析错题栏位异常  " + localException.getMessage());
        localException.printStackTrace();
      }
    }
  }

  public void refresh(Subjects paramSubjects, int paramInt, boolean paramBoolean)
  {
    this.subject = paramSubjects;
    this.currentIndex = paramInt;
    if (this.errorAdapter != null)
    {
      if (paramBoolean)
      {
        this.handler.sendEmptyMessage(2);
        return;
      }
      this.handler.sendEmptyMessage(3);
      return;
    }
    this.mProTools.startDialog(true);
    UserSubject localUserSubject = new UserSubject();
    localUserSubject.setClientType(ToolsUtils.getSDK());
    localUserSubject.setImei(ToolsUtils.getImei(this.mContext));
    localUserSubject.setNet(ToolsUtils.getStringNetworkType(this.mContext));
    localUserSubject.setVersionName(this.mSP.getVersionName());
    localUserSubject.setSubjectId(paramSubjects.getId());
    localUserSubject.setUid(this.mSP.getUserId());
    UrlFactory.getInstance().getErrorBaseket().constructUrl(this, localUserSubject, 0);
  }

  public void refreshFmListView(boolean paramBoolean, int paramInt, List<String> paramList)
  {
    this.isShowTimeStyle = paramBoolean;
    this.currentIndex = paramInt;
    this.deleteIds = paramList;
    if (paramBoolean)
    {
      this.handler.sendEmptyMessage(2);
      return;
    }
    this.handler.sendEmptyMessage(3);
  }

  class MyErrorAdapter extends BaseAdapter
  {
    public MyErrorAdapter()
    {
    }

    public int getCount()
    {
      ((List)ErrorListFragement.this.activity.cahceGroupListHM.get(Integer.valueOf(ErrorListFragement.this.currentIndex))).size();
      return ((List)ErrorListFragement.this.activity.cahceGroupListHM.get(Integer.valueOf(ErrorListFragement.this.currentIndex))).size();
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
      if (paramView == null)
      {
        paramView = View.inflate(ErrorListFragement.this.getActivity(), 2130903104, null);
        localViewHolder = new ViewHolder();
        localViewHolder.item_total_count = ((TextView)paramView.findViewById(2131100096));
        localViewHolder.tv_name = ((TextView)paramView.findViewById(2131099709));
        localViewHolder.tv_number = ((TextView)paramView.findViewById(2131100102));
        paramView.setTag(localViewHolder);
      }
      Entry localEntry;
      while (true)
      {
        localEntry = (Entry)((List)ErrorListFragement.this.activity.cahceGroupListHM.get(Integer.valueOf(ErrorListFragement.this.currentIndex))).get(paramInt);
        if (!ErrorListFragement.this.isShowTimeStyle)
          break;
        localViewHolder.item_total_count.setText(((List)localEntry.getValue()).size() + "道错题");
        localViewHolder.tv_name.setText(((ErrorExercise)((List)localEntry.getValue()).get(0)).getChapter().getName());
        localViewHolder.tv_number.setText("");
        localViewHolder.tv_number.setBackgroundResource(2130837642);
        return paramView;
        localViewHolder = (ViewHolder)paramView.getTag();
      }
      localViewHolder.item_total_count.setText(((List)localEntry.getValue()).size() + "道错题");
      localViewHolder.tv_name.setText(((ErrorExercise)((List)localEntry.getValue()).get(0)).getChapter().getName());
      localViewHolder.tv_number.setText(paramInt + 1);
      localViewHolder.tv_number.setBackgroundResource(2130837811);
      return paramView;
    }

    class ViewHolder
    {
      private TextView item_total_count;
      private TextView tv_name;
      private TextView tv_number;

      ViewHolder()
      {
      }
    }
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.fragment.ErrorListFragement
 * JD-Core Version:    0.6.0
 */