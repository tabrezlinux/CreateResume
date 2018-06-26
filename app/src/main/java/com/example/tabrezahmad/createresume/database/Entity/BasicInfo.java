package com.example.tabrezahmad.createresume.database.Entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class BasicInfo {

    @PrimaryKey(autoGenerate = true)
    public Integer uid;

    @ColumnInfo(name="name")
    public String name;

    @ColumnInfo(name = "dob")
    public String date_of_birth;

    @ColumnInfo(name = "father")
    public String father_name;

    @ColumnInfo(name = "gender")
    public Character gender;

    @ColumnInfo(name = "marital_status")
    public Character marital_status;

    @ColumnInfo(name = "nationality")
    public String nationality;

    @ColumnInfo(name = "language")
    public String Language;

    @ColumnInfo(name = "passport")
    public String passport;

    @ColumnInfo(name = "linked_in")
    public String linked_in;

    @ColumnInfo(name = "website")
    public String website;

    @ColumnInfo(name = "email")
    public String email;

    @ColumnInfo(name = "mobile")
    public String mobile;

    @ColumnInfo(name = "address")
    public String address;

}
