package SongsProgram;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;

/**
 *
 * @author zuro
 */
public class SongDAO {

    /**
     * This code establishes a connection with an SQL database using the jdbc connection class
     * @return Connection - return the connection to the database,
     * or null if the connection fails
     */
    public Connection getConnection()
    {
        Connection conn = null;

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            // Create a connection string
            String connectionUrl = "jdbc:sqlserver://ZEPHYR14US\\MSSQLSERVER04;" +
                    "DatabaseName=Songs;" +
                    "User=javaApps;" +
                    "Password=Java233";

            // Establish connection
            conn = DriverManager.getConnection(connectionUrl);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return conn;
    }

    /**
     * Connects to the database and get all songs
     * @return ArrayList - returns an arrayList of all songs in the database
     */
    public ArrayList getSongs() {

        // Create an array list to store songs
        ArrayList songs = new ArrayList();

        // Establish a connection with the database
        Connection conn = getConnection();
        ResultSet rs = null;


        try {

            // Create an sql query to get songs
            Statement stmt = conn.createStatement();

            String query = "SELECT id, title, artist, releaseYear, genreId, album FROM Songs";

            // Send the sql query
            rs = stmt.executeQuery(query);

            // Parse the result
            while (rs.next())
            {
                // Create a new song using data from the result set
                Song s = new Song(rs.getInt(1),
                                  rs.getString(2),
                                  rs.getString(3),
                                  rs.getInt(4),
                                  rs.getInt(5),
                                  rs.getString(6)
                );

                // Add that song to the ArrayList
                songs.add(s);
            }

            // Close the connection
            conn.close();

        }
        catch (SQLException sqle) {

            System.out.println("A problem occured with the SQL syntax when trying to get" +
                    "all songs from the database");

            sqle.printStackTrace();
        }
        catch (Exception e) {

            System.out.println("A problem occured with the SQL syntax when trying to get" +
                    "all songs from the database");

            e.printStackTrace();
        }

        // Return songs
        return songs;
    }

    /**
     * Accepts a song and updates the database. Uses the id of the song object and
     * update all information with the info in the Song object
     * @param s - Information used to update database
     */
    public void updateSongById(Song s) {

        // Establish a connection
        Connection conn = getConnection();

        try {
            // Create an SQL query
            Statement stmt = conn.createStatement();

            // Make sure strings have quote '%s'
            String query = String.format("UPDATE Songs SET title='%s', artist='%s', releaseYear=%d, genreId=%d, album='%s' WHERE id=%d",
                    s.getTitle(), s.getArtist(), s.getReleaseYear(), s.getGenreId(), s.getAlbum(), s.getId());

            // Send the query
            stmt.executeUpdate(query);

            // Close connection to database
            conn.close();
        }
        catch (Exception ex) {

            ex.printStackTrace();
        }


    }

    public static void main(String[] args) {

        SongDAO songdao = new SongDAO();

        Connection tempConnection = songdao.getConnection();

        ArrayList songs = songdao.getSongs();

        for (int i = 0; i < songs.size(); i++) {
            // Cast item into song
            Song song = (Song) songs.get(i);

            System.out.println("Title: " + song.getTitle() + " Artist: " + song.getArtist());

            if (song.getId() == 4) {
                song.setArtist("Slim Shady");
                songdao.updateSongById(song);
            }
        }
    }
}
