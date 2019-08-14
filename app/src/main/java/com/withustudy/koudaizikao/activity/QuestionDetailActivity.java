package com.withustudy.koudaizikao.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.umeng.analytics.MobclickAgent;
import com.withustudy.koudaizikao.action.AbsSimpleImplUrlFactory;
import com.withustudy.koudaizikao.action.UrlFactory;
import com.withustudy.koudaizikao.base.AbsBaseActivity;
import com.withustudy.koudaizikao.commen.CommonAdapter;
import com.withustudy.koudaizikao.config.Constants.BrushType;
import com.withustudy.koudaizikao.config.KouDaiSP;
import com.withustudy.koudaizikao.custom.Counter;
import com.withustudy.koudaizikao.custom.FragmentpaperViewAdapater;
import com.withustudy.koudaizikao.custom.FragmentpaperViewAdapater.BeforeShowListenner;
import com.withustudy.koudaizikao.custom.SharePopWindow;
import com.withustudy.koudaizikao.db.DbService;
import com.withustudy.koudaizikao.entity.BrushExcerciseBean;
import com.withustudy.koudaizikao.entity.ErrorExercise;
import com.withustudy.koudaizikao.entity.Exercises;
import com.withustudy.koudaizikao.entity.Kpoint;
import com.withustudy.koudaizikao.entity.KpointDetail;
import com.withustudy.koudaizikao.entity.ReqOldExam;
import com.withustudy.koudaizikao.entity.RspCollectExcerBean;
import com.withustudy.koudaizikao.entity.Stem;
import com.withustudy.koudaizikao.entity.req.BeginSection;
import com.withustudy.koudaizikao.entity.req.ExamDate;
import com.withustudy.koudaizikao.entity.req.ExerciseIdList;
import com.withustudy.koudaizikao.entity.req.FavoriteExercise;
import com.withustudy.koudaizikao.entity.req.IntellBrush;
import com.withustudy.koudaizikao.entity.req.KpointExcercise;
import com.withustudy.koudaizikao.entity.req.ReqBrushChapterGoNext;
import com.withustudy.koudaizikao.entity.req.ReqBrushLast;
import com.withustudy.koudaizikao.entity.req.ReqChapterBrush;
import com.withustudy.koudaizikao.entity.req.ReqDeleFavor;
import com.withustudy.koudaizikao.entity.req.ReqFavoriteExercise;
import com.withustudy.koudaizikao.entity.req.ReqSimuExcerciseId;
import com.withustudy.koudaizikao.entity.req.UserSubject;
import com.withustudy.koudaizikao.entity.req.push.UserAnswers;
import com.withustudy.koudaizikao.fragment.QuestionDetailFragment;
import com.withustudy.koudaizikao.tools.LogUtils;
import com.withustudy.koudaizikao.tools.MyLog;
import com.withustudy.koudaizikao.tools.ProTools;
import com.withustudy.koudaizikao.tools.ToolsUtils;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.query.QueryBuilder;
import de.greenrobot.dao.query.WhereCondition;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import koudai.db.BrushExcerciseBatchDao;
import koudai.db.UserAns;
import koudai.db.UserAnsDao;
import koudai.db.UserAnsDao.Properties;

public class QuestionDetailActivity extends AbsBaseActivity
  implements OnClickListener, BeforeShowListenner
{
  private static final int action_brush_excer_req_id = 0;
  private static final int action_brush_excer_req_id_nodata = 10;
  private static final int action_collect_delete_id = 8;
  private static final int action_collect_delete_id_failed = 9;
  private static final int action_collect_excercise = 5;
  private static final int action_deleay_dismiss_pop_id = 2;
  private static final int action_deleay_loading_id = 1;
  private static final int action_show_test_gridview = 3;
  private static final int action_simu_error_explain = 6;
  private static final int action_simu_excerciseId = 4;
  private static final int action_simu_no_data = 7;
  public int FromPage = 0;
  private Button anser_paper_btn;
  private int batchIndex = 0;
  private BrushExcerciseBatchDao brushExcerciseBatchsDao;
  private BrushExcerciseBean brushExcerciseBean;
  private Bundle bundle;
  private List<Exercises> cacheExcerciseList = new ArrayList();
  private List<String> cacheId;
  public HashMap<Integer, HashMap<Integer, String>> cacheOpEdit = new HashMap();
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
  private Counter counter;
  private TextView counterView;
  public HashMap<Integer, String> currRight = new HashMap();
  public int currentIndex;
  private int currentPage = 0;
  private DbService dbService;
  private ImageButton dismiss_test_card_ib;
  private long endTime;
  ArrayList<ErrorExercise> errExsList;
  private List<Integer> errorList;
  public String excerciseBrushId;
  private ExerciseIdList exerciseIdList;
  private String fen = "120";
  private int flag = 0;
  private Button go_first_brush_btn_pop;
  private Button go_last_brush_btn;
  private Button go_last_brush_btn_pop;
  private Button go_on_test_btn;
  private MyGridAdapter gridAdapter;
  private LinearLayout hand_test_paper_ll;
  private ImageView hand_test_paper_ll_iv;
  private LinearLayout hand_test_paper_ll_pop;
  private Handler handler = new Handler()
  {
    public void handleMessage(Message paramMessage)
    {
      switch (paramMessage.what)
      {
      case 2:
      case 4:
      case 11:
      case 12:
      case 13:
      case 14:
      case 15:
      case 16:
      case 17:
      case 18:
      case 19:
      case 20:
      default:
      case 7:
      case 0:
      case 10:
      case 1:
      case 3:
      case 5:
        RspCollectExcerBean localRspCollectExcerBean;
        do
        {
          do
          {
            return;
            Toast.makeText(QuestionDetailActivity.this.getApplicationContext(), "小袋努力编题中。。。", 0).show();
            return;
            List localList = QuestionDetailActivity.this.brushExcerciseBean.getExercises();
            switch (QuestionDetailActivity.this.FromPage)
            {
            case 1:
            case 3:
            default:
              if ((localList == null) || (localList.size() <= 0))
                break;
              switch (QuestionDetailActivity.this.FromPage)
              {
              default:
                QuestionDetailActivity.this.cacheExcerciseList.addAll(localList);
              case 10:
              case 888:
              case 9:
              case 12:
              }
            case 17:
            case 888:
            case 9:
            case 12:
              for (int i = 0; ; i++)
              {
                if (i >= localList.size())
                {
                  if (QuestionDetailActivity.this.isLast)
                  {
                    QuestionDetailFragment localQuestionDetailFragment2 = new QuestionDetailFragment();
                    Bundle localBundle2 = new Bundle();
                    localBundle2.putSerializable("exercises", null);
                    localQuestionDetailFragment2.setArguments(localBundle2);
                    QuestionDetailActivity.this.mContentFragments.add(localQuestionDetailFragment2);
                    QuestionDetailActivity.this.cacheExcerciseList.add(null);
                  }
                  MyLog.log("mContentFragments.size=", Integer.valueOf(QuestionDetailActivity.this.mContentFragments.size()));
                  if (QuestionDetailActivity.this.mAdapter != null)
                    break label761;
                  QuestionDetailActivity.this.mAdapter = new FragmentpaperViewAdapater(QuestionDetailActivity.this.getSupportFragmentManager())
                  {
                    public int getCount()
                    {
                      return QuestionDetailActivity.this.mContentFragments.size();
                    }

                    public Fragment getItem(int paramInt)
                    {
                      return (Fragment)QuestionDetailActivity.this.mContentFragments.get(paramInt);
                    }
                  };
                  QuestionDetailActivity.this.mAdapter.setBeforeShowListenner(QuestionDetailActivity.this);
                  QuestionDetailActivity.this.mViewPager.setAdapter(QuestionDetailActivity.this.mAdapter);
                  ((QuestionDetailFragment)QuestionDetailActivity.this.mContentFragments.get(0)).refreshData((Exercises)QuestionDetailActivity.this.cacheExcerciseList.get(0), QuestionDetailActivity.this.currentIndex);
                  return;
                  QuestionDetailActivity.this.handler.sendEmptyMessageDelayed(1, 100L);
                  QuestionDetailActivity.this.isLast = true;
                  break;
                  Boolean localBoolean2 = Boolean.valueOf(QuestionDetailActivity.this.brushExcerciseBean.isEnd());
                  if ((localBoolean2 == null) || (!localBoolean2.booleanValue()))
                    break;
                  QuestionDetailActivity.this.isLast = true;
                  break;
                  Boolean localBoolean1 = Boolean.valueOf(QuestionDetailActivity.this.brushExcerciseBean.isEnd());
                  if ((localBoolean1 == null) || (!localBoolean1.booleanValue()))
                    break;
                  QuestionDetailActivity.this.isLast = true;
                  break;
                  if (QuestionDetailActivity.this.cacheExcerciseList.size() + localList.size() != QuestionDetailActivity.this.errExsList.size())
                    break label268;
                  QuestionDetailActivity.this.isLast = true;
                  break label268;
                  QuestionDetailActivity.this.totalNum = ((Exercises)localList.get(0)).getTotalNum();
                  break label268;
                  QuestionDetailActivity.this.totalNum = ((Exercises)localList.get(0)).getTotalNum();
                  break label268;
                }
                Exercises localExercises = (Exercises)localList.get(i);
                QuestionDetailFragment localQuestionDetailFragment1 = new QuestionDetailFragment();
                Bundle localBundle1 = new Bundle();
                localBundle1.putSerializable("exercises", localExercises);
                localQuestionDetailFragment1.setArguments(localBundle1);
                QuestionDetailActivity.this.mContentFragments.add(localQuestionDetailFragment1);
              }
              QuestionDetailActivity.this.mAdapter.notifyDataSetChanged();
              return;
            }
            LogUtils.myLog("解析异常或者题目为0");
          }
          while (QuestionDetailActivity.this.FromPage != 1);
          Toast.makeText(QuestionDetailActivity.this.getApplicationContext(), "小袋努力编题中。。。", 0).show();
          return;
          Toast.makeText(QuestionDetailActivity.this.getApplicationContext(), "小袋努力编题中。。。", 0).show();
          return;
          QuestionDetailActivity.this.mProTools.dismissDislog();
          return;
          QuestionDetailActivity.this.gridAdapter = new MyGridAdapter(QuestionDetailActivity.this, QuestionDetailActivity.this.mCardList);
          QuestionDetailActivity.this.simu_card_gv.setAdapter(QuestionDetailActivity.this.gridAdapter);
          return;
          localRspCollectExcerBean = (RspCollectExcerBean)paramMessage.obj;
        }
        while (localRspCollectExcerBean == null);
        if ("OK".equals(localRspCollectExcerBean.getStatus()))
        {
          Toast.makeText(QuestionDetailActivity.this.getApplicationContext(), "收藏成功!", 0).show();
          QuestionDetailActivity.this.isCollect.put(Integer.valueOf(QuestionDetailActivity.this.currentIndex), Boolean.valueOf(true));
          String str2 = ((Exercises)QuestionDetailActivity.this.cacheExcerciseList.get(QuestionDetailActivity.this.currentIndex)).getExerciseId();
          QuestionDetailActivity.this.sendPushCollect(str2, "0");
          QuestionDetailActivity.this.iv_collect_excer.setImageResource(2130837586);
          return;
        }
        Toast.makeText(QuestionDetailActivity.this.getApplicationContext(), "收藏失败!", 0).show();
        return;
      case 8:
        Toast.makeText(QuestionDetailActivity.this.getApplicationContext(), "取消成功!", 0).show();
        QuestionDetailActivity.this.isCollect.put(Integer.valueOf(QuestionDetailActivity.this.currentIndex), Boolean.valueOf(false));
        String str1 = ((Exercises)QuestionDetailActivity.this.cacheExcerciseList.get(QuestionDetailActivity.this.currentIndex)).getExerciseId();
        QuestionDetailActivity.this.sendPushCollect(str1, "1");
        QuestionDetailActivity.this.iv_collect_excer.setImageResource(2130837597);
        return;
      case 9:
        Toast.makeText(QuestionDetailActivity.this.getApplicationContext(), "取消失败!", 0).show();
        return;
      case 6:
        label268: label761: QuestionDetailActivity.this.mAdapter = new FragmentpaperViewAdapater(QuestionDetailActivity.this.getSupportFragmentManager())
        {
          public int getCount()
          {
            return QuestionDetailActivity.this.mContentFragments.size();
          }

          public Fragment getItem(int paramInt)
          {
            return (Fragment)QuestionDetailActivity.this.mContentFragments.get(paramInt);
          }
        };
        QuestionDetailActivity.this.mAdapter.setBeforeShowListenner(QuestionDetailActivity.this);
        QuestionDetailActivity.this.mViewPager.setAdapter(QuestionDetailActivity.this.mAdapter);
        QuestionDetailActivity.this.currentIndex = 0;
        ((QuestionDetailFragment)QuestionDetailActivity.this.mContentFragments.get(0)).refreshData((Exercises)QuestionDetailActivity.this.cacheExcerciseList.get(0), QuestionDetailActivity.this.currentIndex);
        QuestionDetailActivity.this.isSimu = false;
        QuestionDetailActivity.this.isTotalOpen = true;
        QuestionDetailActivity.this.refreshRCL();
        return;
      case 21:
      }
      QuestionDetailActivity.this.dismissPop1();
    }
  };
  private boolean haveDone;
  public HashMap<Integer, Boolean> havePush;
  private boolean isAnswerRight = false;
  private HashMap<Integer, Boolean> isCollect = new HashMap();
  private boolean isGon = true;
  public HashMap<Integer, Boolean> isHaveDone = new HashMap();
  private boolean isInit = true;
  private boolean isLast;
  public boolean isNewcachePage = false;
  private boolean isOnce = false;
  public HashMap<Integer, Boolean> isOpenChild = new HashMap();
  private boolean isShare = false;
  private boolean isShowSimuDetail = false;
  public boolean isSimu = false;
  public boolean isTotalOpen = false;
  private ImageView iv_collect_excer;
  private String kpointId;
  private int lastIndex = 0;
  private String level;
  List<Exercises> listData;
  List<QuestionDetailFragment> listfm;
  private LinearLayout ll_gv;
  private LinearLayout ll_test_card_bottom;
  private FragmentpaperViewAdapater mAdapter;
  private List<QuestionDetailFragment> mAllFm;
  private CommonAdapter<Boolean> mCardAdapter;
  private List<Boolean> mCardList;
  private List<QuestionDetailFragment> mContentFragments = new ArrayList();
  private List<Exercises> mFmExers;
  private ViewPager mViewPager;
  private String miao = "00";
  private TextView pre_yongshi_tv;
  private LinearLayout preview_have_done_ll;
  private ImageView preview_have_done_ll_iv;
  private LinearLayout preview_have_done_ll_pop;
  private LinearLayout question_detail_back_ll;
  private LinearLayout question_ll_collect;
  private LinearLayout question_ll_explain;
  private LinearLayout question_ll_share;
  private TextView qus_title;
  ExerciseIdList req;
  public HashMap<Integer, UserAnswers> resBrush = new HashMap();
  public HashMap<Integer, String> resRightHM = new HashMap();
  public HashMap<Integer, UserAnswers> resSimu;
  private int resultCode;
  private List<Integer> rightList;
  private String shi = "00";
  private int simuExCount = 0;
  private GridView simu_card_gv;
  private GridView simu_card_gv2;
  private ImageView simu_detail_iv;
  private RelativeLayout simu_detail_pop_diss_ll;
  private ImageView simu_detail_share;
  private LinearLayout simula_detail_back_ll;
  private LinearLayout simula_detail_back_pop_ll;
  private LinearLayout simulation_test_excersice_detail_title_ll_t;
  private long startTime;
  private double statisticsSocre;
  public String subjectId;
  private String subjectName;
  private PopupWindow test_card_pop;
  private PopupWindow test_pop;
  private String time;
  private TextView time_counter;
  private RelativeLayout title1;
  private RelativeLayout title2;
  private int totalNum;
  private TextView tv2_name;
  private TextView tv3_name;
  private TextView tvDone;
  private TextView tv_have_done;
  private TextView tv_no_done;
  private TextView tvnodone;
  private String uid = "";
  public UserSubject userSubject;
  private TextView yongshi_tv;

  private void dimissPop()
  {
    if (this.test_pop != null)
    {
      this.test_pop.dismiss();
      this.test_pop = null;
    }
    backgroundAlpha(1.0F);
  }

  private void dimissTestPop()
  {
    if (this.test_card_pop != null)
    {
      this.test_card_pop.dismiss();
      this.test_card_pop = null;
    }
  }

  private void dismissPop1()
  {
    if (this.chapterSectionListPop != null)
    {
      this.chapterSectionListPop.dismiss();
      this.chapterSectionListPop = null;
    }
    backgroundAlpha(1.0F);
  }

  private void goOnCountTime()
  {
    if (this.isGon)
    {
      int i = Integer.parseInt(this.shi);
      int j = Integer.parseInt(this.fen);
      int k = Integer.parseInt(this.miao);
      this.counter = new Counter(this.counterView, 1000 * (k + 60 * (j + i * 60)), 1000);
      this.counter.start();
    }
  }

  private void handPaper()
  {
    double d2;
    if ((this.resultCode != 100) && (this.resultCode != 101))
    {
      this.statisticsSocre = statisticsSocre();
      d2 = Math.ceil(1.0D * (this.endTime - this.startTime) / 60000.0D);
      switch (this.FromPage)
      {
      default:
        sendPushComamd(Constants.BrushType.MOCK_EXAM, d2);
      case 16:
      }
    }
    while (true)
    {
      dimissPop();
      Bundle localBundle = new Bundle();
      localBundle.putString("subjectId", this.subjectId);
      localBundle.putString("subjectName", this.subjectName);
      double d1 = 1.0D * (this.endTime - this.startTime) / 1000.0D;
      localBundle.putDouble("statisticsSocre", this.statisticsSocre);
      localBundle.putInt("FromPage", 3);
      localBundle.putString("level", this.level);
      localBundle.putDouble("time", d1);
      localBundle.putSerializable("currRight", this.currRight);
      localBundle.putSerializable("cacheOpEdit", this.cacheOpEdit);
      localBundle.putInt("count", this.exerciseIdList.getExerciseId().size());
      Intent localIntent = new Intent(getApplicationContext(), ActivitySimuResult.class);
      localBundle.putInt("FromPage", this.FromPage);
      localBundle.putSerializable("exerciseIdList", this.exerciseIdList);
      localBundle.putSerializable("currRight", this.currRight);
      localBundle.putSerializable("brushExcerciseBean", this.brushExcerciseBean);
      localBundle.putSerializable("isHaveDone", this.isHaveDone);
      localIntent.putExtras(localBundle);
      startActivityForResult(localIntent, 100);
      return;
      sendPushComamd(Constants.BrushType.REAL_EXAM, d2);
    }
  }

  private String opStr(List<String> paramList)
  {
    String str1 = "";
    Iterator localIterator;
    if ((paramList != null) && (paramList.size() > 0))
      localIterator = paramList.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return str1;
      String str2 = (String)localIterator.next();
      str1 = str1 + str2 + "%%%%";
    }
  }

  private String opToStr(HashMap<Integer, String> paramHashMap)
  {
    Set localSet = paramHashMap.keySet();
    String str1 = "";
    Iterator localIterator = localSet.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return str1;
      String str2 = (String)paramHashMap.get((Integer)localIterator.next());
      if ((str2 == null) || (str2.equals("")))
        continue;
      str1 = str1 + str2 + "%%%%";
    }
  }

  private void refreshRCL()
  {
    if ((this.mContentFragments != null) && (this.mContentFragments.size() > 0))
    {
      ((QuestionDetailFragment)this.mContentFragments.get(this.currentIndex)).refresh((Exercises)this.cacheExcerciseList.get(this.currentIndex), this.currentIndex);
      int i = -1 + this.currentIndex;
      int j = 1 + this.currentIndex;
      if ((i >= 0) && (this.mContentFragments.size() > i))
        ((QuestionDetailFragment)this.mContentFragments.get(i)).refresh((Exercises)this.cacheExcerciseList.get(i), i);
      if ((j >= 0) && (j < this.mContentFragments.size()))
        ((QuestionDetailFragment)this.mContentFragments.get(j)).refresh((Exercises)this.cacheExcerciseList.get(j), j);
    }
  }

  private void sendPushCollect(String paramString1, String paramString2)
  {
    Intent localIntent = new Intent();
    localIntent.setAction("com.koudai.collect");
    Bundle localBundle = new Bundle();
    localBundle.putSerializable("excerciseId", paramString1);
    localBundle.putString("type", paramString2);
    localIntent.putExtras(localBundle);
    sendBroadcast(localIntent);
  }

  private void sendPushComamd(String paramString, double paramDouble)
  {
    Intent localIntent = new Intent();
    localIntent.setAction("com.koudai.pushAnsers");
    Bundle localBundle = new Bundle();
    localBundle.putSerializable("excerciseBrushId", this.excerciseBrushId);
    localBundle.putString("type", paramString);
    localBundle.putString("subjectId", this.subjectId);
    localBundle.putLong("mockTime", ()paramDouble);
    localIntent.putExtras(localBundle);
    sendBroadcast(localIntent);
  }

  private void showPop()
  {
    if (this.test_pop != null)
    {
      this.test_pop.dismiss();
      this.test_pop = null;
    }
    View localView = getLayoutInflater().inflate(2130903174, null);
    this.go_on_test_btn = ((Button)localView.findViewById(2131100488));
    this.simu_detail_pop_diss_ll = ((RelativeLayout)localView.findViewById(2131100484));
    this.anser_paper_btn = ((Button)localView.findViewById(2131100489));
    this.go_last_brush_btn = ((Button)localView.findViewById(2131100009));
    this.yongshi_tv = ((TextView)localView.findViewById(2131100487));
    this.tv_no_done = ((TextView)localView.findViewById(2131100486));
    this.tv_have_done = ((TextView)localView.findViewById(2131100485));
    this.go_on_test_btn.setOnClickListener(this);
    this.anser_paper_btn.setOnClickListener(this);
    this.go_last_brush_btn.setOnClickListener(this);
    this.simu_detail_pop_diss_ll.setOnClickListener(this);
    Set localSet = this.isHaveDone.keySet();
    int i = 0;
    Iterator localIterator = localSet.iterator();
    double d1;
    double d2;
    if (!localIterator.hasNext())
    {
      int j = this.exerciseIdList.getExerciseId().size();
      this.tv_have_done.setText(i);
      this.tv_no_done.setText(j - i);
      int k = Integer.parseInt(this.shi);
      int m = Integer.parseInt(this.fen);
      int n = 7200 - (Integer.parseInt(this.miao) + 60 * (m + k * 60));
      d1 = Math.floor(1.0D * n / 60.0D);
      d2 = 60.0D * (1.0D * n / 60.0D - d1);
      if (d2.length() > 3)
        break label543;
    }
    label543: for (String str = d2; ; str = d2.substring(0, 1))
    {
      this.yongshi_tv.setText(d1 + "分" + str + "秒");
      localView.setFocusable(true);
      localView.setFocusableInTouchMode(true);
      PopupWindow localPopupWindow = new PopupWindow(localView, this.mSP.getWidth(), ToolsUtils.dip2px(this, 600.0F));
      this.test_pop = localPopupWindow;
      this.test_pop.setAnimationStyle(2131361794);
      this.test_pop.setFocusable(true);
      3 local3 = new OnKeyListener()
      {
        public boolean onKey(View paramView, int paramInt, KeyEvent paramKeyEvent)
        {
          if (paramInt == 4)
          {
            QuestionDetailActivity.this.test_pop.dismiss();
            QuestionDetailActivity.this.test_pop = null;
            QuestionDetailActivity.this.backgroundAlpha(1.0F);
            return true;
          }
          return false;
        }
      };
      localView.setOnKeyListener(local3);
      this.test_pop.showAtLocation(this.mViewPager, 80, 0, 0);
      backgroundAlpha(0.5F);
      return;
      Integer localInteger = (Integer)localIterator.next();
      Boolean localBoolean = (Boolean)this.isHaveDone.get(localInteger);
      if ((localBoolean == null) || (!localBoolean.booleanValue()))
        break;
      i++;
      break;
    }
  }

  private void showPop1()
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
    this.chapterSectionListPop = new PopupWindow(localView, this.mSP.getWidth(), ToolsUtils.dip2px(this, 650.0F));
    this.chapterSectionListPop.setAnimationStyle(2131361794);
    this.chapterSectionListPop.setFocusable(true);
    localView.setOnKeyListener(new OnKeyListener()
    {
      public boolean onKey(View paramView, int paramInt, KeyEvent paramKeyEvent)
      {
        if (paramInt == 4)
        {
          QuestionDetailActivity.this.chapterSectionListPop.dismiss();
          QuestionDetailActivity.this.chapterSectionListPop = null;
          QuestionDetailActivity.this.backgroundAlpha(1.0F);
          return true;
        }
        return false;
      }
    });
    this.chapterSectionListPop.showAtLocation(this.mViewPager, 80, 0, 0);
    backgroundAlpha(0.6F);
  }

  private void showTestCard()
  {
    if (this.test_card_pop != null)
    {
      this.test_card_pop.dismiss();
      this.test_card_pop = null;
    }
    View localView = getLayoutInflater().inflate(2130903173, null);
    this.dismiss_test_card_ib = ((ImageButton)localView.findViewById(2131099916));
    this.tv2_name = ((TextView)localView.findViewById(2131100481));
    this.tv3_name = ((TextView)localView.findViewById(2131100482));
    this.ll_test_card_bottom = ((LinearLayout)localView.findViewById(2131100483));
    this.simula_detail_back_pop_ll = ((LinearLayout)localView.findViewById(2131100480));
    this.hand_test_paper_ll_pop = ((LinearLayout)localView.findViewById(2131099898));
    this.preview_have_done_ll_pop = ((LinearLayout)localView.findViewById(2131099900));
    this.go_first_brush_btn_pop = ((Button)localView.findViewById(2131099921));
    this.go_last_brush_btn_pop = ((Button)localView.findViewById(2131099922));
    this.pre_yongshi_tv = ((TextView)localView.findViewById(2131099919));
    this.time_counter = ((TextView)localView.findViewById(2131099903));
    this.tvDone = ((TextView)localView.findViewById(2131099917));
    this.tvnodone = ((TextView)localView.findViewById(2131099918));
    if (this.miao.length() >= 2)
      this.miao = this.miao.substring(0, 2);
    this.time_counter.setText(this.shi + ":" + this.fen + ":" + this.miao);
    this.simu_card_gv = ((GridView)localView.findViewById(2131099920));
    GridView localGridView = this.simu_card_gv;
    4 local4 = new OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
      {
        LogUtils.myLog("答题卡中GridView点击=" + paramInt);
      }
    };
    localGridView.setOnItemClickListener(local4);
    int i = Integer.parseInt(this.shi);
    int j = Integer.parseInt(this.fen);
    int k = 7200 - (Integer.parseInt(this.miao) + 60 * (j + i * 60));
    double d1 = Math.floor(1.0D * k / 60.0D);
    double d2 = 60.0D * (1.0D * k / 60.0D - d1);
    if (this.isShowSimuDetail)
    {
      int n = 0;
      int i1 = 0;
      int i2 = this.exerciseIdList.getExerciseId().size();
      int i3 = 0;
      int i4;
      Iterator localIterator2;
      if (i3 >= i2)
      {
        Set localSet2 = this.isHaveDone.keySet();
        i4 = 0;
        localIterator2 = localSet2.iterator();
      }
      while (true)
      {
        if (!localIterator2.hasNext())
        {
          this.tvDone.setText(i4);
          this.tv2_name.setText("错误");
          this.tvnodone.setText(i1);
          this.tv3_name.setText("未答");
          this.pre_yongshi_tv.setText(n);
          this.go_first_brush_btn_pop.setText("从头浏览");
          this.go_last_brush_btn_pop.setText("完成并分享");
          this.handler.sendEmptyMessage(3);
          this.dismiss_test_card_ib.setOnClickListener(this);
          this.simula_detail_back_pop_ll.setOnClickListener(this);
          this.go_first_brush_btn_pop.setOnClickListener(this);
          this.go_last_brush_btn_pop.setOnClickListener(this);
          localView.setFocusable(true);
          localView.setFocusableInTouchMode(true);
          PopupWindow localPopupWindow = new PopupWindow(localView, this.mSP.getWidth(), this.simulation_test_excersice_detail_title_ll_t.getHeight());
          this.test_card_pop = localPopupWindow;
          this.test_card_pop.setAnimationStyle(2131361795);
          this.test_card_pop.setFocusable(true);
          5 local5 = new OnKeyListener()
          {
            public boolean onKey(View paramView, int paramInt, KeyEvent paramKeyEvent)
            {
              if (paramInt == 4)
              {
                QuestionDetailActivity.this.test_card_pop.dismiss();
                QuestionDetailActivity.this.test_card_pop = null;
                QuestionDetailActivity.this.handler.sendEmptyMessageDelayed(2, 100L);
                return true;
              }
              return false;
            }
          };
          localView.setOnKeyListener(local5);
          this.test_card_pop.showAtLocation(this.mViewPager, 80, 0, 0);
          return;
          String str2 = (String)this.currRight.get(Integer.valueOf(i3));
          if (str2 == null)
            n++;
          while (true)
          {
            i3++;
            break;
            if (str2.equals("false"))
            {
              i1++;
              continue;
            }
            str2.equals("true");
          }
        }
        Integer localInteger2 = (Integer)localIterator2.next();
        Boolean localBoolean2 = (Boolean)this.isHaveDone.get(localInteger2);
        if ((localBoolean2 == null) || (!localBoolean2.booleanValue()))
          continue;
        i4++;
      }
    }
    this.tv2_name.setText("未答");
    this.tv3_name.setText("耗时");
    this.go_first_brush_btn_pop.setText("继续答题");
    this.go_last_brush_btn_pop.setText("果断交卷");
    String str1;
    label884: int m;
    Iterator localIterator1;
    if (d2.length() <= 3)
    {
      str1 = d2;
      this.pre_yongshi_tv.setText(d1 + "分" + str1 + "秒");
      LogUtils.myLog("simuCount=" + this.simuExCount);
      Set localSet1 = this.isHaveDone.keySet();
      m = 0;
      localIterator1 = localSet1.iterator();
    }
    while (true)
    {
      if (!localIterator1.hasNext())
      {
        this.tvDone.setText(m);
        this.tvnodone.setText(this.exerciseIdList.getExerciseId().size() - m);
        break;
        str1 = d2.substring(0, 1);
        break label884;
      }
      Integer localInteger1 = (Integer)localIterator1.next();
      Boolean localBoolean1 = (Boolean)this.isHaveDone.get(localInteger1);
      if ((localBoolean1 == null) || (!localBoolean1.booleanValue()))
        continue;
      m++;
    }
  }

  private double statisticsSocre()
  {
    double d = 0.0D;
    this.dbService = DbService.getInstance(this.mContext);
    List localList = this.dbService.getUserAnsDao().queryBuilder().where(UserAnsDao.Properties.ExcerciseBrushId.eq(this.excerciseBrushId), new WhereCondition[0]).list();
    Iterator localIterator = localList.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
      {
        LogUtils.myLog("statisticsSocre 统计分数题目数::" + localList.size());
        return d;
      }
      d += ((UserAns)localIterator.next()).getUserScore().doubleValue();
    }
  }

  public void CheckState(boolean paramBoolean)
  {
    this.isAnswerRight = paramBoolean;
  }

  public void backgroundAlpha(float paramFloat)
  {
    WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
    localLayoutParams.alpha = paramFloat;
    getWindow().setAttributes(localLayoutParams);
  }

  public void beforeShowListenner(int paramInt)
  {
    if ((paramInt < 0) || (paramInt > this.mContentFragments.size()))
      return;
    ((QuestionDetailFragment)this.mContentFragments.get(paramInt)).refresh((Exercises)this.cacheExcerciseList.get(paramInt), paramInt);
  }

  protected void bindData()
  {
  }

  public void goNext()
  {
    if (1 + this.currentIndex <= -1 + this.cacheExcerciseList.size())
    {
      this.mViewPager.setCurrentItem(1 + this.currentIndex, true);
      return;
    }
    Toast.makeText(getApplicationContext(), "已经是最后一题", 0).show();
  }

  public void haveDone(boolean paramBoolean)
  {
    this.haveDone = paramBoolean;
    this.isOpenChild.put(Integer.valueOf(this.currentIndex), Boolean.valueOf(true));
  }

  protected void initData()
  {
    this.mProTools.startDialog(true);
    this.excerciseBrushId = (this.subjectId + System.currentTimeMillis());
    switch (this.FromPage)
    {
    default:
    case 15:
    case 17:
    case 10:
      do
      {
        return;
        this.isTotalOpen = true;
        this.req = new ExerciseIdList();
        this.req.setVersionName(this.mSP.getVersionName());
        this.req.setClientType(ToolsUtils.getSDK());
        this.req.setImei(ToolsUtils.getImei(this.mContext));
        this.req.setNet(ToolsUtils.getStringNetworkType(this.mContext));
        this.cacheId = new ArrayList();
        FavoriteExercise localFavoriteExercise = (FavoriteExercise)this.bundle.getSerializable("favoriteExercise");
        this.cacheId.add(localFavoriteExercise.getExerciseId());
        this.req.setExerciseId(this.cacheId);
        this.req.setUserSubject(this.userSubject);
        this.req.setBrushType(Constants.BrushType.FAVORITE_BRUSH);
        UrlFactory.getInstance().getExcerciseDetail().constructUrl(this, this.req, 0);
        this.iv_collect_excer.setImageResource(2130837586);
        this.isCollect.put(Integer.valueOf(this.currentIndex), Boolean.valueOf(true));
        return;
        if (this.isTotalOpen);
        for (boolean bool = false; ; bool = true)
        {
          this.isTotalOpen = bool;
          this.handler.sendEmptyMessage(0);
          return;
        }
        this.startTime = System.currentTimeMillis();
        this.isTotalOpen = true;
        this.errExsList = ((ArrayList)this.bundle.getSerializable("errorExercises"));
      }
      while ((this.errExsList == null) || (this.errExsList.size() <= 0));
      int i;
      if (this.req == null)
      {
        this.req = new ExerciseIdList();
        this.req.setVersionName(this.mSP.getVersionName());
        this.req.setClientType(ToolsUtils.getSDK());
        this.req.setImei(ToolsUtils.getImei(this.mContext));
        this.req.setNet(ToolsUtils.getStringNetworkType(this.mContext));
        this.req.setExerciseId(new ArrayList());
        if (this.cacheId != null)
          break label601;
        this.cacheId = new ArrayList();
        i = this.errExsList.size();
        LogUtils.myLog("总共size=" + i);
      }
      for (int j = 0; ; j++)
      {
        if (j >= 8);
        do
        {
          this.batchIndex = (8 + this.batchIndex);
          this.req.setExerciseId(this.cacheId);
          this.req.setUserSubject(this.userSubject);
          this.req.setBrushType(Constants.BrushType.ERROR_BASKET_BRUSH);
          UrlFactory.getInstance().getExcerciseDetail().constructUrl(this, this.req, 0);
          return;
          this.req.getExerciseId().clear();
          break;
          this.cacheId.clear();
          break label487;
        }
        while (j + this.batchIndex > i - 1);
        String str4 = ((ErrorExercise)this.errExsList.get(j)).getExerciseId();
        this.cacheId.add(str4);
      }
    case 1:
      this.title1.setVisibility(0);
      KpointExcercise localKpointExcercise = new KpointExcercise();
      localKpointExcercise.setVersionName(this.mSP.getVersionName());
      localKpointExcercise.setClientType(ToolsUtils.getSDK());
      localKpointExcercise.setImei(ToolsUtils.getImei(this.mContext));
      localKpointExcercise.setNet(ToolsUtils.getStringNetworkType(this.mContext));
      localKpointExcercise.setUserSubject(this.userSubject);
      localKpointExcercise.setKpointId(this.kpointId);
      UrlFactory.getInstance().getKpointExercise().constructUrl(this, localKpointExcercise, 0);
      return;
    case 888:
      this.title1.setVisibility(0);
      IntellBrush localIntellBrush = new IntellBrush();
      localIntellBrush.setVersionName(this.mSP.getVersionName());
      localIntellBrush.setClientType(ToolsUtils.getSDK());
      localIntellBrush.setImei(ToolsUtils.getImei(this.mContext));
      localIntellBrush.setNet(ToolsUtils.getStringNetworkType(this.mContext));
      localIntellBrush.setUserSubject(this.userSubject);
      UrlFactory.getInstance().postIntellBrush().constructUrl(this, localIntellBrush, 0);
      return;
    case 12:
      this.title1.setVisibility(0);
      ReqBrushLast localReqBrushLast = new ReqBrushLast();
      localReqBrushLast.setVersionName(this.mSP.getVersionName());
      localReqBrushLast.setClientType(ToolsUtils.getSDK());
      localReqBrushLast.setImei(ToolsUtils.getImei(this.mContext));
      localReqBrushLast.setNet(ToolsUtils.getStringNetworkType(this.mContext));
      localReqBrushLast.setContinueLastFlag(true);
      localReqBrushLast.setUserSubject(this.userSubject);
      this.mProTools.startDialog(true);
      UrlFactory.getInstance().getBrushExcercise().constructUrl(this, localReqBrushLast, 0);
      return;
    case 9:
      this.title1.setVisibility(0);
      String str1 = this.bundle.getString("section_id");
      String str2 = this.bundle.getString("section_name");
      String str3 = this.bundle.getString("section_sn");
      ReqChapterBrush localReqChapterBrush = new ReqChapterBrush();
      localReqChapterBrush.setVersionName(this.mSP.getVersionName());
      localReqChapterBrush.setClientType(ToolsUtils.getSDK());
      localReqChapterBrush.setImei(ToolsUtils.getImei(this.mContext));
      localReqChapterBrush.setNet(ToolsUtils.getStringNetworkType(this.mContext));
      BeginSection localBeginSection = new BeginSection();
      localBeginSection.setId(str1);
      localBeginSection.setName(str2);
      localBeginSection.setSn(str3);
      localReqChapterBrush.setBeginSection(localBeginSection);
      localReqChapterBrush.setUserSubject(this.userSubject);
      UrlFactory.getInstance().getBrushExcercise().constructUrl(this, localReqChapterBrush, 0);
      return;
    case 3:
      label487: this.startTime = System.currentTimeMillis();
      label601: this.title2.setVisibility(0);
      ReqSimuExcerciseId localReqSimuExcerciseId = new ReqSimuExcerciseId();
      localReqSimuExcerciseId.setVersionName(this.mSP.getVersionName());
      localReqSimuExcerciseId.setClientType(ToolsUtils.getSDK());
      localReqSimuExcerciseId.setImei(ToolsUtils.getImei(this.mContext));
      localReqSimuExcerciseId.setNet(ToolsUtils.getStringNetworkType(this.mContext));
      localReqSimuExcerciseId.setLevel(this.level);
      UserSubject localUserSubject2 = new UserSubject();
      localUserSubject2.setUid(this.mSP.getUserId());
      localUserSubject2.setSubjectId(this.subjectId);
      localReqSimuExcerciseId.setUserSubject(localUserSubject2);
      UrlFactory.getInstance().getStartSimuExam().constructUrl(this, localReqSimuExcerciseId, 4);
      return;
    case 16:
    }
    this.startTime = System.currentTimeMillis();
    this.title2.setVisibility(0);
    ReqOldExam localReqOldExam = new ReqOldExam();
    UserSubject localUserSubject1 = new UserSubject();
    localUserSubject1.setUid(this.mSP.getUserId());
    localUserSubject1.setSubjectId(this.subjectId);
    localReqOldExam.setUserSubject(localUserSubject1);
    try
    {
      localReqOldExam.setExamDate((ExamDate)this.bundle.getSerializable("examDate"));
      label1294: UrlFactory.getInstance().getOldExamList().constructUrl(this, localReqOldExam, 4);
      return;
    }
    catch (Exception localException)
    {
      break label1294;
    }
  }

  protected void initListener()
  {
    switch (this.FromPage)
    {
    default:
    case 1:
    case 9:
    case 10:
    case 12:
    case 15:
    case 17:
    case 888:
    case 3:
    case 16:
    }
    while (true)
    {
      this.mViewPager.setOnPageChangeListener(new OnPageChangeListener()
      {
        public void onPageScrollStateChanged(int paramInt)
        {
          LogUtils.myLog("onPageScrollStateChanged state=" + paramInt);
          LogUtils.myLog(Integer.valueOf(QuestionDetailActivity.this.currentIndex));
          if (paramInt == 2)
            ((QuestionDetailFragment)QuestionDetailActivity.this.mContentFragments.get(QuestionDetailActivity.this.currentIndex)).onSave(QuestionDetailActivity.this.currentIndex);
          if ((QuestionDetailActivity.this.FromPage != 9) && (QuestionDetailActivity.this.FromPage != 12));
          for (int i = 0; ; i = 1)
          {
            if (((QuestionDetailActivity.this.FromPage == 888) || (i != 0)) && (paramInt == 1) && (QuestionDetailActivity.this.currentIndex == -1 + QuestionDetailActivity.this.mContentFragments.size()))
            {
              LogUtils.myLog("onSave  ");
              ((QuestionDetailFragment)QuestionDetailActivity.this.mContentFragments.get(QuestionDetailActivity.this.currentIndex)).onSave(QuestionDetailActivity.this.currentIndex);
            }
            (-1 + QuestionDetailActivity.this.mContentFragments.size());
            if (paramInt == 1)
              QuestionDetailActivity.this.mContentFragments.size();
            if ((paramInt == 1) && (QuestionDetailActivity.this.isLast) && (QuestionDetailActivity.this.FromPage == 888))
              QuestionDetailActivity.this.mContentFragments.size();
            if ((paramInt == 1) && (QuestionDetailActivity.this.isLast) && (i != 0))
              QuestionDetailActivity.this.mContentFragments.size();
            return;
          }
        }

        public void onPageScrolled(int paramInt1, float paramFloat, int paramInt2)
        {
        }

        public void onPageSelected(int paramInt)
        {
          QuestionDetailActivity.this.refreshData(paramInt);
          if ((QuestionDetailActivity.this.FromPage != 9) && (QuestionDetailActivity.this.FromPage != 12));
          for (int i = 0; ; i = 1)
          {
            if ((QuestionDetailActivity.this.isLast) && (QuestionDetailActivity.this.FromPage == 888) && (paramInt == -1 + QuestionDetailActivity.this.mContentFragments.size()))
            {
              Bundle localBundle3 = new Bundle();
              localBundle3.putString("subjectId", QuestionDetailActivity.this.subjectId);
              localBundle3.putString("subjectName", QuestionDetailActivity.this.subjectName);
              localBundle3.putInt("FromPage", 888);
              Intent localIntent3 = new Intent(QuestionDetailActivity.this.getApplicationContext(), IntellWecomeActivity.class);
              localBundle3.putSerializable("currRight", QuestionDetailActivity.this.currRight);
              localIntent3.putExtras(localBundle3);
              QuestionDetailActivity.this.startActivity(localIntent3);
              QuestionDetailActivity.this.finish(0, 0);
            }
            if ((QuestionDetailActivity.this.isLast) && (i != 0) && (paramInt == -1 + QuestionDetailActivity.this.mContentFragments.size()))
            {
              Bundle localBundle2 = new Bundle();
              localBundle2.putString("subjectId", QuestionDetailActivity.this.subjectId);
              localBundle2.putSerializable("currRight", QuestionDetailActivity.this.currRight);
              localBundle2.putString("subjectName", QuestionDetailActivity.this.subjectName);
              Intent localIntent2 = new Intent(QuestionDetailActivity.this.getApplicationContext(), ShowCardActivity.class);
              localIntent2.putExtras(localBundle2);
              QuestionDetailActivity.this.startActivity(localIntent2);
              QuestionDetailActivity.this.finish(0, 0);
            }
            if ((QuestionDetailActivity.this.isLast) && (QuestionDetailActivity.this.resultCode == 100) && (paramInt == -1 + QuestionDetailActivity.this.mContentFragments.size()))
              QuestionDetailActivity.this.handPaper();
            if ((QuestionDetailActivity.this.isLast) && (QuestionDetailActivity.this.resultCode == 101) && (paramInt == -1 + QuestionDetailActivity.this.listfm.size()))
              QuestionDetailActivity.this.handPaper();
            if ((QuestionDetailActivity.this.isLast) && (QuestionDetailActivity.this.FromPage == 10) && (paramInt == -1 + QuestionDetailActivity.this.mContentFragments.size()))
            {
              Intent localIntent1 = new Intent(QuestionDetailActivity.this.getApplicationContext(), ActivityErrorWecome.class);
              Bundle localBundle1 = new Bundle();
              localBundle1.putString("subjectId", QuestionDetailActivity.this.subjectId);
              localBundle1.putString("subjectName", QuestionDetailActivity.this.subjectName);
              localBundle1.putSerializable("errExsList", QuestionDetailActivity.this.errExsList);
              QuestionDetailActivity.this.endTime = System.currentTimeMillis();
              localBundle1.putLong("time", QuestionDetailActivity.this.endTime - QuestionDetailActivity.this.startTime);
              localIntent1.putExtras(localBundle1);
              QuestionDetailActivity.this.startActivity(localIntent1);
              QuestionDetailActivity.this.finish(0, 0);
            }
            return;
          }
        }
      });
      return;
      this.question_detail_back_ll.setOnClickListener(this);
      this.question_ll_explain.setOnClickListener(this);
      this.question_ll_collect.setOnClickListener(this);
      this.question_ll_share.setOnClickListener(this);
      continue;
      this.simula_detail_back_ll.setOnClickListener(this);
      this.preview_have_done_ll.setOnClickListener(this);
      this.hand_test_paper_ll.setOnClickListener(this);
      this.simula_detail_back_ll.setOnClickListener(this);
    }
  }

  protected void initView()
  {
    try
    {
      this.bundle = getIntent().getExtras();
      this.FromPage = this.bundle.getInt("FromPage");
      this.subjectId = this.bundle.getString("subjectId");
      this.uid = this.mSP.getUserId();
      this.userSubject = new UserSubject();
      this.userSubject.setUid(this.uid);
      this.userSubject.setSubjectId(this.subjectId);
      this.subjectName = this.bundle.getString("subjectName");
      this.kpointId = this.bundle.getString("kpointId");
      this.level = this.bundle.getString("level");
      label125: this.mViewPager = ((ViewPager)findViewById(2131099904));
      this.question_ll_collect = ((LinearLayout)findViewById(2131099894));
      this.iv_collect_excer = ((ImageView)findViewById(2131099895));
      switch (this.FromPage)
      {
      default:
        return;
      case 1:
      case 9:
      case 10:
      case 12:
      case 15:
      case 17:
      case 888:
        this.isSimu = false;
        this.title1 = ((RelativeLayout)findViewById(2131099759));
        this.question_detail_back_ll = ((LinearLayout)findViewById(2131099890));
        this.qus_title = ((TextView)findViewById(2131099891));
        this.qus_title.setText(this.subjectName);
        this.question_ll_explain = ((LinearLayout)findViewById(2131099892));
        this.question_ll_share = ((LinearLayout)findViewById(2131099760));
        this.question_ll_share = ((LinearLayout)findViewById(2131099760));
        this.title1.setVisibility(0);
        return;
      case 3:
      case 16:
      }
      this.isSimu = true;
      this.title2 = ((RelativeLayout)findViewById(2131099896));
      this.simula_detail_back_ll = ((LinearLayout)findViewById(2131099897));
      this.hand_test_paper_ll = ((LinearLayout)findViewById(2131099898));
      this.hand_test_paper_ll_iv = ((ImageView)findViewById(2131099899));
      this.preview_have_done_ll_iv = ((ImageView)findViewById(2131099901));
      this.simu_detail_share = ((ImageView)findViewById(2131099902));
      this.simulation_test_excersice_detail_title_ll_t = ((LinearLayout)findViewById(2131099889));
      this.preview_have_done_ll = ((LinearLayout)findViewById(2131099900));
      this.counterView = ((TextView)findViewById(2131099903));
      this.counter = new Counter(this.counterView, 7200000, 1000);
      this.title2.setVisibility(0);
      return;
    }
    catch (Exception localException)
    {
      break label125;
    }
  }

  public void insertToDb(Exercises paramExercises, HashMap<Integer, String> paramHashMap, boolean paramBoolean, double paramDouble1, double paramDouble2)
  {
    LogUtils.myLog("------------------insertToDb--------------------------------------");
    LogUtils.myLog("insertToDb 批次=" + this.excerciseBrushId);
    Iterator localIterator = paramHashMap.keySet().iterator();
    UserAnsDao localUserAnsDao;
    String str1;
    String str2;
    while (true)
    {
      if (!localIterator.hasNext())
      {
        localUserAnsDao = DbService.getInstance(this).getUserAnsDao();
        QueryBuilder localQueryBuilder = localUserAnsDao.queryBuilder();
        str1 = paramExercises.getExerciseId();
        List localList = localQueryBuilder.where(UserAnsDao.Properties.ExerciseId.eq(str1), new WhereCondition[0]).list();
        str2 = opToStr(paramHashMap);
        if ((localList == null) || (localList.size() <= 0))
          break;
        UserAns localUserAns2 = (UserAns)localList.get(0);
        String str3 = localUserAns2.getExcerciseBrushId();
        LogUtils.myLog("insertToDb 数据库的brushId=" + str3);
        localUserAns2.setUserAnswer(str2);
        localUserAns2.setIsCorrect(Boolean.valueOf(paramBoolean));
        localUserAns2.setUserScore(Double.valueOf(paramDouble1));
        localUserAns2.setBrushTime(Long.valueOf(System.currentTimeMillis()));
        localUserAnsDao.update(localUserAns2);
        return;
      }
      Integer localInteger = (Integer)localIterator.next();
      StringBuilder localStringBuilder = new StringBuilder("insertToDb opEdit.get(");
      LogUtils.myLog(localInteger + ")=" + (String)paramHashMap.get(localInteger));
    }
    UserAns localUserAns1 = new UserAns();
    localUserAns1.setBrushTime(Long.valueOf(System.currentTimeMillis()));
    localUserAns1.setCorrectAnswer(opStr(paramExercises.getCorrectAnswer()));
    KpointDetail localKpointDetail = paramExercises.getKpointDetail();
    if (localKpointDetail != null)
    {
      Kpoint localKpoint = localKpointDetail.getKpoint();
      if (localKpoint != null)
        localUserAns1.setKpointId(localKpoint.getId());
    }
    localUserAns1.setUserScore(Double.valueOf(paramDouble1));
    localUserAns1.setIsCorrect(Boolean.valueOf(paramBoolean));
    localUserAns1.setExerciseId(str1);
    localUserAns1.setSubjectId(this.subjectId);
    localUserAns1.setUserAnswer(str2);
    localUserAns1.setExcerciseBrushId(this.excerciseBrushId);
    localUserAnsDao.insert(localUserAns1);
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    this.resultCode = paramInt2;
    Iterator localIterator2;
    label41: Iterator localIterator1;
    if (this.mAllFm == null)
    {
      this.mAllFm = new ArrayList();
      localIterator2 = this.mContentFragments.iterator();
      if (localIterator2.hasNext());
    }
    else if (this.mFmExers == null)
    {
      this.mFmExers = new ArrayList();
      localIterator1 = this.cacheExcerciseList.iterator();
    }
    while (true)
    {
      if (!localIterator1.hasNext());
      switch (paramInt2)
      {
      default:
        return;
        QuestionDetailFragment localQuestionDetailFragment4 = (QuestionDetailFragment)localIterator2.next();
        this.mAllFm.add(localQuestionDetailFragment4);
        break label41;
        Exercises localExercises2 = (Exercises)localIterator1.next();
        this.mFmExers.add(localExercises2);
      case 102:
      case 100:
      case 101:
      }
    }
    finish(0, 0);
    return;
    this.isShowSimuDetail = true;
    showTestCard();
    this.hand_test_paper_ll_pop.setVisibility(8);
    this.preview_have_done_ll_pop.setVisibility(8);
    this.preview_have_done_ll_iv.setBackgroundResource(2130837623);
    this.isSimu = false;
    this.isLast = true;
    this.mContentFragments.clear();
    this.mContentFragments.addAll(this.mAllFm);
    this.cacheExcerciseList.clear();
    this.cacheExcerciseList.addAll(this.mFmExers);
    if (this.isLast)
    {
      QuestionDetailFragment localQuestionDetailFragment3 = new QuestionDetailFragment();
      Bundle localBundle2 = new Bundle();
      localBundle2.putSerializable("exercises", null);
      localQuestionDetailFragment3.setArguments(localBundle2);
      this.mContentFragments.add(localQuestionDetailFragment3);
      this.cacheExcerciseList.add(null);
    }
    this.mAdapter.notifyDataSetChanged();
    this.mViewPager.setCurrentItem(0);
    this.isGon = false;
    this.title1 = ((RelativeLayout)findViewById(2131099759));
    this.question_detail_back_ll = ((LinearLayout)findViewById(2131099890));
    this.question_ll_explain = ((LinearLayout)findViewById(2131099892));
    this.simu_detail_iv = ((ImageView)findViewById(2131099893));
    this.question_ll_collect = ((LinearLayout)findViewById(2131099894));
    this.question_ll_share = ((LinearLayout)findViewById(2131099760));
    this.question_ll_share = ((LinearLayout)findViewById(2131099760));
    this.question_detail_back_ll.setOnClickListener(this);
    this.question_ll_explain.setOnClickListener(this);
    this.question_ll_collect.setOnClickListener(this);
    this.question_ll_share.setOnClickListener(this);
    this.title1.setVisibility(0);
    this.title2.setVisibility(8);
    this.simu_detail_iv.setBackgroundResource(2130837807);
    this.isTotalOpen = true;
    refreshRCL();
    return;
    this.listfm = new ArrayList();
    this.listData = new ArrayList();
    int i = this.mAllFm.size();
    HashMap localHashMap = this.currRight;
    int j = 0;
    if (j >= i)
    {
      this.isLast = true;
      if (this.isLast)
      {
        QuestionDetailFragment localQuestionDetailFragment2 = new QuestionDetailFragment();
        Bundle localBundle1 = new Bundle();
        localBundle1.putSerializable("exercises", null);
        localQuestionDetailFragment2.setArguments(localBundle1);
        this.listfm.add(localQuestionDetailFragment2);
        this.listData.add(null);
      }
      this.mContentFragments.clear();
      this.mContentFragments.addAll(this.listfm);
      this.cacheExcerciseList.clear();
      this.cacheExcerciseList.addAll(this.listData);
      this.handler.sendEmptyMessage(6);
      if (this.simu_card_gv == null)
      {
        showTestCard();
        dimissTestPop();
      }
      this.handler.sendEmptyMessage(3);
      return;
    }
    String str = (String)localHashMap.get(Integer.valueOf(j));
    QuestionDetailFragment localQuestionDetailFragment1 = (QuestionDetailFragment)this.mAllFm.get(j);
    Exercises localExercises1 = (Exercises)this.mFmExers.get(j);
    if ((str == null) || (str.equals("")))
    {
      this.listfm.add(localQuestionDetailFragment1);
      this.listData.add(localExercises1);
    }
    while (true)
    {
      j++;
      break;
      if (str.equals("true"))
        continue;
      this.listfm.add(localQuestionDetailFragment1);
      this.listData.add(localExercises1);
    }
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    case 2131099965:
    case 2131099966:
    case 2131099967:
    default:
    case 2131099890:
    case 2131099894:
    case 2131099760:
    case 2131099892:
    case 2131099897:
    case 2131099898:
    case 2131099900:
    case 2131100480:
    case 2131100484:
    case 2131100488:
    case 2131100489:
    case 2131100009:
    case 2131099916:
      do
      {
        do
        {
          do
          {
            do
            {
              ReqFavoriteExercise localReqFavoriteExercise;
              FavoriteExercise localFavoriteExercise;
              Exercises localExercises;
              do
              {
                do
                {
                  return;
                  finish(2130968578, 2130968582);
                  return;
                }
                while ((this.cacheExcerciseList == null) || (this.cacheExcerciseList.size() <= 0));
                switch (this.FromPage)
                {
                default:
                case 9:
                case 12:
                case 888:
                }
                while (true)
                {
                  this.mProTools.startDialog(true);
                  Boolean localBoolean = (Boolean)this.isCollect.get(Integer.valueOf(this.currentIndex));
                  if ((localBoolean == null) || (!localBoolean.booleanValue()))
                    break;
                  ReqDeleFavor localReqDeleFavor = new ReqDeleFavor();
                  localReqDeleFavor.setVersionName(this.mSP.getVersionName());
                  localReqDeleFavor.setClientType(ToolsUtils.getSDK());
                  localReqDeleFavor.setImei(ToolsUtils.getImei(this.mContext));
                  localReqDeleFavor.setNet(ToolsUtils.getStringNetworkType(this.mContext));
                  UserSubject localUserSubject = new UserSubject();
                  localUserSubject.setUid(this.mSP.getUserId());
                  localUserSubject.setSubjectId(this.subjectId);
                  localReqDeleFavor.setUserSubject(localUserSubject);
                  ArrayList localArrayList = new ArrayList();
                  localArrayList.add(((Exercises)this.cacheExcerciseList.get(this.currentIndex)).getExerciseId());
                  localReqDeleFavor.setExerciseId(localArrayList);
                  UrlFactory.getInstance().deleteFavorExcercise().constructUrl(this, localReqDeleFavor, 8);
                  return;
                  MobclickAgent.onEvent(this.mContext, "brush_c_collect");
                  continue;
                  MobclickAgent.onEvent(this.mContext, "brush_i_collect");
                }
                localReqFavoriteExercise = new ReqFavoriteExercise();
                localReqFavoriteExercise.setVersionName(this.mSP.getVersionName());
                localReqFavoriteExercise.setClientType(ToolsUtils.getSDK());
                localReqFavoriteExercise.setImei(ToolsUtils.getImei(this.mContext));
                localReqFavoriteExercise.setNet(ToolsUtils.getStringNetworkType(this.mContext));
                localReqFavoriteExercise.setUserSubject(this.userSubject);
                localFavoriteExercise = new FavoriteExercise();
                localExercises = (Exercises)this.cacheExcerciseList.get(this.currentIndex);
                localFavoriteExercise.setStemText(localExercises.getStem().getText());
              }
              while (localExercises == null);
              String str = localExercises.getCategory();
              localFavoriteExercise.setTime(System.currentTimeMillis());
              localFavoriteExercise.setCategory(str);
              localFavoriteExercise.setExerciseId(localExercises.getExerciseId());
              KpointDetail localKpointDetail = localExercises.getKpointDetail();
              if (localKpointDetail != null)
                localFavoriteExercise.setKpoint(localKpointDetail.getKpoint());
              while (true)
              {
                localReqFavoriteExercise.setFavoriteExercise(localFavoriteExercise);
                UrlFactory.getInstance().postAddfFavoriteExercise().constructUrl(this, localReqFavoriteExercise, 5);
                return;
                LogUtils.myLog("知识点KpointDetail字段为null");
              }
            }
            while ((this.cacheExcerciseList == null) || (this.cacheExcerciseList.size() <= 0));
            switch (this.FromPage)
            {
            default:
            case 9:
            case 12:
            case 888:
            }
            while (true)
            {
              SharePopWindow localSharePopWindow2 = new SharePopWindow(this, this.mViewPager);
              localSharePopWindow2.showPop();
              return;
              MobclickAgent.onEvent(this.mContext, "brush_c_share");
              continue;
              MobclickAgent.onEvent(this.mContext, "brush_i_share");
            }
          }
          while ((this.cacheExcerciseList == null) || (this.cacheExcerciseList.size() <= 0));
          if ((this.resultCode == 100) || (this.resultCode == 101))
          {
            showTestCard();
            this.hand_test_paper_ll_pop.setVisibility(8);
            this.preview_have_done_ll_pop.setVisibility(8);
            this.preview_have_done_ll_iv.setBackgroundResource(2130837623);
            this.go_last_brush_btn_pop.setText("完成并分享");
            this.go_first_brush_btn_pop.setText("从头浏览");
            return;
          }
          switch (this.FromPage)
          {
          default:
            if (!this.isTotalOpen)
              break;
          case 9:
          case 12:
          case 888:
          }
          for (boolean bool = false; ; bool = true)
          {
            this.isTotalOpen = bool;
            refreshRCL();
            return;
            MobclickAgent.onEvent(this.mContext, "brush_c_show_answer");
            break;
            MobclickAgent.onEvent(this.mContext, "brush_i_show_answer");
            break;
          }
          finish();
          return;
          this.counter.cancel();
          this.flag = 0;
          String[] arrayOfString2 = this.counterView.getText().toString().split(":");
          this.shi = arrayOfString2[0];
          this.fen = arrayOfString2[1];
          this.miao = arrayOfString2[2];
          showPop();
          return;
        }
        while ((this.cacheExcerciseList == null) || (this.cacheExcerciseList.size() <= 0));
        this.flag = 1;
        this.counter.cancel();
        String[] arrayOfString1 = this.counterView.getText().toString().split(":");
        this.shi = arrayOfString1[0];
        this.fen = arrayOfString1[1];
        this.miao = arrayOfString1[2];
        MobclickAgent.onEvent(this.mContext, "test_answer_card");
        showTestCard();
        return;
        if (this.flag == 0)
        {
          this.test_card_pop.dismiss();
          this.test_card_pop = null;
          this.handler.sendEmptyMessageDelayed(2, 100L);
          return;
        }
        this.test_card_pop.dismiss();
        this.test_card_pop = null;
        backgroundAlpha(1.0F);
        goOnCountTime();
        return;
        dimissPop();
        goOnCountTime();
        return;
        MobclickAgent.onEvent(this.mContext, "test_continue");
        dimissPop();
        goOnCountTime();
        return;
        MobclickAgent.onEvent(this.mContext, "test_show_paper");
        showTestCard();
        return;
        MobclickAgent.onEvent(this.mContext, "test_submit_now");
        this.endTime = System.currentTimeMillis();
        handPaper();
        return;
        dimissTestPop();
      }
      while (this.flag == 0);
      goOnCountTime();
      return;
    case 2131099921:
      if (this.isShowSimuDetail)
      {
        MobclickAgent.onEvent(this.mContext, "test_answer_card_form_start");
        this.mViewPager.setCurrentItem(0);
        this.test_card_pop.dismiss();
        return;
      }
      this.test_card_pop.dismiss();
      this.test_card_pop = null;
      dimissPop();
      return;
    case 2131099922:
      if (this.isShowSimuDetail)
      {
        this.test_card_pop.dismiss();
        SharePopWindow localSharePopWindow1 = new SharePopWindow(this, this.ll_test_card_bottom);
        localSharePopWindow1.showPop();
        return;
      }
      this.endTime = System.currentTimeMillis();
      handPaper();
      return;
    case 2131100039:
      dismissPop1();
      return;
    case 2131100045:
    }
    dismissPop1();
  }

  protected void onDestroy()
  {
    super.onDestroy();
  }

  protected void onRestart()
  {
    super.onRestart();
  }

  protected void onResume()
  {
    super.onResume();
    if (this.counter != null)
      this.counter.start();
  }

  protected void onStop()
  {
    super.onStop();
    switch (this.FromPage)
    {
    case 3:
    case 17:
    default:
      return;
    case 10:
      sendPushComamd(Constants.BrushType.ERROR_BASKET_BRUSH, 0.0D);
      return;
    case 1:
      sendPushComamd(Constants.BrushType.SEQUENCE_BRUSH, 0.0D);
      return;
    case 888:
      sendPushComamd(Constants.BrushType.SMART_BRUSH, 0.0D);
      return;
    case 12:
      sendPushComamd(Constants.BrushType.SEQUENCE_BRUSH, 0.0D);
      return;
    case 9:
    }
    sendPushComamd(Constants.BrushType.SEQUENCE_BRUSH, 0.0D);
  }

  // ERROR //
  public void onSuccess(String paramString1, java.util.Map<String, String> paramMap, String paramString2, int paramInt)
  {
    // Byte code:
    //   0: aload_0
    //   1: aload_1
    //   2: aload_2
    //   3: aload_3
    //   4: iload 4
    //   6: invokespecial 1547	com/withustudy/koudaizikao/base/AbsBaseActivity:onSuccess	(Ljava/lang/String;Ljava/util/Map;Ljava/lang/String;I)V
    //   9: aload_0
    //   10: getfield 208	com/withustudy/koudaizikao/activity/QuestionDetailActivity:isInit	Z
    //   13: ifeq +107 -> 120
    //   16: aload_0
    //   17: iconst_0
    //   18: putfield 208	com/withustudy/koudaizikao/activity/QuestionDetailActivity:isInit	Z
    //   21: aload_0
    //   22: getfield 210	com/withustudy/koudaizikao/activity/QuestionDetailActivity:isSimu	Z
    //   25: ifeq +80 -> 105
    //   28: aload_0
    //   29: getfield 243	com/withustudy/koudaizikao/activity/QuestionDetailActivity:handler	Landroid/os/Handler;
    //   32: iconst_1
    //   33: ldc2_w 1548
    //   36: invokevirtual 1515	android/os/Handler:sendEmptyMessageDelayed	(IJ)Z
    //   39: pop
    //   40: aload_1
    //   41: ifnull +63 -> 104
    //   44: new 1551	com/google/gson/Gson
    //   47: dup
    //   48: invokespecial 1552	com/google/gson/Gson:<init>	()V
    //   51: astore 5
    //   53: iload 4
    //   55: tableswitch	default:+49 -> 104, 0:+75->130, 1:+49->104, 2:+49->104, 3:+49->104, 4:+594->649, 5:+1027->1082, 6:+49->104, 7:+49->104, 8:+1072->1127
    //   105: aload_0
    //   106: getfield 243	com/withustudy/koudaizikao/activity/QuestionDetailActivity:handler	Landroid/os/Handler;
    //   109: iconst_1
    //   110: ldc2_w 1553
    //   113: invokevirtual 1515	android/os/Handler:sendEmptyMessageDelayed	(IJ)Z
    //   116: pop
    //   117: goto -77 -> 40
    //   120: aload_0
    //   121: getfield 279	com/withustudy/koudaizikao/activity/QuestionDetailActivity:mProTools	Lcom/withustudy/koudaizikao/tools/ProTools;
    //   124: invokevirtual 1557	com/withustudy/koudaizikao/tools/ProTools:dismissDislog	()V
    //   127: goto -87 -> 40
    //   130: aload_0
    //   131: aload 5
    //   133: aload_1
    //   134: ldc_w 1559
    //   137: invokevirtual 1563	com/google/gson/Gson:fromJson	(Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object;
    //   140: checkcast 1559	com/withustudy/koudaizikao/entity/BrushExcerciseBean
    //   143: putfield 271	com/withustudy/koudaizikao/activity/QuestionDetailActivity:brushExcerciseBean	Lcom/withustudy/koudaizikao/entity/BrushExcerciseBean;
    //   146: aload_0
    //   147: getfield 271	com/withustudy/koudaizikao/activity/QuestionDetailActivity:brushExcerciseBean	Lcom/withustudy/koudaizikao/entity/BrushExcerciseBean;
    //   150: ifnull +482 -> 632
    //   153: aload_0
    //   154: getfield 243	com/withustudy/koudaizikao/activity/QuestionDetailActivity:handler	Landroid/os/Handler;
    //   157: iconst_0
    //   158: invokevirtual 846	android/os/Handler:sendEmptyMessage	(I)Z
    //   161: pop
    //   162: new 525	java/lang/StringBuilder
    //   165: dup
    //   166: ldc_w 1565
    //   169: invokespecial 532	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   172: aload_0
    //   173: getfield 271	com/withustudy/koudaizikao/activity/QuestionDetailActivity:brushExcerciseBean	Lcom/withustudy/koudaizikao/entity/BrushExcerciseBean;
    //   176: invokevirtual 1568	com/withustudy/koudaizikao/entity/BrushExcerciseBean:isEnd	()Z
    //   179: invokevirtual 1571	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
    //   182: invokevirtual 542	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   185: invokestatic 877	com/withustudy/koudaizikao/tools/LogUtils:myLog	(Ljava/lang/Object;)V
    //   188: aload_0
    //   189: getfield 212	com/withustudy/koudaizikao/activity/QuestionDetailActivity:FromPage	I
    //   192: iconst_3
    //   193: if_icmpeq +333 -> 526
    //   196: aload_0
    //   197: getfield 212	com/withustudy/koudaizikao/activity/QuestionDetailActivity:FromPage	I
    //   200: bipush 16
    //   202: if_icmpeq +324 -> 526
    //   205: iconst_0
    //   206: istore 24
    //   208: aload_0
    //   209: getfield 210	com/withustudy/koudaizikao/activity/QuestionDetailActivity:isSimu	Z
    //   212: ifeq -108 -> 104
    //   215: iload 24
    //   217: ifeq -113 -> 104
    //   220: aload_0
    //   221: getfield 321	com/withustudy/koudaizikao/activity/QuestionDetailActivity:resultCode	I
    //   224: ifne -120 -> 104
    //   227: aload_0
    //   228: getfield 234	com/withustudy/koudaizikao/activity/QuestionDetailActivity:isOnce	Z
    //   231: ifeq -127 -> 104
    //   234: aload_0
    //   235: iconst_0
    //   236: putfield 234	com/withustudy/koudaizikao/activity/QuestionDetailActivity:isOnce	Z
    //   239: aload_0
    //   240: getfield 1005	com/withustudy/koudaizikao/activity/QuestionDetailActivity:req	Lcom/withustudy/koudaizikao/entity/req/ExerciseIdList;
    //   243: ifnonnull +289 -> 532
    //   246: aload_0
    //   247: new 470	com/withustudy/koudaizikao/entity/req/ExerciseIdList
    //   250: dup
    //   251: invokespecial 1003	com/withustudy/koudaizikao/entity/req/ExerciseIdList:<init>	()V
    //   254: putfield 1005	com/withustudy/koudaizikao/activity/QuestionDetailActivity:req	Lcom/withustudy/koudaizikao/entity/req/ExerciseIdList;
    //   257: aload_0
    //   258: getfield 1005	com/withustudy/koudaizikao/activity/QuestionDetailActivity:req	Lcom/withustudy/koudaizikao/entity/req/ExerciseIdList;
    //   261: aload_0
    //   262: getfield 682	com/withustudy/koudaizikao/activity/QuestionDetailActivity:mSP	Lcom/withustudy/koudaizikao/config/KouDaiSP;
    //   265: invokevirtual 1008	com/withustudy/koudaizikao/config/KouDaiSP:getVersionName	()Ljava/lang/String;
    //   268: invokevirtual 1011	com/withustudy/koudaizikao/entity/req/ExerciseIdList:setVersionName	(Ljava/lang/String;)V
    //   271: aload_0
    //   272: getfield 1005	com/withustudy/koudaizikao/activity/QuestionDetailActivity:req	Lcom/withustudy/koudaizikao/entity/req/ExerciseIdList;
    //   275: invokestatic 1014	com/withustudy/koudaizikao/tools/ToolsUtils:getSDK	()Ljava/lang/String;
    //   278: invokevirtual 1017	com/withustudy/koudaizikao/entity/req/ExerciseIdList:setClientType	(Ljava/lang/String;)V
    //   281: aload_0
    //   282: getfield 1005	com/withustudy/koudaizikao/activity/QuestionDetailActivity:req	Lcom/withustudy/koudaizikao/entity/req/ExerciseIdList;
    //   285: aload_0
    //   286: getfield 881	com/withustudy/koudaizikao/activity/QuestionDetailActivity:mContext	Landroid/content/Context;
    //   289: invokestatic 1021	com/withustudy/koudaizikao/tools/ToolsUtils:getImei	(Landroid/content/Context;)Ljava/lang/String;
    //   292: invokevirtual 1024	com/withustudy/koudaizikao/entity/req/ExerciseIdList:setImei	(Ljava/lang/String;)V
    //   295: aload_0
    //   296: getfield 1005	com/withustudy/koudaizikao/activity/QuestionDetailActivity:req	Lcom/withustudy/koudaizikao/entity/req/ExerciseIdList;
    //   299: aload_0
    //   300: getfield 881	com/withustudy/koudaizikao/activity/QuestionDetailActivity:mContext	Landroid/content/Context;
    //   303: invokestatic 1027	com/withustudy/koudaizikao/tools/ToolsUtils:getStringNetworkType	(Landroid/content/Context;)Ljava/lang/String;
    //   306: invokevirtual 1030	com/withustudy/koudaizikao/entity/req/ExerciseIdList:setNet	(Ljava/lang/String;)V
    //   309: aload_0
    //   310: getfield 1032	com/withustudy/koudaizikao/activity/QuestionDetailActivity:cacheId	Ljava/util/List;
    //   313: ifnonnull +234 -> 547
    //   316: aload_0
    //   317: new 199	java/util/ArrayList
    //   320: dup
    //   321: invokespecial 200	java/util/ArrayList:<init>	()V
    //   324: putfield 1032	com/withustudy/koudaizikao/activity/QuestionDetailActivity:cacheId	Ljava/util/List;
    //   327: aload_0
    //   328: getfield 328	com/withustudy/koudaizikao/activity/QuestionDetailActivity:exerciseIdList	Lcom/withustudy/koudaizikao/entity/req/ExerciseIdList;
    //   331: invokevirtual 474	com/withustudy/koudaizikao/entity/req/ExerciseIdList:getExerciseId	()Ljava/util/List;
    //   334: astore 25
    //   336: aload 25
    //   338: ifnull -234 -> 104
    //   341: aload 25
    //   343: invokeinterface 480 1 0
    //   348: ifle -244 -> 104
    //   351: aload_0
    //   352: getfield 230	com/withustudy/koudaizikao/activity/QuestionDetailActivity:batchIndex	I
    //   355: istore 26
    //   357: iload 26
    //   359: aload 25
    //   361: invokeinterface 480 1 0
    //   366: if_icmplt +193 -> 559
    //   369: aload_0
    //   370: getfield 1032	com/withustudy/koudaizikao/activity/QuestionDetailActivity:cacheId	Ljava/util/List;
    //   373: invokeinterface 480 1 0
    //   378: ifle -274 -> 104
    //   381: new 525	java/lang/StringBuilder
    //   384: dup
    //   385: ldc_w 1573
    //   388: invokespecial 532	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   391: aload_0
    //   392: getfield 1032	com/withustudy/koudaizikao/activity/QuestionDetailActivity:cacheId	Ljava/util/List;
    //   395: invokeinterface 480 1 0
    //   400: invokevirtual 652	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   403: invokevirtual 542	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   406: invokestatic 877	com/withustudy/koudaizikao/tools/LogUtils:myLog	(Ljava/lang/Object;)V
    //   409: aload_0
    //   410: getfield 1005	com/withustudy/koudaizikao/activity/QuestionDetailActivity:req	Lcom/withustudy/koudaizikao/entity/req/ExerciseIdList;
    //   413: aload_0
    //   414: getfield 1032	com/withustudy/koudaizikao/activity/QuestionDetailActivity:cacheId	Ljava/util/List;
    //   417: invokevirtual 1051	com/withustudy/koudaizikao/entity/req/ExerciseIdList:setExerciseId	(Ljava/util/List;)V
    //   420: aload_0
    //   421: getfield 212	com/withustudy/koudaizikao/activity/QuestionDetailActivity:FromPage	I
    //   424: bipush 16
    //   426: if_icmpne +179 -> 605
    //   429: new 1191	com/withustudy/koudaizikao/entity/req/UserSubject
    //   432: dup
    //   433: invokespecial 1192	com/withustudy/koudaizikao/entity/req/UserSubject:<init>	()V
    //   436: astore 27
    //   438: aload 27
    //   440: aload_0
    //   441: getfield 682	com/withustudy/koudaizikao/activity/QuestionDetailActivity:mSP	Lcom/withustudy/koudaizikao/config/KouDaiSP;
    //   444: invokevirtual 1195	com/withustudy/koudaizikao/config/KouDaiSP:getUserId	()Ljava/lang/String;
    //   447: invokevirtual 1198	com/withustudy/koudaizikao/entity/req/UserSubject:setUid	(Ljava/lang/String;)V
    //   450: aload 27
    //   452: aload_0
    //   453: getfield 441	com/withustudy/koudaizikao/activity/QuestionDetailActivity:subjectId	Ljava/lang/String;
    //   456: invokevirtual 1201	com/withustudy/koudaizikao/entity/req/UserSubject:setSubjectId	(Ljava/lang/String;)V
    //   459: aload_0
    //   460: getfield 1005	com/withustudy/koudaizikao/activity/QuestionDetailActivity:req	Lcom/withustudy/koudaizikao/entity/req/ExerciseIdList;
    //   463: aload 27
    //   465: invokevirtual 1057	com/withustudy/koudaizikao/entity/req/ExerciseIdList:setUserSubject	(Lcom/withustudy/koudaizikao/entity/req/UserSubject;)V
    //   468: aload_0
    //   469: getfield 212	com/withustudy/koudaizikao/activity/QuestionDetailActivity:FromPage	I
    //   472: tableswitch	default:+20 -> 492, 16:+147->619
    //   493: getfield 1005	com/withustudy/koudaizikao/activity/QuestionDetailActivity:req	Lcom/withustudy/koudaizikao/entity/req/ExerciseIdList;
    //   496: getstatic 429	com/withustudy/koudaizikao/config/Constants$BrushType:MOCK_EXAM	Ljava/lang/String;
    //   499: invokevirtual 1063	com/withustudy/koudaizikao/entity/req/ExerciseIdList:setBrushType	(Ljava/lang/String;)V
    //   502: invokestatic 1068	com/withustudy/koudaizikao/action/UrlFactory:getInstance	()Lcom/withustudy/koudaizikao/action/UrlFactory;
    //   505: invokevirtual 1072	com/withustudy/koudaizikao/action/UrlFactory:getExcerciseDetail	()Lcom/withustudy/koudaizikao/action/AbsSimpleImplUrlFactory;
    //   508: aload_0
    //   509: aload_0
    //   510: getfield 1005	com/withustudy/koudaizikao/activity/QuestionDetailActivity:req	Lcom/withustudy/koudaizikao/entity/req/ExerciseIdList;
    //   513: iconst_0
    //   514: invokevirtual 1078	com/withustudy/koudaizikao/action/AbsSimpleImplUrlFactory:constructUrl	(Lcom/android/http/RequestManager$RequestListener;Ljava/lang/Object;I)V
    //   517: return
    //   518: astore 21
    //   520: aload 21
    //   522: invokevirtual 1576	java/lang/Exception:printStackTrace	()V
    //   525: return
    //   526: iconst_1
    //   527: istore 24
    //   529: goto -321 -> 208
    //   532: aload_0
    //   533: getfield 1005	com/withustudy/koudaizikao/activity/QuestionDetailActivity:req	Lcom/withustudy/koudaizikao/entity/req/ExerciseIdList;
    //   536: invokevirtual 474	com/withustudy/koudaizikao/entity/req/ExerciseIdList:getExerciseId	()Ljava/util/List;
    //   539: invokeinterface 1095 1 0
    //   544: goto -235 -> 309
    //   547: aload_0
    //   548: getfield 1032	com/withustudy/koudaizikao/activity/QuestionDetailActivity:cacheId	Ljava/util/List;
    //   551: invokeinterface 1095 1 0
    //   556: goto -229 -> 327
    //   559: aload 25
    //   561: iload 26
    //   563: invokeinterface 564 2 0
    //   568: checkcast 523	java/lang/String
    //   571: astore 28
    //   573: iload 26
    //   575: iconst_m1
    //   576: aload 25
    //   578: invokeinterface 480 1 0
    //   583: iadd
    //   584: if_icmpgt -215 -> 369
    //   587: aload_0
    //   588: getfield 1032	com/withustudy/koudaizikao/activity/QuestionDetailActivity:cacheId	Ljava/util/List;
    //   591: aload 28
    //   593: invokeinterface 1047 2 0
    //   598: pop
    //   599: iinc 26 1
    //   602: goto -245 -> 357
    //   605: aload_0
    //   606: getfield 1005	com/withustudy/koudaizikao/activity/QuestionDetailActivity:req	Lcom/withustudy/koudaizikao/entity/req/ExerciseIdList;
    //   609: aload_0
    //   610: getfield 1053	com/withustudy/koudaizikao/activity/QuestionDetailActivity:userSubject	Lcom/withustudy/koudaizikao/entity/req/UserSubject;
    //   613: invokevirtual 1057	com/withustudy/koudaizikao/entity/req/ExerciseIdList:setUserSubject	(Lcom/withustudy/koudaizikao/entity/req/UserSubject;)V
    //   616: goto -148 -> 468
    //   619: aload_0
    //   620: getfield 1005	com/withustudy/koudaizikao/activity/QuestionDetailActivity:req	Lcom/withustudy/koudaizikao/entity/req/ExerciseIdList;
    //   623: getstatic 505	com/withustudy/koudaizikao/config/Constants$BrushType:REAL_EXAM	Ljava/lang/String;
    //   626: invokevirtual 1063	com/withustudy/koudaizikao/entity/req/ExerciseIdList:setBrushType	(Ljava/lang/String;)V
    //   629: goto -127 -> 502
    //   632: ldc_w 1578
    //   635: invokestatic 877	com/withustudy/koudaizikao/tools/LogUtils:myLog	(Ljava/lang/Object;)V
    //   638: aload_0
    //   639: getfield 243	com/withustudy/koudaizikao/activity/QuestionDetailActivity:handler	Landroid/os/Handler;
    //   642: bipush 10
    //   644: invokevirtual 846	android/os/Handler:sendEmptyMessage	(I)Z
    //   647: pop
    //   648: return
    //   649: aload_0
    //   650: aload 5
    //   652: aload_1
    //   653: ldc_w 470
    //   656: invokevirtual 1563	com/google/gson/Gson:fromJson	(Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object;
    //   659: checkcast 470	com/withustudy/koudaizikao/entity/req/ExerciseIdList
    //   662: putfield 328	com/withustudy/koudaizikao/activity/QuestionDetailActivity:exerciseIdList	Lcom/withustudy/koudaizikao/entity/req/ExerciseIdList;
    //   665: aload_0
    //   666: getfield 328	com/withustudy/koudaizikao/activity/QuestionDetailActivity:exerciseIdList	Lcom/withustudy/koudaizikao/entity/req/ExerciseIdList;
    //   669: ifnull +406 -> 1075
    //   672: aload_0
    //   673: getfield 328	com/withustudy/koudaizikao/activity/QuestionDetailActivity:exerciseIdList	Lcom/withustudy/koudaizikao/entity/req/ExerciseIdList;
    //   676: invokevirtual 474	com/withustudy/koudaizikao/entity/req/ExerciseIdList:getExerciseId	()Ljava/util/List;
    //   679: astore 15
    //   681: aload 15
    //   683: ifnull +381 -> 1064
    //   686: aload 15
    //   688: invokeinterface 480 1 0
    //   693: ifle +371 -> 1064
    //   696: new 525	java/lang/StringBuilder
    //   699: dup
    //   700: ldc_w 1580
    //   703: invokespecial 532	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   706: aload_0
    //   707: getfield 328	com/withustudy/koudaizikao/activity/QuestionDetailActivity:exerciseIdList	Lcom/withustudy/koudaizikao/entity/req/ExerciseIdList;
    //   710: invokevirtual 474	com/withustudy/koudaizikao/entity/req/ExerciseIdList:getExerciseId	()Ljava/util/List;
    //   713: invokeinterface 480 1 0
    //   718: invokevirtual 652	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   721: invokevirtual 542	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   724: invokestatic 877	com/withustudy/koudaizikao/tools/LogUtils:myLog	(Ljava/lang/Object;)V
    //   727: aload_0
    //   728: getfield 1005	com/withustudy/koudaizikao/activity/QuestionDetailActivity:req	Lcom/withustudy/koudaizikao/entity/req/ExerciseIdList;
    //   731: ifnonnull +247 -> 978
    //   734: aload_0
    //   735: new 470	com/withustudy/koudaizikao/entity/req/ExerciseIdList
    //   738: dup
    //   739: invokespecial 1003	com/withustudy/koudaizikao/entity/req/ExerciseIdList:<init>	()V
    //   742: putfield 1005	com/withustudy/koudaizikao/activity/QuestionDetailActivity:req	Lcom/withustudy/koudaizikao/entity/req/ExerciseIdList;
    //   745: aload_0
    //   746: getfield 1005	com/withustudy/koudaizikao/activity/QuestionDetailActivity:req	Lcom/withustudy/koudaizikao/entity/req/ExerciseIdList;
    //   749: aload_0
    //   750: getfield 682	com/withustudy/koudaizikao/activity/QuestionDetailActivity:mSP	Lcom/withustudy/koudaizikao/config/KouDaiSP;
    //   753: invokevirtual 1008	com/withustudy/koudaizikao/config/KouDaiSP:getVersionName	()Ljava/lang/String;
    //   756: invokevirtual 1011	com/withustudy/koudaizikao/entity/req/ExerciseIdList:setVersionName	(Ljava/lang/String;)V
    //   759: aload_0
    //   760: getfield 1005	com/withustudy/koudaizikao/activity/QuestionDetailActivity:req	Lcom/withustudy/koudaizikao/entity/req/ExerciseIdList;
    //   763: invokestatic 1014	com/withustudy/koudaizikao/tools/ToolsUtils:getSDK	()Ljava/lang/String;
    //   766: invokevirtual 1017	com/withustudy/koudaizikao/entity/req/ExerciseIdList:setClientType	(Ljava/lang/String;)V
    //   769: aload_0
    //   770: getfield 1005	com/withustudy/koudaizikao/activity/QuestionDetailActivity:req	Lcom/withustudy/koudaizikao/entity/req/ExerciseIdList;
    //   773: aload_0
    //   774: getfield 881	com/withustudy/koudaizikao/activity/QuestionDetailActivity:mContext	Landroid/content/Context;
    //   777: invokestatic 1021	com/withustudy/koudaizikao/tools/ToolsUtils:getImei	(Landroid/content/Context;)Ljava/lang/String;
    //   780: invokevirtual 1024	com/withustudy/koudaizikao/entity/req/ExerciseIdList:setImei	(Ljava/lang/String;)V
    //   783: aload_0
    //   784: getfield 1005	com/withustudy/koudaizikao/activity/QuestionDetailActivity:req	Lcom/withustudy/koudaizikao/entity/req/ExerciseIdList;
    //   787: aload_0
    //   788: getfield 881	com/withustudy/koudaizikao/activity/QuestionDetailActivity:mContext	Landroid/content/Context;
    //   791: invokestatic 1027	com/withustudy/koudaizikao/tools/ToolsUtils:getStringNetworkType	(Landroid/content/Context;)Ljava/lang/String;
    //   794: invokevirtual 1030	com/withustudy/koudaizikao/entity/req/ExerciseIdList:setNet	(Ljava/lang/String;)V
    //   797: aload_0
    //   798: getfield 1032	com/withustudy/koudaizikao/activity/QuestionDetailActivity:cacheId	Ljava/util/List;
    //   801: ifnonnull +192 -> 993
    //   804: aload_0
    //   805: new 199	java/util/ArrayList
    //   808: dup
    //   809: invokespecial 200	java/util/ArrayList:<init>	()V
    //   812: putfield 1032	com/withustudy/koudaizikao/activity/QuestionDetailActivity:cacheId	Ljava/util/List;
    //   815: aload_0
    //   816: getfield 328	com/withustudy/koudaizikao/activity/QuestionDetailActivity:exerciseIdList	Lcom/withustudy/koudaizikao/entity/req/ExerciseIdList;
    //   819: invokevirtual 474	com/withustudy/koudaizikao/entity/req/ExerciseIdList:getExerciseId	()Ljava/util/List;
    //   822: astore 17
    //   824: aload_0
    //   825: aload 17
    //   827: invokeinterface 480 1 0
    //   832: putfield 232	com/withustudy/koudaizikao/activity/QuestionDetailActivity:simuExCount	I
    //   835: aload 17
    //   837: ifnull -733 -> 104
    //   840: aload_0
    //   841: getfield 232	com/withustudy/koudaizikao/activity/QuestionDetailActivity:simuExCount	I
    //   844: ifle -740 -> 104
    //   847: iconst_0
    //   848: istore 18
    //   850: goto +333 -> 1183
    //   853: aload_0
    //   854: iconst_5
    //   855: aload_0
    //   856: getfield 230	com/withustudy/koudaizikao/activity/QuestionDetailActivity:batchIndex	I
    //   859: iadd
    //   860: putfield 230	com/withustudy/koudaizikao/activity/QuestionDetailActivity:batchIndex	I
    //   863: aload_0
    //   864: getfield 1032	com/withustudy/koudaizikao/activity/QuestionDetailActivity:cacheId	Ljava/util/List;
    //   867: invokeinterface 480 1 0
    //   872: ifle -768 -> 104
    //   875: aload_0
    //   876: iconst_1
    //   877: putfield 234	com/withustudy/koudaizikao/activity/QuestionDetailActivity:isOnce	Z
    //   880: new 525	java/lang/StringBuilder
    //   883: dup
    //   884: ldc_w 1573
    //   887: invokespecial 532	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   890: aload_0
    //   891: getfield 1032	com/withustudy/koudaizikao/activity/QuestionDetailActivity:cacheId	Ljava/util/List;
    //   894: invokeinterface 480 1 0
    //   899: invokevirtual 652	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   902: invokevirtual 542	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   905: invokestatic 877	com/withustudy/koudaizikao/tools/LogUtils:myLog	(Ljava/lang/Object;)V
    //   908: aload_0
    //   909: getfield 1005	com/withustudy/koudaizikao/activity/QuestionDetailActivity:req	Lcom/withustudy/koudaizikao/entity/req/ExerciseIdList;
    //   912: aload_0
    //   913: getfield 1032	com/withustudy/koudaizikao/activity/QuestionDetailActivity:cacheId	Ljava/util/List;
    //   916: invokevirtual 1051	com/withustudy/koudaizikao/entity/req/ExerciseIdList:setExerciseId	(Ljava/util/List;)V
    //   919: aload_0
    //   920: getfield 1005	com/withustudy/koudaizikao/activity/QuestionDetailActivity:req	Lcom/withustudy/koudaizikao/entity/req/ExerciseIdList;
    //   923: aload_0
    //   924: getfield 1053	com/withustudy/koudaizikao/activity/QuestionDetailActivity:userSubject	Lcom/withustudy/koudaizikao/entity/req/UserSubject;
    //   927: invokevirtual 1057	com/withustudy/koudaizikao/entity/req/ExerciseIdList:setUserSubject	(Lcom/withustudy/koudaizikao/entity/req/UserSubject;)V
    //   930: aload_0
    //   931: getfield 212	com/withustudy/koudaizikao/activity/QuestionDetailActivity:FromPage	I
    //   934: tableswitch	default:+18 -> 952, 16:+117->1051
    //   953: getfield 1005	com/withustudy/koudaizikao/activity/QuestionDetailActivity:req	Lcom/withustudy/koudaizikao/entity/req/ExerciseIdList;
    //   956: getstatic 429	com/withustudy/koudaizikao/config/Constants$BrushType:MOCK_EXAM	Ljava/lang/String;
    //   959: invokevirtual 1063	com/withustudy/koudaizikao/entity/req/ExerciseIdList:setBrushType	(Ljava/lang/String;)V
    //   962: invokestatic 1068	com/withustudy/koudaizikao/action/UrlFactory:getInstance	()Lcom/withustudy/koudaizikao/action/UrlFactory;
    //   965: invokevirtual 1072	com/withustudy/koudaizikao/action/UrlFactory:getExcerciseDetail	()Lcom/withustudy/koudaizikao/action/AbsSimpleImplUrlFactory;
    //   968: aload_0
    //   969: aload_0
    //   970: getfield 1005	com/withustudy/koudaizikao/activity/QuestionDetailActivity:req	Lcom/withustudy/koudaizikao/entity/req/ExerciseIdList;
    //   973: iconst_0
    //   974: invokevirtual 1078	com/withustudy/koudaizikao/action/AbsSimpleImplUrlFactory:constructUrl	(Lcom/android/http/RequestManager$RequestListener;Ljava/lang/Object;I)V
    //   977: return
    //   978: aload_0
    //   979: getfield 1005	com/withustudy/koudaizikao/activity/QuestionDetailActivity:req	Lcom/withustudy/koudaizikao/entity/req/ExerciseIdList;
    //   982: invokevirtual 474	com/withustudy/koudaizikao/entity/req/ExerciseIdList:getExerciseId	()Ljava/util/List;
    //   985: invokeinterface 1095 1 0
    //   990: goto -193 -> 797
    //   993: aload_0
    //   994: getfield 1032	com/withustudy/koudaizikao/activity/QuestionDetailActivity:cacheId	Ljava/util/List;
    //   997: invokeinterface 1095 1 0
    //   1002: goto -187 -> 815
    //   1005: aload 17
    //   1007: iload 18
    //   1009: invokeinterface 564 2 0
    //   1014: checkcast 523	java/lang/String
    //   1017: astore 19
    //   1019: iload 18
    //   1021: iconst_m1
    //   1022: aload 17
    //   1024: invokeinterface 480 1 0
    //   1029: iadd
    //   1030: if_icmpgt -177 -> 853
    //   1033: aload_0
    //   1034: getfield 1032	com/withustudy/koudaizikao/activity/QuestionDetailActivity:cacheId	Ljava/util/List;
    //   1037: aload 19
    //   1039: invokeinterface 1047 2 0
    //   1044: pop
    //   1045: iinc 18 1
    //   1048: goto +135 -> 1183
    //   1051: aload_0
    //   1052: getfield 1005	com/withustudy/koudaizikao/activity/QuestionDetailActivity:req	Lcom/withustudy/koudaizikao/entity/req/ExerciseIdList;
    //   1055: getstatic 505	com/withustudy/koudaizikao/config/Constants$BrushType:REAL_EXAM	Ljava/lang/String;
    //   1058: invokevirtual 1063	com/withustudy/koudaizikao/entity/req/ExerciseIdList:setBrushType	(Ljava/lang/String;)V
    //   1061: goto -99 -> 962
    //   1064: aload_0
    //   1065: getfield 243	com/withustudy/koudaizikao/activity/QuestionDetailActivity:handler	Landroid/os/Handler;
    //   1068: bipush 7
    //   1070: invokevirtual 846	android/os/Handler:sendEmptyMessage	(I)Z
    //   1073: pop
    //   1074: return
    //   1075: ldc_w 1582
    //   1078: invokestatic 877	com/withustudy/koudaizikao/tools/LogUtils:myLog	(Ljava/lang/Object;)V
    //   1081: return
    //   1082: aload 5
    //   1084: aload_1
    //   1085: ldc_w 1584
    //   1088: invokevirtual 1563	com/google/gson/Gson:fromJson	(Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object;
    //   1091: checkcast 1584	com/withustudy/koudaizikao/entity/RspCollectExcerBean
    //   1094: astore 11
    //   1096: aload 11
    //   1098: ifnull -994 -> 104
    //   1101: aload_0
    //   1102: getfield 243	com/withustudy/koudaizikao/activity/QuestionDetailActivity:handler	Landroid/os/Handler;
    //   1105: iconst_5
    //   1106: aload 11
    //   1108: invokevirtual 1588	android/os/Handler:obtainMessage	(ILjava/lang/Object;)Landroid/os/Message;
    //   1111: astore 12
    //   1113: aload_0
    //   1114: getfield 243	com/withustudy/koudaizikao/activity/QuestionDetailActivity:handler	Landroid/os/Handler;
    //   1117: aload 12
    //   1119: invokevirtual 1592	android/os/Handler:sendMessage	(Landroid/os/Message;)Z
    //   1122: pop
    //   1123: return
    //   1124: astore 10
    //   1126: return
    //   1127: invokestatic 1596	com/withustudy/koudaizikao/action/UrlFactory:getInstanceGson	()Lcom/google/gson/Gson;
    //   1130: aload_1
    //   1131: ldc_w 1598
    //   1134: invokevirtual 1563	com/google/gson/Gson:fromJson	(Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object;
    //   1137: checkcast 1598	com/withustudy/koudaizikao/entity/OpreationState
    //   1140: astore 7
    //   1142: aload 7
    //   1144: ifnull -1040 -> 104
    //   1147: aload 7
    //   1149: invokevirtual 1601	com/withustudy/koudaizikao/entity/OpreationState:getStatus	()Ljava/lang/String;
    //   1152: ldc_w 1603
    //   1155: invokevirtual 559	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1158: ifeq +14 -> 1172
    //   1161: aload_0
    //   1162: getfield 243	com/withustudy/koudaizikao/activity/QuestionDetailActivity:handler	Landroid/os/Handler;
    //   1165: bipush 8
    //   1167: invokevirtual 846	android/os/Handler:sendEmptyMessage	(I)Z
    //   1170: pop
    //   1171: return
    //   1172: aload_0
    //   1173: getfield 243	com/withustudy/koudaizikao/activity/QuestionDetailActivity:handler	Landroid/os/Handler;
    //   1176: bipush 9
    //   1178: invokevirtual 846	android/os/Handler:sendEmptyMessage	(I)Z
    //   1181: pop
    //   1182: return
    //   1183: iload 18
    //   1185: iconst_5
    //   1186: if_icmplt -181 -> 1005
    //   1189: goto -336 -> 853
    //   1192: astore 14
    //   1194: return
    //   1195: astore 6
    //   1197: return
    //
    // Exception table:
    //   from	to	target	type
    //   130	205	518	java/lang/Exception
    //   208	215	518	java/lang/Exception
    //   220	309	518	java/lang/Exception
    //   309	327	518	java/lang/Exception
    //   327	336	518	java/lang/Exception
    //   341	357	518	java/lang/Exception
    //   357	369	518	java/lang/Exception
    //   369	468	518	java/lang/Exception
    //   468	492	518	java/lang/Exception
    //   492	502	518	java/lang/Exception
    //   502	517	518	java/lang/Exception
    //   532	544	518	java/lang/Exception
    //   547	556	518	java/lang/Exception
    //   559	599	518	java/lang/Exception
    //   605	616	518	java/lang/Exception
    //   619	629	518	java/lang/Exception
    //   632	648	518	java/lang/Exception
    //   1082	1096	1124	java/lang/Exception
    //   1101	1123	1124	java/lang/Exception
    //   649	681	1192	java/lang/Exception
    //   686	797	1192	java/lang/Exception
    //   797	815	1192	java/lang/Exception
    //   815	835	1192	java/lang/Exception
    //   840	847	1192	java/lang/Exception
    //   853	952	1192	java/lang/Exception
    //   952	962	1192	java/lang/Exception
    //   962	977	1192	java/lang/Exception
    //   978	990	1192	java/lang/Exception
    //   993	1002	1192	java/lang/Exception
    //   1005	1045	1192	java/lang/Exception
    //   1051	1061	1192	java/lang/Exception
    //   1064	1074	1192	java/lang/Exception
    //   1075	1081	1192	java/lang/Exception
    //   1127	1142	1195	java/lang/Exception
    //   1147	1171	1195	java/lang/Exception
    //   1172	1182	1195	java/lang/Exception
  }

  protected void refreshData(int paramInt)
  {
    this.currentIndex = paramInt;
    ((QuestionDetailFragment)this.mContentFragments.get(paramInt)).refreshData((Exercises)this.cacheExcerciseList.get(paramInt), paramInt);
    Boolean localBoolean = (Boolean)this.isCollect.get(Integer.valueOf(paramInt));
    if ((localBoolean == null) || (!localBoolean.booleanValue()))
    {
      this.iv_collect_excer.setImageResource(2130837597);
      if (paramInt != -1 + this.mContentFragments.size());
    }
    switch (this.FromPage)
    {
    case 1:
    case 3:
    case 888:
    default:
    case 10:
      do
      {
        return;
        this.iv_collect_excer.setImageResource(2130837586);
        break;
      }
      while ((this.errExsList == null) || (this.errExsList.size() <= 0));
      label263: label281: int i;
      if (this.req == null)
      {
        this.req = new ExerciseIdList();
        this.req.setVersionName(this.mSP.getVersionName());
        this.req.setClientType(ToolsUtils.getSDK());
        this.req.setImei(ToolsUtils.getImei(this.mContext));
        this.req.setNet(ToolsUtils.getStringNetworkType(this.mContext));
        this.req.setExerciseId(new ArrayList());
        if (this.cacheId != null)
          break label407;
        this.cacheId = new ArrayList();
        i = this.errExsList.size();
        LogUtils.myLog("总共size=" + i);
      }
      for (int j = 0; ; j++)
      {
        if (j >= 8);
        label407: 
        do
        {
          if (this.cacheId.size() <= 0)
            break;
          this.batchIndex = (8 + this.batchIndex);
          this.req.setExerciseId(this.cacheId);
          this.req.setUserSubject(this.userSubject);
          this.req.setBrushType(Constants.BrushType.ERROR_BASKET_BRUSH);
          UrlFactory.getInstance().getExcerciseDetail().constructUrl(this, this.req, 0);
          return;
          this.req.getExerciseId().clear();
          break label263;
          this.cacheId.clear();
          break label281;
        }
        while (j + this.batchIndex > i - 1);
        String str = ((ErrorExercise)this.errExsList.get(j)).getExerciseId();
        this.cacheId.add(str);
      }
    case 9:
    case 12:
    }
    ReqBrushChapterGoNext localReqBrushChapterGoNext = new ReqBrushChapterGoNext();
    localReqBrushChapterGoNext.setVersionName(this.mSP.getVersionName());
    localReqBrushChapterGoNext.setClientType(ToolsUtils.getSDK());
    localReqBrushChapterGoNext.setImei(ToolsUtils.getImei(this.mContext));
    localReqBrushChapterGoNext.setNet(ToolsUtils.getStringNetworkType(this.mContext));
    localReqBrushChapterGoNext.setGetNextFlag(true);
    localReqBrushChapterGoNext.setUserSubject(this.userSubject);
    UrlFactory.getInstance().getBrushExcercise().constructUrl(this, localReqBrushChapterGoNext, 0);
  }

  protected void setContentView()
  {
    setContentView(2130903067);
  }

  protected void showPopCard()
  {
  }

  private class MyGridAdapter extends BaseAdapter
  {
    public MyGridAdapter()
    {
    }

    public int getCount()
    {
      if (QuestionDetailActivity.this.resultCode == 100)
      {
        QuestionDetailActivity.this.mAllFm.size();
        return QuestionDetailActivity.this.mAllFm.size();
      }
      if (QuestionDetailActivity.this.resultCode == 101)
      {
        (-1 + QuestionDetailActivity.this.listfm.size());
        return -1 + QuestionDetailActivity.this.listfm.size();
      }
      return QuestionDetailActivity.this.exerciseIdList.getExerciseId().size();
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
        paramView = View.inflate(QuestionDetailActivity.this.getApplicationContext(), 2130903175, null);
        localViewHolder = new ViewHolder(QuestionDetailActivity.this);
        ViewHolder.access$0(localViewHolder, (Button)paramView.findViewById(2131100490));
        ViewHolder.access$1(localViewHolder, (ImageView)paramView.findViewById(2131100491));
        paramView.setTag(localViewHolder);
        ViewHolder.access$2(localViewHolder).setOnClickListener(new OnClickListener(paramInt)
        {
          public void onClick(View paramView)
          {
            try
            {
              QuestionDetailActivity.this.dimissTestPop();
              QuestionDetailActivity.this.handler.sendEmptyMessageDelayed(21, 400L);
              QuestionDetailActivity.this.mViewPager.setCurrentItem(this.val$position);
              return;
            }
            catch (Exception localException)
            {
            }
          }
        });
        switch (QuestionDetailActivity.this.resultCode)
        {
        default:
          if ((QuestionDetailActivity.this.cacheExcerciseList == null) || (QuestionDetailActivity.this.cacheExcerciseList.size() <= 0))
            break;
          ViewHolder.access$2(localViewHolder).setText(paramInt + 1);
          ViewHolder.access$3(localViewHolder).setVisibility(8);
          Boolean localBoolean = (Boolean)QuestionDetailActivity.this.isHaveDone.get(Integer.valueOf(paramInt));
          if ((localBoolean == null) || (!localBoolean.booleanValue()))
            break label471;
          ViewHolder.access$2(localViewHolder).setBackgroundResource(2130837552);
          ViewHolder.access$2(localViewHolder).setTextColor(QuestionDetailActivity.this.getResources().getColor(2131230726));
        case 100:
        case 101:
        }
      }
      do
      {
        return paramView;
        localViewHolder = (ViewHolder)paramView.getTag();
        break;
        String str = (String)QuestionDetailActivity.this.currRight.get(Integer.valueOf(paramInt));
        ((Exercises)QuestionDetailActivity.this.cacheExcerciseList.get(paramInt));
        ViewHolder.access$2(localViewHolder).setText(paramInt + 1);
        if ((str != null) && (str.equals("true")))
        {
          ViewHolder.access$3(localViewHolder).setVisibility(8);
          ViewHolder.access$2(localViewHolder).setBackgroundResource(2130837552);
          ViewHolder.access$2(localViewHolder).setTextColor(QuestionDetailActivity.this.getResources().getColor(2131230726));
          return paramView;
        }
        ViewHolder.access$2(localViewHolder).setBackgroundResource(2130837551);
        ViewHolder.access$2(localViewHolder).setTextColor(QuestionDetailActivity.this.getResources().getColor(2131230727));
        return paramView;
      }
      while ((Exercises)QuestionDetailActivity.this.listData.get(paramInt) == null);
      ViewHolder.access$2(localViewHolder).setText(paramInt + 1);
      ViewHolder.access$2(localViewHolder).setBackgroundResource(2130837551);
      ViewHolder.access$2(localViewHolder).setTextColor(QuestionDetailActivity.this.getResources().getColor(2131230727));
      return paramView;
      label471: ViewHolder.access$2(localViewHolder).setBackgroundResource(2130837553);
      ViewHolder.access$2(localViewHolder).setTextColor(QuestionDetailActivity.this.getResources().getColor(2131230730));
      return paramView;
    }
  }

  class ViewHolder
  {
    private Button btn;
    private ImageView isRightIv;

    ViewHolder()
    {
    }
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.activity.QuestionDetailActivity
 * JD-Core Version:    0.6.0
 */