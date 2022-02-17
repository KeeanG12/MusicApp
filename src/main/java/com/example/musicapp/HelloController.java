package com.example.musicapp;

import javafx.fxml.FXML;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URISyntaxException;

public class HelloController {
    

    MediaPlayer player;
    @FXML
    protected void onHelloButtonClick() throws URISyntaxException {
        String path = "Stronger";
        //Uses JavaFX-Media to play a MP3 file when button is clicked
        Media sound = new Media(getClass().getResource("/Tunes/" + path + ".mp3").toURI().toString());
        player = new MediaPlayer(sound);
        player.play();
    }
}