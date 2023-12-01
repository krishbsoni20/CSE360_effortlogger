//Krish Bhavik Soni - Access Control and Modification restriction
// Import necessary JavaFX libraries
package com.example.demo1;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import javafx.scene.control.cell.PropertyValueFactory;

// Define the main application class that extends Application
public class HelloApplication extends Application {

    // Declare private class variables
    private Stage primaryStage;
    private StackPane mainPane;
    private GridPane loginPane;
    private GridPane logsConsolePane;

    // Define employee usernames and passwords using a Map
    private static final Map<String, String> employeeCredentials = Map.of(
            "employee1", "employee1pass",
            "employee2", "employee2pass",
            "employee3", "employee3pass",
            "employee4", "employee4pass",
            "employee5", "employee5pass"
    );

    // Define supervisor usernames and passwords using a Map
    private static final Map<String, String> supervisorCredentials = Map.of(
            "supervisor1", "supervisor1pass",
            "supervisor2", "supervisor2pass"
    );

    // Create a TableView and an ObservableList to store log entries
    private TableView<LogEntry> logTableView;
    private ObservableList<LogEntry> logEntries = FXCollections.observableArrayList();

    // The main entry point of the application
    public static void main(String[] args) {
        launch(args);
    }

    // Override the start method to create the initial UI
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Log Tool");

        // Create the main dashboard
        createMainDashboard();

        // Initially show the main dashboard
        showMainDashboard();

        primaryStage.show();
    }

    // Create the main dashboard UI with a single button
    private void createMainDashboard() {
        mainPane = new StackPane();
        mainPane.setPadding(new Insets(10, 10, 10, 10));

        Button logButton = new Button("Log");
        logButton.setOnAction(e -> showLoginPane());

        mainPane.getChildren().add(logButton);
    }

    // Create the login pane UI with user input fields
    private void createLoginPane() {
        loginPane = new GridPane();
        loginPane.setPadding(new Insets(10, 10, 10, 10));
        loginPane.setVgap(5);
        loginPane.setHgap(5);

        Label userLabel = new Label("User:");
        TextField userField = new TextField();
        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        Button loginButton = new Button("Login");
        Button returnButton = new Button("Return to Main Dashboard");

        // Handle login button click event
        loginButton.setOnAction(e -> {
            String user = userField.getText();
            String password = passwordField.getText();

            if (isValidCredentials(user, password)) {
                showLogsConsoleForUser(user);
            } else {
                showAlert("Invalid credentials. Please try again.");
            }
        });

        // Handle return button click event
        returnButton.setOnAction(e -> showMainDashboard());

        // Add UI elements to the login pane
        loginPane.add(userLabel, 0, 0);
        loginPane.add(userField, 1, 0);
        loginPane.add(passwordLabel, 0, 1);
        loginPane.add(passwordField, 1, 1);
        loginPane.add(loginButton, 1, 2);
        loginPane.add(returnButton, 0, 2);
    }

    // Show the main dashboard screen
    private void showMainDashboard() {
        Scene mainScene = new Scene(mainPane, 300, 150);
        primaryStage.setScene(mainScene);
    }

    // Show the login screen
    private void showLoginPane() {
        createLoginPane();
        Scene loginScene = new Scene(loginPane, 300, 150);
        primaryStage.setScene(loginScene);
    }

    // Show the logs console for a user
    private void showLogsConsoleForUser(String user) {
        logsConsolePane = createLogsConsolePane();

        Scene logsScene = new Scene(logsConsolePane, 800, 600);
        primaryStage.setScene(logsScene);

        // Populate the log entries (for demonstration purposes)
        logEntries.clear();
        logEntries.add(new LogEntry("2023-10-29", "08:00 AM", "05:00 PM", user, "Task 1"));
        logEntries.add(new LogEntry("2023-10-28", "09:00 AM", "06:00 PM", user, "Task 2"));
        logEntries.add(new LogEntry("2023-10-27", "07:30 AM", "04:30 PM", user, "Task 3"));
    }

    // Check if the provided user credentials are valid
    private boolean isValidCredentials(String user, String password) {
        if (employeeCredentials.containsKey(user) && employeeCredentials.get(user).equals(password)) {
            return true;
        } else if (supervisorCredentials.containsKey(user) && supervisorCredentials.get(user).equals(password)) {
            return true;
        }
        return false;
    }

    // Show an alert dialog with the specified message
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Create the logs console pane with a TableView
    private GridPane createLogsConsolePane() {
        GridPane logsConsolePane = new GridPane();

        logTableView = new TableView<>();
        TableColumn<LogEntry, String> dateColumn = new TableColumn<>("Date");
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        TableColumn<LogEntry, String> clockInColumn = new TableColumn<>("Clock In");
        clockInColumn.setCellValueFactory(new PropertyValueFactory<>("clockIn"));
        TableColumn<LogEntry, String> clockOutColumn = new TableColumn<>("Clock Out");
        clockOutColumn.setCellValueFactory(new PropertyValueFactory<>("clockOut"));
        TableColumn<LogEntry, String> userColumn = new TableColumn<>("User");
        userColumn.setCellValueFactory(new PropertyValueFactory<>("user"));
        TableColumn<LogEntry, String> taskColumn = new TableColumn<>("Task");
        taskColumn.setCellValueFactory(new PropertyValueFactory<>("task"));

        logTableView.getColumns().addAll(dateColumn, clockInColumn, clockOutColumn, userColumn, taskColumn);
        logTableView.setItems(logEntries);

        // Create buttons for editing logs, returning to the main dashboard, and granting access
        Button editLogsButton = new Button("Edit Logs");
        Button returnButton = new Button("Return to Main Dashboard");
        Button grantAccessButton = new Button("Grant Access");

        VBox buttonBox = new VBox(editLogsButton, returnButton, grantAccessButton);
        buttonBox.setSpacing(10);

        // Handle button click events
        editLogsButton.setOnAction(e -> showEditLogsWindow());
        returnButton.setOnAction(e -> showMainDashboard());
        grantAccessButton.setOnAction(e -> showGrantAccessWindow());

        // Add UI elements to the logs console pane
        logsConsolePane.add(logTableView, 0, 0);
        logsConsolePane.add(buttonBox, 1, 0);

        return logsConsolePane;
    }

    // Stub method to open the edit logs window
    private void showEditLogsWindow() {
        // Implement the logic to open the edit logs window
        // You can create a new stage for editing logs and define its content
    }

    // Stub method to open the grant access window
    private void showGrantAccessWindow() {
        // Implement the logic to open the grant access window
        // You can create a new stage for granting access and define its content
    }

    // Inner class to represent log entries
    public static class LogEntry {
        private String date;
        private String clockIn;
        private String clockOut;
        private String user;
        private String task;

        public LogEntry(String date, String clockIn, String clockOut, String user, String task) {
            this.date = date;
            this.clockIn = clockIn;
            this.clockOut = clockOut;
            this.user = user;
            this.task = task;
        }

        public String getDate() {
            return date;
        }

        public String getClockIn() {
            return clockIn;
        }

        public String getClockOut() {
            return clockOut;
        }

        public String getUser() {
            return user;
        }

        public String getTask() {
            return task;
        }
    }
}
