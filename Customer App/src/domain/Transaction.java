package domain;

import java.io.Serializable;
import java.time.Instant;

public class Transaction implements Serializable {
    private String transactionId;
    private String transactionDate;
    private String transactionTime;
    private String equipmentId;


    private String rentalRequestId;
    private String event;
    private String customerId;
    private String employeeId;
    private float totalAmount;


    public Transaction(){
        this.transactionId = "transactionId";
        this.transactionDate = null;
        this.transactionTime = null;
        this.equipmentId = null;
        this.event = null;
        this.customerId = "customerId";
        this.employeeId = "employeeId";
        this.totalAmount = 0;
        this.rentalRequestId = "";
    }

    public Transaction(String transactionId, String transactionDate, String transactionTime, String equipmentId, String event, String customerId, String employeeId, float totalAmount, String rrId) {
        this.transactionId = transactionId;
        this.transactionDate = transactionDate;
        this.transactionTime = transactionTime;
        this.equipmentId = equipmentId;
        this.event = event;
        this.customerId = customerId;
        this.employeeId = employeeId;
        this.totalAmount = totalAmount;
        this.rentalRequestId = rrId;
    }

    public Transaction(String transactionDate, String transactionTime, String equipmentId, String event, String customerId, String employeeId, float totalAmount, String rrId) {
        this.transactionId = genId();
        this.transactionDate = transactionDate;
        this.transactionTime = transactionTime;
        this.equipmentId = equipmentId;
        this.event = event;
        this.customerId = customerId;
        this.employeeId = employeeId;
        this.totalAmount = totalAmount;
        this.rentalRequestId = rrId;
    }

    public String genId(){
        Instant instant3 = Instant.now();
        long timeStampSeconds = instant3.getEpochSecond();

        return "EQ" + String.valueOf(timeStampSeconds);
    }

    public String getRentalRequestId() {
        return rentalRequestId;
    }

    public void setRentalRequestId(String rentalRequestId) {
        this.rentalRequestId = rentalRequestId;
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }
    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(String transactionTime) {
        this.transactionTime = transactionTime;
    }

    public String getequipmentId() {
        return equipmentId;
    }

    public void setequipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
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
