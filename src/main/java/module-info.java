module com.example.musicapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires jaudiotagger;
    requires java.sql;
    requires javafx.media;


    opens com.example.musicapp to javafx.fxml;
    exports com.example.musicapp;
}