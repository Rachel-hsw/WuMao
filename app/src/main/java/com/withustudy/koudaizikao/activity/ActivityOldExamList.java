package com.withustudy.koudaizikao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.google.gson.Gson;
import com.withustudy.koudaizikao.action.AbsSimpleImplUrlFactory;
import com.withustudy.koudaizikao.action.UrlFactory;
import com.withustudy.koudaizikao.base.AbsBaseActivity;
import com.withustudy.koudaizikao.config.KouDaiSP;
import com.withustudy.koudaizikao.entity.ExamDateList;
import com.withustudy.koudaizikao.entity.ReqOldExam;
import com.withustudy.koudaizikao.entity.req.ExamDate;
import com.withustudy.koudaizikao.entity.req.UserSubject;
import com.withustudy.koudaizikao.tools.LogUtils;
import com.withustudy.koudaizikao.tools.ProTools;
import java.util.List;
import java.util.Map;

public class ActivityOldExamList extends AbsBaseActivity
  implements OnClickListener
{
  public static final int activity_old_examlist = 16;
  private static final int old_exam_action_id_get_list;
  private MyOldExamBaseAdapter adapter;
  private ExamDateList examDateList;
  private List<ExamDate> examDates;
  private Bundle extras;
  private Handler handler = new Handler()
  {
    public void handleMessage(Message paramMessage)
    {
      switch (paramMessage.what)
      {
      default:
        return;
      case 0:
      }
      ActivityOldExamList.this.examDates = ActivityOldExamList.this.examDateList.getExamDate();
      if (ActivityOldExamList.this.examDates == null)
      {
        ActivityOldExamList.this.ll_no_data.setVisibility(0);
        return;
      }
      if (ActivityOldExamList.this.adapter == null)
      {
        ActivityOldExamList.this.adapter = new MyOldExamBaseAdapter(ActivityOldExamList.this, null);
        ActivityOldExamList.this.old_exam_lv.setAdapter(ActivityOldExamList.this.adapter);
        return;
      }
      ActivityOldExamList.this.adapter.notifyDataSetChanged();
    }
  };
  private LinearLayout ll_no_data;
  private LinearLayout old_exam_list_back_ll;
  private ListView old_exam_lv;
  private String subjectId;
  private String subjectName;

  protected void bindData()
  {
    this.mProTools.startDialog(true);
    ReqOldExam localReqOldExam = new ReqOldExam();
    UserSubject localUserSubject = new UserSubject();
    localUserSubject.setUid(this.mSP.getUserId());
    localUserSubject.setSubjectId(this.subjectId);
    localReqOldExam.setUserSubject(localUserSubject);
    UrlFactory.getInstance().getOldExamList().constructUrl(this, localReqOldExam, 0);
  }

  protected void initData()
  {
    this.extras = getIntent().getExtras();
    try
    {
      this.subjectId = this.extras.getString("subjectId");
      this.subjectName = this.extras.getString("subjectName");
      return;
    }
    catch (Exception localException)
    {
    }
  }

  protected void initListener()
  {
    this.old_exam_list_back_ll.setOnClickListener(this);
    this.old_exam_lv.setOnItemClickListener(new OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
      {
        LogUtils.myToast(ActivityOldExamList.this.getApplicationContext(), paramInt);
        Bundle localBundle = new Bundle();
        localBundle.putString("subjectId", ActivityOldExamList.this.subjectId);
        localBundle.putSerializable("examDate", (ExamDate)ActivityOldExamList.this.examDates.get(paramInt));
        localBundle.putInt("FromPage", 16);
        ActivityOldExamList.this.startNewActivity(QuestionDetailActivity.class, 2130968581, 2130968579, false, localBundle);
      }
    });
  }

  protected void initView()
  {
    this.old_exam_list_back_ll = ((LinearLayout)findViewById(2131099847));
    this.old_exam_lv = ((ListView)findViewById(2131099848));
    this.ll_no_data = ((LinearLayout)findViewById(2131099849));
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
      return;
    case 2131099847:
    }
    finish();
  }

  public void onSuccess(String paramString1, Map<String, String> paramMap, String paramString2, int paramInt)
  {
    super.onSuccess(paramString1, paramMap, paramString2, paramInt);
    this.mProTools.dismissDislog();
    Gson localGson;
    if (paramString1 != null)
    {
      localGson = UrlFactory.getInstanceGson();
      switch (paramInt)
      {
      default:
      case 0:
      }
    }
    while (true)
    {
      return;
      try
      {
        this.examDateList = ((ExamDateList)localGson.fromJson(paramString1, ExamDateList.class));
        if (this.examDateList == null)
          continue;
        this.handler.sendEmptyMessage(0);
        return;
      }
      catch (Exception localException)
      {
      }
    }
  }

  protected void setContentView()
  {
    setContentView(2130903064);
  }

  private class MyOldExamBaseAdapter extends BaseAdapter
  {
    private MyOldExamBaseAdapter()
    {
    }

    public int getCount()
    {
      if (ActivityOldExamList.this.examDates == null)
        return 0;
      return ActivityOldExamList.this.examDates.size();
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
        paramView = View.inflate(ActivityOldExamList.this.getApplicationContext(), 2130903063, null);
        localViewHolder = new ViewHolder(ActivityOldExamList.this);
        ViewHolder.access$0(localViewHolder, (TextView)paramView.findViewById(2131099846));
        paramView.setTag(localViewHolder);
      }
      while (true)
      {
        ExamDate localExamDate = (ExamDate)ActivityOldExamList.this.examDates.get(paramInt);
        ViewHolder.access$1(localViewHolder).setText(localExamDate.getDate());
        return paramView;
        localViewHolder = (ViewHolder)paramView.getTag();
      }
    }
  }

  class ViewHolder
  {
    private TextView old_exam_name;

    ViewHolder()
    {
    }
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.activity.ActivityOldExamList
 * JD-Core Version:    0.6.0
 */