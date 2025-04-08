package com.assemblers.app.DatabaseAccess;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.assemblers.app.Models.EmployeePayInfo;
public class ReportDAO {
    public static List<EmployeePayInfo> employeePayInfoById(int empId) {
    String query = "SELECT e.empid, e.Fname, e.Lname, " +
                   "p.pay_date, p.earnings, p.fed_tax, p.fed_med, p.fed_SS, p.state_tax, p.retire_401k, p.health_care " +
                   "FROM employees e " +
                   "LEFT JOIN payroll p ON e.empid = p.empid " +
                   "WHERE e.empid = ?";

    List<EmployeePayInfo> EmployeePayInfoList = new ArrayList<>();

    try (Connection conn = DatabaseAccessHelper.getConnection();
         PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setInt(1, empId);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            EmployeePayInfo payInfo = new EmployeePayInfo(
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
            EmployeePayInfoList.add(payInfo);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    return EmployeePayInfoList.isEmpty() ? null : EmployeePayInfoList; // Return null if no records are found
}
public static float totalPayByJobtitle(int job_title_id){
    String query = "SELECT SUM(p.earnings) AS total_pay " +
                       "FROM payroll p " +
                       "JOIN employees e ON p.empid = e.empid " +
                       "JOIN employee_job_titles ejt ON e.empid = ejt.empid " +
                       "WHERE ejt.job_title_id = ?";

        float totalPay = 0.0f;

        try (Connection conn = DatabaseAccessHelper.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, job_title_id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                totalPay = rs.getFloat("total_pay");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return totalPay;
}
public static float totalPayByDivision(int job_title_id){
    String query = "SELECT SUM(p.earnings) AS total_pay " +
                       "FROM payroll p " +
                       "JOIN employees e ON p.empid = e.empid " +
                       "JOIN employee_division ed ON e.empid = ed.empid " +
                       "WHERE ed.div_ID = ?";

        float totalPay = 0.0f;

        try (Connection conn = DatabaseAccessHelper.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, job_title_id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                totalPay = rs.getFloat("total_pay");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return totalPay;
}
}
