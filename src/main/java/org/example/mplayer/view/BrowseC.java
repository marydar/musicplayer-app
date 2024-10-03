package org.example.mplayer.view;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.NodeOrientation;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.util.Duration;
import org.example.mplayer.HelloApplication;
import org.example.mplayer.controller.AudioController;
import org.example.mplayer.model.database.Database;
import org.example.mplayer.model.audio.Audio;
import org.example.mplayer.model.user.User;
import org.example.mplayer.model.user.Artist;

public class BrowseC implements Initializable {

    public JFXButton getSearch() {
        return search;
    }

    public Pane getSearchPane() {
        return searchPane;
    }

    public String getLast() {
        return last;
    }

    public ArrayList<Audio> getAudios() {
        return Audios;
    }


    public GridPane getGrid() {
        return grid;
    }

    public GridPane getGridSong() {
        return gridSong;
    }

    public GridPane getGridSearch() {
        return gridSearch;
    }

    @FXML
    private JFXButton search;

    AnchorPane previous;
    AnchorPane current;

    @FXML
    private TextField field;

    @FXML
    private Pane searchPane;

    @FXML
    private JFXButton artists;
    @FXML
    private JFXButton audios;
//    @FXML
//    private RadioButton artists;
//    @FXML
//    private RadioButton audios;
//    @FXML
//    private RadioButton key;

    private String last = "search";

    private ArrayList<Audio> Audios = new ArrayList<>();

    private GridPane grid = new GridPane();
    private int row = 0;
    private int col = 0;
    private GridPane gridSong = new GridPane();
    private int rowS = 0;
    private GridPane gridSearch = new GridPane();
    private int rowC = 0;

    private boolean isInAll=false;


    public void nextColor(int index){
        System.out.println(index+"inHOme");
        Node childNode;
        if(isInAll) {
            childNode = gridSong.getChildren().get(index);
            System.out.println(childNode.getId());
            AnchorPane rowAnchorPane = (AnchorPane) childNode;
            AnchorPane tmp = (AnchorPane) rowAnchorPane.getChildren().get(0);
            Utils.getInstance().nextColor(tmp,index,"Allsongs");
        }
        else{
            childNode = gridSearch.getChildren().get(index);
            System.out.println(childNode.getId());
            AnchorPane rowAnchorPane = (AnchorPane) childNode;
            AnchorPane tmp = (AnchorPane) rowAnchorPane.getChildren().get(0);
            Utils.getInstance().nextColor(tmp,index,"search");
        }

    }

    private void showAllArtists(){
        try{
            for (User user : Database.getDatabase().getAllUsers()) {
                if(user instanceof Artist) {
                    System.out.println("before artist");
                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("fxmls/Artist.fxml"));
                    AnchorPane Bpane = fxmlLoader.load();
                    System.out.println("adter");
                    ArtistC artist = (ArtistC) fxmlLoader.getController();
                    String path = Paths.get("src/main/resources/org/example/mplayer/images/b.png").toAbsolutePath().toString();
                    Image image = new Image("file:" + path);
                    artist.setData(((Artist)user).getUsername(), image);
                    if (col > 2) {
                        col = 0;
                        row++;
                    }
                    grid.add(Bpane, col, row);
                    col++;
                    if (row > 1) {
                        grid.setPrefHeight(grid.getPrefHeight() + 100);
                        searchPane.setPrefHeight(searchPane.getPrefHeight() + 100);
                    }
                    final int targRow = row;
                    final int targCol = col - 1;
                    System.out.println("before");
                    Bpane.setOnMouseClicked(e -> {
                        System.out.println("row: " + targRow + " col: " + targCol);
                        Pane pane = new Pane();
                        pane.setPrefSize(1100, 650);
                        pane.getChildren().add(Cpanes.getInstance().getArtistInfo());
                        Cpanes.getInstance().getBorder().last = "ArtistInfo";
                        Cpanes.getInstance().getArF().setData((Artist) user);
                        Cpanes.getInstance().getThCPane().setCenter(pane);

                    });
                }
            }
        }
        catch (IOException e){
        }
    }

    @FXML
    void artistClick(ActionEvent event) {
        last = "AllArtists";
        Cpanes.getInstance().getBorder().getScenes().add("AllArtists");
        searchPane.getChildren().clear();
        searchPane.getChildren().add(grid);
        isInAll= false;
        System.out.println("artists");
        grid.getChildren().clear();
        row=0;
        col=0;
        showAllArtists();
        Utils.getInstance().translateTransition(-200,0,grid,500);
    }

    private void showAllSongs(){
        for (Audio audio : Database.getDatabase().getAllAudio()) {
            try{
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("fxmls/song.fxml"));
                AnchorPane Apane = fxmlLoader.load();
                Song song = (Song) fxmlLoader.getController();
                song.setData(audio.getAudioName(),audio.getArtistUsername(), "0:00",audio.getCover());
                Utils.getInstance().setEndTime(audio,song);
                gridSong.add(Apane, 0, rowS);
                rowS++;
                if(rowS>6){
                    gridSong.setPrefHeight(gridSong.getPrefHeight()+100);
                    searchPane.setPrefHeight(searchPane.getPrefHeight()+100);
                }
                final int trow = rowS-1;
                Apane.setOnMouseClicked(e ->{
                    System.out.println("set");
                    Utils.getInstance().nextColor((AnchorPane) Apane.getChildren().get(0),trow,"Allsongs");
                    Cpanes.getInstance().getBorder().setAudioha(Database.getDatabase().getAllAudio());
                    Cpanes.getInstance().getBorder().setIndex(trow);
                    Cpanes.getInstance().getBorder().setPlayScene("browse");

                });
            }catch (IOException e){
            }
        }
        if(Utils.getInstance().getPlayList().equals("Allsongs")) {
            Node childNode0 = gridSong.getChildren().get(Utils.getInstance().getIndex());
            AnchorPane rowAnchorPane0 = (AnchorPane) childNode0;
            Utils.getInstance().nextColor((AnchorPane) rowAnchorPane0.getChildren().get(0),Utils.getInstance().getIndex(), "Allsongs");
        }
    }
    @FXML
    void audioClick(ActionEvent event) {
        last = "AllAudios";
        Cpanes.getInstance().getBorder().getScenes().add("AllAudios");
        searchPane.getChildren().clear();
        searchPane.getChildren().add(gridSong);
        isInAll = true;
        System.out.println("audios");
        gridSong.getChildren().clear();
        row=0;
        showAllSongs();
        Utils.getInstance().translateTransition(-300,0,gridSong,500);

    }
    public void unShuffle(){
        gridSong.getChildren().clear();
        row=0;
        showAllSongs();
        Cpanes.getInstance().getBorder().setAudioha(Database.getDatabase().getAllAudio());

    }

    public void shuffle(ArrayList<Audio> songs){
        gridSong.getChildren().clear();
        row=0;
        for (Audio audio : songs) {
            try{
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("fxmls/song.fxml"));
                AnchorPane Apane = fxmlLoader.load();
                Song song = (Song) fxmlLoader.getController();
                song.setData(audio.getAudioName(),audio.getArtistUsername(), "0:00",audio.getCover());
                Utils.getInstance().setEndTime(audio,song);
                gridSong.add(Apane, 0, rowS);
                rowS++;
                if(rowS>6){
                    gridSong.setPrefHeight(gridSong.getPrefHeight()+100);
                    searchPane.setPrefHeight(searchPane.getPrefHeight()+100);
                }
                final int trow = rowS-1;
                Apane.setOnMouseClicked(e ->{
                    System.out.println("set");
                    Utils.getInstance().nextColor((AnchorPane) Apane.getChildren().get(0),trow,"Allsongs");
                    Cpanes.getInstance().getBorder().setAudioha(songs);
                    Cpanes.getInstance().getBorder().setIndex(trow);
                    Cpanes.getInstance().getBorder().setPlayScene("browse");

                });
            }catch (IOException e){
            }
        }
        if(Utils.getInstance().getPlayList().equals("Allsongs")) {
            Node childNode0 = gridSong.getChildren().get(Utils.getInstance().getIndex());
            AnchorPane rowAnchorPane0 = (AnchorPane) childNode0;
            Utils.getInstance().nextColor((AnchorPane) rowAnchorPane0.getChildren().get(0),Utils.getInstance().getIndex(), "Allsongs");
        }
    }

    public void firstScene(){
        last = "AllAudios";
        searchPane.getChildren().clear();
        searchPane.getChildren().add(gridSong);
        isInAll = true;
        System.out.println(field.getText().toString());
        gridSong.getChildren().clear();
        rowS = 0;
        showAllSongs();
    }
    private void showSearchResult(String s){
        for (Audio a : AudioController.getAudioController().searchKeyAudio(s)) {
            try {

                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("fxmls/song.fxml"));
                AnchorPane Apane = fxmlLoader.load();
                Song song = (Song) fxmlLoader.getController();
                song.setData(a.getAudioName(), a.getArtistUsername(), "0:00",a.getCover());
                Utils.getInstance().setEndTime(a,song);
                gridSearch.add(Apane, 0, rowC);
                rowC++;

                final int trow = rowC - 1;
                Apane.setOnMouseClicked(e -> {
                    System.out.println("set");


                    Utils.getInstance().nextColor((AnchorPane) Apane.getChildren().get(0),trow,"search");
                        Cpanes.getInstance().getBorder().setAudioha(AudioController.getAudioController().searchKeyAudio(s));
                        Cpanes.getInstance().getBorder().setIndex(trow);

                });

            } catch (IOException e) {
            }

        }
    }

    @FXML
    public void searchClick(ActionEvent event) {
        if(Cpanes.getInstance().getBorder().getScenes().peek().equals("search")==false) {
            Cpanes.getInstance().getBorder().getScenes().add("search");
        }
        last = "search";
        searchPane.getChildren().clear();
        searchPane.getChildren().add(gridSearch);
        isInAll = false;
        System.out.println(field.getText().toString());
        gridSearch.getChildren().clear();
        rowC = 0;
//            firstScene();
        String s =field.getText();
        System.out.println(field.getText().toString());
        showSearchResult(s);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        last = "AllAudios";
        searchPane.getChildren().add(gridSong);
        firstScene();
        isInAll = true;
        String path = Paths.get("src/main/resources/org/example/mplayer/images/browse.png").toAbsolutePath().toString();
        Image image = new Image("file:" + path);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(25);
        imageView.setFitWidth(25);
        imageView.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        imageView.setPreserveRatio(true);
        search.setGraphic(imageView);
        field.setOnKeyPressed( event -> {
            String s = field.getText();
            if(!event.getCode().toString().equals("BACK_SPACE") && !event.getCode().toString().equals("ENTER")) {
                s+= event.getCode().toString();
            }
            last = "search";
            searchPane.getChildren().clear();
            searchPane.getChildren().add(gridSearch);
            isInAll = false;
            System.out.println(field.getText().toString());
            gridSearch.getChildren().clear();
            rowC = 0;
//            firstScene();
            System.out.println(s + " is s:");
            System.out.println(field.getText().toString());
            showSearchResult(s);
        } );
    }
}
