package com.withustudy.koudaizikao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.android.http.FileDownLoad;
import com.google.gson.Gson;
import com.withustudy.koudaizikao.action.AbsSimpleImplUrlFactory;
import com.withustudy.koudaizikao.action.UrlFactory;
import com.withustudy.koudaizikao.base.AbsBaseActivity;
import com.withustudy.koudaizikao.config.KouDaiSP;
import com.withustudy.koudaizikao.custom.SharePopWindow;
import com.withustudy.koudaizikao.entity.ErrorExercise;
import com.withustudy.koudaizikao.entity.OpreationState;
import com.withustudy.koudaizikao.entity.req.ReqDeleteErrors;
import com.withustudy.koudaizikao.entity.req.UserSubject;
import com.withustudy.koudaizikao.tools.ProTools;
import com.withustudy.koudaizikao.tools.ToolsUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ActivityErrorWecome extends AbsBaseActivity
  implements OnClickListener
{
  private static final int action_delete_id = 2;
  private static final int action_delete_id_fail = 3;
  private ImageButton back;
  private int ceil;
  private String count;
  private Button delete;
  ArrayList<ErrorExercise> errExsList;
  private TextView error_count;
  private Handler handler = new Handler()
  {
    public void handleMessage(Message paramMessage)
    {
      switch (paramMessage.what)
      {
      default:
        return;
      case 2:
        Toast.makeText(ActivityErrorWecome.this.getApplicationContext(), "删除成功!", 0).show();
        return;
      case 3:
      }
      Toast.makeText(ActivityErrorWecome.this.getApplicationContext(), "删除失败!", 0).show();
    }
  };
  private LinearLayout ll_bottom;
  private TextView money;
  private ImageView pic;
  private Button review;
  private Button share;
  private String subjectId;
  private String subjectName;
  private long time;
  private TextView timeTv;
  private TextView tvTitle;
  private TextView username;

  private void deleteExcer()
  {
    ReqDeleteErrors localReqDeleteErrors = new ReqDeleteErrors();
    localReqDeleteErrors.setVersionName(this.mSP.getVersionName());
    localReqDeleteErrors.setClientType(ToolsUtils.getSDK());
    localReqDeleteErrors.setImei(ToolsUtils.getImei(this.mContext));
    localReqDeleteErrors.setNet(ToolsUtils.getStringNetworkType(this.mContext));
    UserSubject localUserSubject = new UserSubject();
    this.mProTools.startDialog(true);
    localUserSubject.setSubjectId(this.subjectId);
    localUserSubject.setUid(this.mSP.getUserId());
    localReqDeleteErrors.setUserSubject(localUserSubject);
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = this.errExsList.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
      {
        localReqDeleteErrors.setExerciseId(localArrayList);
        UrlFactory.getInstance().deleteErrorsById().constructUrl(this, localReqDeleteErrors, 2);
        return;
      }
      localArrayList.add(((ErrorExercise)localIterator.next()).getExerciseId());
    }
  }

  protected void bindData()
  {
    this.tvTitle.setText(this.subjectName);
    String str = this.mSP.getNickName();
    this.username.setText(str);
    this.timeTv.setText(this.ceil);
    this.error_count.setText(this.errExsList.size());
  }

  protected void initData()
  {
    Bundle localBundle = getIntent().getExtras();
    try
    {
      this.subjectId = ((String)localBundle.getSerializable("subjectId"));
      this.subjectName = ((String)localBundle.getSerializable("subjectName"));
      this.time = ((Long)localBundle.getSerializable("time")).longValue();
      this.errExsList = ((ArrayList)localBundle.getSerializable("errExsList"));
      this.ceil = (int)Math.ceil(1.0D * this.time / 60000.0D);
      label82: String str = this.mSP.getAvatar();
      if ((str != null) && (!str.equals("")))
        this.mFileDownLoad.downLoadImage(str, this.pic, 1000);
      return;
    }
    catch (Exception localException)
    {
      break label82;
    }
  }

  protected void initListener()
  {
    this.back.setOnClickListener(this);
    this.delete.setOnClickListener(this);
    this.review.setOnClickListener(this);
    this.share.setOnClickListener(this);
  }

  protected void initView()
  {
    this.ll_bottom = ((LinearLayout)findViewById(2131099768));
    this.back = ((ImageButton)findViewById(2131099761));
    this.delete = ((Button)findViewById(2131099769));
    this.review = ((Button)findViewById(2131099770));
    this.share = ((Button)findViewById(2131099713));
    this.pic = ((ImageView)findViewById(2131099763));
    this.tvTitle = ((TextView)findViewById(2131099762));
    this.username = ((TextView)findViewById(2131099764));
    this.timeTv = ((TextView)findViewById(2131099765));
    this.error_count = ((TextView)findViewById(2131099766));
    this.money = ((TextView)findViewById(2131099767));
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
      return;
    case 2131099761:
      finish(0, 0);
      return;
    case 2131099769:
      deleteExcer();
      return;
    case 2131099770:
      Bundle localBundle = new Bundle();
      localBundle.putSerializable("errorExercises", this.errExsList);
      localBundle.putSerializable("subjectId", this.subjectId);
      localBundle.putInt("FromPage", 10);
      startNewActivity(QuestionDetailActivity.class, 2130968581, 2130968579, false, localBundle);
      return;
    case 2131099713:
    }
    new SharePopWindow(this, this.ll_bottom).showPop();
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
      case 2:
        OpreationState localOpreationState = (OpreationState)localGson.fromJson(paramString1, OpreationState.class);
        if (localOpreationState == null)
          break;
        if ("OK".equals(localOpreationState.getStatus()))
        {
          this.handler.sendEmptyMessage(2);
          return;
        }
        this.handler.sendEmptyMessage(3);
      }
      return;
    }
    catch (Exception localException)
    {
    }
  }

  protected void setContentView()
  {
    setContentView(2130903052);
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.activity.ActivityErrorWecome
 * JD-Core Version:    0.6.0
 */