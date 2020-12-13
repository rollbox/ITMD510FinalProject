package iit.controllers;

import iit.models.Product;
import iit.service.IitCustService;
import iit.utils.ComponentUtil;
import iit.utils.IitUtil;
import iit.utils.ServiceFactory;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class IitProductController implements Initializable {
    //获得布局文件中的表格对象
    @FXML
    private TableView<Product> productTable;

    //布局文件中的输入文本框对象，用来输入搜索关键词
    @FXML
    private TextField keywordsField;

    //定义ObservableList数据集合
    private ObservableList<Product> productData = FXCollections.observableArrayList();

    //通过工厂类获得ProductService的实例
    private IitCustService productService = ServiceFactory.getIitCustServiceInstance();

    //定义Product类型集合，用来存放数据库查询结果
    private List<Product> productList;
    ;

    private TableColumn<Product, Product> delCol = new TableColumn<>("");


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //水平方向不显示滚动条
        productTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        //在表格最后加入删除按钮
        delCol.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        delCol.setCellFactory(param -> new TableCell<Product, Product>() {
            private final Button deleteButton = ComponentUtil.getButton("DELETE", "warning-theme");

            @Override
            protected void updateItem(Product product, boolean empty) {
                super.updateItem(product, empty);
                if (product == null) {
                    setGraphic(null);
                    return;
                }
                setGraphic(deleteButton);
                //点击删除按钮，需要将这一行从表格移除，同时从底层数据库真正删除
                deleteButton.setOnAction(event -> {
                    if(!IitUtil.isSuperNotify())
                        return;
                    //删除操作之前，弹出确认对话框，点击确认按钮才删除
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("system message");
                    alert.setHeaderText("Product Name：" + product.getProductName());
                    alert.setContentText("Are you sure to delete this product?");
                    Optional<ButtonType> result = alert.showAndWait();
                    //点击了确认按钮，执行删除操作，同时移除一行模型数据
                    if (result.get() == ButtonType.OK) {
                        productData.remove(product);
                        //调用productService的删除类别方法
                        productService.deleteProduct(Long.parseLong(product.getProductId()));
                    }
                });
            }
        });
        //删除列加入表格
        productTable.getColumns().add(delCol);
        productList = productService.getAllProducts();
        //显示类别的表格数据
        showProductData(productList);
    }



    public void addProduct() {
        if(!IitUtil.isSuperNotify())
            return;
        //创建一个输入对话框
        TextInputDialog dialog = new TextInputDialog("New product");
        dialog.setHeaderText("ADD PRODUCT");
        dialog.setContentText("Product Name:");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            //获得输入的内容
            String productName = result.get();
            //创建一个Product对象，插入数据库，并返回主键
            Product product = new Product();
            product.setProductName(productName);
            product.setProductId( IitUtil.getNextTime());
            productService.addProduct(product);
            //加入ObservableList，刷新模型视图，不用重新查询数据库也可以立刻看到结果
            productData.add(product);
        }
    }

    public void search() {
        String keywords = keywordsField.getText().trim();
        productList = productService.getAllProductsByIdOrName(keywords);
        productTable.getItems().clear();
        showProductData(productList);
    }

    private void showProductData(List<Product> productList) {
        productData.addAll(productList);
        productTable.setItems(productData);
    }

}


