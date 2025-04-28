//this class is for view Employee Information with @para empid
package com.assemblers.app.APIController;
import com.assemblers.app.Models.Employee;
import com.assemblers.app.DatabaseAccess.EmployeeInfoDAO;
import java.util.List;
public class EmployeeInfo {
   public static Employee viewEmployeeInfoById(int empId) {
       return EmployeeInfoDAO.getEmployeeInfoById(empId);
      }
   public static List <Employee> viewEmployeeInfoByFullName(String Fname, String Lname) {
        return EmployeeInfoDAO.getEmployeeInfoByFullName(Fname, Lname);
      }
   public static List <Employee> viewEmployeeInfoName(String name) {
        return EmployeeInfoDAO.getEmployeeInfoByName(name);
      }
   public static  List <Employee> viewEmployeeInfoBySsn(String ssn) {
         return EmployeeInfoDAO.getEmployeeInfoBySsn(ssn);
      }
   public static List <Employee> viewAllEmployee() {
      return EmployeeInfoDAO.viewAllEmployeeInfo();
      }
   /*public static Employee viewEmployeeInfoByDOB(String DOB) {
      return EmployeeInfoDAO.getEmployeeInfoByDOB(DOB);
   }*/
}
