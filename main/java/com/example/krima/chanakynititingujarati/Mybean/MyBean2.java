package com.example.krima.chanakynititingujarati.Mybean;

/**
 * Created by KRIMA on 21-08-2017.
 */

public class MyBean2 {
    String name,number,city,country,email,state;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    @Override
    public String toString() {
        return "MyBean{" +
                "name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", email='" + email + '\'' +
                ", state='" + state + '\'' +
                '}';
    }

}
