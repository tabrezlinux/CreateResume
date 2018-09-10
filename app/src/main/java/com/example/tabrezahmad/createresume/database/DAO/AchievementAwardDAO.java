package com.example.tabrezahmad.createresume.database.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.tabrezahmad.createresume.database.Entity.AchievementsAndAwards;
import com.example.tabrezahmad.createresume.database.Entity.BasicInfo;

import java.util.List;

@Dao
public interface AchievementAwardDAO {

    // find all
    @Query("SELECT * FROM achievementsandawards")
    List<AchievementsAndAwards> getAllAchvivement();

    // find single
    @Query("SELECT * FROM achievementsandawards WHERE f_key == :id")
    AchievementsAndAwards getAchievement(Integer id);

    // find multiple
    @Query("SELECT * FROM achievementsandawards WHERE f_key IN (:ids)")
    List<AchievementsAndAwards> getAchievements(Integer... ids);



    // insert multiple
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long[] insert(AchievementsAndAwards... achievementsAndAwards);

    // insert single
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insert(AchievementsAndAwards achievementsAndAwards);



    // update single
    @Update
    int update(AchievementsAndAwards achievementsAndAwards);

    // update multiple
    @Update
    int update(AchievementsAndAwards... achievementsAndAwards);



    // delete single
    @Delete
    int delete(AchievementsAndAwards achievementsAndAwards);

    // delete multiple
    @Delete
    int delete(AchievementsAndAwards... achievementsAndAwards);


}
