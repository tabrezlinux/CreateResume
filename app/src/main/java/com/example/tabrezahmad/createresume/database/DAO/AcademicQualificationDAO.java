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
public interface AcademicQualificationDAO {

    // get all qualification
    @Query("SELECT * FROM academicqualification")
    public List<AcademicQualification> getAllQualification();

    // get single qualification
    @Query("SELECT * FROM academicqualification WHERE uid == :id LIMIT 1")
    public AcademicQualification getQualification(int id);

    // insert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(AcademicQualification... academicQualifications);

    // insert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public Long insert(AcademicQualification academicQualifications);

    // update
    @Update
    public void update(AcademicQualification academicQualifications);

    // delete
    @Delete
    public void delete(AcademicQualification academicQualifications);


}
