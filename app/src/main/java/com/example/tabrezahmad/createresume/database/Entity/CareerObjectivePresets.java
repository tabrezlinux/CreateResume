package com.example.tabrezahmad.createresume.database.Entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.webkit.JavascriptInterface;

import java.sql.Date;

@Entity
public class CareerObjectivePresets {


    @PrimaryKey(autoGenerate = true)
    public Integer uid;

    @ColumnInfo(name="preset")
    public String preset;

    @ColumnInfo(name="brief")
    public String brief;

}
