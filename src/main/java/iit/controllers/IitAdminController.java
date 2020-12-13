package iit.controllers;

import iit.models.Admin;
import iit.service.AdminService;
import iit.utils.ServiceFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class IitAdminController implements Initializable {
    @FXML
    private ListView<Admin> adminListView;

    private AdminService adminService = ServiceFactory.getAdminServiceInstance();

    private ObservableList<Admin> observableList = FXCollections.observableArrayList();

    private List<Admin> adminList = new ArrayList<>();

    private static final int MAX_THREADS = 4;
    //线程池配置
    private final Executor exec = Executors.newFixedThreadPool(MAX_THREADS, runnable -> {
        Thread t = new Thread(runnable);
        t.setDaemon(true);
        return t;
    });

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        adminList = adminService.getAllAdmins();
        observableList.setAll(adminList);
        adminListView.setItems(observableList);
        adminListView.setCellFactory(new Callback<ListView<Admin>, ListCell<Admin>>() {
            @Override
            public ListCell<Admin> call(ListView<Admin> param) {
                return new ListCell<Admin>() {
                    @Override
                    public void updateItem(Admin item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null && !empty) {
                            HBox container = new HBox();
                            container.setSpacing(20);
                            container.getStyleClass().add("box");
                            container.setMouseTransparent(true);
                            Label accountLabel = new Label(item.getAccount());
                            Label nameLabel = new Label(item.getAccount());
                            container.getChildren().addAll( accountLabel, nameLabel);
                            setGraphic(container);
                        }
                    }
                };
            }
        });
    }

}
