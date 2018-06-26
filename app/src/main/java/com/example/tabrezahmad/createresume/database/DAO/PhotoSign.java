package com.example.tabrezahmad.createresume.database.DAO;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.tabrezahmad.createresume.database.Entity.ProfessionalQualification;

import java.util.List;

public interface PhotoSign {

    // select all user
    @Query("SELECT * FROM PhotoAndSign")
    List<PhotoSign> getPhotoSign();

    @Query("SELECT * FROM PhotoAndSign WHERE id IN (:id) LIMIT 1")
    PhotoSign getPhotoSign(int id);

    // insert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertPhotoSign(PhotoSign... photoSigns);

    // delete user
    @Delete
    void delete(PhotoSign photoSign);
}
