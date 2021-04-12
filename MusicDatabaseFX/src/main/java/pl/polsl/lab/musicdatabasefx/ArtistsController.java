package pl.polsl.lab.musicdatabasefx;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import pl.polsl.lab.musicdatabasefx.wojciech.francke.model.Artist;

/**
 * Controller class for artists.fxml file, which represents the main screen
 * of the program - Artists table.
 *
 * @version 1.0
 * @author Wojciech Francke
 */
public class ArtistsController implements Initializable {

    @FXML private TableView<Artist> table = new TableView();
    @FXML private TableColumn<Artist, String> nameColumn;
    @FXML private TableColumn<Artist, String> locationColumn;
    @FXML private TableColumn<Artist, Number> albumNumberColumn;
    @FXML private Button addButton;
    @FXML private Button editButton;
    @FXML private Button albumsButton;
    
    private List<Artist> model;
    
    /**
     * Method responsible for loading "Add new artist" window
     * 
     * @param event button event
     */
    @FXML private void addNewArtist(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("addartist.fxml"));
        Parent root = loader.load();
        AddArtistController controller = loader.getController();
        controller.setController(this);
        Stage stage = new Stage();
        stage.setTitle("Add new artist");
        stage.setScene(new Scene(root));
        stage.show();
    }
    
    /**
     * Method responsible for loading "Edit artist" window
     * 
     * @param event button event
     */
    @FXML private void editArtist(ActionEvent event) throws IOException {
        if (table.getSelectionModel().getSelectedItem() != null) {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("editartist.fxml"));
            Parent root = loader.load();
            EditArtistController controller = loader.getController();
            controller.setController(this);
            controller.setArtist(table.getSelectionModel().getSelectedItem());
            Stage stage = new Stage();
            stage.setTitle("Edit artist");
            stage.setScene(new Scene(root));
            stage.show();
        }
    }
    
    /**
     * Method responsible for loading "Albums" window
     * 
     * @param event button event
     */
    @FXML private void openAlbums(ActionEvent event) throws IOException {
        if (table.getSelectionModel().getSelectedItem() != null) {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("albums.fxml"));
            Parent root = loader.load();
            AlbumsController controller = loader.getController();
            controller.setController(this);
            controller.setArtist(table.getSelectionModel().getSelectedItem());
            Stage stage = new Stage();
            stage.setTitle("Albums");
            stage.setScene(new Scene(root));
            stage.show();
        }
    }
    
    /**
     * Method that is launched when the .fxml file is loaded, used
     * to set the table
     * 
     * @param url unused
     * @param rb unused
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        model = new ArrayList<>();
        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        locationColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLocation()));
        albumNumberColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getDiscography().size()));
        
        table.getColumns().setAll(nameColumn, locationColumn, albumNumberColumn);
    }

    /**
     * Method that is used to refresh table at the end of some functions
     * 
     * @param model List of artists that is placed in the table
     */
    public void refreshTable(List<Artist> model) {
        table.getItems().clear();
        table.getItems().addAll(model);
    }
    
    /**
     * Method used to check if the input string is an integer.
     * 
     * @param text input string
     * @return true if it is an integer, false if it's not
     */
    public boolean isInt(String text) {
        if(text == null) {
            return false;
        }
        try {
            int i = Integer.parseInt(text);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
    
    /**
     * Method checks model looking for artist with set name.
     * 
     * @param name input name of the artist
     * @return if the artist exists in the model, it returns him, if not, null.
     */
    public Artist findArtist(String name) {
        for(Artist art: model) {
            if(art.getName().equals(name))
                return art;
        }
        return null;
    }

    /**
     * Model getter
     *
     * @return model
     */
    public List<Artist> getModel() {
        return model;
    }
}
