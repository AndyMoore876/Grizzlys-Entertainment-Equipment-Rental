package model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Message {
    private String message;
    private String customerId;
    private String actionedBy;
    private LocalDate sentDate;
    private LocalTime sentTime;
    private LocalDate actionedDate;
    private LocalTime actionedTime;

    public Message(String message, String customerId, String actionedBy, LocalDate sentDate, LocalTime sentTime, LocalDate actionedDate, LocalTime actionedTime) {
        this.message = message;
        this.customerId = customerId;
        this.actionedBy = actionedBy;
        this.sentDate = sentDate;
        this.sentTime = sentTime;
        this.actionedDate = actionedDate;
        this.actionedTime = actionedTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getActionedBy() {
        return actionedBy;
    }

    public void setActionedBy(String actionedBy) {
        this.actionedBy = actionedBy;
    }

    public LocalDate getSentDate() {
        return sentDate;
    }

    public void setSentDate(LocalDate sentDate) {
        this.sentDate = sentDate;
    }

    public LocalTime getSentTime() {
        return sentTime;
    }

    public void setSentTime(LocalTime sentTime) {
        this.sentTime = sentTime;
    }

    public LocalDate getActionedDate() {
        return actionedDate;
    }

    public void setActionedDate(LocalDate actionedDate) {
        this.actionedDate = actionedDate;
    }

    public LocalTime getActionedTime() {
        return actionedTime;
    }

    public void setActionedTime(LocalTime actionedTime) {
        this.actionedTime = actionedTime;
    }
}
