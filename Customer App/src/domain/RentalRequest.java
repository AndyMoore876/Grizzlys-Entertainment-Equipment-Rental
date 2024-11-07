package domain;

import java.io.Serializable;
import java.time.Instant;

public class RentalRequest implements Serializable {

    private String rentalRequestId;
    private String customerId;
    private String equipmentId;
    private String employeeId;
    private String status;
    private String startDate;
    private String returnDate;
    private float totalCost;
    private String event;

    public RentalRequest() {
        this.rentalRequestId = null;
        this.customerId = null;
        this.equipmentId = null;
        this.employeeId = null;
        this.status = null;
        this.startDate = null;
        this.returnDate = null;
        this.totalCost = 0;
        this.event = null;
    }

    public RentalRequest(String rentalRequestId, String customerId, String equipmentId, String employeeId, String status, String startDate, String returnDate, float totalCost, String event) {
        this.rentalRequestId = rentalRequestId;
        this.customerId = customerId;
        this.equipmentId = equipmentId;
        this.employeeId = employeeId;
        this.status = status;
        this.startDate = startDate;
        this.returnDate = returnDate;
        this.totalCost = totalCost;
        this.event = event;
    }

    public RentalRequest(String customerId, String equipmentId, String employeeId, String status, String startDate, String returnDate, float totalCost, String event) {
        this.rentalRequestId = genId();
        this.customerId = customerId;
        this.equipmentId = equipmentId;
        this.employeeId = employeeId;
        this.status = status;
        this.startDate = startDate;
        this.returnDate = returnDate;
        this.totalCost = totalCost;
        this.event = event;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String genId(){
        Instant instant3 = Instant.now();
        long timeStampSeconds = instant3.getEpochSecond();

        return "RR" + String.valueOf(timeStampSeconds);
    }

    public String getRentalRequestId() {
        return rentalRequestId;
    }

    public void setRentalRequestId(String rentalRequestId) {
        this.rentalRequestId = rentalRequestId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public float getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(float totalCost) {
        this.totalCost = totalCost;
    }


}
