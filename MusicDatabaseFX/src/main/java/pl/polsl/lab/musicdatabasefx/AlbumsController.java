package pl.polsl.lab.musicdatabasefx;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleIntegerProperty;
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
import pl.polsl.lab.musicdatabasefx.wojciech.francke.model.Artist;

/**
 * Controller class for albums.fxml file, which represents the albums table.
 *
 * @version 1.0
 * @author Wojciech Francke
 */
public class AlbumsController implements Initializable {

    @FXML private TableView<Album> table = new TableView();
    @FXML private TableColumn<Album, String> titleColumn;
    @FXML private TableColumn<Album, String> genreColumn;
    @FXML private TableColumn<Album, Number> releaseYearColumn;
    @FXML private TableColumn<Album, Number> numberOfTracksColumn;
    @FXML private TableColumn<Album, String> ratingColumn;
    @FXML private Button addButton;
    @FXML private Button editButton;
    @FXML private Button tracksButton;
    @FXML private Button rateButton;
    @FXML private Button refreshButton;
    
    private ArtistsController controller;
    private Artist artist;
    
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
        genreColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getGenre()));
        releaseYearColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getReleaseYear()));
        numberOfTracksColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getTracklist().size()));
        ratingColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().ratingString()));
        
        table.getColumns().setAll(titleColumn, genreColumn, releaseYearColumn, numberOfTracksColumn, ratingColumn);
        
    }
    
    /**
     * Method responsible for loading "Add new album" window
     * 
     * @param event button event
     */
    @FXML private void addNewAlbum(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("addalbum.fxml"));
        Parent root = loader.load();
        AddAlbumController controller = loader.getController();
        controller.setController(this);
        Stage stage = new Stage();
        stage.setTitle("Add new album");
        stage.setScene(new Scene(root));
        stage.show();
    }
    
    /**
     * Method responsible for loading "Edit album" window
     * 
     * @param event button event
     */
    @FXML private void editAlbum(ActionEvent event) throws IOException {
        if (table.getSelectionModel().getSelectedItem() != null) {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("editalbum.fxml"));
            Parent root = loader.load();
            EditAlbumController controller = loader.getController();
            controller.setController(this);
            controller.setAlbum(table.getSelectionModel().getSelectedItem());
            Stage stage = new Stage();
            stage.setTitle("Edit album");
            stage.setScene(new Scene(root));
            stage.show();
        }
    }
    
    /**
     * Method responsible for loading "Tracks" window
     * 
     * @param event button event
     */
    @FXML private void openTracks(ActionEvent event) throws IOException {
        if (table.getSelectionModel().getSelectedItem() != null) {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("tracks.fxml"));
            Parent root = loader.load();
            TracksController controller = loader.getController();
            controller.setController(this);
            controller.setAlbum(table.getSelectionModel().getSelectedItem());
            Stage stage = new Stage();
            stage.setTitle("Tracks");
            stage.setScene(new Scene(root));
            stage.show();
        }
    }
    
    /**
     * Method responsible for loading "Rate album" window
     * 
     * @param event button event
     */
    @FXML private void rateAlbum(ActionEvent event) throws IOException {
        if (table.getSelectionModel().getSelectedItem() != null) {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("ratealbum.fxml"));
            Parent root = loader.load();
            RateAlbumController controller = loader.getController();
            controller.setController(this);
            controller.setAlbum(table.getSelectionModel().getSelectedItem());
            Stage stage = new Stage();
            stage.setTitle("Rate album");
            stage.setScene(new Scene(root));
            stage.show();
        }
    }
    
    /**
     * Method responsible for refreshing the table, used
     * when you open Album window and the already existing objects
     * haven't loaded
     * 
     * @param event button event
     */
    @FXML private void refresh(ActionEvent event) {
        this.refreshTable(artist.getDiscography());
    }
    
    /**
     * Method that is used to refresh table at the end of some functions
     * 
     * @param discography List of albums that is placed in the table
     */
    public void refreshTable(List<Album> discography) {
        table.getItems().clear();
        table.getItems().addAll(discography);
    }
    
    /**
     * Controller getter
     *
     * @return reference to artist screen controller
     */
    public ArtistsController getController() {
        return controller;
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
     * @param artist reference to artist whose discography is shown
     */
    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    /**
     * Artist getter
     *
     * @return reference to artist whose discography is shown
     */
    public Artist getArtist() {
        return artist;
    }
}
