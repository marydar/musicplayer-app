package org.example.mplayer.view;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import org.example.mplayer.controller.AdminController;
import org.example.mplayer.controller.ArtistController;
import org.example.mplayer.controller.ListenerController;
import java.net.URL;
import java.util.ResourceBundle;

public class PersonalC implements Initializable {

    @FXML
    private JFXButton AddBtn;

    @FXML
    private Label addCredit;

    @FXML
    private TextField addCreditField;

    @FXML
    private Line addCreditLine;
    @FXML
    private AnchorPane remDayAnc;
    @FXML
    private Label creditIncome;
    @FXML
    private AnchorPane exDateAnc;

    @FXML
    private Label birth;

    @FXML
    private Label credit;

    @FXML
    private Label email;

    @FXML
    private Label name;

    @FXML
    private Label password;

    @FXML
    private Label phone;

    @FXML
    private Label premiumXp;

    @FXML
    private Label remDays;

    @FXML
    private Label username;

    @FXML
    private Label remDaysT;
    @FXML
    private Label premiumXpT;
    double addAmount = 0;

    @FXML
    private AnchorPane creditAnc;

    @FXML
    void AddClick(ActionEvent event) {
        try {
            addAmount = Double.parseDouble(addCreditField.getText());
            addCreditField.setVisible(false);
            addCreditLine.setVisible(false);
            AddBtn.setVisible(false);
            ListenerController.getListenerController().increaseAccountCredit(addAmount);
            credit.setText(String.valueOf(ListenerController.getListenerController().getListener().getAccountCredit()));
        }catch (NumberFormatException e){
            Utils.getInstance().showError("please enter a valid number");
        }

    }

    @FXML
    void addCredit(MouseEvent event) {
        addCreditField.setVisible(true);
        addCreditLine.setVisible(true);
        AddBtn.setVisible(true);
        addCreditField.setText("");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(Cpanes.getInstance().getBorder().LIsLogin){
            listenerPersonal();
        }
        if(Cpanes.getInstance().getBorder().isAIslogin()){
            System.out.println("isA");
            ArtistPersonal();
        }
        if(Cpanes.getInstance().getBorder().isAdmin){
            System.out.println("isAdmikn");
            AdminPersonal();
        }

    }
    public void listenerPersonal(){
        addCreditField.setVisible(false);
        addCreditLine.setVisible(false);
        AddBtn.setVisible(false);
        username.setText(ListenerController.getListenerController().ShowAccountInfo().get(0));
        name.setText(ListenerController.getListenerController().ShowAccountInfo().get(1));
        email.setText(ListenerController.getListenerController().ShowAccountInfo().get(2));
//        password.setText(ListenerController.getListenerController().ShowAccountInfo().get(3));
        phone.setText(ListenerController.getListenerController().ShowAccountInfo().get(4));
        birth.setText(ListenerController.getListenerController().ShowAccountInfo().get(5));
        credit.setText(ListenerController.getListenerController().ShowAccountInfo().get(6));
        creditIncome.setText("Credit");
        if(ListenerController.getListenerController().ShowAccountInfo().size()>7) {
            premiumXp.setText(ListenerController.getListenerController().ShowAccountInfo().get(7));
            remDays.setText(ListenerController.getListenerController().ShowAccountInfo().get(8));
        }
        else{
            premiumXp.setVisible(false);
            premiumXpT.setVisible(false);
            remDays.setVisible(false);
            remDaysT.setVisible(false);
            exDateAnc.setVisible(false);
            remDayAnc.setVisible(false);
        }
    }
    public void ArtistPersonal(){
        addCreditField.setVisible(false);
        addCreditLine.setVisible(false);
        AddBtn.setVisible(false);
        addCredit.setVisible(false);
        username.setText(ArtistController.getArtistController().ShowAccountInfo().get(0));
        name.setText(ArtistController.getArtistController().ShowAccountInfo().get(1));
        email.setText(ArtistController.getArtistController().ShowAccountInfo().get(2));
//        password.setText(ArtistController.getArtistController().ShowAccountInfo().get(3));
        phone.setText(ArtistController.getArtistController().ShowAccountInfo().get(4));
        birth.setText(ArtistController.getArtistController().ShowAccountInfo().get(5));
        credit.setText(ArtistController.getArtistController().ShowAccountInfo().get(6));
        System.out.println(credit.getText().toString());
        creditIncome.setText("Income");
        premiumXp.setVisible(false);
        premiumXpT.setVisible(false);
        remDays.setVisible(false);
        remDaysT.setVisible(false);
        exDateAnc.setVisible(false);
        remDayAnc.setVisible(false);

    }
    public void AdminPersonal(){
        addCreditField.setVisible(false);
        addCreditLine.setVisible(false);
        AddBtn.setVisible(false);
        addCredit.setVisible(false);
        credit.setVisible(false);
        username.setText(AdminController.getAdminController().ShowAccountInfo().get(0));
        name.setText(AdminController.getAdminController().ShowAccountInfo().get(1));
        email.setText(AdminController.getAdminController().ShowAccountInfo().get(2));
//        password.setText(ArtistController.getArtistController().ShowAccountInfo().get(3));
        phone.setText(AdminController.getAdminController().ShowAccountInfo().get(4));
        birth.setText(AdminController.getAdminController().ShowAccountInfo().get(5));
        creditIncome.setVisible(false);
        creditAnc.setVisible(false);
        premiumXp.setVisible(false);
        premiumXpT.setVisible(false);
        remDays.setVisible(false);
        remDaysT.setVisible(false);
        exDateAnc.setVisible(false);
        remDayAnc.setVisible(false);

    }

}
