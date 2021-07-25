/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.witc.business;

import db.EmployeeDb;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Maggie
 */
public class Employee {
    private int id;
    private String firstName;
    private String lastName;
    private EmployeeRoleType employeeRole;
    private int roleTypeId;
    private String username;
    private String salted;
    private String hashed;
//    private String password;
    private Boolean isActive;
    private LocalDateTime dateAdded;
    private LocalDateTime dateModified;
    
    public Employee(int id)
    {
        this.id = id;
        
    }
    //from db
    public Employee(int id, String firstName,String lastName, String username, String salted, String hashed, EmployeeRoleType employeeRole,Boolean isActive)
    {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.salted = salted;
        this.hashed = hashed;
        this.employeeRole = employeeRole;   
        this.isActive = isActive;
    }
    //from servlet
    public Employee(String firstName,String lastName,int roleTypeId, String username,String salted, String hashed)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.roleTypeId = roleTypeId;
        this.username = username; 
        this.salted = salted;
        this.hashed = hashed;    
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public EmployeeRoleType getEmployeeRole() {
        return employeeRole;
    }

    public void setEmployeeRole(EmployeeRoleType employeeRole) {
        this.employeeRole = employeeRole;
    }

    public int getRoleTypeId() {
        return roleTypeId;
    }

    public void setRoleTypeId(int roleTypeId) {
        this.roleTypeId = roleTypeId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }

    public String getSalted() {
        return salted;
    }

    public void setSalted(String salted) {
        this.salted = salted;
    }

    public String getHashed() {
        return hashed;
    }

    public void setHashed(String hashed) {
        this.hashed = hashed;
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
    public static String generateUserName(String firstName, String lastName)
    {
        String username;
        username = firstName.toLowerCase().substring(0,3)+lastName.toLowerCase().substring(0,3);
        
        return username;
    }
     public static void createEmployeeList(HttpServletRequest request) throws SQLException {
        //Puts each customer object into a list which is then saved to the session
        HttpSession session = request.getSession();
        List<Employee> employees = null;
        try {
            employees = EmployeeDb.getAll();
        } catch (SQLException ex) {
            throw new SQLException("createEmployeeList - Servlet: " + ex);
        }
        session.setAttribute("employeeList", employees);

    }
}
