package com.example.tabrezahmad.createresume.database.Entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.sql.Date;

@Entity
public class AcademicQualification {

    @PrimaryKey(autoGenerate = true)
    private Integer id;

    @ColumnInfo(name = "course")
    private String course;

    @ColumnInfo(name = "university")
    private String university;

    @ColumnInfo(name = "pursuing")
    private char pursuing;

    @ColumnInfo(name = "date")
    private Date date;

    @ColumnInfo(name = "marks")
    private double marks;

    //type include CPGA,PERCENTAGE
    @ColumnInfo(name = "marks_type")
    private String marks_type;

}
