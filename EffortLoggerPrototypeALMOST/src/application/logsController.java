package application;

import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class logsController extends sceneController implements Initializable {

    // Reference to the TableView UI component
    @FXML
    private TableView<logsSetup> table;

    // Columns in the TableView
    @FXML
    private TableColumn<logsSetup, Integer> index;
    @FXML
    private TableColumn<logsSetup, String> date;
    @FXML
    private TableColumn<logsSetup, String> start;
    @FXML
    private TableColumn<logsSetup, String> stop;
    @FXML
    private TableColumn<logsSetup, String> elapsed;
    @FXML
    private TableColumn<logsSetup, String> lifeCycle;
    @FXML
    private TableColumn<logsSetup, String> deliverable;

    // Uncomment the following ObservableList and populate it with data from effortConsoleController
    /*
    ObservableList<logsSetup> list = FXCollections.observableArrayList(
            new logsSetup( // somehow transfer over the data from effortConsoleController over here and add them to the columns as rows
    );
    */

    // Called when the FXML file is loaded
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Set up the PropertyValueFactory for each TableColumn
        index.setCellValueFactory(new PropertyValueFactory<logsSetup, Integer>("index"));
        date.setCellValueFactory(new PropertyValueFactory<logsSetup, String>("date"));
        start.setCellValueFactory(new PropertyValueFactory<logsSetup, String>("start"));
        stop.setCellValueFactory(new PropertyValueFactory<logsSetup, String>("stop"));
        elapsed.setCellValueFactory(new PropertyValueFactory<logsSetup, String>("elapsed"));
        lifeCycle.setCellValueFactory(new PropertyValueFactory<logsSetup, String>("lifeCycle"));
        deliverable.setCellValueFactory(new PropertyValueFactory<logsSetup, String>("deliverable"));

        // Uncomment the following line to set the items in the TableView
        // table.setItems(list);
    }
}
