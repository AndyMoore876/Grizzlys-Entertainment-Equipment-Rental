package domain;

import java.io.Serializable;
import java.time.Instant;

public class Equipment implements Serializable {

    private String equipmentId;
    private String equipmentName;
    private String equipmentType;
    private String event;
    private Boolean rented;
    private String dateRented;
    private String returnDate;
    private float costPerDay;
    private String customerId;

    public Equipment() {
        this.equipmentId = null;
        this.equipmentName = null;
        this.equipmentType = null;
        this.event = null;
        this.rented = null;
        this.dateRented = null;
        this.returnDate = null;
        this.costPerDay = 0;
        this.customerId = null;
    }

    public Equipment(String equipmentId, String equipmentName, String equipmentType, String customerId, String event, Boolean rented, String dateRented, String returnDate, float costPerDay) {
        this.equipmentId = equipmentId;
        this.equipmentName = equipmentName;
        this.equipmentType = equipmentType;
        this.customerId = customerId;
        this.event = event;
        this.rented = rented;
        this.dateRented = dateRented;
        this.returnDate = returnDate;
        this.costPerDay = costPerDay;
    }

    public Equipment(String equipmentName, String equipmentType, String customerId, String event, Boolean rented, String dateRented, String returnDate, float costPerDay) {
        this.equipmentId = genId();
        this.equipmentName = equipmentName;
        this.equipmentType = equipmentType;
        this.customerId = customerId;
        this.event = event;
        this.rented = rented;
        this.dateRented = dateRented;
        this.returnDate = returnDate;
        this.costPerDay = costPerDay;
    }

    public String genId(){
        Instant instant3 = Instant.now();
        long timeStampSeconds = instant3.getEpochSecond();

        return "EQ" + String.valueOf(timeStampSeconds);
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

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public Boolean getRented() {
        return rented;
    }

    public void setRented(Boolean rented) {
        this.rented = rented;
    }

    public String getDateRented() {
        return dateRented;
    }

    public void setDateRented(String dateRented) {
        this.dateRented = dateRented;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public float getCostPerDay() {
        return costPerDay;
    }

    public void setCostPerDay(float costPerDay) {
        this.costPerDay = costPerDay;
    }

    @Override
    public String toString() {
        return "Equipment{" +
                "equipmentId='" + equipmentId + '\'' +
                ", equipmentName=" + equipmentName +
                ", equipmentType='" + equipmentType + '\'' +
                ", event='" + event + '\'' +
                ", rented=" + rented +
                ", dateRented=" + dateRented +
                ", returnDate=" + returnDate +
                ", costPerDay=" + costPerDay +
                '}';
    }
}