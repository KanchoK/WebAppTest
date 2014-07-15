package com.web;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by R500 on 10.7.2014 г..
 */
public class CrudDao {

    public static int addUser(User user){
        Connection conn = DBConnection.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        int key = -1;
        try {
            pst = conn.prepareStatement("insert into user (username, password) values (?,?)", Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, user.getUsername());
            pst.setString(2, user.getPassword());
            pst.executeUpdate();
            rs = pst.getGeneratedKeys();
            if (rs.next())
                key = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try{
                if (pst != null)
                    pst.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
            try {
                if (rs != null)
                    rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            DBConnection.closeConnection();
        }
        return key;
    }

    public static int addCustomer(Customer customer){
        Connection conn = DBConnection.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        int key = -1;
        try {
            pst = conn.prepareStatement("insert into jTable (Name, City) values (?,?)", Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, customer.getCustomerName());
            pst.setString(2, customer.getCity());
            pst.executeUpdate();
            rs = pst.getGeneratedKeys();
            if (rs.next())
                key = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try{
                if (pst != null)
                    pst.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
            try {
                if (rs != null)
                    rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            DBConnection.closeConnection();
        }
        return key;
    }

    public static void deleteUser(User user){
        Connection conn = DBConnection.getConnection();
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement("delete from user where userID = ?");
            pst.setInt(1, user.getUserID());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try{
                if (pst != null)
                    pst.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
            DBConnection.closeConnection();
        }
    }

    public static void deleteCustomer(int customerID){
        Connection conn = DBConnection.getConnection();
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement("delete from jTable where ID = ?");
            pst.setInt(1, customerID);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try{
                if (pst != null)
                    pst.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
            DBConnection.closeConnection();
        }
    }

    public static void updateUser(User user){
        Connection conn = DBConnection.getConnection();
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement("update user set username = ?, password = ? where userid = ?");
            pst.setString(1, user.getUsername());
            pst.setString(2, user.getPassword());
            pst.setInt(3, user.getUserID());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try{
                if (pst != null)
                    pst.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
            DBConnection.closeConnection();
        }
    }

    public static void updateCustomer(Customer customer){
        Connection conn = DBConnection.getConnection();
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement("update jTable set Name = ?, City = ? where ID = ?");
            pst.setString(1, customer.getCustomerName());
            pst.setString(2, customer.getCity());
            pst.setInt(3, customer.getCustomerID());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try{
                if (pst != null)
                    pst.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
            DBConnection.closeConnection();
        }
    }

    public static List<User> getAllUsers(){
        Connection conn = DBConnection.getConnection();
        List<User> users = new ArrayList<User>();
        Statement st = null;
        ResultSet rs = null;
        try {
            st = conn.createStatement();
            rs = st.executeQuery("select * from user");
            while (rs.next()){
                User user = new User();
                user.setUserID(rs.getInt("USERID"));
                user.setUsername(rs.getString("USERNAME"));
                user.setPassword(rs.getString("PASSWORD"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try{
                if (st != null)
                    st.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
            try {
                if (rs != null)
                    rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            DBConnection.closeConnection();
            conn = null;
            st = null;
            rs = null;
        }
        return users;
    }

    public static List<Customer> getAllCustomers(){
        Connection conn = DBConnection.getConnection();
        List<Customer> customers = new ArrayList<Customer>();
        Statement st = null;
        ResultSet rs = null;
        try {
            st = conn.createStatement();
            rs = st.executeQuery("select * from jTable");
            while (rs.next()){
                Customer customer = new Customer();
                customer.setCustomerID(rs.getInt("ID"));
                customer.setCustomerName(rs.getString("Name"));
                customer.setCity(rs.getString("City"));
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try{
                if (st != null)
                    st.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
            try {
                if (rs != null)
                    rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            DBConnection.closeConnection();
            conn = null;
            st = null;
            rs = null;
        }
        return customers;
    }

    public static User getUserByID(int userID){
        User user = new User();
        Connection conn = DBConnection.getConnection();
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement("select * from user where userID = ?");
            pst.setInt(1, userID);
            ResultSet rs = pst.executeQuery();

            if(rs.next()){
                user.setUserID(rs.getInt("userID"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try{
                if (pst != null)
                    pst.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
            DBConnection.closeConnection();
        }
        return user;
    }
}
