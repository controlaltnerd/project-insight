package com.assemblers.app.DatabaseAccess;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.assemblers.app.Models.EmployeePayInfo;
public class ReportDAO {
    public static EmployeePayInfo getEmployeePayInfoById(int empId) {
    String query = "SELECT e.empid, e.Fname, e.Lname" +
                   "p.pay_date, p.earnings, p.fed_tax, p.fed_med, p.fed_SS, p.state_tax, p.retire_401k, p.health_care " +
                   "FROM employees e " +
                   "LEFT JOIN payroll p ON e.empid = p.empid " +
                   "WHERE e.empid = ?";

    try (Connection conn = DatabaseAccessHelper.getConnection();
         PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setInt(1, empId);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return new EmployeePayInfo(
                    rs.getInt("empid"),
                    rs.getString("Fname"),
                    rs.getString("Lname"),
                    rs.getDate("pay_date"),
                    rs.getDouble("earnings"),
                    rs.getDouble("fed_tax"),
                    rs.getDouble("fed_med"),
                    rs.getDouble("fed_SS"),
                    rs.getDouble("state_tax"),
                    rs.getDouble("retire_401k"),
                    rs.getDouble("health_care")
                );
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}
}
