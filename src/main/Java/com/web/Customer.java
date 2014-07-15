package com.web;

/**
 * Created by R500 on 15.7.2014 г..
 */
public class Customer {

    private int customerID;
    private String customerName;
    private String City;

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }
    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public String getCustomerName() {
        return customerName;
    }

    public void setCity(String City) {
        this.City = City;
    }
    public String getCity() {
        return City;
    }
}

