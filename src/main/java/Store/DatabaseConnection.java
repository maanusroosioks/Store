package Store;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DatabaseConnection {
    private static final String url = "jdbc:mysql://localhost:3306/new_schema";
    private static final String username = "root";
    private static final String password = "maanus";
    private static List<String> columnNames = new ArrayList<>();

    public static String getUrl() {
        return url;
    }

    public static String getUsername() {
        return username;
    }

    public static String getPassword() {
        return password;
    }

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

    public void updateEntry(Product productData) {
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

    public void insertEntry(Product productData) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, password);
            pstmt = conn.prepareStatement("INSERT INTO products (productName, productAmount,productPrice) VALUES (?,?,?)");
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