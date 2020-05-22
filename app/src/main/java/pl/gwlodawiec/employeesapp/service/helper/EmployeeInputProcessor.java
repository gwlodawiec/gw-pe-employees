package pl.gwlodawiec.employeesapp.service.helper;

import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import pl.gwlodawiec.employeesapp.R;
import pl.gwlodawiec.employeesapp.model.types.Gender;

/**
 * Process employee input data (validation etc.)
 */
public class EmployeeInputProcessor {

    public static EmployeeInputResponse processEmployeeInput(View view){
        EmployeeInputResponse response = new EmployeeInputResponse();

        EditText firstNameInput = view.findViewById(R.id.firstname_input);
        EditText lastNameInput = view.findViewById(R.id.lastname_input);
        EditText ageInput = view.findViewById(R.id.age_input);
        Spinner genderInput = view.findViewById(R.id.genders_spinner);

        String firstName = firstNameInput.getText().toString();
        String lastName = lastNameInput.getText().toString();
        String ageString = ageInput.getText().toString();
        Gender gender = (Gender) genderInput.getSelectedItem();

        //TODO: implement nicer validation
        if(firstName.isEmpty()){
            firstNameInput.setError("Please, provide first name");
            firstNameInput.requestFocus();
            response.addError("firstName missing");

        }
        if(lastName.isEmpty()){
            lastNameInput.setError("Please, provide last name");
            lastNameInput.requestFocus();
            response.addError("lastName missing");
        }
        Integer age = 0;
        if(!"".equals(ageString)){
            age = Integer.parseInt(ageString);
        } else {
            ageInput.setError("Please, provide proper age value");
            ageInput.requestFocus();
            response.addError("age missing");
        }

        //do not do any further actions
        if(response.hasErrors()){
            return response;
        }

        //Fetch data from input form and store in holder object
        EmployeeInput employeeInput = new EmployeeInput();
        employeeInput.setFirstName(firstName);
        employeeInput.setLastName(lastName);
        employeeInput.setAge(age);
        employeeInput.setGender(gender);

        response.setInput(employeeInput);

        return response;
    }
}
