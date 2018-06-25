package com.example.tabrezahmad.createresume.database.Entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

@Entity
public class ProfessionalQualification {

    @PrimaryKey(autoGenerate = true)
    private Integer id;

    @ColumnInfo(name = "course")
    private String course;

    @ColumnInfo(name = "institution")
    private String institution;

    @ColumnInfo(name = "starting_date")
    private Date starting_date;

    @ColumnInfo(name = "closing_date")
    private Date closing_date;
}
