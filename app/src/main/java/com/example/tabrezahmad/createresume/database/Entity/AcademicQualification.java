package com.example.tabrezahmad.createresume.database.Entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverter;

import java.sql.Date;

@Entity
public class AcademicQualification {

    @PrimaryKey(autoGenerate = true)
    public Integer uid;

    @ColumnInfo(name = "course")
    public String course;

    @ColumnInfo(name = "university")
    public String university;

    @ColumnInfo(name = "pursuing")
    public char pursuing;

    @ColumnInfo(name = "date")
    public long date;

    @ColumnInfo(name = "marks")
    public double marks;

    //type include CPGA,PERCENTAGE
    @ColumnInfo(name = "marks_type")
    public String marks_type;

}
