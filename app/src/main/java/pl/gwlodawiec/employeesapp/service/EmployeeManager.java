package pl.gwlodawiec.employeesapp.service;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.List;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import pl.gwlodawiec.employeesapp.database.client.DatabaseClient;
import pl.gwlodawiec.employeesapp.model.Employee;
import pl.gwlodawiec.employeesapp.service.helper.EmployeeInput;
import pl.gwlodawiec.employeesapp.util.EmployeeFactory;

/**
 * Service class for managing employees
 *
 * Allows add/get operations on employees stored at in-memory database
 */
public class EmployeeManager implements IEmployeeManager {

    private Context context;
    private LifecycleOwner lifecycleOwner;
    private LiveData<List<Employee>> employeeList;

    public EmployeeManager(Context context, LifecycleOwner lifecycleOwner){
        this.context = context;
        this.lifecycleOwner = lifecycleOwner;
        employeeList = DatabaseClient.getInstance(context.getApplicationContext()).getDatabase().employeeDao().getAll();
    }

    @Override
    public LiveData<List<Employee>> getAllEmployees() {
        return employeeList;
    }

    @Override
    public void addEmployee(EmployeeInput employeeInput) {
        Employee employee = EmployeeFactory.employeeFromEmployeeInput(employeeInput);
        saveEmployee(employee);
    }

    private void saveEmployee(Employee employee){
        class SaveEmployee extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                DatabaseClient.getInstance(context.getApplicationContext())
                        .getDatabase()
                        .employeeDao()
                        .saveEmployee(employee);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(context.getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
            }
        }
        SaveEmployee saveEmployee = new SaveEmployee();
        saveEmployee.execute();
    }

    public void delete(Employee employee){
        class DeleteEmployee extends AsyncTask<Void, Void, Void>{

            @Override
            protected Void doInBackground(Void... voids) {
                DatabaseClient.getInstance(context.getApplicationContext())
                        .getDatabase()
                        .employeeDao()
                        .deleteEmployee(employee);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(context.getApplicationContext(), "Deleted", Toast.LENGTH_LONG).show();
            }
        }
        DeleteEmployee deleteEmployee = new DeleteEmployee();
        deleteEmployee.execute();
    }
}
