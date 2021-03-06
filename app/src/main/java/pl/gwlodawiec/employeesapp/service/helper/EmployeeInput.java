package pl.gwlodawiec.employeesapp.service.helper;

import pl.gwlodawiec.employeesapp.model.types.Gender;

/**
 * Helper class for holding employee's data from user input
 */
public class EmployeeInput {

    private String firstName;
    private String lastName;
    private int age;
    private Gender gender;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
