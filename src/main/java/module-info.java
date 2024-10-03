module org.example.mplayer {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;
    requires javafx.media;


    opens org.example.mplayer to javafx.fxml;
    exports org.example.mplayer;
    exports org.example.mplayer.view;
    opens org.example.mplayer.view to javafx.fxml;
}