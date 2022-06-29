package com.example.models;

public class Team {
    private int ID ;
    private String Name ;
    private String SLA ;
    private String Grade;

    public Team(int ID, String name, String SLA, String grade) {
        this.ID = ID;
        Name = name;
        this.SLA = SLA;
        Grade = grade;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSLA() {
        return SLA;
    }

    public void setSLA(String SLA) {
        this.SLA = SLA;
    }

    public String getGrade() {
        return Grade;
    }

    public void setGrade(String grade) {
        Grade = grade;
    }
}
