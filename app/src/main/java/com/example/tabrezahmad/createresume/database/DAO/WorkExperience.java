package com.example.tabrezahmad.createresume.database.DAO;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.tabrezahmad.createresume.database.Entity.ProfessionalQualification;

import java.util.List;

public interface WorkExperience {

    // select all user
    @Query("SELECT * FROM workexperience")
    List<WorkExperience> getAllWorkExperience();

    @Query("SELECT * FROM workexperience WHERE id IN (:id) LIMIT 1")
    WorkExperience getWorkExperience(int id);

    // insert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertWorkExperience(WorkExperience... workExperiences);

    // delete user
    @Delete
    void delete(WorkExperience workExperience);
}
