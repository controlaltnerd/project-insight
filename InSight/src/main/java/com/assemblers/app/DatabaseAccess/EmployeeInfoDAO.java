package com.assemblers.app.DatabaseAccess;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.assemblers.app.Models.Employee;
public class EmployeeInfoDAO {
    public static Employee getEmployeeInfoById(int empId) {
        String query = "SELECT e.empid, e.Fname, e.Lname, jt.job_title, e.email, e.Salary, e.SSN " +
                       "FROM employees e " +
                       "LEFT JOIN employee_job_titles ejt ON e.empid = ejt.empid " +
                       "LEFT JOIN job_titles jt ON ejt.job_title_id = jt.job_title_id " +
                       "WHERE e.empid = ?";
        
        try (Connection conn = DatabaseAccessHelper.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, empId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Employee(
                    rs.getInt("empid"),
                    rs.getString("Fname"),
                    rs.getString("Lname"),
                    rs.getString("job_title"),
                    rs.getString("email"),
                    rs.getDouble("Salary"),
                    rs.getString("SSN")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static List<Employee> viewAllEmployeeInfo() {
    String query = "SELECT e.empid, e.Fname, e.Lname, jt.job_title, e.email, e.Salary, e.SSN " +
                    "FROM employees e " +
                    "LEFT JOIN employee_job_titles ejt ON e.empid = ejt.empid " +
                    "LEFT JOIN job_titles jt ON ejt.job_title_id = jt.job_title_id";
    
    List<Employee> employeeList = new ArrayList<>();
    
    try (Connection conn = DatabaseAccessHelper.getConnection();
         PreparedStatement stmt = conn.prepareStatement(query)) {
        
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Employee employee = new Employee(
                rs.getInt("empid"),
                rs.getString("Fname"),
                rs.getString("Lname"),
                rs.getString("job_title"),
                rs.getString("email"),
                rs.getDouble("Salary"),
                rs.getString("SSN")
            );
            employeeList.add(employee);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    return employeeList;
}
public static Employee getEmployeeInfoBySsn(String ssn) {
    String query =  "SELECT e.empid, e.Fname, e.Lname, jt.job_title, e.email, e.Salary, e.SSN " +
                    "FROM employees e " +
                    "LEFT JOIN employee_job_titles ejt ON e.empid = ejt.empid " +
                    "LEFT JOIN job_titles jt ON ejt.job_title_id = jt.job_title_id " +
                    "WHERE e.SSN = ?";
    
    try (Connection conn = DatabaseAccessHelper.getConnection();
         PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setString(1, ssn);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            return new Employee(
                rs.getInt("empid"),
                rs.getString("Fname"),
                rs.getString("Lname"),
                rs.getString("job_title"),
                rs.getString("email"),
                rs.getDouble("Salary"),
                rs.getString("SSN")
            );
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}
public static Employee getEmployeeInfoByName(String Fname, String Lname) {
    String query =  "SELECT e.empid, e.Fname, e.Lname, jt.job_title, e.email, e.Salary, e.SSN " +
                    "FROM employees e " +
                    "LEFT JOIN employee_job_titles ejt ON e.empid = ejt.empid " +
                    "LEFT JOIN job_titles jt ON ejt.job_title_id = jt.job_title_id " +
                    "WHERE e.Fname = ? AND e.Lname = ?";
    
    try (Connection conn = DatabaseAccessHelper.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setString(1, Fname);
        stmt.setString(2, Lname);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            return new Employee(
                rs.getInt("empid"),
                rs.getString("Fname"),
                rs.getString("Lname"),
                rs.getString("job_title"),
                rs.getString("email"),
                rs.getDouble("Salary"),
                rs.getString("SSN")
            );
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}
/*public static Employee getEmployeeInfoByDOB(String DOB) {
    String query = "SELECT e.empid, e.Fname, e.Lname, jt.job_title, e.email, e.Salary, e.SSN " +
                   "FROM employees e " +
                   "LEFT JOIN employee_job_titles ejt ON e.empid = ejt.empid " +
                   "LEFT JOIN job_titles jt ON ejt.job_title_id = jt.job_title_id " +
                   "WHERE e.empid = ?";
    
    try (Connection conn = DatabaseAccessHelper.getConnection();
         PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setString(1, DOB);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            return new Employee(
                rs.getInt("empid"),
                rs.getString("Fname"),
                rs.getString("Lname"),
                rs.getString("job_title"),
                rs.getString("email"),
                rs.getDouble("Salary"),
                rs.getString("SSN")
            );
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}*/
}