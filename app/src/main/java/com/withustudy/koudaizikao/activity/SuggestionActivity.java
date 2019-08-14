package com.withustudy.koudaizikao.activity;

import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.gson.Gson;
import com.withustudy.koudaizikao.action.AbsSimpleImplUrlFactory;
import com.withustudy.koudaizikao.action.UrlFactory;
import com.withustudy.koudaizikao.base.AbsBaseActivity;
import com.withustudy.koudaizikao.config.KouDaiSP;
import com.withustudy.koudaizikao.entity.content.ResultStatus;
import com.withustudy.koudaizikao.entity.req.SuggestionUpload;
import com.withustudy.koudaizikao.tools.ProTools;
import com.withustudy.koudaizikao.tools.ToolsUtils;
import java.util.Map;

public class SuggestionActivity extends AbsBaseActivity
{
  public static final int ACTION_UPLOAD = 10;
  public static final int UPLOAD_FINISH = 1;
  private Button buttonBack;
  private Button buttonSubmit;
  private EditText edit;
  private CallBackListener mBackListener;
  private SuggestHandler mHandler;

  protected void bindData()
  {
  }

  protected void initData()
  {
    this.mBackListener = new CallBackListener();
    this.mHandler = new SuggestHandler();
  }

  protected void initListener()
  {
    this.buttonBack.setOnClickListener(this.mBackListener);
    this.buttonSubmit.setOnClickListener(this.mBackListener);
  }

  protected void initView()
  {
    this.buttonBack = ((Button)findViewById(2131099997));
    this.edit = ((EditText)findViewById(2131100000));
    this.buttonSubmit = ((Button)findViewById(2131100001));
  }

  public void onError(String paramString1, String paramString2, int paramInt)
  {
    super.onError(paramString1, paramString2, paramInt);
    this.mProTools.dismissDislog();
  }

  public void onSuccess(String paramString1, Map<String, String> paramMap, String paramString2, int paramInt)
  {
    super.onSuccess(paramString1, paramMap, paramString2, paramInt);
    this.mProTools.dismissDislog();
    switch (paramInt)
    {
    default:
      return;
    case 10:
    }
    try
    {
      ResultStatus localResultStatus = (ResultStatus)UrlFactory.getInstanceGson().fromJson(paramString1, ResultStatus.class);
      if ((localResultStatus != null) && (localResultStatus.getStatus().equals("NEWS_STATUS_OK")))
      {
        this.mHandler.sendEmptyMessage(1);
        return;
      }
    }
    catch (Exception localException)
    {
      Toast.makeText(getApplicationContext(), "服务器未响应，请稍后再试", 0).show();
      return;
    }
    Toast.makeText(getApplicationContext(), "服务器未能返回数据，请稍后再试", 0).show();
  }

  protected void setContentView()
  {
    setContentView(2130903083);
  }

  class CallBackListener
    implements OnClickListener
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
      case 2131099997:
        SuggestionActivity.this.finish(2130968578, 2130968582);
        return;
      case 2131100001:
      }
      if (SuggestionActivity.this.edit.getText().toString().equals(""))
      {
        Toast.makeText(SuggestionActivity.this.mContext, "请说点什么吧", 0).show();
        return;
      }
      SuggestionUpload localSuggestionUpload = new SuggestionUpload();
      localSuggestionUpload.setClientType(ToolsUtils.getSDK());
      localSuggestionUpload.setImei(ToolsUtils.getImei(SuggestionActivity.this.mContext));
      localSuggestionUpload.setNet(ToolsUtils.getStringNetworkType(SuggestionActivity.this.mContext));
      localSuggestionUpload.setVersionName(SuggestionActivity.this.mSP.getVersionName());
      localSuggestionUpload.setUid(SuggestionActivity.this.mSP.getUserId());
      localSuggestionUpload.setContent(SuggestionActivity.this.edit.getText().toString());
      SuggestionActivity.this.mProTools.startDialog();
      UrlFactory.getInstance().suggestion().constructUrl(SuggestionActivity.this, localSuggestionUpload, 10);
    }
  }

  class SuggestHandler extends Handler
  {
    SuggestHandler()
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
      }
      Toast.makeText(SuggestionActivity.this.mContext, "感谢您的建议！您的满意是我们唯一的目标！", 0).show();
      SuggestionActivity.this.finish();
    }
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.activity.SuggestionActivity
 * JD-Core Version:    0.6.0
 */