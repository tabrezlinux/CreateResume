package com.example.tabrezahmad.createresume.database.Entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import org.w3c.dom.Text;

@Entity
public class Activities {

    @PrimaryKey(autoGenerate = true)
    private Integer id;

    @ColumnInfo(name = "activities")
    private Text activities;
}
