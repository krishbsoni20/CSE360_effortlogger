package application;

import javafx.beans.property.SimpleStringProperty;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// Class representing a log entry with details such as start time, stop time, etc.
//code was made by me but uploaded through collegue's userid
public class LogEntry {
    // Properties for index, start time, stop time, elapsed time, life cycle, and project
    private final SimpleStringProperty index;
    private final LocalDateTime startTime;
    private final LocalDateTime stopTime;
    private final SimpleStringProperty elapsedTime;
    private final SimpleStringProperty lifeCycle;
    private final SimpleStringProperty project;

    // Constructor to initialize log entry with provided values
    public LogEntry(int index, LocalDateTime startTime, LocalDateTime stopTime, String elapsedTime, String lifeCycle, String project) {
        this.index = new SimpleStringProperty(String.valueOf(index));
        this.startTime = startTime;
        this.stopTime = stopTime;
        this.elapsedTime = new SimpleStringProperty(elapsedTime);
        this.lifeCycle = new SimpleStringProperty(lifeCycle);
        this.project = new SimpleStringProperty(project);
    }

    // Getters for retrieving values of log entry properties
    public String getIndex() {
        return index.get();
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getStopTime() {
        return stopTime;
    }

    public String getElapsedTime() {
        return elapsedTime.get();
    }

    public String getLifeCycle() {
        return lifeCycle.get();
    }

    public String getProject() {
        return project.get();
    }

    // Formatters for DateTime to retrieve formatted start and stop times
    public String getFormattedStartTime() {
        return startTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public String getFormattedStopTime() {
        return stopTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
