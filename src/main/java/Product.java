import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Product {

    private String productName;
    private int productAmount;
    private int productPrice;

    public int getProductAmount() {
        return productAmount;
    }

    public String getProductName() {
        return productName;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductAmount(int productAmount) {
        this.productAmount = productAmount;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public void addProductAmount(int amount) {
        setProductAmount(getProductAmount() + amount);
    }

    public void addProductPrice(int amount) {
        setProductPrice(getProductPrice() + amount);
    }

    public Product(String productName, int productAmount, int productPrice) {
        setProductName(productName);
        setProductAmount(productAmount);
        setProductPrice(productPrice);
    }

    public Product() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter product name");
        String s = br.readLine();
        setProductName(s);
        System.out.println("Enter product amount");
        try {
            int i = Integer.parseInt(br.readLine());
            setProductAmount(i);
        } catch (NumberFormatException nfe) {
            System.err.println("Invalid Format!");
        }
        System.out.println("Enter product price");
        try {
            int j = Integer.parseInt(br.readLine());
            setProductPrice(j);
        } catch (NumberFormatException nfe) {
            System.err.println("Invalid Format!");
        }
    }
}
