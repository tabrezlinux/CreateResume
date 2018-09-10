package com.example.tabrezahmad.createresume.database.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.tabrezahmad.createresume.database.Entity.Activities;
import com.example.tabrezahmad.createresume.database.Entity.Hobby;

import java.util.List;

@Dao
public interface HobbyDAO {

    // find all
    @Query("SELECT * FROM hobby")
    List<Hobby> getAllHobby();

    // find single
    @Query("SELECT * FROM hobby WHERE f_key == :id")
    Hobby getHobby(Integer id);

    // find multiple
    @Query("SELECT * FROM hobby WHERE f_key IN (:ids)")
    List<Hobby> getHobbies(Integer... ids);



    // insert multiple
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long[] insert(Hobby... hobbies);

    // insert single
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insert(Hobby hobby);



    // update single
    @Update
    int update(Hobby hobby);

    // update multiple
    @Update
    int update(Hobby... hobbies);



    // delete single
    @Delete
    int delete(Hobby hobby);

    // delete multiple
    @Delete
    int delete(Hobby... hobbies);

    
}
