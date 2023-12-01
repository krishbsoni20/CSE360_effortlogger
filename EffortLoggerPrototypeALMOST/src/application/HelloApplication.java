/*package com.example.mark5;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class HelloApplication extends Application {

    private final Map<String, String> userCredentials = new HashMap<>();
    private final ObservableList<LogEntry> logEntries = FXCollections.observableArrayList();
    private Stage logWindow;
    private LocalDateTime activityStart;

    public HelloApplication() {
        userCredentials.put("user1", "pass1");
        userCredentials.put("user2", "pass2");
        userCredentials.put("user3", "pass3");
        userCredentials.put("user4", "pass4");
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Effort Logger Tool");
        Scene loginScene = new Scene(createLoginPane(primaryStage), 300, 200);
        primaryStage.setScene(loginScene);
        primaryStage.show();
    }

    private GridPane createLoginPane(Stage primaryStage) {
        GridPane loginPane = new GridPane();
        configureLayout(loginPane);

        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        Button loginButton = new Button("Login");
        loginButton.setOnAction(e -> processLogin(usernameField, passwordField, primaryStage));

        loginPane.add(new Label("Username:"), 0, 0);
        loginPane.add(usernameField, 1, 0);
        loginPane.add(new Label("Password:"), 0, 1);
        loginPane.add(passwordField, 1, 1);
        loginPane.add(loginButton, 1, 2);

        return loginPane;
    }

    private void processLogin(TextField usernameField, PasswordField passwordField, Stage primaryStage) {
        if (isValidCredentials(usernameField.getText(), passwordField.getText())) {
            primaryStage.setScene(createMainScene(primaryStage));
        } else {
            showAlert("Invalid Credentials");
        }
    }

    private boolean isValidCredentials(String username, String password) {
        return userCredentials.containsKey(username) && userCredentials.get(username).equals(password);
    }

    private Scene createMainScene(Stage primaryStage) {
        VBox mainLayout = new VBox(10);
        mainLayout.setPadding(new Insets(10));
        mainLayout.setAlignment(Pos.CENTER);

        ComboBox<String> planDropdown = createComboBox("Plan 1", "Plan 2", "Plan 3");
        ComboBox<String> deliverableDropdown = createComboBox("Deliverable 1", "Deliverable 2", "Deliverable 3");
        ComboBox<String> teamDropdown = createComboBox("Team 1", "Team 2", "Team 3");
        ComboBox<String> lifecycleDropdown = createComboBox("Lifecycle 1", "Lifecycle 2", "Lifecycle 3");

        Button startButton = new Button("Start Activity");
        Button finishButton = new Button("Finish Activity");
        finishButton.setDisable(true);
        Button viewLogsButton = new Button("View Logs");

        startButton.setOnAction(e -> {
            activityStart = LocalDateTime.now();
            startButton.setDisable(true);
            finishButton.setDisable(false);
        });

        finishButton.setOnAction(e -> {
            logEntries.add(new LogEntry(activityStart, LocalDateTime.now(), planDropdown.getValue(), deliverableDropdown.getValue(), teamDropdown.getValue(), lifecycleDropdown.getValue()));
            startButton.setDisable(false);
            finishButton.setDisable(true);
        });

        viewLogsButton.setOnAction(e -> showLogWindow());

        mainLayout.getChildren().addAll(planDropdown, deliverableDropdown, teamDropdown, lifecycleDropdown, startButton, finishButton, viewLogsButton);

        return new Scene(mainLayout, 600, 400);
    }

    private void showLogWindow() {
        if (logWindow == null) {
            logWindow = new Stage();
            logWindow.setTitle("Activity Logs");

            TableView<LogEntry> logTable = new TableView<>(logEntries);
            setupLogTable(logTable);

            VBox logLayout = new VBox(10);
            logLayout.setPadding(new Insets(10));
            logLayout.getChildren().add(logTable);

            Scene logScene = new Scene(logLayout, 600, 400);
            logWindow.setScene(logScene);
        }
        logWindow.show();
    }

    private ComboBox<String> createComboBox(String... items) {
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getItems().addAll(items);
        styleComboBox(comboBox);
        return comboBox;
    }

    private void setupLogTable(TableView<LogEntry> logTable) {
        TableColumn<LogEntry, String> startTimeCol = new TableColumn<>("Start Time");
        startTimeCol.setCellValueFactory(new PropertyValueFactory<>("formattedStartTime"));
        TableColumn<LogEntry, String> endTimeCol = new TableColumn<>("End Time");
        endTimeCol.setCellValueFactory(new PropertyValueFactory<>("formattedEndTime"));
        TableColumn<LogEntry, String> planCol = new TableColumn<>("Plan");
        planCol.setCellValueFactory(new PropertyValueFactory<>("plan"));
        TableColumn<LogEntry, String> deliverableCol = new TableColumn<>("Deliverable");
        deliverableCol.setCellValueFactory(new PropertyValueFactory<>("deliverable"));
        TableColumn<LogEntry, String> teamCol = new TableColumn<>("Team");
        teamCol.setCellValueFactory(new PropertyValueFactory<>("team"));
        TableColumn<LogEntry, String> lifecycleCol = new TableColumn<>("Lifecycle");
        lifecycleCol.setCellValueFactory(new PropertyValueFactory<>("lifecycle"));

        logTable.getColumns().addAll(startTimeCol, endTimeCol, planCol, deliverableCol, teamCol, lifecycleCol);
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.show();
    }

    private void configureLayout(GridPane pane) {
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(10));
        pane.setVgap(10);
        pane.setHgap(10);
    }

    private void styleComboBox(ComboBox<String> comboBox) {
        comboBox.setMinWidth(200);
        comboBox.setMaxWidth(Double.MAX_VALUE);
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static class LogEntry {
        private final LocalDateTime startTime;
        private final LocalDateTime endTime;
        private final String plan;
        private final String deliverable;
        private final String team;
        private final String lifecycle;

        public LogEntry(LocalDateTime startTime, LocalDateTime endTime, String plan, String deliverable, String team, String lifecycle) {
            this.startTime = startTime;
            this.endTime = endTime;
            this.plan = plan;
            this.deliverable = deliverable;
            this.team = team;
            this.lifecycle = lifecycle;
        }

        public String getFormattedStartTime() {
            return formatDateTime(startTime);
        }

        public String getFormattedEndTime() {
            return formatDateTime(endTime);
        }

        private String formatDateTime(LocalDateTime dateTime) {
            return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        }

        public String getPlan() {
            return plan;
        }

        public String getDeliverable() {
            return deliverable;
        }

        public String getTeam() {
            return team;
        }

        public String getLifecycle() {
            return lifecycle;
        }
    }
}
*/
