package com.android.training;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private View.OnClickListener onClickListener;
    private Intent focusAndClickIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cm_activity_main);
        Button button1 = findViewById(R.id.button1);

        onClickListener = new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.button1:
                        startActivity(focusAndClickIntent);
                        break;
                        default:
                            break;
                }
            }
        };


        focusAndClickIntent = new Intent();
        focusAndClickIntent.setClass(this,FocusAndClick.class);
        button1.setOnClickListener(onClickListener);
    }
}
