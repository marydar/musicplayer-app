package org.example.mplayer.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.example.mplayer.model.audio.Music;
import org.example.mplayer.model.audio.Podcast;
import org.example.mplayer.model.user.Podcaster;

public class PodcasterController {

    private static PodcasterController podcasterController;

    private PodcasterController() {

    }

    public static PodcasterController getPodcasterController() {
        if (podcasterController == null) {
            podcasterController = new PodcasterController();
        }
        return podcasterController;
    }

    private static Podcaster podcaster;

    public static Podcaster getPodcaster() {
        return podcaster;
    }

    public static void setPodcaster(Podcaster podcaster) {
        PodcasterController.podcaster = podcaster;
    }

    public void loginPodcaster(Podcaster podcaster) {
        setPodcaster(podcaster);
    }

    public String ShowPodcasterInfo() {
        String txt = "Podcaster info:" +
                "\nuser name : " + getPodcaster().getUsername() +
                "\nName : " + getPodcaster().getName() +
                "\nFollowers : " + String.valueOf(getPodcaster().getFollowers().size()) +
                "\nBiographi : " + getPodcaster().getBiographi() + "\n\n";
        if (getPodcaster().getPodcastList().size() == 0) {
            txt += "No Podcast found!!";
            return txt;
        }
        for (Podcast podcast : getPodcaster().getPodcastList()) {
            txt += "-" + podcast.getAudioName() + "(id:" + String.valueOf(podcast.getId()) + ")\n";

        }

        return txt;
    }

    public String showViewsStatics() {
        ArrayList <Podcast> podcasts = new ArrayList<>();
        String txt = "Podcasts\n";
        if (getPodcaster().getPodcastList().size() == 0) {
            txt += "No Podcast found!!";
            return txt;
        }
        List<Podcast> sorted = getPodcaster().getPodcastList()
                    .stream()
                    .sorted(Comparator.comparing(Podcast -> Podcast.getNumberOfPlays()))
                    .collect(Collectors.toCollection(ArrayList::new))
                    .reversed();

            ArrayList<Podcast> sortedPods = new ArrayList<Podcast>(sorted);
        for (Podcast podcast : sortedPods) {
            txt += "-" + podcast.getAudioName() + "(" + String.valueOf(podcast.getNumberOfPlays()) + ")\n";
        }

        return txt;
    }

}
