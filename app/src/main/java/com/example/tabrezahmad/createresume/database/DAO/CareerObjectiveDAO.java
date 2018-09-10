package com.example.tabrezahmad.createresume.database.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.tabrezahmad.createresume.database.Entity.BasicInfo;
import com.example.tabrezahmad.createresume.database.Entity.CareerObjective;

import java.util.List;

@Dao
public interface CareerObjectiveDAO {

    // find single by f_key
    @Query("SELECT * FROM careerobjective WHERE f_key == :id")
    CareerObjective getCareerObjective(Integer id);

    // insert single
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insert(CareerObjective careerObjective);

    // update single
    @Update
    int update(CareerObjective careerObjective);

    // delete single
    @Delete
    int delete(CareerObjective careerObjective);

}
