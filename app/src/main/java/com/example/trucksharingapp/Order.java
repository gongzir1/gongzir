package com.example.trucksharingapp;

public class Order {
    private int id,image,imageShare;
    private String title,detail,date,time,location,goodtype,weight,height,length,width,vehicle,username;
    public  Order(int image,String title,String detail,int imageShare){

        this.image=image;
        this.title=title;
        this.detail=detail;
        this.imageShare=imageShare;

    }
    public Order(){}

    public Order(String title, String detail) {
        this.title=title;
        this.detail=detail;
    }

    public int getId(){return this.id;}
    public void  setId(int id){this.id=id;}
    public int getImage(){return  this.image;}
    public void setImage(int image){this.image=image;}
    public String getTitle(){return this.title;}
    public void setTitle(String title){this.title=title;}
    public String getDetail(){return this.detail;}
    public void setDetail(String detail){this.detail=detail;}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getImageShare(){return this.imageShare;}
    public void setImageShare(int imageShare){this.imageShare=imageShare;}
    public String getDate(){return this.date;}
    public void setDate(String date){this.date=date;}
    public String getTime(){return this.time;}
    public void setTime(String time){this.time=time;}
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getGoodtype() {
        return goodtype;
    }

    public void setGoodtype(String goodtype) {
        this.goodtype = goodtype;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

}
