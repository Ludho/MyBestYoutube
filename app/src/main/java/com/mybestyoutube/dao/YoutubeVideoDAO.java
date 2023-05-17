package com.mybestyoutube.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.mybestyoutube.model.YoutubeVideo;

import java.util.List;

@Dao
public interface YoutubeVideoDAO {
    @Query("SELECT * FROM YOUTUBEVIDEO WHERE id=:id")
    YoutubeVideo find(int id);

    @Query("DELETE FROM YOUTUBEVIDEO")
    void nukeYoutubeVideo();

    @Insert
    void insertYoutubeVideo(YoutubeVideo youtubeVideo);

    @Update
    void updateYoutubeVideo(YoutubeVideo youtubeVideo);

    @Delete
    void deleteYoutubeVideo(YoutubeVideo youtubeVideo);

    @Query("SELECT * FROM YOUTUBEVIDEO")
    List<YoutubeVideo> getAllYoutubeVideo();

    @Query("SELECT * FROM YOUTUBEVIDEO WHERE category=:category")
    List<YoutubeVideo> getAllYoutubeVideoFromCategory(String category);
}
