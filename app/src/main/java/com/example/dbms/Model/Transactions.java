package com.example.dbms.Model;

public class Transactions {
    int transaction_id;
    int agent_id;
    int customer_id;
    String dateFrom;
    String dateTo;
    int amount;

    public Transactions(int transaction_id, int agent_id, int customer_id, String dateFrom, String dateTo, int amount) {
        this.transaction_id = transaction_id;
        this.agent_id = agent_id;
        this.customer_id = customer_id;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.amount = amount;
    }

    public int getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(int transaction_id) {
        this.transaction_id = transaction_id;
    }

    public int getAgent_id() {
        return agent_id;
    }

    public void setAgent_id(int agent_id) {
        this.agent_id = agent_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
