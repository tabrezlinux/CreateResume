package com.example.tabrezahmad.createresume;

import android.app.DatePickerDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.Calendar;

public class MyDatePicker extends DatePickerDialog {

    public static MyDatePicker getInstance(@NonNull Context context, @Nullable OnDateSetListener listener){

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int themeId = android.R.style.Theme_DeviceDefault;

        MyDatePicker dp = new MyDatePicker(context,themeId,listener,year,month,day);
        return dp;
    }

    private MyDatePicker(@NonNull Context context, int themeResId, @Nullable OnDateSetListener listener, int year, int monthOfYear, int dayOfMonth) {
        super(context, themeResId, listener, year, monthOfYear, dayOfMonth);
    }
}
