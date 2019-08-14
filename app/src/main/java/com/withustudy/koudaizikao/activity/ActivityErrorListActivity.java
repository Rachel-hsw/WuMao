package com.withustudy.koudaizikao.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
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
import com.google.gson.Gson;
import com.withustudy.koudaizikao.action.AbsSimpleImplUrlFactory;
import com.withustudy.koudaizikao.action.UrlFactory;
import com.withustudy.koudaizikao.base.AbsBaseActivity;
import com.withustudy.koudaizikao.config.KouDaiSP;
import com.withustudy.koudaizikao.custom.FragmentpaperViewAdapater;
import com.withustudy.koudaizikao.custom.FragmentpaperViewAdapater.BeforeShowListenner;
import com.withustudy.koudaizikao.custom.ViewPagerIndicator;
import com.withustudy.koudaizikao.custom.ViewPagerIndicator.PageChangeListener;
import com.withustudy.koudaizikao.entity.Chapter;
import com.withustudy.koudaizikao.entity.ErrorExercise;
import com.withustudy.koudaizikao.entity.OpreationState;
import com.withustudy.koudaizikao.entity.Subjects;
import com.withustudy.koudaizikao.entity.SubjectsW;
import com.withustudy.koudaizikao.entity.req.FavoriteExercise;
import com.withustudy.koudaizikao.entity.req.ReqDeleteErrors;
import com.withustudy.koudaizikao.entity.req.ReqMockList;
import com.withustudy.koudaizikao.entity.req.UserSubject;
import com.withustudy.koudaizikao.fragment.ErrorListFragement;
import com.withustudy.koudaizikao.tools.LogUtils;
import com.withustudy.koudaizikao.tools.ProTools;
import com.withustudy.koudaizikao.tools.ToolsUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class ActivityErrorListActivity extends AbsBaseActivity
  implements BeforeShowListenner, OnClickListener
{
  private static final int action_delete_id = 12;
  private static final int action_delete_id_fail = 13;
  private static final int action_get_error_subject = 11;
  private static final int action_id_edit_listview = 1;
  private static final int delete_item = 5;
  private static final int get_subject_list_by_net = 4;
  private Button btn_delete;
  public List<Entry<String, List<ErrorExercise>>> cahceGroupList = new ArrayList();
  public HashMap<Integer, List<Entry<String, List<ErrorExercise>>>> cahceGroupListHM = new HashMap();
  private boolean checkAll = false;
  private int currentIndex;
  public HashMap<Integer, List<String>> deleteIdsHM = new HashMap();
  private ListView edit_lv;
  private ImageButton edit_switch;
  private MyErrorAdapter editadapter;
  private LinearLayout error_ll_back;
  Handler handler = new Handler()
  {
    public void handleMessage(Message paramMessage)
    {
      switch (paramMessage.what)
      {
      default:
      case 13:
      case 1:
      case 11:
        List localList2;
        do
        {
          return;
          Toast.makeText(ActivityErrorListActivity.this.getApplicationContext(), "删除失败!", 0).show();
          return;
          if (ActivityErrorListActivity.this.editadapter == null)
          {
            ActivityErrorListActivity.this.editadapter = new MyErrorAdapter(ActivityErrorListActivity.this);
            ActivityErrorListActivity.this.edit_lv.setAdapter(ActivityErrorListActivity.this.editadapter);
            return;
          }
          ActivityErrorListActivity.this.editadapter.notifyDataSetChanged();
          return;
          localList2 = ActivityErrorListActivity.this.sw.getSubjects();
        }
        while ((localList2 == null) || (localList2.size() <= 0));
        ActivityErrorListActivity.this.subjectList.clear();
        ActivityErrorListActivity.this.subjectList.addAll(localList2);
        if ((ActivityErrorListActivity.this.subjectList != null) && (ActivityErrorListActivity.this.subjectList.size() > 0))
        {
          if (ActivityErrorListActivity.this.mDatas == null)
            ActivityErrorListActivity.this.mDatas = new ArrayList();
          ActivityErrorListActivity.this.mDatas.clear();
          ActivityErrorListActivity.this.mTabContents.clear();
          Iterator localIterator2 = ActivityErrorListActivity.this.subjectList.iterator();
          while (true)
          {
            if (!localIterator2.hasNext())
            {
              ActivityErrorListActivity.this.mAdapter = new FragmentpaperViewAdapater(ActivityErrorListActivity.this.getSupportFragmentManager())
              {
                public int getCount()
                {
                  return ActivityErrorListActivity.this.mTabContents.size();
                }

                public Fragment getItem(int paramInt)
                {
                  return (Fragment)ActivityErrorListActivity.this.mTabContents.get(paramInt);
                }
              };
              ActivityErrorListActivity.this.mAdapter.setBeforeShowListenner(ActivityErrorListActivity.this);
              ActivityErrorListActivity.this.id_vp.setAdapter(ActivityErrorListActivity.this.mAdapter);
              ActivityErrorListActivity.this.id_indicator.setTabItemTitles(ActivityErrorListActivity.this.mDatas);
              ActivityErrorListActivity.this.id_indicator.setViewPager(ActivityErrorListActivity.this.id_vp, 0);
              ActivityErrorListActivity.this.id_vp.setCurrentItem(0);
              return;
            }
            Subjects localSubjects = (Subjects)localIterator2.next();
            ActivityErrorListActivity.this.mDatas.add(localSubjects.getName());
            ErrorListFragement localErrorListFragement = new ErrorListFragement();
            Bundle localBundle = new Bundle();
            localBundle.putSerializable("subject", localSubjects);
            localErrorListFragement.setArguments(localBundle);
            ActivityErrorListActivity.this.mTabContents.add(localErrorListFragement);
          }
        }
        LogUtils.myLog("科目列表为null");
        return;
      case 5:
      }
      Toast.makeText(ActivityErrorListActivity.this.getApplicationContext(), "删除成功!", 0).show();
      Iterator localIterator1 = ActivityErrorListActivity.this.itemEditState.keySet().iterator();
      List localList1;
      int i;
      int j;
      if (!localIterator1.hasNext())
      {
        localList1 = (List)ActivityErrorListActivity.this.cahceGroupListHM.get(Integer.valueOf(ActivityErrorListActivity.this.currentIndex));
        localList1.clear();
        localList1.addAll(ActivityErrorListActivity.this.tempList);
        i = localList1.size();
        ActivityErrorListActivity.this.tv_name.setText((CharSequence)ActivityErrorListActivity.this.mDatas.get(ActivityErrorListActivity.this.currentIndex));
        j = 0;
      }
      for (int k = 0; ; k++)
      {
        if (k >= i)
        {
          ActivityErrorListActivity.this.item_total_count.setText(j + "道错题");
          ActivityErrorListActivity.this.title_check_ib.setBackgroundResource(2130837924);
          if (ActivityErrorListActivity.this.editadapter != null)
            break label759;
          ActivityErrorListActivity.this.editadapter = new MyErrorAdapter(ActivityErrorListActivity.this);
          ActivityErrorListActivity.this.edit_lv.setAdapter(ActivityErrorListActivity.this.editadapter);
          return;
          Integer localInteger = (Integer)localIterator1.next();
          ActivityErrorListActivity.this.itemEditState.put(localInteger, Boolean.valueOf(false));
          break;
        }
        j += ((List)((Entry)localList1.get(k)).getValue()).size();
      }
      label759: ActivityErrorListActivity.this.editadapter.notifyDataSetChanged();
    }
  };
  private ViewPagerIndicator id_indicator;
  private ViewPager id_vp;
  private boolean isEdit;
  private boolean isShowTimeStyle = false;
  private HashMap<Integer, Boolean> itemEditState;
  private TextView item_total_count;
  private List<String> list;
  private LinearLayout ll_edit;
  private FragmentpaperViewAdapater mAdapter;
  public List<String> mDatas = new ArrayList();
  private List<ErrorListFragement> mTabContents = new ArrayList();
  private List<String> mlist = new ArrayList();
  public HashMap<Integer, List<ErrorExercise>> orignalData = new HashMap();
  private ImageButton show_switch_ib;
  private List<Subjects> subjectList = new ArrayList();
  private SubjectsW sw;
  private List<FavoriteExercise> temp;
  public List<Entry<String, List<ErrorExercise>>> tempList;
  private ImageButton title_check_ib;
  private TextView tv_name;
  private LinearLayout unedit_ll;

  private void checkAll()
  {
    List localList = (List)this.cahceGroupListHM.get(Integer.valueOf(this.currentIndex));
    int m;
    if (this.checkAll)
      if ((localList != null) && (localList.size() > 0))
      {
        int k = localList.size();
        m = 0;
        if (m < k)
          break label75;
        this.title_check_ib.setBackgroundResource(2130837638);
        this.handler.sendEmptyMessage(1);
      }
    label75: 
    do
    {
      return;
      this.itemEditState.put(Integer.valueOf(m), Boolean.valueOf(true));
      m++;
      break;
    }
    while ((localList == null) || (localList.size() <= 0));
    int i = localList.size();
    for (int j = 0; ; j++)
    {
      if (j >= i)
      {
        this.title_check_ib.setBackgroundResource(2130837924);
        this.handler.sendEmptyMessage(1);
        return;
      }
      this.itemEditState.put(Integer.valueOf(j), Boolean.valueOf(false));
    }
  }

  private void deleteCheck()
  {
    List localList;
    int i;
    if (this.isEdit)
    {
      localList = (List)this.cahceGroupListHM.get(Integer.valueOf(this.currentIndex));
      if ((localList != null) && (localList.size() > 0))
      {
        this.tempList = new ArrayList();
        i = 0;
        if (i < localList.size())
          break label226;
        LogUtils.myLog(Integer.valueOf(this.tempList.size()));
        ReqDeleteErrors localReqDeleteErrors = new ReqDeleteErrors();
        localReqDeleteErrors.setVersionName(this.mSP.getVersionName());
        localReqDeleteErrors.setClientType(ToolsUtils.getSDK());
        localReqDeleteErrors.setImei(ToolsUtils.getImei(this.mContext));
        localReqDeleteErrors.setNet(ToolsUtils.getStringNetworkType(this.mContext));
        UserSubject localUserSubject = new UserSubject();
        localUserSubject.setSubjectId(((Subjects)this.subjectList.get(this.currentIndex)).getId());
        localUserSubject.setUid(this.mSP.getUserId());
        localReqDeleteErrors.setUserSubject(localUserSubject);
        localReqDeleteErrors.setExerciseId((List)this.deleteIdsHM.get(Integer.valueOf(this.currentIndex)));
        this.mProTools.startDialog(true);
        UrlFactory.getInstance().deleteErrorsById().constructUrl(this, localReqDeleteErrors, 12);
      }
    }
    return;
    label226: Boolean localBoolean = (Boolean)this.itemEditState.get(Integer.valueOf(i));
    Entry localEntry = (Entry)localList.get(i);
    if ((localBoolean == null) || (!localBoolean.booleanValue()))
      this.tempList.add(localEntry);
    while (true)
    {
      i++;
      break;
      Iterator localIterator = ((List)localEntry.getValue()).iterator();
      while (localIterator.hasNext())
      {
        String str = ((ErrorExercise)localIterator.next()).getExerciseId();
        Object localObject = (List)this.deleteIdsHM.get(Integer.valueOf(this.currentIndex));
        if (localObject == null)
        {
          localObject = new ArrayList();
          this.deleteIdsHM.put(Integer.valueOf(this.currentIndex), localObject);
        }
        if (((List)localObject).contains(str))
          continue;
        ((List)localObject).add(str);
      }
    }
  }

  private void goEditUI()
  {
    if (this.isEdit)
    {
      this.unedit_ll.setVisibility(8);
      this.ll_edit.setVisibility(0);
      this.edit_switch.setVisibility(8);
      this.show_switch_ib.setVisibility(8);
      initEditUI();
      return;
    }
    this.unedit_ll.setVisibility(0);
    this.ll_edit.setVisibility(8);
    refreshListView();
  }

  private void goback()
  {
    if (this.isEdit)
    {
      if (this.isEdit);
      for (boolean bool = false; ; bool = true)
      {
        this.isEdit = bool;
        this.unedit_ll.setVisibility(0);
        this.ll_edit.setVisibility(8);
        this.edit_switch.setVisibility(0);
        this.show_switch_ib.setVisibility(0);
        refreshListView();
        return;
      }
    }
    finish(0, 0);
  }

  private void initEditUI()
  {
    this.tv_name.setText((CharSequence)this.mDatas.get(this.currentIndex));
    List localList = (List)this.cahceGroupListHM.get(Integer.valueOf(this.currentIndex));
    int i;
    int j;
    if ((localList != null) && (localList.size() > 0))
    {
      i = localList.size();
      j = 0;
    }
    for (int k = 0; ; k++)
    {
      if (k >= i)
      {
        this.item_total_count.setText(j + "道错题");
        if (this.itemEditState == null)
          this.itemEditState = new HashMap();
        this.handler.sendEmptyMessage(1);
        return;
      }
      j += ((List)((Entry)localList.get(k)).getValue()).size();
    }
  }

  private void refreshListView()
  {
    List localList = (List)this.deleteIdsHM.get(Integer.valueOf(this.currentIndex));
    ((ErrorListFragement)this.mTabContents.get(this.currentIndex)).refreshFmListView(this.isShowTimeStyle, this.currentIndex, localList);
  }

  private void refreshSwitchBg()
  {
    if (this.isShowTimeStyle)
      this.show_switch_ib.setBackgroundResource(2130837637);
    while (true)
    {
      refreshListView();
      return;
      this.show_switch_ib.setBackgroundResource(2130837643);
    }
  }

  public void beforeShowListenner(int paramInt)
  {
    if ((paramInt < 0) || (paramInt > -1 + this.mTabContents.size()))
      return;
    ((ErrorListFragement)this.mTabContents.get(paramInt)).refresh((Subjects)this.subjectList.get(paramInt), paramInt, this.isShowTimeStyle);
  }

  protected void bindData()
  {
  }

  protected void initData()
  {
    this.mProTools.startDialog(true);
    ReqMockList localReqMockList = new ReqMockList();
    localReqMockList.setVersionName(this.mSP.getVersionName());
    localReqMockList.setClientType(ToolsUtils.getSDK());
    localReqMockList.setImei(ToolsUtils.getImei(this.mContext));
    localReqMockList.setNet(ToolsUtils.getStringNetworkType(this.mContext));
    localReqMockList.setUid(this.mSP.getUserId());
    UrlFactory.getInstance().getErrorBaseket().constructUrl(this, localReqMockList, 11);
  }

  protected void initListener()
  {
    this.show_switch_ib.setOnClickListener(this);
    this.edit_switch.setOnClickListener(this);
    this.title_check_ib.setOnClickListener(this);
    this.error_ll_back.setOnClickListener(this);
    this.btn_delete.setOnClickListener(this);
    this.id_indicator.setOnPageChangeListener(new PageChangeListener()
    {
      public void onPageScrollStateChanged(int paramInt)
      {
      }

      public void onPageScrolled(int paramInt1, float paramFloat, int paramInt2)
      {
      }

      public void onPageSelected(int paramInt)
      {
        ActivityErrorListActivity.this.currentIndex = paramInt;
        ActivityErrorListActivity.this.beforeShowListenner(paramInt);
      }
    });
    this.edit_lv.setOnItemClickListener(new OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
      {
        Toast.makeText(ActivityErrorListActivity.this.getApplicationContext(), paramInt, 0).show();
        Boolean localBoolean = (Boolean)ActivityErrorListActivity.this.itemEditState.get(Integer.valueOf(paramInt));
        ImageView localImageView = (ImageView)paramView.findViewById(2131100101);
        if ((localBoolean == null) || (!localBoolean.booleanValue()))
        {
          localImageView.setBackgroundResource(2130837638);
          ActivityErrorListActivity.this.itemEditState.put(Integer.valueOf(paramInt), Boolean.valueOf(true));
          return;
        }
        localImageView.setBackgroundResource(2130837924);
        ActivityErrorListActivity.this.itemEditState.put(Integer.valueOf(paramInt), Boolean.valueOf(false));
      }
    });
  }

  protected void initView()
  {
    this.id_indicator = ((ViewPagerIndicator)findViewById(2131100093));
    this.id_vp = ((ViewPager)findViewById(2131100094));
    this.show_switch_ib = ((ImageButton)findViewById(2131100090));
    this.edit_switch = ((ImageButton)findViewById(2131100091));
    this.title_check_ib = ((ImageButton)findViewById(2131100097));
    this.unedit_ll = ((LinearLayout)findViewById(2131100092));
    this.ll_edit = ((LinearLayout)findViewById(2131100095));
    this.tv_name = ((TextView)findViewById(2131099709));
    this.item_total_count = ((TextView)findViewById(2131100096));
    this.edit_lv = ((ListView)findViewById(2131100098));
    this.btn_delete = ((Button)findViewById(2131100099));
    this.error_ll_back = ((LinearLayout)findViewById(2131100089));
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    case 2131100092:
    case 2131100093:
    case 2131100094:
    case 2131100095:
    case 2131100096:
    case 2131100098:
    default:
      return;
    case 2131100090:
      boolean bool5 = this.isShowTimeStyle;
      boolean bool6 = false;
      if (bool5);
      while (true)
      {
        this.isShowTimeStyle = bool6;
        refreshSwitchBg();
        return;
        bool6 = true;
      }
    case 2131100091:
      boolean bool3 = this.isEdit;
      boolean bool4 = false;
      if (bool3);
      while (true)
      {
        this.isEdit = bool4;
        goEditUI();
        return;
        bool4 = true;
      }
    case 2131100097:
      boolean bool1 = this.checkAll;
      boolean bool2 = false;
      if (bool1);
      while (true)
      {
        this.checkAll = bool2;
        checkAll();
        return;
        bool2 = true;
      }
    case 2131100099:
      deleteCheck();
      return;
    case 2131100089:
    }
    goback();
  }

  public void onSuccess(String paramString1, Map<String, String> paramMap, String paramString2, int paramInt)
  {
    super.onSuccess(paramString1, paramMap, paramString2, paramInt);
    this.mProTools.dismissDislog();
    if (paramString1 != null);
    try
    {
      Gson localGson = UrlFactory.getInstanceGson();
      switch (paramInt)
      {
      case 11:
        this.sw = ((SubjectsW)localGson.fromJson(paramString1, SubjectsW.class));
        if (this.sw != null)
        {
          this.handler.sendEmptyMessage(11);
          return;
        }
        LogUtils.myLog("sw为null");
        return;
      case 12:
        OpreationState localOpreationState = (OpreationState)localGson.fromJson(paramString1, OpreationState.class);
        if (localOpreationState == null)
          break;
        if ("OK".equals(localOpreationState.getStatus()))
        {
          this.handler.sendEmptyMessage(5);
          return;
        }
        this.handler.sendEmptyMessage(13);
      case 4:
      }
      return;
    }
    catch (Exception localException)
    {
    }
  }

  protected void setContentView()
  {
    setContentView(2130903103);
  }

  class MyErrorAdapter extends BaseAdapter
  {
    public MyErrorAdapter()
    {
    }

    public int getCount()
    {
      List localList = (List)ActivityErrorListActivity.this.cahceGroupListHM.get(Integer.valueOf(ActivityErrorListActivity.this.currentIndex));
      if (localList == null)
        return 0;
      return localList.size();
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
      Entry localEntry;
      if (paramView == null)
      {
        paramView = View.inflate(ActivityErrorListActivity.this.getApplicationContext(), 2130903104, null);
        localViewHolder = new ViewHolder();
        localViewHolder.item_total_count = ((TextView)paramView.findViewById(2131100096));
        localViewHolder.tv_name = ((TextView)paramView.findViewById(2131099709));
        localViewHolder.tv_number = ((TextView)paramView.findViewById(2131100102));
        localViewHolder.check_ib = ((ImageView)paramView.findViewById(2131100101));
        paramView.setTag(localViewHolder);
        localEntry = (Entry)((List)ActivityErrorListActivity.this.cahceGroupListHM.get(Integer.valueOf(ActivityErrorListActivity.this.currentIndex))).get(paramInt);
        if (!ActivityErrorListActivity.this.isShowTimeStyle)
          break label284;
        localViewHolder.tv_name.setText(((ErrorExercise)((List)localEntry.getValue()).get(0)).getChapter().getName());
        localViewHolder.tv_number.setText("");
        localViewHolder.tv_number.setBackgroundResource(2130837642);
      }
      while (true)
      {
        localViewHolder.item_total_count.setText(((List)localEntry.getValue()).size() + "道错题");
        Boolean localBoolean = (Boolean)ActivityErrorListActivity.this.itemEditState.get(Integer.valueOf(paramInt));
        if ((localBoolean == null) || (!localBoolean.booleanValue()))
          break label354;
        localViewHolder.check_ib.setBackgroundResource(2130837638);
        return paramView;
        localViewHolder = (ViewHolder)paramView.getTag();
        break;
        label284: localViewHolder.tv_name.setText(((ErrorExercise)((List)localEntry.getValue()).get(0)).getChapter().getName());
        localViewHolder.tv_number.setText(paramInt + 1);
        localViewHolder.tv_number.setBackgroundResource(2130837811);
      }
      label354: localViewHolder.check_ib.setBackgroundResource(2130837924);
      return paramView;
    }

    class ViewHolder
    {
      private ImageView check_ib;
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
 * Qualified Name:     com.withustudy.koudaizikao.activity.ActivityErrorListActivity
 * JD-Core Version:    0.6.0
 */