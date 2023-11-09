package domain;

import java.time.LocalDate;
import java.time.LocalTime;

public class Message {

    private String messageId;
    private String messageContent;
    private String customerId;
    private String employeeId;
    private LocalDate sentDate;
    private LocalTime sentTime;
    private LocalDate responseDate;
    private LocalTime responseTime;
    private Boolean responded;
    private String response;

    public Message() {
        this.messageId = null;
        this.messageContent = null;
        this.customerId = null;
        this.employeeId = null;
        this.sentDate = null;
        this.sentTime = null;
        this.responseDate = null;
        this.responseTime = null;
        this.responded = false;
    }

    public Message(String messageId, String messageContent, String customerId, String employeeId, LocalDate sentDate, LocalTime sentTime, LocalDate responseDate, LocalTime responseTime, Boolean responded) {
        this.messageId = messageId;
        this.messageContent = messageContent;
        this.customerId = customerId;
        this.employeeId = employeeId;
        this.sentDate = sentDate;
        this.sentTime = sentTime;
        this.responseDate = responseDate;
        this.responseTime = responseTime;
        this.responded = responded;
    }

    public Boolean getResponded() {
        return responded;
    }

    public void setResponded(Boolean responded) {
        this.responded = responded;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
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

    public LocalDate getResponseDate() {
        return responseDate;
    }

    public void setResponseDate(LocalDate responseDate) {
        this.responseDate = responseDate;
    }

    public LocalTime getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(LocalTime responseTime) {
        this.responseTime = responseTime;
    }
}
