package pl.gwlodawiec.employeesapp.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import pl.gwlodawiec.employeesapp.database.dao.EmployeeDao;
import pl.gwlodawiec.employeesapp.model.Employee;

@Database(entities = {Employee.class}, version = 1)
public abstract class MainDatabase extends RoomDatabase {
    public abstract EmployeeDao employeeDao();
}
