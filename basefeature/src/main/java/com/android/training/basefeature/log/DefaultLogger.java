package com.android.training.basefeature.log;

import android.util.Log;

/**
 * @author violet
 * @date 2018/3/5 09:53
 */

public class DefaultLogger implements ILogger {

    @Override
    public void d(String tag, String info) {
        Log.d(tag,info);
    }

    @Override
    public void e(String tag, String info) {
        Log.e(tag,info);
    }

    @Override
    public void w(String tag, String info) {
        Log.v(tag,info);
    }
}
