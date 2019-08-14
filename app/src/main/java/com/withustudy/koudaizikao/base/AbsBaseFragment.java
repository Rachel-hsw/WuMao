package com.withustudy.koudaizikao.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.http.FileDownLoad;
import com.android.http.RequestManager.RequestListener;
import com.umeng.analytics.MobclickAgent;
import com.withustudy.koudaizikao.R;
import com.withustudy.koudaizikao.config.KouDaiSP;
import com.withustudy.koudaizikao.tools.FileTools;
import com.withustudy.koudaizikao.tools.LogUtils;
import com.withustudy.koudaizikao.tools.ProTools;
import java.util.Map;

public abstract class AbsBaseFragment extends Fragment
  implements RequestManager.RequestListener
{
  protected Context mContext;
  protected FileDownLoad mFileDownLoad;
  protected FileTools mFileTools;
  protected FragmentManager mFragmentManager;
  protected ProTools mProTools;
  protected View mRootView;
  protected KouDaiSP mSP;

  public abstract void bindData();

  protected final void finish()
  {
    ((AbsBaseActivity)getActivity()).finish();
  }

  protected final void finish(int paramInt1, int paramInt2)
  {
    ((AbsBaseActivity)getActivity()).finish(paramInt1, paramInt2);
  }

  public View getRootView()
  {
    return this.mRootView;
  }

  public abstract void initData();

  public abstract void initListener();

  public abstract void initView(View paramView);

  public void onActivityCreated(Bundle paramBundle)
  {
    super.onActivityCreated(paramBundle);
    initData();
    bindData();
    initListener();
  }

  public final void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.mFragmentManager = getChildFragmentManager();
    this.mContext = getActivity();
    this.mProTools = ProTools.getInstance(this.mContext, R.style.DialogStyle);
    this.mFileTools = FileTools.getInstance(this.mContext);
    this.mFileDownLoad = FileDownLoad.getInstance(this.mContext);
    this.mSP = KouDaiSP.getInstance(this.mContext);
  }

  public final View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    if (this.mRootView == null)
    {
      this.mRootView = onInflaterRootView(paramLayoutInflater, paramBundle);
      if (this.mRootView == null)
        throw new NullPointerException();
      initView(this.mRootView);
    }
    while (true)
    {
      return this.mRootView;
      ViewGroup localViewGroup = (ViewGroup)this.mRootView.getParent();
      if (localViewGroup == null)
        continue;
      localViewGroup.removeView(this.mRootView);
    }
  }

  public void onError(String paramString1, String paramString2, int paramInt)
  {
    LogUtils.myLog("onError!");
    LogUtils.myLog("actionId----->" + paramInt);
    LogUtils.myLog(paramString1);
  }

  public void onHiddenChanged(boolean paramBoolean)
  {
    super.onHiddenChanged(paramBoolean);
  }

  public abstract View onInflaterRootView(LayoutInflater paramLayoutInflater, Bundle paramBundle);

  public void onPause()
  {
    super.onPause();
    if (!LogUtils.isDebug())
      MobclickAgent.onPageEnd("MainScreen");
  }

  public void onRequest()
  {
  }

  public void onResume()
  {
    super.onResume();
    if (!LogUtils.isDebug())
      MobclickAgent.onPageStart("MainScreen");
  }

  public void onStart()
  {
    super.onStart();
  }

  public void onSuccess(String paramString1, Map<String, String> paramMap, String paramString2, int paramInt)
  {
    LogUtils.myLog("OnSucess!");
    LogUtils.myLog("actionId----->" + paramInt);
    LogUtils.myLog("res----->" + paramString1);
  }

  protected final void startNewActivity(Class<? extends Activity> paramClass, int paramInt1, int paramInt2, boolean paramBoolean, Bundle paramBundle)
  {
    ((AbsBaseActivity)getActivity()).startNewActivity(paramClass, paramInt1, paramInt2, paramBoolean, paramBundle);
  }

  protected final void startNewActivity(Class<? extends Activity> paramClass, boolean paramBoolean, Bundle paramBundle)
  {
    ((AbsBaseActivity)getActivity()).startNewActivity(paramClass, paramBoolean, paramBundle);
  }

  protected final void startNewActivityForResult(Class<? extends Activity> paramClass, int paramInt1, int paramInt2, int paramInt3, Bundle paramBundle)
  {
    ((AbsBaseActivity)getActivity()).startNewActivityForResult(paramClass, paramInt1, paramInt2, paramInt3, paramBundle);
  }

  protected final void startNewActivityForResult(Class<? extends Activity> paramClass, int paramInt, Bundle paramBundle)
  {
    ((AbsBaseActivity)getActivity()).startNewActivityForResult(paramClass, paramInt, paramBundle);
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.base.AbsBaseFragment
 * JD-Core Version:    0.6.0
 */