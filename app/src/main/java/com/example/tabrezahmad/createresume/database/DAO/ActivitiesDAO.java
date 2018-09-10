package com.example.tabrezahmad.createresume.database.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.tabrezahmad.createresume.database.Entity.AchievementsAndAwards;
import com.example.tabrezahmad.createresume.database.Entity.Activities;

import java.util.List;

@Dao
public interface ActivitiesDAO {

    // find all
    @Query("SELECT * FROM activities")
    List<Activities> getAllActivity();

    // find single
    @Query("SELECT * FROM activities WHERE f_key == :id")
    Activities getActivity(Integer id);

    // find multiple
    @Query("SELECT * FROM activities WHERE f_key IN (:ids)")
    List<Activities> getActivities(Integer... ids);



    // insert multiple
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long[] insert(Activities... activities);

    // insert single
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insert(Activities activities);



    // update single
    @Update
    int update(Activities activities);

    // update multiple
    @Update
    int update(Activities... activities);



    // delete single
    @Delete
    int delete(Activities activities);

    // delete multiple
    @Delete
    int delete(Activities... activities);

    
}
