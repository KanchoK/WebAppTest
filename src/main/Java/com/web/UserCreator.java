package com.web;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
* Created by R500 on 9.7.2014 г..
*/
public class UserCreator {
    public static void CreateUser(String name, String pass) {
        Connection conn = null;
        PreparedStatement pst = null;

        try {
            conn = DBConnection.getConnection();
            pst = conn.prepareStatement("insert into user (username, password) values (?,?)");
            pst.setString(1, name);
            pst.setString(2, pass);
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
            try{
                if(conn != null)
                    conn.close();
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }

    }
}
