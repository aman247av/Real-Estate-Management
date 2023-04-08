package com.example.dbms.Model;

public class Agent {
    int agent_id;
    String name;
    String contact;
    String office_address;
    String e_mail;
    String password;

    public Agent(int agent_id, String name, String contact, String office_address, String e_mail, String password) {
        this.agent_id = agent_id;
        this.name = name;
        this.contact = contact;
        this.office_address = office_address;
        this.e_mail = e_mail;
        this.password = password;
    }

    public int getAgent_id() {
        return agent_id;
    }

    public void setAgent_id(int agent_id) {
        this.agent_id = agent_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getOffice_address() {
        return office_address;
    }

    public void setOffice_address(String office_address) {
        this.office_address = office_address;
    }

    public String getE_mail() {
        return e_mail;
    }

    public void setE_mail(String e_mail) {
        this.e_mail = e_mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.e_mail = password;
    }
}
