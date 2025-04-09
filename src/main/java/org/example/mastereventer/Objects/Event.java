package org.example.mastereventer.Objects;
import java.time.LocalDateTime;


public class Event {

        private int id;
        private String Title;
        private LocalDateTime startDateTime;
        private LocalDateTime endDateTime;
        private String Location;
        private String notes;
        private String locationGuidance;

                public Event(String title, String location, String notes, LocalDateTime localDateTime) {

            this.id = id;
            this.Title = title;
            this.startDateTime = startDateTime;
            this.endDateTime = endDateTime;
            this.locationGuidance = locationGuidance;
            this.Location = location;
            this.notes = notes;
            this.locationGuidance = locationGuidance;

        }

        public int getId() {
        return id;
        }

        public String getTitle() {
        return Title;
        }

        public LocalDateTime getStartDateTime() {
            return startDateTime;
        }

        public LocalDateTime getEndDateTime() {
            return endDateTime;
        }

        public String getLocation() {
            return Location;
        }

        public String getNotes() {
            return notes;
        }

        public String getLocationGuidance() {
            return locationGuidance;
        }
}
