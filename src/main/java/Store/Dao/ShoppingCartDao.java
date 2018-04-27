package Store.Dao;

import Store.Model.Product;
import Store.Model.ShoppingCartItem;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCartDao extends ProductDao{
    public static ArrayList<ShoppingCartItem> fetchShoppingCartData() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ArrayList<ShoppingCartItem> shoppingCartDataList;
        ResultSet rs = null;
        ShoppingCartItem cartItem;
        try {
            conn = DriverManager.getConnection(getUrl(), getUsername(), getPassword());
            HttpServletRequest request = ServletActionContext.getRequest();
            HttpSession session = request.getSession();
            String email = (String) session.getAttribute("email");
            pstmt = conn.prepareStatement("SELECT shoppingcart.productID,  " +
                    "shoppingcart.productName ,shoppingcart.productAmount, clientinfo.firstName," +
                    "clientinfo.lastName, shoppingcart.shoppingCartID,shoppingcart.productPrice FROM shoppingcart INNER JOIN " +
                    "clientinfo ON shoppingcart.email=clientinfo.email WHERE shoppingcart.email=?");
            pstmt.setString(1, email);
            rs = pstmt.executeQuery();
            shoppingCartDataList = new ArrayList<>();
            if (rs != null) {
                while (rs.next()) {
                    cartItem = new ShoppingCartItem();
                    cartItem.setClientName(rs.getString("firstName") + " "
                            + rs.getString("lastName"));
                    cartItem.setProductID(rs.getInt("productID"));
                    cartItem.setProductName(rs.getString("productName"));
                    cartItem.setProductAmount(rs.getInt("productAmount"));
                    cartItem.setProductPrice(rs.getFloat("productPrice"));
                    cartItem.setShoppingCartID(rs.getInt("shoppingCartID"));
                    shoppingCartDataList.add(cartItem);
                }
                return shoppingCartDataList;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
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
        return shoppingCartDataList;
    }

    public static void deleteFromShoppingCart(ShoppingCartItem cartItem) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DriverManager.getConnection(getUrl(), getUsername(), getPassword());
            HttpServletRequest request = ServletActionContext.getRequest();
            HttpSession session = request.getSession();
            String email = (String) session.getAttribute("email");
            pstmt = conn.prepareStatement("DELETE FROM shoppingcart WHERE shoppingCartID=? AND email=?");
            pstmt.setInt(1, cartItem.getShoppingCartID());
            pstmt.setString(2, email);
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

    public static void emptyShoppingCart() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DriverManager.getConnection(getUrl(), getUsername(), getPassword());
            HttpServletRequest request = ServletActionContext.getRequest();
            HttpSession session = request.getSession();
            String email = (String) session.getAttribute("email");
            pstmt = conn.prepareStatement("DELETE FROM shoppingcart WHERE email=?");
            pstmt.setString(1, email);
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

    public static void addToShoppingCart(Product productData) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            HttpServletRequest request = ServletActionContext.getRequest();
            HttpSession session = request.getSession();
            String email = (String) session.getAttribute("email");
            conn = DriverManager.getConnection(getUrl(), getUsername(), getPassword());
            pstmt = conn.prepareStatement("INSERT INTO shoppingcart (email,productName, productID, productAmount," +
                    "productType,productPrice) VALUES (?,?,?,?,?,?)");
            pstmt.setString(1, email);
            pstmt.setString(2, productData.getProductName());
            pstmt.setInt(3, productData.getProductID());
            pstmt.setInt(4, 1);
            pstmt.setString(5,productData.getProductType());
            pstmt.setFloat(6,productData.getProductPrice());
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
