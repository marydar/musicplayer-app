package org.example.mplayer.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.example.mplayer.model.audio.Album;
import org.example.mplayer.model.audio.Audio;
import org.example.mplayer.model.audio.Music;
import org.example.mplayer.model.database.Database;
import org.example.mplayer.model.user.Singer;
import org.example.mplayer.model.user.User;

public class SingerController {

    private static SingerController singerController;

    private SingerController() {

    }

    public static SingerController getSingerController() {
        if (singerController == null) {
            singerController = new SingerController();
        }
        return singerController;
    }

    private static Singer singer;

    public static Singer getSinger() {
        return singer;
    }

    public static void setSinger(Singer singer) {
        SingerController.singer = singer;
    }

    public String showFollowers() {
        String txt = "All Followers\n";
        for (User user : getSinger().getFollowers()) {
            txt += "-" + user.getUsername() + "\n";
        }
        return txt;
    }

    public void loginSinger(Singer singer) {
        setSinger(singer);
    }

    public String ShowSingerInfo() {
        String txt = "Singer info:" +
                "\nuser name : " + getSinger().getUsername() +
                "\nName : " + getSinger().getName() +
                "\nFollowers : " + String.valueOf(getSinger().getFollowers().size()) +
                "\nBiographi : " + getSinger().getBiographi() + "\n\n";
        if (getSinger().getAlbumList().size() == 0) {
            txt += "No album found!!";
            return txt;
        }
        for (Album album : getSinger().getAlbumList()) {
            txt += album.getAlbumName() + "\n";
            for (Music music : album.getMusicList()) {
                txt += "-" + music.getAudioName() + "(id:" + String.valueOf(music.getId()) + ")\n";
            }
        }

        return txt;
    }

    public String showViewsStatics() {
        ArrayList<Music> musics =new ArrayList<>();
        String txt = "View\n";
        if (getSinger().getAlbumList().size() == 0) {
            txt += "No album found!!";
            return txt;
        }
        for (Album album : getSinger().getAlbumList()) {
            for (Music music : album.getMusicList()) {
                musics.add(music);
            }
        }
        if(musics.size()==0){
            txt+="no music found";
            return txt;
        }
        List<Music> sorted = musics
                    .stream()
                    .sorted(Comparator.comparing(Music -> Music.getNumberOfPlays()))
                    .collect(Collectors.toCollection(ArrayList::new))
                    .reversed();

            ArrayList<Music> sortedMusics = new ArrayList<Music>(sorted);
            for (Music music :sortedMusics) {
                txt += "-" + music.getAudioName() + "(" + String.valueOf(music.getNumberOfPlays()) + ")\n";
            }


        return txt;
    }

    public String createNewAlbum(String albumName) {
        getSinger().addToAlbumList(new Album(albumName, getSinger().getName()));
        return "your album has been created succesfully";
    }

}
