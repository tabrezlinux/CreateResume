package com.example.tabrezahmad.createresume.database.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface PhotoAndSignDAO {

    // select all user
    @Query("SELECT * FROM PhotoAndSign")
    List<PhotoAndSignDAO> getPhotoSign();

    @Query("SELECT * FROM PhotoAndSign WHERE id IN (:id) LIMIT 1")
    PhotoAndSignDAO getPhotoSign(int id);

    // insert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertPhotoSign(PhotoAndSignDAO... photoSigns);

    // delete user
    @Delete
    void delete(PhotoAndSignDAO photoSign);
}
