package iit.controllers;

import iit.models.Event;
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

public class IitEventController implements Initializable {
    //布局文件中的表格视图对象，用来显示数据库中读取的所有信息
    @FXML
    private TableView<Event> eventTable;

    //布局文件中的输入文本框对象，用来输入搜索关键词
    @FXML
    private TextField keywordsField;

    //模型数据集合，可以实时相应数据变化，无需刷新
    private ObservableList<Event> eventData = FXCollections.observableArrayList();

    //Service对象，从DAO工厂通过静态方法获得
    private IitCustService iitCustService = ServiceFactory.getIitCustServiceInstance();

    //集合，存放数据库表各种查询的结果
    private List<Event> eventList = null;

    //表格中的编辑列
    private TableColumn<Event, Event> editCol = new TableColumn<>("");

    //表格中的删除列
    private TableColumn<Event, Event> delCol = new TableColumn<>("");

    //初始化方法，通过调用对表格和列表下拉框的两个封装方法，实现数据初始化
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initTable();
    }

    //表格初始化方法
    private void initTable() {
        //水平方向不显示滚动条，表格的列宽会均匀分布
        eventTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        //1.调用查询所有的方法，
        eventList = iitCustService.getAllEvents();
        //将实体集合作为参数，调用显示数据的方法，可以在界面的表格中显示模型集合的值
        showEventData(eventList);

        //2.编辑列的相关设置
        editCol.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        editCol.setCellFactory(param -> new TableCell<Event, Event>() {
            //通过ComponentUtil工具类的静态方法，传入按钮文字和样式，获得一个按钮对象
            private final Button editButton = ComponentUtil.getButton("Edit", "blue-theme");

            @Override
            protected void updateItem(Event event, boolean empty) {
                super.updateItem(event, empty);
                if (event == null) {
                    setGraphic(null);
                    return;
                }
                setGraphic(editButton);
                editButton.setOnAction(event1 -> {
                    if(!IitUtil.isSuperNotify())
                        return;
                    TextInputDialog dialog = new TextInputDialog(" NEW SALESMANSHIP");
                    dialog.setTitle("EDIT MARKET EVENT: "+ event.getEventId());
                    dialog.setHeaderText("MARKET EVENT NAME：" + event.getEventName());
                    dialog.setContentText("PLEASE ENTER NEW SALESMANSHIP:");
                    Optional<String> result = dialog.showAndWait();
                    if (result.isPresent()) {
                        String salesmanship = result.get();
                        event.setSalesmanship(salesmanship);
                        iitCustService.updateEvent(event);
                    }
                });
            }
        });
        //将编辑列加入表格
        eventTable.getColumns().add(editCol);

        //3.删除列的相关设置
        delCol.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        delCol.setCellFactory(param -> new TableCell<Event, Event>() {
            private final Button deleteButton = ComponentUtil.getButton("DELETE", "warning-theme");

            @Override
            protected void updateItem(Event event, boolean empty) {
                super.updateItem(event, empty);
                if (event == null) {
                    setGraphic(null);
                    return;
                }
                setGraphic(deleteButton);
                //点击删除按钮，需要将这一行从表格移除，同时从底层数据库真正删除
                deleteButton.setOnAction(actionEvent -> {
                    if(!IitUtil.isSuperNotify())
                        return;
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("system message");
                    alert.setHeaderText("system message");
                    alert.setHeaderText("Event Name：" + event.getEventName());
                    alert.setContentText("Are you sure to delete this event?");
                    Optional<ButtonType> result = alert.showAndWait();
                    //点击了确认按钮，执行删除操作，同时移除一行模型数据
                    if (result.get() == ButtonType.OK) {
                        eventData.remove(event);
                        iitCustService.deleteEvent(event.getEventId());
                    }
                });
            }
        });
        //将除列加入表格
        eventTable.getColumns().add(delCol); 
    }

    //显示表格数据的方法
    private void showEventData(List<Event> eventList) {
        eventData.addAll(eventList);
        eventTable.setItems(eventData);
    }

    //弹出新增界面方法
    public void newEventStage() throws Exception {
        if(!IitUtil.isSuperNotify())
            return;
        Stage addStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/iitEventAdd.fxml"));
        AnchorPane root = fxmlLoader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/css/style.css");
        IitEventAddController iitEventAddController = fxmlLoader.getController();
        addStage.setTitle("CREATE MARKET EVENT");
        addStage.setResizable(false);
        addStage.setScene(scene);
        addStage.showAndWait();
        refreshTable();
    }

    public void refreshTable() {
        eventList = iitCustService.getAllEvents();
        eventData.clear();
        showEventData(eventList);
    }

    //根据关键词搜索方法
    public void search() {
        String keywords = keywordsField.getText().trim();
        eventList = iitCustService.getAllEventsByEventIdOrName(keywords);
        eventTable.getItems().clear();
        showEventData(eventList);
    }

}
