package com.example.tabrezahmad.createresume.database.Entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import org.w3c.dom.Text;

@Entity
public class Activity {

    @PrimaryKey(autoGenerate = true)
    private Integer id;

    @ColumnInfo(name = "curricular_activity")
    private Text curricular_activity;

    @ColumnInfo(name = "extra_activity")
    private Text extra_activity;
}
