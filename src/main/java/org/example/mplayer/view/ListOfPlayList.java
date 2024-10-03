package org.example.mplayer.view;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import org.example.mplayer.HelloApplication;
import org.example.mplayer.controller.AudioController;
import org.example.mplayer.model.audio.Audio;
import java.io.IOException;
import java.util.ArrayList;

import org.example.mplayer.model.audio.PlayList;

public class ListOfPlayList {

    @FXML
    private Pane listPane;

    @FXML
    private Label playListName;

    private GridPane grid =new GridPane();
    private int row = 0;
    private AnchorPane previous;
    private AnchorPane current;
    private PlayList playList;
    boolean isSorted=false;

    @FXML
    private JFXButton sort;
    private int currRow=0;
    String currSong = "";

    public void sortClick(ActionEvent event) {

        isSorted=true;
        if(playList.getPlayListName().equals(listName)) {
            currSong = playList.getAudioList().get(Utils.getInstance().getIndex()).getAudioName();
        }
        grid.getChildren().clear();
        row=0;
//        playList.getAudioList().clear();
//        for(Audio audio : AudioController.getAudioController().sortPlaylist(playList)) {
//            playList.getAudioList().add(audio);
//        }
        try{
            for(Audio audio : AudioController.getAudioController().sortPlaylist(playList)){
                if(audio.getAudioName().equals(currSong)){
                    Utils.getInstance().setIndex(row);
                    currRow=row;
                }
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("fxmls/song.fxml"));
                AnchorPane Apane = fxmlLoader.load();
                Song song = (Song) fxmlLoader.getController();
                song.setData(audio.getAudioName(),audio.getArtistUsername(), "0:00",audio.getCover());
                Utils.getInstance().setEndTime(audio,song);
                grid.add(Apane, 0, row);
                row++;
//                if(row>6) {
//                    grid.setPrefHeight(grid.getPrefHeight() + 90);
//                    songPane.setPrefHeight(songPane.getPrefHeight() + 60);
//                }
            }
        }catch (IOException e){
            System.out.println("kkkkk");
        }
        GridEvent();
        listPane.getChildren().clear();
        listPane.getChildren().add(grid);
        if(playList.getPlayListName().equals(listName)) {
            Cpanes.getInstance().getBorder().setAudioha(AudioController.getAudioController().sortPlaylist(playList));
            Cpanes.getInstance().getBorder().index = currRow;
            Cpanes.getInstance().getBorder().setPlayScene("ListOfPlaylist");
        }
//        Utils.getInstance().setIndex();
    }
    public  void setData(PlayList playList){
        isSorted=false;
        this.playList = playList;
        Cpanes.getInstance().getBorder().getScenes().add("ListOfPlaylist");
        this.playListName.setText(playList.getPlayListName());
        addSongs();
    }
    public void nextColor(int index){
        System.out.println(index+"inHOme");
        if(playList.getPlayListName().equals(listName)) {
            Node childNode = grid.getChildren().get(index);
            System.out.println(childNode.getId());
            AnchorPane rowAnchorPane = (AnchorPane) childNode;
            AnchorPane tmp = (AnchorPane) rowAnchorPane.getChildren().get(0);
            Utils.getInstance().nextColor(tmp, index, "playlist");
        }
        else{
            Utils.getInstance().setPlayList("playlist");
            Utils.getInstance().setIndex(index);
        }
    }
    private String listName = "";
    private void GridEvent() {

        int rowCount = grid.getRowCount();

        for (int rowIndex = 0; rowIndex < rowCount; rowIndex++) {
            Node childNode = grid.getChildren().get(rowIndex);
            AnchorPane rowAnchorPane = (AnchorPane) childNode;

            final int targRow = rowIndex;
            rowAnchorPane.setOnMouseClicked(event ->{
                listName = playList.getPlayListName();

                Utils.getInstance().nextColor((AnchorPane) rowAnchorPane.getChildren().get(0),targRow,"playlist");
                if(isSorted) {
                    Cpanes.getInstance().getBorder().setAudioha(AudioController.getAudioController().sortPlaylist(playList));
                }else{
                    Cpanes.getInstance().getBorder().setAudioha(playList.getAudioList());

                }
                    Cpanes.getInstance().getBorder().setIndex(targRow);
                    Cpanes.getInstance().getBorder().setPlayScene("ListOfPlaylist");

            });
        }
        System.out.println("liatname" + listName + " \t playlist: " + playList.getPlayListName() );
        if(playList.getPlayListName().equals(listName)) {
            if (Utils.getInstance().getPlayList().equals("playlist")) {
                Node childNode0 = grid.getChildren().get(Utils.getInstance().getIndex());
                AnchorPane rowAnchorPane0 = (AnchorPane) childNode0;
                Utils.getInstance().nextColor((AnchorPane) rowAnchorPane0.getChildren().get(0), Utils.getInstance().getIndex(), "playlist");
            }
        }
    }
    @FXML
    private Label noSongLbl;

    public void unshuffle(){
        Cpanes.getInstance().getBorder().setAudioha(playList.getAudioList());
    }

    public void shuffle(ArrayList<Audio> list){
        try {
            for (Audio audio : list) {
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("fxmls/song.fxml"));
                AnchorPane Apane = fxmlLoader.load();
                Song song = (Song) fxmlLoader.getController();
                song.setData(audio.getAudioName(), audio.getArtistUsername(), "0:00", audio.getCover());
                Utils.getInstance().setEndTime(audio, song);
                grid.add(Apane, 0, row);
                row++;

            }
        }
        catch (IOException e){
        System.out.println("kkkkk");
    }
    GridEvent();

    }
    public void addSongs(){
        grid.getChildren().clear();
        row=0;
        System.out.println(playList.getAudioList().size());
        if(playList.getAudioList().size()==0){
            System.out.println("popopoppooooooo");
            listPane.getChildren().clear();
            listPane.getChildren().add(noSongLbl);
            noSongLbl.setVisible(true);
            sort.setVisible(false);

        }
        else {
            noSongLbl.setVisible(false);
            sort.setVisible(true);
            listPane.getChildren().clear();
            listPane.getChildren().add(grid);
        }
        try{
            for(Audio audio : playList){
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("fxmls/song.fxml"));
                AnchorPane Apane = fxmlLoader.load();
                Song song = (Song) fxmlLoader.getController();
                song.setData(audio.getAudioName(),audio.getArtistUsername(), "0:00",audio.getCover());
                Utils.getInstance().setEndTime(audio,song);
                grid.add(Apane, 0, row);
                row++;
//                if(row>6) {
//                    grid.setPrefHeight(grid.getPrefHeight() + 90);
//                    songPane.setPrefHeight(songPane.getPrefHeight() + 60);
//                }
            }
        }catch (IOException e){
            System.out.println("kkkkk");
        }
        GridEvent();
//        listPane.getChildren().clear();
//        listPane.getChildren().add(grid);
    }

}
