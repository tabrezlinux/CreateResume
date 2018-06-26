package com.example.tabrezahmad.createresume.database.Entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class TechnicalSkill {

    @PrimaryKey(autoGenerate = true)
    public Integer uid;

    @ColumnInfo(name = "technical_skill")
    public String technical_skill;
}
