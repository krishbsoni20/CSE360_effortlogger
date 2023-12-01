//edited to make log console conpatible to the effortlogger tool.
package application;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

// Class to display activity logs
public class LogConsole {
    private Stage logWindow; // Stage for the log window
    private final ObservableList<LogEntry> logEntries; // ObservableList to hold log entries

    // Constructor to initialize LogConsole with log entries
    public LogConsole(ObservableList<LogEntry> logEntries) {
        this.logEntries = logEntries;
    }

    // Method to show the log window
    public void showLogWindow() {
        // If log window is not initialized, create and set up the window
        if (logWindow == null) {
            logWindow = new Stage();
            logWindow.setTitle("Activity Logs"); // Set window title

            TableView<LogEntry> logTable = new TableView<>(logEntries); // Create a table view for log entries
            setupLogTable(logTable); // Set up the columns and properties for the log table

            VBox logLayout = new VBox(10); // Create a vertical box layout
            logLayout.getChildren().add(logTable); // Add log table to the layout

            Scene logScene = new Scene(logLayout, 800, 400); // Create a scene with the layout
            logWindow.setScene(logScene); // Set the scene for the log window
        }
        logWindow.show(); // Show the log window
    }

    // Method to set up columns and properties for the log table
    private void setupLogTable(TableView<LogEntry> logTable) {
        // Columns setup

        // Index column
        TableColumn<LogEntry, Integer> indexCol = new TableColumn<>("Index");
        indexCol.setCellValueFactory(new PropertyValueFactory<>("index"));

        // Start Time column
        TableColumn<LogEntry, String> startTimeCol = new TableColumn<>("Start Time");
        startTimeCol.setCellValueFactory(new PropertyValueFactory<>("formattedStartTime"));

        // Stop Time column
        TableColumn<LogEntry, String> stopTimeCol = new TableColumn<>("Stop Time");
        stopTimeCol.setCellValueFactory(new PropertyValueFactory<>("formattedStopTime"));

        // Elapsed Time column
        TableColumn<LogEntry, String> elapsedTimeCol = new TableColumn<>("Elapsed Time");
        elapsedTimeCol.setCellValueFactory(new PropertyValueFactory<>("elapsedTime"));

        // Life Cycle column
        TableColumn<LogEntry, String> lifeCycleCol = new TableColumn<>("Life Cycle");
        lifeCycleCol.setCellValueFactory(new PropertyValueFactory<>("lifeCycle"));

        // Project column
        TableColumn<LogEntry, String> projectCol = new TableColumn<>("Project");
        projectCol.setCellValueFactory(new PropertyValueFactory<>("project"));

        // Add columns to the log table
        logTable.getColumns().addAll(indexCol, startTimeCol, stopTimeCol, elapsedTimeCol, lifeCycleCol, projectCol);
    }
}
