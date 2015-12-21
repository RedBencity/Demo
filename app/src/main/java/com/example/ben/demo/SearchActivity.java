package com.example.ben.demo;

        import android.app.Activity;
        import android.content.SharedPreferences;
        import android.graphics.Color;
        import android.os.Bundle;
        import android.text.TextPaint;
        import android.util.DisplayMetrics;
        import android.util.Log;
        import android.view.View;
        import android.widget.EditText;
        import android.widget.ImageView;
        import android.widget.RelativeLayout;
        import android.widget.TextView;

        import com.example.ben.demo.utils.CustomDialog;
        import com.example.ben.demo.utils.ToastManager;

        import java.util.ArrayList;
        import java.util.List;

public class SearchActivity extends Activity {

    private RelativeLayout search_record_area;
    private RelativeLayout search_record_clear;
    private TextView search_text;
    private EditText search_edit;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private ImageView search_back;

    private List<String> recordList;
    private List<TextView> textViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initView();
        getData();
    }

    private void initView() {
        search_record_area = (RelativeLayout) findViewById(R.id.search_record_area);
        search_record_clear = (RelativeLayout) findViewById(R.id.search_record_clear);
        search_text = (TextView) findViewById(R.id.search_text);
        search_edit = (EditText) findViewById(R.id.search_edit);
        search_back = (ImageView)findViewById(R.id.search_back);
        recordList = new ArrayList<String>();
        preferences = getSharedPreferences("search_record", MODE_PRIVATE);
        editor = preferences.edit();
    }

    private void getData() {
        searchRecordUI();


        search_back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                finish();
            }
        });

        search_text.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(!"".equals(search_edit.getText().toString())){
                    loadArrayList();
                    saveRecord(search_edit.getText().toString());
                }else{
                    ToastManager toastManager = ToastManager.getInstance();
                    toastManager.showToast(SearchActivity.this, "搜索记录不能为空");
                }
            }
        });
        search_record_clear.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (recordList.size() > 0) {
                    final CustomDialog customDialog = new CustomDialog(SearchActivity.this);
                    customDialog.setTitle("清除历史记录");
                    customDialog.setLeftText("确定");
                    customDialog.setRightText("取消");
                    customDialog.setDetial("是否清除历史记录?");
                    customDialog.setContentboolean(true);
                    customDialog.setLeftOnClick(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // TODO Auto-generated method stub
                            editor.clear();
                            editor.commit();
                            search_record_area.removeAllViews();
                            recordList.clear();

                            customDialog.dismiss();
                        }
                    });
                    customDialog.setRightOnClick(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // TODO Auto-generated method stub
                            customDialog.dismiss();
                        }
                    });
                    customDialog.show();
                } else {
                    ToastManager toastManager = ToastManager.getInstance();
                    toastManager.showToast(SearchActivity.this, "无历史记录");
                }

            }
        });

    }

    private void searchRecordUI() {
        DisplayMetrics dm = new DisplayMetrics();
        dm = getResources().getDisplayMetrics();
        float density = dm.density;
        int screenWidth = dm.widthPixels;
        int screenHeight = dm.heightPixels;
        TextView textView = null;
        TextPaint textPaint = null;
        textViews = new ArrayList<TextView>();
        int totalMarginLeftWidth = (int) (10 * density);
        int paddingWidth = 5;
        int marginLeftWidth = 10;
        int marginTopHeight = 10;
        int totalMarginTopHeight = 0;
        int mark = 0;

        loadArrayList();

        for (int i = recordList.size() - 1; i >= 0; i--) {
            textView = new TextView(this);
            textView.setText(recordList.get(i));
            textView.setBackgroundColor(Color.WHITE);
            textView.setPadding((int) (paddingWidth * density), (int) (paddingWidth * density), (int) (paddingWidth * density), (int) (paddingWidth * density));
            textViews.add(textView);
        }
        int lines = 0;
        for (int i = 0; i < textViews.size(); i++) {
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams.setMargins(totalMarginLeftWidth, totalMarginTopHeight, 0, 0);
            textViews.get(i).setLayoutParams(layoutParams);
            int width = getTextViewWidth(textViews.get(i));
            int height = getTextViewHeight(textViews.get(i));
            mark = totalMarginLeftWidth + width;
            totalMarginLeftWidth = mark + (int) (marginLeftWidth * density);
            Log.i("totalMaiginWidth", String.valueOf(totalMarginLeftWidth));
            if ((i + 1) < textViews.size()) {
                Log.i("totalMaigin", String.valueOf("aaaaaaaaa" + (totalMarginLeftWidth + getTextViewWidth(textViews.get(i + 1)))));
            }

            if ((i + 1) < textViews.size() && totalMarginLeftWidth + getTextViewWidth(textViews.get(i + 1)) >= screenWidth - (int) (marginLeftWidth * density * 2)) {
                totalMarginTopHeight += height + (int) (marginTopHeight * density);
                totalMarginLeftWidth = (int) (marginLeftWidth * density);
                lines++;

            }
            if (lines >= 3) {
                for (int j = 0; j <= recordList.size() - 1 - (i + 1); j++) {
                    recordList.remove(j);
                    editor.clear();
                    editor.commit();
                    saveArrayList();
                }
                break;
            }
            search_record_area.addView(textViews.get(i));
            final String text = textViews.get(i).getText().toString();
            textViews.get(i).setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    search_edit.setText("");
                    search_edit.setText(text);
                }
            });
        }
    }

    private int getTextViewWidth(TextView textView) {
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        textView.measure(w, h);
        return textView.getMeasuredWidth();
    }

    private int getTextViewHeight(TextView textView) {
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        textView.measure(w, h);
        return textView.getMeasuredHeight();
    }

    private void saveArrayList() {
        editor.putInt("list", recordList.size());
        for (int i = 0; i < recordList.size(); i++) {
            editor.remove("list" + i);
            editor.putString("list" + i, recordList.get(i));
        }
        editor.commit();
    }

    private void loadArrayList() {
        recordList.clear();
        int size = preferences.getInt("list", 0);
        for (int i = 0; i < size; i++) {
            recordList.add(preferences.getString("list" + i, null));
        }
    }

    private void saveRecord(String record) {
        if (!recordList.contains(record)) {
            editor.putString("list" + recordList.size(), record);
            editor.putInt("list", preferences.getInt("list", 0) + 1);
            editor.commit();
        }
    }
}
