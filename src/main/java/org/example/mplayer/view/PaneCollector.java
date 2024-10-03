package org.example.mplayer.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import org.example.mplayer.HelloApplication;
import java.io.IOException;

public class PaneCollector {
    private static PaneCollector paneCollector = new PaneCollector();
    public static PaneCollector getInstance() {
        return paneCollector;
    }
    public void setPanes(){
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("fxmls/library.fxml"));
        try {
            Cpanes.getInstance().setLibPane(loader.load());
            Cpanes.getInstance().setLibraryC(loader.getController());

        }catch (IOException e){
            e.printStackTrace();
        }
        FXMLLoader loaderS = new FXMLLoader(HelloApplication.class.getResource("fxmls/home.fxml"));
        try {
            Cpanes.getInstance().setHome(loaderS.load());
            Cpanes.getInstance().setHomeC(loaderS.getController());

        }catch (IOException e){
            e.printStackTrace();
        }
        Pane pane = new Pane();
        pane.setPrefSize(1100, 650);
        pane.getChildren().add(Cpanes.getInstance().getHome());
        Cpanes.getInstance().getThCPane().setCenter(pane);
        FXMLLoader loaderF = new FXMLLoader(HelloApplication.class.getResource("fxmls/artistInfo.fxml"));
        try {
            Cpanes.getInstance().setArtistInfo(loaderF.load());
            Cpanes.getInstance().setArF(loaderF.getController());

        }catch (IOException e){
            e.printStackTrace();
        }
        FXMLLoader loaderB = new FXMLLoader(HelloApplication.class.getResource("fxmls/browse.fxml"));
        try {
            Cpanes.getInstance().setBrowse(loaderB.load());
            Cpanes.getInstance().setBrowseC(loaderB.getController());

        }catch (IOException e){
            e.printStackTrace();
        }
        FXMLLoader loadernew = new FXMLLoader(HelloApplication.class.getResource("fxmls/playListChoose.fxml"));
        try {
            Cpanes.getInstance().setPlaylist(loadernew.load());
            Cpanes.getInstance().setPlaylistChoose(loadernew.getController());

        }catch (IOException e){
            e.printStackTrace();
        }
        FXMLLoader loaderCr = new FXMLLoader(HelloApplication.class.getResource("fxmls/namePlaylist.fxml"));
        try {
            Cpanes.getInstance().setCreatePlaylist(loaderCr.load());
            Cpanes.getInstance().setNamePlaylist(loaderCr.getController());

        }catch (IOException e){
            e.printStackTrace();
        }
        FXMLLoader loaderP = new FXMLLoader(HelloApplication.class.getResource("fxmls/listOfPlayList.fxml"));
        try {
            Cpanes.getInstance().setListOfPl(loaderP.load());
            Cpanes.getInstance().setListOfPlayListC(loaderP.getController());

        }catch (IOException e){
            e.printStackTrace();
        }
        FXMLLoader loaderSi = new FXMLLoader(HelloApplication.class.getResource("fxmls/signUp.fxml"));
        try {
            Cpanes.getInstance().setSignUp(loaderSi.load());
            Cpanes.getInstance().setSignupC(loaderSi.getController());

        }catch (IOException e){
            e.printStackTrace();
        }
        FXMLLoader loaderlg = new FXMLLoader(HelloApplication.class.getResource("fxmls/login.fxml"));
        try {
            Cpanes.getInstance().setLogin(loaderlg.load());
            Cpanes.getInstance().setLoginC(loaderlg.getController());

        }catch (IOException e){
            e.printStackTrace();
        }
        FXMLLoader error = new FXMLLoader(HelloApplication.class.getResource("fxmls/Error.fxml"));
        try {
            Cpanes.getInstance().setError(error.load());
            Cpanes.getInstance().setErrorC(error.getController());

        }catch (IOException e){
            e.printStackTrace();
        }
        FXMLLoader info = new FXMLLoader(HelloApplication.class.getResource("fxmls/info.fxml"));
        try {
            Cpanes.getInstance().setInfo(info.load());
            Cpanes.getInstance().setInfoC(info.getController());

        }catch (IOException e){
            e.printStackTrace();
        }
    }

}