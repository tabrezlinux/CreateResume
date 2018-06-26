package com.example.tabrezahmad.createresume.database.Entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

@Entity
public class ProfessionalQualification {

    @PrimaryKey(autoGenerate = true)
    public Integer uid;

    @ColumnInfo(name = "course")
    public String course;

    @ColumnInfo(name = "institution")
    public String institution;

    @ColumnInfo(name = "starting_date")
    public long starting_date;

    @ColumnInfo(name = "closing_date")
    public long closing_date;
}
