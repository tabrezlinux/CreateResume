package com.example.tabrezahmad.createresume.database.Entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.webkit.JavascriptInterface;

import java.sql.Date;

@Entity
public class BasicInfo {

    public static final String GENDER_MALE = "m";
    public static final String GENDER_FEMALE = "f";
    public static final String MARITAL_STATUS_MARRIED = "mr";
    public static final String MARITAL_STATUS_UNMARRIED = "un";


    @PrimaryKey(autoGenerate = true)
    public Long uid;

    @ColumnInfo(name="name")
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
    public String[] Language;

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
    public String address;

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
    public String[] getLanguage() {
        return Language;
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
    public String[] getMobile() {
        return mobile;
    }

    @JavascriptInterface
    public String getAddress() {
        return address;
    }
}
