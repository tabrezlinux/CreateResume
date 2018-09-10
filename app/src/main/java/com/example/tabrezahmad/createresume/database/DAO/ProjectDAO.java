package com.example.tabrezahmad.createresume.database.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.tabrezahmad.createresume.database.Entity.BasicInfo;
import com.example.tabrezahmad.createresume.database.Entity.Project;

import java.util.List;

@Dao
public interface ProjectDAO {

    // find all
    @Query("SELECT * FROM project")
    List<Project> getAllProject();

    // find single
    @Query("SELECT * FROM project WHERE f_key == :id")
    Project getProject(Integer id);

    // find multiple
    @Query("SELECT * FROM project WHERE f_key IN (:ids)")
    List<Project> getProjects(Integer... ids);



    // insert multiple
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long[] insert(Project... projects);

    // insert single
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insert(Project project);


    // update single
    @Update
    int update(Project project);

    // update multiple
    @Update
    int update(Project... projects);



    // delete single
    @Delete
    int delete(Project project);

    // delete multiple
    @Delete
    int delete(Project... projects);

}
