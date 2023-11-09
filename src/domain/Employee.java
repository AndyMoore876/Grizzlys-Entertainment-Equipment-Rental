package domain;

import java.io.Serializable;

public class Employee extends User implements Serializable {
    private String employeeId;
    private String firstName;
    private String lastName;

    public Employee(){
        this.employeeId = "employeeId";
        this.firstName = "firstName";
        this.lastName = "lastName";
        this.loggedIn = false;//will not be stored in the database will just be used
        this.passwordHash = "passwordHash";
    }

    public Employee(String employeeId, String firstName, String lastName, Boolean loggedIn, String passwordHash) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.loggedIn = loggedIn;
        this.passwordHash = passwordHash;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

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
}