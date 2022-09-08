package com.kenzie.app.data;

public class Address {
    // properties
    private String recipient;
    private String street;
    private String city;
    private String state;
    private String zip;

    //constructor
    public Address(String recipient,String street, String city, String state, String zip) {
        this.recipient = recipient;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }

    public Address() {
        // this("","","","",""); another way to do it
        this.recipient = "";
        this.street = "";
        this.city = "";
        this.state = "";
        this.zip = "";
    }

    // setters / getters
    public String getRecipient() {
        return recipient;
    }
    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }
    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
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
    public String getZip() {
        return zip;
    }
    public void setZip(String zip) {
        this.zip = zip;
    }
    @Override
    public String toString() {
        return "Address{" +
                "recipient='" + recipient + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip='" + zip + '\'' +
                '}';
    }
}
