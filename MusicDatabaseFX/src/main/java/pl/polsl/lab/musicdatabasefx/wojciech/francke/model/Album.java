package pl.polsl.lab.musicdatabasefx.wojciech.francke.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Album class is used to store album data
 *
 * @author Wojciech Francke
 * @version 3.1
 */
public class Album {
    private String title;
    private List<Track> tracklist;
    private int releaseYear;
    private String genre;
    private double avgRating;
    private int ratings;
    
    /**
     * Album constructor
     *
     * @param title set album title
     * @param releaseYear set album release year
     * @param genre set album genre
     */
    public Album(String title, int releaseYear, String genre){
        this.title = title;
        this.tracklist = new ArrayList<Track>();
        this.releaseYear = releaseYear;
        this.genre = genre;
        this.avgRating = 0;
        this.ratings = 0;
    }

    /**
     * Album title getter
     *
     * @return album title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Album track list getter
     *
     * @return album track list
     */
    public List<Track> getTracklist() {
        return tracklist;
    }

    /**
     * Album release year getter
     *
     * @return album release year
     */
    public int getReleaseYear() {
        return releaseYear;
    }

    /**
     * Album genre getter
     *
     * @return album genre
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Album average rating getter
     *
     * @return album average rating
     */
    public double getAvgRating() {
        return avgRating;
    }

    /**
     * Album ratings number getter
     *
     * @return album number of ratings
     */
    public int getRatings() {
        return ratings;
    }

    /**
     * Album title setter
     *
     * @param title new album title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Album tracklist setter
     *
     * @param tracklist new album tracklist
     */
    public void setTracklist(List<Track> tracklist) {
        this.tracklist = tracklist;
    }

    /**
     * Album ratings release year setter
     *
     * @param releaseYear new album release year
     */
    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    /**
     * Album genre setter
     *
     * @param genre new album genre
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }
    
    /**
     * Method that registers new album rating
     *
     * @param rating new album rating
     * @throws AlbumException
     */
    public void addRating(double rating) throws AlbumException {
        if (rating < 1 || rating > 10) {
            throw new AlbumException("Album rating should be between 1 and 10");
        } else {
            this.avgRating = (this.ratings * this.avgRating + rating) / (this.ratings + 1);
            this.ratings++;
        }
    }
    
    /**
     * Method that converts album score to string
     *
     * @return score string
     */
    public String ratingString() {
        return (this.avgRating + ", out of " + this.ratings);
    }
}
