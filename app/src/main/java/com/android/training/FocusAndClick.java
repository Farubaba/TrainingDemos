package com.android.training;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnWindowFocusChangeListener;
import android.widget.Button;

import java.util.LinkedList;
import java.util.List;

/**
 * @author violet
 * @date 2018/1/10 16:10
 */

public class FocusAndClick extends AppCompatActivity implements View.OnClickListener , OnWindowFocusChangeListener, ViewTreeObserver.OnGlobalFocusChangeListener{

    public static String TAG = FocusAndClick.class.getSimpleName();
    Button button1, button2, button3, button4, button5;
    Button buttonLeft, buttonRight;
    View currentFocusView;

    List<View> viewList = new LinkedList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View contentView = LayoutInflater.from(this).inflate(R.layout.cm_act_focus_click,null);
        setContentView(contentView);

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 =  findViewById(R.id.button3);
        button4 =  findViewById(R.id.button4);
        button5 =  findViewById(R.id.button5);
        buttonLeft = findViewById(R.id.left_button);
        buttonRight = findViewById(R.id.right_button);
        viewList.add(button1);
        viewList.add(button2);
        viewList.add(button3);
        viewList.add(button4);
        viewList.add(button5);
        viewList.add(buttonLeft);
        viewList.add(buttonRight);


        buttonRight.isEnabled();
        buttonRight.isFocusable();
        buttonRight.isFocusableInTouchMode();
        buttonRight.isHovered();
        buttonRight.isClickable();
        buttonRight.isLongClickable();
        buttonRight.isPressed();
        buttonRight.isSelected();

        buttonRight.isActivated();
        buttonRight.isAccessibilityFocused();
        buttonRight.isAttachedToWindow();
        buttonRight.isCursorVisible();
        buttonRight.isDirty();
        buttonRight.isDrawingCacheEnabled();
        buttonRight.isDuplicateParentStateEnabled();

        buttonRight.setClickable(false);
        buttonLeft.setClickable(true);

        button1.setOnClickListener(this);
//        button2.setOnClickListener(this);
//        button3.setOnClickListener(this);
//        button4.setOnClickListener(this);
//        button5.setOnClickListener(this);
//        buttonLeft.setOnClickListener(this);
//        buttonRight.setOnClickListener(this);


        currentFocusView = getCurrentFocus();

        ViewTreeObserver viewTreeObserver = contentView.getRootView().getViewTreeObserver();
        viewTreeObserver.addOnGlobalFocusChangeListener(this);
        viewTreeObserver.addOnWindowFocusChangeListener(this);

    }


    private void showFocusEnableClickable() {
        for(View view : viewList){
            Log.d(TAG,"focusable = " +view.isFocusable()+" isfocused = "+ view.isFocused() +" enable = " + view.isEnabled()+ " clickable = " + view.isClickable() + " isPressed= "+ view.isPaddingRelative());
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button1:
                showFocusEnableClickable();
                button2.requestFocus();
                break;
            case R.id.button2:
                break;
            case R.id.button3:
                break;
            case R.id.button4:
                break;
            case R.id.button5:
                break;
            case R.id.left_button:
                break;
            case R.id.right_button:
                break;
            default:
                break;

        }
        currentFocusView = getCurrentFocus();
       if(currentFocusView != null) {
           currentFocusView.setBackground(getDrawable(R.drawable.color_drawable_5));
       }else{
           Log.d(TAG," click 后，当前没有焦点");
       }

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        Log.d(TAG, "onWindowFocusChanged hasFocus = "+ hasFocus);
        currentFocusView = getCurrentFocus();
        if(hasFocus && currentFocusView != null){
            currentFocusView.setBackground(getDrawable(R.drawable.color_drawable_5));
        }else{
            Log.d(TAG,"onWindowFocusChanged 当前没有焦点");
        }
    }

    @Override
    public void onGlobalFocusChanged(View view, View view1) {
        Log.d(TAG,"onGlobalFocusChanged");
    }
}
