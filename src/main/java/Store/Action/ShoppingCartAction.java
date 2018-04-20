package Store.Action;

import Store.Dao.ProductDao;
import Store.Dao.ShoppingCartDao;
import Store.Model.Product;
import Store.Model.ShoppingCartItem;

import java.util.List;

public class ShoppingCartAction extends ProductAction {
    private List<ShoppingCartItem> shoppingCartDataList;
    private ShoppingCartItem cartItem;
    private int shoppingCartID;

    public ShoppingCartItem getCartItem() {

        return cartItem;
    }

    public void setCartItem(ShoppingCartItem cartItem) {
        this.cartItem = cartItem;
    }

    public String addToShoppingCart() {
        try {
            ShoppingCartDao.addToShoppingCart(getProductData());
            productsTableDataList = ProductDao.fetchProductTableData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;

    }

    public String shoppingCartTable() {
        try {
            shoppingCartDataList = ShoppingCartDao.fetchShoppingCartData();
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
//            LOG.error("asdasd);
//            e.printStackTrace();
        }
        return SUCCESS;
    }

    public String deleteFromShoppingCart() {
        try {
            ShoppingCartDao.deleteFromShoppingCart(cartItem);
            shoppingCartDataList = ShoppingCartDao.fetchShoppingCartData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    public void setShoppingCartDataList(List<ShoppingCartItem> shoppingCartDataList) {
        this.shoppingCartDataList = shoppingCartDataList;
    }

    public List<ShoppingCartItem> getShoppingCartDataList() {
        return shoppingCartDataList;
    }

    public void setShoppingCartID(int shoppingCartID) {
        this.shoppingCartID = shoppingCartID;
    }

    public int getShoppingCartID() {
        return shoppingCartID;
    }

}
