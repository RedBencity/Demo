package com.example.ben.demo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Parcelable;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * @Description 引导页
 * @author hongbencheng
 * @version 1.0.0 2015-12-14
 * @reviewer
 */

public class GuideActivity extends Activity {

    private ViewPager viewPager;
    private ArrayList<View> pageViews;
    private ImageView[] imageViews;
    private ViewGroup main, group;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        LayoutInflater inflater = getLayoutInflater();
        pageViews = new ArrayList<View>();
        pageViews.add(inflater.inflate(R.layout.viewpager1, null));
        pageViews.add(inflater.inflate(R.layout.viewpager2, null));
        pageViews.add(inflater.inflate(R.layout.viewpager3, null));
//        pageViews.add(inflater.inflate(R.layout.item04, null));

        //有几个布局页面就有几个圆点图片
        imageViews = new ImageView[pageViews.size()];
        main = (ViewGroup) inflater.inflate(R.layout.activity_guide, null);

        // group是R.layou.main中的负责包裹小圆点的LinearLayout.
        group = (ViewGroup) main.findViewById(R.id.viewGroup);

        viewPager = (ViewPager) main.findViewById(R.id.guidePages);
        //通过for循环设置圆点图片布局
        for (int i = 0; i < pageViews.size(); i++) {
            imageView = new ImageView(GuideActivity.this);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(20, 20));
            imageView.setPadding(25, 25, 25, 25);
            imageViews[i] = imageView;
            if (i == 0) {
                //默认选中第一张图片
                imageViews[i].setBackgroundResource(R.mipmap.page_indicator);
            } else {
                imageViews[i].setBackgroundResource(R.mipmap.page_indicator_focused);
            }
            ImageView imageViewjj = new ImageView(GuideActivity.this);
            imageViewjj.setLayoutParams(new ViewGroup.LayoutParams(20, 20));
            group.addView(imageViews[i]);
            group.addView(imageViewjj);
        }

        setContentView(main);
        viewPager.setAdapter(new GuidePageAdapter());
        viewPager.setOnPageChangeListener(new GuidePageChangeListener());
    }


    /** 指引页面Adapter */
    class GuidePageAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return pageViews.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public int getItemPosition(Object object) {
            // TODO Auto-generated method stub
            return super.getItemPosition(object);
        }

        @Override
        public void destroyItem(View arg0, int arg1, Object arg2) {
            // TODO Auto-generated method stub
            ((ViewPager) arg0).removeView(pageViews.get(arg1));
        }

        @Override
        public Object instantiateItem(View arg0, int arg1) {
            // TODO Auto-generated method stub
            ((ViewPager) arg0).addView(pageViews.get(arg1));
            return pageViews.get(arg1);
        }

        @Override
        public void restoreState(Parcelable arg0, ClassLoader arg1) {
            // TODO Auto-generated method stub
        }

        @Override
        public Parcelable saveState() {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public void startUpdate(View arg0) {
            // TODO Auto-generated method stub

        }

        @Override
        public void finishUpdate(View arg0) {
            // TODO Auto-generated method stub

        }
    }

    /** 指引页面改监听器 */
    class GuidePageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int arg0) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
            // TODO Auto-generated method stub

        }
        //在指引页面更改事件监听器(GuidePageChangeListener)中要确保在切换页面时下面的圆点图片也跟着改变
        @Override
        public void onPageSelected(int arg0) {
            for (int i = 0; i < imageViews.length; i++) {
                imageViews[arg0]
                        .setBackgroundResource(R.mipmap.page_indicator);
                if (arg0 != i) {
                    imageViews[i]
                            .setBackgroundResource(R.mipmap.page_indicator_focused);
                }
            }

        }
    }
}
