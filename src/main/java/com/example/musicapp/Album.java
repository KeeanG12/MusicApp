package com.example.musicapp;

import java.sql.Date;
import java.util.ArrayList;

public class Album {
    
    private int albumID;
    private  String name;
    private Date produced;
    private int artistID;

    public Album(int albumID, String name, Date produced, int artistID) {
        this.albumID = albumID;
        this.name = name;
        this.produced = produced;
        this.artistID = artistID;
    }

    public int getAlbumID() {
        return albumID;
    }

    public void setAlbumID(int albumID) {
        this.albumID = albumID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getProduced() {
        return produced;
    }

    public void setProduced(Date produced) {
        this.produced = produced;
    }

    public int getArtistID() {
        return artistID;
    }

    public void setArtistID(int artistID) {
        this.artistID = artistID;
    }
}
