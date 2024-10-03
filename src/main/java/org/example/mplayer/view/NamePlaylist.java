package org.example.mplayer.view;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.example.mplayer.controller.ListenerController;
import org.example.mplayer.exceptions.FreeAccountLimitException;


public class NamePlaylist {

    @FXML
    private TextField NameFeild;

    @FXML
    private JFXButton cancel;

    @FXML
    private JFXButton create;

    @FXML
    void cancelClick(ActionEvent event) {
        Cpanes.getInstance().getLibraryC().newStage.close();
    }

    @FXML
    void createClick(ActionEvent event) {
        try {
            ListenerController.getListenerController().createNewPlaylist(NameFeild.getText());
            Cpanes.getInstance().getLibraryC().CreatePlayLists(NameFeild.getText());
        } catch (FreeAccountLimitException e) {
            Utils.getInstance().showError(e.getMessage());
        }
        NameFeild.setText("");
        Cpanes.getInstance().getLibraryC().newStage.close();
    }

}
