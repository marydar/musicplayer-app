package org.example.mplayer.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class PlayListC {
    @FXML
    private Label playlistName;
    @FXML
    private Rectangle imageS;
    public PlayListC() {

    }
    public void setData(String playlistName, Image image) {
        this.playlistName.setText(playlistName);
        this.imageS.setFill(new ImagePattern(image,0,0,1,1,true));
    }

}
