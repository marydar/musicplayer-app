package org.example.mplayer.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.nio.file.Paths;

public class Song {

    @FXML
    private Circle songCover;
    @FXML
    private Label songName;
    @FXML
    private Label Artist;
    @FXML
    private Label time;
    @FXML
    private AnchorPane anchorIN;
    public void setTime(String timee) {
        System.out.println(timee);
        this.time.setText(timee);
    }
    public String getName() {
        return songName.getText();
    }

    public void setData(String songName, String artist, String time,String cover) {
//        String path = Paths.get("src/main/resources/org/example/mplayer/images/"+cover).toAbsolutePath().toString();
        Image image = new Image("file:" + cover);
        songCover.setFill(new ImagePattern(image,0,0,1,1,true));
        this.songName.setText(songName);
        this.Artist.setText(artist);
        this.time.setText(time);
    }
}
