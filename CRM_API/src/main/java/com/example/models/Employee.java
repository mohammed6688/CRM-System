package com.example.models;

public class Employee {
    private int ID;
    private String Fname;
    private String Lname;
    private String City;
    private String Country;
    private String PhoneNumber;
    private int TeamId;

    public Employee(int ID, String fname, String lname, String city, String country, String phoneNumber, int teamId) {
        this.ID = ID;
        Fname = fname;
        Lname = lname;
        City = city;
        Country = country;
        PhoneNumber = phoneNumber;
        TeamId = teamId;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getFname() {
        return Fname;
    }

    public void setFname(String fname) {
        Fname = fname;
    }

    public String getLname() {
        return Lname;
    }

    public void setLname(String lname) {
        Lname = lname;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public int getTeamId() {
        return TeamId;
    }

    public void setTeamId(int teamId) {
        TeamId = teamId;
    }
}
