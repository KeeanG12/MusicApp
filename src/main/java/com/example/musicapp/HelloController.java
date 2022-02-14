package com.example.musicapp;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URISyntaxException;

public class HelloController {
    

    @FXML
    protected void onHelloButtonClick() throws URISyntaxException {
        String path = "Stronger.mp3";
        //Uses JavaFX-Media to play a MP3 file when button is clicked
        Media sound = new Media(getClass().getResource("/Free Lunch.mp3").toURI().toString());
        MediaPlayer player = new MediaPlayer(sound);
        player.play();
    }
}