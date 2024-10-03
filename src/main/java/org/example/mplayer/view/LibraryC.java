package org.example.mplayer.view;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Set;

import javafx.util.Duration;
import org.example.mplayer.HelloApplication;
import org.example.mplayer.controller.AdminController;
import org.example.mplayer.controller.ArtistController;
import org.example.mplayer.controller.ListenerController;
import org.example.mplayer.exceptions.FreeAccountLimitException;
import org.example.mplayer.model.audio.Audio;
import org.example.mplayer.model.audio.Music;
import org.example.mplayer.model.audio.Podcast;
import org.example.mplayer.model.database.Database;
import org.example.mplayer.model.user.Artist;
import org.example.mplayer.model.audio.PlayList;
import org.example.mplayer.model.user.Podcaster;
import org.example.mplayer.model.user.Singer;
import org.example.mplayer.model.user.User;
import org.example.mplayer.model.user.Report;

public class LibraryC implements Initializable {
    private ArrayList<Audio> audios = new ArrayList<>();
    private ArrayList<String> Ends = new ArrayList<>();
    private String previousScene;
    private String currentScene;

    public Pane getLibPane() {
        return libPane;
    }

    @FXML
    private Pane libPane;

    public JFXButton getFavorites() {
        return Favorites;
    }

    public JFXButton getArtists() {
        return Artists;
    }

    public JFXButton getCurBtn() {
        return CurBtn;
    }

    public JFXButton getPreBtn() {
        return preBtn;
    }
    public void setCurBtn(JFXButton curBtn) {
        this.CurBtn = curBtn;
    }

    public void setPreBtn(JFXButton preBtn) {
        this.preBtn = preBtn;
    }

    public JFXButton getGetPremuim() {
        return GetPremuim;
    }

    public JFXButton getPersonal() {
        return personal;
    }

    public JFXButton getPlaylist() {
        return playlist;
    }

    public GridPane getPlayGrid() {
        return playGrid;
    }

    public GridPane getSongs() {
        return songs;
    }
    public GridPane getArtistsPane() {
        return artists;
    }

    @FXML
    private JFXButton Artists;
    @FXML
    private JFXButton Favorites;
    private GridPane songs = new GridPane();
    private  GridPane artists = new GridPane();
    private int rowA=0;
    private int colA=0;
    private int row = 0;
    private int coulomn = 0;
    private AnchorPane previous;
    private AnchorPane current;
    public static String BW = "white";
    private boolean isFavFirst=true;
    @FXML
    private JFXButton GetPremuim;
    @FXML
    private JFXButton personal;
    private JFXButton preBtn =new JFXButton();
    private JFXButton CurBtn = new JFXButton();

    public String getLast() {return last;}

    private String last = "favorite";
    @FXML
    private JFXButton playlist;
    private GridPane playGrid = new GridPane();
    private int rowP=0;
    private int colP=1;

    @FXML
    void playListClick(ActionEvent event) {
        if(Cpanes.getInstance().getBorder().getScenes().peek().equals("playlist")==false) {
            last = "playlist";
            Cpanes.getInstance().getBorder().getScenes().add("playlist");
            if(CurBtn.getId()==null){
                CurBtn = Artists;
            }
            preBtn=CurBtn;
            CurBtn=playlist;
            System.out.println(preBtn.getId());
            System.out.println(CurBtn.getId());
            inPane(false);
            if(Cpanes.getInstance().getBorder().LIsLogin) {
                libPane.getChildren().clear();
                showPlaylists();
                libPane.getChildren().add(playGrid);
                Utils.getInstance().translateTransition(-100,0,playGrid,300);
            }
            else if (Cpanes.getInstance().getBorder().isAIslogin()){
                libPane.getChildren().clear();
                BarChart<String,Number> barChart = chart.drawBar(ArtistController.getArtist());
//                LineChart<String,Number> lineChart = chart.draw(ArtistController.getArtist());
                libPane.getChildren().add(barChart);
                Utils.getInstance().translateTransition(-100,0,barChart,300);
            }
            else if (Cpanes.getInstance().getBorder().isAdmin){
                libPane.getChildren().clear();
                BarChart<String,Number> barChart = chart.drawAll();
                libPane.getChildren().add(barChart);
                Utils.getInstance().translateTransition(-100,0,barChart,300);
            }
        }

    }


    public void inPane(boolean reverse){
        if(reverse){
            CurBtn.setStyle("-fx-text-fill: -fx-BW;");
            preBtn.setStyle("-fx-text-fill: -fx-third;");
        }
        else{
            preBtn.setStyle("-fx-text-fill: -fx-BW;");
            CurBtn.setStyle("-fx-text-fill: -fx-third;");
        }
    }

    public void firstGridInLib(){
        if(Cpanes.getInstance().getBorder().getScenes().peek().equals("favorite")==false){
            last = "favorite";
            System.out.println("hyyy");
            preBtn=CurBtn;
            CurBtn=Artists;
            System.out.println(preBtn.getId());
            System.out.println(CurBtn.getId());
            inPane(false);
            libPane.getChildren().clear();
            if(Cpanes.getInstance().getBorder().LIsLogin) {
                Artists.setText("Favorites");
                Favorites.setText("Followings");
                playlist.setText("Playlists");
                GetPremuim.setText("Get Premium");
                GetPremuim.setVisible(true);
                showSavedFavs();
            }
            else if(Cpanes.getInstance().getBorder().isAIslogin()){
                Artists.setText("MySongs");
                Favorites.setText("Followers");
                playlist.setVisible(true);
                playlist.setText("Statistics");
                GetPremuim.setText("Publish Audio");
                GetPremuim.setVisible(true);
                showArtistSongs();
            }
            else if(Cpanes.getInstance().getBorder().isAdmin){
                Artists.setText("All songs");
                Favorites.setText("All Artists");
                playlist.setVisible(true);
                playlist.setText("Statistics");
                GetPremuim.setText("All reports");
                GetPremuim.setVisible(true);
                showAllSongs();
            }
            libPane.getChildren().add(songs);
        }
    }
    @FXML
    public void ArtClick(ActionEvent event) {
        //favorite
        firstGridInLib();
        Utils.getInstance().translateTransition(-100,0,songs,300);
        Cpanes.getInstance().getBorder().getScenes().add("favorite");

    }
    private void GridEvent() {

        int rowCount = songs.getRowCount();

        for (int rowIndex = 0; rowIndex < rowCount; rowIndex++) {
            Node childNode = songs.getChildren().get(rowIndex);
            AnchorPane rowAnchorPane = (AnchorPane) childNode;

            final int targRow = rowIndex;
            rowAnchorPane.setOnMouseClicked(event ->{
                System.out.println("set");

                Utils.getInstance().nextColor((AnchorPane) rowAnchorPane.getChildren().get(0),targRow,"favorites");
                    Cpanes.getInstance().getBorder().setAudioha(ListenerController.getListenerController().getListener().getFavorites());
                    Cpanes.getInstance().getBorder().setIndex(targRow);


            });
        }
        if(Utils.getInstance().getPlayList().equals("favorites")) {
            Node childNode0 = songs.getChildren().get(Utils.getInstance().getIndex());
            AnchorPane rowAnchorPane0 = (AnchorPane) childNode0;
            Utils.getInstance().nextColor((AnchorPane) rowAnchorPane0.getChildren().get(0),Utils.getInstance().getIndex(), "favorites");
        }
    }
    private void showPremium()
    {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("fxmls/getPremium.fxml"));
        try{
            AnchorPane Bpane = fxmlLoader.load();
            libPane.getChildren().remove(0);
            libPane.getChildren().add(Bpane);
            Utils.getInstance().translateTransition(-100,0,Bpane,300);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    private void showPublish()
    {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("fxmls/publishAudio.fxml"));
        try{
            AnchorPane Bpane = fxmlLoader.load();
            libPane.getChildren().remove(0);
            libPane.getChildren().add(Bpane);
            Utils.getInstance().translateTransition(-100,0,Bpane,300);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    @FXML
    void PremuimClick(ActionEvent event) {
        if(Cpanes.getInstance().getBorder().getScenes().peek().equals("premium")==false) {
            last = "premium";
            Cpanes.getInstance().getBorder().getScenes().add("premium");
            previousScene = currentScene;
            currentScene = "premium";
            if (CurBtn.getId() == null) {
                CurBtn = Artists;
            }
            preBtn = CurBtn;
            CurBtn = GetPremuim;
            System.out.println(preBtn.getId());
            System.out.println(CurBtn.getId());
            inPane(false);
            if(Cpanes.getInstance().getBorder().LIsLogin){
                showPremium();
                GetPremuim.setText("Get Premium");
            }
            else if(Cpanes.getInstance().getBorder().isAIslogin()){
                showPublish();
                GetPremuim.setText("Publish Audio");
            }
            else if(Cpanes.getInstance().getBorder().isAdmin){
                showReports();
                GetPremuim.setText("All reports");
            }

        }


    }
    public void showReports(){
        songs.getChildren().clear();
        row = 0;
        for(Report r : Database.getDatabase().getAllReports()){
            addReport(r);
        }
        libPane.getChildren().clear();
        libPane.getChildren().add(songs);
    }
    private void showPersonalInfo(){
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("fxmls/personal.fxml"));
        try {
            AnchorPane Bpane = fxmlLoader.load();
            libPane.getChildren().remove(0);
            libPane.getChildren().add(Bpane);
            Utils.getInstance().translateTransition(-100,0,Bpane,300);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void personalClick(ActionEvent event) {
        if(Cpanes.getInstance().getBorder().getScenes().peek().equals("personal")==false) {
            last = "personal";
            Cpanes.getInstance().getBorder().getScenes().add("personal");
            previousScene = currentScene;
            currentScene = "personal";
            if (CurBtn.getId() == null) {
                CurBtn = Artists;
            }
            preBtn = CurBtn;
            CurBtn = personal;
            System.out.println(preBtn.getId());
            System.out.println(CurBtn.getId());
            inPane(false);
            showPersonalInfo();

        }
    }
    @FXML
    public void FavClick(ActionEvent event) {
        if(Cpanes.getInstance().getBorder().getScenes().peek().equals("artist")==false) {
            last = "artist";
            Cpanes.getInstance().getBorder().getScenes().add("artist");
            previousScene = currentScene;
            currentScene = "Favorites";
            if (CurBtn.getId() == null) {
                CurBtn = Artists;
            }
            preBtn = CurBtn;
            CurBtn = Favorites;
            System.out.println(preBtn.getId());
            System.out.println(CurBtn.getId());
            inPane(false);

            if (isFavFirst) {
                isFavFirst = false;
            }

            libPane.getChildren().clear();
            if(Cpanes.getInstance().getBorder().LIsLogin) {
                Favorites.setText("Followings");
                showFollowedArtists();
            }
            else if(Cpanes.getInstance().getBorder().isAIslogin()){
                Favorites.setText("Followers");
                showFollowers();
            }
            else if(Cpanes.getInstance().getBorder().isAdmin){
                Favorites.setText("All Artists");
                showArtists();
            }
            libPane.getChildren().add(artists);
            Utils.getInstance().translateTransition(-100,0,artists,300);
        }
    }
    @FXML
    private Label FavEmptyLbl;
    @FXML
    private Label ArtEmptyLbl;


    public void showSavedFavs(){
        songs.getChildren().clear();
        row = 0;
        if(ListenerController.getListenerController().getListener().getFavorites().isEmpty()){
            libPane.getChildren().clear();
            libPane.getChildren().add(FavEmptyLbl);
            FavEmptyLbl.setVisible(true);
        }
        else{
            FavEmptyLbl.setVisible(false);
        }
        for(Audio a : ListenerController.getListenerController().getListener().getFavorites()){
            addToFavs(a);
        }
        if(Utils.getInstance().getPlayList().equals("favorites")) {
            Node childNode0 = songs.getChildren().get(Utils.getInstance().getIndex());
            AnchorPane rowAnchorPane0 = (AnchorPane) childNode0;
            Utils.getInstance().nextColor((AnchorPane) rowAnchorPane0.getChildren().get(0),Utils.getInstance().getIndex(), "favorites");
        }
    }
    public void showArtistSongs(){
        songs.getChildren().clear();
        row = 0;
        ArrayList<Audio> songsArr = new ArrayList<>();
        if(ArtistController.getArtist() instanceof Singer){
            for(Music m: ((Singer) ArtistController.getArtist()).getMusicList()){
                songsArr.add(m);
            }

        }
        else if(ArtistController.getArtist() instanceof Podcaster){
            for(Podcast p: ((Podcaster) ArtistController.getArtist()).getPodcastList()){
                songsArr.add(p);
            }
        }
        if(songsArr.isEmpty()){
            libPane.getChildren().clear();
            libPane.getChildren().add(FavEmptyLbl);
            FavEmptyLbl.setVisible(true);
        }
        else{
            FavEmptyLbl.setVisible(false);
        }

        for(Audio a : songsArr){
            addToFavs(a);
        }
        if(Utils.getInstance().getPlayList().equals("favorites")) {
            Node childNode0 = songs.getChildren().get(Utils.getInstance().getIndex());
            AnchorPane rowAnchorPane0 = (AnchorPane) childNode0;
            Utils.getInstance().nextColor((AnchorPane) rowAnchorPane0.getChildren().get(0),Utils.getInstance().getIndex(), "favorites");
        }
    }
    public void showAllSongs(){
        songs.getChildren().clear();
        row = 0;
        for(Audio a : AdminController.getAdminController().popularAudios()){
            addToAllSongs(a);
        }
    }
    public void shuffle(ArrayList<Audio> list){
        Cpanes.getInstance().getBorder().setPlayScene("library");
        songs.getChildren().clear();
        row = 0;
        for(Audio a : list){
            shufAdd(a);
        }
        if(Utils.getInstance().getPlayList().equals("favorites")) {
            Node childNode0 = songs.getChildren().get(Utils.getInstance().getIndex());
            AnchorPane rowAnchorPane0 = (AnchorPane) childNode0;
            Utils.getInstance().nextColor((AnchorPane) rowAnchorPane0.getChildren().get(0),Utils.getInstance().getIndex(), "favorites");
        }
    }
    public void unShuffle(){
        System.out.println("in library unshyf");
        songs.getChildren().clear();
        row = 0;
        if(Cpanes.getInstance().getBorder().LIsLogin){
            System.out.println("lis log");
            for(Audio a : ListenerController.getListenerController().getListener().getFavorites()){
                addToFavs(a);
            }
            System.out.println(ListenerController.getListenerController().getListener().getFavorites());
            Cpanes.getInstance().getBorder().setAudioha(ListenerController.getListenerController().getListener().getFavorites());
        }
        else if(Cpanes.getInstance().getBorder().isAIslogin()){
            ArrayList<Audio> songsArr = new ArrayList<>();
            if(ArtistController.getArtist() instanceof Singer){
                for(Music m: ((Singer) ArtistController.getArtist()).getMusicList()){
                    songsArr.add(m);
                }

            }
            else if(ArtistController.getArtist() instanceof Podcaster){
                for(Podcast p: ((Podcaster) ArtistController.getArtist()).getPodcastList()){
                    songsArr.add(p);
                }
            }
            for(Audio a : songsArr){
                addToFavs(a);
            }
            Cpanes.getInstance().getBorder().setAudioha(songsArr);
        }
        if(Utils.getInstance().getPlayList().equals("favorites")) {
            Node childNode0 = songs.getChildren().get(Utils.getInstance().getIndex());
            AnchorPane rowAnchorPane0 = (AnchorPane) childNode0;
            Utils.getInstance().nextColor((AnchorPane) rowAnchorPane0.getChildren().get(0),Utils.getInstance().getIndex(), "favorites");
        }
    }
    public void showFollowedArtists(){
        artists.getChildren().clear();
        rowA=0;
        colA=0;
        if(ListenerController.getListenerController().getListener().getFollowings().isEmpty()){
            libPane.getChildren().clear();
            libPane.getChildren().add(ArtEmptyLbl);
            ArtEmptyLbl.setVisible(true);
        }
        else{
            ArtEmptyLbl.setVisible(false);
        }
        for(Artist a : ListenerController.getListenerController().getListener().getFollowings()){
            addToArtists(a.getUsername());
        }
    }
    public void showArtists(){
        artists.getChildren().clear();
        rowA=0;
        colA=0;
        ArtEmptyLbl.setVisible(false);
        for(Artist a : AdminController.getAdminController().showArtistsAll()){
            addToAllArtist(a.getUsername());
        }
    }
    public void showFollowers(){
        artists.getChildren().clear();
        rowA=0;
        colA=0;
        if(ArtistController.getArtist().getFollowers().isEmpty()){
            libPane.getChildren().clear();
            libPane.getChildren().add(ArtEmptyLbl);
            ArtEmptyLbl.setVisible(true);
        }
        else{
            ArtEmptyLbl.setVisible(false);
        }

        for(User a : ArtistController.getArtist().getFollowers()){
            addFollowers(a.getUsername());
        }
    }
    public void showPlaylists(){
        playGrid.getChildren().clear();
        CreateBox();
        rowP=0;
        colP=1;
        for(PlayList p : ListenerController.getListenerController().getListener().getListOfPlayLists()){
            CreatePlayLists(p.getPlayListName());
        }
    }

    public void nextColor(int index) {
        System.out.println(index + "inHOme");

        AnchorPane rowAnchorPane = (AnchorPane) songs.getChildren().get(index);
        AnchorPane tmp = (AnchorPane) rowAnchorPane.getChildren().get(0);
        Utils.getInstance().nextColor(tmp,index,"favorites");
    }
    public void shufAdd(Audio  audio){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("fxmls/song.fxml"));
            AnchorPane Apane = fxmlLoader.load();
            Song song = (Song) fxmlLoader.getController();
            song.setData(audio.getAudioName(), audio.getArtistUsername(), "0:00",audio.getCover());
            Utils.getInstance().setEndTime(audio,song);
            songs.add(Apane, 0, row);
            row++;
            if (row > 6) {
                songs.setPrefHeight(songs.getPrefHeight() + 90);
                libPane.setPrefHeight(libPane.getPrefHeight() + 60);
            }
            final int trow = row-1;
            Apane.setOnMouseClicked(event ->{
                System.out.println("set");


                Utils.getInstance().nextColor((AnchorPane) Apane.getChildren().get(0),trow,"favorites");


            });

        }
        catch (IOException e){
            e.printStackTrace();
        }

    }

    public void addToFavs(Audio audio){
        FavEmptyLbl.setVisible(false);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("fxmls/song.fxml"));
            AnchorPane Apane = fxmlLoader.load();
            Song song = (Song) fxmlLoader.getController();
            song.setData(audio.getAudioName(), audio.getArtistUsername(), "0:00",audio.getCover());
            Utils.getInstance().setEndTime(audio,song);
            songs.add(Apane, 0, row);
            row++;
            if (row > 6) {
                songs.setPrefHeight(songs.getPrefHeight() + 90);
                libPane.setPrefHeight(libPane.getPrefHeight() + 60);
            }
            final int trow = row-1;
            Apane.setOnMouseClicked(event ->{
                System.out.println("set");


                Utils.getInstance().nextColor((AnchorPane) Apane.getChildren().get(0),trow,"favorites");
                if(Cpanes.getInstance().getBorder().LIsLogin) {
                    Cpanes.getInstance().getBorder().setAudioha(ListenerController.getListenerController().getListener().getFavorites());
                }
                else if(Cpanes.getInstance().getBorder().isAIslogin()){
                    ArrayList<Audio> songsArr = new ArrayList<>();
                    if(ArtistController.getArtist() instanceof Singer){
                        for(Music m: ((Singer) ArtistController.getArtist()).getMusicList()){
                            songsArr.add(m);
                        }

                    }
                    else if(ArtistController.getArtist() instanceof Podcaster){
                        for(Podcast p: ((Podcaster) ArtistController.getArtist()).getPodcastList()){
                            songsArr.add(p);
                        }
                    }
                    Cpanes.getInstance().getBorder().setAudioha(songsArr);
                }
                    Cpanes.getInstance().getBorder().setIndex(trow);
                    Cpanes.getInstance().getBorder().setPlayScene("library");

            });

        }
        catch (IOException e){
            e.printStackTrace();
        }


    }
    public void addReport(Report report){
        FavEmptyLbl.setVisible(false);

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("fxmls/report.fxml"));
            AnchorPane Apane = fxmlLoader.load();
            ReportCC reportC = (ReportCC) fxmlLoader.getController();
            reportC.setData("From : "+report.getReportingUser().getUsername(),"To : "+report.getReportedArtist().getUsername());
            songs.add(Apane, 0, row);
            row++;
            if (row > 6) {
                songs.setPrefHeight(songs.getPrefHeight() + 90);
                libPane.setPrefHeight(libPane.getPrefHeight() + 60);
            }
            final int trow = row-1;
            Apane.setOnMouseClicked(event ->{
                System.out.println("set");


                Cpanes.getInstance().getBorder().save("Report\nFrom : "+report.getReportingUser().getUsername()+"\nTo : "+report.getReportedArtist().getUsername()+"\nDescription : "+report.getDescription());

            });

        }
        catch (IOException e){
            e.printStackTrace();
        }


    }
    public void addToAllSongs(Audio audio){
        FavEmptyLbl.setVisible(false);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("fxmls/song.fxml"));
            AnchorPane Apane = fxmlLoader.load();
            Song song = (Song) fxmlLoader.getController();
            song.setData(audio.getAudioName(), audio.getArtistUsername(), "0:00",audio.getCover());
            Utils.getInstance().setEndTime(audio,song);
            songs.add(Apane, 0, row);
            row++;
            if (row > 6) {
                songs.setPrefHeight(songs.getPrefHeight() + 90);
                libPane.setPrefHeight(libPane.getPrefHeight() + 60);
            }
            final int trow = row-1;
            Apane.setOnMouseClicked(event ->{
                System.out.println("set");

                Cpanes.getInstance().getBorder().save("Title : "+audio.getAudioName()+"\nArtist : "+audio.getArtistUsername()+"\nNumberOfLikes : "+audio.getNumberOfLikes()+"\nNumberOfPLays : "+audio.getNumberOfPlays()+"\nGenre : "+audio.getGenre()+"\nRelease date : "+audio.getReleaseDate());

            });

        }
        catch (IOException e){
            e.printStackTrace();
        }


    }
public void CreatePlayLists(String name){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("fxmls/playList.fxml"));
            AnchorPane Apane = fxmlLoader.load();
            PlayListC pl = (PlayListC) fxmlLoader.getController();
            String path = Paths.get("src/main/resources/org/example/mplayer/images/playlist.jpg").toAbsolutePath().toString();
            Image image = new Image("file:" + path);
            pl.setData(name,image);

            if(colP>2){
                colP=0;
                rowP++;
            }
            playGrid.add(Apane, colP, rowP);
            colP++;
            playGrid.setPrefHeight(playGrid.getPrefHeight() + 100);
            libPane.setPrefHeight(libPane.getPrefHeight() + 100);

            final int targRow = rowP;
            final int targCol = colP-1;
            System.out.println("before");
            Apane.setOnMouseClicked(e -> {
                System.out.println("row: " + targRow + " col: " + targCol);
                Pane pane = new Pane();
                pane.setPrefSize(1100, 650);
                pane.getChildren().add(Cpanes.getInstance().getListOfPl());
                Cpanes.getInstance().getBorder().last = "ListOfPlaylist";
                Cpanes.getInstance().getListOfPlayListC().setData(ListenerController.getListenerController().getListener().getListOfPlayLists().get((targRow*3) + targCol -1));
                Cpanes.getInstance().getThCPane().setCenter(pane);

            });

        }
        catch (IOException e){
            e.printStackTrace();
        }


    }
    Stage newStage;
    Scene scene2 = new Scene(new Pane(),200,100);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Artists.setStyle("-fx-text-fill: -fx-third;");
        libPane.getChildren().add(songs);
        GridEvent();


    }
    public void CreateBox(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("fxmls/playList.fxml"));
            AnchorPane Apane = fxmlLoader.load();
            PlayListC pl = (PlayListC) fxmlLoader.getController();
            String path = Paths.get("src/main/resources/org/example/mplayer/images/plus.png").toAbsolutePath().toString();
            Image image = new Image("file:" + path);
            pl.setData("create new",image);
            playGrid.add(Apane, 0, 0);
//            if(rowP>1) {
//                playGrid.setPrefHeight(playGrid.getPrefHeight() + 100);
//                libPane.setPrefHeight(libPane.getPrefHeight() + 100);
//            }
            Apane.setOnMouseClicked(e -> {
                newStage = new Stage();
                newStage.initModality(Modality.APPLICATION_MODAL);//another way????
                scene2.setRoot(Cpanes.getInstance().getCreateNewPl());
                newStage.setScene(scene2);
                newStage.setTitle("create");
                newStage.setX(371);
                newStage.setY(290);
                newStage.initStyle(StageStyle.TRANSPARENT);
                newStage.show();
                e.consume();

            });

        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    public void addNewSong(String name, String artist, String time) {
        row++; // Increment column for next user
    }

    public void AddToPlaylist(Audio audio, Set<String> list){
        for(String s : list){
            try {
                ListenerController.getListenerController().addAudioToPlaylist(s,audio.getId());
            } catch (FreeAccountLimitException e) {
                Utils.getInstance().showError(e.getMessage());
            }
        }
    }
    public void addFollowers(String name){
        ArtEmptyLbl.setVisible(false);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("fxmls/Artist.fxml"));
            AnchorPane Bpane = fxmlLoader.load();
            ArtistC artist = (ArtistC) fxmlLoader.getController();
            String path = Paths.get("src/main/resources/org/example/mplayer/images/user.jpg").toAbsolutePath().toString();
            Image image = new Image("file:" + path);
            artist.setData(name, image);
            if (colA > 2) {
                colA = 0;
                rowA++;
            }
            artists.add(Bpane, colA, rowA);
            colA++;
            if (rowA > 1) {
                artists.setPrefHeight(artists.getPrefHeight() + 100);
                libPane.setPrefHeight(libPane.getPrefHeight() + 100);
            }
        }catch (IOException o){

        }
    }

    public void addToArtists(String name) {
        ArtEmptyLbl.setVisible(false);
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("fxmls/Artist.fxml"));
            AnchorPane Bpane = fxmlLoader.load();
            ArtistC artist = (ArtistC) fxmlLoader.getController();
            String path = Paths.get("src/main/resources/org/example/mplayer/images/b.png").toAbsolutePath().toString();
            Image image = new Image("file:" + path);
            artist.setData(name,image);
            if(colA>2){
                colA=0;
                rowA++;
            }
            artists.add(Bpane, colA, rowA);
            colA++;
            if(rowA>1) {
                artists.setPrefHeight(artists.getPrefHeight() + 100);
                libPane.setPrefHeight(libPane.getPrefHeight() + 100);
            }

            ////////////
            final int targRow = rowA;
            final int targCol = colA-1;
            System.out.println("before");
            Bpane.setOnMouseClicked(e -> {
                System.out.println("row: " + targRow + " col: " + targCol);
                Pane pane = new Pane();
                pane.setPrefSize(1100, 650);
                pane.getChildren().add(Cpanes.getInstance().getArtistInfo());
                Cpanes.getInstance().getBorder().last = "ArtistInfo";
                Artist artist1 = ListenerController.getListenerController().getListener().getFollowings().get((targRow*3) + targCol);
                Cpanes.getInstance().getArF().setData(artist1);
                Cpanes.getInstance().getThCPane().setCenter(pane);

            });
        }catch (IOException e){
            System.out.println("kkkkk");
        }
    }
    public void addToAllArtist(String name) {
        ArtEmptyLbl.setVisible(false);
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("fxmls/Artist.fxml"));
            AnchorPane Bpane = fxmlLoader.load();
            ArtistC artist = (ArtistC) fxmlLoader.getController();
            String path = Paths.get("src/main/resources/org/example/mplayer/images/b.png").toAbsolutePath().toString();
            Image image = new Image("file:" + path);
            artist.setData(name,image);
            if(colA>2){
                colA=0;
                rowA++;
            }
            artists.add(Bpane, colA, rowA);
            colA++;
            if(rowA>1) {
                artists.setPrefHeight(artists.getPrefHeight() + 100);
                libPane.setPrefHeight(libPane.getPrefHeight() + 100);
            }

            ////////////
            final int targRow = rowA;
            final int targCol = colA-1;
            System.out.println("before");
            Bpane.setOnMouseClicked(e -> {
                System.out.println("row: " + targRow + " col: " + targCol);
                Pane pane = new Pane();
                pane.setPrefSize(1100, 650);
                pane.getChildren().add(Cpanes.getInstance().getArtistInfo());
                Cpanes.getInstance().getBorder().last = "ArtistInfo";
                Artist artist1 = AdminController.getAdminController().showArtistsAll().get((targRow*3) + targCol);
                Cpanes.getInstance().getArF().setData(artist1);
                Cpanes.getInstance().getThCPane().setCenter(pane);

            });
        }catch (IOException e){
            System.out.println("kkkkk");
        }
    }
}