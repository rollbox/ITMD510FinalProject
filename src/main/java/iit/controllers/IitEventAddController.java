package iit.controllers;

import iit.models.Event;
import iit.models.Group;
import iit.models.Product;
import iit.service.IitCustService;
import iit.utils.IitUtil;
import iit.utils.ServiceFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class IitEventAddController implements Initializable {
    private ObservableList<Event> eventData = FXCollections.observableArrayList();

    @FXML
    private ComboBox<Product> productType;

    @FXML
    private ComboBox<Group> groupType;

    @FXML
    private TextField eventName ;
    @FXML
    private TextArea salesmanship ;

    private ObservableList<Product> productData = FXCollections.observableArrayList();

    private ObservableList<Group> groupData = FXCollections.observableArrayList();

    private IitCustService custService = ServiceFactory.getIitCustServiceInstance();

    private List<Product> productList = null;

    private List<Group> groupList = null;

    private Long typeId;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initComBox1();
        initComBox2();
    }


    //下拉框初始化方法
    private void initComBox1() {
        //1.到数据库查询所有的类别
        productList = custService.getAllProducts();
        //2.将typeList集合加入typeData模型数据集合
        productData.addAll(productList);
        //3.将数据模型设置给下拉框
        productType.setItems(productData);
        //4.设置显示名称
        productType.setConverter(new StringConverter<Product>() {
            @Override
            public String toString(Product object) {
                return object.getProductName();
            }

            @Override
            public Product fromString(String string) {
                return null;
            }
        });

    }

    //下拉框初始化方法
    private void initComBox2() {
        //1.到数据库查询所有的类别
        groupList = custService.getAllGroups("");
        //2.将typeList集合加入typeData模型数据集合
        groupData.addAll(groupList);
        //3.将数据模型设置给下拉框
        groupType.setItems(groupData);
        //4.设置显示名称
        groupType.setConverter(new StringConverter<Group>() {
            @Override
            public String toString(Group object) {
                return object.getGroupName();
            }

            @Override
            public Group fromString(String string) {
                return null;
            }
        });

    }

    public void addEvent(ActionEvent actionEvent) throws SQLException {
        //获取密码框的
        Event event = new Event();
        Product product = productType.getValue();
        Group group = groupType.getValue();
        String eventStrig = eventName.getText().trim();
        String tipStrig = salesmanship.getText().trim();
        if(product == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("system message");
            alert.setHeaderText("system message");
            alert.setContentText("Please select a product");
            alert.showAndWait();
            return;
        }
        if(group == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("system message");
            alert.setHeaderText("system message");
            alert.setContentText("Please select a group");
            alert.showAndWait();
            return;
        }
        if(tipStrig.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("system message");
            alert.setHeaderText("system message");
            alert.setContentText("Please input salesmanship");
            alert.showAndWait();
            return;
        }
        if(eventStrig.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("system message");
            alert.setHeaderText("system message");
            alert.setContentText("Please input event name");
            alert.showAndWait();
            return;
        }
        event.setEventId(IitUtil.getNextTime());
        event.setEventName(eventStrig);
        event.setSalesmanship(tipStrig);
        event.setGroupId(group.getGroupId());
        event.setGroupName(group.getGroupName());
        event.setProductId(product.getProductId());
        event.setProductName(product.getProductName());
        custService.addEvent(event);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("system message");
        alert.setHeaderText("system message");
        alert.setContentText("success");
        alert.showAndWait();
        //关闭注册页面
        Stage stage = (Stage) productType.getScene().getWindow();
        stage.close();
    }
}
