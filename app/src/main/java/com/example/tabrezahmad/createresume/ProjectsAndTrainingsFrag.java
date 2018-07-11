package com.example.tabrezahmad.createresume;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class ProjectsAndTrainingsFrag extends Fragment implements MyDatePicker.OnDateSetListener {

    private EditText project_date_from;
    private EditText project_date_to;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.work_exp_project, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        project_date_from = (EditText) getActivity().findViewById(R.id.project_from);
        project_date_to = (EditText) getActivity().findViewById(R.id.project_to);

        project_date_from.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                MyDatePicker dp = MyDatePicker.getInstance(getContext(), ProjectsAndTrainingsFrag.this);
                dp.show();
            }
        });

        project_date_to.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                MyDatePicker dp = MyDatePicker.getInstance(getContext(), ProjectsAndTrainingsFrag.this);
                dp.show();
            }
        });



    }

    @Override
    public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
        month = month + 1;
        //  Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

        String date = month + "/" + dayOfMonth + "/" + year;
        project_date_from.setText(date);
        project_date_to.setText(date);
    }
}
