package com.example.tabrezahmad.createresume.database.Entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class AreaOfInterest {

    @PrimaryKey(autoGenerate = true)
    private Integer uid;

    @ColumnInfo(name="area_of_interest")
    private String area_of_interest;
}
