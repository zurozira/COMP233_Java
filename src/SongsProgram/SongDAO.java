package SongsProgram;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;

/**
 * Data access object for the Songs database.
 * Provide methods to get, update, delete song records
 * using JDBC connection to Microsoft SQL Server database
 * @author Vu Cong Bui
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

            String connectionUrl = "jdbc:sqlserver://ZEPHYR14US\\MSSQLSERVER04;" +
                    "DatabaseName=Songs;" +
                    "User=javaApps;" +
                    "Password=Java233";

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
    public ArrayList getSongs()
    {
        ArrayList songs = new ArrayList();
        Connection conn = getConnection();
        ResultSet rs = null;

        try {
            Statement stmt = conn.createStatement();

            String query = "SELECT id, title, artist, releaseYear, genreId, album FROM Songs";

            rs = stmt.executeQuery(query);

            while (rs.next())
            {
                Song s = new Song(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getString(6)
                );
                songs.add(s);
            }

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

        return songs;
    }

    /**
     * Get one song from database by ID number
     * @param id - the unique identifier of the song to get
     * @return the Song object matching the ID or empty song object if there is no match
     */
    public Song getSongById(int id)
    {
        Connection conn = getConnection();
        ResultSet rs = null;
        Song song = new Song();

        try {
            Statement stmt = conn.createStatement();
            String query = String.format("SELECT ID, Title, Artist, ReleaseYear, GenreId, Album from Songs WHERE ID=%d", id);

            rs = stmt.executeQuery(query);

            if (rs.next()) {
                song = new Song(rs.getInt("ID"),
                        rs.getString("Title"),
                        rs.getString("Artist"),
                        rs.getInt("ReleaseYear"),
                        rs.getInt("genreID"),
                        rs.getString("Album")
                );
            }

            conn.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return song;
    }

    /**
     * Accepts a song and updates the database. Uses the id of the song object and
     * update all information with the info in the Song object
     * @param s - Information used to update database
     */
    public void updateSongById(Song s)
    {
        Connection conn = getConnection();

        try {
            Statement stmt = conn.createStatement();

            String query = String.format("UPDATE Songs SET title='%s', artist='%s', releaseYear=%d, genreId=%d, album='%s' WHERE id=%d",
                    s.getTitle(), s.getArtist(), s.getReleaseYear(), s.getGenreId(), s.getAlbum(), s.getId());

            stmt.executeUpdate(query);
            conn.close();
        }
        catch (Exception ex) {

            ex.printStackTrace();
        }
    }

    /**
     * Delete a song from the database by ID number
     * @param id the unique identifier of the song to delete
     */
    public void deleteSongById(int id)
    {
        Connection conn = getConnection();

        try {
            Statement stmt = conn.createStatement();
            String query = String.format("DELETE FROM Songs WHERE ID=%d", id);
            stmt.executeUpdate(query);
            conn.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
