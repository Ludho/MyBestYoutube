package com.mybestyoutube.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.mybestyoutube.dao.YoutubeVideoDAO;
import com.mybestyoutube.model.YoutubeVideo;

/*
*
*  Youtube Video DAO Accessors
*
* */
@Database(entities = {YoutubeVideo.class},version = 1,exportSchema = false)
public abstract class YoutubeVideoDataBase extends RoomDatabase {
    private static final String DATABASE_NAME = "video";

    public static YoutubeVideoDataBase getDb(Context context){
        return Room.databaseBuilder(context, YoutubeVideoDataBase.class, DATABASE_NAME).allowMainThreadQueries().build();
    }

    public abstract YoutubeVideoDAO youtubeVideoDAO();
}
