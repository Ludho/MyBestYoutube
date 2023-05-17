package com.mybestyoutube.model;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class YoutubeVideo implements Serializable{

    /*
    *
    * ATTRIBUTS
    *
    */
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    @NonNull
    private long id;

    @ColumnInfo(name="title")
    @NonNull
    private String title;

    @ColumnInfo(name="description")
    @NonNull
    private String description;

    @ColumnInfo(name="url")
    @NonNull
    private String url;

    @ColumnInfo(name="category")
    @NonNull
    private String category;

    @ColumnInfo(name="favorite")
    private int favorite;

    /*
    *
    * CONSRTUCTORS
    *
    * */

    public YoutubeVideo(String title, String description,String url, String category) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.url = url;
        this.favorite = 0;
    }

    public YoutubeVideo(){}

    /*
    *
    *  GETTEURS / SETTERS
    *
    */

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getFavorite() {
        return favorite;
    }

    public void setFavorite(int favorite) {
        this.favorite = favorite;
    }


}
