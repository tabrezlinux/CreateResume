package com.example.tabrezahmad.createresume.database.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.tabrezahmad.createresume.database.Entity.BasicInfo;
import com.example.tabrezahmad.createresume.database.Entity.Internship;

import java.util.List;

@Dao
public interface InternshipDAO {

    // find all
    @Query("SELECT * FROM internship")
    List<Internship> getAllIngernship();

    // find single
    @Query("SELECT * FROM internship WHERE f_key == :id")
    Internship getInternship(Integer id);

    // find multiple
    @Query("SELECT * FROM internship WHERE f_key IN (:ids)")
    List<Internship> getInternships(Integer... ids);



    // insert multiple
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long[] insert(Internship... internships);

    // insert single
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insert(Internship internship);



    // update single
    @Update
    int update(Internship internship);

    // update multiple
    @Update
    int update(Internship... internships);



    // delete single
    @Delete
    int delete(Internship internship);

    // delete multiple
    @Delete
    int delete(Internship... internships);


}
