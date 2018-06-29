package com.example.tabrezahmad.createresume;

import android.app.DatePickerDialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.CharacterStyle;
import android.text.style.DynamicDrawableSpan;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.tabrezahmad.createresume.database.Entity.BasicInfo;

import java.util.List;

public class BasicInfoFrag extends Fragment implements DatePickerDialog.OnDateSetListener {

    private EditText name, dob, father_name,language,nationality,passport,linkedIn,website,email,mobile, address;
    private RadioGroup rg_gender, rg_marital_status;
    private char gender, marital_status;

    private TextInputLayout tl_name, tl_father_name,tl_language, tl_nationality, tl_passport, tl_linkedin,tl_website,tl_email,tl_mobile,tl_address;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.basic_info, container, false);

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupViews();

    }


    private void setupViews() {
        name = getActivity().findViewById(R.id.name);
        dob = getActivity().findViewById(R.id.dob);
        father_name = getActivity().findViewById(R.id.father_name);

        language = getActivity().findViewById(R.id.language);
        nationality = getActivity().findViewById(R.id.nationality);
        passport = getActivity().findViewById(R.id.passport);
        linkedIn = getActivity().findViewById(R.id.linkedin);
        website = getActivity().findViewById(R.id.website);
        email = getActivity().findViewById(R.id.email);
        mobile = getActivity().findViewById(R.id.mobile);
        address = getActivity().findViewById(R.id.address);


        rg_gender = getActivity().findViewById(R.id.rg_gender);
        rg_marital_status = getActivity().findViewById(R.id.rg_marital_status);

        rg_gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radio_male:
                        gender = 'M';
                        //Toast.makeText(getContext(), "Male is checked",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.radio_female:
                        gender = 'F';
                        //Toast.makeText(getContext(), "Female is checked",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        rg_marital_status.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radio_married:
                        marital_status = 'M';
                        //Toast.makeText(getContext(), "Married is checked",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.radio_unmarried:
                        marital_status = 'U';
                        //Toast.makeText(getContext(), "Unmarried is checked",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        // set gender
        gender = rg_gender.getCheckedRadioButtonId() == R.id.radio_male ? 'M' : 'F';
        // set marital status
        marital_status = rg_marital_status.getCheckedRadioButtonId() == R.id.radio_unmarried ? 'U' : 'M';

        dob.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                DatePicker dp = DatePicker.getInstance(getContext(), BasicInfoFrag.this);
                dp.show();
            }
        });


        tl_name = getActivity().findViewById(R.id.tl_name);

        CharacterStyle cs = new CharacterStyle() {
            @Override
            public void updateDrawState(TextPaint tp) {
                tp.setColor(getResources().getColor(R.color.colorAccent));
                tp.bgColor = getResources().getColor(R.color.colorPrimaryDark);
            }
        };

        name.setText("Mytext");
        name.getEditableText().setSpan(cs,0,6, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);

    }


    @Override
    public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
        String date = (month + 1) + "-" + dayOfMonth + "-" + year;
        dob.setText(date);
    }


    @Override
    public void onPause() {
        Toast.makeText(getContext(), "Paused Frag", Toast.LENGTH_SHORT).show();
        saveFormData();
        super.onPause();
    }


    private void saveFormData() {

        final BasicInfo bi = new BasicInfo();
        bi.name = name.getText().toString();
        bi.date_of_birth = dob.getText().toString();
        bi.father_name = father_name.getText().toString();
        bi.gender = gender;
        bi.marital_status = marital_status;
        bi.Language = language.getText().toString();
        bi.nationality = nationality.getText().toString();
        bi.passport = passport.getText().toString();
        bi.linked_in = linkedIn.getText().toString();
        bi.website = website.getText().toString();
        bi.email = email.getText().toString();
        bi.mobile = mobile.getText().toString();
        bi.address = address.getText().toString();

        // validate form data


        // save model
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                MainActivity.mDatabase.BasicInfoDAO().insert(bi);
                final List<BasicInfo> bis = MainActivity.mDatabase.BasicInfoDAO().getAllBasicInfo();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String ids = "";
                        for (BasicInfo b : bis) {
                            ids += b.uid + " ";
                        }
                        Toast.makeText(getContext(), "record inserted ID=" + ids, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        t.start();

    }


}
