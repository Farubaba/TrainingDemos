package com.android.training.app;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Parcel;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.URLSpan;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;

import com.android.training.basefeature.log.LogManager;

import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Android application 相关工具类
 *
 * @author violet
 * @date 2018/3/5 13:12
 */

public final class AppUtil {

    /**
     * 仅供本类中使用，其他地方使用请调用{@link #getAppContext()}
     */
    private static Context appContext;
    private static DisplayMetrics displayMetrics;
    private static WindowManager windowManager;

    private AppUtil() {

    }

    /**
     * 初始化ApplicationContext
     *
     * @param context
     */
    public static void init(Context context) {
        if (context != null) {
            appContext = context;
        }
        //不要的初始化
        initDisplayMetrics();
    }

    /**
     * 初始化DisplayMetrics和WindowManager
     */
    private static void initDisplayMetrics(){
        windowManager = (WindowManager) getAppContext().getSystemService(Context.WINDOW_SERVICE);
        displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
    }

    /**
     * 获得WindowManager
     * @return
     */
    public static WindowManager getWindowManager(){
        if(windowManager == null){
            initDisplayMetrics();
        }
        return windowManager;
    }

    /**
     * 获取设备的当前设备的DisplayMetrics
     * @return
     */
    public static DisplayMetrics getDisplayMetrics(){
        if(displayMetrics == null){
            initDisplayMetrics();
        }
        LogManager.getInstance().d("density", "dm.widthPixels: " + displayMetrics.widthPixels
                + ",dm.heightPixels: " + displayMetrics.heightPixels + ",dm.density: "
                + displayMetrics.density);
        return displayMetrics;
    }

    /**
     * 获取屏幕宽度
     * @return
     */
    public static int getWindowWidth(){
        return getDisplayMetrics().widthPixels;
    }

    /**
     * 获取屏幕高度
     * @return
     */
    public static int getWindowHeight(){
        return getDisplayMetrics().heightPixels;
    }

    /**
     * 全局可以使用的ApplicationContext {@link Context}
     *
     * @return
     */
    public static Context getAppContext() {
        return appContext;
    }

    /**
     * 获取当前进程的pid
     *
     * @return
     */
    public static int getCurrentProcessId() {
        return android.os.Process.myPid();
    }

    /**
     * 获取当前应用的packageName
     *
     * @return
     */
    public static String getPackageName() {
        return getAppContext().getPackageName();
    }

    /**
     * 获取{@link PackageInfo},以方便从中获取到更多app相关信息
     *
     * @return
     */
    public static PackageInfo getPackageInfo() {
        try {
            PackageInfo packageInfo = getAppContext().getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_ACTIVITIES);
            return packageInfo;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取当前App的versionCode值，如果发生错误，则返回默认值0
     *
     * @return
     */
    public static int getVersionCode() {
        PackageInfo packageInfo = getPackageInfo();
        if (packageInfo != null) {
            return packageInfo.versionCode;
        }
        return C.Integer.ZERO;
    }

    /**
     * 获取当前app的versionName，如果发生错误，则返回空字符串
     *
     * @return
     */
    public static String getVersionName() {
        PackageInfo packageInfo = getPackageInfo();
        if (packageInfo != null) {
            return packageInfo.versionName;
        }
        return C.Strings.EMPTY;
    }

    /**
     * 获得应用首次完全安装的时间
     *
     * @return
     */
    public static long getFirstInstallTime() {
        android.content.pm.PackageInfo pinfo = getPackageInfo();
        if (pinfo != null) {
            long code = pinfo.firstInstallTime;
            return code;
        }
        return 0L;
    }

    /**
     * 获得最近一次update安装的时间
     *
     * @return
     */
    public static long getLastUpdateInstallTime() {
        android.content.pm.PackageInfo pinfo = getPackageInfo();
        if (pinfo != null) {
            long code = pinfo.lastUpdateTime;
            return code;
        }
        return 0;
    }

    /**
     * 判断当前应用是否在前台运行
     *
     * @return
     */
    public static boolean isForgroundRunning() {
        ActivityManager am = (ActivityManager) getAppContext().getSystemService(Context.ACTIVITY_SERVICE);
        ComponentName cn = am.getRunningTasks(1).get(0).topActivity;
        String currentPackageName = cn.getPackageName();
        if (!TextUtils.isEmpty(currentPackageName) && currentPackageName.equals(getPackageName())) {
            return true;
        }
        return false;
    }

    /**
     * 根据pid获取对应进程process的processName,Android中进程名称通常有以下三种：<br>
     * 1、当前app主进程默认的processName就等于packageName<br>
     * 例如：com.android.training<br>
     * 2、当前app的private进程都遵循以下格式：packageName:privateProcessName<br>
     * 例如：com.android.training:private_process<br>
     * 3、全局进程，遵循以下格式：以小写字母开头,必须至少包含一个"."<br>
     * 例如：com.test.global_process<br>
     *
     * @param pid
     * @return
     */
    public static String getProcessNameByPid(int pid) {
        ActivityManager activityManager = (ActivityManager) getAppContext().getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningAppProcessInfoList = activityManager.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcessInfoList) {
            //当前应用进程列表中，pid和当前进程pid相同的进程
            if (runningAppProcessInfo.pid == pid) {
                return runningAppProcessInfo.processName;
            }
        }
        return C.Strings.EMPTY;
    }

    /**
     * 获得当前进程的进程名称
     *
     * @return
     */
    public static String getCurrentProcessName() {
        int pid = android.os.Process.myPid();
        return getProcessNameByPid(pid);
    }

    /**
     * 判断当前进程{@link android.os.Process#myPid()}是否和给定processName进程是同一进程
     *
     * @param processName
     * @return
     */
    public static boolean isThatProcess(String processName) {
        if (processName == null || TextUtils.isEmpty(processName.trim())) {
            return false;
        } else {
            return getCurrentProcessName().equals(processName);
        }
    }

    /**
     * 判断当前进程是否是app主进程
     *
     * @return
     */
    public static boolean isMainProcess() {
        String mainProcessName = getAppContext().getPackageName();
        return isThatProcess(mainProcessName);
    }

    /**
     * 显示软键盘
     *
     * @param view
     */
    public static void showInputMethod(View view) {
        InputMethodManager imm = (InputMethodManager) getAppContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null && view != null) {
            if (!imm.showSoftInput(view, 0)) {
                LogManager.getInstance().d("showInputMethod", "Failed to show soft input method.");
            }
        }
    }

    /**
     * 隐藏软键盘
     *
     * @param view
     */
    public static void hideInputMethod(View view) {
        InputMethodManager imm = (InputMethodManager) getAppContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null && view != null && view.getWindowToken() != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    /**
     * 获取当前设备中android platfor sdk 版本号
     *
     * @return
     */
    public int getSDKVersion() {
        return Build.VERSION.SDK_INT;
    }

    /**
     * 将给定单位值转化成px
     *
     * @param unit
     * @param value
     * @return
     */
    public static float convertUnitValueToPx(int unit, float value) {
        return TypedValue.applyDimension(unit, value, getDisplayMetrics());
    }

    /**
     * 将DP转化成px
     *
     * @param dpValue
     * @return
     */
    public static float dp2px(float dpValue) {
        return convertUnitValueToPx(TypedValue.COMPLEX_UNIT_DIP, dpValue);
    }

    /**
     * 清楚WebView Cookie
     *
     * @param context
     */
    public void removeCookie(Context context) {
        CookieSyncManager.createInstance(context);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.removeAllCookie();
        CookieSyncManager.getInstance().sync();
    }


    /**
     * 去掉超链接中的下划线
     *
     * @param source
     * @return
     */
    public static Spanned fromHtmlNoUnderLine(String source) {
        if (!TextUtils.isEmpty(source) && !TextUtils.isEmpty(source.trim())) {
            Spanned spanned = Html.fromHtml(source);
            return fromHtmlNoUnderLine(spanned);
        } else {
            return new SpannableStringBuilder();
        }
    }

    public static Spanned fromHtmlNoUnderLine(Spanned spanned) {
        if (spanned != null) {
            if (spanned instanceof Spannable) {
                Spannable spannable = (Spannable) spanned;
                URLSpan[] urlSpans = spanned.getSpans(0, spanned.length(), URLSpan.class);
                if (urlSpans != null) {
                    for (URLSpan urlSpan : urlSpans) {
                        int start = spanned.getSpanStart(urlSpan);
                        int end = spanned.getSpanEnd(urlSpan);
                        /**
                         * 重新设置span之前需要移除原来的span,否则在linkify的onKeyDownOnLink中会找不到新设置的span的start和end
                         */
                        spannable.removeSpan(urlSpan);
                        spannable.setSpan(new NoUnderLineUrlSpan(urlSpan.getURL()), start, end, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                    }
                }
                return spannable;
            }
            return spanned;
        } else {
            return new SpannableStringBuilder();
        }
    }

    /**
     * 实现没有下划线的URLSpan
     */
    public static class NoUnderLineUrlSpan extends URLSpan {

        public NoUnderLineUrlSpan(String url) {
            super(url);
        }

        public NoUnderLineUrlSpan(Parcel src) {
            super(src);
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            super.updateDrawState(ds);
            ds.setUnderlineText(false);
        }
    }

    /**
     * 将类似FFFFFF 和 #996633 的16进制字符串转化成int型Color值。
     *
     * @param hexString
     * @return
     */
    public static int convertHexStringToIntColor(String hexString, String defaultColorHexString) {
        try {
            if (!TextUtils.isEmpty(hexString) && !TextUtils.isEmpty(hexString.trim())) {
                if (hexString.startsWith(C.Strings.HEX_COLOR_PREFIX)) {
                    return Color.parseColor(hexString);
                } else {
                    hexString = C.Strings.HEX_COLOR_PREFIX + hexString;
                    return Color.parseColor(hexString);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Color.parseColor(defaultColorHexString);
    }

    /**
     * 获得泛型对象的真实类型，并返回
     * @param o
     * @param i
     * @param <T>
     * @return
     */
    public static <T> T getActualType(Object o, int i) {
        try {
            return ((Class<T>) ((ParameterizedType) (o.getClass()
                    .getGenericSuperclass())).getActualTypeArguments()[i])
                    .newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        return null;
    }
}
