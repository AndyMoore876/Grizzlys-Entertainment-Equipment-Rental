package domain;

import java.io.Serializable;
import java.time.Instant;

public class Customer extends User implements Serializable{

    private String customerId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private float balance;

    public Customer(){
        this.customerId = "customerId";
        this.firstName = "firstName";
        this.lastName = "lastName";
        this.phoneNumber = "phoneNumber";
        this.email = "email";
        this.balance = 0;
    }

    public Customer(String firstName, String lastName, String phoneNumber, String email, float balance, String password) {

        super("", password);

        this.customerId = genId();
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.balance = balance;

        super.setId(this.customerId);
    }

    public Customer(String id, String firstName, String lastName, String phoneNumber, String email, float balance) {

        super(id, "");

        this.customerId = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.balance = balance;

        super.setId(this.customerId);
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public void addToBalance(float amount){
        this.balance+=amount;
    }

    public void minusFromBalance(float amount){
        this.balance-=amount;
    }

    public String genId(){
        Instant instant3 = Instant.now();
        long timeStampSeconds = instant3.getEpochSecond();

        return "CX" + String.valueOf(timeStampSeconds);
    }
}