// dao/UserDAO.java
package DAO;

import Model.User;
import Util.DBConnection;

import java.sql.*;

public class UserDAO {
    public void addUser(User user) throws SQLException {
        String sql = "INSERT INTO user (USER_ID,NAME) VALUES (?,?)";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt   (1,user.getUserId());
            ps.setString(2,user.getName());
            ps.executeUpdate();
        }
    }

    public User getUser(int id) throws SQLException {
        String sql = "SELECT * FROM user WHERE USER_ID=?";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1,id);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    return new User(rs.getInt("USER_ID"), rs.getString("NAME"));
                }
            }
        }
        return null;
    }
}
