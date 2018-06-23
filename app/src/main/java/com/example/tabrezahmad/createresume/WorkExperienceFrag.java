package com.example.tabrezahmad.createresume;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.Calendar;

public class WorkExperienceFrag extends Fragment implements DatePickerDialog.OnDateSetListener {

    private EditText work_exp_from;
    private EditText work_exp_to;
//    private DatePickerDialog.OnDateSetListener mDateSetListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.work_experience, container, false);


    }

    // Basic Info DOB Date Picker


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        work_exp_from = (EditText) getActivity().findViewById(R.id.work_experience_from);
        work_exp_to = (EditText) getActivity().findViewById(R.id.work_experience_to);

        work_exp_from.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                DatePicker dp = DatePicker.getInstance(getContext(), WorkExperienceFrag.this);
                dp.show();
            }
        });

        work_exp_to.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                DatePicker dp = DatePicker.getInstance(getContext(), WorkExperienceFrag.this);
                dp.show();
            }
        });



    }

    @Override
    public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
        month = month + 1;
        //  Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

        String date = month + "/" + dayOfMonth + "/" + year;
        work_exp_from.setText(date);
        work_exp_to.setText(date);
    }





//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);

//        mDisplayDateFrom = (EditText) getActivity().findViewById(R.id.work_experience_from);

//        mDisplayDateFrom.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//                Calendar cal = Calendar.getInstance();
//                int year = cal.get(Calendar.YEAR);
//                int month = cal.get(Calendar.MONTH);
//                int day = cal.get(Calendar.DAY_OF_MONTH);
//
//
//
//
//
////                DatePickerDialog dialog = new DatePickerDialog(getActivity(),
////                        android.R.style.Theme_Material_Dialog,
////                        mDateSetListener,
////                        year,month,day);
////                dialog.show();
//            }
//        });
//
//        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
//                String date = year + "/" + month + "/" + dayOfMonth;
//                mDisplayDateFrom.setText(date);
//
//            }
//
//        };
}
