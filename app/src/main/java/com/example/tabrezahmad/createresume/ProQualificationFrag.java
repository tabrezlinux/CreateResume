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

public class ProQualificationFrag extends Fragment implements View.OnClickListener, View.OnTouchListener, DatePickerDialog.OnDateSetListener {

    private TextInputLayout tl_course, tl_institute, tl_year, tl_marks;
    private EditText et_course, et_institute, et_year, et_marks;
    private RadioGroup rg_is_pursuing, rg_marks_type;
    private String IS_PURSUING, MARKS_TYPE_IS;


    private static final String STATUS_PURSUING = "PURSUING";
    private static final String STATUS_PASSED = "PASSED";
    private static final String TYPE_CGPA = "CGPA";
    private static final String TYPE_PERCENT = "PERCENT";

    private DatePickerDialog datePicker;
    private AcademicQualification QUALIFICATION_OBJ;

    View root;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.qualification_academic, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        setupViews();
        super.onViewCreated(view, savedInstanceState);


    }

    @Override
    public void onPause() {
        Toast.makeText(getContext(), "Paused Frag", Toast.LENGTH_SHORT).show();
        //saveFormData();
        super.onPause();
    }

    private void setupViews() {

        // SETUP ACADEMIC QUALIFICATION ENTITY OBJECT
        QUALIFICATION_OBJ = new AcademicQualification();

        // SET BUTTONS
        FloatingActionButton fab = root.findViewById(R.id.fab);
        fab.setOnClickListener(this);

        // SETUP FIELDS
        et_course = root.findViewById(R.id.course);
        et_institute = root.findViewById(R.id.institute);
        et_year = root.findViewById(R.id.et_year);
        et_marks = root.findViewById(R.id.marks_obtained);

        // SET RADIO BUTTONS
        rg_is_pursuing = getActivity().findViewById(R.id.rg_pursuing_or_passed);
        rg_marks_type = getActivity().findViewById(R.id.rg_marks_type);

        // SETUP FIELDS LAYOUTS
        tl_course = root.findViewById(R.id.tl_course);
        tl_institute = root.findViewById(R.id.tl_institute);
        tl_year = root.findViewById(R.id.tl_year);
        tl_marks = root.findViewById(R.id.tl_marks);

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
                        IS_PURSUING = STATUS_PURSUING;
                        tl_year.setHint("Current Year");

                        // disable marks_type radios
                        rg_marks_type.setEnabled(false);
                        rg_marks_type.getChildAt(0).setEnabled(false);
                        rg_marks_type.getChildAt(1).setEnabled(false);
                        et_marks.setEnabled(false);
                        break;

                    case R.id.rb_passed:
                        IS_PURSUING = STATUS_PASSED;
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
                        MARKS_TYPE_IS = TYPE_CGPA;
                        tl_marks.setHint(TYPE_CGPA + " Marks");
                        Toast.makeText(getContext(), TYPE_CGPA, Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.rb_percentage:
                        MARKS_TYPE_IS = TYPE_PERCENT;
                        tl_marks.setHint("Marks Percentage (%)");
                        Toast.makeText(getContext(), TYPE_PERCENT, Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        // set IS_PURSUING == pursuing/passed
        IS_PURSUING = rg_is_pursuing.getCheckedRadioButtonId() == R.id.rb_passed ? STATUS_PASSED : STATUS_PURSUING;

        // set MARKS_TYPE = cgpa/percentage
        MARKS_TYPE_IS = rg_marks_type.getCheckedRadioButtonId() == R.id.rb_cgpa ? TYPE_CGPA : TYPE_PERCENT;

    }


    // SAVE FORM DATA
    private void saveFormData() {


        // MUST SET FOREIGN_VALUE OF MAIN_ACTIVITY.BASIC_INFO_FOREIGN_KEY_ID
        QUALIFICATION_OBJ.basic_id = MainActivity.BASIC_INFO_FOREIGN_KEY_ID;

        // SET FORM DATA
        QUALIFICATION_OBJ.course = et_course.getText().toString();
        QUALIFICATION_OBJ.institute = et_institute.getText().toString();
        QUALIFICATION_OBJ.passing_status = IS_PURSUING;

        String passing_year = et_year.getText().toString();
        Date year = new Date(System.currentTimeMillis());
        QUALIFICATION_OBJ.year = year;

        try {
            year = Date.valueOf(passing_year);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(getContext().getClass().getName(), "Parsing failed Date/Year");
        }


        // if PASSED then et_year, et_marks, et_marks_type is required
        // else et_year == either empty/required
        if (IS_PURSUING == STATUS_PASSED) {
            QUALIFICATION_OBJ.year = year;
            try {
                QUALIFICATION_OBJ.marks = Double.parseDouble(et_marks.getText().toString());
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getClass().getName(), "Parsing Error, Invalid Marks");
            }

            QUALIFICATION_OBJ.marks_type = MARKS_TYPE_IS;

        } else {
            QUALIFICATION_OBJ.year = year;
            QUALIFICATION_OBJ.marks = null;
            QUALIFICATION_OBJ.marks_type = null;
        }

        // validate form data
        boolean isValid = true; //validateFormData(QUALIFICATION_OBJ);

        // if form is valid then save
        if (isValid) {

            // save model
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    // insert and get insert_id
                    Long id = MainActivity.mDatabase.AcademicQualificationDAO().insert(QUALIFICATION_OBJ);
                    // set insert_id to Qualification Object
                    QUALIFICATION_OBJ.uid = id;
                    QUALIFICATION_OBJ.basic_id = MainActivity.BASIC_INFO_FOREIGN_KEY_ID;
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getContext(), "Inserted ID = " + String.valueOf(QUALIFICATION_OBJ.uid), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
            t.start();
        }


    }


    private boolean validateFormData(AcademicQualification qualification) {

        boolean IS_VALID_FORM = true;

        // et_course ==null || empty
        if (FormValidator.isEmpty(et_course))
            // field is required
            IS_VALID_FORM = false;

        // et_institute !=null
        if (FormValidator.isEmpty(et_institute))
            // field is required
            IS_VALID_FORM = false;

        // IF PASSED then et_year, marks_type and marks are required
        if (IS_PURSUING == STATUS_PASSED) {
            IS_VALID_FORM = FormValidator.isEmpty(et_year);
            IS_VALID_FORM = FormValidator.isEmpty(et_marks);
            IS_VALID_FORM = (MARKS_TYPE_IS == TYPE_CGPA || MARKS_TYPE_IS == TYPE_PERCENT) ? true : false;
        }

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

            case R.id.fab:
                saveFormData();
                Toast.makeText(getContext(), "click received at" + getClass().getName(), Toast.LENGTH_SHORT).show();
                break;
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
