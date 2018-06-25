package com.example.tabrezahmad.createresume.database.Entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import org.w3c.dom.Text;

import java.lang.reflect.Type;

@Entity
public class Contact {

    @PrimaryKey(autoGenerate = true)
    private Integer uid;

    @ColumnInfo(name = "contact")
    private Text contact;

    // type = ADD,EMIAL,MOBILE
    @ColumnInfo(name = "type")
    private String type;

}
