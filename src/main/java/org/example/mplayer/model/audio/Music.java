package org.example.mplayer.model.audio;

import java.util.Date;

public class Music extends Audio{
    private String lyrics;

    public Music(String audioName, String artistName, String artistUsername, Date releaseDate, Genre genre,String link, String cover, String lyrics) {
        super(audioName, artistName,artistUsername, releaseDate, genre,link, cover);
        this.lyrics = lyrics;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }

    public String getLyrics() {
        return lyrics;
    }

}
