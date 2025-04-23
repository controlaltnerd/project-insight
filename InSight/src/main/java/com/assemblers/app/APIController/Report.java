//this class is for viewing report
package com.assemblers.app.APIController;
import com.assemblers.app.DatabaseAccess.ReportDAO;
import com.assemblers.app.Models.EmployeePayInfo;
import java.util.List;
public class Report {
    public static List<EmployeePayInfo> getEmployeePayByEmpid(int empId){
        return ReportDAO.employeePayInfoById(empId);
    }
    public static float getTotalPayByJobtitle(int jobtitle_id, int month){
        return ReportDAO.totalPayByJobtitle(jobtitle_id, month);
    }
    public static float getTotalPayByDivision(int division_id, int month){
        return ReportDAO.totalPayByDivision(division_id, month);
    }
    public static List<EmployeePayInfo> getAllEmployeePayInfo(){
        return ReportDAO.getAllEmployeePayInfo();
    }
}
