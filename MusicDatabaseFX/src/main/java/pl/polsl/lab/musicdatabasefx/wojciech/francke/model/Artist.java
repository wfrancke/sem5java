package pl.polsl.lab.musicdatabasefx.wojciech.francke.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Artist class is used to store artist data
 *
 * @author Wojciech Francke
 * @version 2.1
 */
public class Artist {
    private String name;
    private String location;
    private List<Album> discography;
    
    /**
     * Artist constructor
     *
     * @param name set name for artist
     * @param location set location for artist
     */
    public Artist(String name, String location){
        this.name = name;
        this.location = location;
        this.discography = new ArrayList<>();
    }

    /**
     * Artist name getter
     *
     * @return artists name
     */
    public String getName() {
        return name;
    }

    /**
     * Artist location getter
     *
     * @return artists location
     */
    public String getLocation() {
        return location;
    }

    /**
     * Artist discography getter
     *
     * @return artists discography
     */
    public List<Album> getDiscography() {
        return discography;
    }

    /**
     * Artist name setter
     *
     * @param name new artist name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Artist location setter
     *
     * @param location new artist location
     */
    public void setLocation(String location) {
        this.location = location;
    }
    
    /**
     * Method checks model looking for a set artists album with set title.
     * 
     * @param title input title of the album
     * @return if the album exists in the discography, it returns it, if not, null.
     */
    public Album findAlbum(String title) {
        for(Album alb: this.discography) {
            if(alb.getTitle().equals(title))
                return alb;
        }
        return null;
    }
}
