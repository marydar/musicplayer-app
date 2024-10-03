package org.example.mplayer.view;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.example.mplayer.controller.ListenerController;

public class GetPremiumC {

    @FXML
    private JFXButton get180;

    @FXML
    private JFXButton get30;

    @FXML
    private JFXButton get60;


    @FXML
    void get180(ActionEvent event) {
        try {
            ListenerController.getListenerController().getPremium(180,14);
            Utils.getInstance().showError("your account got premium");
        }catch (Exception e){
            Utils.getInstance().showError(e.getMessage());
            event.consume();
        }
    }

    @FXML
    void get30(ActionEvent event) {
        try {
            ListenerController.getListenerController().getPremium(30,5);
            Utils.getInstance().showError("your account got premium");
        }catch (Exception e){
            Utils.getInstance().showError(e.getMessage());
            event.consume();
        }
    }

    @FXML
    void get60(ActionEvent event) {
        try {
            ListenerController.getListenerController().getPremium(60,9);
            Utils.getInstance().showError("your account got premium");
        }catch (Exception e){
            Utils.getInstance().showError(e.getMessage());
            event.consume();
        }
    }

}
