package org.example.mplayer.view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.example.mplayer.controller.ArtistController;
import org.example.mplayer.model.audio.Album;
import org.example.mplayer.model.user.Podcaster;
import org.example.mplayer.model.user.Singer;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class PublishAudio implements Initializable {
    FileChooser fileChooser = new FileChooser();
    String coverPath;
    String audioPath;
    String audioTitle;
    String audioLyrics;
    String genre;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fileChooser.setInitialDirectory(new File("E:\\Programming\\Musicplayer\\firstproject-musicplayer-phase2-marydar\\mplayer\\src\\main\\resources\\org\\example\\mplayer"));
        genrePicker.getItems().addAll("POP","HIPHOP","JAZZ","ROCK", "COUNTRY","TRUE-CRIME","SOCIETY","INTERVIEW","HISTORY");
    }

    @FXML
    private Label AudioUpload;

    @FXML
    private JFXTextArea lyrics;

    @FXML
    private TextField title;

    @FXML
    private Label uploadCover;
    @FXML
    private JFXButton publishBtn;

    @FXML
    private AnchorPane coverAnc;
    @FXML
    private JFXComboBox<String> genrePicker;

    public void publish(){
        audioPath = audioPath;
        if(ArtistController.getArtist() instanceof Singer){
            ArtistController.getArtistController().publishMusic(audioTitle,genre, audioLyrics, audioPath, coverPath);
        }
        else if(ArtistController.getArtist() instanceof Podcaster){
            ArtistController.getArtistController().publishPodcast(audioTitle,genre, audioLyrics, audioPath, coverPath);
        }
    }

    @FXML
    public void publishClick(ActionEvent event) {
        if(lyrics.getText().isEmpty() || title.getText().isEmpty() || coverPath.isEmpty() || audioPath.isEmpty()){
            Utils.getInstance().showError("fill all the fields");
        }
        else if(genrePicker.getSelectionModel().getSelectedItem()==null ){
            Utils.getInstance().showError("please select a genre");
        }
        else{
            audioLyrics  = lyrics.getText();
            audioTitle = title.getText();
            System.out.println("Title: " + audioTitle + " Lyrics: " + audioLyrics + "audioPath: " + audioPath + "coverPath: " + coverPath);
            lyrics.setText("");
            title.setText("");
            AudioUpload.setText("upload");
            uploadCover.setText("upload");
            genre = genrePicker.getSelectionModel().getSelectedItem().toString();
            genrePicker.getSelectionModel().clearSelection();
            coverAnc.setStyle(" -fx-background-color: #3d3d42; -fx-background-position: center center; -fx-background-size: cover; -fx-background-repeat: no-repeat; -fx-border-radius: 30px; -fx-background-radius: 30px");
            publish();
            Utils.getInstance().showError("your song is published");
        }


    }

    @FXML
    void AudioPClick(MouseEvent event) {
        File file = fileChooser.showOpenDialog(new Stage());
        if (file != null) {
            audioPath = file.getAbsolutePath();
            System.out.println("File path: " + audioPath);
            String audioName = file.getName();
//            String[] split = audioPath.split("/");
            AudioUpload.setText("The Audio\n " + audioName + " \nhas been uploaded");
        }
    }
    @FXML
    void coverPClick(MouseEvent event) {
        File file = fileChooser.showOpenDialog(new Stage());
        if (file != null) {
            coverPath = file.getAbsolutePath();
            System.out.println(coverPath);
            coverPath = coverPath.replace('\\','/');
            System.out.println(coverPath);
            //this gives error but it can read it , the current format is unknown for it
            coverAnc.setStyle(" -fx-background-image: url(file:///"+coverPath+"); -fx-background-position: center center; -fx-background-size: cover; -fx-background-repeat: no-repeat; -fx-border-radius: 30px; -fx-background-radius: 30px");

        }

    }

}

