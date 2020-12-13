package iit.controllers;

import iit.models.Admin;
import iit.service.AdminService;
import iit.service.IitCustService;
import iit.utils.IitUtil;
import iit.utils.ServiceFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class IitUserController implements Initializable {

    @FXML
    private TextField adminName;
    @FXML
    private TextField adminPassword;
    private IitCustService custService = ServiceFactory.getIitCustServiceInstance();
    private AdminService adminService = ServiceFactory.getAdminServiceInstance();

    public void edit() {
        adminPassword.setEditable(true);
        adminPassword.getStyleClass().add("input-group");
        adminPassword.setOnMouseClicked(event -> {
            adminPassword.setText("");
        });


        adminName.setEditable(true);
        adminName.getStyleClass().add("input-group");
        adminName.setOnMouseClicked(event -> {
            adminName.setText("");
        });

    }

    public void save() throws SQLException {
        //获取密码框的
        Admin admin=new Admin();
        String passString = adminPassword.getText().trim();
        String adName = adminName.getText().trim();
        if(adName.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("system message");
            alert.setHeaderText("system message");
            alert.setContentText("User name cannot be empty.");
            alert.showAndWait();
            return;
        }
        if(passString.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("system message");
            alert.setHeaderText("system message");
            alert.setContentText("Password cannot be empty.");
            alert.showAndWait();
            return;
        }
        String salt = IitUtil.randSalt();
        String hash = IitUtil.hashPasswd(salt, passString);
        admin.setPassword(hash);
        admin.setSalt(salt);
        admin.setAccount(adName);
        if(adminService.getAdminByAccount(adName) == null) {
            custService.addAdmin(admin);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("system message");
            alert.setHeaderText("system message");
            alert.setContentText("success");
            alert.showAndWait();
            Stage stage = (Stage) adminPassword.getScene().getWindow();
            stage.close();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("system message");
            alert.setHeaderText("system message");
            alert.setContentText("You input the user name already exists, please re-entry");
            alert.showAndWait();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
