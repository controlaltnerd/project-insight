//this class is for updating employee info
package com.assemblers.app.APIController;
import com.assemblers.app.Models.Employee;
import com.assemblers.app.DatabaseAccess.UpdateEmployeeInfoDAO;
public class UpdateEmployeeInfo {
    public static int updateInfo(Employee employee){
        return UpdateEmployeeInfoDAO.updateEmployeeInfo(employee);
    }
}
