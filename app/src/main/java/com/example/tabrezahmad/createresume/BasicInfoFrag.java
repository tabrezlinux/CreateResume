package com.example.tabrezahmad.createresume;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.ex.chips.RecipientEditTextView;
import com.android.ex.chips.recipientchip.DrawableRecipientChip;
import com.example.tabrezahmad.createresume.database.Entity.BasicInfo;
import com.example.tabrezahmad.createresume.database.FormValidator;

import java.nio.channels.Channels;
import java.sql.Date;
import java.util.Calendar;

public class BasicInfoFrag extends Fragment implements View.OnClickListener, OnDateSetListener, RadioGroup.OnCheckedChangeListener,
        View.OnTouchListener {

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
    //mobile,
    address_line_1,
            address_line_2,
            city,
            state,
            pincode,
            country;
    private RecipientEditTextView mobile;


    private RadioGroup
            rg_gender,
            rg_marital_status;

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
            tl_address_line_1,
            tl_address_line_2,
            tl_city,
            tl_state,
            tl_pincode,
            tl_country;

    //private BasicInfo BASIC_INFO_OBJ;

    View root;
    DatePickerDialog datePicker;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        //EditResumeActivity.mDatabase.BasicInfoDAO().get
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // EditResumeActivity.BI_FOREIGN_KEY_ID == null ? null :
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.basic_info, container, false);
        return root;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupViews();

        // set field values
        setValuesToInputText();


    }

    @Override
    public void onPause() {
        Toast.makeText(getContext(), "Paused Frag", Toast.LENGTH_SHORT).show();
        //saveFormData();
        printBasicModelData();
        super.onPause();

    }

    // SETUP VIEWS
    private void setupViews() {

        // BASIC INFO ENTITY OBJECT
        //BASIC_INFO_OBJ = new BasicInfo();

        //FloatingActionButton fab = root.findViewById(R.id.fab);
        //fab.setOnClickListener(this);

        // init Fields
        name = root.findViewById(R.id.name);
        dob = root.findViewById(R.id.dob);
        father_name = root.findViewById(R.id.father_name);

        rg_gender = root.findViewById(R.id.rg_gender);
        rg_marital_status = root.findViewById(R.id.rg_marital_status);

        email = root.findViewById(R.id.email);
        mobile = root.findViewById(R.id.mobile);

        address_line_1 = root.findViewById(R.id.address_line_1);
        address_line_2 = root.findViewById(R.id.address_line_2);
        city = root.findViewById(R.id.city);
        state = root.findViewById(R.id.state);
        pincode = root.findViewById(R.id.pincode);
        country = root.findViewById(R.id.country);

        language = root.findViewById(R.id.language);
        nationality = root.findViewById(R.id.nationality);
        passport = root.findViewById(R.id.passport);

        linkedIn = root.findViewById(R.id.linkedin);
        website = root.findViewById(R.id.website);


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
        tl_address_line_1 = root.findViewById(R.id.tl_address_line_1);
        tl_address_line_2 = root.findViewById(R.id.tl_address_line_2);
        tl_city = root.findViewById(R.id.tl_city);
        tl_state = root.findViewById(R.id.tl_state);
        tl_pincode = root.findViewById(R.id.tl_pincode);
        tl_country = root.findViewById(R.id.tl_country);


        //------------------------------------------------------------------------------ Mobile Chip

        //final RecipientEditTextView mobileNumberChip = mobile;
        mobile.setMaxChips(3);
        mobile.setChipNotCreatedListener(new RecipientEditTextView.ChipNotCreatedListener() {
            @Override
            public void chipNotCreated(String chipText) {
                Toast.makeText(getContext(), "You set the max number of mobile number." + chipText, Toast.LENGTH_SHORT).show();
            }
        });
        mobile.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                DrawableRecipientChip[] chips = mobile.getSortedRecipients();
                for (DrawableRecipientChip chip : chips) {
                    Log.v("DrawableChip", chip.getEntry().getDisplayName() + " " + chip.getEntry().getDestination());
                }
            }
        }, 5000);

        // 1. check validation
        // not null
        // not empty
        // min 4 character
        // max 60 char
        // 2. if valid
        // remover errors
        // save data to model
        // 3. else
        // show error

        // ----------------------------------------------------------------------------------------- name
        // not null, not empty,  4 - 60 char
        name.addTextChangedListener(FormValidator.getNameWatcher(new FormValidator.MyTextWatcherCallback() {
            @Override
            public void onError(int error_code) {
                switch (error_code) {
                    case FormValidator.ERROR_CODE_IS_EMPTY:
                        tl_name.setError("Required");
                        break;
                    case FormValidator.ERROR_CODE_OUT_OF_RANGE:
                        tl_name.setError("4 - 60 characters required");
                        break;
                    default:
                        tl_name.setError(null);
                }
            }

            @Override
            public void onSuccess(String s) {
                tl_name.setError(null);
                NewResumeActivity.BASIC_INFO.name = s;
            }
        }, 4, 60));

        // ----------------------------------------------------------------------------------------- dob
        // set values of dob

        // ----------------------------------------------------------------------------------------- father name
        // not null, not empty,  4 - 60 char
        father_name.addTextChangedListener(FormValidator.getNameWatcher(new FormValidator.MyTextWatcherCallback() {
            @Override
            public void onError(int error_code) {
                switch (error_code) {
                    case FormValidator.ERROR_CODE_IS_EMPTY:
                        tl_father_name.setError("Required");
                    case FormValidator.ERROR_CODE_OUT_OF_RANGE:
                        tl_father_name.setError("4 - 60 characters required");
                        break;
                    default:
                        tl_father_name.setError(null);
                }
            }

            @Override
            public void onSuccess(String s) {
                tl_father_name.setError(null);
                NewResumeActivity.BASIC_INFO.father = s;
            }
        }, 4, 60));

        // ----------------------------------------------------------------------------------------- email
        // not null, not empty, email pattern
        email.addTextChangedListener(FormValidator.getEmailWatcher(new FormValidator.MyTextWatcherCallback() {
            @Override
            public void onError(int error_code) {
                switch (error_code) {
                    case FormValidator.ERROR_CODE_IS_EMPTY:
                        tl_email.setError("Required");
                        break;
                    case FormValidator.ERROR_CODE_INVALID_EMAIL:
                        tl_email.setError("Invalid email address");
                        break;
                    default:
                        tl_email.setError(null);
                }
            }

            @Override
            public void onSuccess(String s) {
                tl_email.setError(null);
                NewResumeActivity.BASIC_INFO.email = s;
            }
        }));

        // ----------------------------------------------------------------------------------------- mobile
        // not null, not empty, number pattern
        mobile.addTextChangedListener(FormValidator.getMobileWatcher(new FormValidator.MyMobileTextWatcherCallback() {
            @Override
            public void onError(int error_code) {
                switch (error_code) {
                    case FormValidator.ERROR_CODE_IS_EMPTY:
                        tl_mobile.setError("Required");
                        break;
                    case FormValidator.ERROR_CODE_INVALID_NUMBER:
                        tl_mobile.setError("Invalid mobile number");
                        break;
                    case FormValidator.ERROR_CODE_OUT_OF_RANGE:
                        tl_mobile.setError("6 - 20 numbers required");
                        break;
                    default:
                        tl_mobile.setError(null);
                }
            }

            @Override
            public void onSuccess(String[] mobile_numbers) {
                tl_mobile.setError(null);
                NewResumeActivity.BASIC_INFO.mobile = mobile_numbers;
                //Log.e("ROOM", NewResumeActivity.BASIC_INFO.mobile);
            }
        }, 6, 20));


        // ----------------------------------------------------------------------------------------- address_line_1

        // not null, not empty,  4 - 60 char
        address_line_1.addTextChangedListener(FormValidator.getAddressWatcher(new FormValidator.AddressTextWatcher.AddressValidateCallback() {
            @Override
            public void onError(int error_code) {
                switch (error_code) {
                    case FormValidator.ERROR_CODE_IS_EMPTY:
                        tl_address_line_1.setError("Required");
                        break;
                    case FormValidator.ERROR_CODE_OUT_OF_RANGE:
                        tl_address_line_1.setError("4 - 60 characters required");
                        break;
                    default:
                        tl_address_line_1.setError(null);
                }
            }

            @Override
            public void onSuccess(String s) {
                tl_address_line_1.setError(null);
                NewResumeActivity.BASIC_INFO.setAddressLine1(s);
            }

        }, 4, 60));

        // ----------------------------------------------------------------------------------------- address_line_2
        // not null, not empty,  4 - 60 char
        address_line_2.addTextChangedListener(FormValidator.getNameWatcher(new FormValidator.MyTextWatcherCallback() {
            @Override
            public void onError(int error_code) {
                switch (error_code) {
                    case FormValidator.ERROR_CODE_OUT_OF_RANGE:
                        tl_address_line_2.setError("4 - 60 characters required");
                        break;
                    default:
                        tl_address_line_2.setError(null);
                }
            }

            @Override
            public void onSuccess(String s) {
                tl_address_line_2.setError(null);
                NewResumeActivity.BASIC_INFO.setAddressLine2(s);
            }
        }, 4, 60));

        // ----------------------------------------------------------------------------------------- city
        // not null, not empty,  2 - 60 char
        city.addTextChangedListener(FormValidator.getNameWatcher(new FormValidator.MyTextWatcherCallback() {
            @Override
            public void onError(int error_code) {
                switch (error_code) {
                    case FormValidator.ERROR_CODE_IS_EMPTY:
                        tl_city.setError("Required");
                        break;
                    case FormValidator.ERROR_CODE_OUT_OF_RANGE:
                        tl_city.setError("2 - 40 characters required");
                        break;
                    default:
                        tl_city.setError(null);
                }
            }

            @Override
            public void onSuccess(String s) {
                tl_city.setError(null);
                NewResumeActivity.BASIC_INFO.setCity(s);
            }
        }, 2, 40));

        // ----------------------------------------------------------------------------------------- state
        // not null, not empty,  2 - 60 char
        state.addTextChangedListener(FormValidator.getNameWatcher(new FormValidator.MyTextWatcherCallback() {
            @Override
            public void onError(int error_code) {
                switch (error_code) {
                    case FormValidator.ERROR_CODE_IS_EMPTY:
                        tl_state.setError("Required");
                        break;
                    case FormValidator.ERROR_CODE_OUT_OF_RANGE:
                        tl_state.setError("2 - 40 characters required");
                        break;
                    default:
                        tl_state.setError(null);
                }
            }

            @Override
            public void onSuccess(String s) {
                tl_state.setError(null);
                NewResumeActivity.BASIC_INFO.setState(s);
            }
        }, 2, 40));

        // ----------------------------------------------------------------------------------------- country
        // not null, not empty,  2 - 60 char
        country.addTextChangedListener(FormValidator.getNameWatcher(new FormValidator.MyTextWatcherCallback() {
            @Override
            public void onError(int error_code) {
                switch (error_code) {
                    case FormValidator.ERROR_CODE_IS_EMPTY:
                        tl_country.setError("Required");
                        break;
                    case FormValidator.ERROR_CODE_OUT_OF_RANGE:
                        tl_country.setError("2 - 40 characters required");
                        break;
                    default:
                        tl_country.setError(null);
                }
            }

            @Override
            public void onSuccess(String s) {
                tl_country.setError(null);
                NewResumeActivity.BASIC_INFO.setCountry(s);
            }
        }, 2, 40));

        // ----------------------------------------------------------------------------------------- pincode
        // not null, not empty,  4 - 60 char
        pincode.addTextChangedListener(FormValidator.getNumberWatcher(new FormValidator.MyTextWatcherCallback() {
            @Override
            public void onError(int error_code) {
                switch (error_code) {
                    case FormValidator.ERROR_CODE_IS_EMPTY:
                        tl_pincode.setError("Required");
                        break;
                    case FormValidator.ERROR_CODE_OUT_OF_RANGE:
                        tl_pincode.setError("4 - 20 numbers required");
                        break;
                    default:
                        tl_pincode.setError(null);
                }
            }

            @Override
            public void onSuccess(String s) {
                tl_pincode.setError(null);
                NewResumeActivity.BASIC_INFO.setPincode(s);
            }
        }, 4, 20));


        // ----------------------------------------------------------------------------------------- language
        //not null, not empty, char 1-40 language pattern
        language.addTextChangedListener(FormValidator.getNameWatcher(new FormValidator.MyTextWatcherCallback() {
            @Override
            public void onError(int error_code) {
                switch (error_code) {
                    case FormValidator.ERROR_CODE_OUT_OF_RANGE:
                        tl_language.setError("2 - 40 characters required");
                        break;
                    default:
                        tl_language.setError(null);
                }
            }

            @Override
            public void onSuccess(String s) {
                tl_language.setError(null);
                NewResumeActivity.BASIC_INFO.language = new String[]{s};
            }
        }, 2, 40));

        // ----------------------------------------------------------------------------------------- Nationality
        // not null, not empty,  4 - 60 char
        nationality.addTextChangedListener(FormValidator.getNameWatcher(new FormValidator.MyTextWatcherCallback() {
            @Override
            public void onError(int error_code) {
                switch (error_code) {
                    case FormValidator.ERROR_CODE_OUT_OF_RANGE:
                        tl_nationality.setError("2 - 40 characters required");
                        break;
                    default:
                        tl_nationality.setError(null);
                }
            }

            @Override
            public void onSuccess(String s) {
                tl_nationality.setError(null);
                NewResumeActivity.BASIC_INFO.nationality = s;
            }
        }, 2, 40));

        // ----------------------------------------------------------------------------------------- Passport
        //not null, not empty, Passport pattern
        passport.addTextChangedListener(FormValidator.getNameWatcher(new FormValidator.MyTextWatcherCallback() {
            @Override
            public void onError(int error_code) {
                switch (error_code) {
                    case FormValidator.ERROR_CODE_OUT_OF_RANGE:
                        tl_passport.setError("4 - 40 characters required");
                        break;
                    default:
                        tl_passport.setError(null);
                }
            }

            @Override
            public void onSuccess(String s) {
                tl_passport.setError(null);
                NewResumeActivity.BASIC_INFO.passport = s;
            }
        }, 4, 40));

        // ----------------------------------------------------------------------------------------- LinkedIn
        //not null, not empty, linkedIn pattern
        linkedIn.addTextChangedListener(FormValidator.getWebWatcher(new FormValidator.MyTextWatcherCallback() {
            @Override
            public void onError(int error_code) {

                switch (error_code) {
                    case FormValidator.ERROR_CODE_INVALID_WEBSITE:
                        tl_linkedin.setError("Invalid URL");
                        break;
                    default:
                        tl_linkedin.setError(null);
                }
            }

            @Override
            public void onSuccess(String s) {
                tl_linkedin.setError(null);
                NewResumeActivity.BASIC_INFO.linkedIn = s;
            }
        }));

        // ----------------------------------------------------------------------------------------- Website
        //not null, not empty, Website pattern
        website.addTextChangedListener(FormValidator.getWebWatcher(new FormValidator.MyTextWatcherCallback() {
            @Override
            public void onError(int error_code) {

                switch (error_code) {
                    case FormValidator.ERROR_CODE_INVALID_WEBSITE:
                        tl_website.setError("Invalid web URL");
                        break;
                    default:
                        tl_website.setError(null);
                }
            }

            @Override
            public void onSuccess(String s) {
                tl_website.setError(null);
                NewResumeActivity.BASIC_INFO.website = s;
            }
        }));


        // On Touch
        dob.setOnTouchListener(this);

        // Gender OnCheck
        rg_gender.setOnCheckedChangeListener(this);

        // Set Gender "Male" by default
        //Gender = (rg_gender.getCheckedRadioButtonId() == R.id.radio_male) ? BasicInfo.GENDER_MALE : BasicInfo.GENDER_FEMALE;

        // Marital Status OnCheck
        rg_marital_status.setOnCheckedChangeListener(this);

        // Set Status "Unmarried" by default
        //Marital_Status = rg_marital_status.getCheckedRadioButtonId() == R.id.radio_unmarried ? BasicInfo.MARITAL_STATUS_UNMARRIED : BasicInfo.MARITAL_STATUS_MARRIED;


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
//                if (!FormValidator.inLengthRange(s.toString(), 4, 60))
//                    tl_name.setError("Name is required");
//                else
//                    tl_name.setError(null);
//            }
//        };
//        this.name.addTextChangedListener(tw);

    }


    private void setValuesToInputText() {


        // set name
        if (NewResumeActivity.BASIC_INFO.getName() == null)
            NewResumeActivity.BASIC_INFO.name = "";
        name.setText(NewResumeActivity.BASIC_INFO.name);

        // set date of birth if model has null,  should not be empty, not below present date
        long d = 0;
        if (NewResumeActivity.BASIC_INFO.dob == null) {
            Calendar c = Calendar.getInstance();                        // current time
            c.set(1990, 1, 1);                         // set time to 1990-01-01
            d = c.getTimeInMillis();                                    // get long time
            NewResumeActivity.BASIC_INFO.dob = new Date(d);
        }
        dob.setHint(String.valueOf(d));                                                             // set long date
        dob.setText(DateFormat.format("d MMM yyyy", d).toString());                         // set formatted date
        tl_dob.setError(null);                                                                      // clear error

        // father's name
        father_name.setText(NewResumeActivity.BASIC_INFO.father);

        // set gender
        int gender_id = (NewResumeActivity.BASIC_INFO.gender == BasicInfo.GENDER_MALE) ? R.id.radio_male : R.id.radio_female;
        rg_gender.check(gender_id);  //set gender field

        // se marital status
        int marital_status_id = (NewResumeActivity.BASIC_INFO.marital == BasicInfo.MARITAL_STATUS_UNMARRIED) ? R.id.radio_unmarried : R.id.radio_married;
        rg_marital_status.check(marital_status_id);

        //email
        email.setText(NewResumeActivity.BASIC_INFO.email);

        //mobile
        mobile.setText(NewResumeActivity.BASIC_INFO.getMobile());

        //address
        //line 1
        address_line_1.setText(NewResumeActivity.BASIC_INFO.getAddressLine1());

        //line 2
        address_line_2.setText(NewResumeActivity.BASIC_INFO.getAddressLine2());

        //city
        city.setText(NewResumeActivity.BASIC_INFO.getCity());

        //state
        state.setText(NewResumeActivity.BASIC_INFO.getState());

        //country
        country.setText(NewResumeActivity.BASIC_INFO.getCountry());

        //pincode
        pincode.setText(NewResumeActivity.BASIC_INFO.getPincode());

        //language
        language.setText(NewResumeActivity.BASIC_INFO.getLanguage());

        //nationality
        nationality.setText(NewResumeActivity.BASIC_INFO.getNationality());

        //passport
        passport.setText(NewResumeActivity.BASIC_INFO.getPassport());

        //linkedIn
        linkedIn.setText(NewResumeActivity.BASIC_INFO.getLinkedIn());

        //website
        website.setText(NewResumeActivity.BASIC_INFO.getWebsite());

    }


    private void printBasicModelData() {
        Log.e("BASIC name", NewResumeActivity.BASIC_INFO.getName());
        Log.e("BASIC dob", NewResumeActivity.BASIC_INFO.getDob());
        Log.e("BASIC father", NewResumeActivity.BASIC_INFO.getFather());
        Log.e("BASIC gender", NewResumeActivity.BASIC_INFO.getGender());
        Log.e("BASIC marital", NewResumeActivity.BASIC_INFO.getMarital());

        Log.e("BASIC email", NewResumeActivity.BASIC_INFO.getEmail());
        Log.e("BASIC mobile", NewResumeActivity.BASIC_INFO.getMobile());

        Log.e("BASIC addr 1", NewResumeActivity.BASIC_INFO.getAddressLine1());
        Log.e("BASIC addr 2", NewResumeActivity.BASIC_INFO.getAddressLine2());
        Log.e("BASIC city", NewResumeActivity.BASIC_INFO.getCity());
        Log.e("BASIC state", NewResumeActivity.BASIC_INFO.getState());
        Log.e("BASIC country", NewResumeActivity.BASIC_INFO.getCountry());
        Log.e("BASIC pincode", NewResumeActivity.BASIC_INFO.getPincode());

        Log.e("BASIC lang", NewResumeActivity.BASIC_INFO.getLanguage());
        Log.e("BASIC nationality", NewResumeActivity.BASIC_INFO.getNationality());
        Log.e("BASIC passport", NewResumeActivity.BASIC_INFO.getPassport());

        Log.e("BASIC linkedIn", NewResumeActivity.BASIC_INFO.getLinkedIn());
        Log.e("BASIC website", NewResumeActivity.BASIC_INFO.getWebsite());
    }

    // VALIDATE FORM
    private boolean validateFormData() {

        // get form data
        /*String name, date, father_name, gender, marital_status, language, nationality, passport, linkedIn, website, email, mobile, address;

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
        address = this.address_line_1.getText().toString();*/


        // name, required
        //FormValidator.isEmpty(name);
        //FormValidator.inLengthRange(name, 1, 60);

        // dob, required
        //FormValidator.isDateValid(FormValidator.createDate(date));

        // father, required
        //FormValidator.isNull(father_name);
        //FormValidator.inLengthRange(father_name, 1, 60);

        // Gender, required
        //FormValidator.isGenderValid(gender);

        // marital status, required
        //FormValidator.isMaritalStatusValid(marital_status);

        // language, required

        // nationality

        // passport

        // linkedIn

        // website
        //FormValidator.isWebUrl(website);

        // email, required
        //FormValidator.isEmpty(email);
        //FormValidator.isEmailAddress(email);

        // mobile, required
        //FormValidator.isEmpty(mobile);
        //FormValidator.isMobileNumber(mobile);

        // address_line_1, required
        //FormValidator.isEmpty(address);
        //FormValidator.inLengthRange(address, 1, 255);

        return true;
    }

    // SAVE FORM
    private void saveFormData() {

        // 1. VALIDATE FORM DATA
        // 2. SET DATA

        NewResumeActivity.BASIC_INFO.name = name.getText().toString();                              // set name

        Date date = new Date(System.currentTimeMillis());
        NewResumeActivity.BASIC_INFO.dob = date;                                                    // set default date

        // parse date
        try {
            date = new Date(Long.parseLong(dob.getHint().toString()));
            NewResumeActivity.BASIC_INFO.dob = date;
        } catch (Exception e) {
            Log.e("Date", "Parsing Error");
        }

        NewResumeActivity.BASIC_INFO.father = father_name.getText().toString();                     // set father name
        String gender_val = (rg_gender.getCheckedRadioButtonId() == R.id.radio_male ? BasicInfo.GENDER_MALE : BasicInfo.GENDER_FEMALE);
        NewResumeActivity.BASIC_INFO.gender = gender_val;                                           // set gender

        String marital_val = (rg_marital_status.getCheckedRadioButtonId() == R.id.radio_married ? BasicInfo.MARITAL_STATUS_MARRIED : BasicInfo.MARITAL_STATUS_UNMARRIED);
        NewResumeActivity.BASIC_INFO.marital = marital_val;                                         // set marital status

        String lang = language.getText().toString();
        NewResumeActivity.BASIC_INFO.language = (lang == null ? null : lang.split(","));      // set language (split multiple keywords)

        NewResumeActivity.BASIC_INFO.nationality = nationality.getText().toString();                // set nationality
        NewResumeActivity.BASIC_INFO.passport = passport.getText().toString();                      // set passport number
        NewResumeActivity.BASIC_INFO.linkedIn = linkedIn.getText().toString();                      // set linkedIn id
        NewResumeActivity.BASIC_INFO.website = website.getText().toString();                        // set website address_line_1
        NewResumeActivity.BASIC_INFO.email = email.getText().toString();                            // set email id

        String mob = mobile.getText().toString();
        NewResumeActivity.BASIC_INFO.mobile = (mob == null ? null : mob.split(","));          // set mobile number (split multiple keywords)

        NewResumeActivity.BASIC_INFO.setAddressLine1(
                address_line_1.getText().toString().trim());                                        // get address_line_1

        NewResumeActivity.BASIC_INFO.setAddressLine2(
                address_line_2.getText().toString().trim());                                        // get address_line_2

        NewResumeActivity.BASIC_INFO.setCity(
                city.getText().toString().trim());                                                  // get city

        NewResumeActivity.BASIC_INFO.setState(
                state.getText().toString().trim());                                                 // get state

        NewResumeActivity.BASIC_INFO.setCountry(
                country.getText().toString().trim());                                               // get country

        NewResumeActivity.BASIC_INFO.setPincode(
                pincode.getText().toString().trim());                                               // get pincode

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
        address_line_1.getText().clear();

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
        tl_address_line_1.setError(null);
    }

    // SHOW DATEPICKER -----------------------------------------------------------------------------
    private void showDatePickerDialog() {

        // create and show dialog if not exists
        if (datePicker == null) {
            Calendar c = Calendar.getInstance();
            datePicker = new DatePickerDialog(getContext(), this, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
            datePicker.show();
        } else {
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
                    case R.id.radio_male:                                                           // set Gender MALE
                        NewResumeActivity.BASIC_INFO.gender = BasicInfo.GENDER_MALE;
                        break;
                    case R.id.radio_female:                                                         // set Gender FEMALE
                        NewResumeActivity.BASIC_INFO.gender = BasicInfo.GENDER_FEMALE;
                        break;
                }
                break;

            // If MARITAL STATUS
            case R.id.rg_marital_status:
                switch (checkedId) {
                    case R.id.radio_married:
                        NewResumeActivity.BASIC_INFO.marital = BasicInfo.MARITAL_STATUS_MARRIED;
                        break;
                    case R.id.radio_unmarried:
                        NewResumeActivity.BASIC_INFO.marital = BasicInfo.MARITAL_STATUS_UNMARRIED;
                        break;
                }
                break;
        }

    }


    // ON VIEW TOUCH -------------------------------------------------------------------------------
    @Override
    public boolean onTouch(View v, MotionEvent event) {

        switch (v.getId()) {
            case R.id.dob:
                if (event.getAction() == MotionEvent.ACTION_UP)
                    showDatePickerDialog();
                break;
        }

        return true;
    }

    // ON DATE SET ---------------------------------------------------------------------------------
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        // String y = String.format("%4d",year);
        // String m = String.format("%02d", (month + 1) );
        // String d = String.format("%02d",dayOfMonth);

        // user date
        Calendar userCalendar = Calendar.getInstance();
        userCalendar.set(year, month, dayOfMonth);

        // system current date
        Calendar currentCalendar = Calendar.getInstance();

        // android format date
        //DateFormat.format("EEE, d MMMM yyyy",)

        // user calendar is smaller than current time then its ok
        // else invalid date
        int compare_date = Long.compare(userCalendar.getTimeInMillis(), currentCalendar.getTimeInMillis());

        switch (compare_date) {
            // equals or smaller
            case 0:
            case -1:
                // set date
                dob.setText(DateFormat.format("d MMMM yyyy", userCalendar.getTimeInMillis()).toString());
                dob.setHint(String.valueOf(userCalendar.getTimeInMillis()));
                // clear error
                tl_dob.setError(null);
                // save data model
                NewResumeActivity.BASIC_INFO.dob.setTime(userCalendar.getTimeInMillis());
                break;

            // greater
            case 1:
                dob.setText(DateFormat.format("d MMMM yyyy", userCalendar.getTimeInMillis()).toString());
                dob.setHint(String.valueOf(userCalendar.getTimeInMillis()));
                tl_dob.setError("Date Not Valid");
                break;
        }


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
