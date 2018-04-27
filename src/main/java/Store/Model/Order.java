package Store.Model;

import java.util.Date;

public class Order {
    private int billID;
    private int productID;
    private String email;
    private Date timeStamp;
    private String productName;
    private float productPrice;

    public float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(float productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductName() {

        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getTimeStamp() {

        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getProductID() {

        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getBillID() {

        return billID;
    }

    public void setBillID(int billID) {
        this.billID = billID;
    }
}
