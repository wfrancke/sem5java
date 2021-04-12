package pl.polsl.lab.musicdatabasefx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import pl.polsl.lab.musicdatabasefx.wojciech.francke.model.Track;

/**
 * Controller class for addtrack.fxml file, which is used to register new
 * tracks to the model
 *
 * @version 1.0
 * @author Wojciech Francke
 */
public class AddTrackController {

    @FXML private TextField titleTextField;
    @FXML private TextField lengthTextField;
    @FXML private Button okButton;
    
    private TracksController controller;
    
    /**
     * Method that checks if input data is valid, then registers new track and
     * refreshes the table
     * 
     * @param event button event
     */
    @FXML private void confirm(ActionEvent event){
        if (!"".equals(titleTextField.getText())
                && !"".equals(lengthTextField.getText())
                && controller.getController().getController().isInt(lengthTextField.getText())) {
            Track track = new Track(titleTextField.getText(), Integer.parseInt(lengthTextField.getText()));
            controller.getAlbum().getTracklist().add(track);
            controller.refreshTable(controller.getAlbum().getTracklist());
            controller.getController().refreshTable(controller.getController().getArtist().getDiscography());
        }
    }   
    
    /**
     * Controller setter
     *
     * @param controller reference to track screen controller
     */
    public void setController(TracksController controller) {
        this.controller = controller;
    }  
    
}
