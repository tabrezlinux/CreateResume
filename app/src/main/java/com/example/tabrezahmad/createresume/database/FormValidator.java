package com.example.tabrezahmad.createresume.database;

import android.widget.EditText;

import java.security.cert.TrustAnchor;
import java.sql.Date;

/**
 * Created by Me on 04-Jul-18.
 */

public class FormValidator {

    public static boolean isNull(Object object) {
        return  (object == null) ? true : false;
    }

    public static boolean isEmpty(String object) {
        return object.isEmpty();
    }

    public static boolean isEmpty(EditText object) {
        return object.getText().toString().isEmpty();
    }

    public static boolean isDateValid(Date object) {
        return (object == null) ? true : false;
    }

    // create date YYYY-MM-DD
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


    public static boolean isGenderValid(String object) {
        if (isNull(object))
            return false;

        return (object.contentEquals("M") || object.contentEquals("F")) ? true : false;
    }

    public static boolean isMaritalStatusValid(String object) {
        if (isNull(object))
            return false;

        return (object.contentEquals("MR") || object.contentEquals("UN")) ? true : false;
    }

    public static boolean isAlpha(String object) {
        if (isNull(object))
            return false;

        return true;
    }

    public static boolean isNumber(String object) {
        if (isNull(object))
            return false;

        return true;
    }

    public static boolean isEmailAddress(String object) {
        if (isNull(object))
            return false;

        return object.matches("[s+]@[s+][.]s+");
    }

    public static boolean isWebUrl(String object) {
        if (isNull(object))
            return false;

        return true;
    }

    public static boolean isMobileNumber(String object) {
        if (isNull(object))
            return false;

        String regex = "d+";
        return object.matches(regex);
    }

    public static boolean isLengthEqual(String object, int size) {
        if (isNull(object))
            return false;

        return (object.length() == size) ? true : false;

    }

    public static boolean isLengthEqual(EditText object, int size) {
        if (isNull(object))
            return false;

        return (object.getText().toString().length() == size) ? true : false;

    }

    public static boolean isLengthAtleast(String object, int size) {
        if (isNull(object))
            return false;

        return (object.length() >= size) ? true : false;

    }

    public static boolean isLengthAtMax(String object, int size) {
        if (isNull(object))
            return false;

        return (object.length() <= size) ? true : false;

    }

    public static boolean isLengthRange(String object, int min_size, int max_size) {
        if (isNull(object))
            return false;

        return ((object.length() <= max_size) && (object.length() >= min_size)) ? true : false;

    }

    public static boolean isLengthRange(EditText object, int min_size, int max_size) {
        if (isNull(object))
            return false;

        return ((object.getText().toString().length() <= max_size) && (object.length() >= min_size)) ? true : false;

    }


}
