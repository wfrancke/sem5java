package pl.polsl.lab.musicdatabasefx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import pl.polsl.lab.musicdatabasefx.wojciech.francke.model.Track;

/**
 * Controller class for editatrack.fxml file, which is used to edit
 * details of track that already exists in the model
 *
 * @version 1.0
 * @author Wojciech Francke
 */
public class EditTrackController {
    
    @FXML private TextField titleTextField;
    @FXML private TextField lengthTextField;
    @FXML private Button okButton;
    
    private TracksController controller;
    private Track track;
    
    /**
     * Method that checks if input data is valid, then edits tracks details and
     * refreshes the table
     * 
     * @param event button event
     */
    @FXML private void confirm(ActionEvent event){
        if (!"".equals(titleTextField.getText())
                && !"".equals(lengthTextField.getText())
                && controller.getController().getController().isInt(lengthTextField.getText())) {
            track.setTitle(titleTextField.getText());
            track.setLenght(Integer.parseInt(lengthTextField.getText()));
            controller.refreshTable(controller.getAlbum().getTracklist());
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

    /**
     * Track setter
     *
     * @param track reference to Track object that will be edited
     */
    public void setTrack(Track track) {
        this.track = track;
    }
}
