package org.example.mplayer.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.stream.Collectors;

import org.example.mplayer.model.audio.*;
import org.example.mplayer.model.database.Database;
import java.util.*;

public class AudioController {
    private static AudioController audioController;

    private AudioController() {

    }

    public static AudioController getAudioController() {
        if (audioController == null) {
            audioController = new AudioController();
        }
        return audioController;
    }

    public ArrayList<Audio> searchKeyAudio(String key) {
        key = key.toLowerCase();
        ArrayList<Audio> audios = new ArrayList<>();
        for (Audio audio : Database.getDatabase().getAllAudio()) {
            if(audio.getAudioName().toLowerCase().contains(key) || audio.getArtistUsername().toLowerCase().contains(key)) {
                audios.add(audio);
            }
        }
        return audios;
    }
    public ArrayList<Audio> searchAudio(String key) {
        ArrayList<Audio> audios = new ArrayList<>();
        for (Audio audio : Database.getDatabase().getAllAudio()) {
            if ((audio.getAudioName().equals(key)) || audio.getArtistName().equals(key)) {
                 audios.add(audio);
            }
        }
        return audios;
    }

    public ArrayList<Audio> sortPlaylist(PlayList playList) {
        ArrayList<Audio> audios = new ArrayList<>();
        audios.addAll(playList.getAudioList());
        Collections.sort(audios);
        return audios;
    }
    public ArrayList<Audio> sortHome(){
        List<Audio> sorted = Database.getDatabase().getAllAudio()
                .stream()
                .sorted(Comparator.comparing(Audio -> Audio.getNumberOfLikes()))
                .collect(Collectors.toCollection(ArrayList::new))
                .reversed();

        ArrayList<Audio> sortedArr = new ArrayList<Audio>(sorted);
        return sortedArr;
    }

    public String sortAudio(String base) {
        String txt = "Sorted Audios according to ";
        if (base.equals("P")) {
            txt += "number of plays\n";
            List<Audio> sorted = Database.getDatabase().getAllAudio()
                    .stream()
                    .sorted(Comparator.comparing(Audio -> Audio.getNumberOfPlays()))
                    .collect(Collectors.toCollection(ArrayList::new))
                    .reversed();

            ArrayList<Audio> sortedArr = new ArrayList<Audio>(sorted);

            for (Audio audio : sortedArr) {
                txt += "-" + audio.getAudioName() + "(" + audio.getArtistName() + ")\tPlays: "
                        + String.valueOf(audio.getNumberOfPlays()) + "\n";
            }
        }
        if (base.equals("L")) {
            txt += "number of likes\n";
            List<Audio> sorted = Database.getDatabase().getAllAudio()
                    .stream()
                    .sorted(Comparator.comparing(Audio -> Audio.getNumberOfLikes()))
                    .collect(Collectors.toCollection(ArrayList::new))
                    .reversed();
            ArrayList<Audio> sortedArr = new ArrayList<Audio>(sorted);
            for (Audio audio : sortedArr) {
                txt += "-" + audio.getAudioName() + "(" + audio.getArtistName() + ")\tLikes: "
                        + String.valueOf(audio.getNumberOfLikes()) + "\n";
            }
        }
        return txt;
    }

    public String filterAudio(String base, String key) throws ParseException {
        if (base.equals("A")) {
            return filterByArtist(key);
        }
        if (base.equals("G")) {
            return filterByGenres(key);
        }
        if (base.equals("D")) {
            return filterByDate(key);
        } else {
            return "please enter \n'A' if you want audios to filter by artist name\n'G' if you want audios to filter by genres\n'D' if you want audios to filter by release date\n";
        }
    }

    private String filterByArtist(String key) {
        String txt = " Audios filtered by artist\n";
        ArrayList<Audio> filtered = Database.getDatabase().getAllAudio()
                .stream()
                .filter(Audio -> Audio.getArtistUsername().equals(key))
                .collect(Collectors.toCollection(ArrayList::new));
        if (filtered.size() == 0) {
            return "this artist may not be valid or no audio was published by the Artist, enter another name!";
        }
        for (Audio audio : filtered) {
            txt += "-" + audio.getAudioName() + "(" + audio.getArtistName() + ")\n";

        }
        return txt;
    }

    private String filterByGenres(String key) {
        String txt = "Audios filtered by genres\n";
        ArrayList<Audio> filtered = Database.getDatabase().getAllAudio()
                .stream()
                .filter(Audio -> Audio.getGenre().equals(Genre.valueOf(key)))
                .collect(Collectors.toCollection(ArrayList::new));
        if (filtered.size() == 0) {
            return "no audio was published with this genre,  enter another one!";
        }
        for (Audio audio : filtered) {
            txt += "-" + audio.getAudioName() + "(" + audio.getArtistName() + ")\n";

        }
        return txt;
    }

    private boolean cmpDate(Date one, Date two) {
        // LocalDate localone =
        // one.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(one);
        cal2.setTime(two);
        if (cal1.get(Calendar.DAY_OF_MONTH) != cal2.get(Calendar.DAY_OF_MONTH)) {
            return false;
        }
        if (cal1.get(Calendar.MONTH) != cal2.get(Calendar.MONTH)) {
            return false;
        }
        if (cal1.get(Calendar.YEAR) != cal2.get(Calendar.YEAR)) {
            return false;
        }
        return true;
    }

    private String filterByDate(String key) throws ParseException {
        // String [] keys = key.split("/");
        // LocalDate date = LocalDate
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        Date date = formatter.parse(key);

        String txt = "Audios filtered by date\n";
        ArrayList<Audio> filtered = Database.getDatabase().getAllAudio()
                .stream()
                .filter(Audio -> cmpDate(Audio.getReleaseDate(), date))
                .collect(Collectors.toCollection(ArrayList::new));
        if (filtered.size() == 0) {
            return "no audio was published on this day enter another date!";
        }
        for (Audio audio : filtered) {
            txt += "-" + audio.getAudioName() + "(" + audio.getArtistName() + ")\n";

        }
        return txt;
    }

    public String dateToString(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String strDate = dateFormat.format(date);
        return strDate;
    }

    public String filterByDate(String dateone, String datetwo) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        Date date1 = formatter.parse(dateone);
        Date date2 = formatter.parse(datetwo);
        String txt = "Audios filtered between two dates\n";
        ArrayList<Audio> filtered = Database.getDatabase().getAllAudio()
                .stream()
                .filter(e -> e.getReleaseDate().before(date2)
                        && e.getReleaseDate().after(date1))
                .sorted(Comparator.comparing(e -> e.getReleaseDate()))
                .collect(Collectors.toCollection(ArrayList::new));
        if (filtered.size() == 0) {
            return "no audio was published between these two dates, enter two other dates!";
        }
        for (Audio audio : filtered) {
            txt += "-" + audio.getAudioName() + "(" + audio.getArtistName() + ")\n";

        }
        return txt;
    }

    public String playAudio(long audioId) {
        for (Audio audio : Database.getDatabase().getAllAudio()) {
            if (audio.getId() == audioId) {
                audio.setNumberOfPlays(audio.getNumberOfPlays() + 1);
                String txt = "the audio is playing\n" +
                        "title : " + audio.getAudioName() + "\n" +
                        "Artist : " + audio.getArtistName() + "\n" +
                        "likes : " + String.valueOf(audio.getNumberOfLikes()) + "\n" +
                        "plays : " + String.valueOf(audio.getNumberOfPlays()) + "\n";
                return txt;
            }
        }
        return "enter a valid id";
    }

    public boolean likeAudio(long audioId) {
        for (Audio audio : Database.getDatabase().getAllAudio()) {
            if (audio.getId() == audioId) {
                audio.setNumberOfLikes(audio.getNumberOfLikes() + 1);
                return true;
            }
        }
        return false;
    }
    public boolean unlikeAudio(long audioId) {
        for (Audio audio : Database.getDatabase().getAllAudio()) {
            if (audio.getId() == audioId) {
                audio.setNumberOfLikes(audio.getNumberOfLikes() - 1);
                return true;
            }
        }
        return false;
    }

    public String showLyric(long audioId) {
        for (Audio audio : Database.getDatabase().getAllAudio()) {
            if (audio.getId() == audioId) {
                if (audio instanceof Music) {
                    return ((Music) audio).getLyrics();
                }
                if (audio instanceof Podcast) {
                    return ((Podcast) audio).getCaption();
                }
            }
        }
        return "enter a valid id";
    }

}
