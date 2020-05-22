package pl.gwlodawiec.employeesapp.database.dao;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import pl.gwlodawiec.employeesapp.model.Employee;
import pl.gwlodawiec.employeesapp.model.EmployeeAddresses;

@Dao
public interface EmployeeDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    public void saveEmployee(Employee employee);

    @Query("select * from employee order by id asc")
    LiveData<List<Employee>> getAll();

    @Transaction
    @Query("select * from employee")
    List<EmployeeAddresses> getEmployeesWithAddresses();
}
