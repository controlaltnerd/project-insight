//this class is for viewing report
package com.assemblers.app.APIController;
import com.assemblers.app.DatabaseAccess.ReportDAO;
import com.assemblers.app.Models.EmployeePayInfo;
public class Report {
    public static EmployeePayInfo report(int empId){
        return ReportDAO.getEmployeePayInfoById(empId);
    }
}
