package org.example.mplayer.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ReportCC implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void setData(String from, String to) {
        this.fromLbl.setText(from);
        this.ToLbl.setText(to);
    }

    @FXML
    private Label ToLbl;

    @FXML
    private Label fromLbl;

}
