package pl.gwlodawiec.employeesapp.model;

import java.util.List;

import androidx.room.Embedded;
import androidx.room.Relation;

public class EmployeeAddresses {
    @Embedded
    public Employee employee;
    @Relation(
            parentColumn = "id",
            entityColumn = "employeeId"
    )
    public List<Address> addresses;
}
