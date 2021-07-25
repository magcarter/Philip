/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.witc.business;

import db.EmployeeRoleTypeDb;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Maggie
 */
public class EmployeeRoleType {

    private int id;
    private String shortDesc;
    private String longDesc;
    private Boolean isActive;
    private LocalDateTime dateAdded;
    private LocalDateTime dateModified;

    public EmployeeRoleType(int id,String shortDesc,String longDesc) {
        this.id = id;
        this.shortDesc = shortDesc;
        this.longDesc = longDesc;  
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
    
       public static void createEmployeeRoleList(HttpServletRequest request) throws SQLException {
        HttpSession session = request.getSession();
        List<EmployeeRoleType> employeeRoles = null;
        try {
            employeeRoles = EmployeeRoleTypeDb.getAll();
        } catch (SQLException ex) {
            throw new SQLException("buildEmployeeRoleList - EmployeeRoleType.java: " + ex);
        }
        session.setAttribute("employeeRoles", employeeRoles);
    }
       public static EmployeeRoleType getEmployeeRoleType(HttpServletRequest request, int value) {
        //Puts each CreditCardType object into a list which is then saved to the session
        List<EmployeeRoleType> roleTypes = (List<EmployeeRoleType>) request.getSession().getAttribute("employeeRoles");

        EmployeeRoleType selectedRole = null;

        for (EmployeeRoleType currentRole : roleTypes) {

            if (currentRole.getId() == value) {
                selectedRole = currentRole;
            }
        }

        return selectedRole;
    }

}
