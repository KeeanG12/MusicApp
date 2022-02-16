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
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
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

    public void insert(int trackNum, String trackName, String artist, String album, int year) {

        String sql = "INSERT INTO songs(trackNum, artist, trackName, album, year) VALUES(?,?,?,?,?)";
        String sql2 = "SELECT * FROM artist";
        
        String insert = "Insert into artist(artistID, name) VALUES (1, 'Kanye West')";

//        Insert into artist(artistID, name) VALUES (1, 'Kanye West');
//        Insert into artist(artistID, name) VALUES (2, 'Dave');
//        Insert into artist(artistID, name) VALUES (3, 'Jay-Z');
//        Insert into artist(artistID, name) VALUES (4, 'Isaiah Rashad');
//        Insert into artist(artistID, name) VALUES (5, 'Travis Scott');
        

        
        try (Connection conn = this.connect();
             //Uses prepared statement to pass input parameters
             PreparedStatement pstmt = conn.prepareStatement(sql2)) {
//            Statement statement = conn.createStatement();
//            statement.setQueryTimeout(30);
            
//            pstmt.setInt(1, trackNum);
//            pstmt.setString(2, trackName);
//            pstmt.setString(3, artist);
//            pstmt.setString(4, album);
//            pstmt.setInt(5, year);
//            ResultSet rs = statement.executeQuery("select * from songs");
//            while (rs.next()) {
//                // read the result set
//                System.out.println("Artist = " + rs.getString("artist"));
//                System.out.println("TrackName = " + rs.getString("trackName"));
//                System.out.println("Album = " + rs.getString("album"));
//                System.out.println("Year = " + rs.getInt("year"));
//            }
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    

    //toString method for debugging
    @Override
    public String toString() {
        return "Output{" +
                "numFiles=" + numFiles +
                ", numDirectories=" + numDirectories +
                '}';
    }

    //Variables for Number of files, Number of directories and track number
    private int numFiles;
    private int numDirectories;

    int track = 1;

    /**
     * Scans file recursivley and populates SQLite DB will each files tag data
     * @param file
     * @throws CannotReadException
     * @throws TagException
     * @throws InvalidAudioFrameException
     * @throws ReadOnlyFileException
     * @throws IOException
     */
    private void scanAndPopulate(File file)  {
        HelloApplication app = new HelloApplication();
        //Checks if it is a file
        if (file.isFile()) {
            AudioFile audioFile = null;
            try {
                audioFile = AudioFileIO.read(new File(file.getAbsolutePath()));
            } catch (CannotReadException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (TagException e) {
                e.printStackTrace();
            } catch (ReadOnlyFileException e) {
                e.printStackTrace();
            } catch (InvalidAudioFrameException e) {
                e.printStackTrace();
            }
            Tag tag = audioFile.getTag();

            //Uses the insert method to insert each files tag data into SQLite DB
            for (int i = 1; i < 2; i++) {
                app.insert(track, tag.getFirst(FieldKey.TITLE), tag.getFirst(FieldKey.ARTIST), tag.getFirst(FieldKey.ALBUM), Integer.parseInt(tag.getFirst(FieldKey.YEAR)));
            }
            track++;
            numFiles++;
        } else {
            numDirectories++;
            File[] files = file.listFiles();
            for (File otherFile : files) {
                scanAndPopulate(otherFile);
            }
        }
    }

    public static void main(String[] args)  {
//        HelloApplication app = new HelloApplication();
//        
        Database music = Database.getInstance();
        music.insertArtist(5, "Jay-Z");

//        app.scanAndPopulate(new File("D:\\UniWork\\Third Year\\Major Project\\MusicApp\\src\\main\\resources\\Tunes\\TT-WTT"));
//        System.out.println(app);
//        
//         launch();
    }
    
}