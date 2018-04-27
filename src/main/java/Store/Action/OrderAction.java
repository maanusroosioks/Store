package Store.Action;

import Store.Dao.OrderDao;
import Store.Dao.ShoppingCartDao;
import Store.Model.Order;
import Store.Model.ShoppingCartItem;

import java.util.ArrayList;

public class OrderAction extends ProductAction {
    private ArrayList<Order> orderList=new ArrayList<>();
    private ArrayList<ArrayList<Order>> orderListByBillID=new ArrayList<>();
    private ArrayList<ShoppingCartItem> shoppingCartDataList=new ArrayList<>();



    public String ordersPage() {
        try {
            orderListByBillID= OrderDao.fetchOrders();
        } catch (Exception e) {

            e.printStackTrace();
        }
        return SUCCESS;
    }

    public String generateOrder(){
        try {
            shoppingCartDataList = ShoppingCartDao.fetchShoppingCartData();
            OrderDao.generateOrder(shoppingCartDataList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    public ArrayList<ArrayList<Order>> getOrderListByBillID() {
        return orderListByBillID;
    }

    public void setOrderListByBillID(ArrayList<ArrayList<Order>> orderListByBillID) {
        this.orderListByBillID = orderListByBillID;
    }

    public ArrayList<ShoppingCartItem> getShoppingCartDataList() {
        return shoppingCartDataList;
    }

    public void setShoppingCartDataList(ArrayList<ShoppingCartItem> shoppingCartDataList) {
        this.shoppingCartDataList = shoppingCartDataList;
    }

    public ArrayList<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(ArrayList<Order> orderList) {
        this.orderList = orderList;
    }
}
