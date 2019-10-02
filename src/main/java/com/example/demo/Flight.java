package com.example.demo;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import java.time.LocalDate;
// import java.time.LocalDateTime;
import java.util.Date;
// import java.text.DecimalFormat;

@Entity
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    // @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss Z")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    //private LocalDateTime date;

    private String beginAirport;
    private String endAirport;
    private String airline;
    private double price;
    // private static DecimalFormat df2 = new DecimalFormat("#.##");

    // Constructors
    public Flight() {
    }

    public Flight(Date date, String beginAirport, String endAirport, String airline, double price) {
    // public Flight(LocalDateTime date, String beginAirport, String endAirport, String airline, double price) {
        this.date = date;
        this.beginAirport = beginAirport;
        this.endAirport = endAirport;
        this.airline = airline;
        this.price = price;
    }

    // Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    /*
    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
    */
    
    public String getBeginAirport() {
        return beginAirport;
    }

    public void setBeginAirport(String beginAirport) {
        this.beginAirport = beginAirport;
    }

    public String getEndAirport() {
        return endAirport;
    }

    public void setEndAirport(String endAirport) {
        this.endAirport = endAirport;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
