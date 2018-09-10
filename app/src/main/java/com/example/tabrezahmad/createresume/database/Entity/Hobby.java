package com.example.tabrezahmad.createresume.database.Entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import org.w3c.dom.Text;

@Entity
@ForeignKey(entity = BasicInfo.class,parentColumns = "uid",childColumns = "f_key",onDelete = ForeignKey.CASCADE,onUpdate = ForeignKey.CASCADE,deferred = false)
public class Hobby {

    @PrimaryKey(autoGenerate = true)
    public Integer uid;

    // uid of Basic_Info Entity (foreign key)
    @ColumnInfo(name = "f_key")
    public Long f_key;

    @ColumnInfo(name = "hobby")
    public String hobby;
}
