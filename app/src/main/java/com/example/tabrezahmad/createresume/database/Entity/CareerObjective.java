package com.example.tabrezahmad.createresume.database.Entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Relation;

import org.w3c.dom.Text;

import java.util.Date;

@Entity(foreignKeys = @ForeignKey(entity = AreaOfInterest.class,
        parentColumns = "uid",
        childColumns = "aoi_id"))
public class CareerObjective {

    @PrimaryKey(autoGenerate = true)
    private Integer uid;

    @ColumnInfo(name="objective")
    private Text objective;

    @ColumnInfo(name="decleration")
    private Text decleration;

    @ColumnInfo(name="date")
    private Date date;

    @ColumnInfo(name="place")
    private String place;

    @ColumnInfo(name="aoi_id")
    private int aoi_id;

}
