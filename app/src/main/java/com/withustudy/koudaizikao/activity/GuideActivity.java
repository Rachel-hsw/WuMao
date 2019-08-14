package com.withustudy.koudaizikao.activity;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.withustudy.koudaizikao.R;
import com.withustudy.koudaizikao.base.AbsBaseActivity;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends AbsBaseActivity {
    private final int[] center = {158, 214, 154, 253};
    private int currentIndex;
    private View[] guideView;
    private ImageView[] image;
    private ImageView[] imageDots;
    private List<View> list = new ArrayList();
    private CallBackListener mBackListener;
    private ViewPager mViewPager;
    private int[] margin;
    private final double[] scale = {0.795D, 0.9300000000000001D, 0.675D, 0.768D};

    private void initDot(int paramInt) {
        int i = 0;
        if (i >= 4)
            return;
        switch (paramInt) {
            default:
            case 0:
            case 1:
            case 2:
            case 3:
        }
        while (true) {
            i++;
            this.imageDots[i].setBackgroundResource(R.drawable.guide_dots1);
        }
    }

    private void initImage() {
        for (int i = 0; ; i++) {
            if (i >= 4)
                return;
            LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(this.mSP.getWidth() - this.margin[i], (int) ((this.mSP.getWidth() - this.margin[i]) / this.scale[i]));
            localLayoutParams.setMargins(this.margin[i], 0, 0, 0);
            this.image[i].setLayoutParams(localLayoutParams);
        }
    }

    private void initMargin() {
        int i = 0;
        if (i >= 4)
            this.margin = new int[4];
        for (int j = 0; ; j++) {
            if (j >= 4) {
                this.center[i] = (this.mSP.getWidth() * this.center[i] / 720);
                i++;
                break;
            }
            this.margin[j] = (this.mSP.getWidth() / 2 - this.center[j]);
        }
    }

    private void setDot() {
        switch (this.currentIndex) {
            default:
            case 0:
            case 1:
            case 2:
            case 3:
        }
        while (true) {
            this.imageDots[this.currentIndex].setBackgroundResource(R.drawable.guide_dots);
            initDot(0);
        }
    }

    protected void bindData() {
        this.mViewPager.setAdapter(new MyPagerAdapter());
    }

    protected void initData() {
        this.mBackListener = new CallBackListener();
        this.list = new ArrayList();
        this.list.add(this.guideView[0]);
        this.list.add(this.guideView[1]);
        this.list.add(this.guideView[2]);
        this.list.add(this.guideView[3]);
    }

    protected void initListener() {
        this.mViewPager.setOnPageChangeListener(this.mBackListener);
    }

    protected void initView() {
        this.mViewPager = ((ViewPager) findViewById(R.id.splash_vp));
        this.guideView = new View[4];
        this.guideView[0] = View.inflate(getApplicationContext(), R.layout.guide_page_one, null);
        this.guideView[1] = View.inflate(getApplicationContext(), R.layout.guide_page_two, null);
        this.guideView[2] = View.inflate(getApplicationContext(), R.layout.guide_page_three, null);
        this.guideView[3] = View.inflate(getApplicationContext(), R.layout.guide_page_four, null);
        this.imageDots = new ImageView[4];
        this.imageDots[0] = ((ImageView) findViewById(R.id.image_guide_dots1));
        this.imageDots[1] = ((ImageView) findViewById(R.id.image_guide_dots2));
        this.imageDots[2] = ((ImageView) findViewById(R.id.image_guide_dots3));
        this.imageDots[3] = ((ImageView) findViewById(R.id.image_guide_dots4));
        this.image = new ImageView[4];
        this.image[0] = ((ImageView) this.guideView[0].findViewById(R.id.image_guide1));
        this.image[1] = ((ImageView) this.guideView[1].findViewById(R.id.image_guide2));
        this.image[2] = ((ImageView) this.guideView[2].findViewById(R.id.image_guide3));
        this.image[3] = ((ImageView) this.guideView[3].findViewById(R.id.image_guide4));
        initMargin();
        initImage();
    }

    protected void setContentView() {
        setContentView(R.layout.activity_splash);
    }

    class CallBackListener
            implements OnPageChangeListener {
        CallBackListener() {
        }

        public void onPageScrollStateChanged(int paramInt) {
            if ((GuideActivity.this.currentIndex == 3) && (paramInt == 1)) {
                GuideActivity.this.mSP.setFirst(false);
                GuideActivity.this.startNewActivity(SkipActivity.class, true, null);
            }
        }

        public void onPageScrolled(int paramInt1, float paramFloat, int paramInt2) {
        }

        public void onPageSelected(int paramInt) {
            GuideActivity.this.currentIndex = paramInt;
            GuideActivity.this.setDot();
        }
    }

    class MyPagerAdapter extends PagerAdapter {
        MyPagerAdapter() {
        }

        public void destroyItem(ViewGroup paramViewGroup, int paramInt, Object paramObject) {
            paramViewGroup.removeView((View) GuideActivity.this.list.get(paramInt));
        }

        public int getCount() {
            return GuideActivity.this.list.size();
        }

        public Object instantiateItem(ViewGroup paramViewGroup, int paramInt) {
            View localView = (View) GuideActivity.this.list.get(paramInt);
            ViewGroup localViewGroup = (ViewGroup) localView.getParent();
            if (localViewGroup != null)
                localViewGroup.removeAllViews();
            paramViewGroup.addView(localView);
            return localView;
        }

        public boolean isViewFromObject(View paramView, Object paramObject) {
            return paramView == paramObject;
        }
    }
}
