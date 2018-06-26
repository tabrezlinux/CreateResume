package com.example.tabrezahmad.createresume.database.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.tabrezahmad.createresume.database.Entity.BasicInfo;
import com.example.tabrezahmad.createresume.database.Entity.IndustrialExposure;
import com.example.tabrezahmad.createresume.database.Entity.Training;
import com.example.tabrezahmad.createresume.database.Entity.Internship;
import com.example.tabrezahmad.createresume.database.Entity.Project;

import java.util.List;

@Dao
public interface ProjectAndTrainingDAO {

    // select all user
    @Query("SELECT * FROM project")
    List<Project> getProject();

    @Query("SELECT * FROM internship")
    List<Internship> getInternship();

    @Query("SELECT * FROM industrialExposure")
    List<IndustrialExposure> getIndustrialExposure();

    @Query("SELECT * FROM Training")
    List<Training> getInplantTrainingAttended();


    @Query("SELECT * FROM project WHERE id IN (:id) LIMIT 1")
    BasicInfo getProject(int id);

    @Query("SELECT * FROM internship WHERE id IN (:id) LIMIT 1")
    BasicInfo getInternship(int id);

    @Query("SELECT * FROM industrialexposure WHERE id IN (:id) LIMIT 1")
    BasicInfo getIndustrialExposure(int id);

    @Query("SELECT * FROM Training WHERE id IN (:id) LIMIT 1")
    BasicInfo getInplantTrainingAttended(int id);

    // insert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertProject(Project... projects);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertInternship(Internship... internships);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertIndustrialExposure(IndustrialExposure... industrialExposures);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertInplantTrainingAttended(Training... trainings);

    // delete user
    @Delete
    void delete(Project project);

    @Delete
    void delete(Internship internship);

    @Delete
    void delete(IndustrialExposure industrialExposure);

    @Delete
    void delete(Training training);


}
