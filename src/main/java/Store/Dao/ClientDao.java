package Store.Dao;

import Store.Model.Client;

import java.sql.*;

public class ClientDao extends ProductDao{
    public static void insertClient(Client client) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DriverManager.getConnection(getUrl(), getUsername(), getPassword());
            pstmt = conn.prepareStatement("INSERT INTO clientinfo (firstName, lastName," +
                    "email,userpassword) VALUES (?,?,?,?)");
            pstmt.setString(1, client.getFirstName());
            pstmt.setString(2, client.getLastName());
            pstmt.setString(3, client.getEmail());
            pstmt.setString(4, client.getUserpassword());
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public static boolean clientLogIn(String email, String userpassword) {
        boolean status;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection(getUrl(), getUsername(), getPassword());
            pstmt = conn.prepareStatement("SELECT email, userpassword FROM clientinfo " +
                    "WHERE email=? AND userpassword=?");
            pstmt.setString(1, email);
            pstmt.setString(2, userpassword);
            rs = pstmt.executeQuery();
            status = rs.next();
            return status;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
