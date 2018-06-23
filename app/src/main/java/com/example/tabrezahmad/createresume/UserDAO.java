package com.example.tabrezahmad.createresume;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

@Dao
public interface UserDAO {

    // select all user
    @Query("SELECT * FROM user LIMIT 1")
    User getUser();

    // insert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertUsers(User... users);


    // delete user
    @Delete
    void delete(User user);

}
