package org.example.mplayer.view;
import com.jfoenix.controls.JFXButton;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.example.mplayer.HelloApplication;
import org.example.mplayer.controller.ListenerController;
import org.example.mplayer.model.audio.Music;
import org.example.mplayer.model.audio.Podcast;
import org.example.mplayer.model.audio.Audio;
import org.example.mplayer.model.user.Artist;
import org.example.mplayer.model.user.Podcaster;
import org.example.mplayer.model.user.Singer;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ArtistInfoC implements Initializable {
    private AnchorPane previous;
    private AnchorPane current;
    private Artist artist ;
    public Artist getArtist() {return artist;}
    @FXML
    private Label bio;
    private ArrayList<Audio> ArtistAudios = new ArrayList<>();
    @FXML
    private JFXButton personalArt;

    public void personalArtClick(ActionEvent event) {
        Cpanes.getInstance().getBorder().save("Name : "+artist.getName()+"\nEmail : "+artist.getEmailAddress()+"\nBirth Date : "+artist.getBirthDate()+"\nIncome : "+artist.getIncome()+"\nPhone number : "+artist.getPhoneNumber());
    }

    //////////////
    public void setData(Artist artist){
        if(Cpanes.getInstance().getBorder().isAdmin){
            personalArt.setVisible(true);
            personalArt.setText("more info");
            followBtn.setVisible(false);
            reportBtn.setVisible(false);
        }
        else{
            personalArt.setVisible(false);
            followBtn.setVisible(true);
            reportBtn.setVisible(true);
        }
        this.artist = artist;
        Cpanes.getInstance().getBorder().getScenes().add("ArtistInfo");
        String path = Paths.get("src/main/resources/org/example/mplayer/images/b.png").toAbsolutePath().toString();
        Image image = new Image("file:" + path);
        imgCir.setFill(new ImagePattern(image,0,0,1,1,true));
        nameLbl.setText(artist.getUsername());
        followerLbl.setText(String.valueOf(artist.getFollowers().size()));
        bio.setText(artist.getBiographi());
        if(Cpanes.getInstance().getBorder().LIsLogin) {
            if (ListenerController.getListenerController().getListener().getFollowings().contains(artist)) {
                followBtn.setText("Following");
            } else {
                followBtn.setText("Follow");
            }
        }else{
            followBtn.setText("Follow");
        }
        addSongs(artist);

        ScaleTransition scaleTransition = new ScaleTransition();
        scaleTransition.setNode(imgCir);
        scaleTransition.setDuration(Duration.millis(1000));
        scaleTransition.setCycleCount(1);
        scaleTransition.setFromX(0.1);
        scaleTransition.setFromY(0.1);
        scaleTransition.setToX(1);
        scaleTransition.setToY(1);
        scaleTransition.play();

        TranslateTransition tt = new TranslateTransition();
        tt.setNode(grid);
        tt.setDuration(Duration.millis(1000));
        tt.setCycleCount(1);
        tt.setFromY(600);
        tt.setToY(0);
        tt.play();

    }


    public void nextColor(int index){
        if(currArtistUser.equals(artist.getUsername())) {
            System.out.println(index + "inHOme");
            Node childNode = grid.getChildren().get(index);
            System.out.println(childNode.getId());
            AnchorPane rowAnchorPane = (AnchorPane) childNode;
            AnchorPane tmp = (AnchorPane) rowAnchorPane.getChildren().get(0);
            Utils.getInstance().nextColor(tmp, index, "artistInfo");
        }
        else{
            Utils.getInstance().setIndex(index);
            Utils.getInstance().setPlayList("artistInfo");
        }
    }


    private String currArtistUser = "";
    private void GridEvent() {

        int rowCount = grid.getRowCount();

        for (int rowIndex = 0; rowIndex < rowCount; rowIndex++) {
            Node childNode = grid.getChildren().get(rowIndex);
            AnchorPane rowAnchorPane = (AnchorPane) childNode;

            final int targRow = rowIndex;
            rowAnchorPane.setOnMouseClicked(event ->{

                Utils.getInstance().nextColor((AnchorPane) rowAnchorPane.getChildren().get(0),targRow,"artistInfo");
                    audioTypeToAudio();
                    ArrayList<Audio> tmp = new ArrayList<>();
                    tmp.addAll(ArtistAudios);
                    currArtistUser = artist.getUsername();
                    Cpanes.getInstance().getBorder().setAudioha(tmp);
                    Cpanes.getInstance().getBorder().setIndex(targRow);
                    Cpanes.getInstance().getBorder().setPlayScene("ArtistInfo");


            });
        }
        if(currArtistUser.equals(artist.getUsername())) {
            if (Utils.getInstance().getPlayList().equals("artistInfo")) {
                Node childNode0 = grid.getChildren().get(Utils.getInstance().getIndex());
                AnchorPane rowAnchorPane0 = (AnchorPane) childNode0;
                Utils.getInstance().nextColor((AnchorPane) rowAnchorPane0.getChildren().get(0), Utils.getInstance().getIndex(), "artistInfo");
            }
        }else{
//            currArtistUser = artist.getUsername();
        }
    }
    public void audioTypeToAudio(){
        ArtistAudios.clear();
        if(artist instanceof Singer) {
            for (Music m : ((Singer) artist).getMusicList()) {
                ArtistAudios.add((Audio) m);
            }
        }else{
            for (Podcast p : ((Podcaster) artist).getPodcastList()) {
                ArtistAudios.add((Audio) p);
            }
        }
    }

    public void shuffle(ArrayList<Audio> list){
        grid.getChildren().clear();
        row=0;
        try{
            for (Audio a : list) {
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("fxmls/song.fxml"));
                AnchorPane Apane = fxmlLoader.load();
                Song song = (Song) fxmlLoader.getController();
                song.setData(a.getAudioName(),a.getArtistUsername(), "0:00",a.getCover());
                Utils.getInstance().setEndTime(a,song);
                grid.add(Apane, 0, row);
                row++;
                if(row>5) {
                    grid.setPrefHeight(grid.getPrefHeight() + 100);
                    songPane.setPrefHeight(songPane.getPrefHeight() + 100);
                }
            }
        }catch (IOException e){
            System.out.println("kkkkk");
        }
        GridEvent();
        songPane.getChildren().clear();
        songPane.getChildren().add(grid);
    }
    public void setUnShuffle(){
        Cpanes.getInstance().getBorder().getAudioha().clear();
        Cpanes.getInstance().getBorder().getAudioha().addAll(ArtistAudios);

    }
    public void addSongs(){
        addSongs(artist);
    }
    public void addSongs(Artist artist){
        grid.getChildren().clear();
        row=0;
        audioTypeToAudio();
        try{
            for (Audio a : ArtistAudios) {
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("fxmls/song.fxml"));
                AnchorPane Apane = fxmlLoader.load();
                Song song = (Song) fxmlLoader.getController();
                song.setData(a.getAudioName(),a.getArtistUsername(), "0:00",a.getCover());
                Utils.getInstance().setEndTime(a,song);
                grid.add(Apane, 0, row);
                row++;
                if(row>5) {
                    grid.setPrefHeight(grid.getPrefHeight() + 100);
                    songPane.setPrefHeight(songPane.getPrefHeight() + 100);
                }
            }
        }catch (IOException e){
            System.out.println("kkkkk");
        }
        GridEvent();
        songPane.getChildren().clear();
        songPane.getChildren().add(grid);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private Label nameLbl;
    @FXML
    private Label followerLbl;
    @FXML
    private Pane songPane;
    @FXML
    private Circle imgCir;
    @FXML
    private JFXButton followBtn;
    @FXML
    private JFXButton reportBtn;
    public GridPane grid = new GridPane();
    private int row=0;
    private int col=0;

    @FXML
    public void followClick(){
        if(Cpanes.getInstance().getBorder().LIsLogin) {
            if (ListenerController.getListenerController().follow(artist)) {
                Cpanes.getInstance().getLibraryC().addToArtists(artist.getUsername());
                followBtn.setText("following");
            }
            else{
                Cpanes.getInstance().getLibraryC().showFollowedArtists();
                followBtn.setText("follow");
            }
        }else{
            Utils.getInstance().showError("you dont have access");
        }
    }
    @FXML
    public void reportClick(){
        if(Cpanes.getInstance().getBorder().LIsLogin) {
            Cpanes.getInstance().getLibraryC().newStage = new Stage();
            Stage newStage = Cpanes.getInstance().getLibraryC().newStage;
            newStage.initModality(Modality.APPLICATION_MODAL);//another way????
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("fxmls/reportCard.fxml"));
            try {
                newStage.setScene(new Scene(fxmlLoader.load()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            newStage.setTitle("create");
            newStage.setX(710);
            newStage.setY(400);
            newStage.initStyle(StageStyle.TRANSPARENT);
            newStage.show();

        }else{
            Utils.getInstance().showError("you dont have access");
        }
    }

}
