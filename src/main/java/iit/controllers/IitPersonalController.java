package iit.controllers;

import iit.models.Admin;
import iit.service.AdminService;
import iit.utils.IitUtil;
import iit.utils.ServiceFactory;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class IitPersonalController implements Initializable {

    @FXML
    private Label adminName;
    @FXML
    private Label adminType;
    @FXML
    private TextField adminPassword;

    private AdminService adminService = ServiceFactory.getAdminServiceInstance();

    private Admin admin;

    public void setAdmin(Admin admin) {
        this.admin = admin;

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                adminName.setText(admin.getAccount());
                adminType.setText(admin.getRole());
                adminPassword.setText("");
                adminPassword.setEditable(true);
            }
        });
    }

    public void edit() {
        //激活密码框为可编辑状态，同时改变样式
        adminPassword.setEditable(true);
        adminPassword.getStyleClass().add("input-group");
        adminPassword.setOnMouseClicked(event -> {
            adminPassword.setText("");
        });
    }

    public void save() {
        //获取密码框的值
        String passString = adminPassword.getText().trim();
        //更新管理员密码
        String salt = IitUtil.randSalt();
        String hash = IitUtil.hashPasswd(salt, passString);
        admin.setPassword(hash);
        admin.setSalt(salt);
        adminService.updateAdmin(admin);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("system message");
        alert.setHeaderText("system message");
        alert.setContentText("Your password has been revised successfully.");
        alert.showAndWait();
    }
}
