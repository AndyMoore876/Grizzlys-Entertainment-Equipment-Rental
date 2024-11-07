package domain;

import java.io.Serializable;
import java.time.Instant;

public class Employee extends User implements Serializable {
    private String employeeId;
    private String firstName;
    private String lastName;

    public Employee(){
        this.employeeId = "employeeId";
        this.firstName = "firstName";
        this.lastName = "lastName";
        this.passwordHash = "passwordHash";
    }

    public Employee(String firstName, String lastName, String passwordHash) {
        super("",passwordHash);
        this.employeeId = genId();
        this.firstName = firstName;
        this.lastName = lastName;
        super.setId(this.employeeId);
    }

    public Employee(String employeeId, String firstName, String lastName, String passwordHash) {
        super(employeeId,passwordHash);
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String genId(){
        Instant instant3 = Instant.now();
        long timeStampSeconds = instant3.getEpochSecond();

        return "EMP" + String.valueOf(timeStampSeconds);
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

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId='" + employeeId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                '}';
    }
}