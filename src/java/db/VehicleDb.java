/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import edu.witc.business.Vehicle;
import edu.witc.utility.DateUtil;
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
public class VehicleDb {
      public static List<Vehicle> getAll() throws SQLException{



ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();

        PreparedStatement ps = null;
        ResultSet rs = null;

       List<Vehicle> vehicles = new ArrayList<>();
        Vehicle vehicle;
        
        String sql = "SELECT id, short_desc, long_desc, cost_per_day, active, date_added, date_modified FROM vehicle_type";
        
        try{
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                vehicle = new Vehicle(rs.getInt("id"), 
                        rs.getString("short_desc"), 
                        rs.getString("long_desc"),
                        rs.getDouble("cost_per_day"),
                        rs.getBoolean("active"), 
                        DateUtil.getLocalDateTime(rs.getTimestamp("date_added")),
                        DateUtil.getLocalDateTime(rs.getTimestamp("date_modified")));
                vehicles.add(vehicle);
            }
            
        }
        catch(SQLException e)
        {
            throw new SQLException("getAll - VehicleDb: " + e);
        }
        finally{
           //close/free your resources
           DbHelper.closeResultSet(rs);
           DbHelper.closePreparedStatement(ps);
           pool.freeConnection(connection);
       }
        
return vehicles;

    }
      
     public static Vehicle getVehicleByID(int id) throws SQLException
      {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Vehicle vehicle = null;
          
           String sql = "SELECT id, short_desc, long_desc, cost_per_day, active, date_added, date_modified FROM vehicle_type WHERE id = ?";
           
           try{
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while(rs.next()){
                     vehicle = new Vehicle(rs.getInt("id"), 
                        rs.getString("short_desc"), 
                        rs.getString("long_desc"),
                        rs.getDouble("cost_per_day"),
                        rs.getBoolean("active"), 
                        DateUtil.getLocalDateTime(rs.getTimestamp("date_added")),
                        DateUtil.getLocalDateTime(rs.getTimestamp("date_modified")));
            }
         }
            catch(SQLException e)
        {            
             throw new SQLException("getCard - VehicleDb: " + e);
        }
     
        finally{
           //close/free your resources
           DbHelper.closeResultSet(rs);
           DbHelper.closePreparedStatement(ps);
           pool.freeConnection(connection);
       }
            
          return vehicle;
      }
             public static void updateVehicle(int vehicleId, double costPerDay) throws SQLException {
        String sql = "UPDATE vehicle_type SET cost_per_day = ?, date_modified=? WHERE id=?";
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();

        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setDouble(1, costPerDay);            
            ps.setDate(2, DateUtil.getSqlDate());
            ps.setInt(3, vehicleId);
              int rs = -1;
           rs = ps.executeUpdate();
        }
        catch (SQLException e) {
            throw new SQLException("CustomerDb updateCustomer: " + e);
        }
        }
}
