package org.example.mplayer.view;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import org.example.mplayer.controller.ListenerController;
import java.net.URL;
import java.util.*;

public class GenreC implements Initializable {
    @FXML
    private AnchorPane country;

    @FXML
    private AnchorPane hiphop;

    @FXML
    private AnchorPane history;

    @FXML
    private AnchorPane interView;

    @FXML
    private AnchorPane jazz;

    @FXML
    private AnchorPane pop;

    @FXML
    private AnchorPane rock;

    @FXML
    private JFXButton save;

    @FXML
    private AnchorPane society;

    @FXML
    private AnchorPane genreAnc;

    @FXML
    private AnchorPane trueCrime;
    boolean isSelected = false;

    private Map<AnchorPane,Integer> map = new HashMap<AnchorPane,Integer>();

    private Set<String> genres = new HashSet<>();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        event(country,"country");
        event(pop,"pop");
        event(hiphop,"hiphop");
        event(history,"history");
        event(trueCrime,"true_Crime");
        event(interView,"interView");
        event(jazz,"jazz");
        event(rock,"rock");
        event(society,"society");

    }
    public void event(AnchorPane a, String genre){
        map.put(a,0);
        if(Cpanes.getInstance().getBorder().isDark()){
            a.setStyle("-fx-background-color:#212123;");
        }
        else{
            a.setStyle("-fx-background-color:#e5e8ed;");
        }
        a.setOnMouseEntered(eo ->{
            a.setStyle("-fx-background-color:black;");
        });
        a.setOnMouseExited(r->{
            if(map.get(a)==0) {
                if(isDark){
                    a.setStyle("-fx-background-color:#212123;");
                }
                else{
                    a.setStyle("-fx-background-color:#e5e8ed;");
                }

            }
            if(map.get(a)==1) {
                a.setStyle("-fx-background-color:" + Cpanes.getInstance().getBorder().getValue() + ";");
            }
        });

        a.setOnMouseClicked(e ->{
                if (genres.add(genre)) {
                    if(genres.size()>4){
                        genres.remove(genre);
                        Utils.getInstance().showError("you can choose at most 4 genres");
                    }
                    else {
                        map.put(a,1);
                        a.setStyle("-fx-background-color:" + Cpanes.getInstance().getBorder().getValue() + ";");
                    }
                }
                else {
                    map.put(a,0);
                    if(isDark){
                        a.setStyle("-fx-background-color:#212123;");
                    }
                    else{
                        a.setStyle("-fx-background-color:#e5e8ed;");
                    }

                    genres.remove(genre);
                }

        });
    }
    private boolean isDark = true;
    public void theme(boolean isDark){
        this.isDark = isDark;
        if(isDark){
            genreAnc.setStyle("-fx-background-color:#212123;");
        }
        else{
            genreAnc.setStyle("-fx-background-color:#e5e8ed;");
        }
    }

    @FXML
    void save(ActionEvent e) {
        System.out.println(genres);
        ListenerController.getListenerController().setFavoriteGenres(genres);
        Cpanes.getInstance().getSignupC().getGenre().close();
        Cpanes.getInstance().getBorder().backHome();
        Utils.getInstance().showError("you've signed up successfully");

    }

}
