package SongsProgram;

/**
 * Represent a song with its related data
 * @author Vu Cong Bui
 */
public class Song {

    private int id;
    private String title;
    private String artist;
    private int releaseYear;
    private int genreId;
    private String album;

    public Song() {
    }

    public Song(int id, String title, String artist, int releaseYear, int genreId, String album) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.releaseYear = releaseYear;
        this.genreId = genreId;
        this.album = album;
    }

    public Song(String title, String artist, int releaseYear, int genreId, String album) {
        this.title = title;
        this.artist = artist;
        this.releaseYear = releaseYear;
        this.genreId = genreId;
        this.album = album;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getArtist() { return artist; }

    public void setArtist(String artist) { this.artist = artist; }

    public int getReleaseYear() { return releaseYear; }

    public void setReleaseYear(int releaseYear) { this.releaseYear = releaseYear; }

    public int getGenreId() { return genreId; }

    public void setGenreId(int genreId) { this.genreId = genreId; }

    public String getAlbum() { return album; }

    public void setAlbum(String album) { this.album = album; }

    @Override
    /**
     * Return a formatted string of the song
     * @return a string in the format: title | artist | release year | album
     */
    public String toString()
    {
        return String.format("%s | %s | %d | %s", title, artist, releaseYear, album);
    }
}
