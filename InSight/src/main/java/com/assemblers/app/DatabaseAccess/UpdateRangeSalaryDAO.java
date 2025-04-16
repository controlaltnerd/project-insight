package com.assemblers.app.DatabaseAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateRangeSalaryDAO {
    public static int updateRangeSalary(double min, double max, double increment){
        String query = "UPDATE employees " +
                   "SET Salary = Salary + Salary * ? / 100 " +
                   "WHERE Salary BETWEEN ? AND ?";
    
    try (Connection conn = DatabaseAccessHelper.getConnection();
         PreparedStatement stmt = conn.prepareStatement(query)) {
        
        stmt.setDouble(1, increment);
        stmt.setDouble(2, min);
        stmt.setDouble(3, max);

        int rowsUpdated = stmt.executeUpdate();
        return rowsUpdated;
        
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return 0;
    }
}