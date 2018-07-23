package com.example.tabrezahmad.createresume.database.Models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.webkit.JavascriptInterface;

import java.sql.Date;

@Entity
public class ModelBasicInfo {

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

    @ColumnInfo(name = "email")
    public String email;

    @ColumnInfo(name = "mobile")
    public String[] mobile;


    public Long getUid() {
        return uid;
    }
    public String getName() {
        return name;
    }
    public String getDob() {
        return dob.toString();
    }
    public String getFather() {
        return father;
    }
    public String getGender() {
        return gender;
    }
    public String getEmail() {
        return email;
    }
    public String[] getMobile() {
        return mobile;
    }

}
