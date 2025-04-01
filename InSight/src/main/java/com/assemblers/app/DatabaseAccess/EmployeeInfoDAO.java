package com.assemblers.app.DatabaseAccess;
import java.sql.*;
import com.assemblers.app.Models.Employee;
public class EmployeeInfoDAO {
    public static Employee getEmployeeInfoById(int empId) {
        String query = "SELECT e.empid, e.Fname, e.Lname, jt.job_title, p.pay_date, p.earnings, " +
               "p.fed_tax, p.fed_med, p.fed_SS, p.state_tax, p.retire_401k AS retire_401K, p.health_care " +
               "FROM employees e " +
               "JOIN payroll p ON e.empid = p.empid " +
               "JOIN employee_job_titles ejt ON e.empid = ejt.empid " +
               "JOIN job_titles jt ON ejt.job_title_id = jt.job_title_id " +
               "WHERE e.empid = ?";
        
        try (Connection conn = DatabaseAccessHelper.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, empId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Employee(
                    rs.getInt("empid"),
                    rs.getString("fname"),
                    rs.getString("lname"),
                    rs.getString("job_title"),
                    rs.getDate("pay_date"),
                    rs.getDouble("earnings"),
                    rs.getDouble("fed_tax"),
                    rs.getDouble("fed_med"),
                    rs.getDouble("fed_SS"),
                    rs.getDouble("state_tax"),
                    rs.getDouble("retire_401K"),
                    rs.getDouble("health_care")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
