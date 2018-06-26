package com.example.tabrezahmad.createresume.database.Entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class PhotoAndSign {

    @PrimaryKey(autoGenerate = true)
    private Integer uid;
    private String photo_url;
    private String sign_url;

}
