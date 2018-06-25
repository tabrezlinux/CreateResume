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
    private Integer id;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "description")
    private Text description;

    @ColumnInfo(name = "from_date")
    private Date from_date;

    @ColumnInfo(name = "to_date")
    private Date to_date;

    @ColumnInfo(name = "role")
    private String role;

    @ColumnInfo(name = "team_size")
    private Integer team_size;

    @ColumnInfo(name = "oraganizer")
    private String organizer;


}
