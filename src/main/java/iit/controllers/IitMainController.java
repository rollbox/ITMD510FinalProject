package iit.controllers;

import iit.models.Admin;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class IitMainController implements Initializable {

    @FXML
    private StackPane mainContainer;
    @FXML
    private Label timeLabel;
    @FXML
    private ImageView adminAvatar;
    @FXML
    private Label adminName;
    @FXML
    private Label adminType;

    private Admin admin;

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //开启一个UI线程 ,将登录界面传过来的管理员信息显示在主界面的右上角
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                adminName.setText("Hi , "+admin.getAccount());
                adminType.setText(admin.getRole());
            }
        });
        //启一个线程，用来同步获取系统时间
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    //获取系统当前时间
                    LocalDateTime now = LocalDateTime.now();
                    //格式化时间
                    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                    String timeString = dateTimeFormatter.format(now);
                    //启一个UI线程
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            //将格式化后的日期时间显示在标签上
                            timeLabel.setText(timeString);
                        }
                    });
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        System.err.println("中断异常");
                    }
                }
            }
        }).start();

        try {
            AnchorPane anchorPane = new FXMLLoader(getClass().getResource("/views/iitDefault.fxml")).load();
            mainContainer.getChildren().add(anchorPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void listDefault() throws Exception {
        switchView("iitDefault.fxml");
    }

    public void iitProduct() throws Exception {
        switchView("iitProduct.fxml");
    }

    public void iitBigCust() throws Exception {
        switchView("iitBigCust.fxml");
    }

    public void iitBigEvent( )throws Exception {
        switchView("iitBigEvent.fxml");
    }

    public void iitGroup() throws Exception {
        switchView("iitGroup.fxml");
    }

    public void iitGroup_add( ) throws Exception {
        switchView("iitGroupAdd.fxml");
    }

    public void iitEvent() throws Exception {
        switchView("iitEvent.fxml");
    }

    public void listAdmin() throws Exception {
        switchView("iitAdmin.fxml");
    }

    public void listPersonal() throws Exception {
        mainContainer.getChildren().clear();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/iitPersonal.fxml"));
        AnchorPane anchorPane = fxmlLoader.load();
        mainContainer.getChildren().add(anchorPane);
        IitPersonalController personalController = fxmlLoader.getController();
        personalController.setAdmin(admin);
    }

    private void switchView(String fileName) throws Exception {
        //清空原有内容
        mainContainer.getChildren().clear();
        AnchorPane anchorPane = new FXMLLoader(getClass().getResource("/views/" + fileName)).load();
        mainContainer.getChildren().add(anchorPane);
    }

    //退出系统
    public void logout() throws Exception {
        //关闭主界面
        Stage mainStage = (Stage) mainContainer.getScene().getWindow();
        mainStage.close();
        //弹出登录界面
        Stage loginStage = new Stage();
        loginStage.setTitle("Marketing expert");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/iitLogin.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/css/style.css");
        loginStage.setMaximized(true);
        loginStage.getIcons().add(new Image("/img/logo.png"));
        loginStage.setScene(scene);
        loginStage.show();
    }

}
