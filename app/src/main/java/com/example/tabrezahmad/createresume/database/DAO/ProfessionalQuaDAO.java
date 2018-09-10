package com.example.tabrezahmad.createresume.database.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.tabrezahmad.createresume.database.Entity.BasicInfo;
import com.example.tabrezahmad.createresume.database.Entity.ProfessionalQualification;
import com.example.tabrezahmad.createresume.database.Entity.Project;

import java.util.List;

@Dao
public interface ProfessionalQuaDAO {

    // find all
    @Query("SELECT * FROM professionalqualification")
    List<ProfessionalQualification> getAllProfessional();

    // find single
    @Query("SELECT * FROM professionalqualification WHERE f_key == :id")
    ProfessionalQualification getProfessional(Integer id);

    // find multiple
    @Query("SELECT * FROM professionalqualification WHERE f_key IN (:ids)")
    List<ProfessionalQualification> getProfessionals(Integer... ids);



    // insert multiple
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long[] insert(ProfessionalQualification... professionalQualifications);

    // insert single
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insert(ProfessionalQualification professionalQualification);


    // update single
    @Update
    int update(ProfessionalQualification professionalQualification);

    // update multiple
    @Update
    int update(ProfessionalQualification... professionalQualifications);



    // delete single
    @Delete
    int delete(ProfessionalQualification professionalQualification);

    // delete multiple
    @Delete
    int delete(ProfessionalQualification... professionalQualifications);

}
