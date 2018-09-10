package com.example.tabrezahmad.createresume.database.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.tabrezahmad.createresume.database.Entity.Hobby;
import com.example.tabrezahmad.createresume.database.Entity.Skill;

import java.util.List;

@Dao
public interface SkillDAO {

    // find all
    @Query("SELECT * FROM skill")
    List<Skill> getAllSkill();

    // find single
    @Query("SELECT * FROM skill WHERE f_key == :id")
    Skill getSkill(Integer id);

    // find multiple
    @Query("SELECT * FROM skill WHERE f_key IN (:ids)")
    List<Skill> getSkills(Integer... ids);



    // insert multiple
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long[] insert(Skill... skills);

    // insert single
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insert(Skill skill);



    // update single
    @Update
    int update(Skill skill);

    // update multiple
    @Update
    int update(Skill... skills);



    // delete single
    @Delete
    int delete(Skill skill);

    // delete multiple
    @Delete
    int delete(Skill... skills);

    
}
