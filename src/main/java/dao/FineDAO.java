package dao;

import model.Fine;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FineDAO {
    private Connection connection;

    public FineDAO(Connection connection) {
        this.connection = connection;
    }

    // Insert a new Fine
    public boolean insertFine(Fine fine) {
        String query = "INSERT INTO Fine (FINE_ID, BORROW_ID, FINE_AMOUNT, FINE_PAID, FINE_PAID_DATE) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, fine.getFineId());
            stmt.setInt(2, fine.getBorrowId());
            stmt.setDouble(3, fine.getFineAmount());
            stmt.setBoolean(4, fine.isFinePaid());
            stmt.setDate(5, fine.getFinePaidDate());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error inserting fine: " + e.getMessage());
            return false;
        }
    }

    // Update an existing Fine
    public boolean updateFine(Fine fine) {
        String query = "UPDATE Fine SET BORROW_ID = ?, FINE_AMOUNT = ?, FINE_PAID = ?, FINE_PAID_DATE = ? WHERE FINE_ID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, fine.getBorrowId());
            stmt.setDouble(2, fine.getFineAmount());
            stmt.setBoolean(3, fine.isFinePaid());
            stmt.setDate(4, fine.getFinePaidDate());
            stmt.setInt(5, fine.getFineId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error updating fine: " + e.getMessage());
            return false;
        }
    }

    // Delete a Fine by ID
    public boolean deleteFine(int fineId) {
        String query = "DELETE FROM Fine WHERE FINE_ID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, fineId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error deleting fine: " + e.getMessage());
            return false;
        }
    }

    // Get a Fine by ID
    public Fine getFineById(int fineId) {
        String query = "SELECT * FROM Fine WHERE FINE_ID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, fineId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Fine(
                    rs.getInt("FINE_ID"),
                    rs.getInt("BORROW_ID"),
                    rs.getDouble("FINE_AMOUNT"),
                    rs.getBoolean("FINE_PAID"),
                    rs.getDate("FINE_PAID_DATE")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error fetching fine: " + e.getMessage());
        }
        return null;
    }

    // Get all Fines
    public List<Fine> getAllFines() {
        List<Fine> fines = new ArrayList<>();
        String query = "SELECT * FROM Fine";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Fine fine = new Fine(
                    rs.getInt("FINE_ID"),
                    rs.getInt("BORROW_ID"),
                    rs.getDouble("FINE_AMOUNT"),
                    rs.getBoolean("FINE_PAID"),
                    rs.getDate("FINE_PAID_DATE")
                );
                fines.add(fine);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving fines: " + e.getMessage());
        }
        return fines;
    }
}
