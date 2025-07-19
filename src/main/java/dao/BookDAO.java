package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Book;
import utils.DatabaseConnection;

public class BookDAO {

    public boolean addBook(Book book) {
        String query = "INSERT INTO book (BOOK_ID, TITLE, AUTHOR, GENRE, TOTAL_COPIES, AVAILABLE_COPIES) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, book.getBookId());
            stmt.setString(2, book.getTitle());
            stmt.setString(3, book.getAuthor());
            stmt.setString(4, book.getGenre());
            stmt.setInt(5, book.getTotalCopies());
            stmt.setInt(6, book.getAvailableCopies());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error adding book: " + e.getMessage());
            return false;
        }
    }

    public Book getBookById(int bookId) {
        String query = "SELECT * FROM book WHERE BOOK_ID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, bookId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Book(
                    rs.getInt("BOOK_ID"),
                    rs.getString("TITLE"),
                    rs.getString("AUTHOR"),
                    rs.getString("GENRE"),
                    rs.getInt("TOTAL_COPIES"),
                    rs.getInt("AVAILABLE_COPIES")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving book: " + e.getMessage());
        }
        return null;
    }

    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String query = "SELECT * FROM book";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Book book = new Book(
                    rs.getInt("BOOK_ID"),
                    rs.getString("TITLE"),
                    rs.getString("AUTHOR"),
                    rs.getString("GENRE"),
                    rs.getInt("TOTAL_COPIES"),
                    rs.getInt("AVAILABLE_COPIES")
                );
                books.add(book);
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving books: " + e.getMessage());
        }

        return books;
    }

    public boolean updateBook(Book book) {
        String query = "UPDATE book SET TITLE = ?, AUTHOR = ?, GENRE = ?, TOTAL_COPIES = ?, AVAILABLE_COPIES = ? WHERE BOOK_ID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setString(3, book.getGenre());
            stmt.setInt(4, book.getTotalCopies());
            stmt.setInt(5, book.getAvailableCopies());
            stmt.setInt(6, book.getBookId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error updating book: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteBook(int bookId) {
        String query = "DELETE FROM book WHERE BOOK_ID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, bookId);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error deleting book: " + e.getMessage());
            return false;
        }
    }
}
