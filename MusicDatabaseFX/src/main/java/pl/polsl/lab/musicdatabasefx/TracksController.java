package pl.polsl.lab.musicdatabasefx;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import pl.polsl.lab.musicdatabasefx.wojciech.francke.model.Album;
import pl.polsl.lab.musicdatabasefx.wojciech.francke.model.Track;


/**
 * Controller class for tracks.fxml file, which represents the tracks table.
 *
 * @version 1.0
 * @author Wojciech Francke
 */
public class TracksController implements Initializable {

    @FXML private TableView<Track> table = new TableView();
    @FXML private TableColumn<Track, String> titleColumn;
    @FXML private TableColumn<Track, String> lengthColumn;
    @FXML private Button addButton;
    @FXML private Button editButton;
    @FXML private Button refreshButton;
    
    private AlbumsController controller;
    private Album album;
    
    /**
     * Method that is launched when the .fxml file is loaded, used 
     * to set the table
     * 
     * @param url unused
     * @param rb unused
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        titleColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitle()));
        lengthColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().lengthString()));
        
        table.getColumns().setAll(titleColumn, lengthColumn);
    }
    
    /**
     * Method responsible for loading "Add new track" window
     * 
     * @param event button event
     */
    @FXML private void addNewTrack(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("addtrack.fxml"));
        Parent root = loader.load();
        AddTrackController controller = loader.getController();
        controller.setController(this);
        Stage stage = new Stage();
        stage.setTitle("Add new track");
        stage.setScene(new Scene(root));
        stage.show();
    }
    
    /**
     * Method responsible for loading "Edit track" window
     * 
     * @param event button event
     */
    @FXML private void editTrack(ActionEvent event) throws IOException {
        if (table.getSelectionModel().getSelectedItem() != null) {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("edittrack.fxml"));
            Parent root = loader.load();
            EditTrackController controller = loader.getController();
            controller.setController(this);
            controller.setTrack(table.getSelectionModel().getSelectedItem());
            Stage stage = new Stage();
            stage.setTitle("Edit track");
            stage.setScene(new Scene(root));
            stage.show();
        }
    }
    
    /**
     * Method responsible for refreshing the table, used
     * when you open track window and the already existing objects
     * haven't loaded
     * 
     * @param event button event
     */
    @FXML private void refresh(ActionEvent event) {
        this.refreshTable(album.getTracklist());
    }

    /**
     * Method that is used to refresh table at the end of some functions
     * 
     * @param tracklist List of tracks that are placed in the table
     */
    public void refreshTable(List<Track> tracklist) {
        table.getItems().clear();
        table.getItems().addAll(tracklist);
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
     * @param album reference to album which tracklist is shown
     */
    public void setAlbum(Album album) {
        this.album = album;
    }

    /**
     * Controller getter
     *
     * @return reference to album screen controller
     */
    public AlbumsController getController() {
        return controller;
    }

    /**
     * Artist getter
     *
     * @return reference to album which tracklist is shown
     */
    public Album getAlbum() {
        return album;
    }
}
