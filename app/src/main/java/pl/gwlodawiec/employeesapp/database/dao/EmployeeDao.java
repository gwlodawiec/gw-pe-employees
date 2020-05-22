package pl.gwlodawiec.employeesapp.database.dao;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;
import pl.gwlodawiec.employeesapp.model.Employee;
import pl.gwlodawiec.employeesapp.model.EmployeeAddresses;

@Dao
public interface EmployeeDao {
    @Query("select * from employee")
    List<Employee> getAll();

    @Transaction
    @Query("select * from employee")
    List<EmployeeAddresses> getEmployeesWithAddresses();
}
