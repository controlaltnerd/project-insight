package com.assemblers.app.DatabaseAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.assemblers.app.Models.Employee;

public class UpdateEmployeeInfoDAO {
    public static int updateEmployeeInfo(Employee employee) {
        String updateEmployeeQuery = "UPDATE employees " +
                "SET Fname = ?, Lname = ?, email = ?, Salary = ?, SSN = ? " +
                "WHERE empid = ?";

        String updateJobTitleQuery = "UPDATE employee_job_titles " +
                "SET job_title_id = ? WHERE empid = ?";

        try (Connection conn = DatabaseAccessHelper.getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement stmt1 = conn.prepareStatement(updateEmployeeQuery)) {
                stmt1.setString(1, employee.getFname());
                stmt1.setString(2, employee.getLname());
                stmt1.setString(3, employee.getEmail());
                stmt1.setDouble(4, employee.getSalary());
                stmt1.setString(5, employee.getSsn());
                stmt1.setInt(6, employee.getEmpid());
                stmt1.executeUpdate();
            }

            int jobTitleId = AddEmployeeDAO.getJobTitleIdByName(conn, employee.getJob_title());

            try (PreparedStatement stmt2 = conn.prepareStatement(updateJobTitleQuery)) {
                stmt2.setInt(1, jobTitleId);
                stmt2.setInt(2, employee.getEmpid());
                stmt2.executeUpdate();
            }

            conn.commit();
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
