package com.example.tabrezahmad.createresume.database.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.tabrezahmad.createresume.database.Entity.Skill;
import com.example.tabrezahmad.createresume.database.Entity.Strength;

import java.util.List;

@Dao
public interface StrengthDAO {

    // find all
    @Query("SELECT * FROM strength")
    List<Strength> getAllStrength();

    // find single
    @Query("SELECT * FROM strength WHERE f_key == :id")
    Skill getStrength(Integer id);

    // find multiple
    @Query("SELECT * FROM strength WHERE f_key IN (:ids)")
    List<Strength> getStrengths(Integer... ids);



    // insert multiple
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long[] insert(Strength... strengths);

    // insert single
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insert(Strength strength);



    // update single
    @Update
    int update(Strength strength);

    // update multiple
    @Update
    int update(Strength... strengths);



    // delete single
    @Delete
    int delete(Strength strength);

    // delete multiple
    @Delete
    int delete(Strength... strengths);

    
}
