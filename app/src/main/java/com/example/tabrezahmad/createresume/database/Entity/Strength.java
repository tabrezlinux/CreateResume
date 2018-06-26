package com.example.tabrezahmad.createresume.database.Entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Strength {

    @PrimaryKey(autoGenerate = true)
    public Integer uid;

    @ColumnInfo(name="strength")
    public String strength;
}
