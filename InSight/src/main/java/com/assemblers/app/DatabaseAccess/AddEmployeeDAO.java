package com.assemblers.app.DatabaseAccess;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.assemblers.app.Models.Employee;

public class AddEmployeeDAO {
    public static int addEmployee(Employee employee){
        String query1 = "INSERT INTO employees " +
                        "(Fname, Lname, email, Salary, SSN, empid) " +
                        "VALUES (?, ?, ?, ?, ?, ?)";

        String query2 = "INSERT INTO employee_job_titles " +
                        "(empid, job_title_id) " +
                        "VALUES (?, ?)";

        try (Connection conn = DatabaseAccessHelper.getConnection()) {
            conn.setAutoCommit(false); // Start transaction
            int rowUpdates;
            try (PreparedStatement stmt1 = conn.prepareStatement(query1)) {
                stmt1.setString(1, employee.getFname());
                stmt1.setString(2, employee.getLname());
                stmt1.setString(3, employee.getEmail());
                stmt1.setDouble(4, employee.getSalary());
                stmt1.setString(5, employee.getSsn());
                stmt1.setInt(6, employee.getEmpid());
                rowUpdates = stmt1.executeUpdate();
            }

            int jobTitleId = getJobTitleIdByName(conn, employee.getJob_title());

            try (PreparedStatement stmt2 = conn.prepareStatement(query2)) {
                stmt2.setInt(1, employee.getEmpid());
                stmt2.setInt(2, jobTitleId);
                stmt2.executeUpdate();
            }

            conn.commit();
            return rowUpdates;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    static int getJobTitleIdByName(Connection conn, String jobTitleName) {
        String query = "SELECT job_title_id FROM job_titles WHERE job_title = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, jobTitleName);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("job_title_id");
            } else {
                throw new IllegalArgumentException("Job title not found: " + jobTitleName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
}
