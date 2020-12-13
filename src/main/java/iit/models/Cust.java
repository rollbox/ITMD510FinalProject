package iit.models;

import javafx.beans.property.SimpleStringProperty;

/**Cust
 * phone
 * name
 * address
 * products
 * sex
 */
public class Cust {
    private final SimpleStringProperty phone = new SimpleStringProperty("");
    private final SimpleStringProperty name = new SimpleStringProperty("");
    private final SimpleStringProperty address = new SimpleStringProperty("");
    private final SimpleStringProperty products = new SimpleStringProperty("");
    private final SimpleStringProperty sex = new SimpleStringProperty("");

    public Cust() {

    }

    public String getPhone() {
        return phone.get();
    }

    public SimpleStringProperty phoneProperty() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getAddress() {
        return address.get();
    }

    public SimpleStringProperty addressProperty() {
        return address;
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public String getProducts() {
        return products.get();
    }

    public SimpleStringProperty ageProperty() {
        return products;
    }

    public void setProducts(String products) {
        this.products.set(products);
    }

    public String getSex() {
        return sex.get();
    }

    public SimpleStringProperty sexProperty() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex.set(sex);
    }


}
