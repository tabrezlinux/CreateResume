package com.example.tabrezahmad.createresume.database.Entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.webkit.JavascriptInterface;

import java.sql.Date;

@Entity
@ForeignKey(entity = BasicInfo.class,parentColumns = "uid",childColumns = "f_key",onDelete = ForeignKey.CASCADE,onUpdate = ForeignKey.CASCADE,deferred = false)
public class Internship {

    @PrimaryKey(autoGenerate = true)
    public Integer uid;

    // uid of Basic_Info Entity (foreign key)
    @ColumnInfo(name = "f_key")
    public Long f_key;

    @ColumnInfo(name = "company")
    public String company;

    @ColumnInfo(name = "role")
    public String role;

    @ColumnInfo(name = "from_date")
    public Date from_date;

    @ColumnInfo(name = "to_date")
    public Date to_date;

    @ColumnInfo(name="location")
    public String location;

    @ColumnInfo(name="index_id")
    public int index_id;

    @JavascriptInterface
    public Integer getUid() {
        return uid;
    }

    @JavascriptInterface
    public Long getF_key() {
        return f_key;
    }

    @JavascriptInterface
    public String getCompany() {
        return company;
    }

    @JavascriptInterface
    public String getRole() {
        return role;
    }

    @JavascriptInterface
    public Date getFrom_date() {
        return from_date;
    }

    @JavascriptInterface
    public Date getTo_date() {
        return to_date;
    }

    @JavascriptInterface
    public String getLocation() {
        return location;
    }

    @JavascriptInterface
    public int getIndex_id() {
        return index_id;
    }
}
