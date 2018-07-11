package com.example.tabrezahmad.createresume.database.Entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverter;
import android.support.annotation.NonNull;

import java.sql.Date;

@Entity()
@ForeignKey(entity = BasicInfo.class,parentColumns = "uid",childColumns = "basic_id",onDelete = ForeignKey.CASCADE,onUpdate = ForeignKey.CASCADE,deferred = false)
public class AcademicQualification {

    @PrimaryKey(autoGenerate = true)
    public Long uid;

    // id of Basic_Info Entity
    @NonNull
    public Long basic_id;


    @NonNull
    @ColumnInfo(name = "course")
    public String course;

    @NonNull
    @ColumnInfo(name = "institute")
    public String institute;

    // not null status PASSED or PASSING
    @NonNull
    @ColumnInfo(name = "passing_status")
    public String passing_status;

    // not null if STATUS is PASSED
    @ColumnInfo(name = "year")
    public Date year;

    // not null if STATUS is PASSED
    @ColumnInfo(name = "marks")
    public Double marks;

    // not null if STATUS is PASSED, type CPGA/PERCENTAGE
    @ColumnInfo(name = "marks_type")
    public String marks_type;

}
