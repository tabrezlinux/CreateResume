package com.example.tabrezahmad.createresume;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.service.autofill.RegexValidator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.tabrezahmad.createresume.database.Entity.AcademicQualification;
import com.example.tabrezahmad.createresume.database.Entity.BasicInfo;

import java.util.List;

public class AcademicQualificationFrag extends Fragment implements DatePickerDialog.OnDateSetListener {
    View root;

    private TextInputLayout tl_course, tl_institute, tl_year_of_passing, tl_marks_obtained;
    private EditText course, institute, year_of_passing_appearing, marks_obtained;
    private RadioGroup rg_pursuing_or_passed, rg_marks_type;
    private String PURSUING_PASSED, MARKS_TYPE_CGPA_OR_PERCENTAGE;

    private enum PURSUING_OR_PASSED {pursuing, passed}

    ;

    private enum MARKS_TYPE {cgpa, percentage}

    ;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.qualification_academic, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupViews();

    }

    @Override
    public void onPause() {
        Toast.makeText(getContext(), "Paused Frag", Toast.LENGTH_SHORT).show();
        saveFormData();
        super.onPause();
    }

    private void setupViews() {

        course = getActivity().findViewById(R.id.course);
        institute = getActivity().findViewById(R.id.institute);
        year_of_passing_appearing = getActivity().findViewById(R.id.et_year);
        marks_obtained = getActivity().findViewById(R.id.marks_obtained);

        rg_pursuing_or_passed = getActivity().findViewById(R.id.rg_pursuing_or_passed);
        rg_marks_type = getActivity().findViewById(R.id.rg_marks_type);

        rg_pursuing_or_passed.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_pursuing:
                        PURSUING_PASSED = "PURSUING";
                        year_of_passing_appearing.setHint("Current Semester/Year");

                        // disable marks_type radios
                        rg_marks_type.setEnabled(false);
                        rg_marks_type.getChildAt(0).setEnabled(false);
                        rg_marks_type.getChildAt(1).setEnabled(false);
                        marks_obtained.setEnabled(false);

                        Toast.makeText(getContext(), "PURSUING", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.rb_passed:
                        PURSUING_PASSED = "PASSED";
                        year_of_passing_appearing.setHint("Year of passing");

                        // enable marks_type radios
                        rg_marks_type.setEnabled(true);
                        rg_marks_type.getChildAt(0).setEnabled(true);
                        rg_marks_type.getChildAt(1).setEnabled(true);
                        marks_obtained.setEnabled(true);

                        Toast.makeText(getContext(), "PASSED", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        rg_marks_type.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_cgpa:
                        MARKS_TYPE_CGPA_OR_PERCENTAGE = "CGPA";
                        marks_obtained.setHint("CGPA Marks");
                        Toast.makeText(getContext(), "CGPA", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.rb_percentage:
                        MARKS_TYPE_CGPA_OR_PERCENTAGE = "PERCENTAGE";
                        marks_obtained.setHint("Marks Percentage (%)");
                        Toast.makeText(getContext(), "PERCENTAGE", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        // set pursuing/passed
        PURSUING_PASSED = rg_pursuing_or_passed.getCheckedRadioButtonId() == R.id.rb_passed ? "PASSED" : "PURSUING";
        // set cgpa/percentage
        MARKS_TYPE_CGPA_OR_PERCENTAGE = rg_marks_type.getCheckedRadioButtonId() == R.id.rb_cgpa ? "CGPA" : "PERCENTAGE";

        year_of_passing_appearing.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                DatePicker dp = DatePicker.getInstance(getContext(), AcademicQualificationFrag.this);
                dp.show();
            }
        });

    }


    private void saveFormData() {

        // get form data
        final AcademicQualification qualification = new AcademicQualification();
        qualification.course = course.getText().toString();
        qualification.institute = institute.getText().toString();
        qualification.pursuing = PURSUING_PASSED;
        //qualification.year = year_of_passing_appearing.getText().toString();
        qualification.marks = Double.parseDouble(marks_obtained.getText().toString());
        qualification.marks_type = MARKS_TYPE_CGPA_OR_PERCENTAGE;

        // validate form data
        boolean isValid = validateFormData(qualification);

        // if form is valid then save
        if (isValid) {

            // save model
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    MainActivity.mDatabase.AcademicQualificationDAO().insert(qualification);
                    final List<AcademicQualification> qua = MainActivity.mDatabase.AcademicQualificationDAO().getAllQualification();
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            String ids = "";
                            for (AcademicQualification q : qua) {
                                ids += q.uid + " ";
                            }
                            Toast.makeText(getContext(), "record inserted ID = " + ids, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
            t.start();
        }

    }


    private boolean validateFormData(AcademicQualification qualification) {

        // course ==null || empty
        if (qualification.course == null || qualification.course.isEmpty())
            return false; // invalid

        // institute !=null
        if (qualification.course == null || qualification.course.isEmpty())
            return false;

        // IF PURSUING then NULLIFY marks_type and marks
        if (qualification.pursuing != null) {

            if (qualification.pursuing == "PURSUING") {
                qualification.marks_type = null;
                marks_obtained = null; // and marks_type=null marks=null
            }

            // IF passed then NONNULL marks_type and marks
            if (qualification.pursuing == "PASSED") {

                if (qualification.marks_type == null || qualification.marks_type.isEmpty()) {
                    return false; // marks_type required
                }
                if (Double.isNaN(qualification.marks) || qualification.marks <= 0) {
                    return false; // marks_obtained required, could not be 0
                }
            }
        }

        return true;

    }

    @Override
    public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
        String date = (month + 1) + "-" + dayOfMonth + "-" + year;
        year_of_passing_appearing.setText(date);
    }


}
