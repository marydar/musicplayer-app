package org.example.mplayer.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import org.example.mplayer.HelloApplication;
import org.example.mplayer.controller.AudioController;
import org.example.mplayer.controller.ListenerController;
import org.example.mplayer.model.audio.Audio;

public class HomeC implements Initializable {

    @FXML
    private Label trendLbl;
    @FXML
    private Pane trendPane;
    private AnchorPane previous;
    private AnchorPane current;

    private GridPane grid =new GridPane();
    private ArrayList<Audio> audios=new ArrayList<>();
    private int row=0;

    public void nextColor(int index){
        Node childNode = grid.getChildren().get(index);
        AnchorPane rowAnchorPane = (AnchorPane) childNode;
        AnchorPane tmp = (AnchorPane) rowAnchorPane.getChildren().get(0);
        System.out.println(tmp.getId());
        Utils.getInstance().nextColor(tmp,index,"home");
    }

    public void addList(){

        for(Audio audio:audios){
            try{

                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("fxmls/song.fxml"));
                AnchorPane Apane = fxmlLoader.load();
                Song song = (Song) fxmlLoader.getController();
                song.setData(audio.getAudioName(),audio.getArtistUsername(), "0:00",audio.getCover());
                Utils.getInstance().setEndTime(audio,song);
                grid.add(Apane, 0, row);
                row++;
                if(row>6){
                    grid.setPrefHeight(grid.getPrefHeight()+150);
                    trendPane.setPrefHeight(trendPane.getPrefHeight()+150);
                }
            }catch (IOException e){
            }
        }
    }
    private int currIndex = 0;
    private void GridEvent() {
        int rowCount = grid.getRowCount();

        for (int rowIndex = 0; rowIndex < rowCount; rowIndex++) {
            Node childNode = grid.getChildren().get(rowIndex);
            AnchorPane rowAnchorPane = (AnchorPane) childNode;

            final int targRow = rowIndex;
            rowAnchorPane.setOnMouseClicked(event ->{


                Utils.getInstance().nextColor((AnchorPane) rowAnchorPane.getChildren().get(0),targRow,"home");
                    Cpanes.getInstance().getBorder().setAudioha(audios);
                    Cpanes.getInstance().getBorder().setPlayScene("home");
                    Cpanes.getInstance().getBorder().setIndex(targRow);

            });
        }
        if(Utils.getInstance().getPlayList().equals("home")) {
            Node childNode0 = grid.getChildren().get(Utils.getInstance().getIndex());
            AnchorPane rowAnchorPane0 = (AnchorPane) childNode0;
            Utils.getInstance().nextColor((AnchorPane) rowAnchorPane0.getChildren().get(0),Utils.getInstance().getIndex(), "home");
        }

    }

    public void firstRowInhome(){
        Node childNode0 = grid.getChildren().get(0);
        AnchorPane rowAnchorPane0 = (AnchorPane) childNode0;
        Utils.getInstance().nextColor((AnchorPane) rowAnchorPane0.getChildren().get(0),0, "home");
        Utils.getInstance().setIndex(0);
        Cpanes.getInstance().getBorder().setAudioha(audios);
        Cpanes.getInstance().getBorder().setPlayScene("home");
        Cpanes.getInstance().getBorder().index = 0;
    }
    public void shuffle(ArrayList<Audio> shuffledList){
        audios = shuffledList;
        grid.getChildren().clear();
        row=0;
        addList();
        GridEvent();

    }
    public void setUnShuffle(){
        Cpanes.getInstance().getBorder().setAudioha(audios);

    }
    public void suggestion(){
        trendLbl.setText("Suggestion");
        audios = ListenerController.getListenerController().suggestion(5);
        grid.getChildren().clear();
        row=0;
        addList();
        GridEvent();
    }
    public void trending(){
        trendLbl.setText("Trending");
        audios = AudioController.getAudioController().sortHome();
        grid.getChildren().clear();
        row=0;
        addList();
        GridEvent();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        trendPane.getChildren().add(grid);
        trending();
        Cpanes.getInstance().getBorder().setAudioha(audios);
        Cpanes.getInstance().getBorder().setPlayScene("home");

    }
}
