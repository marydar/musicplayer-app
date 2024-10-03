package org.example.mplayer.view;

import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.example.mplayer.HelloApplication;
import org.example.mplayer.model.audio.Audio;
import java.io.File;

public class Utils {
    private static Utils instance;
    public static Utils getInstance() {
        if (instance == null) {
            instance = new Utils();
        }
        return instance;
    }

    private String playList = "home";
    public String getPlayList() {
        return playList;
    }
    public void setPlayList(String playList) {
        this.playList = playList;
    }
    private int index = 0;
    public int getIndex() {
        return index;
    }
    public void setIndex(int index) {
        this.index = index;
    }


    private AnchorPane previous;
    private AnchorPane current;

    public void nextColor(AnchorPane tmp,int index,String list){
        this.index = index;
        playList = list;
        previous = current;
        current = tmp;
        if(current!=previous) {
            if (current != null) {
                System.out.println("jiji");
                current.getChildren().get(0).setStyle("-fx-text-fill: -fx-third;");
                current.getChildren().get(1).setStyle("-fx-text-fill: -fx-third;");
                current.getChildren().get(2).setStyle("-fx-text-fill: -fx-third;");
            }

            if (previous != null) {
                System.out.println("popo");
                previous.getChildren().get(0).setStyle("-fx-text-fill: -fx-BW;");
                previous.getChildren().get(1).setStyle("-fx-text-fill: -fx-BW;");
                previous.getChildren().get(2).setStyle("-fx-text-fill: -fx-BW;");
            }
        }
    }

    public Stage getErrorStage() {return errorStage;}

    private Stage errorStage;
    private Scene errorScene = new Scene(new Pane());
    static int counter = 0;
    Pane pane;
    Error errorC;
    private String setFinal(MediaPlayer mp){
        int min =((int) (mp.getTotalDuration().toSeconds()))/60;
        int sec = ((int)(mp.getTotalDuration().toSeconds()))%60;
        if(sec/10==0){
            return  min+":0"+sec;

        }else{
            return min+":"+sec;
        }
    }

    public void translateTransition(int fromY, int toY, Node node,int milli){
        TranslateTransition tt = new TranslateTransition();
        tt.setNode(node);
        tt.setDuration(Duration.millis(milli));
        tt.setInterpolator(Interpolator.EASE_OUT);
        tt.setCycleCount(1);
        tt.setFromY(fromY);
        tt.setToY(toY);
        tt.play();

    }

    public void setEndTime(Audio audio, Song song){
        String ret = "0:0";
        File file = new File(audio.getLink());
        Media music = new Media(file.toURI().toString());
        MediaPlayer mp = new MediaPlayer(music);
        if (mp.getStatus() == MediaPlayer.Status.UNKNOWN) {
            mp.statusProperty().addListener((obs, oldStatus, newStatus) -> {
                if (newStatus == MediaPlayer.Status.READY) {
                    song.setTime(setFinal(mp));
                }
            });
        } else {
            song.setTime(setFinal(mp));
        }
    }

    public void showError(String message){
        if(Cpanes.getInstance().getBorder().isDark()){
            Cpanes.getInstance().getError().getStylesheets().clear();
            Cpanes.getInstance().getError().getStylesheets().add(HelloApplication.class.getResource("fxmls/Dark.css").toExternalForm());
        }
        else{
            Cpanes.getInstance().getError().getStylesheets().clear();
            Cpanes.getInstance().getError().getStylesheets().add(HelloApplication.class.getResource("fxmls/Light.css").toExternalForm());
        }
        errorStage = new Stage();
        errorStage.initModality(Modality.APPLICATION_MODAL);
        errorScene.setRoot(Cpanes.getInstance().getError());
        Cpanes.getInstance().getErrorC().setMessage(message);
        errorStage.setScene(errorScene);
        errorStage.setX(690);
        errorStage.setY(620);
        errorStage.initStyle(StageStyle.TRANSPARENT);
        errorStage.show();
    }
}
