package com.zxy.http.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import androidx.core.view.ViewCompat;

/**
 * Created by ZXY on 2019/9/5 11:52.
 * Class functions
 * *********************************************************
 * * @类名:ScreenUtils * @类描述:屏幕工具类 * @修改人: * @修改时间: * @修改备注: * @版本:
 * *********************************************************
 */
public class ScreenUtils {


    public static void setTopHeight(View view, Context mContext) {

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            return;
        }
        ViewGroup.LayoutParams params = view.getLayoutParams();
        int height = getStatusBarHeight(mContext);
        params.height = height;

    }

    /**
     * 获取导航栏高度
     * @param context
     * @return
     */
    public static int getDaoHangHeight(Context context) {
        int result = 0;
        int resourceId=0;
        int rid = context.getResources().getIdentifier("config_showNavigationBar", "bool", "android");
        if (rid!=0){
            resourceId = context.getResources().getIdentifier("navigation_bar_height", "dimen", "android");
            return context.getResources().getDimensionPixelSize(resourceId);
        }else
            return 0;
    }

    /**
     * * @方法说明:获取DisplayMetrics对象 * @方法名称:getDisPlayMetrics * @param context
     * * @return * @返回值:DisplayMetrics
     */
    public static DisplayMetrics getDisPlayMetrics(Context context) {
        DisplayMetrics metric = new DisplayMetrics();
        if (null != context) {
            ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(metric);
        }
        return metric;
    }

    /**
     * * @方法说明:获取屏幕的宽度（像素） * @方法名称:getScreenWidth * @param context * @return
     * * @返回值:int
     */
    public static int getScreenWidth(Context context) {
        int width = getDisPlayMetrics(context).widthPixels;
        return width;
    }

    /**
     * * @方法说明:获取屏幕的高（像素） * @方法名称:getScreenHeight * @param context * @return
     * * @返回值:int
     */
    public static int getScreenHeight(Context context) {
        int height = getDisPlayMetrics(context).heightPixels;
        return height;
    }

    /**
     * * @方法说明:屏幕密度(0.75 / 1.0 / 1.5) * @方法名称:getDensity * @param context
     * * @return * @返回 float
     */
    public static float getDensity(Context context) {
        float density = getDisPlayMetrics(context).density;
        return density;
    }

    /**
     * * @方法说明:屏幕密度DPI(120 / 160 / 240) * @方法名称:getDensityDpi * @param context
     * * @return * @返回 int
     */
    public static int getDensityDpi(Context context) {
        int densityDpi = getDisPlayMetrics(context).densityDpi;
        return densityDpi;
    }

    public static float dpToPx(Context context, float dp) {
        if (context == null) {
            return -1;
        }
        return dp * context.getResources().getDisplayMetrics().density;
    }

    public static int dpToPxInt(Context context, float dp) {
        return (int) (dpToPx(context, dp) + 0.5f);
    }

    public static Bitmap zoomBitmap(Bitmap bitmap, int w, int h) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        float scaleWidht = ((float) w / width);
        float scaleHeight = ((float) h / height);
        matrix.postScale(scaleWidht, scaleHeight);
        Bitmap newbmp = Bitmap.createBitmap(bitmap, 0, 0, width, height,
                matrix, true);
        return newbmp;
    }

    public static int getStatusBarHeight(Context mContext) {
        int result = 0;
        int resId = mContext.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resId > 0) {
            result = mContext.getResources().getDimensionPixelSize(resId);
        }
        return result;
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static void imageBehindBar(Activity context){

        Window window = context.getWindow();
        //设置透明状态栏,这样才能让 ContentView 向上
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        //设置状态栏颜色
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(0x00000000);
        }

        ViewGroup mContentView = (ViewGroup) context.findViewById(Window.ID_ANDROID_CONTENT);
        View mChildView = mContentView.getChildAt(0);
        if (mChildView != null) {
            //注意不是设置 ContentView 的 FitsSystemWindows, 而是设置 ContentView 的第一个子 View . 使其不为系统 View 预留空间.
            ViewCompat.setFitsSystemWindows(mChildView, false);
        }
    }


}
