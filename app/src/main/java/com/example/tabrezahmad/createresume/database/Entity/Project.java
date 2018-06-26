package com.example.tabrezahmad.createresume.database.Entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.design.widget.NavigationView;
import android.support.v4.media.VolumeProviderCompat;

import org.w3c.dom.Text;

import java.util.Date;

@Entity
public class Project {

    @PrimaryKey(autoGenerate = true)
    public Integer uid;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "description")
    public String description;

    @ColumnInfo(name = "from_date")
    public long from_date;

    @ColumnInfo(name = "to_date")
    public long to_date;

    @ColumnInfo(name = "role")
    public String role;

    @ColumnInfo(name = "team_size")
    public Integer team_size;

    @ColumnInfo(name = "oraganizer")
    public String organizer;


}
