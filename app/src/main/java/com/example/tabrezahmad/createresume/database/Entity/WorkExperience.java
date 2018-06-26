package com.example.tabrezahmad.createresume.database.Entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

@Entity
public class WorkExperience {

    @PrimaryKey(autoGenerate = true)
    public Integer uid;

    @ColumnInfo(name = "company")
    public String company;

    @ColumnInfo(name = "designation")
    public String designation;

    @ColumnInfo(name = "role")
    public String role;

    @ColumnInfo(name = "working")
    public boolean working;    // true/false

    @ColumnInfo(name = "from_date")
    public long from_date;

    @ColumnInfo(name = "to_date")
    public long to_date;

}
