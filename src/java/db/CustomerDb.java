/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import edu.witc.business.Customer;
import edu.witc.utility.DateUtil;
import edu.witc.utility.ValidatorUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Maggie
 */
public class CustomerDb {
      public static Customer getCustomerByLastName(String lastName) throws SQLException {



ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();

        PreparedStatement ps = null;
        ResultSet rs = null;

        Customer customer = null;
        
        String sql = "SELECT id,credit_card_type_id,vehicle_type_id, first_name, last_name, phone_number, can_text, email_address,credit_card_type_id, card_number, card_expiry, vehicle_type_id, active, date_added, date_modified FROM customer WHERE last_name = ?";
        
        try{
            ps = connection.prepareStatement(sql);
             ps.setString(1, lastName);
            rs = ps.executeQuery();
            while(rs.next()){
                customer = new Customer(
                        rs.getInt("id"), 
                        rs.getInt("credit_card_type_id"),
                        rs.getInt("vehicle_type_id"),
                        rs.getString("first_name"), 
                        rs.getString("last_name"),
                        rs.getString("phone_number"),
                       Customer.getFormattedPhoneNumber(rs.getString("phone_number")),                                  
                        rs.getBoolean("can_text"),
                          rs.getString("email_address"),
                       CreditCardTypeDb.getCard(rs.getInt("credit_card_type_id")),
                        rs.getString("card_number"),
                        rs.getString("card_expiry"),
                        VehicleDb.getVehicleByID(rs.getInt("vehicle_type_id")),
                        rs.getBoolean("active"), 
                        DateUtil.getLocalDateTime(rs.getTimestamp("date_added")),
                        DateUtil.getLocalDateTime(rs.getTimestamp("date_modified")));
               
            }
             if(customer==null)
            {
                customer = new Customer(-1);
            }
        }
        catch(SQLException e)
        {
            throw new SQLException("getCustomerByLastName - CustomerDb: " + e);
        }
        finally{
           //close/free your resources
           DbHelper.closeResultSet(rs);
           DbHelper.closePreparedStatement(ps);
           pool.freeConnection(connection);
       }
        
return customer;

    }  
     public static Customer getCustomerByPhoneNumber(String phone) throws SQLException {



ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();

        PreparedStatement ps = null;
        ResultSet rs = null;

        Customer customer = null;
        
        String sql = "SELECT id,credit_card_type_id,vehicle_type_id, first_name, last_name, phone_number, can_text, email_address,credit_card_type_id, card_number, card_expiry, vehicle_type_id, active, date_added, date_modified FROM customer WHERE phone_number = ?";
        
        try{
            ps = connection.prepareStatement(sql);
             ps.setString(1, phone);
            rs = ps.executeQuery();
            while(rs.next()){
                customer = new Customer(
                        rs.getInt("id"), 
                        rs.getInt("credit_card_type_id"),
                        rs.getInt("vehicle_type_id"),
                        rs.getString("first_name"), 
                        rs.getString("last_name"),
                         rs.getString("phone_number"),
                       Customer.getFormattedPhoneNumber(rs.getString("phone_number")),                                  
                        rs.getBoolean("can_text"),
                          rs.getString("email_address"),
                       CreditCardTypeDb.getCard(rs.getInt("credit_card_type_id")),
                        rs.getString("card_number"),
                        rs.getString("card_expiry"),
                        VehicleDb.getVehicleByID(rs.getInt("vehicle_type_id")),
                        rs.getBoolean("active"), 
                        DateUtil.getLocalDateTime(rs.getTimestamp("date_added")),
                        DateUtil.getLocalDateTime(rs.getTimestamp("date_modified")));
               
            }
             if(customer==null)
            {
                customer = new Customer(-1);
            }
        }
        catch(SQLException e)
        {
            throw new SQLException("getCustomerByPhone - CustomerDb: " + e);
        }
        finally{
           //close/free your resources
           DbHelper.closeResultSet(rs);
           DbHelper.closePreparedStatement(ps);
           pool.freeConnection(connection);
       }
        
return customer;

    }  
     public static List<Customer> getAll() throws SQLException {



ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();

        PreparedStatement ps = null;
        ResultSet rs = null;

       List<Customer> customers = new ArrayList<>();
        Customer customer = null;
        
        String sql = "SELECT id,credit_card_type_id,vehicle_type_id, first_name, last_name, phone_number, can_text, email_address,credit_card_type_id, card_number, card_expiry, vehicle_type_id, active, date_added, date_modified FROM customer";
        
        try{
            ps = connection.prepareStatement(sql);
            
            rs = ps.executeQuery();
            while(rs.next()){
                customer = new Customer(
                        rs.getInt("id"), 
                        rs.getInt("credit_card_type_id"),
                        rs.getInt("vehicle_type_id"),
                        rs.getString("first_name"), 
                        rs.getString("last_name"),
                       Customer.getFormattedPhoneNumber(rs.getString("phone_number")),                                  
                        rs.getBoolean("can_text"),
                          rs.getString("email_address"),
                       CreditCardTypeDb.getCard(rs.getInt("credit_card_type_id")),
                        rs.getString("card_number"),
                        rs.getString("card_expiry"),
                        VehicleDb.getVehicleByID(rs.getInt("vehicle_type_id")),
                        rs.getBoolean("active"), 
                       DateUtil.getLocalDateTime(rs.getTimestamp("date_added")),
                       DateUtil.getLocalDateTime(rs.getTimestamp("date_modified")));
                customers.add(customer);
            }
            
        }
        catch(SQLException e)
        {
            throw new SQLException("getAll - CustomerDb: " + e);
        }
        finally{
           //close/free your resources
           DbHelper.closeResultSet(rs);
           DbHelper.closePreparedStatement(ps);
           pool.freeConnection(connection);
       }
        
return customers;

    }
    
        public static int insertCustomer(Customer customer) throws SQLException{
        int id=-1;
        String sql = "INSERT INTO customer(first_name, last_name, phone_number, can_text, "
                + "email_address, credit_card_type_id, card_number, card_expiry, vehicle_type_id, "
                + "active, date_added, date_modified)  VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";

        int rowsAffected = 0;
        
    ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();

        PreparedStatement ps = null;
        

        try  
        {
              ps = connection.prepareStatement(sql, ps.RETURN_GENERATED_KEYS);
              
              
            ps.setString(1, customer.getFirstName());
            ps.setString(2, customer.getLastName());
            ps.setString(3, customer.getPhoneNumber());
            ps.setBoolean(4, customer.getCanText());
            ps.setString(5, customer.getEmail());
            ps.setInt(6, customer.getCardId());
            ps.setString(7, ValidatorUtil.replaceCharacters(customer.getCreditCardNumber()));
            ps.setString(8, customer.getCardExpiry());
            ps.setInt(9, customer.getVehicleId());
            ps.setBoolean(10, customer.getIsActive());
            ps.setDate(11, DateUtil.getSqlDate());
            ps.setTimestamp(12, DateUtil.getSqlTimestamp());
            rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    id = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new SQLException("CustomerDb insert: " + e);
        }   
        
        return id;
    }
        public static void updateCustomer(Customer customer) throws SQLException {
        String sql = "UPDATE customer SET first_name = ?, last_name =?, phone_number =?, can_text =?, email_Address =?, credit_card_type_id=?, card_number=?, card_expiry=?, vehicle_type_id=?, active=?, date_modified=? WHERE id=?";
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();

        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, customer.getFirstName());
            ps.setString(2, customer.getLastName());
            ps.setString(3,customer.getPhoneNumber());
            ps.setBoolean(4, customer.getCanText());
            ps.setString(5, customer.getEmail());
            ps.setInt(6, customer.getCardId());
            ps.setString(7, customer.getCreditCardNumber());
            ps.setString(8, customer.getCardExpiry());
            ps.setInt(9, customer.getVehicleId());
            ps.setBoolean(10, customer.getIsActive());
            ps.setTimestamp(11, DateUtil.getSqlTimestamp());
            ps.setInt(12, customer.getCustomerId());
              int rs = -1;
           rs = ps.executeUpdate();
        }
        catch (SQLException e) {
            throw new SQLException("CustomerDb updateCustomer: " + e);
        }
        }
}
