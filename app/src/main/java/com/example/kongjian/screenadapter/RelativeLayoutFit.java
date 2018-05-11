package com.example.kongjian.screenadapter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

/**
 * Created by kongjian on 2018/5/9.
 */

public class RelativeLayoutFit extends RelativeLayout {
    private static boolean isMeasure = true;
    public RelativeLayoutFit(Context context) {
        super(context);
    }

    public RelativeLayoutFit(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RelativeLayoutFit(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    //可以在这里进行现有属性和新加属性的操作
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (isMeasure) {
            isMeasure = false;
            //获取所有控件总数
            int count = getChildCount();
            //获取缩放比
            float scaleX = UIUtils.getInstance(getContext()).getHorizontalScaleValue();
            float scaleY = UIUtils.getInstance(getContext()).getVeticalScaleValue();
            for (int i= 0;i<count;i++) {
                View child = getChildAt(i);
                //获取布局参数
                LayoutParams layoutParams = (LayoutParams) child.getLayoutParams();
                //开ViewGroup.LayoutParams始完成缩放
                layoutParams.width = (int) (layoutParams.width * scaleX);
                layoutParams.height = (int) (layoutParams.height * scaleY);
                layoutParams.leftMargin = (int) (layoutParams.leftMargin * scaleX);
                layoutParams.rightMargin = (int) (layoutParams.rightMargin * scaleX);
                layoutParams.topMargin = (int) (layoutParams.topMargin * scaleY);
                layoutParams.bottomMargin = (int) (layoutParams.bottomMargin * scaleY);
            }
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
    //帮助我们把自己定义的属性封装到系统的param中
    @Override
    public RelativeLayout.LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }
    public static class LayoutParams extends RelativeLayout.LayoutParams{
        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
        }

        public LayoutParams(int w, int h) {
            super(w, h);
        }

        public LayoutParams(ViewGroup.LayoutParams source) {
            super(source);
        }

        public LayoutParams(MarginLayoutParams source) {
            super(source);
        }

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        public LayoutParams(RelativeLayout.LayoutParams source) {
            super(source);
        }
    }
}
