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

public class BasicInfoFrag extends Fragment implements DatePickerDialog.OnDateSetListener {

    private EditText basicInfoDob;
//    private DatePickerDialog.OnDateSetListener mDateSetListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.basic_info, container, false);

    }


    // Basic Info DOB Date Picker


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        basicInfoDob = (EditText) getActivity().findViewById(R.id.dob);

        basicInfoDob.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                DatePicker dp = DatePicker.getInstance(getContext(), BasicInfoFrag.this);
                dp.show();
            }
        });

    }

//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//
//
//    }

    @Override
    public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
        month = month + 1;
        //  Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

        String date = month + "/" + dayOfMonth + "/" + year;
        basicInfoDob.setText(date);
    }
}
