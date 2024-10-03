package org.example.mplayer.view;
import javafx.scene.chart.*;
import org.example.mplayer.controller.AdminController;
import org.example.mplayer.controller.ArtistController;
import org.example.mplayer.model.audio.Audio;
import org.example.mplayer.model.audio.Music;
import org.example.mplayer.model.audio.Podcast;
import org.example.mplayer.model.database.Database;
import org.example.mplayer.model.user.Artist;
import org.example.mplayer.model.user.Podcaster;
import org.example.mplayer.model.user.Singer;

import java.util.ArrayList;

public class chart {
    public static BarChart<String, Number> drawBar(Artist artist){
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String,Number> bc =
                new BarChart<String,Number>(xAxis,yAxis);
        bc.setTitle("Views statistics");
        xAxis.setLabel("");
        yAxis.setLabel("");

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Plays");
//        XYChart.Series series2 = new XYChart.Series();
//        series1.setName("Likes");
        bc.setPrefHeight(600);
        bc.setPrefWidth(1000);
        ArrayList<Audio> songsArr = new ArrayList<>();
        if(ArtistController.getArtist() instanceof Singer){
            for(Music m: ((Singer) ArtistController.getArtist()).getMusicList()){
                songsArr.add(m);
            }

        }
        else if(ArtistController.getArtist() instanceof Podcaster){
            for(Podcast p: ((Podcaster) ArtistController.getArtist()).getPodcastList()){
                songsArr.add(p);
            }
        }
        for(Audio a: songsArr){
            series1.getData().add(new XYChart.Data(a.getAudioName(), a.getNumberOfPlays()));
        }
        bc.getData().addAll(series1);

        return bc;
    }
    public static BarChart<String, Number> drawAll(){
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String,Number> bc =
                new BarChart<String,Number>(xAxis,yAxis);
        bc.setTitle("Views statistics");
        xAxis.setLabel("");
        yAxis.setLabel("");

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Likes");
//        XYChart.Series series2 = new XYChart.Series();
//        series1.setName("Likes");
        bc.setPrefHeight(600);
        bc.setPrefWidth(1000);

        for(Audio a: AdminController.getAdminController().popularAudios()){
            series1.getData().add(new XYChart.Data(a.getAudioName(), a.getNumberOfLikes()));
        }
        bc.getData().addAll(series1);

        return bc;
    }
}
