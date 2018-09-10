package com.example.tabrezahmad.createresume.database.Entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import java.sql.Date;

@Entity
@ForeignKey(entity = BasicInfo.class,parentColumns = "uid",childColumns = "f_key",onDelete = ForeignKey.CASCADE,onUpdate = ForeignKey.CASCADE,deferred = false)
public class CareerObjective {

    @PrimaryKey(autoGenerate = true)
    public Integer uid;

    // uid of Basic_Info Entity (foreign key)
    @ColumnInfo(name = "f_key")
    public Long f_key;

    @ColumnInfo(name="objective")
    public String objective;

    @ColumnInfo(name="declaration")
    public String decleration;

    @ColumnInfo(name="date")
    public Date date;

    @ColumnInfo(name="place")
    public String place;


}
