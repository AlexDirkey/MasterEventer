/* package org.example.mastereventer.bll;

import org.example.mastereventer.dal.EventConnector;
import org.example.mastereventer.objects.Event;

import java.sql.SQLException;
import java.util.List;

public class EventManager {

    private final EventConnector eventConnector;

    public EventManager() {
        this.eventConnector = new EventConnector();
    }

    public List<Event> getAllEvents() {
        try {
            return eventConnector.getAllEvents();
        } catch (SQLException e) {
            e.printStackTrace();
            // Du kan evt. smide en runtime exception eller bruge en Logger
            throw new RuntimeException("Kunne ikke hente events fra databasen", e);
        }
    }

    // Her kan du tilføje createEvent(), deleteEvent() osv. senere

}
*/