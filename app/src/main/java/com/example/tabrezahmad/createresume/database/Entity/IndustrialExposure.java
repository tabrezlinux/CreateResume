package com.example.tabrezahmad.createresume.database.Entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.media.AudioRecord;

@Entity
public class IndustrialExposure {

    @PrimaryKey(autoGenerate = true)
    public Integer uid;

    @ColumnInfo(name = "industry")
    public String industry;

    @ColumnInfo(name = "job_role")
    public String job_role;

    @ColumnInfo(name = "duration")
    public String duration;
}
