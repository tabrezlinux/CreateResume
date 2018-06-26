package com.example.tabrezahmad.createresume.database.Entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class PhotoAndSign {

    @PrimaryKey(autoGenerate = true)
    public Integer uid;
    public String photo_url;
    public String sign_url;

}
