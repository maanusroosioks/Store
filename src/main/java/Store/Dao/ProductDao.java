package Store.Dao;

import Store.Model.Client;
import Store.Model.Product;
import Store.Model.ShoppingCartItem;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.*;
import java.util.*;


public class ProductDao {
    private static final String url = "jdbc:mysql://localhost:3306/new_schema";
    private static final String username = "root";
    private static final String password = "maanus";

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static Product findProductEntry(int productID) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Product productData = null;
        try {
            conn = DriverManager.getConnection(url, username, password);
            pstmt = conn.prepareStatement("SELECT productID,  productName ,  productAmount , " +
                    " productPrice,productType FROM products WHERE productID=?");
            pstmt.setInt(1, productID);
            rs = pstmt.executeQuery();
            rs.next();
            productData = new Product();
            productData.setProductID(rs.getInt("productID"));
            productData.setProductName(rs.getString("productName"));
            productData.setProductAmount(rs.getInt("productAmount"));
            productData.setProductPrice(rs.getFloat("productPrice"));
            productData.setProductType(rs.getString("productType"));
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

    public static ArrayList<Product> fetchProductTableData() {
        Connection conn = null;
        Statement stmt = null;
        ArrayList<Product> productsTableDataList;
        ResultSet rs = null;
        Product productData;
        try {
            conn = DriverManager.getConnection(url, username, password);
            String query = "SELECT productID,  productName ,  productAmount ,  productPrice, productType FROM products";
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
                    productData.setProductType(rs.getString("productType"));
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

    public static ArrayList<Product> fetchProductTableData(String productType,
                                                           ArrayList<ArrayList<String>> checkedProductTypeSpecificationsData,
                                                           ArrayList<String> productTableColumnNames) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ArrayList<Product> productsTableDataList;
        ResultSet rs = null;
        Product productData;
        try {
            conn = DriverManager.getConnection(url, username, password);
            StringBuilder query = new StringBuilder();
            query.append("SELECT p.productID,  p.productName,  p.productAmount,  p.productPrice, p.productType ");

            for (String columnName : productTableColumnNames) {
                query.append(", spec.").append(columnName);
            }
            query.append(" FROM products p INNER JOIN ").append(productType).append(" spec ON p.productID=spec.productID");
            if (productType.length() > 0) {
                query.append(" WHERE p.productType=? ");
            }

            if (productTableColumnNames.size() == checkedProductTypeSpecificationsData.size()) {
                for (int i = 0; i < productTableColumnNames.size(); i++) {
                    if (checkedProductTypeSpecificationsData.get(i).size() != 0) {
                        query.append("AND").append(" (spec.").append(productTableColumnNames.get(i));
                    }
                    for (int j = 0; j < checkedProductTypeSpecificationsData.get(i).size(); j++) {
                        if (checkedProductTypeSpecificationsData.get(i).size() != 0 && j == 0) {
                            query.append("=?");
                        }
                        if (checkedProductTypeSpecificationsData.get(i).size() != 0 && j != 0) {
                            query.append(" OR").append(" spec.").append(productTableColumnNames.get(i)).append("=?");
                        }
                    }
                    if (checkedProductTypeSpecificationsData.get(i).size() != 0) {
                        query.append(") ");
                    }
                }
            }
            String builtQuery = query.toString();
            pstmt = conn.prepareStatement(builtQuery);
            if (productType.length() > 0) {
                pstmt.setString(1, productType);
            }
            int pstmtIndex = 2;
            if (productTableColumnNames.size() == checkedProductTypeSpecificationsData.size()) {
                for (int i = 0; i < productTableColumnNames.size(); i++) {
                    for (int j = 0; j < checkedProductTypeSpecificationsData.get(i).size(); j++) {
                        pstmt.setString(pstmtIndex, checkedProductTypeSpecificationsData.get(i).get(j));
                        pstmtIndex++;
                    }
                }
            }
            rs = pstmt.executeQuery();
            productsTableDataList = new ArrayList<Product>();
            if (rs != null) {
                while (rs.next()) {
                    productData = new Product();
                    productData.setProductID(rs.getInt("productID"));
                    productData.setProductName(rs.getString("productName"));
                    productData.setProductAmount(rs.getInt("productAmount"));
                    productData.setProductPrice(rs.getFloat("productPrice"));
                    productData.setProductType(rs.getString("productType"));
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
        return productsTableDataList;
    }

    public static Map<String, ArrayList<String>> fetchProductSpecValuesForFiltering(ArrayList<String> productColumnNames,
                                                                                    String productType) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Map<String, ArrayList<String>> productTypeSpecifcationsData;
        ArrayList<String> temporaryProductValueList;
        try {
            conn = DriverManager.getConnection(url, username, password);
            pstmt = conn.prepareStatement("SELECT * FROM " + productType + " WHERE productType=?");
            pstmt.setString(1, productType);
            rs = pstmt.executeQuery();
            productTypeSpecifcationsData = new LinkedHashMap<>();
            while (rs.next()) {
                for (String productColumnName : productColumnNames) {
                    temporaryProductValueList = new ArrayList<>();
                    temporaryProductValueList.add(rs.getString(productColumnName));
                    if (productTypeSpecifcationsData.get(productColumnName) == null && !temporaryProductValueList.get(0).equals("")) {
                        productTypeSpecifcationsData.put(productColumnName, temporaryProductValueList);
                    } else {
                        if (productTypeSpecifcationsData.get(productColumnName) != null) {
                            if (!productTypeSpecifcationsData.get(productColumnName).contains(rs.getString(productColumnName))
                                    && !temporaryProductValueList.get(0).isEmpty()) {
                                productTypeSpecifcationsData.get(productColumnName).add(rs.getString(productColumnName));
                            }
                        }
                    }
                }
            }
            return productTypeSpecifcationsData;
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

    }

    public static void updateProductEntry(Product productData) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DriverManager.getConnection(url, username, password);
            pstmt = conn.prepareStatement("UPDATE  products SET  productName=?, productAmount=?,productPrice=? WHERE productID=?");
            pstmt.setString(1, productData.getProductName());
            pstmt.setFloat(2, productData.getProductAmount());
            pstmt.setFloat(3, productData.getProductPrice());
            pstmt.setInt(4, productData.getProductID());
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

    public static int insertProductEntry(Product productData) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int queryID = 0;
        try {
            conn = DriverManager.getConnection(url, username, password);
            pstmt = conn.prepareStatement("INSERT INTO products (productName," +
                    " productAmount,productPrice,productType) VALUES (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, productData.getProductName());
            pstmt.setFloat(2, productData.getProductAmount());
            pstmt.setFloat(3, productData.getProductPrice());
            pstmt.setString(4, productData.getProductType());
            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                queryID = rs.getInt(1);
            }
            return queryID;

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
        return queryID;
    }

    public static void insertProductSpecifications(Product productData, ArrayList<String> productTableColumnNames,
                                                   Map<String, Object> productTableSpecValues) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DriverManager.getConnection(url, username, password);
            StringBuilder queryString = new StringBuilder();
            queryString.append("INSERT INTO ").append(productData.getProductType()).append(" (productID,productType");
            for (String value : productTableColumnNames) {
                queryString.append("," + value);
            }
            queryString.append(") VALUES (?,?");
            for (String value : productTableColumnNames) {
                queryString.append(",?");
            }
            queryString.append(")");
            String queryStringComplete = queryString.toString();
            pstmt = conn.prepareStatement(queryStringComplete);
            pstmt.setInt(1, productData.getProductID());
            pstmt.setString(2, productData.getProductType());
            for (int i = 0; i < productTableColumnNames.size(); i++) {
                pstmt.setString(i + 3, (String) productTableSpecValues.get(productTableColumnNames.get(i)));
            }
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

    public void updateProductSpecifications(Product productData, ArrayList<String> productTableColumnNames,
                                            Map<String, Object> productTableSpecValues) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DriverManager.getConnection(url, username, password);
            StringBuilder queryString = new StringBuilder();
            queryString.append("UPDATE " + productData.getProductType() + " SET productType=?");
            for (String value : productTableColumnNames) {
                queryString.append(", " + value + "=?");
            }
            queryString.append(" WHERE productID=?");
            String queryStringComplete = queryString.toString();
            pstmt = conn.prepareStatement(queryStringComplete);
            pstmt.setString(1, productData.getProductType());
            for (int i = 0; i < productTableColumnNames.size(); i++) {
                pstmt.setString(i + 2, (String) productTableSpecValues.get(productTableColumnNames.get(i)));
            }
            pstmt.setInt(productTableColumnNames.size() + 2, productData.getProductID());
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

    public static void deleteProductEntry(Product productData) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DriverManager.getConnection(url, username, password);
            pstmt = conn.prepareStatement("DELETE FROM " + productData.getProductType() + " WHERE productID=?");
            pstmt.setInt(1, productData.getProductID());
            pstmt.executeUpdate();
            pstmt = conn.prepareStatement("DELETE FROM products WHERE productID=?");
            pstmt.setInt(1, productData.getProductID());
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

    public static ArrayList<String> fetchProductTypes() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<String> productTypeList = new ArrayList<>();
        try {
            conn = DriverManager.getConnection(url, username, password);
            pstmt = conn.prepareStatement("SELECT productType FROM producttypes");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                String productType = rs.getString("productType");
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

    public static void insertIntoProductTypeTable(Product productData) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DriverManager.getConnection(url, username, password);
            pstmt = conn.prepareStatement("INSERT  INTO producttypes (productType) VALUES(?)");
            pstmt.setString(1, productData.getProductType());
            pstmt.executeUpdate();
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

    public static ArrayList<String> fetchProductTableColumns(String tableName) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<String> productTableColumnNames = new ArrayList<>();
        try {
            conn = DriverManager.getConnection(url, username, password);
            pstmt = conn.prepareStatement("SELECT * FROM " + tableName);

            rs = pstmt.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            for (int i = 3; i <= columnCount; i++) {
                String name = rsmd.getColumnName(i);
                productTableColumnNames.add(name);
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

    public static ArrayList<String> fetchProductSpecifications(Product productData, ArrayList<String> productColumnNames) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<String> productSpecifcationsData = new ArrayList<>();
        try {
            conn = DriverManager.getConnection(url, username, password);
            pstmt = conn.prepareStatement("SELECT * FROM " + productData.getProductType() + " WHERE productID=?");
            pstmt.setInt(1, productData.getProductID());
            rs = pstmt.executeQuery();
            rs.next();
            for (String name : productColumnNames) {
                productSpecifcationsData.add(rs.getString(name));
            }
            return productSpecifcationsData;
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

    public static void createNewProductTable(List<String> productTableColumnNames, Product productData) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DriverManager.getConnection(url, username, password);
            StringBuilder tableString = new StringBuilder();
            tableString.append("CREATE TABLE ").append(productData.getProductType()).append(" (productID INT NOT NULL, ").
                    append("productType VARCHAR(50) NOT NULL, ");
            for (String columnName : productTableColumnNames) {
                if (columnName != null && !columnName.isEmpty()) {
                    tableString.append(columnName).append(" VARCHAR(50) NULL, ");
                }
            }
            tableString.append("CONSTRAINT ").append(productData.getProductType()).
                    append("_productID_uindex UNIQUE (productID), ").append("CONSTRAINT ").
                    append(productData.getProductType()).append("_products_productID_fk ").
                    append("FOREIGN KEY (productID) REFERENCES products (productID))");
            String queryString = tableString.toString();
            pstmt = conn.prepareStatement(queryString);
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


    public static String getUrl() {
        return url;
    }

    public static String getUsername() {
        return username;
    }

    public static String getPassword() {
        return password;
    }
}
//
//
//
//
//
//
//
//    public static void insertEntry(String productname, float productamount, float productprice) {
//        Connection conn = null;
//        PreparedStatement pstmt = null;
//        try {
//            conn = DriverManager.getConnection(url, username, password);
//            pstmt = conn.prepareStatement("INSERT INTO products (productName,productAmount,productPrice) " +
//                    "VALUES (?,?,?)");
//            pstmt.setString(1, productname);
//            pstmt.setFloat(2, productamount);
//            pstmt.setFloat(3, productprice);
//            pstmt.executeUpdate();
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        } finally {
//            try {
//                pstmt.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//            try {
//                conn.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public static void deleteEntry(int productID) {
//        Connection conn = null;
//        PreparedStatement pstmt = null;
//        try {
//            conn = DriverManager.getConnection(url, username, password);
//            pstmt = conn.prepareStatement("DELETE FROM products WHERE productID=?");
//            pstmt.setInt(1, productID);
//            pstmt.executeUpdate();
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        } finally {
//            try {
//                pstmt.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//            try {
//                conn.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public static void findEntry(String columnName, int value) {
//        Connection conn = null;
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//        if (columnNames.contains(columnName)) {
//            try {
//                conn = DriverManager.getConnection(url, username, password);
//                pstmt = conn.prepareStatement("SELECT * FROM products WHERE " + columnName + "=?");
//                pstmt.setInt(1, value);
//                rs = pstmt.executeQuery();
//                while (rs.next()) {
//                    System.out.printf("Product ID: %d, Product name: %s, Product amount: %f, Product Price: %f%n",
//                            rs.getInt("productID"), rs.getString("productName"), rs.
//                                    getFloat("productAmount"), rs.getFloat("productPrice"));
//                }
//            } catch (SQLException ex) {
//                ex.printStackTrace();
//            } finally {
//                try {
//                    rs.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//                try {
//                    pstmt.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//                try {
//                    conn.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//        } else {
//            System.out.println("Invalid column name");
//        }
//    }
//
//    public static void findEntry(String columnName, String value) {
//        Connection conn = null;
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//        if (columnNames.contains(columnName)) {
//            try {
//                conn = DriverManager.getConnection(url, username, password);
//                pstmt = conn.prepareStatement("SELECT * FROM products WHERE " + columnName + "=?");
//                pstmt.setString(1, value);
//                rs = pstmt.executeQuery();
//                while (rs.next()) {
//                    System.out.printf("Product ID: %d, Product name: %s, Product amount: %f, Product Price: %f%n",
//                            rs.getInt("productID"), rs.getString("productName"), rs.
//                                    getFloat("productAmount"), rs.getFloat("productPrice"));
//                }
//            } catch (SQLException ex) {
//                ex.printStackTrace();
//            } finally {
//                try {
//                    rs.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//                try {
//                    pstmt.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//                try {
//                    conn.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//        } else {
//            System.out.println("Invalid column name");
//        }
//    }
//
//    public static void updateEntry(int productID, String productname, float productamount,
//                                   float productprice) {
//        Connection conn = null;
//        PreparedStatement pstmt = null;
//        try {
//            conn = DriverManager.getConnection(url, username, password);
//            pstmt = conn.prepareStatement("UPDATE  products SET  productName=?, productAmount=?,productPrice=? WHERE productID=?");
//            pstmt.setString(1, productname);
//            pstmt.setFloat(2, productamount);
//            pstmt.setFloat(3, productprice);
//            pstmt.setInt(4, productID);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                pstmt.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//            try {
//                conn.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//
//    }
//
//    public static void productPurchase(int productID, float productamount) {
//        Connection conn = null;
//        PreparedStatement pstmt = null;
//        try {
//            conn = DriverManager.getConnection(url, username, password);
//            pstmt = conn.prepareStatement("INSERT INTO productpurchase (productID,productAmount) " +
//                    " SELECT ?,? FROM products WHERE productID=?");
//            pstmt.setInt(1, productID);
//            pstmt.setFloat(2, productamount);
//            pstmt.setInt(3, productID);
//            pstmt.executeUpdate();
//            pstmt = conn.prepareStatement("UPDATE  products SET  productAmount=productAmount+? WHERE productID=?");
//            pstmt.setFloat(1, productamount);
//            pstmt.setInt(2, productID);
//            pstmt.executeUpdate();
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        } finally {
//            try {
//
//                pstmt.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//            try {
//                conn.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public static void productSale(int productID, float productamount) {
//        Connection conn = null;
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//        float productprice = 0;
//        int queryID = 0;
//        try {
//            conn = DriverManager.getConnection(url, username, password);
//            pstmt = conn.prepareStatement("SELECT productPrice FROM products WHERE productID=?");
//            pstmt.setInt(1, productID);
//            rs = pstmt.executeQuery();
//            if (rs.next()) {
//                productprice = rs.getFloat("productPrice");
//            }
//            pstmt = conn.prepareStatement("INSERT INTO productsale (productID,productAmount) " +
//                    " SELECT ?,? FROM products WHERE productID=? AND productAmount>=?", Statement.RETURN_GENERATED_KEYS);
//            pstmt.setInt(1, productID);
//            pstmt.setFloat(2, productamount);
//            pstmt.setInt(3, productID);
//            pstmt.setFloat(4, productamount);
//            int querySuccessful = pstmt.executeUpdate();
//            rs = pstmt.getGeneratedKeys();
//            if (rs.next()) {
//                queryID = rs.getInt(1);
//            }
//            if (querySuccessful > 0) {
//                pstmt = conn.prepareStatement("UPDATE productsale SET totalPrice=? WHERE saleID=?");
//                pstmt.setFloat(1, productprice * productamount);
//                pstmt.setInt(2, queryID);
//                pstmt.executeUpdate();
//                pstmt = conn.prepareStatement("UPDATE products SET  productAmount=productAmount-? WHERE productID=?");
//                pstmt.setFloat(1, productamount);
//                pstmt.setInt(2, productID);
//                pstmt.executeUpdate();
//            } else {
//                System.out.println("Not enough inventory!");
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        } finally {
//            try {
//                rs.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//            try {
//                pstmt.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//            try {
//                conn.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}