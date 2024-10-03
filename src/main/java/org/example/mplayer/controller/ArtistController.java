package org.example.mplayer.controller;

import java.util.ArrayList;
import java.util.Date;

import org.example.mplayer.model.audio.Album;
import org.example.mplayer.model.audio.Genre;
import org.example.mplayer.model.audio.Music;
import org.example.mplayer.model.audio.Podcast;
import org.example.mplayer.model.database.Database;
import org.example.mplayer.model.user.*;

public class ArtistController {
    private static ArtistController artistController;

    private ArtistController() {

    }

    public static ArtistController getArtistController() {
        if (artistController == null) {
            artistController = new ArtistController();
        }
        return artistController;
    }

    private static Artist artist;

    public static Artist getArtist() {
        return artist;
    }

    public static void setArtist(Artist artist) {
        ArtistController.artist = artist;
    }

    public void loginArtist(Artist artist) {
        setArtist(artist);
        if (artist instanceof Singer) {
            SingerController.getSingerController().setSinger((Singer) artist);
        }
        if (artist instanceof Podcaster) {
            PodcasterController.getPodcasterController().setPodcaster((Podcaster) artist);
        }
    }

    public ArrayList<String> ShowAccountInfo() {
        calculateIncome();
        ArrayList<String> list = new ArrayList<>();
        list.add(getArtist().getUsername());
        list.add(getArtist().getName());
        list.add(getArtist().getEmailAddress());
        list.add(getArtist().getPassword());
        list.add(getArtist().getPhoneNumber());
        list.add(String.valueOf(getArtist().getBirthDate()));
        list.add(String.valueOf(getArtist().getIncome()) );
        return list;
    }

//    public String ShowAccountInfo() {
//        calculateIncome();
//        String txt = "Account info:" +
//                "\nuser name : " + getArtist().getUsername() +
//                "\nName : " + getArtist().getName() +
//                "\nemail address: " + getArtist().getEmailAddress() +
//                "\npassword : " + getArtist().getPassword() +
//                "\nbirth date : " + String.valueOf(getArtist().getBirthDate()) +
//                "\nIncome : " + String.valueOf(getArtist().getIncome());
//        return txt;
//    }

    public String showFollowers() {
        String txt = "All Followers\n";
        if (getArtist().getFollowers().size() == 0) {
            txt += "you dont have any followers yet!";
            return txt;
        }
        for (User user : getArtist().getFollowers()) {
            txt += "-" + user.getUsername() + "\n";
        }
        return txt;
    }

    public String showArtistInfo() {
        if (getArtist() instanceof Singer) {
            String txt = SingerController.getSingerController().ShowSingerInfo();
            return txt;
        }
        if (getArtist() instanceof Podcaster) {
            String txt = PodcasterController.getPodcasterController().ShowPodcasterInfo();
            return txt;
        } else
            return null;

    }

    public String showViewsStatics() {
        if (getArtist() instanceof Singer) {
            String txt = SingerController.getSingerController().showViewsStatics();
            return txt;
        }
        if (getArtist() instanceof Podcaster) {
            String txt = PodcasterController.getPodcasterController().showViewsStatics();
            return txt;
        } else
            return null;
    }

    public String createNewAlbum(String albumName) {
        return SingerController.getSingerController().createNewAlbum(albumName);
    }
    public void publishMusic(String title,String genre,  String lyric, String link, String cover){
        Date currentDate = new Date();
        if (getArtist() instanceof Singer) {
            Music tmp = new Music(title, getArtist().getName(), getArtist().getUsername(), currentDate,
                    Genre.valueOf(genre.toUpperCase()), link, cover, lyric);
            Database.getDatabase().addToAllAudio(tmp);
            ((Singer) getArtist()).getMusicList().add(tmp);
        }
    }
    public void publishPodcast(String title,String genre, String caption, String link, String cover){
        Date currentDate = new Date();
        if (getArtist() instanceof Podcaster) {
            Podcast tmp = new Podcast(title, getArtist().getName(), getArtist().getUsername(), currentDate,
                    Genre.valueOf(genre.toUpperCase()), link, cover, caption);
            Database.getDatabase().addToAllAudio(tmp);
            ((Podcaster) getArtist()).getPodcastList().add(tmp);
        }
    }

//    public void publishMusic(String title, String genre, String lyricsCaption, String link, String cover) {
//
//        Date currentDate = new Date();
//            if (getArtist() instanceof Singer) {
//                Music tmp = new Music(title, getArtist().getName(), getArtist().getUsername(), currentDate,
//                        Genre.valueOf(genre.toUpperCase()), link, cover, lyricsCaption);
//                Database.getDatabase().addToAllAudio(tmp);
//                ((Singer) getArtist()).getMusicList().add(tmp);
//            }
//    }
    public String publishAudio(String type, String title, String genre, String lyricsCaption, String link, String cover,
            long albumId) {

        Date currentDate = new Date();
        if (type.equals("P")) {
            if (getArtist() instanceof Podcaster) {
                Podcast tmp = new Podcast(title, getArtist().getName(), getArtist().getUsername(), currentDate,
                        Genre.valueOf(genre.toUpperCase()), link, cover, lyricsCaption);
                Database.getDatabase().addToAllAudio(tmp);
                ((Podcaster) getArtist()).addToPodcastList(tmp);
                return "your podcast published succesfully";
            } else {
                return "you are not a Podcaster You cant publish a podcast";
            }
        }
        if (type.equals("M")) {
            if (getArtist() instanceof Singer) {
                for (Album album : ((Singer) getArtist()).getAlbumList()) {
                    if (album.getId() == albumId) {
                        Music tmp = new Music(title, getArtist().getName(), getArtist().getUsername(), currentDate,
                                Genre.valueOf(genre.toUpperCase()), link, cover, lyricsCaption);
                        Database.getDatabase().addToAllAudio(tmp);
                        album.addToMusicList(tmp);
                        return "your Music published succesfully";
                    }
                }

                return "you dont have access to this album , enter one of your own album id";
            } else
                return "you are not a Singer You Cant publish a music ";

        } else
            return "this type of audio is not valid enter a valid type\n 'M' for Music & 'P' for podcast";
    }

    public void calculateIncome() {
        if (getArtist() instanceof Singer) {
            calculateIncome((Singer) getArtist());
        }
        if (getArtist() instanceof Podcaster) {
            calculateIncome((Podcaster) getArtist());
        }

    }

    public void calculateIncome(Singer singer) {
        long view = 0;
        for(Music m : singer.getMusicList()) {
            view += m.getNumberOfPlays();
        }
        getArtist().setIncome(view * 0.4);
    }

    public void calculateIncome(Podcaster podcaster) {
        long view = 0;
        for (Podcast podcast : podcaster.getPodcastList()) {
            view += podcast.getNumberOfPlays();
        }
        getArtist().setIncome(view * 0.5);
    }

}
