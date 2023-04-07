package com.example.dbms.Model;

import android.media.Image;

public class Property {
    int property_id;
    String type;
    int area_size;
    int bedroom_count;
    Image image;
    String category;
    int construction_year;
    int rent;
    int selling_price;
    String status;
    String house_no;
    String street;
    String district;
    String city;
    String state;
    int pincode;

    public Property(int property_id, String type, int area_size, int bedroom_count, String category, int construction_year, int rent, int selling_price, String status, String house_no, String street, String district, String city, String state, int pincode) {
        this.property_id = property_id;
        this.type = type;
        this.area_size = area_size;
        this.bedroom_count = bedroom_count;
        this.category = category;
        this.construction_year = construction_year;
        this.rent = rent;
        this.selling_price = selling_price;
        this.status = status;
        this.house_no = house_no;
        this.street = street;
        this.district = district;
        this.city = city;
        this.state = state;
        this.pincode = pincode;
    }

    public int getProperty_id() {
        return property_id;
    }

    public void setProperty_id(int property_id) {
        this.property_id = property_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getArea_size() {
        return area_size;
    }

    public void setArea_size(int area_size) {
        this.area_size = area_size;
    }

    public int getBedroom_count() {
        return bedroom_count;
    }

    public void setBedroom_count(int bedroom_count) {
        this.bedroom_count = bedroom_count;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getConstruction_year() {
        return construction_year;
    }

    public void setConstruction_year(int construction_year) {
        this.construction_year = construction_year;
    }

    public int getRent() {
        return rent;
    }

    public void setRent(int rent) {
        this.rent = rent;
    }

    public int getSelling_price() {
        return selling_price;
    }

    public void setSelling_price(int selling_price) {
        this.selling_price = selling_price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getHouse_no() {
        return house_no;
    }

    public void setHouse_no(String house_no) {
        this.house_no = house_no;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getPincode() {
        return pincode;
    }

    public void setPincode(int pincode) {
        this.pincode = pincode;
    }
}
