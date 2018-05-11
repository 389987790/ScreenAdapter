package com.example.kongjian.screenadapter;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.lang.reflect.Field;

/**
 * Created by user on 2018/5/9.
 */

public class UIUtils {
    Context mContext;
    public static final float STANDARD_WIDTH = 1080f;
    public static final float STANDARD_HEIGHT = 1920f;
    private static final String DIMEN_CLASS = "com.android.internal.R$dimen";
    //设备的实际分辨率
    public static float displayMetricsWidth;
    public static float displayMetricsHeight;

    private static UIUtils uiUtils;
    public static UIUtils getInstance(Context context) {
        if (uiUtils == null) {
            uiUtils = new UIUtils(context);
        }
        return uiUtils;
    }

    private UIUtils(Context context) {
        this.mContext = context;
        WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (displayMetricsWidth == 0.0f || displayMetricsHeight == 0.0f) {
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);

        }
        //获取状态栏的高度
        int systemBarHeight = getSystemHeight(mContext);

        //平板
        if (displayMetrics.widthPixels > displayMetrics.heightPixels) {
            this.displayMetricsWidth = displayMetrics.heightPixels - systemBarHeight;
            this.displayMetricsHeight = displayMetrics.widthPixels;
        }else{
            this.displayMetricsHeight = displayMetrics.heightPixels - systemBarHeight;
            this.displayMetricsWidth = displayMetrics.widthPixels;
        }
    }

    private int getSystemHeight(Context context) {
        return getValue(context, DIMEN_CLASS, "system_bar_height",48);
    }

    private int getValue(Context context, String attrGroupClass, String system_bar_height,int defaultValue) {
        try {
            Class c = Class.forName(attrGroupClass);
            Object o = c.newInstance();
            Field field = c.getField(system_bar_height);
            int z = Integer.parseInt(field.get(o).toString());
            return context.getResources().getDimensionPixelOffset(z);
        } catch (Exception e) {
            e.printStackTrace();
            return defaultValue;
        }
    }
    //获取缩放比
    public float getHorizontalScaleValue() {
        return ((float)this.displayMetricsWidth) / STANDARD_WIDTH;
    }
    public float getVeticalScaleValue() {
        return ((float)this.displayMetricsHeight) / (STANDARD_HEIGHT-getSystemHeight(mContext));
    }
}
