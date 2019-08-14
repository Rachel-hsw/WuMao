package com.withustudy.koudaizikao.fragment;

import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.URLSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.gson.Gson;
import com.withustudy.koudaizikao.action.AbsSimpleImplUrlFactory;
import com.withustudy.koudaizikao.action.UrlFactory;
import com.withustudy.koudaizikao.activity.ActivityOldExamList;
import com.withustudy.koudaizikao.activity.BrushRankingActivity;
import com.withustudy.koudaizikao.activity.ChapterSectionListActivity;
import com.withustudy.koudaizikao.activity.KnowledgeExplainActivity;
import com.withustudy.koudaizikao.activity.QuestionDetailActivity;
import com.withustudy.koudaizikao.activity.SimpleAbsFragImpl;
import com.withustudy.koudaizikao.activity.SimulationInitActivity;
import com.withustudy.koudaizikao.activity.VideoWebViewActivity;
import com.withustudy.koudaizikao.config.KouDaiSP;
import com.withustudy.koudaizikao.entity.Subject;
import com.withustudy.koudaizikao.entity.SubjectState;
import com.withustudy.koudaizikao.entity.SubjectStateW;
import com.withustudy.koudaizikao.entity.req.ReqSubjectState;
import com.withustudy.koudaizikao.tools.LogUtils;
import com.withustudy.koudaizikao.tools.MyLog;
import com.withustudy.koudaizikao.tools.ProTools;
import com.withustudy.koudaizikao.tools.ToolsUtils;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SubjectFragment extends SimpleAbsFragImpl
  implements OnClickListener
{
  public static final int intelligent_brush = 888;
  private Button brush_rank_btn;
  private LinearLayout chapter_section_excerise_ll;
  private LinearLayout collect_ll;
  private int currentIndex = 0;
  private LinearLayout error_ll;
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
      if (SubjectFragment.this.subjectStateW != null)
      {
        SubjectState localSubjectState = SubjectFragment.this.subjectStateW.getSubjectState();
        String str1 = "0";
        String str2 = "0";
        String str3 = "0";
        if (localSubjectState != null)
        {
          if ((localSubjectState.getDoneCount() != null) && (!localSubjectState.getDoneCount().equals("")) && (!localSubjectState.getDoneCount().equals("null")))
            str1 = localSubjectState.getDoneCount();
          if ((localSubjectState.getCurrentRank() != null) && (!localSubjectState.getCurrentRank().equals("")) && (!localSubjectState.getCurrentRank().equals("null")))
            str2 = localSubjectState.getCurrentRank();
          str3 = (int)(100.0D * localSubjectState.getBeatRate());
          if ((localSubjectState.getLackCount() != null) && (!localSubjectState.getLackCount().equals("")) && (!localSubjectState.getLackCount().equals("null")))
            localSubjectState.getLackCount();
        }
        while (true)
        {
          String str4 = SubjectFragment.this.testTv1(str1, str2, str3);
          SubjectFragment.this.testSpananble(SubjectFragment.this.tv1, str4);
          return;
          LogUtils.myLog("刷题主界面数据解析实体bean内部为null");
        }
      }
      LogUtils.myLog("刷题主界面数据解析实体bean为null");
    }
  };
  private LinearLayout intelligent_brush_ll;
  private LinearLayout knowledge_explain_ll;
  private LinearLayout knowledge_map_btn;
  private Button simulation_history_btn;
  private LinearLayout simulation_rank_btn;
  private LinearLayout simulation_test_ll;
  private LinearLayout special_breakthrough_ll;
  private Subject subject;
  private String subjectId = "0";
  private String subjectName;
  private SubjectStateW subjectStateW;
  private TextView tv1;
  private TextView tv2;

  private void onInvisible()
  {
  }

  private void onVisible()
  {
    this.mProTools.startDialog(true);
    ReqSubjectState localReqSubjectState = new ReqSubjectState();
    localReqSubjectState.setClientType(ToolsUtils.getSDK());
    localReqSubjectState.setImei(ToolsUtils.getImei(this.mContext));
    localReqSubjectState.setNet(ToolsUtils.getStringNetworkType(this.mContext));
    localReqSubjectState.setVersionName(this.mSP.getVersionName());
    localReqSubjectState.setUid(this.mSP.getUserId());
    localReqSubjectState.setSubjectId(this.subjectId);
    UrlFactory.getInstance().getSubjectState().constructUrl(this, localReqSubjectState, 0);
  }

  private void testSpananble(TextView paramTextView, String paramString)
  {
    int i = paramString.length();
    SpannableString localSpannableString1 = new SpannableString(Html.fromHtml(paramString.toString()));
    URLSpan[] arrayOfURLSpan = (URLSpan[])localSpannableString1.getSpans(0, i, URLSpan.class);
    String str = paramString.toString();
    SpannableString localSpannableString2 = new SpannableString(Pattern.compile("</?[^>]+>", 2).matcher(str).replaceAll(""));
    int j = arrayOfURLSpan.length;
    for (int k = 0; ; k++)
    {
      if (k >= j)
      {
        paramTextView.setText(localSpannableString2);
        return;
      }
      URLSpan localURLSpan = arrayOfURLSpan[k];
      int m = localSpannableString1.getSpanStart(localURLSpan);
      int n = localSpannableString1.getSpanEnd(localURLSpan);
      localSpannableString2.setSpan(new ForegroundColorSpan(getResources().getColor(2131230742)), m, n, 34);
    }
  }

  private String testTv1(String paramString1, String paramString2, String paramString3)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append(getResources().getString(2131165391));
    localStringBuffer.append("<a href=\"1\">");
    localStringBuffer.append(paramString1);
    localStringBuffer.append("</a>");
    localStringBuffer.append(getResources().getString(2131165392));
    localStringBuffer.append("<a href=\"2\">");
    localStringBuffer.append(paramString2);
    localStringBuffer.append("</a>");
    localStringBuffer.append(getResources().getString(2131165393));
    localStringBuffer.append("<a href=\"3\">");
    localStringBuffer.append(paramString3 + "%");
    localStringBuffer.append("</a>");
    localStringBuffer.append(getResources().getString(2131165394));
    return localStringBuffer.toString();
  }

  public void OnPageChange(int paramInt, String paramString)
  {
    this.currentIndex = paramInt;
    this.subjectId = paramString;
  }

  public void initListener()
  {
    this.chapter_section_excerise_ll.setOnClickListener(this);
    this.knowledge_explain_ll.setOnClickListener(this);
    this.intelligent_brush_ll.setOnClickListener(this);
    this.special_breakthrough_ll.setOnClickListener(this);
    this.simulation_test_ll.setOnClickListener(this);
    this.simulation_rank_btn.setOnClickListener(this);
    this.brush_rank_btn.setOnClickListener(this);
    this.knowledge_map_btn.setOnClickListener(this);
  }

  public void initView(View paramView)
  {
    this.chapter_section_excerise_ll = ((LinearLayout)paramView.findViewById(2131100256));
    this.knowledge_explain_ll = ((LinearLayout)paramView.findViewById(2131100258));
    this.intelligent_brush_ll = ((LinearLayout)paramView.findViewById(2131100257));
    this.special_breakthrough_ll = ((LinearLayout)paramView.findViewById(2131100261));
    this.simulation_test_ll = ((LinearLayout)paramView.findViewById(2131100259));
    this.simulation_rank_btn = ((LinearLayout)paramView.findViewById(2131100262));
    this.brush_rank_btn = ((Button)paramView.findViewById(2131100255));
    this.knowledge_map_btn = ((LinearLayout)paramView.findViewById(2131100260));
    this.tv1 = ((TextView)paramView.findViewById(2131100254));
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
      return;
    case 2131100256:
      Bundle localBundle7 = new Bundle();
      localBundle7.putString("subjectId", this.subject.getId());
      localBundle7.putString("subjectName", this.subject.getName());
      localBundle7.putString("subjectName", this.subjectName);
      startNewActivity(ChapterSectionListActivity.class, 2130968581, 2130968579, false, localBundle7);
      return;
    case 2131100258:
      Bundle localBundle6 = new Bundle();
      localBundle6.putString("subjectId", this.subjectId);
      localBundle6.putString("subjectName", this.subjectName);
      startNewActivity(KnowledgeExplainActivity.class, 2130968581, 2130968579, false, localBundle6);
      return;
    case 2131100257:
      Bundle localBundle5 = new Bundle();
      localBundle5.putString("subjectId", this.subjectId);
      localBundle5.putInt("FromPage", 888);
      localBundle5.putString("subjectName", this.subjectName);
      startNewActivity(QuestionDetailActivity.class, 2130968581, 2130968579, false, localBundle5);
      return;
    case 2131100261:
      Bundle localBundle4 = new Bundle();
      localBundle4.putString("subjectId", this.subjectId);
      localBundle4.putString("subjectName", this.subjectName);
      startNewActivity(ActivityOldExamList.class, 2130968581, 2130968579, false, localBundle4);
      return;
    case 2131100259:
      Bundle localBundle3 = new Bundle();
      localBundle3.putString("subjectId", this.subjectId);
      localBundle3.putString("subjectName", this.subjectName);
      startNewActivity(SimulationInitActivity.class, 2130968581, 2130968579, false, localBundle3);
      return;
    case 2131100262:
      Bundle localBundle2 = new Bundle();
      localBundle2.putInt("type", 1);
      localBundle2.putString("subjectId", this.subjectId);
      localBundle2.putString("subjectName", this.subjectName);
      startNewActivity(BrushRankingActivity.class, 2130968581, 2130968579, false, localBundle2);
      return;
    case 2131100255:
      Bundle localBundle1 = new Bundle();
      localBundle1.putInt("type", 0);
      localBundle1.putString("subjectId", this.subjectId);
      localBundle1.putString("subjectName", this.subjectName);
      startNewActivity(BrushRankingActivity.class, 2130968581, 2130968579, false, localBundle1);
      return;
    case 2131100260:
    }
    startNewActivity(VideoWebViewActivity.class, false, null);
  }

  public View onInflaterRootView(LayoutInflater paramLayoutInflater, Bundle paramBundle)
  {
    try
    {
      this.subject = ((Subject)getArguments().getSerializable("subject"));
      label17: return View.inflate(getActivity(), 2130903124, null);
    }
    catch (Exception localException)
    {
      break label17;
    }
  }

  public void onSuccess(String paramString1, Map<String, String> paramMap, String paramString2, int paramInt)
  {
    super.onSuccess(paramString1, paramMap, paramString2, paramInt);
    if (paramString1 != null)
    {
      Gson localGson = new Gson();
      try
      {
        this.subjectStateW = ((SubjectStateW)localGson.fromJson(paramString1, SubjectStateW.class));
        if (this.subjectStateW != null)
        {
          this.handler.sendEmptyMessage(0);
          return;
        }
        LogUtils.myLog("刷题主界面数据解析实体bean为null");
        return;
      }
      catch (Exception localException)
      {
        LogUtils.myLog("刷题主界面数据实体解析异常:" + localException.getMessage());
      }
    }
  }

  public void refresh(Subject paramSubject, int paramInt, String paramString)
  {
    this.currentIndex = paramInt;
    this.subject = paramSubject;
    this.subjectId = paramSubject.getId();
    this.subjectName = paramString;
    ReqSubjectState localReqSubjectState = new ReqSubjectState();
    localReqSubjectState.setClientType(ToolsUtils.getSDK());
    localReqSubjectState.setImei(ToolsUtils.getImei(this.mContext));
    localReqSubjectState.setNet(ToolsUtils.getStringNetworkType(this.mContext));
    localReqSubjectState.setVersionName(this.mSP.getVersionName());
    localReqSubjectState.setUid(this.mSP.getUserId());
    localReqSubjectState.setSubjectId(this.subjectId);
    UrlFactory.getInstance().getSubjectState().constructUrl(this, localReqSubjectState, 0);
  }

  public void setUserVisibleHint(boolean paramBoolean)
  {
    super.setUserVisibleHint(paramBoolean);
    if (paramBoolean)
    {
      LogUtils.myLog("setUserVisibleHint==" + this.currentIndex, Boolean.valueOf(paramBoolean));
      LogUtils.myLog("setUserVisibleHint mContext=" + this.mContext);
      onVisible();
      return;
    }
    MyLog.log("setUserVisibleHint==" + this.currentIndex, Boolean.valueOf(paramBoolean));
    onInvisible();
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.fragment.SubjectFragment
 * JD-Core Version:    0.6.0
 */