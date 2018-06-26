package com.example.tabrezahmad.createresume.database.Entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import org.w3c.dom.Text;

@Entity
public class ActivityCurricular {

    @PrimaryKey(autoGenerate = true)
    private Integer uid;

    @ColumnInfo(name = "activity")
    private Text activity;
}
