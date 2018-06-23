package com.example.tabrezahmad.createresume;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {User.class},version = 1)
public abstract class MyRoomDatabase extends RoomDatabase {
    public abstract UserDAO UserDao();
}
