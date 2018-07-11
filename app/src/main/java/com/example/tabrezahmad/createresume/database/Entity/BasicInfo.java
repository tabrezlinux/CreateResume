package com.example.tabrezahmad.createresume.database.Entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.TypeConverters;
import android.arch.persistence.room.Update;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;

import com.example.tabrezahmad.createresume.database.Converters.BasicInfoCoverters;
import com.example.tabrezahmad.createresume.database.Converters.Converters;

import java.sql.Date;
import java.util.List;

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

}
