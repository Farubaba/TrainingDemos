package com.android.training.basefeature.log;

/**
 * 所有日志记录工具抽象接口，便于以后移植和替换
 * @author violet
 * @date 2018/3/5 09:49
 */

public interface ILogger {
    void d(String tag, String info);
    void e(String tag, String info);
    void w(String tag, String info);
}
