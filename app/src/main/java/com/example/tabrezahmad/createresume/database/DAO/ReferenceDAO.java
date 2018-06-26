package com.example.tabrezahmad.createresume.database.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.tabrezahmad.createresume.database.Entity.Reference;

import java.util.List;

@Dao
public interface ReferenceDAO {

    // select all user
    @Query("SELECT * FROM reference")
    List<WorkExperienceDAO> getAllWorkExperience();

    @Query("SELECT * FROM reference WHERE id IN (:id) LIMIT 1")
    WorkExperienceDAO getRefernce(int id);

    // insert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertRefrence(Reference... references);

    // delete user
    @Delete
    void delete(Reference reference);
}
