package com.example.tabrezahmad.createresume.database.Entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Reference {

    @PrimaryKey(autoGenerate = true)
    private Integer uid;
    private String name;
    private String email;
    private String mobile;

    private String designation;
    private String organization;
    private String employee_id;

}