import Store.DatabaseConnection;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
//        System.out.println("asd");
//        Product kaup1=new Product("asd",23,5);
//        Product kaup2=new Product();
//        kaup1.addProductAmount(10);
//        System.out.println(kaup1.getProductAmount());
//        FoodProduct kaup3=new FoodProduct("abc",3,4,"23-2-2018");
        DatabaseConnection.productSale(3, 20);

    }


}


