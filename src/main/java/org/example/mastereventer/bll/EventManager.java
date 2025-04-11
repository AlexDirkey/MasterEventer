package org.example.mastereventer.bll;

import org.example.mastereventer.dal.DBConnector;
import org.example.mastereventer.Objects.Event;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EventManager {

    // Opretter et event i databasen
    public void createEvent(Event event) {
        String sql = "INSERT INTO Events (title, startDateTime, location, notes) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, event.getTitle());
            pstmt.setTimestamp(2, Timestamp.valueOf(event.getStartDateTime()));
            pstmt.setString(3, event.getLocation());
            pstmt.setString(4, event.getNotes());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Oprettelse af event mislykkedes, ingen rækker blev påvirket.");
            }


            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Henter et event via dets id
    public Event getEventById(int id) {
        String sql = "SELECT * FROM Events WHERE id = ?";
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String title = rs.getString("title");
                    LocalDateTime startDateTime = rs.getTimestamp("startDateTime").toLocalDateTime();
                    String location = rs.getString("location");
                    String notes = rs.getString("notes");
                    return new Event(title, location, notes, startDateTime);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Henter alle events fra databasen
    public List<Event> getAllEvents() {
        List<Event> events = new ArrayList<>();
        String sql = "SELECT * FROM Events";
        try (Connection conn = DBConnector.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String title = rs.getString("title");
                LocalDateTime startDateTime = rs.getTimestamp("startDateTime").toLocalDateTime();
                String location = rs.getString("location");
                String notes = rs.getString("notes");
                Event event = new Event(title, location, notes, startDateTime);
                events.add(event);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return events;
    }

    // Opdaterer et eksisterende event
    public void updateEvent(Event event) {
        String sql = "UPDATE Events SET title = ?, startDateTime = ?, location = ?, notes = ? WHERE id = ?";
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, event.getTitle());
            pstmt.setTimestamp(2, Timestamp.valueOf(event.getStartDateTime()));
            pstmt.setString(3, event.getLocation());
            pstmt.setString(4, event.getNotes());
            pstmt.setInt(5, event.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Sletter et event via id
    public void deleteEvent(int id) {
        String sql = "DELETE FROM Events WHERE id = ?";
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

