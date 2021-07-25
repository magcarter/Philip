/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import edu.witc.business.EmployeeRoleType;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Maggie
 */
public class EmployeeRoleTypeDb {
     public static List<EmployeeRoleType> getAll() throws SQLException{
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();

        PreparedStatement ps = null;
        ResultSet rs = null;

        List<EmployeeRoleType> employeeRoles = new ArrayList<>();
        EmployeeRoleType employeeRoleType;
        
        String sql = "SELECT id, short_desc, long_desc FROM employee_role_type";
        
        try{
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                employeeRoleType = new EmployeeRoleType(rs.getInt("id"), 
                        rs.getString("short_desc"), 
                        rs.getString("long_desc")
                        );
                employeeRoles.add(employeeRoleType);
            }            
        }
        catch(SQLException e)
        {
             throw new SQLException("getAll - EmployeeRoleTypeDb: " + e);
        }
     
        finally{
           //close/free your resources
           DbHelper.closeResultSet(rs);
           DbHelper.closePreparedStatement(ps);
           pool.freeConnection(connection);
       }
        

        return employeeRoles;

    }
}
