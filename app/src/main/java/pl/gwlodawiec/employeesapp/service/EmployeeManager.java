package pl.gwlodawiec.employeesapp.service;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import pl.gwlodawiec.employeesapp.database.client.DatabaseClient;
import pl.gwlodawiec.employeesapp.model.Employee;
import pl.gwlodawiec.employeesapp.model.types.Gender;
import pl.gwlodawiec.employeesapp.service.helper.EmployeeInput;
import pl.gwlodawiec.employeesapp.util.EmployeeFactory;

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
//        final List<Employee>[] employeeList = new List[]{new ArrayList<Employee>()};
//        DatabaseClient.getInstance(context.getApplicationContext()).getDatabase().employeeDao().getAll().observe(
//                lifecycleOwner, new Observer<List<Employee>>() {
//                    @Override
//                    public void onChanged(List<Employee> employees) {
//                        employeeList[0] = employees;
//                    }
//                }
//        );
//        return employeeList[0];

        return employeeList;
    }

    @Override
    public void addEmployee(EmployeeInput employeeInput) {
        Employee employee = EmployeeFactory.employeeFromEmployeeInput(employeeInput);
        employee.setGender(Gender.MALE);
        saveEmployee(employee);
    }

    private void saveEmployee(Employee employee){
        class SaveEmployee extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                DatabaseClient.getInstance(context.getApplicationContext()).getDatabase().employeeDao().saveEmployee(employee);
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
}
