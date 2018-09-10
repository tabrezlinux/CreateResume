package com.example.tabrezahmad.createresume.database.Entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Me on 06-Aug-18.
 */

@Entity
public class Template {

    @PrimaryKey(autoGenerate = true)
    public Long uid;

    @ColumnInfo(name="file_name")       // file will be stored on directory path constant e.g PATH-CONSTANT/file.zip
    public String file_name;            // file name must include extension e.g temp_001.zip

}
