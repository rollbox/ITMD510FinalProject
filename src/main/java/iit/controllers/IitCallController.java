package iit.controllers;

import iit.models.Cust;
import iit.models.Event;
import iit.service.IitCustService;
import iit.utils.ServiceFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class IitCallController implements Initializable {
    @FXML
    private TableView<Cust> bigCustInfoTable;


    @FXML
    private TableView<Event> bigCustEventTable;


    @FXML
    private TextField keywordsField;

    private ObservableList<Cust> CustData = FXCollections.observableArrayList();

    private ObservableList<Event> EventData = FXCollections.observableArrayList();

    //Service对象，从DAO工厂通过静态方法获得
    private IitCustService iitCustService = ServiceFactory.getIitCustServiceInstance();


    //集合，存放数据库表各种查询的结果
    private List<Cust> allCustInfo = Collections.emptyList();
    private List<Event> allEvent = Collections.emptyList();

    //初始化方法，通过调用对表格和列表下拉框的两个封装方法，实现数据初始化
    @Override
    public void initialize(URL location, ResourceBundle resources) {
       initTable();
    }

    //表格初始化方法
    private void initTable() {
        //水平方向不显示滚动条，表格的列宽会均匀分布
        bigCustInfoTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        bigCustEventTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        //将实体集合作为参数，调用显示数据的方法，可以在界面的表格中显示模型集合的值
        showAllCustData(allCustInfo,allEvent);
    }

    //显示表格数据的方法
    private void showAllCustData(List<Cust> allCustInfo, List<Event> allEvent) {
        CustData.addAll(allCustInfo);
        EventData.addAll(allEvent);
        bigCustInfoTable.setItems(CustData);
        bigCustEventTable.setItems(EventData);
    }


    //根据关键词搜索方法
    public void search() {
        String keywords = keywordsField.getText().trim();
        allEvent = iitCustService.getAllEventsByEventIdOrName(keywords);
        allCustInfo = iitCustService.getAllCustsByEventIdOrName(keywords);
        bigCustInfoTable.getItems().clear();
        bigCustEventTable.getItems().clear();
        showAllCustData(allCustInfo, allEvent);
    }

}
