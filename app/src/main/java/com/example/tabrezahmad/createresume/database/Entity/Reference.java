package com.example.tabrezahmad.createresume.database.Entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Reference {

    @PrimaryKey(autoGenerate = true)
    private Integer id;
}
