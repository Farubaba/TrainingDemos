package com.android.training.basefeature.log;

/**
 * @author violet
 * @date 2018/3/5 09:55
 */
public class LogManager implements ILogger {

    private static LogManager instance = new LogManager();
    private static ILogger logger = new DefaultLogger();

    private LogManager() {

    }

    public static LogManager getInstance(){
        return instance;
    }

    /**
     * 控制是否输出log，默认true。
     * @return
     */
    public boolean isShowLog(){
        //FIXME 之后修改成根据buildConfig和gradle配置属性来控制在各个版本的apk中是否输出日志。
        return true;
    }

    /**
     * 为{@link LogManager}设置一个日志记录器logger，如果传入null，则使用默认{@link DefaultLogger}
     * @param logger
     * @return
     */
    public LogManager setLogger(ILogger logger){
        if(logger != null){
            this.logger = logger;
        }
        return this;
    }

    private ILogger getLogger(){
        if(logger != null){
            return logger;
        }else{
            return new DefaultLogger();
        }
    }

    @Override
    public void d(String TAG, String info) {
        if(isShowLog()){
            getLogger().d(TAG, info);
        }
    }

    @Override
    public void e(String TAG, String info) {
        if(isShowLog()){
            getLogger().e(TAG, info);
        }
    }

    @Override
    public void w(String TAG, String info) {
        if(isShowLog()){
            getLogger().w(TAG, info);
        }
    }
}
