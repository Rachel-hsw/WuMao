package com.withustudy.koudaizikao.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class VpSimpleFragment extends Fragment
{
  public static final String BUNDLE_TITLE = "title";
  private String mTitle = "DefaultValue";

  public static VpSimpleFragment newInstance(String paramString)
  {
    Bundle localBundle = new Bundle();
    localBundle.putString("title", paramString);
    VpSimpleFragment localVpSimpleFragment = new VpSimpleFragment();
    localVpSimpleFragment.setArguments(localBundle);
    return localVpSimpleFragment;
  }

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    Bundle localBundle = getArguments();
    if (localBundle != null)
      this.mTitle = localBundle.getString("title");
    TextView localTextView = new TextView(getActivity());
    localTextView.setText(this.mTitle);
    localTextView.setGravity(17);
    return localTextView;
  }
}

/* Location:           E:\fanbianyi\zikao1.0\1.0.1.0_classes_dex2jar.jar
 * Qualified Name:     com.withustudy.koudaizikao.fragment.VpSimpleFragment
 * JD-Core Version:    0.6.0
 */