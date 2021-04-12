package pl.polsl.lab.musicdatabasefx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import pl.polsl.lab.musicdatabasefx.wojciech.francke.model.Artist;

/**
 * Controller class for editartist.fxml file, which is used to edit
 * details of artist that already exists in the model
 *
 * @version 1.0
 * @author Wojciech Francke
 */
public class EditArtistController {

    @FXML private TextField nameTextField;
    @FXML private TextField locationTextField;
    @FXML private Button okButton;
    
    private ArtistsController controller;
    private Artist artist;
    
    /**
     * Method that checks if input data is valid, then edits artist details and
     * refreshes the table
     * 
     * @param event button event
     */
    @FXML private void confirm(ActionEvent event) {
        if (!"".equals(nameTextField.getText())
                && !"".equals(locationTextField.getText())) {
            artist.setName(nameTextField.getText());
            artist.setLocation(locationTextField.getText());
            controller.refreshTable(controller.getModel());
        }
    }
    
    /**
     * Controller setter
     *
     * @param controller reference to artist screen controller
     */
    public void setController(ArtistsController controller) {
        this.controller = controller;
    }

    /**
     * Artist setter
     *
     * @param artist reference to Artist object that will be edited
     */
    public void setArtist(Artist artist) {
        this.artist = artist;
    }
}
