package iit;

import iit.models.Admin;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class LoginApp extends Application {

    public static Admin admin;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Marketing expert");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/iitLogin.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/css/style.css");
        primaryStage.setMaximized(true);
        primaryStage.getIcons().add(new Image("/img/logo.png"));
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}