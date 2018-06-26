package com.example.tabrezahmad.createresume.database.Entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class TechnicalSkill {

    @PrimaryKey(autoGenerate = true)
    private Integer uid;

    @ColumnInfo(name = "technical_skill")
    private String technical_skill;
}
