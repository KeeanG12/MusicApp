package com.example.musicapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        stage.setTitle("KG Music Player");
        stage.setScene(scene);
        stage.show();
    }

    private Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:music.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

//    public void insert(int trackNum, String trackName, String artist, String album, int year) {
//
//        String sql = "INSERT INTO songs(trackNum, artist, trackName, album, year) VALUES(?,?,?,?,?)";
//        String sql2 = "SELECT * FROM artist";
//        
//        String insert = "Insert into artist(artistID, name) VALUES (1, 'Kanye West')";
//
////        Insert into artist(artistID, name) VALUES (1, 'Kanye West');
////        Insert into artist(artistID, name) VALUES (2, 'Dave');
////        Insert into artist(artistID, name) VALUES (3, 'Jay-Z');
////        Insert into artist(artistID, name) VALUES (4, 'Isaiah Rashad');
////        Insert into artist(artistID, name) VALUES (5, 'Travis Scott');
//        
//
//        
//        try (Connection conn = this.connect();
//             //Uses prepared statement to pass input parameters
//             PreparedStatement pstmt = conn.prepareStatement(sql2)) {
////            Statement statement = conn.createStatement();
////            statement.setQueryTimeout(30);
//            
////            pstmt.setInt(1, trackNum);
////            pstmt.setString(2, trackName);
////            pstmt.setString(3, artist);
////            pstmt.setString(4, album);
////            pstmt.setInt(5, year);
////            ResultSet rs = statement.executeQuery("select * from songs");
////            while (rs.next()) {
////                // read the result set
////                System.out.println("Artist = " + rs.getString("artist"));
////                System.out.println("TrackName = " + rs.getString("trackName"));
////                System.out.println("Album = " + rs.getString("album"));
////                System.out.println("Year = " + rs.getInt("year"));
////            }
//            pstmt.executeUpdate();
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//
//    }

    public static void main(String[] args)  {
//        HelloApplication app = new HelloApplication();
////        
//        Database music = Database.getInstance();
//
//        app.scanAndPopulate(new File("D:\\UniWork\\Third Year\\Major Project\\MusicApp\\src\\main\\resources\\Tunes\\TT-WTT"));
//        System.out.println(app);
//        
         launch();
    }
    
}