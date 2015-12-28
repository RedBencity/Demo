package com.example.ben.demo;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import java.util.ArrayList;

public class QuestionActivity extends Activity {

    private ViewPager viewPager;
    private ArrayList<View> pageViews;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        initView();
        getData();

    }

    private void initView(){
        pageViews = new ArrayList<View>();
        viewPager = (ViewPager)findViewById(R.id.viewpager);


    }

    private void getData(){
        for (int i=0;i<10;i++){
            View view = LayoutInflater.from(this).inflate(R.layout.question,null);
            TextView textView = (TextView)view.findViewById(R.id.question_position);
            textView.setText(i+1+"");
            pageViews.add(view);
        }
        viewPager.setAdapter(new QuestionAdapter());
    }

    class QuestionAdapter extends PagerAdapter{
        @Override
        public int getCount() {
            return pageViews.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        //滑动切换的时候销毁当前的组件
        @Override
        public void destroyItem(ViewGroup container, int position,
                                Object object) {
            ((ViewPager) container).removeView(pageViews.get(position));
        }
        //每次滑动的时候生成的组件
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ((ViewPager) container).addView(pageViews.get(position));
            return pageViews.get(position);
        }
    }

}
