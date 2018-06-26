package com.example.tabrezahmad.createresume.database.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.tabrezahmad.createresume.database.Entity.BasicInfo;
import com.example.tabrezahmad.createresume.database.Entity.ProfessionalQualification;

import java.util.List;

@Dao
public interface QualificationProfessionalDAO {

    // select all user
    @Query("SELECT * FROM professionalqualification")
    List<ProfessionalQualification> getAllProfessionalQualification();

    @Query("SELECT * FROM professionalqualification WHERE id IN (:id) LIMIT 1")
    ProfessionalQualification getProfessionalQualification(int id);

    // insert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertProfessionalQualification(ProfessionalQualification... professionalQualifications);

    // delete user
    @Delete
    void delete(ProfessionalQualification professionalQualification);
}
