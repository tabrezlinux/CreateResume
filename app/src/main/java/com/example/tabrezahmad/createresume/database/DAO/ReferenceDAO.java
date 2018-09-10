package com.example.tabrezahmad.createresume.database.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.tabrezahmad.createresume.database.Entity.Reference;

@Dao
public interface ReferenceDAO {

    // find single by f_key
    @Query("SELECT * FROM reference WHERE f_key == :id")
    Reference getReference(Integer id);

    // insert single
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insert(Reference reference);

    // update single
    @Update
    int update(Reference reference);

    // delete single
    @Delete
    int delete(Reference reference);

}
