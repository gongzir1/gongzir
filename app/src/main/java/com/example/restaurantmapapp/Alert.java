package com.example.restaurantmapapp;

public class Alert {
    private String LostOrFound;
    private String Name;
    private int Phone;
    private String Description;
    private String Date;
    private String Location;
    //alertID for interacting with the database
    private int alertID;



    //getters and setters for the variables
    public int getAlertID() {
        return alertID;
    }

    public void setAlertID(int AlertID) {
        alertID = AlertID;
    }

    public String getLostOrFound() {
        return LostOrFound;
    }

    public void setLostOrFound(String lostOrFound) {
        LostOrFound = lostOrFound;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getPhone() {
        return Phone;
    }

    public void setPhone(int phone) {
        Phone = phone;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    //two constructors, one with alertID for the database interactions, one without
    public Alert(String lostOrFound, String name, int phone, String description, String date) {
        LostOrFound = lostOrFound;
        Name = name;
        Phone = phone;
        Description = description;
        Date = date;

    }

    public Alert(int AlertID, String lostOrFound, String name, int phone, String description, String date) {
        LostOrFound = lostOrFound;
        Name = name;
        Phone = phone;
        Description = description;
        Date = date;
        alertID = AlertID;

    }
}
