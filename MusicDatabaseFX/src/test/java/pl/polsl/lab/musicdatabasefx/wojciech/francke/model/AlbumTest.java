package pl.polsl.lab.musicdatabasefx.wojciech.francke.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


/**
 *
 * @author Wojciech Francke
 * @version 1.0
 */
public class AlbumTest {
    
    private Album album;

    @Test
    public void testAddRating() {
        try {
            album = new Album("test", 2020, "none");
            album.addRating(8);
            assertEquals(album.getAvgRating(), 8, "Album rating should be updated with first new rating 8");
            assertEquals(album.getRatings(), 1, "Album should have now 1 rating");
        } catch(AlbumException ae) {
            fail("Adding new rating 8 fails");
        }
        
        try {
            album = new Album("test", 2020, "none");
            album.addRating(-5);
            fail("Adding new rating -5 fails");
        } catch(AlbumException ae) {
            
        }
        
        try {
            album = new Album("test", 2020, "none");
            album.addRating(15);
            fail("Adding new rating 15 fails");
        } catch(AlbumException ae) {
            
        }
    }
}
