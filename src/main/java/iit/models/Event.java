package iit.models;

import javafx.beans.property.SimpleStringProperty;

/**
 *eventId
 * eventName
 * product_id
 * product_name
 * group_id
 * salesmanship
 */
public class Event {
    private final SimpleStringProperty eventId = new SimpleStringProperty("");
    private final SimpleStringProperty eventName = new SimpleStringProperty("");
    private final SimpleStringProperty productId = new SimpleStringProperty("");
    private final SimpleStringProperty productName = new SimpleStringProperty("");
    private final SimpleStringProperty groupId = new SimpleStringProperty("");
    private final SimpleStringProperty groupName = new SimpleStringProperty("");
    private final SimpleStringProperty salesmanship = new SimpleStringProperty("");

    public Event() {

    }
    public SimpleStringProperty eventIdProperty() {
        return eventId;
    }

    public SimpleStringProperty eventNameProperty() {
        return eventName;
    }

    public String getGroupName() {
        return groupName.get();
    }

    public SimpleStringProperty groupNameProperty() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName.set(groupName);
    }

    public String getEventId() {
        return eventId.get();
    }

    public SimpleStringProperty idProperty() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId.set(eventId);
    }

    public String getEventName() {
        return eventName.get();
    }

    public SimpleStringProperty nameProperty() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName.set(eventName);
    }

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

    public String getGroupId() {
        return groupId.get();
    }

    public SimpleStringProperty groupIdProperty() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId.set(groupId);
    }

    public String getSalesmanship() {
        return salesmanship.get();
    }

    public SimpleStringProperty salesmanshipProperty() {
        return salesmanship;
    }

    public void setSalesmanship(String salesmanship) {
        this.salesmanship.set(salesmanship);
    }
}
