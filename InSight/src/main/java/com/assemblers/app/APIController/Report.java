//this class is for viewing report
package com.assemblers.app.APIController;
import com.assemblers.app.DatabaseAccess.ReportDAO;
public class Report {
    public static void report(){
        ReportDAO.report();
    }
}
