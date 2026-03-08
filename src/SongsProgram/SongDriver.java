package SongsProgram;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Driver class for the Songs database application.
 * Provide an interface menu for managing songs
 * @author Vu Cng Bui
 */
public class SongDriver {

    private ArrayList songs;
    private Song song;

    private Scanner input;
    private SongDAO songDAO;
    private MyHelperMethods helperMethod;

    public SongDriver() {
        input = new Scanner(System.in);
        helperMethod = new MyHelperMethods();
        songDAO = new SongDAO();
    }

    public static void main(String[] args)
    {
        SongDriver driver = new SongDriver();
        driver.run();
    }

    /**
     * Repeatedly display the menu and process user's choice until user select 5 (exit option)
     */
    public void run()
    {
        int choice = 0;
        while (choice != 5) {
            choice = displayMenu();
            processChoice(choice);
        }
    }

    /**
     * Display the main menu and prompts the user for a choice
     * @return the menu option selected by the user
     */
    public int displayMenu()
    {
        System.out.print("""
                +-------------------------+
                |    SONGS DATABASE       |
                +-------------------------+
                | 1. Display all song     |
                | 2. Get a song by ID     |
                | 3. Update a song by ID  |
                | 4. Delete a song by ID  |
                +-------------------------+
                | 5. Exit                 |
                +-------------------------+
                """);

        return helperMethod.optionSelector(1, 5);
    }

    /**
     * Run the method based on user's menu choice.
     * @param choice - the menu option selected by user
     */
    public void processChoice(int choice)
    {
        switch (choice) {
            case 1 -> displayAllSongs();
            case 2 -> getSongById();
            case 3 -> updateSongById();
            case 4 -> deleteSongById();
        }
    }

    /**
     * Get all songs from the database via SongDAO and display them
     */
    public void displayAllSongs()
    {
        System.out.print("""
                +-----------+
                | ALL SONGS |
                +-----------+
                """);

        songs = songDAO.getSongs();

        for (int i = 0; i < songs.size(); i++)
        {
            System.out.println("* " + songs.get(i));
        }

        System.out.println("------");
        helperMethod.waitForInput();
    }

    /**
     * Prompt user for a song ID and reprompt if the ID is invalid.
     * Retrieve the matching song from the database and display it.
     */
    public void getSongById()
    {
        System.out.print("""
                +------------------+
                |  GET SONG BY ID  |
                +------------------+
                """);

        song = null;
        System.out.print("Song ID: ");

        int id;
        boolean validId = false;
        while (!validId)
        {
            id = helperMethod.validIntegerInput();
            song = songDAO.getSongById(id);

            if (song.getId() == 0)
            {
                System.out.print("INVALID ID\n-> ");
            }
            else {
                validId = true;
                System.out.println("* " + song);
            }
        }
        helperMethod.waitForInput();
    }

    /**
     * Prompt user for a song ID and reprompt if the ID is invalid.
     * Retrieve the matching song from the database.
     * Then prompt user to enter new values for each field.
     * Update the song in the database via SongDao.
     */
    public void updateSongById()
    {
        System.out.print("""
                +-------------------+
                | UPDATE SONG BY ID |
                +-------------------+
                """);

        song = null;
        System.out.print("Song ID: ");

        int id;
        boolean validId = false;

        while (!validId)
        {
            id = helperMethod.validIntegerInput();
            song = songDAO.getSongById(id);

            if (song.getId() == 0)
            {
                System.out.print("INVALID ID\n-> ");
            }
            else {
                validId = true;
                System.out.println("* " + song);
            }
        }

        Scanner multiLine = new Scanner(System.in);

        System.out.print("New title: ");
        song.setTitle(multiLine.nextLine());

        System.out.print("New artist: ");
        song.setArtist(multiLine.nextLine());

        System.out.print("New release year: ");
        song.setReleaseYear(helperMethod.validIntegerInput());

        System.out.print("New genre ID: ");
        song.setGenreId(helperMethod.validIntegerInput());

        System.out.print("New album: ");
        song.setAlbum(multiLine.nextLine());

        songDAO.updateSongById(song);
        System.out.println("Updated: " +  song);

        helperMethod.waitForInput();
    }

    /**
     * Prompt user for a song ID, display the matching song and ask for confirmation
     * before delete it from the database via SongDAO.
     */
    public void deleteSongById()
    {
        System.out.print("""
                +-------------------+
                | DELETE SONG BY ID |
                +-------------------+
                """);

        song = null;
        System.out.print("Song ID: ");

        int id;
        boolean validId = false;

        while (!validId)
        {
            id = helperMethod.validIntegerInput();
            song = songDAO.getSongById(id);

            if (song.getId() == 0)
            {
                System.out.print("INVALID ID\n-> ");
            }
            else {
                validId = true;
                System.out.println("* " + song);
            }
        }

        System.out.println("Do you want to delete this song?\n1. Yes\n2. No");

        boolean delete = false;
        int choice = helperMethod.optionSelector(1,2);
        if (choice == 1) {
            String title = song.getTitle();
            songDAO.deleteSongById(song.getId());
            System.out.printf("Song %s was removed from the database\n", title);
        }

        helperMethod.waitForInput();
    }
}
