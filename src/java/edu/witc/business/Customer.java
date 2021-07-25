/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.witc.business;

/**
 *
 * @author Maggie
 */
import db.CustomerDb;
import edu.witc.utility.ValidatorUtil;
import java.io.Serializable;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Customer implements Serializable {

    private int customerId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String formattedPhoneNumber;
    private Boolean canText;  
    private String canTextMessage;
    private String email;
    private CreditCardType creditCardType;
    private String creditCardNumber;
    private String cardExpiry;
    private Vehicle vehicle;
    private int vehicleId;
    private int cardId;
    private Boolean isActive;
    private LocalDateTime dateAdded;
    private LocalDateTime dateModified;
    private String formattedCardNumber;
    private int roleTypeId;

    public Customer() {

    }

    public Customer(int customerId) {
        this.customerId = customerId;

    }
     public Customer(int customerId, int roleTypeId) {
        this.customerId = customerId;
        this.roleTypeId = roleTypeId;

    }

    //to create a new Customer Object from the servlet with no dates
    public Customer(int customerId, int cardId, int vehicleId, String firstName, String lastName, String phoneNumber, Boolean canText, String email,
          CreditCardType creditCardType, String creditCardNumber, String cardExpiry,Vehicle vehicle, String formattedCardNumber) {
        this.customerId = customerId;
        this.cardId = cardId;
        this.vehicleId = vehicleId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;        
        this.canText = canText;
        setCanTextString();
        this.email = email;        
        this.creditCardType = creditCardType;
        this.creditCardNumber = creditCardNumber;
        this.cardExpiry = cardExpiry;
        this.vehicle = vehicle;
        this.isActive = true; 
        this.formattedCardNumber = formattedCardNumber;
    }
    
    //to create a Customer Object from the servlet with dates
    public Customer(int customerId, int cardId, int vehicleId, String firstName, String lastName, String phoneNumber,Boolean canText, String email,
             CreditCardType creditCardType, String creditCardNumber, String cardExpiry, Vehicle vehicle, Boolean isActive,
            LocalDateTime dateAdded, LocalDateTime dateModified) {
        this.customerId = customerId;
        this.cardId = cardId;
        this.vehicleId = vehicleId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.canText = canText;
        setCanTextString();
        this.vehicle = vehicle;
        this.creditCardType = creditCardType;
        this.creditCardNumber = creditCardNumber;
        this.cardExpiry = cardExpiry;          
        this.dateAdded = dateAdded;      
        this.dateModified = dateModified;
        this.isActive = true; 
        
    }  
     //to create a Customer Object from the servlet with dates
    public Customer(int customerId, int cardId, int vehicleId, String firstName, String lastName, String phoneNumber, String formattedPhoneNumber,Boolean canText, String email,
             CreditCardType creditCardType, String creditCardNumber, String cardExpiry, Vehicle vehicle, Boolean isActive,
            LocalDateTime dateAdded, LocalDateTime dateModified) {
        this.customerId = customerId;
        this.cardId = cardId;
        this.vehicleId = vehicleId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.formattedPhoneNumber = formattedPhoneNumber;
        this.canText = canText;
        setCanTextString();
        this.vehicle = vehicle;
        this.creditCardType = creditCardType;
        this.creditCardNumber = creditCardNumber;
        this.cardExpiry = cardExpiry;          
        this.dateAdded = dateAdded;      
        this.dateModified = dateModified;
        this.isActive = true; 
        
    }  
    public String getFormattedPhoneNumber() {
        return formattedPhoneNumber;
    }

    public void setFormattedPhoneNumber(String formattedPhoneNumber) {
        this.formattedPhoneNumber = formattedPhoneNumber;
    }
   
    public int getCustomerId() {
        return customerId;
    }
    

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public Boolean getCanText() {
        return canText;
    }

    public LocalDateTime getDateAdded() {
        return dateAdded;
    }

    public String getCanTextMessage() {
        return canTextMessage;
    }

    public CreditCardType getCreditCardType() {
        return creditCardType;
    }
    public int getCardId()
    {
        return cardId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }
    
    public int getVehicleId() {
        return vehicleId;
    }
    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public String getCardExpiry() {
        return cardExpiry;
    }

    public String getFormattedCardNumber() {
        return formattedCardNumber;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public LocalDateTime getDateModified() {
        return dateModified;
    }

    public void setDateModified(LocalDateTime dateModified) {
        this.dateModified = dateModified;
    }

    public void setFormattedCardNumber(String formattedCardNumber) {
        this.formattedCardNumber = formattedCardNumber;
    }

    public void setCardExpiry(String cardExpiry) {
        this.cardExpiry = cardExpiry;
    }

    public void setCreditCardNumber(String cardNumber) {
        this.creditCardNumber = cardNumber;
    }

    public void setCreditCardType(CreditCardType creditCardType) {
        this.creditCardType = creditCardType;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    private void setCanTextString() {
      if (canText) {
            canTextMessage = "It is okay to text the customer.";
        }
      else
      {
          canTextMessage = "It is not okay to text the customer.";
      }
    }

    public void setDateAdded(LocalDateTime dateAdded) {
        this.dateAdded = dateAdded;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCanText(Boolean canText) {
        this.canText = canText;
    }

    public int getRoleTypeId() {
        return roleTypeId;
    }

    public void setRoleTypeId(int roleTypeId) {
        this.roleTypeId = roleTypeId;
    }

    public static String getFormattedPhoneNumber(String phoneNumber) {
        StringBuilder str = new StringBuilder();
        phoneNumber = ValidatorUtil.replaceNonNumerics(phoneNumber);
        String psub1 = phoneNumber.substring(0, 3);
        String psub2 = phoneNumber.substring(3, 6);
        String psub3 = phoneNumber.substring(6, 10);

        phoneNumber = str.append("(").append(psub1).append(") ").append(psub2).append("-").append(psub3).toString();
        return phoneNumber;
    }

    public int getRandomId() {
        int Id = ThreadLocalRandom.current().nextInt(2, 1000);

        return Id;
    }
       public static void createCustomerList(HttpServletRequest request) throws SQLException {
        //Puts each customer object into a list which is then saved to the session
        HttpSession session = request.getSession();
        List<Customer> customers = null;
        try {
            customers = CustomerDb.getAll();
        } catch (SQLException ex) {
            throw new SQLException("createCustomerList - Servlet: " + ex);
        }
        session.setAttribute("customerList", customers);

    }
}

//Maggie, you strip the format out of the phone number when validating it for searching. But I put in a formated phone and it came back customer
//not found. I removed the formatting and the customer was found. 
//This is because on line 174 of your Servlet, you send in the phone as it was entered; sans whitespace.
//FIXED line 507 of Customer Servlet. I ran the phoneNumber through the ValidatorUtil.replaceNonNumerics() method. Still not sure why this wasn't
//working though because when I inserted a new customer I was using ValidatorUtil.replaceCharacters() in the sql statement which seemed to 
//replace all the charecters usually used in a formatted phone number. 

//Make sure your customer id input is read only. 
//FIXED added the 'readonly' property to the id textbox in register.jsp

//This time your customer updated, but the time was 00:00:00. Instead of getSqlDate() use getSqlTimestamp(). That gives both date and time.
//Refactor your db package so it is fully qualified.
//FIXED line 172 and 222 of CustomerDb.java