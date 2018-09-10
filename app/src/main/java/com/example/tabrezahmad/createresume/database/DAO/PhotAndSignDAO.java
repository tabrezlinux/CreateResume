package com.example.tabrezahmad.createresume.database.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.tabrezahmad.createresume.database.Entity.CareerObjective;
import com.example.tabrezahmad.createresume.database.Entity.PhotoAndSign;

@Dao
public interface PhotAndSignDAO {

    // find single by f_key
    @Query("SELECT * FROM photoandsign WHERE f_key == :id")
    PhotoAndSign getPhotoAndSign(Integer id);

    // insert single
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insert(PhotoAndSign photoAndSign);

    // update single
    @Update
    int update(PhotoAndSign photoAndSign);

    // delete single
    @Delete
    int delete(PhotoAndSign photoAndSign);

}
