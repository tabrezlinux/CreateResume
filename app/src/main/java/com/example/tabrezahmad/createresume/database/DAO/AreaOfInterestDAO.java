package com.example.tabrezahmad.createresume.database.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.tabrezahmad.createresume.database.Entity.AreaOfInterest;
import com.example.tabrezahmad.createresume.database.Entity.Skill;
import com.example.tabrezahmad.createresume.database.Entity.Strength;

import java.util.List;

@Dao
public interface AreaOfInterestDAO {

    // find all
    @Query("SELECT * FROM areaofinterest")
    List<AreaOfInterest> getAllAreaOfInterest();

    // find single
    @Query("SELECT * FROM areaofinterest WHERE f_key == :id")
    AreaOfInterest getAreaOfInterest(Integer id);

    // find multiple
    @Query("SELECT * FROM areaofinterest WHERE f_key IN (:ids)")
    List<AreaOfInterest> getAreaOfInterest(Integer... ids);



    // insert multiple
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long[] insert(AreaOfInterest... areaOfInterests);

    // insert single
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insert(AreaOfInterest areaOfInterest);



    // update single
    @Update
    int update(AreaOfInterest areaOfInterest);

    // update multiple
    @Update
    int update(AreaOfInterest... areaOfInterests);



    // delete single
    @Delete
    int delete(AreaOfInterest areaOfInterest);

    // delete multiple
    @Delete
    int delete(AreaOfInterest... areaOfInterests);

    
}
