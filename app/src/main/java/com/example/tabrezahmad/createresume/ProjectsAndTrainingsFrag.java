package com.example.tabrezahmad.createresume;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tabrezahmad.createresume.database.Entity.BasicInfo;
import com.example.tabrezahmad.createresume.database.FormValidator;

import java.sql.Date;
import java.util.Calendar;

public class ProjectsAndTrainingsFrag extends Fragment implements MyDatePicker.OnDateSetListener {

    private EditText et_title,
            et_description,
            et_from_date,
            et_to_date,
            et_role,
            et_team_size,
            et_location,
            et_organizer;

    private TextInputLayout tl_title,
            tl_description,
            tl_from_date,
            tl_to_date,
            tl_role,
            tl_team_size,
            tl_location,
            tl_organizer;

    View root;
    DatePickerDialog datePickerDialog;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.work_exp_project, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupViews();


        // set field values
        setValuesToInputText();

        saveFormData();

        et_from_date = (EditText) getActivity().findViewById(R.id.et_from);
        et_to_date = (EditText) getActivity().findViewById(R.id.et_to);

        et_from_date.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                MyDatePicker dp = MyDatePicker.getInstance(getContext(), ProjectsAndTrainingsFrag.this);
                dp.show();
            }
        });

        et_to_date.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                MyDatePicker dp = MyDatePicker.getInstance(getContext(), ProjectsAndTrainingsFrag.this);
                dp.show();
            }
        });



    }

    @Override
    public void onPause() {
        Toast.makeText(getContext(), "Paused Frag", Toast.LENGTH_SHORT).show();
        //saveFormData();
        printBasicModelData();
        super.onPause();
    }



    private void setupViews() {

        //setup fields
         et_title = root.findViewById(R.id.et_title);
         et_description = root.findViewById(R.id.et_description);
         et_from_date = root.findViewById(R.id.et_from);
         et_to_date = root.findViewById(R.id.et_to);
         et_role = root.findViewById(R.id.et_role);
         et_team_size = root.findViewById(R.id.et_team_size);
         et_location = root.findViewById(R.id.et_location);
         et_organizer = root.findViewById(R.id.et_organizer);

         //setup layout
        tl_title = root.findViewById(R.id.tl_title);
        tl_description = root.findViewById(R.id.tl_description);
        tl_from_date = root.findViewById(R.id.tl_from);
        tl_to_date = root.findViewById(R.id.tl_to);
        tl_role = root.findViewById(R.id.tl_role);
        tl_team_size = root.findViewById(R.id.tl_team_size);
        tl_location = root.findViewById(R.id.tl_location);
        tl_organizer = root.findViewById(R.id.tl_organizer);


        // ----------------------------------------------------------------------------------------- title
        // not null, not empty,  4 - 60 char
        et_title.addTextChangedListener(FormValidator.getNameWatcher(new FormValidator.MyTextWatcherCallback() {
            @Override
            public void onError(int error_code) {
                switch (error_code) {
                    case FormValidator.ERROR_CODE_IS_EMPTY:
                        tl_title.setError("Required");
                        break;
                    case FormValidator.ERROR_CODE_OUT_OF_RANGE:
                        tl_title.setError("4 - 60 characters required");
                        break;
                    default:
                        tl_title.setError(null);
                }
            }

            @Override
            public void onSuccess(String s) {
                tl_title.setError(null);
                NewResumeActivity.PROJECT.get(0).title = s;
            }
        }, 4, 60));

        // ----------------------------------------------------------------------------------------- description
        // not null, not empty,  4 - 60 char
        et_description.addTextChangedListener(FormValidator.getNameWatcher(new FormValidator.MyTextWatcherCallback() {
            @Override
            public void onError(int error_code) {
                switch (error_code) {
                    case FormValidator.ERROR_CODE_IS_EMPTY:
                        tl_description.setError("Required");
                        break;
                    case FormValidator.ERROR_CODE_OUT_OF_RANGE:
                        tl_description.setError("4 - 60 characters required");
                        break;
                    default:
                        tl_description.setError(null);
                }
            }

            @Override
            public void onSuccess(String s) {
                tl_description.setError(null);
                NewResumeActivity.PROJECT.get(0).description = s;
            }
        }, 4, 60));

        // ----------------------------------------------------------------------------------------- role
        // not null, not empty,  4 - 60 char
        et_role.addTextChangedListener(FormValidator.getNameWatcher(new FormValidator.MyTextWatcherCallback() {
            @Override
            public void onError(int error_code) {
                switch (error_code) {
                    case FormValidator.ERROR_CODE_IS_EMPTY:
                        tl_role.setError("Required");
                        break;
                    case FormValidator.ERROR_CODE_OUT_OF_RANGE:
                        tl_role.setError("4 - 60 characters required");
                        break;
                    default:
                        tl_role.setError(null);
                }
            }

            @Override
            public void onSuccess(String s) {
                tl_role.setError(null);
                NewResumeActivity.PROJECT.get(0).role = s;
            }
        }, 4, 60));


        // ----------------------------------------------------------------------------------------- location
        // not null, not empty,  4 - 60 char
        et_location.addTextChangedListener(FormValidator.getNameWatcher(new FormValidator.MyTextWatcherCallback() {
            @Override
            public void onError(int error_code) {
                switch (error_code) {
                    case FormValidator.ERROR_CODE_IS_EMPTY:
                        tl_location.setError("Required");
                        break;
                    case FormValidator.ERROR_CODE_OUT_OF_RANGE:
                        tl_location.setError("4 - 60 characters required");
                        break;
                    default:
                        tl_location.setError(null);
                }
            }

            @Override
            public void onSuccess(String s) {
                tl_location.setError(null);
                NewResumeActivity.PROJECT.get(0).location = s;
            }
        }, 4, 60));

        // ----------------------------------------------------------------------------------------- organizer
        // not null, not empty,  4 - 60 char
        et_organizer.addTextChangedListener(FormValidator.getNameWatcher(new FormValidator.MyTextWatcherCallback() {
            @Override
            public void onError(int error_code) {
                switch (error_code) {
                    case FormValidator.ERROR_CODE_IS_EMPTY:
                        tl_organizer.setError("Required");
                        break;
                    case FormValidator.ERROR_CODE_OUT_OF_RANGE:
                        tl_organizer.setError("4 - 60 characters required");
                        break;
                    default:
                        tl_organizer.setError(null);
                }
            }

            @Override
            public void onSuccess(String s) {
                tl_organizer.setError(null);
                NewResumeActivity.PROJECT.get(0).organizer = s;
            }
        }, 4, 60));


    }

    private void setValuesToInputText() {

        // set title
        et_title.setText(NewResumeActivity.PROJECT.get(0).title);

        // project description
        et_description.setText(NewResumeActivity.PROJECT.get(0).description);

        // role
        et_role.setText(NewResumeActivity.PROJECT.get(0).role);

        //teamsize
//        et_team_size.setText(NewResumeActivity.PROJECT.get(0).team_size);

        //location
        et_location.setText(NewResumeActivity.PROJECT.get(0).location);

        //organizer
        et_organizer.setText(NewResumeActivity.PROJECT.get(0).organizer);

    }

    // SAVE FORM DATA
    private void saveFormData() {

        // SET FORM DATA
        NewResumeActivity.PROJECT.get(0).title = et_title.getText().toString();
        NewResumeActivity.PROJECT.get(0).description = et_description.getText().toString();
        NewResumeActivity.PROJECT.get(0).role = et_role.getText().toString();
        NewResumeActivity.PROJECT.get(0).location = et_location.getText().toString();
        NewResumeActivity.PROJECT.get(0).organizer = et_organizer.getText().toString();

    }


    private void printBasicModelData() {
        Log.e("Project title", NewResumeActivity.PROJECT.get(0).title);
        Log.e("Project description", NewResumeActivity.PROJECT.get(0).title);
        Log.e("Project from", NewResumeActivity.PROJECT.get(0).title);
        Log.e("Project to", String.valueOf(NewResumeActivity.PROJECT.get(0).to_date));
        Log.e("Project Role", NewResumeActivity.PROJECT.get(0).role);
        Log.e("Project teamsize", String.valueOf(NewResumeActivity.PROJECT.get(0).team_size));
        Log.e("Project location", NewResumeActivity.PROJECT.get(0).location);
        Log.e("Project organizer", NewResumeActivity.PROJECT.get(0).organizer);

    }

    @Override
    public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
        month = month + 1;
        //  Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

        String date = month + "/" + dayOfMonth + "/" + year;
        et_from_date.setText(date);
        et_to_date.setText(date);
    }
}
