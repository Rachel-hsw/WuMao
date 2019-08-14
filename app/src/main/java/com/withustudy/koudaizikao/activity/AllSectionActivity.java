package com.withustudy.koudaizikao.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.withustudy.koudaizikao.action.AbsBaseUrlConstructor;
import com.withustudy.koudaizikao.action.UrlFactory;
import com.withustudy.koudaizikao.adapter.AllSectionAdapter;
import com.withustudy.koudaizikao.base.AbsBaseActivity;
import com.withustudy.koudaizikao.config.KouDaiSP;
import com.withustudy.koudaizikao.entity.Forum;
import com.withustudy.koudaizikao.entity.content.ForumsContent;
import com.withustudy.koudaizikao.tools.ProTools;
import com.withustudy.koudaizikao.tools.ToolsUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AllSectionActivity extends AbsBaseActivity
{
  public static final int ACTION_LOAD = 4;
  public static final int ACTION_REFRESH = 3;
  public static final int LOAD_FINISH = 2;
  public static final int REFRESH_FINISH = 1;
  private Button buttonBack;
  private boolean isLoading = false;
  private boolean isRefresh = false;
  private List<Forum> list;
  private AllSectionAdapter mAdapter;
  private CallBackListener mBackListener;
  private AllSectionHandler mHandler;
  private PullToRefreshListView mListView;
  private final int pageCount = 10;
  private int pageNum;
  private String[] paramter = null;
  private TextView textTitle;

  protected void bindData()
  {
    this.textTitle.setText("全部版块");
    this.mAdapter = new AllSectionAdapter(this.mContext, this.list);
    this.mListView.setAdapter(this.mAdapter);
    ToolsUtils.setList(2, this.mListView, this.mContext);
  }

  protected void initData()
  {
    this.mBackListener = new CallBackListener();
    this.mHandler = new AllSectionHandler();
    this.list = new ArrayList();
    this.paramter = new String[5];
  }

  protected void initListener()
  {
    this.buttonBack.setOnClickListener(this.mBackListener);
    this.mListView.setOnRefreshListener(this.mBackListener);
    this.mListView.setOnItemClickListener(this.mBackListener);
  }

  protected void initView()
  {
    this.buttonBack = ((Button)findViewById(2131099701));
    this.textTitle = ((TextView)findViewById(2131099702));
    this.mListView = ((PullToRefreshListView)findViewById(2131099703));
  }

  public void onError(String paramString1, String paramString2, int paramInt)
  {
    super.onError(paramString1, paramString2, paramInt);
    this.mProTools.dismissDislog();
    switch (paramInt)
    {
    default:
      return;
    case 3:
      this.isRefresh = false;
      this.mListView.onRefreshComplete();
      return;
    case 4:
    }
    this.isLoading = false;
    this.mListView.onRefreshComplete();
  }

  protected void onResume()
  {
    super.onResume();
    this.pageNum = 1;
    this.isRefresh = true;
    this.paramter[0] = this.mSP.getUserId();
    this.paramter[1] = this.mSP.getProId();
    this.paramter[2] = this.mSP.getMajorId();
    this.paramter[3] = String.valueOf(this.pageNum);
    this.paramter[4] = String.valueOf(10);
    UrlFactory.getInstance().getAllSection().constructUrl(this, this.paramter, 3, this.mContext);
    this.mProTools.startDialog(true);
  }

  public void onSuccess(String paramString1, Map<String, String> paramMap, String paramString2, int paramInt)
  {
    super.onSuccess(paramString1, paramMap, paramString2, paramInt);
    this.mProTools.dismissDislog();
    switch (paramInt)
    {
    default:
    case 3:
    case 4:
    }
    do
    {
      do
      {
        return;
        this.isRefresh = false;
        this.mListView.onRefreshComplete();
      }
      while (paramString1 == null);
      try
      {
        ForumsContent localForumsContent2 = (ForumsContent)UrlFactory.getInstanceGson().fromJson(paramString1, ForumsContent.class);
        if ((localForumsContent2 != null) && (localForumsContent2.getResult().equals("true")))
        {
          this.mHandler.sendMessage(this.mHandler.obtainMessage(1, localForumsContent2.getForums()));
          return;
        }
      }
      catch (Exception localException2)
      {
        localException2.printStackTrace();
        Toast.makeText(this.mContext, "服务器未响应，请稍后再试", 0).show();
        return;
      }
      Toast.makeText(this.mContext, "服务器未能返回数据，请稍后再试", 0).show();
      return;
      this.isLoading = false;
      this.mListView.onRefreshComplete();
    }
    while (paramString1 == null);
    try
    {
      ForumsContent localForumsContent1 = (ForumsContent)UrlFactory.getInstanceGson().fromJson(paramString1, ForumsContent.class);
      if ((localForumsContent1 != null) && (localForumsContent1.getResult().equals("true")))
      {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(2, localForumsContent1.getForums()));
        return;
      }
    }
    catch (Exception localException1)
    {
      localException1.printStackTrace();
      Toast.makeText(this.mContext, "服务器未响应，请稍后再试", 0).show();
      return;
    }
    Toast.makeText(this.mContext, "服务器未能返回数据，请稍后再试", 0).show();
  }

  protected void setContentView()
  {
    setContentView(2130903042);
  }

  class AllSectionHandler extends Handler
  {
    AllSectionHandler()
    {
    }

    public void handleMessage(Message paramMessage)
    {
      super.handleMessage(paramMessage);
      switch (paramMessage.what)
      {
      default:
        return;
      case 1:
        AllSectionActivity.this.list.clear();
        AllSectionActivity.this.list.addAll((List)paramMessage.obj);
        AllSectionActivity.this.mAdapter.notifyDataSetChanged();
        return;
      case 2:
      }
      AllSectionActivity.this.list.addAll((List)paramMessage.obj);
      AllSectionActivity.this.mAdapter.notifyDataSetChanged();
    }
  }

  class CallBackListener
    implements OnClickListener, PullToRefreshBase.OnRefreshListener2<ListView>, OnItemClickListener
  {
    CallBackListener()
    {
    }

    public void onClick(View paramView)
    {
      switch (paramView.getId())
      {
      default:
        return;
      case 2131099701:
      }
      AllSectionActivity.this.finish(2130968578, 2130968582);
    }

    public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
    {
      Bundle localBundle = new Bundle();
      localBundle.putSerializable("receive_code", (Serializable)AllSectionActivity.this.list.get(paramInt - 1));
      AllSectionActivity.this.startNewActivity(SectionDetailActivity.class, 2130968581, 2130968579, false, localBundle);
    }

    public void onPullDownToRefresh(PullToRefreshBase<ListView> paramPullToRefreshBase)
    {
      if (!AllSectionActivity.this.isRefresh)
      {
        AllSectionActivity.this.pageNum = 1;
        AllSectionActivity.this.paramter[0] = AllSectionActivity.access$5(AllSectionActivity.this).getUserId();
        AllSectionActivity.this.paramter[1] = AllSectionActivity.access$5(AllSectionActivity.this).getProId();
        AllSectionActivity.this.paramter[2] = AllSectionActivity.access$5(AllSectionActivity.this).getMajorId();
        AllSectionActivity.this.paramter[3] = String.valueOf(AllSectionActivity.access$6(AllSectionActivity.this));
        AllSectionActivity.this.paramter[4] = String.valueOf(10);
        AllSectionActivity.this.isRefresh = true;
        UrlFactory.getInstance().getAllSection().constructUrl(AllSectionActivity.this, AllSectionActivity.this.paramter, 3, AllSectionActivity.this.mContext);
      }
    }

    public void onPullUpToRefresh(PullToRefreshBase<ListView> paramPullToRefreshBase)
    {
      if (!AllSectionActivity.this.isLoading)
      {
        AllSectionActivity localAllSectionActivity = AllSectionActivity.this;
        localAllSectionActivity.pageNum = (1 + localAllSectionActivity.pageNum);
        AllSectionActivity.this.paramter[0] = AllSectionActivity.access$5(AllSectionActivity.this).getUserId();
        AllSectionActivity.this.paramter[1] = AllSectionActivity.access$5(AllSectionActivity.this).getProId();
        AllSectionActivity.this.paramter[2] = AllSectionActivity.access$5(AllSectionActivity.this).getMajorId();
        AllSectionActivity.this.paramter[3] = String.valueOf(AllSectionActivity.access$6(AllSectionActivity.this));
        AllSectionActivity.this.paramter[4] = String.valueOf(10);
        AllSectionActivity.this.isLoading = true;
        UrlFactory.getInstance().getAllSection().constructUrl(AllSectionActivity.this, AllSectionActivity.this.paramter, 4, AllSectionActivity.this.mContext);
      }
    }
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.activity.AllSectionActivity
 * JD-Core Version:    0.6.0
 */