package pl.gwlodawiec.employeesapp.util;

import pl.gwlodawiec.employeesapp.model.Employee;
import pl.gwlodawiec.employeesapp.service.helper.EmployeeInput;

public class EmployeeFactory {

    public static Employee employeeFromEmployeeInput(EmployeeInput employeeInput){
        Employee employee = new Employee();
        employee.setFirstName(employeeInput.getFirstName());
        employee.setLastName(employeeInput.getLastName());
        employee.setAge(employeeInput.getAge());
        employee.setGender(employeeInput.getGender());
        return employee;
    }
}
