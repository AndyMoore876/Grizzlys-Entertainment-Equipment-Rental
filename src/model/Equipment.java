package model;

import javax.swing.*;
import java.io.Serializable;
import java.util.Date;

public class Equipment implements Serializable {

    private String equipmentId;
    private String equipmentName;
    private String equipmentType;
    private String eventId;
    private Boolean rented;
    private Date dateRented;
    private Date returnDate;
    private float costPerDay;
    private float totalCost;
    private String customerId;

    public Equipment() {
        this.equipmentId = null;
        this.equipmentName = null;
        this.equipmentType = null;
        this.eventId = null;
        this.rented = null;
        this.dateRented = null;
        this.returnDate = null;
        this.costPerDay = 0;
        this.totalCost = 0;
        this.customerId=null;
    }

    public Equipment(String equipmentId, String equipmentName, String equipmentType, String customerId, String eventId, Boolean rented, Date dateRented, Date returnDate, float costPerDay, float totalCost) {
        this.equipmentId = equipmentId;
        this.equipmentName = equipmentName;
        this.equipmentType = equipmentType;
        this.customerId = customerId;
        this.eventId = eventId;
        this.rented = rented;
        this.dateRented = dateRented;
        this.returnDate = returnDate;
        this.costPerDay = costPerDay;
        this.totalCost = totalCost;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
    //setters and getters
    public String getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(String equipmentType) {
        this.equipmentType = equipmentType;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public Boolean getRented() {
        return rented;
    }

    public void setRented(Boolean rented) {
        this.rented = rented;
    }

    public Date getDateRented() {
        return dateRented;
    }

    public void setDateRented(Date dateRented) {
        this.dateRented = dateRented;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public float getCostPerDay() {
        return costPerDay;
    }

    public void setCostPerDay(float costPerDay) {
        this.costPerDay = costPerDay;
    }

    public float getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(float totalCost) {
        this.totalCost = totalCost;
    }
    //end of setters and getters


    @java.lang.Override
    public java.lang.String toString() {
        return "Equipment{" +
                "equipmentId='" + equipmentId + '\'' +
                ", equipmentName=" + equipmentName +
                ", equipmentType='" + equipmentType + '\'' +
                ", eventId='" + eventId + '\'' +
                ", rented=" + rented +
                ", dateRented=" + dateRented +
                ", returnDate=" + returnDate +
                ", costPerDay=" + costPerDay +
                ", totalCost=" + totalCost +
                '}';
    }

    //to be updated for booking in advanced while booked
    public void rent(String customerID, String eventId, Date startDate, Date returnDate){
        if (this.rented){
            JOptionPane.showMessageDialog(null,"Equipment already rented for the selected period and cannot be booked until returned.");
        } else {
            this.dateRented = startDate;
            this.returnDate = returnDate;
            this.rented = true;
        }
    }
}