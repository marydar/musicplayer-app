package org.example.mplayer.model.user;

import java.util.ArrayList;
import java.util.Date;

import org.example.mplayer.model.audio.Album;
import org.example.mplayer.model.audio.Music;

public class Singer extends Artist{
    public void setMusicList(ArrayList<Music> musicList) {
        this.musicList = musicList;
    }

    private ArrayList<Album> albumList;

    public ArrayList<Music> getMusicList() {
        return musicList;
    }

    private ArrayList<Music> musicList;
    public Singer(String password,String username, String name, String emailAddress, String phoneNumber,
            Date birthDate, String biographi) {
        super(password,username, name , emailAddress, phoneNumber, birthDate, biographi);
        albumList = new ArrayList<>();
        musicList = new ArrayList<>();
    }

    public ArrayList<Album> getAlbumList() {
        return albumList;
    }

    public void setAlbumList(ArrayList<Album> albumList) {
        this.albumList = albumList;
    }
    public void addToAlbumList(Album album) {
        this.albumList.add(album);
    }

    
    
    
}
