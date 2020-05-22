package pl.gwlodawiec.employeesapp.service.helper;

import java.util.ArrayList;
import java.util.List;

public class EmployeeInputResponse {
    private EmployeeInput input;
    private List<String> errors = new ArrayList<String>();

    public EmployeeInput getInput() {
        return input;
    }

    public void setInput(EmployeeInput input) {
        this.input = input;
    }

    public List<String> getErrors() {
        if(this.errors == null){
            return new ArrayList<String>();
        }
        return this.errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public void addError(String error){
        getErrors().add(error);
    }

    public boolean hasErrors() {
        return (this.errors != null && !this.errors.isEmpty());
    }
}
