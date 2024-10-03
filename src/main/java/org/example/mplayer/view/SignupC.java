package org.example.mplayer.view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.example.mplayer.HelloApplication;
import org.example.mplayer.controller.UserController;
import org.example.mplayer.exceptions.InvalidFormatException;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ResourceBundle;

public class SignupC implements Initializable {

    @FXML
    private JFXComboBox<Integer> DayCombo;

    @FXML
    private JFXComboBox<String> MonthCombo;

    @FXML
    private JFXComboBox<Integer> YearCombo;

    @FXML
    private ToggleGroup type;

    @FXML
    private JFXButton goLogin;
    @FXML
    private JFXButton goHome;
    public JFXButton getGoHome() {
        return goHome;
    }

    @FXML
    private JFXTextArea bio;

    @FXML
    private TextField email;


    @FXML
    private TextField name;

    @FXML
    private TextField number;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @FXML
    private RadioButton L;
    @FXML
    private RadioButton S;
    @FXML
    private RadioButton P;

    @FXML
    private Label bioTitle;

    @FXML
    private JFXButton SignUp;

    public void ClearTheFields(){
        username.setText("");
        name.setText("");
        password.setText("");
        email.setText("");
        number.setText("");
        bio.setText("");
        DayCombo.getSelectionModel().clearSelection();
        MonthCombo.getSelectionModel().clearSelection();
        YearCombo.getSelectionModel().clearSelection();
        DayCombo.setPromptText("Day");
        MonthCombo.setPromptText("Month");
        YearCombo.setPromptText("Year");
    }

    public void signUpClick(ActionEvent event) throws ParseException, InvalidFormatException {
        String date ;
        if(username.getText().equals("")|| password.getText().equals("")||email.getText().equals("")||number.getText().equals("") ||name.getText().equals("")){
            Utils.getInstance().showError("please fill all the fields");
        }
        else if(DayCombo.getSelectionModel().getSelectedItem()==null || MonthCombo.getSelectionModel().getSelectedItem()==null || YearCombo.getSelectionModel().getSelectedItem()==null){
            Utils.getInstance().showError("Please select a valid Date");
        }

        else{
        date  = (DayCombo.getSelectionModel().getSelectedItem().toString())+"/"+MonthCombo.getSelectionModel().getSelectedItem().toString()+"/"+YearCombo.getSelectionModel().getSelectedItem().toString();
        System.out.println(date);
        if(S.isSelected()) {
            try {
                UserController.getUserController().signupNewUser("S",username.getText().toString(),password.getText().toString(),name.getText(),email.getText(),number.getText(),date,bio.getText());
                ClearTheFields();
                Cpanes.getInstance().getBorder().backHome();
                Utils.getInstance().showError("you've signed up successfully\n log in to use more features");
            }
            catch (InvalidFormatException e) {
                Utils.getInstance().showError(e.getMessage());
            }
        }
        else if(P.isSelected()) {
            try {
                UserController.getUserController().signupNewUser("P",username.getText().toString(),password.getText().toString(),name.getText(),email.getText(),number.getText(),date,bio.getText());
                ClearTheFields();
                Cpanes.getInstance().getBorder().backHome();
                Utils.getInstance().showError("you've signed up successfully\n log in to use more features");
            }
            catch (InvalidFormatException e) {
                Utils.getInstance().showError(e.getMessage());
            }
        }
        else if(L.isSelected()) {
            try {
                UserController.getUserController().signupNewUser("L",username.getText().toString(),password.getText().toString(),name.getText(),email.getText(),number.getText(),date,bio.getText());
                ClearTheFields();
                genreStage();
            }
            catch (InvalidFormatException e) {
                Utils.getInstance().showError(e.getMessage());
            }
        }
        }
    }

    public Stage getGenre() {return genre;}

    private Stage genre;
    private Scene genreScene = new Scene(new Pane());
    public void genreStage(){
        FXMLLoader fx = new FXMLLoader(HelloApplication.class.getResource("fxmls/genrePick.fxml"));
        try {
            genre = new Stage();
            genreScene.setRoot(fx.load());
            ((GenreC)fx.getController()).theme(Cpanes.getInstance().getBorder().isDark());
            genre.setScene(genreScene);
            genre.initStyle(StageStyle.TRANSPARENT);
            genre.show();

        }catch (IOException e1) {

        }
    }
    public void goHomeClick(ActionEvent event) {
        ClearTheFields();
        Cpanes.getInstance().getBorder().backHome();
    }
    public void goLoginClick(ActionEvent event) {
        ClearTheFields();
        Cpanes.getInstance().getBorder().goLogin();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String path = Paths.get("src/main/resources/org/example/mplayer/images/backW.png").toAbsolutePath().toString();
        Image image = new Image("file:" + path);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(25);
        imageView.setFitWidth(25);
        imageView.setPreserveRatio(true);
        goHome.setGraphic(imageView);
        L.fire();
        bio.setVisible(false);
        bioTitle.setVisible(false);
        P.setOnAction(e ->{
            P.fire();
            bio.setVisible(true);
            bioTitle.setVisible(true);


        });

        S.setOnAction(e ->{
            S.fire();
            bio.setVisible(true);
            bioTitle.setVisible(true);


        });
        L.setOnAction(e ->{
            L.fire();
            bio.setVisible(false);
            bioTitle.setVisible(false);


        });
        for (int i = 1; i <=31 ; i++) {
            DayCombo.getItems().add(i);
        }
        for (int i = 1950; i <=2024 ; i++) {
            YearCombo.getItems().add(i);
        }
        MonthCombo.getItems().addAll("Jan","Feb","Mar","Apr","May", "Jun","Jul","Aug","Sep","Oct","Nov","Dec");
    }
}
