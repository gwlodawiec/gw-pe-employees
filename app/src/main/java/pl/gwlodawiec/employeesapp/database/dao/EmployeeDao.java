package pl.gwlodawiec.employeesapp.database.dao;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import pl.gwlodawiec.employeesapp.model.Employee;
import pl.gwlodawiec.employeesapp.model.EmployeeAddresses;

/**
 * DAO for manipulating employee table
 */
@Dao
public interface EmployeeDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    public void saveEmployee(Employee employee);

    @Query("select * from employee order by id asc")
    public LiveData<List<Employee>> getAll();

    @Transaction
    @Query("select * from employee")
    public List<EmployeeAddresses> getEmployeesWithAddresses();

    @Delete
    public void deleteEmployee(Employee employee);
}
