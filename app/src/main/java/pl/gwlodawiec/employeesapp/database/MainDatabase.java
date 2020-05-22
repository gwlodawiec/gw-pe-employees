package pl.gwlodawiec.employeesapp.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import pl.gwlodawiec.employeesapp.database.dao.EmployeeDao;
import pl.gwlodawiec.employeesapp.model.Address;
import pl.gwlodawiec.employeesapp.model.Employee;
import pl.gwlodawiec.employeesapp.util.GenderConverter;

@Database(entities = {Employee.class, Address.class}, version = 1)
@TypeConverters({GenderConverter.class})
public abstract class MainDatabase extends RoomDatabase {
    public abstract EmployeeDao employeeDao();
}
