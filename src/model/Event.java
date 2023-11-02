package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Event implements Serializable {
    private String eventId;
    private String eventName;
    private LocalDate startDate;
    private LocalDate endDate;
    private String customerId;

    public Event(){
        this.eventId = "eventId";
        this.eventName = "eventName";
        this.startDate = null;
        this.endDate = null;
        this.customerId = "customerId";
    }

    public Event(String eventId, String eventName, LocalDate startDate, LocalDate endDate, String customerId) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.customerId = customerId;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}
