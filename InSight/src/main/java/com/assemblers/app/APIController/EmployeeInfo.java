//this class is for view Employee Information with @para empid
package com.assemblers.app.APIController;
import com.assemblers.app.Models.Employee;
import com.assemblers.app.DatabaseAccess.EmployeeInfoDAO;
public class EmployeeInfo {
    public static Employee viewEmployeeInfoById(int empId) {
       return EmployeeInfoDAO.getEmployeeInfoById(empId);
    }
}
