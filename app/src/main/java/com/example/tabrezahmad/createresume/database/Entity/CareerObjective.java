package com.example.tabrezahmad.createresume.database.Entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import org.w3c.dom.Text;

import java.util.Date;

@Entity
public class CareerObjective {

    @PrimaryKey(autoGenerate = true)
    private Integer id;

    @ColumnInfo(name="objective")
    private Text objective;

    @ColumnInfo(name="decleration")
    private Text decleration;

    @ColumnInfo(name="date")
    private Date date;

    @ColumnInfo(name="place")
    private String place;

    @ColumnInfo(name="area_of_interest")
    private Text area_of_interest;

    @ColumnInfo(name="strength")
    private String strength;
}
