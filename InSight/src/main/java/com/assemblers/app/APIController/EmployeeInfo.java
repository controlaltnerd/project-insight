//this class is for view Employee Information with @para empid
package com.assemblers.app.APIController;
import com.assemblers.app.Models.Employee;
import com.assemblers.app.DatabaseAccess.EmployeeInfoDAO;
import java.util.List;
public class EmployeeInfo {
    public static Employee viewEmployeeInfoById(int empId) {
       return EmployeeInfoDAO.getEmployeeInfoById(empId);
    }
    public static Employee viewEmployeeInfoByName(String Fname, String Lname) {
        return EmployeeInfoDAO.getEmployeeInfoByName(Fname, Lname);
     }
   public static Employee viewEmployeeInfoBySsn(String ssn) {
        return EmployeeInfoDAO.getEmployeeInfoBySsn(ssn);
     }
   public static List <Employee> viewAllEmployee() {
      return EmployeeInfoDAO.viewAllEmployeeInfo();
   }
   /*public static Employee viewEmployeeInfoByDOB(String DOB) {
      return EmployeeInfoDAO.getEmployeeInfoByDOB(DOB);
   }*/
}
