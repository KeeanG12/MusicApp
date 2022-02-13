module com.example.musicapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.musicapp to javafx.fxml;
    exports com.example.musicapp;
}