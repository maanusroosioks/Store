package Store;

import com.opensymphony.xwork2.ActionSupport;

import java.util.ArrayList;
import java.util.List;

public class ProductAction extends ActionSupport {
    private List<Product> productsTableDataList = null;
    private List<ShoppingCartItem> shoppingCartDataList = null;
    private DatabaseConnection productObject = null;
    private Product productData = null;
    private ShoppingCartItem cartItem= null;
    private int productID, productAmount, shoppingCartID;
    private String productName,productType;
    private float productPrice;
    private List<String> productTypeList=new ArrayList<>();
    private List<String> productTableColumnNames=new ArrayList<>();

    public String productTable() {
        try {
            System.out.println(productType);
            productsTableDataList = new DatabaseConnection().fetchProductTableData();
            productTableColumnNames=new DatabaseConnection().fetchProductTableColumns(productType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    public String productShoppingTable() {
        try {
            productsTableDataList = new DatabaseConnection().fetchProductTableData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    public String fetchProductEditingValues() {
        try {
            productData = new DatabaseConnection().findProductEntry(productID);
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
            new DatabaseConnection().updateProductEntry(productData);
            productsTableDataList = new DatabaseConnection().fetchProductTableData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    public String newProductPage(){
        productTypeList=new DatabaseConnection().fetchProductTypes();

        return SUCCESS;
    }
    public String displayNewProductSpecifications(){
        return SUCCESS;
    }
    public String insertNewProduct(){
        try {
            productData = new Product();
            productData.setProductID(productID);
            productData.setProductName(productName);
            productData.setProductAmount(productAmount);
            productData.setProductPrice(productPrice);
            new DatabaseConnection().insertProductEntry(productData);
            productsTableDataList = new DatabaseConnection().fetchProductTableData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    public String deleteProduct(){
        try {
            productData = new Product();
            productData.setProductID(productID);
            productData.setProductName(productName);
            productData.setProductAmount(productAmount);
            productData.setProductPrice(productPrice);
            new DatabaseConnection().deleteProductEntry(productData);
            productsTableDataList = new DatabaseConnection().fetchProductTableData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    public String addToShoppingCart(){
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
    public String deleteFromShoppingCart(){
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

}
