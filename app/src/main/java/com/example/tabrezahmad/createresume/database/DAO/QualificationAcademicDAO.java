package com.example.tabrezahmad.createresume.database.DAO;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.tabrezahmad.createresume.database.Entity.AcademicQualification;
import com.example.tabrezahmad.createresume.database.Entity.BasicInfo;

import java.util.List;

public interface QualificationAcademicDAO {

    // select all user
    @Query("SELECT * FROM academicqualification")
    List<AcademicQualification> getAllAcademicQualification();

    @Query("SELECT * FROM academicqualification WHERE id IN (:id) LIMIT 1")
    AcademicQualification getAcademicQualification(int id);

    // insert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertAcademicQualification(AcademicQualification... academicQualifications);

    // delete user
    @Delete
    void delete(BasicInfo basicInfo);
}
