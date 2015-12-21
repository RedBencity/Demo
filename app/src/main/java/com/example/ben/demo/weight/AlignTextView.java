package com.example.ben.demo.weight;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by hongbencheng on 2015/12/16 0016.
 *
 * @author hongbencheng
 * @Description 两端对齐的TextView
 */
public class AlignTextView extends TextView {
    //view的坐标
    private int top, left, right, bottom;

    //屏幕像素密度
    private float density;
    //view宽和高
    private int width = 0, height;
    //text的字体
    float textSize = getTextSize();

    //字的颜色
    ColorStateList textColor;
    TextPaint textPaint;

    public AlignTextView(Context context) {
        super(context);
    }

    public AlignTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public AlignTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        textColor = getTextColors();
        textPaint = getPaint();
        int color = textColor.getColorForState(getDrawableState(), 0);
        textPaint.setColor(color);

        density = getResources().getDisplayMetrics().density;
        // float textPaintWidth = textPaint.measureText(getText().toString());
        // Log.i("测试字符串:", textPaintWidth + " " + density + " 字大小" + textSize);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = measureWidth(widthMeasureSpec);
        int heightSize = measureHeight(heightMeasureSpec);
        // Log.i("onMeasure", widthSize + " " + heightSize);
        setMeasuredDimension(widthSize, heightSize);
//          super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

    private int measureWidth(int widthMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        if (widthMode == MeasureSpec.EXACTLY) {
        } else if (widthMode == MeasureSpec.AT_MOST) {
        } else if (widthMode == MeasureSpec.UNSPECIFIED) {
        }

        return widthSize;
    }

    private int measureHeight(int heightMeasureSpec) {
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        //width = getResources().getDisplayMetrics().widthPixels - getCompoundPaddingLeft() - getCompoundPaddingRight();
        String text = getText().toString();
        textChar = text.toCharArray();
        //一行文本的宽度
        float sentenceWidth = getCompoundPaddingLeft();
        float sentenceHeight = textSize;
        int lines = 0;
        if (width != 0) {
            for (int i = 0; i < textChar.length; i++) {
                if (sentenceWidth + textPaint.measureText(String.valueOf(textChar[i])) > width) {
                    sentenceWidth = getCompoundPaddingLeft();
                    lines++;
                }
                float wordWidth = textPaint.measureText(String.valueOf(textChar[i]));
                sentenceWidth += wordWidth;
            }
            if (heightMode == MeasureSpec.UNSPECIFIED) {
                lines++;
                heightSize = (int) (lines * sentenceHeight) + (int) (5 * density);
                Log.i("UNSPECIFIED", heightSize + " ");
            } else if (heightMode == MeasureSpec.AT_MOST) {
                Log.i("AT_MOST", heightSize + " ");
            } else if (heightMode == MeasureSpec.EXACTLY) {
                Log.i("EXACTLY", heightSize + " ");
            } else {
                heightSize = 0;
            }
        }
        return heightSize;
    }

    //把文本内容转换成字符数组
    private char[] textChar;

    @Override
    protected void onDraw(Canvas canvas) {
        //获取view内容
        String text = getText().toString();

        //view的内边距
        final int compoundPaddingLeft = getCompoundPaddingLeft();
//        final int compoundPaddingTop = getCompoundPaddingTop();
//        final int compoundPaddingRight = getCompoundPaddingRight();
//        final int compoundPaddingBottom = getCompoundPaddingBottom();
        // Log.i("compoundPaddingLeft", compoundPaddingLeft + "");

        textChar = text.toCharArray();
        //文本的总长度
        float textPaintWidth = textPaint.measureText(text);
        //一行文本的宽度
        float sentenceWidth = compoundPaddingLeft;
        float sentenceHeight = textSize;
        //用来存储每行的剩余控件平摊数值
        float[] spaces = new float[(int) (textPaintWidth / width) + 20];
        //储存每行最后一个字的位置
        int[] position = new int[spaces.length];
        //标记每行的末尾字位置
        int mark = 0;
        //计算行数
        int lineCount = 0;
        //计算平摊值
        for (int i = 0; i < textChar.length; i++) {
            if (sentenceWidth + textPaint.measureText(String.valueOf(textChar[i])) > width) {
                // Log.i("sentence", sentenceWidth + " 末尾位置 " + (i - 1) + " 末尾字 " + textChar[i - 1] + " 一行几个字 " + (i - mark) + "   残余值  " + (width - sentenceWidth));
                float space = (width - sentenceWidth) / (i - 1 - mark);
                position[lineCount] = i - 1;
                spaces[lineCount++] = space;
                sentenceWidth = compoundPaddingLeft;
                mark = i;
            }
            float wordWidth = textPaint.measureText(String.valueOf(textChar[i]));
            sentenceWidth += wordWidth;
        }

        sentenceWidth = compoundPaddingLeft;
        lineCount = 0;

        //画出文本
        for (int i = 0; i < textChar.length; i++) {

            if (sentenceWidth + textPaint.measureText(String.valueOf(textChar[i])) > width + 1) {
                sentenceWidth = compoundPaddingLeft;
                sentenceHeight += textSize;
                lineCount++;
            }
            if (i == position[lineCount]) {
                float wordWidth = textPaint.measureText(String.valueOf(textChar[i]));
                canvas.drawText(String.valueOf(textChar[i]), sentenceWidth, sentenceHeight, textPaint);
                sentenceWidth += wordWidth;
            } else {
                float wordWidth = textPaint.measureText(String.valueOf(textChar[i]));
                canvas.drawText(String.valueOf(textChar[i]), sentenceWidth, sentenceHeight, textPaint);
                sentenceWidth += wordWidth + spaces[lineCount];
            }
        }
        // Log.i("测试字符串:", textPaintWidth + " " + textPaint.measureText("哈") + " " + textPaint.measureText("。") + " " + textPaint.measureText("a") + " " + textPaint.measureText(")"));
        // Log.i("onDraw", left + " " + top + " " + right + " " + bottom + " " + width + " " + height);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
        this.width = right - left - getCompoundPaddingRight();
        this.height = bottom - top;
    }


}
