package com.android.training.basefeature.utils;

import java.util.concurrent.CountDownLatch;

/**
 * @author violet
 * @date 2018/3/5 09:55
 */
public class ConcurrentUtil {

    /**
     * 只能CountDown一次
     * @return
     */
    public static CountDownLatch newSingleStepCountDownLatch(){
        return new CountDownLatch(1);
    }

    public static CountDownLatch countDown(CountDownLatch countDownLatch){
        if(countDownLatch != null){
            countDownLatch.countDown();
        }
        return countDownLatch;
    }

    /**
     *
     * @param countDownLatch
     * @return
     */
    public static CountDownLatch await(CountDownLatch countDownLatch){
        if(countDownLatch != null){
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return countDownLatch;
    }
}
