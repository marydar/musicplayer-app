package org.example.mplayer.view;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import org.example.mplayer.controller.ArtistController;
import org.example.mplayer.controller.ListenerController;
import org.example.mplayer.controller.UserController;
import org.example.mplayer.model.user.Admin;

import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;

public class LoginC implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String path = Paths.get("src/main/resources/org/example/mplayer/images/backW.png").toAbsolutePath().toString();
        Image image = new Image("file:" + path);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(25);
        imageView.setFitWidth(25);
        imageView.setPreserveRatio(true);
        goHome.setGraphic(imageView);

    }

    @FXML
    private JFXButton goHome;
    public JFXButton getGoHome() {
        return goHome;
    }

    @FXML
    private AnchorPane loginAnc;
    public AnchorPane getLoginAnc() {
        return loginAnc;
    }
    @FXML
    private JFXButton goSignup;

    @FXML
    private JFXButton login;

    @FXML
    private TextField userName;

    @FXML
    private PasswordField password;

    @FXML
    void goHomeClick(ActionEvent event) {
        userName.setText("");
        password.setText("");
        Cpanes.getInstance().getBorder().backHome();
    }

    @FXML
    void goSignUpClick(ActionEvent event) {
        userName.setText("");
        password.setText("");
        Cpanes.getInstance().getBorder().goSignUp();
    }

    @FXML
    void loginClick(ActionEvent event) {
        try {
            String key = UserController.getUserController().loginUser(userName.getText(), password.getText());
            Cpanes.getInstance().getBorder().backHome();
            Cpanes.getInstance().getBorder().getLogIn().setText("LogOut");
            Cpanes.getInstance().getBorder().getUserLbl().setVisible(true);
            Cpanes.getInstance().getBorder().getUserIcon().setVisible(true);
            if(key.equals("L")) {
                Cpanes.getInstance().getBorder().setUserName(ListenerController.getListenerController().getListener().getUsername());
                Cpanes.getInstance().getBorder().LIsLogin = true;
                Cpanes.getInstance().getLibraryC().firstGridInLib();
                Cpanes.getInstance().getHomeC().suggestion();
            }
            if(key.equals("A")) {
                Cpanes.getInstance().getBorder().setAIslogin(true);
                Cpanes.getInstance().getBorder().setUserName(ArtistController.getArtist().getUsername());
//                Cpanes.getInstance().getBorder().LIsLogin = false;
//                Cpanes.getInstance().getBorder().isAIslogin() = true;
                Cpanes.getInstance().getLibraryC().firstGridInLib();

            }
            if(key.equals("Admin")) {
                System.out.println("Admin");
                Cpanes.getInstance().getBorder().isAdmin = true;
                Cpanes.getInstance().getBorder().LIsLogin = false;
                Cpanes.getInstance().getBorder().setAIslogin(false);
                Cpanes.getInstance().getBorder().setUserName(Admin.getAdmin().getUsername());
                Cpanes.getInstance().getLibraryC().firstGridInLib();
            }
            userName.setText("");
            password.setText("");
            Cpanes.getInstance().getHomeC().firstRowInhome();
        }catch (Exception e){
            Utils.getInstance().showError(e.getMessage());
            event.consume();
        }

    }


}
