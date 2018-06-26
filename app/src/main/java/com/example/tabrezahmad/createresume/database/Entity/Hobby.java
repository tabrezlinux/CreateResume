package com.example.tabrezahmad.createresume.database.Entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import org.w3c.dom.Text;

@Entity
public class Hobby {

    @PrimaryKey(autoGenerate = true)
    public Integer uid;

    @ColumnInfo(name = "hobby")
    public String hobby;
}
