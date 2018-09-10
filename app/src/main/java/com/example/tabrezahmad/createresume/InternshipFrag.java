package com.example.tabrezahmad.createresume;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.tabrezahmad.createresume.database.FormValidator;

import java.util.Calendar;

public class InternshipFrag extends Fragment implements DatePickerDialog.OnDateSetListener, View.OnTouchListener {

    private EditText et_company,
            et_role,
            et_from,
            et_to,
            et_location;

    private TextInputLayout tl_company,
            tl_role,
            tl_from,
            tl_to,
            tl_location;

    View root;
    DatePickerDialog datePickerDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.work_exp_internship, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupViews();

        setValuesToInputText();


    }


    private void setupViews() {

        //setup Fields
        et_company = root.findViewById(R.id.et_company);
        et_role = root.findViewById(R.id.et_role);
        et_from = root.findViewById(R.id.et_from);
        et_to = root.findViewById(R.id.et_to);
        et_location = root.findViewById(R.id.et_location);

        //setup layouts
        tl_company = root.findViewById(R.id.tl_company);
        tl_role = root.findViewById(R.id.tl_role);
        tl_from = root.findViewById(R.id.tl_from);
        tl_to = root.findViewById(R.id.tl_to);
        tl_location = root.findViewById(R.id.tl_location);

        //----------------------------------------------------------------------------------Company
        // Char 2-80, not null and empty, remove error, save data
        et_company.addTextChangedListener(FormValidator.getNameWatcher(new FormValidator.MyTextWatcherCallback() {
            @Override
            public void onError(int error_code) {
                switch (error_code) {
                    case FormValidator.ERROR_CODE_IS_EMPTY:
                        tl_company.setError("Required");
                        break;

                    case FormValidator.ERROR_CODE_OUT_OF_RANGE:
                        tl_company.setError("2-80 Character Required");
                        break;
                    default:
                        tl_company.setError(null);
                }
            }

            @Override
            public void onSuccess(String s) {
                tl_company.setError(null);
                NewResumeActivity.INTERNSHIP.get(0).company = s;
            }
        }, 2, 80));

        //--------------------------------------------------------------------------------------Role
        //Not null or empty, 4-80 Character, save data
        et_role.addTextChangedListener(FormValidator.getNameWatcher(new FormValidator.MyTextWatcherCallback() {
            @Override
            public void onError(int error_code) {
                switch (error_code) {
                    case FormValidator.ERROR_CODE_IS_EMPTY:
                        tl_role.setError("Required");
                        break;

                    case FormValidator.ERROR_CODE_OUT_OF_RANGE:
                        tl_role.setError("4-80 Char required");
                        break;

                    default:
                        tl_role.setError(null);

                }

            }

            @Override
            public void onSuccess(String s) {
                tl_role.setError(null);
                NewResumeActivity.INTERNSHIP.get(0).role = s;

            }
        }, 4, 80));

        //--------------------------------------------------------------------------------------Role
        //Not null or empty, 4-80 Character, save data
//        et_location.addTextChangedListener(FormValidator.getNameWatcher(new FormValidator.MyTextWatcherCallback() {
//            @Override
//            public void onError(int error_code) {
//                switch (error_code) {
//                    case FormValidator.ERROR_CODE_IS_EMPTY:
//                        tl_location.setError("Required");
//                        break;
//
//                    case FormValidator.ERROR_CODE_OUT_OF_RANGE:
//                        tl_location.setError("2-80 Char required");
//                        break;
//
//                    default:
//                        tl_location.setError(null);
//
//                }
//
//            }
//
//            @Override
//            public void onSuccess(String s) {
//                tl_location.setError(null);
//                NewResumeActivity.INTERNSHIP.get(0).location = s;
//
//            }
//        }, 2, 80));


    }

    // SHOW DATEPICKER -----------------------------------------------------------------------------
    private void showDatePickerDialog() {

        // create and show dialog if not exists
        if (datePickerDialog == null) {
            Calendar c = Calendar.getInstance();
            datePickerDialog = new DatePickerDialog(getContext(), this, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
            datePickerDialog.show();
        } else {
            datePickerDialog.show();
        }

    }

    // ON VIEW TOUCH -------------------------------------------------------------------------------
    @Override
    public boolean onTouch(View v, MotionEvent event) {

        switch (v.getId()) {
            case R.id.et_from:
                if (event.getAction() == MotionEvent.ACTION_UP)
                    showDatePickerDialog();

            case R.id.et_to:
                if(event.getAction() == MotionEvent.ACTION_UP)
                    showDatePickerDialog();
                break;
        }

        return true;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        // user date
        Calendar userCalendar = Calendar.getInstance();
        userCalendar.set(year, month, dayOfMonth);
    }

    private void setValuesToInputText() {

        et_company.setText(NewResumeActivity.INTERNSHIP.get(0).company);
        et_role.setText(NewResumeActivity.INTERNSHIP.get(0).role);

    }
}
