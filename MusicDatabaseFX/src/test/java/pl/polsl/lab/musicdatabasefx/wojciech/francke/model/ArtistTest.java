package pl.polsl.lab.musicdatabasefx.wojciech.francke.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 *
 * @author Wojciech Francke
 * @version 1.0
 */
public class ArtistTest {
    
    private Artist artist;

    @Test
    public void testFindAlbum() {
        artist = new Artist("test", "testowo");
        artist.getDiscography().add(new Album("estt", 2020, "none"));
        artist.getDiscography().add(new Album("tset", 2021, "some"));
        artist.getDiscography().add(new Album("sett", 2022, "all"));
        
        Album result = artist.findAlbum("tset");
        assertEquals(result.getReleaseYear(), 2021, "Release year of found album should match 2021");
        
        result = artist.findAlbum("sett");
        assertEquals(result.getReleaseYear(), 2022, "Release year of found album should match 2022");
        
        result = artist.findAlbum("thisdoesntexist");
        assertEquals(result, null, "If album is not present in discography result should be null");
    }
    
    /*
    @ParameterizedTest
    @CsvSource({"tset,2021", "sett,2022"})
    public void testFindAlbum(String title, int actualYear) {
        artist = new Artist("test", "testowo");
        artist.getDiscography().add(new Album("estt", 2020, "none"));
        artist.getDiscography().add(new Album("tset", 2021, "some"));
        artist.getDiscography().add(new Album("sett", 2022, "all"));
        
        Album result = artist.findAlbum(title);
        assertEquals(result.getReleaseYear(), actualYear, "Release year of found album should match");
    }
*/
}
