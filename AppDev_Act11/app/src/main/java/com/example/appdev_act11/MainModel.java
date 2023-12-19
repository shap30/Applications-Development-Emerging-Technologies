package com.example.appdev_act11;

public class MainModel {

    String b_name, c_song, d_playlist, e_imageurl;

    MainModel() {
    }

    public MainModel(String b_name, String c_song, String d_playlist, String e_imageurl) {
        this.b_name = b_name;
        this.c_song = c_song;
        this.d_playlist = d_playlist;
        this.e_imageurl = e_imageurl;
    }

    public String getB_name() {
        return b_name;
    }

    public void setB_name(String b_name) {
        this.b_name = b_name;
    }

    public String getC_song() {
        return c_song;
    }

    public void setC_song(String c_song) {
        this.c_song = c_song;
    }

    public String getD_playlist() {
        return d_playlist;
    }

    public void setD_playlist(String d_playlist) {
        this.d_playlist = d_playlist;
    }

    public String getE_imageurl() {
        return e_imageurl;
    }

    public void setE_imageurl(String e_imageurl) {
        this.e_imageurl = e_imageurl;
    }
}
