package Store;

import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DatabaseConnection {
    private static final String url = "jdbc:mysql://localhost:3306/new_schema";
    private static final String username = "root";
    private static final String password = "maanus";
    private static List<String> columnNames = new ArrayList<>();

    public Product findProductEntry(int productID) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Product productData = null;
        try {
            conn = DriverManager.getConnection(url, username, password);
            pstmt = conn.prepareStatement("SELECT productID,  productName ,  productAmount , " +
                    " productPrice FROM products WHERE productID=?");
            pstmt.setInt(1, productID);
            rs = pstmt.executeQuery();
            rs.next();
            productData = new Product();
            productData.setProductID(rs.getInt("productID"));
            productData.setProductName(rs.getString("productName"));
            productData.setProductAmount(rs.getInt("productAmount"));
            productData.setProductPrice(rs.getFloat("productPrice"));
            return productData;
        } catch (SQLException e) {
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
    }

    public List<Product> fetchProductTableData() {
        Connection conn = null;
        Statement stmt = null;
        List<Product> productsTableDataList;
        ResultSet rs = null;
        Product productData;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, password);
            String query = "SELECT productID,  productName ,  productAmount ,  productPrice FROM products";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            productsTableDataList = new ArrayList<Product>();
            if (rs != null) {
                while (rs.next()) {
                    productData = new Product();
                    productData.setProductID(rs.getInt("productID"));
                    productData.setProductName(rs.getString("productName"));
                    productData.setProductAmount(rs.getInt("productAmount"));
                    productData.setProductPrice(rs.getFloat("productPrice"));
                    productsTableDataList.add(productData);
                }
                return productsTableDataList;
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
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return productsTableDataList;
    }

    public void updateProductEntry(Product productData) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, password);
            pstmt = conn.prepareStatement("UPDATE  products SET  productName=?, productAmount=?,productPrice=? WHERE productID=?");
            pstmt.setString(1, productData.productName);
            pstmt.setFloat(2, productData.productAmount);
            pstmt.setFloat(3, productData.productPrice);
            pstmt.setInt(4, productData.productID);
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

    public void insertProductEntry(Product productData) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, password);
            pstmt = conn.prepareStatement("INSERT INTO products (productName," +
                    " productAmount,productPrice) VALUES (?,?,?)");
            pstmt.setString(1, productData.productName);
            pstmt.setFloat(2, productData.productAmount);
            pstmt.setFloat(3, productData.productPrice);
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

    public void deleteProductEntry(Product productData) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, password);
            pstmt = conn.prepareStatement("DELETE FROM products WHERE productID=?");
            pstmt.setInt(1, productData.productID);
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

    public void addToShoppingTable(Product productData) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            HttpServletRequest request= ServletActionContext.getRequest();
            HttpSession session=request.getSession();
            String email=(String)session.getAttribute("email");
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, password);
            pstmt = conn.prepareStatement("INSERT INTO shoppingcart (email,productName, productID, productAmount) VALUES (?,?,?,?)");
            pstmt.setString(1, email);
            pstmt.setString(2, productData.productName);
            pstmt.setInt(3,productData.productID);
            pstmt.setInt(4, 1);
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

    public  List<String> fetchProductTypes() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List <String> productTypeList=new ArrayList<>();
        try {
            conn = DriverManager.getConnection(url, username, password);
            pstmt = conn.prepareStatement("SELECT productType FROM products");
            rs = pstmt.executeQuery();
            while(rs.next()){
                String productType=rs.getString("productType");
                productTypeList.add(productType);
            }
            return productTypeList;
        } catch (SQLException e) {
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
    }

    public List<String> fetchProductTableColumns(String tableName){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List <String> productTableColumnNames=new ArrayList<>();
        try {
            conn = DriverManager.getConnection(url, username, password);
            pstmt = conn.prepareStatement("SELECT * FROM ?");
            pstmt.setString(1,tableName);
            rs = pstmt.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            for (int i = 1; i <= columnCount; i++ ) {
                String name = rsmd.getColumnName(i);
                productTableColumnNames.add(name);
                System.out.println(name);
            }
            return productTableColumnNames;
        } catch (SQLException e) {
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
    }
    public List<ShoppingCartItem> fetchShoppingCartData() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        List<ShoppingCartItem> shoppingCartDataList;
        ResultSet rs = null;
        ShoppingCartItem cartItem;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, password);
            HttpServletRequest request= ServletActionContext.getRequest();
            HttpSession session=request.getSession();
            String email=(String)session.getAttribute("email");
            pstmt = conn.prepareStatement("SELECT shoppingcart.productID,  " +
                    "shoppingcart.productName ,shoppingcart.productAmount, clientinfo.firstName," +
                    "clientinfo.lastName, shoppingCartID FROM shoppingcart INNER JOIN " +
                    "clientinfo ON shoppingcart.email=clientinfo.email WHERE shoppingcart.email=?");
            pstmt.setString(1, email);
            rs = pstmt.executeQuery();
            shoppingCartDataList = new ArrayList<ShoppingCartItem>();
            if (rs != null) {
                while (rs.next()) {
                    cartItem = new ShoppingCartItem();
                    cartItem.setClientName(rs.getString("firstName")+ " "
                            +rs.getString("lastName"));
                    cartItem.setProductID(rs.getInt("productID"));
                    cartItem.setProductName(rs.getString("productName"));
                    cartItem.setProductAmount(rs.getInt("productAmount"));
                    cartItem.setShoppingCartID(rs.getInt("shoppingCartID"));
//                    productData.setProductPrice(rs.getFloat("productPrice"));
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

    public void deleteFromShoppingCart(ShoppingCartItem cartItem){
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, password);
            HttpServletRequest request= ServletActionContext.getRequest();
            HttpSession session=request.getSession();
            String email=(String)session.getAttribute("email");
            pstmt = conn.prepareStatement("DELETE FROM shoppingcart WHERE shoppingCartID=? AND email=?");
            pstmt.setInt(1, cartItem.shoppingCartID);
            pstmt.setString(2,email);
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

    public void insertClient(Client client) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, password);
            pstmt = conn.prepareStatement("INSERT INTO clientinfo (firstName, lastName," +
                    "email,userpassword) VALUES (?,?,?,?)");
            pstmt.setString(1, client.firstName);
            pstmt.setString(2, client.lastName);
            pstmt.setString(3, client.email);
            pstmt.setString(4,client.userpassword);
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
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, password);
            pstmt = conn.prepareStatement("SELECT email, userpassword FROM clientinfo " +
                    "WHERE email=? AND userpassword=?");
            pstmt.setString(1, email);
            pstmt.setString(2, userpassword);
            rs = pstmt.executeQuery();
            status=rs.next();
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


    public static String getUrl() {
        return url;
    }

    public static String getUsername() {
        return username;
    }

    public static String getPassword() {
        return password;
    }
    //
//
//
//
//
//
//
    public static void insertEntry(String productname, float productamount, float productprice) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DriverManager.getConnection(url, username, password);
            pstmt = conn.prepareStatement("INSERT INTO products (productName,productAmount,productPrice) " +
                    "VALUES (?,?,?)");
            pstmt.setString(1, productname);
            pstmt.setFloat(2, productamount);
            pstmt.setFloat(3, productprice);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
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

    public static void deleteEntry(int productID) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DriverManager.getConnection(url, username, password);
            pstmt = conn.prepareStatement("DELETE FROM products WHERE productID=?");
            pstmt.setInt(1, productID);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
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

    public static void findEntry(String columnName, int value) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        if (columnNames.contains(columnName)) {
            try {
                conn = DriverManager.getConnection(url, username, password);
                pstmt = conn.prepareStatement("SELECT * FROM products WHERE " + columnName + "=?");
                pstmt.setInt(1, value);
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    System.out.printf("Product ID: %d, Product name: %s, Product amount: %f, Product Price: %f%n",
                            rs.getInt("productID"), rs.getString("productName"), rs.
                                    getFloat("productAmount"), rs.getFloat("productPrice"));
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
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
        } else {
            System.out.println("Invalid column name");
        }
    }

    public static void findEntry(String columnName, String value) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        if (columnNames.contains(columnName)) {
            try {
                conn = DriverManager.getConnection(url, username, password);
                pstmt = conn.prepareStatement("SELECT * FROM products WHERE " + columnName + "=?");
                pstmt.setString(1, value);
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    System.out.printf("Product ID: %d, Product name: %s, Product amount: %f, Product Price: %f%n",
                            rs.getInt("productID"), rs.getString("productName"), rs.
                                    getFloat("productAmount"), rs.getFloat("productPrice"));
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
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
        } else {
            System.out.println("Invalid column name");
        }
    }

    public static void updateEntry(int productID, String productname, float productamount,
                                   float productprice) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DriverManager.getConnection(url, username, password);
            pstmt = conn.prepareStatement("UPDATE  products SET  productName=?, productAmount=?,productPrice=? WHERE productID=?");
            pstmt.setString(1, productname);
            pstmt.setFloat(2, productamount);
            pstmt.setFloat(3, productprice);
            pstmt.setInt(4, productID);
        } catch (SQLException e) {
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

    public static void productPurchase(int productID, float productamount) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DriverManager.getConnection(url, username, password);
            pstmt = conn.prepareStatement("INSERT INTO productpurchase (productID,productAmount) " +
                    " SELECT ?,? FROM products WHERE productID=?");
            pstmt.setInt(1, productID);
            pstmt.setFloat(2, productamount);
            pstmt.setInt(3, productID);
            pstmt.executeUpdate();
            pstmt = conn.prepareStatement("UPDATE  products SET  productAmount=productAmount+? WHERE productID=?");
            pstmt.setFloat(1, productamount);
            pstmt.setInt(2, productID);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
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

    public static void productSale(int productID, float productamount) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        float productprice = 0;
        int queryID = 0;
        try {
            conn = DriverManager.getConnection(url, username, password);
            pstmt = conn.prepareStatement("SELECT productPrice FROM products WHERE productID=?");
            pstmt.setInt(1, productID);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                productprice = rs.getFloat("productPrice");
            }
            pstmt = conn.prepareStatement("INSERT INTO productsale (productID,productAmount) " +
                    " SELECT ?,? FROM products WHERE productID=? AND productAmount>=?", Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, productID);
            pstmt.setFloat(2, productamount);
            pstmt.setInt(3, productID);
            pstmt.setFloat(4, productamount);
            int querySuccessful = pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                queryID = rs.getInt(1);
            }
            if (querySuccessful > 0) {
                pstmt = conn.prepareStatement("UPDATE productsale SET totalPrice=? WHERE saleID=?");
                pstmt.setFloat(1, productprice * productamount);
                pstmt.setInt(2, queryID);
                pstmt.executeUpdate();
                pstmt = conn.prepareStatement("UPDATE products SET  productAmount=productAmount-? WHERE productID=?");
                pstmt.setFloat(1, productamount);
                pstmt.setInt(2, productID);
                pstmt.executeUpdate();
            } else {
                System.out.println("Not enough inventory!");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
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