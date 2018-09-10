package com.example.tabrezahmad.createresume.database;

import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;

import java.sql.Date;

/**
 * Created by Me on 04-Jul-18.
 */

public class FormValidator {


    // null check
    public static boolean isNull(Object object) {
        return (object == null) ? true : false;
    }

    // empty check
    public static boolean isEmpty(String s) {
        return s.trim().isEmpty();
    }

    // data check
    public static boolean isDateValid(Date object) {
        return (object == null) ? true : false;
    }

    // create date YYYY-MM-DD
    @Nullable
    public static Date createDate(String object) {

        if (isNull(object))
            return null;

        Date date = null;

        try {
            date = Date.valueOf(object);
        } catch (Exception e) {
            return null;
        }

        return date;

    }

    // create date YYYY-MM-DD
    public static Date createDate(Long val) {

        if (isNull(val))
            return null;

        Date date = null;

        try {
            date = new Date(val);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return date;
    }


    // gender check
    public static boolean isGenderValid(String object) {
        if (isNull(object))
            return false;

        return (object.contentEquals("M") || object.contentEquals("F")) ? true : false;
    }

    // is marital valid
    public static boolean isMaritalStatusValid(String object) {
        if (isNull(object))
            return false;

        return (object.contentEquals("MR") || object.contentEquals("UN")) ? true : false;
    }


    public static boolean isAlpha(String s) {
        if (isNull(s))
            return false;

        String regex = "[a-z A-Z]+"; // www.a.a
        return s.matches(regex);
    }

    public static boolean isNumber(String object) {
        if (isNull(object))
            return false;

        String regex = "\\d+";
        return object.matches(regex);
    }

    public static boolean isEmailAddress(String object) {
        if (isNull(object))
            return false;

        String regex = "\\w.+@\\p{Alpha}+\\.\\p{Alpha}+"; // someone@gmail.com
        return object.matches(regex);
    }

    public static boolean isWebUrl(String s) {
        if (isNull(s))
            return false;

        return s.matches("(WWW|www\\.+\\p{Alnum}+\\.\\p{Alpha}+)|(WWW|www\\.+\\p{Alnum}+\\.\\p{Alpha}+\\.\\p{Alpha}+)");
    }

    public static boolean isMobileNumber(String object) {
        if (isNull(object))
            return false;

        String regex = "\\d+";
        return object.matches(regex);
    }


    public static boolean isLengthEqual(String object, int size) {
        if (isNull(object))
            return false;

        return (object.trim().length() == size) ? true : false;

    }

    public static boolean isSizeSmaller(String s, int size) {
        if (isNull(s))
            return false;

        return (s.trim().length() < size) ? true : false;

    }

    public static boolean isSizeGreater(String s, int size) {
        if (isNull(s))
            return false;

        return (s.trim().length() > size) ? true : false;

    }

    public static boolean inLengthRange(String s, int min_size, int max_size) {
        if (isNull(s))
            return false;

        return ((s.trim().length() <= max_size) && (s.trim().length() >= min_size)) ? true : false;

    }


    // validation error code constant
    public static final int ERROR_CODE_RANGE_LESS = 0;
    public static final int ERROR_CODE_RANGE_GREATER = 1;
    public static final int ERROR_CODE_IS_EMPTY = 2;
    public static final int ERROR_CODE_INVALID_GENDER = 3;
    public static final int ERROR_CODE_INVALID_STATUS = 4;
    public static final int ERROR_CODE_INVALID_DATE = 5;
    public static final int ERROR_CODE_INVALID_NUMBER = 6;
    public static final int ERROR_CODE_INVALID_EMAIL = 7;
    public static final int ERROR_CODE_INVALID_WEBSITE = 8;
    public static final int ERROR_CODE_OUT_OF_RANGE = 9;

    // get name watcher
    public static NameTextWatcher getNameWatcher(MyTextWatcherCallback callback, int char_min, int char_max) {
        return new NameTextWatcher(callback, char_min, char_max);
    }

    // get dob watcher
    /*public static DobTextWatcher getDobWatcher(MyTextWatcherCallback callback, long min_datetime, long max_datetime){
        return new DobTextWatcher(callback,min_datetime,max_datetime);
    }*/

    // get email watcher
    public static EmailTextWatcher getEmailWatcher(MyTextWatcherCallback callback) {
        return new EmailTextWatcher(callback);
    }

    // get web watcher
    public static WebTextWatcher getWebWatcher(MyTextWatcherCallback callback) {
        return new WebTextWatcher(callback);
    }

    // get number watcher
    public static NumberTextWatcher getNumberWatcher(MyTextWatcherCallback callback, int char_min, int char_max) {
        return new NumberTextWatcher(callback, char_min, char_max);
    }

    // get mobile watcher
    public static MobileTextWatcher getMobileWatcher(MyMobileTextWatcherCallback callback, int char_min, int char_max) {
        return new MobileTextWatcher(callback, char_min, char_max);
    }

    // get address watcher
    public static AddressTextWatcher getAddressWatcher(AddressTextWatcher.AddressValidateCallback addressValidateCallback, int char_min, int char_max) {
        return new AddressTextWatcher(addressValidateCallback, char_min, char_max);
    }


    // interface
    public interface MyTextWatcherCallback {
        void onError(int error_code);

        void onSuccess(String s);
    }


    // NAME WATCHER
    private static class NameTextWatcher implements TextWatcher {

        MyTextWatcherCallback callback;

        int char_min, char_max;

        private NameTextWatcher() {
        }

        public NameTextWatcher(MyTextWatcherCallback callback, int char_min, int char_max) {
            this.callback = callback;
            this.char_min = char_min;
            this.char_max = char_max;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            boolean is_empty = FormValidator.isEmpty(s.toString());
            boolean in_range = FormValidator.inLengthRange(s.toString(), char_min, char_max);

            if (!is_empty) {
                if (!in_range) {
                    callback.onError(ERROR_CODE_OUT_OF_RANGE);
                    return;
                }

                callback.onSuccess(s.toString());

            } else {
                callback.onError(ERROR_CODE_IS_EMPTY);
            }

        }
    }

    // EMAIL WATCHER
    private static class EmailTextWatcher implements TextWatcher {

        MyTextWatcherCallback callback;


        private EmailTextWatcher() {
        }

        public EmailTextWatcher(MyTextWatcherCallback callback) {
            this.callback = callback;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            boolean is_empty = FormValidator.isEmpty(s.toString());
            boolean is_valid_email = FormValidator.isEmailAddress(s.toString());
            if (!is_empty) {
                if (!is_valid_email) {
                    callback.onError(ERROR_CODE_INVALID_EMAIL);
                    return;
                }

                callback.onSuccess(s.toString());

            } else {
                callback.onError(ERROR_CODE_IS_EMPTY);
            }

        }
    }

    // WEB WATCHER
    private static class WebTextWatcher implements TextWatcher {

        MyTextWatcherCallback callback;


        private WebTextWatcher() {
        }

        public WebTextWatcher(MyTextWatcherCallback callback) {
            this.callback = callback;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            boolean is_empty = FormValidator.isEmpty(s.toString());
            boolean is_valid_web = FormValidator.isWebUrl(s.toString());
            if (!is_empty) {
                if (!is_valid_web) {
                    callback.onError(ERROR_CODE_INVALID_WEBSITE);
                    return;
                }

                callback.onSuccess(s.toString());

            } else {
                callback.onError(ERROR_CODE_IS_EMPTY);
            }

        }
    }

    // NUMBER WATCHER0
    private static class NumberTextWatcher implements TextWatcher {

        MyTextWatcherCallback callback;

        int char_min, char_max;


        private NumberTextWatcher() {
        }

        public NumberTextWatcher(MyTextWatcherCallback callback, int char_min, int char_max) {
            this.callback = callback;
            this.char_min = char_min;
            this.char_max = char_max;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            boolean is_empty = FormValidator.isEmpty(s.toString());
            boolean in_range = FormValidator.inLengthRange(s.toString(), char_min, char_max);

            if (is_empty) {
                callback.onError(ERROR_CODE_IS_EMPTY);
                return;
            }
            if (!in_range) {
                callback.onError(ERROR_CODE_OUT_OF_RANGE);
                return;
            }

            callback.onSuccess(s.toString());


        }
    }

    // interface
    public interface MyMobileTextWatcherCallback {
        void onError(int error_code);
        void onSuccess(String[] mobile_numbers);
    }

    // MOBILE WATCHER
    private static class MobileTextWatcher implements TextWatcher {

        MyMobileTextWatcherCallback callback;
        int char_min, char_max;


        private MobileTextWatcher() {
        }

        public MobileTextWatcher(MyMobileTextWatcherCallback callback, int char_min, int char_max) {
            this.callback = callback;
            this.char_min = char_min;
            this.char_max = char_max;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            boolean is_empty = FormValidator.isEmpty(s.toString().trim());
            //boolean in_range = FormValidator.inLengthRange(s.toString().trim(), char_min, char_max);
            //boolean is_mobile_number = FormValidator.isMobileNumber(s.toString().trim());

            // if empty string
            if (is_empty) {
                callback.onError(ERROR_CODE_IS_EMPTY);
                return;
            }

            // if string has more than one mobile, separated by comma
            // then split and process each
            String[] string_array = s.toString().trim().split(",");

            // for each mobile
            for (String mob : string_array) {

                boolean in_range = FormValidator.inLengthRange(mob.trim(), char_min, char_max);
                boolean is_mobile_number = FormValidator.isMobileNumber(mob.trim());

                if (!is_mobile_number) {
                    callback.onError(ERROR_CODE_INVALID_NUMBER);
                    return;
                }

                if (!in_range) {
                    callback.onError(ERROR_CODE_OUT_OF_RANGE);
                    return;
                }
            }

            callback.onSuccess(string_array);


        }
    }


    // ADDRESS WATCHER CALLBACKS -------------------------------------------------------------------

    // ADDRESS WATCHER
    public static class AddressTextWatcher implements TextWatcher {

        // Address interface text callback
        public interface AddressValidateCallback {
            void onError(int error_code);

            void onSuccess(String s);
        }

        AddressValidateCallback addressValidateCallback;

        int char_min, char_max;

        private AddressTextWatcher() {
        }

        public AddressTextWatcher(AddressValidateCallback textChangeAfterCallback, int char_min, int char_max) {
            this.addressValidateCallback = textChangeAfterCallback;
            this.char_min = char_min;
            this.char_max = char_max;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {

            boolean is_empty = FormValidator.isEmpty(s.toString());
            boolean in_range = FormValidator.inLengthRange(s.toString(), char_min, char_max);


            if (is_empty) {
                addressValidateCallback.onError(ERROR_CODE_IS_EMPTY);
                return;
            }

            if (!in_range) {
                addressValidateCallback.onError(ERROR_CODE_OUT_OF_RANGE);
                return;
            }

            addressValidateCallback.onSuccess(s.toString());

        }
    }


}
