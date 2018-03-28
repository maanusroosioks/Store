

public class FoodProduct extends Product {
    private String expirationDate;

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public FoodProduct(String productName, int productAmount, int productPrice, String expirationDate) {
        super(productName, productAmount, productPrice);
        setExpirationDate(expirationDate);
    }
}
