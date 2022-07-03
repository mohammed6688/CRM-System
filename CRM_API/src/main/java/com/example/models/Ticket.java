package com.example.models;

public class Ticket {
    private int id	;
    private String TimeCreated;
    private int customer_id;
    private String status;
    private String description ;
    private boolean customer_notification;
    private boolean is_notified ;

    private int sr_id ; //sr_id;
    private int emp_id_for_creation;
    private int emp_id_for_management;

    public Ticket(int customer_id, String description, int sr_id, int emp_id_creation) {
        this.customer_id = customer_id;
        this.description = description;
        this.sr_id = sr_id;
        this.emp_id_for_creation = emp_id_creation;
    }

    public Ticket(int id, String timeCreated, int customer_id, String status, String description, boolean customer_notification, boolean is_notified, int sr_id, int emp_id_for_creation, int emp_id_for_management) {
        this.id = id;
        TimeCreated = timeCreated;
        this.customer_id = customer_id;
        this.status = status;
        this.description = description;
        this.customer_notification = customer_notification;
        this.is_notified = is_notified;
        this.sr_id = sr_id;
        this.emp_id_for_creation = emp_id_for_creation;
        this.emp_id_for_management = emp_id_for_management;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTimeCreated() {
        return TimeCreated;
    }

    public void setTimeCreated(String timeCreated) {
        TimeCreated = timeCreated;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCustomer_notification() {
        return customer_notification;
    }

    public void setCustomer_notification(boolean customer_notification) {
        this.customer_notification = customer_notification;
    }

    public boolean isIs_notified() {
        return is_notified;
    }

    public void setIs_notified(boolean is_notified) {
        this.is_notified = is_notified;
    }

    public int getSr_id() {
        return sr_id;
    }

    public void setSr_id(int sr_id) {
        this.sr_id = sr_id;
    }

    public int getEmp_id_for_creation() {
        return emp_id_for_creation;
    }

    public void setEmp_id_for_creation(int emp_id_for_creation) {
        this.emp_id_for_creation = emp_id_for_creation;
    }

    public int getEmp_id_for_management() {
        return emp_id_for_management;
    }

    public void setEmp_id_for_management(int emp_id_for_management) {
        this.emp_id_for_management = emp_id_for_management;
    }
}
