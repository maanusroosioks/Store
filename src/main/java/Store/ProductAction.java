package Store;

import com.opensymphony.xwork2.ActionSupport;


import java.util.*;

public class ProductAction extends ActionSupport {
    private List<Product> productsTableDataList = null;
    private List<ShoppingCartItem> shoppingCartDataList = null;
    private DatabaseConnection productObject = null;
    private Product productData = null;
    private ShoppingCartItem cartItem = null;
    private int productID, productAmount, shoppingCartID;
    private String productName, productType, value1, value2, value3, value4, value5, value6, value7, value8 = null;
    private float productPrice;
    private List<String> productTypeList = new ArrayList<>();
    private List<String> productTableColumnNames = new ArrayList<>();
    private Map<String, Object> productTableSpecValues;
    private String[] productSpecValueList;
    private List<String> productSpecificationsData = new ArrayList<>();
    private Map<String,ArrayList<String>> productTypeSpecificationsData= new HashMap<>();;

    public String productTable() {
        try {
            productsTableDataList = new DatabaseConnection().fetchProductTableData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    public String productShoppingTable() {
        try {
            productTypeList = new DatabaseConnection().fetchProductTypes();
            if(productType==null){
                productsTableDataList = new DatabaseConnection().fetchProductTableData();
            }
            else{
                System.out.println(productTypeSpecificationsData.get("b"));
                System.out.println("___");
                productsTableDataList = new DatabaseConnection().fetchProductTableData(productType);
                productTableColumnNames = new DatabaseConnection().fetchProductTableColumns(productType);
                productTableColumnNames.remove(1);
                productTableColumnNames.remove(0);
                productTypeSpecificationsData=new DatabaseConnection().fetchProductSpecValuesForFiltering(productTableColumnNames,productType);
                System.out.println(productTypeSpecificationsData.get("b"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }



    public String fetchProductEditingValues() {
        try {
            productData = new DatabaseConnection().findProductEntry(productID);
            productTableColumnNames = new DatabaseConnection().fetchProductTableColumns(productData.productType);
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
            productData = new Product();
            productData.setProductID(productID);
            productData.setProductName(productName);
            productData.setProductAmount(productAmount);
            productData.setProductPrice(productPrice);
            productData.setProductType(productType);
            new DatabaseConnection().updateProductEntry(productData);
            productTableColumnNames = new DatabaseConnection().fetchProductTableColumns(productType);
            productTableColumnNames.remove(1);
            productTableColumnNames.remove(0);
            productTableSpecValues = new HashMap<>();
            productSpecValueList = new String[] {value1,value2,value3,value4,value5,value6,value7,value8};
            for (int i = 0; i < productTableColumnNames.size(); i++) {
                productTableSpecValues.put(productTableColumnNames.get(i), productSpecValueList[i]);
            }
            new DatabaseConnection().updateProductSpecifications(productData, productTableColumnNames, productTableSpecValues);
            productsTableDataList = new DatabaseConnection().fetchProductTableData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    public String newProductPage() {
        productTypeList = new DatabaseConnection().fetchProductTypes();
        return SUCCESS;
    }

    public String displayNewProductSpecifications() {
        try {
            productTypeList = new DatabaseConnection().fetchProductTypes();
            productTableColumnNames = new DatabaseConnection().fetchProductTableColumns(productType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    public String productSpecificationsPage() {
        try {
            productData = new Product();
            productData = new DatabaseConnection().findProductEntry(productID);
            productTableColumnNames = new DatabaseConnection().fetchProductTableColumns(productData.productType);
            productTableColumnNames.remove(1);
            productTableColumnNames.remove(0);
            productSpecificationsData = new DatabaseConnection().fetchProductSpecifications(productData, productTableColumnNames);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    public String insertNewProduct() {
        try {
            productTableColumnNames = new DatabaseConnection().fetchProductTableColumns(productType);
            productTableColumnNames.remove(1);
            productTableColumnNames.remove(0);
            productTableSpecValues = new HashMap<>();
            productSpecValueList = new String[] {value1,value2,value3,value4,value5,value6,value7,value8};
            for (int i = 0; i < productTableColumnNames.size(); i++) {
                productTableSpecValues.put(productTableColumnNames.get(i), productSpecValueList[i]);
            }
            productData = new Product();
            productData.setProductID(productID);
            productData.setProductType(productType);
            productData.setProductName(productName);
            productData.setProductAmount(productAmount);
            productData.setProductPrice(productPrice);
            int queryID = new DatabaseConnection().insertProductEntry(productData);
            productData.setProductID(queryID);
            new DatabaseConnection().insertProductSpecifications(productData, productTableColumnNames, productTableSpecValues);
            productsTableDataList = new DatabaseConnection().fetchProductTableData();
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
            productTableColumnNames = new ArrayList<>();
            productTableColumnNames.add(value1);
            productTableColumnNames.add(value2);
            productTableColumnNames.add(value3);
            productTableColumnNames.add(value4);
            productTableColumnNames.add(value5);
            productTableColumnNames.add(value6);
            productTableColumnNames.add(value7);
            productTableColumnNames.add(value8);
            Iterator<String> iter = productTableColumnNames.iterator();
            while (iter.hasNext()) {
                String clg = iter.next();
                if (clg.length() == 0) {
                    iter.remove();
                }
            }
            productData = new Product();
            productData.setProductType(productType);
            new DatabaseConnection().createNewProductTable(productTableColumnNames, productData);
            new DatabaseConnection().insertIntoProductTypeTable(productData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    public String deleteProduct() {
        try {
            productData = new Product();
            productData.setProductID(productID);
            productData.setProductName(productName);
            productData.setProductAmount(productAmount);
            productData.setProductPrice(productPrice);
            productData.setProductType(productType);
            new DatabaseConnection().deleteProductEntry(productData);
            productsTableDataList = new DatabaseConnection().fetchProductTableData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    public String addToShoppingCart() {
        try {
            productData = new Product();
            productData.setProductID(productID);
            productData.setProductName(productName);
            productData.setProductPrice(productPrice);
            new DatabaseConnection().addToShoppingTable(productData);
            productsTableDataList = new DatabaseConnection().fetchProductTableData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    public String shoppingCartTable() {
        try {
            shoppingCartDataList = new DatabaseConnection().fetchShoppingCartData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    public String deleteFromShoppingCart() {
        try {
            cartItem = new ShoppingCartItem();
            cartItem.setProductID(productID);
            cartItem.setProductName(productName);
            cartItem.setProductAmount(productAmount);
            cartItem.setProductPrice(productPrice);
            cartItem.setShoppingCartID(shoppingCartID);
            new DatabaseConnection().deleteFromShoppingCart(cartItem);
            shoppingCartDataList = new DatabaseConnection().fetchShoppingCartData();
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
