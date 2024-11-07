package domain;


import java.io.Serializable;
import java.time.Instant;

public class Message implements Serializable {

    private String messageId;
    private String messageContent;
    private String customerId;
    private String employeeId;
    private String sentDate;
    private String sentTime;
    private String sender;

    public Message(String messageId, String messageContent, String customerId, String employeeId, String sentDate, String sentTime, String sender) {
        this.messageId = messageId;
        this.messageContent = messageContent;
        this.customerId = customerId;
        this.employeeId = employeeId;
        this.sentDate = sentDate;
        this.sentTime = sentTime;
        this.sender = sender;
    }

    public Message(String messageContent, String customerId, String employeeId, String sentDate, String sentTime) {
        this.messageId = genId();
        this.messageContent = messageContent;
        this.customerId = customerId;
        this.employeeId = employeeId;
        this.sentDate = sentDate;
        this.sentTime = sentTime;
        this.sender = sender;
    }

    public Message() {
        this.messageId = null;
        this.messageContent = null;
        this.customerId = null;
        this.employeeId = null;
        this.sentDate = null;
        this.sentTime = null;
        this.sender  = sender;
    }

    public String genId(){
        Instant instant3 = Instant.now();
        long timeStampSeconds = instant3.getEpochSecond();

        return "MES" + String.valueOf(timeStampSeconds);
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
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

    public String getSentDate() {
        return sentDate;
    }

    public void setSentDate(String sentDate) {
        this.sentDate = sentDate;
    }

    public String getSentTime() {
        return sentTime;
    }

    public void setSentTime(String sentTime) {
        this.sentTime = sentTime;
    }

}
