package com.example.tabrezahmad.createresume.database.Entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

@Entity
public class WorkExperience {

    @PrimaryKey(autoGenerate = true)
    private Integer id;

    @ColumnInfo(name = "company")
    private String company;

    @ColumnInfo(name = "designation")
    private String designation;

    @ColumnInfo(name = "role")
    private String role;

    @ColumnInfo(name = "working")
    private char working;

    @ColumnInfo(name = "from_date")
    private Date from_date;

    @ColumnInfo(name = "to_date")
    private Date to_date;

}
