package org.example.mplayer.model.audio;

import java.util.Date;

public class Podcast extends Audio{
    private String caption;

    public Podcast(String audioName, String artistName, String artistUsername, Date releaseDate, Genre genre, String link, String cover, String caption) {
        super(audioName, artistName, artistUsername, releaseDate, genre, link, cover);
        this.caption = caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getCaption() {
        return caption;
    }
    
}
