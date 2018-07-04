package com.example.tabrezahmad.createresume.database.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.tabrezahmad.createresume.database.Entity.AcademicQualification;
import com.example.tabrezahmad.createresume.database.Entity.BasicInfo;

import java.util.List;

@Dao
public interface QualificationAcademicDAO {

    // select all user
    @Query("SELECT * FROM academicqualification")
    public List<AcademicQualification> getAllQualification();

    @Query("SELECT * FROM academicqualification WHERE uid IN (:id) LIMIT 1")
    public AcademicQualification getQualification(int id);

    @Query("SELECT * FROM academicqualification LIMIT 1")
    public AcademicQualification getQualification();

    // insert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(AcademicQualification... academicQualifications);

    // update
    @Update
    public void update(AcademicQualification... academicQualifications);

    // delete
    @Delete
    public void delete(AcademicQualification... academicQualifications);


}
