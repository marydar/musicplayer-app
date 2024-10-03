package org.example.mplayer.view;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Error {
    @FXML
    private JFXButton OK;
    @FXML
    private Label message;
    public void setMessage(String message) {
        this.message.setText(message);
    }

    public String getMessage() {
        return message.getText();
    }

    public void OKClick(ActionEvent event) {
        Utils.getInstance().getErrorStage().close();
    }

}
