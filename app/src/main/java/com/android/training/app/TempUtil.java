package com.android.training.app;

import android.widget.EditText;

/**
 * @author violet
 * @date 2018/3/5 20:17
 */

public class TempUtil {
    public static void setSelectionEnd(EditText editText) {
        if (editText != null) {
            try {
                editText.setSelection(editText.getText().length());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
