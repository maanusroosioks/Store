package Store;

public class Product {
    int productID, productAmount;
    String productName;
    float productPrice;

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public void setProductPrice(float productPrice) {
        this.productPrice = productPrice;
    }

    public void setProductAmount(int productAmount) {
        this.productAmount = productAmount;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductID() {
        return productID;
    }

    public String getProductName() {
        return productName;
    }

    public float getProductAmount() {
        return productAmount;
    }

    public float getProductPrice() {
        return productPrice;
    }
}
