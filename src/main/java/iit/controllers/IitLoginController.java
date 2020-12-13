package iit.controllers;

import iit.LoginApp;
import iit.models.Admin;
import iit.service.AdminService;
import iit.utils.ServiceFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class IitLoginController {
    @FXML
    private TextField accountField;
    @FXML
    private PasswordField passwordField;

    private AdminService adminService = ServiceFactory.getAdminServiceInstance();

    public void login() throws Exception {
        String account = accountField.getText().trim();
        String password = passwordField.getText().trim();
        if(account.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("User name cannot be empty.");
            alert.showAndWait();
            return;
        }
        //调用登录功能
        if (adminService.login(account, password)) {
            Stage mainStage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/iitMain.fxml"));
            BorderPane root = fxmlLoader.load();
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/css/style.css");
            mainStage.setTitle("Marketing expert");
            mainStage.setMaximized(true);
            mainStage.setScene(scene);
            mainStage.getIcons().add(new Image("/img/logo.png"));
            mainStage.show();

            Admin admin = adminService.getAdminByAccount(account);
            IitMainController mainController = fxmlLoader.getController();
            mainController.setAdmin(admin);
            LoginApp.admin = admin;
            Stage loginStage = (Stage) accountField.getScene().getWindow();
            loginStage.close();

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("system message");
            alert.setHeaderText("system message");
            alert.setContentText("The user name or password you entered is wrong, login failed!");
            alert.showAndWait();
        }
    }

    public void iitUser(ActionEvent actionEvent)  throws Exception {
        Stage addStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/iitUser.fxml"));
        AnchorPane root = fxmlLoader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/css/style.css");
//        IitEventAddController iitEventAddController = fxmlLoader.getController();
        addStage.setTitle("CREATE NEW USER");

        addStage.setResizable(false);
        addStage.setScene(scene);
        addStage.show();
    }
}
