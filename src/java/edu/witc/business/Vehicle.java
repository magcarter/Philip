/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.witc.business;

import db.VehicleDb;
import java.io.Serializable;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Maggie
 */
public class Vehicle implements Serializable{
     private int id;
    private String shortDesc;
    private String longDesc;
    private double costPerDay;
    private Boolean isActive;
    private LocalDateTime dateAdded;
    private LocalDateTime dateModified;
    
    public Vehicle()
    {
         id=0;
        longDesc="Choose One";
    }
    
      public Vehicle(int id, String shortDesc, String longDesc, double costPerDay, Boolean isActive, LocalDateTime dateAdded, LocalDateTime dateModified)
    {
         this.id = id;
         this.shortDesc = shortDesc;
         this.longDesc = longDesc;
         this.costPerDay = costPerDay;
         this.isActive = isActive;
         this.dateAdded = dateAdded;
        this.dateModified = dateModified;
         
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getLongDesc() {
        return longDesc;
    }

    public void setLongDesc(String longDesc) {
        this.longDesc = longDesc;
    }

    public double getCostPerDay() {
        return costPerDay;
    }

    public void setCostPerDay(double costPerDay) {
        this.costPerDay = costPerDay;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public LocalDateTime getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDateTime dateAdded) {
        this.dateAdded = dateAdded;
    }

    public LocalDateTime getDateModified() {
        return dateModified;
    }

    public void setDateModified(LocalDateTime dateModified) {
        this.dateModified = dateModified;
    }
    
      public static void createVehicleList(HttpServletRequest request) throws SQLException {
        HttpSession session = request.getSession();
        List<Vehicle> vehicles = null;
        try {
            vehicles = VehicleDb.getAll();
        } catch (SQLException ex) {
            throw new SQLException("buildVehicleList - Vehicle.java: " + ex);
        }
        session.setAttribute("vehicles", vehicles);
    }
      
}
