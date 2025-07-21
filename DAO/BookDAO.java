// dao/BookDAO.java
package DAO;

import Model.Book;
import Util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    public void addBook(Book book) throws SQLException {
        String sql = """
            INSERT INTO book
            (BOOK_ID,TITLE,AUTHOR,GENRE,TOTAL_COPIES,AVAILABLE_COPIES)
            VALUES (?,?,?,?,?,?)""";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt   (1, book.getBookId());
            ps.setString(2, book.getTitle());
            ps.setString(3, book.getAuthor());
            ps.setString(4, book.getGenre());
            ps.setInt   (5, book.getTotalCopies());
            ps.setInt   (6, book.getAvailableCopies());
            ps.executeUpdate();
        }
    }

    public Book getBook(int id) throws SQLException {
        String sql = "SELECT * FROM book WHERE BOOK_ID=?";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1,id);
            try (ResultSet rs = ps.executeQuery()) {
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
            }
        }
        return null;
    }

    public void updateAvailableCopies(int bookId,int delta) throws SQLException {
        String sql = "UPDATE book SET AVAILABLE_COPIES=AVAILABLE_COPIES+? WHERE BOOK_ID=?";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, delta);
            ps.setInt(2, bookId);
            ps.executeUpdate();
        }
    }

    public List<Book> listAll() throws SQLException {
        List<Book> list = new ArrayList<>();
        String sql = "SELECT * FROM book";
        try (Connection c = DBConnection.getConnection();
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Book(
                    rs.getInt("BOOK_ID"),
                    rs.getString("TITLE"),
                    rs.getString("AUTHOR"),
                    rs.getString("GENRE"),
                    rs.getInt("TOTAL_COPIES"),
                    rs.getInt("AVAILABLE_COPIES")
                ));
            }
        }
        return list;
    }
}
