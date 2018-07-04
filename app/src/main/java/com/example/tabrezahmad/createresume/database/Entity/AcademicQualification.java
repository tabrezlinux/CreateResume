package com.example.tabrezahmad.createresume.database.Entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverter;
import android.support.annotation.NonNull;

import java.sql.Date;

@Entity
public class AcademicQualification {

    @PrimaryKey(autoGenerate = true)
    public Integer uid;

    @NonNull
    @ColumnInfo(name = "course")
    public String course;

    @NonNull
    @ColumnInfo(name = "institute")
    public String institute;

    @NonNull
    @ColumnInfo(name = "pursuing")
    public String pursuing;

    @NonNull
    @ColumnInfo(name = "year")
    public long year;

    @NonNull
    @ColumnInfo(name = "marks")
    public double marks;

    //type include CPGA,PERCENTAGE
    @NonNull
    @ColumnInfo(name = "marks_type")
    public String marks_type;

}
