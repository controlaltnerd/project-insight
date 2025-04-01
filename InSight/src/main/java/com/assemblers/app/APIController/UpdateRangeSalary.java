//this class is for updating employee's salaray of a specific range
package com.assemblers.app.APIController;
import com.assemblers.app.DatabaseAccess.UpdateRangeSalaryDAO;
public class UpdateRangeSalary {
    public static void updateRangeSalary(int min, int max, int increment){
        UpdateRangeSalaryDAO.updateRangeSalary(min, max, increment);
    }
}
