package com.example.tabrezahmad.createresume.database;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

public interface BasicInfoDAO {

    // select all user
    @Query("SELECT * FROM basicinfo")
    List<BasicInfo> getAllBasicInfo();

    @Query("SELECT * FROM basicinfo WHERE uid IN (:id) LIMIT 1")
    BasicInfo getBasicInfo(int id);

    // insert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertBasicInfo(BasicInfo... basicInfos);

    // delete user
    @Delete
    void delete(BasicInfo basicInfo);


}