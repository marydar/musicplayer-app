package org.example.mplayer.view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.example.mplayer.controller.ListenerController;
public class ReportC {

    @FXML
    private JFXButton cancel;

    @FXML
    private JFXButton create;

    @FXML
    private JFXTextArea expArea;

    @FXML
    void cancelClick(ActionEvent event) {
        Cpanes.getInstance().getLibraryC().newStage.close();
    }

    @FXML
    void createClick(ActionEvent event) {
        ListenerController.getListenerController().reportArtist(Cpanes.getInstance().getArF().getArtist().getUsername(), expArea.getText());
        expArea.setText("");
        Cpanes.getInstance().getLibraryC().newStage.close();
        Utils.getInstance().showError("we received your feedback");
    }

}
