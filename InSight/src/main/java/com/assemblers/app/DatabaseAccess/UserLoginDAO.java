package com.assemblers.app.DatabaseAccess;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.assemblers.app.Models.User;

public class UserLoginDAO {
   public static User login(String username, String password) {
        try (Connection conn = DatabaseAccessHelper.getConnection()) {
            String query = "SELECT u.empid, u.role FROM users u WHERE u.username = ? AND u.password = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, username);
                stmt.setString(2, password);

                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    return new User(rs.getInt("empid"), rs.getInt("role"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Return null if login fails
   }
}
