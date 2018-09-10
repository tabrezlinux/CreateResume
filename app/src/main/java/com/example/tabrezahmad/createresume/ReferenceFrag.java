package com.example.tabrezahmad.createresume;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;

import com.android.ex.chips.RecipientEditTextView;
import com.android.ex.chips.recipientchip.DrawableRecipientChip;
import com.example.tabrezahmad.createresume.database.FormValidator;

public class ReferenceFrag extends Fragment {
    private EditText et_name,
            et_designation,
            et_organization,
            et_email,
            et_mobile;

    private TextInputLayout tl_name,
            tl_designation,
            tl_organization,
            tl_email,
            tl_mobile;

    private RecipientEditTextView mobile;

    View root;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.reference, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupViews();
    }

    private void setupViews() {

        //setup fields
        et_name = root.findViewById(R.id.et_name);
        et_designation = root.findViewById(R.id.et_designation);
        et_organization = root.findViewById(R.id.et_organization);
        et_email = root.findViewById(R.id.et_email);
        //et_mobile = root.findViewById(R.id.et_mobile);
        mobile = root.findViewById(R.id.et_mobile);

        //setup layouts
        tl_name = root.findViewById(R.id.tl_name);
        tl_designation = root.findViewById(R.id.tl_designation);
        tl_organization = root.findViewById(R.id.tl_organization);
        tl_email = root.findViewById(R.id.tl_email);
        tl_mobile = root.findViewById(R.id.tl_mobile);

        // ----------------------------------------------------------------------------------------- Name
        // not null, not empty,  4 - 60 char
        et_name.addTextChangedListener(FormValidator.getNameWatcher(new FormValidator.MyTextWatcherCallback() {
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
                NewResumeActivity.REFERENCE.name = s;
            }
        }, 4, 60));

        // ----------------------------------------------------------------------------------------- Designation
        // not null, not empty,  4 - 60 char
        et_designation.addTextChangedListener(FormValidator.getNameWatcher(new FormValidator.MyTextWatcherCallback() {
            @Override
            public void onError(int error_code) {
                switch (error_code) {
                    case FormValidator.ERROR_CODE_IS_EMPTY:
                        tl_designation.setError("Required");
                        break;
                    case FormValidator.ERROR_CODE_OUT_OF_RANGE:
                        tl_designation.setError("4 - 60 characters required");
                        break;
                    default:
                        tl_designation.setError(null);
                }
            }

            @Override
            public void onSuccess(String s) {
                tl_designation.setError(null);
                NewResumeActivity.REFERENCE.designation = s;
            }
        }, 4, 60));

        // ----------------------------------------------------------------------------------------- Organization
        // not null, not empty,  4 - 60 char
        et_organization.addTextChangedListener(FormValidator.getNameWatcher(new FormValidator.MyTextWatcherCallback() {
            @Override
            public void onError(int error_code) {
                switch (error_code) {
                    case FormValidator.ERROR_CODE_IS_EMPTY:
                        tl_organization.setError("Required");
                        break;
                    case FormValidator.ERROR_CODE_OUT_OF_RANGE:
                        tl_organization.setError("4 - 60 characters required");
                        break;
                    default:
                        tl_organization.setError(null);
                }
            }

            @Override
            public void onSuccess(String s) {
                tl_organization.setError(null);
                NewResumeActivity.REFERENCE.organization = s;
            }
        }, 4, 60));

        // ----------------------------------------------------------------------------------------- Email
        // not null, not empty,  4 - 60 char
        et_email.addTextChangedListener(FormValidator.getEmailWatcher(new FormValidator.MyTextWatcherCallback() {
            @Override
            public void onError(int error_code) {
                switch (error_code) {
                    case FormValidator.ERROR_CODE_IS_EMPTY:
                        tl_email.setError("Required");
                        break;
                    case FormValidator.ERROR_CODE_INVALID_EMAIL:
                        tl_email.setError("Invalid");
                        break;
                    default:
                        tl_email.setError(null);
                }
            }

            @Override
            public void onSuccess(String s) {
                tl_email.setError(null);
                NewResumeActivity.REFERENCE.email = s;
            }
        }));

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


        // ----------------------------------------------------------------------------------------- mobile
        // not null, not empty, number pattern
        et_mobile.addTextChangedListener(FormValidator.getMobileWatcher(new FormValidator.MyMobileTextWatcherCallback() {
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
                NewResumeActivity.REFERENCE.mobile = mobile_numbers;
                //Log.e("ROOM", NewResumeActivity.BASIC_INFO.mobile);
            }
        }, 6, 20));


    }
}
