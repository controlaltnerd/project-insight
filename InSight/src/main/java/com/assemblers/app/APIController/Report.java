//this class is for viewing report
package com.assemblers.app.APIController;
import com.assemblers.app.DatabaseAccess.ReportDAO;
import com.assemblers.app.Models.EmployeePayInfo;
import java.util.List;
public class Report {
    public static List<EmployeePayInfo> getEmployeePayReport(int empId){
        return ReportDAO.employeePayInfoById(empId);
    }
    public static float getTotalPayByJobtitle(int jobtitle_id){
        return ReportDAO.totalPayByJobtitle(jobtitle_id);
    }
    public static float getEmployeeReportByDivision(int division_id){
        return ReportDAO.totalPayByDivision(division_id);
    }
}
