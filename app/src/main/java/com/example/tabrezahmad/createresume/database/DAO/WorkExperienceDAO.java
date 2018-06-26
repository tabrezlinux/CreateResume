package com.example.tabrezahmad.createresume.database.DAO;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

public interface WorkExperienceDAO {

    // select all user
    @Query("SELECT * FROM workexperience")
    List<WorkExperienceDAO> getAllWorkExperience();

    @Query("SELECT * FROM workexperience WHERE uid IN (:id) LIMIT 1")
    WorkExperienceDAO getWorkExperience(int id);

    // insert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertWorkExperience(WorkExperienceDAO... workExperiences);

    // delete user
    @Delete
    void delete(WorkExperienceDAO workExperience);
}
