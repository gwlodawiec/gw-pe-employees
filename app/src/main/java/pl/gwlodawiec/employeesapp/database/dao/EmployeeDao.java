package pl.gwlodawiec.employeesapp.database.dao;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Query;
import pl.gwlodawiec.employeesapp.model.Employee;

@Dao
public interface EmployeeDao {
    @Query("select * from employee")
    List<Employee> getAll();
}
