package org.example.mplayer.view;


import com.jfoenix.controls.JFXButton;
import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class FullScreenC implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        RotateTransition rotate = new RotateTransition();
        rotate.setNode(cover);
        cover.setOnMouseEntered(e->{
            rotate.setDuration(Duration.millis(3000));
            rotate.setCycleCount(1);
            rotate.setInterpolator(Interpolator.LINEAR);
            rotate.setByAngle(360);
            rotate.play();

        });
    }

    @FXML
    private Label lyrics;
    @FXML
    private JFXButton cover;

    @FXML
    private Label audioName;
    @FXML
    private AnchorPane AudioAnc;

    public void setData(String lyric, String coverlink, String audioName, String likes, String plays){
        lyrics.setText(lyric);
        this.audioName.setText(audioName+"\nLikes: "+likes+"\tPlays: "+plays);
        Image image = new Image("file:"+coverlink);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(500);
        imageView.setFitWidth(500);
        imageView.setPreserveRatio(true);
        this.cover.setGraphic(imageView);


        ScaleTransition scaleTransition = new ScaleTransition();
        scaleTransition.setNode(cover);
        scaleTransition.setDuration(Duration.millis(1000));
        scaleTransition.setCycleCount(1);
        scaleTransition.setFromX(0.1);
        scaleTransition.setFromY(0.1);
        scaleTransition.setToX(1);
        scaleTransition.setToY(1);
        scaleTransition.play();

        FadeTransition fadeTransition = new FadeTransition(Duration.millis(2000), this.audioName);
        fadeTransition.setFromValue(0.1);
        fadeTransition.setToValue(1);
        fadeTransition.play();

        FadeTransition fadeTransition2 = new FadeTransition(Duration.millis(2000), this.lyrics);
        fadeTransition2.setFromValue(0.1);
        fadeTransition2.setToValue(1);
        fadeTransition2.play();
    }
}
