package com.example.tabrezahmad.createresume.database.Entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Relation;

import org.w3c.dom.Text;

import java.util.Date;

@Entity
public class CareerObjective {

    @PrimaryKey(autoGenerate = true)
    public Integer uid;

    @ColumnInfo(name="objective")
    public String objective;

    @ColumnInfo(name="declaration")
    public String decleration;

    @ColumnInfo(name="date")
    public long date;

    @ColumnInfo(name="place")
    public String place;

    @ColumnInfo(name="aoi_id")
    public int aoi_id;

}
