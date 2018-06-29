package com.example.tabrezahmad.createresume.database.Entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

@Entity
public class BasicInfo {

    @PrimaryKey(autoGenerate = true)
    public Integer uid;

    @NonNull
    @ColumnInfo(name="name")
    public String name;

    @NonNull
    @ColumnInfo(name = "dob")
    public String date_of_birth;

    @ColumnInfo(name = "father")
    public String father_name;

    // M = Male, F = Female
    @NonNull
    @ColumnInfo(name = "gender")
    public char gender;

    // U = unmarried, M = Married
    @NonNull
    @ColumnInfo(name = "marital_status")
    public char marital_status;

    @ColumnInfo(name = "nationality")
    public String nationality;

    @ColumnInfo(name = "language")
    @NonNull
    public String Language;

    @ColumnInfo(name = "passport")
    public String passport;

    @ColumnInfo(name = "linked_in")
    public String linked_in;

    @ColumnInfo(name = "website")
    public String website;

    @ColumnInfo(name = "email")
    @NonNull
    public String email;

    @ColumnInfo(name = "mobile")
    @NonNull
    public String mobile;

    @ColumnInfo(name = "address")
    @NonNull
    public String address;

}
