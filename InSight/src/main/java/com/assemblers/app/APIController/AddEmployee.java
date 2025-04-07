package com.assemblers.app.APIController;
import com.assemblers.app.Models.Employee;
import com.assemblers.app.DatabaseAccess.AddEmployeeDAO;
public class AddEmployee {
    public static int addNewEmployee(Employee employee){
        return AddEmployeeDAO.addEmployee(employee);
    }
}
