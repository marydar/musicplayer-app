package org.example.mplayer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.example.mplayer.controller.UserController;
import org.example.mplayer.model.audio.Genre;
import org.example.mplayer.model.audio.Music;
import org.example.mplayer.model.audio.Podcast;
import org.example.mplayer.model.database.Database;
import org.example.mplayer.model.user.Admin;
import org.example.mplayer.model.user.Podcaster;
import org.example.mplayer.model.user.Report;
import org.example.mplayer.model.user.Singer;
import org.example.mplayer.view.Cpanes;
import org.example.mplayer.view.PaneCollector;
import org.example.mplayer.view.Utils;
import java.io.IOException;
import java.util.Date;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("fxmls/border.fxml"));
            try {
                Cpanes.getInstance().setThCPane(fxmlLoader.load());
                Cpanes.getInstance().setBorder(fxmlLoader.getController());

            } catch (IOException e) {
                e.printStackTrace();
            }
            Scene scene = new Scene(Cpanes.getInstance().getThCPane());
            Cpanes.getInstance().getBorder().setStage(stage);
            PaneCollector.getInstance().setPanes();

            stage.setTitle("player");
            stage.setScene(scene);
            stage.initStyle(StageStyle.TRANSPARENT);

            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
//            Utils.getInstance().showError("jluhuguygtyurtur");
        }
    }

    public static void main(String[] args) {

        String lyric="this is lyric \n Habata itara modoranai to itte\n" +
                "Mezashita no wa aoi aoi ano sora\n" +
                "\n" +
                "Kanashimi wa mada oboerarezu\n" +
                "Setsunasa wa ima tsukami hajimeta\n" +
                "Anata e to daku kono kanjou mo\n" +
                "Ima kotoba ni kawatte iku\n" +
                "\n" +
                "Michi naru sekai no yume kara mezamete\n" +
                "Kono hane wo hiroge tobitatsu";


        String lyric2 = "Miakita kago wa hora sutete iku\n" +
                "Furikaeru koto wa mou nai\n" +
                "Takanaru kodou ni kokyuu wo azukete\n" +
                "Kono mado wo kette tobitatsu\n" +
                "Kakedashitara te ni dekiru to itte\n" +
                "Izanau no wa tooi tooi ano koe\n" +
                "Mabushi sugita anata no te mo nigitte\n" +
                "Motomeru hodo aoi aoi ano sora\n" +
                "\n" +
                "Ochite iku to wakatte ita\n" +
                "Soredemo hikari wo oi tsudzukete iku yo";

        String lyric3 = "Habata itara modoranai to itte\n" +
                "Mezashita no wa shiroi shiroi ano kumo\n" +
                "Tsukinuketara mitsukaru to shitte\n" +
                "\n" +
                "Furikiru hodo aoi aoi ano sora\n" +
                "Aoi aoi ano sora\n" +
                "Aoi aoi ano sora\n" +
                "\n" +
                "Aisou sukita you na oto de\n" +
                "Sabireta furui mado wa kowareta";

        Date date = new Date();
        Admin admin = Admin.getAdmin("zxcv9876#A", "isAdmin", "AdminName", "emailAdmin@gmail.com", "09876543215",date);
        Database.getDatabase().addToAllUsers(admin);
        try {
            UserController.getUserController().signupNewUser("S", "Uchiha Itachi", "singerOne#1", "first", "email123@gmail.com", "09876543212", "1/Jan/2000", "this is a bio for myself\nhahaha");
            UserController.getUserController().signupNewUser("S", "Uzumaki Naruto", "singerTwo#2", "second", "email123@gmail.com", "09876543212", "1/Jan/2000", "no bio");
            UserController.getUserController().signupNewUser("P", "Namikaze Minato", "singerThree#2", "third", "email123@gmail.com", "09876543212", "1/Jan/2000", "no bio");
            UserController.getUserController().signupNewUser("L", "moi", "zxcv9876#A", "kjlhi", "email123@gmail.com", "09876543212", "1/Jan/2000", "no bio");
        }catch (Exception e){
            System.out.println("failed");
        }

        Singer s1=(Singer)Database.getDatabase().getAllUsers().get(1);
        Report r = new Report(Database.getDatabase().getAllUsers().get(4),s1,"this is description \nplease check ");
        Database.getDatabase().getAllReports().add(r);

        Music m1 = new Music("sadness and sorrow","first","Uchiha Itachi",date, Genre.POP,"E:\\Programming\\Musicplayer\\firstproject-musicplayer-phase2-marydar\\mplayer\\src\\main\\resources\\org\\example\\mplayer\\Songs\\08.-Sadness-and-Sorrow.mp3","E:\\Programming\\Musicplayer\\firstproject-musicplayer-phase2-marydar\\mplayer\\src\\main\\resources\\org\\example\\mplayer\\images\\sad.jpg",lyric);
//        Music m1 = new Music("sadness and sorrow","first","Uchiha Itachi",date, Genre.POP,"https://dl.psarena.ir/wp-content/uploads/2021/06/08.-Sadness-and-Sorrow.mp3","sad.jpg",lyric);
        Database.getDatabase().getAllAudio().add(m1);
        m1.setNumberOfPlays(10);
        m1.setNumberOfLikes(5);
        s1.getMusicList().add(m1);
        m1 = new Music("The-Rising-Fighting-Spirit","first","Uchiha Itachi",date, Genre.POP,"E:\\Programming\\Musicplayer\\firstproject-musicplayer-phase2-marydar\\mplayer\\src\\main\\resources\\org\\example\\mplayer\\Songs\\07.-The-Rising-Fighting-Spirit.mp3","E:\\Programming\\Musicplayer\\firstproject-musicplayer-phase2-marydar\\mplayer\\src\\main\\resources\\org\\example\\mplayer\\images\\rise.jpg",lyric2);
//        m1 = new Music("The-Rising-Fighting-Spirit","first","Uchiha Itachi",date, Genre.POP,"https://dl.psarena.ir/wp-content/uploads/2021/06/07.-The-Rising-Fighting-Spirit.mp3","rise.jpg",lyric2);
        Database.getDatabase().getAllAudio().add(m1);
        m1.setNumberOfPlays(6);
        m1.setNumberOfLikes(1);
        s1.getMusicList().add(m1);
        m1 = new Music("Akatsuki","first","Uchiha Itachi",date, Genre.POP,"E:\\Programming\\Musicplayer\\firstproject-musicplayer-phase2-marydar\\mplayer\\src\\main\\resources\\org\\example\\mplayer\\Songs\\10.-Akatsuki.mp3","E:\\Programming\\Musicplayer\\firstproject-musicplayer-phase2-marydar\\mplayer\\src\\main\\resources\\org\\example\\mplayer\\images\\akat.jpg",lyric3);
//        m1 = new Music("Akatsuki","first","Uchiha Itachi",date, Genre.POP,"https://dl.psarena.ir/wp-content/uploads/2021/06/10.-Akatsuki.mp3","akat.jpg",lyric3);
        Database.getDatabase().getAllAudio().add(m1);
        m1.setNumberOfPlays(8);
        m1.setNumberOfLikes(9);
        s1.getMusicList().add(m1);
        m1 = new Music("Fooling-Mode","first","Uchiha Itachi",date, Genre.POP,"E:\\Programming\\Musicplayer\\firstproject-musicplayer-phase2-marydar\\mplayer\\src\\main\\resources\\org\\example\\mplayer\\Songs\\08.-Fooling-Mode.mp3","E:\\Programming\\Musicplayer\\firstproject-musicplayer-phase2-marydar\\mplayer\\src\\main\\resources\\org\\example\\mplayer\\images\\narutoo.png",lyric3);
//        m1 = new Music("Fooling-Mode","first","Uchiha Itachi",date, Genre.POP,"https://dl.psarena.ir/wp-content/uploads/2021/06/08.-Fooling-Mode.mp3","narutoo.png",lyric3);
        Database.getDatabase().getAllAudio().add(m1);
        m1.setNumberOfPlays(2);
        s1.getMusicList().add(m1);
        m1 = new Music("Orochimarus-Theme","first","Uchiha Itachi",date, Genre.POP,"E:\\Programming\\Musicplayer\\firstproject-musicplayer-phase2-marydar\\mplayer\\src\\main\\resources\\org\\example\\mplayer\\Songs\\13.-Orochimarus-Theme.mp3","E:\\Programming\\Musicplayer\\firstproject-musicplayer-phase2-marydar\\mplayer\\src\\main\\resources\\org\\example\\mplayer\\images\\oro.jpg",lyric3);
//        m1 = new Music("Orochimarus-Theme","first","Uchiha Itachi",date, Genre.POP,"https://dl.psarena.ir/wp-content/uploads/2021/06/13.-Orochimarus-Theme.mp3","oro.jpg",lyric3);
        Database.getDatabase().getAllAudio().add(m1);
        m1.setNumberOfPlays(1);
        m1.setNumberOfLikes(2);
        s1.getMusicList().add(m1);
        ////////////////
        Singer s2=(Singer)Database.getDatabase().getAllUsers().get(2);
        r = new Report(Database.getDatabase().getAllUsers().get(4),s2,"this is description \nplease check ");
        Database.getDatabase().getAllReports().add(r);

        m1 = new Music("Experienced-Many-Battles","second","Uzumaki Naruto",date, Genre.POP,"E:\\Programming\\Musicplayer\\firstproject-musicplayer-phase2-marydar\\mplayer\\src\\main\\resources\\org\\example\\mplayer\\Songs\\04.-Experienced-Many-Battles.mp3","E:\\Programming\\Musicplayer\\firstproject-musicplayer-phase2-marydar\\mplayer\\src\\main\\resources\\org\\example\\mplayer\\images\\itachi.jpg",lyric);
//        m1 = new Music("Experienced-Many-Battles","second","Uzumaki Naruto",date, Genre.POP,"https://dl.psarena.ir/wp-content/uploads/2021/06/04.-Experienced-Many-Battles.mp3","itachi.jpg",lyric);
        Database.getDatabase().getAllAudio().add(m1);
        s2.getMusicList().add(m1);
        m1 = new Music("Friend","second","Uzumaki Naruto",date, Genre.POP,"E:\\Programming\\Musicplayer\\firstproject-musicplayer-phase2-marydar\\mplayer\\src\\main\\resources\\org\\example\\mplayer\\Songs\\17.-Friend.mp3","E:\\Programming\\Musicplayer\\firstproject-musicplayer-phase2-marydar\\mplayer\\src\\main\\resources\\org\\example\\mplayer\\images\\friend.jpg",lyric2);
//        m1 = new Music("Friend","second","Uzumaki Naruto",date, Genre.POP,"https://dl.psarena.ir/wp-content/uploads/2021/06/17.-Friend.mp3","friend.jpg",lyric2);
        Database.getDatabase().getAllAudio().add(m1);
        m1.setNumberOfLikes(4);
        s2.getMusicList().add(m1);
        m1 = new Music("Obitos-Theme","second","Uzumaki Naruto",date, Genre.POP,"E:\\Programming\\Musicplayer\\firstproject-musicplayer-phase2-marydar\\mplayer\\src\\main\\resources\\org\\example\\mplayer\\Songs\\19.-Obitos-Theme.mp3","E:\\Programming\\Musicplayer\\firstproject-musicplayer-phase2-marydar\\mplayer\\src\\main\\resources\\org\\example\\mplayer\\images\\obito.jpg",lyric3);
//        m1 = new Music("Obitos-Theme","second","Uzumaki Naruto",date, Genre.POP,"https://dl.psarena.ir/wp-content/uploads/2021/06/19.-Obitos-Theme.mp3","obito.jpg",lyric3);
        Database.getDatabase().getAllAudio().add(m1);
        s2.getMusicList().add(m1);
        ////////////////
        Podcaster s3=(Podcaster) Database.getDatabase().getAllUsers().get(3);
        r = new Report(Database.getDatabase().getAllUsers().get(4),s3,"this is description \nplease check ");
        Database.getDatabase().getAllReports().add(r);
        Podcast m2 = new Podcast("Many-Nights","third","Namikaze Minato",date, Genre.POP,"E:\\Programming\\Musicplayer\\firstproject-musicplayer-phase2-marydar\\mplayer\\src\\main\\resources\\org\\example\\mplayer\\Songs\\23.-Many-Nights.mp3","E:\\Programming\\Musicplayer\\firstproject-musicplayer-phase2-marydar\\mplayer\\src\\main\\resources\\org\\example\\mplayer\\images\\naruto.jpg",lyric);
//        Podcast m2 = new Podcast("Many-Nights","third","Namikaze Minato",date, Genre.POP,"https://dl.psarena.ir/wp-content/uploads/2021/06/23.-Many-Nights.mp3","naruto.jpg",lyric);
        Database.getDatabase().getAllAudio().add(m2);
        m2.setNumberOfLikes(3);
        s3.getPodcastList().add(m2);
        m2 = new Podcast("Departure-to-the-Front-Lines","third","Namikaze Minato",date, Genre.POP,"E:\\Programming\\Musicplayer\\firstproject-musicplayer-phase2-marydar\\mplayer\\src\\main\\resources\\org\\example\\mplayer\\Songs\\08.-Departure-to-the-Front-Lines.mp3","E:\\Programming\\Musicplayer\\firstproject-musicplayer-phase2-marydar\\mplayer\\src\\main\\resources\\org\\example\\mplayer\\images\\war.jpg",lyric2);
//        m2 = new Podcast("Departure-to-the-Front-Lines","third","Namikaze Minato",date, Genre.POP,"https://dl.psarena.ir/wp-content/uploads/2021/06/08.-Departure-to-the-Front-Lines.mp3","war.jpg",lyric2);
        Database.getDatabase().getAllAudio().add(m2);
        s3.getPodcastList().add(m2);



        launch();
    }
}