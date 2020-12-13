package iit.models;

import javafx.beans.property.SimpleStringProperty;

/**
 * Product
 */
public class Product {
    private final SimpleStringProperty productId = new SimpleStringProperty("");
    private final SimpleStringProperty productName = new SimpleStringProperty("");
    public String getProductId() {
        return productId.get();
    }

    public SimpleStringProperty productIdProperty() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId.set(productId);
    }

    public String getProductName() {
        return productName.get();
    }

    public SimpleStringProperty productNameProperty() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName.set(productName);
    }


    public  Product() {
    }

}
