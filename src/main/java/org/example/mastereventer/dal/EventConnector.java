/*package org.example.mastereventer.dal;
import org.example.mastereventer.Objects.Event;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EventConnector {
    private final String connectionString = "jdbc:sqlserver://localhost;databaseName=MasterEventer;user=sa;password=yourPassword;encrypt=true;trustServerCertificate=true";

    public List<Event> getAllEvents() throws SQLException {
        List<Event> events = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(connectionString);
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("select * from events");) {

            while (rs.next()) {
                Event event = new Event(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getTimestamp("startDateTime"),
                        rs.getTimestamp("endDateTime"),
                        rs.getString("location"),
                        rs.getString("notes"),
                        rs.getString("locationGuidance")

                        );
                        events.add(event);
            }
        }

        return events;
    } */

