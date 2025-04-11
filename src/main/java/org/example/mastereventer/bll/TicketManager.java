package org.example.mastereventer.bll;

import org.example.mastereventer.Objects.Ticket;
import org.example.mastereventer.dal.DBConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketManager {

    // Opretter en billet i databasen
    public void createTicket(Ticket ticket) throws SQLException {
        String sql = "INSERT INTO Tickets (description, customerName, customerEmail) VALUES (?, ?, ?)";
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, ticket.getDescription());
            pstmt.setString(2, ticket.getCustomerName());
            pstmt.setString(3, ticket.getCustomerEmail());
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Oprettelse af billet mislykkedes, ingen rækker blev påvirket.");
            }

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    ticket.setId(generatedKeys.getString(1));
                }
            }
        }
    }

    // Henter alle billetter fra databasen
    public List<Ticket> getAllTickets() throws SQLException {
        List<Ticket> tickets = new ArrayList<>();
        String sql = "SELECT * FROM Tickets";
        try (Connection conn = DBConnector.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String id = rs.getString("id");
                String description = rs.getString("description");
                String customerName = rs.getString("customerName");
                String customerEmail = rs.getString("customerEmail");
                Ticket ticket = new Ticket(description, customerName, customerEmail);
                ticket.setId(String.valueOf(id));
                tickets.add(ticket);
            }
        }
        return tickets;
    }

    // Opdaterer en billet i databasen
    public void updateTicket(Ticket ticket) throws SQLException {
        String sql = "UPDATE Tickets SET description = ?, customerName = ?, customerEmail = ? WHERE id = ?";
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, ticket.getDescription());
            pstmt.setString(2, ticket.getCustomerName());
            pstmt.setString(3, ticket.getCustomerEmail());
            pstmt.setString(4, ticket.getId());
            pstmt.executeUpdate();
        }
    }

    // Sletter en billet via dens id
    public void deleteTicket(int id) throws SQLException {
        String sql = "DELETE FROM Tickets WHERE id = ?";
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }
}
