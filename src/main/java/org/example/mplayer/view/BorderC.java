package org.example.mplayer.view;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import org.example.mplayer.HelloApplication;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXToggleButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.NodeOrientation;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import  javafx.scene.media.Media;
import  javafx.scene.media.MediaPlayer;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.example.mplayer.controller.ListenerController;
import org.example.mplayer.model.audio.Audio;
import org.example.mplayer.model.audio.Music;
import org.example.mplayer.model.audio.Podcast;
import org.example.mplayer.model.database.Database;
import org.example.mplayer.model.interfaces.GeneralOperation;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.*;

public class BorderC implements Initializable, GeneralOperation {

    private Media music ;
    private MediaPlayer mp ;
    private boolean isplaying =false;
    private boolean isFull =false;
    public   int index = 0;
    private double thisTotal;

    public boolean isAIslogin() {return AIslogin;}
    public void setAIslogin(boolean AIslogin) {this.AIslogin = AIslogin;}
    private boolean AIslogin = false;

    public void setAudioha(ArrayList<Audio> audioha) {this.audioha = audioha;}

    public ArrayList<Audio> getAudioha() {return audioha;}

    private ArrayList<Audio> audioha = Database.getDatabase().getAllAudio();

    public boolean isDark() {
        return isDark;
    }

    private boolean isDark = true;

    private Stack<String> scenes = new Stack<>();
    public Stack<String> getScenes() {return scenes;}

    @FXML
    private JFXComboBox thirdColorCombo;
    @FXML
    private JFXButton browseBtn;
    @FXML
    private JFXButton homeBtn;
    @FXML
    private JFXToggleButton themeBtn;
    @FXML
    private JFXButton close;
    @FXML
    private JFXButton back;
    @FXML
    private Slider sliy;
    @FXML
    private JFXButton home;
    @FXML
    private JFXButton browse;
    @FXML
    private JFXButton libraryBtn;
    @FXML
    private JFXButton library;
    private String value = "teal";
    public String getValue(){return value;}
    private double progress = 0;
    private String BW = "white";
    @FXML
    private JFXButton previous;
    @FXML
    private JFXButton next;
    @FXML
    private JFXButton play;
    @FXML
    private JFXButton fullScreen;
    @FXML
    private Label CurrTime;
    @FXML
    private Label TotalTime;
    @FXML
    private JFXButton likeBtn;

    public BorderPane getMain() {
        return main;
    }

    @FXML
    private BorderPane main;
    @FXML
    private JFXButton shuffle;
    @FXML
    private JFXButton repeat;

    private boolean isShuffle = false;
    private boolean isRepeat = false;

    ArrayList<Audio> shuffleList = new ArrayList<>();

    public void shuffle(){
        shuffleList.clear();
        Random rand = new Random();
        ArrayList<Integer> rands = new ArrayList<>();

        for (int i = 0; i < audioha.size(); i++) {
            int randomIndex = rand.nextInt(audioha.size());
            while (rands.contains(randomIndex)) {
                randomIndex = rand.nextInt(audioha.size());
            }
            rands.add(randomIndex);
            this.shuffleList.add(audioha.get(randomIndex));

        }
//        for(int i=0;i<audioha.size();i++){
//            if(i%2==0) {
//                shuffleList.add(audioha.get(i));
//            }
//            else{
//                shuffleList.add(audioha.get(audioha.size()-i));
//            }
//        }
        System.out.println(shuffleList);
        System.out.println(audioha);
        audioha = shuffleList;

    }

    public void unShuffle(){
        if (playScene.equals("ArtistInfo")) {
            Cpanes.getInstance().getArF().addSongs();
            Cpanes.getInstance().getArF().setUnShuffle();
        }
        else if (playScene.equals("ListOfPlaylist")) {
            Cpanes.getInstance().getListOfPlayListC().addSongs();
            Cpanes.getInstance().getListOfPlayListC().unshuffle();
        }
        else if(playScene.equals("library")){
            System.out.println("unshuffle library");
            Cpanes.getInstance().getLibraryC().unShuffle();
        }
        else if(playScene.equals("home")){
            if(LIsLogin){
                Cpanes.getInstance().getHomeC().suggestion();
                Cpanes.getInstance().getHomeC().setUnShuffle();
            }
            else{
                Cpanes.getInstance().getHomeC().trending();
                Cpanes.getInstance().getHomeC().setUnShuffle();
            }
        }
        else if(playScene.equals("browse")){
            Cpanes.getInstance().getBrowseC().unShuffle();
        }
    }
    public void shuffleOnScene(){
        if (playScene.equals("ArtistInfo")) {
            System.out.println("playA");
            Cpanes.getInstance().getArF().shuffle(shuffleList);
        }
        else if (playScene.equals("ListOfPlaylist")) {
            System.out.println("playB");
            Cpanes.getInstance().getListOfPlayListC().shuffle(shuffleList);
        }
        else if(playScene.equals("library")){
            System.out.println("playc");
            Cpanes.getInstance().getLibraryC().shuffle(shuffleList);
        }
        else if(playScene.equals("home")){
            System.out.println("playd");
            Cpanes.getInstance().getHomeC().shuffle(shuffleList);
        }
        else if(playScene.equals("browse")){
            System.out.println("playe");
            Cpanes.getInstance().getBrowseC().shuffle(shuffleList);
        }
    }

    public void shuffleClick(ActionEvent event) {

        if(isShuffle){
            isShuffle = false;
            shuffle.setOpacity(1);
            unShuffle();

        }
        else {
            shuffle();
            shuffleOnScene();
//            if(isRepeat){
//                isRepeat = false;
//                repeat.setOpacity(1);
//            }
            isShuffle = true;
            shuffle.setOpacity(0.5);
        }
        mp.stop();
        if(isplaying){
            isplaying = false;
            if(isDark){
                setImage("playW",false,play);
            }
            else{
                setImage("playB",false,play);
            }
        }
        File file = new File(audioha.get(index).getLink());
        music = new Media(file.toURI().toString());
        mp = new MediaPlayer(music);
        needs();
    }
    public void repeatClick(ActionEvent event) {
        if(isRepeat){
            isRepeat = false;
            repeat.setOpacity(1);
        }
        else {

            isRepeat = true;
            repeat.setOpacity(0.5);
            mp.stop();
            mp.play();
        }

    }

    public Label getUserLbl() {
        return userLbl;
    }

    public JFXButton getUserIcon() {
        return userIcon;
    }

    @FXML
    private Label userLbl;
    public boolean isAdmin = false;

    public void setUserName(String userName){userLbl.setText(userName);}

    @FXML
    private    JFXButton logIn;
    @FXML
    private JFXButton userIcon;

    public JFXButton getLogIn() {return logIn;}

    boolean LIsLogin =false;

    private JFXButton preBtn = new JFXButton();
    private JFXButton curBtn = new JFXButton();

    private Stack<String> mains = new Stack<>();
    String last = "home";

    @FXML
    private JFXButton AddToPlayList;

    private  Stage stage;
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    private Stage newStage;
    public Stage getNewStage() {
        return newStage;
    }
    private Scene scene2 = new Scene(new Pane(), 300,200);

    private String playScene = "home";
    public void setPlayScene(String playScene) {
        this.playScene = playScene;
    }

    //////////////////////////////////
    public void setPlayLists(Set<String> names){
        Cpanes.getInstance().getLibraryC().AddToPlaylist(audioha.get(index),names);
    }
    @FXML
    void AddClick(ActionEvent event) {
        if(LIsLogin){
            System.out.println("add");
            newStage = new Stage();
            newStage.initModality(Modality.APPLICATION_MODAL);//another way????
            scene2.setRoot(Cpanes.getInstance().getPlaylist());
            Cpanes.getInstance().getPlaylistChoose().set(audioha.get(index));
            newStage.setScene(scene2);
            newStage.setTitle("Playlists");
            newStage.setX(1060);
            newStage.setY(550);
            newStage.initStyle(StageStyle.TRANSPARENT);
            newStage.show();
            event.consume();
        }
        else{
            Utils.getInstance().showError("you dont have access");
        }
    }

    public void setUnback(Pane pane) {
        System.out.println("laaaaaaaaaaaast: "+last);
        if (last.equals("ArtistInfo")) {
            pane.getChildren().add(Cpanes.getInstance().getArtistInfo());

        }
        if (last.equals("ListOfPlaylist")) {
            pane.getChildren().add(Cpanes.getInstance().getListOfPl());

        }
        else if(last.equals("library")){
            pane.getChildren().add(Cpanes.getInstance().getLibPane());
        }
        else if(last.equals("home")){
            pane.getChildren().add(Cpanes.getInstance().getHome());
        }
        else if(last.equals("browse")){
            pane.getChildren().add(Cpanes.getInstance().getBrowse());
        }
    }

    public void addPlays(){
        if(LIsLogin){
            ListenerController.getListenerController().addAudioPlay(audioha.get(index).getId());
        }
    }

    public  void setIndex(int num){
        if(!isplaying){
            isplaying = true;
            if(isDark){
                setImage("pauseW",false,play);
            }
            else{
                setImage("pauseB",false,play);
            }
        }
        isFull = true;
        System.out.println(num);
        index = num;
        sliy.setValue(0);
        mp.stop();
        File file = new File(audioha.get(index).getLink());
        music = new Media(file.toURI().toString());
//        music = new Media(audioha.get(index).getLink());
        mp = new MediaPlayer(music);
        needs();
        mp.play();
        addPlays();
        setFull();
        checkLike();
    }


    public void setFull(){
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("fxmls/fullScreen.fxml"));
        Pane pane = new Pane();
        pane.setPrefSize(1100, 650);
        try {
            pane.getChildren().add(loader.load());
            main.setCenter(pane);
            FullScreenC fullScreen1 = loader.getController();
//            if(isShuffle){
//                if(audioha.get(index) instanceof Music) {
//                    fullScreen1.setData(((Music)shuffleList.get(index)).getLyrics(), shuffleList.get(index).getCover(),shuffleList.get(index).getAudioName(),String.valueOf(shuffleList.get(index).getNumberOfLikes()),String.valueOf(shuffleList.get(index).getNumberOfPlays()));
//                }
//                else{
//                    fullScreen1.setData(((Podcast)shuffleList.get(index)).getCaption(), shuffleList.get(index).getCover(),shuffleList.get(index).getAudioName(),String.valueOf(shuffleList.get(index).getNumberOfLikes()),String.valueOf(shuffleList.get(index).getNumberOfPlays()));
//                }
//            }

                if(audioha.get(index) instanceof Music) {
                    fullScreen1.setData(((Music)audioha.get(index)).getLyrics(), audioha.get(index).getCover(),audioha.get(index).getAudioName(),String.valueOf(audioha.get(index).getNumberOfLikes()),String.valueOf(audioha.get(index).getNumberOfPlays()));
                }
                else{
                    fullScreen1.setData(((Podcast)audioha.get(index)).getCaption(), audioha.get(index).getCover(),audioha.get(index).getAudioName(),String.valueOf(audioha.get(index).getNumberOfLikes()),String.valueOf(audioha.get(index).getNumberOfPlays()));
                }

        } catch (IOException e) {
            System.out.println("catch");

        }
        main.getLeft().setVisible(false);
        main.getTop().setVisible(false);
    }
    private void setCenter(Pane p){
        Pane pane = new Pane();
        pane.setPrefSize(1100, 650);
        pane.getChildren().add(p);
        main.setCenter(pane);
    }
    @FXML
    public void FullScreenClick(ActionEvent event) {
        if (isFull){
            isFull = false;

            Pane pane = new Pane();
            pane.setPrefSize(1100, 650);
            setUnback(pane);
            main.setCenter(pane);

            main.getLeft().setVisible(true);
            main.getTop().setVisible(true);
            if(isDark){
                setImage("fullW",false,fullScreen);
            }
            else{
                setImage("fullB",false,fullScreen);
            }
        }
        else{
            isFull = true;
            setFull();
            if(isDark){
                setImage("unfullW",false,fullScreen);
            }
            else{
                setImage("unfullB",false,fullScreen);
            }
        }



    }
    @FXML
    public void playClick(ActionEvent event) {
        if(isplaying){
            isplaying = false;
            mp.pause();
            if(isDark){
                setImage("playW",false,play);
            }
            else{
                setImage("playB",false,play);
            }

        }
        else{
            mp.play();
            checkLike();
            addPlays();
            isplaying = true;
            if(isDark){
                setImage("pauseW",false,play);
            }
            else{
                setImage("pauseB",false,play);
            }


        }

    }
    @FXML
    public void previousClick(ActionEvent event) {
        if(!isplaying){
            isplaying = true;
            if(isDark){
                setImage("pauseW",false,play);
            }
            else{
                setImage("pauseB",false,play);
            }
        }
        sliy.setValue(0);
        mp.stop();
        index--;
        if(index<0){
            index=audioha.size()-1;
        }
        if(isFull){
            setFull();
        }
//        if(isShuffle){
//            File file = new File(shuffleList.get(index).getLink());
//            music = new Media(file.toURI().toString());
//        }else{
            File file = new File(audioha.get(index).getLink());
            music = new Media(file.toURI().toString());
//        }
//        music = new Media(audioha.get(index).getLink());
        mp = new MediaPlayer(music);
        needs();
        mp.play();
        addPlays();
        goNextColor();
        checkLike();

    }
    @FXML
    public void nextClick(ActionEvent event) {
        if(!isplaying){
            isplaying = true;
            if(isDark){
                setImage("pauseW",false,play);
            }
            else{
                setImage("pauseB",false,play);
            }
        }
        sliy.setValue(0);
        mp.stop();
        index++;
        if(index ==audioha.size()){
            index = 0;
        }
        if(isFull){
            setFull();
        }
//        if(isShuffle){
//            System.out.println("isShuff");
//            File file = new File(this.shuffleList.get(index).getLink());
//            music = new Media(file.toURI().toString());
//        }
//        else if(!isShuffle){
            System.out.println("not shugff");
            File file = new File(audioha.get(index).getLink());
            music = new Media(file.toURI().toString());
//        }
//        music = new Media(audioha.get(index).getLink());
        mp = new MediaPlayer(music);
        needs();
        mp.play();
        addPlays();
        goNextColor();
        checkLike();

    }
    public void nextGo(){
        sliy.setValue(0);
        mp.stop();
//        if(!isRepeat){
        System.out.println("index"+index);
            index++;
            if(index ==audioha.size()){
                index = 0;
            }
//        }
//        index++;
        if(isFull){
            setFull();
        }
        System.out.println(audioha.get(index).getAudioName());
        File file = new File(audioha.get(index).getLink());
        music = new Media(file.toURI().toString());
        mp = new MediaPlayer(music);
        needs();
        mp.play();
        addPlays();
        goNextColor();
        checkLike();
    }
    public void goNextColor(){
        if (playScene.equals("ArtistInfo")) {
            System.out.println("playA");
            Cpanes.getInstance().getArF().nextColor(index);
        }
        else if (playScene.equals("ListOfPlaylist")) {
            System.out.println("playB");
            Cpanes.getInstance().getListOfPlayListC().nextColor(index);
        }
        else if(playScene.equals("library")){
            System.out.println("playc");
            Cpanes.getInstance().getLibraryC().nextColor(index);
        }
        else if(playScene.equals("home")){
            System.out.println("playd");
            Cpanes.getInstance().getHomeC().nextColor(index);
        }
        else if(playScene.equals("browse")){
            System.out.println("playe");
            Cpanes.getInstance().getBrowseC().nextColor(index);
        }
    }

    @FXML
    void sliderDrag(MouseEvent event) {
//        mp.pause();
//        sliy.setValue((mp.getCurrentTime().toSeconds()));

//        if(sliy.getValue() == sliy.getMax()){
//                nextGo();
//        }
    }

    void currentTime(double newTime){
        int min ;
        int sec ;
        sec = ((int )newTime)%60;
        min = ((int )newTime)/60;
        if(sec/10==0){
            CurrTime.setText(min+":0"+sec);
        }else{
            CurrTime.setText(min+":"+sec);
        }
    }

    void initializeFinal(MediaPlayer mp){
        sliy.setMax(mp.getTotalDuration().toSeconds());
        thisTotal = mp.getTotalDuration().toSeconds();
        int min =((int) (mp.getTotalDuration().toSeconds()))/60;
        int sec = ((int)(mp.getTotalDuration().toSeconds()))%60;
        if(sec/10==0){
            TotalTime.setText(min+":0"+sec);

        }else{
            TotalTime.setText(min+":"+sec);
        }
    }

    public void needs(){
        sliy.valueProperty().addListener((observableValue2, number, t2) -> {
//            if(sliy.getValue() == sliy.getMax()){
//                nextGo();
//            }
            progress = t2.doubleValue() / sliy.getMax();
            if(progress==0){
                main.setStyle("-fx-slider: linear-gradient(from 0% 0% to 0% 0%, white, white); -fx-third: " + value+";");
            }
            else{
                main.setStyle("-fx-slider: linear-gradient(from "+((progress*100)-5.0)+"% 0% to "+progress*100+"% 0%, "+value+", white);-fx-third: " + value+"; -fx-BW: "+BW+";");
            }
            if (sliy.isValueChanging()) {
                mp.seek(Duration.seconds(t2.intValue()));
            }
        });
        mp.currentTimeProperty().addListener((observableValue3, duration, t3) -> {
            if (!sliy.isValueChanging()) {
                sliy.setValue(t3.toSeconds());
                currentTime(t3.toSeconds());
                if(t3.toSeconds()==thisTotal){
                    nextGo();
                }

            }
        });
        if (mp.getStatus() == MediaPlayer.Status.UNKNOWN) {
            mp.statusProperty().addListener((obs, oldStatus, newStatus) -> {
                if (newStatus == MediaPlayer.Status.READY) {
                    initializeFinal(mp);
                }
            });
        } else {
            initializeFinal(mp);
        }

        mp.currentTimeProperty().addListener((observable, duration, t1) -> {
            currentTime(t1.toSeconds());

        });
    }
    public void setImage(String name, boolean reversed, JFXButton button){
        String path = Paths.get("src/main/resources/org/example/mplayer/images/"+name+".png").toAbsolutePath().toString();
        Image image = new Image("file:" + path);
        ImageView imageView = new ImageView(image);
        if(reversed){
            imageView.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        }
        imageView.setFitHeight(25);
        imageView.setFitWidth(25);
        imageView.setPreserveRatio(true);
        button.setGraphic(imageView);
    }
    Circle c ;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        main.getTop().setOnMousePressed(pressEvent -> {
            main.getTop().setOnMouseDragged(dragEvent -> {
                stage.setX(dragEvent.getScreenX() - pressEvent.getSceneX());
                stage.setY(dragEvent.getScreenY() - pressEvent.getSceneY());
            });
        });
//        main.setOnMousePressed(pressEvent -> {
//            main.setOnMouseDragged(dragEvent -> {
//                stage.setX(dragEvent.getScreenX() - pressEvent.getSceneX());
//                stage.setY(dragEvent.getScreenY() - pressEvent.getSceneY());
//            });
//        });
        userIcon.setVisible(false);
        userLbl.setVisible(false);
        scenes.add("home");
        mains.add("home");
        System.out.println("hyuug");
        System.out.println("jhhgfdj");
        File file = new File(audioha.get(index).getLink());
        music = new Media(file.toURI().toString());
//        music = new Media(audioha.get(index).getLink());
        System.out.println("oytredcv");
        mp = new MediaPlayer(music);
        System.out.println("bvvxdset");
        needs();
        preBtn=curBtn;
        curBtn = homeBtn;
        inPane();
        /////////////////
        home.setStyle("-fx-text-fill:-fx-third;");

        setImage("previousW",false,next);

        setImage("playW",false,play);

        setImage("previousW",true,previous);

        setImage("fullW",false,fullScreen);

        setImage("home",false,home);

        setImage("browse",false,browse);

        setImage("backW",false,back);

        setImage("closeW",false,close);

        setImage("heartW",false,likeBtn);

        setImage("libraryW",false,library);

        setImage("userW",false,userIcon);

        setImage("repeatW",false,repeat);

        setImage("shuffleW",false,shuffle);



        main.setStyle("-fx-slider: linear-gradient(from 0% 0% to 0% 0%, white, white);");

        thirdColorCombo.getItems().addAll("teal", "orange", "red");
        thirdColorCombo.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {

            value = newValue.toString();
            inPane(false);

            main.setStyle("-fx-slider: linear-gradient(from "+((progress*100)-5.0)+"% 0% to "+progress*100+"% 0%, "+value+", white);-fx-third: " + value+"; -fx-BW: "+BW+";");

        });
        main.setOnMouseDragged(e->{
            Circle c = new Circle(0,0,15);
            Random rng = new Random();
            double p = rng.nextDouble();
//            int r = p & 255;
//            int g = (p >>> 8) & 255;
//            int b = (p >>> 16) & 255;
//            double op = (p >>> 24) / 255.0;

//            c.setFill(Color.rgb(r, g, b, op));
            double h=0;
            if(value.equals("red")){
                h = 358.0;
            }
            else if(value.equals("orange")){
                h= 30.0;
            }
            else if(value.equals("teal")){
                h=188.0;
            }
            p -= 0.5;
            p *= 2;
            p *= 20;
            h+=p;
            double s = 1.0;
            double b = 0.92;
            c.setFill(Color.hsb(h, s, b, 1));
            c.setCenterX(e.getX());
            c.setCenterY(e.getY());
            main.getChildren().add(c);
            ScaleTransition scaleTransition = new ScaleTransition();
            scaleTransition.setNode(c);
            scaleTransition.setDuration(Duration.millis(1000));
            scaleTransition.setCycleCount(1);
            scaleTransition.setFromX(1);
            scaleTransition.setFromY(1);
            scaleTransition.setToX(0);
            scaleTransition.setToY(0);
            scaleTransition.play();
            FadeTransition fd = new FadeTransition(Duration.millis(1000), c);
            fd.setFromValue(1);
            fd.setToValue(0);
            fd.play();
            fd.setOnFinished(f->{
                main.getChildren().remove(c);
            });
        });
    }

    public void backHome(){
        setCenter(Cpanes.getInstance().getHome());
        main.getLeft().setVisible(true);
        main.getBottom().setVisible(true);
        main.getTop().setVisible(true);
        last = "home";
        preBtn=curBtn;
        curBtn=homeBtn;
        System.out.println(curBtn.getId());
        inPane(false);
        scenes.clear();
        scenes.add("home");
        Cpanes.getInstance().getBrowseC().firstScene();
    }
    public void goLogin(){
        setCenter(Cpanes.getInstance().getLogin());
        main.getLeft().setVisible(false);
        main.getBottom().setVisible(false);
        main.getTop().setVisible(false);
        ScaleTransition scaleTransition = new ScaleTransition();
        scaleTransition.setNode(Cpanes.getInstance().getLogin());
        scaleTransition.setDuration(Duration.millis(200));
        scaleTransition.setCycleCount(1);
        scaleTransition.setFromX(0.7);
        scaleTransition.setFromY(0.7);
        scaleTransition.setToX(1);
        scaleTransition.setToY(1);
        scaleTransition.play();
    }
    public void goSignUp(){
        setCenter(Cpanes.getInstance().getSignup());
        main.getLeft().setVisible(false);
        main.getBottom().setVisible(false);
        main.getTop().setVisible(false);
        ScaleTransition scaleTransition = new ScaleTransition();
        scaleTransition.setNode(Cpanes.getInstance().getSignup());
        scaleTransition.setDuration(Duration.millis(200));
        scaleTransition.setCycleCount(1);
        scaleTransition.setFromX(0.7);
        scaleTransition.setFromY(0.7);
        scaleTransition.setToX(1);
        scaleTransition.setToY(1);
        scaleTransition.play();
    }

    @FXML
    public void SearchClick(ActionEvent event) {
        search();
    }

    @FXML
    public void homeClick(ActionEvent event) {
        if(LIsLogin){
            Cpanes.getInstance().getHomeC().suggestion();
        }
        else{
            Cpanes.getInstance().getHomeC().trending();
        }
        last = "home";
        System.out.println(scenes);
        if(scenes.peek().equals("home")==false){
            scenes.add("home");
            preBtn=curBtn;
            curBtn=homeBtn;
            System.out.println(curBtn.getId());
            inPane(false);
            setCenter(Cpanes.getInstance().getHome());
        }
        System.out.println("searchhh");
        System.out.println(scenes);

    }

    @FXML
    void libraryClick(ActionEvent event) {
        if(LIsLogin) {
            Cpanes.getInstance().getLibraryC().getGetPremuim().setVisible(true);
            Cpanes.getInstance().getLibraryC().getPlaylist().setVisible(true);
            last = "library";
            System.out.println("libbbbb");
            System.out.println(Cpanes.getInstance().getLibraryC().getLast());
            System.out.println(scenes.peek());
            if (scenes.peek().equals(Cpanes.getInstance().getLibraryC().getLast()) == false) {
                System.out.println("innnnnnnnnnn");
                scenes.add("library");
                scenes.add(Cpanes.getInstance().getLibraryC().getLast());
                System.out.println("homeeeee");
                preBtn = curBtn;
                curBtn = libraryBtn;
                inPane(false);
                setCenter(Cpanes.getInstance().getLibPane());
            }
        }
        else if(AIslogin || isAdmin){
            last = "library";
            System.out.println("libbbbb");
            System.out.println(Cpanes.getInstance().getLibraryC().getLast());
            System.out.println(scenes.peek());
            if (scenes.peek().equals(Cpanes.getInstance().getLibraryC().getLast()) == false) {
                System.out.println("innnnnnnnnnn");
                scenes.add("library");
                scenes.add(Cpanes.getInstance().getLibraryC().getLast());
                System.out.println("homeeeee");
                preBtn = curBtn;
                curBtn = libraryBtn;
                inPane(false);
                setCenter(Cpanes.getInstance().getLibPane());
            }
//            Utils.getInstance().showError("Sorry dear ! we are still working on this part \n and it's not ready yet:))");
        }
        else{
            Utils.getInstance().showError("you dont have access");
            event.consume();
        }

    }


    public void inPane(){
        preBtn.setStyle("-fx-text-fill: -fx-BW;");
        curBtn.setStyle("-fx-text-fill: -fx-third;");

    }
    public void inPane(boolean reverse){
        if(reverse){
            curBtn.setStyle("-fx-text-fill: -fx-BW;");
            preBtn.setStyle("-fx-text-fill: -fx-third;");
        }
        else{
            System.out.println(preBtn.getId());
            System.out.println(curBtn.getId());
            preBtn.setStyle("-fx-text-fill: -fx-BW;");
            curBtn.setStyle("-fx-text-fill: -fx-third;");
        }


    }
    @FXML
    public void closeClicked(ActionEvent event) {
        try {
            stage.close();
        }catch (Exception e){

        }
        finally {
        }
    }

    public void goBack(String p){
        if(p.equals("home")){
            last = "home";
            preBtn = curBtn;
            curBtn = homeBtn;
            inPane();
            setCenter(Cpanes.getInstance().getHome());
        }
        if(p.equals("library")){
            last = "library";
            scenes.pop();
            goBack(scenes.peek());
        }
        if(p.equals("browse")){
            last = "browse";
            scenes.pop();
            goBack(scenes.peek());
        }
        if(p.equals("premium")){
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("fxmls/getPremium.fxml"));
            try {AnchorPane Bpane = fxmlLoader.load();
                setInLib(Cpanes.getInstance().getLibraryC().getGetPremuim(),Bpane);}
            catch(IOException e){
                e.printStackTrace();}
        }
        if(p.equals("personal")){
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("fxmls/personal.fxml"));
            try {AnchorPane Bpane = fxmlLoader.load();
                setInLib(Cpanes.getInstance().getLibraryC().getPersonal(),Bpane);}
            catch(IOException e){
                e.printStackTrace();}
        }
        if(p.equals("favorite")){
            setInLib(Cpanes.getInstance().getLibraryC().getArtists(),Cpanes.getInstance().getLibraryC().getSongs());
        }
        if(p.equals("artist")){
            setInLib(Cpanes.getInstance().getLibraryC().getFavorites(),Cpanes.getInstance().getLibraryC().getArtistsPane());
        }
        if(p.equals("playlist")){
            setInLib(Cpanes.getInstance().getLibraryC().getPlaylist(),Cpanes.getInstance().getLibraryC().getPlayGrid());
        }
        if(p.equals("search")){
            setInBrowse(Cpanes.getInstance().getBrowseC().getGridSearch());
        }
        if(p.equals("AllAudios")){
            setInBrowse(Cpanes.getInstance().getBrowseC().getGridSong());
        }
        if(p.equals("AllArtists")){
            setInBrowse(Cpanes.getInstance().getBrowseC().getGrid());
        }
        if(p.equals("ArtistInfo")){
            last = "ArtistInfo";
            setCenter(Cpanes.getInstance().getArtistInfo());
        }
        if(p.equals("ListOfPlaylist")){
            last = "ListOfPlaylist";
            setCenter(Cpanes.getInstance().getListOfPl());
        }
    }

    public void setInBrowse(GridPane gp){
        last = "browse";
        preBtn = curBtn;
        curBtn = browseBtn;
        inPane();
        Cpanes.getInstance().getBrowseC().getSearchPane().getChildren().clear();
        Cpanes.getInstance().getBrowseC().getSearchPane().getChildren().add(gp);
        setCenter(Cpanes.getInstance().getBrowse());
    }
    public void setInLib(JFXButton button , Pane paneIn){
        last = "library";
        preBtn = curBtn;
        curBtn = libraryBtn;
        Cpanes.getInstance().getLibraryC().setPreBtn(Cpanes.getInstance().getLibraryC().getCurBtn());
        Cpanes.getInstance().getLibraryC().setCurBtn(button);
        inPane();
        Cpanes.getInstance().getLibraryC().inPane(false);
        Cpanes.getInstance().getLibraryC().getLibPane().getChildren().clear();
        Cpanes.getInstance().getLibraryC().getLibPane().getChildren().add(paneIn);
        setCenter(Cpanes.getInstance().getLibPane());
    }

    @FXML
    public void userIconClick(ActionEvent event){
        if(true) {
            if (Cpanes.getInstance().getBorder().getScenes().peek().equals("personal") == false) {
                Cpanes.getInstance().getBorder().getScenes().add("personal");
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("fxmls/personal.fxml"));
                try {
                    AnchorPane Bpane = fxmlLoader.load();
                    setInLib(Cpanes.getInstance().getLibraryC().getPersonal(), Bpane);
                    TranslateTransition tt = new TranslateTransition();
                    tt.setNode(Bpane);
                    tt.setDuration(Duration.millis(1000));
                    tt.setCycleCount(1);
                    tt.setFromY(-300);
                    tt.setToY(0);
                    tt.play();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
    @FXML
    public void backClicked(ActionEvent event) {
        backTo();
    }

    public void save(String info){
        try {

            Pane pane = new Pane();
            pane.getChildren().add(Cpanes.getInstance().getInfo());
            Cpanes.getInstance().getInfoC().setInfo(info);
            Cpanes.getInstance().getInfoC().getOk().setOnAction(e->{
                main.getChildren().remove(pane);
            });
            pane.setPrefSize(300,200);
            pane.setLayoutX(450);
            pane.setLayoutY(300);
            Utils.getInstance().translateTransition(300,0,pane,500);
            main.getChildren().add(pane);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void logInClick(ActionEvent event) {

        mp.stop();
        if(isDark) {
            setImage("playW",false,play);
            isplaying = false;
        }
        else{
            setImage("playB",false,play);
            isplaying = false;
        }
        index =0;
        if(!LIsLogin && !AIslogin && !isAdmin) {
            login();
        }
        else{
           logout();
        }
        if(AIslogin){
            logout();
            AIslogin = false;
        }
        if(isAdmin){
            logout();
            isAdmin = false;
        }
    }
    private void checkLike(){
        if(LIsLogin){
            if(ListenerController.getListenerController().getListener().getFavorites().contains(audioha.get(index))){
                System.out.println(audioha.get(index).getAudioName());
                setImage("heartR",false,likeBtn);
            }
            else{
                System.out.println(audioha.get(index).getAudioName());
                if(isDark) {
                    setImage("heartW", false, likeBtn);
                }else{
                    setImage("heartB",false,likeBtn);
                }
            }
        }
        else{
            if(isDark) {
                setImage("heartW", false, likeBtn);
            }else{
                setImage("heartB",false,likeBtn);
            }
        }
    }
    @FXML
    public void onLike(ActionEvent event) {
        if(LIsLogin) {
            System.out.println("liked");
            if(ListenerController.getListenerController().like(audioha.get(index))) {
                setImage("heartR",false,likeBtn);
                ListenerController.getListenerController().likeAudio(audioha.get(index).getId());
                Cpanes.getInstance().getLibraryC().addToFavs(audioha.get(index));
            }
            else{
                ListenerController.getListenerController().likeAudio(audioha.get(index).getId());
                if(isDark) {
                    setImage("heartW", false, likeBtn);
                }else{
                    setImage("heartB",false,likeBtn);
                }
                Cpanes.getInstance().getLibraryC().showSavedFavs();
            }
        }else{
            Utils.getInstance().showError("you dont have access");
        }
    }

    private void goToDark(){
        setImage("previousW",false,next);

        if(isplaying) {
            setImage("pauseW",false,play);
        }else{
            setImage("playW",false,play);
        }

        setImage("previousW",true,previous);

        setImage("fullW",false,fullScreen);

        setImage("home",false,home);

        setImage("browse",false,browse);

        setImage("backW",false,back);

        setImage("closeW",false,close);

        setImage("libraryW",false,library);

        setImage("heartW",false,likeBtn);

        setImage("userW",false,userIcon);

        setImage("repeatW",false,repeat);

        setImage("shuffleW",false,shuffle);

        setImage("backW",false,Cpanes.getInstance().getLoginC().getGoHome());

        setImage("backW",false,Cpanes.getInstance().getSignupC().getGoHome());

        setImage("browse",false,Cpanes.getInstance().getBrowseC().getSearch());

        isDark = true;
        themeBtn.setText("Dark");
//
        main.getStylesheets().clear();
        main.getStylesheets().add(HelloApplication.class.getResource("fxmls/Dark.css").toExternalForm());
    }
    private void goToLight(){
        setImage("previousB",false,next);

        if(isplaying) {
            setImage("pauseB",false,play);
        }else{
            setImage("playB",false,play);
        }

        setImage("previousB",true,previous);

        setImage("fullB",false,fullScreen);

        setImage("homeB",false,home);

        setImage("browseB",false,browse);

        setImage("back",false,back);

        setImage("close",false,close);

        setImage("libraryB",false,library);

        setImage("heartB",false,likeBtn);

        setImage("userB",false,userIcon);

        setImage("repeatB",false,repeat);

        setImage("shuffleB",false,shuffle);

        setImage("back",false,Cpanes.getInstance().getLoginC().getGoHome());

        setImage("back",false,Cpanes.getInstance().getSignupC().getGoHome());

        setImage("browseB",true,Cpanes.getInstance().getBrowseC().getSearch());

        isDark = false;
        themeBtn.setText("Light");

        main.getStylesheets().clear();

        main.getStylesheets().add(HelloApplication.class.getResource("fxmls/Light.css").toExternalForm());
    }
    @FXML
    public void theme(){
        if(isDark){
            BW = "black";
            LibraryC.BW = BW;
            main.setStyle("-fx-slider: linear-gradient(from "+((progress*100)-5.0)+"% 0% to "+progress*100+"% 0%, "+value+", white);-fx-third: " + value+"; -fx-BW: "+BW+";");
            /////////////////
            goToLight();
        }
        else{
            BW="white";
            LibraryC.BW = BW;
            main.setStyle("-fx-slider: linear-gradient(from "+((progress*100)-5.0)+"% 0% to "+progress*100+"% 0%, "+value+", white);-fx-third: " + value+"; -fx-BW: "+BW+";");
            goToDark();
        }
    }


    @Override
    public void backTo() {
        if(scenes.size()!=1) {
            scenes.pop();
            goBack(scenes.peek());
            System.out.println("back");
        }
    }

    @Override
    public void logout() {
        userLbl.setVisible(false);
        userIcon.setVisible(false);
        logIn.setText("Login");
        LIsLogin = false;
        AIslogin = false;
        checkLike();
        backHome();
        Cpanes.getInstance().getHomeC().trending();
        Cpanes.getInstance().getHomeC().firstRowInhome();
    }

    @Override
    public void login() {
        setCenter(Cpanes.getInstance().getLogin());
        main.getLeft().setVisible(false);
        main.getBottom().setVisible(false);
        main.getTop().setVisible(false);
        ScaleTransition scaleTransition = new ScaleTransition();
        scaleTransition.setNode(Cpanes.getInstance().getLogin());
        scaleTransition.setDuration(Duration.millis(200));
        scaleTransition.setCycleCount(1);
        scaleTransition.setFromX(0.7);
        scaleTransition.setFromY(0.7);
        scaleTransition.setToX(1);
        scaleTransition.setToY(1);
        scaleTransition.play();
    }

    @Override
    public void signup() {
        //no signup button in first scene
    }

    @Override
    public void search() {
        last = "browse";
        if(scenes.peek().equals(Cpanes.getInstance().getBrowseC().getLast())==false){
            scenes.add("browse");
            scenes.add(Cpanes.getInstance().getBrowseC().getLast());
            System.out.println("search");
            preBtn=curBtn;
            curBtn=browseBtn;
            inPane(false);
            setCenter(Cpanes.getInstance().getBrowse());
        }
        //search is in browse tab--no direct search bar
    }
}

