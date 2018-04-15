package Store;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


import java.util.*;

public class ProductAction extends ActionSupport {
    public static final Logger LOG = LogManager.getLogger(ProductAction.class);
    private List<Product> productsTableDataList;
    private List<ShoppingCartItem> shoppingCartDataList;
    private DatabaseConnection productObject;
    private Product productData;
    private ShoppingCartItem cartItem;
    private int productID, productAmount, shoppingCartID;
    private String productName, productType, value1, value2, value3, value4, value5, value6, value7, value8;
    private float productPrice;
    private List<String> productTypeList = new ArrayList<>();
    private List<String> productTableColumnNames = new ArrayList<>();
    private Map<String, Object> productTableSpecValues;
    private String[] productSpecValueList;
    private List<String> productSpecificationsData = new ArrayList<>();
    private Map<String, ArrayList<String>> productTypeSpecificationsData = new HashMap<>();


    public String productTable() {
        try {
            productsTableDataList = DatabaseConnection.fetchProductTableData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    public String productShoppingTable() {
        try {
            productTypeList = DatabaseConnection.fetchProductTypes();
            if (productData == null || productData.getProductType().isEmpty()) {
                productsTableDataList = DatabaseConnection.fetchProductTableData();
            } else {
                productsTableDataList = DatabaseConnection.fetchProductTableData(productData.getProductType());
                productTableColumnNames = DatabaseConnection.fetchProductTableColumns(productData.getProductType());
                productTableColumnNames.remove(1);
                productTableColumnNames.remove(0);
                productTypeSpecificationsData = DatabaseConnection.fetchProductSpecValuesForFiltering
                        (productTableColumnNames, productData.getProductType());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }


    public String fetchProductEditingValues() {
        try {
            productData = DatabaseConnection.findProductEntry(productID);
            productTableColumnNames = new DatabaseConnection().fetchProductTableColumns(productData.getProductType());
            productTableColumnNames.remove(1);
            productTableColumnNames.remove(0);
            productSpecificationsData = new DatabaseConnection().fetchProductSpecifications(productData, productTableColumnNames);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    public String updateProductValues() {
        try {
            DatabaseConnection.updateProductEntry(productData);
            productTableColumnNames = DatabaseConnection.fetchProductTableColumns(productData.getProductType());
            productTableColumnNames.remove(1);
            productTableColumnNames.remove(0);
            productTableSpecValues = new HashMap<>();
            productSpecValueList = new String[]{value1, value2, value3, value4, value5, value6, value7, value8};
            for (int i = 0; i < productTableColumnNames.size(); i++) {
                productTableSpecValues.put(productTableColumnNames.get(i), productSpecValueList[i]);
            }
            new DatabaseConnection().updateProductSpecifications(productData, productTableColumnNames, productTableSpecValues);
            productsTableDataList = DatabaseConnection.fetchProductTableData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    public String newProductPage() {
        productTypeList = DatabaseConnection.fetchProductTypes();
        return SUCCESS;
    }

    public String displayNewProductSpecifications() {
        try {
            productTypeList = DatabaseConnection.fetchProductTypes();
            productTableColumnNames = DatabaseConnection.fetchProductTableColumns(productData.getProductType());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    public String productSpecificationsPage() {
        try {
            productData = DatabaseConnection.findProductEntry(productData.getProductID());
            productTableColumnNames = DatabaseConnection.fetchProductTableColumns(productData.getProductType());
            productTableColumnNames.remove(1);
            productTableColumnNames.remove(0);
            productSpecificationsData = DatabaseConnection.fetchProductSpecifications(productData, productTableColumnNames);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    public String insertNewProduct() {
        try {
            productTableColumnNames = DatabaseConnection.fetchProductTableColumns(productData.getProductType());
            productTableColumnNames.remove(1);
            productTableColumnNames.remove(0);
            productTableSpecValues = new HashMap<>();
            productSpecValueList = new String[]{value1, value2, value3, value4, value5, value6, value7, value8};
            for (int i = 0; i < productTableColumnNames.size(); i++) {
                productTableSpecValues.put(productTableColumnNames.get(i), productSpecValueList[i]);
            }
            int queryID = DatabaseConnection.insertProductEntry(productData);
            productData.setProductID(queryID);
            DatabaseConnection.insertProductSpecifications(productData, productTableColumnNames, productTableSpecValues);
            productsTableDataList = DatabaseConnection.fetchProductTableData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    public String productTypeCreationPage() {
        return SUCCESS;
    }

    public String createNewProductTable() {
        try {
//            Iterator<String> iter = productTableColumnNames.iterator();
//            while (iter.hasNext()) {
//                String clg = iter.next();
//                if (clg.length() == 0) {
//                    iter.remove();
//                }
//            }
            DatabaseConnection.createNewProductTable(productTableColumnNames, productData);
            DatabaseConnection.insertIntoProductTypeTable(productData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    public String deleteProduct() {
        try {
            DatabaseConnection.deleteProductEntry(productData);
            productsTableDataList = DatabaseConnection.fetchProductTableData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    public String addToShoppingCart() {
        try {
            DatabaseConnection.addToShoppingTable(productData);
            productsTableDataList = DatabaseConnection.fetchProductTableData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    public String shoppingCartTable() {
        try {
            shoppingCartDataList = DatabaseConnection.fetchShoppingCartData();
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
//            LOG.error("asdasd);
//            e.printStackTrace();
        }
        return SUCCESS;
    }

    public String deleteFromShoppingCart() {
        try {
            DatabaseConnection.deleteFromShoppingCart(cartItem);
            shoppingCartDataList = DatabaseConnection.fetchShoppingCartData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }


    public Map<String, ArrayList<String>> getProductTypeSpecificationsData() {
        return productTypeSpecificationsData;
    }

    public void setProductTypeSpecificationsData(Map<String, ArrayList<String>> productTypeSpecificationsData) {
        this.productTypeSpecificationsData = productTypeSpecificationsData;
    }

    public void setProductSpecValueList(String[] productSpecValueList) {
        this.productSpecValueList = productSpecValueList;
    }

    public String[] getProductSpecValueList() {
        return productSpecValueList;
    }

    public List<String> getProductSpecificationsData() {
        return productSpecificationsData;
    }

    public void setProductSpecificationsData(List<String> productSpecificationsData) {
        this.productSpecificationsData = productSpecificationsData;
    }

    public Map<String, Object> getProductTableSpecValues() {
        return productTableSpecValues;
    }

    public void setProductTableSpecValues(Map<String, Object> productTableSpecValues) {
        this.productTableSpecValues = productTableSpecValues;
    }

    public List<String> getProductTableColumnNames() {
        return productTableColumnNames;
    }

    public void setProductTableColumnNames(List<String> productTableColumnNames) {
        this.productTableColumnNames = productTableColumnNames;
    }


    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductTypeList(List<String> productTypeList) {
        this.productTypeList = productTypeList;
    }

    public List<String> getProductTypeList() {
        return productTypeList;
    }

    public void setShoppingCartID(int shoppingCartID) {
        this.shoppingCartID = shoppingCartID;
    }

    public int getShoppingCartID() {
        return shoppingCartID;
    }

    public void setShoppingCartDataList(List<ShoppingCartItem> shoppingCartDataList) {
        this.shoppingCartDataList = shoppingCartDataList;
    }

    public List<ShoppingCartItem> getShoppingCartDataList() {
        return shoppingCartDataList;
    }

    public void setCartItem(ShoppingCartItem cartItem) {
        this.cartItem = cartItem;
    }

    public ShoppingCartItem getCartItem() {
        return cartItem;
    }

    public void setProductsTableDataList(List<Product> productsTableDataList) {
        this.productsTableDataList = productsTableDataList;
    }

    public void setProductObject(DatabaseConnection productObject) {
        this.productObject = productObject;
    }

    public DatabaseConnection getProductObject() {
        return productObject;
    }

    public void setProductAmount(int productAmount) {
        this.productAmount = productAmount;
    }

    public float getProductPrice() {
        return productPrice;
    }

    public void setProductData(Product productData) {
        this.productData = productData;
    }

    public int getProductAmount() {
        return productAmount;
    }

    public int getProductID() {
        return productID;
    }

    public List<Product> getProductsTableDataList() {
        return productsTableDataList;
    }

    public Product getProductData() {
        return productData;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductPrice(float productPrice) {
        this.productPrice = productPrice;
    }

    public String getValue1() {
        return value1;
    }

    public String getValue2() {
        return value2;
    }

    public String getValue3() {
        return value3;
    }

    public String getValue4() {
        return value4;
    }

    public String getValue5() {
        return value5;
    }

    public String getValue6() {
        return value6;
    }

    public String getValue7() {
        return value7;
    }

    public String getValue8() {
        return value8;
    }

    public void setValue1(String value1) {
        this.value1 = value1;
    }

    public void setValue2(String value2) {
        this.value2 = value2;
    }

    public void setValue3(String value3) {
        this.value3 = value3;
    }

    public void setValue4(String value4) {
        this.value4 = value4;
    }

    public void setValue5(String value5) {
        this.value5 = value5;
    }

    public void setValue6(String value6) {
        this.value6 = value6;
    }

    public void setValue7(String value7) {
        this.value7 = value7;
    }

    public void setValue8(String value8) {
        this.value8 = value8;
    }
}
