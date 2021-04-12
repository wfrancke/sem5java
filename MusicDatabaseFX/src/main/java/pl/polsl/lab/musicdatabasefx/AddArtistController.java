package pl.polsl.lab.musicdatabasefx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import pl.polsl.lab.musicdatabasefx.wojciech.francke.model.Artist;

/**
 * Controller class for addartist.fxml file, which is used to register new
 * artists to the model
 *
 * @version 1.0
 * @author Wojciech Francke
 */
public class AddArtistController {

    @FXML private TextField nameTextField;
    @FXML private TextField locationTextField;
    @FXML private Button okButton;
    
    private ArtistsController controller;
    
    /**
     * Method that checks if input data is valid, then registers new artist and
     * refreshes the table
     * 
     * @param event button event
     */
    @FXML private void confirm(ActionEvent event) {
        if (!"".equals(nameTextField.getText())
                && !"".equals(locationTextField.getText())
                && controller.findArtist(nameTextField.getText()) == null) {
            Artist artist = new Artist(nameTextField.getText(), locationTextField.getText());
            controller.getModel().add(artist);
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
    
}
