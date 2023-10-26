package model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Transaction {
    public String transactionId;
    public LocalDate transactionDate;
    public LocalTime transactionTime;
    public ArrayList<String> equipmentIdList;
    public ArrayList<String> eventIdList;
    public String customerId;
    public String employeeId;

    public Transaction(String transactionId, LocalDate transactionDate, LocalTime transactionTime, ArrayList<String> equipmentIdList, ArrayList<String> eventIdList, String customerId, String employeeId) {
        this.transactionId = transactionId;
        this.transactionDate = transactionDate;
        this.transactionTime = transactionTime;
        this.equipmentIdList = equipmentIdList;
        this.eventIdList = eventIdList;
        this.customerId = customerId;
        this.employeeId = employeeId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public LocalTime getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(LocalTime transactionTime) {
        this.transactionTime = transactionTime;
    }

    public ArrayList<String> getEquipmentIdList() {
        return equipmentIdList;
    }

    public void setEquipmentIdList(ArrayList<String> equipmentIdList) {
        this.equipmentIdList = equipmentIdList;
    }

    public ArrayList<String> getEventIdList() {
        return eventIdList;
    }

    public void setEventIdList(ArrayList<String> eventIdList) {
        this.eventIdList = eventIdList;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }
}
