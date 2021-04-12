package pl.polsl.lab.musicdatabasefx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import pl.polsl.lab.musicdatabasefx.wojciech.francke.model.Album;

/**
 * Controller class for editalbum.fxml file, which is used to edit
 * details of album that already exists in the model
 *
 * @version 1.0
 * @author Wojciech Francke
 */
public class EditAlbumController {

   @FXML private TextField titleTextField;
    @FXML private TextField releaseYearTextField;
    @FXML private TextField genreTextField;
    @FXML private Button okButton;
    
    private AlbumsController controller;
    private Album album;
    
    /**
     * Method that checks if input data is valid, then edits album details and
     * refreshes the table
     * 
     * @param event button event
     */
    @FXML private void confirm(ActionEvent event) {
        if (!"".equals(titleTextField.getText())
                && !"".equals(releaseYearTextField.getText())
                && !"".equals(genreTextField.getText())
                && controller.getController().isInt(releaseYearTextField.getText())) {
            album.setTitle(titleTextField.getText());
            album.setReleaseYear(Integer.parseInt(releaseYearTextField.getText()));
            album.setGenre(genreTextField.getText());
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
     * @param album reference to Album object that will be edited
     */
    public void setAlbum(Album album) {
        this.album = album;
    }
}
