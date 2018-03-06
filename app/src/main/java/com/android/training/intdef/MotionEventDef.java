package com.android.training.intdef;


import android.support.annotation.IntDef;
import android.view.MotionEvent;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.CLASS;
import static java.lang.annotation.RetentionPolicy.SOURCE;

/**
 * @author violet
 * @date 2018/2/24 18:45
 */


@IntDef({MotionEvent.ACTION_DOWN,
        MotionEvent.ACTION_MOVE,
        MotionEvent.ACTION_CANCEL,
        MotionEvent.ACTION_UP,
        MotionEvent.ACTION_POINTER_DOWN,
        MotionEvent.ACTION_POINTER_UP,
        MotionEvent.ACTION_OUTSIDE})
@Retention(SOURCE)
public @interface MotionEventDef {
    
}




