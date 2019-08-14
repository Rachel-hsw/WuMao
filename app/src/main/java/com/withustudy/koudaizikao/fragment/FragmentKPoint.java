package com.withustudy.koudaizikao.fragment;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.android.http.FileDownLoad;
import com.example.test.FlowLayout;
import com.himamis.retex.renderer.android.LaTeXView;
import com.umeng.analytics.MobclickAgent;
import com.withustudy.koudaizikao.activity.KnowledgePointDetailActivity;
import com.withustudy.koudaizikao.activity.QuestionDetailActivity;
import com.withustudy.koudaizikao.activity.ShowPictureActivity;
import com.withustudy.koudaizikao.base.AbsBaseFragment;
import com.withustudy.koudaizikao.config.KouDaiSP;
import com.withustudy.koudaizikao.entity.Kpoint;
import com.withustudy.koudaizikao.entity.KpointDetails;
import com.withustudy.koudaizikao.tools.LogUtils;
import com.withustudy.koudaizikao.tools.ToolsUtils;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FragmentKPoint extends AbsBaseFragment
  implements OnClickListener
{
  public static final int fragmentKPoint = 1;
  private KnowledgePointDetailActivity activity;
  private Button anser_paper_btn;
  private Button btn_again_excercise;
  private List<KpointDetails> cacheAllData;
  private LinearLayout dis_ll;
  private FlowLayout fl_point;
  private Button go_last_brush_btn;
  private Button go_on_test_btn;
  private ImageView iv_level_a;
  private ImageView iv_level_b;
  private ImageView iv_level_c;
  private ImageView iv_level_d;
  private ImageView iv_level_e;
  private ImageView iv_sj;
  private TextView k_jiangjie_content;
  private TextView k_my_tv_error;
  private PopupWindow k_pop;
  private RelativeLayout k_rl_title;
  private TextView k_tv_error;
  private LinearLayout k_yuyin_ll;
  private KpointDetails kpointDetails;
  private LinearLayout ll_sj;
  private ListView lv;
  private MyAdapter myadapter;
  private ImageButton play_yuyin;
  private RelativeLayout rl_sj;
  private LinearLayout simu_detail_pop_diss_ll;
  private PopupWindow test_pop;
  private TextView text_knowlege_point_chapter_count;
  private TextView text_knowlege_point_name1;
  private ImageButton text_knowlege_point_pull_down;
  private TextView tv_k_label;
  private TextView tv_yuyin_time;

  private void ShowPop()
  {
    if (this.k_pop != null)
    {
      this.k_pop.dismiss();
      this.k_pop = null;
    }
    KnowledgePointDetailActivity localKnowledgePointDetailActivity = (KnowledgePointDetailActivity)getActivity();
    View localView = localKnowledgePointDetailActivity.getLayoutInflater().inflate(2130903154, null);
    this.lv = ((ListView)localView.findViewById(2131100124));
    this.lv.setOnItemClickListener(new OnItemClickListener(localKnowledgePointDetailActivity)
    {
      public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
      {
        FragmentKPoint.this.k_pop.dismiss();
        this.val$activity.callBack(paramInt);
      }
    });
    this.dis_ll = ((LinearLayout)localView.findViewById(2131100430));
    this.dis_ll.setOnClickListener(this);
    this.lv.setAdapter(new MyAdapter());
    localView.setFocusable(true);
    localView.setFocusableInTouchMode(true);
    localView.setOnFocusChangeListener(new OnFocusChangeListener()
    {
      public void onFocusChange(View paramView, boolean paramBoolean)
      {
        if (!paramBoolean)
          FragmentKPoint.this.text_knowlege_point_pull_down.setBackgroundResource(2130837685);
      }
    });
    this.k_pop = new PopupWindow(localView, KouDaiSP.getInstance(this.mContext).getWidth(), ToolsUtils.dip2px(getActivity(), 550.0F));
    this.k_pop.setBackgroundDrawable(new BitmapDrawable());
    this.k_pop.setOutsideTouchable(true);
    this.k_pop.setFocusable(true);
    localView.setOnKeyListener(new OnKeyListener()
    {
      public boolean onKey(View paramView, int paramInt, KeyEvent paramKeyEvent)
      {
        if (paramInt == 4)
        {
          FragmentKPoint.this.k_pop.dismiss();
          FragmentKPoint.this.k_pop = null;
          FragmentKPoint.this.backgroundAlpha(1.0F);
          return true;
        }
        return false;
      }
    });
    this.k_pop.showAtLocation(this.k_rl_title, 80, 0, 0);
    backgroundAlpha(1.0F);
  }

  private void dimissPop()
  {
    if (this.k_pop != null)
    {
      this.k_pop.dismiss();
      this.k_pop = null;
    }
    backgroundAlpha(1.0F);
    this.text_knowlege_point_pull_down.setBackgroundResource(2130837685);
  }

  private List<View> scanKnow(String paramString)
  {
    List localList = spiltString(paramString);
    ArrayList localArrayList = null;
    Iterator localIterator;
    if (localList != null)
    {
      int i = localList.size();
      localArrayList = null;
      if (i > 0)
      {
        localArrayList = new ArrayList();
        localIterator = localList.iterator();
      }
    }
    while (true)
    {
      if (!localIterator.hasNext())
        return localArrayList;
      String str1 = (String)localIterator.next();
      if (str1.contains("pic:"))
      {
        ImageView localImageView = (ImageView)View.inflate(getActivity(), 2130903131, null);
        localArrayList.add(localImageView);
        String str2 = str1.substring("pic:".length());
        localImageView.setOnClickListener(new OnClickListener(str2)
        {
          public void onClick(View paramView)
          {
            Bundle localBundle = new Bundle();
            localBundle.putInt("currentitem_key", 0);
            ArrayList localArrayList = new ArrayList();
            localArrayList.add(this.val$url);
            localBundle.putSerializable("image_name", (Serializable)localArrayList);
            FragmentKPoint.this.startNewActivity(ShowPictureActivity.class, false, localBundle);
          }
        });
        this.mFileDownLoad.downLoadImage(str2, localImageView);
        continue;
      }
      localArrayList.addAll(scaningString(str1, true));
    }
  }

  private List<View> scaningString(String paramString, boolean paramBoolean)
  {
    ArrayList localArrayList;
    String str1;
    int i;
    TextView localTextView2;
    if ((paramString != null) && (!paramString.equals("")) && (paramString.length() > 0))
    {
      localArrayList = new ArrayList();
      str1 = "";
      i = 0;
      if (i >= paramString.length())
        if (!str1.equals(""))
        {
          localTextView2 = new TextView(getActivity());
          if (!paramBoolean)
            break label331;
          localTextView2.setTextSize(2, 15.0F);
          localTextView2.setTextColor(getResources().getColor(2131230759));
        }
    }
    while (true)
    {
      localTextView2.setLineSpacing(0.0F, 1.3F);
      localTextView2.setText(str1);
      localArrayList.add(localTextView2);
      return localArrayList;
      String str2 = paramString.substring(i, i + 1);
      if (str2.equals("$"))
      {
        TextView localTextView1 = new TextView(getActivity());
        if (paramBoolean)
        {
          localTextView1.setTextSize(2, 15.0F);
          localTextView1.setTextColor(getResources().getColor(2131230759));
        }
        while (true)
        {
          localTextView1.setLineSpacing(0.0F, 1.3F);
          localTextView1.setText(str1);
          localArrayList.add(localTextView1);
          str1 = "";
          i += 2;
          label222: i++;
          break;
          localTextView1.setTextSize(2, 17.0F);
          localTextView1.setTextColor(getResources().getColor(2131230760));
        }
      }
      if (str2.equals("#"));
      try
      {
        LaTeXView localLaTeXView = new LaTeXView(getActivity());
        localLaTeXView.setLatexText(str1);
        localArrayList.add(localLaTeXView);
        label295: str1 = "";
        i += 2;
        break label222;
        str1 = str1 + str2;
        break label222;
        label331: localTextView2.setTextSize(2, 17.0F);
        localTextView2.setTextColor(getResources().getColor(2131230760));
        continue;
        return null;
      }
      catch (Exception localException)
      {
        break label295;
      }
    }
  }

  private List<String> spiltString(String paramString)
  {
    int i = 0;
    ArrayList localArrayList = new ArrayList();
    int j = paramString.indexOf("[pic:", i);
    Iterator localIterator;
    if (j == -1)
    {
      if (i + 1 < paramString.length())
        localArrayList.add(paramString.substring(i + 1, paramString.length()));
      localIterator = localArrayList.iterator();
    }
    while (true)
    {
      if (!localIterator.hasNext())
      {
        return localArrayList;
        if (i + 1 < j)
        {
          localArrayList.add(paramString.substring(i, j));
          i = j;
        }
        int k = paramString.indexOf("]", i);
        if ((k == -1) || (i + 1 >= k))
          break;
        localArrayList.add(paramString.substring(i + 1, k));
        i = k + 1;
        break;
      }
      String str = (String)localIterator.next();
      LogUtils.myLog("知识点和讲解的公式 图片混排切割=" + str);
    }
  }

  public void backgroundAlpha(float paramFloat)
  {
    WindowManager.LayoutParams localLayoutParams = getActivity().getWindow().getAttributes();
    localLayoutParams.alpha = paramFloat;
    getActivity().getWindow().setAttributes(localLayoutParams);
  }

  public void bindData()
  {
  }

  public void initData()
  {
  }

  public void initListener()
  {
    this.play_yuyin.setOnClickListener(this);
    this.btn_again_excercise.setOnClickListener(this);
    this.text_knowlege_point_pull_down.setOnClickListener(this);
  }

  public void initView(View paramView)
  {
    this.fl_point = ((FlowLayout)paramView.findViewById(2131100248));
    this.text_knowlege_point_pull_down = ((ImageButton)paramView.findViewById(2131100242));
    this.text_knowlege_point_chapter_count = ((TextView)paramView.findViewById(2131100240));
    this.text_knowlege_point_name1 = ((TextView)paramView.findViewById(2131100241));
    this.tv_k_label = ((TextView)paramView.findViewById(2131100246));
    this.ll_sj = ((LinearLayout)paramView.findViewById(2131100245));
    this.rl_sj = ((RelativeLayout)paramView.findViewById(2131100244));
    this.iv_sj = ((ImageView)paramView.findViewById(2131100243));
    this.k_yuyin_ll = ((LinearLayout)paramView.findViewById(2131100247));
    this.play_yuyin = ((ImageButton)paramView.findViewById(2131100225));
    this.tv_yuyin_time = ((TextView)paramView.findViewById(2131100226));
    this.k_jiangjie_content = ((TextView)paramView.findViewById(2131100249));
    this.k_tv_error = ((TextView)paramView.findViewById(2131100227));
    this.k_my_tv_error = ((TextView)paramView.findViewById(2131100228));
    this.iv_level_a = ((ImageView)paramView.findViewById(2131100229));
    this.iv_level_b = ((ImageView)paramView.findViewById(2131100230));
    this.iv_level_c = ((ImageView)paramView.findViewById(2131100231));
    this.iv_level_d = ((ImageView)paramView.findViewById(2131100232));
    this.iv_level_e = ((ImageView)paramView.findViewById(2131100233));
    this.btn_again_excercise = ((Button)paramView.findViewById(2131100250));
    this.k_rl_title = ((RelativeLayout)paramView.findViewById(2131100239));
  }

  public void onAttach(Activity paramActivity)
  {
    super.onAttach(paramActivity);
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    case 2131100225:
    default:
    case 2131100430:
    case 2131100242:
    case 2131100250:
    }
    Kpoint localKpoint;
    do
    {
      do
      {
        return;
        dimissPop();
        return;
        ShowPop();
        this.text_knowlege_point_pull_down.setBackgroundResource(2130837690);
        return;
        MobclickAgent.onEvent(this.mContext, "brush_k_exercise");
      }
      while (this.kpointDetails == null);
      localKpoint = this.kpointDetails.getKpoint();
    }
    while (localKpoint == null);
    String str = localKpoint.getId();
    Bundle localBundle = new Bundle();
    localBundle.putString("subjectId", ((KnowledgePointDetailActivity)getActivity()).subjectId);
    localBundle.putString("kpointId", str);
    localBundle.putInt("FromPage", 1);
    startNewActivity(QuestionDetailActivity.class, 2130968581, 2130968579, false, localBundle);
  }

  public View onInflaterRootView(LayoutInflater paramLayoutInflater, Bundle paramBundle)
  {
    return View.inflate(getActivity(), 2130903123, null);
  }

  public void refreshData(KpointDetails paramKpointDetails, List<KpointDetails> paramList)
  {
    this.kpointDetails = paramKpointDetails;
    Iterator localIterator;
    String str2;
    if (paramKpointDetails != null)
    {
      String str1 = paramKpointDetails.getExplainText();
      if ((this.fl_point == null) || (this.fl_point.getChildCount() <= 0))
      {
        List localList = scanKnow("  " + str1);
        if ((localList != null) && (localList.size() > 0))
        {
          localIterator = localList.iterator();
          if (localIterator.hasNext())
            break label379;
          this.fl_point.setVisibility(0);
        }
      }
      this.k_jiangjie_content.setVisibility(8);
      Kpoint localKpoint = paramKpointDetails.getKpoint();
      if (localKpoint != null)
      {
        String str5 = localKpoint.getName();
        this.text_knowlege_point_name1.setText(str5);
        String str6 = localKpoint.getSn();
        this.text_knowlege_point_chapter_count.setText(str6);
      }
      str2 = paramKpointDetails.getGraspRequire();
      if ((str2 != null) && (!str2.equals("")))
        break label403;
      this.ll_sj.setVisibility(8);
      this.rl_sj.setVisibility(8);
      this.iv_sj.setVisibility(8);
      label197: double d1 = paramKpointDetails.getErrorRate();
      double d2 = paramKpointDetails.getMyErrorRate();
      double d3 = d1 * 100.0D;
      String str3 = new DecimalFormat("0.0").format(d3);
      this.k_tv_error.setText(str3 + "%");
      double d4 = d2 * 100.0D;
      String str4 = new DecimalFormat("0.0").format(d4);
      this.k_my_tv_error.setText(str4 + "%");
      switch ((int)paramKpointDetails.getGraspLevel())
      {
      default:
      case 0:
      case 1:
      case 2:
      case 3:
      case 4:
      case 5:
      }
    }
    while (true)
    {
      if ((paramList != null) && (paramList.size() > 0))
        this.cacheAllData = paramList;
      return;
      label379: View localView = (View)localIterator.next();
      this.fl_point.addView(localView);
      break;
      label403: this.tv_k_label.setText(str2);
      this.ll_sj.setVisibility(0);
      this.rl_sj.setVisibility(0);
      this.iv_sj.setVisibility(0);
      break label197;
      this.iv_level_a.setBackgroundResource(2130837660);
      continue;
      this.iv_level_a.setBackgroundResource(2130837660);
      this.iv_level_b.setBackgroundResource(2130837660);
      continue;
      this.iv_level_a.setBackgroundResource(2130837660);
      this.iv_level_b.setBackgroundResource(2130837660);
      this.iv_level_c.setBackgroundResource(2130837660);
      continue;
      this.iv_level_a.setBackgroundResource(2130837660);
      this.iv_level_b.setBackgroundResource(2130837660);
      this.iv_level_c.setBackgroundResource(2130837660);
      this.iv_level_d.setBackgroundResource(2130837660);
      continue;
      this.iv_level_a.setBackgroundResource(2130837660);
      this.iv_level_b.setBackgroundResource(2130837660);
      this.iv_level_c.setBackgroundResource(2130837660);
      this.iv_level_d.setBackgroundResource(2130837660);
      this.iv_level_e.setBackgroundResource(2130837660);
    }
  }

  public void setUserVisibleHint(boolean paramBoolean)
  {
    super.setUserVisibleHint(paramBoolean);
    if ((paramBoolean) && (this.kpointDetails != null))
      LogUtils.myLog("FragmentKpoint setUserVisibleHint  isVisibleToUser=" + paramBoolean);
  }

  class MyAdapter extends BaseAdapter
  {
    MyAdapter()
    {
    }

    public int getCount()
    {
      if ((FragmentKPoint.this.cacheAllData == null) || (FragmentKPoint.this.cacheAllData.size() == 0))
        return 0;
      return FragmentKPoint.this.cacheAllData.size();
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
        paramView = View.inflate(FragmentKPoint.this.getActivity(), 2130903155, null);
        localViewHolder = new ViewHolder(FragmentKPoint.this);
        ViewHolder.access$0(localViewHolder, (TextView)paramView.findViewById(2131099709));
        ViewHolder.access$1(localViewHolder, (TextView)paramView.findViewById(2131100431));
        paramView.setTag(localViewHolder);
      }
      while (true)
      {
        KpointDetails localKpointDetails = (KpointDetails)FragmentKPoint.this.cacheAllData.get(paramInt);
        if (localKpointDetails != null)
        {
          ViewHolder.access$2(localViewHolder).setText(localKpointDetails.getKpoint().getName());
          ViewHolder.access$3(localViewHolder).setText(localKpointDetails.getKpoint().getSn());
        }
        return paramView;
        localViewHolder = (ViewHolder)paramView.getTag();
      }
    }
  }

  class ViewHolder
  {
    private TextView tv_name;
    private TextView tv_num;

    ViewHolder()
    {
    }
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.fragment.FragmentKPoint
 * JD-Core Version:    0.6.0
 */