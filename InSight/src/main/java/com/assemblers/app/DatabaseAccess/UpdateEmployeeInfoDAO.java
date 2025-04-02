package com.assemblers.app.DatabaseAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.assemblers.app.Models.Employee;

public class UpdateEmployeeInfoDAO {
    public static void UpdateEmployeeInfo(Employee employee){
        String query = "UPDATE employees " +
                   "SET Fname = ?, Lname = ?, email = ? , Salary = ?, SSN = ? " +
                   "WHERE empid = ?";
    
    try (Connection conn = DatabaseAccessHelper.getConnection();
         PreparedStatement stmt = conn.prepareStatement(query)) {
        
        stmt.setString(1, employee.getFname());
        stmt.setString(2, employee.getLname());
        stmt.setString(3, employee.getEmail());
        stmt.setDouble(4, employee.getSalary());
        stmt.setString(5, employee.getSsn());
        stmt.setInt(6, employee.getEmpid());
        
    } catch (SQLException e) {
        e.printStackTrace();
    }
    }
}
