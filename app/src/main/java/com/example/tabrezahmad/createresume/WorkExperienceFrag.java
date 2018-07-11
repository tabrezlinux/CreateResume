package com.example.tabrezahmad.createresume;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.joda.time.DateTime;
import org.joda.time.DateTimeFieldType;
import org.joda.time.Days;
import org.joda.time.Duration;
import org.joda.time.DurationField;
import org.joda.time.DurationFieldType;
import org.joda.time.LocalDate;
import org.joda.time.ReadablePartial;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class WorkExperienceFrag extends Fragment implements View.OnClickListener, View.OnTouchListener, DatePickerDialog.OnDateSetListener {

    private TextInputEditText et_company, et_designation, et_role, et_from_date, et_to_date;
    private RadioGroup rg_working_status;
    private TextInputLayout tl_company, tl_designation, tl_role, tl_from_date, tl_to_date;

    private final String WORKING_STATUS_WORKED = "WORKED";
    private final String WORKING_STATUS_WORKING = "WORKING";

    private String IS_WORKING;

    View root;
    DatePickerDialog datePickerFrom, datePickerTo;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.work_experience, container, false);
        return root;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // SETUP FIELDS
        et_company = root.findViewById(R.id.et_company);
        et_designation = root.findViewById(R.id.et_designation);
        et_role = root.findViewById(R.id.et_role);
        et_from_date = root.findViewById(R.id.et_from_date);
        et_to_date = root.findViewById(R.id.et_to_date);

        // SETUP RADIO GROUP
        rg_working_status = root.findViewById(R.id.rg_working_status);

        // SETUP FILED LAYOUTS
        tl_company = root.findViewById(R.id.tl_company);
        tl_designation = root.findViewById(R.id.tl_designation);
        tl_role = root.findViewById(R.id.tl_role);
        tl_from_date = root.findViewById(R.id.tl_from_date);
        tl_to_date = root.findViewById(R.id.tl_to_date);

        // SET ON CLICK
        et_from_date.setOnClickListener(this);
        et_to_date.setOnClickListener(this);

        // SET ON TOUCH
        et_from_date.setOnTouchListener(this);
        et_to_date.setOnTouchListener(this);

        // SET ON CHECK RADIO (from date)
        rg_working_status.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    // IF WORKED
                    case R.id.rb_worked:
                        et_from_date.setEnabled(true);
                        et_to_date.setEnabled(true);
                        break;
                    // IF CURRENTLY WORKING
                    case R.id.rb_working:
                        et_from_date.setEnabled(true);
                        et_to_date.setEnabled(false);
                        break;

                }
            }
        });

        // SET RADIO BUTTON DEFAULTS
        IS_WORKING = (rg_working_status.getCheckedRadioButtonId() == R.id.rb_working) ? WORKING_STATUS_WORKING : WORKING_STATUS_WORKED;

        // SET FROM AND TO DATE FIELDS
        switch (IS_WORKING) {
            // IF WORKED
            case WORKING_STATUS_WORKED:
                et_from_date.setEnabled(true);
                et_to_date.setEnabled(true);
                break;
            // IF CURRENTLY WORKING
            case WORKING_STATUS_WORKING:
                et_from_date.setEnabled(true);
                et_to_date.setEnabled(false);
                break;
        }


    }

    // SHOW DATEPICKER -----------------------------------------------------------------------------
    private void showDatePickerDialog(final EditText mView) {

        switch (mView.getId()) {

            // create and show From dialog if not exists
            case R.id.et_from_date:
                if (datePickerFrom != null) {
                    datePickerFrom.show();
                } else {
                    datePickerFrom = new DatePickerDialog(getContext(), this, 2018, 1, 1);
                    datePickerFrom.getDatePicker().setId(R.id.et_from_date);
                    datePickerFrom.setTitle("From");
                    datePickerFrom.show();
                }
                break;

            // create and show From dialog if not exists
            case R.id.et_to_date:
                if (datePickerTo != null) {
                    datePickerTo.getDatePicker().setId(R.id.et_to_date);
                    datePickerTo.setTitle("To");
                    datePickerTo.show();
                } else {
                    datePickerTo = new DatePickerDialog(getContext(), this, 2018, 1, 1);
                    datePickerTo.show();
                }
                break;
        }


    }

    // ON DATE SET ---------------------------------------------------------------------------------
    @Override
    public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, dayOfMonth);

        switch (view.getId()) {
            // set format date
            case R.id.et_from_date:
                et_from_date.setText(DateFormat.format("MMM dd, yyyy", calendar.getTimeInMillis()).toString());
                et_from_date.setHint(String.valueOf(calendar.getTimeInMillis()));
                break;
            // set to date
            case R.id.et_to_date:
                et_to_date.setText(DateFormat.format("MMM dd, yyyy", calendar.getTimeInMillis()).toString());
                et_to_date.setHint(String.valueOf(calendar.getTimeInMillis()));
                break;
        }

        // evaluate the duration
        if (et_from_date.getHint() != null && et_to_date.getHint() != null) {
            if (et_from_date.getHint().length() > 0 && et_to_date.getHint().length() > 0) {
                String dateDiff = DateUtils.getRelativeTimeSpanString(
                        Long.parseLong(et_from_date.getHint().toString()),
                        Long.parseLong(et_to_date.getHint().toString()),
                        2
                ).toString();

                LocalDate jFromDate = new LocalDate(Long.parseLong(et_from_date.getHint().toString()));
                LocalDate jToDate = new LocalDate(Long.parseLong(et_to_date.getHint().toString()));

                DateTime dtFrom = new DateTime(jFromDate);
                DateTime dtTo = new DateTime(jToDate);

                Days days = Days.daysBetween(jFromDate,jToDate);
                DateTime dt = new DateTime(days);
                int y = dt.get(DateTimeFieldType.year());
                int m = dt.get(DateTimeFieldType.monthOfYear());
                int d = dt.get(DateTimeFieldType.dayOfMonth());

                Toast.makeText(getContext(), "y = " + y + "m =" + m + "d =" + d  , Toast.LENGTH_LONG).show();

            }
        }


    }

    // ON CLICK ------------------------------------------------------------------------------------
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                Toast.makeText(getContext(), "click received at" + getClass().getName(), Toast.LENGTH_SHORT).show();
            case R.id.et_from_date:
                showDatePickerDialog(et_from_date);
                break;
            case R.id.et_to_date:
                showDatePickerDialog(et_to_date);
                break;
        }
    }

    // ON TOUCH ------------------------------------------------------------------------------------
    @Override
    public boolean onTouch(View v, MotionEvent event) {

        switch (v.getId()) {
            case R.id.et_from_date:
                showDatePickerDialog(et_from_date);
                break;
            case R.id.et_to_date:
                showDatePickerDialog(et_to_date);
                break;

        }

        return true;
    }


}
