package iit.models;

import javafx.beans.property.SimpleStringProperty;

/**
 * group_name
 * group_id
 * groupDesc
 */
public class Group {
    public String getGroupName() {
        return groupName.get();
    }

    public SimpleStringProperty groupNameProperty() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName.set(groupName);
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

    public String getGroupDesc() {
        return groupDesc.get();
    }

    public SimpleStringProperty groupDescProperty() {
        return groupDesc;
    }

    public void setGroupDesc(String groupDesc) {
        this.groupDesc.set(groupDesc);
    }

    private final SimpleStringProperty groupName = new SimpleStringProperty("");
    private final SimpleStringProperty groupId = new SimpleStringProperty("");
    private final SimpleStringProperty groupDesc = new SimpleStringProperty("");

    public Group() {

    }


}
