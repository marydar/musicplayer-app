package org.example.mplayer.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.example.mplayer.model.audio.Album;
import org.example.mplayer.model.audio.Audio;
import org.example.mplayer.model.audio.Music;
import org.example.mplayer.model.audio.Podcast;
import org.example.mplayer.model.database.Database;
import org.example.mplayer.model.user.*;

public class AdminController {
    private static AdminController adminController;

    private AdminController() {

    }

    public static AdminController getAdminController() {
        if (adminController == null) {
            adminController = new AdminController();
        }
        return adminController;
    }

    public ArrayList<String> ShowAccountInfo() {

        ArrayList<String> list = new ArrayList<>();
        list.add(Admin.getAdmin().getUsername());
        list.add(Admin.getAdmin().getName());
        list.add(Admin.getAdmin().getEmailAddress());
        list.add(Admin.getAdmin().getPassword());
        list.add(Admin.getAdmin().getPhoneNumber());
        list.add(String.valueOf(Admin.getAdmin().getBirthDate()));
        return list;
    }

    public ArrayList<Audio> popularAudios() {
        String txt = "Popular Audios\n";
        List<Audio> sorted = Database.getDatabase().getAllAudio()
                .stream()
                .sorted(Comparator.comparing(Audio -> Audio.getNumberOfLikes()))
                .collect(Collectors.toCollection(ArrayList::new))
                .reversed();
        ArrayList<Audio> sortedArr = new ArrayList<Audio>(sorted);

        return sortedArr;
    }
    /// artists and audios


    public String showAllAudios(int page) {
        int audiosPerPage = 5;
        String txt = "All Audios Page " + String.valueOf(page) + "\n";
        // first 1 last 5/ first 6 last 10
        int first = (audiosPerPage * page) - (audiosPerPage - 1);
        int last = (audiosPerPage * page);
        if (first > Database.getDatabase().getAllAudio().size()) {
            txt += "no song found on this page";
            return txt;
        }
        int counter = 1;
        for (Audio audio : Database.getDatabase().getAllAudio()) {
            if (counter >= first && counter <= last) {
                txt += "Title : " + audio.getAudioName() + "\t" +
                        "Likes : " + String.valueOf(audio.getNumberOfLikes()) + "\t" +
                        "Plays : " + String.valueOf(audio.getNumberOfPlays()) + "\n";
            }
            counter++;
        }
        return txt;

    }

    public String showAudioInfo(long audioId) {
        String txt = "Information of Audio with Id :" + String.valueOf(audioId) + "\n";
        for (Audio audio : Database.getDatabase().getAllAudio()) {
            if (audio.getId() == audioId) {
                txt += "Title : " + audio.getAudioName() + "\n" +
                        "Artist : " + audio.getArtistName() + "\n" +
                        "Genre : " + String.valueOf(audio.getGenre()) + "\n" +
                        "Likes : " + String.valueOf(audio.getNumberOfLikes()) + "\n" +
                        "Plays : " + String.valueOf(audio.getNumberOfPlays()) + "\n" +
                        "release Date : " + String.valueOf(audio.getReleaseDate()) + "\n" +
                        "Link : " + String.valueOf(audio.getLink()) + "\n";
            }
        }
        return txt;
    }

    public String reports() {
        String txt = "All reports\n";
        if (Database.getDatabase().getAllReports().size() == 0) {
            return "no report has been recieved";
        }
        for (Report report : Database.getDatabase().getAllReports()) {
            txt += "<->reported Artist : " + report.getReportedArtist() + "\n" +
                    "reporting user : " + report.getReportingUser() + "\n" +
                    "Description : " + report.getDescription() + "\n";
        }
        return txt;
    }

    public ArrayList<Artist> showArtistsAll() {
        ArrayList<Artist> artists = new ArrayList<>();
        for (User user : Database.getDatabase().getAllUsers()) {
            if (user instanceof Artist) {
                artists.add((Artist) user);
            }
        }
        return artists;

    }

    public String showArtistFullInfo(String username) {
        User user = UserController.getUserController().findUser(username);

        if (user instanceof Singer) {
            return showArtistFullInfo((Singer) user, username);
        }
        if (user instanceof Podcaster) {
            return showArtistFullInfo((Podcaster) user, username);
        }

        return null;
    }

    private String showArtistFullInfo(Singer singer, String username) {
        String txt = "Singer info:" +
                "\nuser name : " + singer.getUsername() +
                "\nFirst name : " + singer.getName() +
                "\nemail address: " + singer.getEmailAddress() +
                "\npassword : " + singer.getPassword() +
                "\nbirth date : " + String.valueOf(singer.getBirthDate()) +
                "\nFollowers : " + String.valueOf(singer.getFollowers().size()) +
                "\nBiographi : " + singer.getBiographi() + "\n\n";
        if (singer.getAlbumList().size() == 0) {
            txt += "No album found!!";
            return txt;
        }
        for (Album album : singer.getAlbumList()) {
            txt += album.getAlbumName() + "\n";
            for (Music music : album.getMusicList()) {
                txt += "-" + music.getAudioName() + "(id:" + String.valueOf(music.getId()) + ")\n";
            }
        }

        return txt;
    }

    private String showArtistFullInfo(Podcaster podcaster, String username) {
        String txt = "Podcaster info:" +
                "\nuser name : " + podcaster.getUsername() +
                "\nFirst name : " + podcaster.getName() +
                "\nemail address: " + podcaster.getEmailAddress() +
                "\npassword : " + podcaster.getPassword() +
                "\nbirth date : " + String.valueOf(podcaster.getBirthDate()) +
                "\nFollowers : " + String.valueOf(podcaster.getFollowers().size()) +
                "\nBiographi : " + podcaster.getBiographi() + "\n\n";
        if (podcaster.getPodcastList().size() == 0) {
            txt += "No podcast found!!";
            return txt;
        }
        for (Podcast podcast : podcaster.getPodcastList()) {
            txt += "-" + podcast.getAudioName() + "(id:" + String.valueOf(podcast.getId()) + ")\n";
        }

        return txt;
    }

}
