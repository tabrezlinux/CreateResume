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
public interface AcademicQuaDAO {

    // find all
    @Query("SELECT * FROM academicqualification")
    List<AcademicQualification> getAllAcademic();

    // find single
    @Query("SELECT * FROM academicqualification WHERE uid == :id")
    AcademicQualification getAcademic(Integer id);

    // find multiple
    @Query("SELECT * FROM academicqualification WHERE uid IN (:ids)")
    List<AcademicQualification> getAcademics(Integer... ids);



    // insert multiple
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long[] insert(AcademicQualification... academicQualifications);

    // insert single
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insert(AcademicQualification academicQualification);



    // update single
    @Update
    int update(AcademicQualification academicQualification);

    // update multiple
    @Update
    int update(AcademicQualification... academicQualifications);



    // delete single
    @Delete
    int delete(AcademicQualification academicQualification);

    // delete multiple
    @Delete
    int delete(AcademicQualification... academicQualifications);

}
