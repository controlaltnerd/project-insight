//this class is for updating employee's salaray of a specific range
package com.assemblers.app.APIController;
import com.assemblers.app.DatabaseAccess.UpdateRangeSalaryDAO;
public class UpdateRangeSalary {
    public static int updateRangeSalary(double min, double max, double increment){
        return UpdateRangeSalaryDAO.updateRangeSalary(min, max, increment);
    }
}
