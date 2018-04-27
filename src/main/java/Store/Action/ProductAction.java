package Store.Action;

import Store.Dao.ProductDao;
import Store.Dao.ShoppingCartDao;
import Store.Model.Product;
import Store.Model.ShoppingCartItem;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


import java.util.*;

public class ProductAction extends ActionSupport {
    public static final Logger LOG = LogManager.getLogger(ProductAction.class);
    ArrayList<Product> productsTableDataList;
    private ProductDao productObject;
    private Product productData;
    private int productID, productAmount;
    private String productName, productType;
    private float productPrice;
    private ArrayList<String> productTypeList = new ArrayList<>();
    private ArrayList<String> productTableColumnNames = new ArrayList<>();
    private ArrayList<String> productSpecValueList = new ArrayList<>();
    private Map<String, Object> productTableSpecValues;
    private ArrayList<String> productSpecificationsData = new ArrayList<>();
    private Map<String, ArrayList<String>> productTypeSpecificationsData = new LinkedHashMap<>();
    private ArrayList<ArrayList<String>> checkedProductTypeSpecificationsData = new ArrayList<>();

    public String productTable() {
        try {
            productTypeList = ProductDao.fetchProductTypes();
            if (productData == null || productData.getProductType().isEmpty()) {
                productsTableDataList = ProductDao.fetchProductTableData();
            } else {
                productTableColumnNames = ProductDao.fetchProductTableColumns(productData.getProductType());
                productTypeSpecificationsData = ProductDao.fetchProductSpecValuesForFiltering
                        (productTableColumnNames, productData.getProductType());
                productsTableDataList = ProductDao.fetchProductTableData(productData.getProductType(),
                        checkedProductTypeSpecificationsData,productTableColumnNames);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    public String productShoppingTable() {
        try {
            productTypeList = ProductDao.fetchProductTypes();
            if (productData == null || productData.getProductType().isEmpty()) {
                productsTableDataList = ProductDao.fetchProductTableData();
            } else {
                productTableColumnNames = ProductDao.fetchProductTableColumns(productData.getProductType());
                productTypeSpecificationsData = ProductDao.fetchProductSpecValuesForFiltering
                        (productTableColumnNames, productData.getProductType());
                productsTableDataList = ProductDao.fetchProductTableData(productData.getProductType(),
                        checkedProductTypeSpecificationsData,productTableColumnNames);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }


    public String fetchProductEditingValues() {
        try {
            productData = ProductDao.findProductEntry(productID);
            productTableColumnNames = ProductDao.fetchProductTableColumns(productData.getProductType());
            productSpecificationsData = ProductDao.fetchProductSpecifications(productData, productTableColumnNames);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    public String updateProductValues() {
        try {
            ProductDao.updateProductEntry(productData);
            productTableColumnNames = ProductDao.fetchProductTableColumns(productData.getProductType());
            productTableSpecValues = new HashMap<>();
            for (int i = 0; i < productTableColumnNames.size(); i++) {
                productTableSpecValues.put(productTableColumnNames.get(i), productSpecValueList.get(i));
            }
            new ProductDao().updateProductSpecifications(productData, productTableColumnNames, productTableSpecValues);
            productsTableDataList = ProductDao.fetchProductTableData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    public String newProductPage() {
        productTypeList = ProductDao.fetchProductTypes();
        return SUCCESS;
    }

    public String displayNewProductSpecifications() {
        try {
            productTypeList = ProductDao.fetchProductTypes();
            productTableColumnNames = ProductDao.fetchProductTableColumns(productData.getProductType());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    public String productSpecificationsPage() {
        try {
            productData = ProductDao.findProductEntry(productData.getProductID());
            productTableColumnNames = ProductDao.fetchProductTableColumns(productData.getProductType());
            productSpecificationsData = ProductDao.fetchProductSpecifications(productData, productTableColumnNames);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    public String insertNewProduct() {
        try {
            productTableColumnNames = ProductDao.fetchProductTableColumns(productData.getProductType());
            productTableSpecValues = new HashMap<>();
            for (int i = 0; i < productTableColumnNames.size(); i++) {
                productTableSpecValues.put(productTableColumnNames.get(i), productSpecValueList.get(i));
            }
            int queryID = ProductDao.insertProductEntry(productData);
            productData.setProductID(queryID);
            ProductDao.insertProductSpecifications(productData, productTableColumnNames, productTableSpecValues);
            productsTableDataList = ProductDao.fetchProductTableData();
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
            ProductDao.createNewProductTable(productTableColumnNames, productData);
            ProductDao.insertIntoProductTypeTable(productData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    public String deleteProduct() {
        try {
            ProductDao.deleteProductEntry(productData);
            productsTableDataList = ProductDao.fetchProductTableData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    public ArrayList<ArrayList<String>> getCheckedProductTypeSpecificationsData() {
        return checkedProductTypeSpecificationsData;
    }

    public void setCheckedProductTypeSpecificationsData(ArrayList<ArrayList<String>> checkedProductTypeSpecificationsData) {
        this.checkedProductTypeSpecificationsData = checkedProductTypeSpecificationsData;
    }

    public Map<String, ArrayList<String>> getProductTypeSpecificationsData() {
        return productTypeSpecificationsData;
    }

    public void setProductTypeSpecificationsData(Map<String, ArrayList<String>> productTypeSpecificationsData) {
        this.productTypeSpecificationsData = productTypeSpecificationsData;
    }

    public List<String> getProductSpecValueList() {
        return productSpecValueList;
    }

    public void setProductSpecValueList(ArrayList<String> productSpecValueList) {
        this.productSpecValueList = productSpecValueList;
    }

    public List<String> getProductSpecificationsData() {
        return productSpecificationsData;
    }

    public void setProductSpecificationsData(ArrayList<String> productSpecificationsData) {
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

    public void setProductTableColumnNames(ArrayList<String> productTableColumnNames) {
        this.productTableColumnNames = productTableColumnNames;
    }


    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductTypeList(ArrayList<String> productTypeList) {
        this.productTypeList = productTypeList;
    }

    public List<String> getProductTypeList() {
        return productTypeList;
    }


    public void setProductsTableDataList(ArrayList<Product> productsTableDataList) {
        this.productsTableDataList = productsTableDataList;
    }

    public void setProductObject(ProductDao productObject) {
        this.productObject = productObject;
    }

    public ProductDao getProductObject() {
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

