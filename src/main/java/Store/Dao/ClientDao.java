package Store.Dao;

import Store.Model.Client;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.*;

public class ClientDao extends ProductDao {
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
            pstmt.setString(4, client.getUserPassword());
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

    public static boolean clientLogIn(String email, String userPassword) {
        boolean status;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection(getUrl(), getUsername(), getPassword());
            pstmt = conn.prepareStatement("SELECT email, userpassword FROM clientinfo " +
                    "WHERE email=? AND userpassword=?");
            pstmt.setString(1, email);
            pstmt.setString(2, userPassword);
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

    public static Client fetchClientProfile() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            HttpServletRequest request = ServletActionContext.getRequest();
            HttpSession session = request.getSession();
            String email = (String) session.getAttribute("email");
            conn = DriverManager.getConnection(getUrl(), getUsername(), getPassword());
            pstmt = conn.prepareStatement("SELECT email, userpassword,firstName,lastName FROM clientinfo " +
                    "WHERE email=? ");
            pstmt.setString(1, email);
            rs = pstmt.executeQuery();
            rs.next();
            Client client = new Client();
            client.setEmail(rs.getString("email"));
            client.setFirstName(rs.getString("firstName"));
            client.setLastName(rs.getString("lastName"));
            client.setUserPassword(rs.getString("userpassword"));
            return client;
        } catch (Exception e) {
            e.printStackTrace();
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
        return null;
    }

    public static void updateClientInfo(Client client) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            HttpServletRequest request = ServletActionContext.getRequest();
            HttpSession session = request.getSession();
            String email = (String) session.getAttribute("email");
            conn = DriverManager.getConnection(getUrl(), getUsername(), getPassword());
            pstmt=conn.prepareStatement("SET FOREIGN_KEY_CHECKS=0");
            pstmt.executeUpdate();
            pstmt = conn.prepareStatement("UPDATE clientinfo SET firstName=?,lastName=?," +
                    "email=?, userpassword=? WHERE email=?");
            pstmt.setString(1, client.getFirstName());
            pstmt.setString(2, client.getLastName());
            pstmt.setString(3, client.getEmail());
            pstmt.setString(4, client.getUserPassword());
            pstmt.setString(5, email);
            pstmt.executeUpdate();

            pstmt = conn.prepareStatement("UPDATE shoppingcart SET email=? WHERE email=?");
            pstmt.setString(1, client.getEmail());
            pstmt.setString(2, email);
            pstmt.executeUpdate();

            pstmt = conn.prepareStatement("UPDATE orders SET email=? WHERE email=?");
            pstmt.setString(1, client.getEmail());
            pstmt.setString(2, email);
            pstmt.executeUpdate();

            pstmt=conn.prepareStatement("SET FOREIGN_KEY_CHECKS=1");
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
}
