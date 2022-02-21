package com.example.musicapp;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;

import java.io.File;
import java.io.IOException;
import java.sql.*;

public class Database {

    String artist = "Create table if not exists artist(artistID integer primary key NOT NULL, name string NOT NULL)";

    String album = "Create table if not exists album(albumID integer primary key NOT NULL, name string NOT NULL, produced DATE, artist integer, foreign key (artist) REFERENCES artist (artistID));";

    String song = "Create table if not exists songs(songID integer primary key NOT NULL, name string NOT NULL, album Integer, Foreign key (album) references album (albumID));";
    
    private static Database music = null;
    
    private Database() {
        
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
    
    public void startUp() throws SQLException {

        try (Connection conn = this.connect();
             //Uses prepared statement to pass input parameters
            Statement statement = conn.createStatement()) {
                statement.executeUpdate(artist);
                statement.executeUpdate(album);
                statement.executeUpdate(song);
                
        } catch ( SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertArtist(int artistID, String artistName) {
        String artist = "INSERT INTO artist(artistID, name) VALUES(?,?)";
        String song = "INSERT INTO song(songID, name, albumID) VALUES(?,?,?)";

        String insert = "Insert into artist(artistID, name) VALUES (1, 'Kanye West')";
        try (Connection conn = this.connect();
             //Uses prepared statement to pass input parameters
             PreparedStatement statement = conn.prepareStatement(artist)) {
            Statement statement1 = conn.createStatement();

            statement.setInt(1, artistID);
            statement.setString(2, artistName);
            ResultSet rs = statement1.executeQuery("select * from artist");
            statement.executeUpdate();
            while (rs.next()) {
                // read the result set
                System.out.println("ArtistID = " + rs.getInt("artistID"));
                System.out.println("Name = " + rs.getString("name"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


        public void insertAlbum (int artistID, int albumID, String albumName, int produced) {
        
            String album = "INSERT INTO album(albumID, name, produced, artistID) VALUES(?,?,?,?)";

            try (Connection conn = this.connect();
                 //Uses prepared statement to pass input parameters
                 PreparedStatement statement = conn.prepareStatement(album)) {
                Statement statement1 = conn.createStatement();

                statement.setInt(1, albumID);
                statement.setString(2, albumName);
                statement.setInt(3, produced);
                statement.setInt(4, artistID);
                ResultSet rs = statement1.executeQuery("select * from album");
                statement.executeUpdate();
                while (rs.next()) {
                    // read the result set
                    System.out.println("AlbumID = " + rs.getInt("albumID"));
                    System.out.println("Name = " + rs.getString("name"));
                    System.out.println("Produced = " + rs.getInt("produced"));
                    System.out.println("ArtistID = " + rs.getInt("artist"));
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        
        public void insertSong(int albumID, int songID, String songName) {

            String song = "INSERT INTO song(songID, name, albumID) VALUES(?,?,?)";
            
            try (Connection conn = this.connect();
                 //Uses prepared statement to pass input parameters
                 PreparedStatement statement = conn.prepareStatement(song)) {
                Statement statement1 = conn.createStatement();

                statement.setInt(1, songID);
                statement.setString(2, songName);
                statement.setInt(3, albumID);
                ResultSet rs = statement1.executeQuery("select * from song");
                statement.executeUpdate();
                while (rs.next()) {
                    // read the result set
                    System.out.println("SongID = " + rs.getInt("albumID"));
                    System.out.println("Name = " + rs.getString("name"));
                    System.out.println("AlbumID = " + rs.getInt("album"));
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        }

    //Variables for Number of files, Number of directories and track number
    private int numFiles;
    private int numDirectories;

    int id = 1;

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
        Database music = Database.getInstance();
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
            }
            id++;
            numFiles++;
        } else {
            numDirectories++;
            File[] files = file.listFiles();
            for (File otherFile : files) {
                scanAndPopulate(otherFile);
            }
        }
    }
    
    public static Database getInstance() {
        if (music == null)
            music = new Database();
        return music;
    }
    
    
}
