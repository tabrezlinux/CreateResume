package com.example.tabrezahmad.createresume.database.Entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Me on 06-Aug-18.
 */

@Entity
@ForeignKey(entity = BasicInfo.class,parentColumns = "uid",childColumns = "fk_basic_id",onDelete = ForeignKey.CASCADE,onUpdate = ForeignKey.CASCADE,deferred = false)
public class TemplateChoice {

    @PrimaryKey(autoGenerate = false)           // uid of basic_info
    public Long fk_basic_id;

    @ColumnInfo(name="fk_template_id")          // uid of template_table
    public Long fk_template_id;

}
