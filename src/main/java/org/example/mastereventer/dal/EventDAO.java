package org.example.mastereventer.dal;

import org.example.mastereventer.Objects.Event;
import java.sql.SQLException;
import java.util.List;


public abstract class EventDAO {
    abstract void createEvent(Event event) throws SQLException;
    abstract Event getEventById(int id) throws SQLException;
    abstract List<Event> getAllEvents() throws SQLException;
    abstract void updateEvent(Event event) throws SQLException;
    abstract void deleteEvent(int id) throws SQLException;
}
