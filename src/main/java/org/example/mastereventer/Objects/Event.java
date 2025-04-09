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

    // Constructor, der sætter de væsentlige felter
    public Event(String title, String location, String notes, LocalDateTime startDateTime) {
        this.title = title;
        this.location = location;
        this.notes = notes;
        this.startDateTime = startDateTime;
        // Sæt endDateTime til fx 2 timer efter start – alternativt kan du udvide constructoren senere:
        this.endDateTime = startDateTime.plusHours(2);
        // Hvis du ikke har information for locationGuidance, sæt den til en tom streng
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
