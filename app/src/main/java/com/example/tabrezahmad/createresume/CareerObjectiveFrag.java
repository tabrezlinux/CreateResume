package com.example.tabrezahmad.createresume;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.tabrezahmad.createresume.database.FormValidator;

public class CareerObjectiveFrag extends Fragment implements MyDatePicker.OnDateSetListener {
    private EditText et_career_obj,
            et_aof,
            et_strength,
            et_declaration,
            et_date,
            et_place;

    private TextInputLayout tl_career_obj,
            tl_aof,
            tl_strength,
            tl_declaration,
            tl_date,
            tl_place;

    View root;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.designation_career_objective, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupViews();

        et_date = (EditText) getActivity().findViewById(R.id.et_date);

        et_date.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                MyDatePicker dp = MyDatePicker.getInstance(getContext(), CareerObjectiveFrag.this);
                dp.show();
            }
        });


    }

    private void setupViews() {

        //set fields
        et_career_obj = root.findViewById(R.id.et_career_obj);
        et_aof = root.findViewById(R.id.et_aof);
        et_strength = root.findViewById(R.id.et_strength);
        et_declaration = root.findViewById(R.id.et_declaration);
        et_date = root.findViewById(R.id.et_date);
        et_place = root.findViewById(R.id.et_place);

        //set layouts
        tl_career_obj = root.findViewById(R.id.tl_career_obj);
        tl_aof = root.findViewById(R.id.tl_aof);
        tl_strength = root.findViewById(R.id.tl_strength);
        tl_declaration = root.findViewById(R.id.tl_declaration);
        tl_date = root.findViewById(R.id.tl_date);
        tl_place = root.findViewById(R.id.tl_place);

        // ----------------------------------------------------------------------------------------- Career Objective
        // not null, not empty,  4 - 60 char
        et_career_obj.addTextChangedListener(FormValidator.getNameWatcher(new FormValidator.MyTextWatcherCallback() {
            @Override
            public void onError(int error_code) {
                switch (error_code) {
                    case FormValidator.ERROR_CODE_IS_EMPTY:
                        tl_career_obj.setError("Required");
                        break;
                    case FormValidator.ERROR_CODE_OUT_OF_RANGE:
                        tl_career_obj.setError("4 - 60 characters required");
                        break;
                    default:
                        tl_career_obj.setError(null);
                }
            }

            @Override
            public void onSuccess(String s) {
                tl_career_obj.setError(null);
                NewResumeActivity.CAREER_OBJECTIVE.objective = s;
            }
        }, 4, 60));

        // ----------------------------------------------------------------------------------------- Area of Interest
        // not null, not empty,  4 - 60 char
        et_aof.addTextChangedListener(FormValidator.getNameWatcher(new FormValidator.MyTextWatcherCallback() {
            @Override
            public void onError(int error_code) {
                switch (error_code) {
                    case FormValidator.ERROR_CODE_IS_EMPTY:
                        tl_aof.setError("Required");
                        break;
                    case FormValidator.ERROR_CODE_OUT_OF_RANGE:
                        tl_aof.setError("4 - 60 characters required");
                        break;
                    default:
                        tl_aof.setError(null);
                }
            }

            @Override
            public void onSuccess(String s) {
                tl_aof.setError(null);
                NewResumeActivity.AREA_OF_INTEREST.get(0).aoi = s;
            }
        }, 4, 60));

        // ----------------------------------------------------------------------------------------- Strength
        // not null, not empty,  4 - 60 char
        et_strength.addTextChangedListener(FormValidator.getNameWatcher(new FormValidator.MyTextWatcherCallback() {
            @Override
            public void onError(int error_code) {
                switch (error_code) {
                    case FormValidator.ERROR_CODE_IS_EMPTY:
                        tl_strength.setError("Required");
                        break;
                    case FormValidator.ERROR_CODE_OUT_OF_RANGE:
                        tl_strength.setError("4 - 60 characters required");
                        break;
                    default:
                        tl_strength.setError(null);
                }
            }

            @Override
            public void onSuccess(String s) {
                tl_strength.setError(null);
                NewResumeActivity.STRENGTH.get(0).strength = s;
            }
        }, 4, 60));

        // ----------------------------------------------------------------------------------------- Declaration
        // not null, not empty,  4 - 60 char
        et_declaration.addTextChangedListener(FormValidator.getNameWatcher(new FormValidator.MyTextWatcherCallback() {
            @Override
            public void onError(int error_code) {
                switch (error_code) {
                    case FormValidator.ERROR_CODE_IS_EMPTY:
                        tl_declaration.setError("Required");
                        break;
                    case FormValidator.ERROR_CODE_OUT_OF_RANGE:
                        tl_declaration.setError("4 - 60 characters required");
                        break;
                    default:
                        tl_declaration.setError(null);
                }
            }

            @Override
            public void onSuccess(String s) {
                tl_declaration.setError(null);
                NewResumeActivity.CAREER_OBJECTIVE.decleration = s;
            }
        }, 4, 60));

        // ----------------------------------------------------------------------------------------- Place
        // not null, not empty,  4 - 60 char
        et_place.addTextChangedListener(FormValidator.getNameWatcher(new FormValidator.MyTextWatcherCallback() {
            @Override
            public void onError(int error_code) {
                switch (error_code) {
                    case FormValidator.ERROR_CODE_IS_EMPTY:
                        tl_place.setError("Required");
                        break;
                    case FormValidator.ERROR_CODE_OUT_OF_RANGE:
                        tl_place.setError("4 - 60 characters required");
                        break;
                    default:
                        tl_place.setError(null);
                }
            }

            @Override
            public void onSuccess(String s) {
                tl_place.setError(null);
                NewResumeActivity.CAREER_OBJECTIVE.place = s;
            }
        }, 4, 60));
    }

    //set date
    @Override
    public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
        month = month + 1;
        //  Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

        String date = month + "/" + dayOfMonth + "/" + year;
        et_date.setText(date);
    }


}
