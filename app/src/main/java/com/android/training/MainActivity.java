package com.android.training;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.drawable.VectorDrawable;
import android.support.graphics.drawable.AnimatedVectorDrawableCompat;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.Vector;

public class MainActivity extends AppCompatActivity {
    private View.OnClickListener onClickListener;
    private Intent focusAndClickIntent;
    private Intent constraintActivityIntent;
    private Intent processActivityIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cm_activity_main);
        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);

        ImageButton imageButton2 = findViewById(R.id.imageButton2);
        final AnimatedVectorDrawableCompat animatedVectorDrawableCompat = ((AnimatedVectorDrawableCompat) imageButton2.getDrawable());
        animatedVectorDrawableCompat.start();

        onClickListener = new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.button1:
                        startActivity(focusAndClickIntent);
                        break;
                    case R.id.button2:
                        startActivity(constraintActivityIntent);
                        break;
                    case R.id.button3:
                        startActivity(processActivityIntent);
                        break;
                        default:
                            if(animatedVectorDrawableCompat.isRunning()){
                                animatedVectorDrawableCompat.stop();
                            }else{
                                animatedVectorDrawableCompat.start();
                            }
                            break;
                }
            }
        };


        focusAndClickIntent = new Intent();
        focusAndClickIntent.setClass(this,FocusAndClick.class);

        constraintActivityIntent = new Intent();
        constraintActivityIntent.setClass(this,ConstraintLayoutTest.class);


        processActivityIntent = new Intent();
        processActivityIntent.setClass(this,ProcessActivity.class);


        button1.setOnClickListener(onClickListener);
        button2.setOnClickListener(onClickListener);
        button3.setOnClickListener(onClickListener);
        imageButton2.setOnClickListener(onClickListener);
    }
}
