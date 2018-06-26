package com.example.tabrezahmad.createresume.database.Entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import org.w3c.dom.Text;

import java.util.List;

@Entity
public class Skill {

    @PrimaryKey(autoGenerate = true)
    public Integer uid;

    @ColumnInfo(name = "skills")
    public String skills;
}
