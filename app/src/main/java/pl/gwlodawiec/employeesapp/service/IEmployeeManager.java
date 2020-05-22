package pl.gwlodawiec.employeesapp.service;

import java.util.List;

import androidx.lifecycle.LiveData;
import pl.gwlodawiec.employeesapp.model.Employee;
import pl.gwlodawiec.employeesapp.service.helper.EmployeeInput;

public interface IEmployeeManager {
    public LiveData<List<Employee>> getAllEmployees();
    public void addEmployee(EmployeeInput employeeInput);
}
