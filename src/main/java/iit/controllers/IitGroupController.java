package iit.controllers;

import iit.models.Group;
import iit.service.IitCustService;
import iit.utils.ComponentUtil;
import iit.utils.IitUtil;
import iit.utils.ServiceFactory;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class IitGroupController implements Initializable {
    @FXML
    private TableView<Group>   groupTable;

    @FXML
    private TextField keywordsField;

    private ObservableList<Group> groupData = FXCollections.observableArrayList();

    //表格中的编辑列
    private TableColumn<Group, Group> editCol = new TableColumn<>("");

    //表格中的删除列
    private TableColumn<Group, Group> delCol = new TableColumn<>("");

    //Service对象，从DAO工厂通过静态方法获得
    private IitCustService iitCustService = ServiceFactory.getIitCustServiceInstance();


    //集合，存放数据库表各种查询的结果
    private List<Group> allGroup = null;

//    //集合，存放数据库表各种查询的结果
//    private  Cust  custInfo = null;

    //初始化方法，通过调用对表格和列表下拉框的两个封装方法，实现数据初始化
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initTable();
    }

    //表格初始化方法
    private void initTable() {
        //水平方向不显示滚动条，表格的列宽会均匀分布
        groupTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        //1.调用查询所有的方法，
        allGroup = iitCustService.getAllGroups("");
        //将实体集合作为参数，调用显示数据的方法，可以在界面的表格中显示模型集合的值
        showAllCustData(allGroup );

        //2.编辑列的相关设置
        editCol.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        editCol.setCellFactory(param -> new TableCell<Group, Group>() {
            //通过ComponentUtil工具类的静态方法，传入按钮文字和样式，获得一个按钮对象
            private final Button editButton = ComponentUtil.getButton("EDIT", "blue-theme");

            @Override
            protected void updateItem(Group group, boolean empty) {
                super.updateItem(group, empty);
                if (group == null) {
                    setGraphic(null);
                    return;
                }
                setGraphic(editButton);

                editButton.setOnAction(event -> {
                    if(!IitUtil.isSuperNotify())
                        return;
                    TextInputDialog dialog = new TextInputDialog(group.getGroupDesc());
                    dialog.setTitle("EDIT CUST GROUP:"+group.getGroupId());
                    dialog.setHeaderText(" CUST GROUP NAME：" + group.getGroupName());
                    dialog.setContentText("PLEASE ENTER NEW CUST GROUP DESC:");
                    Optional<String> result = dialog.showAndWait();
                    //确认输入了内容，避免NPE
                    if (result.isPresent()) {
                        //获取输入的新价格并转化成Double数据
                        String groupDesc = result.get();
                        group.setGroupDesc(groupDesc);
                        //更新信息
                        iitCustService.updateCustomerGroup(group);
                    }
                });
            }
        });
        //将编辑列加入表格
        groupTable.getColumns().add(editCol);

        //3.删除列的相关设置
        delCol.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        delCol.setCellFactory(param -> new TableCell<Group, Group>() {
            private final Button deleteButton = ComponentUtil.getButton("DELETE", "warning-theme");

            @Override
            protected void updateItem(Group group, boolean empty) {
                super.updateItem(group, empty);
                if (group == null) {
                    setGraphic(null);
                    return;
                }
//                deleteButton.setDisable(false);
                setGraphic(deleteButton);
                //点击删除按钮，需要将这一行从表格移除，同时从底层数据库真正删除
                deleteButton.setOnAction(event -> {
                    if(!IitUtil.isSuperNotify())
                        return;
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation dialog");
                    alert.setHeaderText("Group Name ：" + group.getGroupName());
                    alert.setContentText("Are you sure to delete this group?");
                    Optional<ButtonType> result = alert.showAndWait();
                    //点击了确认按钮，执行删除操作，同时移除一行模型数据
                    if (result.get() == ButtonType.OK) {
                        groupData.remove(group);
                        iitCustService.DeleteCustomerGroup(group.getGroupId());
                    }
                });
            }
        });
        //将除列加入表格
        groupTable.getColumns().add(delCol);
    }

    //显示表格数据的方法
    private void showAllCustData(List<Group> allGroup ) {
        groupData.addAll(allGroup);
        groupTable.setItems(groupData);
    }


    //根据关键词搜索方法
    public void viewCustomerGroup  () {
        groupTable.getItems().removeAll(groupData);
        //获得输入的查询关键字
        String keywords = keywordsField.getText().trim();
        allGroup = iitCustService.getAllGroups(keywords);
        groupData.addAll(allGroup);
        groupTable.setItems(groupData);
    }

    public void   createCustomerGroup() throws Exception {
        if(!IitUtil.isSuperNotify())
            return;
        Stage addStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/iitGroupAdd.fxml"));
        AnchorPane root = fxmlLoader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/css/style.css");
//        IitEventAddController iitEventAddController = fxmlLoader.getController();
        addStage.setTitle("CREATE NEW CUST GROUP");
        //界面大小不可变
        addStage.setResizable(false);
        addStage.setScene(scene);
        addStage.show();

    }


}
