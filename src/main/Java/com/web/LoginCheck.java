package com.web;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;

/**
 * Created by R500 on 9.7.2014 г..
 */
public class LoginCheck {
    public static boolean validate(String name, String pass) throws ClassNotFoundException, SQLException {
        boolean result = false;

        Connection conn;
        conn = DBConnection.getConnection();

        PreparedStatement pstmt = conn.prepareStatement("select * from user where username = ? and password = ?");
        pstmt.setString(1, name);
        pstmt.setString(2, pass);

        ResultSet rs = pstmt.executeQuery();
        result = rs.next();

        return result;
    }
}
