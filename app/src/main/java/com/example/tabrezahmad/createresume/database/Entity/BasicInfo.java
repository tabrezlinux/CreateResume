package com.example.tabrezahmad.createresume.database.Entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.webkit.JavascriptInterface;

import java.sql.Date;

@Entity
public class BasicInfo {

    @PrimaryKey(autoGenerate = true)
    public Long uid;

    @NonNull
    @ColumnInfo(name="name")
    public String name;

    @NonNull
    @ColumnInfo(name = "dob")
    public Date date_of_birth;

    @NonNull
    @ColumnInfo(name = "father")
    public String father_name;

    // M = Male, F = Female
    @NonNull
    @ColumnInfo(name = "gender")
    public String gender;

    // UN = unmarried, MR = Married
    @NonNull
    @ColumnInfo(name = "marital_status")
    public String marital_status;

    @ColumnInfo(name = "nationality")
    public String nationality;

    @ColumnInfo(name = "language")
    public String[] Language;

    @ColumnInfo(name = "passport")
    public String passport;

    @ColumnInfo(name = "linked_in")
    public String linked_in;

    @ColumnInfo(name = "website")
    public String website;

    @NonNull
    @ColumnInfo(name = "email")
    public String email;

    @NonNull
    @ColumnInfo(name = "mobile")
    public String[] mobile;

    @NonNull
    @ColumnInfo(name = "address")
    public String address;


    @JavascriptInterface
    public Long getUid() {
        return uid;
    }

    @JavascriptInterface
    @NonNull
    public String getName() {
        return name;
    }

    @JavascriptInterface
    @NonNull
    public Date getDate_of_birth() {
        return date_of_birth;
    }

    @JavascriptInterface
    @NonNull
    public String getFather_name() {
        return father_name;
    }

    @JavascriptInterface
    @NonNull
    public String getGender() {
        return gender;
    }

    @JavascriptInterface
    @NonNull
    public String getMarital_status() {
        return marital_status;
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
    public String getLinked_in() {
        return linked_in;
    }

    @JavascriptInterface
    public String getWebsite() {
        return website;
    }

    @JavascriptInterface
    @NonNull
    public String getEmail() {
        return email;
    }

    @JavascriptInterface
    @NonNull
    public String[] getMobile() {
        return mobile;
    }

    @JavascriptInterface
    @NonNull
    public String getAddress() {
        return address;
    }
}
