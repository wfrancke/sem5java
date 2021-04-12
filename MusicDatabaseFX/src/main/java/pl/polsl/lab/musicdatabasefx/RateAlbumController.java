package pl.polsl.lab.musicdatabasefx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import pl.polsl.lab.musicdatabasefx.wojciech.francke.model.Album;
import pl.polsl.lab.musicdatabasefx.wojciech.francke.model.AlbumException;

/**
 * Controller class for ratealbum.fxml file, which is used to register
 * new ratings for an album
 *
 * @version 1.0
 * @author Wojciech Francke
 */
public class RateAlbumController {
    
    @FXML private TextField ratingTextField;
    @FXML private Button okButton;
    
    private AlbumsController controller;
    private Album album;
    
    /**
     * Method that checks if input data is valid, then registers new rating and
     * refreshes the table
     * 
     * @param event button event
     */
    @FXML private void confirm(ActionEvent event) throws AlbumException {
        if (!"".equals(ratingTextField.getText())
                && controller.getController().isInt(ratingTextField.getText())) {
            album.addRating(Double.parseDouble(ratingTextField.getText()));
            controller.refreshTable(controller.getArtist().getDiscography());
        }
    }

    /**
     * Controller setter
     *
     * @param controller reference to album screen controller
     */
    public void setController(AlbumsController controller) {
        this.controller = controller;
    }

    /**
     * Album setter
     *
     * @param album reference to Album object that will receive the rating
     */
    public void setAlbum(Album album) {
        this.album = album;
    }
}
