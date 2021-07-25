/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import edu.witc.utility.DateUtil;
import edu.witc.business.Employee;
import edu.witc.business.EmployeeRoleType;
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
public class EmployeeDb {

    public static List<Employee> getAll() throws SQLException {

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();

        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Employee> employees = new ArrayList<>();
        Employee employee;

        String sql = "SELECT e.id, first_name, last_name, user_name, salted, hashed, e.active, ert.id, short_desc, "
                + "long_desc FROM employee e JOIN employee_role_type ert on e.role_type_id = ert.id";

        try {
            ps = connection.prepareStatement(sql);

            rs = ps.executeQuery();
            while (rs.next()) {
                employee = new Employee(
                        rs.getInt("e.id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("user_name"),
                        rs.getString("salted"),
                        rs.getString("hashed"),
                        new EmployeeRoleType(rs.getInt("ert.id"), 
                        rs.getString("short_desc"), 
                        rs.getString("long_desc")),
                        rs.getBoolean("e.active"));
                employees.add(employee);
            }

        } catch (SQLException e) {
            throw new SQLException("getAll - EmployeeDb: " + e);
        } finally {
            //close/free your resources
            DbHelper.closeResultSet(rs);
            DbHelper.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }

        return employees;

    }
public static Employee getEmployeeById(int id) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Employee employee = null;

        String sql = "SELECT e.id, first_name, last_name, user_name, salted, hashed, e.active, ert.id, short_desc, "
                + "long_desc FROM employee e JOIN employee_role_type ert on e.role_type_id = ert.id WHERE e.id = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
               employee = new Employee(
                        rs.getInt("e.id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("user_name"),
                        rs.getString("salted"),
                        rs.getString("hashed"),
                        new EmployeeRoleType(
                        rs.getInt("ert.id"), 
                        rs.getString("short_desc"), 
                        rs.getString("long_desc")),
                        rs.getBoolean("e.active"));
            }
        } catch (SQLException e) {
            System.out.println("getEmployeeById - EmployeeDb: " + e);
        } finally {
            //close/free your resources
            DbHelper.closeResultSet(rs);
            DbHelper.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        return employee;
    }
    public static int insertEmployee(Employee employee) throws SQLException {
        int id = -1;
        String sql = "INSERT INTO employee(first_name, last_name, user_name, role_type_id,salted, hashed, active, date_added, date_modified) VALUES (?,?,?,?,?,?,?,?,?)";

        int rowsAffected = 0;

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        try {
            ps = connection.prepareStatement(sql, ps.RETURN_GENERATED_KEYS);

            ps.setString(1, employee.getFirstName());
            ps.setString(2, employee.getLastName());
            ps.setString(3, employee.getUsername());
            ps.setInt(4, employee.getRoleTypeId());
            ps.setString(5, employee.getSalted());
            ps.setString(6, employee.getHashed());
            ps.setBoolean(7, true);
            ps.setDate(8, DateUtil.getSqlDate());
            ps.setTimestamp(9, DateUtil.getSqlTimestamp());
            rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    id = rs.getInt(1);
                }
                DbHelper.closeResultSet(rs);
            }
        } catch (SQLException e) {
            throw new SQLException("EmployeeDb insert: " + e);
        } finally {
            //close/free your resources

            DbHelper.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }

        return id;
    }
         public static void updateEmployee(Employee employee) throws SQLException {
         String sql = "UPDATE employee SET first_name = ?, last_name =?, role_type_id=?, active=?, date_modified=? WHERE id=?";
         ConnectionPool pool = ConnectionPool.getInstance();
         Connection connection = pool.getConnection();
         PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, employee.getFirstName());
            ps.setString(2, employee.getLastName());
            ps.setInt(3, employee.getRoleTypeId());
            ps.setBoolean(4, employee.getIsActive());
            ps.setTimestamp(5, DateUtil.getSqlTimestamp());
            ps.setInt(6, employee.getId());
            ps.executeUpdate();
        }
        catch (SQLException e) {
            throw new SQLException("EmployeeDb updateEmployee: " + e);
        }
          finally{
           //close/free your resources
           DbHelper.closePreparedStatement(ps);
           pool.freeConnection(connection);
       }
         }
public static Employee getEmployeeByUsername(String username) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Employee employee = null;
       
            String sql = "SELECT e.id, first_name, last_name, user_name, salted, hashed, e.active, ert.id, short_desc, "
                + "long_desc FROM employee e JOIN employee_role_type ert on e.role_type_id = ert.id WHERE user_name = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            while (rs.next()) {
               employee = new Employee(
                        rs.getInt("e.id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("user_name"),
                        rs.getString("salted"),
                        rs.getString("hashed"),
                        new EmployeeRoleType(
                        rs.getInt("ert.id"), 
                        rs.getString("short_desc"), 
                        rs.getString("long_desc")),
                        rs.getBoolean("e.active"));
            }

            if (employee == null) {
                employee = new Employee(-1);
            }
        } catch (SQLException e) {
            System.out.println("logInByUsernameAndPassword - EmployeeDb: " + e);
        } finally {
            //close/free your resources
            DbHelper.closeResultSet(rs);
            DbHelper.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        return employee;
    }
}
