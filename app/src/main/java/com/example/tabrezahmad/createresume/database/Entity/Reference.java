package com.example.tabrezahmad.createresume.database.Entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Reference {

    @PrimaryKey(autoGenerate = true)
    public Integer uid;
    public String name;
    public String email;
    public String mobile;

    public String designation;
    public String organization;
    public String employee_id;

}