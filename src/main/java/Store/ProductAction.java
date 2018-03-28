package Store;

import com.opensymphony.xwork2.ActionSupport;
import java.util.List;

public class ProductAction extends ActionSupport {
    List<Product> productsTableDataList = null;
    DatabaseConnection productObject = null;
    Product productData = null;
    int productID, productAmount;
    String productName;
    float productPrice;

    public String productTable() {
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
            new DatabaseConnection().updateEntry(productData);
            productsTableDataList = new DatabaseConnection().fetchProductTableData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    public String newProductPage(){
        return SUCCESS;
    }

    public String insertNewProduct(){
        try {
            productData = new Product();
            productData.setProductID(productID);
            productData.setProductName(productName);
            productData.setProductAmount(productAmount);
            productData.setProductPrice(productPrice);
            new DatabaseConnection().insertEntry(productData);
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
