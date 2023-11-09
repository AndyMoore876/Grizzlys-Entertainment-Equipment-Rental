package domain;

import java.io.Serializable;
import java.time.LocalDate;

public class RentalRequest implements Serializable {
    private String customerId;
    private String equipmentId;
    private String employeeId;
    private String status;
    private LocalDate startDate;
    private LocalDate returnDate;



    private float totalCost;

    public RentalRequest() {
        this.customerId = null;
        this.equipmentId = null;
        this.employeeId = null;
        this.status = null;
        this.startDate = null;
        this.returnDate = null;
        this.totalCost = 0;
    }

    public RentalRequest(String customerId, String equipmentId, String employeeId, String status, LocalDate startDate, LocalDate returnDate, float totalCost) {
        this.customerId = customerId;
        this.equipmentId = equipmentId;
        this.employeeId = employeeId;
        this.status = status;
        this.startDate = startDate;
        this.returnDate = returnDate;
        this.totalCost = totalCost;
    }

    public float getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(float totalCost) {
        this.totalCost = totalCost;
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
}
