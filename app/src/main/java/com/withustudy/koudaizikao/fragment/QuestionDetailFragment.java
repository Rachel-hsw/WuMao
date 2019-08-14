package com.withustudy.koudaizikao.fragment;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import com.android.http.FileDownLoad;
import com.example.test.FlowLayout;
import com.google.gson.Gson;
import com.himamis.retex.renderer.android.LaTeXView;
import com.umeng.analytics.MobclickAgent;
import com.withustudy.koudaizikao.action.AbsSimpleImplUrlFactory;
import com.withustudy.koudaizikao.action.UrlFactory;
import com.withustudy.koudaizikao.activity.QuestionDetailActivity;
import com.withustudy.koudaizikao.activity.QuestionMoreComentActivity;
import com.withustudy.koudaizikao.activity.ShowPictureActivity;
import com.withustudy.koudaizikao.activity.SimpleAbsFragImpl;
import com.withustudy.koudaizikao.adapter.ExcerciseCommentAdapter;
import com.withustudy.koudaizikao.adapter.ExcerciseCommentAdapter.Infor;
import com.withustudy.koudaizikao.config.Constants;
import com.withustudy.koudaizikao.config.KouDaiSP;
import com.withustudy.koudaizikao.custom.MyListView;
import com.withustudy.koudaizikao.entity.BrushExcerciseBean;
import com.withustudy.koudaizikao.entity.Comments;
import com.withustudy.koudaizikao.entity.ErrorRate;
import com.withustudy.koudaizikao.entity.Exercises;
import com.withustudy.koudaizikao.entity.Explain;
import com.withustudy.koudaizikao.entity.Kpoint;
import com.withustudy.koudaizikao.entity.KpointDetail;
import com.withustudy.koudaizikao.entity.MyErrorRate;
import com.withustudy.koudaizikao.entity.Options;
import com.withustudy.koudaizikao.entity.PublishCommentBean;
import com.withustudy.koudaizikao.entity.Stem;
import com.withustudy.koudaizikao.entity.req.Comment;
import com.withustudy.koudaizikao.entity.req.ExerciseIdList;
import com.withustudy.koudaizikao.entity.req.ReqComment;
import com.withustudy.koudaizikao.entity.req.Subjects;
import com.withustudy.koudaizikao.entity.req.UserInfo;
import com.withustudy.koudaizikao.tools.LogUtils;
import com.withustudy.koudaizikao.tools.ProTools;
import com.withustudy.koudaizikao.tools.ToolsUtils;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class QuestionDetailFragment extends SimpleAbsFragImpl
  implements OnClickListener
{
  private static final int action_get_comment = 9;
  private static final int action_get_current_excer_detail = 10;
  private static final int action_id_hidden_inputsoft = 6;
  private static final int action_pubish_comment = 4;
  private static final int action_publish_fail = 8;
  public static final int replay = 7;
  private QuestionDetailActivity activity;
  private BrushExcerciseBean brushExcerciseBean;
  private Button btn_all_tucao;
  private Button btn_confirm;
  private Button btn_publish;
  private List<Comment> cacheComment;
  private String category;
  private MyListView comment_lv;
  private List<String> correntAnser;
  private int currentIndex;
  private TextView doneTv;
  private EditText[] editText;
  private EditText edt0;
  private EditText edt1;
  private EditText edt2;
  private EditText edt3;
  private EditText edt4;
  private EditText edt5;
  private EditText et_publish;
  private ExcerciseCommentAdapter exAdapter;
  private int excerciseType = -1;
  private Exercises exercises;
  private FlowLayout fl_jiangjie_content;
  private FlowLayout fl_jiangjie_point;
  private FlowLayout fl_stem;
  private FlowLayout futu;
  private LinearLayout futu_container;
  private Handler handler = new Handler()
  {
    public void handleMessage(Message paramMessage)
    {
      switch (paramMessage.what)
      {
      case 2:
      case 3:
      case 4:
      case 5:
      default:
        return;
      case 7:
        QuestionDetailFragment.this.manager.toggleSoftInput(0, 2);
        QuestionDetailFragment.this.infor = ((Infor)paramMessage.obj);
        return;
      case 10:
        if ((QuestionDetailFragment.this.tv_total_tucao == null) || (QuestionDetailFragment.this.btn_all_tucao == null))
          QuestionDetailFragment.this.initExplainView();
        Comments localComments = QuestionDetailFragment.this.exercises.getComments();
        int i = 0;
        if (localComments != null)
          i = localComments.getTotalNum();
        String str1 = "共" + i + "条吐槽";
        QuestionDetailFragment.this.tv_total_tucao.setText(str1);
        String str2 = "所有吐槽" + i + "条";
        QuestionDetailFragment.this.btn_all_tucao.setText(str2);
        LogUtils.myLog("获取题目的详情更新UI ExcerciseCommentAdapter size=" + QuestionDetailFragment.this.cacheComment.size());
        if (QuestionDetailFragment.this.exAdapter == null)
        {
          QuestionDetailFragment.this.exAdapter = new ExcerciseCommentAdapter(QuestionDetailFragment.this.getActivity(), QuestionDetailFragment.this.cacheComment, QuestionDetailFragment.this.handler);
          QuestionDetailFragment.this.comment_lv.setAdapter(QuestionDetailFragment.this.exAdapter);
          return;
        }
        QuestionDetailFragment.this.exAdapter.notifyDataSetChanged();
        return;
      case 1:
        QuestionDetailFragment.this.activity.goNext();
        return;
      case 6:
        QuestionDetailFragment.this.manager.hideSoftInputFromWindow(QuestionDetailFragment.this.getActivity().getCurrentFocus().getWindowToken(), 2);
        QuestionDetailFragment.this.et_publish.setText("");
        ExerciseIdList localExerciseIdList = new ExerciseIdList();
        localExerciseIdList.setClientType(ToolsUtils.getSDK());
        localExerciseIdList.setImei(ToolsUtils.getImei(QuestionDetailFragment.this.mContext));
        localExerciseIdList.setNet(ToolsUtils.getStringNetworkType(QuestionDetailFragment.this.mContext));
        localExerciseIdList.setVersionName(QuestionDetailFragment.this.mSP.getVersionName());
        ArrayList localArrayList = new ArrayList();
        localExerciseIdList.setExerciseId(localArrayList);
        localArrayList.add(QuestionDetailFragment.this.exercises.getExerciseId());
        localExerciseIdList.setUserSubject(QuestionDetailFragment.this.activity.userSubject);
        UrlFactory.getInstance().getExcerciseDetail().constructUrl(QuestionDetailFragment.this, localExerciseIdList, 10);
        return;
      case 8:
        Toast.makeText(QuestionDetailFragment.this.getActivity(), "发布失败", 0).show();
        return;
      case 9:
      }
      Exercises localExercises = (Exercises)paramMessage.obj;
      QuestionDetailFragment.this.getComement(localExercises);
    }
  };
  private boolean haveDone = false;
  private ImageButton ib_dianzan;
  private Infor infor;
  private boolean isCurrRight = false;
  private boolean isSimu;
  private LinearLayout itemA;
  private LinearLayout itemB;
  private LinearLayout itemC;
  private LinearLayout itemD;
  private LinearLayout itemE;
  private LinearLayout itemF;
  private ImageView iv_done_evaluate;
  private ImageView[] iv_lines;
  private ImageView iva;
  private ImageView ivb;
  private ImageView ivc;
  private ImageView ivd;
  private ImageView ive;
  private TextView k_my_tv_error;
  private TextView k_tv_error;
  private LinearLayout layout_question_detail;
  private LinearLayout layout_question_detail_edit;
  private TextView lianxi;
  private LinearLayout ll_detail;
  private LinearLayout ll_excer_jiangjie;
  private LinearLayout ll_item_container2;
  private LinearLayout ll_label_content;
  private LinearLayout ll_line;
  private LinearLayout ll_notification;
  private InputMethodManager manager;
  private HashMap<Integer, String> opEdit;
  private FlowLayout opa_fl;
  private ImageView opa_ib;
  private FlowLayout opb_fl;
  private ImageView opb_ib;
  private FlowLayout opc_fl;
  private ImageView opc_ib;
  private FlowLayout opd_fl;
  private ImageView opd_ib;
  private FlowLayout ope_fl;
  private ImageView ope_ib;
  private FlowLayout opf_fl;
  private ImageView opf_ib;
  private PublishCommentBean publishCommentBean;
  private TextView q_tv_jiangjie_content;
  private TextView q_tv_jiangjie_title;
  private TextView q_tv_point;
  private LinearLayout q_yuyin_ll;
  private TextView questionNum;
  private TextView qustion_title;
  private View rootView;
  private ScrollView scrollView;
  private String subjectId;
  private int t;
  private View tk0;
  private LinearLayout tk_layout;
  private LinearLayout tk_ll2;
  private TextView tv_anser_label;
  private TextView tv_done_evaluate1;
  private TextView tv_done_evaluate2;
  private TextView tv_exp_content;
  private TextView tv_exp_level;
  private TextView tv_exp_title;
  private TextView tv_total_tucao;
  private TextView typeLabel;
  private double userScore = 0.0D;
  private EditText wenda_et;
  private TextView yuce;
  private TextView zhengti;
  private ImageView zhishline;

  private void addFutuView(Stem paramStem)
  {
    List localList = paramStem.getFigure();
    Iterator localIterator;
    if ((localList != null) && (localList.size() > 0))
    {
      this.futu_container.setVisibility(0);
      localIterator = localList.iterator();
    }
    while (true)
    {
      if (!localIterator.hasNext())
        return;
      String str = (String)localIterator.next();
      ImageView localImageView = (ImageView)View.inflate(getActivity(), 2130903131, null);
      this.futu_container.addView(localImageView);
      this.mFileDownLoad.downLoadImage(str, localImageView);
      localImageView.setOnClickListener(new OnClickListener(str)
      {
        public void onClick(View paramView)
        {
          Bundle localBundle = new Bundle();
          localBundle.putInt("currentitem_key", 0);
          ArrayList localArrayList = new ArrayList();
          localArrayList.add(this.val$string);
          localBundle.putSerializable("image_name", (Serializable)localArrayList);
          QuestionDetailFragment.this.startNewActivity(ShowPictureActivity.class, false, localBundle);
        }
      });
    }
  }

  private void btnConfirm()
  {
    if (!this.isSimu);
    switch (this.excerciseType)
    {
    default:
      if (!this.wenda_et.isEnabled())
        break;
      this.activity.isHaveDone.put(Integer.valueOf(this.currentIndex), Boolean.valueOf(true));
      this.wenda_et.setEnabled(false);
      this.isCurrRight = true;
      this.userScore = this.exercises.getScore();
      this.activity.currRight.put(Integer.valueOf(this.currentIndex), this.isCurrRight);
      LogUtils.myLog("插入currentInx=" + this.currentIndex);
      this.activity.insertToDb(this.exercises, this.opEdit, this.isCurrRight, this.userScore, this.exercises.getScore());
      initExplainView();
      initExplainData();
      initListener();
    case 0:
    case 4:
    case 1:
    case 2:
    case 3:
      do
        return;
      while (!isEnable());
      this.haveDone = true;
      this.activity.isHaveDone.put(Integer.valueOf(this.currentIndex), Boolean.valueOf(true));
      LogUtils.myLog("插入currentInx=" + this.currentIndex);
      showRightOrError();
      this.activity.insertToDb(this.exercises, this.opEdit, this.isCurrRight, this.userScore, this.exercises.getScore());
      return;
    case 5:
    }
    LogUtils.myLog("插入currentInx=" + this.currentIndex);
    checkTk();
    this.activity.insertToDb(this.exercises, this.opEdit, this.isCurrRight, this.userScore, this.exercises.getScore());
  }

  private void changePressBg(int paramInt)
  {
    Boolean localBoolean;
    if ((this.excerciseType == 2) || (this.excerciseType == 1) || (this.excerciseType == 3))
    {
      localBoolean = Boolean.valueOf(getItemState(paramInt));
      switch (paramInt)
      {
      default:
        if (isChoice())
        {
          this.btn_confirm.setEnabled(true);
          label87: if (!this.isSimu)
            break;
          if (!isChoice())
            break label733;
          this.activity.isHaveDone.put(Integer.valueOf(this.currentIndex), Boolean.valueOf(true));
          label123: checkDXRight();
        }
      case 0:
      case 1:
      case 2:
      case 3:
      case 4:
      case 5:
      }
    }
    label733: 
    do
    {
      return;
      if ((localBoolean == null) || (!localBoolean.booleanValue()))
      {
        if (this.opa_ib == null)
          break;
        this.opa_ib.setBackgroundResource(2130837504);
        setFlChildTexFont(this.opa_fl, true);
        this.opEdit.put(Integer.valueOf(paramInt), "A");
        break;
      }
      if (this.opa_ib == null)
        break;
      this.opa_ib.setBackgroundResource(2130837506);
      setFlChildTexFont(this.opa_fl, false);
      this.opEdit.put(Integer.valueOf(paramInt), "");
      break;
      if ((localBoolean == null) || (!localBoolean.booleanValue()))
      {
        if (this.opb_ib == null)
          break;
        this.opb_ib.setBackgroundResource(2130837516);
        setFlChildTexFont(this.opb_fl, true);
        this.opEdit.put(Integer.valueOf(paramInt), "B");
        break;
      }
      if (this.opb_ib == null)
        break;
      this.opb_ib.setBackgroundResource(2130837518);
      setFlChildTexFont(this.opb_fl, false);
      this.opEdit.put(Integer.valueOf(paramInt), "");
      break;
      if ((localBoolean == null) || (!localBoolean.booleanValue()))
      {
        if (this.opc_ib == null)
          break;
        this.opc_ib.setBackgroundResource(2130837603);
        setFlChildTexFont(this.opc_fl, true);
        this.opEdit.put(Integer.valueOf(paramInt), "C");
        break;
      }
      if (this.opc_ib == null)
        break;
      this.opc_ib.setBackgroundResource(2130837605);
      setFlChildTexFont(this.opc_fl, false);
      this.opEdit.put(Integer.valueOf(paramInt), "");
      break;
      if ((localBoolean == null) || (!localBoolean.booleanValue()))
      {
        if (this.opd_ib == null)
          break;
        this.opd_ib.setBackgroundResource(2130837615);
        setFlChildTexFont(this.opd_fl, true);
        this.opEdit.put(Integer.valueOf(paramInt), "D");
        break;
      }
      if (this.opd_ib == null)
        break;
      this.opd_ib.setBackgroundResource(2130837617);
      setFlChildTexFont(this.opd_fl, false);
      this.opEdit.put(Integer.valueOf(paramInt), "");
      break;
      if ((localBoolean == null) || (!localBoolean.booleanValue()))
      {
        if (this.ope_ib == null)
          break;
        this.ope_ib.setBackgroundResource(2130837628);
        setFlChildTexFont(this.ope_fl, true);
        this.opEdit.put(Integer.valueOf(paramInt), "E");
        break;
      }
      if (this.ope_ib == null)
        break;
      this.ope_ib.setBackgroundResource(2130837630);
      setFlChildTexFont(this.ope_fl, false);
      this.opEdit.put(Integer.valueOf(paramInt), "");
      break;
      if ((localBoolean == null) || (!localBoolean.booleanValue()))
      {
        if (this.opf_ib == null)
          break;
        this.opf_ib.setBackgroundResource(2130837644);
        setFlChildTexFont(this.opf_fl, true);
        this.opEdit.put(Integer.valueOf(paramInt), "F");
        break;
      }
      if (this.opf_ib == null)
        break;
      this.opf_ib.setBackgroundResource(2130837646);
      setFlChildTexFont(this.opf_fl, false);
      this.opEdit.put(Integer.valueOf(paramInt), "");
      break;
      this.btn_confirm.setEnabled(false);
      break label87;
      this.activity.isHaveDone.put(Integer.valueOf(this.currentIndex), Boolean.valueOf(false));
      break label123;
    }
    while ((this.excerciseType != 0) && (this.excerciseType != 4));
    if (this.isSimu)
    {
      resetColorBg();
      resetFont();
      resetCheck();
    }
    int i;
    Iterator localIterator;
    switch (paramInt)
    {
    default:
      this.haveDone = true;
      this.activity.isHaveDone.put(Integer.valueOf(this.currentIndex), Boolean.valueOf(true));
      if (!this.isSimu)
        break;
      i = 0;
      localIterator = this.opEdit.keySet().iterator();
    case 0:
    case 1:
    case 2:
    case 3:
    case 4:
    case 5:
      while (true)
      {
        if (!localIterator.hasNext())
        {
          isRight(i);
          this.activity.currRight.put(Integer.valueOf(this.currentIndex), this.isCurrRight);
          LogUtils.myLog("插入currentInx=" + this.currentIndex);
          this.activity.insertToDb(this.exercises, this.opEdit, this.isCurrRight, this.userScore, this.exercises.getScore());
          this.handler.sendEmptyMessageDelayed(1, 800L);
          return;
          if (this.opa_ib == null)
            break;
          this.opa_ib.setBackgroundResource(2130837504);
          setFlChildTexFont(this.opa_fl, true);
          this.opEdit.put(Integer.valueOf(paramInt), "A");
          break;
          if (this.opb_ib == null)
            break;
          this.opb_ib.setBackgroundResource(2130837516);
          setFlChildTexFont(this.opb_fl, true);
          this.opEdit.put(Integer.valueOf(paramInt), "B");
          break;
          if (this.opc_ib == null)
            break;
          this.opc_ib.setBackgroundResource(2130837603);
          setFlChildTexFont(this.opc_fl, true);
          this.opEdit.put(Integer.valueOf(paramInt), "C");
          break;
          if (this.opd_ib == null)
            break;
          this.opd_ib.setBackgroundResource(2130837615);
          setFlChildTexFont(this.opd_fl, true);
          this.opEdit.put(Integer.valueOf(paramInt), "D");
          break;
          if (this.ope_ib == null)
            break;
          this.ope_ib.setBackgroundResource(2130837628);
          setFlChildTexFont(this.ope_fl, true);
          this.opEdit.put(Integer.valueOf(paramInt), "E");
          break;
          if (this.opf_ib == null)
            break;
          this.opf_ib.setBackgroundResource(2130837644);
          setFlChildTexFont(this.opf_fl, true);
          this.opEdit.put(Integer.valueOf(paramInt), "F");
          break;
        }
        Integer localInteger = (Integer)localIterator.next();
        String str = (String)this.opEdit.get(localInteger);
        if ((str == null) || (str.equals("")))
          continue;
        i++;
      }
    }
    LogUtils.myLog("插入currentInx=" + this.currentIndex);
    showRightOrError();
    this.activity.insertToDb(this.exercises, this.opEdit, this.isCurrRight, this.userScore, this.exercises.getScore());
  }

  private void checkDXRight()
  {
    int i = 0;
    Iterator localIterator = this.opEdit.keySet().iterator();
    while (true)
    {
      if (!localIterator.hasNext())
      {
        isRight(i);
        this.activity.currRight.put(Integer.valueOf(this.currentIndex), this.isCurrRight);
        this.activity.insertToDb(this.exercises, this.opEdit, this.isCurrRight, this.userScore, this.exercises.getScore());
        LogUtils.myLog("插入currentInx=" + this.currentIndex);
        return;
      }
      Integer localInteger = (Integer)localIterator.next();
      String str = (String)this.opEdit.get(localInteger);
      if ((str == null) || (str.equals("")))
        continue;
      i++;
    }
  }

  private void checkTk()
  {
    try
    {
      List localList = this.exercises.getCorrectAnswer();
      int i = localList.size();
      int[] arrayOfInt = { 2130837822, 2130837824, 2130837826, 2130837828, 2130837830, 2130837832, 2130837834, 2130837836, 2130837838, 2130837815, 2130837817, 2130837819, 2130837821 };
      this.tv_anser_label.setVisibility(0);
      int j = 0;
      int k = 0;
      while (true)
      {
        int m;
        int n;
        if (this.isSimu)
        {
          m = 0;
          n = 0;
          label136: if (n >= i)
            this.userScore = (this.exercises.getScore() * (1.0D * m / i));
        }
        else
        {
          this.activity.currRight.put(Integer.valueOf(this.currentIndex), this.isCurrRight);
          this.btn_confirm.setVisibility(8);
          this.haveDone = true;
          this.activity.isHaveDone.put(Integer.valueOf(this.currentIndex), Boolean.valueOf(true));
          showExplain();
          return;
          label238: String str1 = (String)localList.get(k);
          String str2 = this.editText[k].getText().toString().trim();
          if ((str2 == null) || (str2.equals("")))
          {
            this.editText[k].setTextColor(getResources().getColor(2131230740));
            this.editText[k].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(arrayOfInt[k]), null, getResources().getDrawable(2130837641), null);
            showTKannswer(k);
          }
          while (true)
          {
            this.editText[k].setEnabled(false);
            k++;
            break;
            if (str1.equals(str2))
            {
              j++;
              this.editText[k].setTextColor(getResources().getColor(2131230739));
              this.editText[k].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(arrayOfInt[k]), null, getResources().getDrawable(2130837779), null);
              continue;
            }
            j++;
            this.editText[k].setTextColor(getResources().getColor(2131230740));
            this.editText[k].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(arrayOfInt[k]), null, getResources().getDrawable(2130837641), null);
            showTKannswer(k);
          }
        }
        int i1;
        do
        {
          String str5 = (String)localList.get(i1);
          String str6 = this.editText[i1].getText().toString().trim();
          if ((str6 == null) || (str6.equals("")))
          {
            this.isCurrRight = false;
            break;
          }
          if (str5.equals(str6))
          {
            this.isCurrRight = true;
            i1++;
          }
          else
          {
            this.isCurrRight = false;
            break;
            do
            {
              this.isCurrRight = false;
              break;
              String str3 = (String)localList.get(n);
              String str4 = this.editText[n].getText().toString().trim();
              if ((str4 != null) && (!str4.equals("")))
              {
                boolean bool = str3.equals(str4);
                if (bool)
                  m++;
              }
              n++;
              break label136;
              if (k < i)
                break label238;
            }
            while (j != i);
            i1 = 0;
          }
        }
        while (i1 < i);
      }
    }
    catch (Exception localException)
    {
    }
  }

  private void checkTk1()
  {
    try
    {
      if ((this.ll_item_container2 != null) && (this.ll_item_container2.getChildCount() > 0))
        this.ll_item_container2.removeAllViews();
      List localList = this.exercises.getCorrectAnswer();
      int i;
      int[] arrayOfInt;
      int j;
      if (localList != null)
      {
        i = localList.size();
        arrayOfInt = new int[] { 2130837822, 2130837824, 2130837826, 2130837828, 2130837830, 2130837832, 2130837834, 2130837836, 2130837838, 2130837815, 2130837817, 2130837819, 2130837821 };
        this.tv_anser_label.setVisibility(0);
        j = 0;
      }
      else
      {
        this.btn_confirm.setVisibility(8);
        showExplain();
        return;
      }
      label443: 
      while (true)
      {
        String str1 = (String)localList.get(j);
        String str2 = this.editText[j].getText().toString().trim();
        if ((this.editText != null) && (this.editText.length > 0))
        {
          if ((str2 == null) || (str2.equals("")))
          {
            this.editText[j].setTextColor(getResources().getColor(2131230740));
            this.editText[j].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(arrayOfInt[j]), null, getResources().getDrawable(2130837641), null);
            showTKannswer(j);
          }
          while (true)
          {
            this.editText[j].setEnabled(false);
            break;
            if (str1.equals(str2))
            {
              this.editText[j].setTextColor(getResources().getColor(2131230739));
              this.editText[j].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(arrayOfInt[j]), null, getResources().getDrawable(2130837779), null);
              continue;
            }
            this.editText[j].setTextColor(getResources().getColor(2131230740));
            this.editText[j].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(arrayOfInt[j]), null, getResources().getDrawable(2130837641), null);
            showTKannswer(j);
          }
        }
        while (true)
        {
          if (j < i)
            break label443;
          break;
          j++;
        }
      }
    }
    catch (Exception localException)
    {
    }
  }

  private void dianZan()
  {
  }

  private void getComement(Exercises paramExercises)
  {
    List localList;
    if (paramExercises != null)
    {
      Comments localComments = paramExercises.getComments();
      if (localComments != null)
      {
        localList = localComments.getComment();
        if ((localList != null) && (localList.size() > 0))
        {
          if (this.cacheComment != null)
            break label71;
          this.cacheComment = new ArrayList();
        }
      }
    }
    while (true)
    {
      this.cacheComment.addAll(localList);
      this.handler.sendEmptyMessage(10);
      return;
      label71: this.cacheComment.clear();
    }
  }

  private boolean getItemState(int paramInt)
  {
    int i = 1;
    String str = (String)this.opEdit.get(Integer.valueOf(paramInt));
    if ((str == null) || (str.equals("")))
      i = 0;
    do
      return i;
    while ((str.equals("A")) || (str.equals("B")) || (str.equals("C")) || (str.equals("D")) || (str.equals("E")) || (!str.equals("F")));
    return i;
  }

  private void init()
  {
    this.category = this.exercises.getCategory();
    try
    {
      this.excerciseType = ((Integer)Constants.typeToIndex.get(this.category)).intValue();
      label31: this.fl_stem = ((FlowLayout)this.rootView.findViewById(2131100184));
      this.futu_container = ((LinearLayout)this.rootView.findViewById(2131100185));
      Iterator localIterator2;
      if (this.exercises != null)
      {
        Stem localStem = this.exercises.getStem();
        if (localStem != null)
        {
          if ((localStem.getText() != null) && (!localStem.getText().equals("")))
          {
            List localList4 = scaningString(this.exercises.getStem().getText(), false);
            this.fl_stem.setOption(false);
            localIterator2 = localList4.iterator();
            if (localIterator2.hasNext())
              break label401;
          }
          addFutuView(localStem);
        }
      }
      optionToViews();
      List localList1 = this.exercises.getOptions();
      if ((localList1 != null) && (localList1.size() > 0))
      {
        int j = localList1.size();
        Options localOptions = (Options)localList1.get(0);
        if ((localOptions.getContent() != null) && (!localOptions.getContent().equals("")))
          switch (j)
          {
          default:
          case 1:
          case 2:
          case 3:
          case 4:
          case 5:
          case 6:
          }
      }
      List localList2;
      int i;
      while (true)
      {
        localList2 = this.exercises.getOptions();
        if ((localList2 != null) && (localList2.size() > 0))
        {
          i = 0;
          if (i < localList2.size())
            break label611;
        }
        switch (this.excerciseType)
        {
        case 0:
        case 1:
        case 2:
        case 3:
        case 4:
        case 5:
        case 6:
        case 7:
        case 8:
        case 9:
        case 10:
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
        }
        return;
        label401: View localView2 = (View)localIterator2.next();
        this.fl_stem.addView(localView2);
        break;
        this.itemA.setVisibility(0);
        continue;
        this.itemA.setVisibility(0);
        this.itemB.setVisibility(0);
        continue;
        this.itemA.setVisibility(0);
        this.itemB.setVisibility(0);
        this.itemC.setVisibility(0);
        continue;
        this.itemA.setVisibility(0);
        this.itemB.setVisibility(0);
        this.itemC.setVisibility(0);
        this.itemD.setVisibility(0);
        continue;
        this.itemA.setVisibility(0);
        this.itemB.setVisibility(0);
        this.itemC.setVisibility(0);
        this.itemD.setVisibility(0);
        this.itemE.setVisibility(0);
        continue;
        this.itemA.setVisibility(0);
        this.itemB.setVisibility(0);
        this.itemC.setVisibility(0);
        this.itemD.setVisibility(0);
        this.itemE.setVisibility(0);
        this.itemF.setVisibility(0);
      }
      label611: String str = ((Options)localList2.get(i)).getContent();
      Iterator localIterator1;
      if ((str != null) && (!str.equals("")))
      {
        List localList3 = scaningString(str, true);
        LogUtils.myLog("questionDetailFragment optionViews size=" + localList3.size());
        localIterator1 = localList3.iterator();
      }
      while (true)
      {
        if (!localIterator1.hasNext())
        {
          i++;
          break;
        }
        View localView1 = (View)localIterator1.next();
        switch (i)
        {
        default:
          break;
        case 0:
          this.opa_fl.setOption(true);
          this.opa_fl.addView(localView1);
          break;
        case 1:
          this.opb_fl.setOption(true);
          this.opb_fl.addView(localView1);
          break;
        case 2:
          this.opc_fl.setOption(true);
          this.opc_fl.addView(localView1);
          break;
        case 3:
          this.opd_fl.setOption(true);
          this.opd_fl.addView(localView1);
          break;
        case 4:
          this.ope_fl.setOption(true);
          this.ope_fl.addView(localView1);
          break;
        case 5:
          this.opf_fl.setOption(true);
          this.opf_fl.addView(localView1);
        }
      }
    }
    catch (Exception localException)
    {
      break label31;
    }
  }

  private void initExplainData()
  {
    int i;
    int j;
    double d1;
    label74: int k;
    int m;
    double d2;
    label128: Iterator localIterator3;
    label362: label492: Iterator localIterator2;
    label419: label562: label572: Iterator localIterator1;
    if (this.isCurrRight)
    {
      this.tv_done_evaluate1.setText("其实吧~这道题是酱紫滴");
      this.iv_done_evaluate.setBackgroundResource(2130837684);
      MyErrorRate localMyErrorRate = this.exercises.getMyErrorRate();
      i = 0;
      j = 0;
      if (localMyErrorRate != null)
      {
        j = localMyErrorRate.getErrorNum();
        i = localMyErrorRate.getTotalNum();
      }
      if (!this.isCurrRight)
        break label800;
      d1 = 1.0D * j / (i + 1);
      ErrorRate localErrorRate = this.exercises.getErrorRate();
      k = 0;
      m = 0;
      if (localErrorRate != null)
      {
        k = localErrorRate.getErrorNum();
        m = localErrorRate.getTotalNum();
      }
      if (!this.isCurrRight)
        break label817;
      d2 = 1.0D * k / (m + 1);
      LogUtils.myLog("tempErrRate-----" + d2);
      LogUtils.myLog("tempMyErrRate-----" + d1);
      double d3 = d2 * 100.0D;
      String str1 = new DecimalFormat("0.0").format(d3);
      this.k_tv_error.setText(str1 + "%");
      double d4 = d1 * 100.0D;
      String str2 = new DecimalFormat("0.0").format(d4);
      this.k_my_tv_error.setText(str2 + "%");
      KpointDetail localKpointDetail = this.exercises.getKpointDetail();
      if (localKpointDetail == null)
        break label1035;
      String str5 = localKpointDetail.getExplainText();
      if ((this.fl_jiangjie_point == null) || (this.fl_jiangjie_point.getChildCount() <= 0))
      {
        List localList3 = scanKnow("  " + str5);
        if ((localList3 != null) && (localList3.size() > 0))
        {
          localIterator3 = localList3.iterator();
          if (localIterator3.hasNext())
            break label835;
          this.fl_jiangjie_point.setVisibility(0);
        }
      }
      this.tv_exp_content.setVisibility(8);
      String str6 = localKpointDetail.getGraspRequire();
      if (str6 == null)
        break label859;
      this.tv_exp_level.setText(str6);
      this.tv_exp_level.setVisibility(8);
      Kpoint localKpoint = localKpointDetail.getKpoint();
      if (localKpoint != null)
      {
        String str7 = localKpoint.getName();
        this.tv_exp_title.setText(str7);
      }
      switch ((int)localKpointDetail.getGraspLevel())
      {
      default:
        Explain localExplain = this.exercises.getExplain();
        if (localExplain != null)
        {
          List localList1 = localExplain.getFigure();
          if ((this.ll_excer_jiangjie == null) || (this.ll_excer_jiangjie.getChildCount() <= 0))
          {
            if ((localList1 == null) || (localList1.size() <= 0))
              break label1117;
            this.ll_excer_jiangjie.setVisibility(0);
            localIterator2 = localList1.iterator();
            if (localIterator2.hasNext())
              break;
          }
          else
          {
            String str3 = localExplain.getText();
            if ((this.fl_jiangjie_content == null) || (this.fl_jiangjie_content.getChildCount() <= 0))
            {
              List localList2 = scanKnow("  " + str3);
              if ((localList2 != null) && (localList2.size() > 0))
              {
                localIterator1 = localList2.iterator();
                label644: if (localIterator1.hasNext())
                  break label1129;
              }
            }
            this.q_tv_jiangjie_content.setVisibility(8);
            this.q_yuyin_ll.setVisibility(8);
          }
        }
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
      Message localMessage = this.handler.obtainMessage();
      localMessage.what = 9;
      localMessage.obj = this.exercises;
      this.handler.sendMessage(localMessage);
      return;
      HashMap localHashMap = this.activity.isHaveDone;
      if ((localHashMap == null) || (localHashMap.get(Integer.valueOf(this.currentIndex)) == null))
      {
        this.tv_done_evaluate1.setText("其实吧~这道题是酱紫滴");
        this.iv_done_evaluate.setBackgroundResource(2130837684);
      }
      while (true)
      {
        LogUtils.myLog("isHaveDone ?=" + localHashMap);
        break;
        this.tv_done_evaluate1.setText(" 呜呜呜~答错了!");
        this.iv_done_evaluate.setBackgroundResource(2130837619);
      }
      label800: d1 = (1.0D + 1.0D * j) / (i + 1);
      break label74;
      label817: d2 = (1.0D + 1.0D * k) / (m + 1);
      break label128;
      label835: View localView2 = (View)localIterator3.next();
      this.fl_jiangjie_point.addView(localView2);
      break label362;
      label859: this.tv_exp_level.setVisibility(0);
      break label419;
      this.iva.setBackgroundResource(2130837660);
      break label492;
      this.iva.setBackgroundResource(2130837660);
      this.ivb.setBackgroundResource(2130837660);
      break label492;
      this.iva.setBackgroundResource(2130837660);
      this.ivb.setBackgroundResource(2130837660);
      this.ivc.setBackgroundResource(2130837660);
      break label492;
      this.iva.setBackgroundResource(2130837660);
      this.ivb.setBackgroundResource(2130837660);
      this.ivc.setBackgroundResource(2130837660);
      this.ivd.setBackgroundResource(2130837660);
      break label492;
      this.iva.setBackgroundResource(2130837660);
      this.ivb.setBackgroundResource(2130837660);
      this.ivc.setBackgroundResource(2130837660);
      this.ivd.setBackgroundResource(2130837660);
      this.ive.setBackgroundResource(2130837660);
      break label492;
      label1035: this.tv_exp_content.setVisibility(8);
      break label492;
      String str4 = (String)localIterator2.next();
      ImageView localImageView = (ImageView)View.inflate(getActivity(), 2130903131, null);
      this.ll_excer_jiangjie.addView(localImageView);
      this.mFileDownLoad.downLoadImage(str4, localImageView);
      4 local4 = new OnClickListener(str4)
      {
        public void onClick(View paramView)
        {
          Bundle localBundle = new Bundle();
          localBundle.putInt("currentitem_key", 0);
          ArrayList localArrayList = new ArrayList();
          localArrayList.add(this.val$string);
          localBundle.putSerializable("image_name", (Serializable)localArrayList);
          QuestionDetailFragment.this.startNewActivity(ShowPictureActivity.class, false, localBundle);
        }
      };
      localImageView.setOnClickListener(local4);
      break label562;
      label1117: this.ll_excer_jiangjie.setVisibility(8);
      break label572;
      label1129: View localView1 = (View)localIterator1.next();
      this.fl_jiangjie_content.addView(localView1);
      break label644;
      this.q_tv_jiangjie_content.setVisibility(8);
      this.q_tv_jiangjie_title.setVisibility(8);
      this.zhishline.setVisibility(8);
    }
  }

  private void initExplainListen()
  {
    this.ib_dianzan.setOnClickListener(this);
    this.btn_publish.setOnClickListener(this);
    this.btn_all_tucao.setOnClickListener(this);
  }

  private void initExplainView()
  {
    this.iva = ((ImageView)this.rootView.findViewById(2131100229));
    this.ivb = ((ImageView)this.rootView.findViewById(2131100230));
    this.ivc = ((ImageView)this.rootView.findViewById(2131100231));
    this.ivd = ((ImageView)this.rootView.findViewById(2131100232));
    this.ive = ((ImageView)this.rootView.findViewById(2131100233));
    this.fl_jiangjie_content = ((FlowLayout)this.rootView.findViewById(2131100215));
    this.fl_jiangjie_content.setOption(true);
    this.fl_jiangjie_point = ((FlowLayout)this.rootView.findViewById(2131100222));
    this.fl_jiangjie_point.setOption(true);
    this.q_tv_jiangjie_title = ((TextView)this.rootView.findViewById(2131100214));
    this.q_tv_point = ((TextView)this.rootView.findViewById(2131100219));
    this.zhishline = ((ImageView)this.rootView.findViewById(2131100213));
    this.k_my_tv_error = ((TextView)this.rootView.findViewById(2131100228));
    this.q_yuyin_ll = ((LinearLayout)this.rootView.findViewById(2131100224));
    this.ll_excer_jiangjie = ((LinearLayout)this.rootView.findViewById(2131100216));
    this.k_tv_error = ((TextView)this.rootView.findViewById(2131100227));
    this.tv_done_evaluate1 = ((TextView)this.rootView.findViewById(2131100210));
    this.iv_done_evaluate = ((ImageView)this.rootView.findViewById(2131100211));
    this.q_tv_jiangjie_content = ((TextView)this.rootView.findViewById(2131100217));
    this.tv_exp_title = ((TextView)this.rootView.findViewById(2131100220));
    this.tv_exp_level = ((TextView)this.rootView.findViewById(2131100221));
    this.tv_exp_content = ((TextView)this.rootView.findViewById(2131100223));
    this.tv_total_tucao = ((TextView)this.rootView.findViewById(2131100234));
    this.btn_all_tucao = ((Button)this.rootView.findViewById(2131100236));
    this.btn_all_tucao.setOnClickListener(this);
    this.ib_dianzan = ((ImageButton)this.rootView.findViewById(2131100237));
    this.et_publish = ((EditText)this.rootView.findViewById(2131100108));
    this.btn_publish = ((Button)this.rootView.findViewById(2131100238));
    this.btn_publish.setOnClickListener(this);
    this.layout_question_detail = ((LinearLayout)this.rootView.findViewById(2131100207));
    this.ll_notification.setVisibility(0);
    this.btn_confirm.setVisibility(8);
    this.ll_detail.setVisibility(0);
    this.layout_question_detail_edit.setVisibility(0);
  }

  private void initTk()
  {
    try
    {
      this.tk0 = View.inflate(getActivity(), 2130903178, null);
      this.tk_layout.addView(this.tk0);
      this.tk_layout.setVisibility(0);
      this.tv_anser_label = ((TextView)this.tk0.findViewById(2131100496));
      this.tv_anser_label.setVisibility(8);
      this.tk_ll2 = ((LinearLayout)this.tk0.findViewById(2131100498));
      this.tk_ll2.setVisibility(8);
      this.ll_item_container2 = ((LinearLayout)this.tk0.findViewById(2131100500));
      this.ll_item_container2.setVisibility(8);
      int[] arrayOfInt = { 2130837822, 2130837824, 2130837826, 2130837828, 2130837830, 2130837832, 2130837834, 2130837836, 2130837838, 2130837815, 2130837817, 2130837819, 2130837821 };
      LinearLayout localLinearLayout = (LinearLayout)this.tk0.findViewById(2131100497);
      localLinearLayout.setVisibility(0);
      this.editText = new EditText[2 * this.t];
      this.iv_lines = new ImageView[2 * this.t];
      int i = 0;
      if (i >= this.t);
      for (int j = 0; ; j++)
      {
        if (j >= this.editText.length)
        {
          return;
          View localView = View.inflate(getActivity(), 2130903177, null);
          this.editText[i] = ((EditText)localView.findViewById(2131100493));
          this.iv_lines[i] = ((ImageView)localView.findViewById(2131100494));
          Drawable localDrawable = getResources().getDrawable(arrayOfInt[i]);
          this.editText[i].setCompoundDrawablesWithIntrinsicBounds(localDrawable, null, null, null);
          localLinearLayout.addView(localView);
          i++;
          break;
        }
        EditText localEditText = this.editText[j];
        if (localEditText == null)
          continue;
        localEditText.addTextChangedListener(new MyTextWatcher(j));
      }
    }
    catch (Exception localException)
    {
    }
  }

  private boolean isChoice()
  {
    Iterator localIterator = this.opEdit.keySet().iterator();
    String str;
    do
    {
      if (!localIterator.hasNext())
        return false;
      Integer localInteger = (Integer)localIterator.next();
      str = (String)this.opEdit.get(localInteger);
    }
    while ((str == null) || (str.equals("")));
    return true;
  }

  private boolean isEditEnable()
  {
    boolean bool = true;
    if (this.edt0 != null)
      bool &= this.edt0.isEnabled();
    if (this.edt1 != null)
      bool &= this.edt1.isEnabled();
    if (this.edt2 != null)
      bool &= this.edt2.isEnabled();
    if (this.edt3 != null)
      bool &= this.edt3.isEnabled();
    if (this.edt4 != null)
      bool &= this.edt4.isEnabled();
    if (this.edt5 != null)
      bool &= this.edt5.isEnabled();
    return bool;
  }

  private boolean isEnable()
  {
    boolean bool = true;
    if (this.itemA != null)
      bool &= this.itemA.isEnabled();
    if (this.itemB != null)
      bool &= this.itemB.isEnabled();
    if (this.itemC != null)
      bool &= this.itemC.isEnabled();
    if (this.itemD != null)
      bool &= this.itemD.isEnabled();
    if (this.itemE != null)
      bool &= this.itemE.isEnabled();
    if (this.itemF != null)
      bool &= this.itemF.isEnabled();
    return bool;
  }

  private boolean isEnableExcercise()
  {
    switch (this.excerciseType)
    {
    default:
      return true & this.wenda_et.isEnabled();
    case 0:
    case 1:
    case 2:
    case 3:
    case 4:
      return true & isEnable();
    case 5:
    }
    return true & isEditEnable();
  }

  private void isHaveDone()
  {
    Iterator localIterator = this.opEdit.keySet().iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return;
      Integer localInteger = (Integer)localIterator.next();
      String str = (String)this.opEdit.get(localInteger);
      if ((str == null) || (str.equals("")))
        continue;
      this.activity.isHaveDone.put(Integer.valueOf(this.activity.currentIndex), Boolean.valueOf(true));
    }
  }

  private void isRight(int paramInt)
  {
    Iterator localIterator;
    if (paramInt == this.correntAnser.size())
    {
      localIterator = this.opEdit.keySet().iterator();
      if (localIterator.hasNext());
    }
    while (true)
    {
      if (!this.isCurrRight)
        break label131;
      this.userScore = this.exercises.getScore();
      return;
      Integer localInteger = (Integer)localIterator.next();
      String str = (String)this.opEdit.get(localInteger);
      if ((str == null) || (str.equals("")))
        break;
      if (this.correntAnser.contains(str))
      {
        this.isCurrRight = true;
        break;
      }
      this.isCurrRight = false;
      continue;
      this.isCurrRight = false;
    }
    label131: this.userScore = 0.0D;
  }

  private void optionToViews()
  {
    this.itemA = ((LinearLayout)this.rootView.findViewById(2131100189));
    this.opa_ib = ((ImageView)this.rootView.findViewById(2131100190));
    this.opa_fl = ((FlowLayout)this.rootView.findViewById(2131100191));
    this.itemA.setOnClickListener(this);
    this.itemB = ((LinearLayout)this.rootView.findViewById(2131100192));
    this.opb_ib = ((ImageView)this.rootView.findViewById(2131100193));
    this.opb_fl = ((FlowLayout)this.rootView.findViewById(2131100194));
    this.itemB.setOnClickListener(this);
    this.itemC = ((LinearLayout)this.rootView.findViewById(2131100195));
    this.opc_ib = ((ImageView)this.rootView.findViewById(2131100196));
    this.opc_fl = ((FlowLayout)this.rootView.findViewById(2131100197));
    this.itemC.setOnClickListener(this);
    this.itemD = ((LinearLayout)this.rootView.findViewById(2131100198));
    this.opd_ib = ((ImageView)this.rootView.findViewById(2131100199));
    this.opd_fl = ((FlowLayout)this.rootView.findViewById(2131100200));
    this.itemD.setOnClickListener(this);
    this.itemE = ((LinearLayout)this.rootView.findViewById(2131100201));
    this.ope_ib = ((ImageView)this.rootView.findViewById(2131100202));
    this.ope_fl = ((FlowLayout)this.rootView.findViewById(2131100203));
    this.itemE.setOnClickListener(this);
    this.itemF = ((LinearLayout)this.rootView.findViewById(2131100204));
    this.opf_ib = ((ImageView)this.rootView.findViewById(2131100205));
    this.opf_fl = ((FlowLayout)this.rootView.findViewById(2131100206));
    this.itemF.setOnClickListener(this);
  }

  private void publishComment()
  {
    String str1 = this.et_publish.getText().toString().trim();
    if (str1.equals(""))
    {
      Toast.makeText(getActivity(), "内容不能为空", 0).show();
      return;
    }
    ReqComment localReqComment = new ReqComment();
    localReqComment.setClientType(ToolsUtils.getSDK());
    localReqComment.setImei(ToolsUtils.getImei(this.mContext));
    localReqComment.setNet(ToolsUtils.getStringNetworkType(this.mContext));
    localReqComment.setVersionName(this.mSP.getVersionName());
    localReqComment.setExerciseId(this.exercises.getExerciseId());
    Comment localComment1 = new Comment();
    if (this.infor != null)
    {
      int i = this.infor.postion;
      Comment localComment2 = this.infor.comment;
      Html.fromHtml("今日刷题" + ToolsUtils.formatText("", "#D6D6D6"));
      localComment1.setReplyFloorContent("回复" + (i + 1) + "楼" + localComment2.getLocation() + "学员: " + localComment2.getContent());
      this.infor = null;
    }
    String str2 = this.mSP.getCityName();
    localComment1.setCommentTime(System.currentTimeMillis());
    localComment1.setContent(str1);
    localComment1.setLocation(str2);
    UserInfo localUserInfo = new UserInfo();
    localUserInfo.setUid(this.mSP.getUserId());
    Subjects localSubjects = new Subjects();
    localSubjects.setId(this.subjectId);
    localSubjects.setName("");
    localUserInfo.getSubjects().add(localSubjects);
    localComment1.setUserInfo(localUserInfo);
    localReqComment.setComment(localComment1);
    this.mProTools.startDialog(true);
    UrlFactory.getInstance().postAddExerciseComment().constructUrl(this, localReqComment, 4);
  }

  private void resetBg()
  {
    resetItemBg(this.opa_fl, 0);
    resetItemBg(this.opb_fl, 1);
    resetItemBg(this.opc_fl, 2);
    resetItemBg(this.opd_fl, 3);
    resetItemBg(this.ope_fl, 4);
    resetItemBg(this.opf_fl, 5);
  }

  private void resetCheck()
  {
    for (int i = 0; ; i++)
    {
      if (i >= this.opEdit.size())
        return;
      this.opEdit.put(Integer.valueOf(i), "");
    }
  }

  private void resetColorBg()
  {
    if (this.opa_ib != null)
      this.opa_ib.setBackgroundResource(2130837506);
    if (this.opb_ib != null)
      this.opb_ib.setBackgroundResource(2130837518);
    if (this.opc_ib != null)
      this.opc_ib.setBackgroundResource(2130837605);
    if (this.opd_ib != null)
      this.opd_ib.setBackgroundResource(2130837617);
    if (this.ope_ib != null)
      this.ope_ib.setBackgroundResource(2130837630);
    if (this.opf_ib != null)
      this.opf_ib.setBackgroundResource(2130837646);
  }

  private void resetFont()
  {
    if (this.opa_fl != null)
      setFlChildTexFont(this.opa_fl, false);
    if (this.opb_fl != null)
      setFlChildTexFont(this.opb_fl, false);
    if (this.opc_fl != null)
      setFlChildTexFont(this.opc_fl, false);
    if (this.opd_fl != null)
      setFlChildTexFont(this.opd_fl, false);
    if (this.ope_fl != null)
      setFlChildTexFont(this.ope_fl, false);
    if (this.opf_fl != null)
      setFlChildTexFont(this.opf_fl, false);
  }

  private void resetItemBg(FlowLayout paramFlowLayout, int paramInt)
  {
    int j;
    if (paramFlowLayout != null)
    {
      int i = paramFlowLayout.getChildCount();
      j = 0;
      if (j < i);
    }
    else
    {
      switch (paramInt)
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
    do
    {
      do
      {
        do
        {
          do
          {
            do
            {
              do
              {
                return;
                View localView = paramFlowLayout.getChildAt(j);
                if ((localView instanceof TextView))
                  ((TextView)localView).setTextColor(getResources().getColor(2131230759));
                while (true)
                {
                  j++;
                  break;
                  if (!(localView instanceof ImageView))
                    continue;
                  localView.setVisibility(8);
                }
              }
              while (this.opa_ib == null);
              this.opa_ib.setBackgroundResource(2130837506);
              return;
            }
            while (this.opb_ib == null);
            this.opb_ib.setBackgroundResource(2130837518);
            return;
          }
          while (this.opc_ib == null);
          this.opc_ib.setBackgroundResource(2130837605);
          return;
        }
        while (this.opd_ib == null);
        this.opd_ib.setBackgroundResource(2130837617);
        return;
      }
      while (this.ope_ib == null);
      this.ope_ib.setBackgroundResource(2130837630);
      return;
    }
    while (this.opf_ib == null);
    this.opf_ib.setBackgroundResource(2130837646);
  }

  private void resetTk()
  {
    try
    {
      Set localSet = this.opEdit.keySet();
      int[] arrayOfInt = { 2130837822, 2130837824, 2130837826, 2130837828, 2130837830, 2130837832, 2130837834, 2130837836, 2130837838, 2130837815, 2130837817, 2130837819, 2130837821 };
      List localList = this.exercises.getCorrectAnswer();
      int i;
      int j;
      if (localList != null)
      {
        i = localList.size();
        if ((this.editText != null) && (this.editText.length > 0))
        {
          j = 0;
          break label434;
        }
      }
      while (true)
      {
        Iterator localIterator = localSet.iterator();
        if (!localIterator.hasNext())
        {
          this.tv_anser_label.setVisibility(8);
          this.tk_ll2.setVisibility(8);
          this.btn_confirm.setVisibility(0);
          if ((this.ll_item_container2 == null) || (this.ll_item_container2.getChildCount() <= 0))
            break;
          this.ll_item_container2.removeAllViews();
          return;
        }
        label434: 
        do
        {
          Drawable localDrawable2 = getResources().getDrawable(arrayOfInt[j]);
          this.editText[j].setCompoundDrawablesWithIntrinsicBounds(localDrawable2, null, null, null);
          this.editText[j].setEnabled(true);
          j++;
          continue;
          Integer localInteger = (Integer)localIterator.next();
          String str = (String)this.opEdit.get(localInteger);
          if ((str == null) || (str.equals("")))
          {
            this.editText[localInteger.intValue()].setEnabled(true);
            this.editText[localInteger.intValue()].setText("");
          }
          while (true)
          {
            Drawable localDrawable1 = getResources().getDrawable(arrayOfInt[localInteger.intValue()]);
            this.editText[localInteger.intValue()].setCompoundDrawablesWithIntrinsicBounds(localDrawable1, null, null, null);
            this.iv_lines[localInteger.intValue()].setVisibility(0);
            this.editText[localInteger.intValue()].setTextColor(getResources().getColor(2131230729));
            break;
            this.editText[localInteger.intValue()].setEnabled(true);
            this.editText[localInteger.intValue()].setText(str);
          }
        }
        while (j < i);
      }
      return;
    }
    catch (Exception localException)
    {
    }
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
            QuestionDetailFragment.this.startNewActivity(ShowPictureActivity.class, false, localBundle);
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
    TextView localTextView3;
    if ((paramString != null) && (!paramString.equals("")) && (paramString.length() > 0))
    {
      localArrayList = new ArrayList();
      this.t = 0;
      str1 = "";
      i = 0;
      if (i >= paramString.length())
        if (!str1.equals(""))
        {
          localTextView3 = new TextView(getActivity());
          if (!paramBoolean)
            break label984;
          localTextView3.setTextSize(2, 15.0F);
          localTextView3.setTextColor(getResources().getColor(2131230759));
        }
    }
    while (true)
    {
      localTextView3.setLineSpacing(0.0F, 1.3F);
      localTextView3.setText(str1);
      localArrayList.add(localTextView3);
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
          label227: i++;
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
        label300: str1 = "";
        i += 2;
        break label227;
        if ((str2.equals("_")) && (this.category.equals("填空")))
        {
          if ((i + 1 < paramString.length()) && (i + 2 < paramString.length()) && (i + 3 < paramString.length()) && (i + 4 <= paramString.length()))
          {
            String str3 = paramString.substring(i + 1, i + 2);
            String str4 = paramString.substring(i + 2, i + 3);
            String str5 = paramString.substring(i + 3, i + 4);
            if ((str3.equals("_")) && (str4.equals("_")) && (str5.equals("_")))
            {
              TextView localTextView2 = new TextView(getActivity());
              label495: ImageView localImageView;
              if (paramBoolean)
              {
                localTextView2.setTextSize(2, 15.0F);
                localTextView2.setTextColor(getResources().getColor(2131230759));
                localTextView2.setLineSpacing(0.0F, 1.3F);
                localTextView2.setText(str1);
                localArrayList.add(localTextView2);
                str1 = "";
                localImageView = new ImageView(getActivity());
                this.t = (1 + this.t);
                switch (this.t)
                {
                default:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                case 11:
                case 12:
                case 13:
                }
              }
              while (true)
              {
                localArrayList.add(localImageView);
                i += 3;
                break;
                localTextView2.setTextSize(2, 17.0F);
                localTextView2.setTextColor(getResources().getColor(2131230760));
                break label495;
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
                continue;
                localImageView.setBackgroundResource(2130837831);
                localImageView.setTag("tk6");
                continue;
                localImageView.setBackgroundResource(2130837833);
                localImageView.setTag("tk7");
                continue;
                localImageView.setBackgroundResource(2130837835);
                localImageView.setTag("tk8");
                continue;
                localImageView.setBackgroundResource(2130837837);
                localImageView.setTag("tk9");
                continue;
                localImageView.setBackgroundResource(2130837814);
                localImageView.setTag("tk10");
                continue;
                localImageView.setBackgroundResource(2130837816);
                localImageView.setTag("tk11");
                continue;
                localImageView.setBackgroundResource(2130837818);
                localImageView.setTag("tk12");
                continue;
                localImageView.setBackgroundResource(2130837820);
                localImageView.setTag("tk13");
              }
            }
            str1 = str1 + str2;
            break label227;
          }
          str1 = str1 + str2;
          break label227;
        }
        str1 = str1 + str2;
        break label227;
        label984: localTextView3.setTextSize(2, 17.0F);
        localTextView3.setTextColor(getResources().getColor(2131230760));
        continue;
        return null;
      }
      catch (Exception localException)
      {
        break label300;
      }
    }
  }

  private void selectOption(int paramInt)
  {
    if (this.isSimu)
      changePressBg(paramInt);
    do
      return;
    while (!isEnableExcercise());
    changePressBg(paramInt);
  }

  private void setFlChildTexFont(FlowLayout paramFlowLayout, boolean paramBoolean)
  {
    int j;
    if (paramFlowLayout != null)
    {
      int i = paramFlowLayout.getChildCount();
      j = 0;
      if (j < i);
    }
    else
    {
      return;
    }
    View localView = paramFlowLayout.getChildAt(j);
    TextView localTextView;
    if ((localView instanceof TextView))
    {
      localTextView = (TextView)localView;
      if (!paramBoolean)
        break label67;
      localTextView.setTextColor(getResources().getColor(2131230758));
    }
    while (true)
    {
      j++;
      break;
      label67: localTextView.setTextColor(getResources().getColor(2131230759));
    }
  }

  private void setItemBg(FlowLayout paramFlowLayout, boolean paramBoolean)
  {
    setItemFlowlaoutChildBg(paramFlowLayout, paramBoolean);
    ImageView localImageView;
    if (isEnable())
    {
      localImageView = new ImageView(getActivity());
      if (!paramBoolean)
        break label42;
      localImageView.setBackgroundResource(2130837779);
    }
    while (true)
    {
      paramFlowLayout.addView(localImageView);
      return;
      label42: localImageView.setBackgroundResource(2130837641);
    }
  }

  private void setItemFlowlaoutChildBg(FlowLayout paramFlowLayout, boolean paramBoolean)
  {
    int j;
    if (paramFlowLayout != null)
    {
      int i = paramFlowLayout.getChildCount();
      j = 0;
      if (j < i);
    }
    else
    {
      return;
    }
    View localView = paramFlowLayout.getChildAt(j);
    TextView localTextView;
    if ((localView instanceof TextView))
    {
      localTextView = (TextView)localView;
      if (!paramBoolean)
        break label67;
      localTextView.setTextColor(getResources().getColor(2131230758));
    }
    while (true)
    {
      j++;
      break;
      label67: localTextView.setTextColor(getResources().getColor(2131230738));
    }
  }

  private void showCorrentUI(int paramInt)
  {
    switch (paramInt)
    {
    default:
    case 0:
    case 1:
    case 2:
    case 3:
    case 4:
    case 5:
    }
    do
    {
      do
      {
        do
        {
          do
          {
            do
            {
              do
              {
                return;
                if (this.opa_ib == null)
                  continue;
                this.opa_ib.setBackgroundResource(2130837504);
              }
              while (this.opa_fl == null);
              setItemBg(this.opa_fl, true);
              return;
              if (this.opb_ib == null)
                continue;
              this.opb_ib.setBackgroundResource(2130837516);
            }
            while (this.opb_fl == null);
            setItemBg(this.opb_fl, true);
            return;
            if (this.opc_ib == null)
              continue;
            this.opc_ib.setBackgroundResource(2130837603);
          }
          while (this.opc_fl == null);
          setItemBg(this.opc_fl, true);
          return;
          if (this.opd_ib == null)
            continue;
          this.opd_ib.setBackgroundResource(2130837615);
        }
        while (this.opd_fl == null);
        setItemBg(this.opd_fl, true);
        return;
        if (this.ope_ib == null)
          continue;
        this.ope_ib.setBackgroundResource(2130837628);
      }
      while (this.ope_fl == null);
      setItemBg(this.ope_fl, true);
      return;
      if (this.opf_ib == null)
        continue;
      this.opf_ib.setBackgroundResource(2130837644);
    }
    while (this.opf_fl == null);
    setItemBg(this.opf_fl, true);
  }

  private void showErrorUI(int paramInt)
  {
    switch (paramInt)
    {
    default:
      return;
    case 0:
      this.opa_ib.setBackgroundResource(2130837505);
      setItemBg(this.opa_fl, false);
      return;
    case 1:
      this.opb_ib.setBackgroundResource(2130837517);
      setItemBg(this.opb_fl, false);
      return;
    case 2:
      this.opc_ib.setBackgroundResource(2130837604);
      setItemBg(this.opc_fl, false);
      return;
    case 3:
      this.opd_ib.setBackgroundResource(2130837616);
      setItemBg(this.opd_fl, false);
      return;
    case 4:
      this.ope_ib.setBackgroundResource(2130837629);
      setItemBg(this.ope_fl, false);
      return;
    case 5:
    }
    this.opf_ib.setBackgroundResource(2130837645);
    setItemBg(this.opf_fl, false);
  }

  private void showExplain()
  {
    initExplainView();
    initExplainData();
    initExplainListen();
  }

  private void showItemForUserCheck(Integer paramInteger)
  {
    switch (paramInteger.intValue())
    {
    default:
    case 0:
    case 1:
    case 2:
    case 3:
    case 4:
    case 5:
    }
    do
    {
      do
      {
        do
        {
          do
          {
            do
            {
              do
              {
                return;
                if (this.opa_fl == null)
                  continue;
                setItemFlowlaoutChildBg(this.opa_fl, true);
              }
              while (this.opa_ib == null);
              this.opa_ib.setBackgroundResource(2130837504);
              return;
              if (this.opb_fl == null)
                continue;
              setItemFlowlaoutChildBg(this.opb_fl, true);
            }
            while (this.opb_ib == null);
            this.opb_ib.setBackgroundResource(2130837516);
            return;
            if (this.opc_fl == null)
              continue;
            setItemFlowlaoutChildBg(this.opc_fl, true);
          }
          while (this.opc_ib == null);
          this.opc_ib.setBackgroundResource(2130837603);
          return;
          if (this.opd_fl == null)
            continue;
          setItemFlowlaoutChildBg(this.opd_fl, true);
        }
        while (this.opd_ib == null);
        this.opd_ib.setBackgroundResource(2130837615);
        return;
        if (this.ope_fl == null)
          continue;
        setItemFlowlaoutChildBg(this.ope_fl, true);
      }
      while (this.ope_ib == null);
      this.ope_ib.setBackgroundResource(2130837628);
      return;
      if (this.opf_fl == null)
        continue;
      setItemFlowlaoutChildBg(this.opf_fl, true);
    }
    while (this.opf_ib == null);
    this.opf_ib.setBackgroundResource(2130837644);
  }

  private void showRightOrError()
  {
    Iterator localIterator1 = this.correntAnser.iterator();
    int i;
    Iterator localIterator2;
    if (!localIterator1.hasNext())
    {
      i = 0;
      localIterator2 = this.opEdit.keySet().iterator();
    }
    while (true)
    {
      if (!localIterator2.hasNext())
      {
        isRight(i);
        this.activity.currRight.put(Integer.valueOf(this.currentIndex), this.isCurrRight);
        showExplain();
        enableOption();
        if (!this.isCurrRight)
          break label336;
        this.handler.sendEmptyMessageDelayed(1, 800L);
        return;
        String str1 = (String)localIterator1.next();
        if ((str1 == null) || (str1.equals("")) || (str1.equals("ⅹ")) || (str1.equals("√")))
          break;
        if (str1.equals("A"))
        {
          showCorrentUI(0);
          break;
        }
        if (str1.equals("B"))
        {
          showCorrentUI(1);
          break;
        }
        if (str1.equals("C"))
        {
          showCorrentUI(2);
          break;
        }
        if (str1.equals("D"))
        {
          showCorrentUI(3);
          break;
        }
        if (str1.equals("E"))
        {
          showCorrentUI(4);
          break;
        }
        if (!str1.equals("F"))
          break;
        showCorrentUI(5);
        break;
      }
      Integer localInteger = (Integer)localIterator2.next();
      String str2 = (String)this.opEdit.get(localInteger);
      if ((str2 == null) || (str2.equals("")))
        continue;
      i++;
      if (this.correntAnser.contains(str2))
        continue;
      showErrorUI(localInteger.intValue());
    }
    label336: LogUtils.myLog("错误留在当页");
  }

  private void showTKannswer(int paramInt)
  {
    try
    {
      this.tk_ll2.setVisibility(0);
      List localList = this.exercises.getCorrectAnswer();
      int[] arrayOfInt;
      int i;
      if (localList != null)
      {
        arrayOfInt = new int[] { 2130837822, 2130837824, 2130837826, 2130837828, 2130837830, 2130837832, 2130837834, 2130837836, 2130837838, 2130837815, 2130837817, 2130837819, 2130837821 };
        if (localList != null)
          i = localList.size();
      }
      for (int j = 0; ; j++)
      {
        if (j >= i)
        {
          this.ll_item_container2.setVisibility(0);
          return;
        }
        if (j != paramInt)
          continue;
        FlowLayout localFlowLayout = (FlowLayout)View.inflate(getActivity(), 2130903176, null);
        localFlowLayout.setOption(true);
        ImageView localImageView = (ImageView)View.inflate(getActivity(), 2130903179, null);
        localImageView.setBackgroundResource(arrayOfInt[j]);
        localFlowLayout.addView(localImageView);
        Iterator localIterator = scaningString((String)localList.get(j), true).iterator();
        while (true)
          if (!localIterator.hasNext())
          {
            this.ll_item_container2.addView(localFlowLayout);
          }
          else
          {
            View localView = (View)localIterator.next();
            if ((localView instanceof TextView))
            {
              TextView localTextView = (TextView)localView;
              localTextView.setTextColor(getResources().getColor(2131230739));
              localFlowLayout.addView(localTextView);
              continue;
            }
            localFlowLayout.addView(localView);
            continue;
            return;
          }
      }
    }
    catch (Exception localException)
    {
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
      LogUtils.myLog("知识点 讲解公式 文字 图片 混排切割 " + str);
    }
  }

  public void ableOption()
  {
    if (this.itemA != null)
      this.itemA.setEnabled(true);
    if (this.itemB != null)
      this.itemB.setEnabled(true);
    if (this.itemC != null)
      this.itemC.setEnabled(true);
    if (this.itemD != null)
      this.itemD.setEnabled(true);
    if (this.itemE != null)
      this.itemE.setEnabled(true);
    if (this.itemF != null)
      this.itemF.setEnabled(true);
  }

  public void confirmBySmoothPage()
  {
    if (this.isSimu)
      switch (this.excerciseType)
      {
      default:
      case 0:
      case 4:
      case 5:
      case 1:
      case 2:
      case 3:
      }
    do
    {
      this.activity.isHaveDone.put(Integer.valueOf(this.activity.currentIndex), Boolean.valueOf(true));
      do
      {
        do
          return;
        while (this.opEdit.size() <= 0);
        isHaveDone();
        int j = 0;
        Iterator localIterator3 = this.opEdit.keySet().iterator();
        while (true)
        {
          if (!localIterator3.hasNext())
          {
            isRight(j);
            return;
          }
          Integer localInteger2 = (Integer)localIterator3.next();
          String str3 = (String)this.opEdit.get(localInteger2);
          if ((str3 == null) || (str3.equals("")))
            continue;
          j++;
        }
        switch (this.excerciseType)
        {
        case 0:
        case 4:
        case 5:
        default:
        case 1:
        case 2:
        case 3:
        }
      }
      while (!this.wenda_et.isEnabled());
      this.activity.isHaveDone.put(Integer.valueOf(this.activity.currentIndex), Boolean.valueOf(true));
      this.wenda_et.setEnabled(false);
      showExplain();
      return;
    }
    while ((!isEnable()) || (this.opEdit.size() <= 0));
    isHaveDone();
    Iterator localIterator1 = this.correntAnser.iterator();
    int i;
    Iterator localIterator2;
    if (!localIterator1.hasNext())
    {
      i = 0;
      localIterator2 = this.opEdit.keySet().iterator();
    }
    while (true)
    {
      if (!localIterator2.hasNext())
      {
        isRight(i);
        showExplain();
        enableOption();
        return;
        String str1 = (String)localIterator1.next();
        if ((str1 == null) || (str1.equals("")) || (str1.equals("ⅹ")) || (str1.equals("√")))
          break;
        if (str1.equals("A"))
        {
          showCorrentUI(0);
          break;
        }
        if (str1.equals("B"))
        {
          showCorrentUI(1);
          break;
        }
        if (str1.equals("C"))
        {
          showCorrentUI(2);
          break;
        }
        if (str1.equals("D"))
        {
          showCorrentUI(3);
          break;
        }
        if (str1.equals("E"))
        {
          showCorrentUI(4);
          break;
        }
        if (!str1.equals("F"))
          break;
        showCorrentUI(5);
        break;
      }
      Integer localInteger1 = (Integer)localIterator2.next();
      String str2 = (String)this.opEdit.get(localInteger1);
      if ((str2 == null) || (str2.equals("")))
        continue;
      i++;
      if (this.correntAnser.contains(str2))
        continue;
      showErrorUI(localInteger1.intValue());
    }
  }

  public void enableOption()
  {
    if (this.itemA != null)
      this.itemA.setEnabled(false);
    if (this.itemB != null)
      this.itemB.setEnabled(false);
    if (this.itemC != null)
      this.itemC.setEnabled(false);
    if (this.itemD != null)
      this.itemD.setEnabled(false);
    if (this.itemE != null)
      this.itemE.setEnabled(false);
    if (this.itemF != null)
      this.itemF.setEnabled(false);
  }

  public void initData()
  {
    Iterator localIterator;
    if (this.exercises != null)
    {
      this.manager = ((InputMethodManager)getActivity().getSystemService("input_method"));
      this.activity = ((QuestionDetailActivity)getActivity());
      this.subjectId = this.activity.subjectId;
      this.isSimu = this.activity.isSimu;
      KpointDetail localKpointDetail = this.exercises.getKpointDetail();
      if (localKpointDetail != null)
      {
        Kpoint localKpoint = localKpointDetail.getKpoint();
        if (localKpoint != null)
        {
          String str3 = localKpoint.getSn();
          String str4 = localKpoint.getName();
          if ((str3 != null) && (!str3.equals("")))
            this.questionNum.setText(str3);
          if ((str4 != null) && (!str4.equals("")))
            this.qustion_title.setText(str4);
        }
      }
      String str1 = this.exercises.getExerciseNo();
      int i = this.exercises.getTotalNum();
      this.doneTv.setText(str1 + "题/" + i + "题");
      this.typeLabel.setText(this.category);
      List localList = this.exercises.getLabels();
      if ((localList != null) && (localList.size() > 0))
      {
        localIterator = localList.iterator();
        if (localIterator.hasNext())
          break label406;
      }
      this.correntAnser = this.exercises.getCorrectAnswer();
      this.opEdit = new HashMap();
      this.activity.cacheOpEdit.put(Integer.valueOf(this.currentIndex), this.opEdit);
      if (!this.isSimu)
        break label593;
      this.questionNum.setVisibility(8);
      this.qustion_title.setVisibility(8);
      this.doneTv.setVisibility(8);
      this.ll_label_content.setVisibility(8);
      this.ll_line.setVisibility(8);
      switch (this.excerciseType)
      {
      default:
        this.wenda_et.setVisibility(0);
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
      this.btn_confirm.setVisibility(8);
      return;
      label406: String str2 = (String)localIterator.next();
      if (str2.contains("真题"))
      {
        this.zhengti.setText("真题");
        this.zhengti.setBackgroundResource(2130837937);
        this.zhengti.setVisibility(0);
        break;
      }
      if (str2.contains("模拟"))
      {
        this.yuce.setText("模拟");
        this.yuce.setBackgroundResource(2130837727);
        this.yuce.setVisibility(0);
        break;
      }
      if (str2.contains("练习"))
      {
        this.lianxi.setText("练习");
        this.lianxi.setBackgroundResource(2130837697);
        this.lianxi.setVisibility(0);
        break;
      }
      if (!str2.contains("预测"))
        break;
      this.yuce.setText("预测");
      this.yuce.setBackgroundResource(2130837931);
      this.yuce.setVisibility(0);
      break;
      initTk();
    }
    label593: if (this.exercises != null)
      switch (this.excerciseType)
      {
      default:
        this.btn_confirm.setVisibility(0);
        this.wenda_et.setVisibility(0);
      case 0:
      case 1:
      case 2:
      case 3:
      case 4:
      case 5:
      }
    while (this.activity.havePush == null)
    {
      this.activity.havePush = new HashMap();
      return;
      this.btn_confirm.setVisibility(8);
      continue;
      this.btn_confirm.setEnabled(false);
      this.btn_confirm.setVisibility(0);
      continue;
      this.btn_confirm.setEnabled(false);
      this.btn_confirm.setVisibility(0);
      continue;
      this.btn_confirm.setEnabled(false);
      this.btn_confirm.setVisibility(0);
      continue;
      this.btn_confirm.setVisibility(8);
      continue;
      this.tk_layout.setVisibility(0);
      initTk();
      this.btn_confirm.setVisibility(0);
    }
  }

  public void initListener()
  {
    if (this.exercises != null)
      this.wenda_et.addTextChangedListener(new TextWatcher()
      {
        public void afterTextChanged(Editable paramEditable)
        {
        }

        public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
        {
        }

        public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
        {
          String str = paramCharSequence.toString().trim();
          if ((str != null) && (!"".equals(str)))
          {
            QuestionDetailFragment.this.opEdit.put(Integer.valueOf(0), str);
            if (QuestionDetailFragment.this.isSimu)
            {
              if (paramCharSequence.length() <= 0)
                break label225;
              QuestionDetailFragment.this.isCurrRight = true;
              QuestionDetailFragment.this.activity.currRight.put(Integer.valueOf(QuestionDetailFragment.this.currentIndex), "true");
              QuestionDetailFragment.this.activity.isHaveDone.put(Integer.valueOf(QuestionDetailFragment.this.currentIndex), Boolean.valueOf(true));
              if (!QuestionDetailFragment.this.isCurrRight)
                break label289;
            }
          }
          label289: for (QuestionDetailFragment.this.userScore = QuestionDetailFragment.this.exercises.getScore(); ; QuestionDetailFragment.this.userScore = 0.0D)
          {
            QuestionDetailFragment.this.activity.insertToDb(QuestionDetailFragment.this.exercises, QuestionDetailFragment.this.opEdit, QuestionDetailFragment.this.isCurrRight, QuestionDetailFragment.this.userScore, QuestionDetailFragment.this.exercises.getScore());
            LogUtils.myLog("插入currentInx=" + QuestionDetailFragment.this.currentIndex);
            return;
            label225: QuestionDetailFragment.this.isCurrRight = false;
            QuestionDetailFragment.this.activity.currRight.put(Integer.valueOf(QuestionDetailFragment.this.currentIndex), null);
            QuestionDetailFragment.this.activity.isHaveDone.put(Integer.valueOf(QuestionDetailFragment.this.currentIndex), Boolean.valueOf(false));
            break;
          }
        }
      });
  }

  public void initView(View paramView)
  {
    if (this.exercises != null)
    {
      this.yuce = ((TextView)paramView.findViewById(2131100182));
      this.zhengti = ((TextView)paramView.findViewById(2131100181));
      this.lianxi = ((TextView)paramView.findViewById(2131100183));
      this.scrollView = ((ScrollView)paramView.findViewById(2131099650));
      this.wenda_et = ((EditText)paramView.findViewById(2131100186));
      this.questionNum = ((TextView)paramView.findViewById(2131100175));
      this.qustion_title = ((TextView)paramView.findViewById(2131100176));
      this.ll_label_content = ((LinearLayout)paramView.findViewById(2131100180));
      this.typeLabel = ((TextView)paramView.findViewById(2131100177));
      this.doneTv = ((TextView)paramView.findViewById(2131100178));
      this.ll_detail = ((LinearLayout)paramView.findViewById(2131100212));
      this.layout_question_detail_edit = ((LinearLayout)paramView.findViewById(2131100107));
      this.btn_confirm = ((Button)paramView.findViewById(2131100208));
      this.btn_confirm.setOnClickListener(this);
      this.ll_notification = ((LinearLayout)paramView.findViewById(2131100209));
      this.tk_layout = ((LinearLayout)paramView.findViewById(2131100188));
      this.ll_line = ((LinearLayout)paramView.findViewById(2131100179));
      this.ll_notification.setVisibility(8);
      this.comment_lv = ((MyListView)paramView.findViewById(2131100235));
    }
  }

  public void onAttach(Activity paramActivity)
  {
    super.onAttach(paramActivity);
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
      return;
    case 2131100189:
      selectOption(0);
      return;
    case 2131100192:
      selectOption(1);
      return;
    case 2131100195:
      selectOption(2);
      return;
    case 2131100198:
      selectOption(3);
      return;
    case 2131100201:
      selectOption(4);
      return;
    case 2131100204:
      selectOption(5);
      return;
    case 2131100237:
      dianZan();
      return;
    case 2131100238:
      switch (this.activity.FromPage)
      {
      default:
      case 9:
      case 12:
      case 888:
      }
      while (true)
      {
        publishComment();
        return;
        MobclickAgent.onEvent(this.mContext, "brush_c_publish");
        continue;
        MobclickAgent.onEvent(this.mContext, "brush_i_publish");
      }
    case 2131100236:
      switch (this.activity.FromPage)
      {
      default:
      case 9:
      case 12:
      case 888:
      }
      while (true)
      {
        MobclickAgent.onEvent(this.mContext, "brush_i_more_comment");
        MobclickAgent.onEvent(this.mContext, "brush_c_more_comment");
        Bundle localBundle = new Bundle();
        localBundle.putString("exerciseId", this.exercises.getExerciseId());
        localBundle.putString("subjectId", this.subjectId);
        startNewActivity(QuestionMoreComentActivity.class, 2130968581, 2130968579, false, localBundle);
        return;
        MobclickAgent.onEvent(this.mContext, "brush_c_more_comment");
        continue;
        MobclickAgent.onEvent(this.mContext, "brush_i_more_comment");
      }
    case 2131100208:
    }
    btnConfirm();
  }

  public View onInflaterRootView(LayoutInflater paramLayoutInflater, Bundle paramBundle)
  {
    try
    {
      this.exercises = ((Exercises)getArguments().getSerializable("exercises"));
      label17: if (this.exercises == null)
        this.rootView = paramLayoutInflater.inflate(2130903107, null);
      while (true)
      {
        return this.rootView;
        this.rootView = paramLayoutInflater.inflate(2130903122, null);
        init();
      }
    }
    catch (Exception localException)
    {
      break label17;
    }
  }

  public void onSave(int paramInt)
  {
    if (this.exercises == null)
      return;
    Iterator localIterator;
    if (this.isSimu)
      localIterator = this.opEdit.keySet().iterator();
    while (true)
    {
      if (!localIterator.hasNext())
      {
        LogUtils.myLog("right=?" + (String)this.activity.currRight.get(Integer.valueOf(paramInt)));
        LogUtils.myLog("currentindex=" + paramInt);
        LogUtils.myLog("activity.resBrush.size())==" + this.activity.resBrush.size());
        LogUtils.myLog("activity.resBrush.size())==" + this.activity.resBrush.size());
        return;
      }
      Integer localInteger = (Integer)localIterator.next();
      LogUtils.myLog("opEdit.get(" + localInteger + ")=" + (String)this.opEdit.get(localInteger));
    }
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
      case 9:
      default:
      case 4:
      case 10:
      }
    }
    while (true)
    {
      return;
      this.publishCommentBean = ((PublishCommentBean)localGson.fromJson(paramString1, PublishCommentBean.class));
      if (this.publishCommentBean != null)
      {
        if ("OK".equals(this.publishCommentBean.getStatus()))
        {
          this.handler.sendEmptyMessageDelayed(6, 400L);
          return;
        }
        this.handler.sendEmptyMessageDelayed(8, 400L);
        return;
      }
      LogUtils.myLog("发布评论解析异常");
      return;
      try
      {
        this.brushExcerciseBean = ((BrushExcerciseBean)localGson.fromJson(paramString1, BrushExcerciseBean.class));
        if (this.brushExcerciseBean == null)
          break;
        List localList = this.brushExcerciseBean.getExercises();
        if ((localList == null) || (localList.size() <= 0))
          continue;
        Exercises localExercises = (Exercises)localList.get(0);
        this.exercises = localExercises;
        Message localMessage = this.handler.obtainMessage();
        localMessage.what = 9;
        localMessage.obj = localExercises;
        this.handler.sendMessage(localMessage);
        return;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        LogUtils.myLog("刷题主界面数据实体解析异常:" + localException.getMessage());
        return;
      }
    }
    LogUtils.myLog("刷题主界面数据解析实体bean为null");
  }

  public void pushAll()
  {
    if (this.exercises == null);
    while (true)
    {
      return;
      LogUtils.myLog("pushAll");
      LogUtils.myLog("activity.resBrush.size()=" + this.activity.resBrush.size());
      LogUtils.myLog("currentIndex=" + this.currentIndex);
      LogUtils.myLog("right?=" + (String)this.activity.currRight.get(Integer.valueOf(this.currentIndex)));
      Iterator localIterator = this.opEdit.keySet().iterator();
      while (localIterator.hasNext())
      {
        Integer localInteger = (Integer)localIterator.next();
        LogUtils.myLog("opEdit.get(" + localInteger + ")" + (String)this.opEdit.get(localInteger));
      }
    }
  }

  public void refresh(Exercises paramExercises, int paramInt)
  {
    if (paramExercises == null);
    Boolean localBoolean;
    do
    {
      QuestionDetailActivity localQuestionDetailActivity;
      while (true)
      {
        return;
        localQuestionDetailActivity = (QuestionDetailActivity)getActivity();
        this.isSimu = localQuestionDetailActivity.isSimu;
        if (this.isSimu)
          continue;
        if (!localQuestionDetailActivity.isTotalOpen)
          break;
        switch (this.excerciseType)
        {
        default:
          if (!this.wenda_et.isEnabled())
            continue;
          showExplain();
          this.wenda_et.setEnabled(false);
          return;
        case 0:
        case 4:
          if (!isEnable())
            continue;
          Iterator localIterator4 = this.correntAnser.iterator();
          Iterator localIterator5;
          if (!localIterator4.hasNext())
            localIterator5 = this.opEdit.keySet().iterator();
          while (true)
          {
            if (!localIterator5.hasNext())
            {
              showExplain();
              enableOption();
              return;
              String str4 = (String)localIterator4.next();
              if ((str4 == null) || (str4.equals("")) || (str4.equals("ⅹ")) || (str4.equals("√")))
                break;
              if (str4.equals("A"))
              {
                showCorrentUI(0);
                break;
              }
              if (str4.equals("B"))
              {
                showCorrentUI(1);
                break;
              }
              if (str4.equals("C"))
              {
                showCorrentUI(2);
                break;
              }
              if (str4.equals("D"))
              {
                showCorrentUI(3);
                break;
              }
              if (str4.equals("E"))
              {
                showCorrentUI(4);
                break;
              }
              if (!str4.equals("F"))
                break;
              showCorrentUI(5);
              break;
            }
            Integer localInteger3 = (Integer)localIterator5.next();
            String str5 = (String)this.opEdit.get(localInteger3);
            if ((str5 == null) || (str5.equals("")) || (this.correntAnser.contains(str5)))
              continue;
            showErrorUI(localInteger3.intValue());
          }
        case 1:
        case 2:
        case 3:
          if (!isEnable())
            continue;
          Iterator localIterator2 = this.correntAnser.iterator();
          Iterator localIterator3;
          if (!localIterator2.hasNext())
            localIterator3 = this.opEdit.keySet().iterator();
          while (true)
          {
            if (!localIterator3.hasNext())
            {
              showExplain();
              enableOption();
              return;
              String str2 = (String)localIterator2.next();
              if ((str2 == null) || (str2.equals("")) || (str2.equals("ⅹ")) || (str2.equals("√")))
                break;
              if (str2.equals("A"))
              {
                showCorrentUI(0);
                break;
              }
              if (str2.equals("B"))
              {
                showCorrentUI(1);
                break;
              }
              if (str2.equals("C"))
              {
                showCorrentUI(2);
                break;
              }
              if (str2.equals("D"))
              {
                showCorrentUI(3);
                break;
              }
              if (str2.equals("E"))
              {
                showCorrentUI(4);
                break;
              }
              if (!str2.equals("F"))
                break;
              showCorrentUI(5);
              break;
            }
            Integer localInteger2 = (Integer)localIterator3.next();
            String str3 = (String)this.opEdit.get(localInteger2);
            if ((str3 == null) || (str3.equals("")) || (this.correntAnser.contains(str3)))
              continue;
            showErrorUI(localInteger2.intValue());
          }
        case 5:
          checkTk1();
          return;
        }
      }
      localBoolean = (Boolean)localQuestionDetailActivity.isHaveDone.get(Integer.valueOf(paramInt));
    }
    while ((localBoolean != null) && (localBoolean.booleanValue()));
    this.ll_detail.setVisibility(8);
    this.ll_notification.setVisibility(8);
    this.layout_question_detail_edit.setVisibility(8);
    switch (this.excerciseType)
    {
    default:
      this.wenda_et.setEnabled(true);
      this.btn_confirm.setVisibility(0);
      return;
    case 0:
    case 4:
      ableOption();
      resetBg();
      this.btn_confirm.setVisibility(8);
      return;
    case 1:
    case 2:
    case 3:
      ableOption();
      resetBg();
      Iterator localIterator1;
      if ((this.opEdit != null) && (this.opEdit.size() > 0))
        localIterator1 = this.opEdit.keySet().iterator();
      while (true)
      {
        if (!localIterator1.hasNext())
        {
          this.btn_confirm.setVisibility(0);
          return;
        }
        Integer localInteger1 = (Integer)localIterator1.next();
        String str1 = (String)this.opEdit.get(localInteger1);
        if ((str1 == null) || (str1.equals("")))
          continue;
        showItemForUserCheck(localInteger1);
      }
    case 5:
    }
    resetTk();
  }

  public void refreshData(Exercises paramExercises, int paramInt)
  {
    if (paramExercises == null)
      return;
    this.exercises = paramExercises;
    this.currentIndex = paramInt;
  }

  private class MyTextWatcher
    implements TextWatcher
  {
    private int index;

    public MyTextWatcher(int arg2)
    {
      int i;
      this.index = i;
    }

    public void afterTextChanged(Editable paramEditable)
    {
    }

    public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
    {
    }

    public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
    {
      String str1 = paramCharSequence.toString();
      List localList;
      int i;
      int j;
      label117: int n;
      label127: int k;
      if ((str1 == null) || (str1.equals("")))
      {
        QuestionDetailFragment.this.opEdit.put(Integer.valueOf(this.index), "");
        if (QuestionDetailFragment.this.isSimu)
        {
          localList = QuestionDetailFragment.this.exercises.getCorrectAnswer();
          i = localList.size();
          j = 0;
          if (j < i)
            break label319;
          if (paramInt3 <= 0)
            break label396;
          QuestionDetailFragment.this.activity.isHaveDone.put(Integer.valueOf(QuestionDetailFragment.this.currentIndex), Boolean.valueOf(true));
          if (paramInt3 != i)
            break label525;
          n = 0;
          if (n < i)
            break label427;
          label134: k = 0;
        }
      }
      for (int m = 0; ; m++)
      {
        if (m >= i)
        {
          double d = QuestionDetailFragment.this.exercises.getScore();
          QuestionDetailFragment.this.userScore = (d * (1.0D * k / i));
          QuestionDetailFragment.this.activity.currRight.put(Integer.valueOf(QuestionDetailFragment.this.currentIndex), QuestionDetailFragment.this.isCurrRight);
          QuestionDetailFragment.this.activity.insertToDb(QuestionDetailFragment.this.exercises, QuestionDetailFragment.this.opEdit, QuestionDetailFragment.this.isCurrRight, QuestionDetailFragment.this.userScore, QuestionDetailFragment.this.exercises.getScore());
          LogUtils.myLog("插入currentInx=" + QuestionDetailFragment.this.currentIndex);
          return;
          QuestionDetailFragment.this.opEdit.put(Integer.valueOf(this.index), str1);
          break;
          label319: String str2 = (String)localList.get(j);
          String str3 = QuestionDetailFragment.this.editText[j].getText().toString().trim();
          if ((str3 != null) && (!str3.equals("")))
          {
            if (!str2.equals(str3))
              break label390;
            paramInt3++;
          }
          while (true)
          {
            j++;
            break;
            label390: paramInt3++;
          }
          label396: QuestionDetailFragment.this.activity.isHaveDone.put(Integer.valueOf(QuestionDetailFragment.this.currentIndex), Boolean.valueOf(false));
          break label117;
          label427: String str6 = (String)localList.get(n);
          String str7 = QuestionDetailFragment.this.editText[n].getText().toString().trim();
          if ((str7 == null) || (str7.equals("")))
          {
            QuestionDetailFragment.this.isCurrRight = false;
            break label134;
          }
          if (str6.equals(str7))
          {
            QuestionDetailFragment.this.isCurrRight = true;
            n++;
            break label127;
          }
          QuestionDetailFragment.this.isCurrRight = false;
          break label134;
          label525: QuestionDetailFragment.this.isCurrRight = false;
          break label134;
        }
        String str4 = (String)localList.get(m);
        String str5 = QuestionDetailFragment.this.editText[m].getText().toString().trim();
        if ((str5 == null) || (str5.equals("")) || (!str4.equals(str5)))
          continue;
        k++;
      }
    }
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.fragment.QuestionDetailFragment
 * JD-Core Version:    0.6.0
 */