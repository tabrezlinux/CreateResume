package com.example.tabrezahmad.createresume.database.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.tabrezahmad.createresume.database.Entity.BasicInfo;
import com.example.tabrezahmad.createresume.database.Entity.WorkExperience;

import java.util.List;

@Dao
public interface WorkExperienceDAO {

    // find all
    @Query("SELECT * FROM workexperience")
    List<WorkExperience> getAllWorkExperience();

    // find single
    @Query("SELECT * FROM workexperience WHERE f_key == :id")
    WorkExperience getWorkExperience(Integer id);

    // find multiple
    @Query("SELECT * FROM workexperience WHERE f_key IN (:ids)")
    List<WorkExperience> getWorkExperiences(Integer... ids);


    // insert multiple
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long[] insert(WorkExperience... workExperiences);

    // insert single
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insert(WorkExperience workExperience);



    // update single
    @Update
    int update(WorkExperience workExperience);

    // update multiple
    @Update
    int update(WorkExperience... workExperiences);



    // delete single
    @Delete
    int delete(WorkExperience workExperience);

    // delete multiple
    @Delete
    int delete(WorkExperience... workExperiences);

}
