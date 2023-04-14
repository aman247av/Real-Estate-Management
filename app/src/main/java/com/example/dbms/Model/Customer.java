package com.example.dbms.Model;

public class Customer {
    int customer_id;
    String email;
    String name;
    String password;
    String contact;
    String DOB;

    public Customer(int customer_id, String email, String name, String password, String contact, String DOB) {
        this.customer_id = customer_id;
        this.email = email;
        this.name = name;
        this.password = password;
        this.contact = contact;
        this.DOB = DOB;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }
}
