package pl.polsl.lab.musicdatabasefx.wojciech.francke.model;

/**
 * Track class is used to store track data
 *
 * @author Wojciech Francke
 * @version 3.1
 */
public class Track {
    private String title;
    private int lenght;
    
    /**
     * Track constructor
     *
     * @param title set track title
     * @param length set track length in seconds
     */
    public Track(String title, int length){
        this.title = title;
        this.lenght = length;
    }

    /**
     * Track title getter
     *
     * @return track title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Track length getter
     *
     * @return track length in seconds
     */
    public int getLenght() {
        return lenght;
    }

    /**
     * Track title setter
     *
     * @param title new track title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Track length setter
     *
     * @param lenght new track length
     */
    public void setLenght(int lenght) {
        this.lenght = lenght;
    }
    
    /**
     * Method that converts track length to MM:SS format
     *
     * @return length string
     */
    public String lengthString() {
        return(this.lenght/60 + ":" + this.lenght%60);
    }
}
