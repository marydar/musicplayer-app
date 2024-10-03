package org.example.mplayer.view;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class InfoC {

    @FXML
    private Label content;

    @FXML
    private JFXButton ok;
    public JFXButton getOk() {
        return ok;
    }

    public void setInfo(String content){
        this.content.setText(content);
    }
    @FXML
    void okClick(ActionEvent event) {
        System.out.println("jkhkjh");
    }

}
