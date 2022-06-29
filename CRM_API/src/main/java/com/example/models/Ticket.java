package com.example.models;

public class Ticket {
    private int TicketId;
    private String TimeCreated;
    private int CustomerId;
    private String Status;
    private String Description ;
    private boolean CustomerNotifcation;
    private boolean IsNotified ;

    public Ticket(int ticketId, String timeCreated, int customerId, String status, String description, boolean customerNotifcation, boolean isNotified) {
        TicketId = ticketId;
        TimeCreated = timeCreated;
        CustomerId = customerId;
        Status = status;
        Description = description;
        CustomerNotifcation = customerNotifcation;
        IsNotified = isNotified;
    }

    public int getTicketId() {
        return TicketId;
    }

    public void setTicketId(int ticketId) {
        TicketId = ticketId;
    }

    public String getTimeCreated() {
        return TimeCreated;
    }

    public void setTimeCreated(String timeCreated) {
        TimeCreated = timeCreated;
    }

    public int getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(int customerId) {
        CustomerId = customerId;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public boolean isCustomerNotifcation() {
        return CustomerNotifcation;
    }

    public void setCustomerNotifcation(boolean customerNotifcation) {
        CustomerNotifcation = customerNotifcation;
    }

    public boolean isNotified() {
        return IsNotified;
    }

    public void setNotified(boolean notified) {
        IsNotified = notified;
    }
}
