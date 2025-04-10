package com.assemblers.app.DatabaseAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateRangeSalaryDAO {
    public static int updateRangeSalary(int min, int max, int increment){
        String query = "UPDATE employees " +
                   "SET Salary = Salary + Salary * ? / 100 " +
                   "WHERE Salary BETWEEN ? AND ?";
    
    try (Connection conn = DatabaseAccessHelper.getConnection();
         PreparedStatement stmt = conn.prepareStatement(query)) {
        
        stmt.setInt(1, increment);
        stmt.setInt(2, min);
        stmt.setInt(3, max);

        int rowsUpdated = stmt.executeUpdate();
        return rowsUpdated;
        
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return 0;
    }
}