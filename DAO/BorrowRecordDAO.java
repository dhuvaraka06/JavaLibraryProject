// dao/BorrowRecordDAO.java
package DAO;

import Model.BorrowRecord;
import Util.DBConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BorrowRecordDAO {
    public void addRecord(BorrowRecord br) throws SQLException {
        String sql = """
            INSERT INTO borrow_record
            (RECORD_ID,USER_ID,BOOK_ID,BORROW_DATE,DUE_DATE,RENEWED,RETURN_DATE)
            VALUES (?,?,?,?,?,?,?)""";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt       (1, br.getRecordId());
            ps.setInt       (2, br.getUserId());
            ps.setInt       (3, br.getBookId());
            ps.setDate      (4, Date.valueOf(br.getBorrowDate()));
            ps.setDate      (5, Date.valueOf(br.getDueDate()));
            ps.setBoolean   (6, br.isRenewed());
            if (br.getReturnDate()==null)
                ps.setNull(7, Types.DATE);
            else
                ps.setDate(7, Date.valueOf(br.getReturnDate()));
            ps.executeUpdate();
        }
    }

    public BorrowRecord getActiveRecord(int userId,int bookId) throws SQLException {
        String sql = """
            SELECT * FROM borrow_record
            WHERE USER_ID=? AND BOOK_ID=? AND RETURN_DATE IS NULL""";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps=c.prepareStatement(sql)) {
            ps.setInt(1,userId);
            ps.setInt(2,bookId);
            try(ResultSet rs=ps.executeQuery()){
                if(rs.next()){
                    return map(rs);
                }
            }
        }
        return null;
    }

    public void updateRecord(BorrowRecord br) throws SQLException {
        String sql = """
            UPDATE borrow_record
            SET DUE_DATE=?, RENEWED=?, RETURN_DATE=?
            WHERE RECORD_ID=?""";
        try(Connection c=DBConnection.getConnection();
            PreparedStatement ps=c.prepareStatement(sql)){
            ps.setDate   (1, Date.valueOf(br.getDueDate()));
            ps.setBoolean(2, br.isRenewed());
            if (br.getReturnDate()==null)
                ps.setNull(3, Types.DATE);
            else
                ps.setDate(3, Date.valueOf(br.getReturnDate()));
            ps.setInt    (4, br.getRecordId());
            ps.executeUpdate();
        }
    }

    private BorrowRecord map(ResultSet rs) throws SQLException {
        return new BorrowRecord(
                rs.getInt("RECORD_ID"),
                rs.getInt("USER_ID"),
                rs.getInt("BOOK_ID"),
                rs.getDate("BORROW_DATE").toLocalDate(),
                rs.getDate("DUE_DATE").toLocalDate(),
                rs.getBoolean("RENEWED"),
                rs.getDate("RETURN_DATE")==null?null:
                   rs.getDate("RETURN_DATE").toLocalDate()
        );
    }

    public List<BorrowRecord> listActiveByUser(int userId) throws SQLException {
        List<BorrowRecord> list=new ArrayList<>();
        String sql = "SELECT * FROM borrow_record WHERE USER_ID=? AND RETURN_DATE IS NULL";
        try(Connection c=DBConnection.getConnection();
            PreparedStatement ps=c.prepareStatement(sql)){
            ps.setInt(1,userId);
            try(ResultSet rs=ps.executeQuery()){
                while(rs.next()) list.add(map(rs));
            }
        }
        return list;
    }
}
