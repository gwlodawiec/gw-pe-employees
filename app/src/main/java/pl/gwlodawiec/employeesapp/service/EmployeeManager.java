package pl.gwlodawiec.employeesapp.service;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.List;

import pl.gwlodawiec.employeesapp.database.client.DatabaseClient;
import pl.gwlodawiec.employeesapp.model.Employee;
import pl.gwlodawiec.employeesapp.model.types.Gender;
import pl.gwlodawiec.employeesapp.service.helper.EmployeeInput;
import pl.gwlodawiec.employeesapp.util.EmployeeFactory;

public class EmployeeManager implements IEmployeeManager {

    private Context context;

    public EmployeeManager(Context context){
        this.context = context;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return null;
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
