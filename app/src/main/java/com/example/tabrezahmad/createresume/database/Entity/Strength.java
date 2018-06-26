package com.example.tabrezahmad.createresume.database.Entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Strength {

    @PrimaryKey(autoGenerate = true)
    private Integer uid;

    @ColumnInfo(name="strength")
    private String strength;
}
