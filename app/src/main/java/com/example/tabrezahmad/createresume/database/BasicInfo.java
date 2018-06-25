package com.example.tabrezahmad.createresume.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class BasicInfo {

    @PrimaryKey(autoGenerate = true)
    private Integer uid;

    @ColumnInfo(name="name")
    private String name;

    @ColumnInfo(name = "dob")
    private String date_of_birth;

    @ColumnInfo(name = "father")
    private String father_name;

    @ColumnInfo(name = "gender")
    private Character gender;

    @ColumnInfo(name = "marital_status")
    private Character marital_status;

    @ColumnInfo(name = "nationality")
    private String nationality;

    @ColumnInfo(name = "language")
    private String[] Language;

    @ColumnInfo(name = "passport")
    private String passport;

    @ColumnInfo(name = "linked_in")
    private String linked_in;

    @ColumnInfo(name = "website")
    private String website;

}
