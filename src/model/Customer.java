package model;

import java.io.Serializable;

public class Customer implements Serializable{

    public String customerId;
    public String firstName;
    public String lastName;
    public String phoneNumber;
    public String email;
    public float balance;

    public Customer(String customerId, String firstName, String lastName, String phoneNumber, String email, float balance) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.balance = balance;
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

    //createEvent(){}


}