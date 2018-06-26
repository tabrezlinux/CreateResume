package com.example.tabrezahmad.createresume.database.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface CareerObjectiveDAO {

    // select all user
    @Query("SELECT * FROM workexperience")
    List<CareerObjectiveDAO> getAllWorkExperience();

    @Query("SELECT * FROM workexperience WHERE uid IN (:id) LIMIT 1")
    CareerObjectiveDAO getWorkExperience(int id);

    // insert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertWorkExperience(CareerObjectiveDAO... workExperiences);

    // delete user
    @Delete
    void delete(CareerObjectiveDAO workExperience);
}
