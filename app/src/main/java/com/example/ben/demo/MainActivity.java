package com.example.ben.demo;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener {
    private ImageView icon_home_practice, icon_home_discovery, icon_home_misc;
    private TextView home_practice, home_discovery, home_misc;
    private RelativeLayout one,two,three;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        icon_home_practice = (ImageView) findViewById(R.id.icon_home_practice);
        icon_home_discovery = (ImageView) findViewById(R.id.icon_home_discovery);
        icon_home_misc = (ImageView) findViewById(R.id.icon_home_misc);
        home_practice = (TextView) findViewById(R.id.home_practice);
        home_discovery = (TextView) findViewById(R.id.home_discovery);
        home_misc = (TextView) findViewById(R.id.home_misc);
        one = (RelativeLayout)findViewById(R.id.one);
        two = (RelativeLayout)findViewById(R.id.two);
        three = (RelativeLayout)findViewById(R.id.three);
        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.one:
                setChoiceItem(0);
                break;
            case R.id.two:
                setChoiceItem(1);
                break;
            case R.id.three:
                setChoiceItem(2);
                break;
        }
    }

    private void setChoiceItem(int index) {
        clearChoice();
        switch (index) {
            case 0:
                home_practice.setTextColor(getResources().getColor(R.color.theme_color));
                icon_home_practice.setImageResource(R.mipmap.icon_home_practice_checked);
                break;
            case 1:
                home_discovery.setTextColor(getResources().getColor(R.color.theme_color));
                icon_home_discovery.setImageResource(R.mipmap.icon_home_discovery_checked);
                break;
            case 2:
                home_misc.setTextColor(getResources().getColor(R.color.theme_color));
                icon_home_misc.setImageResource(R.mipmap.icon_home_misc_checked);
                break;
        }
    }

    private void clearChoice() {
        home_practice.setTextColor(getResources().getColor(R.color.gray));
        icon_home_practice.setImageResource(R.mipmap.icon_home_practice);

        home_discovery.setTextColor(getResources().getColor(R.color.gray));
        icon_home_discovery.setImageResource(R.mipmap.icon_home_discovery);

        home_misc.setTextColor(getResources().getColor(R.color.gray));
        icon_home_misc.setImageResource(R.mipmap.icon_home_misc);
    }

    long mMillis;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis() - mMillis > 2000) {
                mMillis = System.currentTimeMillis();
                Toast.makeText(MainActivity.this, "再次点击返回键，将退出程序",
                        Toast.LENGTH_SHORT).show();
                return false;
            } else {
                try {
                    android.os.Process.killProcess(android.os.Process.myPid());
                } catch (Exception e) {
                    e.printStackTrace();

                    Log.e("cbjtnews", e.toString());
                }
                return true;
            }
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }
}
