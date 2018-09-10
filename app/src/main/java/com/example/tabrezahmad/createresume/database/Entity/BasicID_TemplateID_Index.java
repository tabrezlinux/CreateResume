package com.example.tabrezahmad.createresume.database.Entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Me on 06-Aug-18.
 */

@Entity
public class BasicID_TemplateID_Index {

    @PrimaryKey(autoGenerate = false)
    public Long basic_id;                   // correspond to basic info uid


    public Long template_id;                // correspond to template table uid

}
