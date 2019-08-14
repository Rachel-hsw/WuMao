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
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.android.http.LoadControler;
import com.google.gson.Gson;
import com.umeng.analytics.MobclickAgent;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.withustudy.koudaizikao.action.AbsSimpleImplUrlFactory;
import com.withustudy.koudaizikao.action.UrlFactory;
import com.withustudy.koudaizikao.base.AbsBaseActivity;
import com.withustudy.koudaizikao.commen.CommonAdapter;
import com.withustudy.koudaizikao.config.KouDaiSP;
import com.withustudy.koudaizikao.custom.SharePopWindow;
import com.withustudy.koudaizikao.entity.Chapter;
import com.withustudy.koudaizikao.entity.ChapterSectionListBean;
import com.withustudy.koudaizikao.entity.ChapterSummary;
import com.withustudy.koudaizikao.entity.Section;
import com.withustudy.koudaizikao.entity.SectionSummary;
import com.withustudy.koudaizikao.entity.req.ReqSubjectState;
import com.withustudy.koudaizikao.tools.LogUtils;
import com.withustudy.koudaizikao.tools.MyLog;
import com.withustudy.koudaizikao.tools.ProTools;
import com.withustudy.koudaizikao.tools.ToolsUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@SuppressLint({"ShowToast"})
public class ChapterSectionListActivity extends AbsBaseActivity
  implements OnClickListener
{
  private static final int action_get_subject_chapter_infor = 102;
  public static final int chapter_section_brush_init = 9;
  public static final int chapter_section_brush_last = 12;
  public static HashMap<String, String> snToNumber = new HashMap()
  {
    private static final long serialVersionUID = 1L;
  };

  @SuppressLint({"UseSparseArrays"})
  private HashMap<Integer, Boolean> ItemSwitch = new HashMap();
  private List<ChapterSummary> cacheData = new ArrayList();
  private ChapterSectionListBean chapterSectionListBean;
  private PopupWindow chapterSectionListPop;
  private LinearLayout chapter_brush_ll;
  private ImageView chapter_icon;
  private RelativeLayout chapter_list_pop_diss_ll;
  private LinearLayout chapter_section_back_ll;
  private ListView chapter_section_lv;
  private ImageButton chapter_section_share_ib;
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
      case 102:
        int i;
        Iterator localIterator;
        while (true)
        {
          return;
          List localList = ChapterSectionListActivity.this.chapterSectionListBean.getChapterSummary();
          ChapterSectionListActivity.this.cacheData.clear();
          ChapterSectionListActivity.this.cacheData.addAll(localList);
          if (localList == null)
            break label268;
          if (ChapterSectionListActivity.this.mBaseAdapter != null)
            break;
          ChapterSectionListActivity.this.mBaseAdapter = new MyBaseAdapter(ChapterSectionListActivity.this, ChapterSectionListActivity.this.cacheData);
          ChapterSectionListActivity.this.chapter_section_lv.setAdapter(ChapterSectionListActivity.this.mBaseAdapter);
          boolean bool = ChapterSectionListActivity.this.chapterSectionListBean.isHasLastFlag();
          if (!bool)
            continue;
          LogUtils.myLog("初次蒙层hasLastFlag=" + bool);
          LogUtils.myLog("hasLastFlag=" + bool);
          i = 0;
          localIterator = localList.iterator();
        }
        while (true)
        {
          if (!localIterator.hasNext())
          {
            ChapterSectionListActivity.this.checkActivityInitFinshed(i);
            return;
            ChapterSectionListActivity.this.mBaseAdapter.notifyDataSetChanged();
            break;
          }
          i += ((ChapterSummary)localIterator.next()).getDoneExerciseNum();
          LogUtils.myLog("total=" + i);
        }
        label268: LogUtils.myLog("章节列表数据解析实体bean内部为null");
        return;
      case 1:
      }
      ChapterSectionListActivity.this.showPop(0);
    }
  };
  private boolean hasFocus;
  private LinearLayout have_net_ll;
  private boolean isInit = true;
  private boolean isShare = false;
  private CommonAdapter<ChapterSummary> mAdapter;
  private MyBaseAdapter mBaseAdapter;
  private LoadControler mLoadControler = null;
  private RelativeLayout no_net_rl;
  private String subjectId = "";
  private String subjectName = "";
  private TextView tv_name;

  private void checkActivityInitFinshed(int paramInt)
  {
    if (this.isInit)
    {
      3 local3 = new Runnable(paramInt)
      {
        public void run()
        {
          ChapterSectionListActivity.this.findViewById(2131099735);
          if (ChapterSectionListActivity.this.hasFocus)
          {
            ChapterSectionListActivity.this.showPop(this.val$total);
            ChapterSectionListActivity.this.handler.removeCallbacks(this);
            return;
          }
          ChapterSectionListActivity.this.handler.postDelayed(this, 30L);
        }
      };
      this.handler.post(local3);
      this.isInit = false;
    }
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

  private void showPop(int paramInt)
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
        break label493;
    }
    label493: for (this.chapterSectionListPop = new PopupWindow(localView, this.mSP.getWidth(), ToolsUtils.dip2px(this, 650.0F)); ; this.chapterSectionListPop = new PopupWindow(localView, this.mSP.getWidth(), ToolsUtils.dip2px(this, 450.0F)))
    {
      this.chapterSectionListPop.setAnimationStyle(2131361794);
      this.chapterSectionListPop.setFocusable(true);
      localView.setOnKeyListener(new OnKeyListener()
      {
        public boolean onKey(View paramView, int paramInt, KeyEvent paramKeyEvent)
        {
          if (paramInt == 4)
          {
            ChapterSectionListActivity.this.chapterSectionListPop.dismiss();
            ChapterSectionListActivity.this.chapterSectionListPop = null;
            ChapterSectionListActivity.this.backgroundAlpha(1.0F);
            return true;
          }
          return false;
        }
      });
      this.chapterSectionListPop.showAtLocation(this.chapter_section_lv, 80, 0, 0);
      backgroundAlpha(0.3F);
      return;
      this.chapter_brush_ll.setVisibility(0);
      this.chapter_share_ll.setVisibility(8);
      this.chapter_tv1.setVisibility(0);
      this.chapter_tv1.setText("学霸你好~");
      this.chapter_tv2.setText("你已经做到第" + paramInt + "道题");
      this.chapter_tv2.setVisibility(0);
      this.chapter_icon.setBackgroundResource(2130837932);
      this.goFirstBrushBtn = ((Button)localView.findViewById(2131100008));
      this.goLastBrushBtn = ((Button)localView.findViewById(2131100009));
      this.goFirstBrushBtn.setOnClickListener(this);
      this.goLastBrushBtn.setOnClickListener(this);
      this.chapter_list_pop_diss_ll.setOnClickListener(this);
      break;
    }
  }

  private void showSectionList()
  {
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
    boolean bool = ToolsUtils.isNetworkConnected(this);
    try
    {
      Bundle localBundle = getIntent().getExtras();
      this.subjectId = localBundle.getString("subjectId");
      this.subjectName = localBundle.getString("subjectName");
      label38: this.tv_name.setText(this.subjectName);
      this.isShare = false;
      if (bool)
      {
        this.mProTools.startDialog(true);
        this.have_net_ll.setVisibility(0);
        this.no_net_rl.setVisibility(8);
        String str = this.mSP.getUserId();
        ReqSubjectState localReqSubjectState = new ReqSubjectState();
        localReqSubjectState.setVersionName(this.mSP.getVersionName());
        localReqSubjectState.setClientType(ToolsUtils.getSDK());
        localReqSubjectState.setImei(ToolsUtils.getImei(this.mContext));
        localReqSubjectState.setNet(ToolsUtils.getStringNetworkType(this.mContext));
        localReqSubjectState.setUid(str);
        localReqSubjectState.setSubjectId(this.subjectId);
        UrlFactory.getInstance().getChapterSectionList().constructUrl(this, localReqSubjectState, 102);
        return;
      }
      this.have_net_ll.setVisibility(8);
      this.no_net_rl.setVisibility(0);
      return;
    }
    catch (Exception localException)
    {
      break label38;
    }
  }

  protected void initListener()
  {
    this.chapter_section_back_ll.setOnClickListener(this);
    this.chapter_section_share_ib.setOnClickListener(this);
  }

  protected void initView()
  {
    this.chapter_section_lv = ((ListView)findViewById(2131099739));
    this.have_net_ll = ((LinearLayout)findViewById(2131099736));
    this.no_net_rl = ((RelativeLayout)findViewById(2131099741));
    this.chapter_section_back_ll = ((LinearLayout)findViewById(2131099737));
    this.chapter_section_share_ib = ((ImageButton)findViewById(2131099738));
    this.tv_name = ((TextView)findViewById(2131099709));
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
      return;
    case 2131100039:
      dismissPop();
      return;
    case 2131099737:
      finish();
      return;
    case 2131100008:
      MobclickAgent.onEvent(this.mContext, "brush_c_from_start");
      dismissPop();
      return;
    case 2131100009:
      MobclickAgent.onEvent(this.mContext, "brush_c_continue");
      dismissPop();
      Bundle localBundle = new Bundle();
      localBundle.putString("subjectId", this.subjectId);
      localBundle.putInt("FromPage", 12);
      startNewActivity(QuestionDetailActivity.class, 2130968581, 2130968579, false, localBundle);
      return;
    case 2131099738:
      new SharePopWindow(this, this.chapter_section_lv).showPop();
      return;
    case 2131100045:
      dismissPop();
      return;
    case 2131099965:
      share(SHARE_MEDIA.SINA, "http://share.kdzikao.com/app/share.page");
      return;
    case 2131099966:
      share(SHARE_MEDIA.WEIXIN_CIRCLE, "http://share.kdzikao.com/app/share.page");
      return;
    case 2131099967:
    }
    share(SHARE_MEDIA.WEIXIN, "http://share.kdzikao.com/app/share.page");
  }

  protected void onRestart()
  {
    super.onRestart();
    this.mProTools.startDialog(true);
    this.have_net_ll.setVisibility(0);
    this.no_net_rl.setVisibility(8);
    String str = this.mSP.getUserId();
    ReqSubjectState localReqSubjectState = new ReqSubjectState();
    localReqSubjectState.setVersionName(this.mSP.getVersionName());
    localReqSubjectState.setClientType(ToolsUtils.getSDK());
    localReqSubjectState.setImei(ToolsUtils.getImei(this.mContext));
    localReqSubjectState.setNet(ToolsUtils.getStringNetworkType(this.mContext));
    localReqSubjectState.setUid(str);
    localReqSubjectState.setSubjectId(this.subjectId);
    UrlFactory.getInstance().getChapterSectionList().constructUrl(this, localReqSubjectState, 102);
  }

  public void onSuccess(String paramString1, Map<String, String> paramMap, String paramString2, int paramInt)
  {
    super.onSuccess(paramString1, paramMap, paramString2, paramInt);
    this.mProTools.dismissDislog();
    if (paramString1 != null)
    {
      Gson localGson = new Gson();
      try
      {
        this.chapterSectionListBean = ((ChapterSectionListBean)localGson.fromJson(paramString1, ChapterSectionListBean.class));
        if (this.chapterSectionListBean != null)
        {
          this.handler.sendEmptyMessage(102);
          return;
        }
        LogUtils.myLog("章节列表数据解析实体bean为null");
        return;
      }
      catch (Exception localException)
      {
        LogUtils.myLog("章节列表解析异常:" + localException.getMessage());
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
    setContentView(2130903047);
  }

  class MyBaseAdapter extends BaseAdapter
  {
    private List<ChapterSummary> data;

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
      ChapterSummary localChapterSummary;
      List localList;
      int i;
      if (paramView == null)
      {
        paramView = View.inflate(ChapterSectionListActivity.this.getApplicationContext(), 2130903091, null);
        localViewHolder = new ViewHolder(ChapterSectionListActivity.this);
        ViewHolder.access$0(localViewHolder, (ImageView)paramView.findViewById(2131100033));
        ViewHolder.access$1(localViewHolder, paramView.findViewById(2131100036));
        ViewHolder.access$2(localViewHolder, (TextView)paramView.findViewById(2131100035));
        ViewHolder.access$3(localViewHolder, (TextView)paramView.findViewById(2131100032));
        ViewHolder.access$4(localViewHolder, (TextView)paramView.findViewById(2131100037));
        ViewHolder.access$5(localViewHolder, (TextView)paramView.findViewById(2131100034));
        ViewHolder.access$6(localViewHolder, (LinearLayout)paramView.findViewById(2131100031));
        ViewHolder.access$7(localViewHolder, (LinearLayout)paramView.findViewById(2131100038));
        paramView.setTag(localViewHolder);
        ViewHolder.access$8(localViewHolder).setVisibility(8);
        localChapterSummary = (ChapterSummary)this.data.get(paramInt);
        ViewHolder.access$9(localViewHolder).setVisibility(0);
        String str1 = localChapterSummary.getGraspLevel();
        ViewHolder.access$10(localViewHolder).setText(str1 + "%");
        String str2 = localChapterSummary.getChapter().getSn();
        ViewHolder.access$11(localViewHolder).setText(str2);
        ViewHolder.access$12(localViewHolder).setText(localChapterSummary.getChapter().getName());
        ViewHolder.access$13(localViewHolder).setText(localChapterSummary.getDoneExerciseNum() + "题/" + localChapterSummary.getTotalExerciseNum() + "题");
        Boolean localBoolean = (Boolean)ChapterSectionListActivity.this.ItemSwitch.get(Integer.valueOf(paramInt));
        if ((localBoolean == null) || (!localBoolean.booleanValue()))
          break label652;
        ViewHolder.access$14(localViewHolder).setVisibility(0);
        ViewHolder.access$9(localViewHolder).setVisibility(8);
        ViewHolder.access$8(localViewHolder).setVisibility(0);
        if ((ViewHolder.access$14(localViewHolder) != null) && (ViewHolder.access$14(localViewHolder).getChildCount() > 0))
          ViewHolder.access$14(localViewHolder).removeAllViews();
        localList = localChapterSummary.getSectionSummary();
        MyLog.log("sectionList", Integer.valueOf(localList.size()));
        i = 0;
        label407: if (i < localList.size())
          break label462;
      }
      while (true)
      {
        LinearLayout localLinearLayout1 = ViewHolder.access$15(localViewHolder);
        2 local2 = new OnClickListener(paramInt, localViewHolder, localChapterSummary)
        {
          public void onClick(View paramView)
          {
            Boolean localBoolean = (Boolean)ChapterSectionListActivity.this.ItemSwitch.get(Integer.valueOf(this.val$position));
            if ((localBoolean == null) || (!localBoolean.booleanValue()))
            {
              ViewHolder.access$9(this.val$vh).setVisibility(8);
              ViewHolder.access$8(this.val$vh).setVisibility(0);
              ViewHolder.access$14(this.val$vh).setVisibility(0);
              if ((ViewHolder.access$14(this.val$vh) != null) && (ViewHolder.access$14(this.val$vh).getChildCount() > 0))
                ViewHolder.access$14(this.val$vh).removeAllViews();
              List localList = this.val$item.getSectionSummary();
              MyLog.log("sectionList", Integer.valueOf(localList.size()));
              for (int i = 0; ; i++)
              {
                if (i >= localList.size())
                {
                  ChapterSectionListActivity.this.ItemSwitch.put(Integer.valueOf(this.val$position), Boolean.valueOf(true));
                  return;
                }
                SectionSummary localSectionSummary = (SectionSummary)localList.get(i);
                LinearLayout localLinearLayout = (LinearLayout)View.inflate(ChapterSectionListActivity.this.getApplicationContext(), 2130903170, null);
                localLinearLayout.setOnClickListener(new OnClickListener(localSectionSummary)
                {
                  public void onClick(View paramView)
                  {
                    Bundle localBundle = new Bundle();
                    Section localSection = this.val$section.getSection();
                    String str1 = localSection.getId();
                    String str2 = localSection.getName();
                    String str3 = localSection.getSn();
                    localBundle.putString("section_id", str1);
                    localBundle.putString("section_name", str2);
                    localBundle.putString("section_sn", str3);
                    localBundle.putString("subjectName", ChapterSectionListActivity.this.subjectName);
                    localBundle.putString("subjectId", ChapterSectionListActivity.this.subjectId);
                    localBundle.putInt("FromPage", 9);
                    ChapterSectionListActivity.this.startNewActivity(QuestionDetailActivity.class, 2130968581, 2130968579, false, localBundle);
                  }
                });
                TextView localTextView = (TextView)localLinearLayout.findViewById(2131100477);
                ((TextView)localLinearLayout.findViewById(2131099957)).setText(localSectionSummary.getGraspLevel() + "%");
                localTextView.setText(localSectionSummary.getDoneExerciseNum() + "题/" + localSectionSummary.getTotalExerciseNum() + "题");
                ((RelativeLayout)localLinearLayout.findViewById(2131100478));
                ((TextView)localLinearLayout.findViewById(2131100476)).setText(localSectionSummary.getSection().getName());
                ViewHolder.access$14(this.val$vh).addView(localLinearLayout);
              }
            }
            ViewHolder.access$9(this.val$vh).setVisibility(0);
            if ((ViewHolder.access$14(this.val$vh) != null) && (ViewHolder.access$14(this.val$vh).getChildCount() > 0))
              ViewHolder.access$14(this.val$vh).removeAllViews();
            ViewHolder.access$8(this.val$vh).setVisibility(8);
            ViewHolder.access$14(this.val$vh).setVisibility(8);
            ChapterSectionListActivity.this.ItemSwitch.put(Integer.valueOf(this.val$position), Boolean.valueOf(false));
          }
        };
        localLinearLayout1.setOnClickListener(local2);
        return paramView;
        localViewHolder = (ViewHolder)paramView.getTag();
        break;
        label462: SectionSummary localSectionSummary = (SectionSummary)localList.get(i);
        LinearLayout localLinearLayout2 = (LinearLayout)View.inflate(ChapterSectionListActivity.this.getApplicationContext(), 2130903170, null);
        1 local1 = new OnClickListener(localSectionSummary)
        {
          public void onClick(View paramView)
          {
            Bundle localBundle = new Bundle();
            Section localSection = this.val$section.getSection();
            String str1 = localSection.getId();
            String str2 = localSection.getName();
            String str3 = localSection.getSn();
            localBundle.putString("section_id", str1);
            localBundle.putString("section_name", str2);
            localBundle.putString("subjectName", ChapterSectionListActivity.this.subjectName);
            localBundle.putString("section_sn", str3);
            localBundle.putString("subjectId", ChapterSectionListActivity.this.subjectId);
            localBundle.putInt("FromPage", 9);
            ChapterSectionListActivity.this.startNewActivity(QuestionDetailActivity.class, 2130968581, 2130968579, false, localBundle);
          }
        };
        localLinearLayout2.setOnClickListener(local1);
        TextView localTextView = (TextView)localLinearLayout2.findViewById(2131100477);
        ((TextView)localLinearLayout2.findViewById(2131099957)).setText(localSectionSummary.getGraspLevel() + "%");
        localTextView.setText(localSectionSummary.getDoneExerciseNum() + "题/" + localSectionSummary.getTotalExerciseNum() + "题");
        ((RelativeLayout)localLinearLayout2.findViewById(2131100478));
        ((TextView)localLinearLayout2.findViewById(2131100476)).setText(localSectionSummary.getSection().getName());
        ViewHolder.access$14(localViewHolder).addView(localLinearLayout2);
        i++;
        break label407;
        label652: ViewHolder.access$9(localViewHolder).setVisibility(0);
        ViewHolder.access$8(localViewHolder).setVisibility(8);
        if ((ViewHolder.access$14(localViewHolder) != null) && (ViewHolder.access$14(localViewHolder).getChildCount() > 0))
          ViewHolder.access$14(localViewHolder).removeAllViews();
        ViewHolder.access$14(localViewHolder).setVisibility(8);
      }
    }
  }

  class ViewHolder
  {
    private TextView chapter_excercise_do_t;
    private TextView chapter_name_t;
    private View chapter_section_item_rl;
    private LinearLayout chapter_section_list_item_t;
    private ImageView know_xuxian_iv;
    private TextView knowledge_number_tv_t;
    private View level_c;
    private LinearLayout section_list_ll;
    private TextView tv_master;

    ViewHolder()
    {
    }
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.activity.ChapterSectionListActivity
 * JD-Core Version:    0.6.0
 */