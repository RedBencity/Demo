package com.example.ben.demo;

import android.app.Activity;
import android.graphics.Paint;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ben.demo.weight.AlignTextView;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class MainActivity extends Activity {
    private TextView textView;
    private AlignTextView alignTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.aaa);
        alignTextView =(AlignTextView)findViewById(R.id.bbb);
        String aa = "北京时间12月5日凌晨4点(当地时间14日晚间20点)，2015-16赛季英超第16轮一场焦点战，切尔西做客皇权球场挑战莱斯特城。上半场马赫雷斯助攻瓦尔迪破门，阿扎尔伤退，切尔西0-1落后；下半场马赫雷斯的进球为莱斯特城扩大领先优势，雷米替补上阵攻入一球，切尔西客场1-2不敌莱斯特城，遭遇英超2连败；莱斯特城回到联赛第1位。";
        String bb = "活到老a，学到老。人生犹如一大课堂，社会就是知识的海洋 ，有学不完的知识，用不完的智慧。所以，人一辈子都要做学生，活到老，学到老；向书本学，向他人学；从实践中学，从社会中学；学知识，学做人。毛泽东曾经说过，他一日不学习，赶不上少奇同志。古人云，不进则退！每个人都要有危机感，时刻保 持进取心。如果不思进取，就随时被淘汰的可能。如果放松学习，不充实自己的头脑，就会变得空虚无聊，人生的质量就要打折扣。";
        try {
            textView.setText(bb);
        } catch (Exception e) {
            e.printStackTrace();

        }
        alignTextView.setText(aa );
    }



}
