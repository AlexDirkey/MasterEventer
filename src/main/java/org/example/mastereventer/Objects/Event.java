package org.example.mastereventer.Objects;

import java.time.LocalDateTime;

public class Event {

    private int id;
    private String title;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private String location;
    private String notes;
    private String locationGuidance;

    // Constructor
    public Event(String title, String location, String notes, LocalDateTime startDateTime) {
        this.title = title;
        this.location = location;
        this.notes = notes;
        this.startDateTime = startDateTime;
        // Brug endTime til automatisk måske?
        this.endDateTime = startDateTime.plusHours(2);
        this.locationGuidance = "";
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public String getLocation() {
        return location;
    }

    public String getNotes() {
        return notes;
    }

    public String getLocationGuidance() {
        return locationGuidance;
    }
}
