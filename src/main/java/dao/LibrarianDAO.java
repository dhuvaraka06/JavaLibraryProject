package dao;

import model.Librarian;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibrarianDAO {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/library_management";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "your_password"; // Change this

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
    }

    // Insert Librarian
    public boolean addLibrarian(Librarian librarian) {
        String sql = "INSERT INTO LIBRARIAN (LIBRARIAN_ID, NAME, EMAIL, PASSWORD) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, librarian.getLibrarianId());
            stmt.setString(2, librarian.getName());
            stmt.setString(3, librarian.getEmail());
            stmt.setString(4, librarian.getPassword());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error adding librarian: " + e.getMessage());
            return false;
        }
    }

    // Get all librarians
    public List<Librarian> getAllLibrarians() {
        List<Librarian> librarians = new ArrayList<>();
        String sql = "SELECT * FROM LIBRARIAN";
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Librarian librarian = new Librarian();
                librarian.setLibrarianId(rs.getInt("LIBRARIAN_ID"));
                librarian.setName(rs.getString("NAME"));
                librarian.setEmail(rs.getString("EMAIL"));
                librarian.setPassword(rs.getString("PASSWORD"));
                librarians.add(librarian);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching librarians: " + e.getMessage());
        }
        return librarians;
    }

    // Get librarian by ID
    public Librarian getLibrarianById(int id) {
        String sql = "SELECT * FROM LIBRARIAN WHERE LIBRARIAN_ID = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Librarian(
                    rs.getInt("LIBRARIAN_ID"),
                    rs.getString("NAME"),
                    rs.getString("EMAIL"),
                    rs.getString("PASSWORD")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error fetching librarian: " + e.getMessage());
        }
        return null;
    }

    // Update librarian
    public boolean updateLibrarian(Librarian librarian) {
        String sql = "UPDATE LIBRARIAN SET NAME = ?, EMAIL = ?, PASSWORD = ? WHERE LIBRARIAN_ID = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, librarian.getName());
            stmt.setString(2, librarian.getEmail());
            stmt.setString(3, librarian.getPassword());
            stmt.setInt(4, librarian.getLibrarianId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error updating librarian: " + e.getMessage());
            return false;
        }
    }

    // Delete librarian
    public boolean deleteLibrarian(int id) {
        String sql = "DELETE FROM LIBRARIAN WHERE LIBRARIAN_ID = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error deleting librarian: " + e.getMessage());
            return false;
        }
    }
}
