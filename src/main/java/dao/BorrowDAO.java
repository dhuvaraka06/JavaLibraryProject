package dao;

import model.Borrow;
import utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BorrowDAO {

    public boolean addBorrow(Borrow borrow) {
        String query = "INSERT INTO borrow (USER_ID, BOOK_ID, BORROW_DATE, RENEWED, DUE_DATE, RENEWED_DATE, RETURN_DATE) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, borrow.getUserId());
            pstmt.setInt(2, borrow.getBookId());
            pstmt.setDate(3, borrow.getBorrowDate());
            pstmt.setBoolean(4, borrow.isRenewed());
            pstmt.setDate(5, borrow.getDueDate());
            pstmt.setDate(6, borrow.getRenewedDate());
            pstmt.setDate(7, borrow.getReturnDate());

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error adding borrow: " + e.getMessage());
            return false;
        }
    }

    public boolean updateBorrow(Borrow borrow) {
        String query = "UPDATE borrow SET USER_ID = ?, BOOK_ID = ?, BORROW_DATE = ?, RENEWED = ?, DUE_DATE = ?, RENEWED_DATE = ?, RETURN_DATE = ? WHERE BORROW_ID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, borrow.getUserId());
            pstmt.setInt(2, borrow.getBookId());
            pstmt.setDate(3, borrow.getBorrowDate());
            pstmt.setBoolean(4, borrow.isRenewed());
            pstmt.setDate(5, borrow.getDueDate());
            pstmt.setDate(6, borrow.getRenewedDate());
            pstmt.setDate(7, borrow.getReturnDate());
            pstmt.setInt(8, borrow.getBorrowId());

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error updating borrow: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteBorrow(int borrowId) {
        String query = "DELETE FROM borrow WHERE BORROW_ID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, borrowId);
            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error deleting borrow: " + e.getMessage());
            return false;
        }
    }

    public Borrow getBorrowById(int borrowId) {
        String query = "SELECT * FROM borrow WHERE BORROW_ID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, borrowId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Borrow(
                    rs.getInt("BORROW_ID"),
                    rs.getInt("USER_ID"),
                    rs.getInt("BOOK_ID"),
                    rs.getDate("BORROW_DATE"),
                    rs.getBoolean("RENEWED"),
                    rs.getDate("DUE_DATE"),
                    rs.getDate("RENEWED_DATE"),
                    rs.getDate("RETURN_DATE")
                );
            }

        } catch (SQLException e) {
            System.err.println("Error fetching borrow by ID: " + e.getMessage());
        }

        return null;
    }

    public List<Borrow> getAllBorrows() {
        List<Borrow> borrowList = new ArrayList<>();
        String query = "SELECT * FROM borrow";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Borrow borrow = new Borrow(
                    rs.getInt("BORROW_ID"),
                    rs.getInt("USER_ID"),
                    rs.getInt("BOOK_ID"),
                    rs.getDate("BORROW_DATE"),
                    rs.getBoolean("RENEWED"),
                    rs.getDate("DUE_DATE"),
                    rs.getDate("RENEWED_DATE"),
                    rs.getDate("RETURN_DATE")
                );
                borrowList.add(borrow);
            }

        } catch (SQLException e) {
            System.err.println("Error fetching all borrows: " + e.getMessage());
        }

        return borrowList;
    }
}
