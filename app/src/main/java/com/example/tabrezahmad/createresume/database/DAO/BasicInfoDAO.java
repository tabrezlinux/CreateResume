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

    // find all
    @Query("SELECT * FROM basicinfo")
    List<BasicInfo> getAllBasicInfo();

    // find single
    @Query("SELECT * FROM basicinfo WHERE uid == :id")
    BasicInfo getBasicInfo(Integer id);

    // find multiple
    @Query("SELECT * FROM basicinfo WHERE uid IN (:ids)")
    List<BasicInfo> getBasicInfos(Integer... ids);



    // insert multiple
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long[] insert(BasicInfo... basicInfos);

    // insert single
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insert(BasicInfo basicInfo);

    //@Insert(onConflict = OnConflictStrategy.REPLACE)
    //List<Long> insert(BasicInfo... basicInfos);



    // update single
    @Update
    int update(BasicInfo basicInfos);

    // update multiple
    @Update
    int update(BasicInfo... basicInfos);



    // delete single
    @Delete
    int delete(BasicInfo basicInfos);

    // delete multiple
    @Delete
    int delete(BasicInfo... basicInfos);

}
