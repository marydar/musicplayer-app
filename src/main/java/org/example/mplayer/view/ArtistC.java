package org.example.mplayer.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class ArtistC {
    @FXML
    public Label name;
    @FXML
    private Circle imageC;
    @FXML
    private AnchorPane ArtAnc;

    public void setData(String name, Image image) {
        this.name.setText(name);
        this.imageC.setFill(new ImagePattern(image,0,0,1,1,true));
    }

}
