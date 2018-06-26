package com.example.tabrezahmad.createresume.database.Entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Internship {

    @PrimaryKey(autoGenerate = true)
    private Integer uid;

    @ColumnInfo(name = "industry")
    private String industry;

    @ColumnInfo(name = "job_role")
    private String job_role;

    @ColumnInfo(name = "duration")
    private String duration;

}
