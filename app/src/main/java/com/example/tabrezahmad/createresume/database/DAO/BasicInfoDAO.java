package com.example.tabrezahmad.createresume.database.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.tabrezahmad.createresume.database.Entity.BasicInfo;

import java.util.List;

@Dao
public interface BasicInfoDAO {

    // select all user
    @Query("SELECT * FROM basicinfo")
    public List<BasicInfo> getAllBasicInfo();

    @Query("SELECT * FROM basicinfo WHERE uid IN (:id) LIMIT 1")
    public BasicInfo getBasicInfo(int id);

    @Query("SELECT * FROM basicinfo LIMIT 1")
    public BasicInfo getBasicInfo();

    // insert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(BasicInfo... basicInfos);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public Long insert(BasicInfo basicInfo);

    //@Insert(onConflict = OnConflictStrategy.REPLACE)
    //public List<Long> insert(BasicInfo... basicInfos);

    //update
    @Update
    public void update(BasicInfo... basicInfos);

    // delete user
    @Delete
    public void delete(BasicInfo... basicInfo);


}
