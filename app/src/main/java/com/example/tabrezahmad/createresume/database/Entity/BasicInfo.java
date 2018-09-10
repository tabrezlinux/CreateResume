package com.example.tabrezahmad.createresume.database.Entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.util.Log;
import android.webkit.JavascriptInterface;

import java.sql.Date;
import java.util.Calendar;

@Entity
public class BasicInfo {

    public static final String GENDER_MALE = "m";
    public static final String GENDER_FEMALE = "f";
    public static final String MARITAL_STATUS_MARRIED = "mr";
    public static final String MARITAL_STATUS_UNMARRIED = "un";

    // constructor
    public BasicInfo(){

        dob = new Date(System.currentTimeMillis());
        father = "";
        gender = GENDER_MALE;
        marital = MARITAL_STATUS_UNMARRIED;
        nationality = "";
        language = new String[]{};
        passport = "";
        linkedIn = "";
        website = "";
        email = "";
        mobile = new String[]{};
        address = new String[6];
        address[0] = "";
        address[1] = "";
        address[2] = "";
        address[3] = "";
        address[4] = "";
        address[5] = "";

    }


    @PrimaryKey(autoGenerate = true)
    public Long uid;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "dob")
    public Date dob;

    @ColumnInfo(name = "father")
    public String father;

    // M = Male, F = Female
    @ColumnInfo(name = "gender")
    public String gender;

    // UN = unmarried, MR = Married
    @ColumnInfo(name = "marital_status")
    public String marital;

    @ColumnInfo(name = "nationality")
    public String nationality;

    @ColumnInfo(name = "language")
    public String[] language;

    @ColumnInfo(name = "passport")
    public String passport;

    @ColumnInfo(name = "linked_in")
    public String linkedIn;

    @ColumnInfo(name = "website")
    public String website;

    @ColumnInfo(name = "email")
    public String email;

    @ColumnInfo(name = "mobile")
    public String[] mobile;

    @ColumnInfo(name = "address")
    public String[] address;


    @JavascriptInterface
    public Long getUid() {
        return uid;
    }

    @JavascriptInterface
    public String getName() {
        return name;
    }

    @JavascriptInterface
    public String getDob() {
        return dob.toString();
    }

    @JavascriptInterface
    public String getFather() {
        return father;
    }

    @JavascriptInterface
    public String getGender() {
        return gender;
    }

    @JavascriptInterface
    public String getMarital() {
        return marital;
    }

    @JavascriptInterface
    public String getNationality() {
        return nationality;
    }

    @JavascriptInterface
    public String getLanguage() {

        StringBuilder sb = new StringBuilder();
        if (language != null && (language.length > 0))
            for (String lang : language) {
                sb.append(lang);
            }

        return sb.toString();
    }

    @JavascriptInterface
    public String getPassport() {
        return passport;
    }

    @JavascriptInterface
    public String getLinkedIn() {
        return linkedIn;
    }

    @JavascriptInterface
    public String getWebsite() {
        return website;
    }

    @JavascriptInterface
    public String getEmail() {
        return email;
    }

    @JavascriptInterface
    public String getMobile() {

        StringBuilder sb = new StringBuilder();
        if (mobile != null && (mobile.length > 0))
            for (String mob : mobile) {
                sb.append(mob);
            }

        return sb.toString();
    }

    @JavascriptInterface
    public String getAddressFull() {
        StringBuilder sb = new StringBuilder();
        for (String a : address) {
            sb.append(a + " ");
            return sb.toString();
        }
        return "";
    }

    @JavascriptInterface
    public String getAddressLine1() {
        return address[0];
    }

    public void setAddressLine1(@NonNull String addr) {
        address[0] = addr;
    }

    @JavascriptInterface
    public String getAddressLine2() {
        return address[1];
    }

    public void setAddressLine2(@NonNull String addr2) {
        address[1] = addr2;
    }

    @JavascriptInterface
    public String getCity() {
        return address[2];
    }

    public void setCity(@NonNull String city) {
        address[2] = city;
    }

    @JavascriptInterface
    public String getState() {
        return  address[3];
    }

    public void setState(@NonNull String state) {
        address[3] = state;
    }

    @JavascriptInterface
    public String getCountry() {
        return address[4];
    }

    public void setCountry(@NonNull String country) {
        address[4] = country;
    }

    @JavascriptInterface
    public String getPincode() {
        return address[5];
    }

    public void setPincode(@NonNull String pincode) {
        address[5] = pincode;
    }


}
