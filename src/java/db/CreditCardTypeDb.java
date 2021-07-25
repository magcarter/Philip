/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import edu.witc.business.CreditCardType;
import edu.witc.utility.DateUtil;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

/**
 *
 * @author Maggie
 */
public class CreditCardTypeDb {

    public static CreditCardType getCard(int id) throws SQLException
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        CreditCardType card = null;
        
        String sql = "SELECT id, short_desc, long_desc, active, date_added FROM credit_card_type WHERE id=?";
         try{
            ps = connection.prepareStatement(sql);
             ps.setInt(1, id);
            rs = ps.executeQuery();
            while(rs.next()){
                card = new CreditCardType(rs.getInt("id"), 
                        rs.getString("short_desc"), 
                        rs.getString("long_desc"),
                        rs.getBoolean("active"), 
                        DateUtil.getLocalDateTime(rs.getTimestamp("date_added")));
                
            }
         }
            catch(SQLException e)
        {
              throw new SQLException("getCard - CreditCardTypeDb: " + e);
        }
     
        finally{
           //close/free your resources
           DbHelper.closeResultSet(rs);
           DbHelper.closePreparedStatement(ps);
           pool.freeConnection(connection);
       }
            
        return card;
    }
    public static List<CreditCardType> getAll() throws SQLException{
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();

        PreparedStatement ps = null;
        ResultSet rs = null;

        List<CreditCardType> cards = new ArrayList<>();
        CreditCardType card = null;
        
        String sql = "SELECT id, short_desc, long_desc, active, date_added FROM credit_card_type";
        
        try{
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                card = new CreditCardType(rs.getInt("id"), 
                        rs.getString("short_desc"), 
                        rs.getString("long_desc"),
                        rs.getBoolean("active"), 
                        DateUtil.getLocalDateTime(rs.getTimestamp("date_added")));
                cards.add(card);
            }
            if(!cards.isEmpty()){
                cards.add(0, new CreditCardType());
            }
            
        }
        catch(SQLException e)
        {
             throw new SQLException("getAll - CreditCardTypeDb: " + e);
        }
     
        finally{
           //close/free your resources
           DbHelper.closeResultSet(rs);
           DbHelper.closePreparedStatement(ps);
           pool.freeConnection(connection);
       }
        

        return cards;

    }
}
