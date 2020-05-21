package pl.gwlodawiec.employeesapp.service;

import java.util.List;

import pl.gwlodawiec.employeesapp.model.Employee;

public interface EmployeeManager {
    public List<Employee> getAllEmployees();
    public void addEmployee(Employee employee);
}
