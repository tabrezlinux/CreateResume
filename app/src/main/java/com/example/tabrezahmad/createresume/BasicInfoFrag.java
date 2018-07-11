package com.example.tabrezahmad.createresume;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
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

import com.example.tabrezahmad.createresume.database.Entity.BasicInfo;
import com.example.tabrezahmad.createresume.database.FormValidator;

import java.sql.Date;
import java.util.Calendar;

public class BasicInfoFrag extends Fragment implements View.OnClickListener, OnDateSetListener, RadioGroup.OnCheckedChangeListener,
        View.OnFocusChangeListener, View.OnTouchListener {

    private EditText
            name,
            dob,
            father_name,
            language,
            nationality,
            passport,
            linkedIn,
            website,
            email,
            mobile,
            address;

    private RadioGroup
            rg_gender,
            rg_marital_status;

    private String
            Gender,
            Marital_Status;

    private TextInputLayout
            tl_name,
            tl_dob,
            tl_father_name,
            tl_language,
            tl_nationality,
            tl_passport,
            tl_linkedin,
            tl_website,
            tl_email,
            tl_mobile,
            tl_address;

    private final String GENDER_MALE = "M";
    private final String GENDER_FEMALE = "F";
    private final String STATUS_MARRIED = "MR";
    private final String STATUS_UNMARRIED = "UN";

    private BasicInfo BASIC_INFO_OBJ;

    View root;
    DatePickerDialog datePicker;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        //MainActivity.mDatabase.BasicInfoDAO().get
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //MainActivity.BASIC_INFO_FOREIGN_KEY_ID == null ? null :
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.basic_info, container, false);
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
        super.onPause();
    }

    // SETUP VIEWS
    private void setupViews() {

        // BASIC INFO ENTITY OBJECT
        BASIC_INFO_OBJ = new BasicInfo();

        FloatingActionButton fab = root.findViewById(R.id.fab);
        fab.setOnClickListener(this);

        // Fields
        name = root.findViewById(R.id.name);
        dob = root.findViewById(R.id.dob);
        father_name = root.findViewById(R.id.father_name);

        rg_gender = root.findViewById(R.id.rg_gender);
        rg_marital_status = root.findViewById(R.id.rg_marital_status);

        language = root.findViewById(R.id.language);
        nationality = root.findViewById(R.id.nationality);
        passport = root.findViewById(R.id.passport);

        linkedIn = root.findViewById(R.id.linkedin);
        website = root.findViewById(R.id.website);
        email = root.findViewById(R.id.email);
        mobile = root.findViewById(R.id.mobile);
        address = root.findViewById(R.id.address);

        // On Focus Changed
        name.setOnFocusChangeListener(this);
        dob.setOnFocusChangeListener(this);
        father_name.setOnFocusChangeListener(this);
        language.setOnFocusChangeListener(this);
        nationality.setOnFocusChangeListener(this);
        passport.setOnFocusChangeListener(this);
        linkedIn.setOnFocusChangeListener(this);
        website.setOnFocusChangeListener(this);
        email.setOnFocusChangeListener(this);
        mobile.setOnFocusChangeListener(this);
        address.setOnFocusChangeListener(this);

        // On Touch
        dob.setOnTouchListener(this);

        // Gender OnCheck
        rg_gender.setOnCheckedChangeListener(this);

        // Set Gender by default
        Gender = rg_gender.getCheckedRadioButtonId() == R.id.radio_male ? GENDER_MALE : GENDER_FEMALE;

        // Marital Status OnCheck
        rg_marital_status.setOnCheckedChangeListener(this);

        // Set Status by default
        Marital_Status = rg_marital_status.getCheckedRadioButtonId() == R.id.radio_unmarried ? STATUS_UNMARRIED : STATUS_MARRIED;


        // ET Layouts
        tl_name = root.findViewById(R.id.tl_name);
        tl_dob = root.findViewById(R.id.tl_dob);
        tl_father_name = root.findViewById(R.id.tl_father_name);
        tl_language = root.findViewById(R.id.tl_language);
        tl_nationality = root.findViewById(R.id.tl_nationality);
        tl_passport = root.findViewById(R.id.tl_passport);
        tl_linkedin = root.findViewById(R.id.tl_linkedin);
        tl_website = root.findViewById(R.id.tl_website);
        tl_email = root.findViewById(R.id.tl_email);
        tl_mobile = root.findViewById(R.id.tl_mobile);
        tl_address = root.findViewById(R.id.tl_address);

//        CharacterStyle cs = new CharacterStyle() {
//            @Override
//            public void updateDrawState(TextPaint tp) {
//                tp.setColor(getResources().getColor(R.color.colorAccent));
//                tp.bgColor = getResources().getColor(R.color.colorPrimaryDark);
//            }
//        };
//        name.getEditableText().setSpan(cs, 0, 6, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);

//        TextWatcher tw = new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                if (!FormValidator.isLengthRange(s.toString(), 4, 60))
//                    tl_name.setError("Name is required");
//                else
//                    tl_name.setError(null);
//            }
//        };
//        this.name.addTextChangedListener(tw);

    }

    // VALIDATE FORM
    private boolean validateFormData() {

        // get form data
        String name, date, father_name, gender, marital_status, language, nationality, passport, linkedIn, website, email, mobile, address;

        name = this.name.getText().toString();
        date = this.dob.getHint().toString();
        father_name = this.father_name.getText().toString();
        gender = this.Gender;
        marital_status = this.Marital_Status;
        language = this.language.getText().toString();
        nationality = this.nationality.getText().toString();
        passport = this.passport.getText().toString();
        linkedIn = this.linkedIn.getText().toString();
        website = this.website.getText().toString();
        email = this.email.getText().toString();
        mobile = this.mobile.getText().toString();
        address = this.address.getText().toString();


        // name, required
        FormValidator.isEmpty(name);
        FormValidator.isLengthRange(name, 1, 60);

        // dob, required
        FormValidator.isDateValid(FormValidator.createDate(date));

        // father, required
        FormValidator.isNull(father_name);
        FormValidator.isLengthRange(father_name, 1, 60);

        // Gender, required
        FormValidator.isGenderValid(gender);

        // marital status, required
        FormValidator.isMaritalStatusValid(marital_status);

        // language, required

        // nationality

        // passport

        // linkedIn

        // website
        FormValidator.isWebUrl(website);

        // email, required
        FormValidator.isEmpty(email);
        FormValidator.isEmailAddress(email);

        // mobile, required
        FormValidator.isEmpty(mobile);
        FormValidator.isMobileNumber(mobile);

        // address, required
        FormValidator.isEmpty(address);
        FormValidator.isLengthRange(address, 1, 255);

        return true;
    }

    // SAVE FORM
    private void saveFormData() {

        // get form data
        BASIC_INFO_OBJ.name = name.getText().toString();

        // set date
        Date date = new Date(System.currentTimeMillis());
        BASIC_INFO_OBJ.date_of_birth = date;

        // parse date
        try {
            date = new Date(Long.parseLong(dob.getHint().toString()));
            BASIC_INFO_OBJ.date_of_birth = date;
            Log.d("Date", date.toString());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Date", "Parsing Error : Date");
        }

        // set father name
        BASIC_INFO_OBJ.father_name = father_name.getText().toString();

        // set gender
        BASIC_INFO_OBJ.gender = Gender;

        // set status
        BASIC_INFO_OBJ.marital_status = Marital_Status;

        // set language (split multiple keywords)
        String lang = language.getText().toString();
        BASIC_INFO_OBJ.Language = (lang == null ? null : lang.split(","));

        // set nationality
        BASIC_INFO_OBJ.nationality = nationality.getText().toString();

        // set passport number
        BASIC_INFO_OBJ.passport = passport.getText().toString();

        // set linkedIn id
        BASIC_INFO_OBJ.linked_in = linkedIn.getText().toString();

        // set website address
        BASIC_INFO_OBJ.website = website.getText().toString();

        // set email id
        BASIC_INFO_OBJ.email = email.getText().toString();

        // set mobile number (split multiple keywords)
        String mob = mobile.getText().toString();
        BASIC_INFO_OBJ.mobile = (mob == null) ? null : mob.split(",");

        // set address
        BASIC_INFO_OBJ.address = address.getText().toString();

        // VALIDATE FORM DATA
        Log.d("REACHED", "here");

        // SAVE MODEL ON NEW THREAD
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {

                // update
                if ((BASIC_INFO_OBJ.uid != null) && BASIC_INFO_OBJ.uid == MainActivity.BASIC_INFO_FOREIGN_KEY_ID) {

                    MainActivity.mDatabase.BasicInfoDAO().update(BASIC_INFO_OBJ);
                    BASIC_INFO_OBJ.uid = MainActivity.BASIC_INFO_FOREIGN_KEY_ID;

                    // show message
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getContext(), "Record updated", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                // else insert
                else {

                    BASIC_INFO_OBJ.uid = MainActivity.mDatabase.BasicInfoDAO().insert(BASIC_INFO_OBJ);
                    MainActivity.BASIC_INFO_FOREIGN_KEY_ID = BASIC_INFO_OBJ.uid;

                    // show message
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getContext(), "record inserted ID = " + String.valueOf(BASIC_INFO_OBJ.uid), Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        });
        t.start();

    }

    // RESET FORM
    private void resetForm() {

        name.getText().clear();
        dob.getText().clear();
        father_name.getText().clear();

        rg_gender.check(0);
        rg_marital_status.check(0);

        language.getText().clear();
        nationality.getText().clear();
        passport.getText().clear();
        linkedIn.getText().clear();
        website.getText().clear();

        email.getText().clear();
        mobile.getText().clear();
        address.getText().clear();

    }

    // RESET FORM ERRORS
    private void resetFormErrors() {
        tl_name.setError(null);
        tl_dob.setError(null);
        tl_father_name.setError(null);
        tl_language.setError(null);
        tl_nationality.setError(null);
        tl_linkedin.setError(null);
        tl_website.setError(null);
        tl_email.setError(null);
        tl_mobile.setError(null);
        tl_address.setError(null);
    }

    // SHOW DATEPICKER -----------------------------------------------------------------------------
    private void showDatePickerDialog(final EditText mView) {

        // create and show dialog if not exists
        if (datePicker != null) {
            datePicker.show();
        } else {
            datePicker = new DatePickerDialog(getContext(), this, 2018, 1, 1);
            datePicker.show();
        }

    }

    // ON RADIO BUTTON CHECKED ---------------------------------------------------------------------
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        switch (group.getId()) {

            // If GENDER
            case R.id.rg_gender:
                switch (checkedId) {
                    case R.id.radio_male:           // set Gender MALE
                        Gender = GENDER_MALE;
                        break;
                    case R.id.radio_female:         // set Gender FEMALE
                        Gender = GENDER_FEMALE;
                        break;
                }
                break;

            // If MARITAL STATUS
            case R.id.rg_marital_status:
                switch (checkedId) {
                    case R.id.radio_married:
                        Marital_Status = STATUS_MARRIED;
                        break;
                    case R.id.radio_unmarried:
                        Marital_Status = STATUS_UNMARRIED;
                        break;
                }
                break;
        }

    }

    // ON VIEW FOCUS -------------------------------------------------------------------------------
    @Override
    public void onFocusChange(View v, boolean hasFocus) {

        // On Focus
        switch (v.getId()) {

            // Name
            case R.id.name:
                if (hasFocus)
                    tl_name.setError(null);
                break;

            // DOB
            case R.id.dob:
                if (hasFocus) {
                    tl_dob.setError(null);
                    showDatePickerDialog(dob);
                }
                break;

            // Father Name
            case R.id.father_name:
                if (hasFocus)
                    tl_name.setError(null);
                break;

            // Language
            case R.id.language:
                if (hasFocus)
                    tl_name.setError(null);
                break;

            // Nationality
            case R.id.nationality:
                if (hasFocus)
                    tl_name.setError(null);
                break;

            // Passport
            case R.id.passport:
                if (hasFocus)
                    tl_name.setError(null);
                break;

            // LinkedIn
            case R.id.linkedin:
                if (hasFocus)
                    tl_name.setError(null);
                break;

            // Website URL
            case R.id.website:
                if (hasFocus)
                    tl_name.setError(null);
                break;

            // Email
            case R.id.email:
                if (hasFocus)
                    tl_name.setError(null);
                break;

            // Mobile
            case R.id.mobile:
                if (hasFocus)
                    tl_name.setError(null);
                break;

            // Address
            case R.id.address:
                if (hasFocus)
                    tl_name.setError(null);
                break;

        }

        // On Focus Loose
        switch (v.getId()) {
            // Name
            case R.id.name:
                if (!hasFocus) {
                    if (FormValidator.isEmpty(name))
                        tl_name.setError("name is required");
                    else {
                        if (!FormValidator.isLengthRange(name, 4, 60))
                            tl_name.setError("4-60 characters required");
                    }
                }
                break;

            // DOB
            case R.id.dob:
                if (!hasFocus) {
                    if (FormValidator.isEmpty(dob))
                        tl_dob.setError("date of birth is required");
                    else {
                        if (!FormValidator.isDateValid(new Date(System.currentTimeMillis())))
                            tl_dob.setError("invalid date");
                    }
                }
                break;

            // Father Name
            case R.id.father_name:
                if (!hasFocus) {
                    if (FormValidator.isEmpty(father_name))
                        tl_father_name.setError("father name is required");
                    else {
                        if (!FormValidator.isLengthRange(father_name, 4, 60))
                            tl_father_name.setError("4-60 characters required");
                    }
                }
                break;

            // Language
            case R.id.language:
                if (!hasFocus) {
                    if (!FormValidator.isEmpty(language))
                        if (!FormValidator.isLengthRange(language, 4, 255))
                            tl_language.setError("invalid language");
                }
                break;

            // Nationality
            case R.id.nationality:
                if (!hasFocus) {
                    if (!FormValidator.isEmpty(nationality))
                        if (!FormValidator.isLengthRange(nationality, 4, 60))
                            tl_nationality.setError("invalid nationality");
                }
                break;

            // Passport
            case R.id.passport:
                if (!hasFocus) {
                    if (!FormValidator.isEmpty(passport))
                        if (!FormValidator.isLengthRange(passport, 4, 60))
                            tl_passport.setError("invalid passport number");
                }
                break;

            // LinkedIn
            case R.id.linkedin:
                if (!hasFocus) {
                    if (!FormValidator.isEmpty(linkedIn))
                        if (!FormValidator.isLengthRange(linkedIn, 4, 200))
                            tl_linkedin.setError("invalid LinkedIn profile");
                }
                break;

            // Website URL
            case R.id.website:
                if (!hasFocus) {
                    if (!FormValidator.isEmpty(website))
                        if (!FormValidator.isWebUrl(website.getText().toString()))
                            tl_website.setError("invalid website URL");
                }
                break;

            // Email
            case R.id.email:
                if (!hasFocus) {
                    if (!FormValidator.isEmpty(email))
                        if (!FormValidator.isEmailAddress(email.getText().toString()))
                            tl_email.setError("invalid email URL");
                }
                break;

            // Mobile
            case R.id.mobile:
                if (!hasFocus) {
                    if (!FormValidator.isEmpty(mobile))
                        if (!FormValidator.isMobileNumber(mobile.getText().toString()))
                            tl_mobile.setError("invalid mobile number");
                }
                break;

            // Address
            case R.id.address:
                if (!hasFocus) {
                    if (!FormValidator.isEmpty(address))
                        if (!FormValidator.isLengthRange(address, 4, 160))
                            tl_address.setError("invalid mobile number");
                }
                break;

        }

    }

    // ON VIEW TOUCH -------------------------------------------------------------------------------
    @Override
    public boolean onTouch(View v, MotionEvent event) {

        switch (v.getId()) {
            case R.id.dob:
                if (event.getAction() == MotionEvent.ACTION_DOWN)
                    showDatePickerDialog(dob);
                break;
        }

        return true;
    }

    // ON DATE SET ---------------------------------------------------------------------------------
    @Override
    public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {

        // String y = String.format("%4d",year);
        // String m = String.format("%02d", (month + 1) );
        // String d = String.format("%02d",dayOfMonth);

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, dayOfMonth);

        // format date
        //DateFormat.format("EEE, d MMMM yyyy",)
        dob.setText(DateFormat.format("d MMMM yyyy", calendar.getTimeInMillis()).toString());
        //dob.setText(String.valueOf(calendar.getTimeInMillis()));
        dob.setHint(String.valueOf(calendar.getTimeInMillis()));

    }

    // ON CLICK ------------------------------------------------------------------------------------
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                saveFormData();
            default:
                break;
        }

    }

}
