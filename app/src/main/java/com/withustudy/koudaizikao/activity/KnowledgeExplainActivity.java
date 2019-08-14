package com.withustudy.koudaizikao.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.android.http.LoadControler;
import com.google.gson.Gson;
import com.withustudy.koudaizikao.action.AbsSimpleImplUrlFactory;
import com.withustudy.koudaizikao.action.UrlFactory;
import com.withustudy.koudaizikao.base.AbsBaseActivity;
import com.withustudy.koudaizikao.commen.CommonAdapter;
import com.withustudy.koudaizikao.config.KouDaiSP;
import com.withustudy.koudaizikao.custom.SharePopWindow;
import com.withustudy.koudaizikao.entity.Chapter;
import com.withustudy.koudaizikao.entity.ChapterKpointSummary;
import com.withustudy.koudaizikao.entity.ChapterKpointSummaryW;
import com.withustudy.koudaizikao.entity.Section;
import com.withustudy.koudaizikao.entity.SectionKpointSummary;
import com.withustudy.koudaizikao.entity.req.UserSubject;
import com.withustudy.koudaizikao.tools.LogUtils;
import com.withustudy.koudaizikao.tools.MyLog;
import com.withustudy.koudaizikao.tools.ProTools;
import com.withustudy.koudaizikao.tools.ToolsUtils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressLint({"ShowToast"})
public class KnowledgeExplainActivity extends AbsBaseActivity
  implements OnClickListener
{
  private HashMap<Integer, Boolean> ItemOpenSwitch = new HashMap();
  private ChapterKpointSummaryW chapterKpointSummaryW;
  private PopupWindow chapterSectionListPop;
  private LinearLayout chapter_brush_ll;
  private ImageView chapter_icon;
  private RelativeLayout chapter_list_pop_diss_ll;
  private Button chapter_share_cancel;
  private LinearLayout chapter_share_ll;
  private TextView chapter_tv1;
  private TextView chapter_tv2;
  private ImageButton chapter_weibo_share;
  private ImageButton chapter_weixin;
  private ImageButton chapter_weixin_pengyou;
  private ImageButton collect_ib;
  private Button goFirstBrushBtn;
  private Button goLastBrushBtn;
  private Handler handler = new Handler()
  {
    public void handleMessage(Message paramMessage)
    {
      switch (paramMessage.what)
      {
      default:
        return;
      case 0:
        List localList = KnowledgeExplainActivity.this.chapterKpointSummaryW.getChapterKpointSummary();
        if (localList != null)
          if (KnowledgeExplainActivity.this.mBaseAdapter == null)
          {
            KnowledgeExplainActivity.this.mBaseAdapter = new MyBaseAdapter(KnowledgeExplainActivity.this, localList);
            KnowledgeExplainActivity.this.knowledge_explain_lv.setAdapter(KnowledgeExplainActivity.this.mBaseAdapter);
          }
        while (true)
        {
          KnowledgeExplainActivity.this.mProTools.dismissDislog();
          return;
          KnowledgeExplainActivity.this.mBaseAdapter.notifyDataSetChanged();
          continue;
          LogUtils.myLog("知识点讲解数据解析实体bean实体内部为null");
        }
      case 1:
      }
      KnowledgeExplainActivity.this.showPop();
    }
  };
  private boolean hasFocus;
  private boolean isShare = false;
  private LinearLayout knowledge_back_ll;
  private ListView knowledge_explain_lv;
  private LinearLayout knowledge_have_net_ll;
  private RelativeLayout knowledge_no_net_rl;
  private RelativeLayout knowledge_rl_bottom;
  private ImageButton kwonledge_explain_ib_share;
  private CommonAdapter<ChapterKpointSummary> mAdapter;
  private MyBaseAdapter mBaseAdapter;
  private Bundle mBundle;
  private LoadControler mLoadControler = null;
  public HashMap<String, String> snToNumber = new HashMap()
  {
    private final long serialVersionUID = 1L;
  };
  private String subjectId;
  private String subjectName;
  private TextView tv_name;

  private void checkActivityInitFinshed()
  {
    3 local3 = new Runnable()
    {
      public void run()
      {
        if (KnowledgeExplainActivity.this.hasFocus)
        {
          KnowledgeExplainActivity.this.showPop();
          KnowledgeExplainActivity.this.handler.removeCallbacks(this);
          return;
        }
        KnowledgeExplainActivity.this.handler.postDelayed(this, 30L);
      }
    };
    this.handler.post(local3);
  }

  private void dismissPop()
  {
    if (this.chapterSectionListPop != null)
    {
      this.chapterSectionListPop.dismiss();
      this.chapterSectionListPop = null;
    }
    backgroundAlpha(1.0F);
    this.isShare = false;
  }

  private void showPop()
  {
    if (this.chapterSectionListPop != null)
    {
      this.chapterSectionListPop.dismiss();
      this.chapterSectionListPop = null;
    }
    View localView = getLayoutInflater().inflate(2130903092, null);
    this.chapter_list_pop_diss_ll = ((RelativeLayout)localView.findViewById(2131100039));
    this.chapter_share_ll = ((LinearLayout)localView.findViewById(2131100044));
    this.chapter_brush_ll = ((LinearLayout)localView.findViewById(2131100043));
    this.chapter_icon = ((ImageView)localView.findViewById(2131100040));
    this.chapter_tv1 = ((TextView)localView.findViewById(2131100041));
    this.chapter_tv2 = ((TextView)localView.findViewById(2131100042));
    if (this.isShare)
    {
      this.chapter_share_ll.setVisibility(0);
      this.chapter_brush_ll.setVisibility(8);
      this.chapter_weibo_share = ((ImageButton)localView.findViewById(2131099965));
      this.chapter_weixin_pengyou = ((ImageButton)localView.findViewById(2131099966));
      this.chapter_weixin = ((ImageButton)localView.findViewById(2131099967));
      this.chapter_share_cancel = ((Button)localView.findViewById(2131100045));
      this.chapter_weixin.setOnClickListener(this);
      this.chapter_weixin_pengyou.setOnClickListener(this);
      this.chapter_weibo_share.setOnClickListener(this);
      this.chapter_share_cancel.setOnClickListener(this);
      this.chapter_list_pop_diss_ll.setOnClickListener(this);
      this.chapter_tv1.setVisibility(8);
      this.chapter_tv2.setText("号召更多的小伙伴来学习吧~");
      this.chapter_tv2.setVisibility(0);
      this.chapter_icon.setBackgroundResource(2130837702);
      localView.setFocusable(true);
      localView.setFocusableInTouchMode(true);
      if (!this.isShare)
        break label492;
    }
    label492: for (this.chapterSectionListPop = new PopupWindow(localView, this.mSP.getWidth(), ToolsUtils.dip2px(this, 650.0F)); ; this.chapterSectionListPop = new PopupWindow(localView, this.mSP.getWidth(), ToolsUtils.dip2px(this, 450.0F)))
    {
      this.chapterSectionListPop.setAnimationStyle(2131361794);
      this.chapterSectionListPop.setFocusable(true);
      localView.setOnKeyListener(new OnKeyListener()
      {
        public boolean onKey(View paramView, int paramInt, KeyEvent paramKeyEvent)
        {
          if (paramInt == 4)
          {
            KnowledgeExplainActivity.this.chapterSectionListPop.dismiss();
            KnowledgeExplainActivity.this.chapterSectionListPop = null;
            KnowledgeExplainActivity.this.backgroundAlpha(1.0F);
            return true;
          }
          return false;
        }
      });
      this.chapterSectionListPop.showAtLocation(this.knowledge_explain_lv, 80, 0, 0);
      backgroundAlpha(0.6F);
      return;
      this.chapter_brush_ll.setVisibility(0);
      this.chapter_share_ll.setVisibility(8);
      this.chapter_tv1.setVisibility(0);
      this.chapter_tv1.setText("上次错题知识点");
      this.chapter_tv2.setText("小袋已经下载到本地了");
      this.chapter_tv2.setVisibility(0);
      this.chapter_icon.setBackgroundResource(2130837929);
      this.goFirstBrushBtn = ((Button)localView.findViewById(2131100008));
      this.goFirstBrushBtn.setText("选择章节");
      this.goLastBrushBtn = ((Button)localView.findViewById(2131100009));
      this.goLastBrushBtn.setText("继续上次复习");
      this.goFirstBrushBtn.setOnClickListener(this);
      this.goLastBrushBtn.setOnClickListener(this);
      this.chapter_list_pop_diss_ll.setOnClickListener(this);
      break;
    }
  }

  public void backgroundAlpha(float paramFloat)
  {
    WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
    localLayoutParams.alpha = paramFloat;
    getWindow().setAttributes(localLayoutParams);
  }

  protected void bindData()
  {
  }

  protected void initData()
  {
    Intent localIntent = getIntent();
    try
    {
      this.mBundle = localIntent.getExtras();
      this.subjectId = this.mBundle.getString("subjectId");
      this.subjectName = this.mBundle.getString("subjectName");
      label41: this.tv_name.setText(this.subjectName);
      if (ToolsUtils.isNetworkConnected(this))
      {
        this.knowledge_have_net_ll.setVisibility(0);
        this.knowledge_no_net_rl.setVisibility(8);
        this.mProTools.startDialog(true);
        UserSubject localUserSubject = new UserSubject();
        localUserSubject.setVersionName(this.mSP.getVersionName());
        localUserSubject.setClientType(ToolsUtils.getSDK());
        localUserSubject.setImei(ToolsUtils.getImei(this.mContext));
        localUserSubject.setNet(ToolsUtils.getStringNetworkType(this.mContext));
        localUserSubject.setUid(this.mSP.getUserId());
        localUserSubject.setSubjectId(this.subjectId);
        UrlFactory.getInstance().getChapterKpointSummary().constructUrl(this, localUserSubject, 0);
        return;
      }
      this.knowledge_have_net_ll.setVisibility(8);
      this.knowledge_no_net_rl.setVisibility(0);
      return;
    }
    catch (Exception localException)
    {
      break label41;
    }
  }

  protected void initListener()
  {
    this.knowledge_back_ll.setOnClickListener(this);
    this.kwonledge_explain_ib_share.setOnClickListener(this);
  }

  protected void initView()
  {
    this.knowledge_explain_lv = ((ListView)findViewById(2131099815));
    this.knowledge_have_net_ll = ((LinearLayout)findViewById(2131099812));
    this.knowledge_no_net_rl = ((RelativeLayout)findViewById(2131099816));
    this.knowledge_back_ll = ((LinearLayout)findViewById(2131099813));
    this.kwonledge_explain_ib_share = ((ImageButton)findViewById(2131099814));
    this.tv_name = ((TextView)findViewById(2131099709));
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    case 2131099965:
    case 2131099966:
    case 2131099967:
    case 2131100009:
    case 2131100436:
    case 2131100444:
    default:
      return;
    case 2131100039:
      dismissPop();
      return;
    case 2131099813:
      finish();
      return;
    case 2131100008:
      dismissPop();
      return;
    case 2131099814:
      new SharePopWindow(this, this.knowledge_explain_lv).showPop();
      return;
    case 2131100045:
    }
    dismissPop();
  }

  public void onSuccess(String paramString1, Map<String, String> paramMap, String paramString2, int paramInt)
  {
    super.onSuccess(paramString1, paramMap, paramString2, paramInt);
    if (paramString1 != null)
    {
      Gson localGson = new Gson();
      try
      {
        this.chapterKpointSummaryW = ((ChapterKpointSummaryW)localGson.fromJson(paramString1, ChapterKpointSummaryW.class));
        if (this.chapterKpointSummaryW != null)
        {
          this.handler.sendEmptyMessage(0);
          return;
        }
        LogUtils.myLog("知识点讲解数据解析实体bean为null");
        return;
      }
      catch (Exception localException)
      {
        LogUtils.myLog("知识点讲解解析异常:" + localException.getMessage());
      }
    }
  }

  public void onWindowFocusChanged(boolean paramBoolean)
  {
    super.onWindowFocusChanged(paramBoolean);
    this.hasFocus = paramBoolean;
  }

  protected void setContentView()
  {
    setContentView(2130903057);
  }

  class MyBaseAdapter extends BaseAdapter
  {
    private List<ChapterKpointSummary> data;

    public MyBaseAdapter()
    {
      Object localObject;
      this.data = localObject;
    }

    public int getCount()
    {
      if (this.data == null)
        return 0;
      return this.data.size();
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
      ChapterKpointSummary localChapterKpointSummary;
      LinearLayout.LayoutParams localLayoutParams1;
      List localList;
      int i;
      if (paramView == null)
      {
        paramView = View.inflate(KnowledgeExplainActivity.this.getApplicationContext(), 2130903159, null);
        localViewHolder = new ViewHolder(KnowledgeExplainActivity.this);
        ViewHolder.access$0(localViewHolder, (ImageView)paramView.findViewById(2131100033));
        ViewHolder.access$1(localViewHolder, (ImageView)paramView.findViewById(2131100446));
        ViewHolder.access$2(localViewHolder, (ImageButton)paramView.findViewById(2131100449));
        ViewHolder.access$3(localViewHolder, (TextView)paramView.findViewById(2131100032));
        ViewHolder.access$4(localViewHolder, (TextView)paramView.findViewById(2131100447));
        ViewHolder.access$5(localViewHolder, (TextView)paramView.findViewById(2131100448));
        ViewHolder.access$6(localViewHolder, (LinearLayout)paramView.findViewById(2131100450));
        paramView.setTag(localViewHolder);
        localChapterKpointSummary = (ChapterKpointSummary)this.data.get(paramInt);
        Boolean localBoolean = (Boolean)KnowledgeExplainActivity.this.ItemOpenSwitch.get(Integer.valueOf(paramInt));
        String str = localChapterKpointSummary.getChapter().getSn();
        ViewHolder.access$7(localViewHolder).setText(str);
        ViewHolder.access$8(localViewHolder).setText(localChapterKpointSummary.getChapter().getName());
        ViewHolder.access$9(localViewHolder).setText("共" + localChapterKpointSummary.getTotalKpointNum() + "个知识点,已学" + localChapterKpointSummary.getLearnedKpointNum() + "个知识点");
        localLayoutParams1 = (LinearLayout.LayoutParams) ViewHolder.access$10(localViewHolder).getLayoutParams();
        localLayoutParams1.leftMargin = ToolsUtils.dip2px(KnowledgeExplainActivity.this.getApplicationContext(), 15.0F);
        ViewHolder.access$10(localViewHolder).setLayoutParams(localLayoutParams1);
        ViewHolder.access$11(localViewHolder).setVisibility(8);
        if ((localBoolean == null) || (!localBoolean.booleanValue()))
          break label635;
        ViewHolder.access$12(localViewHolder).setVisibility(0);
        ViewHolder.access$13(localViewHolder).setVisibility(8);
        ViewHolder.access$11(localViewHolder).setVisibility(0);
        LinearLayout.LayoutParams localLayoutParams2 = (LinearLayout.LayoutParams) ViewHolder.access$10(localViewHolder).getLayoutParams();
        localLayoutParams2.leftMargin = ToolsUtils.dip2px(KnowledgeExplainActivity.this.getApplicationContext(), 25.0F);
        ViewHolder.access$10(localViewHolder).setLayoutParams(localLayoutParams2);
        if ((ViewHolder.access$12(localViewHolder) != null) && (ViewHolder.access$12(localViewHolder).getChildCount() > 0))
          ViewHolder.access$12(localViewHolder).removeAllViews();
        localList = localChapterKpointSummary.getSectionKpointSummary();
        MyLog.log("knowledgeItemList", Integer.valueOf(localList.size()));
        i = 0;
        label434: if (i < localList.size())
          break label481;
      }
      while (true)
      {
        2 local2 = new OnClickListener(paramInt, localViewHolder, localChapterKpointSummary)
        {
          public void onClick(View paramView)
          {
            Boolean localBoolean = (Boolean)KnowledgeExplainActivity.this.ItemOpenSwitch.get(Integer.valueOf(this.val$position));
            if ((localBoolean == null) || (!localBoolean.booleanValue()))
            {
              ViewHolder.access$12(this.val$vh).setVisibility(0);
              ViewHolder.access$13(this.val$vh).setVisibility(8);
              ViewHolder.access$11(this.val$vh).setVisibility(0);
              LinearLayout.LayoutParams localLayoutParams1 = (LinearLayout.LayoutParams) ViewHolder.access$10(this.val$vh).getLayoutParams();
              localLayoutParams1.leftMargin = ToolsUtils.dip2px(KnowledgeExplainActivity.this.getApplicationContext(), 25.0F);
              ViewHolder.access$10(this.val$vh).setLayoutParams(localLayoutParams1);
              if ((ViewHolder.access$12(this.val$vh) != null) && (ViewHolder.access$12(this.val$vh).getChildCount() > 0))
                ViewHolder.access$12(this.val$vh).removeAllViews();
              List localList = this.val$item.getSectionKpointSummary();
              MyLog.log("knowledgeItemList", Integer.valueOf(localList.size()));
              for (int i = 0; ; i++)
              {
                if (i >= localList.size())
                {
                  KnowledgeExplainActivity.this.ItemOpenSwitch.put(Integer.valueOf(this.val$position), Boolean.valueOf(true));
                  return;
                }
                SectionKpointSummary localSectionKpointSummary = (SectionKpointSummary)localList.get(i);
                LinearLayout localLinearLayout = (LinearLayout)View.inflate(KnowledgeExplainActivity.this.getApplicationContext(), 2130903157, null);
                ((TextView)localLinearLayout.findViewById(2131100438)).setText("共" + localSectionKpointSummary.getTotalKpointNum() + "个知识点,已学" + localSectionKpointSummary.getLearnedKpointNum() + "个知识点");
                ((TextView)localLinearLayout.findViewById(2131100437)).setText(localSectionKpointSummary.getSection().getName());
                ((ImageButton)localLinearLayout.findViewById(2131100439)).setOnClickListener(KnowledgeExplainActivity.this);
                localLinearLayout.setOnClickListener(new OnClickListener(localSectionKpointSummary)
                {
                  public void onClick(View paramView)
                  {
                    KnowledgeExplainActivity.this.mBundle = new Bundle();
                    Section localSection = this.val$knowledgeItem.getSection();
                    String str1 = localSection.getId();
                    String str2 = localSection.getName();
                    String str3 = localSection.getSn();
                    KnowledgeExplainActivity.this.mBundle.putString("subjectId", KnowledgeExplainActivity.this.subjectId);
                    KnowledgeExplainActivity.this.mBundle.putString("sectionId", str1);
                    KnowledgeExplainActivity.this.mBundle.putString("sectionName", str2);
                    KnowledgeExplainActivity.this.mBundle.putString("sectionSn", str3);
                    KnowledgeExplainActivity.this.startNewActivity(KnowledgePointDetailActivity.class, 2130968581, 2130968579, false, KnowledgeExplainActivity.this.mBundle);
                  }
                });
                ViewHolder.access$12(this.val$vh).addView(localLinearLayout);
              }
            }
            ViewHolder.access$12(this.val$vh).setVisibility(8);
            ViewHolder.access$11(this.val$vh).setVisibility(8);
            LinearLayout.LayoutParams localLayoutParams2 = (LinearLayout.LayoutParams) ViewHolder.access$10(this.val$vh).getLayoutParams();
            localLayoutParams2.leftMargin = ToolsUtils.dip2px(KnowledgeExplainActivity.this.getApplicationContext(), 15.0F);
            ViewHolder.access$10(this.val$vh).setLayoutParams(localLayoutParams2);
            ViewHolder.access$13(this.val$vh).setVisibility(0);
            if ((ViewHolder.access$12(this.val$vh) != null) && (ViewHolder.access$12(this.val$vh).getChildCount() > 0))
              ViewHolder.access$12(this.val$vh).removeAllViews();
            KnowledgeExplainActivity.this.ItemOpenSwitch.put(Integer.valueOf(this.val$position), Boolean.valueOf(false));
          }
        };
        paramView.setOnClickListener(local2);
        return paramView;
        localViewHolder = (ViewHolder)paramView.getTag();
        break;
        label481: SectionKpointSummary localSectionKpointSummary = (SectionKpointSummary)localList.get(i);
        LinearLayout localLinearLayout = (LinearLayout)View.inflate(KnowledgeExplainActivity.this.getApplicationContext(), 2130903157, null);
        ((TextView)localLinearLayout.findViewById(2131100438)).setText("共" + localSectionKpointSummary.getTotalKpointNum() + "个知识点,已学" + localSectionKpointSummary.getLearnedKpointNum() + "个知识点");
        ((TextView)localLinearLayout.findViewById(2131100437)).setText(localSectionKpointSummary.getSection().getName());
        ((ImageButton)localLinearLayout.findViewById(2131100439)).setOnClickListener(KnowledgeExplainActivity.this);
        localLinearLayout.setOnClickListener(new OnClickListener(localSectionKpointSummary)
        {
          public void onClick(View paramView)
          {
            KnowledgeExplainActivity.this.mBundle = new Bundle();
            Section localSection = this.val$knowledgeItem.getSection();
            String str1 = localSection.getId();
            String str2 = localSection.getName();
            String str3 = localSection.getSn();
            KnowledgeExplainActivity.this.mBundle.putString("subjectId", KnowledgeExplainActivity.this.subjectId);
            KnowledgeExplainActivity.this.mBundle.putString("sectionId", str1);
            KnowledgeExplainActivity.this.mBundle.putString("sectionName", str2);
            KnowledgeExplainActivity.this.mBundle.putString("sectionSn", str3);
            KnowledgeExplainActivity.this.startNewActivity(KnowledgePointDetailActivity.class, 2130968581, 2130968579, false, KnowledgeExplainActivity.this.mBundle);
          }
        });
        ViewHolder.access$12(localViewHolder).addView(localLinearLayout);
        i++;
        break label434;
        label635: ViewHolder.access$12(localViewHolder).setVisibility(8);
        ViewHolder.access$11(localViewHolder).setVisibility(8);
        ViewHolder.access$10(localViewHolder).setLayoutParams(localLayoutParams1);
        ViewHolder.access$13(localViewHolder).setVisibility(0);
        if ((ViewHolder.access$12(localViewHolder) == null) || (ViewHolder.access$12(localViewHolder).getChildCount() <= 0))
          continue;
        ViewHolder.access$12(localViewHolder).removeAllViews();
      }
    }
  }

  class ViewHolder
  {
    private ImageView know_xuxian_iv;
    private ImageView know_xuxian_w;
    private ImageButton knowledge_collect_ib;
    private TextView knowledge_item_name_t1;
    private TextView knowledge_learn_t;
    private LinearLayout knowledge_list_ll;
    private TextView knowledge_number_tv_t;

    ViewHolder()
    {
    }
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.activity.KnowledgeExplainActivity
 * JD-Core Version:    0.6.0
 */