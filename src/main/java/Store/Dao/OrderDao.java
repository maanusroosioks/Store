package Store.Dao;

import Store.Model.Order;
import Store.Model.ShoppingCartItem;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.*;
import java.util.ArrayList;

public class OrderDao extends ProductDao {
    public static void generateOrder(ArrayList<ShoppingCartItem> shoppingCartDataList) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs;
        try {
            HttpServletRequest request = ServletActionContext.getRequest();
            HttpSession session = request.getSession();
            String email = (String) session.getAttribute("email");
            conn = DriverManager.getConnection(getUrl(), getUsername(), getPassword());
            pstmt = conn.prepareStatement("INSERT INTO orders (email) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, email);
            pstmt.executeUpdate();
            rs=pstmt.getGeneratedKeys();
            rs.next();
            int insertedID=rs.getInt(1);
            for(ShoppingCartItem item:shoppingCartDataList){
                pstmt = conn.prepareStatement("INSERT INTO orderproducts (billID,productID) VALUES (?,?)");
                pstmt.setInt(1,insertedID);
                pstmt.setInt(2,item.getProductID());
                pstmt.executeUpdate();
            }
            pstmt = conn.prepareStatement("DELETE FROM shoppingcart WHERE email=?");
            pstmt.setString(1,email);
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

    public static ArrayList<ArrayList<Order>> fetchOrders() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs;
        ArrayList<Order> orderList=new ArrayList<>();
        ArrayList<ArrayList<Order>> orderListByBillID=new ArrayList<>();
        Order order=new Order();
        try {
            HttpServletRequest request = ServletActionContext.getRequest();
            HttpSession session = request.getSession();
            String email = (String) session.getAttribute("email");
            conn = DriverManager.getConnection(getUrl(), getUsername(), getPassword());
            pstmt = conn.prepareStatement("SELECT o.email,o.billID,o.time,op.productID,p.productName,p.productPrice " +
                    "FROM orderproducts op INNER JOIN orders o ON op.billID = o.billID INNER JOIN products p " +
                    "ON op.productID = p.productID WHERE email=?");
            pstmt.setString(1,email);
            rs=pstmt.executeQuery();
            while(rs.next()){
                order=new Order();
                order.setEmail(rs.getString("email"));
                order.setBillID(rs.getInt("billID"));
                order.setTimeStamp(rs.getTimestamp("time"));
                order.setProductID(rs.getInt("productID"));
                order.setProductName(rs.getString("productName"));
                order.setProductPrice(rs.getFloat("productPrice"));
                orderList.add(order);
            }
            int orderIndex=0;
            orderListByBillID.add(new ArrayList<Order>());
            for(Order order1:orderList){
                if(orderListByBillID.get(orderIndex)==null){
                    orderListByBillID.add(new ArrayList<Order>());
                }
                if(orderListByBillID.get(orderIndex).size()>1
                        && order.getBillID()!=orderListByBillID.get(orderIndex).get(orderListByBillID.
                        get(orderIndex).size()-1).getBillID())
                {
                    orderIndex++;
                    orderListByBillID.add(new ArrayList<Order>());
                }
                orderListByBillID.get(orderIndex).add(order1);
            }
           return orderListByBillID;
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
        return orderListByBillID;
    }
}
