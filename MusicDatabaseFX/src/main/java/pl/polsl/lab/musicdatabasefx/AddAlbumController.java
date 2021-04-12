/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.lab.musicdatabasefx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import pl.polsl.lab.musicdatabasefx.wojciech.francke.model.Album;

/**
 * Controller class for addalbum.fxml file, which is used to register new
 * albums to the model
 *
 * @version 1.0
 * @author Wojciech Francke
 */
public class AddAlbumController {

    @FXML private TextField titleTextField;
    @FXML private TextField releaseYearTextField;
    @FXML private TextField genreTextField;
    @FXML private Button okButton;
    
    private AlbumsController controller;
    
    /**
     * Method that checks if input data is valid, then registers new album and
     * refreshes the table
     * 
     * @param event button event
     */
    @FXML private void confirm(ActionEvent event){
        if (!"".equals(titleTextField.getText())
                && !"".equals(releaseYearTextField.getText())
                && !"".equals(genreTextField.getText())
                && controller.getController().isInt(releaseYearTextField.getText())
                && controller.getArtist().findAlbum(titleTextField.getText()) == null) {
            Album album = new Album(titleTextField.getText(), Integer.parseInt(releaseYearTextField.getText()), genreTextField.getText());
            controller.getArtist().getDiscography().add(album);
            controller.refreshTable(controller.getArtist().getDiscography());
            controller.getController().refreshTable(controller.getController().getModel());
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
    
}
