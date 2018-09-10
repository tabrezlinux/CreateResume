package com.example.tabrezahmad.createresume;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.tabrezahmad.createresume.database.Entity.AcademicQualification;
import com.example.tabrezahmad.createresume.database.FormValidator;

import java.sql.Date;
import java.util.Calendar;

public class AcademicQuaFrag extends Fragment implements View.OnClickListener, View.OnTouchListener, DatePickerDialog.OnDateSetListener {

    private TextInputLayout
            tl_course,
            tl_institute,
            tl_year,
            tl_marks;

    private EditText
            et_course,
            et_institute,
            et_year,
            et_marks;

    private RadioGroup
            rg_is_pursuing,
            rg_marks_type;

    private String
            IS_PURSUING,
            MARKS_TYPE_IS;

    private DatePickerDialog datePicker;

    View root;

    LayoutInflater inflater;
    ViewGroup container;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.inflater = inflater;
        this.container = container;
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.qualification_academic, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupViews();

        setValuesToInputText();
        saveFormData();


    }

    private void setValuesToInputText() {
        et_course.setText(NewResumeActivity.ACADEMIC_QUA.get(0).course);
        et_institute.setText(NewResumeActivity.ACADEMIC_QUA.get(0).institute);
    }

    @Override
    public void onPause() {
        Toast.makeText(getContext(), "Paused Frag", Toast.LENGTH_SHORT).show();
        //saveFormData();
        printBasicModelData();
        super.onPause();
    }

    private void setupViews() {

        // SETUP FIELDS
        et_course = root.findViewById(R.id.course);
        et_institute = root.findViewById(R.id.institute);
        et_year = root.findViewById(R.id.et_year);
        et_marks = root.findViewById(R.id.marks_obtained);

        rg_is_pursuing = getActivity().findViewById(R.id.rg_pursuing_or_passed);
        rg_marks_type = getActivity().findViewById(R.id.rg_marks_type);

        //set fields value



        // SETUP FIELDS LAYOUTS
        tl_course = root.findViewById(R.id.tl_course);
        tl_institute = root.findViewById(R.id.tl_institute);
        tl_year = root.findViewById(R.id.tl_year);
        tl_marks = root.findViewById(R.id.tl_marks);


        // ----------------------------------------------------------------------------------------- course
        // not null, not empty,  4 - 60 char
        et_course.addTextChangedListener(FormValidator.getNameWatcher(new FormValidator.MyTextWatcherCallback() {
            @Override
            public void onError(int error_code) {
                switch (error_code) {
                    case FormValidator.ERROR_CODE_IS_EMPTY:
                        tl_course.setError("Required");
                        break;
                    case FormValidator.ERROR_CODE_OUT_OF_RANGE:
                        tl_course.setError("4 - 60 characters required");
                        break;
                    default:
                        tl_course.setError(null);
                }
            }

            @Override
            public void onSuccess(String s) {
                tl_course.setError(null);
                NewResumeActivity.ACADEMIC_QUA.get(0).course = s;
            }
        }, 4, 60));

        // ----------------------------------------------------------------------------------------- institute
        // not null, not empty,  4 - 60 char
        et_institute.addTextChangedListener(FormValidator.getNameWatcher(new FormValidator.MyTextWatcherCallback() {
            @Override
            public void onError(int error_code) {
                switch (error_code) {
                    case FormValidator.ERROR_CODE_IS_EMPTY:
                        tl_institute.setError("Required");
                        break;
                    case FormValidator.ERROR_CODE_OUT_OF_RANGE:
                        tl_institute.setError("4 - 60 characters required");
                        break;
                    default:
                        tl_institute.setError(null);
                }
            }

            @Override
            public void onSuccess(String s) {
                tl_institute.setError(null);
                NewResumeActivity.ACADEMIC_QUA.get(0).institute = s;
            }
        }, 4, 60));




        // ON CLICK LISTENER
        et_year.setOnClickListener(this);

        // ON TOUCH LISTENER
        et_year.setOnTouchListener(this);

        // ON CHANGE RADIO LISTENER (PASSED/PURSUING)
        rg_is_pursuing.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_pursuing:
                        IS_PURSUING = AcademicQualification.STATUS_PURSUING;
                        tl_year.setHint("Current Year");

                        // disable marks_type radios
                        rg_marks_type.setEnabled(false);
                        rg_marks_type.getChildAt(0).setEnabled(false);
                        rg_marks_type.getChildAt(1).setEnabled(false);
                        et_marks.setEnabled(false);
                        break;

                    case R.id.rb_passed:
                        IS_PURSUING = AcademicQualification.STATUS_PASSED;
                        tl_year.setHint("Year of Passing");

                        // enable marks_type radios
                        rg_marks_type.setEnabled(true);
                        rg_marks_type.getChildAt(0).setEnabled(true);
                        rg_marks_type.getChildAt(1).setEnabled(true);
                        et_marks.setEnabled(true);
                        break;
                }
            }
        });

        // ON CHANGE RADIO LISTENER (CGPA/PERCENT)
        rg_marks_type.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_cgpa:
                        MARKS_TYPE_IS = AcademicQualification.MARKS_TYPE_CGPA;
                        tl_marks.setHint(AcademicQualification.MARKS_TYPE_CGPA + " Marks");
                        break;
                    case R.id.rb_percentage:
                        MARKS_TYPE_IS = AcademicQualification.MARKS_TYPE_PERCENT;
                        tl_marks.setHint("Marks Percentage (%)");
                        break;
                }
            }
        });

        // set IS_PURSUING == pursuing/passed
        IS_PURSUING = rg_is_pursuing.getCheckedRadioButtonId() == R.id.rb_passed ? AcademicQualification.STATUS_PASSED : AcademicQualification.STATUS_PURSUING;

        // set MARKS_TYPE = cgpa/percentage
        MARKS_TYPE_IS = rg_marks_type.getCheckedRadioButtonId() == R.id.rb_cgpa ? AcademicQualification.MARKS_TYPE_CGPA : AcademicQualification.MARKS_TYPE_PERCENT;

    }

    private void printBasicModelData() {
        Log.e("Academic name", NewResumeActivity.ACADEMIC_QUA.get(0).getCourse());
        Log.e("Academic institute", NewResumeActivity.ACADEMIC_QUA.get(0).getInstitute());
        Log.e("Academic passingstatus", NewResumeActivity.ACADEMIC_QUA.get(0).getPassing_status());
        Log.e("Academic markstype", NewResumeActivity.ACADEMIC_QUA.get(0).getMarks_type());
        Log.e("Academic year", String.valueOf(NewResumeActivity.ACADEMIC_QUA.get(0).getYear()));
        Log.e("Academic marks", String.valueOf(NewResumeActivity.ACADEMIC_QUA.get(0).getMarks()));
    }



    // SAVE FORM DATA
    private void saveFormData() {

        // SET FORM DATA
        NewResumeActivity.ACADEMIC_QUA.get(0).course = et_course.getText().toString();
        NewResumeActivity.ACADEMIC_QUA.get(0).institute = et_institute.getText().toString();
        NewResumeActivity.ACADEMIC_QUA.get(0).passing_status = IS_PURSUING;

        String passing_year = et_year.getText().toString();
        Date year = new Date(System.currentTimeMillis());
        NewResumeActivity.ACADEMIC_QUA.get(0).year = year;

        try {
            year = Date.valueOf(passing_year);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(getContext().getClass().getName(), "Parsing failed Date/Year");
        }


        // if PASSED then et_year, et_marks, et_marks_type is required
        // else et_year == either empty/required
        if (IS_PURSUING == AcademicQualification.STATUS_PASSED) {
            NewResumeActivity.ACADEMIC_QUA.get(0).year = year;
            try {
                NewResumeActivity.ACADEMIC_QUA.get(0).marks = Float.parseFloat(et_marks.getText().toString());
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getClass().getName(), "Parsing Error, Invalid Marks");
            }

            NewResumeActivity.ACADEMIC_QUA.get(0).marks_type = MARKS_TYPE_IS;

        } else {
            NewResumeActivity.ACADEMIC_QUA.get(0).year = year;
            NewResumeActivity.ACADEMIC_QUA.get(0).marks = null;
            NewResumeActivity.ACADEMIC_QUA.get(0).marks_type = null;
        }

        // validate form data
        boolean isValid = true; //validateFormData(QUALIFICATION_OBJ);


    }



    private boolean validateFormData(AcademicQualification qualification) {

        boolean IS_VALID_FORM = true;



        return IS_VALID_FORM;

    }


    // ON DATE SET ---------------------------------------------------------------------------------
    @Override
    public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {

        // create date instance based on et_year,month and day
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, dayOfMonth);

        // set user formatted date
        this.et_year.setText(DateFormat.format("MMM dd, yyyy", calendar.getTimeInMillis()).toString());

        // set actual date in milli sec. for SQL
        this.et_year.setHint(String.valueOf(calendar.getTimeInMillis()));

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.et_year:
                showDatePickerDialog(et_year);
                break;
        }

    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (v.getId()) {
            case R.id.et_year:
                showDatePickerDialog(et_year);
        }
        return true;
    }

    private void showDatePickerDialog(final EditText mView) {

        // create and show dialog if not exists
        if (datePicker != null) {
            datePicker.show();
        } else {
            datePicker = new DatePickerDialog(getContext(), this, 2018, 1, 1);
            datePicker.show();
        }

    }



}
